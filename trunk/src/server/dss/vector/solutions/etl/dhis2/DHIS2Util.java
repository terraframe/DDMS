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
import java.util.ArrayList;
import java.util.Collections;

import com.runwaysdk.dataaccess.DuplicateDataException;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSpoof;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.sql.MdAttributeConcrete_SQL;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdAttributeDouble;
import com.runwaysdk.system.metadata.MdAttributeFloat;
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdAttributeLong;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdAttributeText;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.etl.dhis2.util.DHIS2IdCache;
import dss.vector.solutions.ontology.BrowserRoot;
import dss.vector.solutions.ontology.BrowserRootView;
import dss.vector.solutions.ontology.Term;

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
  
  public static OrgUnit getOrgUnitFromGeoEntity(String geoId)
  {
    if (geoId == null) { return null; }
    
    GeoMapQuery query = new GeoMapQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoId));
    query.AND(query.getConfirmed().EQ(true));
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
  
  public static String getDhis2IdFromRunwayId(MdAttribute mdAttr, ValueQuery vq)
  {
    return getDhis2IdFromRunwayId(getUniqueIdentifier(mdAttr, vq));
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
  
  private static String getUniqueIdentifier(MdAttribute mdAttr, ValueQuery vq)
  {
    String uniqueId = null;
    
    // Our best bet is to map it to the term attribute root
    if (mdAttr instanceof MdAttributeReference)
    {
      MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
      
      if (reference.definesType().equals(Term.CLASS))
      {
        MdAttributeDAOIF mdAttrDAOIF = MdAttributeDAO.get(mdAttr.getId());
        
        ArrayList<String> rootIds = new ArrayList<String>();
        
        BrowserRootView[] roots = BrowserRoot.getAttributeRoots(mdAttrDAOIF.definedByClass().definesType(), mdAttrDAOIF.definesAttribute());
        for (BrowserRootView root : roots)
        {
          rootIds.add(root.getTermId());
        }
        
        Collections.sort(rootIds);
        
        int strlen = 64;
        if (rootIds.size() == 5) { strlen = 50; }
        if (rootIds.size() == 6) { strlen = 45; }
        if (rootIds.size() == 7) { strlen = 40; }
        if (rootIds.size() >= 8) { strlen = 30; }
        
        uniqueId = "";
        for (String rootId : rootIds)
        {
          uniqueId += rootId.substring(0, strlen);
        }
      }
    }
    
    // Second best is to map it to the defining attribute.
    if (uniqueId == null && vq.hasSelectableRef(mdAttr.getAttributeName()))
    {
      Selectable sel = vq.getSelectableRef(mdAttr.getAttributeName());
      
      if ( !(sel instanceof SelectableSQL) && !(sel instanceof SelectableSpoof) && !(sel.getMdAttributeIF() instanceof MdAttributeConcrete_SQL) && sel.getMdAttributeIF() != null )
      {
        MdAttributeConcreteDAOIF attr = sel.getMdAttributeIF();
        uniqueId = attr.getId();
      }
    }
    
    // Third best is the attribute name.
    if (uniqueId == null)
    {
      uniqueId = mdAttr.getAttributeName();
    }
    
    return uniqueId;
  }
  
  /**
   * 
   * @param mdAttr The MdAttribute originating from the MdTable which represents a column in our persisted table.
   * @param idCache
   * @param vq
   * @return
   */
  public static String queryAndMapIds(MdAttribute mdAttr, DHIS2IdCache idCache, ValueQuery vq)
  {
    return queryAndMapIds(getUniqueIdentifier(mdAttr, vq), idCache);
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
