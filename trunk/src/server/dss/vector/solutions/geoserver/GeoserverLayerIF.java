package dss.vector.solutions.geoserver;

import org.json.JSONException;
import org.json.JSONObject;

public interface GeoserverLayerIF
{
  public String getLayerName();

  public String getStyleName();

  public JSONObject toJSON() throws JSONException;
}
