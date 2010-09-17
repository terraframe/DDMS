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
import com.runwaysdk.constants.MdLocalizableInfo;
import com.runwaysdk.constants.ServerProperties;
import com.runwaysdk.constants.StructInfo;
import com.runwaysdk.dataaccess.EntityDAO;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeCharDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDimensionDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdLocalStructDAOIF;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.StructDAO;
import com.runwaysdk.dataaccess.StructDAOIF;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdAction;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDimension;
import com.runwaysdk.system.metadata.MdAttributeLocal;
import com.runwaysdk.system.metadata.MdAttributeLocalQuery;
import com.runwaysdk.system.metadata.MdIndex;
import com.runwaysdk.system.metadata.MdLocalizable;
import com.runwaysdk.system.metadata.MdMethod;
import com.runwaysdk.system.metadata.MdParameter;
import com.runwaysdk.system.metadata.Metadata;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.ontology.Term;

public class MdssLocalizationExporter implements Reloadable
{
  public static final String MANAGER_PROPERTIES = "Manager";
  public static final String MDSS_PROPERTIES = "UI Text";
  public static final String DISPLAY_LABELS = "Display Labels";
  public static final String DESCRIPTIONS = "Descriptions";
  public static final String TERM_LABELS = "Term Labels";
  public static final String COMMON_EXCEPTIONS = "Common Exceptions";
  public static final String CLIENT_EXCEPTIONS = "Client Exceptions";
  public static final String SERVER_EXCEPTIONS = "Server Exceptions";
  public static final String MD_EXCEPTIONS = "Custom Exceptions";

  /**
   * The in memory representation of the xls file
   */
  private HSSFWorkbook workbook;
  private HSSFSheet exceptionSheet;
  private HSSFSheet labelSheet;
  private HSSFSheet termSheet;
  private HSSFSheet descriptionSheet;
  private HSSFSheet clientSheet;
  private HSSFSheet serverSheet;
  private HSSFSheet commonSheet;
  private HSSFSheet propertySheet;
  private HSSFSheet managerSheet;
  private List<Locale> locales;
  private List<LocaleDimension> columns;
  private List<String> typeExemptions;

  @Request
  public static void main(String[] args) throws Exception
  {
    long start = System.currentTimeMillis();

    MdssLocalizationExporter exporter = new MdssLocalizationExporter();
    exporter.export();
    FileIO.write("localizer.xls", exporter.write());

    long stop = System.currentTimeMillis();
    System.out.println("Execution time: " + ( stop - start ) / 1000.0 + " seconds");
  }

  public MdssLocalizationExporter()
  {
    locales = new LinkedList<Locale>();
    columns = new LinkedList<LocaleDimension>();
    addLocaleDimensions(MdAttributeLocalInfo.DEFAULT_LOCALE);
    typeExemptions = new LinkedList<String>();
    typeExemptions.add(MdAction.CLASS);
    typeExemptions.add(MdParameter.CLASS);
    typeExemptions.add(MdIndex.CLASS);
    typeExemptions.add(MdMethod.CLASS);
    typeExemptions.add(MdAttributeDimension.CLASS);
  }

  public void addLocale(Locale l)
  {
    if (!locales.contains(l))
    {
      locales.add(l);
      addLocaleDimensions(l.toString());
    }
  }

  private void addLocaleDimensions(String localeString)
  {
    columns.add(new LocaleDimension(localeString));
    for (MdDimensionDAOIF dim : MdDimensionDAO.getAllMdDimensions())
    {
      columns.add(new LocaleDimension(localeString, dim));
    }
  }

  public void export()
  {
    workbook = new HSSFWorkbook();
    exceptionSheet = workbook.createSheet(MD_EXCEPTIONS);
    termSheet = workbook.createSheet(TERM_LABELS);
    labelSheet = workbook.createSheet(DISPLAY_LABELS);
    descriptionSheet = workbook.createSheet(DESCRIPTIONS);
    serverSheet = workbook.createSheet(SERVER_EXCEPTIONS);
    clientSheet = workbook.createSheet(CLIENT_EXCEPTIONS);
    commonSheet = workbook.createSheet(COMMON_EXCEPTIONS);
    propertySheet = workbook.createSheet(MDSS_PROPERTIES);
    managerSheet = workbook.createSheet(MANAGER_PROPERTIES);

    prepareExceptions();
    prepareDisplayLabels();
    prepareTermLabels();
    prepareDescriptions();
    prepareProperties("MDSS", propertySheet);
    prepareProperties("serverExceptions", serverSheet);
    prepareProperties("commonExceptions", commonSheet);
    prepareProperties("clientExceptions", clientSheet);
    prepareProperties("admin", managerSheet);
    prepareHeaders();
  }

  private void prepareHeaders()
  {
    HSSFSheet[] sheets = new HSSFSheet[]{exceptionSheet, serverSheet, clientSheet, commonSheet, labelSheet, termSheet, descriptionSheet, propertySheet, managerSheet};
    for (HSSFSheet sheet : sheets)
    {
      HSSFRow row = sheet.createRow(0);
      int i=0;

      boolean ignoreDimensions = sheet.equals(serverSheet) || sheet.equals(clientSheet) || sheet.equals(commonSheet) || sheet.equals(managerSheet);

      if (sheet.equals(labelSheet) || sheet.equals(termSheet) || sheet.equals(exceptionSheet) || sheet.equals(descriptionSheet))
      {
        row.createCell(i++).setCellValue(new HSSFRichTextString("Type"));
        row.createCell(i++).setCellValue(new HSSFRichTextString("Attribute Name"));
      }

      row.createCell(i++).setCellValue(new HSSFRichTextString("Key"));

      for (LocaleDimension c : columns)
      {
        if (ignoreDimensions && c.hasDimension())
        {
          continue;
        }
        row.createCell(i++).setCellValue(new HSSFRichTextString(c.getColumnName()));
      }

      for (short s=0; s<i; s++)
      {
        sheet.autoSizeColumn(s);
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

  private void prepareDisplayLabels()
  {
    QueryFactory qf = new QueryFactory();
    MdAttributeLocalQuery localQuery = new MdAttributeLocalQuery(qf);
    localQuery.WHERE(localQuery.getAttributeName().NE(Term.TERMDISPLAYLABEL));
    localQuery.WHERE(localQuery.getAttributeName().NE(MdLocalizableInfo.MESSAGE));
    localQuery.WHERE(localQuery.getAttributeName().NE(Metadata.DESCRIPTION));
    OIterator<? extends MdAttributeLocal> iterator = localQuery.getIterator();
    prepareAttributeList(labelSheet, iterator.getAll());
    iterator.close();
  }

  private void prepareDescriptions()
  {
//    List<MdAttributeLocal> list = new LinkedList<MdAttributeLocal>();
//    MdAttributeLocal local = (MdAttributeLocal) BusinessFacade.get(Metadata.getDescriptionMd());
//    list.add(local);
//    prepareAttributeList(descriptionSheet, list);

    QueryFactory qf = new QueryFactory();
    MdAttributeLocalQuery localQuery = new MdAttributeLocalQuery(qf);
    localQuery.WHERE(localQuery.getAttributeName().EQ(Metadata.DESCRIPTION));
    OIterator<? extends MdAttributeLocal> iterator = localQuery.getIterator();
    prepareAttributeList(descriptionSheet, iterator.getAll());
    iterator.close();
  }

  private void prepareExceptions()
  {
    List<MdAttributeLocal> list = new LinkedList<MdAttributeLocal>();
    MdAttributeLocal local = (MdAttributeLocal) BusinessFacade.get(MdLocalizable.getMessageMd());
    list.add(local);
    prepareAttributeList(exceptionSheet, list);
  }

  private void prepareTermLabels()
  {
    List<MdAttributeLocal> list = new LinkedList<MdAttributeLocal>();
    MdAttributeLocal local = (MdAttributeLocal) BusinessFacade.get(Term.getTermDisplayLabelMd());
    list.add(local);
    prepareAttributeList(termSheet, list);
  }

  private void prepareAttributeList(HSSFSheet sheet, List<? extends MdAttributeLocal> all)
  {
    int r = 1;
    for (MdAttributeLocal local : all)
    {
      MdTypeDAOIF mdType = MdTypeDAO.get(local.getValue(MdAttributeLocal.DEFININGMDCLASS));
      MdLocalStructDAOIF mdLocalStruct = (MdLocalStructDAOIF)BusinessFacade.getEntityDAO(local.getMdStruct());
      Boolean enforceSiteMaster = MdAttributeBooleanUtil.getTypeSafeValue(mdLocalStruct.getValue(MdLocalStructInfo.ENFORCE_SITE_MASTER));
      String definingType = mdType.definesType();
      String attributeName = local.getAttributeName();

      if (typeExemptions.contains(definingType))
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
          if (definedAttribute.equalsIgnoreCase(Metadata.ID) ||
                  definedAttribute.equalsIgnoreCase(Metadata.CREATEDBY) ||
                  definedAttribute.equalsIgnoreCase(Metadata.ENTITYDOMAIN) ||
                  definedAttribute.equalsIgnoreCase(Metadata.KEYNAME) ||
                  definedAttribute.equalsIgnoreCase(Metadata.LASTUPDATEDATE) ||
                  definedAttribute.equalsIgnoreCase(Metadata.LASTUPDATEDBY) ||
                  definedAttribute.equalsIgnoreCase(Metadata.LOCKEDBY) ||
                  definedAttribute.equalsIgnoreCase(Metadata.OWNER) ||
                  definedAttribute.equalsIgnoreCase(Metadata.SEQ) ||
                  definedAttribute.equalsIgnoreCase(Metadata.TYPE))
              {
                continue;
              }

          // Selectively get rid of create dates
          if (definedAttribute.equalsIgnoreCase(Metadata.CREATEDATE))
              {
      	  		if (mdType.definesType().equals("dss.vector.solutions.general.Email")) {
      	  			continue;
      	  		}
      	  		if (mdType.definesType().equals("com.runwaysdk.system.transaction.TransactionRecord")) {
      	  			continue;
      	  		}
              }

          if (definedAttribute.equalsIgnoreCase(Metadata.SITEMASTER))
              {
    	  		if (mdType.definesType().equals("com.runwaysdk.system.transaction.TransactionRecord")) {
      	  			continue;
      	  		}
              }
        }

        // Ignore attribute dimensions
        if (entity instanceof MdAttributeDimensionDAOIF)
        {
          continue;
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

        HSSFRow row = sheet.createRow(r++);
        int c=0;
        row.createCell(c++).setCellValue(new HSSFRichTextString(entity.getType()));
        row.createCell(c++).setCellValue(new HSSFRichTextString(attributeName));
        row.createCell(c++).setCellValue(new HSSFRichTextString(entity.getKey()));
//        row.createCell(c++).setCellValue(new HSSFRichTextString(struct.getValue(MdAttributeLocalInfo.DEFAULT_LOCALE)));

        for (LocaleDimension col : columns)
        {
          HSSFCell cell = row.createCell(c++);

          if (mdLocalStruct.definesAttribute(col.getAttributeName())==null)
          {
            continue;
          }

          String value = struct.getValue(col.getAttributeName());
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
    }

    // Now read each locale
    int c=0;
    boolean ignoreDimensions = sheet.equals(serverSheet) || sheet.equals(clientSheet) || sheet.equals(commonSheet) || sheet.equals(managerSheet);
    for (LocaleDimension l : columns)
    {
      if (ignoreDimensions && l.hasDimension())
      {
        continue;
      }
      c++;

      File localFile = new File(dir, l.getPropertyFileName(bundleName));
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

  public static Map<String, String> getProperties(List<String> lines)
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
