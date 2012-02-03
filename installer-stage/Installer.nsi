# Auto-generated by EclipseNSIS Script Wizard
# Mar 11, 2010 4:03:45 PM

Name DDMS

# General Symbol Definitions
!define REGKEY "SOFTWARE\$(^Name)"
!define VERSION 2.0
!define COMPANY "Innovative Vector Control Consortium"
!define URL "http://www.ivcc.com/"

# MUI Symbol Definitions
!define MUI_ICON "ivcc_roundel_1.ico"
!define MUI_FINISHPAGE_NOAUTOCLOSE
!define MUI_STARTMENUPAGE_REGISTRY_ROOT HKLM
!define MUI_STARTMENUPAGE_NODISABLE
!define MUI_STARTMENUPAGE_REGISTRY_KEY ${REGKEY}
!define MUI_STARTMENUPAGE_REGISTRY_VALUENAME StartMenuGroup
!define MUI_STARTMENUPAGE_DEFAULTFOLDER DDMS
!define MUI_UNICON "ivcc_roundel_1.ico"
!define MUI_UNFINISHPAGE_NOAUTOCLOSE

# Included files
!include Sections.nsh
!include MUI2.nsh
!include nsDialogs.nsh
!include LogicLib.nsh
!include FileFunc.nsh

# Define access to the RIndexOf function
!macro RIndexOf Var Str Char
Push "${Char}"
Push "${Str}"
 Call RIndexOf
Pop "${Var}"
!macroend
!define RIndexOf "!insertmacro RIndexOf"

# Define access to the CharStrip function
!macro CharStrip Char InStr OutVar
 Push '${InStr}'
 Push '${Char}'
  Call CharStrip
 Pop '${OutVar}'
!macroend
!define CharStrip '!insertmacro CharStrip'

# Define access to the StrTrimNewLines function
!macro StrTrimNewLines ResultVar String
  Push "${String}"
  Call StrTrimNewLines
  Pop "${ResultVar}"
!macroend
!define StrTrimNewLines "!insertmacro StrTrimNewLines"

# Define access to the StrCase function
!macro StrCase ResultVar String Case
  Push "${String}"
  Push "${Case}"
  Call StrCase
  Pop "${ResultVar}"
!macroend
!define StrCase "!insertmacro StrCase"

# Variables
Var TfDialog
Var Label
Var Text
Var InstallationNumber
Var Params
Var Master_Value
Var FPath
Var FVersion
Var PatchVersion
Var TermsVersion
Var RootsVersion
Var MenuVersion
Var LocalizationVersion
Var PermissionsVersion
Var AppName
Var LowerAppName

# Installer pages
!insertmacro MUI_PAGE_WELCOME
Page custom appNameInputPage appNameUniquenessCheck
Page custom userInputPage exitUserInputPage
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES
!insertmacro MUI_UNPAGE_FINISH

# Installer languages
!insertmacro MUI_LANGUAGE English

# Installer attributes
OutFile InstallDDMS.exe
InstallDir C:\MDSS
CRCCheck on
XPStyle on
ShowInstDetails show
VIProductVersion 2.0.0.0
VIAddVersionKey ProductName DDMS
VIAddVersionKey ProductVersion "${VERSION}"
VIAddVersionKey FileVersion "${VERSION}"
VIAddVersionKey FileDescription ""
VIAddVersionKey LegalCopyright ""
InstallDirRegKey HKLM "${REGKEY}" Path
ShowUninstDetails show
RequestExecutionLevel admin

Function userInputPage
  StrCmp  $Master_Value "true" 0 +2
    Return  
  !insertmacro MUI_HEADER_TEXT "Installation Number" "Specify the installation number"
  nsDialogs::Create 1018
  Pop $TfDialog
  
  ${If} $TfDialog == error
    Abort
  ${EndIf}
  
  # Create the label, which gets put on the stack
  ${NSD_CreateLabel} 0 2u 25% 12u "Installation Number"
  # Pop the label off the stack and store it in $Label
  Pop $Label
  
  ${NSD_CreateText} 25% 0 74% 12u $InstallationNumber
  Pop $Text
  # Set up the number validator
  ${NSD_OnChange} $Text verifyNumber
  
  ${NSD_CreateHLine} 0 18u 100% 2 "HLine"
  Pop $Label
  
  # Create the label, which gets put on the stack
  ${NSD_CreateLabel} 0 23u 100% 32u "The installation number must be unique!  If any other installation shares the same number, synchronization will fail and you will not be able to share or receive any data.  The number should be between 1 and 999.  If you're not sure which installation number to use, please consult the IT professional that provided your install disc."
  # Pop the label off the stack and store it in $Label
  Pop $Label
  
  nsDialogs::Show
FunctionEnd

# Enforces an Installation number between 1 and 999
Function verifyNumber
  Pop $1 # $1 == $ Text
  
  ${NSD_GetText} $Text $0
  ${If} $0 > 999
    ${NSD_SetText} $Text 999
  ${EndIf}
  ${If} $0 < 1
    ${NSD_SetText} $Text 1
  ${EndIf}
FunctionEnd

Function exitUserInputPage
 StrCmp  $Master_Value "true" 0 +2
    Return  
  # Pull the text out of the form element and store it in $InstallationNumber
  ${NSD_GetText} $Text $InstallationNumber
FunctionEnd

Function appNameInputPage
  !insertmacro MUI_HEADER_TEXT "Installation Name" "Specify the installation name"
  nsDialogs::Create 1018
  Pop $TfDialog
  
  ${If} $TfDialog == error
    Abort
  ${EndIf}
  
  # Create the label, which gets put on the stack
  ${NSD_CreateLabel} 0 2u 25% 12u "Installation Name"
  # Pop the label off the stack and store it in $Label
  Pop $Label
  
  ${NSD_CreateText} 25% 0 74% 12u $AppName
  Pop $Text
  # Set up the number validator
  ${NSD_OnChange} $Text sanitizeName
  
  ${NSD_CreateHLine} 0 18u 100% 2 "HLine"
  Pop $Label
  
  # Create the label, which gets put on the stack
  ${NSD_CreateLabel} 0 23u 100% 32u "The installation name must be unique, and can only contain URL characters: alpha, numbers, - and _"
  # Pop the label off the stack and store it in $Label
  Pop $Label
  
  nsDialogs::Show
FunctionEnd

Function appNameUniquenessCheck
    ClearErrors
    FileOpen $0 $INSTDIR\manager\manager-1.0.0\classes\applications.txt r
    
    appNameFileReadLoop:
    # Read a line from the file into $1
    FileRead $0 $1
    # Errors means end of File
    IfErrors appNameDone
    
    # Removes the newline from the end of $1
    ${StrTrimNewLines} $1 $1
    
    StrCmp $AppName $1 appNameCollision appNameFileReadLoop
    
    appNameCollision:
    MessageBox MB_OK "$1 already exists.  Please choose another name."
	FileClose $0
    Abort
    
    appNameDone:
    ClearErrors
	FileClose $0
FunctionEnd

Function sanitizeName
  Pop $1 # $1 == $ Text
  
  ${NSD_GetText} $Text $0
  StrCpy $AppName $0
  ${CharStrip} "/" $0 $0
  ${CharStrip} "\" $0 $0
  ${CharStrip} "*" $0 $0
  ${CharStrip} "&" $0 $0
  ${CharStrip} '"' $0 $0
  ${CharStrip} ":" $0 $0
  ${CharStrip} "<" $0 $0
  ${CharStrip} ">" $0 $0
  ${CharStrip} "?" $0 $0
  ${CharStrip} "$$" $0 $0
  ${CharStrip} "+" $0 $0
  ${CharStrip} "," $0 $0
  ${CharStrip} ";" $0 $0
  ${CharStrip} "=" $0 $0
  ${CharStrip} "@" $0 $0
  ${CharStrip} " " $0 $0
  ${CharStrip} "#" $0 $0
  ${CharStrip} "%" $0 $0
  ${CharStrip} "[" $0 $0
  ${CharStrip} "]" $0 $0
  ${CharStrip} "{" $0 $0
  ${CharStrip} "}" $0 $0
  ${CharStrip} "^" $0 $0
  ${CharStrip} "~" $0 $0
  ${CharStrip} "`" $0 $0
  ${CharStrip} "." $0 $0
  
  StrCmp $AppName $0 +2
  
  ${NSD_SetText} $Text $0
  
FunctionEnd

Function stopPostgres
  LogEx::Write "Stopping PostgreSQL"
  ExecWait `$INSTDIR\PostgreSql\9.1\bin\pg_ctl.exe stop -D $INSTDIR\PostgreSql\9.1\data` 

  # Wait until postgres is stopped
  StrCpy $0 0
  
  PostgresUp:
    # Sleep 2 seconds
    Sleep 5000	
	
	# Increment the timeout counter
	IntOp $0 $0 + 1
	
	# Check to make sure the timeout hasn't expired
	${If} $0 > 50
  	# Goto PostgresDown
    LogEx::Write "PostgreSQL failed to stop."
	  MessageBox MB_OK "Postgres failed to stop." 
	  #Abort
    ${EndIf}	
	
	IfFileExists $INSTDIR\PostgreSql\9.1\data\postmaster.pid PostgresUp PostgresDown
  PostgresDown:
FunctionEnd

Function startPostgres
  LogEx::Write "Starting PostgreSQL"
  ExecWait `$INSTDIR\PostgreSql\9.1\bin\pg_ctl.exe start -D $INSTDIR\PostgreSql\9.1\data` 

  # Wait until postgres is stopped
  StrCpy $0 0
  
  PostgresUp:
    # Sleep 2 seconds
    Sleep 5000	
	
	# Increment the timeout counter
	IntOp $0 $0 + 1
	
	# Check to make sure the timeout hasn't expired
	${If} $0 > 50
	  Goto PostgresDown
    LogEx::Write "PostgreSQL failed to start."
	  MessageBox MB_OK "Postgres failed to start." 
	  #Abort
    ${EndIf}	
	
	IfFileExists $INSTDIR\PostgreSql\9.1\data\postmaster.pid PostgresDown PostgresUp
  PostgresDown:
FunctionEnd


# Installer sections
Section -Main SEC0000
    SetOutPath $INSTDIR
    
    # These version numbers are automatically regexed by ant
    StrCpy $PatchVersion 6755
    StrCpy $TermsVersion 6644
    StrCpy $RootsVersion 5432
    StrCpy $MenuVersion 6655
    StrCpy $LocalizationVersion 6734
    StrCpy $PermissionsVersion 6743
    
    LogEx::Init "$INSTDIR\installer-log.txt"
    StrCmp $Master_Value "true" +1 +2
    LogEx::Write "Beginning MASTER install named $AppName"
    LogEx::Write "Beginning dependent install named $AppName"
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Searching for Firefox"
    Call findFireFox
    StrCmp $FPath "" installFireFox doneInstallFireFox
    installFireFox:
      LogEx::Write "Installing Firefox 8.0.1"
      !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Firefox"
      File "Firefox Setup 8.0.1.exe"
      ExecWait `"$INSTDIR\Firefox Setup 8.0.1.exe"`
      Call findFireFox
    doneInstallFireFox:
      
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Verifying Firefox Installation"
    StrCmp $FPath "" fireFoxNotFound fireFoxFound
    fireFoxNotFound:
      LogEx::Write "Firefox 8.0.1 was not found."
      MessageBox MB_OK "Could not find FireFox.  Please install again and ensure that FireFox installs correctly"
      Abort
    
    fireFoxFound:
    #Determine if this is a full install or just another app
    ReadRegStr $0 HKLM "${REGKEY}\Components" Main
    StrCmp $0 "" 0 appInstall
    LogEx::Write "Firefox 8.0.1 is installed."
    
    # Force firefox to open up, just in case it has been freshly installed, so that the first-time setup can finish before we isntall the screengrab plugin
#    SetOutPath $INSTDIR
#    File closeme.html
#    File logo.gif
#	ExecWait `"$FPath\firefox.exe" "$INSTDIR\closeme.html"`
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Qcal"
    LogEx::Write "Installing Qcal"
    SetOutPath $INSTDIR\IRMA
    File /r /x .svn IRMA\*
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing BIRT"
    LogEx::Write "Installing BIRT"
    SetOutPath $INSTDIR\birt
    File /r /x .svn birt\*
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing DDMS Managers"
    LogEx::Write "Installing DDMS Managers"
    SetOutPath $INSTDIR\manager
    File ..\standalone\patch\manager.bat
    File ..\standalone\patch\manager.ico	
    SetOutPath $INSTDIR\manager\backup-manager-1.0.0
    File /r /x .svn ..\standalone\backup-manager-1.0.0\*
    SetOutPath $INSTDIR\manager\ddms-initializer-1.0.0
    File /r /x .svn ..\standalone\ddms-initializer-1.0.0\*
    SetOutPath $INSTDIR\manager\geo-manager-1.0.0
    File /r /x .svn ..\standalone\geo-manager-1.0.0\*
    SetOutPath $INSTDIR\manager\manager-1.0.0
    File /r /x .svn ..\standalone\manager-1.0.0\*
    SetOutPath $INSTDIR\manager\synch-manager-1.0.0
    File /r /x .svn ..\standalone\synch-manager-1.0.0\*
    SetOutPath $INSTDIR\manager\keystore
    File /r /x .svn ..\standalone\doc\keystore\*
    
    # Add the special elevation command for backup/restore
    LogEx::Write "Adding special elevation command for backup/restore"
    SetOutPath $INSTDIR
    File elevate.cmd
    File elevate.vbs
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Java"
    LogEx::Write "Installing Java"
    SetOutPath $INSTDIR\Java
    File /r /x .svn Java\*
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Copying docs"
    LogEx::Write "Copying docs"
    SetOutPath $INSTDIR\doc
    File /r /x .svn doc\*
    
    # To accomodate multi-installs, this does not include the user-named webapp, which is instead copied later
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Tomcat"
    LogEx::Write "Installing Tomcat"
    SetOutPath $INSTDIR\tomcat6
    File /r /x .svn tomcat6\*
    SetOutPath $INSTDIR
        
    # Install Postgres
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing PostgreSQL"
    LogEx::Write "Installing PostgreSQL"
    File "postgresql-9.1.2-1-windows.exe"
    ExecWait `"$INSTDIR\postgresql-9.1.2-1-windows.exe" --mode unattended --serviceaccount ddmspostgres --servicepassword RQ42juEdxa3o --create_shortcuts 0 --prefix C:\MDSS\PostgreSql\9.1 --datadir C:\MDSS\PostgreSql\9.1\data --superpassword CbyD6aTc54HA --serverport 5444 --locale en`
    #IfErrors PostgresInstallError PostgressInstallSuccess
	#IfFileExists C:\MDSS\PostgreSql\9.1\data\postmaster.pid PostgressInstallSuccess PostgresInstallError

	#PostgresInstallError:
	#MessageBox MB_OK "Postgres failed to install correctly" 
	#Abort
	
	#PostgressInstallSuccess:
    Call stopPostgres
	
    # Get the Windows Version (XP, Vista, etc.)
    nsisos::osversion
    
    LogEx::Write "Installing custom pg_hba.conf"
    # Version 5 means XP.  No IPv6
    ${If} $0 == 5
      File "/oname=C:\MDSS\PostgreSql\9.1\data\pg_hba.conf" "pg_hba_ipv4.conf"
    
    # Version 5 means Vista or Seven.  IPv6 enabled
    ${ElseIf} $0 == 6
      File "/oname=C:\MDSS\PostgreSql\9.1\data\pg_hba.conf" "pg_hba_ipv6.conf"
    
    # Who knows what version we're on.
    ${Else}
      MessageBox MB_OK "Unable to detect your windows version. DDMS is designed for Windows XP, Vista, or 7, and may not function properly on other platforms."
      File "/oname=C:\MDSS\PostgreSql\9.1\data\pg_hba.conf" "pg_hba_ipv6.conf"
    ${EndIf}
    
    # Copy the tweaked postgres config
    LogEx::Write "Installing custom postgresql.conf"
    File "/oname=C:\MDSS\PostgreSql\9.1\data\postgresql.conf" "postgresql.conf"

	Sleep 2000    
	Call startPostgres
    
    # Install PostGIS
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing PostGIS"
    LogEx::Write "Installing PostGIS"
    File "postgis-pg91-setup-1.5.3-2.exe"
    ExecWait `"$INSTDIR\postgis-pg91-setup-1.5.3-2.exe" /S`
    
    # We jump to this point if only installing a new app
    appInstall:
    
    # Copy the webapp in the correct folder
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Tomcat"
    LogEx::Write "Copying the webapp to $INSTDIR\tomcat6\webapps\$AppName"
    SetOutPath $INSTDIR\tomcat6\webapps\$AppName
    File /r /x .svn webapp\*
    SetOutPath $INSTDIR
    
    # Create the database
    ${StrCase} $LowerAppName $AppName "L"
    LogEx::Write "Creating the database"
    ExecWait `"C:\MDSS\PostgreSql\9.1\bin\psql" -p 5444 -h 127.0.0.1 -U postgres -d postgres -c "CREATE USER mdssdeploy ENCRYPTED PASSWORD 'mdssdeploy'"`
    ExecWait `"C:\MDSS\PostgreSql\9.1\bin\psql" -p 5444 -h 127.0.0.1 -U postgres -d postgres -c "CREATE DATABASE $LowerAppName WITH ENCODING='UTF8' TEMPLATE=template0 LC_COLLATE='C' LC_CTYPE='C' OWNER=mdssdeploy"`
    
    # Restore the db from the dump file
    # pg_dump.exe -b -f C:\stage\mdss.backup -F p -U postgres mdssdeploy
    LogEx::Write "Restoring the database from dump file"
    File "mdss.backup"
    ExecWait `"C:\MDSS\PostgreSql\9.1\bin\psql" -U postgres -d $LowerAppName -p 5444 -h 127.0.0.1 -f C:\MDSS\mdss.backup`

    # Update the installation number
    LogEx::Write "Updating the installation number"
    ExecWait `"C:\MDSS\PostgreSql\9.1\bin\psql" -U mdssdeploy -d $LowerAppName -p 5444 -h 127.0.0.1 -c "update local_property set property_value='$InstallationNumber' where property_name='SHORT_ID_OFFSET'"`
    
    # Ports 5444-5452 and 8149-8159 available
    # takeown /f C:\MDSS\PostgreSql /r /d y
    # icalcs C:\MDSS\PostgreSql /grant administrators:F /t
    
    # Update lots of things	
	  ClearErrors
    LogEx::Write "Executing Post Install Setup Java"
    ExecWait `$INSTDIR\Java\jdk1.6.0_16\bin\java.exe -cp "C:\MDSS\tomcat6\webapps\$AppName\WEB-INF\classes;C:\MDSS\tomcat6\webapps\$AppName\WEB-INF\lib\*" dss.vector.solutions.util.PostInstallSetup -a$AppName -n$InstallationNumber -i$Master_Value`
	LogEx::AddFile "   >" "$INSTDIR\PostInstallSetup.log"
	delete $INSTDIR\PostInstallSetup.log
    IfErrors postInstallError skipErrorMsg
	
	postInstallError:
    LogEx::Write "Post Install Setup Failed"
	MessageBox MB_OK "Post install process failed." 
	ClearErrors
	
	skipErrorMsg:
	
    # Copy the profile to the backup manager
    LogEx::Write "Copying profile to backup manager"
    CreateDirectory $INSTDIR\manager\backup-manager-1.0.0\profiles\$AppName
    CopyFiles /FILESONLY $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\*.* $INSTDIR\manager\backup-manager-1.0.0\profiles\$AppName
    
    # Copy in the pregenerated cache files
    LogEx::Write "Copying over pregenerated cache files"
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Copying cache files"
    File /oname=$INSTDIR\tomcat6\$AppName.data DDMS.data
    File /oname=$INSTDIR\tomcat6\$AppName.index DDMS.index
    
    LogEx::Write "Writing version numbers to registry"
    WriteRegStr HKLM "${REGKEY}\Components" Main 1
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" App $PatchVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Terms $TermsVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Roots $RootsVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Menu $MenuVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Localization $LocalizationVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Permissions $PermissionsVersion
    WriteRegStr HKLM "${REGKEY}\Components" Manager 1
    WriteRegStr HKLM "${REGKEY}\Components" Runway 1
    
    # Write some shortcuts
    LogEx::Write "Creating shortcuts"
    SetShellVarContext all
    CreateDirectory $SMPROGRAMS\DDMS
    SetOutPath $FPath
    CreateShortcut "$SMPROGRAMS\DDMS\Open $AppName.lnk" "$FPath\firefox.exe" "http://127.0.0.1:8080/$AppName/"
    SetOutPath $INSTDIR\birt
    CreateShortcut "$SMPROGRAMS\DDMS\BIRT.lnk" "$INSTDIR\birt\BIRT.exe"
    SetOutPath $INSTDIR\IRMA
    CreateShortcut "$SMPROGRAMS\DDMS\Qcal.lnk" "$INSTDIR\IRMA\Qcal.exe"
    SetOutPath $INSTDIR
    CreateShortcut "$SMPROGRAMS\DDMS\Uninstall $(^Name).lnk" "$INSTDIR\uninstall.exe"
    SetOutPath $INSTDIR\manager
    CreateShortcut "$SMPROGRAMS\DDMS\Manager.lnk" "$INSTDIR\manager\manager.bat" "" "$INSTDIR\manager\manager.ico" 0 "" "" "Start DDMS mananger"
	LogEx::Write "Installation complete."
	LogEx::Close
SectionEnd

Section -post SEC0001
    WriteRegStr HKLM "${REGKEY}" Path $INSTDIR
    SetOutPath $INSTDIR
    WriteUninstaller $INSTDIR\uninstall.exe
    RmDir /r /REBOOTOK "$SMPROGRAMS\PostGIS 1.5 for PostgreSQL 9.1"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayName "$(^Name)"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayVersion "${VERSION}"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayIcon $INSTDIR\uninstall.exe
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" UninstallString $INSTDIR\uninstall.exe
    WriteRegDWORD HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" NoModify 1
    WriteRegDWORD HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" NoRepair 1
SectionEnd

# Macro for selecting uninstaller sections
!macro SELECT_UNSECTION SECTION_NAME UNSECTION_ID
    Push $R0
    ReadRegStr $R0 HKLM "${REGKEY}\Components" "${SECTION_NAME}"
    StrCmp $R0 1 0 next${UNSECTION_ID}
    !insertmacro SelectSection "${UNSECTION_ID}"
    GoTo done${UNSECTION_ID}
next${UNSECTION_ID}:
    !insertmacro UnselectSection "${UNSECTION_ID}"
done${UNSECTION_ID}:
    Pop $R0
!macroend

# Uninstaller sections
Section /o -un.Main UNSEC0000
    CreateDirectory $DESKTOP\temp_uninstall_files
    CopyFiles $INSTDIR\PostgreSQL\9.1\uninstall*.exe $DESKTOP\temp_uninstall_files
    RmDir /r /REBOOTOK $INSTDIR
    DeleteRegValue HKLM "${REGKEY}\Components" Main
    DeleteRegValue HKLM "${REGKEY}\Components\$AppName" App
    DeleteRegValue HKLM "${REGKEY}\Components\$AppName" Terms
    DeleteRegValue HKLM "${REGKEY}\Components\$AppName" Roots
    DeleteRegValue HKLM "${REGKEY}\Components\$AppName" Menu
    DeleteRegValue HKLM "${REGKEY}\Components\$AppName" Localization
    DeleteRegValue HKLM "${REGKEY}\Components\$AppName" Permissions
    DeleteRegValue HKLM "${REGKEY}\Components" Manager
    DeleteRegValue HKLM "${REGKEY}\Components" Runway
    ExecWait $DESKTOP\temp_uninstall_files\uninstall-postgis-pg91-1.5.3-2.exe
    ExecWait $DESKTOP\temp_uninstall_files\uninstall-postgresql.exe
    RmDir /r /REBOOTOK $DESKTOP\temp_uninstall_files
SectionEnd

Section -un.post UNSEC0001
    DeleteRegKey HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)"
    Delete /REBOOTOK "$SMPROGRAMS\DDMS\Open $AppName.lnk"
    Delete /REBOOTOK "$SMPROGRAMS\DDMS\BIRT.lnk"
    Delete /REBOOTOK "$SMPROGRAMS\DDMS\Qcal.lnk"
    Delete /REBOOTOK "$SMPROGRAMS\DDMS\Manager.lnk"
    Delete /REBOOTOK "$SMPROGRAMS\DDMS\Uninstall $(^Name).lnk"
    Delete /REBOOTOK $INSTDIR\uninstall.exe
    DeleteRegValue HKLM "${REGKEY}" StartMenuGroup
    DeleteRegValue HKLM "${REGKEY}" Path
    DeleteRegKey HKLM "${REGKEY}\Components"
    DeleteRegKey HKLM "${REGKEY}"
    SetShellVarContext all
    RmDir /r /REBOOTOK $SMPROGRAMS\DDMS
    RmDir /REBOOTOK $INSTDIR
SectionEnd

# Installer functions
Function .onInit
    # LogSet On
    SetOverwrite try
    InitPluginsDir
    SetRebootFlag true
    # Initialize the value of the text string
    StrCpy $InstallationNumber "1"
    StrCpy $AppName "Name"
    StrCpy $Master_Value "init"
    
    # Read the command-line parameters
    ${GetParameters} $Params
    # Check for the presence of the -master flag
    ${GetOptions} "$Params" "-master" $R0
 
	IfErrors masterFalse masterTrue
   masterFalse:
      StrCpy $Master_Value "false"
      Goto masterDone
    masterTrue:
      StrCpy $Master_Value "true"
      StrCpy $InstallationNumber "0"
    masterDone:
      ClearErrors
    
FunctionEnd

# Finds the firefox executable by checking an assortment of registry keys and stores the path in $FPath
Function findFireFox
    LogEx::Write "Searching for Firefox"
    ReadRegStr $FPath HKCR "firefoxhtml\defaulticon" ""
    call StripPath
    StrCmp $FPath "" +1 FDone
    
    ReadRegStr $FPath HKCR "firefoxurl\defaulticon" ""
    call StripPath
    StrCmp $FPath "" +1 FDone
    
    ReadRegStr $FPath HKLM "Software\classes\firefoxhtml\defaulticon" ""
    call StripPath
    StrCmp $FPath "" +1 FDone
    
    ReadRegStr $FPath HKLM "Software\classes\firefoxurl\defaulticon" ""
    call StripPath
    StrCmp $FPath "" +1 FDone
    
    ReadRegStr $FPath HKLM "Software\clients\startmenuinternet\firefox.exe\shell\open\command" ""
    call StripPath
    StrCmp $FPath "" +1 FDone
    
    ReadRegStr $FPath HKLM "Software\Microsoft\Windows\CurrentVersion\app paths\firefox.exe" ""
    call StripPath
    StrCmp $FPath "" +1 FDone
    
    ReadRegStr $FPath HKLM "Software\Microsoft\Windows\CurrentVersion\app paths\firefox.exe" "Path"
    StrCmp $FPath "" +1 FDone
    
    ReadRegStr $FVersion HKLM "Software\mozilla\mozilla firefox" "CurrentVersion"
    ReadRegStr $FPath HKLM "Software\mozilla\mozilla firefox\$FVersion\main" "Install Directory"
    StrCmp $FPath "" +1 FDone
    
    ReadRegStr $FPath HKLM "Software\mozilla\mozilla firefox\$FVersion\main" "PathToExe"
    call StripPath
    
    FDone:
FunctionEnd

Function RIndexOf
Exch $R0
Exch
Exch $R1
Push $R2
Push $R3
 
 StrCpy $R3 $R0
 StrCpy $R0 0
 IntOp $R0 $R0 + 1
  StrCpy $R2 $R3 1 -$R0
  StrCmp $R2 "" +2
  StrCmp $R2 $R1 +2 -3
 
 StrCpy $R0 -1
 
Pop $R3
Pop $R2
Pop $R1
Exch $R0
FunctionEnd

# Removes the given char from the string
# Example: ${CharStrip} "." "99.21" $R0
# Results: $R0 == "9921"
Function CharStrip
Exch $R0 #char
Exch
Exch $R1 #in string
Push $R2
Push $R3
Push $R4
 StrCpy $R2 -1
 IntOp $R2 $R2 + 1
 StrCpy $R3 $R1 1 $R2
 StrCmp $R3 "" +8
 StrCmp $R3 $R0 0 -3
  StrCpy $R3 $R1 $R2
  IntOp $R2 $R2 + 1
  StrCpy $R4 $R1 "" $R2
  StrCpy $R1 $R3$R4
  IntOp $R2 $R2 - 2
  Goto -9
  StrCpy $R0 $R1
Pop $R4
Pop $R3
Pop $R2
Pop $R1
Exch $R0
FunctionEnd

Function StrCase
/*After this point:
  ------------------------------------------
  $0 = String (input)
  $1 = Case (input)
  $2 = StrLength (temp)
  $3 = StartChar (temp)
  $4 = EndChar (temp)
  $5 = ResultStr (temp)
  $6 = CurrentChar (temp)
  $7 = LastChar (temp)
  $8 = Temp (temp)*/
 
  ;Get input from user
  Exch $1
  Exch
  Exch $0
  Exch
  Push $2
  Push $3
  Push $4
  Push $5
  Push $6
  Push $7
  Push $8
 
  ;Initialize variables
  StrCpy $2 ""
  StrCpy $3 ""
  StrCpy $4 ""
  StrCpy $5 ""
  StrCpy $6 ""
  StrCpy $7 ""
  StrCpy $8 ""
 
  ;Upper and lower cases are simple to use
  ${If} $1 == "U"
 
    ;Upper Case:
    ;-----------
    ;Convert all characters to upper case.
 
    System::Call "User32::CharUpper(t r0 r5)i"
    Goto StrCase_End
  ${ElseIf} $1 == "L"
 
    ;Lower Case:
    ;-----------
    ;Convert all characters to lower case.
 
    System::Call "User32::CharLower(t r0 r5)i"
    Goto StrCase_End
  ${EndIf}
 
  ;For the rest of cases:
  ;Get "String" length
  StrLen $2 $0
 
  ;Make a loop until the end of "String"
  ${For} $3 0 $2
    ;Add 1 to "EndChar" counter also
    IntOp $4 $3 + 1
 
    # Step 1: Detect one character at a time
 
    ;Remove characters before "StartChar" except when
    ;"StartChar" is the first character of "String"
    ${If} $3 <> 0
      StrCpy $6 $0 `` $3
    ${EndIf}
 
    ;Remove characters after "EndChar" except when
    ;"EndChar" is the last character of "String"
    ${If} $4 <> $2
      ${If} $3 = 0
        StrCpy $6 $0 1
      ${Else}
        StrCpy $6 $6 1
      ${EndIf}
    ${EndIf}
 
    # Step 2: Convert to the advanced case user chose:
 
    ${If} $1 == "T"
 
      ;Title Case:
      ;------------------
      ; Convert all characters after a non-alphabetic character to upper case.
      ; Else convert to lower case.
 
      ;Use "IsCharAlpha" for the job
      System::Call "*(&t1 r7) i .r8"
      System::Call "*$8(&i1 .r7)"
      System::Free $8
      System::Call "user32::IsCharAlpha(i r7) i .r8"
 
      ;Verify "IsCharAlpha" result and convert the character
      ${If} $8 = 0
        System::Call "User32::CharUpper(t r6 r6)i"
      ${Else}
        System::Call "User32::CharLower(t r6 r6)i"
      ${EndIf}
    ${ElseIf} $1 == "S"
 
      ;Sentence Case:
      ;------------------
      ; Convert all characters after a ".", "!" or "?" character to upper case.
      ; Else convert to lower case. Spaces or tabs after these marks are ignored.
 
      ;Detect current characters and ignore if necessary
      ${If} $6 == " "
      ${OrIf} $6 == "$\t"
        Goto IgnoreLetter
      ${EndIf}
 
      ;Detect last characters and convert
      ${If} $7 == "."
      ${OrIf} $7 == "!"
      ${OrIf} $7 == "?"
      ${OrIf} $7 == ""
        System::Call "User32::CharUpper(t r6 r6)i"
      ${Else}
        System::Call "User32::CharLower(t r6 r6)i"
      ${EndIf}
    ${ElseIf} $1 == "<>"
 
      ;Switch Case:
      ;------------------
      ; Switch all characters cases to their inverse case.
 
      ;Use "IsCharUpper" for the job
      System::Call "*(&t1 r6) i .r8"
      System::Call "*$8(&i1 .r7)"
      System::Free $8
      System::Call "user32::IsCharUpper(i r7) i .r8"
 
      ;Verify "IsCharUpper" result and convert the character
      ${If} $8 = 0
        System::Call "User32::CharUpper(t r6 r6)i"
      ${Else}
        System::Call "User32::CharLower(t r6 r6)i"
      ${EndIf}
    ${EndIf}
 
    ;Write the character to "LastChar"
    StrCpy $7 $6
 
    IgnoreLetter:
    ;Add this character to "ResultStr"
    StrCpy $5 `$5$6`
  ${Next}
 
  StrCase_End:
 
/*After this point:
  ------------------------------------------
  $0 = ResultVar (output)*/
 
  ; Copy "ResultStr" to "ResultVar"
  StrCpy $0 $5
 
  ;Return output to user
  Pop $8
  Pop $7
  Pop $6
  Pop $5
  Pop $4
  Pop $3
  Pop $2
  Pop $1
  Exch $0
FunctionEnd

# Removes anything after the final "\" in the FPath variable
Function StripPath
    StrLen $0 $FPath
    ${RIndexOf} $1 $FPath "\"
    IntOp $0 $0 - $1
    StrCpy $FPath $FPath $0
FunctionEnd

Function StrTrimNewLines
/*After this point:
  ------------------------------------------
  $R0 = String (input)
  $R1 = TrimCounter (temp)
  $R2 = Temp (temp)*/
 
  ;Get input from user
  Exch $R0
  Push $R1
  Push $R2
 
  ;Initialize trim counter
  StrCpy $R1 0
 
  loop:
  ;Subtract to get "String"'s last characters
  IntOp $R1 $R1 - 1
 
  ;Verify if they are either $\r or $\n
  StrCpy $R2 $R0 1 $R1
  ${If} $R2 == `$\r`
  ${OrIf} $R2 == `$\n`
    Goto loop
  ${EndIf}
 
  ;Trim characters (if needed)
  IntOp $R1 $R1 + 1
  ${If} $R1 < 0
    StrCpy $R0 $R0 $R1
  ${EndIf}
 
/*After this point:
  ------------------------------------------
  $R0 = ResultVar (output)*/
 
  ;Return output to user
  Pop $R2
  Pop $R1
  Exch $R0
FunctionEnd

# Uninstaller functions
Function un.onInit
    ReadRegStr $INSTDIR HKLM "${REGKEY}" Path
    !insertmacro SELECT_UNSECTION Main ${UNSEC0000}
	SetRebootFlag true
FunctionEnd

