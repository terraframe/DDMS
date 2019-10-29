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
package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;

public class InfectionAssayView extends InfectionAssayViewBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = -236995216;
  
  public InfectionAssayView()
  {
    super();
  }
  
  public void populateView(InfectionAssay concrete)
  {
    this.setUniqueAssayId(concrete.getUniqueAssayId());
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setIdentMethod(concrete.getIdentMethod());
    this.setInfected(concrete.getInfected());
    this.setMosquitoId(concrete.getMosquitoId());
    this.setNumberPositive(concrete.getNumberPositive());
    this.setNumberTested(concrete.getNumberTested());
    this.setParasite(concrete.getParasite());
    this.setSex(concrete.getSex());
    this.setSpecies(concrete.getSpecies());
    this.setTestMethod(concrete.getTestMethod());
    this.setDisease(concrete.getDisease());
  }

  private void populateConcrete(InfectionAssay concrete)
  {
    concrete.setUniqueAssayId(this.getUniqueAssayId());

    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, COLLECTION))
    {
      concrete.setCollection(this.getCollection());
    }

    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, IDENTMETHOD))
    {
      concrete.setIdentMethod(this.getIdentMethod());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, INFECTED))
    {
      concrete.setInfected(this.getInfected());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, MOSQUITOID))
    {
      concrete.setMosquitoId(this.getMosquitoId());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, NUMBERPOSITIVE))
    {
      concrete.setNumberPositive(this.getNumberPositive());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, NUMBERTESTED))
    {
      concrete.setNumberTested(this.getNumberTested());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, PARASITE))
    {
      concrete.setParasite(this.getParasite());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, SEX))
    {
      concrete.setSex(this.getSex());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, SPECIES))
    {
      concrete.setSpecies(this.getSpecies());
    }
      
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, TESTMETHOD))
    {
      concrete.setTestMethod(this.getTestMethod());
    }
    
    if (this.isNew() && this.getDisease() != null) {
    	concrete.setDisease(this.getDisease());
    }
  }

  private void buildAttributeMap(InfectionAssay concrete)
  {
    new AttributeNotificationMap(concrete, InfectionAssay.UNIQUEASSAYID, this, InfectionAssayView.UNIQUEASSAYID);
    new AttributeNotificationMap(concrete, InfectionAssay.ID, this, InfectionAssayView.CONCRETEID);
    new AttributeNotificationMap(concrete, InfectionAssay.COLLECTION, this, InfectionAssayView.COLLECTION);
    new AttributeNotificationMap(concrete, InfectionAssay.IDENTMETHOD, this, InfectionAssayView.IDENTMETHOD);
    new AttributeNotificationMap(concrete, InfectionAssay.INFECTED, this, InfectionAssayView.INFECTED);
    new AttributeNotificationMap(concrete, InfectionAssay.MOSQUITOID, this, InfectionAssayView.MOSQUITOID);
    new AttributeNotificationMap(concrete, InfectionAssay.NUMBERPOSITIVE, this, InfectionAssayView.NUMBERPOSITIVE);
    new AttributeNotificationMap(concrete, InfectionAssay.NUMBERTESTED, this, InfectionAssayView.NUMBERTESTED);
    new AttributeNotificationMap(concrete, InfectionAssay.PARASITE, this, InfectionAssayView.PARASITE);
    new AttributeNotificationMap(concrete, InfectionAssay.SEX, this, InfectionAssayView.SEX);
    new AttributeNotificationMap(concrete, InfectionAssay.SPECIES, this, InfectionAssayView.SPECIES);
    new AttributeNotificationMap(concrete, InfectionAssay.TESTMETHOD, this, InfectionAssayView.TESTMETHOD);
    new AttributeNotificationMap(concrete, InfectionAssay.DISEASE, this, InfectionAssayView.DISEASE);
  }

  @Override
  public void apply()
  {
    InfectionAssay concrete = UniqueAssayUtil.getOrCreateAssay(InfectionAssay.class, this.getUniqueAssayId());
    if(!concrete.isNew())
    {
      concrete.appLock();
    }
    
    if (this.hasConcrete())
    {
      concrete = InfectionAssay.lock(this.getConcreteId());
    }

    // Build the attribute map between InfectionAssay and
    // InfectionAssayView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      InfectionAssay.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  } 

  @Transaction
  public static InfectionAssayView[] applyAll(InfectionAssayView[] views)
  {
    UniqueAssayUtil.validateUniqueAssayIds(views);
    
    for(InfectionAssayView view : views)
    {
      view.apply();
    }
    
    return views;
  }

}
