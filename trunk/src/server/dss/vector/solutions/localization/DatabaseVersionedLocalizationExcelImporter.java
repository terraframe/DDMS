package dss.vector.solutions.localization;

import java.io.File;
import java.io.FileInputStream;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.constants.MdAttributeCharacterInfo;
import com.runwaysdk.dataaccess.CoreException;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.io.TimeFormat;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;

import dss.vector.solutions.util.MdssLocalizationImporter;

public class DatabaseVersionedLocalizationExcelImporter
{
  private static Logger logger = LoggerFactory.getLogger(DatabaseVersionedLocalizationExcelImporter.class);
  
  public static final String LOCALIZATION_VERSION_TIMESTAMP_PROPERTY = "DDMS00000000000000002";
  // delete from dynamic_properties where id ='DDMS00000000000000002'
  
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
  
  private boolean removeExisting;
  
  public DatabaseVersionedLocalizationExcelImporter(String location)
  {
    initialize(location, true);
  }
  
  public DatabaseVersionedLocalizationExcelImporter(String location, boolean removeExisting)
  {
    initialize(location, removeExisting);
  }
  
  private void initialize(String location, boolean removeExisting)
  {
    this.removeExisting = removeExisting;
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
    List<String> values = Database.getPropertyValue(LOCALIZATION_VERSION_TIMESTAMP_PROPERTY);

    for (String str : values)
    {
      String timestamp = str.substring(8);
      
      timestamps.add(new Date(Long.parseLong(timestamp)));
    }
  }
  
  protected void performDoIt(File file, Date timestamp)
  {
    // Only perform the doIt if this file has not already been imported
    if (!timestamps.contains(timestamp))
    {
      logger.info("Importing localization file [" + file.getAbsolutePath() + "] with removeExisting set to [" + removeExisting + "]");
      
      Database.addPropertyValue(Database.VERSION_NUMBER, MdAttributeCharacterInfo.CLASS, "LOCALIZ-" + new TimeFormat(timestamp.getTime()).format().substring(8), LOCALIZATION_VERSION_TIMESTAMP_PROPERTY);

      try
      {
        MdssLocalizationImporter mli = new MdssLocalizationImporter();
        mli.read(new FileInputStream(file), removeExisting);
      }
      catch (FileNotFoundException e)
      {
        throw new CoreException(e);
      }
      
      timestamps.add(timestamp);
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
  }
  
  public static void main(String[] args)
  {
    try
    {
      DatabaseVersionedLocalizationExcelImporter.run(args);
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }
  
  @Request
  public static void run(String[] args)
  {
    boolean removeExisting = true;
    
    if (args.length < 1)
    {
      String errMsg = "One argument is required for DatabaseLocalizationExcelVersioning:\n" + "  1) Location of the folder containing the schema(version date).xls files\n";
      throw new CoreException(errMsg);
    }
    if (args.length == 2)
    {
      removeExisting = Boolean.parseBoolean(args[1]);
    }

    try
    {
      new DatabaseVersionedLocalizationExcelImporter(args[0], removeExisting).doAll();
    }
    catch (ParseException e)
    {
      throw new CoreException(e);
    }
  }
  
  private static class ExcelTimestampFile
  {
    private static final String DATE_PATTEN  = "\\d{4,}";

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
        Pattern namePattern = Pattern.compile(NAME_PATTERN);
        
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
