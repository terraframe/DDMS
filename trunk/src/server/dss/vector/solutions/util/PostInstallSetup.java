package dss.vector.solutions.util;

import java.io.File;
import java.io.IOException;

import com.runwaysdk.util.FileIO;

public class PostInstallSetup
{
  public static void main(String[] args) throws Exception
  {
    String installationNumber = args[0];
    editFile(installationNumber + ".mdss.ivcc.com", "domain", "C:/MDSS/tomcat6/webapps/DDMS/WEB-INF/classes/terraframe.properties");
    
    Boolean isMaster = Boolean.parseBoolean(args[1]);
    
    if (isMaster)
    {
      editFile("true", "master", "C:/MDSS/tomcat6/webapps/DDMS/WEB-INF/classes/install.properties");
      editFile("0.mdss.ivcc.com", "domain", "C:/MDSS/tomcat6/webapps/DDMS/WEB-INF/classes/terraframe.properties");
      editFile("0.mdss.ivcc.com", "domain", "C:/MDSS/manager/profiles/manager/common/terraframe.properties");
    }
    else
    {
      editFile("false", "master", "C:/MDSS/tomcat6/webapps/DDMS/WEB-INF/classes/install.properties");
      editFile(installationNumber + ".mdss.ivcc.com", "domain", "C:/DDMS/tomcat6/webapps/DDMS/WEB-INF/classes/terraframe.properties");
      editFile(installationNumber + ".mdss.ivcc.com", "domain", "C:/MDSS/manager/profiles/manager/common/terraframe.properties");
    }
  }
  
  private static void editFile(String newValue, String key, String path) throws IOException
  {
    File file = new File(path);
    String data = new String();
    for (String line : FileIO.readLines(file))
    {
      if (!line.startsWith(key + '='))
      {
        data += line;
      }
      else
      {
        data += key + '=' + newValue;
      }
      data += "\n";
    }
    FileIO.write(file, data);
  }
}
