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

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AVG;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.MAX;
import com.runwaysdk.query.MIN;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SUM;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.TableQuery;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdAttributeNumber;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessDTO;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.etl.dhis2.AbstractDHIS2Connector;
import dss.vector.solutions.etl.dhis2.response.DHIS2EmptyDatasetException;
import dss.vector.solutions.etl.dhis2.response.DHIS2TrackerResponseProcessor;
import dss.vector.solutions.etl.dhis2.response.GeoFieldRequiredException;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
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
    
    createCategoryOptionsMetadata(metadata); // #8
    
    createCategoryMetadata(metadata); // #9
    
    createCategoryCombinationMetadata(metadata); // #10
    
    createCategoryOptionCombinationMetadata(metadata); // #11
    
    createDataElementsMetadata(metadata); // #12
    
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
        // Numbers are not exported as categories.
        else if (mdAttr instanceof MdAttributeNumber)
        {
          if (!(sel instanceof AggregateFunction))
          {
            throw new RuntimeException("All number columns must be aggregated. [" + mdAttr.getDisplayLabel().getValue() + "] is not aggregated.");
          }
        }
        // Most columns are defaulted to character (time, user, etc)
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
      
      Set<String> noDuplicatesSet = new HashSet<String>(); // Prevents us from exporting the same object many times.
      
      OIterator<ValueObject> it = vq.getIterator();
      for (ValueObject val : it)
      {
        for (MdAttribute mdAttr : categoryAttrs)
        {
          String attrVal = val.getValue(mdAttr.getAttributeName());
          if (attrVal == null) { continue; }
          
          // We need to figure out values for all of these if we're to export. The problem is that the values will vary depending on the attribute.
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
          else // This typically matches to characters (which is most columns)
          {
            name = attrVal;
            shortName = name;
            code = attrVal;
            rwId = mdAttr.getAttributeName() + "." + attrVal; // TODO : We may need to filter out values from the attrVal here
            
            if (rwId.length() > 64)
            {
              rwId = rwId.substring(0, 63);
            }
          }
          
          // If we set values to those variables, then we know we have categories that need to be exported.
          if (name != null)
          {
            if (noDuplicatesSet.contains(rwId))
            {
              continue;
            }
            else
            {
              noDuplicatesSet.add(rwId);
            }
            
            String dhis2Id = DHIS2ExportUtil.queryAndMapIds(rwId, idCache);
            
            JSONObject option = new JSONObject();
            option.put("code", code);
            option.put("name", name);
            option.put("shortName", shortName);
            option.put("id", dhis2Id);
            options.put(option);
            
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
        String dhis2Id = DHIS2ExportUtil.queryAndMapIds(mdAttr.getId(), idCache);
        
        // Basic identifier info about the category
        JSONObject category = new JSONObject();
        category.put("name", mdAttr.getDisplayLabel().getValue());
        category.put("id", dhis2Id);
        category.put("dataDimensionType", "DISAGGREGATION");
        
        // A list of all the category options associated with this category
        Set<String> categoryOptions = categoryMetadataMap.get(mdAttr);
        JSONArray jsonCategoryOptions = new JSONArray();
        for (String option : categoryOptions)
        {
          JSONObject jsonCategoryOption = new JSONObject();
          jsonCategoryOption.put("id", option);
          jsonCategoryOptions.put(jsonCategoryOption);
        }
        category.put("categoryOptions", jsonCategoryOptions);
        
        categories.put(category);
      }
      
      json.put("categories", categories);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void createCategoryCombinationMetadata(JSONObject json)
  {
    try
    {
      JSONArray categoryCombos = new JSONArray();
      JSONObject categoryCombo = new JSONObject();
      
      String dhis2Id = DHIS2ExportUtil.queryAndMapIds(mdClass.getId(), idCache);
      
      categoryCombo.put("name", mdClass.getDisplayLabel().getValue());
      categoryCombo.put("id", dhis2Id);
      categoryCombo.put("dataDimensionType", "DISAGGREGATION");
      
      // We can get the category ids by reading the json we generated earlier.
      JSONArray categoryIds = new JSONArray();
      JSONArray allCategories = json.getJSONArray("categories");
      for (int i = 0; i < allCategories.length(); ++i)
      {
        String categoryId = allCategories.getJSONObject(i).getString("id");
        
        categoryIds.put(new JSONObject().put("id", categoryId));
      }
      categoryCombo.put("categories", categoryIds);
      
      categoryCombos.put(categoryCombo);
      json.put("categoryCombos", categoryCombos);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void createCategoryOptionCombinationMetadata(JSONObject json)
  {
    try
    {
      // Fetch the default category combo
      HTTPResponse response = dhis2.apiGet("categoryOptionCombos", new NameValuePair[]{
          new NameValuePair("filter", "displayName:eq:default")
      });
      DHIS2TrackerResponseProcessor.validateStatusCode(response);
      
      JSONObject defaultCatJS = response.getJSONObject();
      JSONArray defaultCombos = defaultCatJS.getJSONArray("categoryOptionCombos");
      JSONObject defaultCombo = defaultCombos.getJSONObject(0);
      
      String categoryComboId = DHIS2ExportUtil.queryAndMapIds(mdClass.getId(), idCache);
      JSONObject categoryCombo = new JSONObject();
      categoryCombo.put("id", categoryComboId);
      
      // Do a cross product on our attributes
      JSONArray combos = new JSONArray();
      
      JSONArray allCategoryOptions = json.getJSONArray("categoryOptions");
      for (int i = 0; i < allCategoryOptions.length(); ++i)
      {
        JSONObject option1 = allCategoryOptions.getJSONObject(i);
        
        // Add default to the cross product
        JSONObject defaultCross = new JSONObject();
        defaultCross.put("name", option1.getString("name") + ", " + defaultCombo.getString("displayName"));
        defaultCross.put("id", DHIS2ExportUtil.queryAndMapIds(option1.getString("id") + defaultCombo.getString("id"), idCache));
        
        defaultCross.put("categoryCombo", categoryCombo);
        
        JSONArray crossCategoryOptions = new JSONArray();
        crossCategoryOptions.put(new JSONObject().put("id", option1.getString("id")));
        crossCategoryOptions.put(new JSONObject().put("id", defaultCombo.getString("id")));
        defaultCross.put("categoryOptions", crossCategoryOptions);
        
        combos.put(defaultCross);
        
        
        for (int j = 0; j < allCategoryOptions.length(); ++j)
        {
          JSONObject option2 = allCategoryOptions.getJSONObject(j);
          
          if (option1 != option2)
          {
            String runwayId = option1.getString("id") + option2.getString("id");
            String dhis2Id = DHIS2ExportUtil.queryAndMapIds(runwayId, idCache);
            
            JSONObject combo = new JSONObject();
            combo.put("name", option1.getString("name") + ", " + option2.getString("name"));
            combo.put("id", dhis2Id);
            
            combo.put("categoryCombo", categoryCombo);
            
            JSONArray categoryOptions = new JSONArray();
            categoryOptions.put(new JSONObject().put("id", option1.getString("id")));
            categoryOptions.put(new JSONObject().put("id", option2.getString("id")));
            combo.put("categoryOptions", categoryOptions);
            
            combos.put(combo);
          }
        }
      }
      
      json.put("categoryOptionCombos", combos);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void createDataElementsMetadata(JSONObject json)
  {
    try
    {
      // Fetch the org unit levels
      HTTPResponse response = dhis2.apiGet("filledOrganisationUnitLevels", new NameValuePair[]{});
      DHIS2TrackerResponseProcessor.validateStatusCode(response);
      
      JSONArray levels = response.getJSONArray();
      JSONArray aggregationLevels = new JSONArray();
      for (int i = 0; i < levels.length(); ++i)
      {
        JSONObject level = levels.getJSONObject(i);
        aggregationLevels.put(level.getInt("level"));
      }
      
      
      String categoryComboId = DHIS2ExportUtil.queryAndMapIds(mdClass.getId(), idCache);
      
      JSONArray dataElements = new JSONArray();
      
      OIterator<? extends MdAttribute> mdAttrs = mdClass.getAllAttribute();
      for (MdAttribute mdAttr : mdAttrs)
      {
        if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
            !ArrayUtils.contains(MdClassExporter.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
          )
        {
          if (mdAttr instanceof MdAttributeNumber)
          {
            Selectable sel = valueQuery.getSelectableRef(mdAttr.getAttributeName());
            
            String dhis2Id = DHIS2ExportUtil.queryAndMapIds(mdAttr.getId(), idCache);
            
            JSONObject dataElement = new JSONObject();
            
            dataElement.put("id", dhis2Id);
            
            String name = mdClass.getDisplayLabel().getValue() + " " + mdAttr.getDisplayLabel().getValue();
            dataElement.put("name", name);
            dataElement.put("shortName", name);
            
            if (sel instanceof AVG)
            {
              dataElement.put("aggregationType", "AVG");
            }
            else if (sel instanceof SUM)
            {
              dataElement.put("aggregationType", "SUM");
            }
            else if (sel instanceof MIN)
            {
              dataElement.put("aggregationType", "MIN");
            }
            else if (sel instanceof MAX)
            {
              dataElement.put("aggregationType", "MAX");
            }
            
            dataElement.put("domainType", "AGGREGATE");
            
            if (mdAttr instanceof MdAttributeInteger)
            {
              dataElement.put("valueType", "INTEGER");
            }
            else
            {
              dataElement.put("valueType", "NUMBER");
            }
            
            dataElement.put("zeroIsSignificant", true);
            
            dataElement.put("categoryCombo", new JSONObject().put("id", categoryComboId));
            
            dataElement.put("aggregationLevels", aggregationLevels);
            
            dataElements.put(dataElement);
          }
        }
      }
      
      json.put("dataElements", dataElements);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
}
