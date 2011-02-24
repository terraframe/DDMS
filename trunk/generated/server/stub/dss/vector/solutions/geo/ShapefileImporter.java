package dss.vector.solutions.geo;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeType;

import com.runwaysdk.dataaccess.transaction.ITaskListener;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.vividsolutions.jts.geom.Geometry;

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.TransactionExecuter;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.util.GeometryHelper;

/**
 * Class responsible for importing GeoEntity definitions from a shapefile.
 * 
 * @author Justin Smethie
 */
public class ShapefileImporter implements Runnable
{
  /**
   * URL of the file being imported
   */
  private URL                 url;

  /**
   * Class which extends GeoEntity which is being imported
   */
  private Class<GeoEntity>    clazz;

  /**
   * Name of shapefile attribute which is used to derive the entity name
   */
  private String              name;

  /**
   * Optional name of the shapefile attribute which is used to derive the geo
   * id. If this value is null then the geo id is auto-generated.
   */
  private String              id;

  /**
   * Optional name of the shapefile attribute which is used to specify the
   * entity name or geo id of the parent entity for the entity being imported.
   * If this value is null than the parent is assumed to be Earth.
   */
  private String              parent;

  /**
   * Optional name of the shapefile attribute which is used to restrict the
   * parent to a specific universal type when searching for the parent entity.
   * If this value is null than the search does not restrict by type.
   */
  private String              parentType;

  /**
   * Helper used to convert Geometry data to Point and MultiPolygon data.
   */
  private GeometryHelper      helper;

  /**
   * Cached instance of Earth.
   */
  private Earth               earth;

  /**
   * Collection of ITaskListeners which are interested in the progress of the
   * import.
   */
  private List<ITaskListener> listeners;

  /**
   * Map between a feature id and its geo entity
   */
  private Map<String, String> entityIdMap;

  /**
   * @param url
   *          URL of the shapefile
   */
  public ShapefileImporter(URL url)
  {
    this.url = url;
    this.helper = new GeometryHelper();
    this.earth = Earth.getEarthInstance();
    this.listeners = new LinkedList<ITaskListener>();
    this.entityIdMap = new HashMap<String, String>();
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getParent()
  {
    return parent;
  }

  public void setParent(String parent)
  {
    this.parent = parent;
  }

  public String getParentType()
  {
    return parentType;
  }

  public void setParentType(String parentType)
  {
    this.parentType = parentType;
  }

  /**
   * @return Map between Shapefile Feature ID and the imported Entity id.
   */
  protected Map<String, String> getEntityIdMap()
  {
    return entityIdMap;
  }

  /**
   * @param type
   *          Fully qualified type of the entities being imported.
   */
  @SuppressWarnings("unchecked")
  public void setType(String type)
  {
    this.clazz = (Class<GeoEntity>) LoaderDecorator.load(type);
  }

  public void addListener(ITaskListener listener)
  {
    this.listeners.add(listener);
  }

  public void removeListener(ITaskListener listener)
  {
    this.listeners.remove(listener);
  }

  public void run()
  {
    try
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          ShapefileImporter.this.fireStart();

          ShapefileImporter.this.createEntities();
          ShapefileImporter.this.buildLocatedIn();

          ShapefileImporter.this.fireStartTask("REBUILD_ALL_PATHS", -1);

          // Rebuild the all paths table
          GeoEntity.buildAllPathsFast();

          ShapefileImporter.this.fireTaskDone(true);
        }
      }.execute();
    }
    catch (RuntimeException e)
    {
      this.fireTaskDone(false);

      throw e;
    }
    catch (Exception e)
    {
      this.fireTaskDone(false);

      throw new RuntimeException(e);
    }
  }

  /**
   * Builds the located in relationships for the imported entities
   */
  private void buildLocatedIn()
  {
    try
    {
      ShapefileDataStore store = new ShapefileDataStore(url);

      try
      {
        String[] typeNames = store.getTypeNames();

        for (String typeName : typeNames)
        {
          FeatureSource<SimpleFeatureType, SimpleFeature> source = store.getFeatureSource(typeName);

          // Display the geo entity information about each row
          FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures();

          FeatureIterator<SimpleFeature> iterator = collection.features();

          this.fireStartTask("BUILD_LOCATED_IN", collection.size());

          try
          {
            while (iterator.hasNext())
            {
              SimpleFeature feature = iterator.next();
              GeoEntity parent = this.getParent(feature);

              String entityId = this.entityIdMap.get(feature.getID());
              GeoEntity child = GeoEntity.get(entityId);

              LocatedIn locIn = child.addLocatedInGeoEntity(parent);
              locIn.applyWithoutCreatingAllPaths();

              this.fireTaskProgress(1);
            }
          }
          finally
          {
            iterator.close();
          }
        }
      }
      finally
      {
        store.dispose();
      }
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Imports the entities from the shapefile
   */
  private void createEntities()
  {
    try
    {
      ShapefileDataStore store = new ShapefileDataStore(url);

      try
      {
        String[] typeNames = store.getTypeNames();

        for (String typeName : typeNames)
        {
          FeatureSource<SimpleFeatureType, SimpleFeature> source = store.getFeatureSource(typeName);

          // Display the geo entity information about each row
          FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures();

          FeatureIterator<SimpleFeature> iterator = collection.features();

          this.fireStartTask("IMPORT_ENTITIES", collection.size());

          try
          {
            while (iterator.hasNext())
            {
              SimpleFeature feature = iterator.next();
              Geometry geometry = (Geometry) feature.getDefaultGeometry();

              String entityName = this.getName(feature);
              String geoId = this.getGeoId(feature);

              GeoEntity entity = newInstance();
              entity.setGeoData(geometry.toText());
              entity.setGeoPoint(helper.getGeoPoint(geometry));
              entity.setGeoMultiPolygon(helper.getGeoMultiPolygon(geometry));
              entity.setEntityName(entityName);
              entity.setGeoId(geoId);
              entity.apply();

              this.entityIdMap.put(feature.getID(), entity.getId());

              this.fireTaskProgress(1);
            }
          }
          finally
          {
            iterator.close();
          }
        }
      }
      finally
      {
        store.dispose();
      }
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * @return A list of attributes defined in the shapefile
   */
  public List<String> getAttributes()
  {
    List<String> attributes = new LinkedList<String>();

    try
    {
      // get feature results
      ShapefileDataStore store = new ShapefileDataStore(url);
      String[] typeNames = store.getTypeNames();

      for (String name : typeNames)
      {
        FeatureSource<SimpleFeatureType, SimpleFeature> source = store.getFeatureSource(name);

        // print out a feature type header and wait for user input
        SimpleFeatureType schema = source.getSchema();

        List<AttributeType> types = schema.getTypes();

        for (AttributeType type : types)
        {
          // Filter out the geometry columns
          if (!Geometry.class.isAssignableFrom(type.getBinding()))
          {
            attributes.add(type.getName().toString());
          }
        }
      }
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }

    return attributes;
  }

  /**
   * Returns the entity as defined by the 'parent' and 'parentType' attributes
   * of the given feature. If an entity is not found then Earth is returned by
   * default. The 'parent' value of the feature must define an entity name or a
   * geo id. The 'parentType' value of the feature must define the localized
   * display label of the universal.
   * 
   * @param feature
   *          Shapefile feature used to determine the parent
   * @return Parent entity
   */
  private GeoEntity getParent(SimpleFeature feature)
  {
    if (this.parent != null && this.parentType != null)
    {
      Object _entityName = feature.getAttribute(this.parent);
      Object _type = feature.getAttribute(this.parentType);

      if (_entityName != null && _type != null)
      {

        String type = _type.toString();
        String entityName = _entityName.toString();

        GeoEntity parent = ShapefileImporter.getByEntityNameAndType(entityName, type);

        if (parent != null)
        {
          return parent;
        }
      }
    }

    if (this.parent != null)
    {
      Object _entityName = feature.getAttribute(this.parent);

      if (_entityName != null)
      {
        String entityName = _entityName.toString();

        GeoEntity parent = ShapefileImporter.getByEntityName(entityName);

        if (parent != null)
        {
          return parent;
        }
      }
    }

    return earth;
  }

  /**
   * @param feature
   *          Shapefile feature
   * 
   * @return The geoId as defined by the 'id' attribute on the feature. If the
   *         geoId is null than a generated geoId is returned.
   */
  private String getGeoId(SimpleFeature feature)
  {
    if (this.id != null)
    {
      Object geoId = feature.getAttribute(this.id);

      if (geoId != null)
      {
        return geoId.toString();
      }
    }

    return LocalProperty.getNextId();
  }

  /**
   * @param feature
   * @return The entityName as defined by the 'name' attribute of the feature
   */
  private String getName(SimpleFeature feature)
  {
    return feature.getAttribute(this.name).toString();
  }

  /**
   * @return A new instance of the desired class which extends GeoEntity.
   * 
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  private GeoEntity newInstance() throws InstantiationException, IllegalAccessException
  {
    return clazz.newInstance();
  }

  /**
   * Fires a start event
   */
  private void fireStart()
  {
    for (ITaskListener listener : listeners)
    {
      listener.start();
    }
  }

  /**
   * Fires a start task event
   * 
   * @param taskName
   *          Name of the task
   * @param amount
   *          Total amount of work which needs to be done by the task.
   */
  private void fireStartTask(String taskName, int amount)
  {
    for (ITaskListener listener : listeners)
    {
      listener.taskStart(taskName, amount);
    }
  }

  /**
   * Fires a task progress event
   * 
   * @param amount
   *          Amount of progress
   */
  private void fireTaskProgress(int amount)
  {
    for (ITaskListener listener : listeners)
    {
      listener.taskProgress(amount);
    }
  }

  /**
   * Fires a task done event
   * 
   * @param status
   *          Flag indicating if the task finished succesfully or not.
   */
  private void fireTaskDone(boolean status)
  {
    for (ITaskListener listener : listeners)
    {
      listener.done(status);
    }
  }

  /**
   * @param entityName
   *          Entity name of geo Id.
   * @param type
   *          Localized display label of the entity type
   * @return GeoEntity which satisfies the search criteria, or null of no
   *         entities meet the criteria.
   */
  public static GeoEntity getByEntityNameAndType(String entityName, String type)
  {
    GeoEntityQuery query = new GeoEntityQuery(new QueryFactory());
    query.WHERE(OR.get(query.getEntityName().EQ(entityName), query.getGeoId().EQ(entityName)));
    query.AND(query.getType().EQ("dss.vector.solutions.geo.generated." + type));

    long count = query.getCount();

    if (count > 1)
    {
      String message = "Multiple geo entities of the name [" + entityName + "] and type [" + type + "].";

      AmbigiousGeoEntityException e = new AmbigiousGeoEntityException(message);
      e.setEntityName(entityName);
      e.apply();

      throw e;
    }

    OIterator<? extends GeoEntity> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  /**
   * @param entityName
   *          Entity name of geo Id.
   * @return GeoEntity which satisfies the search criteria, or null of no
   *         entities meet the criteria.
   */
  public static GeoEntity getByEntityName(String entityName)
  {
    GeoEntityQuery query = new GeoEntityQuery(new QueryFactory());
    query.WHERE(query.getEntityName().EQ(entityName));
    query.OR(query.getGeoId().EQ(entityName));

    long count = query.getCount();

    if (count > 1)
    {
      String message = "Multiple geo entities of the name [" + entityName + "].";

      AmbigiousGeoEntityException e = new AmbigiousGeoEntityException(message);
      e.setEntityName(entityName);
      e.apply();

      throw e;
    }

    OIterator<? extends GeoEntity> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }
}
