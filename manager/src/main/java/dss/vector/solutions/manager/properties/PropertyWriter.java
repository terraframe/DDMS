package dss.vector.solutions.manager.properties;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PropertyWriter
{
  private String propertyPath;
  
  public PropertyWriter(String propertyPath)
  {
    this.propertyPath = propertyPath;
  }

  public boolean write(String propertyName, String propertyValue)
  {
    boolean success = false;

    BufferedReader in = null;
    BufferedWriter out = null;

    File newprops = new File(propertyPath + ".new");
    File props = new File(propertyPath);

    try
    {
      in = new BufferedReader(new FileReader(props));
      out = new BufferedWriter(new FileWriter(newprops));
      String line;
      while ( ( line = in.readLine() ) != null)
      {
        if (line.trim().startsWith(propertyName))
        {
          out.write(propertyName + "=" + propertyValue);
        }
        else
        {
          out.write(line);
        }
        out.write("\n");
      }
      success = true;
    }
    catch (Exception e)
    {
      success = false;
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
          success = false;
        }
      }
      if (out != null)
      {
        try
        {
          out.close();
        }
        catch (IOException e)
        {
          success = false;
        }
      }
    }

    if (success)
    {
      success = false;
      File oldprops = new File(propertyPath + ".old");

      if (props.renameTo(oldprops))
      {
        if (newprops.renameTo(props))
        {
          oldprops.delete();
          success = true;
        }
      }
    }

    return success;
  }

}
