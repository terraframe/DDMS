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
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Runway SDK(tm).  If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.kaleidoscope.data.etl;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.gis.geo.GeoNodeGeometry;
import com.runwaysdk.system.gis.geo.GeoNodeGeometryQuery;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.util.IDGenerator;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;

public class TargetFieldPoint extends TargetFieldCoordinate implements TargetFieldPointIF, Reloadable
{
  private String id;

  public TargetFieldPoint()
  {
    this.id = IDGenerator.nextID();
  }

  @Override
  public FieldValue getValue(MdAttributeConcreteDAOIF mdAttribute, Transient source)
  {
    Coordinate coord = super.getCoordinate(mdAttribute, source);

    if (coord != null)
    {
      Point point = this.getGeometryFactory().createPoint(coord);

      return new FieldValue(this.getGeometryHelper().getGeoPoint(point));
    }

    return new FieldValue();
  }

  @Override
  public void persist(TargetBinding binding)
  {
    MdAttribute latitudeAttribute = MdAttribute.getByKey(binding.getSourceView().definesType() + "." + this.getLatitudeSourceAttributeName());
    MdAttribute longitudeAttribute = MdAttribute.getByKey(binding.getSourceView().definesType() + "." + this.getLongitudeSourceAttributeName());
    MdWebAttribute targetAttribute = MdWebAttribute.getByKey(this.getKey());

    TargetFieldPointBinding field = new TargetFieldPointBinding();
    field.setTarget(binding);
    field.setTargetAttribute(targetAttribute);
    field.setLatitudeAttribute(latitudeAttribute);
    field.setLongitudeAttribute(longitudeAttribute);
    field.setColumnLabel(this.getLabel());
    field.apply();
  }

  @Override
  public String getId()
  {
    return this.id;
  }

  public GeoNodeGeometry getNode()
  {
    GeoNodeGeometryQuery query = new GeoNodeGeometryQuery(new QueryFactory());
    query.WHERE(query.getGeometryAttribute().EQ(MdAttribute.getByKey(this.getKey())));

    OIterator<? extends GeoNodeGeometry> iterator = query.getIterator();

    try
    {
      GeoNodeGeometry node = iterator.next();

      return node;
    }
    finally
    {
      iterator.close();
    }
  }

  public TargetFieldDerivedBinding getDerivedBinding(MdAttributeReference geoEntityAttribute)
  {
    TargetFieldDerivedBindingQuery query = new TargetFieldDerivedBindingQuery(new QueryFactory());
    query.WHERE(query.getTargetAttribute().EQ(geoEntityAttribute));

    OIterator<? extends TargetFieldDerivedBinding> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        TargetFieldDerivedBinding binding = iterator.next();

        return binding;
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public JSONObject toJSON() throws JSONException
  {
    GeoNodeGeometry node = this.getNode();

    MdAttribute identifierAttribute = node.getIdentifierAttribute();
    MdAttributeReference geoEntityAttribute = node.getGeoEntityAttribute();

    JSONObject object = new JSONObject();
    object.put("id", this.id);
    object.put("label", this.getLabel());
    object.put("latitude", this.getLatitudeLabel());
    object.put("longitude", this.getLongitudeLabel());
    object.put("featureLabel", identifierAttribute.getDisplayLabel().getValue());

    TargetFieldDerivedBinding binding = this.getDerivedBinding(geoEntityAttribute);

    if (binding != null)
    {
      object.put("location", "DERIVE");
      object.put("universal", binding.getUniversalId());
    }
    else
    {
      object.put("location", geoEntityAttribute.getDisplayLabel().getValue());
      object.put("universal", "");
    }

    return object;

  }
}
