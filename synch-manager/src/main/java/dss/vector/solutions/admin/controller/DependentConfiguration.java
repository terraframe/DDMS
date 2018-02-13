/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
