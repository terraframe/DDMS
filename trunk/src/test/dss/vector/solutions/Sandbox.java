package dss.vector.solutions;
import java.io.IOException;
import java.util.List;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.RoleDAOIF;
import com.terraframe.mojo.business.rbac.SingleActorDAOIF;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.ServerConstants;
import com.terraframe.mojo.dataaccess.EntityDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.CountryQuery;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Province;


public class Sandbox
{
  public static void main(String[] args) throws IOException
  {

    ClientSession session = ClientSession.createUserSession(ServerConstants.SYSTEM_USER_NAME, "mdsstest2");
    ClientRequestIF request = session.getRequest();

    createGeoEntities(request.getSessionId());


    //testDefineAllowedTree(request.getSessionId());
  }

  @StartSession
  private static void createGeoEntities(String sessionId) throws IOException
  {
     MDSSUser u = new MDSSUser();
     u.setUsername("MDSS");
     u.setPassword("MDSS");
     u.apply();

     SingleActorDAOIF uDAO = (SingleActorDAOIF) BusinessFacade.getEntityDAO(u);
     RoleDAO.findRole(RoleDAOIF.ADMIN_ROLE).assignMember(uDAO);
  }

  private static void r(GeoEntity child)
  {
    List<GeoEntity> parents = child.getImmediateParents();
    System.out.println(child.getId()+ " - " +child.getEntityName() + " - " + child.getType());
    for(GeoEntity parent : parents)
    {
      r(parent);
    }
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
