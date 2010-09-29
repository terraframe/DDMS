package dss.vector.solutions.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.runwaysdk.SystemException;
import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.dataaccess.EntityDAO;
import com.runwaysdk.dataaccess.StructDAO;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.FileWriteException;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.dataaccess.metadata.SupportedLocaleDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.SupportedLocale;
import com.runwaysdk.util.FileIO;

public class MdssLocalizationImporter implements Reloadable
{
  private HSSFSheet    exceptionSheet;

  private HSSFSheet    termSheet;

  private HSSFSheet    labelSheet;

  private HSSFSheet    descriptionSheet;

  private HSSFSheet    clientSheet;

  private HSSFSheet    serverSheet;

  private HSSFSheet    commonSheet;

  private HSSFSheet    propertySheet;

  private HSSFSheet    managerSheet;

  private static int modifiedCount = 0;

  @Request
  public static void main(String[] args) throws FileNotFoundException
  {
    // Force the cache to boot so it's not included in our timing
    MetadataDAO.get(MdBusinessInfo.CLASS, MdBusinessInfo.CLASS);
    long start = System.currentTimeMillis();

    String fileName = "doc/DiseaseLocalizationDefaults.xls";
    File file = new File(fileName);
    if (args.length == 0)
    {
      if (file.exists())
      {
        System.out.println("No file name specified. Using default location: " + file.getAbsoluteFile());
      }
      else
      {
        System.out.println("No file name specified. Add file name as a comand line argument.");
        return;
      }
    }
    else
    {
      fileName = args[0];
      file = new File(fileName);
    }

    MdssLocalizationImporter mli = new MdssLocalizationImporter();
    mli.read(new FileInputStream(file));

    long stop = System.currentTimeMillis();
    System.out.println("\nImported in " + ( stop - start ) / 1000.0 + " seconds");
  }

  public MdssLocalizationImporter()
  {
  }

  @Request
  public void read(InputStream stream)
  {
    openStream(stream);

    checkLocales();

    updateLocalAttribute(exceptionSheet);
    updateLocalAttribute(termSheet);
    updateLocalAttribute(labelSheet);
    updateLocalAttribute(descriptionSheet);
    updateProperties("MDSS", propertySheet);
    updateProperties("serverExceptions", serverSheet);
    updateProperties("commonExceptions", commonSheet);
    updateProperties("clientExceptions", clientSheet);
    updateProperties("admin", managerSheet);
    copyAdminProperties();

    for (LocaleDimension ld : getColumnHeaders(propertySheet))
    {
      if (ld.hasDimension())
      {
        mergeProperties(ld);
      }
    }
  }

  private void copyAdminProperties()
  {
    if (managerSheet==null)
    {
      return;
    }
    
    ResourceBundle command = ResourceBundle.getBundle("command");
    File destination = new File(command.getString("manager.profiles"));
    if (!destination.exists())
    {
      return;
    }
    
    File source = null;
    try
    {
      source = FileIO.getDirectory("admin.properties");
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
      return;
    }
    
    class AdminFilter implements FilenameFilter, Reloadable
    {
      public boolean accept(File dir, String name)
      {
        if (name.startsWith("admin") && name.endsWith(".properties"))
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    }
    
    FileIO.copyFolder(source, destination, new AdminFilter());
  }

  private void mergeProperties(LocaleDimension child)
  {
    LocaleDimension parent = null;
    for (LocaleDimension option : getColumnHeaders(propertySheet))
    {
      if (child.isDimensionChildOf(option))
      {
        parent = option;
        break;
      }
    }

    if (parent == null)
    {
      return;
    }

    String parentFileName = parent.getPropertyFileName("MDSS");
    File dir;
    try
    {
      dir = FileIO.getDirectory("MDSS.properties");
    }
    catch (URISyntaxException e)
    {
      throw new SystemException(e);
    }

    File parentFile = new File(dir, parentFileName);
    File childFile = new File(dir, child.getPropertyFileName("MDSS"));
    List<String> parentLines;
    List<String> childLines;

    try
    {
      if (parentFile.exists())
      {
        parentLines = FileIO.readLines(parentFile);
      }
      else
      {
        parentLines = new LinkedList<String>();
      }
    }
    catch (IOException e)
    {
      throw new FileReadException(parentFile, e);
    }

    try
    {
      if (childFile.exists())
      {
        childLines = FileIO.readLines(childFile);
      }
      else
      {
        childLines = new LinkedList<String>();
      }
    }
    catch (IOException e)
    {
      throw new FileReadException(childFile, e);
    }

    Map<String, String> childProps = MdssLocalizationExporter.getProperties(childLines);
    Map<String, String> parentProps = MdssLocalizationExporter.getProperties(parentLines);
    parentProps.putAll(childProps);

    String data = new String();
    for (Entry<String, String> entry : parentProps.entrySet())
    {
      data += entry.getKey() + "=" + entry.getValue() + '\n';
    }

    try
    {
      FileIO.write(childFile, data);
    }
    catch (IOException e)
    {
      throw new FileWriteException(childFile, e);
    }
  }

  private void checkLocales()
  {
    Set<String> allLocales = new TreeSet<String>();
    HSSFSheet[] sheets = new HSSFSheet[]{exceptionSheet, termSheet, descriptionSheet, serverSheet, clientSheet, commonSheet, labelSheet, propertySheet, managerSheet};
    for (HSSFSheet sheet : sheets)
    {
      for (LocaleDimension ld : getColumnHeaders(sheet))
      {
        allLocales.add(ld.getLocaleString());
      }
    }

    for (String locale : allLocales)
    {
      if (locale.equalsIgnoreCase(MdAttributeLocalInfo.DEFAULT_LOCALE))
      {
        continue;
      }
      try
      {
        SupportedLocaleDAO.get(SupportedLocale.CLASS, SupportedLocale.CLASS + '.' + locale);
      }
      catch (DataNotFoundException e)
      {
        LocaleNotInstalledException localeNotInstalledException = new LocaleNotInstalledException();
        localeNotInstalledException.setLocaleString(locale);
        throw localeNotInstalledException;
      }
    }
  }

  @SuppressWarnings("unchecked")
  private List<LocaleDimension> getColumnHeaders(HSSFSheet sheet)
  {
    List<LocaleDimension> list = new LinkedList<LocaleDimension>();
    if (sheet==null)
    {
      return list;
    }

    HSSFRow row = sheet.getRow(0);
    Iterator<HSSFCell> cellIterator = row.cellIterator();
    cellIterator.next();

    if (sheet.equals(labelSheet) || sheet.equals(termSheet) || sheet.equals(descriptionSheet) || sheet.equals(exceptionSheet))
    {
      cellIterator.next();
      cellIterator.next();
    }

    while (cellIterator.hasNext())
    {
      HSSFCell cell = cellIterator.next();
      list.add(LocaleDimension.parseColumnHeader(getStringValue(cell)));
    }
    return list;
  }

  @SuppressWarnings("unchecked")
  private void updateProperties(String bundle, HSSFSheet sheet)
  {
    // Bail if there's no tab for this bundle
    if (sheet==null)
    {
      return;
    }

    File dir = null;
    try
    {
      dir = FileIO.getDirectory(bundle + ".properties");
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
    }

    int c = 0;
    for (LocaleDimension l : getColumnHeaders(sheet))
    {
      c++;
      String data = new String();
      Iterator<HSSFRow> rowIterator = sheet.rowIterator();
      rowIterator.next();
      while (rowIterator.hasNext())
      {
        HSSFRow row = rowIterator.next();
        String key = getStringValue(row.getCell(0));
        if (key == null)
        {
          continue;
        }

        HSSFCell cell = row.getCell(c);
        String value = getStringValue(cell);
        if (value == null)
        {
          value = new String();
        }

        data += key + '=' + value + '\n';
      }

      // Don't bother writing if no keys were specified
      if (data.length()==0)
      {
        continue;
      }

      String fileName = l.getPropertyFileName(bundle);
      File file = new File(dir, fileName);
      try
      {
        FileIO.write(file, data);
      }
      catch (IOException e)
      {
        throw new FileWriteException(file, e);
      }
    }
  }

  private String getStringValue(HSSFCell cell)
  {
    String value = ExcelUtil.getString(cell);
    if (value==null)
    {
      return null;
    }

    if (value.length()==0)
    {
      return null;
    }
    else
    {
      return value;
    }
  }

  @SuppressWarnings("unchecked")
  @Transaction
  private void updateLocalAttribute(HSSFSheet sheet)
  {
    // Bail if there's no tab for labels
    if (sheet==null)
    {
      return;
    }

    String sheetName = getSheetName(sheet);

    List<LocaleDimension> columnHeaders = getColumnHeaders(sheet);

    Iterator<HSSFRow> rowIterator = sheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext())
    {
      HSSFRow row = rowIterator.next();
      readLocalAttributeRow(columnHeaders, row, sheetName);

//      if (row.getRowNum()%50==0)
//        System.out.print(".");
    }
  }

  private void readLocalAttributeRow(List<LocaleDimension> localeDimensions, HSSFRow row, String sheetName)
  {
    int c = 0;
    String type = getStringValue(row.getCell(c++));
    String attributeName = getStringValue(row.getCell(c++));
    String key = getStringValue(row.getCell(c++));

    if (key == null || type == null || attributeName == null)
    {
      return;
    }

    StructDAO struct;
    try
    {
      struct = StructDAO.get(EntityDAO.get(type, key).getValue(attributeName)).getStructDAO();
    }
    catch (DataNotFoundException e)
    {
      LocalizedRowIgnoredWarning warning = new LocalizedRowIgnoredWarning();
      warning.setSheet(sheetName);
      warning.setRow(row.getRowNum()+1);
      warning.throwIt();

      return;
    }

    boolean apply = false;
    for (LocaleDimension ld : localeDimensions)
    {
      String value = getStringValue(row.getCell(c++));
      if (value != null)
      {
        String localeAttributeName = ld.getAttributeName();
        String oldValue = struct.getValue(localeAttributeName);

        // To speed things up, only set values that have changed
        if (!oldValue.equals(value))
        {
          struct.setValue(localeAttributeName, value);
          apply = true;
        }
      }
    }

    // To speed things up, only apply if we actually changed a value
    if (apply)
    {
      struct.apply();
      modifiedCount++;
    }
  }

  /**
   * Opens the stream and parses the sheet based on name
   *
   * @param stream
   */
  private void openStream(InputStream stream)
  {
    try
    {
      POIFSFileSystem fileSystem = new POIFSFileSystem(stream);
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
      exceptionSheet = workbook.getSheet(MdssLocalizationExporter.MD_EXCEPTIONS);
      termSheet = workbook.getSheet(MdssLocalizationExporter.TERM_LABELS);
      descriptionSheet = workbook.getSheet(MdssLocalizationExporter.DESCRIPTIONS);
      clientSheet = workbook.getSheet(MdssLocalizationExporter.CLIENT_EXCEPTIONS);
      serverSheet = workbook.getSheet(MdssLocalizationExporter.SERVER_EXCEPTIONS);
      commonSheet = workbook.getSheet(MdssLocalizationExporter.COMMON_EXCEPTIONS);
      labelSheet = workbook.getSheet(MdssLocalizationExporter.DISPLAY_LABELS);
      propertySheet = workbook.getSheet(MdssLocalizationExporter.MDSS_PROPERTIES);
      managerSheet = workbook.getSheet(MdssLocalizationExporter.MANAGER_PROPERTIES);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }

  /**
   * HSSF is pretty awful and doesn't have an way to get the name of a sheet, so we have to do stuff like this instead.
   *
   * @param sheet
   * @return Name of the sheet
   */
  private String getSheetName(HSSFSheet sheet)
  {
    if (sheet.equals(exceptionSheet))
    {
      return MdssLocalizationExporter.MD_EXCEPTIONS;
    }
    else if (sheet.equals(termSheet))
    {
      return MdssLocalizationExporter.TERM_LABELS;
    }
    else if (sheet.equals(descriptionSheet))
    {
      return MdssLocalizationExporter.DESCRIPTIONS;
    }
    else if (sheet.equals(clientSheet))
    {
      return MdssLocalizationExporter.CLIENT_EXCEPTIONS;
    }
    else if (sheet.equals(serverSheet))
    {
      return MdssLocalizationExporter.SERVER_EXCEPTIONS;
    }
    else if (sheet.equals(commonSheet))
    {
      return MdssLocalizationExporter.COMMON_EXCEPTIONS;
    }
    else if (sheet.equals(labelSheet))
    {
      return MdssLocalizationExporter.DISPLAY_LABELS;
    }
    else if (sheet.equals(propertySheet))
    {
      return MdssLocalizationExporter.MDSS_PROPERTIES;
    }
    else if (sheet.equals(managerSheet))
    {
      return MdssLocalizationExporter.MANAGER_PROPERTIES;
    }
    else
    {
      return "Unknown";
    }
  }
}
