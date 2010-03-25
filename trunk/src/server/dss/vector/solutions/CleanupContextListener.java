package dss.vector.solutions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.StartSession;

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
    sql += "  ((packagename || '.' || typename)); \n";
    
    sql += "CREATE  INDEX fqtn_md_type_btree \n";
    sql += "  ON md_type  \n";
    sql += "  USING btree \n";
    sql += "  ((packagename || '.' || typename)); \n";
    
    sql += "CREATE INDEX display_label_btree \n";
    sql += "  ON md_type \n";
    sql += "  (displaylabel); \n";
    
    sql += "CREATE INDEX geoentityclass_btree \n";
    sql += "  ON geohierarchy \n";
    sql += "  USING btree \n";
    sql += "  (geoentityclass); \n";
    
    sql += "CREATE INDEX geoentityclass_hash \n";
    sql += "  ON geohierarchy \n";
    sql += "  USING hash \n";
    sql += "  (geoentityclass); \n";
    
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
    sql += "WHERE c.relname ~ '^(metadatadisplaylabel)$' \n";
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
    sql += "SELECT  (t1.packagename || '.' || t1.typename) AS parent_type, \n";
    sql += "  g1.geoentityclass as parent_class, \n";
    sql += "  g1.political AS parent_political, \n";
    sql += "  g1.spraytargetallowed AS parent_spraytargetallowed,  \n";
    sql += "  g1.populationallowed AS parent_populationallowed, \n";
    sql += "  (t2.packagename || '.' || t2.typename) AS child_type, \n";
    sql += "  g2.geoentityclass As child_class, \n";
    sql += "  g2.political AS child_political, \n";
    sql += "  g2.spraytargetallowed AS child_spraytargetallowed,  \n";
    sql += "  g2.populationallowed AS child_populationallowed \n";
    sql += "FROM allowedin , \n";
    sql += "  geohierarchy g1,  \n";
    sql += "  geohierarchy g2, \n";
    sql += "  md_type t1 , \n";
    sql += "  md_type t2  \n";
    sql += "WHERE  allowedin.parent_id = g1.id \n";
    sql += "AND allowedin.child_id = g2.id \n";
    sql += "AND t1.id = g1.geoentityclass  \n";
    sql += "AND t2.id = g2.geoentityclass \n";
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
    list.add(com.runwaysdk.constants.MdAttributeLocalInfo.DEFAULT_LOCALE );
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
    sql += "geo0.geoID, \n";
    sql += "(t1.packagename || '.' || t1.typename) AS parent_type, \n";
    sql += "g1.political AS political, \n";
    sql += "g1.spraytargetallowed AS spraytargetallowed,  \n";
    sql += "g1.populationallowed AS populationallowed,\n \n";
    for (String locale : list)
    {
      //sql += "dl1.defaultlocale AS type_displayLabel_" + locale +", \n";
      sql += "geo0.entityName|| ' (' || dl1." + locale + " ||  COALESCE(' : ' || ter.display,'')   || ') - ' || geo0.geoId AS " + locale +", \n";
    }
    sql += "geo0.entityName|| COALESCE(' : ' || ter.display,'')   AS shortDisplayLabel \n"; 
    sql += "FROM  \n";
    sql += "geohierarchy g1,  \n";
    sql += "md_type t1 , \n";
    sql += "metadatadisplaylabel dl1, \n";
    sql += "geoentity geo0 \n";
    sql += "LEFT JOIN term AS ter ON ter.id = geo0.term \n";
    sql += "WHERE   \n";
    sql += "t1.id = g1.geoentityclass  \n";
    sql += "AND t1.displaylabel = dl1.id \n";
    sql += "AND geo0.type =  (t1.packagename || '.' || t1.typename); \n";
    
    
    
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
    sql += "  _geoEntityId         VARCHAR, \n";
    sql += "  _year             INT, \n";
    sql += "  _middleDay INT \n";
    sql += ") \n";
    sql += "RETURNS DOUBLE PRECISION AS $$ \n";
    
    sql += "DECLARE \n";
    sql += "  _population                Double Precision; \n";
    sql += "  _sql VARCHAR; \n";
    sql += "   rec record; \n";
    sql += "  _prevYear INT; \n";
    sql += "  _growth FLOAT; \n";
    sql += "  _childCount FLOAT;  \n";
    sql += "  _percentageAdjustment FLOAT; \n";
    sql += "BEGIN \n";
    sql += "  _percentageAdjustment = (_middleDay/183); \n";
    sql += "  SELECT population , yearofdata, growthrate FROM populationdata pd JOIN geo_displayLabel gd ON gd.id = pd.geoentity \n";
    sql += "    WHERE pd.yearofdata  <= _year AND pd.geoentity = _geoEntityId  \n";
    sql += "    AND gd.populationallowed = 1 AND gd.political = 1 \n";
    sql += "    ORDER BY pd.yearofdata DESC \n";
    sql += "    LIMIT 1 \n";
    sql += "    INTO _population, _prevYear, _growth; \n";
    sql += "     \n";
    sql += "    IF _population IS NOT NULL THEN \n";
    sql += "      WHILE _prevYear < _year LOOP \n";
    sql += "        IF _prevYear = _year - 1  THEN \n";
    sql += "           _population := _population + (_population * (_growth * _percentageAdjustment )); \n";
    sql += "        ELSE \n";
    sql += "           _population := _population + (_population * _growth); \n";
    sql += "        END IF; \n";
    sql += "        _prevYear := _prevYear + 1; \n";
    sql += "       -- RAISE NOTICE '% % % %', _geoEntityId,_year,_prevYear,_population; \n";
    sql += "      END LOOP; \n";
    sql += "    ELSE \n";
    sql += "     _population := 0; \n";
    sql += "      --check if this branch will lead to any data \n";
    sql += "      SELECT count(ap.id) FROM allpaths_geo ap \n";
    sql += "     JOIN populationdata pd ON ap.childgeoentity = pd.geoentity JOIN geo_displayLabel gd ON gd.id = pd.geoentity \n";
    sql += "     WHERE pd.population IS NOT NULL AND ap.parentgeoentity =  _geoEntityId AND pd.yearofdata <= _year \n";
    sql += "     AND gd.populationallowed = 1 AND gd.political = 1 \n";
    sql += "      INTO _childCount; \n";
    sql += "      --continue to recurse if this branch has data \n";
    sql += "       IF _childCount > 0 THEN \n";
    sql += "          \n";
    sql += "         _sql := 'SELECT child_id  FROM locatedin WHERE parent_id = ' || quote_literal(_geoEntityId); \n";
    sql += "         FOR  rec IN EXECUTE _sql LOOP \n";
    sql += "           _population = _population + get_adjusted_population(rec.child_id, _year,_middleDay); \n";
    sql += "         END LOOP; \n";
    sql += "       END IF; \n";
    sql += "    END IF; \n";
    sql += "    RETURN _population; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";

    
    
    
    sql += "CREATE OR REPLACE FUNCTION get_yearly_population_by_geoid_and_date \n";
    sql += "( \n";
    sql += "  _geoId         VARCHAR, \n";
    sql += "  _date             DATE \n";
    sql += ") \n";
    sql += "RETURNS FLOAT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _population      FLOAT; \n";
    sql += "  _geoEntityId  VARCHAR; \n";
    sql += "  _year             INT; \n";
    sql += "BEGIN \n";
    sql += "  SELECT id FROM geoentity WHERE geoid = _geoId \n";
    sql += "    INTO _geoEntityId; \n";
    sql += "   _year := EXTRACT(year FROM _date); \n";
    sql += "  SELECT get_adjusted_population(_geoEntityId, _year,183) \n";
    sql += "    INTO _population; \n";
    sql += "     \n";
    sql += "    RETURN _population; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";

    
    
    
    sql += "CREATE OR REPLACE FUNCTION get_seasonal_population_by_geoid_and_date \n";
    sql += "( \n";
    sql += "  _geoId         VARCHAR, \n";
    sql += "  _date      DATE \n";
    sql += ") \n";
    sql += "RETURNS FLOAT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _population                FLOAT; \n";
    sql += "  _geoEntityId  VARCHAR; \n";
    sql += "  _year             INT; \n";
    sql += "  _seasonStart DATE; \n";
    sql += "  _seasonMiddle DATE; \n";
    sql += "  _seasonEnd DATE; \n";
    sql += "  _middleDay INT; \n";
    sql += "BEGIN \n";
    sql += "  SELECT id FROM geoentity WHERE geoid = _geoId \n";
    sql += "    INTO _geoEntityId; \n";
    sql += "  SELECT startdate,enddate FROM malariaseason AS ms  WHERE ms.startDate <= _date AND ms.endDate >= _date \n";
    sql += "    INTO _seasonStart, _seasonEnd; \n";
    sql += "   \n";
    sql += "  _seasonMiddle := _seasonStart + ((_seasonEnd - _seasonStart)/2); \n";
    sql += "  _middleDay := EXTRACT(doy FROM _seasonMiddle); \n";
    sql += "  _year := EXTRACT(year FROM _seasonMiddle); \n";
    sql += "  \n";
    sql += "  SELECT get_adjusted_population(_geoEntityId, _year, _middleDay) \n";
    sql += "    INTO _population; \n";
    sql += "     \n";
    sql += "    RETURN _population; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
    
    
    
    
    
    
    sql += "CREATE OR REPLACE FUNCTION get_seasonal_spray_target_by_geoEntityId_and_date \n";
    sql += "( \n";
    sql += "  _geoEntityId         VARCHAR, \n";
    sql += "  _date      DATE \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _epiWeek      INT; \n";
    sql += "  _firstDayOfEpiWeek INT; \n";
    sql += "  _seasonId    VARCHAR; \n";
    sql += "  _targetColumn  VARCHAR; \n";
    sql += "BEGIN \n";
    sql += "  SELECT id FROM malariaseason AS ms WHERE _date BETWEEN ms.startDate AND ms.endDate \n";
    sql += "    INTO _seasonId; \n";
    sql += "     \n";
    sql += "   IF _seasonId IS NULL THEN \n";
    sql += "     RETURN NULL; \n";
    sql += "   END IF; \n";
    sql += "   SELECT propertyValue FROM property WHERE keyname = 'epiStartWeekDay' \n";
    sql += "   INTO _firstDayOfEpiWeek; \n";
    sql += "    \n";
    sql += "  _epiWeek := get_epiWeek_from_date(_date,_firstDayOfEpiWeek); \n";
    sql += "  _targetColumn := ('target_' || _epiWeek);  \n";
    sql += "     \n";
    sql += "   RETURN get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(_geoEntityId,_seasonId,_targetColumn); \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
    
    
    
    
    
    sql += "CREATE OR REPLACE FUNCTION get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar \n";
    sql += "( \n";
    sql += "  _geoEntityId         VARCHAR, \n";
    sql += "  _seasonId    VARCHAR, \n";
    sql += "  _targetColumn  VARCHAR \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _target       INT; \n";
    sql += "  _childCount  INT; \n";
    sql += "  _sql TEXT; \n";
    sql += "  rec RECORD; \n";
    sql += "BEGIN \n";
    sql += "  EXECUTE 'SELECT '|| _targetColumn ||' FROM geotarget  WHERE season = $1 AND geoentity = $2 ' \n";
    sql += "    INTO _target \n";
    sql += "    USING _seasonId, _geoEntityId; \n";
    sql += "     \n";
    sql += "    IF _target IS NULL THEN \n";
    sql += "      --check if this branch will lead to any data \n";
    sql += "      EXECUTE 'SELECT count(gt.id) FROM allpaths_geo ap \n";
    sql += "     JOIN geotarget gt ON ap.childgeoentity = gt.geoentity   \n";
    sql += "      WHERE gt.season = $1 AND ap.parentgeoentity = $2 ' \n";
    sql += "      INTO _childCount \n";
    sql += "      USING _seasonId, _geoEntityId; \n";
    sql += "      --continue to recurse if this branch has data \n";
    sql += "      IF _childCount > 0 THEN \n";
    sql += "             _target := 0; \n";
    sql += "         _sql := 'SELECT child_id  FROM locatedin WHERE parent_id = ' || quote_literal(_geoEntityId); \n";
    sql += "         FOR  rec IN EXECUTE _sql LOOP \n";
    sql += "           _target := _target + coalesce(get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(rec.child_id,_seasonId,_targetColumn),0); \n";
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
    sql += "  _firstDayOfEpiWeek INT, \n";
    sql += "  OUT _epiWeek INT \n";
    sql += "   \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _year INT; \n";
    sql += "  _fourthOfJanWeekDay INT; \n";
    sql += "  _startDate date; \n";
    sql += "  _nextStartDate date; \n";
    sql += "  _prevStartDate date; \n";
    sql += "BEGIN  \n";
    sql += "  _year := EXTRACT(YEAR FROM _date); \n";
    sql += "   \n";
    sql += "  _prevStartDate = get_epiStart(_year-1,_firstDayOfEpiWeek); \n";
    sql += "  _startDate := get_epiStart(_year,_firstDayOfEpiWeek); \n";
    sql += "  _nextStartDate := get_epiStart(_year+1,_firstDayOfEpiWeek); \n";
    sql += "  --RAISE NOTICE '% % % %', _year,_prevStartDate,_startDate,_nextStartDate; \n";
    sql += "  CASE \n";
    sql += "   WHEN (_date >= _startDate ) AND (_date < _nextStartDate)  THEN \n";
    sql += "      _epiWeek := EXTRACT('epoch' FROM _date)::INT  - EXTRACT('epoch' FROM _startDate)::INT; \n";
    sql += "      _epiWeek := round((_epiWeek::FLOAT /(60.0*60.0*24.0))::INT  / 7)+1; \n";
    sql += "      --RAISE NOTICE '% % % % % %',_startDate, _date, _epiWeek, _epiWeek /(60*60), _epiWeek /(60*60*24), _epiWeek /(60*60*24*7); \n";
    sql += "       \n";
    sql += "       \n";
    sql += "   WHEN _date >= _nextStartDate THEN \n";
    sql += "      _epiWeek := 1; \n";
    sql += "   WHEN _date < _startDate THEN \n";
    sql += "      _epiWeek := EXTRACT('epoch' FROM _date)::INT  - EXTRACT('epoch' FROM _prevStartDate)::INT; \n";
    sql += "      _epiWeek := (round(_epiWeek::FLOAT /(60.0*60.0*24.0))::INT  / 7 )+1; \n";
    sql += "  END CASE; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
    
    
    
    
    sql += "CREATE OR REPLACE FUNCTION get_epiYear_from_date \n";
    sql += "( \n";
    sql += "  _date      DATE, \n";
    sql += "  -- 0 = SUNDAY \n";
    sql += "  _firstDayOfEpiWeek INT, \n";
    sql += "  OUT _epiYear INT \n";
    sql += "   \n";
    sql += ") \n";
    sql += "RETURNS INT AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _year INT; \n";
    sql += "  _fourthOfJanWeekDay INT; \n";
    sql += "  _startDate date; \n";
    sql += "  _nextStartDate date; \n";
    sql += "  _prevStartDate date; \n";
    sql += "BEGIN  \n";
    sql += "  _year := EXTRACT(YEAR FROM _date); \n";
    sql += "   \n";
    sql += "  _prevStartDate = get_epiStart(_year-1,_firstDayOfEpiWeek); \n";
    sql += "  _startDate := get_epiStart(_year,_firstDayOfEpiWeek); \n";
    sql += "  _nextStartDate := get_epiStart(_year+1,_firstDayOfEpiWeek); \n";
    sql += "  RAISE NOTICE '% % % %', _year,_prevStartDate,_startDate,_nextStartDate; \n";
    sql += "  CASE \n";
    sql += "   WHEN (_date >= _startDate ) AND (_date < _nextStartDate)  THEN \n";
    sql += "      _epiYear := _year; \n";
    sql += "   WHEN _date >= _nextStartDate THEN \n";
    sql += "      _epiYear := _year + 1; \n";
    sql += "   WHEN _date < _startDate THEN \n";
    sql += "      _epiYear := _year - 1;  \n";
    sql += "  END CASE; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
    
    
    
    
    sql += "CREATE OR REPLACE FUNCTION get_epiStart \n";
    sql += "( \n";
    sql += "  _year INT, \n";
    sql += "  _firstDayOfEpiWeek INT, \n";
    sql += "  OUT _startDate DATE \n";
    sql += ") \n";
    sql += "RETURNS DATE AS $$ \n";
    sql += "DECLARE \n";
    sql += "  _yearT TEXT; \n";
    sql += "  _fourthOfJanWeekDay INT; \n";
    sql += "  _janFourth DATE; \n";
    sql += "BEGIN  \n";
    sql += "  _janFourth := to_date(_year::TEXT || '-4', 'YYYY-DDD'); \n";
    sql += "  _fourthOfJanWeekDay := EXTRACT(DOW FROM _janFourth); \n";
    sql += "  IF (_fourthOfJanWeekDay >= _firstDayOfEpiWeek )THEN \n";
    sql += "   _startDate  := _janFourth - ((_fourthOfJanWeekDay - _firstDayOfEpiWeek)::text || ' days')::interval; \n";
    sql += "   --RAISE NOTICE 'A: % % %', _year,_fourthOfJanWeekDay,_startDate; \n";
    sql += "  ELSE \n";
    sql += "   _startDate  := _janFourth - ((7 + _fourthOfJanWeekDay - _firstDayOfEpiWeek)::text || ' days')::interval; \n";
    sql += "   --RAISE NOTICE 'B: % % %', _year,_fourthOfJanWeekDay,_startDate; \n";
    sql += "  END IF; \n";
    sql += "END; \n";
    sql += "$$ LANGUAGE plpgsql; \n";
    
    return sql;
  }
  
  
}
