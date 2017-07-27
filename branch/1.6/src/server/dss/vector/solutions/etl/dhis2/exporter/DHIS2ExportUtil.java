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
package dss.vector.solutions.etl.dhis2.exporter;

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

import dss.vector.solutions.etl.dhis2.DHIS2IdMapping;
import dss.vector.solutions.etl.dhis2.DHIS2IdMappingQuery;
import dss.vector.solutions.etl.dhis2.util.DHIS2IdCache;

/**
 * @author rrowlands
 */
public class DHIS2ExportUtil
{
  public static String queryAndMapIds(String runwayId, DHIS2IdCache idCache)
  {
    DHIS2IdMappingQuery idq = new DHIS2IdMappingQuery(new QueryFactory());
    idq.WHERE(idq.getRunwayId().EQ(runwayId));
    OIterator<? extends DHIS2IdMapping> mappingIt = idq.getIterator();
    try
    {
      if (mappingIt.hasNext())
      {
        DHIS2IdMapping mapping = mappingIt.next();
        
        return mapping.getDhis2Id();
      }
      else
      {
        String id = idCache.next();
        
        DHIS2IdMapping map = new DHIS2IdMapping();
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
