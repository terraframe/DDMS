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
package dss.vector.solutions.ontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.runwaysdk.constants.MdAttributeCharacterInfo;
import com.runwaysdk.dataaccess.CoreException;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.io.TimeFormat;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;

public class DatabaseVersionedOntologyExcelImporter
{
  public static final String ONTOLOGY_VERSION_TIMESTAMP_PROPERTY = "DDMS00000000000000001";
  // delete from dynamic_properties where id ='DDMS00000000000000001'
  
  class VersionComparator implements Comparator<File>
  {
    public int compare(File arg0, File arg1)
    {
      return new ExcelTimestampFile(arg0).compareTo(new ExcelTimestampFile(arg1));
    }
  }
  
  /**
   * List of timestamps which have already been imported
   */
  protected Set<Date> timestamps;
  
  /**
   * List of all schema files in the given location in order from earliest to
   * latest
   */
  protected Set<File>       ordered;

  /**
   * Mapping between a file and its timestamp
   */
  protected Map<Date, File> map;
  
  protected boolean rebuildAllpaths;
  
  public DatabaseVersionedOntologyExcelImporter(String location)
  {
    this.rebuildAllpaths = false;
    this.map = new HashMap<Date, File>();
    this.ordered = new TreeSet<File>(new VersionComparator());

    File directory = new File(location);

    for (File file : ExcelTimestampFile.getTimestampedFiles(directory))
    {
      ordered.add(file);

      map.put(new ExcelTimestampFile(file).getDate(), file);
    }

    timestamps = new TreeSet<Date>();

    // Get a list of all the imported versions
    List<String> values = Database.getPropertyValue(ONTOLOGY_VERSION_TIMESTAMP_PROPERTY);

    for (String timestamp : values)
    {
      timestamps.add(new Date(Long.parseLong(timestamp)));
    }
  }
  
  protected void performDoIt(File file, Date timestamp)
  {
    // Only perform the doIt if this file has not already been imported
    if (!timestamps.contains(timestamp))
    {
      Database.addPropertyValue(Database.VERSION_NUMBER, MdAttributeCharacterInfo.CLASS, new TimeFormat(timestamp.getTime()).format(), ONTOLOGY_VERSION_TIMESTAMP_PROPERTY);

      try
      {
        OntologyExcelImporter.main(new String[]{file.getAbsolutePath()});
      }
      catch (FileNotFoundException e)
      {
        throw new CoreException(e);
      }
      
      timestamps.add(timestamp);
      
      rebuildAllpaths = true;
    }
  }
  
  @Transaction
  public void performDoIt(List<File> files)
  {
    for (File file : files)
    {
      Date date = new ExcelTimestampFile(file).getDate();

      this.performDoIt(file, date);
    }
  }
  
  public void doAll() throws ParseException
  {
    List<File> list = new LinkedList<File>(ordered);

    this.performDoIt(list);
    
    if (rebuildAllpaths)
    {
      AllPaths.main(new String[]{});
    }
  }
  
  public static void main(String[] args)
  {
    try
    {
      DatabaseVersionedOntologyExcelImporter.run(args);
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }
  
  @Request
  public static void run(String[] args)
  {
    if (args.length < 1)
    {
      String errMsg = "One argument is required for DatabaseOntologyExcelVersioning:\n" + "  1) Location of the folder containing the schema(version date).xml files\n";
      throw new CoreException(errMsg);
    }

    try
    {
      new DatabaseVersionedOntologyExcelImporter(args[0]).doAll();
    }
    catch (ParseException e)
    {
      throw new CoreException(e);
    }
  }
  
  private static class ExcelTimestampFile
  {
    private static final String DATE_PATTEN  = "\\d{13,}";

    private static final String NAME_PATTERN = "^[A-Za-z_\\-\\d\\.]*\\((" + DATE_PATTEN + ")\\)[A-Za-z_\\-\\d\\.]*.xls$";

    private File                file;

    public ExcelTimestampFile(File file)
    {
      this.file = file;
    }
    
    public Long getTimestamp()
    {
      Pattern namePattern = Pattern.compile(NAME_PATTERN);
      Matcher nameMatcher = namePattern.matcher(file.getName());

      if (nameMatcher.find())
      {
        Long timeStamp = Long.parseLong(nameMatcher.group(1));
        return timeStamp;
      }

      return null;
    }

    public Date getDate()
    {
      Long timeStamp = getTimestamp();

      if (timeStamp != null)
      {
        return new Date(timeStamp);
      }

      return null;
    }

    public int compareTo(ExcelTimestampFile file)
    {
      return this.getTimestamp().compareTo(file.getTimestamp());
    }

    public static List<File> getTimestampedFiles(File directory)
    {
      List<File> list = new LinkedList<File>();

      if (directory.isDirectory())
      {
        Pattern namePattern = Pattern.compile(ExcelTimestampFile.NAME_PATTERN);

        for (File file : directory.listFiles())
        {
          String name = file.getName();
          Matcher nameMatcher = namePattern.matcher(name);

          if (nameMatcher.find())
          {
            list.add(file);
          }
        }
      }

      return list;
    }
  }
}
