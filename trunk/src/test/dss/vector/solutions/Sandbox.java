package dss.vector.solutions;
import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.MdTypeInfo;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.dataaccess.BusinessDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.BusinessDAOQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.generated.CountryQuery;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Province;


public class Sandbox
{
  public static void main(String[] args)
  {
    ClientSession session = ClientSession.createUserSession(ServerConstants.SYSTEM_USER_NAME, ServerConstants.SYSTEM_DEFAULT_PASSWORD);
    ClientRequestIF request = session.getRequest();
    
    createGeoEntities(request.getSessionId());
    //testDefineAllowedTree(request.getSessionId());
  }
  
  @StartSession
  private static void createGeoEntities(String sessionId)
  {
    createGeoEntities(); 
  }
  
  @Transaction
  private static void createGeoEntities()
  {
    GeoEntity country = new Country();
    country.setGeoId(genGeoId());
    country.setEntityName("Country 1");
    country.apply();
    
    GeoEntity province = new Province();
    province.setGeoId(genGeoId());
    province.setEntityName("Province 1");
    province.apply();
    province.addLocatedInGeoEntity(country).apply();
    
    GeoEntity district1 = new District();
    district1.setGeoId(genGeoId());
    district1.setEntityName("District 1");
    district1.apply();
    district1.addLocatedInGeoEntity(province).apply();
    
    GeoEntity district2 = new District();
    district2.setGeoId(genGeoId());
    district2.setEntityName("District 2");
    district2.apply();
    district2.addLocatedInGeoEntity(province).apply();
  }
  
  private static String genGeoId()
  {
    String r = String.valueOf(Math.random());
    return r.substring(r.length()-6);
  }
  
  @StartSession
  private static void testDefineAllowedTree(String sessionId)
  {
    QueryFactory f = new QueryFactory();
    CountryQuery q = new CountryQuery(f);
    
    OIterator<? extends GeoEntity> iter = q.getIterator();
    try
    {
      String gHid = iter.next().getId();
      String json = GeoHierarchy.defineAllowedTree(gHid);
      System.out.println(json);
    }
    finally
    {
      iter.close();
    }
  }
}
