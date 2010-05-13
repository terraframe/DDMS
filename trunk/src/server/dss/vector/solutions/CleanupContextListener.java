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
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.StartSession;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.query.SavedMap;

public class CleanupContextListener implements ServletContextListener, Reloadable
{

  public void contextDestroyed(ServletContextEvent arg0)
  {
    doCleanup();
  }
  
  @StartSession
  private void doCleanup()
  {
    // Clean up all database map views
    SavedMap.cleanOldViews(System.currentTimeMillis());
    runSql(getDropSql());
  }
  
  @StartSession
  public void contextInitialized(ServletContextEvent arg0)
  {
      runSql(getDropSql());
      runSql(getIndexSql());
      runSql(getGeoDisplayViewSQL());
      runSql(getGeohierarchyAllpathsSQL());
      runSql(getFunctionSql());
  }

  private String getDropSql()
  {
    String sql = "";
    sql += "DROP VIEW IF EXISTS geo_displayLabel; \n";
    sql += "DROP VIEW IF EXISTS geohierarchy_allpaths; \n";
    
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
    String sql = "";
    
    sql += "DROP INDEX IF EXISTS fqtn_md_type_hash; \n";
    sql += "DROP INDEX IF EXISTS fqtn_md_type_btree; \n";
    sql += "DROP INDEX IF EXISTS display_label_btree; \n";
    sql += "DROP INDEX IF EXISTS geoentityclass_btree; \n";
    sql += "DROP INDEX IF EXISTS geoentityclass_hash; \n";
    
    sql += "CREATE  INDEX fqtn_md_type_hash \n";
    sql += "  ON md_type  \n";
    sql += "  USING hash \n";
    sql += "  ((package_name || '.' || type_name)); \n";
    
    sql += "CREATE  INDEX fqtn_md_type_btree \n";
    sql += "  ON md_type  \n";
    sql += "  USING btree \n";
    sql += "  ((package_name || '.' || type_name)); \n";
    
    sql += "CREATE INDEX display_label_btree \n";
    sql += "  ON md_type \n";
    sql += "  (display_label); \n";
    
    sql += "CREATE INDEX geoentityclass_btree \n";
    sql += "  ON geo_hierarchy \n";
    sql += "  USING btree \n";
    sql += "  (geo_entity_class); \n";
    
    sql += "CREATE INDEX geoentityclass_hash \n";
    sql += "  ON geo_hierarchy \n";
    sql += "  USING hash \n";
    sql += "  (geo_entity_class); \n";
    
    return sql;
    
  }
  
  
  private List<String> getLocaleColumns() throws SQLException
  { 
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
    sql += "WHERE c.relname ~ '^(metadata_display_label)$' \n";
    sql += "AND pg_catalog.pg_table_is_visible(c.oid) );\n";
    
    List<String> list = new LinkedList<String>();
    
    
    Connection conn = Database.getConnection();
    Statement statement = null;
    try
    {
      statement = conn.createStatement();
      
      ResultSet resultSet = statement.executeQuery(sql);
      
      while ( resultSet.next())
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
          //throw e2;
        }
      }
    }
    return list;
    
  }
  
  
  private String getGeohierarchyAllpathsSQL()
  {
    
    String sql = "";
    
    sql += "CREATE OR REPLACE VIEW geohierarchy_allpaths AS \n";
    sql += "WITH RECURSIVE geohierarchy_flags AS( \n";
    sql += "SELECT  (t1.package_name || '.' || t1.type_name) AS parent_type, \n";
    sql += "  g1.geo_entity_class as parent_class, \n";
    sql += "  g1.political AS parent_political, \n";
    sql += "  g1.spray_target_allowed AS parent_spraytargetallowed,  \n";
    sql += "  g1.population_allowed AS parent_populationallowed, \n";
    sql += "  (t2.package_name || '.' || t2.type_name) AS child_type, \n";
    sql += "  g2.geo_entity_class As child_class, \n";
    sql += "  g2.political AS child_political, \n";
    sql += "  g2.spray_target_allowed AS child_spraytargetallowed,  \n";
    sql += "  g2.population_allowed AS child_populationallowed \n";
    sql += "FROM allowed_in , \n";
    sql += "  geo_hierarchy g1,  \n";
    sql += "  geo_hierarchy g2, \n";
    sql += "  md_type t1 , \n";
    sql += "  md_type t2  \n";
    sql += "WHERE  allowed_in.parent_id = g1.id \n";
    sql += "AND allowed_in.child_id = g2.id \n";
    sql += "AND t1.id = g1.geo_entity_class  \n";
    sql += "AND t2.id = g2.geo_entity_class \n";
    sql += ") \n";
    sql += ", recursive_rollup AS ( \n";
    sql += " SELECT child_type, parent_type, parent_type as root_type, child_political, child_spraytargetallowed, child_populationallowed, parent_political, parent_spraytargetallowed, parent_populationallowed, parent_class as root_class ,0 as depth,  child_class \n";
    sql += "   \n";
    sql += "  FROM geohierarchy_flags \n";
    sql += " UNION \n";
    sql += " SELECT b.child_type, b.parent_type, a.root_type, b.child_political, b.child_spraytargetallowed, b.child_populationallowed, a.parent_political, a.parent_spraytargetallowed, a.parent_populationallowed, a.root_class , a.depth+1 , b.child_class \n";
    sql += " FROM recursive_rollup a,  geohierarchy_flags b \n";
    sql += " WHERE a.child_type = b.parent_type \n";
    sql += ") \n";
    sql += "select root_type, root_class, child_type, child_class, child_political, child_spraytargetallowed, child_populationallowed, parent_political, parent_spraytargetallowed, parent_populationallowed , depth \n";
    sql += "from recursive_rollup  ; \n";

    
    
    return sql;
  }
  
  private String getGeoDisplayViewSQL()
  {
    List<String> list = new LinkedList<String>();
    
    String key = MetadataDisplayLabel.CLASS+"."+MetadataDisplayLabel.DEFAULTLOCALE;
    String defaultLocaleColumn = MdAttributeConcrete.getByKey(key).getColumnName();
    
    list.add(defaultLocaleColumn);
    try
    {
      list.addAll(getLocaleColumns());
    }
    catch (SQLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    String sql = "";
    
    sql += "CREATE OR REPLACE VIEW geo_displayLabel AS \n";
    sql += "SELECT   \n";
    sql += "geo0.id,  \n";
    sql += "geo0.geo_ID, \n";
    sql += "(t1.package_name || '.' || t1.type_name) AS parent_type, \n";
    sql += "g1.political AS political, \n";
    sql += "g1.spray_target_allowed AS spray_target_allowed,  \n";
    sql += "g1.population_allowed AS population_allowed,\n \n";
    for (String locale : list)
    {
      //sql += "dl1.defaultlocale AS type_displayLabel_" + locale +", \n";
      sql += "geo0.entity_Name|| ' (' || dl1." + locale + " ||  COALESCE(' : ' || ter.name,'')   || ') - ' || geo0.geo_Id AS " + locale +", \n";
    }
    sql += "geo0.entity_Name|| COALESCE(' : ' || ter.name,'')   AS short_Display_Label \n"; 
    sql += "FROM  \n";
    sql += "geo_hierarchy g1,  \n";
    sql += "md_type t1 , \n";
    sql += "metadata_display_label dl1, \n";
    sql += "geo_entity geo0 \n";
    sql += "LEFT JOIN term AS ter ON ter.id = geo0.term \n";
    sql += "WHERE   \n";
    sql += "t1.id = g1.geo_entity_class  \n";
    sql += "AND t1.display_label = dl1.id \n";
    sql += "AND geo0.type =  (t1.package_name || '.' || t1.type_name); \n";
    
    
    
    return sql;
    
  }
  

  private  void runSql(String storedProcSource) 
  {
    //System.out.println(storedProcSource);


    Connection conn = Database.getConnection();
    Statement statement = null;
    try
    {
      statement = conn.createStatement();
      statement.execute(storedProcSource);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
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
          e2.printStackTrace();
        }
      }
    }

  }
  
  
  private String getFunctionSql()
  {
    String sql = "";
    

    
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
    sql += "  SELECT population , year_of_data, growth_rate FROM population_data pd JOIN geo_displayLabel gd ON gd.id = pd.geo_entity \n";
    sql += "    WHERE pd.year_of_data  <= _year AND pd.geo_entity = _geo_Entity_Id  \n";
    sql += "    AND gd.population_allowed = 1 AND gd.political = 1 \n";
    sql += "    ORDER BY pd.year_of_data DESC \n";
    sql += "    LIMIT 1 \n";
    sql += "    INTO _population, _prev_Year, _growth; \n";
    sql += "     \n";
    sql += "    IF _population IS NOT NULL THEN \n";
    sql += "      WHILE _prev_Year < _year LOOP \n";
    sql += "        IF _prev_Year = _year - 1  THEN \n";
    sql += "           _population := _population + (_population * (_growth * _percentage_Adjustment )); \n";
    sql += "        ELSE \n";
    sql += "           _population := _population + (_population * _growth); \n";
    sql += "        END IF; \n";
    sql += "        _prev_Year := _prev_Year + 1; \n";
    sql += "       -- RAISE NOTICE '% % % %', _geo_Entity_Id,_year,_prev_Year,_population; \n";
    sql += "      END LOOP; \n";
    sql += "    ELSE \n";
    sql += "     _population := 0; \n";
    sql += "      --check if this branch will lead to any data \n";
    sql += "      SELECT count(ap.id) FROM allpaths_geo ap \n";
    sql += "     JOIN population_data pd ON ap.child_geo_entity = pd.geo_entity JOIN geo_display_Label gd ON gd.id = pd.geo_entity \n";
    sql += "     WHERE pd.population IS NOT NULL AND ap.parent_geo_entity =  _geo_Entity_Id AND pd.year_of_data <= _year \n";
    sql += "     AND gd.population_allowed = 1 AND gd.political = 1 \n";
    sql += "      INTO _child_Count; \n";
    sql += "      --continue to recurse if this branch has data \n";
    sql += "       IF _child_Count > 0 THEN \n";
    sql += "          \n";
    sql += "         _sql := 'SELECT child_id  FROM located_in WHERE parent_id = ' || quote_literal(_geo_Entity_Id); \n";
    sql += "         FOR  rec IN EXECUTE _sql LOOP \n";
    sql += "           _population = _population + get_adjusted_population(rec.child_id, _year,_middle_Day); \n";
    sql += "         END LOOP; \n";
    sql += "       END IF; \n";
    sql += "    END IF; \n";
    sql += "    RETURN _population; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";

    
    
    
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
    sql += "  SELECT id FROM geo_entity WHERE geo_id = _geoId \n";
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
    sql += "  SELECT id FROM geo_entity WHERE geoid = _geo_Id \n";
    sql += "    INTO _geo_Entity_Id; \n";
    sql += "  SELECT start_date,end_date FROM malaria_season AS ms  WHERE ms.start_Date <= _date AND ms.end_Date >= _date \n";
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
    
    
    
    
    
    
    sql += "CREATE OR REPLACE FUNCTION get_seasonal_spray_target_by_geoEntityId_and_date \n";
    sql += "( \n";
    sql += "  _geo_Entity_Id         VARCHAR, \n";
    sql += "  _date      DATE \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _epi_Week      INT; \n";
    sql += "  _first_Day_Of_Epi_Week INT; \n";
    sql += "  _season_Id    VARCHAR; \n";
    sql += "  _target_Column  VARCHAR; \n";
    sql += "BEGIN \n";
    sql += "  SELECT id FROM malaria_season AS ms WHERE _date BETWEEN ms.start_Date AND ms.end_Date \n";
    sql += "    INTO _season_Id; \n";
    sql += "     \n";
    sql += "   IF _season_Id IS NULL THEN \n";
    sql += "     RETURN NULL; \n";
    sql += "   END IF; \n";
    sql += "   SELECT property_Value FROM property WHERE key_name = 'epiStartWeekDay' \n";
    sql += "   INTO _first_Day_Of_Epi_Week; \n";
    sql += "    \n";
    sql += "  _epi_Week := get_epiWeek_from_date(_date,_first_Day_Of_Epi_Week); \n";
    sql += "  _target_Column := ('target_' || _epi_Week);  \n";
    sql += "     \n";
    sql += "   RETURN get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(_geo_Entity_Id,_season_Id,_target_Column); \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
    
    
    
    
    
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
    sql += "  EXECUTE 'SELECT '|| _target_Column ||' FROM geo_target  WHERE season = $1 AND geo_entity = $2 ' \n";
    sql += "    INTO _target \n";
    sql += "    USING _season_Id, _geo_Entity_Id; \n";
    sql += "     \n";
    sql += "    IF _target IS NULL THEN \n";
    sql += "      --check if this branch will lead to any data \n";
    sql += "      EXECUTE 'SELECT count(gt.id) FROM allpaths_geo ap \n";
    sql += "     JOIN geo_target gt ON ap.child_geo_entity = gt.geo_entity   \n";
    sql += "      WHERE gt.season = $1 AND ap.parent_geo_entity = $2 ' \n";
    sql += "      INTO _child_Count \n";
    sql += "      USING _season_Id, _geo_Entity_Id; \n";
    sql += "      --continue to recurse if this branch has data \n";
    sql += "      IF _child_Count > 0 THEN \n";
    sql += "             _target := 0; \n";
    sql += "         _sql := 'SELECT child_id  FROM located_in WHERE parent_id = ' || quote_literal(_geo_Entity_Id); \n";
    sql += "         FOR  rec IN EXECUTE _sql LOOP \n";
    sql += "           _target := _target + coalesce(get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(rec.child_id,_season_Id,_target_Column),0); \n";
    sql += "         END LOOP; \n";
    sql += "       END IF; \n";
    sql += "    END IF; \n";
    sql += "     \n";
    sql += "    RETURN _target; \n";
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
    sql += "   WHEN (_date >= _start_Date ) AND (_date < _next_Start_Date)  THEN \n";
    sql += "      _epi_Year := _year; \n";
    sql += "   WHEN _date >= _next_Start_Date THEN \n";
    sql += "      _epi_Year := _year + 1; \n";
    sql += "   WHEN _date < _start_Date THEN \n";
    sql += "      _epi_Year := _year - 1;  \n";
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
    sql += "   _start_Date  := _jan_Fourth - ((7 + _fourth_Of_Jan_Week_Day - _firs_tDay_Of_Epi_Week)::text || ' days')::interval; \n";
    sql += "   --RAISE NOTICE 'B: % % %', _year,_fourth_Of_Jan_Week_Day,_start_Date; \n";
    sql += "  END IF; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
    
    return sql;
  }
  
  
}
