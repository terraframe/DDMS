package dss.vector.solutions.geoserver;

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer;

public class GeoserverBatch implements Reloadable
{
  /**
   * These are for storing mass publish/deletes which can be pushArrayList<E> once for maximum efficiency.
   */
  private ArrayList<DashboardLayer>   layersToPublish;

  private ArrayList<String>           layersToDrop;

  private ArrayList<String>           stylesToDrop;

  public GeoserverBatch()
  {
    this.layersToPublish = new ArrayList<DashboardLayer>();
    this.layersToDrop = new ArrayList<String>();
    this.stylesToDrop = new ArrayList<String>();
  }

  public void addLayerToPublish(DashboardLayer layer)
  {
    this.layersToPublish.add(layer);
  }

  public void addLayerToDrop(DashboardLayer layer)
  {
    String viewName = layer.getViewName();

    if (viewName != null && viewName.length() > 0)
    {
      layersToDrop.add(viewName);
    }

    List<? extends DashboardStyle> styles = layer.getStyles();

    for (int i = 0; i < styles.size(); ++i)
    {
      DashboardStyle style = styles.get(i);

      String styleName = style.getName();

      if (styleName != null && styleName.length() > 0)
      {
        stylesToDrop.add(styleName);
      }
    }
  }

  public void addLayerToDrop(String layerName, String styleName)
  {
    layersToDrop.add(layerName);
    stylesToDrop.add(styleName);
  }

  public ArrayList<String> getLayersToDrop()
  {
    return layersToDrop;
  }

  public ArrayList<DashboardLayer> getLayersToPublish()
  {
    return layersToPublish;
  }

  public ArrayList<String> getStylesToDrop()
  {
    return stylesToDrop;
  }
}
