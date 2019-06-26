package com.runwaysdk.dataaccess.patch;

import java.io.File;
import java.io.FilenameFilter;

public class SvnFilenameFilter implements FilenameFilter
{
  @Override
  public boolean accept(File file, String filename)
  {
    return ! ( filename.endsWith(".svn") );
  }
}