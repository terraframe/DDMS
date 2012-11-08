package dss.vector.solutions.admin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionJspReader
{
  private String jspPath;

  public VersionJspReader(String jspPath)
  {
    this.jspPath = jspPath;
  }

  public String getValue(String propertyName)
  {
    File props = new File(jspPath);

    BufferedReader in = null;

    try
    {
      in = new BufferedReader(new FileReader(props));

      String line;

      while ( ( line = in.readLine() ) != null)
      {
        if (line != null && line.contains(propertyName))
        {
          Pattern pattern = Pattern.compile(".*value=\"([\\d|\\.]*)\".*");

          Matcher matcher = pattern.matcher(line);
          matcher.matches();

          String value = matcher.group(1);

          return value;
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
