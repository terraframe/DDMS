
/*
 * Creates an id.  It generates its own sequence number to use in generating the id.
 * It also generates its own timestamp.
 */
CREATE OR REPLACE FUNCTION com_terraframe_mojo_createid
(
  _rootTypeId         VARCHAR,
  _random             BIGINT,
  _sitemaster         VARCHAR
)
RETURNS VARCHAR AS $$

DECLARE
  _seq                allpaths.seq%TYPE;
  _currentTimeMillis  TIMESTAMP;
  _id                 VARCHAR;

BEGIN

	SELECT NEXTVAL('object_sequence_unique_id') INTO _seq;
    SELECT NOW() INTO _currentTimeMillis;

	SELECT com_terraframe_mojo_createid
	  (_rootTypeId,
       _random,
       _sitemaster,
       _seq,
       _currentTimeMillis) INTO _id;

    RETURN _id;

END;
$$ LANGUAGE plpgsql;


/*
 * Creates an id.  It requires a sequence number and a timestamp.
 */
CREATE OR REPLACE FUNCTION com_terraframe_mojo_createid
(
  _rootTypeId         VARCHAR,
  _random             BIGINT,
  _sitemaster         VARCHAR,
  _seq                BIGINT,
  _currentTimeMillis  TIMESTAMP
)
RETURNS VARCHAR AS $$


DECLARE
  _idString           VARCHAR;
  _id                 VARCHAR;

BEGIN

    _idString := _sitemaster || ':' || _currentTimeMillis || ':' || _random || ':' || _seq;
    SELECT MD5(_idString) INTO _id;
    _id := _id || _rootTypeId;

    RETURN _id;

END;
$$ LANGUAGE plpgsql;