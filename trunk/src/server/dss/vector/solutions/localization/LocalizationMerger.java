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
package dss.vector.solutions.localization;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.session.Request;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.util.MdssLocalizationExporter;
import dss.vector.solutions.util.MdssLocalizationImporter;

public class LocalizationMerger
{
  @Request
  public static void main(String[] args) throws Exception
  {
    long start = System.currentTimeMillis();
    File dir = new File("doc/localization/");
    File mergedFile = new File("doc/MergedLocalization.xls");
    
    switch (args.length)
    {
      case 2:
        mergedFile = new File(args[1]);
      case 1:
        dir = new File(args[0]);
      case 0:
        break;
      default:
        System.out.println("Usage: LocalizationMerger [dir] [outfile]");
        System.out.println("  -dir: The directory of localization files to merge");
        System.out.println("  -outfile: The output file");
    }
    
    File[] files = dir.listFiles(new FileFilter()
    {     
      @Override
      public boolean accept(File pathname)
      {
        return pathname.getAbsolutePath().endsWith(".xlsx") || pathname.getAbsolutePath().endsWith(".xls");
      }
    });
    
    Comparator<File> c = new Comparator<File>()
    {
      @Override
      public int compare(File o1, File o2)
      {
        return o1.getAbsoluteFile().compareTo(o2.getAbsoluteFile());
      }
    };
    
    List<File> list = Arrays.asList(files);
    Collections.sort(list, c);
    
    System.out.println("Importing files from " + dir.getAbsolutePath());
    for (File file : list)
    {
      System.out.println("  " + file.getName());
      MdssLocalizationImporter mli = new MdssLocalizationImporter();
      mli.read(new FileInputStream(file));
    }
    
    System.out.println("Exporting Merged file to " + mergedFile.getAbsolutePath());
    MdssLocalizationExporter exporter = new MdssLocalizationExporter();
    exporter.export();
    FileIO.write(mergedFile, exporter.write());
    
    long stop = System.currentTimeMillis();
    System.out.println("Execution time: " + ( stop - start ) / 1000.0 + " seconds");
    

    CacheShutdown.shutdown();
  }
}
