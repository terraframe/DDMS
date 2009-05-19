package dss.vector.solutions.query;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

public abstract class Layer extends LayerBase implements com.terraframe.mojo.generation.loader.Reloadable
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

    // Note that a geometry style may not exist until
    // a thematic layer type (geo entity type) has been defined.
    GeometryStyle geoStyle = this.getGeometryStyle();
    if(geoStyle != null)
    {
      this.getGeometryStyle().lock();
    }

    this.getTextStyle().lock();
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    // Note that a geometry style may not exist until
    // a thematic layer type (geo entity type) has been defined.
    GeometryStyle geoStyle = this.getGeometryStyle();
    if(geoStyle != null)
    {
      this.getGeometryStyle().unlock();
    }

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

}