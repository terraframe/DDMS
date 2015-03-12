# Parameters read from the user calling our program
param (
  [string]$startApp = $null,
  [string]$stopApp = $null,
  [string]$backup = $null,
  [string]$restore = $null,
  [string]$backupAll = $null,
  [switch]$startTomcat,
  [switch]$stopTomcat,
  [switch]$getTomcatStatus,
  [switch]$scheduler,
  
  [string]$filename = $null
)
function help()
{
  echo "The following commands are supported:"
  echo "-startApp <appName>                     Starts the DDMS application by name <appName>."
  echo "-stopApp <appName>                      Stops the DDMS application by name <appName>."
  echo "-backup <appName> -filename <filePath>  Starts backing up the DDMS application <appName> to a file <filePath>. This path can be either absolute or relative."
  echo "-restore <appName> -filename <filePath> Starts a restore on <appName> from file <filePath>. This path can be either absolute or relative."
  echo "-backupAll <directory>                  Backs up all applications to the specified directory."
  echo "-startTomcat                            Starts Tomcat and all DDMS applications."
  echo "-stopTomcat                             Stops Tomcat and all DDMS applications."
  echo "-getTomcatStatus                        Returns the current status of Tomcat."
  echo "-scheduler                              Prints some information about running scripts in the Windows scheduler."
}

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
    startApp
  }
  elseif ($stopApp -ne $null -and $stopApp -ne "")
  {
    stopApp
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
    getTomcatStatus > $null
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
  elseif ($scheduler.IsPresent)
  {
    helpScheduler
  }
  else
  {
    help
  }
}

function helpScheduler
{
  echo "Here's an example of how to run a backupAll in the Windows scheduler:"
  echo "powershell -Command `"powershell -ExecutionPolicy ByPass -NoLogo -NonInteractive -NoProfile -WindowStyle Hidden -File C:\MDSS\manager\manager.ps1 -backupAll C:\MDSS\backups > C:\MDSS\backups\backup.log`""
}

function backupAll()
{
  $managerLoc = getManagerPath "manager"
  $appStr = Get-Content "$($managerLoc)\classes\applications.txt"
  $apps = ($appStr -split '[\r\n]') |? {$_} 
  
  echo "`n---Backing up DDMS applications:`---`n"
  echo $appStr
  
  foreach ($app in $apps) {
    echo "`n---Beginning backup of $($app)---`n"
    
    $stopApp = $app
    stopApp
    
    $backup = $app
    $filename = "$($backupAll)\$app"
    backup
    
    $startApp = $app
    startApp
  }
  
  echo "`n---DDMS backup complete---`n"
}

function getTomcatStatus()
{
  Write-Host "Checking server status..."

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
  
  Write-Host "Server is $($status)"
  return $status
}

function startTomcat()
{
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
      echo "Either a problem occurred that prevented tomcat from starting or we timed out waiting for it. Please contact your technical support staff (exit code $($LASTEXITCODE))."
    }
    else
    {
      echo "Tomcat was started successfully."
    }
  }
  else
  {
    echo "Tomcat must be stopped before you can start it."
  }
}

function stopTomcat()
{
  echo "stop tomcat"

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
      echo "Either a problem occurred that prevented tomcat from stopping or we timed out waiting for it. Please contact your technical support staff (exit code $($LASTEXITCODE))."
    }
    else
    {
      echo "Tomcat was stopped successfully."
    }
  }
  else
  {
    echo "Tomcat must be fully started before you can stop it."
  }
}

function getAppStatus($appName)
{
  Write-Host "Getting status of app $($appName)..."

  $resp = downloadUrl "$($url)list"
  
  $likeStr = "*"
  $likeStr += "$($appName)"
  $likeStr += ":running*"
  
  if ($resp -eq -1)
  {
    return -1
  }
  elseif ($resp -like $likeStr)
  {
    Write-Host "$($appName) is started."
    return 1
  }
  else
  {
    $notMatchStr = ".*"
    $notMatchStr += "$($appName)"
    $notMatchStr += ":.*"
    if ($resp -notMatch $notMatchStr)
    {
      Write-Host "The app name ($($appName)) is invalid. There is no app by that name on the server."
      return -2
    }
    else
    {
      Write-Host "$($appName) is not started."
      return 0
    }
  }
}

function downloadUrl($urlParam)
{
  try
  {
    $uri = New-Object "System.Uri" "$urlParam" 
    $request = [System.Net.HttpWebRequest]::Create($uri) 
    $request.Credentials = new-object System.Net.NetworkCredential($username, $password)
    $request.set_Timeout(60000) #60 second timeout 
    $response = $request.GetResponse()
    $totalLength = [System.Math]::Floor($response.get_ContentLength()/1024) 
    $responseStream = $response.GetResponseStream() 
    $targetStream = New-Object System.IO.MemoryStream
    $buffer = new-object byte[] 10KB 
    $count = $responseStream.Read($buffer,0,$buffer.length) 
    $downloadedBytes = $count 
    while ($count -gt 0) 
    {
      $targetStream.Write($buffer, 0, $count) 
      $count = $responseStream.Read($buffer,0,$buffer.length) 
      $downloadedBytes = $downloadedBytes + $count 
    }
    $targetStream.Flush()
    
    $targetStream.Position = 0;
    $sr = New-Object System.IO.StreamReader($targetStream)
    $str = $sr.ReadToEnd()
    
    $targetStream.Close() 
    $targetStream.Dispose() 
    $responseStream.Dispose() 
    
    return $str
  }
  Catch [System.Net.WebException] 
  {
    if ($_.Exception.Status -eq "ConnectFailure")
    {
      Write-Host "Unable to connect to the server, it may not be started."
    }
    else
    {
      #$statusCode = [int]$_.Exception.Response.StatusCode
      #$output = $_.Exception|format-list -force
      #Write-Host $output
      echo $_.Exception
    }
  }
  Catch
  {
    #$statusCode = [int]$_.Exception.Response.StatusCode
    #$output = $_.Exception|format-list -force
    #Write-Host $output
    echo $_.Exception
  }
  
  return -1
}

function startApp($webclient)
{
  echo "Starting app $($startApp)..."
  
  $out = downloadUrl($url + "start?path=/" + $startApp)
  echo $out
}

function stopApp($webclient)
{
  echo "Stopping app $($stopApp)..."
  
  $out = downloadUrl($url + "stop?path=/" + $stopApp)
  echo $out
}

function backup()
{
  $status = getAppStatus $backup
  
  if ($status -eq 0 -or $status -eq -1)
  {
    echo "Starting backup of app $($backup)..."
  
    $classpath = getClasspath "backup-manager" $backup
    $cmd = "$($java) $($memory) -cp `"$($classpath)`" com.runwaysdk.manager.action.BackupAction  --appName `"$($backup)`" --file `"$($filename)`""
    
    Invoke-Expression $cmd
  }
  elseif ($status -ne -2)
  {
    echo "The app ($($backup)) must be stopped before backing up."
  }
}

function restore()
{
  $status = getAppStatus $restore
  
  if ($status -eq 0 -or $status -eq -1)
  {
    echo "Starting restore of app $($restore)..."
  
    $classpath = getClasspath "backup-manager" $restore
    $cmd = "$($java) $($memory) -cp `"$($classpath)`" com.runwaysdk.manager.action.RestoreAction --appName `"$($restore)`" --file `"$($filename)`""
    
    Invoke-Expression $cmd
  }
  elseif ($status -ne -2)
  {
    echo "The app ($($restore)) must be stopped before restoring."
  }
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

main
