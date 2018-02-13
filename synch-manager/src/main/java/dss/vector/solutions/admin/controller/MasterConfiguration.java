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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.constants.DeployProperties;

public class MasterConfiguration extends ErrorConfiguration
{
  class ExportFilter implements FileFilter
  {
    private Set<String> filterNames;

    public ExportFilter()
    {
      this.filterNames = new TreeSet<String>();
      this.filterNames.add("common.properties");
      this.filterNames.add("terraframe.properties");
      this.filterNames.add("database.properties");
      this.filterNames.add("install.properties");
      // this.filterNames.add("style.css");
      this.filterNames.add("viewComponentOldWay.jsp");
      this.filterNames.add("login.jsp");
      this.filterNames.add("WEB-INF");
      this.filterNames.add("classes");
      this.filterNames.add("webDir");
      this.filterNames.add("sessionCache");
    }

    @Override
    public boolean accept(File file)
    {
      String fileName = file.getName();
      String path = file.getAbsolutePath();

      return ! ( path.contains(".svn") || filterNames.contains(fileName) );
    }
  }

  private String shellText;

  public MasterConfiguration(String shellText)
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
    return false;
  }

  @Override
  public List<String> getExportApplicationFiles()
  {
    List<String> paths = new LinkedList<String>();

    File root = new File(DeployProperties.getDeployPath());
    String rootPath = root.getAbsolutePath();

    addFiles(paths, root, rootPath);
    addFiles(paths, new File(DeployProperties.getJspDir()), rootPath);
    addFiles(paths, new File(DeployProperties.getJspDir() + File.separator + "classes"), rootPath);

    return paths;
  }

  private void addFiles(List<String> paths, File directory, String rootPath)
  {
    File[] files = directory.listFiles(new ExportFilter());

    for (File file : files)
    {
      paths.add(file.getAbsolutePath().replace(rootPath, ""));
    }
  }

  @Override
  public boolean getImportApplicationFiles()
  {
    return false;
  }
}
