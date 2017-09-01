package dss.vector.solutions.etl.dhis2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.F;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;

public class OrgUnit extends OrgUnitBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 27697990;

  public OrgUnit()
  {
    super();
  }

  @Override
  public String buildKey()
  {
    return this.getDhis2Id();
  }

  public static String search(String text, String geoId)
  {
    try
    {
//      GeoEntity entity = GeoEntity.get(geoId);
//      MdBusiness mdBusiness = MdBusiness.getMdBusiness(entity.getType());

      ValueQuery vQuery = new ValueQuery(new QueryFactory());

      OrgUnitQuery query = new OrgUnitQuery(vQuery);
//      GeoLevelMapQuery glQuery = new GeoLevelMapQuery(vQuery);

      Selectable label = F.CONCAT(F.CONCAT(F.CONCAT(query.getName(), " ("), F.CONCAT(query.getOrgUnitLevel().getName(), ") ")), F.COALESCE((Attribute) query.getCode(), "''"), "label");

      vQuery.SELECT(label, query.getId("id"));
      vQuery.WHERE(query.getName().LIKEi("%" + text + "%"));
//      vQuery.AND(query.getOrgUnitLevel().EQ(glQuery.getOrgUnitLevel()));
//      vQuery.AND(glQuery.getUniversal().EQ(mdBusiness));
      vQuery.ORDER_BY_ASC(query.getName());
      vQuery.restrictRows(20, 1);

      OIterator<ValueObject> it = vQuery.getIterator();

      try
      {
        JSONArray response = new JSONArray();

        while (it.hasNext())
        {
          ValueObject unit = it.next();

          JSONObject object = new JSONObject();
          object.put("text", unit.getValue("label"));
          object.put("data", unit.getValue("id"));

          response.put(object);
        }

        return response.toString();
      }
      finally
      {
        it.close();
      }

    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static void main(String[] args)
  {
    test();
  }

  @Request
  private static void test()
  {
    GeoLevelMap map = new GeoLevelMap();
    map.setUniversal(MdBusiness.getMdBusiness(Earth.CLASS));
    map.apply();

    map = new GeoLevelMap();
    map.setUniversal(MdBusiness.getMdBusiness(GeoEntity.CLASS));
    map.apply();

    // MdBusiness universal = MdBusiness.getMdBusiness(District.CLASS);
    //
    // GeoLevelMap map = new GeoLevelMap();
    // map.setUniversal(universal);
    // map.apply();
    //
    // OrgUnitLevel level = new OrgUnitLevel();
    // level.setDhis2Id("District");
    // level.setLevel(0);
    // level.setName("District");
    // level.apply();
    //
    // OrgUnit unit = new OrgUnit();
    // unit.setValid(true);
    // unit.setCode("DHISCODE");
    // unit.setDhis2Id("Test Location");
    // unit.setOrgUnitLevel(level);
    // unit.setName("Test Location");
    // unit.apply();
    //
    // OrgUnit unit2 = new OrgUnit();
    // unit2.setValid(true);
    // unit2.setCode("DHISCODE2");
    // unit2.setDhis2Id("Test District");
    // unit2.setOrgUnitLevel(level);
    // unit2.setName("Test Name");
    // unit2.apply();

    // OrgUnitLevel level = OrgUnitLevel.getByKey("District");
    //
    // OrgUnit unit3 = new OrgUnit();
    // unit3.setValid(true);
    // unit3.setDhis2Id("Test District 3");
    // unit3.setOrgUnitLevel(level);
    // unit3.setName("Test Name");
    // unit3.apply();
  }
}
