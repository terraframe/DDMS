
/*
 * Creates an id.  It requires a sequence number and a timestamp.
 */
CREATE OR REPLACE FUNCTION com_terraframe_mojo_createid
(
  _root_Type_Id         VARCHAR,
  _random             BIGINT,
  _site_master         VARCHAR,
  _seq                BIGINT,
  _current_Time_Millis  TIMESTAMP
)
RETURNS VARCHAR AS $$


DECLARE
  _id_String           VARCHAR;
  _id                 VARCHAR;

BEGIN

    _idString := _site_master || ':' || _current_Time_Millis || ':' || _random || ':' || _seq;
    SELECT MD5(_id_String) INTO _id;
    _id := _id || _root_Type_Id;

    RETURN _id;

END;
$$ LANGUAGE plpgsql;