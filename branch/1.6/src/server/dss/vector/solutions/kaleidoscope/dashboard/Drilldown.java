package dss.vector.solutions.kaleidoscope.dashboard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;

public class Drilldown implements Reloadable
{
  public static final String  KEY = "drillDown";

  private String              geoId;

  private Map<String, String> universals;

  public Drilldown(String geoId, Map<String, String> universals)
  {
    super();
    this.geoId = geoId;
    this.universals = universals;
  }

  public String getGeoId()
  {
    return geoId;
  }

  public Map<String, String> getUniversals()
  {
    return universals;
  }

  @SuppressWarnings("unchecked")
  public static LinkedList<Drilldown> deserialize(String state)
  {
    if (state != null && state.length() > 0)
    {
      try
      {
        JSONObject context = new JSONObject(state);

        if (context.has(KEY))
        {
          LinkedList<Drilldown> list = new LinkedList<Drilldown>();

          JSONArray array = context.getJSONArray(KEY);

          for (int i = 0; i < array.length(); i++)
          {
            JSONObject drilldown = array.getJSONObject(i);
            String geoId = drilldown.getString("geoId");

            JSONObject oUniversals = drilldown.getJSONObject("universals");

            Map<String, String> universals = new HashMap<String, String>();

            Iterator<String> layers = oUniversals.keys();

            while (layers.hasNext())
            {
              String layerId = layers.next();

              universals.put(layerId, oUniversals.getString(layerId));
            }

            list.add(new Drilldown(geoId, universals));
          }

          return list;
        }
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    return new LinkedList<Drilldown>();
  }
}
