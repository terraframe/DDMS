WITH RECURSIVE geo_displayLabel AS (
SELECT geo.id, geo_id, COALESCE( geoLabel.m_alaria_default_locale, geoLabel.default_locale) || ' (' || 
COALESCE( typeLabel.m_alaria_default_locale, typeLabel.default_locale) ||
COALESCE( ' : ' || termLabel.m_alaria_default_locale, ' : ' || termLabel.default_locale, '') || ')' AS label
FROM  
geo_entity geo 
INNER JOIN geo_entity_entity_label geoLabel ON geo.entity_label = geoLabel.id
INNER JOIN md_type md ON geo.type =  (md.package_name || '.' || md.type_name)
INNER JOIN metadata_display_label typeLabel ON md.display_label = typeLabel.id 
LEFT JOIN term AS term ON term.id = geo.term 
LEFT JOIN term_term_display_label AS termLabel ON termLabel.id = term.term_display_label 

),
mainQuery AS (
	SELECT taxon,
		SUM(females_total) as abundance_sum, 
 		array_agg(collection_id) as collectionIds, 
 		array_agg(coalesce(collection_id || sub_collection_id, collection_id)) as subCollectionIds, 
     		((NULL::integer)) AS collectionCount,
		((COALESCE( term_term_display_label_10.m_alaria_default_locale, term_term_display_label_10.default_locale))) AS collectionMethod_displayLabel,
		((COALESCE( term_term_display_label_8.m_alaria_default_locale, term_term_display_label_8.default_locale))) AS taxon_displayLabel,
		((COALESCE( term_term_display_label_12.m_alaria_default_locale, term_term_display_label_12.default_locale))) AS collectionType_displayLabel,
		((COALESCE( term_term_display_label_14.m_alaria_default_locale, term_term_display_label_14.default_locale))) AS wallType_displayLabel,
		((1)) AS abundance_1,
		('') AS geo_id_coalesce_alias
	FROM mosquito_collection mosquito_collection_5 LEFT JOIN term term_9 ON mosquito_collection_5.collection_method = term_9.id
	 	LEFT JOIN term_term_display_label term_term_display_label_10 ON term_9.term_display_label = term_term_display_label_10.id
	 	LEFT JOIN term term_11 ON mosquito_collection_5.collection_type = term_11.id
	 	LEFT JOIN term_term_display_label term_term_display_label_12 ON term_11.term_display_label = term_term_display_label_12.id
	 	LEFT JOIN term term_13 ON mosquito_collection_5.wall_type = term_13.id
	 	LEFT JOIN term_term_display_label term_term_display_label_14 ON term_13.term_display_label = term_term_display_label_14.id,
     		sub_collection sub_collection_2 LEFT JOIN term term_7 ON sub_collection_2.taxon = term_7.id
 		LEFT JOIN term_term_display_label term_term_display_label_8 ON term_7.term_display_label = term_term_display_label_8.id
	WHERE (sub_collection_2.collection = mosquito_collection_5.id
		AND mosquito_collection_5.abundance = 1)
	GROUP BY taxon, collectionMethod_displayLabel, taxon_displayLabel, collectionType_displayLabel, wallType_displayLabel, geo_id_coalesce_alias

),
taxonCountQuery AS
(
	SELECT mainQuery.*,
	(
		SELECT SUM(ss.abundance_sum)
		FROM mainQuery as ss, allpaths_ontology ap
		WHERE ss.taxon = child_term AND parent_term = mainQuery.taxon AND ss.taxon != mainQuery.taxon  AND ss.geo_id_coalesce_alias = mainQuery.geo_id_coalesce_alias
 			AND ss.collectionMethod_displayLabel = mainQuery.collectionMethod_displayLabel
 			AND CASE WHEN mainQuery.collectionType_displayLabel IS NULL THEN true ELSE ss.collectionType_displayLabel = mainQuery.collectionType_displayLabel END
 			AND CASE WHEN mainQuery.wallType_displayLabel IS NULL THEN true ELSE ss.wallType_displayLabel = mainQuery.wallType_displayLabel END
	) as total_of_children,
	ARRAY
	(
		SELECT distinct unnest(collectionIDs)
		FROM mainQuery as ss
		WHERE 1 = 1 AND ss.geo_id_coalesce_alias = mainQuery.geo_id_coalesce_alias
 			AND ss.collectionMethod_displayLabel = mainQuery.collectionMethod_displayLabel
 			AND CASE WHEN mainQuery.collectionType_displayLabel IS NULL THEN true ELSE ss.collectionType_displayLabel = mainQuery.collectionType_displayLabel END
 			AND CASE WHEN mainQuery.wallType_displayLabel IS NULL THEN true ELSE ss.wallType_displayLabel = mainQuery.wallType_displayLabel END
	)::text[] allCollectionIds,
	ARRAY	
	(
		SELECT distinct unnest(collectionIDs)
		FROM mainQuery as ss, allpaths_ontology ap
		WHERE ss.taxon = ap.child_term AND ap.parent_term = mainQuery.taxon
 			AND ss.collectionMethod_displayLabel = mainQuery.collectionMethod_displayLabel
 			AND CASE WHEN mainQuery.collectionType_displayLabel IS NULL THEN true ELSE ss.collectionType_displayLabel = mainQuery.collectionType_displayLabel END
 			AND CASE WHEN mainQuery.wallType_displayLabel IS NULL THEN true ELSE ss.wallType_displayLabel = mainQuery.wallType_displayLabel END
	)::text[] allCollectionIdsAggregatedBySpecies,
	ARRAY
	(
		SELECT distinct unnest(subCollectionIDs)
		FROM mainQuery as ss
		WHERE 1 = 1  AND ss.geo_id_coalesce_alias = mainQuery.geo_id_coalesce_alias
 			AND ss.collectionMethod_displayLabel = mainQuery.collectionMethod_displayLabel
 			AND CASE WHEN mainQuery.collectionType_displayLabel IS NULL THEN true ELSE ss.collectionType_displayLabel = mainQuery.collectionType_displayLabel END
 			AND CASE WHEN mainQuery.wallType_displayLabel IS NULL THEN true ELSE ss.wallType_displayLabel = mainQuery.wallType_displayLabel END
	)::text[] allSubCollectionIds, 
	(
		SELECT COUNT(*)
		FROM mainQuery as ss, allpaths_ontology ap
		WHERE ss.taxon = parent_term  AND child_term = mainQuery.taxon AND ss.taxon != mainQuery.taxon  AND ss.geo_id_coalesce_alias = mainQuery.geo_id_coalesce_alias
 			AND ss.collectionMethod_displayLabel = mainQuery.collectionMethod_displayLabel
 			AND CASE WHEN mainQuery.collectionType_displayLabel IS NULL THEN true ELSE ss.collectionType_displayLabel = mainQuery.collectionType_displayLabel END
 			AND CASE WHEN mainQuery.wallType_displayLabel IS NULL THEN true ELSE ss.wallType_displayLabel = mainQuery.wallType_displayLabel END
	) as depth,
	(
		SELECT nt.parent
		FROM get_next_taxon(mainQuery.taxon) nt, mainQuery ss 
		where nt.parent = ss.taxon AND mainQuery.taxon != nt.parent  AND ss.geo_id_coalesce_alias = mainQuery.geo_id_coalesce_alias
 			AND ss.collectionMethod_displayLabel = mainQuery.collectionMethod_displayLabel
 			AND CASE WHEN mainQuery.collectionType_displayLabel IS NULL THEN true ELSE ss.collectionType_displayLabel = mainQuery.collectionType_displayLabel END
 			AND CASE WHEN mainQuery.wallType_displayLabel IS NULL THEN true ELSE ss.wallType_displayLabel = mainQuery.wallType_displayLabel END 
 		ORDER BY nt.depth ASC LIMIT 1
	) as parent, 
  	mainQuery.collectionMethod_displayLabel  ||  mainQuery.collectionType_displayLabel  ||  mainQuery.wallType_displayLabel   AS areaGroup
 	FROM mainQuery
),
percent_view AS
(
	SELECT taxonCountQuery.*,
	(
		(abundance_sum + coalesce(total_of_children,0)) / 
 		NULLIF((SELECT SUM(coalesce(ss.total_of_children,0) + ss.abundance_sum)
		FROM taxonCountQuery AS ss
		WHERE ss.parent = taxonCountQuery.parent
			AND ss.geo_id_coalesce_alias = taxonCountQuery.geo_id_coalesce_alias
 			AND ss.collectionMethod_displayLabel = taxonCountQuery.collectionMethod_displayLabel
 			AND CASE WHEN taxonCountQuery.collectionType_displayLabel IS NULL THEN true ELSE ss.collectionType_displayLabel = taxonCountQuery.collectionType_displayLabel END
 			AND CASE WHEN taxonCountQuery.wallType_displayLabel IS NULL THEN true ELSE ss.wallType_displayLabel = taxonCountQuery.wallType_displayLabel END   ), 0))  as my_share 
		FROM taxonCountQuery 
		GROUP BY parent,abundance_sum,allCollectionIds,allCollectionIdsAggregatedBySpecies,geo_id_coalesce_alias,collectionMethod_displayLabel,total_of_children,allSubCollectionIds,depth,wallType_displayLabel,abundance_1,collectionIDs,collectionCount,taxon,subCollectionIDs,collectionType_displayLabel,taxon_displayLabel,areaGroup 

),
abundance_view AS
(
	SELECT *, abundance_sum + coalesce(total_of_children,0) as final_abundance
	FROM percent_view
	WHERE depth = 0
 	UNION ALL
 	SELECT child_v.*, parent_v.final_abundance * child_v.my_share 
 	FROM abundance_view parent_v, percent_view child_v
	WHERE parent_v.taxon = child_v.parent
		AND parent_v.areagroup = child_v.areagroup AND child_v.geo_id_coalesce_alias = parent_v.geo_id_coalesce_alias 

)
SELECT
     (array_length(allCollectionIds,1)) AS collectionCount,
     (array_length(allCollectionIdsAggregatedBySpecies,1)) AS allCollectionIdsAggregatedBySpecies,
     (collectionMethod_displayLabel) AS collectionMethod_displayLabel,
     (taxon_displayLabel) AS taxon_displayLabel,
     (collectionType_displayLabel) AS collectionType_displayLabel,
     (wallType_displayLabel) AS wallType_displayLabel,
     (1.0*(final_abundance/array_length(allCollectionIds,1))) AS abundance_1,
     (count(*) over()) AS dss_vector_solutions__window_count
FROM abundance_view abundance_view 

