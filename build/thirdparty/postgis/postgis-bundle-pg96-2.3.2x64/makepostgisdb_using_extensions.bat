set PGPORT=5444
set PGHOST=localhost
set PGUSER=postgres
set PGPASSWORD=postgres
set THEDB=template_postgis
set PGINSTALL=C:\MDSS\PostgreSql\9.6
set PGADMIN=%PGINSTALL%\pgAdmin III
set PGBIN=%PGINSTALL%\bin\
set PGLIB=%PGINSTALL%\lib\
set POSTGISVER=2.3
xcopy bin\*.* "%PGBIN%"
xcopy /I /S bin\postgisgui\* "%PGBIN%\postgisgui"
xcopy /I plugins.d\* "%PGADMIN%\plugins.d"
xcopy lib\*.* "%PGLIB%"
xcopy share\extension\*.* "%PGINSTALL%\share\extension"
xcopy /I /S share\contrib\*.* "%PGINSTALL%\share\contrib"
xcopy /I gdal-data "%PGINSTALL%\gdal-data"
"%PGBIN%\psql"  -c "CREATE DATABASE %THEDB%"
"%PGBIN%\psql"  -d "%THEDB%" -c "CREATE EXTENSION postgis;"
"%PGBIN%\psql"  -d "%THEDB%" -c "CREATE EXTENSION postgis_sfcgal;"
"%PGBIN%\psql"  -d "%THEDB%" -c "CREATE EXTENSION postgis_topology;"
"%PGBIN%\psql"  -d "%THEDB%" -c "CREATE EXTENSION address_standardizer;"
"%PGBIN%\psql"  -d "%THEDB%" -c "CREATE EXTENSION address_standardizer_data_us;"
"%PGBIN%\psql"  -d "%THEDB%" -c "CREATE EXTENSION fuzzystrmatch;"
"%PGBIN%\psql"  -d "%THEDB%" -c "CREATE EXTENSION postgis_tiger_geocoder;"

"%PGBIN%\psql" -d "%THEDB%" -c "UPDATE pg_database SET datistemplate = true WHERE datname = '%THEDB%';GRANT ALL ON geometry_columns TO PUBLIC; GRANT ALL ON spatial_ref_sys TO PUBLIC"
