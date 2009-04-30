package dss.vector.solutions.query;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.gis.metadata.MdAttributeLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon;
import com.terraframe.mojo.system.gis.metadata.MdAttributePoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributePolygon;

import dss.vector.solutions.LineStyle;
import dss.vector.solutions.PointStyle;
import dss.vector.solutions.PolygonStyle;
import dss.vector.solutions.geo.GeoHierarchy;

public class Layer extends LayerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240900978895L;

  public Layer()
  {
    super();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();
    this.getGeometryStyle().lock();
    this.getTextStyle().lock();
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();
    this.getGeometryStyle().unlock();
    this.getTextStyle().unlock();
  }

  /**
   * Updates the given layer and its associated styles.
   *
   * @param geometryStyle
   * @param textStyle
   * @param layerId
   * @return
   */
  @Transaction
  public static Layer updateLayer(GeometryStyle geometryStyle, TextStyle textStyle, String layerId)
  {
    Layer layer = Layer.get(layerId);

    geometryStyle.apply();
    textStyle.apply();

    layer.unlock();
    return layer;
  }

  /**
   * Creates a new layer with default styles.
   *
   * @param savedSearchId
   * @param layerClass
   * @return The created Layer
   */
  @Transaction
  public static Layer createLayer(String savedSearchId, String layerClass)
  {
    GeoHierarchy geoH = GeoHierarchy.getGeoHierarchyFromType(layerClass);

    Layer layer = new Layer();
    layer.setGeoHierarchy(geoH);

    MdAttributeGeometry attrGeoMd = GeoHierarchy.getGeometry(geoH.getGeoEntityClass());
    GeometryStyle geoStyle;
    if(attrGeoMd instanceof MdAttributePoint
        || attrGeoMd instanceof MdAttributeMultiPoint)
    {
      geoStyle = new PointStyle();
    }
    else if(attrGeoMd instanceof MdAttributeLineString
        || attrGeoMd instanceof MdAttributeMultiLineString)
    {
      geoStyle = new LineStyle();
    }
    else if(attrGeoMd instanceof MdAttributePolygon
        || attrGeoMd instanceof MdAttributeMultiPolygon)
    {
      geoStyle = new PolygonStyle();
    }
    else
    {
      String error = "The Geometry type ["+attrGeoMd.getType()+"] is not supported as a layer.";
      throw new ProgrammingErrorException(error);
    }
    geoStyle.apply(); // use defaults
    layer.setGeometryStyle(geoStyle);

    // text style
    TextStyle textStyle = new TextStyle();
    textStyle.apply();
    layer.setTextStyle(textStyle);

    layer.apply();

    SavedSearch search = SavedSearch.get(savedSearchId);
    search.addDefinesLayers(layer).apply();

    return layer;
  }

}
