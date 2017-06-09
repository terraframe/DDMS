package dss.vector.solutions.geoserver;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.gis.constants.GeoserverProperties;

public class GeoserverLayer implements GeoserverLayerIF
{
  public static enum LayerType {
    POINT, POLYGON, LINE
  }

  private String    layerName;

  private String    styleName;

  private LayerType layerType;

  public void setLayerName(String layerName)
  {
    this.layerName = layerName;
  }

  @Override
  public String getLayerName()
  {
    return this.layerName;
  }

  public void setStyleName(String styleName)
  {
    this.styleName = styleName;
  }

  @Override
  public String getStyleName()
  {
    return this.styleName;
  }

  public void setLayerType(LayerType layerType)
  {
    this.layerType = layerType;
  }

  public LayerType getLayerType()
  {
    return layerType;
  }

  @Override
  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    object.put("layerName", this.layerName);
    object.put("layerType", this.layerType.name());
    object.put("workspace", GeoserverProperties.getWorkspace());

    return object;
  }
}
