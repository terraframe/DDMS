/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.entomology.assay;

import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.entomology.ControlMortalityException;
import dss.vector.solutions.entomology.ResistanceProperty;
import dss.vector.solutions.ontology.Term;

public class LarvaeDiscriminatingDoseAssay extends LarvaeDiscriminatingDoseAssayBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = 1236962664268L;

  public LarvaeDiscriminatingDoseAssay()
  {
    super();
  }
    
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if(this.getUniqueAssayId() != null)
    {
      return this.getUniqueAssayId();
    }
    else if(this.getCollection() != null && this.getInsecticide() != null)
    {
      return "(" + this.getCollection().getCollectionId() + ", " + this.getInsecticide().toString() + ")";
    }
    
    return super.toString();
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    new QuantityDeadValidator(this).validate();
  }

  @Override
  public void validateControlTestMortality()
  {
    if(this.getControlTestMortality() != null && this.getControlTestMortality() > 20)
    {
      String msg = "The mortality rate of the control collection exceeds 20% invalidating this test";

      ControlMortalityException e = new ControlMortalityException(msg);
      e.apply();

      throw e;
    }
  }

  @Override
  public void apply()
  {
    UniqueAssayUtil.setUniqueAssayId(this);
    
    validateControlTestMortality();
    validateQuantityDead();

    float mortality = 0.0F;
    int live = 0;

    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
    {
      mortality = (float) ( this.getQuantityDead() ) * 100 / this.getQuantityTested();
      live = this.getQuantityTested() - this.getQuantityDead();
    }

    this.setQuantityLive(live);
    this.setMortality(mortality);

    if(this.getControlTestMortality() != null && this.getControlTestMortality() > 5)
    {
      //Use abbots formula to correct the mortality rate
      //Corrected % = 100 * (T - C) / (100 - C) (WHO/CDC/NTD/WHOPES/GCDPP/2006.3)
      //T = % mortality of the treated population
      //C = % mortality of the control population

      float corrected = 100.0F * (mortality - this.getControlTestMortality()) / (100.0F - this.getControlTestMortality());
      this.setMortality(corrected);
    }

    super.apply();


    if (this.isResistant() && this.getInsecticide() != null && this.getCollection() != null)
    {
      Term activeIngredient = this.getInsecticide().getActiveIngredient();
      String label = activeIngredient.getTermDisplayLabel().getValue(Session.getCurrentLocale());
      String collectionId = this.getCollection().getCollectionId();

      ResistantCollection info = new ResistantCollection();
      info.setActiveIngredient(label);
      info.setCollectionId(collectionId);
      info.throwIt();
    }
  }

  protected boolean isResistant()
  {
    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.LARVAE_DDA_RESISTANCE);

    return ( this.getMortality() < resistant );
  }

  protected boolean isPotentiallyResistant()
  {
    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.LARVAE_DDA_RESISTANCE);
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE);

    return ( resistant < this.getMortality() && this.getMortality() <= susceptible );
  }

  protected boolean isSusceptible()
  {
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE);

    return ( this.getMortality() > susceptible );
  }

  public static String getResistanceSQL(String[] labels)
  {

    String assayTable = MdBusiness.getMdBusiness(LarvaeDiscriminatingDoseAssay.CLASS).getTableName();

    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.LARVAE_DDA_RESISTANCE);
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE);

    String mortality = LarvaeDiscriminatingDoseAssay.MORTALITY;

    return CollectionAssay.getCollectionResistanceSQL(assayTable, mortality, resistant.toString(), susceptible.toString(),labels);
  }
}
