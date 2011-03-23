# Auto-generated by EclipseNSIS Script Wizard
# Mar 18, 2011 10:39:10 AM

Name "DDMS"

# General Symbol Definitions
!define REGKEY "SOFTWARE\$(^Name)"
!define VERSION 3.1.0.0
!define COMPANY IVCC
!define URL ivcc.com

# MUI Symbol Definitions
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install-blue.ico"
!define MUI_FINISHPAGE_NOAUTOCLOSE

# Included files
!include Sections.nsh
!include MUI2.nsh

# Variables
Var Version

# Installer pages
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH

# Installer languages
!insertmacro MUI_LANGUAGE English

# Installer attributes
OutFile manager.exe
InstallDir C:\MDSS\manager
CRCCheck on
XPStyle on
ShowInstDetails show
VIProductVersion 3.1.0.0
VIAddVersionKey ProductName "DDMS Manager"
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
    
    # This version number is automatically replaced by manager.xml
    StrCpy $Version 5928
    
    # Before we start, check the versions to make sure this is actually a patch.
    ReadRegStr $0 HKLM "${REGKEY}\Components" Manager
    ${If} $0 >= $Version
        MessageBox MB_OK "The manager is more recent than this patch. Patch will halt."
        Abort
    ${EndIf}
    
    # Special check to make sure ajde goes away.
    Delete $INSTDIR\manager\lib\ajde.jar
    
    SetOutPath $INSTDIR\bin
    File /r /x .svn ..\bin\*
    SetOutPath $INSTDIR\lib
    File /r /x .svn ..\lib\*
    SetOutPath $INSTDIR\profiles
    File /r /x .svn /x terraframe.properties ..\profiles\*
    SetOutPath $INSTDIR\icons
    File /r /x .svn ..\icons\*
    
    WriteRegStr HKLM "${REGKEY}\Components" Manager $Version
SectionEnd

# Installer functions
Function .onInit
    InitPluginsDir
FunctionEnd

