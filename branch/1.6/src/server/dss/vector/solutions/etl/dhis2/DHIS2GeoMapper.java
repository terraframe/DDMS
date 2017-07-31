package dss.vector.solutions.etl.dhis2;

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

import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

import dss.vector.solutions.etl.dhis2.response.DHIS2TrackerResponseProcessor;
import dss.vector.solutions.etl.dhis2.response.HTTPResponse;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.geo.GeoSynonymQuery;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.util.QueryUtil;

/**
 * This class is responsible for pulling org units from DHIS2 and associating them with a GeoEntity in DDMS
 * 
 * @author rrowlands
 */
public class DHIS2GeoMapper
{
  public static final String MAPPING_PREFIX = "GEO_";
  
  private AbstractDHIS2Connector dhis2;
  
  private Map<Integer, GeoHierarchy> universals;
  
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
    
    universals = new HashMap<Integer, GeoHierarchy>();
  }
  
  protected void mapAll()
  {
    mapAllInTransaction();
  }
  
  @Transaction
  protected void mapAllInTransaction()
  {
    try
    {
      deleteAll();
      mapOrgUnitLevels();
      mapOrgUnits();
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void deleteAll()
  {
    Database.parseAndExecute("DELETE FROM dhis2_id_mapping WHERE runway_id like '" + MAPPING_PREFIX + "%'");
  }
  
  protected void mapOrgUnitLevels() throws JSONException
  {
    HTTPResponse response = dhis2.apiGet("metadata", new NameValuePair[] {
        new NameValuePair("assumeTrue", "false"),
        new NameValuePair("organisationUnitLevels", "true")
    });
    DHIS2TrackerResponseProcessor.validateStatusCode(response); // TODO : We need better validation than just status code.
    
    JSONObject json = response.getJSONObject();
    
    JSONArray levels = json.getJSONArray("organisationUnitLevels");
    
    for (int i = 0; i < levels.length(); ++i)
    {
      JSONObject level = levels.getJSONObject(i);
      
      String name = "";
      if (level.has("name"))
      {
        name = level.getString("name");
      }
      else
      {
        continue;
      }
      
      GeoHierarchyQuery ghq = new GeoHierarchyQuery(new QueryFactory());
      ghq.WHERE(F.TRIM(ghq.getGeoEntityClass().getDisplayLabel().localize()).EQi(name.trim()));
      OIterator<? extends GeoHierarchy> it = ghq.getIterator();
      
      try
      {
        if (it.hasNext())
        {
          GeoHierarchy gh = it.next();
          
          Integer levelInt = Integer.parseInt(level.getString("level"));
          
          DHIS2Util.mapIds(MAPPING_PREFIX + gh.getId(), level.getString("id"));
          
          universals.put(levelInt, gh);
          
          logger.info("Successfully mapped orgUnitLevel [" + name + "] at level [" + levelInt + "].");
        }
        else
        {
          throw new RuntimeException("Could not find match for OrgUnitLevel [" + level.getString("name") + "].");
        }
      }
      finally
      {
        it.close();
      }
    }
    
    if (!universals.containsKey(0))
    {
      universals.put(0, GeoHierarchy.getByKey(Country.CLASS));
    }
  }
  
  protected void mapOrgUnits() throws JSONException
  {
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
      
      String name = "";
      if (unit.has("name"))
      {
        name = unit.getString("name");
      }
      else if (unit.has("shortName"))
      {
        name = unit.getString("shortName");
      }
      
      int level = -1;
      if (unit.has("path"))
      {
        String path = unit.getString("path");
        
        if (!path.startsWith("/"))
        {
          path = "/" + path;
        }
        
        level = StringUtils.countMatches(path, "/") - 1;
      }
      
      if (unit.has("code"))
      {
        String geoId = unit.getString("code");
        
        Savepoint sp = Database.setSavepoint();
        try
        {
          GeoEntity geoEntity = GeoEntity.getByKey(geoId); // The key is the geoId
          
//          logger.info("Mapping [" + name + "] level [" + level + "] to [" + geoEntity.getEntityLabel().getValue() + "] from geoId [" + geoId + "]." );
          
          DHIS2Util.mapIds(MAPPING_PREFIX + geoEntity.getId(), unit.getString("id"));
        }
        catch (DataNotFoundException e)
        {
          Database.rollbackSavepoint(sp);
          
          if (level != -1)
          {
            GeoHierarchy universal = universals.get(level);
            
            GeoEntity geoEntity = findGeoEntityNameMatch(name, universal);
            
            if (geoEntity != null)
            {
              DHIS2Util.mapIds(MAPPING_PREFIX + geoEntity.getId(), unit.getString("id"));
              
//              logger.info("Mapping [" + name + "] level [" + level + "] to [" + geoEntity.getEntityLabel().getValue() + "] from geoId [" + geoId + "]." );
            }
            else
            {
              logger.warn("No mapping found for shortName [" + name + "] and code [" + geoId + "] and level [" + level + "].");
            }
          }
        }
      }
      else if (unit.has("shortName"))
      {
        logger.warn("Expected code on [" + name + "] at level [" + level + "].");
      }
    }
  }
  
  // A good reference : TargetFieldGeoEntity.findGeoEntity
  protected GeoEntity findGeoEntityNameMatch(String label, GeoHierarchy universal)
  {
    QueryFactory factory = new QueryFactory();
    
    GeoSynonymQuery synonymQuery = new GeoSynonymQuery(factory);
    synonymQuery.WHERE(synonymQuery.getEntityName().EQ(label));
    
    MdBusinessDAOIF mdBusinessDAOIF = MdBusinessDAO.get(universal.getGeoEntityClassId());
    GeoEntityQuery query = (GeoEntityQuery) QueryUtil.getQuery(mdBusinessDAOIF, factory);
    query.AND(OR.get(query.getEntityLabel().localize().EQi(label), query.synonyms(synonymQuery)));
    
    OIterator<? extends GeoEntity> iterator = query.getIterator();
    
    try
    {
      if (iterator.hasNext())
      {
        GeoEntity entity = iterator.next();
        
        if (iterator.hasNext())
        {
          logger.warn("Multiple matches found for geo label [" + label + "] and universal [" + universal.getDisplayLabel() + "]");
        }
        
        return entity;
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }
}
