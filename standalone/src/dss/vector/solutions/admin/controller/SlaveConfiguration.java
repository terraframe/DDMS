package dss.vector.solutions.admin.controller;

import com.runwaysdk.controller.ConfigurationAdapter;

public class SlaveConfiguration extends ConfigurationAdapter
{
  @Override
  public boolean getExportStoredApplicationFiles()
  {
    return false;
  }
  
  @Override
  public boolean getImportApplicationFiles()
  {
    return true;
  }
}
