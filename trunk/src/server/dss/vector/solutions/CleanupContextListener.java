package dss.vector.solutions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdTypeInfo;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdEntity;
import com.runwaysdk.system.metadata.MdType;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;
import com.runwaysdk.system.metadata.SupportedLocale;
import com.runwaysdk.system.metadata.SupportedLocaleQuery;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.GeoTarget;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.query.SavedMap;
import dss.vector.solutions.util.QueryUtil;

public class CleanupContextListener implements ServletContextListener, Reloadable
{

  public void contextDestroyed(ServletContextEvent arg0)
  {
    doCleanup();
  }

  @Request
  private void doCleanup()
  {
    // Clean up all database map views
    SavedMap.cleanOldViews(System.currentTimeMillis());
    runSql(getDropSql());
  }

  @Request
  public void contextInitialized(ServletContextEvent arg0)
  {
    SavedMap.cleanOldViews(System.currentTimeMillis());
    runSql(getDropSql());
    runSql(getIndexSql());
    runSql(getGeohierarchyAllpathsSQL());
    runSql(getFunctionSql());
  }

  private String getDropSql()
  {
    String sql = "";
    sql += "DROP VIEW IF EXISTS "+GeoHierarchy.ALLPATHS_VIEW+"; \n";

    sql += "DROP FUNCTION IF EXISTS get_epiStart(int,int); \n";
    sql += "DROP FUNCTION IF EXISTS get_epiYear_from_date(date,int); \n";
    sql += "DROP FUNCTION IF EXISTS get_epiWeek_from_date(date,int); \n";

    sql += "DROP FUNCTION IF EXISTS get_yearly_population_by_geoid_and_date(varchar,date); \n";
    sql += "DROP FUNCTION IF EXISTS get_seasonal_population_by_geoid_and_date(varchar,date); \n";
    sql += "DROP FUNCTION IF EXISTS get_adjusted_population(varchar,int,int); \n";

    return sql;
  }

  private String getIndexSql()
  {
    MdEntityDAOIF geoHierarchyMd = MdEntityDAO.getMdEntityDAO(GeoHierarchy.CLASS);
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

//    sql += "CREATE  INDEX fqtn_md_type_hash \n";
//    sql += "  ON " + mdTypeTable + "  \n";
//    sql += "  USING hash \n";
//    sql += "  ((" + pckNameCol + " || '.' || " + nameCol + ")); \n";

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

//    sql += "CREATE INDEX geoentityclass_hash \n";
//    sql += "  ON " + geoHierarchyTable + " \n";
//    sql += "  USING hash \n";
//    sql += "  (geo_entity_class); \n";

    return sql;

  }

  @SuppressWarnings("unchecked")
  private static List<String> newGetLocaleColumns() throws SQLException
  {
    LinkedList<String> list = new LinkedList<String>();
    List<MdDimensionDAOIF> allDimensions = MdDimensionDAO.getAllMdDimensions();
    List<SupportedLocale> allLocales = (List<SupportedLocale>)new SupportedLocaleQuery(new QueryFactory()).getIterator().getAll();
    SupportedLocale defaultLocale = new SupportedLocale();
    defaultLocale.setEnumName(MdAttributeLocalInfo.DEFAULT_LOCALE);
    allLocales.add(defaultLocale);
    for (SupportedLocale locale : allLocales)
    {
      String localeString = locale.getEnumName();
      list.add(MetadataDAO.convertCamelCaseToUnderscore(localeString));
      for (MdDimensionDAOIF dimension : allDimensions)
      {
        list.add(MetadataDAO.convertCamelCaseToUnderscore(dimension.getName()+localeString));
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
    String allPathsSQL = GeoHierarchy.getAllPathsSQL();
    String sql = "";

    sql += "CREATE OR REPLACE VIEW "+GeoHierarchy.ALLPATHS_VIEW+" AS \n";
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
    MdEntityDAOIF allPathsMd = MdEntityDAO.getMdEntityDAO(AllPaths.CLASS);
    String allPathsTable = allPathsMd.getTableName();
    String childGeoEntityCol = QueryUtil.getColumnName(AllPaths.getChildGeoEntityMd());
    String parentGeoEntityCol = QueryUtil.getColumnName(AllPaths.getParentGeoEntityMd());

    MdEntityDAOIF populationDataMd = MdEntityDAO.getMdEntityDAO(PopulationData.CLASS);
    String populationDataTable = populationDataMd.getTableName();

    MdEntityDAOIF locatedInMd = MdEntityDAO.getMdEntityDAO(LocatedIn.CLASS);
    String locatedInTable = locatedInMd.getTableName();

    MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
    String malariaSeasonTable = malariaSeasonMd.getTableName();
    String startDateCol = QueryUtil.getColumnName(MalariaSeason.getStartDateMd());
    String endDateCol = QueryUtil.getColumnName(MalariaSeason.getEndDateMd());
    String diseaseCol = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());

    String politicalCol = QueryUtil.getColumnName(GeoHierarchy.getPoliticalMd());
    String populationAllowedCol = QueryUtil.getColumnName(GeoHierarchy.getPopulationAllowedMd());

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

    MdEntity geoEntity = MdEntity.getMdEntity(GeoEntity.CLASS);
    String geoEntityTable = geoEntity.getTableName();
    String typeCol = QueryUtil.getColumnName(GeoEntity.getTypeMd());

    MdEntity mdType = MdEntity.getMdEntity(MdType.CLASS);
    String mdTypeTable = mdType.getTableName();
    String pckCol = QueryUtil.getColumnName(MdType.getPackageNameMd());
    String typeNameCol = QueryUtil.getColumnName(MdType.getTypeNameMd());

    MdEntity geoHierarchy = MdEntity.getMdEntity(GeoHierarchy.CLASS);
    String geoHierarchyTable = geoHierarchy.getTableName();
    String geoEntityClassCol = QueryUtil.getColumnName(GeoHierarchy.getGeoEntityClassMd());
    
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
    sql += "  SELECT population , year_of_data, growth_rate FROM "+populationDataTable+" pd \n";
    sql += "    INNER JOIN "+geoEntityTable+" g ON pd.geo_entity = g.id \n";
    sql += "    INNER JOIN "+mdTypeTable+" m ON g."+typeCol+" LIKE m."+pckCol+" || '.' || m."+typeNameCol+" \n";
    sql += "    INNER JOIN "+geoHierarchyTable+" h ON m.id = h."+geoEntityClassCol+" \n";
    sql += "    WHERE pd.year_of_data  <= _year AND pd.geo_entity = _geo_Entity_Id  \n";
    sql += "    AND h."+populationAllowedCol+" = 1 AND h."+politicalCol+" = 1 \n";
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
    sql += "          SELECT growth_rate FROM "+populationDataTable+" pd \n";
    sql += "          INNER JOIN "+geoEntityTable+" g ON pd.geo_entity = g.id \n";
    sql += "          INNER JOIN "+mdTypeTable+" m ON g."+typeCol+" LIKE m."+pckCol+" || '.' || m."+typeNameCol+" \n";
    sql += "          INNER JOIN "+geoHierarchyTable+" h ON m.id = h."+geoEntityClassCol+" \n";
    sql += "          WHERE pd.year_of_data <= _prev_Year AND pd.geo_entity = _geo_Entity_Id \n";
    sql += "          AND h."+populationAllowedCol+" = 1 AND h."+politicalCol+" = 1 \n";
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
//    sql += "      --check if this branch will lead to any data \n";
//    sql += "      SELECT count(ap.id) FROM " + allPathsTable + " ap \n";
//    sql += "     JOIN " + populationDataTable + " pd ON ap." + childGeoEntityCol + " = pd." + geoEntityCol + " JOIN geo_displayLabel gd ON gd.id = pd." + geoEntityCol + " \n";
//    sql += "     WHERE pd.population IS NOT NULL AND ap." + parentGeoEntityCol + " =  _geo_Entity_Id AND pd." + yearOfDataCol + " <= _year \n";
//    sql += "     AND gd." + populationAllowedCol + " = 1 AND gd."+politicalCol+" = 1 \n";
//    sql += "      INTO _child_Count; \n";
//    sql += "      --continue to recurse if this branch has data \n";
//    sql += "       IF _child_Count > 0 THEN \n";
//    sql += "          \n";
//    sql += "         _sql := 'SELECT " + RelationshipDAOIF.CHILD_ID_COLUMN + "  FROM " + locatedInTable + " WHERE " + RelationshipDAOIF.PARENT_ID_COLUMN + " = ' || quote_literal(_geo_Entity_Id); \n";
//    sql += "         FOR  rec IN EXECUTE _sql LOOP \n";
//    sql += "           _population = _population + get_adjusted_population(rec." + RelationshipDAOIF.CHILD_ID_COLUMN + ", _year,_middle_Day); \n";
//    sql += "         END LOOP; \n";
//    sql += "       END IF; \n";
    sql += "    END IF; \n";
    sql += "    RETURN _population; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";

    String geoIdCol = QueryUtil.getColumnName(GeoEntity.getGeoIdMd());

    sql += "CREATE OR REPLACE FUNCTION get_yearly_population_by_geoid_and_date \n";
    sql += "( \n";
    sql += "  _geo_Id         VARCHAR, \n";
    sql += "  _date             DATE \n";
    sql += ") \n";
    sql += "RETURNS FLOAT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _population      FLOAT; \n";
    sql += "  _geo_Entity_Id  VARCHAR; \n";
    sql += "  _year             INT; \n";
    sql += "BEGIN \n";
    sql += "  SELECT id FROM " + geoEntityTable + " WHERE " + geoIdCol + " = _geo_Id \n";
    sql += "    INTO _geo_Entity_Id; \n";
    sql += "   _year := EXTRACT(year FROM _date); \n";
    sql += "  SELECT get_adjusted_population(_geo_Entity_Id, _year,183) \n";
    sql += "    INTO _population; \n";
    sql += "     \n";
    sql += "    RETURN _population; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
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

    MdEntityDAOIF propertyMd = MdEntityDAO.getMdEntityDAO(Property.CLASS);
    String propertyTable = propertyMd.getTableName();
    String propertyValueCol = QueryUtil.getColumnName(Property.getPropertyValueMd());
    String keyNameCol = QueryUtil.getColumnName(Property.getKeyNameMd());

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
    sql += "  SELECT id FROM " + malariaSeasonTable + " AS ms WHERE _date BETWEEN ms." + startDateCol + " AND ms." + endDateCol + " \n AND ms."+diseaseCol+" = _disease";
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

    MdEntityDAOIF geoTargetMd = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS);
    String geoTargetTable = geoTargetMd.getTableName();
    String seasonCol = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
    String gtDiseaseCol = QueryUtil.getColumnName(GeoTarget.getDiseaseMd());
    String geoEntityTargetCol = QueryUtil.getColumnName(GeoTarget.getGeoEntityMd());
//    String idCol = QueryUtil.getIdColumn();

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

    sql += "CREATE OR REPLACE FUNCTION "+QueryConstants.SUM_AREA_TARGETS+" \n";
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
    sql += "BEGIN \n";
    sql += "  EXECUTE 'SELECT target_'|| _target_Column ||' FROM "+geoTargetTable+" WHERE "+geoEntityTargetCol+" = '|| quote_literal(_geo_target_id) \n";
    sql += "    || ' AND "+seasonCol+" = ' || quote_literal(_season) || ' AND "+gtDiseaseCol+" = ' || quote_literal(_disease) \n";
    sql += "  INTO _target;\n";
    sql += "  IF _target IS NULL THEN\n";
//    sql += "    --check if this branch will lead to any data \n";
    sql += "    _target := 0;\n";
    sql += "    _sql := 'SELECT "+RelationshipDAOIF.CHILD_ID_COLUMN+"  FROM "+locatedInTable+" WHERE "+RelationshipDAOIF.PARENT_ID_COLUMN+" = ' || quote_literal(_geo_target_id); \n";
    sql += "    FOR  rec IN EXECUTE _sql LOOP \n";
    sql += "      _target = _target + "+QueryConstants.SUM_AREA_TARGETS+"(rec."+RelationshipDAOIF.CHILD_ID_COLUMN+", _target_column, _disease, _season); \n";
    sql += "    END LOOP;\n";
    sql += "  END IF;\n";
    sql += "  RETURN _target; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
    
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
    sql += "$$ LANGUAGE plpgsql; \n";

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
    sql += "  RAISE NOTICE '% % % %', _year,_prev_Start_Date,_start_Date,_next_Start_Date; \n";
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
    sql += "$$ LANGUAGE plpgsql; \n";

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
    sql += "$$ LANGUAGE plpgsql; \n";
    return sql;
  }

}
