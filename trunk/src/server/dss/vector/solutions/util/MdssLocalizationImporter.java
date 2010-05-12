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
    // Force teh cache to boot so it's not included in our timing
    MetadataDAO.get(MdBusinessInfo.CLASS, MdBusinessInfo.CLASS);
    long start = System.currentTimeMillis();

    MdssLocalizationImporter mli = new MdssLocalizationImporter();
    mli.read(new FileInputStream("testLoc.xls"));

    long stop = System.currentTimeMillis();
    System.out.println("\nModified " + modifiedCount + " rows in " + ( stop - start ) / 1000.0 + " seconds");
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
  }

  private void checkLocales()
  {
    Set<String> allLocales = new TreeSet<String>();
    HSSFSheet[] sheets = new HSSFSheet[]{customSheet, serverSheet, clientSheet, commonSheet, labelSheet, propertySheet, controlPanelSheet};
    for (HSSFSheet sheet : sheets)
    {
      allLocales.addAll(parseLocales(sheet));
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
  private List<String> parseLocales(HSSFSheet sheet)
  {
    List<String> locales = new LinkedList<String>();
    
    if (sheet==null)
    {
      return locales;
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
      String localeString = getStringValue(cell);
      if (localeString != null)
      {
        locales.add(localeString);
      }
    }
    
    return locales;
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
    for (String l : parseLocales(sheet))
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

      String fileName;
      if (l.equals(MdAttributeLocalInfo.DEFAULT_LOCALE))
      {
        fileName = bundle + ".properties";
      }
      else
      {
        fileName = bundle + '_' + l + ".properties";
      }

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
    
    List<String> locales = parseLocales(labelSheet);
    
    Iterator<HSSFRow> rowIterator = labelSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext())
    {
      HSSFRow row = rowIterator.next();
      readLabelRow2(locales, row);
      
      if (row.getRowNum()%50==0)
        System.out.print(".");
    }
  }

  private void readLabelRow2(List<String> locales, HSSFRow row)
  {
    int c = 0;
    String type = getStringValue(row.getCell(c++));
    String attributeName = getStringValue(row.getCell(c++));
    String key = getStringValue(row.getCell(c++));
    
    if (key == null || type == null || attributeName == null)
    {
      return;
    }
    
//    EntityDAO entity = EntityDAO.get(type, key).getEntityDAO();
    StructDAO struct = StructDAO.get(EntityDAO.get(type, key).getValue(attributeName)).getStructDAO();

    boolean apply = false;
    for (String localeString : locales)
    {
      String value = getStringValue(row.getCell(c++));
      if (value != null)
      {
//        String oldValue = entity.getStructValue(attributeName, localeString);
        String oldValue = struct.getValue(localeString);
        
        // To speed things up, only set values that have changed
        if (!oldValue.equals(value))
        {
//          entity.setStructValue(attributeName, localeString, value);
          struct.setValue(localeString, value);
          apply = true;
        }
      }
    }
    
    // To speed things up, only apply if we actually changed a value
    if (apply)
    {
//      entity.apply();
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
      for (String localeString : parseLocales(customSheet))
      {
        String value = getStringValue(row.getCell(c++));
        if (value != null)
        {
          allTemplates.put(localeString, value);
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
