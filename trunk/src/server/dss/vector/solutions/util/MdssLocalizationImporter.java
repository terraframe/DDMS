package dss.vector.solutions.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import com.runwaysdk.constants.ServerProperties;
import com.runwaysdk.dataaccess.EntityDAO;
import com.runwaysdk.dataaccess.StructDAO;
import com.runwaysdk.dataaccess.attributes.entity.AttributeStruct;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.cache.ObjectCache;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.database.EntityDAOFactory;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.FileWriteException;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.dataaccess.metadata.SupportedLocaleDAO;
import com.runwaysdk.dataaccess.transaction.ActionEnumDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.SupportedLocale;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.InstallProperties;

public class MdssLocalizationImporter implements Reloadable
{
  private HSSFSheet  exceptionSheet;

  private HSSFSheet  termSheet;

  private HSSFSheet  entityLabelSheet;

  private HSSFSheet  labelSheet;

  private HSSFSheet  descriptionSheet;

  private HSSFSheet  clientSheet;

  private HSSFSheet  serverSheet;

  private HSSFSheet  commonSheet;

  private HSSFSheet  propertySheet;

  private HSSFSheet  managerSheet;

  private HSSFSheet  synchSheet;

  private HSSFSheet  geoSheet;

  private HSSFSheet  initializerSheet;

  private HSSFSheet  backupSheet;

  private static int modifiedCount = 0;

  public static void main(String[] args) throws FileNotFoundException
  {
    try
    {
      MdssLocalizationImporter.start(args);
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  @Request
  private static void start(String[] args) throws FileNotFoundException
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
    updateLocalAttribute(entityLabelSheet);
    updateLocalAttribute(labelSheet);
    updateLocalAttribute(descriptionSheet);
    updateProperties("MDSS", propertySheet);
    updateProperties("serverExceptions", serverSheet);
    updateProperties("commonExceptions", commonSheet);
    updateProperties("clientExceptions", clientSheet);

    // Copy over the manager properties
    updateProperties(InstallProperties.getManagerClasses(), "localization", managerSheet);
    updateProperties(InstallProperties.getSynchClasses(), "admin", synchSheet);
    updateProperties(InstallProperties.getGeoClasses(), "localization", geoSheet);
    updateProperties(InstallProperties.getInitializerClasses(), "localization", initializerSheet);
    updateProperties(InstallProperties.getBackupClasses(), "localization", backupSheet);
  }

  @SuppressWarnings("unused")
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
    HSSFSheet[] sheets = new HSSFSheet[] { exceptionSheet, termSheet, entityLabelSheet, descriptionSheet, serverSheet, clientSheet, commonSheet, labelSheet, propertySheet, managerSheet, synchSheet, geoSheet, initializerSheet, backupSheet };
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
    List<LocaleDimension> list = new ArrayList<LocaleDimension>();
    if (sheet == null)
    {
      return list;
    }

    HSSFRow row = sheet.getRow(0);
    Iterator<HSSFCell> cellIterator = row.cellIterator();
    cellIterator.next();

    if (sheet.equals(labelSheet) || sheet.equals(termSheet) || sheet.equals(entityLabelSheet) || sheet.equals(descriptionSheet) || sheet.equals(exceptionSheet))
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

  private void updateProperties(String bundle, HSSFSheet sheet)
  {
    try
    {
      File dir = FileIO.getDirectory(bundle + ".properties");

      this.updateProperties(dir, bundle, sheet);
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void updateProperties(File dir, String bundleName, HSSFSheet sheet)
  {
    // Bail if there's no tab for this bundle
    if (sheet == null)
    {
      return;
    }

    List<LocaleDimension> localeDimensions = this.getColumnHeaders(sheet);

    for (int i = 0; i < localeDimensions.size(); i++)
    {
      int cellIndex = i + 1;
      LocaleDimension localeDimension = localeDimensions.get(i);

      File file = new File(dir, localeDimension.getPropertyFileName(bundleName));

      Iterator<HSSFRow> rowIterator = sheet.rowIterator();
      rowIterator.next();

      Map<String, String> props = localeDimension.getPropertiesFromFile(file);

      while (rowIterator.hasNext())
      {
        HSSFRow row = rowIterator.next();
        String key = this.getStringValue(row.getCell(0));

        if (key == null)
        {
          continue;
        }

        HSSFCell cell = row.getCell(cellIndex);
        String value = getStringValue(cell);

        if (value != null)
        {
          props.put(key, value);
        }
        else
        {
          // If the cell is blank, ensure that the new file will not contain the
          // key, even if it did before
          props.remove(key);
        }
      }

      StringBuffer data = new StringBuffer();

      for (String key : props.keySet())
      {
        String value = props.get(key);

        data.append(key + "=" + value + "\n");
      }

      try
      {
        FileIO.write(file, data.toString());
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
    if (value == null)
    {
      return null;
    }

    if (value.length() == 0)
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
    if (sheet == null)
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

      // if (row.getRowNum()%50==0)
      // System.out.print(".");
    }
  }

  /**
   * Method to actually make the changes in the localized attribtues for a row.
   * Modified StructDAOs directly for performance reasons. If we modified the
   * MdTypeDAOs, that would trigger recompiles and cache reloads that are slow.
   * Instead we modify the Struct and tell the cache which entities need to be
   * refreshed.
   * 
   * 
   * @param localeDimensions
   * @param row
   * @param sheetName
   */
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
    EntityDAO entity;
    try
    {
      // Casting to DAO so we can update the cache for this object only, not a
      // complete rebuild
      entity = (EntityDAO) EntityDAO.get(type, key);

      // Casting to AttributeStruct so we can get the StructDAO reference
      AttributeStruct attributeStruct = (AttributeStruct) entity.getAttribute(attributeName);
      struct = attributeStruct.getStructDAO();
    }
    catch (DataNotFoundException e)
    {
      LocalizedRowIgnoredWarning warning = new LocalizedRowIgnoredWarning();
      warning.setSheet(sheetName);
      warning.setRow(row.getRowNum() + 1);
      warning.throwIt();

      return;
    }

    boolean apply = false;
    for (LocaleDimension ld : localeDimensions)
    {
      String value = getStringValue(row.getCell(c++));
      if (value == null)
      {
        value = new String();
      }

      String localeAttributeName = ld.getAttributeName();
      String oldValue = struct.getValue(localeAttributeName);

      // To speed things up, only set values that have changed
      if (!oldValue.equals(value))
      {
        struct.setValue(localeAttributeName, value);
        apply = true;
      }
    }

    // To speed things up, only apply if we actually changed a value
    if (apply)
    {
      struct.apply();

      ObjectCache.updateCache(entity);

      // We need to apply the entity to ensure that the localization changes are
      // logged in the transaction record
      if (ServerProperties.logTransactions() && (entity.getSiteMaster() != null && entity.getSiteMaster().length() > 0))
      {
        EntityDAOFactory.logTransactionItem(entity, ActionEnumDAO.UPDATE, true);
      }

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
      entityLabelSheet = workbook.getSheet(MdssLocalizationExporter.GEO_ENTITY_LABELS);
      descriptionSheet = workbook.getSheet(MdssLocalizationExporter.DESCRIPTIONS);
      clientSheet = workbook.getSheet(MdssLocalizationExporter.CLIENT_EXCEPTIONS);
      serverSheet = workbook.getSheet(MdssLocalizationExporter.SERVER_EXCEPTIONS);
      commonSheet = workbook.getSheet(MdssLocalizationExporter.COMMON_EXCEPTIONS);
      labelSheet = workbook.getSheet(MdssLocalizationExporter.DISPLAY_LABELS);
      propertySheet = workbook.getSheet(MdssLocalizationExporter.MDSS_PROPERTIES);
      managerSheet = workbook.getSheet(MdssLocalizationExporter.MANAGER_PROPERTIES);
      synchSheet = workbook.getSheet(MdssLocalizationExporter.SYNCH_PROPERTIES);
      geoSheet = workbook.getSheet(MdssLocalizationExporter.GEO_PROPERTIES);
      initializerSheet = workbook.getSheet(MdssLocalizationExporter.INITIALIZER_PROPERTIES);
      backupSheet = workbook.getSheet(MdssLocalizationExporter.BACKUP_PROPERTIES);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }

  /**
   * HSSF is pretty awful and doesn't have an way to get the name of a sheet, so
   * we have to do stuff like this instead.
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
    else if (sheet.equals(entityLabelSheet))
    {
      return MdssLocalizationExporter.GEO_ENTITY_LABELS;
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
