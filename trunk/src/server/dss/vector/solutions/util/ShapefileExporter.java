package dss.vector.solutions.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;

public class ShapefileExporter implements Reloadable
{
  private static final String SHAPEFILE_EXTRACTION_CMD = "pgsql2shp";

  private static final int    BUFFER_SIZE              = 4096;

  public void export(List<Layer> layers, OutputStream output)
  {
    File dir = this.createTempDir("shapefile");
    if (dir != null)
    {
      for (Layer layer : layers)
      {

        String name = layer.getLayerName();
        String dbView = layer.getViewName();

        this.pgsql2shp(dir, name, dbView);
      }
      this.zipDir(dir, output);
    }
  }

  private void zipDir(File dir, OutputStream output)
  {
    ZipOutputStream zos = new ZipOutputStream(output);

    byte[] buffer = new byte[BUFFER_SIZE];

    String[] dirList = dir.list();

    int count = 0;
    try
    {
      for (int i = 0; i < dirList.length; i++)
      {
        File f = new File(dir, dirList[i]);
        FileInputStream fis = new FileInputStream(f);
        zos.putNextEntry(new ZipEntry(f.getName()));
        while ( ( count = fis.read(buffer) ) != -1)
        {
          zos.write(buffer, 0, count);
        }
      }
      zos.finish();
    }
    catch (FileNotFoundException e)
    {
      // This should be impossible, since we're iterating over a directory
      // listing
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
    }
  }

  private synchronized File createTempDir(String prefix)
  {
    File dir = null;

    try
    {
      dir = File.createTempFile(prefix, "");
      dir.delete(); // Delete the tempfile
      dir.mkdir(); // Recreate it as a directory
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return dir;
  }

  private void pgsql2shp(File dir, String name, String viewName)
  {
    ArrayList<String> args = new ArrayList<String>();

    String bin = DatabaseProperties.getDatabaseBinDirectory();
    if (!bin.endsWith(File.separator))
    {
      bin += File.separator;
    }

    args.add(bin + SHAPEFILE_EXTRACTION_CMD);

    // Output file name
    args.add("-f");
    args.add(name);

    // Column containing geodata
    args.add("-g");
    args.add(QueryConstants.GEOMETRY_NAME_COLUMN);

    // Host / port
    args.add("-h");
    args.add(DatabaseProperties.getServerName());
    args.add("-p");
    args.add(Integer.toString(DatabaseProperties.getPort()));

    // Username / password
    args.add("-u");
    args.add(DatabaseProperties.getUser());
    args.add("-P");
    args.add(DatabaseProperties.getPassword());

    // Database name
    args.add(DatabaseProperties.getDatabaseName());

    // View/table name (or SQL)
    args.add(viewName);

    ProcessBuilder pb = new ProcessBuilder(args);
    pb.directory(dir);
    String output = this.run(pb);
  }

  private String run(ProcessBuilder pb)
  {
    this.print(pb);
    StringBuilder sb = new StringBuilder();

    String line = null;
    int exitVal = 0;
    try
    {
      Process pr = pb.start();
      BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
      while ( ( line = input.readLine() ) != null)
      {
        sb.append(line + "\n");
      }
      exitVal = pr.waitFor();
    }
    catch (IOException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (InterruptedException e)
    {
      // Do nothing);
    }

    if (exitVal != 0)
    {
      return sb.toString();
    }
    else
    {
      return null;
    }
  }

  private void print(ProcessBuilder pb)
  {
    String message = new String();
    for (String cmd : pb.command())
    {
      message += cmd + "\n";
    }
  }
}
