--DROP VIEW IF EXISTS geo_displayLabel;

CREATE OR REPLACE VIEW geo_displayLabel AS
SELECT  
  geo0.id, 
  geo0.geoID,
  dl1.defaultlocale AS type_displayLabel,
  geo0.entityName|| ' (' || dl1.defaultlocale ||  COALESCE(' : ' || ter.display,'')   || ') - ' || geo0.geoId AS displayLabel,
  geo0.entityName|| COALESCE(' : ' || ter.display,'')   AS shortDisplayLabel,
  (t1.packagename || '.' || t1.typename) AS parent_type,
  g1.political AS political,
  g1.spraytargetallowed AS spraytargetallowed, 
  g1.populationallowed AS populationallowed
FROM 
  geohierarchy g1, 
  md_type t1 ,
  metadatadisplaylabel dl1,
  geoentity geo0
  LEFT JOIN term AS ter ON ter.id = geo0.term
WHERE  
 t1.id = g1.geoentityclass 
AND t1.displaylabel = dl1.id
AND geo0.type =  (t1.packagename || '.' || t1.typename);



CREATE OR REPLACE VIEW geohierarchy_allpaths AS
WITH RECURSIVE geohierarchy_flags AS(
SELECT  (t1.packagename || '.' || t1.typename) AS parent_type,
  g1.geoentityclass as parent_class,
  g1.political AS parent_political,
  g1.spraytargetallowed AS parent_spraytargetallowed, 
  g1.populationallowed AS parent_populationallowed,
  (t2.packagename || '.' || t2.typename) AS child_type,
  g2.geoentityclass As child_class,
  g2.political AS child_political,
  g2.spraytargetallowed AS child_spraytargetallowed, 
  g2.populationallowed AS child_populationallowed
FROM allowedin ,
  geohierarchy g1, 
  geohierarchy g2,
  md_type t1 ,
  md_type t2 
WHERE  allowedin.parent_id = g1.id
AND allowedin.child_id = g2.id
AND t1.id = g1.geoentityclass 
AND t2.id = g2.geoentityclass

)
, recursive_rollup AS (
 SELECT child_type, parent_type, parent_type as root_type, child_political, child_spraytargetallowed, child_populationallowed, parent_political, parent_spraytargetallowed, parent_populationallowed, parent_class as root_class ,0 as depth,  child_class
  
  FROM geohierarchy_flags
 UNION
 SELECT b.child_type, b.parent_type, a.root_type, b.child_political, b.child_spraytargetallowed, b.child_populationallowed, a.parent_political, a.parent_spraytargetallowed, a.parent_populationallowed, a.root_class , a.depth+1 , b.child_class
 FROM recursive_rollup a,  geohierarchy_flags b
 WHERE a.child_type = b.parent_type
)

select root_type, root_class, child_type, child_class, child_political, child_spraytargetallowed, child_populationallowed, parent_political, parent_spraytargetallowed, parent_populationallowed , depth
from recursive_rollup  ;


/*
 * Get the popluation or cacluate it if it is not available. 
 */
CREATE OR REPLACE FUNCTION get_adjusted_population
(
  _geoEntityId         VARCHAR,
  _year             INT,
  _middleDay INT
)
RETURNS FLOAT AS $$

DECLARE
  _population                FLOAT;
  _sql VARCHAR;
   rec record;
  _prevYear INT;
  _growth FLOAT;
  _childCount FLOAT; 
  _percentageAdjustment FLOAT;
BEGIN

  _percentageAdjustment = (_middleDay/183);

  SELECT population , yearofdata, growthrate FROM populationdata pd 
    WHERE pd.yearofdata  <= _year AND pd.geoentity = _geoEntityId 
    ORDER BY pd.yearofdata DESC
    LIMIT 1
    INTO _population, _prevYear, _growth;
    
    IF _population IS NOT NULL THEN
      WHILE _prevYear < _year LOOP
        IF _prevYear = _year - 1  THEN
           _population := _population + (_population * (_growth * _percentageAdjustment ));
        ELSE
           _population := _population + (_population * _growth);
        END IF;
        _prevYear := _prevYear + 1;
      END LOOP;
    ELSE
     _population := 0;
      --check if this branch will lead to any data
      SELECT count(ap.id) FROM allpaths_geo ap
	  JOIN populationdata pd ON ap.childgeoentity = pd.geoentity 
	  WHERE pd.population IS NOT NULL AND ap.parentgeoentity =  _geoEntityId AND pd.yearofdata <= _year
      INTO _childCount;
      --continue to recurse if this branch has data
       IF _childCount > 0 THEN
	      
	      _sql := 'SELECT child_id  FROM locatedin WHERE parent_id = ' || quote_literal(_geoEntityId);
	      FOR  rec IN EXECUTE _sql LOOP
		    _population = _population + get_adjusted_population(rec.child_id, _year,_middleDay);
	      END LOOP;
       END IF;
    END IF;
    
    RETURN _population;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_yearly_population_by_geoid_and_date
(
  _geoId         VARCHAR,
  _date             DATE
)
RETURNS FLOAT AS $$

DECLARE
  _population      FLOAT;
  _geoEntityId  VARCHAR;
  _year             INT;
BEGIN

  SELECT id FROM geoentity WHERE geoid = _geoId
    INTO _geoEntityId;

   _year := EXTRACT(year FROM _date);

  SELECT get_adjusted_population(_geoEntityId, _year,183)
    INTO _population;
    
    RETURN _population;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION get_seasonal_population_by_geoid_and_date
(
  _geoId         VARCHAR,
  _date      DATE
)
RETURNS FLOAT AS $$

DECLARE
  _population                FLOAT;
  _geoEntityId  VARCHAR;
  _year             INT;
  _seasonStart DATE;
  _seasonMiddle DATE;
  _seasonEnd DATE;
  _middleDay INT;
BEGIN

  SELECT id FROM geoentity WHERE geoid = _geoId
    INTO _geoEntityId;

  SELECT startdate,enddate FROM malariaseason AS ms  WHERE ms.startDate <= _date AND ms.endDate >= _date
    INTO _seasonStart, _seasonEnd;
  
  _seasonMiddle := _seasonStart + ((_seasonEnd - _seasonStart)/2);

  _middleDay := EXTRACT(doy FROM _seasonMiddle);

  _year := EXTRACT(year FROM _seasonMiddle);
 
  SELECT get_adjusted_population(_geoEntityId, _year, _middleDay)
    INTO _population;
    
    RETURN _population;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_seasonal_spray_target_by_geoEntityId_and_date
(
  _geoEntityId         VARCHAR,
  _date      DATE
)
RETURNS INT AS $$

DECLARE
  _epiWeek      INT;
  _firstDayOfEpiWeek INT;
  _seasonId  	VARCHAR;
  _targetColumn  VARCHAR;
BEGIN

  SELECT id FROM malariaseason AS ms WHERE _date BETWEEN ms.startDate AND ms.endDate
    INTO _seasonId;
    
   IF _seasonId IS NULL THEN
     RETURN NULL;
   END IF;

   SELECT propertyValue FROM property WHERE keyname = 'epiStartWeekDay'
   INTO _firstDayOfEpiWeek;
   
  _epiWeek := get_epiWeek_from_date(_date,_firstDayOfEpiWeek);

  _targetColumn := ('target_' || _epiWeek); 
    
   RETURN get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(_geoEntityId,_seasonId,_targetColumn);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar
(
  _geoEntityId         VARCHAR,
  _seasonId  	VARCHAR,
  _targetColumn  VARCHAR
)
RETURNS INT AS $$

DECLARE
  _target       INT;
  _childCount  INT;

  _sql TEXT;
  rec RECORD;
BEGIN

  EXECUTE 'SELECT '|| _targetColumn ||' FROM geotarget  WHERE season = $1 AND geoentity = $2 '
    INTO _target
    USING _seasonId, _geoEntityId;
    
    IF _target IS NULL THEN
      --check if this branch will lead to any data
      EXECUTE 'SELECT count(gt.id) FROM allpaths_geo ap
	  JOIN geotarget gt ON ap.childgeoentity = gt.geoentity  
	   WHERE gt.season = $1 AND ap.parentgeoentity = $2 '
      INTO _childCount
      USING _seasonId, _geoEntityId;
      --continue to recurse if this branch has data
      IF _childCount > 0 THEN
             _target := 0;
	      _sql := 'SELECT child_id  FROM locatedin WHERE parent_id = ' || quote_literal(_geoEntityId);
	      FOR  rec IN EXECUTE _sql LOOP
		    _target = _target + get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(rec.child_id,_seasonId,_targetColumn);
	      END LOOP;
       END IF;
    END IF;
    
    RETURN _target;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_epiWeek_from_date
(
  _date      DATE,
  -- 0 = SUNDAY
  _firstDayOfEpiWeek INT,
  OUT _epiWeek INT
  
)
RETURNS INT AS $$

DECLARE
  _year INT;
  _fourthOfJanWeekDay INT;
  _startDate date;
  _nextStartDate date;
  _prevStartDate date;
BEGIN 
  _year := EXTRACT(YEAR FROM _date);
  
  _prevStartDate = get_epiStart(_year-1,_firstDayOfEpiWeek);
  _startDate := get_epiStart(_year,_firstDayOfEpiWeek);
  _nextStartDate := get_epiStart(_year+1,_firstDayOfEpiWeek);

  --RAISE NOTICE '% % % %', _year,_prevStartDate,_startDate,_nextStartDate;
  CASE
   WHEN (_date >= _startDate ) AND (_date < _nextStartDate)  THEN
      _epiWeek := EXTRACT('epoch' FROM _date)::INT  - EXTRACT('epoch' FROM _startDate)::INT;
      _epiWeek := round(_epiWeek::FLOAT /(60.0*60.0*24.0))::INT  / 7;
      --RAISE NOTICE '% % % % % %',_startDate, _date, _epiWeek, _epiWeek /(60*60), _epiWeek /(60*60*24), _epiWeek /(60*60*24*7);
      
      
   WHEN _date >= _nextStartDate THEN
      _epiWeek := 1;
   WHEN _date < _startDate THEN
      _epiWeek := EXTRACT('epoch' FROM _date)::INT  - EXTRACT('epoch' FROM _prevStartDate)::INT;
      _epiWeek := (round(_epiWeek::FLOAT /(60.0*60.0*24.0))::INT  / 7 )+1;
  END CASE;

END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_epiYear_from_date
(
  _date      DATE,
  -- 0 = SUNDAY
  _firstDayOfEpiWeek INT,
  OUT _epiYear INT
  
)
RETURNS INT AS $$

DECLARE
  _year INT;
  _fourthOfJanWeekDay INT;
  _startDate date;
  _nextStartDate date;
  _prevStartDate date;
BEGIN 
  _year := EXTRACT(YEAR FROM _date);
  
  _prevStartDate = get_epiStart(_year-1,_firstDayOfEpiWeek);
  _startDate := get_epiStart(_year,_firstDayOfEpiWeek);
  _nextStartDate := get_epiStart(_year+1,_firstDayOfEpiWeek);

  RAISE NOTICE '% % % %', _year,_prevStartDate,_startDate,_nextStartDate;
  CASE
   WHEN (_date >= _startDate ) AND (_date < _nextStartDate)  THEN
      _epiYear := _year;
   WHEN _date >= _nextStartDate THEN
      _epiYear := _year + 1;
   WHEN _date < _startDate THEN
      _epiYear := _year - 1; 
  END CASE;

END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION get_epiStart
(
  _year INT,
  _firstDayOfEpiWeek INT,
  OUT _startDate DATE
)
RETURNS DATE AS $$

DECLARE
  _yearT TEXT;
  _fourthOfJanWeekDay INT;
  _janFourth DATE;
BEGIN 
  _janFourth := to_date(_year::TEXT || '-4', 'YYYY-DDD');
  _fourthOfJanWeekDay := EXTRACT(DOW FROM _janFourth);

  IF (_fourthOfJanWeekDay >= _firstDayOfEpiWeek )THEN
	_startDate  := _janFourth - ((_fourthOfJanWeekDay - _firstDayOfEpiWeek)::text || ' days')::interval;
	--RAISE NOTICE 'A: % % %', _year,_fourthOfJanWeekDay,_startDate;
  ELSE
	_startDate  := _janFourth - ((7 + _fourthOfJanWeekDay - _firstDayOfEpiWeek)::text || ' days')::interval;
	--RAISE NOTICE 'B: % % %', _year,_fourthOfJanWeekDay,_startDate;
  END IF;

  
END;
$$ LANGUAGE plpgsql;

/*
SELECT ser as year, 
get_epistart(ser,0) sun,
get_epistart(ser,1) mon,
get_epistart(ser,2) tues
,get_epistart(ser,3) wens,
get_epistart(ser,4) thurs,
get_epistart(ser,5) fri,
get_epistart(ser,6) sat
FROM
(SELECT generate_series(1990,2020)  as ser) as s;
*/

--SELECT EXTRACT(week FROM '2010-01-01'::date)-1;
--SELECT get_seasonal_spray_target_by_geoEntityId_and_date((SELECT id FROM geoentity WHERE geoid = '2828009'),'2010-01-01'::date)
--SELECT get_seasonal_spray_target_by_geoEntityId_and_date((SELECT id FROM geoentity WHERE geoid = '1300158'),'2010-01-01'::date)


--select get_yearly_population_by_geoid_and_date('22002',  '2010-01-01'::DATE)
--select get_seasonal_population_by_geoid_and_date('22002',  '2010-01-01'::DATE)