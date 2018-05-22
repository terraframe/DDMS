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
package dss.vector.solutions.etl.dhis2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.dataaccess.DuplicateDataException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;

import dss.vector.solutions.etl.dhis2.response.DHIS2TrackerResponseProcessor;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
import dss.vector.solutions.geo.GeoHierarchyQuery;

/**
 * This class is responsible for pulling org units from DHIS2 and associating them with a GeoEntity in DDMS
 * 
 * @author rrowlands
 */
public class DHIS2GeoMapper implements Reloadable
{
  private DHIS2HTTPConnector dhis2;
  
  private Map<Integer, OrgUnitLevel> levels;
  
//  private String[] countryOrgUnitExcludes;
  
  private static Logger logger = LoggerFactory.getLogger(DHIS2GeoMapper.class);
  
  public static void main(String[] args)
  {
    CommandLineParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption(Option.builder("url").hasArg().argName("url").longOpt("url").desc("URL of the DHIS2 server to connect to, including the port. Defaults to: http://127.0.0.1:8085/").optionalArg(true).build());
    options.addOption(Option.builder("username").hasArg().argName("username").longOpt("username").desc("The username of the root (admin) DHIS2 user.").required().build());
    options.addOption(Option.builder("password").hasArg().argName("password").longOpt("password").desc("The password for the root (admin) DHIS2 user.").required().build());
//    options.addOption(Option.builder("countryOrgUnitExcludes").hasArg().argName("countryOrgUnitExcludes").longOpt("countryOrgUnitExcludes").desc("DHIS2 does not support multiple countries. However, some systems are misconfigured and have multiple countries. Our importer does not support this. You must exclude the extraneous countries to get this importer to work. This is a comma separated list of org unit ids to exclude from the import.").optionalArg(true).build());
    
    try {
      CommandLine line = parser.parse( options, args );
      
      String url = line.getOptionValue("url");
      String username = line.getOptionValue("username");
      String password = line.getOptionValue("password");
//      String countryOrgUnitExcludes = line.getOptionValue("countryOrgUnitExcludes");
      
      if (url == null)
      {
        url = "http://127.0.0.1:8085/";
      }
      
      doImportInRequest(url, username, password);
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  // Don't ever put an @Request on a main method or you will regret it I promise you
  @Request
  public static void doImportInRequest(String url, String username, String password)
  {
    DHIS2HTTPConfiguration config = DHIS2HTTPConfiguration.getByKey("DEFAULT");
    config.setPazzword(password);
    config.setUsername(username);
    config.setUrl(url);
    config.appLock();
    config.apply();
    
    new DHIS2GeoMapper(url, username, password).mapAll();
  }
  
  public DHIS2GeoMapper(String url, String username, String password)
  {
//    if (countryOrgUnitExcludes != null)
//    {
//      this.countryOrgUnitExcludes = StringUtils.split(countryOrgUnitExcludes, ",");
//    }
    
    dhis2 = new DHIS2HTTPConnector();
    dhis2.setServerUrl(url);
    dhis2.setCredentials(username, password);
    
    levels = new HashMap<Integer, OrgUnitLevel>();
  }
  
  public DHIS2GeoMapper()
  {
    dhis2 = new DHIS2HTTPConnector();
    dhis2.readConfigFromDB();
    
    levels = new HashMap<Integer, OrgUnitLevel>();
  }
  
  public void mapAll()
  {
    fetchAllInTransaction();
    matchAllInTransaction();
    logger.info("Geo mapping is finished.");
  }
  
  @Transaction
  protected void fetchAllInTransaction()
  {
    try
    {
      fetchOrgUnitLevels();
      fetchOrgUnits();
      populateGeoLevelMap();
      populateGeoMap();
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  @Transaction
  protected void matchAllInTransaction()
  {
    matchOrgUnitLevels();
    matchOrgUnitsByCode();
    matchOrgUnitsByName();
  }
  
  protected void fetchOrgUnitLevels() throws JSONException
  {
    int updated = 0;
    int created = 0;
    int deleted = 0;
    
    Database.parseAndExecute("UPDATE org_unit_level SET validflag=0");
    
    HTTPResponse response = dhis2.apiGet("metadata", new NameValuePair[] {
        new NameValuePair("assumeTrue", "false"),
        new NameValuePair("organisationUnitLevels", "true")
    });
    DHIS2TrackerResponseProcessor.validateStatusCode(response); // TODO : We need better validation than just status code.
    
    JSONObject json = response.getJSONObject();
    
    JSONArray jsonLevels = json.getJSONArray("organisationUnitLevels");
    
    for (int i = 0; i < jsonLevels.length(); ++i)
    {
      JSONObject jsonOUL = jsonLevels.getJSONObject(i);
      
      String name = "";
      if (jsonOUL.has("name"))
      {
        name = jsonOUL.getString("name");
      }
      else if (jsonOUL.has("shortName"))
      {
        name = jsonOUL.getString("shortName");
      }
      else
      {
        continue;
      }
      
      Integer levelInt = Integer.parseInt(jsonOUL.getString("level"));
      
      OrgUnitLevel oul = new OrgUnitLevel();
      oul.setName(name);
      oul.setDhis2Id(jsonOUL.getString("id"));
      oul.setLevel(levelInt);
      oul.setValid(true);
      
      Savepoint sp = Database.setSavepoint();
      try
      {
        oul.apply();
        
        created++;
      }
      catch (DuplicateDataException e)
      {
        Database.rollbackSavepoint(sp);
        
        OrgUnitLevel dup = OrgUnitLevel.getByKey(oul.getDhis2Id());
        dup.appLock();
        dup.setName(oul.getName());
        dup.setLevel(oul.getLevel());
        dup.setValid(true);
        dup.apply();
        
        oul = dup;
        
        updated++;
      }
      finally
      {
        Database.releaseSavepoint(sp);
      }
      
      levels.put(levelInt, oul);
    }
    
    OrgUnitLevelQuery query = new OrgUnitLevelQuery(new QueryFactory());
    query.WHERE(query.getValid().EQ(false));
    OIterator<? extends OrgUnitLevel> it = query.getIterator();
    
    try
    {
      for (OrgUnitLevel level : it)
      {
        level.delete();
        deleted++;
      }
    }
    finally
    {
      it.close();
    }
    
    logger.info("Org unit level fetching completed. created = " + created + " updated = " + updated + " deleted = " + deleted);
  }
  
  protected void fetchOrgUnits() throws JSONException
  {
    int updated = 0;
    int created = 0;
    int deleted = 0;
    
    Database.parseAndExecute("UPDATE org_unit SET validflag=0");
    
    HTTPResponse response = dhis2.apiGet("metadata", new NameValuePair[] {
        new NameValuePair("assumeTrue", "false"),
        new NameValuePair("organisationUnits", "true")
    });
    DHIS2TrackerResponseProcessor.validateStatusCode(response); // TODO : We need better validation than just status code.
    
    JSONObject json = response.getJSONObject();
    
    JSONArray units = json.getJSONArray("organisationUnits");
    for (int i = 0; i < units.length(); ++i)
    {
      JSONObject unit = units.getJSONObject(i);
      
      OrgUnit org = new OrgUnit();
      
      org.setDhis2Id(unit.getString("id"));
      
      if (unit.has("code"))
      {
        org.setCode(unit.getString("code"));
      }
      
      // Find the org unit name
      String name = "";
      if (unit.has("name"))
      {
        name = unit.getString("name");
      }
      else if (unit.has("shortName"))
      {
        name = unit.getString("shortName");
      }
      org.setName(name);
      
      // Find the universal
      if (unit.has("path"))
      {
        String path = unit.getString("path");
        
        if (!path.startsWith("/"))
        {
          path = "/" + path;
        }
        if (!path.endsWith("/"))
        {
          path = path + "/";
        }
        
        int level = StringUtils.countMatches(path, "/") - 1;
        
        org.setOrgUnitLevel(levels.get(level));
        org.setPath(path);
      }
      
//      org.setParent(parent); // TODO
      
      org.setValid(true);
      
      Savepoint sp = Database.setSavepoint();
      try
      {
        org.apply();
        
        created++;
      }
      catch (DuplicateDataException e)
      {
        Database.rollbackSavepoint(sp);
        
        OrgUnit dup = OrgUnit.getByKey(org.getDhis2Id());
        dup.appLock();
        dup.setCode(org.getCode());
        dup.setName(org.getName());
        dup.setPath(org.getPath());
        dup.setOrgUnitLevel(org.getOrgUnitLevel());
        dup.setParent(org.getParent());
        dup.setValid(true);
        dup.apply();
        
        updated++;
      }
      finally
      {
        Database.releaseSavepoint(sp);
      }
    }
    
    OrgUnitQuery query = new OrgUnitQuery(new QueryFactory());
    query.WHERE(query.getValid().EQ(false));
    OIterator<? extends OrgUnit> it = query.getIterator();
    
    try
    {
      for (OrgUnit unit : it)
      {
        unit.delete();
        deleted++;
      }
    }
    finally
    {
      it.close();
    }
    
    logger.info("Org unit fetching completed. created = " + created + " updated = " + updated + " deleted = " + deleted);
  }
  
  protected void populateGeoMap()
  {
    int newMappings = 0;
    
    ResultSet resultSet = Database.query("select id from geo_entity ge where ge.id not in (select geo_entity from geo_map)");

    try
    {
      while (resultSet.next())
      {
        GeoMap map = new GeoMap();
        map.setValue(GeoMap.GEOENTITY, resultSet.getString("id"));
        map.apply();
      }
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
      try
      {
        java.sql.Statement statement = resultSet.getStatement();
        resultSet.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }
    
    logger.info("Added " + newMappings + " new mappings to the GeoMap table.");
  }
  
  protected void populateGeoLevelMap()
  {
    int newMappings = 0;
    
    ResultSet resultSet = Database.query("select geo_entity_class from geo_hierarchy gh where gh.geo_entity_class not in (select universal from geo_level_map)");

    try
    {
      while (resultSet.next())
      {
        GeoLevelMap map = new GeoLevelMap();
        map.setValue(GeoLevelMap.UNIVERSAL, resultSet.getString("geo_entity_class"));
        map.apply();
        
        newMappings++;
      }
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
      try
      {
        java.sql.Statement statement = resultSet.getStatement();
        resultSet.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }
    
    logger.info("Added " + newMappings + " new mappings to the GeoLevelMap table.");
  }
  
  protected void matchOrgUnitLevels()
  {
    int hits = 0;
    
    QueryFactory qf = new QueryFactory();
    
    ValueQuery vq = new ValueQuery(qf);
    OrgUnitLevelQuery levelq = new OrgUnitLevelQuery(qf);
    GeoHierarchyQuery ghq = new GeoHierarchyQuery(qf);
    GeoLevelMapQuery glmq = new GeoLevelMapQuery(qf);
    
    vq.SELECT(levelq.getId("levelId"));
    vq.SELECT(glmq.getId("mapId"));
    vq.WHERE(glmq.getConfirmed().EQ(false));
    vq.WHERE(F.TRIM(ghq.getGeoEntityClass().getDisplayLabel().localize()).EQi(F.TRIM(levelq.getName())));
    vq.AND(glmq.getUniversal().EQ(ghq.getGeoEntityClass()));
    
    OIterator<ValueObject> it = vq.getIterator();
    
    try
    {
      for (ValueObject obj : it)
      {
        OrgUnitLevel ol = OrgUnitLevel.get(obj.getValue("levelId"));
        GeoLevelMap map = GeoLevelMap.get(obj.getValue("mapId"));
        
        map.appLock();
        map.setOrgUnitLevel(ol);
        map.apply();
        
        hits++;
      }
    }
    finally
    {
      it.close();
    }
    
    logger.info("Org unit level matching completed. hits = " + hits);
  }
  
  protected void matchOrgUnitsByCode()
  {
    int hits = 0;
    
    QueryFactory qf = new QueryFactory();
    
    ValueQuery vq = new ValueQuery(qf);
    GeoMapQuery gmq = new GeoMapQuery(qf);
    OrgUnitQuery ouq = new OrgUnitQuery(qf);
    
    vq.SELECT(gmq.getId("geoMapId"));
    vq.SELECT(ouq.getId("orgUnitId"));
    vq.WHERE(gmq.getConfirmed().EQ(false));
    vq.WHERE(ouq.getCode().EQ(gmq.getGeoEntity().getGeoId()));
    
    OIterator<? extends ValueObject> it = vq.getIterator();
    
    try
    {
      for (ValueObject obj : it)
      {
        GeoMap map = GeoMap.get(obj.getValue("geoMapId"));
        OrgUnit unit = OrgUnit.get(obj.getValue("orgUnitId"));
        
        map.appLock();
        map.setOrgUnit(unit);
        map.setConfirmed(false);
        map.apply();
        
        hits++;
      }
    }
    finally
    {
      it.close();
    }
    
    logger.info("Org unit code matching completed. hits = " + hits);
  }
  
  protected void matchOrgUnitsByName()
  {
    int hits = 0;
    
    
    ResultSet resultSet = Database.query("SELECT\n" + 
        "  gm.id AS geoMapId,\n" + 
        "  ou.id AS orgUnitId\n" + 
        "FROM\n" + 
        "  geo_entity AS ge\n" + 
        "  INNER JOIN geo_entity_entity_label AS lbl ON lbl.id=ge.entity_label\n" + 
        "  INNER JOIN geo_map AS gm ON gm.geo_entity=ge.id\n" + 
        "  INNER JOIN metadata AS uni ON uni.key_name=ge.type\n" + 
        "  INNER JOIN geo_level_map glm ON glm.universal=uni.id\n" + 
        "  LEFT JOIN org_unit_level oul ON oul.id=glm.org_unit_level\n" + 
        "  FULL OUTER JOIN org_unit AS ou ON ou.org_unit_level=oul.id\n" + 
        "\n" + 
        "WHERE\n" + 
        "  gm.confirmed = 0 AND\n" + 
        "  UPPER(ou.name) = UPPER(COALESCE(lbl.m_alaria_default_locale, lbl.default_locale))");

    try
    {
      while (resultSet.next())
      {
        GeoMap map = GeoMap.get(resultSet.getString("geoMapId"));
        OrgUnit unit = OrgUnit.get(resultSet.getString("orgUnitId"));
        
        map.appLock();
        map.setOrgUnit(unit);
        map.setConfirmed(false);
        map.apply();
        
        hits++;
      }
    }
    catch (SQLException sqlEx1)
    {
      Database.throwDatabaseException(sqlEx1);
    }
    finally
    {
      try
      {
        java.sql.Statement statement = resultSet.getStatement();
        resultSet.close();
        statement.close();
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }
    
    logger.info("Org unit name matching completed. hits = " + hits);
  }
}
