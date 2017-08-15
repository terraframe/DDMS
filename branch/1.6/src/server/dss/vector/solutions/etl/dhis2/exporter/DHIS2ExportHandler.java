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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
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
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdAttributeNumber;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessDTO;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.etl.dhis2.AbstractDHIS2Connector;
import dss.vector.solutions.etl.dhis2.CalendarYearRequiredException;
import dss.vector.solutions.etl.dhis2.DHIS2ExportableDataset;
import dss.vector.solutions.etl.dhis2.DHIS2Util;
import dss.vector.solutions.etl.dhis2.MaxOneGeoColumnException;
import dss.vector.solutions.etl.dhis2.NumbersMustBeAggregatedException;
import dss.vector.solutions.etl.dhis2.OrgUnit;
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
public class DHIS2ExportHandler implements Reloadable
{
  private static Logger logger = LoggerFactory.getLogger(DHIS2ExportHandler.class);
  
  protected DHIS2ExportableDataset exportable;
  
  protected MdClass mdClass;
  
  protected SavedSearch savedSearch;
  
  protected ValueQuery valueQuery;
  
  protected AbstractDHIS2Connector dhis2;
  
  private DHIS2IdCache idCache;
  
  private ArrayList<MdAttribute> categoryAttrs = new ArrayList<MdAttribute>();
  
  // The keys are categoryAttrs. The values are a set of all ids of category options associated with that attribute.
  private Map<MdAttribute, Set<String>> categoryMetadataMap = new HashMap<MdAttribute, Set<String>>();
  
  private String categoryComboId = null;
  
  private final int pageSize = 1000;
  
  private long teiCount;
  
  private MdAttribute periodMdAttr = null;
  
  static String[] skipAttrs = new String[]{
    MdBusinessDTO.CACHEALGORITHM, MdBusinessDTO.TABLENAME, MdBusinessDTO.KEYNAME,
    MdBusinessDTO.BASECLASS, MdBusinessDTO.BASESOURCE, MdBusinessDTO.DTOCLASS, MdBusinessDTO.DTOSOURCE, MdBusinessDTO.STUBCLASS, MdBusinessDTO.STUBDTOCLASS, MdBusinessDTO.STUBDTOSOURCE, MdBusinessDTO.STUBSOURCE,
    MdAttributeConcreteDTO.GETTERVISIBILITY, MdAttributeConcreteDTO.INDEXTYPE, MdAttributeConcreteDTO.INDEXNAME, MdAttributeConcreteDTO.COLUMNNAME,
    MdAttributeConcreteDTO.DEFININGMDCLASS, MdAttributeConcreteDTO.ENTITYDOMAIN, MdAttributeConcreteDTO.OWNER, MdAttributeConcreteDTO.SETTERVISIBILITY, MdAttributeConcreteDTO.SITEMASTER
  };
  
  public DHIS2ExportHandler(DHIS2ExportableDataset exportable, AbstractDHIS2Connector dhis2)
  {
    this.exportable = exportable;
    this.mdClass = exportable.getQueryRef();
    this.dhis2 = dhis2;
    this.idCache = new DHIS2IdCache(dhis2);
  }
  
  public void export()
  {
    JSONObject payload = new JSONObject();
    exportTransaction1(payload);
//    exportTransaction2(payload);
  }
  
  @Transaction
  protected void exportTransaction1(JSONObject payload)
  {
    gatherPrereqs();
    
    validateExport();
    
    createCategoryOptionsMetadata(payload); // #8
    
    createCategoryMetadata(payload); // #9
    
    createCategoryCombinationMetadata(payload); // #10
    
    createCategoryOptionCombinationMetadata(payload); // #11
    
    createDataElementsMetadata(payload); // #12
    
    createDataSetMetadata(payload); // #13
    
    createDataValues(payload); // #14
    
//    System.out.println(payload.toString());
    
    try
    {
      PrintWriter writer = new PrintWriter(CommonProperties.getDeployRoot() + "/dhis2-export.json", "UTF-8");
      writer.println(payload.toString());
      writer.close();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
//  @Transaction
//  protected void exportTransaction2(JSONObject payload)
//  {
////    createDataValues(payload); // #14
//    
//    System.out.println(payload.toString());
//    
//    try
//    {
//      PrintWriter writer = new PrintWriter(CommonProperties.getDeployRoot() + "/dhis2-export.json", "UTF-8");
//      writer.println(payload.toString());
//      writer.close();
//    }
//    catch (IOException e)
//    {
//      throw new RuntimeException(e);
//    }
//  }
  
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
          !ArrayUtils.contains(DHIS2ExportHandler.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
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
            NumbersMustBeAggregatedException ex = new NumbersMustBeAggregatedException();
            ex.setNumberColumn(mdAttr.getDisplayLabel().getValue());
            throw ex;
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
      MaxOneGeoColumnException ex = new MaxOneGeoColumnException();
      throw ex;
    }
    else if (!hasYear)
    {
      CalendarYearRequiredException ex = new CalendarYearRequiredException();
      throw ex;
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
          String runwayId = null;
          
          if (mdAttr instanceof MdAttributeReference)
          {
            MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
            
            if (reference.definesType().equals(Term.CLASS))
            {
              Term term = Term.get(attrVal);
              
              name = term.getTermDisplayLabel().getValue();
              shortName = name;
              code = term.getTermId();
              runwayId = term.getId();
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
            String text = mdAttr.getAttributeName() + "." + attrVal; // TODO : We may need to filter out values from the attrVal here
            
            if (text.length() > 128)
            {
              text = text.substring(0, 127);
            }
            
            runwayId = text;
          }
          
          // If we set values to those variables, then we know we have categories that need to be exported.
          if (name != null)
          {
            if (noDuplicatesSet.contains(runwayId))
            {
              continue;
            }
            else
            {
              noDuplicatesSet.add(runwayId);
            }
            
            String dhis2Id = DHIS2Util.queryAndMapIds(runwayId, idCache);
            
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
        String dhis2Id = DHIS2Util.queryAndMapIds(mdAttr.getId(), idCache);
        
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
      
      categoryComboId = DHIS2Util.queryAndMapIds(mdClass.getId() + "_catCombo", idCache);
      
      categoryCombo.put("name", mdClass.getDisplayLabel().getValue());
      categoryCombo.put("id", categoryComboId);
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
      JSONArray optCombos = new JSONArray();
    
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
        ArrayList<String> optComboNames = new ArrayList<String>();
        ArrayList<String> optComboDHIds = new ArrayList<String>();
        ArrayList<String> optComboRWIds = new ArrayList<String>();
        
        JSONArray catOpts = new JSONArray();
        
        for (MdAttribute mdAttr : categoryAttrs)
        {
          String attrVal = val.getValue(mdAttr.getAttributeName());
          if (attrVal == null) { continue; } // TODO : Add the default here
          
          // We need to figure out values for all of these if we're to export. The problem is that the values will vary depending on the attribute.
          String name = null;
          String runwayId = null;
          
          if (mdAttr instanceof MdAttributeReference)
          {
            MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
            
            if (reference.definesType().equals(Term.CLASS))
            {
              Term term = Term.get(attrVal);
              
              name = term.getTermDisplayLabel().getValue();
              runwayId = term.getId();
            }
          }
          else if (mdAttr instanceof MdAttributeNumber || mdAttr instanceof MdAttributeBoolean)
          {
            continue;
          }
          else // This typically matches to characters (which is most columns)
          {
            name = attrVal;
            String text = mdAttr.getAttributeName() + "." + attrVal; // TODO : We may need to filter out values from the attrVal here
            
            if (text.length() > 128)
            {
              text = text.substring(0, 127);
            }
            
            runwayId = text;
          }
          
          // If we set values to those variables, then we know we have categories that need to be exported.
          if (name != null)
          {
            String dhis2Id = DHIS2Util.getDhis2IdFromRunwayId(runwayId);
            
            optComboNames.add(name);
            optComboDHIds.add(dhis2Id);
            optComboRWIds.add(runwayId);
            
            catOpts.put(new JSONObject().put("id", dhis2Id));
          }
        }
        
        if (optComboNames.size() > 0)
        {
          String comboRWId = StringUtils.join(optComboRWIds, "");
          
          if (noDuplicatesSet.contains(comboRWId))
          {
            continue;
          }
          else
          {
            noDuplicatesSet.add(comboRWId);
          }
          
          JSONObject optCombo = new JSONObject();
          
          optCombo.put("name", StringUtils.join(optComboNames, ", "));
          optCombo.put("id", DHIS2Util.queryAndMapIds(comboRWId, idCache));
          optCombo.put("categoryCombo", new JSONObject().put("id", categoryComboId));
          optCombo.put("categoryOptions", catOpts);
          
          optCombos.put(optCombo);
        }
      }
    
      json.put("categoryOptionCombos", optCombos);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  // This code is for doing a cross product of category options (which maybe we'll use at some point in the future)
//  protected void createCategoryOptionCombinationMetadata(JSONObject json)
//  {
//    try
//    {
//      // Fetch the default category combo
//      HTTPResponse response = dhis2.apiGet("categoryOptionCombos", new NameValuePair[]{
//          new NameValuePair("filter", "displayName:eq:default")
//      });
//      DHIS2TrackerResponseProcessor.validateStatusCode(response);
//      
//      JSONObject defaultCatJS = response.getJSONObject();
//      JSONArray defaultCombos = defaultCatJS.getJSONArray("categoryOptionCombos");
//      JSONObject defaultCombo = defaultCombos.getJSONObject(0);
//      
//      JSONObject categoryCombo = new JSONObject();
//      categoryCombo.put("id", categoryComboId);
//      
//      // Do a cross product on our attributes
//      JSONArray combos = new JSONArray();
//      
//      Set<MdAttribute> categoryAttrs = categoryMetadataMap.keySet();
//      
//      // Prime the loop
//      int[] indicies = new int[categoryAttrs.size()];
//      for (int i = 0; i < categoryAttrs.size(); ++i)
//      {
//        indicies[i] = 0;
//      }
//      int curIndex = categoryAttrs.size()-1;
//      
//      // When our 0th index is maxed, we know we're done.
//      while (indicies[0] < categoryMetadataMap.get(0).size())
//      {
//        // if you ++ curIndex past the max, go back one and ++ it
//        
//        // if curIndex is not past the max, and curIndex is not at the largest index, then skip it forward an index
//        
//        ++curIndex;
//      }
//      
//      
////      for (MdAttribute catAtt : categoryAttrs)
////      {
////        // Add default to the cross product
////        JSONObject defaultCross = new JSONObject();
////        defaultCross.put("name", option1.getString("name") + ", " + defaultCombo.getString("displayName"));
////        defaultCross.put("id", DHIS2Util.queryAndMapIds(option1.getString("id") + defaultCombo.getString("id"), idCache));
////        
////        defaultCross.put("categoryCombo", categoryCombo);
////        
////        JSONArray crossCategoryOptions = new JSONArray();
////        crossCategoryOptions.put(new JSONObject().put("id", option1.getString("id")));
////        crossCategoryOptions.put(new JSONObject().put("id", defaultCombo.getString("id")));
////        defaultCross.put("categoryOptions", crossCategoryOptions);
////        
////        combos.put(defaultCross);
////        
////        
////        for (int j = 0; j < allCategoryOptions.length(); ++j)
////        {
////          JSONObject option2 = allCategoryOptions.getJSONObject(j);
////          
////          if (option1 != option2)
////          {
////            String runwayId = option1.getString("id") + option2.getString("id");
////            String dhis2Id = DHIS2Util.queryAndMapIds(runwayId, idCache);
////            
////            JSONObject combo = new JSONObject();
////            combo.put("name", option1.getString("name") + ", " + option2.getString("name"));
////            combo.put("id", dhis2Id);
////            
////            combo.put("categoryCombo", categoryCombo);
////            
////            JSONArray categoryOptions = new JSONArray();
////            categoryOptions.put(new JSONObject().put("id", option1.getString("id")));
////            categoryOptions.put(new JSONObject().put("id", option2.getString("id")));
////            combo.put("categoryOptions", categoryOptions);
////            
////            combos.put(combo);
////          }
////        }
////      }
//      
//      json.put("categoryOptionCombos", combos);
//    }
//    catch (JSONException e)
//    {
//      throw new RuntimeException(e);
//    }
//  }
  
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
      
      
      JSONArray dataElements = new JSONArray();
      
      OIterator<? extends MdAttribute> mdAttrs = mdClass.getAllAttribute();
      for (MdAttribute mdAttr : mdAttrs)
      {
        if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
            !ArrayUtils.contains(DHIS2ExportHandler.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
          )
        {
          if (mdAttr instanceof MdAttributeNumber)
          {
            Selectable sel = valueQuery.getSelectableRef(mdAttr.getAttributeName());
            
            String dhis2Id = DHIS2Util.queryAndMapIds(mdAttr.getId(), idCache);
            
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
  
  protected void createDataSetMetadata(JSONObject json)
  {
    try
    {
      String dhis2Id = DHIS2Util.queryAndMapIds(mdClass.getId(), idCache);
      
      JSONArray dataSets = new JSONArray();
      
      JSONObject dataSet = new JSONObject();
      
      dataSet.put("id", dhis2Id);
      
      dataSet.put("name", this.exportable.getDhis2Name());
      
      dataSet.put("timelyDays", 0);
      
      int periodInt = 4;
      
      JSONArray dataSetElements = new JSONArray();
      
      OIterator<? extends MdAttribute> mdAttrs = mdClass.getAllAttribute();
      for (MdAttribute mdAttr : mdAttrs)
      {
        if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
            !ArrayUtils.contains(DHIS2ExportHandler.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
          )
        {
          if (mdAttr instanceof MdAttributeNumber)
          {
            JSONObject dataSetElement = new JSONObject();
            
            String dataElementId = DHIS2Util.queryAndMapIds(mdAttr.getId(), idCache);
            String dataSetElementId = DHIS2Util.queryAndMapIds(mdAttr.getId() + "_dataSetElement", idCache);
            
            dataSetElement.put("id", dataSetElementId);
            
            
            JSONObject dataElement = new JSONObject();
            dataElement.put("id", dataElementId);
            dataSetElement.put("dataElement", dataElement);
            
            
            dataSetElements.put(dataSetElement);
          }
          else if (mdAttr instanceof MdAttributeDate)
          {
            periodInt = 0;
            periodMdAttr = mdAttr;
          }
          else if (mdAttr.getAttributeName().contains("dategroup"))
          {
            String attrName = mdAttr.getAttributeName();
            
            if (periodInt > 1 && attrName.equals("dategroup_epiweek"))
            {
              periodInt = 1;
              periodMdAttr = mdAttr;
            }
            else if (periodInt > 2 && attrName.equals("dategroup_month"))
            {
              periodInt = 2;
              periodMdAttr = mdAttr;
            }
            else if (periodInt > 3 && attrName.equals("dategroup_quarter"))
            {
              periodInt = 3;
              periodMdAttr = mdAttr;
            }
            else if (periodInt > 4 && (attrName.equals("dategroup_epiyear") || attrName.equals("dategroup_year")))
            {
              periodInt = 4;
              periodMdAttr = mdAttr;
            }
          }
        }
      }
      dataSet.put("dataSetElements", dataSetElements);
      
      if (periodInt == 0)
      {
        dataSet.put("periodType", "Daily");
      }
      else if (periodInt == 1)
      {
        dataSet.put("periodType", "Weekly");
      }
      else if (periodInt == 2)
      {
        dataSet.put("periodType", "Monthly");
      }
      else if (periodInt == 3)
      {
        dataSet.put("periodType", "Quarterly");
      }
      else
      {
        dataSet.put("periodType", "Yearly");
      }
      
      dataSet.put("categoryCombo", new JSONObject().put("id", categoryComboId));
      
      JSONArray organisationUnits = new JSONArray();
      
      GeoEntity zambia = GeoEntity.getByKey("ZA");
      OrgUnit zambiaOrgUnit = DHIS2Util.getOrgUnitFromGeoEntity(zambia);
      if (zambiaOrgUnit == null)
      {
        throw new RuntimeException("Zambia is not mapped.");
      }
      
      JSONObject organisationUnit = new JSONObject();
      organisationUnit.put("id", zambiaOrgUnit.getDhis2Id());
      organisationUnits.put(organisationUnit);
      
      dataSet.put("organisationUnits", organisationUnits);
      
      dataSets.put(dataSet);
      
      json.put("dataSets", dataSets);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void createDataValues(JSONObject json)
  {
    try
    {
      GeoEntity zambia = GeoEntity.getByKey("ZA");
      OrgUnit zambiaOrgUnit = DHIS2Util.getOrgUnitFromGeoEntity(zambia);
      if (zambiaOrgUnit == null)
      {
        throw new RuntimeException("Zambia is not mapped.");
      }
      
      JSONArray dataValues = new JSONArray();
      
      QueryFactory qf = new QueryFactory();

      ValueQuery vq = qf.valueQuery();
      TableQuery tq = qf.tableQuery(mdClass.definesType());
      
      List<? extends MdAttribute> attrs = mdClass.getAllAttribute().getAll();
      for (MdAttribute mdAttr : attrs)
      {
        if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
          !ArrayUtils.contains(DHIS2ExportHandler.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME)))
        {
          vq.SELECT(tq.get(mdAttr.getAttributeName()));
        }
      }
      
      HashMap<MdAttribute, String> attrIdMap = new HashMap<MdAttribute, String>();
      for (MdAttribute mdAttr : attrs)
      {
        if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
            !ArrayUtils.contains(DHIS2ExportHandler.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
            && mdAttr instanceof MdAttributeNumber)
        {
          attrIdMap.put(mdAttr, DHIS2Util.getDhis2IdFromRunwayId(mdAttr.getId()));
        }
      }
      
      JSONArray dataElementMetadatas = json.getJSONArray("dataElements");
      
      OIterator<ValueObject> it = vq.getIterator();
      for (ValueObject val : it)
      {
        for (int dei = 0; dei < dataElementMetadatas.length(); ++dei)
        {
          JSONObject dataElementM = dataElementMetadatas.getJSONObject(dei);
          
          JSONObject dataValue = new JSONObject();
          
          dataValue.put("dataElement", dataElementM.getString("id"));
          
          String period = "";
          if (val.hasAttribute("dategroup_year"))
          {
            period = val.getValue("dategroup_year");
          }
          else if (val.hasAttribute("dategroup_epiyear"))
          {
            period = val.getValue("dategroup_epiyear");
          }
          if (val.hasAttribute("dategroup_epiweek"))
          {
            period += "W" + val.getValue("dategroup_epiweek");
          }
          dataValue.put("period", period);
          
          dataValue.put("orgUnit", zambiaOrgUnit.getDhis2Id()); // TODO
          
          ArrayList<String> runwayIds = new ArrayList<String>();
          for (MdAttribute mdAttr : categoryAttrs)
          {
            String attrVal = val.getValue(mdAttr.getAttributeName());
            
            if (mdAttr instanceof MdAttributeReference)
            {
              MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
              
              if (reference.definesType().equals(Term.CLASS))
              {
                Term term = Term.get(attrVal);
                
                runwayIds.add(term.getId());
              }
            }
            else
            {
              // If this code changes don't forget to also change the corresponding code when the categoryOption is created.
              
              String text = mdAttr.getAttributeName() + "." + attrVal; // TODO : We may need to filter out values from the attrVal here
              
              if (text.length() > 128)
              {
                text = text.substring(0, 127);
              }
              
              runwayIds.add(text);
            }
          }
          
          String rwId = StringUtils.join(runwayIds, "");
          String catOptComboId = DHIS2Util.getDhis2IdFromRunwayId(rwId);
          if (catOptComboId == null) { throw new RuntimeException("Unable to find a category option combo by runway id [" + rwId + "]. This object should already be mapped at this point."); }
          dataValue.put("categoryOptionCombo", catOptComboId);
          
          for (MdAttribute attr : attrs)
          {
            if (attr instanceof MdAttributeNumber && attrIdMap.get(attr).equals(dataElementM.getString("id")))
            {
              dataValue.put("value", val.getValue(attr.getAttributeName()));
            }
          }
          
          dataValues.put(dataValue);
        }
      }
      
      json.put("dataValues", dataValues);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
}
