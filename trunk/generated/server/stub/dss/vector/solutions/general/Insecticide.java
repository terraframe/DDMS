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
package dss.vector.solutions.general;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;

import dss.vector.solutions.RequiredAttributeException;
import dss.vector.solutions.entomology.InsecticideNotFoundException;
import dss.vector.solutions.ontology.Term;

public class Insecticide extends InsecticideBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237396178247L;

  public Insecticide()
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
    else if(this.getActiveIngredient() != null && this.getUnits() != null && this.getAmount() != null)
    {
      String activeIngredient = this.getActiveIngredient().getTermDisplayLabel().getValue();
      String units = this.getUnits().getTermDisplayLabel().getValue();
      
      return activeIngredient + " - " + this.getAmount() + " " + units;
    }
    
    return super.toString();
  }


  
  @Override
  protected String buildKey()
  {
    if (this.getUnits() != null && this.getActiveIngredient() != null)
    {
      String unit = this.getUnits().getKey();
      String ingredient = this.getActiveIngredient().getKey();

      return ingredient + " - " + this.getAmount() + " " + unit;
    }
    
    return this.getId();
  }
  
  public static Insecticide get(String activeIngredient, String unit, Double amount)
  {
    Term ingredient = Term.validateByDisplayLabel(activeIngredient, Insecticide.getActiveIngredientMd());
    Term u = Term.validateByDisplayLabel(unit, Insecticide.getUnitsMd());
    
    if(u == null)
    {
      String msg = "Units is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(Insecticide.getUnitsMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }
    
    if(amount == null)
    {
      String msg = "Amount is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(Insecticide.getAmountMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }
    
    if(ingredient == null)
    {
      String msg = "Active ingredient is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(Insecticide.getActiveIngredientMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }

    InsecticideQuery insecticideQuery = new InsecticideQuery(new QueryFactory());
    insecticideQuery.WHERE(insecticideQuery.getActiveIngredient().EQ(ingredient));
    insecticideQuery.WHERE(insecticideQuery.getUnits().EQ(u));
    insecticideQuery.WHERE(insecticideQuery.getAmount().EQ(amount));
    
    OIterator<? extends Insecticide> iterator = insecticideQuery.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      InsecticideNotFoundException ex = new InsecticideNotFoundException();
      ex.setActiveIngredient(ingredient);
      ex.setAmount(amount);
      ex.setUnits(u);
      ex.apply();
      throw ex;
    }
    finally
    {
      iterator.close();
    }    
  }  
}
