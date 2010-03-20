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

import com.runwaysdk.SystemException;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.constants.MdAttributeBooleanUtil;
import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.MdLocalStructInfo;
import com.runwaysdk.constants.ServerProperties;
import com.runwaysdk.constants.StructInfo;
import com.runwaysdk.dataaccess.EntityDAO;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeCharDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdLocalStructDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.StructDAO;
import com.runwaysdk.dataaccess.StructDAOIF;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.XMLParseException;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdLocalizableDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.StartSession;
import com.runwaysdk.system.metadata.MdAction;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeLocal;
import com.runwaysdk.system.metadata.MdAttributeLocalQuery;
import com.runwaysdk.system.metadata.MdIndex;
import com.runwaysdk.system.metadata.MdLocalizable;
import com.runwaysdk.system.metadata.MdLocalizableQuery;
import com.runwaysdk.system.metadata.MdMethod;
import com.runwaysdk.system.metadata.MdParameter;
import com.runwaysdk.system.metadata.MetaData;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.util.LocalizeUtil;

public class MdssLocalizationExporter implements Reloadable
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

  @StartSession
  public static void main(String[] args) throws Exception
  {
    long start = System.currentTimeMillis();

    MdssLocalizationExporter exporter = new MdssLocalizationExporter();
    exporter.addLocale(Locale.ENGLISH);
    exporter.addLocale(Locale.FRENCH);
    exporter.export();
    FileIO.write("localizer.xls", exporter.write());

    long stop = System.currentTimeMillis();
    System.out.println("Execution time: " + ( stop - start ) / 1000.0 + " seconds");
  }
  
  public MdssLocalizationExporter()
  {
    locales = new LinkedList<Locale>();
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
//    prepareTypes();
    prepareLocalizedAttributes();
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
      
      if (sheet.equals(labelSheet))
      {
        row.createCell(i++).setCellValue(new HSSFRichTextString("Type"));
        row.createCell(i++).setCellValue(new HSSFRichTextString("Attribute Name"));
      }
      
      row.createCell(i++).setCellValue(new HSSFRichTextString("Key"));
      row.createCell(i++).setCellValue(new HSSFRichTextString(MdAttributeLocalInfo.DEFAULT_LOCALE));
      for (Locale l : locales)
      {
        row.createCell(i++).setCellValue(new HSSFRichTextString(l.toString()));
      }
      
      for (short s=0; s<i; s++)
      {
        sheet.autoSizeColumn(s);
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
      setExceptionMessage(templates, row, c++, MdAttributeLocalInfo.DEFAULT_LOCALE);
      for (Locale l : locales)
      {
        setExceptionMessage(templates, row, c++, l.toString());
      }
    }
  }

  private void setExceptionMessage(Map<String, String> templates, HSSFRow row, int c, String localeString)
  {
    HSSFCell cell = row.createCell(c);
    String message = templates.get(localeString);
    if (message!=null)
    {
      cell.setCellValue(new HSSFRichTextString(message));
    }
  }
  
  private void prepareLocalizedAttributes()
  {
    List<String> exemptions = new LinkedList<String>();
    exemptions.add(MdAction.CLASS);
    exemptions.add(MdParameter.CLASS);
    exemptions.add(MdIndex.CLASS);
    exemptions.add(MdMethod.CLASS);
    
    int r=1;
    QueryFactory qf = new QueryFactory();
    MdAttributeLocalQuery localQuery = new MdAttributeLocalQuery(qf);
    for (MdAttributeLocal local : localQuery.getIterator())
    {
      MdTypeDAOIF mdType = MdTypeDAO.get(local.getValue(MdAttributeLocal.DEFININGMDCLASS));
      MdLocalStructDAOIF mdLocalStruct = (MdLocalStructDAOIF)BusinessFacade.getEntityDAO(local.getMdStruct());
      Boolean enforceSiteMaster = MdAttributeBooleanUtil.getTypeSafeValue(mdLocalStruct.getValue(MdLocalStructInfo.ENFORCE_SITE_MASTER));
      String definingType = mdType.definesType();
      String attributeName = local.getAttributeName();
      
      if (exemptions.contains(definingType) || attributeName.equalsIgnoreCase(MetaData.DESCRIPTION))
      {
        continue;
      }
      
      for (String id : EntityDAO.getEntityIdsDB(definingType))
      {
        EntityDAOIF entity = EntityDAO.get(id);
        StructDAOIF struct = StructDAO.get(entity.getValue(attributeName));
        
        // Don't export instances mastered at another site
        if (enforceSiteMaster && !struct.getValue(StructInfo.SITE_MASTER).equals(ServerProperties.getDomain()))
        {
          continue;
        }
        
        // Some attributes are re-created at the top of every hierarchy.  Ignore them.
        if (entity instanceof MdAttributeDAOIF)
        {
          MdAttributeDAOIF mdAttribute = (MdAttributeDAOIF) entity;
          String definedAttribute = mdAttribute.getValue(MdAttributeConcrete.ATTRIBUTENAME);
          if (definedAttribute.equalsIgnoreCase(MetaData.ID) ||
              definedAttribute.equalsIgnoreCase(MetaData.CREATEDATE) ||
              definedAttribute.equalsIgnoreCase(MetaData.CREATEDBY) ||
              definedAttribute.equalsIgnoreCase(MetaData.ENTITYDOMAIN) ||
              definedAttribute.equalsIgnoreCase(MetaData.ID) ||
              definedAttribute.equalsIgnoreCase(MetaData.KEYNAME) ||
              definedAttribute.equalsIgnoreCase(MetaData.LASTUPDATEDATE) ||
              definedAttribute.equalsIgnoreCase(MetaData.LASTUPDATEDBY) ||
              definedAttribute.equalsIgnoreCase(MetaData.LOCKEDBY) ||
              definedAttribute.equalsIgnoreCase(MetaData.OWNER) ||
              definedAttribute.equalsIgnoreCase(MetaData.SEQ) ||
              definedAttribute.equalsIgnoreCase(MetaData.SITEMASTER) ||
              definedAttribute.equalsIgnoreCase(MetaData.TYPE))
          {
            continue;
          }
        }
        
        // We don't want to export the attribute definitions of the locales on our MdLocalStructs 
        if (entity instanceof MdAttributeCharDAOIF)
        {
          MdAttributeCharDAOIF mdAttribute = (MdAttributeCharDAOIF) entity;
          MdClassDAOIF definedByClass = mdAttribute.definedByClass();
          if (definedByClass instanceof MdLocalStructDAOIF)
          {
            continue;
          }
        }
        
        HSSFRow row = labelSheet.createRow(r++);
        int c=0;
        row.createCell(c++).setCellValue(new HSSFRichTextString(entity.getType()));
        row.createCell(c++).setCellValue(new HSSFRichTextString(attributeName));
        row.createCell(c++).setCellValue(new HSSFRichTextString(entity.getKey()));
        row.createCell(c++).setCellValue(new HSSFRichTextString(struct.getValue(MdAttributeLocalInfo.DEFAULT_LOCALE)));
        
        for (Locale l : locales)
        {
          HSSFCell cell = row.createCell(c++);
          String localeString = l.toString();
          
          if (mdLocalStruct.definesAttribute(localeString)==null)
          {
            continue;
          }
          
          String value = struct.getValue(localeString);
          if (value.trim().length()>0)
          {
            cell.setCellValue(new HSSFRichTextString(value));
          }
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
    int c=1;
    for (Locale l : locales)
    {
      c++;
      
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
        String key = ExcelUtil.getString(row.getCell(0));
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
