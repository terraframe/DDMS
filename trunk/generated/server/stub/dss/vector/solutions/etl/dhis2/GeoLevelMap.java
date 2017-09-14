package dss.vector.solutions.etl.dhis2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoLevelMap extends GeoLevelMapBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 267881842;

  public GeoLevelMap()
  {
    super();
  }

  public static String getAll()
  {
    GeoLevelMapQuery query = new GeoLevelMapQuery(new QueryFactory());
    query.WHERE(query.getUniversal().getTypeName().NE(Earth.class.getSimpleName()));
    query.AND(query.getUniversal().getTypeName().NE(GeoEntity.class.getSimpleName()));

    OIterator<? extends GeoLevelMap> it = query.getIterator();

    try
    {
      JSONArray response = new JSONArray();

      while (it.hasNext())
      {
        GeoLevelMap map = it.next();

        JSONObject jObject = new JSONObject();
        jObject.put("id", map.getId());
        jObject.put("levelId", map.getOrgUnitLevelId());
        jObject.put("label", map.getUniversal().getDisplayLabel().getValue());
        jObject.put("confirmed", map.getConfirmed());

        response.put(jObject);
      }

      return response.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      it.close();
    }
  }
}
