package dss.vector.solutions.admin.controller;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.manager.controller.ConfigurationAdapter;

public class MasterConfiguration extends ConfigurationAdapter
{
  @Override
  public boolean getExportStoredApplicationFiles()
  {
    return false;
  }

  @Override
  public List<String> getExportApplicationFiles()
  {
    List<String> files = new LinkedList<String>();

    files.add("/");

    return files;
  }
  
  @Override
  public boolean getImportApplicationFiles()
  {
    return false;
  }
}
