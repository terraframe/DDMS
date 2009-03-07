package csu.mrc.ivcc.mdss;
import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdBusiness;

import csu.mrc.ivcc.mdss.geo.GeoHierarchy;
import csu.mrc.ivcc.mdss.geo.GeoHierarchyQuery;
import csu.mrc.ivcc.mdss.geo.generated.Country;
import csu.mrc.ivcc.mdss.geo.generated.District;
import csu.mrc.ivcc.mdss.geo.generated.GeoEntity;
import csu.mrc.ivcc.mdss.geo.generated.Province;


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
    MdBusiness mdGeoEntity = MdBusiness.getMdBusiness(Country.CLASS);
    
    QueryFactory f = new QueryFactory();
    GeoHierarchyQuery q = new GeoHierarchyQuery(f);
    q.WHERE(q.getGeoEntityClass().EQ(mdGeoEntity.getId()));
    
    OIterator<? extends GeoHierarchy> iter = q.getIterator();
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
