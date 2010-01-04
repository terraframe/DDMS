CREATE OR REPLACE VIEW geo_displayLabel AS
SELECT  
  geo0.id, 
  geo0.geoID,
  dl1.defaultlocale AS type_displayLabel,
  geo0.entityName|| ' (' || dl1.defaultlocale ||  COALESCE(' : ' || ter.display,'')   || ') - ' || geo0.geoId AS displayLabel,
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

--select get_yearly_population_by_geoid_and_date('22002',  '2010-01-01'::DATE)
--select get_seasonal_population_by_geoid_and_date('22002',  '2010-01-01'::DATE)