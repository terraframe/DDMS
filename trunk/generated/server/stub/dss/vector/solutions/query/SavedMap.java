package dss.vector.solutions.query;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeEnumerationDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.database.DatabaseException;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.util.ShapefileExporter;

public class SavedMap extends SavedMapBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252823927;

  public SavedMap()
  {
    super();
  }

  @Override
  public String toString()
  {
    return this.getMapName();
  }

  @Override
  public LayerViewQuery getAllLayers()
  {
    QueryFactory f = new QueryFactory();
    LayerViewQuery q = new LayerViewQuery(f, this.getId());

    return q;
  }

  /**
   * Refreshes the map data, including rerunning the queries and creating DB
   * views.
   */
  @Override
  @Transaction
  public String refreshMap()
  {
    JSONObject mapData;
    JSONArray layersJSON;

    Map<Layer, ValueQuery> layersVQ = MapUtil.createDBViews(getOrderedLayers(), false);

    try
    {
      String geoserverPath = MapUtil.getGeoServerRemoteURL();

      mapData = new JSONObject();
      layersJSON = new JSONArray();

      mapData.put("geoserverURL", geoserverPath);
      mapData.put("layers", layersJSON);
    }
    catch (JSONException e)
    {
      String error = "Could not create the mapping data.";
      throw new ProgrammingErrorException(error, e);
    }

    // Create the JSON for the layers that will be passed to OpenLayers
    List<Layer> bboxLayers = new LinkedList<Layer>();
    int count = 0;
    Iterator<Layer> iter = layersVQ.keySet().iterator();
    while (iter.hasNext())
    {
      Layer layer = iter.next();

      String namespacedView = QueryConstants.MDSS_NAMESPACE + ":" + layer.getViewName();
      String sldFile = MapUtil.formatSLD(layer);

      JSONObject layerJSON = new JSONObject();
      try
      {
        layerJSON.put("view", namespacedView);
        layerJSON.put("sld", sldFile);
        layerJSON.put("opacity", layer.getOpacity());

        // Always add the base layer to the bounding box
        if (count == 0 || layer.getAddToBBox())
        {
          bboxLayers.add(layer);
        }

        // Add the legend if enabled
        if (layer.getEnableLegend())
        {
          JSONObject legend = new JSONObject();
          legend.put("title", layer.getLegendTitle());
          legend.put("showLegendBorder", layer.getShowLegendBorder());
          legend.put("fontTitleFamily", layer.getLegendTitleFontFamily());
          legend.put("fontTitleFill", layer.getLegendTitleFontFill());
          legend.put("fontTitleSize", layer.getLegendTitleFontSize());
          legend.put("fontTitleStyle", layer.getLegendTitleFontStyles().get(0).name().toLowerCase());
          legend.put("fontFamily", layer.getLegendFontFamily());
          legend.put("fontFill", layer.getLegendFontFill());
          legend.put("fontSize", layer.getLegendFontSize());
          legend.put("fontStyle", layer.getLegendFontStyles().get(0).name().toLowerCase());
          
          MdAttributeDAO md = (MdAttributeDAO) MdAttributeDAO.get(layer.getValue(Layer.LEGENDCOLOR));
          String colorAttribute = md.definesAttribute();

          if (layer.getCreateRawLegend())
          {
            legend.put("createRawLegend", true);
            legend.put("color", layer.getDefaultStyles().getValue(colorAttribute));
          }
          else
          {
            JSONArray categories = new JSONArray();
            legend.put("categories", categories);

            List<? extends AbstractCategory> cats = layer.getAllHasCategory().getAll();
            CategorySorter.sort(cats);
            
            for (AbstractCategory cat : cats)
            {
              JSONObject category = new JSONObject();

              if (cat instanceof RangeCategory)
              {
                category.put("lower", ( (RangeCategory) cat ).getLowerBoundStr());
                category.put("upper", ( (RangeCategory) cat ).getUpperBoundStr());
              }
              else
              {
                category.put("exact", ( (NonRangeCategory) cat ).getExactValueStr());
              }

              category.put("color", cat.getStyles().getValue(colorAttribute));

              categories.put(category);
            }
          }

          layerJSON.put("legend", legend);
        }
        else
        {
          layerJSON.put("legend", JSONObject.NULL);
        }

        layersJSON.put(layerJSON);

        count++;
      }
      catch (JSONException e)
      {
        String error = "Could not produce the information for the layer [" + layer.getLayerName() + "]";
        throw new ProgrammingErrorException(error, e);
      }
    }

    // restrict the map bounds to the applicable layers
    try
    {
      mapData.put("bbox", MapUtil.getThematicBBox(bboxLayers));
    }
    catch (JSONException e)
    {
      String error = "Could not produce the bounding box.";
      throw new ProgrammingErrorException(error, e);
    }

    return mapData.toString();
  };

  /**
   * Returns the layers of this map in order of position on map, starting with
   * the base layer at index 0.
   * 
   * @return
   */
  private Layer[] getOrderedLayers()
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery hasQ = new HasLayersQuery(f);

    ValueQuery vq = new ValueQuery(f);
    vq.SELECT(hasQ.childId("child_id"), hasQ.getLayerPosition("layerPosition"));
    vq.WHERE(hasQ.parentId().EQ(this.getId()));
    vq.ORDER_BY_ASC(hasQ.getLayerPosition());

    Map<String, Integer> positions = new HashMap<String, Integer>();
    for (ValueObject o : vq.getIterator().getAll())
    {
      positions.put(o.getValue("child_id"), Integer.valueOf(o.getValue("layerPosition")));
    }

    Layer[] ordered = new Layer[positions.size()];
    for (Layer layer : this.getAllLayer().getAll())
    {
      ordered[positions.get(layer.getId())] = layer;
    }

    return ordered;
  }

  @Override
  public InputStream exportShapefile()
  {
    List<? extends Layer> layers = this.getAllLayer().getAll();
    if (layers.size() == 0)
    {
      String error = "The map [] does not have any layers to export.";
      throw new NoLayersInExportException(error);
    }

    Map<Layer, ValueQuery> layersVQ = MapUtil.createDBViews(getOrderedLayers(), false);
    Iterator<Layer> iter = layersVQ.keySet().iterator();
    List<Layer> layersInMap = new LinkedList<Layer>();
    while (iter.hasNext())
    {
      layersInMap.add(iter.next());
    }

    ShapefileExporter exporter = new ShapefileExporter();

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    exporter.export(layersInMap, output);

    InputStream input = new ByteArrayInputStream(output.toByteArray());

    return input;
  }
  
  private void copyStyles(Styles source, Styles dest)
  {
    for (MdAttributeDAOIF mdAttr : source.getMdClass().definesAttributes())
    {
      if (!mdAttr.isSystem())
      {
        String name = mdAttr.definesAttribute();

        if (mdAttr instanceof MdAttributeEnumerationDAOIF)
        {
          String value = source.getEnumValues(name).get(0).getId();
          dest.addEnumItem(name, value);
        }
        else
        {
          String value = source.getValue(name);
          dest.setValue(name, value);
        }
      }
    }
  }

  /**
   * Creates a SavedMap and clones the given layers to be applied as layers on
   * the created SavedMap.
   */
  @Override
  @Transaction
  public LayerViewQuery createFromExisting(String existingMapId)
  {
    this.apply();

    // copy the layers from the existing map
    SavedMap existingMap = SavedMap.get(existingMapId);
    for (HasLayers existingRel : existingMap.getAllLayerRel().getAll())
    {
      Layer existingLayer = existingRel.getChild();
      Layer layer = new Layer();

      for (MdAttributeDAOIF mdAttr : layer.getMdClass().definesAttributes())
      {
        if (!mdAttr.isSystem())
        {
          String name = mdAttr.definesAttribute();

          if (mdAttr instanceof MdAttributeEnumerationDAOIF)
          {
            String value = existingLayer.getEnumValues(name).get(0).getId();
            layer.addEnumItem(name, value);
          }
          else if (!name.equals(Layer.SLDFILE) && !name.equals(Layer.DEFAULTSTYLES))
          {
            String value = existingLayer.getValue(name);
            layer.setValue(name, value);
          }
        }
      }

      // copy the default layer styles
      Styles existingStyles = existingLayer.getDefaultStyles();
      Styles styles = new Styles();
      copyStyles(existingStyles, styles);
      styles.apply();

      layer.setDefaultStyles(styles);
      layer.apply();

      // copy all the categories on the layer
      for(AbstractCategory category : existingLayer.getAllHasCategory().getAll())
      {
        AbstractCategory copy;
        try
        {
          copy = (AbstractCategory) LoaderDecorator.load(category.getType()).newInstance();
        }
        catch(Throwable t)
        {
          throw new ProgrammingErrorException(t);
        }
        
        for(MdAttributeDAOIF mdAttr : copy.getMdClass().definesAttributes())
        {
          String name = mdAttr.definesAttribute();
          if(!mdAttr.isSystem() && !name.equals(AbstractCategory.STYLES))
          {
            copy.setValue(name, category.getValue(name));
          }
        }
        
        Styles cStyles = new Styles();
        copyStyles(category.getStyles(), cStyles);
        cStyles.apply();
        
        copy.setStyles(cStyles);
        copy.apply();
        
        layer.addHasCategory(copy).apply();
      }

      // copy the relationship between the SavedMap and Layer
      HasLayers rel = this.addLayer(layer);
      rel.setLayerPosition(existingRel.getLayerPosition());
      rel.apply();
      
    }

    return this.getAllLayers();
  }

  /**
   * Removes the given layer from this SavedMap and reorders the other layers
   * accordingly.
   */
  @Override
  @Transaction
  public void deleteLayerFromMap(String layerId)
  {
    Layer layer = Layer.get(layerId);
    layer.delete();

    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);
    q.WHERE(q.parentId().EQ(this.getId()));

    q.ORDER_BY_ASC(q.getLayerPosition());

    OIterator<? extends HasLayers> iter = q.getIterator();

    try
    {
      int count = 0;
      while (iter.hasNext())
      {
        HasLayers rel = iter.next();
        rel.appLock();
        rel.setLayerPosition(count++);
        rel.apply();
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Moves the given layer to the desired position. All other layers are
   * repositioned accordingly.
   * 
   */
  @Override
  @Transaction
  public void moveLayerOnMap(String layerId, Integer layerPosition)
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);
    q.WHERE(q.parentId().EQ(this.getId()));

    q.ORDER_BY_ASC(q.getLayerPosition());

    OIterator<? extends HasLayers> iter = q.getIterator();
    List<HasLayers> rels = new LinkedList<HasLayers>();

    try
    {
      int oldIndex = 0;
      boolean found = false;
      while (iter.hasNext())
      {
        HasLayers rel = iter.next();
        if (rel.getChildId().equals(layerId))
        {
          found = true;
        }
        else if (!found)
        {
          oldIndex++;
        }

        rels.add(rel);
      }

      // swap the moved layer position
      rels.add(layerPosition, rels.remove(oldIndex));

      // now reset all layer indexes
      int count = 0;
      for (HasLayers rel : rels)
      {
        rel.appLock();
        rel.setLayerPosition(count++);
        rel.apply();
      }
    }
    finally
    {
      iter.close();
    }
  }

  public long getLayerCount()
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);

    q.WHERE(q.parentId().EQ(this.getId()));

    return q.getCount();
  }

  /**
   * Returns all non-default maps from all users. This way users can view maps
   * that others have created.
   * 
   * @return
   */
  public static SavedMapQuery getAllSavedMaps()
  {
    QueryFactory f = new QueryFactory();
    SavedMapQuery q = new SavedMapQuery(f);

    q.WHERE(q.getType().NE(DefaultSavedMap.CLASS));

    return q;
  }

  /**
   * Loads the default map.
   * 
   * @param map
   *          FIXME the map parameter may not be needed
   * @return
   */
  @Transaction
  public static SavedMap loadDefaultMap(SavedMap map)
  {
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    DefaultSavedMap defaultMap = (DefaultSavedMap) mdssUser.getDefaultMap();
    if (defaultMap != null)
    {
      defaultMap.delete();
    }

    defaultMap = new DefaultSavedMap();
    defaultMap.apply();

    mdssUser.appLock();
    mdssUser.setDefaultMap(defaultMap);
    mdssUser.apply();

    return defaultMap;
  }

  /**
   * Cleans up all views older than an hour.
   */
  public static void cleanOldViews()
  {
    long anHourAgo = System.currentTimeMillis() - ( 60 * 60 * 1000 );
    for (String viewName : Database.getViewsByPrefix(Layer.GEO_VIEW_PREFIX))
    {
      String next = viewName;
      try
      {
        long parseLong = Long.parseLong(next.substring(Layer.GEO_VIEW_PREFIX.length()));
        if (parseLong < anHourAgo)
        {
          try
          {
            MapUtil.deleteMapView(viewName);
          }
          catch (DatabaseException e)
          {
            // This is okay
          }
        }
      }
      catch (NumberFormatException e)
      {
        // This can happen if there's a view that matches the prefix but doesn't
        // have the timestamp. Just ignore it.
      }
    }
  }

}
