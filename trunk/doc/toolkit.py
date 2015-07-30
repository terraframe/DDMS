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

from email.mime.text import MIMEText

DEVELOPER_EMAILS = ["rrowlands@terraframe.com"]
TESTING_ROOT = "C:/DDMSTesting"
TEMP_ROOT = "Z:/ddms"
MDSS_ROOT = "C:/MDSS"
RESTORE_FILE = "india-2015_07_10-17_28_04.zip"

ftpCredentials = {'host' : 'ftp.terraframe.com',
'user' : 'terraframe@terraframe.com',
'passwd' : '_Terra4Frame'}

LOG_FILE = TESTING_ROOT + "/logs/toolkit.log"

def toolkitMain():
    if not os.path.exists(TEMP_ROOT):
        os.mkdir(TEMP_ROOT)
    
    if os.path.exists(TESTING_ROOT + "/logs"):
        shutil.rmtree(TESTING_ROOT + "/logs")
    os.mkdir(TESTING_ROOT + "/logs")

    logging.basicConfig(filename=LOG_FILE, level=logging.DEBUG)

    deleteRestoreTemp()
    deleteGeneratedBackups()

    logging.info("Reading config")
    if not os.path.isfile("toolkit.cfg"):
        with open('toolkit.cfg', 'a+') as cfgFile:
            json.dump({}, cfgFile)
            cfgFile.close()
    
    with open('toolkit.cfg', 'r+') as cfgFile:
        cfg = json.load(cfgFile)
        cfgFile.close()
    
    logging.info("Checking dependencies")
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
    uninstall()

def getDependencies(cfg):
    installRe = re.compile("InstallDDMSblank-.*\.exe")
    patchRe = re.compile("patch-.*\.exe")

    connection = ftplib.FTP(**ftpCredentials)
    connection.cwd("/ddms/testing")

    getDepends = []
    installerChecked = False
    patcherChecked = False
    shouldLoop = True
    while (shouldLoop):
        directory = connection.mlsd()
        for file in directory:
            if (installRe.match(file[0])):
                if ((not "installer" in cfg) or cfg["installer"] != file[1]["modify"]):
                    cfg["installer"] = file[1]["modify"]
                    getDepends.append(file[0])
                    installerChecked = True
                    installerName = file[0]
            if (patchRe.match(file[0])):
                if ((not "patcher" in cfg) or cfg["patcher"] != file[1]["modify"]):
                    cfg["patcher"] = file[1]["modify"]
                    getDepends.append(file[0])
                    patcherChecked = True
                    patcherName = file[0]
            
            if (patcherChecked and installerChecked):
                shouldLoop = False
            else:
                time.sleep(15)

    # download dependencies we'll need
    for depend in getDepends:
        if depend == installerName:
            if os.path.isfile(TEMP_ROOT + "/installer.exe"):
                logging.info("deleting old installer")
                os.remove(TEMP_ROOT + "/installer.exe")
            
            dependFile = open(TEMP_ROOT + "/installer.exe", "wb")
        else:
            if os.path.isfile(TEMP_ROOT + "/patcher.exe"):
                logging.info("deleting old patcher")
                os.remove(TEMP_ROOT + "/patcher.exe")
            
            dependFile = open(TEMP_ROOT + "/patcher.exe", "wb")
        def depend_cb(block):
            dependFile.write(block)

        logging.info("downloading " + depend)
        connection.retrbinary('RETR ' + depend, depend_cb)
        dependFile.close()

    # Download the backup file
    if (not os.path.isfile(TEMP_ROOT + "/" + RESTORE_FILE)):
        connection.cwd("/ddms/backups")

        restoreFile = open(TEMP_ROOT + "/" + RESTORE_FILE, "wb")
        def restore_cb(block):
            restoreFile.write(block)

        logging.info("downloading " + restoreFile.name)
        connection.retrbinary('RETR ' + RESTORE_FILE, restore_cb)
        restoreFile.close()

    # Save last modified so we won't download it again later
    with open('toolkit.cfg', 'r+') as cfgFile:
        json.dump(cfg, cfgFile)
        cfgFile.close()
    
    connection.quit()

def install(appName):
    logging.info("Starting install of [" + appName + "].")

    with open(LOG_FILE, "a+") as out:
        baseCmds = [TEMP_ROOT + "/installer.exe", "/S", "-app_name", appName, "-master", "-install_number", "0"]
    
        procezz = subprocess.Popen(baseCmds, stdout=out, stderr=out, cwd=os.getcwd())
        result = procezz.wait()

    if (result is not 0):
        exitWithFailure("Toolkit is exiting with failure, install subprocess failed.")

    exitIfErrorInLogs()

def restore(appName, bkpFile=None):
    if bkpFile is None:
        bkpFile = TEMP_ROOT + "/" + RESTORE_FILE
    
    manager("-restore", appName, "-filename", "\"" + bkpFile + "\"",)

    deleteRestoreTemp()

def backup(appName, bkpFile=None):
    if bkpFile is None:
        bkpFile = TEMP_ROOT + "/" + appName + "_GENERATED_BACKUP_"
    
    manager("-backup", appName, "-filename", "\"" + bkpFile + "\"")

# Doing a restore creates a temp directory which causes us to run out of space. Calling this function will delete that temp directory.
def deleteRestoreTemp():
    tempRE = re.compile("_temp_")
    tempFiles = os.listdir(TEMP_ROOT)
    for file in tempFiles:
        if os.path.isdir(TEMP_ROOT + "/" + file) and tempRE.match(file):
            logging.info("Deleting backup temp directory [" + TEMP_ROOT + "/" + file + "]")
            shutil.rmtree(TEMP_ROOT + "/" + file)

# Running the backup command generates a backup file. We're going to delete it with this function.
def deleteGeneratedBackups():
    tempRE = re.compile(".*_generated_backup_.*\.zip")
    tempFiles = os.listdir(TEMP_ROOT)
    for file in tempFiles:
        if os.path.isfile(TEMP_ROOT + "/" + file) and tempRE.match(file.lower()):
            logging.info("Deleting generated backup [" + TEMP_ROOT + "/" + file + "]")
            os.remove(TEMP_ROOT + "/" + file)

def patch():
    logging.info("Starting patch.")
    
    out = open(LOG_FILE,"a+")
    baseCmds = [TEMP_ROOT + "/patcher.exe", "/S"]
    
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
    out = open(LOG_FILE,"a+")
    
    baseCmds = [r'C:\WINDOWS\system32\WindowsPowerShell\v1.0\powershell.exe',
                             '-ExecutionPolicy',
                             'ByPass',
                            '-File',
                             MDSS_ROOT + '/manager/manager.ps1']
    baseCmds.extend(cmds)
    
    procezz = subprocess.Popen(baseCmds, stdout=out, stderr=out, cwd=os.getcwd())
    result = procezz.wait()
    out.close()

    if (result is not 0):
        exitWithFailure("Toolkit is exiting with failure, manager subprocess failed executing commands [" + ", ".join(cmds) + "]")

    exitIfErrorInLogs()

def uninstall():
    if os.path.isfile(MDSS_ROOT + "/uninstall.exe"):
        logging.info("Starting uninstall.")
    
        with open(LOG_FILE,"a+") as out:
            baseCmds = [MDSS_ROOT + "/uninstall.exe", "/S"]
            procezz = subprocess.Popen(baseCmds, stdout=out, stderr=out, cwd=os.getcwd())
            result = procezz.wait()

        if (result is not 0):
            exitWithFailure("Toolkit is exiting with failure, uninstall subprocess failed.")

        exitIfErrorInLogs()
        
        systemReboot()
    else:
        exitWithFailure("Unable to uninstall because the uninstall program did not exist.")

def exitWithFailure(msg):
    logging.error(msg)
    sys.stderr.write(msg)
    backupLogs()

    sendEmail("The recent DDMS build failed automated testing. Reason:\n" + msg)
    
    sys.exit(1)

def sendEmail(msg):
    # Send an email telling developers we failed
    server = smtplib.SMTP('email-smtp.us-west-2.amazonaws.com', 587)
    server.starttls()
    server.login('AKIAJJJLYB7NOSKKVK4Q', 'AlydUrfmU28gBEk9KD/ZosQi4wUYURD0vx1EiYMcBYvB')
    server.sendmail("rrowlands@terraframe.com", "rrowlands@terraframe.com", msg)
    server.quit()

def systemReboot():
    with open(LOG_FILE,"a+") as out:
        baseCmds = ["shutdown.exe", "-t", "0", "-r"]
        procezz = subprocess.Popen(baseCmds, stdout=out, stderr=out, cwd=os.getcwd())
        result = procezz.wait()

def backupLogs():
    if os.path.exists(MDSS_ROOT + "/logs"):
        shutil.copytree(MDSS_ROOT + "/logs", TESTING_ROOT + "/logs/mdsslogs")

    if os.path.exists(MDSS_ROOT + "/tomcat6/logs"):
        shutil.copytree(MDSS_ROOT + "/logs", TESTING_ROOT + "/logs/tomcatlogs")

    if os.path.exists(MDSS_ROOT + "/patch"):
        shutil.copytree(MDSS_ROOT + "/patch", TESTING_ROOT + "/logs/patch")

    if os.path.exists(MDSS_ROOT + "/installer-log.txt"):
        shutil.copyfile(MDSS_ROOT + "/installer-log.txt", TESTING_ROOT + "/logs/installer-log.txt")

    if os.path.exists(MDSS_ROOT + "/PostInstallSetup.log"):
        shutil.copyfile(MDSS_ROOT + "/PostInstallSetup.log", TESTING_ROOT + "/logs/PostInstallSetup.log")

    ts = time.time()
    st = datetime.datetime.fromtimestamp(ts).strftime('(%Y-%m-%d)-(%H-%M-%S)')
    backedUp = TESTING_ROOT + "/" + st
    logging.info("Saving results to [" + backedUp + "].")
    shutil.copytree(TESTING_ROOT + "/logs", backedUp)

# "error" is printed to the logs as part of normal server operation.... todo this needs to be fixed
logExitConditions = ["exception", "severe", "fatal", "The following commands are supported"]
def exitIfErrorInLogs():
    logFiles = []
    
    # Look at all logs in mdss/logs
    if os.path.exists(MDSS_ROOT + "/logs"):
        mdssLogs = os.listdir(MDSS_ROOT + "/logs")
        mdssLogs = [MDSS_ROOT + "/logs/" + fname for fname in mdssLogs]
        logFiles.extend(mdssLogs)

    # Look at all logs in tomcat
    if os.path.exists(MDSS_ROOT + "/tomcat6/logs"):
        tomcatLogs = os.listdir(MDSS_ROOT + "/tomcat6/logs")
        tomcatLogs = [MDSS_ROOT + "/tomcat6/logs/" + fname for fname in tomcatLogs]
        logFiles.extend(tomcatLogs)

    # Look at all logs in mdss/patch
    if os.path.exists(MDSS_ROOT + "/patch"):
        patchLogs = os.listdir(MDSS_ROOT + "/patch")
        patchLogs = [MDSS_ROOT + "/patch/" + fname for fname in patchLogs]
        logFiles.extend(patchLogs)

    if os.path.exists(LOG_FILE):
        logFiles.append(LOG_FILE)

    if os.path.exists(MDSS_ROOT + "/installer-log.txt"):
        logFiles.append(MDSS_ROOT + "/installer-log.txt")

    daemonRE = re.compile(".*commons-daemon.*\.log")
    for fileName in logFiles:
        if daemonRE.match(fileName):
            continue
        
        file = open(fileName)
        contents = file.read().lower()
        file.close()
        
        for bad in logExitConditions:
            if bad.lower() in contents:
                exitWithFailure("An error [" + bad + "] was detected in the log file [" + fileName + "].")

if __name__ == "__main__":
    toolkitMain()
