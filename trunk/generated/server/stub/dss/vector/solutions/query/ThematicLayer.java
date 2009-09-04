package dss.vector.solutions.query;

import org.apache.commons.lang.math.NumberRange;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;

import dss.vector.solutions.geo.GeoHierarchy;

public class ThematicLayer extends ThematicLayerBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID          = 1241158194588L;
  public static final String GEO_VIEW_PREFIX = "geo$";

  public ThematicLayer()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
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
    if (thematicVariable == null && categories != null && categories.length > 0)
    {
      String error = "A layer is required when adding categories to a thematic variable.";
      CategoriesWithoutThematicException ex = new CategoriesWithoutThematicException(error);
      throw ex;
    }
    else if(thematicVariable != null)
    {
      thematicVariable.apply();
      validateCategoryBounds(categories);
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
   * Ensures that no overlapping exists among category boundaries.
   *
   * @param categories
   */
  private static void validateCategoryBounds(AbstractCategory[] categories)
  {
    NumberRange[] ranges = new NumberRange[categories.length];

    for(int i=0; i<categories.length; i++)
    {
      AbstractCategory category = categories[i];
      ranges[i] = category.getAsNumberRange();
    }

    for(int i=0; i<ranges.length; i++)
    {
      for(int j=0; i<ranges.length; i++)
      {
        if(i != j && ranges[i].overlapsRange(ranges[j]))
        {
          OverlapBoundsException ex = new OverlapBoundsException();

          NumberRange range1 = ranges[i];
          Number min1 = range1.getMinimumNumber();
          Number max1 = range1.getMaximumNumber();
          ex.setRangeOne(min1.equals(max1) ? min1.toString() : min1.toString()+", "+max1.toString());

          NumberRange range2 = ranges[j];
          Number min2 = range2.getMinimumNumber();
          Number max2 = range2.getMaximumNumber();
          ex.setRangeTwo(min2.equals(max2) ? min2.toString() : min2.toString()+", "+max2.toString());

          throw ex;
        }
      }
    }
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

    String viewName = GEO_VIEW_PREFIX + System.currentTimeMillis();
//    String viewName = GEO_VIEW_PREFIX + thematicLayer.getId().substring(0, 16);
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
  @Override
  @Transaction
  public void changeLayerType(String thematicLayerType)
  {
    // Geo Style
    this.appLock();

    if (thematicLayerType != null && thematicLayerType.trim().length() > 0)
    {
      GeoHierarchy thematicGeoH = GeoHierarchy.getGeoHierarchyFromType(thematicLayerType);
      MdAttributeGeometry geoAttr = thematicGeoH.getGeometry();

      this.setGeoHierarchy(thematicGeoH);

      // Geo Style
      GeometryStyle geoStyle = GeometryStyle.convertAttributeGeometryToStyle(geoAttr);
      this.setGeometryStyle(geoStyle);
    }
    else
    {
      this.setGeoHierarchy(null);
      this.setGeometryStyle(null);
    }    
    

    this.apply();
  }
  
  public LayerView getAsView()
  {
    LayerView view = super.getAsView();
    
    view.setIsThematic(true);
    
    GeoHierarchy geo = this.getGeoHierarchy();
    if(geo != null)
    {
      view.setThematicType(geo.getGeoEntityClass().definesType());
    }
    
    return view;
  }

}
