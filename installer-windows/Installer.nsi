# Auto-generated by EclipseNSIS Script Wizard
# Mar 11, 2010 4:03:45 PM

Name DDMS

# General Symbol Definitions
!define REGKEY "SOFTWARE\$(^Name)"
!define VERSION 2.0
!define COMPANY "Innovative Vector Control Consortium"
!define URL "http://www.ivcc.com/"

# MUI Symbol Definitions
!define MUI_ICON "logo.ico"
!define MUI_FINISHPAGE_NOAUTOCLOSE
!define MUI_STARTMENUPAGE_REGISTRY_ROOT HKLM
!define MUI_STARTMENUPAGE_NODISABLE
!define MUI_STARTMENUPAGE_REGISTRY_KEY ${REGKEY}
!define MUI_STARTMENUPAGE_REGISTRY_VALUENAME StartMenuGroup
!define MUI_STARTMENUPAGE_DEFAULTFOLDER DDMS
!define MUI_UNICON "logo.ico"
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

# Variables
Var StartMenuGroup
Var TfDialog
Var Label
Var Text
Var InstallationNumber
Var Params
Var Master_Value
Var FPath
Var FVersion

# Installer pages
!insertmacro MUI_PAGE_WELCOME
Page custom userInputPage exitUserInputPage
!insertmacro MUI_PAGE_STARTMENU Application $StartMenuGroup
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

# Installer languages
!insertmacro MUI_LANGUAGE English

# Installer attributes
OutFile InstallDdms.exe
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

# Installer sections
Section -Main SEC0000
    SetOutPath $INSTDIR
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Searching for Firefox"
    Call findFireFox
    StrCmp $FPath "" installFireFox doneInstallFireFox
    installFireFox:
      !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Firefox"
      File "Firefox Setup 3.6.7.exe"
      ExecWait `"$INSTDIR\Firefox Setup 3.6.7.exe"`
      Call findFireFox
    doneInstallFireFox:
      
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Verifying Firefox Installation"
    StrCmp $FPath "" fireFoxNotFound fireFoxFound
    fireFoxNotFound:
      MessageBox MB_OK "Could not find FireFox.  Please install again and ensure that FireFox installs correctly"
      Abort
    
    fireFoxFound:
    # Force firefox to open up, just in case it has been freshly installed, so that the first-time setup can finish before we isntall the screengrab plugin
	ExecWait `"$FPath\firefox.exe"`
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Qcal"
    SetOutPath $INSTDIR\IRMA
    File /r IRMA\*
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing BIRT"
    SetOutPath $INSTDIR\birt
    File /r birt\*
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing DDMS Manager"
    SetOutPath $INSTDIR\manager
    File /r manager\*
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Java"
    SetOutPath $INSTDIR\Java
    File /r Java\*
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Copying docs"
    SetOutPath $INSTDIR\doc
    File /r doc\*
    
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing Tomcat"
    SetOutPath $INSTDIR\tomcat6
    File /r tomcat6\*
    SetOutPath $INSTDIR
    
    # Install the ScreenGrab addon
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing the ScrenGrab Plugin"
    #File "/oname=$FPath\extensions\screengrab-0.96.3-fx.xpi" "screengrab-0.96.3-fx.xpi"
	File "screengrab-0.96.3-fx.xpi"
	ExecWait `"$FPath\firefox.exe" "$INSTDIR\screengrab-0.96.3-fx.xpi"`
    
    # Install Postgres
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing PostgreSql"
    File "postgresql-8.4.1-1-windows.exe"
    ExecWait `"$INSTDIR\postgresql-8.4.1-1-windows.exe" --mode unattended --serviceaccount ddmspostgres --servicepassword RQ42juEdxa3o --create_shortcuts 0 --prefix C:\MDSS\PostgreSql\8.4 --datadir C:\MDSS\PostgreSql\8.4\data --superpassword CbyD6aTc54HA --serverport 5444 --locale en`
    ExecWait `net stop postgresql-8.4`   
    
    # Get the Windows Version (XP, Vista, etc.)
    nsisos::osversion
    
    # Version 5 means XP.  No IPv6
    ${If} $0 == 5
      File "/oname=C:\MDSS\PostgreSql\8.4\data\pg_hba.conf" "pg_hba_ipv4.conf"
    
    # Version 5 means Vista or Seven.  IPv6 enabled
    ${ElseIf} $0 == 6
      File "/oname=C:\MDSS\PostgreSql\8.4\data\pg_hba.conf" "pg_hba_ipv6.conf"
    
    # Who knows what version we're on.
    ${Else}
      MessageBox MB_OK "Unable to detect your windows version. DDMS is designed for Windows XP, Vista, or 7, and may not function properly on other platforms."
      File "/oname=C:\MDSS\PostgreSql\8.4\data\pg_hba.conf" "pg_hba_ipv6.conf"
    ${EndIf}
    
    # Copy the tweaked postgres config
    File "/oname=C:\MDSS\PostgreSql\8.4\data\postgresql.conf" "postgresql.conf"
    
    ExecWait `net start postgresql-8.4`
    
    # Install PostGIS
    !insertmacro MUI_HEADER_TEXT "Installing DDMS" "Installing PostGIS"
    File "postgis-pg84-setup-1.4.2-1.exe"
    ExecWait `"$INSTDIR\postgis-pg84-setup-1.4.2-1.exe" /S`
    
    # Create the database
    ExecWait `"C:\MDSS\PostgreSql\8.4\bin\psql" -p 5444 -h 127.0.0.1 -U postgres -d postgres -c "CREATE USER mdssdeploy ENCRYPTED PASSWORD 'mdssdeploy'"`
    ExecWait `"C:\MDSS\PostgreSql\8.4\bin\psql" -p 5444 -h 127.0.0.1 -U postgres -d postgres -c "CREATE DATABASE mdssdeploy WITH ENCODING='UTF8' TEMPLATE=template0 LC_COLLATE='C' LC_CTYPE='C' OWNER=mdssdeploy"`
    
    # Restore the db from the dump file
    # pg_dump.exe -b -f C:\stage\mdss.backup -F p -U postgres mdssdeploy
    File "mdss.backup"
    ExecWait `"C:\MDSS\PostgreSql\8.4\bin\psql" -U postgres -d mdssdeploy -p 5444 -h 127.0.0.1 -f C:\MDSS\mdss.backup`

    # Update the installation number
    ExecWait `"C:\MDSS\PostgreSql\8.4\bin\psql" -U mdssdeploy -d mdssdeploy -p 5444 -h 127.0.0.1 -c "update property set propertyvalue='$InstallationNumber' where propertyname='SHORT_ID_OFFSET'"`
    
    # Ports 5444-5452 and 8149-8159 available
    # takeown /f C:\MDSS\PostgreSql /r /d y
    # icalcs C:\MDSS\PostgreSql /grant administrators:F /t
    
    # Update terraframe.properties
    ExecWait `$INSTDIR\Java\jdk1.6.0_16\bin\java.exe -cp C:\MDSS\tomcat6\webapps\DDMS\WEB-INF\classes;C:\MDSS\tomcat6\webapps\DDMS\WEB-INF\lib\* dss/vector/solutions/util/PostInstallSetup $InstallationNumber $Master_Value`
    
    WriteRegStr HKLM "${REGKEY}\Components" Main 1
SectionEnd

Section -post SEC0001
    WriteRegStr HKLM "${REGKEY}" Path $INSTDIR
    SetOutPath $INSTDIR
    WriteUninstaller $INSTDIR\uninstall.exe
    !insertmacro MUI_STARTMENU_WRITE_BEGIN Application
    SetOutPath $SMPROGRAMS\$StartMenuGroup
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Open $(^Name).lnk" "$FPath\firefox.exe" "http://127.0.0.1:8080/DDMS/"
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Start $(^Name).lnk" "$INSTDIR\tomcat6\bin\startup.bat"
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Stop $(^Name).lnk" "$INSTDIR\tomcat6\bin\shutdown.bat"
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\BIRT.lnk" "$INSTDIR\birt\BIRT.exe"
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Qcal.lnk" "$INSTDIR\IRMA\Qcal.exe"
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Manager.lnk" "$INSTDIR\Java\jdk1.6.0_16\bin\javaw.exe" "-Xmx512m -cp C:\MDSS\manager\bin;C:\MDSS\manager\lib\*;C:\MDSS\manager\profiles dss/vector/solutions/admin/MDSSModule"
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Uninstall $(^Name).lnk" "$INSTDIR\uninstall.exe"
    RmDir /r /REBOOTOK "$SMPROGRAMS\PostGIS 1.4 for PostgreSQL 8.4"
    !insertmacro MUI_STARTMENU_WRITE_END
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
    ExecWait $INSTDIR\PostgreSQL\8.4\uninstall-postgis-pg84-1.4.0-2.exe
    ExecWait $INSTDIR\PostgreSQL\8.4\uninstall-postgresql.exe
    RmDir /r /REBOOTOK $INSTDIR
    RmDir /r /REBOOTOK $INSTDIR
    DeleteRegValue HKLM "${REGKEY}\Components" Main
SectionEnd

Section -un.post UNSEC0001
    DeleteRegKey HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\Open $(^Name).lnk"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\Start $(^Name).lnk"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\Stop $(^Name).lnk"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\BIRT.lnk"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\Qcal.lnk"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\Manager.lnk"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\Uninstall $(^Name).lnk"
    Delete /REBOOTOK $INSTDIR\uninstall.exe
    DeleteRegValue HKLM "${REGKEY}" StartMenuGroup
    DeleteRegValue HKLM "${REGKEY}" Path
    DeleteRegKey /IfEmpty HKLM "${REGKEY}\Components"
    DeleteRegKey /IfEmpty HKLM "${REGKEY}"
    RmDir /REBOOTOK $SMPROGRAMS\$StartMenuGroup
    RmDir /REBOOTOK $INSTDIR
SectionEnd

# Installer functions
Function .onInit
    LogSet On
    SetOverwrite try
    InitPluginsDir
    SetRebootFlag true
    # Initialize the value of the text string
    StrCpy $InstallationNumber "1"
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

# Removes anything after the final "\" in the FPath variable
Function StripPath
    StrLen $0 $FPath
    ${RIndexOf} $1 $FPath "\"
    IntOp $0 $0 - $1
    StrCpy $FPath $FPath $0
FunctionEnd

# Uninstaller functions
Function un.onInit
    ReadRegStr $INSTDIR HKLM "${REGKEY}" Path
    !insertmacro MUI_STARTMENU_GETFOLDER Application $StartMenuGroup
    !insertmacro SELECT_UNSECTION Main ${UNSEC0000}
FunctionEnd

