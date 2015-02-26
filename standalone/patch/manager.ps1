# Parameters read from the user calling our program
param (
    [string]$startApp = $null,
  [string]$stopApp = $null,
  [switch]$startTomcat,
  [switch]$stopTomcat,
  [switch]$getTomcatStatus,
  [string]$backup = $null,
  [string]$backupAll = $null,
  [string]$restore = $null,
  [string]$filename = $null
)

# Define some variables that we'll use for this script.
$basedir = split-path -parent $MyInvocation.MyCommand.Definition
$ddmsLoc = "$($basedir)\..\"
$java = $ddmsLoc + "Java\jdk1.6.0_16\bin\java.exe"
$username = "ddms"
$password = "ddms"
$url = "http://127.0.0.1:8080/manager/"
$memory = "-Xms128m -Xmx1028m -XX:MaxPermSize=512m"
$ssl = "`"-Djavax.rmi.ssl.client.enabledProtocols=TLSv1`" `"-Djavax.rmi.ssl.client.enabledCipherSuites=SSL_RSA_WITH_RC4_128_MD5`" `"-Djavax.net.ssl.trustStorePassword=1206b6579Acb3`" `"-Djavax.net.ssl.trustStore=$($ddmsLoc)manager\keystore\ddms.ts`" `"-Djavax.net.ssl.keyStorePassword=4b657920666fZ`" `"-Djavax.net.ssl.keyStore=$($ddmsLoc)manager\keystore\ddms.ks`" `"-Djava.endorsed.dirs=$($ddmsLoc)tomcat6\endorsed`""

function main()
{
  if ($startApp -ne $null -and $startApp -ne "")
  {
    $webclient = logIn
    startApp $webclient
  }
  elseif ($stopApp -ne $null -and $stopApp -ne "")
  {
    $webclient = logIn
    stopApp $webclient
  }
  elseif ($startTomcat.IsPresent)
  {
    startTomcat
  }
  elseif ($stopTomcat.IsPresent)
  {
    stopTomcat
  }
  elseif ($getTomcatStatus.IsPresent)
  {
    getTomcatStatus
  }
  elseif ($backup -ne $null -and $backup -ne "")
  {
    if ($filename -eq $null -or $filename -eq "")
    {
      echo "The filename parameter is required when using backup. Run this program again with -help for more information."
      return
    }
  
    backup
  }
  elseif ($backupAll -ne $null -and $backupAll -ne "")
  {
    backupAll
  }
  elseif ($restore -ne $null -and $restore -ne "")
  {
    if ($filename -eq $null -or $filename -eq "")
    {
      echo "The filename parameter is required when using restore. Run this program again with -help for more information."
      return
    }
  
    restore
  }
  else
  {
    help
  }
}

function backupAll()
{
  $managerLoc = getManagerPath "manager"
  $appStr = Get-Content "$($managerLoc)\classes\applications.txt"
  $apps = ($appStr -split '[\r\n]') |? {$_} 
  
  echo "`n---Backing up DDMS applications:`---`n"
  echo $appStr
  
  $webclient = logIn
  
  foreach ($app in $apps) {
    echo "`n---Beginning backup of $($app)---`n"
    
    $stopApp = $app
    stopApp $webclient
    
    $backup = $app
    $filename = $backupAll
    backup
    
    $startApp = $app
    startApp $webclient
  }
}

function getTomcatStatus()
{
  $classpath = getClasspath "manager" $null
  $cmd = "$($java) $($memory) $($ssl) -classpath `"$($classpath)`" dss.vector.solutions.manager.server.ServerStatus -g"
  
  Invoke-Expression $cmd
  
  $status = "invalid"
  if ($LASTEXITCODE -eq 10)
  {
    $status = "started"
  }
  elseif ($LASTEXITCODE -eq 11)
  {
    $status = "starting"
  }
  elseif ($LASTEXITCODE -eq 12)
  {
    $status = "stopped"
  }
  elseif ($LASTEXITCODE -eq 13)
  {
    $status = "stopping"
  }
  
  echo $status
}

function startTomcat()
{
  echo "Checking server status..."

  $status = getTomcatStatus
  
  if ($status -eq "stopped")
  {
    echo "Starting tomcat..."
  
    cmd.exe /c sc start Tomcat6
    
    $classpath = getClasspath "manager" $null
    $cmd = "$($java) $($memory) $($ssl) -cp `"$($classpath)`" dss.vector.solutions.manager.server.ServerStatus -s"
    
    Invoke-Expression $cmd
    
    if ($LASTEXITCODE -ne 0)
    {
      echo "Either a problem occurred that prevented tomcat from starting or we timed out waiting for it. Please contact your technical support staff."
    }
    else
    {
      echo "Tomcat was started successfully."
    }
  }
  else
  {
    echo "Tomcat must be stopped before you can start it. Your command has been ignored."
  }
}

function stopTomcat()
{
  echo "Checking server status..."

  $status = getTomcatStatus
  
  if ($status -eq "started")
  {
    echo "Stopping tomcat..."
    cmd.exe /c sc stop Tomcat6
    
    $classpath = getClasspath "manager" $null
    $cmd = "$($java) $($memory) $($ssl) -cp `"$($classpath)`" dss.vector.solutions.manager.server.ServerStatus -o"
    
    Invoke-Expression $cmd
    
    if ($LASTEXITCODE -ne 0)
    {
      echo "Either a problem occurred that prevented tomcat from stopping or we timed out waiting for it. Please contact your technical support staff."
    }
    else
    {
      echo "Tomcat was stopped successfully."
    }
  }
  else
  {
    echo "Tomcat must be fully started before you can stop it. Your command has been ignored."
  }
}

function logIn()
{
  $webclient = new-object System.Net.WebClient
  $webclient.Credentials = new-object System.Net.NetworkCredential($username, $password)
  
  return $webclient
}

function startApp($webclient)
{
  echo "Checking server status..."

  $status = getTomcatStatus
  
  if ($status -eq "started")
  {
    try
    {
      $output = $webclient.DownloadString($url + "start?path=/" + $startApp)
    }
    catch [System.Net.WebException] 
    {
      $statusCode = [int]$_.Exception.Response.StatusCode
      $output = "Error [" + $statusCode + "]: " + $_.Exception.Response.StatusDescription
    }

    echo $output
  }
  else
  {
    echo "Tomcat must be fully started before you can start an app. Your command has been ignored."
  }
}

function stopApp($webclient)
{
  echo "Checking server status..."

  $status = getTomcatStatus
  
  if ($status -eq "started")
  {
    try
    {
      $output = $webclient.DownloadString($url + "stop?path=/" + $stopApp)
    }
    catch [System.Net.WebException] 
    {
      $statusCode = [int]$_.Exception.Response.StatusCode
      $output = "Error [" + $statusCode + "]: " + $_.Exception.Response.StatusDescription
    }

    echo $output
  }
  else
  {
    echo "Tomcat must be fully started before you can stop an app. Your command has been ignored."
  }
}

function backup()
{
  $classpath = getClasspath "backup-manager" $backup
  $cmd = "$($java) $($memory) -cp `"$($classpath)`" com.runwaysdk.manager.action.BackupAction  --appName `"$($backup)`" --file `"$($filename)`""
  
  Invoke-Expression $cmd
}

function restore()
{
  $classpath = getClasspath "backup-manager" $restore
  $cmd = "$($java) $($memory) -cp `"$($classpath)`" com.runwaysdk.manager.action.RestoreAction --appName `"$($restore)`" --file `"$($filename)`""
  
  Invoke-Expression $cmd
}

# Searches to find the path to the manager
function getManagerPath($manager)
{
  Get-ChildItem -Path "$($ddmsLoc)manager\" -Filter "$($manager)-*" | `
  Foreach-Object{
    return $_.FullName
  }
}

# Returns the classpath for executing commands on the provided manager
function getClasspath($manager, $appName)
{
  $managerPath = getManagerPath("$($manager)")
  
  $cpArr = @()
  
  Get-ChildItem -Path "$($managerPath)\lib\" -Filter "*.jar" | `
  Foreach-Object{
    $cpArr += $_.FullName
  }
  
  $cp = $cpArr -join ";"
  
  $cp += ";$($managerPath)\classes"
  if ($appName -ne $null)
  {
    $cp += ";$($managerPath)\profiles\$($appName)"
  }
  
  return $cp
}

function help()
{
  echo "The following commands are supported:"
  echo "-start <appName>                        Starts the DDMS application by name <appName>."
  echo "-stop <appName>                         Stops the DDMS application by name <appName>."
  echo "-restore <appName> -filename <filePath> Starts a restore on <appName> from file <filePath>. This path can be either absolute or relative."
  echo "-backup <appName> -filename <filePath>  Starts backing up the DDMS application <appName> to a file <filePath>. This path can be either absolute or relative."
  echo "-backupAll <directory>                  Backs up all applications to the specified directory."
  echo "-getTomcatStatus                        Returns the current status of tomcat."
}

main
