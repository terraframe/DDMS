/*
SELECT dss_ontology_build_allpaths('z6n4nserbewij67if9ykzs010471b6v6', 1234, 'www.mdss.com', '0000000000000000000000000000001000000000000000000000000000000003', LOCALTIMESTAMP, 'swvpyeupgazp93x943y5xcvnjte24rjy1svxgm8h0q3cgvy8nvi1941cl9l5ekyb');
*/

CREATE OR REPLACE FUNCTION dss_ontology_build_allpaths
(
  _all_Paths_Root_Type_Id     VARCHAR,
  _random                 BIGINT,
  _site_master             allpaths_ontology.site_master%TYPE,
  _created_By_Id            allpaths_ontology.id%TYPE,
  _transaction_Date        allpaths_ontology.create_date%TYPE,
  _ontology_Relationship_Id allpaths_ontology.ontology_relationship%TYPE
)
RETURNS VOID AS $$

DECLARE
  _termCursor  CURSOR (c_ontologyRelationshipId allpaths_ontology.ontology_relationship%TYPE) FOR
  	SELECT parent_id, child_id
	FROM
	  ( WITH RECURSIVE quick_paths AS
	    ( SELECT child_id as root_id, child_id, parent_id
	      FROM term_relationship
	      WHERE term_relationship.ontology_Relationship = c_ontology_Relationship_Id
          UNION
          SELECT a.root_id, b.child_id, b.parent_id
          FROM quick_paths a, term_relationship b
          WHERE b.child_id = a.parent_id
          AND b.ontology_Relationship = c_ontology_Relationship_Id
        )
        SELECT root_id as child_id, parent_id
        FROM quick_paths
        UNION
        SELECT id, id
        FROM term
        WHERE id IN
          (SELECT parent_id
           FROM term_relationship
           WHERE ontology_relationship = c_ontology_Relationship_Id
          )
         OR id IN
          (SELECT child_id
           FROM term_relationship
           WHERE ontology_relationship = c_ontology_Relationship_Id
          )
      ) AS recurs_rel;

  _parent_Id  termrelationship.parent_id%TYPE;
  _child_Id   termrelationship.child_id%TYPE;
  _seq       allpaths_ontology.seq%TYPE;

BEGIN

  _seq := 0;

  OPEN _term_Cursor(_ontology_Relationship_Id);

  LOOP
    FETCH _term_Cursor INTO _parent_Id, _child_Id;

    EXIT WHEN NOT FOUND;

    _seq := _seq + 1;

    PERFORM
      dss_ontology_create_allpath(
        _all_Paths_Root_Type_Id,
        _random,
        _site_master,
        _created_By_Id,
        _transaction_Date,
        _parent_Id,
        _child_Id,
        _ontology_Relationship_Id,
        _seq);

  END LOOP;

  CLOSE _termCursor;

END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION dss_ontology_create_allpath
(
  _allPathsRootTypeId   VARCHAR,
  _random               BIGINT,
  _site_master           allpaths_ontology.site_master%TYPE,
  _created_By_Id          allpaths_ontology.id%TYPE,
  _transaction_Date      allpaths_ontology.create_date%TYPE,
  _parent_Term           allpaths_ontology.parent_term%TYPE,
  _child_Term            allpaths_ontology.child_term%TYPE,
  _ontology_Relationship_Id allpaths_ontology.ontology_relationship%TYPE,
  _seq                  allpaths_ontology.seq%TYPE
)
RETURNS VOID AS $$


DECLARE
  _id                 allpaths_ontology.id%TYPE;
  _idString           VARCHAR;
  _currentTimeMillis  TIMESTAMP;

BEGIN

	SELECT com_terraframe_mojo_createid
	  (_all_Paths_Root_TypeId,
       _random,
       _site_master,
       _seq,
       _transaction_Date) INTO _id;

    INSERT INTO allpaths_ontology
      (id,
       site_master,
       key_name,
       owner,
       create_date,
       type,
       last_update_date,
       last_updated_by,
       seq,
       created_by,
       parent_term,
       child_term,
       ontology_relationship)
    VALUES
      (_id,
       _site_master,
       _id,
       _created_By_Id,
       _transaction_Date,
       'dss.vector.solutions.ontology.AllPaths',
       _transaction_Date,
       _created_By_Id,
       _seq,
       _created_By_Id,
       _parent_Term,
       _child_Term,
       _ontology_Relationship_Id
      );

END;
$$ LANGUAGE plpgsql;


/*
SELECT dss_ontology_copy_term(
'z6n4nserbewij67if9ykzs010471b6v6',
1234,
'www.mdss.com',
'0000000000000000000000000000001000000000000000000000000000000003',
LOCALTIMESTAMP,
'05brck3zaer5w5vbcee16ry28lzao1e3xeklfgy3bklheiiulqszhfay163qlpta',
'zbdda5r3mmkkwyuhla67ymkn551jmtpnxeklfgy3bklheiiulqszhfay163qlpta',
'05kt1jddumk5qm8juvcxseyist5klwhrzm1g3udb394gw9tn8ca9qvefl9ncyubd'
);


mdssdevelop=> select id from term where term_id = 'IDOMAL:0000047';
                                id
------------------------------------------------------------------
 05brck3zaer5w5vbcee16ry28lzao1e3xeklfgy3bklheiiulqszhfay163qlpta
(1 row)

mdssdevelop=> select id from term where term_id = 'IDOMAL:0000049';
                                id
------------------------------------------------------------------
 zbdda5r3mmkkwyuhla67ymkn551jmtpnxeklfgy3bklheiiulqszhfay163qlpta



*/

CREATE OR REPLACE FUNCTION dss_ontology_copy_term
(
  _all_Paths_Root_Type_Id     VARCHAR,
  _random                 BIGINT,
  _site_master             allpaths_ontology.site_master%TYPE,
  _created_By_Id            allpaths_ontology.id%TYPE,
  _transaction_Date        allpaths_ontology.create_date%TYPE,
  _new_Parent_Term          allpaths_ontology.parent_term%TYPE,
  _child_Term              allpaths_ontology.child_term%TYPE,
  _ontology_Relationship_Id allpaths_ontology.ontology_relationship%TYPE
)
RETURNS VOID AS $$

DECLARE

BEGIN

  -- For each child term, copy all of the paths of the new parent
    INSERT INTO allpaths_ontology
      (id,
       site_master,
       key_name,
       owner,
       create_date,
       type,
       last_update_date,
       last_updated_by,
       seq,
       created_by,
       parent_term,
       child_term,
       ontology_relationship)
    SELECT
        MD5(allpaths_parent.parent_term || allpaths_child.child_term) || _all_Paths_Root_Type_Id AS newId,
      	_site_master,
        MD5(allpaths_parent.parent_term || allpaths_child.child_term) || _all_Paths_Root_Type_Id AS newKey,
    	_created_By_Id,
        _transaction_Date,
    	'dss.vector.solutions.ontology.AllPaths',
    	_transaction_Date,
        _created_By_Id,
        NEXTVAL('object_sequence_unique_id') as seq,
        _created_By_Id,
        allpaths_parent.parent_term,
        allpaths_child.child_term,
        _ontology_Relationship_Id
      FROM
        -- Fech all of the recursive children of the given child term, including the child term itself.
        (SELECT child_term
  	       FROM allpaths_ontology
  	      WHERE parent_term = _child_Term
  	        AND ontology_Relationship = _ontologyRelationshipId) AS allpaths_child,
        -- Fech all of the recursive parents of the given new parent term, including the new parent term itself.
        (SELECT parent_term
           FROM allpaths_ontology
          WHERE child_term = _new_Parent_Term
            AND ontology_Relationship = _ontology_Relationship_Id
        ) AS allpaths_parent
        -- Since a term can have multiple parents, a path to one of the new parent's parents may already exist
        WHERE allpaths_parent.parent_term NOT IN
         (SELECT parent_term
           FROM allpaths_ontology
          WHERE parentterm = allpaths_parent.parent_term
            AND childTerm = allpaths_child.child_term
            AND ontology_Relationship = _ontology_Relationship_Id);
--        AND allpaths_child.child_term NOT IN
--         (SELECT child_term
--           FROM allpaths_ontology
--          WHERE parent_term = allpaths_parent.parent_term
--            AND child_Term = allpaths_child.child_term
--            AND ontology_Relationship = _ontology_Relationship_Id);

END;
$$ LANGUAGE plpgsql;



/*

SELECT pg_attribute.attname
FROM pg_attribute, pg_class
WHERE pg_class.relname = 'asejnv791i8m4q2wsnvgs0rl49tezi'
AND pg_attribute.attrelid = pg_class.oid;

 */

