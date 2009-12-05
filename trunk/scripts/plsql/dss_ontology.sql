/*
SELECT dss_ontology_build_allpaths('z6n4nserbewij67if9ykzs010471b6v6', 1234, 'www.mdss.com', '0000000000000000000000000000001000000000000000000000000000000003', LOCALTIMESTAMP, 'swvpyeupgazp93x943y5xcvnjte24rjy1svxgm8h0q3cgvy8nvi1941cl9l5ekyb');
*/

CREATE OR REPLACE FUNCTION dss_ontology_build_allpaths
(
  _allPathsRootTypeId     VARCHAR,
  _random                 BIGINT,
  _sitemaster             allpaths_ontology.sitemaster%TYPE,
  _createdById            allpaths_ontology.id%TYPE,
  _transactionDate        allpaths_ontology.createdate%TYPE,
  _ontologyRelationshipId allpaths_ontology.ontologyrelationship%TYPE
)
RETURNS VOID AS $$

DECLARE
  _termCursor  CURSOR (c_ontologyRelationshipId allpaths_ontology.ontologyrelationship%TYPE) FOR
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
  _seq       allpaths_ontology.seq%TYPE;

BEGIN

  _seq := 0;

  OPEN _termCursor(_ontologyRelationshipId);

  LOOP
    FETCH _termCursor INTO _parentId, _childId;

    EXIT WHEN NOT FOUND;

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
  _sitemaster           allpaths_ontology.sitemaster%TYPE,
  _createdById          allpaths_ontology.id%TYPE,
  _transactionDate      allpaths_ontology.createdate%TYPE,
  _parentTerm           allpaths_ontology.parentterm%TYPE,
  _childTerm            allpaths_ontology.childterm%TYPE,
  _ontologyRelationshipId allpaths_ontology.ontologyrelationship%TYPE,
  _seq                  allpaths_ontology.seq%TYPE
)
RETURNS VOID AS $$


DECLARE
  _id                 allpaths_ontology.id%TYPE;
  _idString           VARCHAR;
  _currentTimeMillis  TIMESTAMP;

BEGIN

	SELECT com_terraframe_mojo_createid
	  (_allPathsRootTypeId,
       _random,
       _sitemaster,
       _seq,
       _transactionDate) INTO _id;

    INSERT INTO allpaths_ontology
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
'05brck3zaer5w5vbcee16ry28lzao1e3xeklfgy3bklheiiulqszhfay163qlpta',
'zbdda5r3mmkkwyuhla67ymkn551jmtpnxeklfgy3bklheiiulqszhfay163qlpta',
'05kt1jddumk5qm8juvcxseyist5klwhrzm1g3udb394gw9tn8ca9qvefl9ncyubd'
);


mdssdevelop=> select id from term where termid = 'IDOMAL:0000047';
                                id
------------------------------------------------------------------
 05brck3zaer5w5vbcee16ry28lzao1e3xeklfgy3bklheiiulqszhfay163qlpta
(1 row)

mdssdevelop=> select id from term where termid = 'IDOMAL:0000049';
                                id
------------------------------------------------------------------
 zbdda5r3mmkkwyuhla67ymkn551jmtpnxeklfgy3bklheiiulqszhfay163qlpta



*/

CREATE OR REPLACE FUNCTION dss_ontology_copy_term
(
  _allPathsRootTypeId     VARCHAR,
  _random                 BIGINT,
  _sitemaster             allpaths_ontology.sitemaster%TYPE,
  _createdById            allpaths_ontology.id%TYPE,
  _transactionDate        allpaths_ontology.createdate%TYPE,
  _newParentTerm          allpaths_ontology.parentterm%TYPE,
  _childTerm              allpaths_ontology.childterm%TYPE,
  _ontologyRelationshipId allpaths_ontology.ontologyrelationship%TYPE
)
RETURNS VOID AS $$

DECLARE

BEGIN

  -- For each child term, copy all of the paths of the new parent
    INSERT INTO allpaths_ontology
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
        MD5(allpaths_parent.parentterm || allpaths_child.childterm) || _allPathsRootTypeId AS newId,
      	_sitemaster,
        MD5(allpaths_parent.parentterm || allpaths_child.childterm) || _allPathsRootTypeId AS newKey,
    	_createdById,
        _transactionDate,
    	'dss.vector.solutions.ontology.AllPaths',
    	_transactionDate,
        _createdById,
        NEXTVAL('object_sequence_unique_id') as seq,
        _createdById,
        allpaths_parent.parentterm,
        allpaths_child.childterm,
        _ontologyRelationshipId
      FROM
        -- Fech all of the recursive children of the given child term, including the child term itself.
        (SELECT childterm
  	       FROM allpaths_ontology
  	      WHERE parentterm = _childTerm
  	        AND ontologyRelationship = _ontologyRelationshipId) AS allpaths_child,
        -- Fech all of the recursive parents of the given new parent term, including the new parent term itself.
        (SELECT parentterm
           FROM allpaths_ontology
          WHERE childterm = _newParentTerm
            AND ontologyRelationship = _ontologyRelationshipId
        ) AS allpaths_parent
        -- Since a term can have multiple parents, a path to one of the new parent's parents may already exist
        WHERE allpaths_parent.parentterm NOT IN
         (SELECT parentterm
           FROM allpaths_ontology
          WHERE parentterm = allpaths_parent.parentterm
            AND childTerm = allpaths_child.childterm
            AND ontologyRelationship = _ontologyRelationshipId);
--        AND allpaths_child.childterm NOT IN
--         (SELECT childterm
--           FROM allpaths_ontology
--          WHERE parentterm = allpaths_parent.parentterm
--            AND childTerm = allpaths_child.childterm
--            AND ontologyRelationship = _ontologyRelationshipId);

END;
$$ LANGUAGE plpgsql;



/*

SELECT pg_attribute.attname
FROM pg_attribute, pg_class
WHERE pg_class.relname = 'asejnv791i8m4q2wsnvgs0rl49tezi'
AND pg_attribute.attrelid = pg_class.oid;

 */

