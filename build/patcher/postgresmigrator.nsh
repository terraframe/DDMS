
# This function will perform a hard upgrade of postgres and postgis.
# $5 is the old postgres version
# If you add a new Postgres version make sure to also update PropertiesAgent.java in the restore manager.
Function upgradePostgresAndPostgis

  ClearErrors
  
  Call getPostgresVersion
  ${if} $5 == ${CURRENT_POSTGRES_VERSION}
    LogEx::Write "Skipping Postgres migration because we are already up to date."
    Return
  ${endif}
  
  CreateDirectory $INSTDIR\migrate
  !insertmacro MUI_HEADER_TEXT "Updating database software" "This one time operation may take a while..."

  ####################################
  # Backup All Application Databases #
  ####################################
  LogEx::Write "Backing up application databases."
  ClearErrors
  FileOpen $AppFile $INSTDIR\manager\manager-1.0.0\classes\applications.txt r
  
  UPPOSTGRESappNameFileReadLoop:
  # Read a line from the file into $1
  FileRead $AppFile $1
      
  # Errors means end of File
  IfErrors UPPOSTGRESappNameDone
      
  # Removes the newline from the end of $1
  ${StrTrimNewLines} $1 $1
  
  Push $1
  Pop $AppName
  ${StrCase} $LowerAppName $AppName "L"

  # Back it up
  ${if} $5 == "9.4"
    DetailPrint "Backing up $AppName"
    LogEx::Write "Backing up $AppName."
    push `"$INSTDIR\PostgreSql\9.4\bin\pg_dump.exe" -p 5444 -h 127.0.0.1 -U postgres -Fc -b -v -f "$INSTDIR\migrate\$LowerAppName.backup" $LowerAppName`
    Call execDos
  ${elseif} $5 == "9.1"
    DetailPrint "Backing up $AppName"
    LogEx::Write "Backing up $AppName."
    push `"$INSTDIR\PostgreSql\9.1\bin\pg_dump.exe" -p 5444 -h 127.0.0.1 -U postgres -Fc -b -v -f "$INSTDIR\migrate\$LowerAppName.backup" $LowerAppName`
    Call execDos
  ${endif}
  
  Goto UPPOSTGRESappNameFileReadLoop
          
  UPPOSTGRESappNameDone:
  ClearErrors
  FileClose $AppFile
  
  #############################
  # Migrate Database Software #
  #############################
  Call migrateDatabaseSoftware
  
  #################################################
  # Restore Application Databases On New Software #
  #################################################
  LogEx::Write "Restoring application databases."
  
  # Create the mdssdeploy role
  LogEx::Write "Creating the mdssdeploy role"
  push `"$INSTDIR\${POSTGRES_DIR}\bin\psql.exe" -p 5444 -h 127.0.0.1 -U postgres -d postgres -c "CREATE USER mdssdeploy ENCRYPTED PASSWORD 'mdssdeploy'"`
  Call execDos
  
  ClearErrors
  FileOpen $AppFile $INSTDIR\manager\manager-1.0.0\classes\applications.txt r
  
  UPPOSTGRES2appNameFileReadLoop:
  # Read a line from the file into $1
  FileRead $AppFile $1
      
  # Errors means end of File
  IfErrors UPPOSTGRES2appNameDone
      
  # Removes the newline from the end of $1
  ${StrTrimNewLines} $1 $1
  
  Push $1
  Pop $AppName
  ${StrCase} $LowerAppName $AppName "L"

  !insertmacro MUI_HEADER_TEXT "Restoring Application Databases" "Restoring $AppName"
  LogEx::Write "Restoring application databases."
  
  # Create a new db
  LogEx::Write "Creating the database"
  push `"$INSTDIR\${POSTGRES_DIR}\bin\psql.exe" -p 5444 -h 127.0.0.1 -U postgres -d postgres -c "CREATE DATABASE $LowerAppName WITH ENCODING='UTF8' TEMPLATE=template0 OWNER=mdssdeploy"`
  Call execDos 
  
  # Set the db search path
  LogEx::Write "Setting search path"
  push `"$INSTDIR\${POSTGRES_DIR}\bin\psql" -p 5444 -h 127.0.0.1 -U postgres -d postgres -c "ALTER DATABASE $LowerAppName SET search_path=ddms,public"`
  Call execDos
  
  # create extension postgis
  LogEx::Write "Installing PostGIS extension."
  push `"$INSTDIR\${POSTGRES_DIR}\bin\psql.exe" -p 5444 -h 127.0.0.1 -U postgres -d $LowerAppName -c "CREATE EXTENSION postgis"`
  Call execDos
  
  # create extension fuzzystrmatch
  push `"$INSTDIR\${POSTGRES_DIR}\bin\psql" -p 5444 -h 127.0.0.1 -U postgres -d $LowerAppName -c "CREATE EXTENSION IF NOT EXISTS fuzzystrmatch"`
  Call execDos
  
  # Import the app data into the new db
  LogEx::Write "Restoring $AppName database from $INSTDIR\migrate\$LowerAppName.backup."
  DetailPrint "Restoring $AppName database."
  SetOutPath $INSTDIR\migrate
  File ..\patch\postgis_restore.exe
  File ..\patch\postgis_restore_launcher.bat
  push `"$INSTDIR\migrate\postgis_restore_launcher.bat" $LowerAppName $INSTDIR`
  Call execDos
	
  # Update database.properties (databaseBinDirectory)
  LogEx::Write "Updating database.properties in [$INSTDIR\tomcat\webapps\$AppName\WEB-INF\classes\database.properties]"
  Push PostgreSQL/$5                                                                  # text to be replaced
  Push ${POSTGRES_DIR}                                                                 # replace with
  Push all                                                                             # replace all occurrences
  Push all                                                                             # replace all occurrences
  Push $INSTDIR\tomcat\webapps\$AppName\WEB-INF\classes\database.properties           # file to replace in
  Call AdvReplaceInFile
  
  Push PostgreSQL\$5                                                                  # text to be replaced
  Push ${POSTGRES_DIR}                                                                 # replace with
  Push all                                                                             # replace all occurrences
  Push all                                                                             # replace all occurrences
  Push $INSTDIR\tomcat\webapps\$AppName\WEB-INF\classes\database.properties           # file to replace in
  Call AdvReplaceInFile
  
  LogEx::Write "Updating database.properties in [$INSTDIR\manager\backup-manager-1.0.0\profiles\$AppName\database.properties]"
  Push PostgreSQL/$5                                                                  # text to be replaced
  Push ${POSTGRES_DIR}                                                                 # replace with
  Push all                                                                             # replace all occurrences
  Push all                                                                             # replace all occurrences
  Push $INSTDIR\manager\backup-manager-1.0.0\profiles\$AppName\database.properties     # file to replace in
  Call AdvReplaceInFile
  
  Push PostgreSQL\$5                                                                  # text to be replaced
  Push ${POSTGRES_DIR}                                                                 # replace with
  Push all                                                                             # replace all occurrences
  Push all                                                                             # replace all occurrences
  Push $INSTDIR\manager\backup-manager-1.0.0\profiles\$AppName\database.properties     # file to replace in
  Call AdvReplaceInFile

  Goto UPPOSTGRES2appNameFileReadLoop
          
  UPPOSTGRES2appNameDone:
  ClearErrors
  FileClose $AppFile
  
  RmDir /r "$INSTDIR\migrate"
FunctionEnd

Function getPostgresVersion
  IfFileExists $INSTDIR\PostgreSql\9.1\bin\psql.exe Yes91 No91
  Yes91:
    LogEx::Write "Current postgres version detected as 9.1."
    StrCpy $5 "9.1"
    Return
  No91:

  IfFileExists $INSTDIR\PostgreSql\9.4\bin\psql.exe Yes94 No94
  Yes94:
    LogEx::Write "Current postgres version detected as 9.4."
    StrCpy $5 "9.4"
    Return
  No94:
  
  IfFileExists $INSTDIR\PostgreSql\9.6\bin\psql.exe Yes96 No96
  Yes96:
    LogEx::Write "Current Postgres version detected as 9.6."
    StrCpy $5 "9.6"
    Return
  No96:
  
  LogEx::Write "Unable to determine the current Postgres version."
  StrCpy $JavaError 1
  Call JavaAbort
FunctionEnd

# This function uninstalls the old database software and installs the new software
Function migrateDatabaseSoftware
  push PostgreSql\$5
  Call stopPostgres

	# Uninstall old Postgres & PostGIS
	!insertmacro MUI_HEADER_TEXT "Updating Database Software" "Uninstalling old software"
  LogEx::Write "Uninstalling old software"
  ${If} $5 == "9.1"
	  push `"$INSTDIR\PostgreSql\9.1\uninstall-postgis-pg91-1.5.3-2.exe" /S`
	  Call execDos
  ${EndIf}
	
  push `"$INSTDIR\PostgreSql\$5\uninstall-postgresql.exe" --mode unattended`
	Call execDos
	
	RmDir /r "$INSTDIR\PostgreSql"
	
	!insertmacro MUI_HEADER_TEXT "Updating Database Software" "Installing PostgreSQL 9.6"
  LogEx::Write "Installing Postgres 9.6."
	SetOutPath $INSTDIR
  ${If} ${RunningX64}
	  File ..\installer-stage\postgresql-9.6.3-2-windows-x64.exe
	  push `"$INSTDIR\postgresql-9.6.3-2-windows-x64.exe" --mode unattended --serviceaccount ddmspostgres --servicepassword RQ42juEdxa3o --create_shortcuts 0 --prefix $INSTDIR\${POSTGRES_DIR} --datadir $INSTDIR\${POSTGRES_DIR}\data --superpassword CbyD6aTc54HA --serverport 5444 --locale "Arabic, Saudi Arabia"`
	  Call execDos
	${Else}
	  File ..\installer-stage\postgresql-9.6.3-2-windows.exe
	  push `"$INSTDIR\postgresql-9.6.3-2-windows.exe" --mode unattended --serviceaccount ddmspostgres --servicepassword RQ42juEdxa3o --create_shortcuts 0 --prefix $INSTDIR\${POSTGRES_DIR} --datadir $INSTDIR\${POSTGRES_DIR}\data --superpassword CbyD6aTc54HA --serverport 5444 --locale "Arabic, Saudi Arabia"`
	  Call execDos
	${EndIf}
	
	#MessageBox MB_OK|MB_ICONINFORMATION "Please run through the postgres installer and click OK here when finished."
	
	push ${POSTGRES_DIR}
  Call stopPostgres

  # Get the Windows Version (XP, Vista, etc.)
  nsisos::osversion
  
  LogEx::Write "Installing custom pg_hba.conf"
  # Version 5 means XP.  No IPv6
  ${If} $0 == 5
    File "/oname=$INSTDIR\${POSTGRES_DIR}\data\pg_hba.conf" "..\installer-stage\pg_hba_ipv4.conf"
  
  # Version 5 means Vista or Seven.  IPv6 enabled
  ${ElseIf} $0 == 6
    File "/oname=$INSTDIR\${POSTGRES_DIR}\data\pg_hba.conf" "..\installer-stage\pg_hba_ipv6.conf"
  
  # Who knows what version we're on.
  ${Else}
    LogEx::Write "ERROR: Unable to detect your windows version. DDMS is designed for Windows XP, Vista, or 7, and may not function properly on other platforms."
  MessageBox MB_OK|MB_ICONEXCLAMATION "Unable to detect your windows version. DDMS is designed for Windows XP, Vista, or 7, and may not function properly on other platforms." /SD IDOK
    File "/oname=$INSTDIR\${POSTGRES_DIR}\data\pg_hba.conf" "..\installer-stage\pg_hba_ipv6.conf"
  ${EndIf}
  
  # Copy the tweaked postgres config
  LogEx::Write "Installing custom postgresql.conf"
  File "/oname=$INSTDIR\${POSTGRES_DIR}\data\postgresql.conf" "..\installer-stage\postgresql.conf"
	
	Sleep 2000    
	LogEx::Write "The database is initializing. This may take a few minutes."
	DetailPrint "The database is initializing. This may take a few minutes."
	Call startPostgres
	
	# Install the latest PostGIS
	!insertmacro MUI_HEADER_TEXT "Updating Database Software" "Updating PostGIS"
    LogEx::Write "Updating PostGIS"
	CreateDirectory $INSTDIR\migrate\postgis
	${If} ${RunningX64}
	  SetOutPath "$INSTDIR\migrate\postgis"
	  File /r "..\installer-stage\postgis-bundle-pg96-2.3.2x64\*"
	  push `"$INSTDIR\migrate\postgis\makepostgisdb_using_extensions.bat"`
	  Call execDos
	${Else}
	  SetOutPath "$INSTDIR\migrate\postgis"
	  File /r "..\installer-stage\postgis-bundle-pg96-2.3.1x32\*"
	  push `"$INSTDIR\migrate\postgis\makepostgisdb_using_extensions.bat"`
	  Call execDos
	${EndIf}
	RmDir /r "$INSTDIR\migrate\postgis"
FunctionEnd
