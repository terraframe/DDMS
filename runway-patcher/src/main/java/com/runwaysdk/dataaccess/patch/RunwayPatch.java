package com.runwaysdk.dataaccess.patch;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.RunwayVersion;

public class RunwayPatch
{
  public static final String          LIB_DIRECTORY = "lib";

  public static final String          SQL_DIRECTORY = "sql";

  private HashMap<String, List<File>> sqlFiles;

  private File[]                      libFiles;

  private RunwayVersion               version;

  public RunwayPatch(File directory)
  {
    this.version = new RunwayVersion(directory.getName());
    this.sqlFiles = new HashMap<String, List<File>>();
    
    File libDir = new File(directory.getAbsolutePath() + File.separator + LIB_DIRECTORY);
    if (libDir.exists())
    {
      this.libFiles = libDir.listFiles(new SvnFilenameFilter());
    }
    else
    {
      this.libFiles = new File[]{};
    }

    File sqlDir = new File(directory.getAbsolutePath() + File.separator + SQL_DIRECTORY);
    if (sqlDir.exists())
    {
      File[] vendors = sqlDir.listFiles(new SvnFilenameFilter());
      
      for (File vendor : vendors)
      {
        File[] array = vendor.listFiles(new FileFilter()
        {
          public boolean accept(File file)
          {
            return ( file.getAbsolutePath().endsWith(".sql") );
          }
        });
  
        List<File> files = new LinkedList<File>(Arrays.asList(array));
  
        Collections.sort(files, new Comparator<File>()
        {
          @Override
          public int compare(File file1, File file2)
          {
            return file1.getName().compareTo(file2.getName());
          }
        });
  
        this.sqlFiles.put(vendor.getName(), files);
      }
    }
  }

  public List<File> getFilesToImport(String vendorName)
  {
    if (this.sqlFiles.containsKey(vendorName))
    {
      return this.sqlFiles.get(vendorName);
    }

    return new LinkedList<File>();
  }

  public RunwayVersion getRunwayVersion()
  {
    return this.version;
  }

  public File[] getLibFiles()
  {
    return this.libFiles;
  }

}
