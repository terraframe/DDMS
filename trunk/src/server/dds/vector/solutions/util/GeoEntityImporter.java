package dds.vector.solutions.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;

import com.terraframe.mojo.constants.DatabaseProperties;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;
import com.vividsolutions.jts.io.WKTReader;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GeoEntityImporter 
{
  private static String UNIVERSAL_ID                  = "universal_id";
  
  private static String UNIVERSAL_NAME                = "universal";
  
  private static String UNIVERSAL_TABLE               = "universal";
  
  private static String GEOM_CENTRIOD                 = "geom_centriod";

  private static String GEOM_LINESTRING               = "geom_linestring";

  private static String GEOM_POLYGON                  = "geom_polygon";

  private static String GEOM_MULTIPOINT               = "geom_multipoint";
  
  private static String GEOM_MULTIPOLYGON             = "geom_multipolygon";
  
  private static String GEOGRAPHIC_ENTITIES_GEOMETRY  = "geographic_entities_geometry";
  
  private static String GEOGRAPHIC_ENTITIES_RELATIONS = "geographic_entities_relations";
  
  private static String GEOGRAPHIC_ENTITIES           = "geographic_entities";
  
  private static String INSTANCE_OF                   = "instance_of";

  private static String LOCATED_IN                    = "located_in";
  
  private static String GEO_ID                        = "geo_id";
  
  private static String ENTITY_ID                     = "entity_id";
  
  private static String GAZ_ID                        = "gaz_id";
  
  private static String GEO_NAME                      = "geo_name";
  
  private String       dbUser;
  
  private String       dbPassword;
  
  private String       dbName;

  private DataSource   dataSource;
  
  private Connection   conn;
  
  private Map<Integer, GeoHierarchy> geoHierarchyMap;
  
  private static final int feedbackMod = 50;
  
 /**
  * @param args
  */
  @StartSession
  public static void main(String[] args) throws Exception
  {
    if (args.length != 3)
    {
      String errMsg = 
        "\nIncorrect usage\n\n"+
        "Expected arguments:\n"+
        "1) database user name\n"+
        "2) database password\n"+
        "3) database name";
      throw new RuntimeException(errMsg);
    }
    else
    {
      importGeoEntities(args[0], args[1], args[2]);
//      deleteGeoEntityies();
    }    
  }
  
  @Transaction
  private static void deleteGeoEntityies()
  {
    System.out.println("Deleting GeoEntities ");
    
    int applyCount = 0;
    
    QueryFactory f = new QueryFactory();
    
    GeoEntityQuery q = new GeoEntityQuery(f);
    
    OIterator<? extends GeoEntity> i = q.getIterator();
    
    for (GeoEntity geoEntity : i)
    {
      System.out.print(".");
      
      applyCount++;
      
      if (applyCount % feedbackMod == 0)
      {
        System.out.println();
      }
      
      geoEntity.delete();
    }
    
    System.out.println("\nFINISHED\n");
  }
  
  public static void importGeoEntities(String dbUser, String dbPassword, String dbName) throws Exception
  {
    GeoEntityImporter geoEntityImporter = null;
    
    try
    {
      geoEntityImporter = new GeoEntityImporter(dbUser, dbPassword, dbName);
      geoEntityImporter.importGeoEntities();
    }
    finally
    {
      if (geoEntityImporter != null &&
          geoEntityImporter.conn != null)
      {
        geoEntityImporter.conn.close();
      }
    }
  }
  
  private GeoEntityImporter(String dbUser, String dbPassword, String dbName) throws Exception
  {
    this.dbUser = dbUser;
    this.dbPassword = dbPassword;
    this.dbName = dbName;

    BaseDataSource pgDataSource = new PGSimpleDataSource();

    pgDataSource.setServerName(DatabaseProperties.getServerName());
    pgDataSource.setPortNumber(DatabaseProperties.getPort());
    pgDataSource.setDatabaseName(this.dbName);
    pgDataSource.setUser(this.dbUser);
    pgDataSource.setPassword(this.dbPassword);
    this.dataSource = (DataSource)pgDataSource;

    this.conn = this.dataSource.getConnection();
    
    this.geoHierarchyMap = new HashMap<Integer, GeoHierarchy>();
  }

  @Transaction
  private void importGeoEntities() throws Exception
  {
//    this.buildGeoHierarchyMap();
    this.createGeoEntities();
//    this.createLocatedInRelationships();
  }

  
  private void createLocatedInRelationships() throws Exception
  {
    System.out.println("Creating GeoEntity LocatedIn Relatoinships ");
    
    int applyCount = 0;
    
    String sql = 
    " SELECT rel."+LOCATED_IN+", rel."+GEO_ID+",\n"+
    "        ent."+ENTITY_ID+"\n"+
    "   FROM "+GEOGRAPHIC_ENTITIES_RELATIONS+" rel, "+GEOGRAPHIC_ENTITIES+" ent\n"+
    "  WHERE rel."+GEO_ID+" = ent."+GEO_ID;

    Statement statement = null;
    ResultSet resultSet = null;

    try
    {
      statement = this.conn.createStatement();
      
      statement.execute(sql);
      resultSet = statement.getResultSet();
   
      while(resultSet.next())
      {
        String locatedIn = resultSet.getString(LOCATED_IN);
        String geoId = resultSet.getString(GEO_ID);
        
        if (locatedIn != null && !locatedIn.trim().equals(""))
        {
          GeoEntity childGeoEntity = GeoEntity.searchByGeoId(geoId);
        
          GeoEntity parentGoEntity = GeoEntity.searchByGeoId(locatedIn);
        
          System.out.print(".");
          
          applyCount++;
          
          if (applyCount % feedbackMod == 0)
          {
            System.out.println();
          }
          
          childGeoEntity.addLocatedInGeoEntity(parentGoEntity).apply();
        }
      }
    }
    finally
    {
      if (resultSet != null)
      {
        resultSet.close();
      }
      
      if (statement != null)
      {
        statement.close();
      }
    }
    System.out.println("\nFINISHED\n");
  }
  
  /*
 SELECT geom.geom_centriod,
        rel.instance_of, rel.geo_id,
        ent.geo_name
   FROM geographic_entities_geometry geom, geographic_entities_relations rel, geographic_entities ent
  WHERE rel.geo_id = geom.geo_id
    AND rel.geo_id = ent.geo_id
    and rel.instance_of = 1
  
      " SELECT geom."+GEOM_CENTRIOD+", geom."+GEOM_LINESTRING+", geom."+GEOM_POLYGON+", geom."+GEOM_MULTIPOINT+", geom."+GEOM_MULTIPOLYGON+",\n" +
      "        rel."+INSTANCE_OF+", rel."+GEO_ID+",\n"+
      "        ent."+GEO_NAME+", ent."+ENTITY_ID+",\n"+
      "        ent."+GAZ_ID+"\n"+
      "   FROM "+GEOGRAPHIC_ENTITIES_GEOMETRY+" geom, "+GEOGRAPHIC_ENTITIES_RELATIONS+" rel, "+GEOGRAPHIC_ENTITIES+" ent\n"+
      "  WHERE rel."+GEO_ID+" = geom."+GEO_ID+"\n"+
      "    AND rel."+GEO_ID+" = ent."+GEO_ID;
  
  GEOGRAPHIC_ENTITIES_GEOMETRY+" geom, "
  
  */
  private void createGeoEntities() throws Exception
  {
    System.out.println("Creating GeoEntities ");
    
    int applyCount = 0;
    
    String sql = 
      " SELECT geom."+GEOM_CENTRIOD+", geom."+GEOM_LINESTRING+", geom."+GEOM_POLYGON+", geom."+GEOM_MULTIPOINT+", geom."+GEOM_MULTIPOLYGON+",\n" +
      "        rel."+INSTANCE_OF+", rel."+GEO_ID+",\n"+
      "        ent."+GEO_NAME+", ent."+ENTITY_ID+",\n"+
      "        ent."+GAZ_ID+"\n"+
      "   FROM "+GEOGRAPHIC_ENTITIES_RELATIONS+" rel,\n" +
      "        "+GEOGRAPHIC_ENTITIES+" ent LEFT JOIN "+GEOGRAPHIC_ENTITIES_GEOMETRY+" geom ON ent."+GEO_ID+" = geom."+GEO_ID+"\n"+
      "  WHERE rel."+GEO_ID+" = ent."+GEO_ID;
    
    Statement statement = null;
    ResultSet resultSet = null;

    try
    {
      statement = this.conn.createStatement();
      
      statement.execute(sql);
      resultSet = statement.getResultSet();
      
      while(resultSet.next())
      {
        int universalId = resultSet.getInt(INSTANCE_OF);
        long geoId = resultSet.getLong(GEO_ID);
        long gazId = resultSet.getLong(GAZ_ID);
        String geoName = resultSet.getString(GEO_NAME);

        if (resultSet.getObject(GEOM_CENTRIOD) != null)
        {
//          Heads up:
          System.out.println("--------------------------------------------------------"); 
          
          org.postgis.PGgeometry pgGeometry = (org.postgis.PGgeometry)resultSet.getObject(GEOM_CENTRIOD);
            
          org.postgis.Geometry geometry = pgGeometry.getGeometry();     
          
          System.out.println(geometry.getClass().getName());     
          System.out.println(geometry.toString());

      System.out.println("Type: "+geometry.getType());
      System.out.println("Value: "+geometry.getValue());
      System.out.println("TypeString: "+geometry.getTypeString());      

          com.vividsolutions.jts.geom.Geometry jtsGeometry2 = new WKTReader().read( geometry.getTypeString()+geometry.getValue());
//          com.vividsolutions.jts.geom.Geometry jtsGeometry2 = new WKTReader().read( geometry.toString());
          
          System.out.println(jtsGeometry2.getClass().getName());
          System.out.println(jtsGeometry2.toString());   
        }
//        GeoHierarchy geoHierarchy = this.geoHierarchyMap.get(universalId);
//
//        String type = geoHierarchy.getGeoEntityClass().definesType();
//        
//        Class<?> businessClass = LoaderDecorator.load(type);
//        
//        GeoEntity geoEntity = (GeoEntity)businessClass.getConstructor().newInstance();
//        
//        geoEntity.setGeoId(Long.toString(geoId));
//        geoEntity.setGazId(gazId);
//        geoEntity.setEntityName(geoName);
//
//        JtsGeometry pontField = (JtsGeometry)resultSet.getObject(GEOM_CENTRIOD);
//        JtsGeometry lineStringField = (JtsGeometry)resultSet.getObject(GEOM_LINESTRING);
//        JtsGeometry polygonField = (JtsGeometry)resultSet.getObject(GEOM_POLYGON);
//        JtsGeometry multiPointField = (JtsGeometry)resultSet.getObject(GEOM_MULTIPOINT);
//        JtsGeometry multiPolygonField = (JtsGeometry)resultSet.getObject(GEOM_MULTIPOLYGON);
//      
//        try
//        {
//          if (pontField != null)
//          {
//            businessClass.getMethod("setPoint", Point.class).invoke(geoEntity, (Point)pontField.getGeometry());
//          }
//          else if (lineStringField != null)
//          {
//            businessClass.getMethod("setLineString", LineString.class).invoke(geoEntity, (LineString)lineStringField.getGeometry());
//          }
//          else if (polygonField != null)
//          {
//            businessClass.getMethod("setPolygon", Polygon.class).invoke(geoEntity, (Polygon)polygonField.getGeometry());
//          }
//          else if (multiPointField != null)
//          {
//            businessClass.getMethod("setMultiPoint", MultiPoint.class).invoke(geoEntity, (MultiPoint)multiPointField.getGeometry());
//          }
//          else if (multiPolygonField != null)
//          {
//            Geometry geometry = multiPolygonField.getGeometry();
//          
//            MultiPolygon multiPolygon;
//          
//            if (geometry instanceof Polygon)
//            {
//              multiPolygon = new MultiPolygon(new Polygon[]{(Polygon)geometry}, geometry.getFactory());
//            }
//            else
//            {
//              multiPolygon = (MultiPolygon)geometry;
//            }
//          
//            businessClass.getMethod("setMultiPolygon", MultiPolygon.class).invoke(geoEntity, multiPolygon);
//          }
//        }
//        catch(Exception e)
//        {
//          System.out.println(geoName+"  geoId: "+geoId+"  type: "+type); 
//          throw e;
//        }
//       
//        System.out.print(".");
//
//        applyCount++;
//       
//        if (applyCount % feedbackMod == 0)
//        {
//          System.out.println();
//        }
//        
//        geoEntity.apply();
      }
    }
    finally
    {
      if (resultSet != null)
      {
        resultSet.close();
      }
      
      if (statement != null)
      {
        statement.close();
      }
    }
    
    System.out.println("\nFINISHED\n");
  }
  
  
  private void buildGeoHierarchyMap() throws Exception
  {
    String sql = "SELECT "+UNIVERSAL_ID+", "+UNIVERSAL_NAME+" FROM "+UNIVERSAL_TABLE;

    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      statement = this.conn.createStatement();
      
      statement.execute(sql);
      resultSet = statement.getResultSet();
      
      while(resultSet.next())
      {
        int universalId = resultSet.getInt(UNIVERSAL_ID);
        String universalName = resultSet.getString(UNIVERSAL_NAME);
        
        GeoHierarchy geoHierarchy = lookupGeoHierarchy(universalName);

        if (geoHierarchy != null)
        {
          this.geoHierarchyMap.put(universalId, geoHierarchy);
        }
      }
    }
    finally
    {
      if (resultSet != null)
      {
        resultSet.close();
      }
      
      if (statement != null)
      {
        statement.close();
      }
    }
  }
  
  private GeoHierarchy lookupGeoHierarchy(String universalName)
  {
    QueryFactory f = new QueryFactory();
    GeoHierarchyQuery q = new GeoHierarchyQuery(f);

    q.WHERE(F.UPPER(F.TRIM(q.getGeoEntityClass().getTypeName())).EQ(universalName.trim().toUpperCase())
        .OR(F.UPPER(F.TRIM(q.getGeoEntityClass().getDisplayLabel())).EQ(universalName.trim().toUpperCase())));
    
    OIterator<? extends GeoHierarchy> i = q.getIterator();

    if (i.hasNext())
    {
      try
      {
        return i.next();
      }
      finally
      {
        i.close();
      }
    }
    else
    {
      String errMsg = "Unable to find a universal with the name \""+universalName+"\"";
      System.err.println(errMsg);
      
      return null;
//      throw new RuntimeException(errMsg);
    }
  }
}
