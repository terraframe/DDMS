package dss.vector.solutions;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdTypeInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdEntity;
import com.runwaysdk.system.metadata.MdType;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;
import com.runwaysdk.system.metadata.SupportedLocale;
import com.runwaysdk.system.metadata.SupportedLocaleQuery;

import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

/**
 * IMPORTANT: THIS CLASS CAN NOT BE RELOADABLE. THIS IS DUE TO THE FACT THAT IT IS A SINGLETON CLASS, AND WHEN A CLASSLOADER IS DROPPED ALL OF THE STATIC FIELDS ON ALL OF THE CLASSES WHICH BELONG TO THAT CLASSLOADER ARE ALSO LOST. AS SUCH, IN THIS CLASS ANY ACCESS TO A RELOADABLE CLASS MUST BE ACCOMPLISHED THROUGH REFLECTION. THIS BREAKS THE RELOADABLE INFECTION.
 * 
 * @author jsmethie
 */
public class ServerContext
{
  private static ServerContext instance = null;

  public ServerContext(boolean skipStartup)
  {
    if (!skipStartup)
    {
      this.contextInitialized();
    }
  }
  
  private ServerContext()
  {
    this.contextInitialized();
  }

  public synchronized void contextDestroyed()
  {
//    doCleanup();
    
    this.shutdownGeoserver();
    
    Database.close();
  }

  /**
   * This code is invoked when patching (by the DatabaseViewCleanerPatcher.java)
   */
  @Request
  public void doCleanup()
  {
    // Clean up all database map views
    this.cleanupViews();

    // Clean up the generated map views
    this.deleteGeneratedMapViews();

//    runSql(this.getDropSql());
  }

  @Request
  private void contextInitialized()
  {
//    cleanupViews();

//    runSql(getDropSql());
    runSql(getIndexSql());
    runSql(getGeohierarchyAllpathsSQL());
    runSql(getFunctionSql());

    // Load all saved query views
    this.createViews();

    // Delete any views which might not have be deleted
//    this.deleteGeneratedMapViews();

    // Create the generated map views
    this.createGeneratedMapViews();

    // Startup geo server layers
    this.initializeGeoserver();
  }

  private void deleteGeoserverLayers()
  {
    Thread t = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        /*
         * Must use reflection in order to break the reloadable infectionious.
         */
        try
        {
//          Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.query.MapUtil");
//          clazz.getMethod("cleanupLayers").invoke(null);
        }
        catch (RuntimeException e)
        {
          throw e;
        }
        catch (Exception e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    });
    t.setDaemon(true);
    t.start();

  }

  private void createGeneratedMapViews()
  {
    /*
     * Must use reflection in order to break the reloadable infectionious.
     */
    try
    {
      Class<?> savedSearch = LoaderDecorator.load("dss.vector.solutions.query.CycleJob");
      savedSearch.getMethod("createGeneratedMapViews").invoke(null);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private void initializeGeoserver()
  {
    /*
     * Must use reflection in order to break the reloadable infectionious.
     */
    try
    {
      Class<?> savedSearch = LoaderDecorator.load("dss.vector.solutions.geoserver.GeoserverInitializer");
      savedSearch.getMethod("setup").invoke(null);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  private void shutdownGeoserver()
  {
    /*
     * Must use reflection in order to break the reloadable infectionious.
     */
    try
    {
      Class<?> savedSearch = LoaderDecorator.load("dss.vector.solutions.geoserver.GeoserverInitializer");
      savedSearch.getMethod("shutdown").invoke(null);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  private void deleteGeneratedMapViews()
  {
    /*
     * Must use reflection in order to break the reloadable infectionious.
     */
    try
    {
      Class<?> savedSearch = LoaderDecorator.load("dss.vector.solutions.query.CycleJob");
      savedSearch.getMethod("deleteGeneratedMapViews").invoke(null);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private String getDropSql()
  {
    /*
     * Copy of GeoHierarchy.ALLPATHS_VIEW in order to break Reloadable dependency.
     */
    String ALLPATHS_VIEW = "geohierarchy_allpaths";

    /*
     * Copy of MosquitoCollectionQB.GET_NEXT_TAXON_FUNCTION in order to break Reloadable dependency.
     */
    String GET_NEXT_TAXON_FUNCTION = "get_next_taxon";

    String sql = "";
    sql += "DROP VIEW IF EXISTS " + ALLPATHS_VIEW + "; \n";

    sql += "DROP FUNCTION IF EXISTS get_epiStart(int,int); \n";
    sql += "DROP FUNCTION IF EXISTS get_epiYear_from_date(date,int); \n";
    sql += "DROP FUNCTION IF EXISTS get_epiWeek_from_date(date,int); \n";
    sql += "DROP FUNCTION IF EXISTS " + GET_NEXT_TAXON_FUNCTION + "(varchar); \n";

    sql += "DROP FUNCTION IF EXISTS get_yearly_population_by_geoid_and_date(varchar,date); \n";
    sql += "DROP FUNCTION IF EXISTS get_seasonal_population_by_geoid_and_date(varchar,date); \n";
    sql += "DROP FUNCTION IF EXISTS get_adjusted_population(varchar,int,int); \n";
    sql += "DROP FUNCTION IF EXISTS get_threshold_by_geoid_and_epiweek(varchar,date,varchar); \n";

    return sql;
  }

  private String getIndexSql()
  {
    /*
     * Copy of GeoHierarchy.CLASS in order to break Reloadable dependency.
     */
    String GEO_HIERARCHY_CLASS = "dss.vector.solutions.geo.GeoHierarchy";

    MdEntityDAOIF geoHierarchyMd = MdEntityDAO.getMdEntityDAO(GEO_HIERARCHY_CLASS);
    String geoHierarchyTable = geoHierarchyMd.getTableName();

    MdEntityDAOIF mdTypeMd = MdEntityDAO.getMdEntityDAO(MdTypeInfo.CLASS);
    String mdTypeTable = mdTypeMd.getTableName();
    String pckNameCol = QueryUtil.getColumnName(MdType.getPackageNameMd());
    String nameCol = QueryUtil.getColumnName(MdType.getTypeNameMd());

    String sql = "";

    sql += "DROP INDEX IF EXISTS fqtn_md_type_hash; \n";
    sql += "DROP INDEX IF EXISTS fqtn_md_type_btree; \n";
    sql += "DROP INDEX IF EXISTS display_label_btree; \n";
    sql += "DROP INDEX IF EXISTS geoentityclass_btree; \n";
    sql += "DROP INDEX IF EXISTS geoentityclass_hash; \n";

    // sql += "CREATE  INDEX fqtn_md_type_hash \n";
    // sql += "  ON " + mdTypeTable + "  \n";
    // sql += "  USING hash \n";
    // sql += "  ((" + pckNameCol + " || '.' || " + nameCol + ")); \n";

    sql += "CREATE  INDEX fqtn_md_type_btree \n";
    sql += "  ON " + mdTypeTable + "  \n";
    sql += "  USING btree \n";
    sql += "  ((" + pckNameCol + " || '.' || " + nameCol + ")); \n";

    sql += "CREATE INDEX display_label_btree \n";
    sql += "  ON " + mdTypeTable + " \n";
    sql += "  (display_label); \n";

    sql += "CREATE INDEX geoentityclass_btree \n";
    sql += "  ON " + geoHierarchyTable + " \n";
    sql += "  USING btree \n";
    sql += "  (geo_entity_class); \n";

    // sql += "CREATE INDEX geoentityclass_hash \n";
    // sql += "  ON " + geoHierarchyTable + " \n";
    // sql += "  USING hash \n";
    // sql += "  (geo_entity_class); \n";

    return sql;

  }

  @SuppressWarnings("unchecked")
  private static List<String> newGetLocaleColumns() throws SQLException
  {
    LinkedList<String> list = new LinkedList<String>();
    List<MdDimensionDAOIF> allDimensions = MdDimensionDAO.getAllMdDimensions();
    List<SupportedLocale> allLocales = (List<SupportedLocale>) new SupportedLocaleQuery(new QueryFactory()).getIterator().getAll();
    SupportedLocale defaultLocale = new SupportedLocale();
    defaultLocale.setEnumName(MdAttributeLocalInfo.DEFAULT_LOCALE);
    allLocales.add(defaultLocale);
    for (SupportedLocale locale : allLocales)
    {
      String localeString = locale.getEnumName();
      list.add(MetadataDAO.convertCamelCaseToUnderscore(localeString));
      for (MdDimensionDAOIF dimension : allDimensions)
      {
        list.add(MetadataDAO.convertCamelCaseToUnderscore(dimension.getName() + localeString));
      }
    }
    return list;
  }

  private static List<String> getLocaleColumns() throws SQLException
  {
    MdEntityDAOIF mdDisLabel = MdEntityDAO.getMdEntityDAO(MetadataDisplayLabel.CLASS);
    String mdDisTable = mdDisLabel.getTableName();

    String sql = "";
    sql += " SELECT \n";
    sql += "a.attname as Column \n";
    sql += "FROM \n";
    sql += "pg_catalog.pg_attribute a \n";
    sql += "WHERE \n";
    sql += "a.attnum > 4 \n";
    sql += "AND NOT a.attisdropped \n";
    sql += "AND a.attrelid = ( \n";
    sql += "SELECT c.oid \n";
    sql += "FROM pg_catalog.pg_class c \n";
    sql += "LEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace \n";
    sql += "WHERE c.relname ~ '^(" + mdDisTable + ")$' \n";
    sql += "AND pg_catalog.pg_table_is_visible(c.oid) );\n";

    List<String> list = new LinkedList<String>();
    list.add(MetadataDAO.convertCamelCaseToUnderscore(MdAttributeLocalInfo.DEFAULT_LOCALE));

    Connection conn = Database.getConnection();
    Statement statement = null;
    try
    {
      statement = conn.createStatement();

      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next())
      {
        list.add(resultSet.getString(1));

      }

    }
    catch (SQLException e)
    {
      throw e;
    }
    finally
    {
      if (statement != null)
      {
        try
        {
          statement.close();
        }
        catch (SQLException e2)
        {
          // throw e2;
        }
      }
    }
    return list;

  }

  private String getGeohierarchyAllpathsSQL()
  {
    /*
     * Copy of GeoHierarchy.ALLPATHS_VIEW in order to break Reloadable dependency.
     */
    String ALLPATHS_VIEW = "geohierarchy_allpaths";

    String allPathsSQL = this.getAllPathsSQL();

    String sql = new String();
    sql += "CREATE OR REPLACE VIEW " + ALLPATHS_VIEW + " AS \n";
    sql += allPathsSQL;
    sql += "select root_type, root_class, child_type, child_class, child_political, child_spraytargetallowed, child_populationallowed, parent_political, parent_spraytargetallowed, parent_populationallowed , depth \n";
    sql += "from recursive_rollup  ; \n";

    return sql;
  }

  private static void runSql(String storedProcSource)
  {
    // MdssLog.debug(storedProcSource);

    Connection conn = Database.getConnection();
    Statement statement = null;
    try
    {
      statement = conn.createStatement();
      statement.execute(storedProcSource);
    }
    catch (SQLException e)
    {
      MdssLog.fatal(e);
      throw new DatabaseException(e);
    }
    finally
    {
      if (statement != null)
      {
        try
        {
          statement.close();
        }
        catch (SQLException e2)
        {
          MdssLog.fatal(e2);
          throw new DatabaseException(e2);
        }
      }
    }

  }

  private String getFunctionSql()
  {
    /*
     * Copy of AllPaths.CLASS in order to break Reloadable dependency.
     */
    String ALL_PATHS_CLASS = "dss.vector.solutions.geo.AllPaths";

    /*
     * Copy of PopulationData.CLASS in order to break Reloadable dependency.
     */
    String POPULATION_DATE_CLASS = "dss.vector.solutions.general.PopulationData";

    /*
     * Copy of LocatedIn.CLASS in order to break Reloadable dependency.
     */
    String LOCATED_IN_CLASS = "dss.vector.solutions.geo.LocatedIn";

    /*
     * Copy of MalariaSeason.CLASS in order to break Reloadable dependency.
     */
    String MALARIA_SEASON_CLASS = "dss.vector.solutions.general.MalariaSeason";

    /*
     * Copy of GeoHierarchy.CLASS in order to break Reloadable dependency.
     */
    String GEO_HIERARCHY_CLASS = "dss.vector.solutions.geo.GeoHierarchy";

    /*
     * Copy of GeoEntity.CLASS in order to break Reloadable dependency.
     */
    String GEO_ENTITY_CLASS = "dss.vector.solutions.geo.generated.GeoEntity";

    /*
     * Copy of Property.CLASS in order to break Reloadable dependency.
     */
    String PROPERTY_CLASS = "dss.vector.solutions.Property";

    /*
     * Copy of GeoTarget.CLASS in order to break Reloadable dependency.
     */
    String GEO_TARGET_CLASS = "dss.vector.solutions.irs.GeoTarget";

    /*
     * Copy of TermRelationship.CLASS in order to break Reloadable dependency.
     */
    String TERM_RELATIONSHIP_CLASS = "dss.vector.solutions.ontology.TermRelationship";

    MdEntityDAOIF allPathsMd = MdEntityDAO.getMdEntityDAO(ALL_PATHS_CLASS);
    String allPathsTable = allPathsMd.getTableName();
    String childGeoEntityCol = QueryUtil.getColumnName(this.getMdAttribute(ALL_PATHS_CLASS, "getChildGeoEntityMd"));
    String parentGeoEntityCol = QueryUtil.getColumnName(this.getMdAttribute(ALL_PATHS_CLASS, "getParentGeoEntityMd"));

    MdEntityDAOIF populationDataMd = MdEntityDAO.getMdEntityDAO(POPULATION_DATE_CLASS);
    String populationDataTable = populationDataMd.getTableName();

    MdEntityDAOIF locatedInMd = MdEntityDAO.getMdEntityDAO(LOCATED_IN_CLASS);
    String locatedInTable = locatedInMd.getTableName();

    MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MALARIA_SEASON_CLASS);
    String malariaSeasonTable = malariaSeasonMd.getTableName();
    String startDateCol = QueryUtil.getColumnName(this.getMdAttribute(MALARIA_SEASON_CLASS, "getStartDateMd"));
    String endDateCol = QueryUtil.getColumnName(this.getMdAttribute(MALARIA_SEASON_CLASS, "getEndDateMd"));
    String diseaseCol = QueryUtil.getColumnName(this.getMdAttribute(MALARIA_SEASON_CLASS, "getDiseaseMd"));

    String politicalCol = QueryUtil.getColumnName(this.getMdAttribute(GEO_HIERARCHY_CLASS, "getPoliticalMd"));
    String populationAllowedCol = QueryUtil.getColumnName(this.getMdAttribute(GEO_HIERARCHY_CLASS, "getPopulationAllowedMd"));

    String sql = "";

    sql += "CREATE OR REPLACE FUNCTION sum_stringified_id_int_pairs(anyarray) RETURNS Double Precision AS $$ \n";
    sql += "SELECT \n";
    sql += "sum (split_part($1[i]::varchar,'~',2)::Double Precision) FROM \n";
    sql += "generate_series(array_lower($1,1),  \n";
    sql += "array_upper($1,1)) g(i);  \n";
    sql += "$$ LANGUAGE sql IMMUTABLE; \n";

    sql += "CREATE OR REPLACE FUNCTION min_stringified_id_int_pairs(anyarray) RETURNS Double Precision AS $$ \n";
    sql += "SELECT \n";
    sql += "min (split_part($1[i]::varchar,'~',2)::Double Precision) FROM \n";
    sql += "generate_series(array_lower($1,1),  \n";
    sql += "array_upper($1,1)) g(i);  \n";
    sql += "$$ LANGUAGE sql IMMUTABLE; \n";

    sql += "CREATE OR REPLACE FUNCTION max_stringified_id_int_pairs(anyarray) RETURNS Double Precision AS $$ \n";
    sql += "SELECT \n";
    sql += "max (split_part($1[i]::varchar,'~',2)::Double Precision) FROM \n";
    sql += "generate_series(array_lower($1,1),  \n";
    sql += "array_upper($1,1)) g(i);  \n";
    sql += "$$ LANGUAGE sql IMMUTABLE; \n";

    sql += "CREATE OR REPLACE FUNCTION avg_stringified_id_int_pairs(anyarray) RETURNS Double Precision AS $$ \n";
    sql += "SELECT \n";
    sql += "avg (split_part($1[i]::varchar,'~',2)::Double Precision) FROM \n";
    sql += "generate_series(array_lower($1,1),  \n";
    sql += "array_upper($1,1)) g(i);  \n";
    sql += "$$ LANGUAGE sql IMMUTABLE; \n";

    MdEntity geoEntity = MdEntity.getMdEntity(GEO_ENTITY_CLASS);
    String geoEntityTable = geoEntity.getTableName();
    String typeCol = QueryUtil.getColumnName(this.getMdAttribute(GEO_ENTITY_CLASS, "getTypeMd"));

    // MdType is part of the runway jars and isn't reloaded
    MdEntity mdType = MdEntity.getMdEntity(MdType.CLASS);
    String mdTypeTable = mdType.getTableName();
    String pckCol = QueryUtil.getColumnName(MdType.getPackageNameMd());
    String typeNameCol = QueryUtil.getColumnName(MdType.getTypeNameMd());

    MdEntity geoHierarchy = MdEntity.getMdEntity(GEO_HIERARCHY_CLASS);
    String geoHierarchyTable = geoHierarchy.getTableName();
    String geoEntityClassCol = QueryUtil.getColumnName(this.getMdAttribute(GEO_HIERARCHY_CLASS, "getGeoEntityClassMd"));

    sql += "CREATE OR REPLACE FUNCTION get_adjusted_population \n";
    sql += "( \n";
    sql += "  _geo_Entity_Id         VARCHAR, \n";
    sql += "  _year             INT, \n";
    sql += "  _middle_Day INT \n";
    sql += ") \n";
    sql += "RETURNS DOUBLE PRECISION AS $$ \n";

    sql += "DECLARE \n";
    sql += "  _population                Double Precision; \n";
    sql += "  _sql VARCHAR; \n";
    sql += "   rec record; \n";
    sql += "  _prev_Year INT; \n";
    sql += "  _growth FLOAT; \n";
    sql += "  _child_Count FLOAT;  \n";
    sql += "  _percentage_Adjustment FLOAT; \n";
    sql += "BEGIN \n";
    sql += "  _percentage_Adjustment = (_middle_Day/183); \n";
    sql += "  SELECT population , year_of_data, growth_rate FROM " + populationDataTable + " pd \n";
    sql += "    INNER JOIN " + geoEntityTable + " g ON pd.geo_entity = g.id \n";
    sql += "    INNER JOIN " + mdTypeTable + " m ON g." + typeCol + " LIKE m." + pckCol + " || '.' || m." + typeNameCol + " \n";
    sql += "    INNER JOIN " + geoHierarchyTable + " h ON m.id = h." + geoEntityClassCol + " \n";
    sql += "    WHERE pd.year_of_data  <= _year AND pd.geo_entity = _geo_Entity_Id  \n";
    sql += "    AND h." + populationAllowedCol + " = 1 AND h." + politicalCol + " = 1 \n";
    sql += "    AND population IS NOT NULL \n";
    sql += "    ORDER BY pd.year_of_data DESC \n";
    sql += "    LIMIT 1 \n";
    sql += "    INTO _population, _prev_Year, _growth; \n";
    sql += "     \n";
    sql += "    IF _population IS NOT NULL THEN \n";
    sql += "      WHILE _prev_Year < _year LOOP \n";
    // If the growth rate is null then grab the last known rate before the year
    // of the population to extrapolate the population based on that prior rate.
    sql += "        IF _growth IS NULL THEN \n";
    sql += "          SELECT growth_rate FROM " + populationDataTable + " pd \n";
    sql += "          INNER JOIN " + geoEntityTable + " g ON pd.geo_entity = g.id \n";
    sql += "          INNER JOIN " + mdTypeTable + " m ON g." + typeCol + " LIKE m." + pckCol + " || '.' || m." + typeNameCol + " \n";
    sql += "          INNER JOIN " + geoHierarchyTable + " h ON m.id = h." + geoEntityClassCol + " \n";
    sql += "          WHERE pd.year_of_data <= _prev_Year AND pd.geo_entity = _geo_Entity_Id \n";
    sql += "          AND h." + populationAllowedCol + " = 1 AND h." + politicalCol + " = 1 \n";
    sql += "          AND growth_rate IS NOT NULL \n";
    sql += "          ORDER BY pd.year_of_data DESC \n";
    sql += "          LIMIT 1 \n";
    sql += "          INTO _growth; \n";
    sql += "        END IF;\n";
    sql += "        IF _growth IS NULL THEN \n";
    sql += "          _growth := 0.0; \n"; // No growth found
    sql += "        END IF;\n";
    sql += "        IF _prev_Year = _year - 1  THEN \n";
    sql += "           _population := _population + (_population * (_growth * _percentage_Adjustment )); \n";
    sql += "        ELSE \n";
    sql += "           _population := _population + (_population * _growth); \n";
    sql += "        END IF; \n";
    sql += "        _prev_Year := _prev_Year + 1; \n";
    sql += "        _growth := null; \n";
    sql += "       -- RAISE NOTICE '% % % %', _geo_Entity_Id,_year,_prev_Year,_population; \n";
    sql += "      END LOOP; \n";
    sql += "    ELSE \n";
    sql += "     _population := 0; \n";
    // sql += "      --check if this branch will lead to any data \n";
    // sql += "      SELECT count(ap.id) FROM " + allPathsTable + " ap \n";
    // sql += "     JOIN " + populationDataTable + " pd ON ap." +
    // childGeoEntityCol + " = pd." + geoEntityCol +
    // " JOIN geo_displayLabel gd ON gd.id = pd." + geoEntityCol + " \n";
    // sql += "     WHERE pd.population IS NOT NULL AND ap." +
    // parentGeoEntityCol + " =  _geo_Entity_Id AND pd." + yearOfDataCol +
    // " <= _year \n";
    // sql += "     AND gd." + populationAllowedCol +
    // " = 1 AND gd."+politicalCol+" = 1 \n";
    // sql += "      INTO _child_Count; \n";
    // sql += "      --continue to recurse if this branch has data \n";
    // sql += "       IF _child_Count > 0 THEN \n";
    // sql += "          \n";
    // sql += "         _sql := 'SELECT " + RelationshipDAOIF.CHILD_ID_COLUMN +
    // "  FROM " + locatedInTable + " WHERE " +
    // RelationshipDAOIF.PARENT_ID_COLUMN +
    // " = ' || quote_literal(_geo_Entity_Id); \n";
    // sql += "         FOR  rec IN EXECUTE _sql LOOP \n";
    // sql +=
    // "           _population = _population + get_adjusted_population(rec." +
    // RelationshipDAOIF.CHILD_ID_COLUMN + ", _year,_middle_Day); \n";
    // sql += "         END LOOP; \n";
    // sql += "       END IF; \n";
    sql += "    END IF; \n";
    sql += "    RETURN _population; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";

    String geoIdCol = QueryUtil.getColumnName(this.getMdAttribute(GEO_ENTITY_CLASS, "getGeoIdMd"));

    sql += "CREATE OR REPLACE FUNCTION get_yearly_population_by_geoid_and_date(_geo_id character varying, _date date)\n" + 
        "  RETURNS double precision AS\n" + 
        "$BODY$ \n" + 
        "DECLARE \n" + 
        "  _population      FLOAT; \n" + 
        "  _geo_Entity_Id  VARCHAR; \n" + 
        "  _year             INT; \n" + 
        "BEGIN\n" + 
        "  IF NOT EXISTS (\n" + 
        "      SELECT 1 \n" + 
        "      FROM   pg_class c \n" + 
        "      WHERE  c.relname = 'pop_cached' \n" + 
        "    ) THEN\n" + 
        "    \n" + 
        "    EXECUTE 'CREATE TEMP TABLE IF NOT EXISTS pop_cached (\n" + 
        "    _cache_geo_id varchar(64),\n" + 
        "    _cache_year integer,\n" + 
        "    _cache_pop float\n" + 
        "    ) ON COMMIT DROP'; \n" + 
        "    EXECUTE 'CREATE INDEX cached_pop_index ON pop_cached (_cache_geo_id, _cache_year, _cache_pop)'; \n" + 
        "  END IF;\n" + 
        "  \n" + 
        "  _year := EXTRACT(year FROM _date); \n" + 
        "  \n" + 
        "  SELECT _cache_pop FROM pop_cached WHERE _geo_id = _cache_geo_id AND _year = _cache_year INTO _population; \n" + 
        "  IF _population IS NOT NULL THEN \n" + 
        "    RETURN _population; \n" + 
        "  END IF;\n" + 
        "  \n" + 
        "  SELECT id FROM geo_entity WHERE geo_id = _geo_Id INTO _geo_Entity_Id; \n" + 
        "  SELECT get_adjusted_population(_geo_Entity_Id, _year,183) INTO _population; \n" + 
        "  \n" + 
        "  INSERT INTO pop_cached (_cache_geo_id, _cache_year, _cache_pop) VALUES (_geo_id, _year, _population); \n" + 
        "  RETURN _population; \n" + 
        "END; \n" + 
        "$BODY$\n" + 
        "  LANGUAGE plpgsql; \n";
    
    sql += "CREATE OR REPLACE FUNCTION get_seasonal_population_by_geoid_and_date \n";
    sql += "( \n";
    sql += "  _geo_Id         VARCHAR, \n";
    sql += "  _date      DATE \n";
    sql += ") \n";
    sql += "RETURNS FLOAT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _population                FLOAT; \n";
    sql += "  _geo_Entity_Id  VARCHAR; \n";
    sql += "  _year             INT; \n";
    sql += "  _season_Start DATE; \n";
    sql += "  _season_Middle DATE; \n";
    sql += "  _season_End DATE; \n";
    sql += "  _middle_Day INT; \n";
    sql += "BEGIN \n";
    sql += "  SELECT id FROM " + geoEntityTable + " WHERE " + geoIdCol + " = _geo_Id \n";
    sql += "    INTO _geo_Entity_Id; \n";
    sql += "  SELECT " + startDateCol + "," + endDateCol + " FROM " + malariaSeasonTable + " AS ms  WHERE ms." + startDateCol + " <= _date AND ms." + endDateCol + " >= _date \n";
    sql += "    INTO _season_Start, _season_End; \n";
    sql += "   \n";
    sql += "  _season_Middle := _season_Start + ((_season_End - _season_Start)/2); \n";
    sql += "  _middle_Day := EXTRACT(doy FROM _season_Middle); \n";
    sql += "  _year := EXTRACT(year FROM _season_Middle); \n";
    sql += "  \n";
    sql += "  SELECT get_adjusted_population(_geo_Entity_Id, _year, _middle_Day) \n";
    sql += "    INTO _population; \n";
    sql += "     \n";
    sql += "    RETURN _population; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";

    MdEntityDAOIF propertyMd = MdEntityDAO.getMdEntityDAO(PROPERTY_CLASS);
    String propertyTable = propertyMd.getTableName();
    String propertyValueCol = QueryUtil.getColumnName(this.getMdAttribute(PROPERTY_CLASS, "getPropertyValueMd"));
    String keyNameCol = QueryUtil.getColumnName(this.getMdAttribute(PROPERTY_CLASS, "getKeyNameMd"));

    sql += "CREATE OR REPLACE FUNCTION get_seasonal_spray_target_by_geoEntityId_and_date \n";
    sql += "( \n";
    sql += "  _geo_Entity_Id         VARCHAR, \n";
    sql += "  _date      DATE, \n";
    sql += "  _disease VARCHAR \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _epi_Week      INT; \n";
    sql += "  _first_Day_Of_Epi_Week INT; \n";
    sql += "  _season_Id    VARCHAR; \n";
    sql += "  _target_Column  VARCHAR; \n";
    sql += "BEGIN \n";
    sql += "  SELECT id FROM " + malariaSeasonTable + " AS ms WHERE _date BETWEEN ms." + startDateCol + " AND ms." + endDateCol + " \n AND ms." + diseaseCol + " = _disease";
    sql += "    INTO _season_Id; \n";
    sql += "     \n";
    sql += "   IF _season_Id IS NULL THEN \n";
    sql += "     RETURN NULL; \n";
    sql += "   END IF; \n";
    sql += "   SELECT " + propertyValueCol + " FROM " + propertyTable + " WHERE " + keyNameCol + " = '" + PropertyInfo.EPI_START_DAY + "' \n";
    sql += "   INTO _first_Day_Of_Epi_Week; \n";
    sql += "    \n";
    sql += "  _epi_Week := get_epiWeek_from_date(_date,_first_Day_Of_Epi_Week); \n";
    sql += "  _target_Column := ('target_' || _epi_Week);  \n";
    sql += "     \n";
    sql += "   RETURN get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(_geo_Entity_Id,_season_Id,_target_Column); \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";

    MdEntityDAOIF geoTargetMd = MdEntityDAO.getMdEntityDAO(GEO_TARGET_CLASS);
    String geoTargetTable = geoTargetMd.getTableName();
    String seasonCol = QueryUtil.getColumnName(this.getMdAttribute(GEO_TARGET_CLASS, "getSeasonMd"));
    String gtDiseaseCol = QueryUtil.getColumnName(this.getMdAttribute(GEO_TARGET_CLASS, "getDiseaseMd"));
    String geoEntityTargetCol = QueryUtil.getColumnName(this.getMdAttribute(GEO_TARGET_CLASS, "getGeoEntityMd"));
    // String idCol = QueryUtil.getIdColumn();

    sql += "CREATE OR REPLACE FUNCTION get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar \n";
    sql += "( \n";
    sql += "  _geo_Entity_Id         VARCHAR, \n";
    sql += "  _season_Id    VARCHAR, \n";
    sql += "  _target_Column  VARCHAR \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _target       INT; \n";
    sql += "  _child_Count  INT; \n";
    sql += "  _sql TEXT; \n";
    sql += "  rec RECORD; \n";
    sql += "BEGIN \n";
    sql += "  EXECUTE 'SELECT '|| _target_Column ||' FROM " + geoTargetTable + "  WHERE " + seasonCol + " = $1 AND " + geoEntityTable + " = $2 ' \n";
    sql += "    INTO _target \n";
    sql += "    USING _season_Id, _geo_Entity_Id; \n";
    sql += "     \n";
    sql += "    IF _target IS NULL THEN \n";
    sql += "      --check if this branch will lead to any data \n";
    sql += "      EXECUTE 'SELECT count(gt.id) FROM " + allPathsTable + " ap \n";
    sql += "     JOIN " + geoTargetTable + " gt ON ap." + childGeoEntityCol + " = gt." + geoEntityTargetCol + "   \n";
    sql += "      WHERE gt." + seasonCol + " = $1 AND ap." + parentGeoEntityCol + " = $2 ' \n";
    sql += "      INTO _child_Count \n";
    sql += "      USING _season_Id, _geo_Entity_Id; \n";
    sql += "      --continue to recurse if this branch has data \n";
    sql += "      IF _child_Count > 0 THEN \n";
    sql += "             _target := 0; \n";
    sql += "         _sql := 'SELECT " + RelationshipDAOIF.CHILD_ID_COLUMN + "  FROM " + locatedInTable + " WHERE " + RelationshipDAOIF.PARENT_ID_COLUMN + " = ' || quote_literal(_geo_Entity_Id); \n";
    sql += "         FOR  rec IN EXECUTE _sql LOOP \n";
    sql += "           _target := _target + coalesce(get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(rec." + RelationshipDAOIF.CHILD_ID_COLUMN + ",_season_Id,_target_Column),0); \n";
    sql += "         END LOOP; \n";
    sql += "       END IF; \n";
    sql += "    END IF; \n";
    sql += "     \n";
    sql += "    RETURN _target; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";

    /*
     * Copy of QueryConstants.SUM_AREA_TARGETS in order to break Reloadable dependency.
     */
    String aptCache = "apt_cached";
    sql += "CREATE OR REPLACE FUNCTION " + QueryConstants.SUM_AREA_TARGETS + " \n";
    sql += "( \n";
    sql += "  _geo_target_id VARCHAR, \n";
    sql += "  _target_column VARCHAR, \n";
    sql += "  _disease VARCHAR, \n";
    sql += "  _season VARCHAR \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n ";
    sql += " _target  INT;\n";
    sql += " _child_Count FLOAT;\n";
    sql += " _sql VARCHAR;\n";
    sql += " rec record;\n";
    sql += " _week INT;\n";
    sql += "BEGIN \n";
    sql += "IF NOT EXISTS ( \n";
    sql += "    SELECT 1 \n";
    sql += "    FROM   pg_class c \n";
    sql += "    WHERE  c.relname = '" + aptCache + "' \n";
    sql += "    ) THEN \n";
    sql += "  EXECUTE 'CREATE TEMP TABLE IF NOT EXISTS " + aptCache + " (  \n";
    sql += "  id varchar(64),  \n";
    sql += "  target integer,  \n";
    sql += "  week integer,  \n";
    sql += "  season varchar(64),  \n";
    sql += "  disease varchar(64)  \n";
    sql += "  ) ON COMMIT DROP'; \n";
    sql += "  EXECUTE 'CREATE INDEX cached_apt_index ON " + aptCache + " (id, week, season, disease)'; \n";
    sql += "END IF; \n";
    sql += "  _week = _target_column::integer; \n";
    sql += "  SELECT target FROM " + aptCache + " WHERE id = _geo_target_id AND week = _week AND season = _season AND disease = _disease INTO _target; \n";
    sql += "  IF _target IS NOT NULL THEN \n";
    sql += "    RETURN _target; \n";
    sql += "  END IF; \n";
    sql += "  EXECUTE 'SELECT target_'|| _target_Column ||' FROM " + geoTargetTable + " WHERE " + geoEntityTargetCol + " = '|| quote_literal(_geo_target_id) \n";
    sql += "    || ' AND " + seasonCol + " = ' || quote_literal(_season) || ' AND " + gtDiseaseCol + " = ' || quote_literal(_disease) \n";
    sql += "  INTO _target;\n";
    sql += "  IF _target IS NULL THEN\n";
    // sql += "    --check if this branch will lead to any data \n";
    sql += "    _target := 0;\n";
    sql += "    _sql := 'SELECT " + RelationshipDAOIF.CHILD_ID_COLUMN + "  FROM " + locatedInTable + " WHERE " + RelationshipDAOIF.PARENT_ID_COLUMN + " = ' || quote_literal(_geo_target_id); \n";
    sql += "    FOR  rec IN EXECUTE _sql LOOP \n";
    sql += "      _target = _target + " + QueryConstants.SUM_AREA_TARGETS + "(rec." + RelationshipDAOIF.CHILD_ID_COLUMN + ", _target_column, _disease, _season); \n";
    sql += "    END LOOP;\n";
    sql += "  END IF;\n";
    sql += "  INSERT INTO " + aptCache + " (id, target, week, season, disease) VALUES (_geo_target_id, _target, _week, _season, _disease); \n";
    sql += "  RETURN _target; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql VOLATILE; \n";

    sql += "CREATE OR REPLACE FUNCTION get_epiWeek_from_date \n";
    sql += "( \n";
    sql += "  _date      DATE, \n";
    sql += "  -- 0 = SUNDAY \n";
    sql += "  _first_Day_Of_Epi_Week INT, \n";
    sql += "  OUT _epi_Week INT \n";
    sql += "   \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _year INT; \n";
    sql += "  _fourth_Of_Jan_Week_Day INT; \n";
    sql += "  _start_Date date; \n";
    sql += "  _next_Start_Date date; \n";
    sql += "  _prev_Start_Date date; \n";
    sql += "BEGIN  \n";
    sql += "  _year := EXTRACT(YEAR FROM _date); \n";
    sql += "   \n";
    sql += "  _prev_Start_Date = get_epiStart(_year-1,_first_Day_Of_Epi_Week); \n";
    sql += "  _start_Date := get_epiStart(_year,_first_Day_Of_Epi_Week); \n";
    sql += "  _next_Start_Date := get_epiStart(_year+1,_first_Day_Of_Epi_Week); \n";
    sql += "  --RAISE NOTICE '% % % %', _year,_prev_Start_Date,_start_Date,_next_Start_Date; \n";
    sql += "  CASE \n";
    sql += "   WHEN _date IS NULL THEN \n";
    sql += "      _epi_Week := NULL; \n";
    sql += "   WHEN (_date >= _start_Date ) AND (_date < _next_Start_Date)  THEN \n";
    sql += "      _epi_Week := EXTRACT('epoch' FROM _date)::INT  - EXTRACT('epoch' FROM _start_Date)::INT; \n";
    sql += "      _epi_Week := round((_epi_Week::FLOAT /(60.0*60.0*24.0))::INT  / 7)+1; \n";
    sql += "      --RAISE NOTICE '% % % % % %',_start_Date, _date, _epi_Week, _epi_Week /(60*60), _epi_Week /(60*60*24), _epi_Week /(60*60*24*7); \n";
    sql += "       \n";
    sql += "       \n";
    sql += "   WHEN _date >= _next_Start_Date THEN \n";
    sql += "      _epi_Week := 1; \n";
    sql += "   WHEN _date < _start_Date THEN \n";
    sql += "      _epi_Week := EXTRACT('epoch' FROM _date)::INT  - EXTRACT('epoch' FROM _prev_Start_Date)::INT; \n";
    sql += "      _epi_Week := (round(_epi_Week::FLOAT /(60.0*60.0*24.0))::INT  / 7 )+1; \n";
    sql += "   ELSE \n";
    sql += "      _epi_Week := NULL; \n";
    sql += "  END CASE; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql STABLE; \n";

    sql += "CREATE OR REPLACE FUNCTION get_epiYear_from_date \n";
    sql += "( \n";
    sql += "  _date      DATE, \n";
    sql += "  -- 0 = SUNDAY \n";
    sql += "  _first_Day_Of_Epi_Week INT, \n";
    sql += "  OUT _epi_Year INT \n";
    sql += "   \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _year INT; \n";
    sql += "  _fourth_Of_Jan_Week_Day INT; \n";
    sql += "  _start_Date date; \n";
    sql += "  _next_Start_Date date; \n";
    sql += "  _prev_Start_Date date; \n";
    sql += "BEGIN  \n";
    sql += "  _year := EXTRACT(YEAR FROM _date); \n";
    sql += "   \n";
    sql += "  _prev_Start_Date = get_epiStart(_year-1,_first_Day_Of_Epi_Week); \n";
    sql += "  _start_Date := get_epiStart(_year,_first_Day_Of_Epi_Week); \n";
    sql += "  _next_Start_Date := get_epiStart(_year+1,_first_Day_Of_Epi_Week); \n";
    sql += "  --RAISE NOTICE '% % % %', _year,_prev_Start_Date,_start_Date,_next_Start_Date; \n";
    sql += "  CASE \n";
    sql += "   WHEN _date is NULL THEN \n";
    sql += "      _epi_Year := NULL; \n";
    sql += "   WHEN (_date >= _start_Date ) AND (_date < _next_Start_Date)  THEN \n";
    sql += "      _epi_Year := _year; \n";
    sql += "   WHEN _date >= _next_Start_Date THEN \n";
    sql += "      _epi_Year := _year + 1; \n";
    sql += "   WHEN _date < _start_Date THEN \n";
    sql += "      _epi_Year := _year - 1;  \n";
    sql += "   ELSE \n";
    sql += "      _epi_Year := NULL; \n";
    sql += "  END CASE; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql STABLE; \n";

    sql += "CREATE OR REPLACE FUNCTION get_epiStart \n";
    sql += "( \n";
    sql += "  _year INT, \n";
    sql += "  _first_Day_Of_Epi_Week INT, \n";
    sql += "  OUT _start_Date DATE \n";
    sql += ") \n";
    sql += "RETURNS DATE AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _yearT TEXT; \n";
    sql += "  _fourth_Of_Jan_Week_Day INT; \n";
    sql += "  _jan_Fourth DATE; \n";
    sql += "BEGIN  \n";
    sql += "  _jan_Fourth := to_date(_year::TEXT || '-4', 'YYYY-DDD'); \n";
    sql += "  _fourth_Of_Jan_Week_Day := EXTRACT(DOW FROM _jan_Fourth); \n";
    sql += "  IF (_fourth_Of_Jan_Week_Day >= _first_Day_Of_Epi_Week )THEN \n";
    sql += "   _start_Date  := _jan_Fourth - ((_fourth_Of_Jan_Week_Day - _first_Day_Of_Epi_Week)::text || ' days')::interval; \n";
    sql += "   --RAISE NOTICE 'A: % % %', _year,_fourth_Of_Jan_Week_Day,_start_Date; \n";
    sql += "  ELSE \n";
    sql += "   _start_Date  := _jan_Fourth - ((7 + _fourth_Of_Jan_Week_Day - _first_Day_Of_Epi_Week)::text || ' days')::interval; \n";
    sql += "   --RAISE NOTICE 'B: % % %', _year,_fourth_Of_Jan_Week_Day,_start_Date; \n";
    sql += "  END IF; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql STABLE; \n";

    String termRel = MdEntity.getMdEntity(TERM_RELATIONSHIP_CLASS).getTableName();

    /*
     * Copy of MosquitoCollectionQB.GET_NEXT_TAXON_FUNCTION in order to break Reloadable dependency.
     */
    String GET_NEXT_TAXON_FUNCTION = "get_next_taxon";

    sql += "CREATE OR REPLACE FUNCTION " + GET_NEXT_TAXON_FUNCTION + "(taxon varchar) \n";
    sql += "RETURNS TABLE(parent varchar, depth integer) AS \n";
    sql += "$BODY$ \n";
    sql += "WITH RECURSIVE parents (child, parent, depth) AS ( \n";
    sql += " SELECT " + RelationshipDAOIF.CHILD_ID_COLUMN + ", " + RelationshipDAOIF.PARENT_ID_COLUMN + ", 0 FROM " + termRel + " WHERE " + RelationshipDAOIF.CHILD_ID_COLUMN + " = $1 \n";
    sql += " UNION \n";
    sql += " SELECT tr." + RelationshipDAOIF.CHILD_ID_COLUMN + ", tr." + RelationshipDAOIF.PARENT_ID_COLUMN + ", p.depth+1 FROM parents p INNER JOIN " + termRel + " tr ON tr." + RelationshipDAOIF.CHILD_ID_COLUMN + " = p.parent \n";
    sql += ")\n";
    sql += " SELECT parent::varchar, depth::int FROM parents; \n";
    sql += "$BODY$ \n";
    sql += "LANGUAGE sql VOLATILE; \n";

    /*
     * THRESHOLD CALCULATOR
     * 
     * SQL String built with:
     * http://stackoverflow.com/questions/2159678/paste-a-multi-line-java-string-in-eclipse
     * 
     * Eclipse has an option so that copy-paste of multi-line text into String literals will result in quoted newlines:
     * Preferences/Java/Editor/Typing/ "Escape text when pasting into a string literal"
     */
    sql += "-- _thresholdType can be either 'notification' or 'identification' and indicates the type of threshold to calculate.\n" + 
        "CREATE OR REPLACE FUNCTION ddms.get_threshold_by_geoid_and_epiweek(_thresholdType character varying, _universalId character varying, _geo_target_id character varying, _epi_week integer, _disease character varying, _season character varying)\n" + 
        "  RETURNS float AS\n" + 
        "$BODY$ \n" + 
        "DECLARE \n" + 
        "  _target  float;\n" + 
        " _child_Count FLOAT;\n" + 
        " _sql VARCHAR;\n" + 
        " rec record;\n" + 
        " _week INT;\n" + 
        "BEGIN \n" + 
        "IF NOT EXISTS ( \n" + 
        "    SELECT 1 \n" + 
        "    FROM   pg_class c \n" + 
        "    WHERE  c.relname = 'apt_cached' \n" + 
        "    ) THEN \n" + 
        "  EXECUTE 'CREATE TEMP TABLE IF NOT EXISTS apt_cached (  \n" + 
        "  id varchar(64),\n" + 
        "  thresholdType varchar(64),\n" + 
        "  target integer,\n" + 
        "  week integer,\n" + 
        "  season varchar(64),  \n" + 
        "  disease varchar(64)\n" + 
        "  ) ON COMMIT DROP'; \n" + 
        "  EXECUTE 'CREATE INDEX cached_apt_index ON apt_cached (id, week, season, disease)'; \n" + 
        "END IF; \n" + 
        "  _week = _epi_week::integer; \n" + 
        "  \n" + 
        "  SELECT target FROM apt_cached WHERE id = _geo_target_id AND week = _week AND season = _season AND disease = _disease AND thresholdType = _thresholdType INTO _target; \n" + 
        "    IF _target IS NOT NULL THEN \n" + 
        "      RETURN _target; \n" + 
        "    END IF; \n" + 
        "\n" + 
        "  EXECUTE E'WITH dateExtrapolationView AS (\n" + 
        "    SELECT \n" + 
        "     year_of_week AS year_of_week, \n" + 
        "     period AS period, \n" + 
        "     (get_epistart(year_of_week, 0) + (to_char((period)*7, \\'999\\')||\\' days\\')::interval)::date AS planned_date \n" + 
        "    FROM epi_week \n" + 
        "  ),\n" + 
        "\n" + 
        "  geoThresholdView AS (\n" + 
        "    SELECT \n" + 
        "      wt.id id,\n" + 
        "      wt.' || _thresholdType || ' threshold,\n" + 
        "      td.geo_entity geo_entity,\n" + 
        "      ew.period epi_week,\n" + 
        "      ew.year_of_week epi_year,\n" + 
        "      de.planned_date AS threshold_date,\n" + 
        "      td.season season,\n" + 
        "      ms.disease disease\n" + 
        "    FROM\n" + 
        "      weekly_threshold wt\n" + 
        "      INNER JOIN threshold_data td ON wt.parent_id=td.id\n" + 
        "      INNER JOIN epi_week ew ON wt.child_id=ew.id\n" + 
        "      INNER JOIN malaria_season ms ON td.season=ms.id\n" + 
        "      CROSS JOIN dateExtrapolationView de\n" + 
        "      INNER JOIN allpaths_geo apg ON apg.child_geo_entity = td.geo_entity\n" + 
        "    WHERE \n" + 
        "      wt.' || _thresholdType || ' IS NOT NULL\n" + 
        "      AND ew.period = de.period\n" + 
        "      AND de.planned_date BETWEEN ms.start_date AND ms.end_date\n" + 
        "      AND apg.parent_universal = ' || quote_literal(_universalId) || '\n" + 
        "    GROUP BY wt.id, td.geo_entity, de.planned_date, wt.' || _thresholdType || ', td.season, ms.disease, ew.period, ew.year_of_week\n" + 
        "  )\n" + 
        "\n" + 
        "  SELECT threshold FROM geoThresholdView WHERE geo_entity = ' || quote_literal(_geo_target_id) || '\n" + 
        "    AND epi_week = ' || _week || '\n" + 
        "    AND season = ' || quote_literal(_season) || ' AND disease = ' || quote_literal(_disease) || ';' INTO _target;\n" + 
        "  \n" + 
        "  IF _target IS NULL THEN\n" + 
        "    _target := 0;\n" + 
        "    _sql := 'SELECT child_id  FROM located_in WHERE parent_id = ' || quote_literal(_geo_target_id); \n" + 
        "    FOR  rec IN EXECUTE _sql LOOP \n" + 
        "      _target = _target + get_threshold_by_geoid_and_epiweek(_thresholdType, _universalId, rec.child_id, _week, _disease, _season); \n" + 
        "    END LOOP;\n" + 
        "  END IF;\n" + 
        "  INSERT INTO apt_cached (id, thresholdType, target, week, season, disease) VALUES (_geo_target_id, _thresholdType, _target, _week, _season, _disease); \n" + 
        "  RETURN _target; \n" + 
        "END; \n" + 
        "$BODY$\n" + 
        "  LANGUAGE plpgsql VOLATILE;\n";
    
    /*
     * SUMMING AREA PLANNED TARGETS WITH GEO INCLUDES
     */
    sql += "CREATE OR REPLACE FUNCTION ddms.get_area_target_by_id_tar_geos(_geo_target_id character varying, _target_column character varying, _disease character varying, _season character varying, _geoincludes character[])\n" + 
        "  RETURNS integer AS\n" + 
        "  \n" + 
        "-- geoIncludes is an array specifying which geo entities to include in the rollup.\n" + 
        "-- These geoentities must be in the descendants heirarchy and only data contained\n" + 
        "-- within them will be rolled into the target.\n" + 
        "\n" + 
        "$BODY$ \n" + 
        "DECLARE \n" + 
        "  _target  INT;\n" + 
        " _child_Count FLOAT;\n" + 
        " _sql VARCHAR;\n" + 
        " rec record;\n" + 
        " _week INT;\n" + 
        " _matchesGeoIncludes BOOLEAN;\n" + 
        " _shouldAdd BOOLEAN;\n" + 
        "BEGIN \n" + 
        "\n" + 
        "  -- Create us a temp table if we don't have one\n" + 
        "  IF NOT EXISTS ( \n" + 
        "      SELECT 1 \n" + 
        "      FROM   pg_class c \n" + 
        "      WHERE  c.relname = 'apt_cached' \n" + 
        "      ) THEN \n" + 
        "    EXECUTE 'CREATE TEMP TABLE IF NOT EXISTS apt_cached (  \n" + 
        "    id varchar(64),  \n" + 
        "    target integer,  \n" + 
        "    week integer,  \n" + 
        "    season varchar(64),  \n" + 
        "    disease varchar(64)  \n" + 
        "    ) ON COMMIT DROP'; \n" + 
        "    EXECUTE 'CREATE INDEX cached_apt_index ON apt_cached (id, week, season, disease)'; \n" + 
        "  END IF; \n" + 
        "\n" + 
        "  _week = _target_column::integer; \n" + 
        "  \n" + 
        "  -- Is the provided GeoEntity a match with our geoIncludes?\n" + 
        "  IF EXISTS (SELECT (1) AS geoExistsConstant FROM allpaths_geo ap WHERE ap.child_geo_entity = _geo_target_id AND _geoIncludes @> ARRAY[ap.parent_geo_entity]) THEN\n" + 
        "        -- Get the value from the cache if it exists\n" + 
        "        SELECT target FROM apt_cached WHERE id = _geo_target_id AND week = _week AND season = _season AND disease = _disease INTO _target; \n" + 
        "        IF _target IS NOT NULL THEN \n" + 
        "            RETURN _target; \n" + 
        "        END IF; \n" + 
        "    \n" + 
        "        -- Get the value directly from the geo_target table, if it exists\n" + 
        "        EXECUTE 'SELECT target_'|| _target_Column ||' FROM geo_target WHERE geo_entity = '|| quote_literal(_geo_target_id) \n" + 
        "            || ' AND season = ' || quote_literal(_season) || ' AND disease = ' || quote_literal(_disease) \n" + 
        "        INTO _target;\n" + 
        "  END IF;\n" + 
        "  \n" + 
        "  -- Calculate the value as the sum of its children\n" + 
        "  IF _target IS NULL THEN\n" + 
        "    _target := 0;\n" + 
        "    \n" + 
        "    -- loop over all the children\n" + 
        "    _sql := 'SELECT child_id  FROM located_in WHERE parent_id = ' || quote_literal(_geo_target_id); \n" + 
        "    FOR  rec IN EXECUTE _sql LOOP\n" + 
        "      _shouldAdd = false;\n" + 
        "      \n" + 
        "      -- We need to decide whether or not this child should be summed into the APT or not, based on the geo includes\n" + 
        "      -- 1) child is a geoInclude : add it to the APT\n" + 
        "      IF _geoIncludes @> ARRAY[rec.child_id] THEN\n" + 
        "        _shouldAdd = true;\n" + 
        "      -- 2) child is a child of a geoInclude : add it to the APT\n" + 
        "      ELSIF (EXISTS (SELECT (1) AS geoExistsConstant FROM allpaths_geo ap WHERE ap.child_geo_entity = rec.child_id AND _geoIncludes @> ARRAY[ap.parent_geo_entity])) THEN\n" + 
        "        _shouldAdd = true;\n" + 
        "      -- 3) geoInclude is a child of our child (rec). We don't want to add the APT of child to the APT, but we may be adding some of its children to the APT. Loop over its children.\n" + 
        "      ELSIF (EXISTS (SELECT (1) AS geoExistsConstant FROM allpaths_geo ap WHERE ap.parent_geo_entity = rec.child_id AND _geoIncludes @> ARRAY[ap.child_geo_entity])) THEN\n" + 
        "        _shouldAdd = true;\n" + 
        "      END IF;\n" + 
        "      \n" + 
        "      IF _shouldAdd THEN\n" + 
        "        _target = _target + get_area_target_by_id_tar_geos(rec.child_id, _target_column, _disease, _season, _geoIncludes);\n" + 
        "      END IF;\n" + 
        "    END LOOP;\n" + 
        "  END IF;\n" + 
        "  INSERT INTO apt_cached (id, target, week, season, disease) VALUES (_geo_target_id, _target, _week, _season, _disease); \n" + 
        "  RETURN _target; \n" + 
        "END; \n" + 
        "$BODY$\n" + 
        "  LANGUAGE plpgsql VOLATILE";

    return sql;
  }

  public void cleanupViews()
  {
    /*
     * Must use reflection in order to break the reloadable infectionious.
     */
    try
    {
      Class<?> savedSearch = LoaderDecorator.load("dss.vector.solutions.query.SavedSearch");
      savedSearch.getMethod("cleanupDatabaseViews").invoke(null);

      Class<?> savedMap = LoaderDecorator.load("dss.vector.solutions.query.SavedMap");
      Method method = savedMap.getMethod("cleanOldViews", Long.TYPE);
      method.invoke(null, System.currentTimeMillis());
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public void createViews()
  {
    /*
     * Must use reflection in order to break the reloadable infectionious.
     */
    try
    {
      Class<?> savedSearch = LoaderDecorator.load("dss.vector.solutions.query.SavedSearch");
      savedSearch.getMethod("createDatabaseViews").invoke(null);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public String getAllPathsSQL()
  {
    return (String) this.invokeGetter("dss.vector.solutions.geo.GeoHierarchy", "getAllPathsSQL");
  }

  private MdAttributeDAOIF getMdAttribute(String className, String methodName)
  {
    return (MdAttributeDAOIF) this.invokeGetter(className, methodName);
  }

  public Object invokeGetter(String className, String methodName)
  {
    try
    {
      Class<?> clazz = LoaderDecorator.load(className);
      return clazz.getMethod(methodName).invoke(null);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }

  }

  public static ServerContext instance()
  {
    if (instance == null)
    {
      synchronized (ServerContext.class)
      {
        if (instance == null)
        {
          instance = new ServerContext();
        }
      }
    }

    return instance;
  }
}
