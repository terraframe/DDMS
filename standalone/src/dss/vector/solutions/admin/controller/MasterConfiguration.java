package dss.vector.solutions.admin.controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.controller.ConfigurationAdapter;

public class MasterConfiguration extends ConfigurationAdapter
{
  @Override
  public boolean getExportStoredApplicationFiles()
  {
    return true;
  }

  @Override
  public List<String> getExportApplicationFiles()
  {
    List<String> files = new LinkedList<String>();

    files.add(File.separator + "WEB-INF" + File.separator + "dss");
    files.add(File.separator + "js");
    files.add(File.separator + "css");
    files.add(File.separator + "imgs");
    files.add(File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "com");
    files.add(File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "dss");
    files.add(File.separator + "WEB-INF" + File.separator + "lib");

    return files;
  }
  
  @Override
  public boolean getImportApplicationFiles()
  {
    return false;
  }
}
