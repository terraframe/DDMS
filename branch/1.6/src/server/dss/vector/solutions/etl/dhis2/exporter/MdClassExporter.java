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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.TableQuery;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;
import com.runwaysdk.system.metadata.MdAttributeNumber;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessDTO;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.etl.dhis2.AbstractDHIS2Connector;
import dss.vector.solutions.etl.dhis2.DHIS2IdMapping;
import dss.vector.solutions.etl.dhis2.DHIS2IdMappingQuery;
import dss.vector.solutions.etl.dhis2.response.DHIS2EmptyDatasetException;
import dss.vector.solutions.etl.dhis2.response.GeoFieldRequiredException;
import dss.vector.solutions.etl.dhis2.util.DHIS2IdCache;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.QueryBuilder;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchQuery;

/**
 * This class is responsible for exporting an MdClass to DHIS2.
 * 
 * @author rrowlands
 */
public class MdClassExporter
{
  private static Logger logger = LoggerFactory.getLogger(MdClassExporter.class);
  
  protected MdClass mdClass;
  
  protected SavedSearch savedSearch;
  
  protected ValueQuery valueQuery;
  
  protected AbstractDHIS2Connector dhis2;
  
  private DHIS2IdCache idCache;
  
  private ArrayList<MdAttribute> categoryAttrs = new ArrayList<MdAttribute>();
  
  private Map<MdAttribute, Set<String>> categoryMetadataMap = new HashMap<MdAttribute, Set<String>>();
  
  private final int pageSize = 1000;
  
  private long teiCount;
  
  static String[] skipAttrs = new String[]{
    MdBusinessDTO.CACHEALGORITHM, MdBusinessDTO.TABLENAME, MdBusinessDTO.KEYNAME,
    MdBusinessDTO.BASECLASS, MdBusinessDTO.BASESOURCE, MdBusinessDTO.DTOCLASS, MdBusinessDTO.DTOSOURCE, MdBusinessDTO.STUBCLASS, MdBusinessDTO.STUBDTOCLASS, MdBusinessDTO.STUBDTOSOURCE, MdBusinessDTO.STUBSOURCE,
    MdAttributeConcreteDTO.GETTERVISIBILITY, MdAttributeConcreteDTO.INDEXTYPE, MdAttributeConcreteDTO.INDEXNAME, MdAttributeConcreteDTO.COLUMNNAME,
    MdAttributeConcreteDTO.DEFININGMDCLASS, MdAttributeConcreteDTO.ENTITYDOMAIN, MdAttributeConcreteDTO.OWNER, MdAttributeConcreteDTO.SETTERVISIBILITY, MdAttributeConcreteDTO.SITEMASTER
  };
  
  public MdClassExporter(MdClass mdClass, AbstractDHIS2Connector dhis2)
  {
    this.mdClass = mdClass;
    this.dhis2 = dhis2;
    this.idCache = new DHIS2IdCache(dhis2);
  }
  
  @Transaction
  public void export()
  {
    gatherPrereqs();
    
    validateExport();
    
    JSONObject metadata = new JSONObject();
    
    createCategoryOptionsMetadata(metadata);
    
    createCategoryMetadata(metadata);
    
    System.out.println(metadata.toString());
  }
  
  protected void gatherPrereqs()
  {
    QueryFactory qf = new QueryFactory();
    SavedSearchQuery ssq = new SavedSearchQuery(qf);
    ssq.WHERE(ssq.getMaterializedTable().EQ(mdClass));
    savedSearch = ssq.getIterator().next();
    
    String queryClass = QueryConstants.getQueryClass(this.savedSearch.getQueryType());
    valueQuery = QueryBuilder.getValueQuery(queryClass, this.savedSearch.getQueryXml(), this.savedSearch.getConfig(), null, null, null, this.savedSearch.getDisease());
  }
  
  protected void validateExport()
  {
    // Check #1 : We have to have rows in our dataset
    QueryFactory qf = new QueryFactory();
    TableQuery tq = qf.tableQuery(mdClass.definesType());
    teiCount = tq.getCount();
    
    if (teiCount == 0)
    {
      DHIS2EmptyDatasetException ex = new DHIS2EmptyDatasetException();
      throw ex;
    }
    
    
    // Check #2 : Attribute validation
    int numGeos = 0;
    boolean hasYear = false;
    OIterator<? extends MdAttribute> mdAttrs = mdClass.getAllAttribute();
    for (MdAttribute mdAttr : mdAttrs)
    {
      if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
          !ArrayUtils.contains(MdClassExporter.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
        )
      {
        Selectable sel = null;
        if (valueQuery.hasSelectableRef(mdAttr.getAttributeName()))
        {
          sel = valueQuery.getSelectableRef(mdAttr.getAttributeName());
        }
        
        if (mdAttr instanceof MdAttributeReference)
        {
          MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
          
          if (reference.definesType().equals(GeoEntity.CLASS))
          {
            numGeos++;
          }
          else
          {
            categoryAttrs.add(mdAttr);
          }
        }
        else if (mdAttr instanceof MdAttributeNumber)
        {
          if (!(sel instanceof AggregateFunction))
          {
            throw new RuntimeException("All number columns must be aggregated. [" + mdAttr.getDisplayLabel().getValue() + "] is not aggregated.");
          }
        }
        // TODO : Time columns aren't actually an instance of moment
        else if (mdAttr instanceof MdAttributeCharacter)
        {
          if (mdAttr.getAttributeName().equals("dategroup_year"))
          {
            hasYear = true;
            continue;
          }
          else if (mdAttr.getAttributeName().contains("dategroup"))
          {
            continue;
          }
          
          categoryAttrs.add(mdAttr);
        }
        else
        {
          categoryAttrs.add(mdAttr);
        }
      }
    }
    
    if (numGeos == 0)
    {
      GeoFieldRequiredException ex = new GeoFieldRequiredException();
      throw ex;
    }
    else if (numGeos > 1)
    {
      throw new RuntimeException("Query must contain exactly one geo column."); // TODO : Custom exception
    }
    else if (!hasYear)
    {
      throw new RuntimeException("Query must contain 'Calendar year' column.");
    }
  }
  
  protected void createCategoryOptionsMetadata(JSONObject json)
  {
    try
    {
      JSONArray options = new JSONArray();
    
      QueryFactory qf = new QueryFactory();

      ValueQuery vq = qf.valueQuery();
      TableQuery tq = qf.tableQuery(mdClass.definesType());
      for (MdAttribute attr : categoryAttrs)
      {
        vq.SELECT(tq.get(attr.getAttributeName()));
      }
      
      OIterator<ValueObject> it = vq.getIterator();
      for (ValueObject val : it)
      {
        for (MdAttribute mdAttr : categoryAttrs)
        {
          String attrVal = val.getValue(mdAttr.getAttributeName());
          if (attrVal == null) { continue; }
          
          String name = null;
          String code = null;
          String shortName = null;
          String rwId = null;
          
          if (mdAttr instanceof MdAttributeReference)
          {
            MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
            
            if (reference.definesType().equals(Term.CLASS))
            {
              Term term = Term.get(attrVal);
              
              name = term.getTermDisplayLabel().getValue();
              shortName = name;
              code = term.getTermId();
              rwId = term.getId();
            }
          }
          else if (mdAttr instanceof MdAttributeNumber || mdAttr instanceof MdAttributeBoolean)
          {
            continue;
          }
          else
          {
            name = attrVal;
            shortName = name;
            code = attrVal;
            rwId = mdClass.getTypeName() + "." + mdAttr.getAttributeName() + "." + attrVal;
            
            if (rwId.length() > 64)
            {
              rwId = rwId.substring(0, 63);
            }
          }
          
          if (name != null)
          {
            DHIS2IdMappingQuery idq = new DHIS2IdMappingQuery(new QueryFactory());
            idq.WHERE(idq.getRunwayId().EQ(rwId));
            OIterator<? extends DHIS2IdMapping> mappingIt = idq.getIterator();
            try
            {
              if (mappingIt.hasNext())
              {
                continue;
              }
            }
            finally
            {
              mappingIt.close();
            }
            
            String dhis2Id = idCache.next();
            
            JSONObject option = new JSONObject();
            option.put("code", code);
            option.put("name", name);
            option.put("shortName", shortName);
            option.put("id", dhis2Id);
            options.put(option);
            
            DHIS2IdMapping map = new DHIS2IdMapping();
            map.setRunwayId(rwId);
            map.setDhis2Id(dhis2Id);
            map.apply();
            
            Set<String> catSet = categoryMetadataMap.get(mdAttr);
            if (catSet == null)
            {
              catSet = new HashSet<String>();
            }
            catSet.add(dhis2Id);
            categoryMetadataMap.put(mdAttr, catSet);
          }
        }
      }
    
      json.put("categoryOptions", options);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void createCategoryMetadata(JSONObject json)
  {
    try
    {
      JSONArray categories = new JSONArray();
      
      Set<MdAttribute> keys = categoryMetadataMap.keySet();
      
      for (MdAttribute mdAttr : keys)
      {
        Set<String> categoryOptions = categoryMetadataMap.get(mdAttr);
        
        String dhis2Id = idCache.next();
        
        JSONObject category = new JSONObject();
        category.put("name", mdAttr.getDisplayLabel().getValue());
        category.put("id", dhis2Id);
        category.put("dataDimensionType", "DISAGGREGATION");
        
        JSONArray jsonCategoryOptions = new JSONArray();
        for (String option : categoryOptions)
        {
          JSONObject jsonCategoryOption = new JSONObject();
          jsonCategoryOption.put("id", option);
          jsonCategoryOptions.put(jsonCategoryOption);
        }
        category.put("categoryOptions", jsonCategoryOptions);
        
        categories.put(category);
        
        DHIS2IdMapping map = new DHIS2IdMapping();
        map.setRunwayId(mdAttr.getId());
        map.setDhis2Id(dhis2Id);
        map.apply();
      }
      
      json.put("categories", categories);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
}
