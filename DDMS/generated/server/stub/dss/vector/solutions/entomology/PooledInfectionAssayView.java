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

public class PooledInfectionAssayView extends PooledInfectionAssayViewBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = 416207252;
  
  public PooledInfectionAssayView()
  {
    super();
  }
 
  public void populateView(PooledInfectionAssay concrete)
  {
    this.setUniqueAssayId(concrete.getUniqueAssayId());
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setIdentMethod(concrete.getIdentMethod());
    this.setInfected(concrete.getInfected());
    this.setPoolId(concrete.getPoolId());
    this.setNumberPositive(concrete.getNumberPositive());
    this.setPoolsTested(concrete.getPoolsTested());
    this.setMosquitosTested(concrete.getMosquitosTested());
    this.setParasite(concrete.getParasite());
    this.setSex(concrete.getSex());
    this.setSpecies(concrete.getSpecies());
    this.setTestMethod(concrete.getTestMethod());
    this.setDisease(concrete.getDisease());
  }

  private void populateConcrete(PooledInfectionAssay concrete)
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
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, POOLID))
    {
      concrete.setPoolId(this.getPoolId());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, NUMBERPOSITIVE))
    {
      concrete.setNumberPositive(this.getNumberPositive());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, POOLSTESTED))
    {
      concrete.setPoolsTested(this.getPoolsTested());
    }
    
    if(UniqueAssayUtil.allowAttributeUpdate(this, concrete, MOSQUITOSTESTED))
    {
      concrete.setMosquitosTested(this.getMosquitosTested());
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

  private void buildAttributeMap(PooledInfectionAssay concrete)
  {
    new AttributeNotificationMap(concrete, PooledInfectionAssay.UNIQUEASSAYID, this, PooledInfectionAssay.UNIQUEASSAYID);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.ID, this, PooledInfectionAssayView.CONCRETEID);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.COLLECTION, this, PooledInfectionAssayView.COLLECTION);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.IDENTMETHOD, this, PooledInfectionAssayView.IDENTMETHOD);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.INFECTED, this, PooledInfectionAssayView.INFECTED);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.POOLID, this, PooledInfectionAssayView.POOLID);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.NUMBERPOSITIVE, this, PooledInfectionAssayView.NUMBERPOSITIVE);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.POOLSTESTED, this, PooledInfectionAssayView.POOLSTESTED);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.MOSQUITOSTESTED, this, PooledInfectionAssayView.MOSQUITOSTESTED);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.PARASITE, this, PooledInfectionAssayView.PARASITE);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.SEX, this, PooledInfectionAssayView.SEX);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.SPECIES, this, PooledInfectionAssayView.SPECIES);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.TESTMETHOD, this, PooledInfectionAssayView.TESTMETHOD);
    new AttributeNotificationMap(concrete, PooledInfectionAssay.DISEASE, this, PooledInfectionAssayView.DISEASE);
  }

  @Override
  public void apply()
  {
    PooledInfectionAssay concrete = UniqueAssayUtil.getOrCreateAssay(PooledInfectionAssay.class, this.getUniqueAssayId());
    if(!concrete.isNew())
    {
      concrete.appLock();
    }
    
    if (this.hasConcrete())
    {
      concrete = PooledInfectionAssay.lock(this.getConcreteId());
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
      PooledInfectionAssay.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  } 

  @Transaction
  public static PooledInfectionAssayView[] applyAll(PooledInfectionAssayView[] views)
  {
    UniqueAssayUtil.validateUniqueAssayIds(views);
    
    for(PooledInfectionAssayView view : views)
    {
      view.apply();
    }
    
    return views;
  }

}
