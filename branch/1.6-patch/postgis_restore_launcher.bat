setlocal
SET PATH=%PATH%;%2\PostgreSql\9.4\bin
"%2\migrate\postgis_restore.exe" "%2\migrate\%1.backup" | psql -h localhost -p 5444 -U postgres %1 2> %2\patch\output\pgis_restore_errors.txt