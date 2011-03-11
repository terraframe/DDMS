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

# Installer pages
!insertmacro MUI_PAGE_WELCOME
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

# Installer sections
Section -Main SEC0000
    SetOutPath $INSTDIR
    SetOverwrite on
    
    # Fix pathing in target profile
    # Switch between deploy and develop environments as needed OR wrap the main classes
    
    # These version numbers are automatically replaced by patch.xml
    StrCpy $PatchVersion 5877
    StrCpy $TermsVersion 5814
    StrCpy $RootsVersion 5432
    StrCpy $MenuVersion 5814
    StrCpy $LocalizationVersion 5852
    StrCpy $PermissionsVersion 5650
    
    # Before we start, check the versions to make sure this is actually a patch.
    ReadRegStr $0 HKLM "${REGKEY}\Components\blank" App
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
    StrCpy $Classpath "$INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes;$INSTDIR\tomcat6\webapps\DDMS\WEB-INF\lib\*"
    
    # Remove any old log files that may be laying around
    Delete $AgentDir\*.out
    Delete $AgentDir\*.err

    # Import Most Recent
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Copying patch files"
    SetOutPath $PatchDir
    File OutputAgent.jar
    
    # copy_country_schema,import_geodata,develop_build_all_paths_geodata,import_ontology_roots
    # develop_create_univeral_queries,copy_dev_to_deploy
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Updating web files"
    SetOutPath $INSTDIR\tomcat6\webapps\DDMS
    File /r webapp\*
    File /oname=$INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\version.xsd ..\profiles\version.xsd
    
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
    Rename $INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\local.properties $INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\local-deploy.properties
    Rename $INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\local-develop.properties $INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\local.properties
    
    # Terms
    !insertmacro MUI_HEADER_TEXT "Patching DDMS" "Importing Ontology"
    SetOutPath $PatchDir\doc
    File ..\doc\ontology\MOterms.xls
    ReadRegStr $0 HKLM "${REGKEY}\Components\blank" Terms
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
    ReadRegStr $0 HKLM "${REGKEY}\Components\blank" Roots
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
    ReadRegStr $0 HKLM "${REGKEY}\Components\blank" Menu
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
    ReadRegStr $0 HKLM "${REGKEY}\Components\blank" Localization
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
    ReadRegStr $0 HKLM "${REGKEY}\Components\blank" Permissions
    ${If} $PermissionsVersion > $0
        ExecWait `$Java $JavaOpts=$AgentDir\localization -cp $Classpath dss.vector.solutions.permission.PermissionImporter $PatchDir\doc\Permissions.xls` $JavaError
        Call JavaAbort
    ${Else}
        DetailPrint "Skipping Permissions because they are already up to date"
    ${EndIf}
    
    # Switch back to the deploy environment
    Rename $INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\local.properties $INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\local-develop.properties
    Rename $INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\local-deploy.properties $INSTDIR\tomcat6\webapps\DDMS\WEB-INF\classes\local.properties
    
    # Write updated versions into registry 
    WriteRegStr HKLM "${REGKEY}\Components" Main 1
    WriteRegStr HKLM "${REGKEY}\Components\blank" App $PatchVersion
    WriteRegStr HKLM "${REGKEY}\Components\blank" Terms $TermsVersion
    WriteRegStr HKLM "${REGKEY}\Components\blank" Roots $RootsVersion
    WriteRegStr HKLM "${REGKEY}\Components\blank" Menu $MenuVersion
    WriteRegStr HKLM "${REGKEY}\Components\blank" Localization $LocalizationVersion
    WriteRegStr HKLM "${REGKEY}\Components\blank" Permissions $PermissionsVersion
    WriteRegStr HKLM "${REGKEY}\Components" Manager 1
    WriteRegStr HKLM "${REGKEY}\Components" Runway 1
SectionEnd

Function JavaAbort
    ${If} $JavaError == 1
      Abort "Patch failed.  It is strongly recommended to restore from a backup to ensure that the app continues to function properly."
    ${EndIf}
FunctionEnd

# Installer functions
Function .onInit
    InitPluginsDir
FunctionEnd

