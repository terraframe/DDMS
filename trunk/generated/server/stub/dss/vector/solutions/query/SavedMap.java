package dss.vector.solutions.query;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.database.DatabaseException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.MDSSUser;

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
   * Refreshes the map data, including rerunning the queries and
   * creating DB views.
   */
  @Override
  @Transaction
  public String refreshMap()
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery hasQ = new HasLayersQuery(f);
    
    ValueQuery vq = new ValueQuery(f);
    vq.SELECT(hasQ.childId("child_id"), hasQ.getLayerPosition("layerPosition"));
    vq.WHERE(hasQ.parentId().EQ(this.getId()));
    vq.ORDER_BY_ASC(hasQ.getLayerPosition());
    
    Map<String, Integer> positions = new HashMap<String, Integer>();
    for(ValueObject o : vq.getIterator().getAll())
    {
      positions.put(o.getValue("child_id"), Integer.valueOf(o.getValue("layerPosition")));
    }
    
    Layer[] ordered = new Layer[positions.size()];
    for(Layer layer : this.getAllLayer().getAll())
    {
      ordered[positions.get(layer.getId())] = layer;
    }
    
    return MapUtil.createDBViews(ordered); 
  };
  
  /**
   * Creates a SavedMap and clones the given layers to be
   * applied as layers on the created SavedMap.
   */
  @Override
  @Transaction
  public LayerViewQuery createFromExisting(String existingMapId)
  {
    this.apply();
    
    // copy the layers from the existing map
    SavedMap existingMap = SavedMap.get(existingMapId);
    for(HasLayers existingRel : existingMap.getAllLayerRel().getAll())
    {
      Layer existingLayer = existingRel.getChild();
      Layer layer = new Layer();
      
      layer.setLayerName(existingLayer.getLayerName());
      layer.setSavedSearch(existingLayer.getSavedSearch());
      layer.setMdAttribute(existingLayer.getMdAttribute());
      layer.setGeoHierarchy(existingLayer.getGeoHierarchy());
      
      // copy the styles
      Styles existingStyles = existingLayer.getDefaultStyles();
      Styles styles = new Styles();
      
      styles.setFill(existingStyles.getFill());
      styles.setFontFamily(existingStyles.getFontFamily());
      styles.setFontSize(existingStyles.getFontSize());
      styles.addFontStyles(existingStyles.getFontStyles().get(0));
      
      styles.setPointStroke(existingStyles.getPointStroke());
      styles.setPointWidth(existingStyles.getPointWidth());
      
      styles.setPolygonFill(existingStyles.getPolygonFill());
      styles.setPolygonStroke(existingStyles.getPolygonStroke());
      styles.setPolygonWidth(existingStyles.getPolygonWidth());
      
      styles.addPointMarker(existingStyles.getPointMarker().get(0));
      
      styles.apply();
      
      layer.setDefaultStyles(styles);
      layer.apply();

      // copy the relationship      
      HasLayers rel = this.addLayer(layer);
      rel.setLayerPosition(existingRel.getLayerPosition());
      rel.apply();
    }
    
    return this.getAllLayers();
  }
  
  
  
  /**
   * Removes the given layer from this SavedMap and reorders
   * the other layers accordingly.
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
      while(iter.hasNext())
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
   * Moves the given layer to the desired position. All other layers
   * are repositioned accordingly.
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
      while(iter.hasNext())
      {
        HasLayers rel = iter.next();
        if(rel.getChildId().equals(layerId))
        {
          found = true;
        }
        else if(!found)
        {
          oldIndex++;
        }
        
        rels.add(rel);
      }
      
      // swap the moved layer position
      rels.add(layerPosition, rels.remove(oldIndex));
      
      // now reset all layer indexes
      int count = 0;
      for(HasLayers rel : rels)
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
   * Returns all non-default maps from all users. This
   * way users can view maps that others have created.
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
   * @param map FIXME the map parameter may not be needed
   * @return
   */
  @Transaction
  public static SavedMap loadDefaultMap(SavedMap map)
  {
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());
    
    DefaultSavedMap defaultMap = (DefaultSavedMap) mdssUser.getDefaultMap();
    if(defaultMap != null)
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
    long anHourAgo = System.currentTimeMillis() - (60 * 60 * 1000);
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
          catch(DatabaseException e)
          {
            // This is okay 
          }
        }
      }
      catch (NumberFormatException e)
      {
        // This can happen if there's a view that matches the prefix but doesn't have the timestamp.  Just ignore it.
      }
    }
  }  
  
}
