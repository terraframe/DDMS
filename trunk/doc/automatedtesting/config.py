TESTING_ROOT = "C:/DDMSTesting"
TEMP_ROOT = TESTING_ROOT + "/downloads"
MDSS_ROOT = "C:/MDSS"
RESTORE_FILE = "india-2015_07_10-17_28_04.zip"

ftp = {
'host' : 'ftp.terraframe.com',
'user' : 'terraframe@terraframe.com',
'passwd' : '_Terra4Frame'
}

email = {
 "server"   : 'box737.bluehost.com',
 "port"     : "465",
 "username" :'builder@terraframe.com',
 "password" : '_Terra4Frame',
 "from"     : "builder@terraframe.com",
 "to"       : ["rrowlands@terraframe.com"]
    }

LOG_FILE = TESTING_ROOT + "/logs/toolkit.log"
ERR_LOG_FILE = TESTING_ROOT + "/logs/toolkit_err.log"

# "error" is printed to the logs as part of normal server operation.... todo this needs to be fixed
logExitConditions = ["exception", "severe", "fatal", "The following commands are supported"]