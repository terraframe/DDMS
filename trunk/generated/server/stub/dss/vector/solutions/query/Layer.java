package dss.vector.solutions.query;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.math.NumberRange;

import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.system.WebFile;
import com.terraframe.mojo.util.FileIO;

public class Layer extends LayerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240900978895L;

  public static final String GEO_VIEW_PREFIX = "geo$";
  
  public Layer()
  {
    super();
  }
  
  /**
   * Applies this Layer along with its Styles.
   */
  @Override
  @Transaction
  public void applyWithStyles(Styles styles, String savedMapId)
  {
    styles.apply();
    
    boolean isNew = this.isNew();
    
    if(isNew)
    {
      this.setDefaultStyles(styles);
    }
    
    this.apply();
    
    if(isNew)
    {
      SavedMap map = SavedMap.get(savedMapId);
      
      HasLayers rel = this.addMap(map);
      rel.setLayerPosition((int)map.getLayerCount());
      rel.apply();
    }
  }
  
  @Override
  public void apply()
  {
    if(this.isNew())
    {
      String name = GEO_VIEW_PREFIX + System.currentTimeMillis();
      this.setViewName(name);
    }
    
    super.apply();
  }
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();
    
    this.getDefaultStyles().lock();
    
    OIterator<? extends AbstractCategory> iter = this.getAllHasCategory();
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

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    this.getDefaultStyles().unlock();
    
    OIterator<? extends AbstractCategory> iter = this.getAllHasCategory();
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

  @Override
  @Transaction
  public void delete()
  {
    String webId = this.getSldFile();
    if(webId != null && webId.trim().length() > 0)
    {
      WebFile webFile = WebFile.get(webId);
      String path = webFile.getFilePath();
      String fileName = webFile.getFileName();
      String extension = webFile.getFileExtension();

      webFile.delete();
      
      // remove the SLD artifact
      String rootPath = LocalProperties.getWebDirectory();
      
      String filepath = rootPath + path + fileName + "." + extension;
      File file = new File(filepath);
      if(file.exists())
      {
        try
        {
          FileIO.deleteFile(file);
        }
        catch (IOException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
    
    super.delete();
    
    this.getDefaultStyles().delete();
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
  public static Layer updateThematicVariable(String layerId, ThematicVariable thematicVariable,
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


    Layer layer = Layer.get(layerId);

    // remove the previous ThematicVariable
    ThematicVariable oldVariable = layer.getThematicVariable();
    if(oldVariable != null)
    {
      oldVariable.delete();
    }

    // remove all prior Categories
    OIterator<? extends AbstractCategory> oldCategories = layer.getAllHasCategory();
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
      layer.addHasCategory(category).apply();
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
      //ranges[i] = category.getAsNumberRange();
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

  /*
   * FIXME MAP artifact from ThematicLayer
   * Creates a new ThematicLayer based off the given thematic layer type.
   *
   * @param thematicLayerType
   * @return
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
  */

  /* FIXME MAP artifact from UniversalLayer
  @Transaction
  public static Layer createLayer(String savedSearchId, String layerClass)
  {
    GeoHierarchy geoH = GeoHierarchy.getGeoHierarchyFromType(layerClass);
    MdBusiness md = geoH.getGeoEntityClass();
    String viewName = md.getTypeName().toLowerCase() + QueryConstants.VIEW_NAME_SUFFIX;;

    UniversalLayer layer = new UniversalLayer();
    layer.setGeoHierarchy(geoH);
    layer.setViewName(viewName);

    // geo style
    MdAttributeGeometry attrGeoMd = geoH.getGeometry();
    GeometryStyle geoStyle = GeometryStyle.convertAttributeGeometryToStyle(attrGeoMd);
    layer.setGeometryStyle(geoStyle);

    // text style
    TextStyle textStyle = new TextStyle();
    textStyle.apply();
    layer.setTextStyle(textStyle);

    layer.apply();

    SavedSearch search = SavedSearch.get(savedSearchId);
    search.addDefinesLayers(layer).apply();

    return layer;
  }*/
  

}