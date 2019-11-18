DO
$do$
DECLARE
  _db TEXT := 'odk';
  _user TEXT := 'postgres';
  _password TEXT := 'postgres';
BEGIN
  CREATE EXTENSION IF NOT EXISTS dblink; -- enable extension 
  IF EXISTS (SELECT 1 FROM pg_database WHERE datname = _db) THEN
    RAISE NOTICE 'ODK database already exists';
  ELSE
    PERFORM dblink_connect('host=localhost user=' || _user || ' password=' || _password || ' port=5444 dbname=' || current_database());
    PERFORM dblink_exec('CREATE DATABASE ' || _db);
    RAISE NOTICE 'ODK database successfully created';
  END IF;
END
$do$