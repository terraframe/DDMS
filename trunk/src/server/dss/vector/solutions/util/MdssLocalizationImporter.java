package dss.vector.solutions.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.xml.sax.SAXException;

import com.runwaysdk.SystemException;
import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.constants.MdLocalizableInfo;
import com.runwaysdk.dataaccess.EntityDAO;
import com.runwaysdk.dataaccess.StructDAO;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.FileWriteException;
import com.runwaysdk.dataaccess.io.XMLException;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdLocalizableDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.dataaccess.metadata.SupportedLocaleDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.StartSession;
import com.runwaysdk.system.metadata.MdLocalizable;
import com.runwaysdk.system.metadata.SupportedLocale;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.util.LocalizeUtil;

public class MdssLocalizationImporter implements Reloadable
{
  private HSSFSheet    customSheet;

  private HSSFSheet    clientSheet;

  private HSSFSheet    serverSheet;

  private HSSFSheet    commonSheet;

  private HSSFSheet    labelSheet;

  private HSSFSheet    propertySheet;

  private HSSFSheet    controlPanelSheet;

  private static int modifiedCount = 0;
  
  @StartSession
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

  @StartSession
  public void read(InputStream stream)
  {
    openStream(stream);
    
    checkLocales();

    updateProperties("MDSS", propertySheet);
    updateProperties("serverExceptions", serverSheet);
    updateProperties("commonExceptions", commonSheet);
    updateProperties("clientExceptions", clientSheet);
    updateProperties("MdssControlPanel", controlPanelSheet);
    updateExceptions();
    updateLabels();
    
    for (LocaleDimension ld : getColumnHeaders(propertySheet))
    {
      if (ld.hasDimension())
      {
        mergeProperties(ld);
      }
    }
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
      dir = FileIO.getDirectory(parentFileName);
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
      parentLines = FileIO.readLines(parentFile);
    }
    catch (IOException e)
    {
      throw new FileReadException(parentFile, e);
    }
    
    try
    {
      childLines = FileIO.readLines(childFile);
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
    HSSFSheet[] sheets = new HSSFSheet[]{customSheet, serverSheet, clientSheet, commonSheet, labelSheet, propertySheet, controlPanelSheet};
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
    
    if (sheet.equals(labelSheet))
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
        if (cell == null)
        {
          continue;
        }

        data += key + '=' + getStringValue(cell) + '\n';
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
  private void updateLabels()
  {
    // Bail if there's no tab for labels
    if (labelSheet==null)
    {
      return;
    }
    
    List<LocaleDimension> columnHeaders = getColumnHeaders(labelSheet);
    
    Iterator<HSSFRow> rowIterator = labelSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext())
    {
      HSSFRow row = rowIterator.next();
      readLabelRow(columnHeaders, row);
      
//      if (row.getRowNum()%50==0)
//        System.out.print(".");
    }
  }

  private void readLabelRow(List<LocaleDimension> localeDimensions, HSSFRow row)
  {
    int c = 0;
    String type = getStringValue(row.getCell(c++));
    String attributeName = getStringValue(row.getCell(c++));
    String key = getStringValue(row.getCell(c++));
    
    if (key == null || type == null || attributeName == null)
    {
      return;
    }
    
    StructDAO struct = StructDAO.get(EntityDAO.get(type, key).getValue(attributeName)).getStructDAO();

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

  @SuppressWarnings("unchecked")
  private void updateExceptions()
  {
    // If there's no tab for custom exceptions, bail
    if (customSheet==null)
    {
      return;
    }
    
    Iterator<HSSFRow> rowIterator = customSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext())
    {
      HSSFRow row = rowIterator.next();

      String key = getStringValue(row.getCell(0));
      if (key == null)
      {
        continue;
      }

      MdLocalizableDAO dao = (MdLocalizableDAO) MdLocalizableDAO.get(MdLocalizable.CLASS, key);

      File xmlFile = dao.getXmlFile();
      if (!xmlFile.exists())
      {
        try
        {
          FileIO.write(xmlFile, dao.getValue(MdLocalizableInfo.MESSAGES));
        }
        catch (IOException e)
        {
          throw new FileWriteException(xmlFile, e);
        }
      }
      
      String xmlString;
      try
      {
        xmlString = FileIO.readString(xmlFile);
      }
      catch (IOException e)
      {
        throw new FileReadException(xmlFile, e);
      }

      // Get all the the existing templates
      Map<String, String> allTemplates = getTemplates(xmlFile);
      int c = 1;
      // Add new template definitions, possibly overwriting old ones 
      for (LocaleDimension ld : getColumnHeaders(customSheet))
      {
        String value = getStringValue(row.getCell(c++));
        if (value != null)
        {
          allTemplates.put(ld.getAttributeName(), value);
        }
      }

      // Parse out the header and footer of the xml file
      int start = xmlString.indexOf("<locale");
      int end = xmlString.lastIndexOf("/locale>");
      String prefix = xmlString.substring(0, start).trim();
      String suffix = "\n" + xmlString.substring(end + 8).trim();

      // Reconstruct the body of the xml
      String middle = new String();
      for (Map.Entry<String, String> entry : allTemplates.entrySet())
      {
        String value = entry.getValue();
        if (value != null)
        {
          middle += "\n  <locale language=\"" + entry.getKey() + "\">" + value + "</locale>";
        }
      }

      try
      {
        // Write out the updated file
        FileIO.write(xmlFile, prefix + middle + suffix);
      }
      catch (IOException e)
      {
        throw new FileWriteException(xmlFile, e);
      }
    }
  }

  private Map<String, String> getTemplates(File xmlFile)
  {
    try
    {
      return LocalizeUtil.getAllTemplates(xmlFile);
    }
    catch (IOException e1)
    {
      throw new FileReadException(xmlFile, e1);
    }
    catch (SAXException e1)
    {
      throw new XMLException(e1);
    }
    catch (ParserConfigurationException e1)
    {
      throw new XMLException(e1);
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
      customSheet = workbook.getSheet(MdssLocalizationExporter.MD_EXCEPTIONS);
      clientSheet = workbook.getSheet(MdssLocalizationExporter.CLIENT_EXCEPTIONS);
      serverSheet = workbook.getSheet(MdssLocalizationExporter.SERVER_EXCEPTIONS);
      commonSheet = workbook.getSheet(MdssLocalizationExporter.COMMON_EXCEPTIONS);
      labelSheet = workbook.getSheet(MdssLocalizationExporter.DISPLAY_LABELS);
      propertySheet = workbook.getSheet(MdssLocalizationExporter.MDSS_PROPERTIES);
      controlPanelSheet = workbook.getSheet(MdssLocalizationExporter.CONTROL_PANEL_PROPERTIES);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }

}
