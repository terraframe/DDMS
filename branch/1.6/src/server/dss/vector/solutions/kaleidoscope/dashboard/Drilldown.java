package dss.vector.solutions.kaleidoscope.dashboard;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;

public class Drilldown implements Reloadable
{
  public static final String KEY = "drillDown";

  private String             layerId;

  private String             geoId;

  private String             universalId;

  public Drilldown(String layerId, String geoId, String universalId)
  {
    super();
    this.layerId = layerId;
    this.geoId = geoId;
    this.universalId = universalId;
  }

  public String getGeoId()
  {
    return geoId;
  }

  public String getLayerId()
  {
    return layerId;
  }

  public String getUniversalId()
  {
    return universalId;
  }

  public static Map<String, Drilldown> deserialize(String state)
  {
    if (state != null && state.length() > 0)
    {
      try
      {
        JSONObject context = new JSONObject(state);

        if (context.has(KEY))
        {
          Map<String, Drilldown> map = new LinkedHashMap<String, Drilldown>();

          JSONArray array = context.getJSONArray(KEY);

          for (int i = 0; i < array.length(); i++)
          {
            JSONObject drilldown = array.getJSONObject(i);
            String layerId = drilldown.getString("layerId");
            String geoId = drilldown.getString("geoId");
            String universalId = drilldown.getString("universalId");

            map.put(layerId, new Drilldown(layerId, geoId, universalId));
          }

          return map;
        }
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    return new HashMap<String, Drilldown>();
  }
}
