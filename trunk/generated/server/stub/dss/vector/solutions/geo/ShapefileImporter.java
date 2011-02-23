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

public class ShapefileImporter implements Runnable
{
  private URL                 url;

  private Class<GeoEntity>    clazz;

  private String              name;

  private String              id;

  private String              locatedIn;

  private String              locatedInType;

  private GeometryHelper      helper;

  private Earth               earth;

  private List<ITaskListener> listeners;

  /**
   * Map between a feature id and its geo entity
   */
  private Map<String, String> entityIdMap;

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

  public String getLocatedIn()
  {
    return locatedIn;
  }

  public void setLocatedIn(String locatedIn)
  {
    this.locatedIn = locatedIn;
  }

  public String getLocatedInType()
  {
    return locatedInType;
  }

  public void setLocatedInType(String locatedInType)
  {
    this.locatedInType = locatedInType;
  }

  protected Map<String, String> getEntityIdMap()
  {
    return entityIdMap;
  }

  @SuppressWarnings("unchecked")
  public void setUniversal(String universal)
  {
    this.clazz = (Class<GeoEntity>) LoaderDecorator.load(universal);
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

  private GeoEntity getParent(SimpleFeature feature)
  {
    if (this.locatedIn != null && this.locatedInType != null)
    {
      Object _entityName = feature.getAttribute(this.locatedIn);
      Object _type = feature.getAttribute(this.locatedInType);

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

    if (this.locatedIn != null)
    {
      Object _entityName = feature.getAttribute(this.locatedIn);

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

  private String getName(SimpleFeature feature)
  {
    return feature.getAttribute(this.name).toString();
  }

  private GeoEntity newInstance() throws InstantiationException, IllegalAccessException
  {
    return clazz.newInstance();
  }

  private void fireStart()
  {
    for (ITaskListener listener : listeners)
    {
      listener.start();
    }
  }

  private void fireStartTask(String taskName, int amount)
  {
    for (ITaskListener listener : listeners)
    {
      listener.taskStart(taskName, amount);
    }
  }

  private void fireTaskProgress(int amount)
  {
    for (ITaskListener listener : listeners)
    {
      listener.taskProgress(amount);
    }
  }

  private void fireTaskDone(boolean status)
  {
    for (ITaskListener listener : listeners)
    {
      listener.done(status);
    }
  }

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
