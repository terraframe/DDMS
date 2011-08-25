# Auto-generated by EclipseNSIS Script Wizard
# Feb 10, 2011 10:10:22 AM

Name "DDMS"

RequestExecutionLevel highest

# General Symbol Definitions
!define REGKEY "SOFTWARE\$(^Name)"
!define VERSION 3.1
!define COMPANY "Innovative Vector Control Consortium"
!define URL "http://www.ivcc.com/"

# MUI Symbol Definitions
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install-colorful.ico"
!define MUI_FINISHPAGE_NOAUTOCLOSE

# Included files
!include Sections.nsh
!include MUI2.nsh
!include nsDialogs.nsh
!include LogicLib.nsh
!include FileFunc.nsh

# Define access to the StrTrimNewLines function
!macro StrTrimNewLines ResultVar String
  Push "${String}"
  Call StrTrimNewLines
  Pop "${ResultVar}"
!macroend
!define StrTrimNewLines "!insertmacro StrTrimNewLines"

# Variables
Var Java
Var JavaOpts
Var JavaError
Var Classpath
Var PatchDir
Var AgentDir
Var PatchVersion
Var TermsVersion
Var RootsVersion
Var MenuVersion
Var LocalizationVersion
Var PermissionsVersion
Var Label
Var DropList
Var AppName
Var TfDialog

# Installer pages
!insertmacro MUI_PAGE_WELCOME
Page custom appNameInputPage appNameInputLeavePage
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH

# Installer languages
!insertmacro MUI_LANGUAGE English

# Installer attributes
OutFile patch.exe
InstallDir C:\MDSS
CRCCheck on
XPStyle on
ShowInstDetails show
VIProductVersion 3.1.0.0
VIAddVersionKey ProductName "DDMS"
VIAddVersionKey ProductVersion "${VERSION}"
VIAddVersionKey CompanyName "${COMPANY}"
VIAddVersionKey CompanyWebsite "${URL}"
VIAddVersionKey FileVersion "${VERSION}"
VIAddVersionKey FileDescription ""
VIAddVersionKey LegalCopyright ""

Function checkIfMaster
    ClearErrors
    FileOpen $0 $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\install.properties r
    
    propFileReadLoop:
    # Read a line from the file into $1
    FileRead $0 $1
    # Errors means end of File
    IfErrors isNotMaster
    
    # Removes the newline from the end of $1
    ${StrTrimNewLines} $1 $1
    
    StrCmp $1 "master=true" isMaster propFileReadLoop
    
    isNotMaster:
    MessageBox MB_OK|MB_ICONEXCLAMATION "Only master installations can be patched. Patch will halt."
    FileClose $0
    Abort
    
    isMaster:
    ClearErrors
    FileClose $0
FunctionEnd

Function appNameInputLeavePage
    ${NSD_GetText} $DropList $AppName
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

  # Create the droplist, which gets put on the stack
  ${NSD_CreateDropList} 25% 0 74% 12u $AppName
  # Pop the droplist off the stack and store it in $DropList  
  Pop $DropList

  ClearErrors
  FileOpen $0 $INSTDIR\manager\manager-1.0.0\classes\applications.txt r
    
  appNameFileReadLoop:
  # Read a line from the file into $1
  FileRead $0 $1
    
  # Errors means end of File
  IfErrors appNameDone
    
  # Removes the newline from the end of $1
  ${StrTrimNewLines} $1 $1
    
  # Add the value to the droplist
  ${NSD_CB_AddString} $DropList $1
  
  Goto appNameFileReadLoop
        
  appNameDone:
  ClearErrors
  FileClose $0
      
  nsDialogs::Show
FunctionEnd


# Installer sections
Section -Main SEC0000
    SetOutPath $INSTDIR
    SetOverwrite on
    
    # Fix pathing in target profile
    # Switch between deploy and develop environments as needed OR wrap the main classes
    # Fix overwrite of site master / domain in terraframe.properties
    
    # These version numbers are automatically replaced by patch.xml
    StrCpy $PatchVersion 6333
    StrCpy $TermsVersion 5814
    StrCpy $RootsVersion 5432
    StrCpy $MenuVersion 5814
    StrCpy $LocalizationVersion 5978
    StrCpy $PermissionsVersion 5974
    
    # Only master installations can be patched.
    Call checkIfMaster
    
    # Before we start, check the versions to make sure this is actually a patch.
    ReadRegStr $0 HKLM "${REGKEY}\Components\$AppName" App
    ${If} $0 >= $PatchVersion
        MessageBox MB_OK "DDMS is more recent than this patch. Patch will halt."
        Abort
    ${EndIf}
    
    # Set some constants
    StrCpy $PatchDir "$INSTDIR\patch"
    StrCpy $AgentDir "$PatchDir\output"
    StrCpy $Java "$INSTDIR\Java\jdk1.6.0_16\bin\java.exe"
    StrCpy $JavaOpts "-Xmx1024m -javaagent:$PatchDir\OutputAgent.jar"
;    StrCpy $Java "C:\Program Files (x86)\Java\jdk1.6.0_20\bin\java.exe"
;    StrCpy $JavaOpts "-Xmx1024m -javaagent:$PatchDir\OutputAgent.jar -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y"
    StrCpy $Classpath "$INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes;$INSTDIR\tomcat6\webapps\$AppName\WEB-INF\lib\*"
    
    # Remove any old log files that may be laying around
    Delete $AgentDir\*.out
    Delete $AgentDir\*.err

    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Copying patch files"
    SetOutPath $PatchDir
    File OutputAgent.jar
    
    # Special check to make sure ajde goes away.
    Delete $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\lib\ajde.jar
    
    # Copy web files
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Updating web files"
    SetOutPath $INSTDIR\tomcat6\webapps\$AppName
    File /r webapp\*
    File /oname=$INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\version.xsd ..\profiles\version.xsd
    
    # Import Most Recent
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Importing updated schema definitions"
    SetOutPath $PatchDir\schema
    File /x .svn ..\doc\individual\*
    ExecWait `$Java $JavaOpts=$AgentDir\versioning -cp $Classpath com.runwaysdk.dataaccess.io.Versioning $PatchDir\schema /version.xsd` $JavaError
    Call JavaAbort
    
    # Update Database Source and Class
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Updating Database"
    ExecWait `$Java $JavaOpts=$AgentDir\updateDB -cp $Classpath com.runwaysdk.util.UpdateDatabaseSourceAndClasses` $JavaError
    Call JavaAbort
    # Delete $PatchDir\schema
    
    # Switch to the develop environment
    Rename $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\local.properties $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\local-deploy.properties
    Rename $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\local-develop.properties $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\local.properties
    
    # Terms
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Importing Ontology"
    SetOutPath $PatchDir\doc
    File ..\doc\ontology\MOterms.xls
    ReadRegStr $0 HKLM "${REGKEY}\Components\$AppName" Terms
    ${If} $TermsVersion > $0
        ExecWait `$Java $JavaOpts=$AgentDir\terms -cp $Classpath dss.vector.solutions.ontology.OntologyExcelImporter $PatchDir\doc\MOterms.xls` $JavaError
        Call JavaAbort
        ExecWait `$Java $JavaOpts=$AgentDir\term_all_paths -cp $Classpath dss.vector.solutions.ontology.AllPaths` $JavaError
        Call JavaAbort
    ${Else}
        DetailPrint "Skipping Ontology because it is already up to date"
    ${EndIf}
    
    # Term Roots
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Setting up Ontology Roots"
    File ..\doc\ontology\MOroots.xls
    File geo-universals.xls
    ReadRegStr $0 HKLM "${REGKEY}\Components\$AppName" Roots
    ${If} $RootsVersion > $0
        ExecWait `$Java $JavaOpts=$AgentDir\terms -cp $Classpath dss.vector.solutions.ontology.PostOntologySetup $PatchDir\doc\MOroots.xls $PatchDir\doc\geo-universals.xls` $JavaError
        Call JavaAbort
    ${Else}
        DetailPrint "Skipping Ontology Roots because they are already up to date"
    ${EndIf}
    
    # Menu Items
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Importing Menu Items"
    SetOutPath $PatchDir\doc
    File ..\doc\menu\MenuItems.xls
    ReadRegStr $0 HKLM "${REGKEY}\Components\$AppName" Menu
    ${If} $MenuVersion > $0
        ExecWait `$Java $JavaOpts=$AgentDir\menu -cp $Classpath dss.vector.solutions.util.MenuItemImporter $PatchDir\doc\MenuItems.xls` $JavaError
        Call JavaAbort
    ${Else}
        DetailPrint "Skipping Menu because it is already up to date"
    ${EndIf}
    
    # Create Universal Queries
    # Not needed according to Naifeh
    
    # Localization
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Updating Localization"
    SetOutPath $PatchDir\doc
    File ..\doc\DiseaseLocalizationDefaults.xls
    ReadRegStr $0 HKLM "${REGKEY}\Components\$AppName" Localization
    ${If} $LocalizationVersion > $0
        ExecWait `$Java $JavaOpts=$AgentDir\localization -cp $Classpath dss.vector.solutions.util.MdssLocalizationImporter $PatchDir\doc\DiseaseLocalizationDefaults.xls` $JavaError
        Call JavaAbort
    ${Else}
        DetailPrint "Skipping Localization because it is already up to date"
    ${EndIf}
    
    # Permissions
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Updating Permissions"
    SetOutPath $PatchDir\doc
    File ..\doc\permissions\Permissions.xls
    ReadRegStr $0 HKLM "${REGKEY}\Components\$AppName" Permissions
    ${If} $PermissionsVersion > $0
        ExecWait `$Java $JavaOpts=$AgentDir\localization -cp $Classpath dss.vector.solutions.permission.PermissionImporter $PatchDir\doc\Permissions.xls` $JavaError
        Call JavaAbort
    ${Else}
        DetailPrint "Skipping Permissions because they are already up to date"
    ${EndIf}
    
    # Switch back to the deploy environment
    Rename $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\local.properties $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\local-develop.properties
    Rename $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\local-deploy.properties $INSTDIR\tomcat6\webapps\$AppName\WEB-INF\classes\local.properties
    
    # Write updated versions into registry 
    WriteRegStr HKLM "${REGKEY}\Components" Main 1
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" App $PatchVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Terms $TermsVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Roots $RootsVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Menu $MenuVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Localization $LocalizationVersion
    WriteRegStr HKLM "${REGKEY}\Components\$AppName" Permissions $PermissionsVersion
    
    # We need to clear the old cache
    Delete $INSTDIR\tomcat6\$AppName.index
    Delete $INSTDIR\tomcat6\$AppName.data
    
    #Overwriting manage and runway is a mistake, since they may be up to date already.
;    WriteRegStr HKLM "${REGKEY}\Components" Manager 1
;    WriteRegStr HKLM "${REGKEY}\Components" Runway 1
SectionEnd

Function JavaAbort
    ${If} $JavaError == 1
      Abort "Patch failed.  It is strongly recommended to restore from a backup to ensure that the app continues to function properly."
    ${EndIf}
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

# Installer functions
Function .onInit
    InitPluginsDir
FunctionEnd

