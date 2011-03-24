package dss.vector.solutions.admin.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.constants.DeployProperties;
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
    List<String> paths = new LinkedList<String>();

    String root = DeployProperties.getDeployPath();
    File dir = new File(root);

    File[] files = dir.listFiles(new FileFilter()
    {      
      @Override
      public boolean accept(File file)
      {
        return !(file.getAbsolutePath().contains(".svn"));
      }
    });

    for (File file : files)
    {
      String path = file.getAbsolutePath().replace(root, "");

      paths.add(path);
    }

    return paths;
  }

  @Override
  public boolean getImportApplicationFiles()
  {
    return false;
  }
}
