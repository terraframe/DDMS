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
AND geo0.type =  (t1.packagename || '.' || t1.typename)