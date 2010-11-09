package dss.vector.solutions.admin.controller;

import com.runwaysdk.manager.controller.ConfigurationAdapter;

public class SlaveConfiguration extends ConfigurationAdapter
{
  @Override
  public boolean getExportStoredApplicationFiles()
  {
    return true;
  }
  
  @Override
  public boolean getImportApplicationFiles()
  {
    return true;
  }
}
