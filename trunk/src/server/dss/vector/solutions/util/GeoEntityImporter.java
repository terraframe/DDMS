/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.postgis.jts.JtsGeometry;
import org.postgresql.PGConnection;
import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;

import com.runwaysdk.business.Business;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.dataaccess.InvalidIdException;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.gis.dataaccess.database.PostGIS;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdEntity;
import com.vividsolutions.jts.geom.Geometry;

import dss.vector.solutions.DefaultGeoEntity;
import dss.vector.solutions.MdssLog;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyQuery;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;

/**
 * @author chris
 * 
 */
public class GeoEntityImporter
{
  private static String  UNIVERSAL_ID                  = "universal_id";

  private static String  UNIVERSAL_NAME                = "universal";

  private static String  UNIVERSAL_TABLE               = "universal";

  // private static String GEOM_CENTROID = "geom_centroid";
  // private static String GEOM_MULTIPOINT = "geom_multipoint";
  // private static String GEOM_LINESTRING = "geom_linestring";
  // private static String GEOM_POLYGON = "geom_polygon";
  private static String  GEOM_POINT                    = "geom_point";

  private static String  GEOM_MULTILINESTRING          = "geom_multilinestring";

  private static String  GEOM_MULTIPOLYGON             = "geom_multipolygon";

  private static String  GEOGRAPHIC_ENTITIES_GEOMETRY  = "geographic_entities_geometry";

  private static String  GEOGRAPHIC_ENTITIES_RELATIONS = "geographic_entities_relations";

  private static String  GEOGRAPHIC_ENTITIES           = "geographic_entities";

  private static String  INSTANCE_OF                   = "instance_of";

  private static String  LOCATED_IN                    = "located_in";

  private static String  GEO_ID                        = "geo_id";

  private static String  ENTITY_ID                     = "entity_id";

  private static String  GEO_NAME                      = "geo_name";

  private static int     BATCH_SIZE                    = 1000;

  private String         dbUser;

  private String         dbPassword;

  private String         dbName;

  private String         universalSpreadsheet;

  private DataSource     dataSource;

  private Connection     conn;

  private GeometryHelper geometryHelper                = new GeometryHelper();

  private class UniversalSubtype
  {
    public GeoHierarchy geoHierarchy;

    public Term         moTerm;

    public UniversalSubtype(GeoHierarchy geoHierarchy, Term moTerm)
    {
      this.geoHierarchy = geoHierarchy;
      this.moTerm = moTerm;
    }
  }

  private Map<Integer, UniversalSubtype> universalMap;

  public static final int                feedbackMod = 50;

  /**
   * @param args
   */
  @Request
  public static void main(String[] args) throws Exception
  {
    if (args.length != 4)
    {
      String errMsg = "\nIncorrect usage\n\n" + "Expected arguments:\n" + "1) database user name\n" + "2) database password\n" + "3) database name\n" + "4) universal spreadsheet filename";
      throw new RuntimeException(errMsg);
    }
    else
    {
      importGeoEntities(args);
    }
    
    CacheShutdown.shutdown();
  }

  @Transaction
  private void deleteAllTableRecords(String className)
  {
    MdEntity biz = MdEntity.getMdEntity(className);
    biz.deleteAllTableRecords();
  }

  @Transaction
  private void deleteGeoEntities()
  {
    // this.deleteAllTableRecords(AllPaths.CLASS);

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

  }

  @Request
  public static void importGeoEntities(String[] args) throws Exception
  {
    if (!"mrc_blank".equals(args[2]))
    {
      GeoEntityImporter geoEntityImporter = null;

      try
      {
        geoEntityImporter = new GeoEntityImporter(args[0], args[1], args[2], args[3]);
        geoEntityImporter.importGeoEntities();
      }
      finally
      {
        if (geoEntityImporter != null && geoEntityImporter.conn != null)
        {
          geoEntityImporter.conn.close();
        }
      }
    }
  }

  private GeoEntityImporter(String dbUser, String dbPassword, String dbName, String universalSpreadsheet) throws Exception
  {
    this.dbUser = dbUser;
    this.dbPassword = dbPassword;
    this.dbName = dbName;
    this.universalSpreadsheet = universalSpreadsheet;

    BaseDataSource pgDataSource = new PGSimpleDataSource();

    pgDataSource.setServerName(DatabaseProperties.getServerName());
    pgDataSource.setPortNumber(DatabaseProperties.getPort());
    pgDataSource.setDatabaseName(this.dbName);
    pgDataSource.setUser(this.dbUser);
    pgDataSource.setPassword(this.dbPassword);
    this.dataSource = (DataSource) pgDataSource;

    this.conn = this.dataSource.getConnection();

    PGConnection pgconn = (PGConnection) this.conn;

    try
    {
      pgconn.addDataType("geometry", org.postgis.jts.JtsGeometry.class);
      pgconn.addDataType("box3d", org.postgis.PGbox3d.class);
      pgconn.addDataType("box2d", org.postgis.PGbox2d.class);
    }
    catch (SQLException e)
    {
      String errMsg = e.getMessage();
      throw new ProgrammingErrorException(errMsg, e);
    }

    this.conn = (Connection) PostGIS.mapColumnTypes((PGConnection) this.conn);

    this.universalMap = new HashMap<Integer, UniversalSubtype>();
  }

  private void importGeoEntities() throws Exception
  {
    // deleteGeoEntities();
//    this.updateUniversalRoots();
    this.buildUniversalMap();
    this.createGeoEntities();
    this.createLocatedInRelationships();
  }

  @Transaction
  private void createLocatedInRelationships() throws Exception
  {

    int applyCount = 0;

    String sql = " SELECT rel." + LOCATED_IN + ", rel." + GEO_ID + ",\n" + "        ent." + ENTITY_ID + "\n" + "   FROM " + GEOGRAPHIC_ENTITIES_RELATIONS + " rel, " + GEOGRAPHIC_ENTITIES + " ent\n" + "  WHERE rel." + GEO_ID + " = ent." + GEO_ID + "\n" + appendFileteredUniversalClause();

    Statement statement = null;
    ResultSet resultSet = null;

    try
    {
      statement = this.conn.createStatement();

      statement.execute(sql);
      resultSet = statement.getResultSet();

      while (resultSet.next())
      {
        String locatedIn = resultSet.getString(LOCATED_IN);
        String geoId = resultSet.getString(GEO_ID);

        // Parent GeoEntity (will be Earth if unspecified)
        GeoEntity parentGeoEntity = null;
        if ( ( locatedIn != null ) && ( locatedIn.trim().length() > 0 ) && ( !locatedIn.trim().equals("1") ))
        {
          try
          {
            parentGeoEntity = GeoEntity.searchByGeoId(locatedIn);
          }
          catch (InvalidIdException iie)
          {
            parentGeoEntity = Earth.getEarthInstance();
          }
        }
        else
        {
          parentGeoEntity = Earth.getEarthInstance();
        }

        if (parentGeoEntity != null)
        {
          GeoEntity childGeoEntity = GeoEntity.searchByGeoId(geoId);

          System.out.print(".");

          applyCount++;

          if (applyCount % feedbackMod == 0)
          {
            System.out.println();
          }

          ( (LocatedIn) childGeoEntity.addParent(parentGeoEntity, LocatedIn.CLASS) ).applyWithoutCreatingAllPaths();

          // childGeoEntity.addLocatedInGeoEntity(parentGeoEntity).apply();
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

  private String appendFileteredUniversalClause()
  {
    /*************
     * WARNING WARNING WARNING WARNING **************************** This is a
     * GIANT HACK due to the Malawi data being utterly ridiculous -- They
     * created a single River entity that contains line data from ALL of the
     * rivers in the entire country! The geo_id is also used in Mozambique so we
     * need to use the geo_id/entity_id which IS unique across the three
     * countries
     */
    return " AND NOT (ent.geo_id = 15000 AND ent.entity_id=42542)";

    /*
     * "    AND rel."+INSTANCE_OF+" != 14 \n"+
     * "    AND rel."+INSTANCE_OF+" != 16 \n"+
     * "    AND rel."+INSTANCE_OF+" != 17 \n"+
     * "    AND rel."+INSTANCE_OF+" != 18 \n"+
     * "    AND rel."+INSTANCE_OF+" != 19";
     */
  }

  /*
   * SELECT geom.geom_centriod, rel.instance_of, rel.geo_id, ent.geo_name FROM
   * geographic_entities_geometry geom, geographic_entities_relations rel,
   * geographic_entities ent WHERE rel.geo_id = geom.geo_id AND rel.geo_id =
   * ent.geo_id and rel.instance_of = 1
   * " SELECT geom."+GEOM_CENTRIOD+", geom."+GEOM_LINESTRING+",
   * geom."+GEOM_POLYGON+", geom."+GEOM_MULTIPOINT+",
   * geom."+GEOM_MULTIPOLYGON+",\n" + " rel."+INSTANCE_OF+",
   * rel."+GEO_ID+",\n"+ " ent."+GEO_NAME+", ent."+ENTITY_ID+",\n"+ "
   * ent."+GAZ_ID+"\n"+ " FROM "+GEOGRAPHIC_ENTITIES_GEOMETRY+" geom,
   * "+GEOGRAPHIC_ENTITIES_RELATIONS+" rel, "+GEOGRAPHIC_ENTITIES+" ent\n"+ "
   * WHERE rel."+GEO_ID+" = geom."+GEO_ID+"\n"+ " AND rel."+GEO_ID+" =
   * ent."+GEO_ID;
   * 
   * GEOGRAPHIC_ENTITIES_GEOMETRY+" geom, "
   */
  private void createGeoEntities() throws Exception
  {

    int applyCount = 0;

    String sql = " SELECT geom." + GEOM_MULTILINESTRING + ", geom." + GEOM_POINT + ", geom." + GEOM_MULTIPOLYGON + ",\n" + "        rel." + INSTANCE_OF + ", rel." + GEO_ID + ",\n" + "        ent." + GEO_NAME + ", ent." + ENTITY_ID + "\n" + "   FROM " + GEOGRAPHIC_ENTITIES_RELATIONS + " rel,\n" + "        " + GEOGRAPHIC_ENTITIES + " ent LEFT JOIN " + GEOGRAPHIC_ENTITIES_GEOMETRY + " geom ON ent." + GEO_ID + " = geom." + GEO_ID + "\n" + "  WHERE rel." + GEO_ID + " = ent." + GEO_ID + "\n"
        + appendFileteredUniversalClause();

    Statement statement = null;
    ResultSet resultSet = null;

    ArrayList<GeoEntity> batch = new ArrayList<GeoEntity>(BATCH_SIZE);

    try
    {
      statement = this.conn.createStatement();

      statement.execute(sql);
      resultSet = statement.getResultSet();

      while (resultSet.next())
      {
        int universalId = resultSet.getInt(INSTANCE_OF);
        long geoId = resultSet.getLong(GEO_ID);
        String geoName = resultSet.getString(GEO_NAME);

        UniversalSubtype universalSubtype = this.universalMap.get(universalId);
        String type = universalSubtype.geoHierarchy.getGeoEntityClass().definesType();
        Class<?> businessClass = LoaderDecorator.load(type);
        GeoEntity geoEntity = (GeoEntity) businessClass.getConstructor().newInstance();

        geoEntity.setGeoId(Long.toString(geoId));
//        geoEntity.setEntityName(geoName);
        geoEntity.getEntityLabel().setValue(geoName);

        geoEntity.setTerm(universalSubtype.moTerm);

        JtsGeometry pointField = (JtsGeometry) resultSet.getObject(GEOM_POINT);
        JtsGeometry multiLineStringField = (JtsGeometry) resultSet.getObject(GEOM_MULTILINESTRING);
        JtsGeometry multiPolygonField = (JtsGeometry) resultSet.getObject(GEOM_MULTIPOLYGON);

        Geometry g = null;

        try
        {
          if (pointField != null)
          {
            g = pointField.getGeometry();
          }
          else if (multiLineStringField != null)
          {
            g = multiLineStringField.getGeometry();
          }
          else if (multiPolygonField != null)
          {
            g = multiPolygonField.getGeometry();
          }
          if (g != null)
          {
            geoEntity.setGeoData(g.toText());
            geoEntity.setGeoPoint(geometryHelper.getGeoPoint(g));
            geoEntity.setGeoMultiPolygon(geometryHelper.getGeoMultiPolygon(g));
            // businessClass.getMethod("setMultiPolygon",
            // MultiPolygon.class).invoke(geoEntity,
            // geometryHelper.getGeoMultiPolygon(g));
          }
        }
        catch (Exception e)
        {
          MdssLog.error("Error importing GeoEntity [" + geoName + "]  geoId: [" + geoId + "]  type: [" + type + "]", e);
          throw e;
        }

        // MdssLog.debug(geoPoint);
        // MdssLog.debug(geoMultiPolygon);
        System.out.print(".");

        applyCount++;

        if (applyCount % feedbackMod == 0)
        {
          System.out.println();
        }

        // geoEntity.apply();
        batch.add(geoEntity);
        if (batch.size() == BATCH_SIZE)
        {
          this.applyBatch(batch);
          batch = new ArrayList<GeoEntity>(BATCH_SIZE);
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

    this.applyBatch(batch);

    // All GeoEntities have been created so attempt to set the
    // default based on the system property.
    try
    {
      String geoId = Property.getStr(PropertyInfo.INSTALL_PACKAGE, PropertyInfo.COUNTRY_GEO_ID);
      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

      DefaultGeoEntity defaultGeoEntity = DefaultGeoEntity.getDefaultGeoEntity();
      defaultGeoEntity.appLock();
      defaultGeoEntity.setGeoEntity(geoEntity);
      defaultGeoEntity.apply();
    }
    catch (InvalidIdException e)
    {
      // No match found ... keep the current default
    }
  }

  @Transaction
  private void applyBatch(List<? extends Business> batch)
  {
    for (Business businessObject : batch)
    {
      businessObject.apply();
    }
  }

  private String getCellValue(Row row, int col)
  {
    return ExcelUtil.getString(row.getCell(col));
  }

  @Transaction
  private void buildUniversalMap() throws Exception
  {
    InputStream is = new FileInputStream(this.universalSpreadsheet);
    Workbook wb = WorkbookFactory.create(is);
    Sheet sheet = wb.getSheetAt(1); // Use second sheet
    int rowCount = 1; // Start at second row
    Row row = sheet.getRow(rowCount++);
    while (row != null && row.getCell(0).getCellType() != Cell.CELL_TYPE_BLANK)
    {
      int oldUniversalId = (int) row.getCell(0).getNumericCellValue();
      // String oldUniversalName = this.getCellValue(row, 1);
      String newUniversalName = this.getCellValue(row, 2);
      String moTermId = this.getCellValue(row, 3);
      Term moTerm = null;
      if (moTermId != null && moTermId.length() > 0)
      {
        moTerm = this.lookupTerm(moTermId);
      }
      this.universalMap.put(oldUniversalId, new UniversalSubtype(lookupGeoHierarchy(newUniversalName), moTerm));
      row = sheet.getRow(rowCount++);
    }
  }

  private GeoHierarchy lookupGeoHierarchy(String universalName)
  {
    String systemName = GeoHierarchy.getSystemName(universalName);

    // q.WHERE(F.UPPER(F.TRIM(q.getGeoEntityClass().getTypeName())).EQ(universalName.trim().toUpperCase())
    // .OR(F.UPPER(F.TRIM(q.getGeoEntityClass().getDisplayLabel().getDefaultLocale())).EQ(universalName.trim().toUpperCase())));

    QueryFactory f = new QueryFactory();
    GeoHierarchyQuery q = new GeoHierarchyQuery(f);
    q.WHERE(q.getGeoEntityClass().getTypeName().EQ(systemName));

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
      String errMsg = "Unable to find a universal with the name \"" + universalName + "\" (or \"" + systemName + "\")";
      System.err.println(errMsg);

      return null;
      // throw new RuntimeException(errMsg);
    }
  }

  private Term lookupTerm(String termId)
  {
    QueryFactory f = new QueryFactory();
    TermQuery q = new TermQuery(f);

    // q.WHERE(F.UPPER(F.TRIM(q.getGeoEntityClass().getTypeName())).EQ(universalName.trim().toUpperCase())
    // .OR(F.UPPER(F.TRIM(q.getGeoEntityClass().getDisplayLabel().getDefaultLocale())).EQ(universalName.trim().toUpperCase())));

    q.WHERE(q.getTermId().EQ(termId));

    OIterator<? extends Term> i = q.getIterator();

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
      String errMsg = "Unable to find a term with the id \"" + termId + "\"";
      System.err.println(errMsg);

      return null;
      // throw new RuntimeException(errMsg);
    }
  }
}
