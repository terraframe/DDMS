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
/**
 * 
 */
package dss.vector.solutions.form;

import java.util.List;

import com.runwaysdk.constants.MdAttributeReferenceInfo;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdWebGeo;

import dss.vector.solutions.geo.ExtraFieldUniversal;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;

public class WebGeoBuilder extends WebAttributeBuilder implements Reloadable
{
  private GeoField geoField;

  private String[] extraUniversals;

  public WebGeoBuilder()
  {
    super();
  }

  public WebGeoBuilder(MdWebGeo mdWebGeo, GeoField geoField, String[] extraUniversals)
  {
    super(mdWebGeo);

    this.geoField = geoField;
    this.extraUniversals = extraUniversals;
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebGeoBuilder();
  }

  @Override
  public MdWebGeo getMdField()
  {
    return (MdWebGeo) super.getMdField();
  }

  public void setGeoField(GeoField geoField)
  {
    this.geoField = geoField;
  }

  public void setExtraUniversals(String[] extraUniversals)
  {
    this.extraUniversals = extraUniversals;
  }

  @Override
  protected void create()
  {
    super.create();

    MdWebGeo mdWebGeo = this.getMdField();

    MdAttributeReference mdAttributeReference = (MdAttributeReference) mdWebGeo.getDefiningMdAttribute();
    this.geoField.setGeoAttribute(mdAttributeReference);
    this.geoField.apply();

    if (this.extraUniversals != null)
    {
      for (String extraUniversal : extraUniversals)
      {
        this.geoField.addGeoHierarchies(GeoHierarchy.get(extraUniversal)).apply();
      }
    }
  }

  @Override
  protected MdAttributeConcrete newMdAttribute()
  {
    return new MdAttributeReference();
  }

  @Override
  protected void update()
  {
    super.update();

    this.geoField.apply();

    this.deleteExistingExtraUniversals();

    if (this.extraUniversals != null)
    {
      for (String extraUniversal : extraUniversals)
      {
        this.geoField.addGeoHierarchies(GeoHierarchy.get(extraUniversal)).apply();
      }
    }
  }

  private void deleteExistingExtraUniversals()
  {
    OIterator<? extends ExtraFieldUniversal> it = this.geoField.getAllGeoHierarchiesRel();

    try
    {
      List<? extends ExtraFieldUniversal> list = it.getAll();

      for (ExtraFieldUniversal universal : list)
      {
        universal.delete();
      }
    }
    finally
    {
      it.close();
    }
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    super.updateMdAttribute(mdAttribute);

    mdAttribute.setValue(MdAttributeReferenceInfo.REF_MD_ENTITY, MdClass.getMdClass(GeoEntity.CLASS).getId());
  }

  @Override
  public void delete()
  {
    MdWebGeo mdWebGeo = this.getMdField();
    MdAttribute mdAttribute = mdWebGeo.getDefiningMdAttribute();
    GeoField geoField = GeoField.getGeoField(mdAttribute);

    geoField.delete();

    super.delete();
  }
}
