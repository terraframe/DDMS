/*
SELECT dss_ontology_build_allpaths('z6n4nserbewij67if9ykzs010471b6v6', 1234, 'www.mdss.com', '0000000000000000000000000000001000000000000000000000000000000003', LOCALTIMESTAMP, 'swvpyeupgazp93x943y5xcvnjte24rjy1svxgm8h0q3cgvy8nvi1941cl9l5ekyb');
*/

CREATE OR REPLACE FUNCTION dss_ontology_build_allpaths
(
  _allPathsRootTypeId     VARCHAR,
  _random                 BIGINT,
  _sitemaster             allpaths.sitemaster%TYPE,
  _createdById            allpaths.id%TYPE,
  _transactionDate        allpaths.createdate%TYPE,
  _ontologyRelationshipId allpaths.ontologyrelationship%TYPE
)
RETURNS VOID AS $$

DECLARE
  _termCursor  CURSOR (c_ontologyRelationshipId allpaths.ontologyrelationship%TYPE) FOR
  	SELECT parent_id, child_id
	FROM
	  ( WITH RECURSIVE quick_paths AS
	    ( SELECT child_id as root_id, child_id, parent_id
	      FROM termrelationship
	      WHERE termrelationship.ontologyRelationship = c_ontologyRelationshipId
          UNION
          SELECT a.root_id, b.child_id, b.parent_id
          FROM quick_paths a, termrelationship b
          WHERE b.child_id = a.parent_id
          AND b.ontologyRelationship = c_ontologyRelationshipId
        )
        SELECT root_id as child_id, parent_id
        FROM quick_paths
        UNION
        SELECT id, id
        FROM term
        WHERE id IN
          (SELECT parent_id
           FROM termrelationship
           WHERE ontologyrelationship = c_ontologyRelationshipId
          )
         OR id IN
          (SELECT child_id
           FROM termrelationship
           WHERE ontologyrelationship = c_ontologyRelationshipId
          )
      ) AS recurs_rel;

  _parentId  termrelationship.parent_id%TYPE;
  _childId   termrelationship.child_id%TYPE;

  _seq       allpaths.seq%TYPE;

BEGIN

  _seq := 0;

  OPEN _termCursor(_ontologyRelationshipId);

  LOOP
    FETCH _termCursor INTO _parentId, _childId;

    EXIT WHEN NOT FOUND;

--	SELECT NEXTVAL('object_sequence_unique_id') INTO _seq;
    _seq := _seq + 1;

    PERFORM
      dss_ontology_create_allpath(
        _allPathsRootTypeId,
        _random,
        _sitemaster,
        _createdById,
        _transactionDate,
        _parentId,
        _childId,
        _ontologyRelationshipId,
        _seq);

  END LOOP;

  CLOSE _termCursor;

END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION dss_ontology_create_allpath
(
  _allPathsRootTypeId   VARCHAR,
  _random               BIGINT,
  _sitemaster           allpaths.sitemaster%TYPE,
  _createdById          allpaths.id%TYPE,
  _transactionDate      allpaths.createdate%TYPE,
  _parentTerm           allpaths.parentterm%TYPE,
  _childTerm            allpaths.childterm%TYPE,
  _ontologyRelationshipId allpaths.ontologyrelationship%TYPE,
  _seq                  allpaths.seq%TYPE
)
RETURNS VOID AS $$


DECLARE
  _id                 allpaths.id%TYPE;
  _idString           VARCHAR;
  _currentTimeMillis  TIMESTAMP;

BEGIN

--	SELECT NOW() + interval '1 millisecond';

--    EXECUTE 'SELECT timestamp ''' || _transactionDate || ''' + interval ''' || _seq || ' millisecond ''' INTO _currentTimeMillis;

	SELECT com_terraframe_mojo_createid
	  (_allPathsRootTypeId,
       _random,
       _sitemaster,
       _seq,
       _transactionDate) INTO _id;

    INSERT INTO allpaths
      (id,
       sitemaster,
       keyname,
       owner,
       createdate,
       type,
       lastupdatedate,
       lastupdatedby,
       seq,
       createdby,
       parentterm,
       childterm,
       ontologyrelationship)
    VALUES
      (_id,
       _sitemaster,
       _id,
       _createdById,
       _transactionDate,
       'dss.vector.solutions.ontology.AllPaths',
       _transactionDate,
       _createdById,
       _seq,
       _createdById,
       _parentTerm,
       _childTerm,
       _ontologyRelationshipId
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


mdssdevelop=> select id from term where termid = 'IDOMAL:0000047';
                                id
------------------------------------------------------------------
 zn96h6uok8wi9mp9ckp9mxwznfiyfiktsne8jxk8l206b5zftsv4t85gv0ryhuzr
(1 row)

mdssdevelop=> select id from term where termid = 'IDOMAL:0000049';
                                id
------------------------------------------------------------------
 1yivcac6w8n9tf1kim8xb3jdcsz5cxncsne8jxk8l206b5zftsv4t85gv0ryhuzr



*/

CREATE OR REPLACE FUNCTION dss_ontology_copy_term
(
  _allPathsRootTypeId     VARCHAR,
  _random                 BIGINT,
  _sitemaster             allpaths.sitemaster%TYPE,
  _createdById            allpaths.id%TYPE,
  _transactionDate        allpaths.createdate%TYPE,
  _newParentTerm          allpaths.parentterm%TYPE,
  _childTerm              allpaths.childterm%TYPE,
  _ontologyRelationshipId allpaths.ontologyrelationship%TYPE
)
RETURNS VOID AS $$

DECLARE
  _seq                allpaths.seq%TYPE;

  -- As the cursor iteratates over the term query, store the current term in this variable
  _cursorChildTerm      allpaths.childterm%TYPE;

  -- Fech all of the recursive children of the given child term, including the child term itself.
  _termCursor  CURSOR (c_childTerm allpaths.childterm%TYPE, c_ontologyRelationshipId allpaths.ontologyrelationship%TYPE) FOR
  	SELECT childTerm
  	  FROM allpaths
  	 WHERE parentTerm = c_childTerm
  	   AND ontologyRelationship = c_ontologyRelationshipId;

BEGIN

  OPEN _termCursor(_childTerm, _ontologyRelationshipId);

  -- For each child term, copy all of the paths of the new parent
  LOOP
    FETCH _termCursor INTO _cursorChildTerm;

    EXIT WHEN NOT FOUND;

	SELECT NEXTVAL('object_sequence_unique_id') INTO _seq;

    INSERT INTO allpaths
      (id,
       sitemaster,
       keyname,
       owner,
       createdate,
       type,
       lastupdatedate,
       lastupdatedby,
       seq,
       createdby,
       parentterm,
       childterm,
       ontologyrelationship)
    SELECT
        (SELECT com_terraframe_mojo_createid(_allPathsRootTypeId, _random, _sitemaster, _seq, _transactionDate) AS newId),
      	_sitemaster,
      	(SELECT com_terraframe_mojo_createid(_allPathsRootTypeId, _random, _sitemaster, _seq, _transactionDate) AS newKey),
    	_createdById,
        _transactionDate,
    	'dss.vector.solutions.ontology.AllPaths',
    	_transactionDate,
        _createdById,
        _seq,
        _createdById,
        spooftable.parentterm,
        _cursorChildTerm,
        _ontologyRelationshipId
      FROM
        (SELECT parentterm
           FROM allpaths
          WHERE childterm = _newParentTerm
            AND ontologyRelationship = _ontologyRelationshipId
            -- Since a term can have multiple parents, a path to one of the new parent's parents may already exist
            AND parentterm NOT IN
              (SELECT parentterm
                 FROM allpaths
                 WHERE childTerm = _cursorChildTerm)
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

