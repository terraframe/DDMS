package com.runwaysdk.dataaccess.patch;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.RunwayVersion;
import com.runwaysdk.constants.DatabaseProperties;
import com.runwaysdk.dataaccess.database.AbstractDatabase;
import com.runwaysdk.dataaccess.database.DatabaseFactory;
import com.runwaysdk.util.FileIO;

public class RunwayPatcher
{
  private File             patchDir;

  private File             libDir;

  private AbstractDatabase database;

  public RunwayPatcher(File patchDir, File properties, File libDir)
  {
    this.patchDir = patchDir;
    this.libDir = libDir;

    this.database = new DatabaseFactory().createDatabase(properties);
  }

  public RunwayPatcher(File patchDir, DatabaseProperties properties, File libDir)
  {
    this.patchDir = patchDir;
    this.libDir = libDir;

    this.database = new DatabaseFactory().createDatabase(properties);
  }

  public List<RunwayPatch> getPatches()
  {
    List<RunwayPatch> patches = new LinkedList<RunwayPatch>();

    // Loop through all of the version directories for the patch directory
    File[] versionDirectories = patchDir.listFiles(new SvnFilenameFilter());

    for (File versionDirectory : versionDirectories)
    {
      patches.add(new RunwayPatch(versionDirectory));
    }

    /*
     * Important: The patches must be sorted such that the patches are returned
     * in ascending order. This is to ensure that when a patch is executed it
     * has all of previous patches already executed. Otherwise there may be gaps
     * in the metadata which will cause errors.
     */
    Collections.sort(patches, new Comparator<RunwayPatch>()
    {
      public int compare(RunwayPatch p1, RunwayPatch p2)
      {
        return p2.getRunwayVersion().compareTo(p1.getRunwayVersion());
      }
    });

    return patches;
  }

  public void patch()
  {
    RunwayVersion current = this.database.getRunwayVersion();
    String vendorName = this.database.getDatabaseProperties().getVendorName();
    
    System.out.println("Starting Runway Metadata patch. Current metadata version: " + current.toString() + ". VendorName: " + vendorName);

    List<RunwayPatch> patches = this.getPatches();

    for (RunwayPatch patch : patches)
    {
      RunwayVersion version = patch.getRunwayVersion();

      if (current == null || version.isGreater(current))
      {
        List<File> files = patch.getFilesToImport(vendorName);

        for (File file : files)
        {
          try
          {
            String sql = FileIO.readString(file);

            if (sql.length() > 0)
            {
              System.out.println("Executing SQL file [" + file.getAbsolutePath() + "] of version [" + version.toString() + "].");
              
              database.execute(sql);
            }
          }
          catch (Exception e)
          {
            throw new FileReadException(e);
          }
        }

        File[] sources = patch.getLibFiles();

        for (File source : sources)
        {
          try
          {
            File destination = new File(libDir.getAbsolutePath() + File.separator + source.getName());

            FileIO.copyFile(source, destination);
          }
          catch (Exception e)
          {
            throw new FileWriteException(e);
          }
        }

        database.setRunwayVersion(version);
        current = version;
      }
    }
  }

}
