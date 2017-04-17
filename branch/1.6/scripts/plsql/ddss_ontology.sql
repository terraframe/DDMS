/*
SELECT dss_ontology_build_allpaths('z6n4nserbewij67if9ykzs010471b6v6', 1234, 'www.mdss.com', '0000000000000000000000000000001000000000000000000000000000000003', LOCALTIMESTAMP, 'swvpyeupgazp93x943y5xcvnjte24rjy1svxgm8h0q3cgvy8nvi1941cl9l5ekyb');
*/

CREATE OR REPLACE FUNCTION dss_ontology_build_allpaths
(
  _all_Paths_Root_TypeId     VARCHAR,
  _random                 BIGINT,
  _site_master             allpaths.site_master%TYPE,
  _created_By_Id            allpaths.id%TYPE,
  _transaction_Date        allpaths.create_date%TYPE,
  _ontology_Relationship_Id allpaths.ontology_relationship%TYPE
)
RETURNS VOID AS $$

DECLARE
  _termCursor  CURSOR (c_ontology_RelationshipId allpaths.ontology_relationship%TYPE) FOR
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

  _parent_Id  term_relationship.parent_id%TYPE;
  _child_Id   term_relationship.child_id%TYPE;

  _seq       allpaths.seq%TYPE;

BEGIN

  _seq := 0;

  OPEN _termCursor(_ontology_Relationship_Id);

  LOOP
    FETCH _termCursor INTO _parentId, _childId;

    EXIT WHEN NOT FOUND;

--	SELECT NEXTVAL('object_sequence_unique_id') INTO _seq;
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
  _site_master           allpaths.site_master%TYPE,
  _created_By_Id          allpaths.id%TYPE,
  _transaction_Date      allpaths.create_date%TYPE,
  _parent_Term           allpaths.parent_term%TYPE,
  _child_Term            allpaths.child_term%TYPE,
  _ontology_Relationship_Id allpaths.ontology_relationship%TYPE,
  _seq                  allpaths.seq%TYPE
)
RETURNS VOID AS $$


DECLARE
  _id                 allpaths.id%TYPE;
  _idString           VARCHAR;
  _currentTimeMillis  TIMESTAMP;

BEGIN

--	SELECT NOW() + interval '1 millisecond';

--    EXECUTE 'SELECT timestamp ''' || _transaction_Date || ''' + interval ''' || _seq || ' millisecond ''' INTO _currentTimeMillis;

	SELECT com_terraframe_mojo_createid
	  (_all_Paths_Root_Type_Id,
       _random,
       _site_master,
       _seq,
       _transaction_Date) INTO _id;

    INSERT INTO allpaths
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
'zn96h6uok8wi9mp9ckp9mxwznfiyfiktsne8jxk8l206b5zftsv4t85gv0ryhuzr',
'1yivcac6w8n9tf1kim8xb3jdcsz5cxncsne8jxk8l206b5zftsv4t85gv0ryhuzr',
'6oeb2eotn3gyzx6efajdrfvrhq4vpblhzad6j3ceqzmnmlccektq3l57zy7skzgp'
);


mdssdevelop=> select id from term where term_id = 'IDOMAL:0000047';
                                id
------------------------------------------------------------------
 zn96h6uok8wi9mp9ckp9mxwznfiyfiktsne8jxk8l206b5zftsv4t85gv0ryhuzr
(1 row)

mdssdevelop=> select id from term where term_id = 'IDOMAL:0000049';
                                id
------------------------------------------------------------------
 1yivcac6w8n9tf1kim8xb3jdcsz5cxncsne8jxk8l206b5zftsv4t85gv0ryhuzr



*/

CREATE OR REPLACE FUNCTION dss_ontology_copy_term
(
  _all_Paths_Root_Type_Id     VARCHAR,
  _random                 BIGINT,
  _site_master             allpaths.site_master%TYPE,
  _created_By_Id            allpaths.id%TYPE,
  _transaction_Date        allpaths.create_date%TYPE,
  _newParent_Term          allpaths.parent_term%TYPE,
  _child_Term              allpaths.child_term%TYPE,
  _ontology_Relationship_Id allpaths.ontology_relationship%TYPE
)
RETURNS VOID AS $$

DECLARE
  _seq                allpaths.seq%TYPE;

  -- As the cursor iteratates over the term query, store the current term in this variable
  _cursorChildTerm      allpaths.child_term%TYPE;

  -- Fech all of the recursive children of the given child term, including the child term itself.
  _termCursor  CURSOR (c_child_Term allpaths.child_term%TYPE, c_ontology_Relationship_Id allpaths.ontology_relationship%TYPE) FOR
  	SELECT child_Term
  	  FROM allpaths
  	 WHERE parent_Term = c_child_Term
  	   AND ontology_Relationship = c_ontology_Relationship_Id;

BEGIN

  OPEN _termCursor(_child_Term, _ontology_Relationship_Id);

  -- For each child term, copy all of the paths of the new parent
  LOOP
    FETCH _termCursor INTO _cursorChildTerm;

    EXIT WHEN NOT FOUND;

	SELECT NEXTVAL('object_sequence_unique_id') INTO _seq;

    INSERT INTO allpaths
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
        (SELECT com_terraframe_mojo_createid(_all_Paths_Root_Type_Id, _random, _site_master, _seq, _transaction_Date) AS new_Id),
      	_site_master,
      	(SELECT com_terraframe_mojo_createid(_all_Paths_Root_Type_Id, _random, _site_master, _seq, _transaction_Date) AS new_Key),
    	_created_By_Id,
        _transaction_Date,
    	'dss.vector.solutions.ontology.AllPaths',
    	_transaction_Date,
        _created_By_Id,
        _seq,
        _created_By_Id,
        spooftable.parent_term,
        _cursorChildTerm,
        _ontology_Relationship_Id
      FROM
        (SELECT parent_term
           FROM allpaths
          WHERE child_term = _new_Parent_Term
            AND ontology_Relationship = _ontology_Relationship_Id
            -- Since a term can have multiple parents, a path to one of the new parent's parents may already exist
            AND parent_term NOT IN
              (SELECT parent_term
                 FROM allpaths
                 WHERE child_Term = _cursorChildTerm)
        ) AS spooftable;

  END LOOP;


END;
$$ LANGUAGE plpgsql;

/*

SELECT pg_attribute.attname
FROM pg_attribute, pg_class
WHERE pg_class.relname = 'asejnv791i8m4q2wsnvgs0rl49tezi'
AND pg_attribute.attrelid = pg_class.oid;

 */

