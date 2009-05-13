package dss.vector.solutions.query;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;

import dss.vector.solutions.geo.GeoHierarchy;

public class ThematicLayer extends ThematicLayerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID          = 1241158194588L;

  public ThematicLayer()
  {
    super();
  }

  /**
   * Locks this Layer and all of its categories.
   */
  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    OIterator<? extends AbstractCategory> iter = this.getAllDefinesCategory();
    try
    {
      while (iter.hasNext())
      {
        iter.next().lock();
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Unlocks this Layer and all of its categories.
   */
  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    OIterator<? extends AbstractCategory> iter = this.getAllDefinesCategory();
    try
    {
      while (iter.hasNext())
      {
        iter.next().unlock();
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Updates a ThematicLayer with the given information.
   *
   * @param layerId
   * @param thematicVariable
   * @param categories
   * @return
   */
  @Transaction
  public static ThematicLayer updateThematicVariable(String layerId, ThematicVariable thematicVariable,
      AbstractCategory[] categories)
  {
    if (thematicVariable == null)
    {
      String error = "A layer is required when adding categories to a thematic variable.";
      CategoriesWithoutThematicException ex = new CategoriesWithoutThematicException(error);
      throw ex;
    }
    else
    {
      thematicVariable.apply();
    }

    ThematicLayer layer = ThematicLayer.get(layerId);

    // remove the previous ThematicVariable
    ThematicVariable oldVariable = layer.getThematicVariable();
    if(oldVariable != null)
    {
      oldVariable.delete();
    }

    // remove all prior Categories
    OIterator<? extends AbstractCategory> oldCategories = layer.getAllDefinesCategory();
    try
    {
      while (oldCategories.hasNext())
      {
        oldCategories.next().delete();
      }
    }
    finally
    {
      oldCategories.close();
    }

    layer.lock();
    layer.setThematicVariable(thematicVariable);
    layer.apply();

    for (AbstractCategory category : categories)
    {
      category.apply();
      layer.addDefinesCategory(category).apply();
    }

    return layer;
  }

  /**
   * Creates a new ThematicLayer based off the given thematic layer type.
   *
   * @param thematicLayerType
   * @return
   */
  @Transaction
  public static ThematicLayer newInstance(String thematicLayerType)
  {
    ThematicLayer thematicLayer = new ThematicLayer();

    if (thematicLayerType != null && thematicLayerType.trim().length() > 0)
    {
      GeoHierarchy thematicGeoH = GeoHierarchy.getGeoHierarchyFromType(thematicLayerType);
      MdAttributeGeometry geoAttr = thematicGeoH.getGeometry();

      thematicLayer.setGeoHierarchy(thematicGeoH);

      // Geo Style
      GeometryStyle geoStyle = GeometryStyle.convertAttributeGeometryToStyle(geoAttr);
      thematicLayer.setGeometryStyle(geoStyle);
    }
    else
    {
      thematicLayer.setGeoHierarchy(null);
      thematicLayer.setGeometryStyle(null);
    }

    // Create the view name, which has to be unique as unique as possible
    // without going over the maximum view name size.
    String viewName = "view_" + thematicLayer.getId().substring(0, 16);
    thematicLayer.setViewName(viewName);

    // text style
    TextStyle textStyle = new TextStyle();
    textStyle.apply();
    thematicLayer.setTextStyle(textStyle);
    thematicLayer.apply();

    return thematicLayer;
  }

  /**
   * Changes the underlying styles to reflect the thematic layer type.
   *
   */
  public void changeLayerType(String thematicLayerType)
  {
    GeoHierarchy thematicGeoH = GeoHierarchy.getGeoHierarchyFromType(thematicLayerType);
    MdAttributeGeometry geoAttr = thematicGeoH.getGeometry();

    // Geo Style
    GeometryStyle geoStyle = GeometryStyle.convertAttributeGeometryToStyle(geoAttr);

    this.appLock();
    this.setGeoHierarchy(thematicGeoH);
    this.setGeometryStyle(geoStyle);
    this.apply();
  }

}
