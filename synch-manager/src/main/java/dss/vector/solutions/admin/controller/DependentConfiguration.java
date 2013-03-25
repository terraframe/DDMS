package dss.vector.solutions.admin.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.LinkedList;

import com.runwaysdk.constants.DeployProperties;

public class DependentConfiguration extends ErrorConfiguration
{
  private String shellText;

  public DependentConfiguration(String shellText)
  {
    this.shellText = shellText;
  }

  @Override
  public String getShellText()
  {
    return this.shellText;
  }

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

  @Override
  public Collection<String> getFilesToDeleteOnImport()
  {
    Collection<String> paths = new LinkedList<String>();

    FileFilter filter = new FileFilter()
    {
      @Override
      public boolean accept(File file)
      {
        String path = file.getAbsolutePath();
        return ! ( path.contains(".svn") || path.contains(".properties") || path.contains(".xml") || path.contains(".xsd") || path.contains(".tld") );
      }
    };

    File classesDirectory = new File(DeployProperties.getJspDir() + File.separator + "classes");
    File sourceDirectory = new File(DeployProperties.getJspDir() + File.separator + "source");
    File libDirectory = new File(DeployProperties.getJspDir() + File.separator + "lib");

    addFiles(paths, filter, classesDirectory);
    addFiles(paths, filter, sourceDirectory);
    addFiles(paths, filter, libDirectory);

    return paths;
  }

  private void addFiles(Collection<String> paths, FileFilter filter, File directory)
  {
    File root = new File(DeployProperties.getDeployPath());
    String rootPath = root.getAbsolutePath();

    File[] files = directory.listFiles(filter);

    for (File file : files)
    {
      paths.add(file.getAbsolutePath().replace(rootPath, ""));
    }
  }
}
