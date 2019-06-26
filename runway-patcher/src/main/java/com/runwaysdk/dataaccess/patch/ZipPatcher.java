package com.runwaysdk.dataaccess.patch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import com.runwaysdk.util.FileIO;

public class ZipPatcher
{
  public static final String DEFAULT_RESOURCE_NAME = "/patches.zip";

  public static final String TEMP_PATCH_NAME       = "patch_temp";

  private ZipInputStream     stream;

  private File               temp;

  private File               properties;

  private File               libDir;

  private ZipPatcher(File zipFile, File libDir) throws FileNotFoundException
  {
    String fileName = zipFile.getName();

    this.stream = new ZipInputStream(new FileInputStream(zipFile));
    this.libDir = libDir;

    this.temp = new File(zipFile.getParent() + File.separator + fileName.split("\\.")[0] + "_temp");
    this.temp.mkdirs();
  }

  public ZipPatcher(File zipFile, File properties, File libDir) throws FileNotFoundException
  {
    this(zipFile, libDir);

    this.properties = properties;
  }

  public ZipPatcher(ZipInputStream stream, File properties, File libDir)
  {
    this.stream = stream;
    this.properties = properties;
    this.libDir = libDir;

    this.temp = new File(TEMP_PATCH_NAME);
    this.temp.mkdirs();
  }

  public File getTemp()
  {
    return temp;
  }

  public void patch()
  {
    this.unzipFile();

    try
    {
      RunwayPatcher patcher = new RunwayPatcher(temp, properties, libDir);
      patcher.patch();
    }
    finally
    {
      try
      {
        FileIO.deleteDirectory(this.temp);
      }
      catch (IOException e)
      {
        throw new FileWriteException(e);
      }
    }
  }

  private void unzipFile()
  {
    try
    {
      FileIO.write(stream, temp);
    }
    catch (Exception e)
    {
      throw new FileWriteException(e);
    }
  }

  public static void main(String[] args)
  {
    if (args.length == 2)
    {
      InputStream stream = Object.class.getResourceAsStream(DEFAULT_RESOURCE_NAME);

      if (stream != null)
      {
        ZipInputStream zipInputStream = new ZipInputStream(stream);

        ZipPatcher patcher = new ZipPatcher(zipInputStream, new File(args[0]), new File(args[1]));
        patcher.patch();
      }
      else
      {
        String msg = "Unable to find the resource [" + DEFAULT_RESOURCE_NAME + "]";
        throw new UnknownResourceException(msg);
      }

    }
    else if (args.length == 3)
    {
      try
      {
        ZipPatcher patcher = new ZipPatcher(new File(args[0]), new File(args[1]), new File(args[2]));
        patcher.patch();
      }
      catch (FileNotFoundException e)
      {
        throw new FileReadException(e);
      }
    }
    else
    {
      String msg = "Invalid arguments.  Expected:\n -Location of the zip file\n -Location of the database properties file\n -Location of the lib file (optional)";

      System.err.println(msg);

      throw new InvalidArgumentException(msg);
    }
  }
}
