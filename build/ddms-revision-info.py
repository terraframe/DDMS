#!/usr/bin/env python

import smtplib
import os
import os.path
import sys
import subprocess
import re

def writeRevisionInfo(nsisFile, projectRoot):
  replaceInFile(nsisFile, "PatchVersion", getModTime(projectRoot, "DDMS"))
  replaceInFile(nsisFile, "RootsVersion", getModTime(projectRoot, "DDMS/doc/ontology/MOroots.xls"))
  replaceInFile(nsisFile, "MenuVersion", getModTime(projectRoot, "DDMS/doc/menu/MenuItems.xls"))
  replaceInFile(nsisFile, "LocalizationVersion", getModTime(projectRoot, "DDMS/doc/localization"))
  replaceInFile(nsisFile, "PermissionsVersion", getModTime(projectRoot, "DDMS/profiles/Permissions.xls"))
  replaceInFile(nsisFile, "RunwayVersion", getModTime(projectRoot, "ddms-runway-patcher"))
  replaceInFile(nsisFile, "ManagerVersion", getModTime(projectRoot, "DDMS/"))
  replaceInFile(nsisFile, "BirtVersion", getModTime(projectRoot, "build/thirdparty/birt"))
  replaceInFile(nsisFile, "EclipseVersion", getModTime(projectRoot, "build/thirdparty/eclipse"))
  replaceInFile(nsisFile, "WebappsVersion", getModTime(projectRoot, "build/thirdparty/tomcat/webapps"))
  replaceInFile(nsisFile, "JavaVersion", getModTime(projectRoot, "build/thirdparty/Java"))
  replaceInFile(nsisFile, "TomcatVersion", getModTime(projectRoot, "build/thirdparty/tomcat"))

def replaceInFile(nsisFile, varName, modTime):
  print("Setting " + varName + " to " + modTime + " in " + nsisFile)

  with open(nsisFile, 'r') as file:
    data = file.read()

  data = re.sub(
           "StrCpy \$" + varName + " \d*",
           "StrCpy $" + varName + " " + modTime,
           data
       )

  with open(nsisFile, 'w') as file:
    file.write(data)

def getModTime(projectRoot, path):
  fullpath = os.path.join(projectRoot, path)

  stdOut = subprocess.check_output(["git", "log", "-1", '--format="%at"', fullpath])

  print("Executed Git Command [git log -1 --format=\"%at\" " + str(fullpath) + "] and got response [" + str(stdOut) + "]")

  p = re.compile("^.*?(\d{8,13}).*?$")
  match = p.match(str(stdOut))

  return match.group(1)

writeRevisionInfo(sys.argv[1], sys.argv[2])
