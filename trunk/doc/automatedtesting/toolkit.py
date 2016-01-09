import ftplib
import re
import json
import os
import os.path
import time
import datetime
import unittest
import subprocess
import sys
import shutil
import logging
import smtplib
import config
import ctypes
import boto3

from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email.utils import COMMASPACE, formatdate
from email import encoders

# Global variable which will hold our json configuration, which we use to store timestamps of files we've downloaded
cfg = None
# TODO : We also have an object called config, which is imported from a user config file. This is confusing..

def toolkitMain():
  if not os.path.exists(config.TEMP_ROOT):
    os.mkdir(config.TEMP_ROOT)
  
  if os.path.exists(config.TESTING_ROOT + "/logs"):
    shutil.rmtree(config.TESTING_ROOT + "/logs")
    time.sleep(0.5) # Sometimes windows will give us permissions error if we try to remake this too quickly
  os.mkdir(config.TESTING_ROOT + "/logs")

  logging.basicConfig(filename=config.LOG_FILE, level=logging.DEBUG)

  deleteRestoreTemp()
  deleteGeneratedBackups()

  global cfg
  cfg = getConfig()
  
  getDependencies(cfg)
  
  logging.info("Running tests")
  for all_test_suite in unittest.defaultTestLoader.discover('tests', pattern='*.py'):
    for test_suite in all_test_suite:
      logging.info("Executing test [" + str(test_suite) + "]")
      suite = unittest.TestSuite()
      suite.addTests(test_suite)
      runner = unittest.TextTestRunner(verbosity = 2)
      runner.run(suite)
  
  backupLogs()
  sendEmail("The recent DDMS build has completed testing successfully.")

def saveConfig():
  with open(config.TESTING_ROOT + "/toolkitpersist.cfg", 'r+') as cfgFile:
    json.dump(cfg, cfgFile)
    cfgFile.close()

def getConfig():
  global cfg
  if not (cfg is None):
    return cfg

  if not os.path.isfile(config.TESTING_ROOT + "/toolkitpersist.cfg"):
    with open(config.TESTING_ROOT + "/toolkitpersist.cfg", 'a+') as cfgFile:
      logging.info("Config file not found, creating new one.")
      json.dump({}, cfgFile)
  
  with open(config.TESTING_ROOT + "/toolkitpersist.cfg", 'r+') as cfgFile:
    cfg = json.load(cfgFile)
    logging.info("Read config as " + str(cfg))
  
  return cfg

def s3Dependency(filename):
  s3 = boto3.resource('s3')
  bucket = s3.Bucket("dss.vector.solutions.ddms")
  object = s3.Object('dss.vector.solutions.ddms', "testing/" + filename)
  
  #objLM = datetime.datetime.fromtimestamp(time.mktime(time.strptime(object.last_modified, '%a, %d %b %Y %H:%M:%S %Z')))
  objLM = object.last_modified
  
  # TODO : wait if doesn't exist on server
  if (os.path.isfile(config.TEMP_ROOT + "/" + filename)):
    localLM = datetime.datetime.fromtimestamp(os.path.getmtime(filename))

    if (
    objLM > localLM
    ):
      logging.info("deleting old " + filename)
      os.remove(config.TEMP_ROOT + "/" + filename)
  
      object.download_file(config.TEMP_ROOT + "/" + filename)
    
  else:
    object.download_file(config.TEMP_ROOT + "/" + filename)
  
def getDependencies(cfg):
  logging.info("Checking dependencies")

  installRe = re.compile("InstallDDMSblank-.*\.exe")
  patchRe = re.compile("patch-.*\.exe")

  connection = ftplib.FTP(**config.ftp)
  connection.cwd("/ddms/testing")

  getDepends = []
  installerChecked = False
  patcherChecked = False
  foundMarker = False
  shouldLoop = True
  toldUserWereWaiting = False
  while (shouldLoop):
    directory = connection.mlsd()
    for file in directory:
      if (
        installRe.match(file[0]) and
        not installerChecked and
        ((not "installer" in cfg) or cfg["installer"] != file[1]["modify"])
        ):
        cfg["installer"] = file[1]["modify"]
        getDepends.append(file[0])
        installerChecked = True
        toldUserWereWaiting = False
        installerName = file[0]
      elif (
        patchRe.match(file[0]) and
        not patcherChecked and
        ((not "patcher" in cfg) or cfg["patcher"] != file[1]["modify"])
        ):
        cfg["patcher"] = file[1]["modify"]
        getDepends.append(file[0])
        patcherChecked = True
        toldUserWereWaiting = False
        patcherName = file[0]
      elif (file[0] == "marker.txt"):
        foundMarker = True # The marker tells us that not only do the files exist, they're also finished uploading
        toldUserWereWaiting = False
        
    if (patcherChecked and installerChecked and foundMarker):
      shouldLoop = False
      logging.info("Installer and patcher found.")
      time.sleep(3)
    else:
      if (not toldUserWereWaiting):
        if patcherChecked and installerChecked:
          logging.info("Waiting on marker flag.")
        elif not patcherChecked and not installerChecked:
          logging.info("Waiting on both patcher and installer.")
        elif not patcherChecked and installerChecked:
          logging.info("Waiting on patcher.")
        elif not installerChecked and patcherChecked:
          logging.info("Waiting on installer.")
        toldUserWereWaiting = True
      time.sleep(30)

  # download dependencies we'll need
  for depend in getDepends:
    if depend == installerName:
      if os.path.isfile(config.TEMP_ROOT + "/installer.exe"):
        logging.info("deleting old installer")
        os.remove(config.TEMP_ROOT + "/installer.exe")
      
      dependFile = open(config.TEMP_ROOT + "/installer.exe", "wb")
    else:
      if os.path.isfile(config.TEMP_ROOT + "/patcher.exe"):
        logging.info("deleting old patcher")
        os.remove(config.TEMP_ROOT + "/patcher.exe")
      
      dependFile = open(config.TEMP_ROOT + "/patcher.exe", "wb")
        
    def depend_cb(block):
      dependFile.write(block)

    logging.info("downloading " + depend)
    connection.retrbinary('RETR ' + depend, depend_cb)
    dependFile.close()

  # Download the backup file
  if (not os.path.isfile(config.TEMP_ROOT + "/" + config.RESTORE_FILE + ".zip")):
    connection.cwd("/ddms/backups")

    restoreFile = open(config.TEMP_ROOT + "/" + config.RESTORE_FILE + ".zip", "wb")
    def restore_cb(block):
      restoreFile.write(block)

    logging.info("downloading " + restoreFile.name)
    connection.retrbinary('RETR ' + config.RESTORE_FILE + ".zip", restore_cb)
    restoreFile.close()
 
  saveConfig()
 
  connection.quit()

def install(appName, programName="installer.exe"):
  logging.info("Starting install of [" + appName + "].")

  with open(config.LOG_FILE, "a+") as out:
    baseCmds = [config.TEMP_ROOT + "/" + programName, "/S", "-app_name", appName, "-master", "-install_number", "0"]

    procezz = subprocess.Popen(baseCmds, stdout=out, stderr=out, cwd=os.getcwd())
    result = procezz.wait()

  if (result is not 0):
    exitWithFailure("Toolkit is exiting with failure, install subprocess failed.")

  exitIfErrorInLogs()
  
  # Update log4j to debug logging levels
  '''
  backupMan = open(config.MDSS_ROOT + "/manager/backup-manager-1.0.0/profiles/" + appName + "/log4j.properties").read()
  backupMan = backupMan.replace("WARN", "DEBUG")
  f=open(config.MDSS_ROOT + "/manager/backup-manager-1.0.0/profiles/" + appName + "/log4j.properties", 'w')
  f.write(backupMan)
  f.flush()
  f.close()
  
  tomcat = open(config.MDSS_ROOT + "/tomcat/webapps/" + appName + "/WEB-INF/classes/log4j.properties").read()
  tomcat = tomcat.replace("WARN", "DEBUG")
  f=open(config.MDSS_ROOT + "/tomcat/webapps/" + appName + "/WEB-INF/classes/log4j.properties", 'w')
  f.write(tomcat)
  f.flush()
  f.close()
  '''

def restore(appName, bkpFile=None):
  if bkpFile is None:
    bkpFile = config.TEMP_ROOT + "/" + config.RESTORE_FILE + ".zip"
  
  #if not os.path.exists(config.RESTORE_DIR):
  #  os.makedirs(config.RESTORE_DIR)
  #copied = config.RESTORE_DIR + "/" + config.RESTORE_FILE + ".zip"
  
  #shutil.copyfile(bkpFile, copied)
  
  manager("-restore", appName, "-filename", "\"" + bkpFile + "\"",)

  deleteRestoreTemp()

def backup(appName, bkpFile=None):
  if bkpFile is None:
    bkpFile = config.TEMP_ROOT + "/" + appName + "_GENERATED_BACKUP_"
  
  manager("-backup", appName, "-filename", "\"" + bkpFile + "\"")

# Doing a restore creates a temp directory which causes us to run out of space. Calling this function will delete that temp directory.
def deleteRestoreTemp():
  pass
  #tempDir = config.RESTORE_DIR
  #if os.path.isdir(tempDir):
    #logging.info("Deleting backup temp directory [" + tempDir + "]")
    #shutil.rmtree(tempDir)

# Running the backup command generates a backup file. We're going to delete it with this function.
def deleteGeneratedBackups():
  tempRE = re.compile(".*_generated_backup_.*\.zip")
  tempFiles = os.listdir(config.TEMP_ROOT)
  for file in tempFiles:
    if os.path.isfile(config.TEMP_ROOT + "/" + file) and tempRE.match(file.lower()):
      logging.info("Deleting generated backup [" + config.TEMP_ROOT + "/" + file + "]")
      os.remove(config.TEMP_ROOT + "/" + file)

def patch():
  logging.info("Starting patch.")
    
  out = open(config.LOG_FILE,"a+")
  baseCmds = [config.TEMP_ROOT + "/patcher.exe", "/S"]
    
  procezz = subprocess.Popen(baseCmds, stdout=out, stderr=out, cwd=os.getcwd())
  result = procezz.wait()
  out.close()

  if (result is not 0):
    exitWithFailure("Toolkit is exiting with failure, patch subprocess failed.")

    exitIfErrorInLogs()

def startTomcat():
  manager("-startTomcat")

def stopTomcat():
  manager("-stopTomcat")

def manager(*cmds):
  with open(config.LOG_FILE,"a+") as out:
    with open(config.ERR_LOG_FILE,"a+") as err:
      baseCmds = [r'C:\WINDOWS\system32\WindowsPowerShell\v1.0\powershell.exe',
             '-ExecutionPolicy',
             'ByPass',
            '-File',
             config.MDSS_ROOT + '/manager/manager.ps1']
      baseCmds.extend(cmds)
      
      procezz = subprocess.Popen(baseCmds, stdout=out, stderr=err, cwd=os.getcwd())
      result = procezz.wait()

      if (result is not 0):
        exitWithFailure("Toolkit is exiting with failure, manager subprocess failed executing commands [" + ", ".join(cmds) + "]")

      exitIfErrorInLogs()

def uninstall():
  if os.path.isfile(config.MDSS_ROOT + "/uninstall.exe"):
    logging.info("Starting uninstall.")

    with open(config.LOG_FILE,"a+") as out:
      baseCmds = [config.MDSS_ROOT + "/uninstall.exe", "/S"]
      procezz = subprocess.Popen(baseCmds, stdout=out, stderr=out, cwd=os.getcwd())
      result = procezz.wait()

    if (result is not 0):
      exitWithFailure("Toolkit is exiting with failure, uninstall subprocess failed.")

    exitIfErrorInLogs()
    
    systemReboot()
  else:
    exitWithFailure("Unable to uninstall because the uninstall program did not exist.")

def exitWithFailure(msg, files=[]):
  logging.error(msg)
  sys.stderr.write(msg)
  backupLogs()

  sendEmail("The recent DDMS build failed automated testing. Reason:\n" + msg, files)
  
  sys.exit(1)

def sendEmail(body, files=[]):
  msg = MIMEMultipart()
  msg['Subject'] = 'DDMS Automated Testing'
  msg['From'] = config.email["from"]
  msg['To'] = ", ".join(config.email["to"])
  msg.attach(MIMEText(body))
  
  for f in files:
    part = MIMEBase('application', "octet-stream")
    part.set_payload( open(f,"rb").read() )
    encoders.encode_base64(part)
    part.add_header('Content-Disposition', 'attachment; filename="{0}"'.format(os.path.basename(f)))
    msg.attach(part)
  
  server = smtplib.SMTP(config.email["server"])
  server.starttls()
  server.login(config.email["username"], config.email["password"])
  logging.info("Sending email(s) to [" + msg['To'] + "] with body [" + body + "]")
  server.sendmail(config.email["from"], config.email["to"], msg.as_string())
  server.quit()
  
def systemReboot():
  with open(config.LOG_FILE,"a+") as out:
    baseCmds = ["shutdown.exe", "-t", "0", "-r"]
    procezz = subprocess.Popen(baseCmds, stdout=out, stderr=out, cwd=os.getcwd())
    result = procezz.wait()

def manualTesting():
  exitIfErrorInLogs()
  sendEmail("Ready for manual testing.")
  ctypes.windll.user32.MessageBoxW(0, "You now have an opportunity to do any additional manual testing. When finished, click OK and control will be given back to the toolkit.", "DDMS Release Testing", 0)
  
def backupLogs():
  if os.path.exists(config.MDSS_ROOT + "/logs"):
    shutil.copytree(config.MDSS_ROOT + "/logs", config.TESTING_ROOT + "/logs/mdsslogs")

  if os.path.exists(config.MDSS_ROOT + "/tomcat6/logs"):
    shutil.copytree(config.MDSS_ROOT + "/tomcat6/logs", config.TESTING_ROOT + "/logs/tomcatlogs")

  if os.path.exists(config.MDSS_ROOT + "/patch"):
    shutil.copytree(config.MDSS_ROOT + "/patch", config.TESTING_ROOT + "/logs/patch")

  if os.path.exists(config.MDSS_ROOT + "/installer-log.txt"):
    shutil.copyfile(config.MDSS_ROOT + "/installer-log.txt", config.TESTING_ROOT + "/logs/installer-log.txt")

  if os.path.exists(config.MDSS_ROOT + "/PostInstallSetup.log"):
    shutil.copyfile(config.MDSS_ROOT + "/PostInstallSetup.log", config.TESTING_ROOT + "/logs/PostInstallSetup.log")

  if os.path.exists(config.TESTING_ROOT + "/shell.out"):
    shutil.copyfile(config.TESTING_ROOT + "/shell.out", config.TESTING_ROOT + "/logs/shell.out")
    
  ts = time.time()
  st = datetime.datetime.fromtimestamp(ts).strftime('(%Y-%m-%d)-(%H-%M-%S)')
  backedUp = config.TESTING_ROOT + "/results/" + st
  logging.info("Saving results to [" + backedUp + "].")
  shutil.copytree(config.TESTING_ROOT + "/logs", backedUp)


def exitIfErrorInLogs():
  logFiles = []
  
  # Look at all logs in mdss/logs
  if os.path.exists(config.MDSS_ROOT + "/logs"):
    mdssLogs = os.listdir(config.MDSS_ROOT + "/logs")
    mdssLogs = [config.MDSS_ROOT + "/logs/" + fname for fname in mdssLogs]
    logFiles.extend(mdssLogs)

  # Look at all logs in tomcat
  if os.path.exists(config.MDSS_ROOT + "/tomcat6/logs"):
    tomcatLogs = os.listdir(config.MDSS_ROOT + "/tomcat6/logs")
    tomcatLogs = [config.MDSS_ROOT + "/tomcat6/logs/" + fname for fname in tomcatLogs]
    logFiles.extend(tomcatLogs)

  # Look at all logs in mdss/patch
  if os.path.exists(config.MDSS_ROOT + "/patch"):
    patchLogs = os.listdir(config.MDSS_ROOT + "/patch")
    patchLogs = [config.MDSS_ROOT + "/patch/" + fname for fname in patchLogs]
    logFiles.extend(patchLogs)

  if os.path.exists(config.LOG_FILE):
    logFiles.append(config.LOG_FILE)

  if os.path.exists(config.MDSS_ROOT + "/installer-log.txt"):
    logFiles.append(config.MDSS_ROOT + "/installer-log.txt")
    
  if os.path.exists(config.TESTING_ROOT + "/shell.out"):
    logFiles.append(config.TESTING_ROOT + "/shell.out")

  daemonRE = re.compile(".*commons-daemon.*\.log")
  for fileName in logFiles:
    if os.path.isdir(fileName) or daemonRE.match(fileName):
      continue
    
    with open(fileName) as file:
      contents = file.read().lower()
      
    for bad in config.logExitConditions:
      if bad.lower() in contents:
        exitWithFailure("An error [" + bad + "] was detected in the log file [" + fileName + "].", [fileName])

if __name__ == "__main__":
  toolkitMain()
