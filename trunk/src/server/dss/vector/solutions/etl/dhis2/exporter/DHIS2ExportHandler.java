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
package dss.vector.solutions.etl.dhis2.exporter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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

import com.runwaysdk.constants.Constants;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.dataaccess.IndicatorCompositeDAO;
import com.runwaysdk.dataaccess.IndicatorCompositeDAOIF;
import com.runwaysdk.dataaccess.IndicatorPrimitiveDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.attributes.value.Attribute;
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
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.TableQuery;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.IndicatorComposite;
import com.runwaysdk.system.metadata.IndicatorElement;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdAttributeIndicator;
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdAttributeNumber;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessDTO;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.etl.dhis2.CalendarYearRequiredException;
import dss.vector.solutions.etl.dhis2.DHIS2ExportableDataset;
import dss.vector.solutions.etl.dhis2.DHIS2HTTPConnector;
import dss.vector.solutions.etl.dhis2.DHIS2Util;
import dss.vector.solutions.etl.dhis2.GeoMapQuery;
import dss.vector.solutions.etl.dhis2.InvalidFieldException;
import dss.vector.solutions.etl.dhis2.MaxOneGeoColumnException;
import dss.vector.solutions.etl.dhis2.NumbersMustBeAggregatedException;
import dss.vector.solutions.etl.dhis2.OrgUnit;
import dss.vector.solutions.etl.dhis2.OrgUnitQuery;
import dss.vector.solutions.etl.dhis2.TooManyDatesException;
import dss.vector.solutions.etl.dhis2.response.DHIS2EmptyDatasetException;
import dss.vector.solutions.etl.dhis2.response.DHIS2TrackerResponseProcessor;
import dss.vector.solutions.etl.dhis2.response.GeoFieldRequiredException;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
import dss.vector.solutions.etl.dhis2.util.DHIS2IdCache;
import dss.vector.solutions.geo.GeoHierarchy;
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
  
  private PrintStream log = null;
  
  protected DHIS2ExportableDataset exportable;
  
  protected MdClass mdClass;
  
  protected SavedSearch savedSearch;
  
  protected ValueQuery valueQuery;
  
  protected DHIS2HTTPConnector dhis2;
  
  private DHIS2IdCache idCache;
  
  private ArrayList<MdAttribute> categoryAttrs = new ArrayList<MdAttribute>();
  
  private String defaultComboId;
  
  // The keys are categoryAttrs. The values are a set of all ids of category options associated with that attribute.
  private Map<MdAttribute, Set<String>> categoryMetadataMap = new HashMap<MdAttribute, Set<String>>();
  
  private String categoryComboId = null;
  
  private final int pageSize = 1000;
  
  private long teiCount;
  
  private DHIS2ExportResults results;
  
  private MdAttribute periodMdAttr = null;
  
  private static final String namePrefix = " ";
  
  // The aggregation of some calculated fields cannot be determined automatically from the query and thus we are hardcoding them here.
  private Map<String, String> hardcodedFieldAggregationMap = new HashMap<String, String>();
  
  static String[] skipAttrs = new String[]{
    MdBusinessDTO.CACHEALGORITHM, MdBusinessDTO.TABLENAME, MdBusinessDTO.KEYNAME,
    MdBusinessDTO.BASECLASS, MdBusinessDTO.BASESOURCE, MdBusinessDTO.DTOCLASS, MdBusinessDTO.DTOSOURCE, MdBusinessDTO.STUBCLASS, MdBusinessDTO.STUBDTOCLASS, MdBusinessDTO.STUBDTOSOURCE, MdBusinessDTO.STUBSOURCE,
    MdAttributeConcreteDTO.GETTERVISIBILITY, MdAttributeConcreteDTO.INDEXTYPE, MdAttributeConcreteDTO.INDEXNAME, MdAttributeConcreteDTO.COLUMNNAME,
    MdAttributeConcreteDTO.DEFININGMDCLASS, MdAttributeConcreteDTO.ENTITYDOMAIN, MdAttributeConcreteDTO.OWNER, MdAttributeConcreteDTO.SETTERVISIBILITY, MdAttributeConcreteDTO.SITEMASTER
  };
  
  public DHIS2ExportHandler(DHIS2ExportableDataset exportable, DHIS2HTTPConnector dhis2)
  {
    this.exportable = exportable;
    this.mdClass = exportable.getQueryRef();
    this.dhis2 = dhis2;
    this.idCache = new DHIS2IdCache(dhis2);
    results = new DHIS2ExportResults(this.exportable);
  }
  
  @Transaction
  public DHIS2ExportResults export(String strategy)
  {
    try
    {
      gatherPrereqs();
      
      validateExport();
      
      JSONObject metadata = new JSONObject();
      
      createCategoryOptionsMetadata(metadata); // #8
      
      createCategoryMetadata(metadata); // #9
      
      createCategoryCombinationMetadata(metadata); // #10
      
      createCategoryOptionCombinationMetadata(metadata); // #11
      
      createDataElementsMetadata(metadata); // #12
      
      createDataSetMetadata(metadata); // #13
      
      createDataElementGroupMetadata(metadata);
      
      createIndicatorTypes(metadata);
      
      createIndicators(metadata);
      
      JSONObject data = createDataValues(metadata); // #14
      
      // Write json to a file
      try
      {
        PrintWriter writer = new PrintWriter(this.exportable.getMetadataFileLocation(), "UTF-8");
        writer.println(metadata.toString());
        writer.close();
        
        PrintWriter writer2 = new PrintWriter(this.exportable.getDataFileLocation(), "UTF-8");
        writer2.println(data.toString());
        writer2.close();
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
      
      // Send it to DHIS2
      HTTPResponse resp = dhis2.apiPost("metadata.json?importStrategy=" + strategy, metadata.toString());
      log.append("Sent metadata json to DHIS2 and received this as a response:\n" + resp.getResponse() + "\n");
      DHIS2TrackerResponseProcessor.validateStatusCode(resp); // TODO : We need to do a better job displaying the results to the end user if it throws an exception
      results.processMetadataResponse(resp);
        
      HTTPResponse resp2 = dhis2.apiPost("dataValueSets.json?importStrategy=" + strategy, data.toString());
      log.append("Sent data json to DHIS2 and received this as a response:\n" + resp2.getResponse() + "\n");
//      DHIS2TrackerResponseProcessor.validateStatusCode(resp2); // At this point the metadata has already been committed to DHIS2 so we don't want to rollback the transaction
      results.processDataResponse(resp2);
      
      return results;
    }
    finally
    {
      if (log != null)
      {
        log.close();
      }
    }
  }
  
  protected void gatherPrereqs()
  {
    // Instantiate our logger
    File dhis2Dir = new File(DeployProperties.getDeployPath() + "/DHIS2");
    dhis2Dir.mkdirs();
    try
    {
      log = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.exportable.getLogLocation())));
    }
    catch (FileNotFoundException e1)
    {
      logger.error("Unable to open log file [" + DeployProperties.getDeployPath() + "/DHIS2/export.log]");
    }
    
    // Create a ValueQuery which we will use to help us with the export
    QueryFactory qf = new QueryFactory();
    SavedSearchQuery ssq = new SavedSearchQuery(qf);
    ssq.WHERE(ssq.getMaterializedTable().EQ(mdClass));
    OIterator<? extends SavedSearch> it = ssq.getIterator();
    try
    {
      savedSearch = it.next();
    }
    finally
    {
      it.close();
    }
    String queryClass = QueryConstants.getQueryClass(this.savedSearch.getQueryType());
    valueQuery = QueryBuilder.getValueQuery(queryClass, this.savedSearch.getQueryXml(), this.savedSearch.getConfig(), null, null, null, this.savedSearch.getDisease());
    
    // The aggregation of some calculated fields cannot be determined automatically from the query and thus we are hardcoding them here.
    // The key is the attribute name (of the selectable) and the value is the DHIS2 aggregation function (so AVERAGE instead of AVG).
//    hardcodedFieldAggregationMap.put("abundance_1", "SUM");
//    hardcodedFieldAggregationMap.put("abundance_10", "SUM");
//    hardcodedFieldAggregationMap.put("abundance_100", "SUM");
//    hardcodedFieldAggregationMap.put("abundance_1000", "SUM");
    hardcodedFieldAggregationMap.put("collectionCount", "SUM");
    hardcodedFieldAggregationMap.put("subCollectionCount", "SUM");
    hardcodedFieldAggregationMap.put("mosquitoCount", "SUM");
    hardcodedFieldAggregationMap.put("mosquitoCount", "SUM");
    hardcodedFieldAggregationMap.put("operator_planned_target", "SUM");
    hardcodedFieldAggregationMap.put("team_planned_target", "SUM");
    hardcodedFieldAggregationMap.put("area_planned_target", "SUM");
  }
  
  private String getAggTypeFromSql(SelectableSQL sel)
  {
    if (hardcodedFieldAggregationMap.containsKey(sel.getResultAttributeName()))
    {
      return hardcodedFieldAggregationMap.get(sel.getResultAttributeName());
    }
    
    String sql = sel.getSQL().toLowerCase();
    
    if (StringUtils.countMatches(sql, "min(") == 1 && !(sql.contains("max(") || sql.contains("avg(") || sql.contains("sum(")))
    {
      return "MIN";
    }
    else if (StringUtils.countMatches(sql, "max(") == 1 && !(sql.contains("min(") || sql.contains("avg(") || sql.contains("sum(")))
    {
      return "MAX";
    }
    else if (StringUtils.countMatches(sql, "avg(") == 1 && !(sql.contains("min(") || sql.contains("max(") || sql.contains("sum(")))
    {
      return "AVERAGE";
    }
    else if (StringUtils.countMatches(sql, "sum(") == 1 && !(sql.contains("min(") || sql.contains("max(") || sql.contains("avg(")))
    {
      return "SUM";
    }
    
    return null;
  }
  
  protected void validateExport()
  {
//    if (this.exportable.getDhis2Name().length() > 50)
//    {
//      DHIS2NameLengthException ex = new DHIS2NameLengthException();
//      ex.setCharLen("50");
//      ex.setName(this.exportable.getDhis2Name());
//      throw ex;
//    }
    
    // Check #1 : We have to have rows in our dataset
    QueryFactory qf = new QueryFactory();
    TableQuery tq = qf.tableQuery(mdClass.definesType());
    teiCount = tq.getCount();
    
    if (teiCount == 0)
    {
      DHIS2EmptyDatasetException ex = new DHIS2EmptyDatasetException();
      ex.setDataset(this.exportable.getDhis2Name());
      throw ex;
    }
    
    
    // Check #2 : Attribute validation
    int numDates = 0;
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
          if (sel instanceof SelectableSQL)
          {
            if (getAggTypeFromSql((SelectableSQL) sel) == null)
            {
              InvalidFieldException ex = new InvalidFieldException();
              ex.setField(mdAttr.getDisplayLabel().getValue(Locale.ROOT));
              ex.setDataset(this.exportable.getDhis2Name());
              throw ex;
            }
          }
          else if (!(sel instanceof AggregateFunction))
          {
            NumbersMustBeAggregatedException ex = new NumbersMustBeAggregatedException();
            ex.setNumberColumn(mdAttr.getDisplayLabel().getValue(Locale.ROOT));
            ex.setDataset(this.exportable.getDhis2Name());
            throw ex;
          }
        }
        else if (mdAttr.getAttributeName().equals("dategroup_year") || mdAttr.getAttributeName().equals("dategroup_epiyear"))
        {
          hasYear = true;
        }
        else if (mdAttr.getAttributeName().contains("dategroup"))
        {
          continue;
        }
        else if (mdAttr instanceof MdAttributeDate)
        {
          numDates++;
        }
        else if (mdAttr instanceof MdAttributeIndicator)
        {
          continue; // We'll deal with these later
        }
        // Most columns are defaulted to character (time, user, etc)
        else
        {
          categoryAttrs.add(mdAttr);
        }
      }
    }
    
    if (numGeos == 0)
    {
      GeoFieldRequiredException ex = new GeoFieldRequiredException();
      ex.setDataset(this.exportable.getDhis2Name());
      throw ex;
    }
    else if (numGeos > 1)
    {
      MaxOneGeoColumnException ex = new MaxOneGeoColumnException();
      ex.setDataset(this.exportable.getDhis2Name());
      throw ex;
    }
    if (numDates > 1)
    {
      TooManyDatesException ex = new TooManyDatesException();
      ex.setDataset(this.exportable.getDhis2Name());
      throw ex;
    }
    else if (numDates == 0 && !hasYear)
    {
      CalendarYearRequiredException ex = new CalendarYearRequiredException();
      ex.setDataset(this.exportable.getDhis2Name());
      throw ex;
    }
    
    String ss = "";
    OIterator<? extends MdAttribute> mdAttrs2 = mdClass.getAllAttribute();
    for (MdAttribute mdAttr : mdAttrs2)
    {
      ss = ss + mdAttr.getAttributeName() + ", ";
    }
    log.append("attributeNames in query : " + ss.toString() + "\n");
  }
  
  protected void createCategoryOptionsMetadata(JSONObject json)
  {
    try
    {
      if (categoryAttrs.size() == 0) { return; }
      
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
          if (attrVal == null || attrVal.length() == 0) { continue; }
          
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
              
              name = term.getTermDisplayLabel().getValue(Locale.ROOT);
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
            
            MetadataElement option = new MetadataElement();
            option.setCode(code);
            option.setName(name);
            option.setShortName(shortName);
            option.setId(dhis2Id);
            options.put(option.getJSON());
            
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
      if (categoryAttrs.size() == 0) { return; }
      
      JSONArray categories = new JSONArray();
      
      Set<MdAttribute> keys = categoryMetadataMap.keySet();
      
      for (MdAttribute mdAttr : keys)
      {
        String dhis2Id = DHIS2Util.queryAndMapIds(mdAttr, idCache, valueQuery);
        
        // Basic identifier info about the category
        MetadataElement category = new MetadataElement();
        category.setName(mdAttr.getDisplayLabel().getValue(Locale.ROOT));
        category.setId(dhis2Id);
        category.put("dataDimensionType", "ATTRIBUTE");
        
        // If this category already exists in DHIS2, then we need to fetch the existing options so we don't clear what's already there
        Set<String> categoryOptions = categoryMetadataMap.get(mdAttr); // A list of all the category options associated with this category
        
        HTTPResponse response = dhis2.apiGet("metadata", new NameValuePair[]{
            new NameValuePair("categories", "true"),
            new NameValuePair("assumeTrue", "false"),
            new NameValuePair("filter", "id:eq:" + dhis2Id)
          });
        DHIS2TrackerResponseProcessor.validateStatusCode(response);
        
        JSONObject existingCategoryResponse = response.getJSONObject();
        if (existingCategoryResponse.has("categories"))
        {
          JSONArray existingCategories = existingCategoryResponse.getJSONArray("categories");
          
          if (existingCategories.length() > 0)
          {
            JSONObject existingCategory = existingCategories.getJSONObject(0);
            
            JSONArray existingCategoryOptions = existingCategory.getJSONArray("categoryOptions");
            
            for (int i = 0; i < existingCategoryOptions.length(); ++i)
            {
              categoryOptions.add(existingCategoryOptions.getJSONObject(i).getString("id"));
            }
          }
        }
        
        
        JSONArray jsonCategoryOptions = new JSONArray();
        for (String option : categoryOptions)
        {
          JSONObject jsonCategoryOption = new JSONObject();
          jsonCategoryOption.put("id", option);
          jsonCategoryOptions.put(jsonCategoryOption);
        }
        category.put("categoryOptions", jsonCategoryOptions);
        
        categories.put(category.getJSON());
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
      if (categoryAttrs.size() == 0) { return; }
      
      JSONArray categoryCombos = new JSONArray();
      MetadataElement categoryCombo = new MetadataElement();
      
      categoryComboId = DHIS2Util.queryAndMapIds(mdClass.getId() + "_catCombo", idCache);
      
      categoryCombo.setName(mdClass.getDisplayLabel().getValue(Locale.ROOT));
      categoryCombo.setId(categoryComboId);
      categoryCombo.put("dataDimensionType", "ATTRIBUTE");
      
      // We can get the category ids by reading the json we generated earlier.
      JSONArray categoryIds = new JSONArray();
      JSONArray allCategories = json.getJSONArray("categories");
      for (int i = 0; i < allCategories.length(); ++i)
      {
        String categoryId = allCategories.getJSONObject(i).getString("id");
        
        categoryIds.put(new JSONObject().put("id", categoryId));
      }
      categoryCombo.put("categories", categoryIds);
      
      categoryCombos.put(categoryCombo.getJSON());
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
      if (categoryAttrs.size() == 0) { return; }
      
      // Fetch the default category combo
      HTTPResponse response = dhis2.apiGet("categoryOptionCombos", new NameValuePair[]{
          new NameValuePair("filter", "displayName:eq:default")
      });
      DHIS2TrackerResponseProcessor.validateStatusCode(response);
      
      JSONObject defaultCatJS = response.getJSONObject();
      JSONArray defaultCombos = defaultCatJS.getJSONArray("categoryOptionCombos");
      JSONObject defaultCombo = defaultCombos.getJSONObject(0);
      defaultComboId = defaultCombo.getString("id");
      
      
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
          
          // We need to figure out values for all of these if we're to export. The problem is that the values will vary depending on the attribute.
          String name = null;
          String runwayId = null;
          
          if (attrVal == null || attrVal.length() == 0)
          {
            // Null values will match to the 'default' DHIS2 category option.
            name = "default";
            runwayId = defaultComboId;
          }
          else if (mdAttr instanceof MdAttributeReference)
          {
            MdBusiness reference = ((MdAttributeReference) mdAttr).getMdBusiness();
            
            if (reference.definesType().equals(Term.CLASS))
            {
              Term term = Term.get(attrVal);
              
              name = term.getTermDisplayLabel().getValue(Locale.ROOT);
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
            String dhis2Id;
            if (name.equals("default"))
            {
              dhis2Id = defaultComboId;
            }
            else
            {
              dhis2Id = DHIS2Util.getDhis2IdFromRunwayId(runwayId);
            }
            
            optComboNames.add(name);
            optComboDHIds.add(dhis2Id);
            optComboRWIds.add(runwayId);
            
            catOpts.put(new JSONObject().put("id", dhis2Id));
          }
        }
        
        if (optComboNames.size() > 0)
        {
          Collections.sort(optComboRWIds);
          String comboRWId = StringUtils.join(optComboRWIds, "");
          
          if (noDuplicatesSet.contains(comboRWId))
          {
            continue;
          }
          else
          {
            noDuplicatesSet.add(comboRWId);
          }
          
          MetadataElement optCombo = new MetadataElement();
          
          optCombo.setName(StringUtils.join(optComboNames, ", "));
          optCombo.setId(DHIS2Util.queryAndMapIds(comboRWId, idCache));
          optCombo.put("categoryCombo", new JSONObject().put("id", categoryComboId));
          optCombo.put("categoryOptions", catOpts);
          
          optCombos.put(optCombo.getJSON());
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
//      HTTPResponse response = dhis2.apiGet("filledOrganisationUnitLevels", new NameValuePair[]{});
//      DHIS2TrackerResponseProcessor.validateStatusCode(response);
//      
//      JSONArray levels = response.getJSONArray();
//      JSONArray aggregationLevels = new JSONArray();
//      for (int i = 0; i < levels.length(); ++i)
//      {
//        JSONObject level = levels.getJSONObject(i);
//        aggregationLevels.put(level.getInt("level"));
//      }
      
      
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
            
            String dhis2Id = DHIS2Util.queryAndMapIds(mdAttr, idCache, valueQuery);
            
            MetadataElement dataElement = new MetadataElement();
            
            dataElement.setId(dhis2Id);
            
            String name = this.exportable.getDhis2Name() + " " + mdAttr.getDisplayLabel().getValue(Locale.ROOT);
            dataElement.setName(name);
            
            String shortName = this.exportable.getDhis2Name();
            if (shortName.length() > 35)
            {
              shortName = shortName.substring(0, 35);
            }
            shortName = shortName + " " + mdAttr.getDisplayLabel().getValue(Locale.ROOT);
            dataElement.setShortName(shortName);
            
            if (sel instanceof SelectableSQL)
            {
              String aggType = getAggTypeFromSql((SelectableSQL) sel);
              
              dataElement.put("aggregationType", aggType);
            }
            else if (sel instanceof AggregateFunction)
            {
              if (sel instanceof AVG)
              {
                dataElement.put("aggregationType", "AVERAGE");
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
            
//            dataElement.put("categoryCombo", new JSONObject().put("id", categoryComboId));
            
//            dataElement.put("aggregationLevels", aggregationLevels);
            
            dataElements.put(dataElement.getJSON());
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
      // We need to include all DHIS2 mapped org units
      JSONArray organisationUnits = new JSONArray();
      
      QueryFactory qf = new QueryFactory();
      
      ValueQuery vq = new ValueQuery(qf);
      GeoMapQuery gmq = new GeoMapQuery(qf);
      OrgUnitQuery ouq = new OrgUnitQuery(qf);
      
      vq.SELECT(ouq.getDhis2Id("dhis2Id"));
      vq.WHERE(gmq.getConfirmed().EQ(true));
      vq.AND(gmq.getOrgUnit().NE(""));
      vq.AND(gmq.getOrgUnit().EQ(ouq));
      
      OIterator<? extends ValueObject> it = vq.getIterator();
      
      try
      {
        for (ValueObject obj : it)
        {
          JSONObject organisationUnit = new JSONObject();
          organisationUnit.put("id", obj.getValue("dhis2Id"));
          organisationUnits.put(organisationUnit);
        }
      }
      finally
      {
        it.close();
      }
      
      
      
      
      
      String dhis2Id = DHIS2Util.queryAndMapIds(mdClass.getId(), idCache);
      
      JSONArray dataSets = new JSONArray();
      
      MetadataElement dataSet = new MetadataElement();
      
      dataSet.setId(dhis2Id);
      
      dataSet.setName(this.exportable.getDhis2Name());
      
      dataSet.put("timelyDays", 0);
      
      int periodInt = 5;
      
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
            
            String dataElementId = DHIS2Util.getDhis2IdFromRunwayId(mdAttr, valueQuery);
            String dataSetElementId = DHIS2Util.queryAndMapIds(mdClass.getId() + mdAttr.getId() + "_dataSetElement", idCache);
            
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
      
      if (categoryComboId != null)
      {
        dataSet.put("categoryCombo", new JSONObject().put("id", categoryComboId));
      }
      
      dataSet.put("organisationUnits", organisationUnits);
      
      dataSets.put(dataSet.getJSON());
      
      json.put("dataSets", dataSets);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void createDataElementGroupMetadata(JSONObject payload)
  {
    try
    {
      JSONArray dataElementGroups = new JSONArray();
      
      MetadataElement dataElementGroup = new MetadataElement();
      dataElementGroup.setName(this.exportable.getDhis2Name());
      dataElementGroup.setId(DHIS2Util.queryAndMapIds(mdClass.getId() + "_deg", idCache));
      
      JSONArray dataElements = new JSONArray();
      JSONArray dataElementMetadatas = payload.getJSONArray("dataElements");
      for (int dei = 0; dei < dataElementMetadatas.length(); ++dei)
      {
        JSONObject dataElementM = dataElementMetadatas.getJSONObject(dei);
        
        dataElements.put(new JSONObject().put("id", dataElementM.get("id")));
      }
      dataElementGroup.put("dataElements", dataElements);
      
      dataElementGroups.put(dataElementGroup.getJSON());
      
      payload.put("dataElementGroups", dataElementGroups);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void createIndicatorTypes(JSONObject payload)
  {
    try
    {
      JSONArray indicatorTypes = new JSONArray();
      
      MetadataElement basic = new MetadataElement();
      basic.setName("Basic");
      basic.setId(DHIS2Util.queryAndMapIds("_BasicIndicatorType_", idCache));
      basic.put("factor", 1);
      indicatorTypes.put(basic.getJSON());
      
      MetadataElement percentage = new MetadataElement();
      percentage.setName("Percentage");
      percentage.setId(DHIS2Util.queryAndMapIds("_PercentageIndicatorType_", idCache));
      percentage.put("factor", 100);
      indicatorTypes.put(percentage.getJSON());
      
      payload.put("indicatorTypes", indicatorTypes);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void createIndicators(JSONObject payload)
  {
    try
    {
      JSONArray indicatorTypes = new JSONArray();
      
      OIterator<? extends MdAttribute> mdAttrs = mdClass.getAllAttribute();
      for (MdAttribute mdAttr : mdAttrs)
      {
        if (mdAttr.getValue(MdAttributeConcreteDTO.SYSTEM).equals(MdAttributeBooleanInfo.FALSE) && 
            !ArrayUtils.contains(DHIS2ExportHandler.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
          )
        {
          if (mdAttr instanceof MdAttributeIndicator)
          {
            MdAttributeIndicator mdIndi = (MdAttributeIndicator)mdAttr;
            IndicatorElement indiEle = mdIndi.getIndicatorElement();
            
            if (indiEle instanceof IndicatorComposite)
            {
              IndicatorCompositeDAOIF composite = IndicatorCompositeDAO.get(indiEle.getId());
              
              IndicatorPrimitiveDAOIF leftOp = (IndicatorPrimitiveDAOIF) composite.getLeftOperand();
              IndicatorPrimitiveDAOIF rightOp = (IndicatorPrimitiveDAOIF) composite.getRightOperand();
              
              MetadataElement indy = new MetadataElement();
              
              indy.setId(DHIS2Util.queryAndMapIds(mdAttr, idCache, valueQuery));
              
              indy.setName(mdAttr.getDisplayLabel().getValue(Locale.ROOT));
              indy.setShortName(mdAttr.getDisplayLabel().getValue(Locale.ROOT));
              
              indy.put("numeratorDescription", leftOp.getLocalizedLabel());
              indy.put("denominatorDescription", rightOp.getLocalizedLabel());
              
              indy.put("numerator", "#{" + DHIS2Util.getDhis2IdFromRunwayId(MdAttribute.get(leftOp.getMdAttributePrimitive().getId()), valueQuery) + "}");
              indy.put("denominator", "#{" + DHIS2Util.getDhis2IdFromRunwayId(MdAttribute.get(rightOp.getMdAttributePrimitive().getId()), valueQuery) + "}");
              
              if (composite.isPercentage())
              {
                indy.put("indicatorType", new JSONObject().put("id", DHIS2Util.getDhis2IdFromRunwayId("_PercentageIndicatorType_")));
              }
              else
              {
                indy.put("indicatorType", new JSONObject().put("id", DHIS2Util.getDhis2IdFromRunwayId("_BasicIndicatorType_")));
              }
              
              indicatorTypes.put(indy.getJSON());
            }
          }
        }
      }
      
      payload.put("indicators", indicatorTypes);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected JSONObject createDataValues(JSONObject metadata)
  {
    try
    {
      GeoEntity zambia = GeoEntity.getByKey("ZA");
      OrgUnit zambiaOrgUnit = DHIS2Util.getOrgUnitFromGeoEntity(zambia.getId());
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
          !ArrayUtils.contains(DHIS2ExportHandler.skipAttrs, mdAttr.getValue(MdAttributeConcreteDTO.ATTRIBUTENAME))
          && !(mdAttr instanceof MdAttributeIndicator))
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
          attrIdMap.put(mdAttr, DHIS2Util.getDhis2IdFromRunwayId(mdAttr, valueQuery));
        }
      }
      
      
      String geoType = null;
      JSONObject selectedUniMap = new JSONObject(savedSearch.getConfig()).getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        String attributeKey = (String) keys.next();

        JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
        if (universals.length() > 0)  // && attributeKey.equals(queryType + '.' + AggregatedCase.GEOENTITY)
        {
          String[] selectedUniversals = new String[universals.length()];
          for (int i = 0; i < universals.length(); i++)
          {
            selectedUniversals[i] = universals.getString(i);
          }
          geoType = GeoHierarchy.getMostChildishUniversialType(selectedUniversals);
          geoType = geoType.substring(geoType.lastIndexOf('.')).toLowerCase();
          geoType = attributeKey + '.' + geoType + '.' + GeoEntity.ID;
          geoType = geoType.replace('.', '_');
          geoType = geoType.substring(geoType.length() - 20, geoType.length()); // TODO : I think this depends on database column name length so we're just kinda doing our best here. If database column name length is less than 20 this will break.
        }
      }
      
      JSONArray dataElementMetadatas = metadata.getJSONArray("dataElements");
      
      int row = 0;
      
      OIterator<ValueObject> it = vq.getIterator();
      for (ValueObject val : it)
      {
        for (int dei = 0; dei < dataElementMetadatas.length(); ++dei)
        {
          JSONObject dataElementM = dataElementMetadatas.getJSONObject(dei);
          
          JSONObject dataValue = new JSONObject();
          
          dataValue.put("dataElement", dataElementM.getString("id"));
          
          String period = "";
          String periodN = periodMdAttr.getAttributeName();
          
          if (periodN.equals("dategroup_year") || periodN.equals("dategroup_epiyear") || periodN.equals("dategroup_epiweek")
              || periodN.equals("dategroup_month") || periodN.equals("dategroup_quarter")
              )
          {
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
            else if (val.hasAttribute("dategroup_month"))
            {
              period += val.getValue("dategroup_month");
            }
            else if (val.hasAttribute("dategroup_quarter"))
            {
              period += "Q" + val.getValue("dategroup_quarter");
            }
          }
          else
          {
            String rwDate = val.getValue(periodMdAttr.getAttributeName());
            
            // Convert from runway's date format to DHIS2's format
            try
            {
              DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH);
              Date date = format.parse(rwDate);
            
              SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
              period = formatter.format(date);
            }
            catch (ParseException e)
            {
              throw new RuntimeException(e);
            }
          }
          dataValue.put("period", period);
          
          if (geoType != null)
          {
            String geoId = null;
            
            Map<String, Attribute> valMap = val.getAttributeMap();
            Set<String> valMapKeys = valMap.keySet();
            for (String valMapKey : valMapKeys)
            {
              if (valMapKey.contains(geoType))
              {
                geoId = val.getValue(valMapKey);
              }
            }
            
            OrgUnit orgUnit = DHIS2Util.getOrgUnitFromGeoEntity(geoId);
            
            if (orgUnit != null)
            {
              dataValue.put("orgUnit", orgUnit.getDhis2Id());
            }
            else
            {
              if (geoId != null)
              {
                geoId = GeoEntity.get(geoId).getEntityLabel().getValue(Locale.ROOT);
              }
              else
              {
                geoId = "null";
              }
              
              String msg = row + " : The GeoEntity [" + geoId + "] is not mapped to an OrgUnit.";
//              logger.warn(msg);
              if (log != null)
              {
                log.append(msg + "\n");
              }
            }
          }
          else
          {
            dataValue.put("orgUnit", zambiaOrgUnit.getDhis2Id());
          }
          
          if (categoryAttrs.size() > 0)
          {
            ArrayList<String> runwayIds = new ArrayList<String>();
            for (MdAttribute mdAttr : categoryAttrs)
            {
              String attrVal = val.getValue(mdAttr.getAttributeName());
              
              if (attrVal == null || attrVal.length() == 0)
              {
                // Null values will match to the 'default' DHIS2 category option.
                runwayIds.add(defaultComboId);
              }
              else if (mdAttr instanceof MdAttributeReference)
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
          
            Collections.sort(runwayIds);
            String rwId = StringUtils.join(runwayIds, "");
            String catOptComboId = DHIS2Util.getDhis2IdFromRunwayId(rwId);
            if (catOptComboId == null) { throw new RuntimeException("Unable to find a category option combo by runway id [" + rwId + "]. This object should already be mapped at this point."); }
            dataValue.put("attributeOptionCombo", catOptComboId);
          }
          
          String value = "";
          for (MdAttribute attr : attrs)
          {
            if (attr instanceof MdAttributeNumber && attrIdMap.get(attr).equals(dataElementM.getString("id")))
            {
              value = val.getValue(attr.getAttributeName());
              continue;
            }
          }
          
          if (!value.equals(""))
          {
            dataValue.put("value", value);
            dataValues.put(dataValue);
          }
        }
        
        row++;
      }
      
      JSONObject data = new JSONObject();
      data.put("dataValues", dataValues);
      return data;
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
}
