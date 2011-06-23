package dss.vector.solutions.manager.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PropertyReader
{
  private String propertyPath;
  
  public PropertyReader(String propertyPath)
  {
    this.propertyPath = propertyPath;
  }
  
  public String getValue(String propertyName)
  {
    File props = new File(propertyPath);

    BufferedReader in = null;
    
    try
    {
      in = new BufferedReader(new FileReader(props));

      String line;

      while ( ( line = in.readLine() ) != null)
      {
        if(line != null && line.contains(propertyName))
        {
          line = line.replace(propertyName, "");
          line = line.replace("=", "");
          
          return line.trim();
        }
      }
    }
    catch (Exception e)
    {
    }
    finally
    {
      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }

    return null;
  }
}
