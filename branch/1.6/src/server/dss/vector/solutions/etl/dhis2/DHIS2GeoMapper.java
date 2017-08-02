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

import com.runwaysdk.business.Business;
import com.runwaysdk.dataaccess.DuplicateDataException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;

import dss.vector.solutions.etl.dhis2.response.DHIS2TrackerResponseProcessor;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.geo.generated.GeoEntity;

/**
 * This class is responsible for pulling org units from DHIS2 and associating them with a GeoEntity in DDMS
 * 
 * @author rrowlands
 */
public class DHIS2GeoMapper
{
  private AbstractDHIS2Connector dhis2;
  
  private Map<Integer, OrgUnitLevel> levels;
  
  private String[] countryOrgUnitExcludes;
  
  private static Logger logger = LoggerFactory.getLogger(DHIS2GeoMapper.class);
  
  public static void main(String[] args)
  {
    CommandLineParser parser = new DefaultParser();
    Options options = new Options();
    options.addOption(Option.builder("url").hasArg().argName("url").longOpt("url").desc("URL of the DHIS2 server to connect to, including the port. Defaults to: http://127.0.0.1:8085/").optionalArg(true).build());
    options.addOption(Option.builder("username").hasArg().argName("username").longOpt("username").desc("The username of the root (admin) DHIS2 user.").required().build());
    options.addOption(Option.builder("password").hasArg().argName("password").longOpt("password").desc("The password for the root (admin) DHIS2 user.").required().build());
    options.addOption(Option.builder("countryOrgUnitExcludes").hasArg().argName("countryOrgUnitExcludes").longOpt("countryOrgUnitExcludes").desc("DHIS2 does not support multiple countries. However, some systems are misconfigured and have multiple countries. Our importer does not support this. You must exclude the extraneous countries to get this importer to work. This is a comma separated list of org unit ids to exclude from the import.").optionalArg(true).build());
    
    try {
      CommandLine line = parser.parse( options, args );
      
      String url = line.getOptionValue("url");
      String username = line.getOptionValue("username");
      String password = line.getOptionValue("password");
      String countryOrgUnitExcludes = line.getOptionValue("countryOrgUnitExcludes");
      
      if (url == null)
      {
        url = "http://127.0.0.1:8085/";
      }
      
      doImportInRequest(url, username, password, countryOrgUnitExcludes);
    }
    catch (ParseException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  // Don't ever put an @Request on a main method or you will regret it I promise you
  @Request
  public static void doImportInRequest(String url, String username, String password, String countryOrgUnitExcludes)
  {
    DHIS2HTTPConfiguration config = DHIS2HTTPConfiguration.getByKey("DEFAULT");
    config.setPazzword(password);
    config.setUsername(username);
    config.setUrl(url);
    config.appLock();
    config.apply();
    
    new DHIS2GeoMapper(url, username, password, countryOrgUnitExcludes).mapAll();
  }
  
  public DHIS2GeoMapper(String url, String username, String password, String countryOrgUnitExcludes)
  {
    if (countryOrgUnitExcludes != null)
    {
      this.countryOrgUnitExcludes = StringUtils.split(countryOrgUnitExcludes, ",");
    }
    
    dhis2 = new DHIS2HTTPCredentialConnector();
    dhis2.setServerUrl(url);
    dhis2.setCredentials(username, password);
    
    levels = new HashMap<Integer, OrgUnitLevel>();
  }
  
  protected void mapAll()
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
  }
  
  protected void fetchOrgUnitLevels() throws JSONException
  {
    Database.parseAndExecute("UPDATE org_unit_level SET validFlag=0");
    
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
      }
      catch (DuplicateDataException e)
      {
        Database.rollbackSavepoint(sp);
        
        OrgUnitLevel dup = OrgUnitLevel.getByKey(oul.getDhis2Id());
        dup.setName(oul.getName());
        dup.setLevel(oul.getLevel());
        dup.setUniversal(oul.getUniversal());
        dup.setValid(true);
        dup.apply();
        
        oul = dup;
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
      }
    }
    finally
    {
      it.close();
    }
  }
  
  protected void fetchOrgUnits() throws JSONException
  {
    Database.parseAndExecute("UPDATE org_unit SET validFlag=0");
    
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
      }
      catch (DuplicateDataException e)
      {
        Database.rollbackSavepoint(sp);
        
        OrgUnit dup = OrgUnit.getByKey(org.getDhis2Id());
        dup.setCode(org.getCode());
        dup.setName(org.getName());
        dup.setPath(org.getPath());
        dup.setOrgUnitLevel(org.getOrgUnitLevel());
        dup.setParent(org.getParent());
        dup.apply();
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
      }
    }
    finally
    {
      it.close();
    }
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
  
  protected void matchOrgUnitLevels()
  {
    int hits = 0;
    
    QueryFactory qf = new QueryFactory();
    
    ValueQuery vq = new ValueQuery(qf);
    OrgUnitLevelQuery levelq = new OrgUnitLevelQuery(qf);
    GeoHierarchyQuery ghq = new GeoHierarchyQuery(qf);
    
    vq.SELECT(ghq.getId("geoHierarchyId"));
    vq.SELECT(levelq.getId("levelId"));
    vq.WHERE(F.TRIM(ghq.getGeoEntityClass().getDisplayLabel().localize()).EQi(F.TRIM(levelq.getName())));
    
    OIterator<ValueObject> it = vq.getIterator();
    
    try
    {
      for (ValueObject obj : it)
      {
        GeoHierarchy gh = (GeoHierarchy) Business.get(obj.getValue("geoHierarchyId"));
        OrgUnitLevel level = OrgUnitLevel.get(obj.getValue("levelId"));
        
        level.setUniversal(gh.getGeoEntityClass());
        
        level.apply();
        
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
    
    logger.info("Org unit matching completed. hits = " + hits);
  }
  
  // A good reference : TargetFieldGeoEntity.findGeoEntity
//  protected GeoEntity findOrgUnitNameMatch(String label, GeoHierarchy universal)
//  {
//    QueryFactory factory = new QueryFactory();
//    
//    GeoSynonymQuery synonymQuery = new GeoSynonymQuery(factory);
//    synonymQuery.WHERE(synonymQuery.getEntityName().EQ(label));
//    
//    MdBusinessDAOIF mdBusinessDAOIF = MdBusinessDAO.get(universal.getGeoEntityClassId());
//    GeoEntityQuery query = (GeoEntityQuery) QueryUtil.getQuery(mdBusinessDAOIF, factory);
//    query.AND(OR.get(query.getEntityLabel().localize().EQi(label), query.synonyms(synonymQuery)));
//    
//    OIterator<? extends GeoEntity> iterator = query.getIterator();
//    
//    try
//    {
//      if (iterator.hasNext())
//      {
//        GeoEntity entity = iterator.next();
//        
//        if (iterator.hasNext())
//        {
//          String msg = "Multiple matches found for geo label [" + label + "] and universal [" + universal.getDisplayLabel() + "]";
//          logger.warn(msg);
////          System.out.println(msg);
//        }
//        
//        return entity;
//      }
//
//      return null;
//    }
//    finally
//    {
//      iterator.close();
//    }
//  }
}
