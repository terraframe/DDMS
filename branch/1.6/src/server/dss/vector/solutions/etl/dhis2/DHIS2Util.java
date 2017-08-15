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
package dss.vector.solutions.etl.dhis2;

import java.sql.Savepoint;

import com.runwaysdk.dataaccess.DuplicateDataException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdAttributeDouble;
import com.runwaysdk.system.metadata.MdAttributeFloat;
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdAttributeLong;
import com.runwaysdk.system.metadata.MdAttributeText;

import dss.vector.solutions.etl.dhis2.util.DHIS2IdCache;
import dss.vector.solutions.geo.generated.GeoEntity;

/**
 * @author rrowlands
 */
public class DHIS2Util implements Reloadable
{
  public static void mapIds(String runwayId, String dhis2Id)
  {
    Savepoint sp = Database.setSavepoint();
    try
    {
      BasicIdMapping map = new BasicIdMapping();
      map.setRunwayId(runwayId);
      map.setDhis2Id(dhis2Id);
      map.apply();
    }
    catch (DuplicateDataException e)
    {
      Database.rollbackSavepoint(sp);
    }
    finally
    {
      Database.releaseSavepoint(sp);
    }
  }
  
  public static OrgUnit getOrgUnitFromGeoEntity(GeoEntity geo)
  {
    GeoMapQuery query = new GeoMapQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geo));
    OIterator<? extends GeoMap> mappingIt = query.getIterator();
    try
    {
      if (mappingIt.hasNext())
      {
        return mappingIt.next().getOrgUnit();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      mappingIt.close();
    }
  }
  
  public static String getRunwayIdFromDhis2Id(String dhis2Id)
  {
    BasicIdMappingQuery query = new BasicIdMappingQuery(new QueryFactory());
    query.WHERE(query.getDhis2Id().EQ(dhis2Id));
    OIterator<? extends BasicIdMapping> mappingIt = query.getIterator();
    try
    {
      if (mappingIt.hasNext())
      {
        return mappingIt.next().getRunwayId();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      mappingIt.close();
    }
  }
  
  public static String getDhis2IdFromRunwayId(String runwayId)
  {
    BasicIdMappingQuery query = new BasicIdMappingQuery(new QueryFactory());
    query.WHERE(query.getRunwayId().EQ(runwayId));
    OIterator<? extends BasicIdMapping> mappingIt = query.getIterator();
    try
    {
      if (mappingIt.hasNext())
      {
        return mappingIt.next().getDhis2Id();
      }
      else
      {
        throw new RuntimeException("Expected a basic mapping to exist with runwayId [" + runwayId + "].");
      }
    }
    finally
    {
      mappingIt.close();
    }
  }

  public static String queryAndMapIds(String runwayId, DHIS2IdCache idCache)
  {
    BasicIdMappingQuery query = new BasicIdMappingQuery(new QueryFactory());
    query.WHERE(query.getRunwayId().EQ(runwayId));
    OIterator<? extends BasicIdMapping> mappingIt = query.getIterator();
    try
    {
      if (mappingIt.hasNext())
      {
        BasicIdMapping mapping = mappingIt.next();
        
        return mapping.getDhis2Id();
      }
      else
      {
        String id = idCache.next();
        
        BasicIdMapping map = new BasicIdMapping();
        map.setRunwayId(runwayId);
        map.setDhis2Id(id);
        map.apply();
        
        return id;
      }
    }
    finally
    {
      mappingIt.close();
    }
  }
  
  public static String getDHIS2TypeFromMdAttribute(MdAttribute mdAttr)
  {
    String valueType = null;
    
    // Complete list of valid DHIS2 (API version 25) datatypes:
    // UNIT_INTERVAL, LETTER, BOOLEAN, NUMBER, TEXT, DATE, LONG_TEXT, FILE_RESOURCE, USERNAME, TRACKER_ASSOCIATE, COORDINATE, INTEGER_POSITIVE, DATETIME, EMAIL, TRUE_ONLY, INTEGER, INTEGER_ZERO_OR_POSITIVE, ORGANISATION_UNIT, TIME, INTEGER_NEGATIVE, PERCENTAGE, PHONE_NUMBER
    
    if (mdAttr instanceof MdAttributeDate)
    {
      valueType = "DATE";
    }
    else if (mdAttr instanceof MdAttributeCharacter)
    {
      valueType = "TEXT";
    }
    else if (mdAttr instanceof MdAttributeText)
    {
      valueType = "LONG_TEXT";
    }
    else if (mdAttr instanceof MdAttributeBoolean)
    {
      valueType = "BOOLEAN";
    }
    else if (mdAttr instanceof MdAttributeInteger)
    {
      valueType = "INTEGER";
    }
    else if (mdAttr instanceof MdAttributeLong)
    {
      valueType = "NUMBER";
    }
    else if (mdAttr instanceof MdAttributeDouble)
    {
      valueType = "NUMBER";
    }
    else if (mdAttr instanceof MdAttributeFloat)
    {
      valueType = "NUMBER";
    }
    else
    {
      valueType = "TEXT";
    }
    
    return valueType;
  }
}
