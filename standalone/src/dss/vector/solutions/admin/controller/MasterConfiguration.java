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

    File dir = new File(DeployProperties.getDeployPath());

    File[] files = dir.listFiles(new FileFilter()
    {
      @Override
      public boolean accept(File file)
      {
        String path = file.getAbsolutePath();
        boolean isSVN = path.contains(".svn");

        return !isSVN;
      }
    });

    for (File file : files)
    {
      paths.add(File.separator + file.getName());
    }

    return paths;
  }

  @Override
  public boolean getImportApplicationFiles()
  {
    return false;
  }
}
