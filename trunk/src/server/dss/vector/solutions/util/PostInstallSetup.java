package dss.vector.solutions.util;

import java.io.File;
import java.io.IOException;

import com.runwaysdk.util.FileIO;

public class PostInstallSetup
{
  public static void main(String[] args) throws Exception
  {
    editFile(args[0] + ".mdss.vector.solutions", "domain", "C:/MDSS/tomcat6/webapps/MDSS/WEB-INF/classes/terraframe.properties");
    editFile(args[1], "master", "C:/MDSS/tomcat6/webapps/MDSS/WEB-INF/classes/install.properties");
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
