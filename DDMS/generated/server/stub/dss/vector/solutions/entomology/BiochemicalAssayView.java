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

public class BiochemicalAssayView extends BiochemicalAssayViewBase implements
    com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = -1386315237;

  public BiochemicalAssayView()
  {
    super();
  }

  public void populateView(BiochemicalAssay concrete)
  {
    this.setUniqueAssayId(concrete.getUniqueAssayId());
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setIdentMethod(concrete.getIdentMethod());
    this.setIsofemale(concrete.getIsofemale());
    this.setMosquitoId(concrete.getMosquitoId());
    this.setNumberElevated(concrete.getNumberElevated());
    this.setNumberTested(concrete.getNumberTested());
    this.setGeneration(concrete.getGeneration());
    this.setSex(concrete.getSex());
    this.setSpecies(concrete.getSpecies());
    this.setAssay(concrete.getAssay());
  }

  private void populateConcrete(BiochemicalAssay concrete)
  {
    concrete.setUniqueAssayId(this.getUniqueAssayId());

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, COLLECTION))
    {
      concrete.setCollection(this.getCollection());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, IDENTMETHOD))
    {
      concrete.setIdentMethod(this.getIdentMethod());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, ISOFEMALE))
    {
      concrete.setIsofemale(this.getIsofemale());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, MOSQUITOID))
    {
      concrete.setMosquitoId(this.getMosquitoId());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, NUMBERELEVATED))
    {
      concrete.setNumberElevated(this.getNumberElevated());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, NUMBERTESTED))
    {
      concrete.setNumberTested(this.getNumberTested());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, GENERATION))
    {
      concrete.setGeneration(this.getGeneration());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, SEX))
    {
      concrete.setSex(this.getSex());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, SPECIES))
    {
      concrete.setSpecies(this.getSpecies());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, ASSAY))
    {
      concrete.setAssay(this.getAssay());
    }
  }

  private void buildAttributeMap(BiochemicalAssay concrete)
  {
    new AttributeNotificationMap(concrete, BiochemicalAssay.UNIQUEASSAYID, this,
        BiochemicalAssayView.UNIQUEASSAYID);
    new AttributeNotificationMap(concrete, BiochemicalAssay.ID, this, BiochemicalAssayView.CONCRETEID);
    new AttributeNotificationMap(concrete, BiochemicalAssay.COLLECTION, this,
        BiochemicalAssayView.COLLECTION);
    new AttributeNotificationMap(concrete, BiochemicalAssay.IDENTMETHOD, this,
        BiochemicalAssayView.IDENTMETHOD);
    new AttributeNotificationMap(concrete, BiochemicalAssay.ISOFEMALE, this,
        BiochemicalAssayView.ISOFEMALE);
    new AttributeNotificationMap(concrete, BiochemicalAssay.MOSQUITOID, this,
        BiochemicalAssayView.MOSQUITOID);
    new AttributeNotificationMap(concrete, BiochemicalAssay.NUMBERELEVATED, this,
        BiochemicalAssayView.NUMBERELEVATED);
    new AttributeNotificationMap(concrete, BiochemicalAssay.NUMBERTESTED, this,
        BiochemicalAssayView.NUMBERTESTED);
    new AttributeNotificationMap(concrete, BiochemicalAssay.GENERATION, this,
        BiochemicalAssayView.GENERATION);
    new AttributeNotificationMap(concrete, BiochemicalAssay.SEX, this, BiochemicalAssayView.SEX);
    new AttributeNotificationMap(concrete, BiochemicalAssay.SPECIES, this, BiochemicalAssayView.SPECIES);
    new AttributeNotificationMap(concrete, BiochemicalAssay.ASSAY, this, BiochemicalAssayView.ASSAY);
  }

  @Override
  public void apply()
  {
    BiochemicalAssay concrete = UniqueAssayUtil.getOrCreateAssay(BiochemicalAssay.class,
        this.getUniqueAssayId());
    if (!concrete.isNew())
    {
      concrete.appLock();
    }

    if (this.hasConcrete())
    {
      concrete = BiochemicalAssay.lock(this.getConcreteId());
    }

    // Build the attribute map between BiochemicalAssay and
    // BiochemicalAssayView for error handling
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
      BiochemicalAssay.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public static BiochemicalAssayView[] applyAll(BiochemicalAssayView[] views)
  {
    UniqueAssayUtil.validateUniqueAssayIds(views);
    
    for (BiochemicalAssayView view : views)
    {
      view.apply();
    }

    return views;
  }

}
