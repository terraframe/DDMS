set PGPORT=5444
set PGHOST=localhost
set PGUSER=postgres
set PGPASSWORD=postgres
set THEDB=template_postgis
set PGINSTALL=C:\MDSS\PostgreSql\9.4
set PGADMIN=%PGINSTALL%\pgAdmin III
set PGBIN=%PGINSTALL%\bin\
set PGLIB=%PGINSTALL%\lib\
set POSTGISVER=2.1
xcopy /y /i bin\*.* "%PGBIN%"
xcopy /y /i /I /S bin\postgisgui\* "%PGBIN%\postgisgui"
xcopy /y /i /I /S share\contrib\postgis-%POSTGISVER% "%PGINSTALL%\share\contrib\postgis-%POSTGISVER%"
xcopy /y /i /I plugins.d\* "%PGADMIN%\plugins.d"
xcopy /y /i lib\*.* "%PGLIB%"
xcopy /y /i /I gdal-data "%PGINSTALL%\gdal-data"
"%PGBIN%\psql"  -c "CREATE DATABASE %THEDB%"
"%PGBIN%\psql"  -d "%THEDB%" -c "CREATE EXTENSION postgis;"

"%PGBIN%\psql" -d "%THEDB%" -c "UPDATE pg_database SET datistemplate = true WHERE datname = '%THEDB%';GRANT ALL ON geometry_columns TO PUBLIC; GRANT ALL ON spatial_ref_sys TO PUBLIC"
