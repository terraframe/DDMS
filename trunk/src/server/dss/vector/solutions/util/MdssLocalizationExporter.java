package dss.vector.solutions.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.SystemException;
import com.terraframe.mojo.constants.ServerProperties;
import com.terraframe.mojo.dataaccess.MdLocalStructDAOIF;
import com.terraframe.mojo.dataaccess.io.FileReadException;
import com.terraframe.mojo.dataaccess.io.XMLParseException;
import com.terraframe.mojo.dataaccess.metadata.MdLocalizableDAO;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.MdAttribute;
import com.terraframe.mojo.system.metadata.MdAttributeConcrete;
import com.terraframe.mojo.system.metadata.MdClass;
import com.terraframe.mojo.system.metadata.MdEnumeration;
import com.terraframe.mojo.system.metadata.MdLocalizable;
import com.terraframe.mojo.system.metadata.MdLocalizableQuery;
import com.terraframe.mojo.system.metadata.MetaData;
import com.terraframe.mojo.system.metadata.MetaDataDisplayLabel;
import com.terraframe.mojo.system.metadata.MetaDataQuery;
import com.terraframe.mojo.util.FileIO;
import com.terraframe.mojo.util.LocalizeUtil;

public class MdssLocalizationExporter
{
  public static final String CONTROL_PANEL_PROPERTIES = "Control Panel";
  public static final String MDSS_PROPERTIES = "UI Text";
  public static final String DISPLAY_LABELS = "Display Labels";
  public static final String COMMON_EXCEPTIONS = "Common Exceptions";
  public static final String CLIENT_EXCEPTIONS = "Client Exceptions";
  public static final String SERVER_EXCEPTIONS = "Server Exceptions";
  public static final String MD_EXCEPTIONS = "Custom Exceptions";
  
  /**
   * The in memory representation of the xls file
   */
  private HSSFWorkbook workbook;
  private HSSFSheet customSheet;
  private HSSFSheet clientSheet;
  private HSSFSheet serverSheet;
  private HSSFSheet commonSheet;
  private HSSFSheet labelSheet;
  private HSSFSheet propertySheet;
  private HSSFSheet controlPanelSheet;
  private List<Locale> locales;
  
  public MdssLocalizationExporter()
  {
    locales = new LinkedList<Locale>();
    locales.add(Locale.ENGLISH);
  }
  
  public void addLocale(Locale l)
  {
    if (!locales.contains(l))
    {
      locales.add(l);
    }
  }
  
  public void export()
  {
    workbook = new HSSFWorkbook();
    customSheet = workbook.createSheet(MD_EXCEPTIONS);
    serverSheet = workbook.createSheet(SERVER_EXCEPTIONS);
    clientSheet = workbook.createSheet(CLIENT_EXCEPTIONS);
    commonSheet = workbook.createSheet(COMMON_EXCEPTIONS);
    labelSheet = workbook.createSheet(DISPLAY_LABELS);
    propertySheet = workbook.createSheet(MDSS_PROPERTIES);
    controlPanelSheet = workbook.createSheet(CONTROL_PANEL_PROPERTIES);
    
    prepareExceptions();
    prepareTypes();
    prepareProperties("MDSS", propertySheet);
    prepareProperties("serverExceptions", serverSheet);
    prepareProperties("commonExceptions", commonSheet);
    prepareProperties("clientExceptions", clientSheet);
    prepareProperties("MdssControlPanel", controlPanelSheet);
    prepareHeaders();
  }

  private void prepareHeaders()
  {
    HSSFSheet[] sheets = new HSSFSheet[]{customSheet, serverSheet, clientSheet, commonSheet, labelSheet, propertySheet, controlPanelSheet};
    for (HSSFSheet sheet : sheets)
    {
      HSSFRow row = sheet.createRow(0);
      int i=0;
      sheet.autoSizeColumn((short)i);
      row.createCell(i++).setCellValue(new HSSFRichTextString("Key"));
      for (Locale l : locales)
      {
        sheet.autoSizeColumn((short)i);
        row.createCell(i++).setCellValue(new HSSFRichTextString(l.toString()));
      }
    }
  }
  
  private void prepareExceptions()
  {
    int r=1;
    MdLocalizableQuery query = new MdLocalizableQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getPackageName());
    query.ORDER_BY_ASC(query.getTypeName());
    for (MdLocalizable localizable : query.getIterator())
    {
      MdLocalizableDAO dao = (MdLocalizableDAO)MdLocalizableDAO.get(localizable.getId());
      File xmlFile = dao.getXmlFile();
      Map<String, String> templates;
      try
      {
        templates = LocalizeUtil.getAllTemplates(xmlFile);
      }
      catch (IOException e)
      {
        throw new FileReadException(xmlFile, e);
      }
      catch (Exception e)
      {
        throw new XMLParseException(e);
      }
      
      HSSFRow row = customSheet.createRow(r++);
      int c = 0;
      row.createCell(c++).setCellValue(new HSSFRichTextString(localizable.definesType()));
      for (Locale l : locales)
      {
        HSSFCell cell = row.createCell(c++);
        String message = templates.get(l.toString());
        if (message!=null)
        {
          cell.setCellValue(new HSSFRichTextString(message));
        }
      }
    }
  }
  
  private void prepareTypes()
  {
    int r=1;
    QueryFactory qf = new QueryFactory();
    MetaDataQuery query = new MetaDataQuery(qf);
    query.ORDER_BY_ASC(query.getKeyName());
    for (MetaData md : query.getIterator())
    {
      if (!md.getSiteMaster().equalsIgnoreCase(ServerProperties.getDomain()))
      {
        continue;
      }
      
      MetaDataDisplayLabel label = null;
      if (md instanceof MdClass)
      {
        label = ((MdClass) md).getDisplayLabel();
      }
      if (md instanceof MdEnumeration)
      {
        label = ((MdEnumeration) md).getDisplayLabel();
      }
      if (md instanceof MdAttribute)
      {
        MdAttribute mdAttribute = (MdAttribute) md;
        String attributeName = mdAttribute.getValue(MdAttributeConcrete.ATTRIBUTENAME);
        if (attributeName.equalsIgnoreCase(MetaData.ID) ||
            attributeName.equalsIgnoreCase(MetaData.CREATEDATE) ||
            attributeName.equalsIgnoreCase(MetaData.CREATEDBY) ||
            attributeName.equalsIgnoreCase(MetaData.ENTITYDOMAIN) ||
            attributeName.equalsIgnoreCase(MetaData.ID) ||
            attributeName.equalsIgnoreCase(MetaData.KEYNAME) ||
            attributeName.equalsIgnoreCase(MetaData.LASTUPDATEDATE) ||
            attributeName.equalsIgnoreCase(MetaData.LASTUPDATEDBY) ||
            attributeName.equalsIgnoreCase(MetaData.LOCKEDBY) ||
            attributeName.equalsIgnoreCase(MetaData.OWNER) ||
            attributeName.equalsIgnoreCase(MetaData.SEQ) ||
            attributeName.equalsIgnoreCase(MetaData.SITEMASTER) ||
            attributeName.equalsIgnoreCase(MetaData.TYPE))
        {
          continue;
        }
          
        label = mdAttribute.getDisplayLabel();
      }
      
      if (label==null)
      {
        continue;
      }
      MdLocalStructDAOIF mdLocalStruct = label.getMdClass();
      
      HSSFRow row = labelSheet.createRow(r++);
      int c=0;
      row.createCell(c++).setCellValue(new HSSFRichTextString(md.getKeyName()));
      for (Locale l : locales)
      {
        HSSFCell cell = row.createCell(c++);
        String localeString = l.toString();
        
        if (c==2)
        {
          localeString = MetaDataDisplayLabel.DEFAULTLOCALE;
        }
        else if (mdLocalStruct.definesAttribute(localeString)==null)
        {
          continue;
        }
        
        String value = label.getValue(localeString);
        if (value.trim().length()>0)
        {
          cell.setCellValue(new HSSFRichTextString(value));
        }
      }
    }
  }
  
  @SuppressWarnings("unchecked")
  private void prepareProperties(String bundleName, HSSFSheet sheet)
  {
    URL url = Thread.currentThread().getContextClassLoader().getResource(bundleName + ".properties");
    File base = null;
    File dir = null;
    List<String> lines = null;
    try
    {
      base = new File(url.toURI());
      dir = base.getParentFile();
      lines = FileIO.readLines(base);
    }
    catch (Exception e)
    {
      if (base != null)
      {
        throw new FileReadException(base, e);
      }
      else
      {
        throw new SystemException(e);
      }
    }
    
    int r = 1;
    // First, read the keys from the default
    for (Entry<String, String> e : getProperties(lines).entrySet())
    {
      HSSFRow row = sheet.createRow(r++);
      row.createCell(0).setCellValue(new HSSFRichTextString(e.getKey()));
      row.createCell(1).setCellValue(new HSSFRichTextString(e.getValue()));
    }
    
    // Now read each locale
    int c=0;
    for (Locale l : locales)
    {
      c++;
      if (c==1)
      {
        continue;
      }
      
      File localFile = new File(dir, "MDSS_" + l.toString() + ".properties");
      if (!localFile.exists())
      {
        continue;
      }
      
      Map<String, String> properties = null;
      try
      {
        properties = getProperties(FileIO.readLines(localFile));
      }
      catch (IOException e)
      {
        throw new FileReadException(localFile, e);
      }
      
      Iterator<HSSFRow> rowIterator = sheet.rowIterator();
      while (rowIterator.hasNext())
      {
        HSSFRow row = rowIterator.next();
        String key = row.getCell(0).getRichStringCellValue().toString();
        String value = properties.get(key);
        
        if (value!=null)
        {
          row.createCell(c).setCellValue(new HSSFRichTextString(value));
        }
      }
    }
  }
  
  private Map<String, String> getProperties(List<String> lines)
  {
    LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
    for (String line : lines)
    {
      // Check to see if this line is a comment
      if (line.trim().startsWith("#"))
      {
        continue;
      }
      
      String[] split = line.split("=", 2);
      if (split.length!=2)
      {
        continue;
      }
      map.put(split[0], split [1]);
    }
    return map;
  }

  /**
   * Writes the workbook to a byte array, which can then be written to the
   * filesystem or streamed across the net.
   *
   * @return
   */
  public byte[] write()
  {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedOutputStream buffer = new BufferedOutputStream(bytes);
    try
    {
      workbook.write(buffer);
      buffer.flush();
      buffer.close();
      return bytes.toByteArray();
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }
}
