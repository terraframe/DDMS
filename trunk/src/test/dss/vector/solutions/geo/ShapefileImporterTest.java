package dss.vector.solutions.geo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.runwaysdk.dataaccess.transaction.ITaskListener;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.TransactionExecuter;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;

public class ShapefileImporterTest
{
  class Task
  {
    private String taskName;

    private int    total;

    private int    work;

    public Task(String taskName, int total)
    {
      this.taskName = taskName;
      this.total = total;
      this.work = 0;
    }

    public String getTaskName()
    {
      return taskName;
    }

    public int getTotal()
    {
      return total;
    }

    public int getWork()
    {
      return work;
    }

    public void addWork(int work)
    {
      this.work += work;
    }
  }

  class MockTaskListener implements ITaskListener
  {
    private boolean    isStarted;

    private boolean    isDone;

    private boolean    status;

    private List<Task> tasks;

    public MockTaskListener()
    {
      this.tasks = new LinkedList<Task>();
      this.isDone = false;
      this.isStarted = false;
      this.status = false;
    }

    @Override
    public void done(boolean status)
    {
      this.isDone = true;
      this.status = status;
    }

    @Override
    public void start()
    {
      this.isStarted = true;
    }

    @Override
    public void taskProgress(int amount)
    {
      this.tasks.get(0).addWork(amount);
    }

    @Override
    public void taskStart(String name, int amount)
    {
      this.tasks.add(0, new Task(name, amount));
    }

    public List<Task> getTasks()
    {
      return tasks;
    }

    public boolean isDone()
    {
      return isDone;
    }

    public boolean isStarted()
    {
      return isStarted;
    }

    public boolean isStatus()
    {
      return status;
    }
  }

  private static final String NAME_ATTRIBUTE        = "EntityName";

  private static final String ID_ATTRIBUTE          = "ID_ATT";

  private static final String GEOMETRY_ATTRIBUTE    = "Location";

  private static final String PARENT_ATTRIBUTE      = "Parent";

  private static final String PARENT_TYPE_ATTRIBUTE = "ParentType";

  private static GeoEntity    parent;

  private static URL          url;

  private static GeoEntity    entity1;

  private static GeoEntity    entity2;

  @BeforeClass
  public static void classSetup() throws Exception
  {
    File file = new File("test/shapefile.shp");

    if (!file.exists())
    {
      file.getParentFile().mkdirs();
      file.createNewFile();
    }

    url = file.toURI().toURL();

    new TransactionExecuter()
    {

      @Override
      protected void executeMethod() throws Exception
      {
        parent = new Country();
        parent.setGeoId("TestParent01");
        parent.setEntityName("Test Parent Entity 2");
        parent.apply();

        entity1 = new Country();
        entity1.setGeoId("TestParent02");
        entity1.setEntityName("Test Parent Entity Duplicate");
        entity1.apply();

        entity2 = new Country();
        entity2.setGeoId("TestParent03");
        entity2.setEntityName("Test Parent Entity Duplicate");
        entity2.apply();
      }
    }.execute();
  }

  @AfterClass
  public static void classTearDown() throws Exception
  {
    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        parent.deleteEntity();
        entity1.deleteEntity();
        entity2.deleteEntity();
      }
    }.execute();
  }

  private static void createPointShapefile() throws IOException, ParseException
  {
    // Build the test shapefile
    SimpleFeatureTypeBuilder schemaBuilder = new SimpleFeatureTypeBuilder();
    schemaBuilder.setName("Flag");
    schemaBuilder.setNamespaceURI("http://localhost/");
    schemaBuilder.setCRS(DefaultGeographicCRS.WGS84);

    // add attributes in order
    schemaBuilder.add(GEOMETRY_ATTRIBUTE, Point.class);
    schemaBuilder.add(ID_ATTRIBUTE, String.class);
    schemaBuilder.add(NAME_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_TYPE_ATTRIBUTE, String.class);

    SimpleFeatureType schema = schemaBuilder.buildFeatureType();

    ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();

    Map<String, Serializable> params = new HashMap<String, Serializable>();
    params.put("url", url);
    params.put("create spatial index", Boolean.TRUE);

    ShapefileDataStore store = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
    store.createSchema(schema);

    FeatureWriter<SimpleFeatureType, SimpleFeature> writer = store.getFeatureWriter(Transaction.AUTO_COMMIT);

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

    WKTReader reader = new WKTReader(geometryFactory);

    try
    {
      for (int i = 0; i < 100; i++)
      {
        Geometry geometry = reader.read("POINT (" + i + " " + i + ")");
        String geoId = ShapefileImporterTest.getGeoId(i);
        String name = ShapefileImporterTest.getName(i);

        SimpleFeature feature = writer.next();

        feature.setAttribute(GEOMETRY_ATTRIBUTE, geometry);
        feature.setAttribute(ID_ATTRIBUTE, geoId);
        feature.setAttribute(NAME_ATTRIBUTE, name);
        feature.setAttribute(PARENT_ATTRIBUTE, parent.getEntityName());
        feature.setAttribute(PARENT_TYPE_ATTRIBUTE, "Country");

        writer.write();
      }
    }
    finally
    {
      writer.close();
    }
  }

  private static void createPointShapefileWithDuplicateLocatedIn() throws IOException, ParseException
  {
    // Build the test shapefile
    SimpleFeatureTypeBuilder schemaBuilder = new SimpleFeatureTypeBuilder();
    schemaBuilder.setName("Flag");
    schemaBuilder.setNamespaceURI("http://localhost/");
    schemaBuilder.setCRS(DefaultGeographicCRS.WGS84);

    // add attributes in order
    schemaBuilder.add(GEOMETRY_ATTRIBUTE, Point.class);
    schemaBuilder.add(ID_ATTRIBUTE, String.class);
    schemaBuilder.add(NAME_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_TYPE_ATTRIBUTE, String.class);

    SimpleFeatureType schema = schemaBuilder.buildFeatureType();

    ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();

    Map<String, Serializable> params = new HashMap<String, Serializable>();
    params.put("url", url);
    params.put("create spatial index", Boolean.TRUE);

    ShapefileDataStore store = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
    store.createSchema(schema);

    FeatureWriter<SimpleFeatureType, SimpleFeature> writer = store.getFeatureWriter(Transaction.AUTO_COMMIT);

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

    WKTReader reader = new WKTReader(geometryFactory);

    try
    {
      for (int i = 0; i < 100; i++)
      {
        Geometry geometry = reader.read("POINT (" + i + " " + i + ")");
        String geoId = ShapefileImporterTest.getGeoId(i);
        String name = ShapefileImporterTest.getName(i);

        SimpleFeature feature = writer.next();

        feature.setAttribute(GEOMETRY_ATTRIBUTE, geometry);
        feature.setAttribute(ID_ATTRIBUTE, geoId);
        feature.setAttribute(NAME_ATTRIBUTE, name);
        feature.setAttribute(PARENT_ATTRIBUTE, entity1.getEntityName());
        feature.setAttribute(PARENT_TYPE_ATTRIBUTE, "Country");

        writer.write();
      }
    }
    finally
    {
      writer.close();
    }
  }

  private static void createMultipolygonShapefile() throws IOException, ParseException
  {
    // Build the test shapefile
    SimpleFeatureTypeBuilder schemaBuilder = new SimpleFeatureTypeBuilder();
    schemaBuilder.setName("Flag");
    schemaBuilder.setNamespaceURI("http://localhost/");
    schemaBuilder.setCRS(DefaultGeographicCRS.WGS84);

    // add attributes in order
    schemaBuilder.add(GEOMETRY_ATTRIBUTE, Point.class);
    schemaBuilder.add(ID_ATTRIBUTE, String.class);
    schemaBuilder.add(NAME_ATTRIBUTE, String.class);

    SimpleFeatureType schema = schemaBuilder.buildFeatureType();

    ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();

    Map<String, Serializable> params = new HashMap<String, Serializable>();
    params.put("url", url);
    params.put("create spatial index", Boolean.TRUE);

    ShapefileDataStore store = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
    store.createSchema(schema);

    FeatureWriter<SimpleFeatureType, SimpleFeature> writer = store.getFeatureWriter(Transaction.AUTO_COMMIT);

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

    WKTReader reader = new WKTReader(geometryFactory);

    try
    {
      for (int i = 0; i < 100; i++)
      {
        int j = i + 100;
        int k = i + 200;

        Geometry geometry = reader.read("MULTIPOLYGON(((" + i + " " + i + "," + j + " " + j + "," + k + " " + k + "," + i + " " + i + "),(" + i + " " + j + "," + i + " " + k + "," + j + " " + j + "," + i + " " + j + ")),((" + j + " " + i + "," + j + " " + k + "," + k + " " + k + "," + j + " " + i + ")))");
        String geoId = ShapefileImporterTest.getGeoId(i);
        String name = ShapefileImporterTest.getName(i);

        SimpleFeature feature = writer.next();

        feature.setAttribute(GEOMETRY_ATTRIBUTE, geometry);
        feature.setAttribute(ID_ATTRIBUTE, geoId);
        feature.setAttribute(NAME_ATTRIBUTE, name);

        writer.write();
      }
    }
    finally
    {
      writer.close();
    }
  }

  private static void createPointShapefileWithNullIdColumn() throws IOException, ParseException
  {
    // Build the test shapefile
    SimpleFeatureTypeBuilder schemaBuilder = new SimpleFeatureTypeBuilder();
    schemaBuilder.setName("Flag");
    schemaBuilder.setNamespaceURI("http://localhost/");
    schemaBuilder.setCRS(DefaultGeographicCRS.WGS84);

    // add attributes in order
    schemaBuilder.add(GEOMETRY_ATTRIBUTE, Point.class);
    schemaBuilder.add(ID_ATTRIBUTE, String.class);
    schemaBuilder.add(NAME_ATTRIBUTE, String.class);

    SimpleFeatureType schema = schemaBuilder.buildFeatureType();

    ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();

    Map<String, Serializable> params = new HashMap<String, Serializable>();
    params.put("url", url);
    params.put("create spatial index", Boolean.TRUE);

    ShapefileDataStore store = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
    store.createSchema(schema);

    FeatureWriter<SimpleFeatureType, SimpleFeature> writer = store.getFeatureWriter(Transaction.AUTO_COMMIT);

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

    WKTReader reader = new WKTReader(geometryFactory);

    try
    {
      for (int i = 0; i < 100; i++)
      {
        Point point = (Point) reader.read("POINT (" + i + " " + i + ")");
        String name = ShapefileImporterTest.getName(i);

        SimpleFeature feature = writer.next();

        feature.setAttribute(GEOMETRY_ATTRIBUTE, point);
        feature.setAttribute(ID_ATTRIBUTE, "");
        feature.setAttribute(NAME_ATTRIBUTE, name);

        writer.write();
      }
    }
    finally
    {
      writer.close();
    }
  }

  private static void createPointShapefileWithNullLocatedInColumn() throws IOException, ParseException
  {
    // Build the test shapefile
    SimpleFeatureTypeBuilder schemaBuilder = new SimpleFeatureTypeBuilder();
    schemaBuilder.setName("Flag");
    schemaBuilder.setNamespaceURI("http://localhost/");
    schemaBuilder.setCRS(DefaultGeographicCRS.WGS84);

    // add attributes in order
    schemaBuilder.add(GEOMETRY_ATTRIBUTE, Point.class);
    schemaBuilder.add(ID_ATTRIBUTE, String.class);
    schemaBuilder.add(NAME_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_ATTRIBUTE, String.class);

    SimpleFeatureType schema = schemaBuilder.buildFeatureType();

    ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();

    Map<String, Serializable> params = new HashMap<String, Serializable>();
    params.put("url", url);
    params.put("create spatial index", Boolean.TRUE);

    ShapefileDataStore store = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
    store.createSchema(schema);

    FeatureWriter<SimpleFeatureType, SimpleFeature> writer = store.getFeatureWriter(Transaction.AUTO_COMMIT);

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

    WKTReader reader = new WKTReader(geometryFactory);

    try
    {
      for (int i = 0; i < 100; i++)
      {
        Geometry geometry = reader.read("POINT (" + i + " " + i + ")");
        String geoId = ShapefileImporterTest.getGeoId(i);
        String name = ShapefileImporterTest.getName(i);

        SimpleFeature feature = writer.next();

        feature.setAttribute(GEOMETRY_ATTRIBUTE, geometry);
        feature.setAttribute(ID_ATTRIBUTE, geoId);
        feature.setAttribute(NAME_ATTRIBUTE, name);
        feature.setAttribute(PARENT_ATTRIBUTE, "");

        writer.write();
      }
    }
    finally
    {
      writer.close();
    }
  }

  private static void createPointShapefileWithNullLocatedInTypeColumn() throws IOException, ParseException
  {
    // Build the test shapefile
    SimpleFeatureTypeBuilder schemaBuilder = new SimpleFeatureTypeBuilder();
    schemaBuilder.setName("Flag");
    schemaBuilder.setNamespaceURI("http://localhost/");
    schemaBuilder.setCRS(DefaultGeographicCRS.WGS84);

    // add attributes in order
    schemaBuilder.add(GEOMETRY_ATTRIBUTE, Point.class);
    schemaBuilder.add(ID_ATTRIBUTE, String.class);
    schemaBuilder.add(NAME_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_TYPE_ATTRIBUTE, String.class);

    SimpleFeatureType schema = schemaBuilder.buildFeatureType();

    ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();

    Map<String, Serializable> params = new HashMap<String, Serializable>();
    params.put("url", url);
    params.put("create spatial index", Boolean.TRUE);

    ShapefileDataStore store = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
    store.createSchema(schema);

    FeatureWriter<SimpleFeatureType, SimpleFeature> writer = store.getFeatureWriter(Transaction.AUTO_COMMIT);

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

    WKTReader reader = new WKTReader(geometryFactory);

    try
    {
      for (int i = 0; i < 100; i++)
      {
        Geometry geometry = reader.read("POINT (" + i + " " + i + ")");
        String geoId = ShapefileImporterTest.getGeoId(i);
        String name = ShapefileImporterTest.getName(i);

        SimpleFeature feature = writer.next();

        feature.setAttribute(GEOMETRY_ATTRIBUTE, geometry);
        feature.setAttribute(ID_ATTRIBUTE, geoId);
        feature.setAttribute(NAME_ATTRIBUTE, name);
        feature.setAttribute(PARENT_ATTRIBUTE, "");
        feature.setAttribute(PARENT_TYPE_ATTRIBUTE, "");

        writer.write();
      }
    }
    finally
    {
      writer.close();
    }
  }

  private static void createPointShapefileWithUnknownLocatedInTypeColumn() throws IOException, ParseException
  {
    // Build the test shapefile
    SimpleFeatureTypeBuilder schemaBuilder = new SimpleFeatureTypeBuilder();
    schemaBuilder.setName("Flag");
    schemaBuilder.setNamespaceURI("http://localhost/");
    schemaBuilder.setCRS(DefaultGeographicCRS.WGS84);

    // add attributes in order
    schemaBuilder.add(GEOMETRY_ATTRIBUTE, Point.class);
    schemaBuilder.add(ID_ATTRIBUTE, String.class);
    schemaBuilder.add(NAME_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_ATTRIBUTE, String.class);
    schemaBuilder.add(PARENT_TYPE_ATTRIBUTE, String.class);

    SimpleFeatureType schema = schemaBuilder.buildFeatureType();

    ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();

    Map<String, Serializable> params = new HashMap<String, Serializable>();
    params.put("url", url);
    params.put("create spatial index", Boolean.TRUE);

    ShapefileDataStore store = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
    store.createSchema(schema);

    FeatureWriter<SimpleFeatureType, SimpleFeature> writer = store.getFeatureWriter(Transaction.AUTO_COMMIT);

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

    WKTReader reader = new WKTReader(geometryFactory);

    try
    {
      for (int i = 0; i < 100; i++)
      {
        Geometry geometry = reader.read("POINT (" + i + " " + i + ")");
        String geoId = ShapefileImporterTest.getGeoId(i);
        String name = ShapefileImporterTest.getName(i);

        SimpleFeature feature = writer.next();

        feature.setAttribute(GEOMETRY_ATTRIBUTE, geometry);
        feature.setAttribute(ID_ATTRIBUTE, geoId);
        feature.setAttribute(NAME_ATTRIBUTE, name);
        feature.setAttribute(PARENT_ATTRIBUTE, parent.getEntityName());
        feature.setAttribute(PARENT_TYPE_ATTRIBUTE, "NoCountry");

        writer.write();
      }
    }
    finally
    {
      writer.close();
    }
  }

  public static String getName(int i)
  {
    return "Test Entity " + i;
  }

  public static String getGeoId(int i)
  {
    return "Geo Id " + i;
  }

  @Test
  public void testGetAttribute() throws IOException, ParseException
  {
    createPointShapefile();

    final ShapefileImporter importer = new ShapefileImporter(url);

    List<String> attributes = importer.getAttributes();

    assertEquals(4, attributes.size());

    assertTrue(attributes.contains(ID_ATTRIBUTE));
    assertTrue(attributes.contains(NAME_ATTRIBUTE));
    assertTrue(attributes.contains(PARENT_ATTRIBUTE));
    assertTrue(attributes.contains(PARENT_TYPE_ATTRIBUTE));
  }

  @Test
  public void testImportWithGeneratedIds() throws Exception
  {
    createPointShapefile();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(Country.CLASS);
    importer.setName(NAME_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        assertNotNull(ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i)));
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity entity = GeoEntity.get(id);

            entity.deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testImportWithIdAttribute() throws Exception
  {
    createPointShapefile();

    Earth earth = Earth.getEarthInstance();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(HealthFacility.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);

        List<GeoEntity> parents = entity.getAllParents();

        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
        assertEquals(2, parents.size());
        assertTrue(parents.contains(earth));
        assertTrue(parents.contains(entity));
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testImportWithNullIdAttribute() throws Exception
  {
    createPointShapefileWithNullIdColumn();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(Country.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);
        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertTrue(entity.getGeoId().length() > 0);
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testMultipolygonImport() throws Exception
  {
    createMultipolygonShapefile();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(Country.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);
        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testLocatedIn() throws Exception
  {
    createPointShapefile();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(HealthFacility.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);
    importer.setParent(PARENT_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);

        List<GeoEntity> parents = entity.getAllParents();

        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
        assertEquals(2, parents.size());
        assertTrue(parents.contains(parent));
        assertTrue(parents.contains(entity));
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testUnknownLocatedIn() throws Exception
  {
    createPointShapefile();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(HealthFacility.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);
    importer.setParent("bogusValue");

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    Earth earth = Earth.getEarthInstance();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);

        List<GeoEntity> parents = entity.getAllParents();

        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
        assertEquals(2, parents.size());
        assertTrue(parents.contains(earth));
        assertTrue(parents.contains(entity));
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testLocatedInWithLocatedInType() throws Exception
  {
    createPointShapefile();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(HealthFacility.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);
    importer.setParent(PARENT_ATTRIBUTE);
    importer.setParentType(PARENT_TYPE_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);

        List<GeoEntity> parents = entity.getAllParents();

        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
        assertEquals(2, parents.size());
        assertTrue(parents.contains(parent));
        assertTrue(parents.contains(entity));
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testNullLocatedIn() throws Exception
  {
    createPointShapefileWithNullLocatedInColumn();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(HealthFacility.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);
    importer.setParent(PARENT_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    Earth earth = Earth.getEarthInstance();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);

        List<GeoEntity> parents = entity.getAllParents();

        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
        assertEquals(2, parents.size());
        assertTrue(parents.contains(earth));
        assertTrue(parents.contains(entity));
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testLocatedInWithNullLocatedInType() throws Exception
  {
    createPointShapefileWithNullLocatedInTypeColumn();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(HealthFacility.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);
    importer.setParent(PARENT_ATTRIBUTE);
    importer.setParentType(PARENT_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    Earth earth = Earth.getEarthInstance();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);

        List<GeoEntity> parents = entity.getAllParents();

        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
        assertEquals(2, parents.size());
        assertTrue(parents.contains(earth));
        assertTrue(parents.contains(entity));
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }

  }

  @Test
  public void testLocatedInWithUnknownLocatedInType() throws Exception
  {
    createPointShapefileWithUnknownLocatedInTypeColumn();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(HealthFacility.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);
    importer.setParent(PARENT_ATTRIBUTE);
    importer.setParentType(PARENT_TYPE_ATTRIBUTE);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);

        List<GeoEntity> parents = entity.getAllParents();

        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
        assertEquals(2, parents.size());
        assertTrue(parents.contains(parent));
        assertTrue(parents.contains(entity));
      }
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testTaskListener() throws Exception
  {
    createPointShapefile();

    Earth earth = Earth.getEarthInstance();

    MockTaskListener listener = new MockTaskListener();

    final ShapefileImporter importer = new ShapefileImporter(url);
    importer.setType(HealthFacility.CLASS);
    importer.setName(NAME_ATTRIBUTE);
    importer.setId(ID_ATTRIBUTE);
    importer.addListener(listener);

    new TransactionExecuter()
    {
      @Override
      protected void executeMethod() throws Exception
      {
        importer.run();
      }
    }.execute();

    final Map<String, String> map = importer.getEntityIdMap();

    try
    {
      assertEquals(100, map.size());

      for (int i = 0; i < 100; i++)
      {
        GeoEntity entity = ShapefileImporter.getByEntityName(ShapefileImporterTest.getName(i));

        assertNotNull(entity);

        List<GeoEntity> parents = entity.getAllParents();

        assertEquals(ShapefileImporterTest.getName(i), entity.getEntityName());
        assertEquals(ShapefileImporterTest.getGeoId(i), entity.getGeoId());
        assertEquals(2, parents.size());
        assertTrue(parents.contains(earth));
        assertTrue(parents.contains(entity));
      }

      assertEquals(true, listener.isDone());
      assertEquals(true, listener.isStatus());
      assertEquals(true, listener.isStarted());

      List<Task> tasks = listener.getTasks();

      assertEquals(3, tasks.size());
      assertEquals(-1, tasks.get(0).getTotal());
      assertEquals(100, tasks.get(1).getTotal());
      assertEquals(100, tasks.get(2).getTotal());

      assertEquals(0, tasks.get(0).getWork());
      assertEquals(100, tasks.get(1).getWork());
      assertEquals(100, tasks.get(2).getWork());
    }
    finally
    {
      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();
    }
  }

  @Test
  public void testDuplicateLocatedIn() throws Exception
  {
    createPointShapefileWithDuplicateLocatedIn();

    try
    {
      final ShapefileImporter importer = new ShapefileImporter(url);
      importer.setType(HealthFacility.CLASS);
      importer.setName(NAME_ATTRIBUTE);
      importer.setId(ID_ATTRIBUTE);
      importer.setParent(PARENT_ATTRIBUTE);

      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          importer.run();
        }
      }.execute();

      final Map<String, String> map = importer.getEntityIdMap();

      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();

      fail("Able to import a shapefile with ambigous located in parents");
    }
    catch (AmbigiousGeoEntityException e)
    {
      // This is expected
      assertEquals(e.getEntityName(), entity1.getEntityName());
    }
  }

  @Test
  public void testDuplicateLocatedInWithType() throws Exception
  {
    createPointShapefileWithDuplicateLocatedIn();

    try
    {
      final ShapefileImporter importer = new ShapefileImporter(url);
      importer.setType(HealthFacility.CLASS);
      importer.setName(NAME_ATTRIBUTE);
      importer.setId(ID_ATTRIBUTE);
      importer.setParent(PARENT_ATTRIBUTE);
      importer.setParentType(PARENT_TYPE_ATTRIBUTE);

      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          importer.run();
        }
      }.execute();

      final Map<String, String> map = importer.getEntityIdMap();

      new TransactionExecuter()
      {
        @Override
        protected void executeMethod() throws Exception
        {
          for (String key : map.keySet())
          {
            String id = map.get(key);

            GeoEntity.get(id).deleteEntity();
          }
        }
      }.execute();

      fail("Able to import a shapefile with ambigous located in parents");
    }
    catch (AmbigiousGeoEntityException e)
    {
      // This is expected
      assertEquals(e.getEntityName(), entity1.getEntityName());
    }
  }
}
