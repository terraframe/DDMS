package dss.vector.solutions.odk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.filters.StringInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.io.Files;
import com.runwaysdk.Pair;
import com.runwaysdk.RunwayExceptionIF;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdFieldDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.MdWebSingleTermGridDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExportSheet;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelSheetMetadata;
import com.runwaysdk.dataaccess.io.FormExcelExporter;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdWebFormDAO;
import com.runwaysdk.dataaccess.metadata.MdWebSingleTermGridDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutableJob;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

import dss.vector.solutions.ExcelImportHistory;
import dss.vector.solutions.ExcelImportJob;
import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.Email;
import dss.vector.solutions.general.EmailConfiguration;
import dss.vector.solutions.general.EmailConfigurationException;
import dss.vector.solutions.generator.FormColumnFactory;
import dss.vector.solutions.generator.FormImportFilter;
import dss.vector.solutions.generator.FormSurveyColumnFactory;
import dss.vector.solutions.generator.FormSurveyImportFilter;
import dss.vector.solutions.generator.GridExcelAdapter;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.generator.MultiTermListener;
import dss.vector.solutions.util.LocalizationFacade;

public class MobileDataUploadJob extends MobileDataUploadJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1002885144;

  private static Logger     logger           = LoggerFactory.getLogger(MobileDataUploadJob.class);

  public MobileDataUploadJob()
  {
    super();
  }

  @Override
  public void execute(ExecutionContext executionContext)
  {
    AllJobStatus status = doIt(executionContext.getJobHistory());

    executionContext.setStatus(status);
  }

  private AllJobStatus doIt(JobHistory history)
  {
    ODKForm form = ODKForm.factory(this.getFormType());

    return this.doIt(form, history);
  }

  private AllJobStatus doIt(ODKForm form, JobHistory history)
  {
    AllJobStatus mobileDataUploadJobStatus = AllJobStatus.FAILURE;

    File parent = Files.createTempDir();
    
    Throwable fatalError = null;
    ArrayList<Pair<String, ExcelImportHistory>> failedFiles = new ArrayList<Pair<String, ExcelImportHistory>>();

    try
    {
      ODK2Excel importer = new ODK2Excel(form, this.getQueryCursor());
      Collection<String> allUUIDS = importer.getUUIDs();

      if (allUUIDS.size() > 0)
      {
        Map<String, Collection<String>> groupedUUIDS = this.group(form, allUUIDS);

        Set<Entry<String, Collection<String>>> entries = groupedUUIDS.entrySet();

        for (Entry<String, Collection<String>> entry : entries)
        {
          Collection<String> uuids = entry.getValue();
          String username = entry.getKey();

          ExcelSheetMetadata metadata = new ExcelSheetMetadata();
          metadata.setValues(this.getDisease().getDisplayLabel());
          metadata.setValues(username);

          Map<String, ExcelExportSheet> sheets = this.createSheets(form, metadata);

          importer.export(uuids, sheets);

          XSSFWorkbook workbook = new XSSFWorkbook();

          for (Entry<String, ExcelExportSheet> e : sheets.entrySet())
          {
            ExcelExportSheet sheet = e.getValue();

            if (form.isExport(e.getKey()))
            {
              this.copySheetIntoWorkbook(workbook, sheet.getSheet(), sheet.getSheetName());
            }
          }

          File file = null;
          try
          {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String label = form.getViewMd().getDisplayLabel(Session.getCurrentLocale());

            String filename = label + "-" + username + "-" + format.format(importer.getExportDateTime()) + ".xlsx";

            file = new File(parent, filename);
            try (FileOutputStream fos = new FileOutputStream(file))
            {
              workbook.write(fos);
            }
          }
          catch (FileNotFoundException e)
          {
            logger.error("Unable to write file:" + e.getMessage());

            throw new ProgrammingErrorException(e);
          }
          catch (IOException e)
          {
            throw new ProgrammingErrorException(e);
          }

          String userId = this.getUser(username);
          String dimensionId = this.getDisease().getDimensionId();
          
          try
          {
            ExcelImportManager manager = ExcelImportManager.getNewInstance();
            manager.setUserId(userId);
            manager.setDimensionId(dimensionId);

            try (FileInputStream eis = new FileInputStream(file))
            {
              ExcelImportHistory result = manager.importAndWait(eis, new String[] {}, file.getName());

              AllJobStatus excelImportStatus = AllJobStatus.FAILURE;
              if (result != null)
              {
                Thread.sleep(100);
                result = ExcelImportHistory.get(result.getId());
                
                excelImportStatus = result.getStatus().get(0);
              }
              
              File archive = new File(ODKFacade.getArchivePath());
              archive.mkdirs();

              try (FileInputStream fis = new FileInputStream(file))
              {
                try (FileOutputStream fos = new FileOutputStream(new File(archive, file.getName())))
                {
                  IOUtils.copy(fis, fos);
                }
              }
              
              if (!excelImportStatus.equals(AllJobStatus.SUCCESS))
              {
                failedFiles.add(new Pair<String, ExcelImportHistory>(file.getName(), result));
              }
            }
          }
          catch (IOException e)
          {
            throw new ProgrammingErrorException(e);
          }
        }

        this.appLock();
        this.setQueryCursor(this.prepareCursor(importer.getCursor()));
        this.setLastExportDate(importer.getExportDateTime());
        this.apply();
      }
      else
      {
        logger.debug("No ODK data to export for type [" + form.getViewMd().definesType() + "]");
      }
    }
    catch (Throwable t)
    {
      fatalError = t;
    }
    finally
    {
      FileUtils.deleteQuietly(parent);
    }
    
    sendErrorEmail(fatalError, failedFiles);
    
    if (fatalError != null)
    {
      if (fatalError instanceof RuntimeException)
      {
        throw (RuntimeException) fatalError;
      }
      else
      {
        throw new RuntimeException(fatalError);
      }
    }
    else if (failedFiles.size() > 0)
    {
      mobileDataUploadJobStatus = AllJobStatus.WARNING;
    }
    else
    {
      mobileDataUploadJobStatus = AllJobStatus.SUCCESS;
    }
    
    return mobileDataUploadJobStatus;
  }
  
  private void sendErrorEmail(Throwable err, ArrayList<Pair<String, ExcelImportHistory>> files)
  {
    if (err != null || files.size() > 0)
    {
      String subject = LocalizationFacade.getFromBundles("mobileDataUpload.email.FailureSubject");
      
      StringBuilder bodyBuilder = new StringBuilder();
      
      /*
       * Build the email Body
       * template: {serverUrl}\n The Mobile Data Upload Job [{jobName}] has failed.\n{details}
       */
      String body = LocalizationFacade.getFromBundles("mobileDataUpload.email.FailureBody");
      body = body.replaceAll("\\\\n", "\n");
      String serverUrl = "http://localhost:8080/";
      try
      {
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("C:\\MDSS\\manager\\manager-1.0.0\\classes\\server.properties")));

        String hostname = prop.getProperty("security.server.hostname");
        Boolean https = Boolean.parseBoolean(prop.getProperty("https.enable"));
        
        if (hostname != null && hostname.length() > 0)
        {
          if (!hostname.startsWith("http"))
          {
            if (https)
            {
              hostname = "https://" + hostname;
            }
            else
            {
              hostname = "http://" + hostname;
            }
          }
          
          if (!hostname.endsWith("/"))
          {
            hostname = hostname + "/";
          }
          
          serverUrl = hostname;
        }
      } 
      catch (Throwable ex) {
        ex.printStackTrace();
      }
      
      // Add the app context
      serverUrl = serverUrl + CommonProperties.getDeployAppName() + "/";
      
      serverUrl = serverUrl + "dss.vector.solutions.generator.ExcelController.viewManager.mojo";
      body = body.replaceFirst("\\{serverUrl\\}", serverUrl);
      
      body = body.replaceFirst("\\{jobName\\}", this.getJobId());
      
      bodyBuilder.append(body);
      bodyBuilder.append("\n\n");
      
      /*
       * Append exception information
       */
      if (err != null)
      {
        String msg = ExecutableJob.getMessageFromException(err);
        bodyBuilder.append(msg);
        bodyBuilder.append("\n\n");
      }
      
      /*
       * Build the file details
       * template: The following files encountered errors when importing:\n{files}
       */
      if (files.size() > 0)
      {
        String details = LocalizationFacade.getFromBundles("mobileDataUpload.email.FailureFileDetails");
        details = details.replaceAll("\\\\n", "\n");
        
        StringBuilder fileBuilder = new StringBuilder();
        
        for (Pair<String, ExcelImportHistory> file : files)
        {
          ExcelImportHistory history = file.getSecond();
          
          if (history != null)
          {
            fileBuilder.append(file.getFirst() + " (" + history.getStatus().get(0).getDisplayLabel() + ")\n");
          }
          else
          {
            fileBuilder.append(file.getFirst() + "\n");
          }
        }
        
        details = details.replaceFirst("\\{files\\}", fileBuilder.toString());
        
        bodyBuilder.append(details);
        bodyBuilder.append("\n\n");
      }
      
      /*
       * Send the email
       */
      EmailConfiguration config = EmailConfiguration.getDefault();
      
      Email email = new Email();
      email.setToAddresses(config.getTo());
      email.setDisease(Disease.getCurrent());
      email.setFromAddress(config.getFrom());
      email.setSubject(subject);
      email.setBody(bodyBuilder.toString());
      email.apply();
      
      if (!email.send(config))
      {
        EmailConfigurationException emailEx = new EmailConfigurationException();
        
        String error = email.getError();
        error = error.substring(error.indexOf(": ")+1, error.length());
        emailEx.setExtra(error);
        
        throw emailEx;
      }
    }
  }

  private String prepareCursor(String cursor)
  {
    try
    {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
      Calendar calendar = Calendar.getInstance();

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = factory.newDocumentBuilder();
      Document document = docBuilder.parse(new StringInputStream(cursor));

      XPathFactory xPathfactory = XPathFactory.newInstance();
      XPath xpath = xPathfactory.newXPath();
      XPathExpression expr = xpath.compile("//uriLastReturnedValue");

      NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

      for (int i = 0; i < nodeList.getLength(); i++)
      {
        Node node = nodeList.item(i);
        node.getParentNode().removeChild(node);
      }

      expr = xpath.compile("//attributeValue");

      nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

      for (int i = 0; i < nodeList.getLength(); i++)
      {
        Node node = nodeList.item(i);
        String content = node.getTextContent();

        Date date = format.parse(content);
        calendar.clear();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, 1);
        date = calendar.getTime();

        String value = format.format(date) + "+0000";
        node.setTextContent(value);
      }

      // 2018-06-08T19:24:02.949
      //

      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer();
      // transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      // transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
      // "2");

      StringWriter sw = new StringWriter();
      DOMSource source = new DOMSource(document);
      transformer.transform(source, new StreamResult(sw));

      return sw.toString();
    }
    catch (Exception e)
    {
      logger.error(e.getMessage());

      return cursor;
    }
  }

  private void copySheetIntoWorkbook(Workbook workbook, Sheet oldSheet, String name)
  {
    Sheet newSheet = workbook.createSheet(name);
    Iterator<Row> rowIterator = oldSheet.rowIterator();
    while (rowIterator.hasNext())
    {
      Row oldRow = rowIterator.next();
      int rowNum = oldRow.getRowNum();
      Row newRow = newSheet.createRow(rowNum);

      Iterator<Cell> cellIterator = oldRow.cellIterator();
      while (cellIterator.hasNext())
      {
        Cell oldCell = cellIterator.next();
        int columnIndex = oldCell.getColumnIndex();
        Cell newCell = newRow.createCell(columnIndex);
        switch (oldCell.getCellType())
        {
          case Cell.CELL_TYPE_BOOLEAN:
            newCell.setCellValue(oldCell.getBooleanCellValue());
            break;
          case Cell.CELL_TYPE_FORMULA:
            newCell.setCellFormula(oldCell.getCellFormula());
            break;
          case Cell.CELL_TYPE_NUMERIC:
            newCell.setCellValue(oldCell.getNumericCellValue());
            break;
          case Cell.CELL_TYPE_STRING:
            newCell.setCellValue(oldCell.getRichStringCellValue());
            break;
          case Cell.CELL_TYPE_ERROR:
            newCell.setCellType(Cell.CELL_TYPE_ERROR);
            newCell.setCellValue(oldCell.getErrorCellValue());
            break;
        }

        if (rowNum == 2)
        {
          newSheet.setColumnWidth(columnIndex, oldSheet.getColumnWidth(columnIndex));
        }
      }
    }
  }

  private Map<String, ExcelExportSheet> createSheets(ODKForm form, ExcelSheetMetadata metadata)
  {
    HashMap<String, ExcelExportSheet> sheets = new LinkedHashMap<String, ExcelExportSheet>();

    createSheets(form, metadata, sheets);

    return sheets;
  }

  private void createSheets(ODKForm form, ExcelSheetMetadata metadata, Map<String, ExcelExportSheet> sheets)
  {
    // if (form.isExport())
    // {
    MdClassDAOIF target = form.getViewMd();
    String type = target.definesType();

    if (MdFormUtil.isFormBusinessPackage(type))
    {
      MdWebForm mdWebForm = MdFormUtil.getMdFormFromBusinessType(type);
      MdFormDAOIF mdForm = (MdFormDAOIF) MdFormDAO.get(mdWebForm.getId());
      String formType = mdForm.definesType();

      ExcelExporter exporter = this.getFormExporter(type);

      List<ExcelExportListener> listeners = new LinkedList<ExcelExportListener>();

      List<DynamicGeoColumnListener> geoListeners = MdFormUtil.getGeoListeners(mdForm, null);

      for (DynamicGeoColumnListener listener : geoListeners)
      {
        listeners.add(listener);
      }

      List<MultiTermListener> multiTermListeners = MdFormUtil.getMultiTermListeners(mdForm);

      for (MultiTermListener listener : multiTermListeners)
      {
        listeners.add(listener);
      }

      listeners.add(new UUIDExcelListener());

      ExcelExportSheet sheet = exporter.addTemplate(formType, metadata, listeners);

      sheets.put(type, sheet);
    }
    else if (MdFormUtil.isFormRelationshipPackage(type))
    {
      MdRelationshipDAOIF mdRelationship = (MdRelationshipDAOIF) target;
      MdBusinessDAOIF formMdBusiness = mdRelationship.getParentMdBusiness();
      MdWebForm mdForm = MdFormUtil.getMdFormFromBusinessType(formMdBusiness.definesType());
      MdWebSingleTermGrid mdWebGrid = MdFormUtil.getGridAttribute(mdForm, mdRelationship);

      MdWebFormDAOIF mdWebFormDAO = MdWebFormDAO.get(mdForm.getId());
      MdFieldDAOIF mdFieldDAO = MdWebSingleTermGridDAO.get(mdWebGrid.getId());

      GridExcelAdapter sheet = new GridExcelAdapter(mdWebFormDAO, (MdWebSingleTermGridDAOIF) mdFieldDAO, mdRelationship);
      sheet.addTemplate(mdRelationship.definesType());

      ExcelExporter exporter = new ExcelExporter(new XSSFWorkbook());
      exporter.addSheet(sheet);

      sheets.put(type, sheet);
    }
    else
    {
      // Setup the listeners excel export listeners
      ExcelExporter exporter = new ExcelExporter(new XSSFWorkbook());

      this.setupListener(exporter, target);

      exporter.addListener(new UUIDExcelListener());

      sheets.put(type, exporter.addTemplate(form.getViewMd().definesType(), metadata));
    }
    // }

    LinkedList<ODKFormJoin> joins = form.getJoins();

    for (ODKFormJoin join : joins)
    {
      if (join.isStandalone())
      {
        this.createSheets(join.getChild(), metadata, sheets);
      }
    }
  }

  public ExcelExporter getFormExporter(String type)
  {
    if (type.equals(FormSurvey.CLASS) || type.equals(FormHousehold.CLASS) || type.equals(FormBedNet.CLASS) || type.equals(FormPerson.CLASS))
    {
      return new FormExcelExporter(new FormSurveyImportFilter(), new FormSurveyColumnFactory(), new XSSFWorkbook());
    }

    return new FormExcelExporter(new FormImportFilter(), new FormColumnFactory(), new XSSFWorkbook());
  }

  private Map<String, Collection<String>> group(ODKForm form, Collection<String> uuids)
  {
    Map<String, Collection<String>> map = new HashMap<String, Collection<String>>();

    String tableName = getODKTableName(form.getFormId());

    for (String uuid : uuids)
    {
      if (uuid != null && uuid.length() > 0)
      {
        String username = getODKUser(tableName, uuid);

        if (!map.containsKey(username))
        {
          map.put(username, new LinkedList<String>());
        }

        map.get(username).add(uuid);
      }
    }

    return map;
  }

  private String getUser(String username)
  {
    return UserDAO.findUser(username).getId();
  }

  private void setupListener(ExcelExporter exporter, MdClassDAOIF target)
  {
    try
    {
      // Load the type which is being exported
      Class<?> c = LoaderDecorator.load(target.definesType());

      // Get the listener method
      Method method = c.getMethod(MDSSInfo.LISTENER_METHOD, ExcelExporter.class, String[].class);

      // Invoke the method and get the ExcelExportListener
      method.invoke(null, exporter, new String[] {});
    }
    catch (SecurityException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (NoSuchMethodException e)
    {
      // Do nothing if the method doesn't exist then continue
      logger.debug("Method [" + MDSSInfo.LISTENER_METHOD + "] does not exist on type [" + target.definesType() + "]");
    }
    catch (IllegalArgumentException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (IllegalAccessException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (InvocationTargetException e)
    {
      Throwable targetException = e.getTargetException();
      if (targetException instanceof RunwayExceptionIF)
      {
        throw (RuntimeException) targetException;
      }
      else
      {
        throw new ProgrammingErrorException(e);
      }
    }
  }

  public static String getODKUser(String tableName, String uuid)
  {
    Properties props = getODKProperties();

    String schema = props.getProperty("jdbc.schema");

    try (Connection connection = getConnection(props))
    {
      StringBuilder builder = new StringBuilder();
      builder.append("SELECT \"_CREATOR_URI_USER\", \"_LAST_UPDATE_URI_USER\"");
      builder.append(" FROM " + schema + ".\"" + tableName + "\" AS tab");
      builder.append(" WHERE tab.\"_URI\" = '" + uuid + "'");

      Statement statement = connection.createStatement();
      ResultSet results = statement.executeQuery(builder.toString());

      if (results.next())
      {
        String updator = results.getString("_LAST_UPDATE_URI_USER");
        String creator = results.getString("_CREATOR_URI_USER");
        String uri = updator != null ? updator : creator;

        String username = uri.split("\\|")[0].replaceFirst("uid:", "");

        return username;
      }
      else
      {
        throw new ProgrammingErrorException("Unable to find the ODK Table Name for the ODK Form with id [" + uuid + "]");
      }
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public static String getODKTableName(String formId)
  {
    Properties props = getODKProperties();

    String schema = props.getProperty("jdbc.schema");

    try (Connection connection = getConnection(props))
    {
      StringBuilder builder = new StringBuilder();
      builder.append("SELECT \"PERSIST_AS_TABLE_NAME\"");
      builder.append(" FROM " + schema + "._form_data_model AS fdm");
      builder.append(" WHERE fdm.\"ELEMENT_TYPE\" = 'GROUP' AND fdm.\"ELEMENT_NAME\" = '" + formId + "'");

      Statement statement = connection.createStatement();
      ResultSet results = statement.executeQuery(builder.toString());

      if (results.next())
      {
        String tableName = results.getString("PERSIST_AS_TABLE_NAME");

        return tableName;
      }
      else
      {
        throw new ProgrammingErrorException("Unable to find the ODK Table Name for the ODK Form with id [" + formId + "]");
      }
    }
    catch (SQLException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private static Connection getConnection(Properties props) throws SQLException
  {
    String url = props.getProperty("jdbc.url");
    String username = props.getProperty("jdbc.username");
    String password = props.getProperty("jdbc.password");

    return DriverManager.getConnection(url, username, password);
  }

  private static Properties getODKProperties()
  {
    Properties props = new Properties();

    try (FileInputStream istream = new FileInputStream(ODKFacade.getJDBCProperties()))
    {
      props.load(istream);
    }
    catch (IOException e)
    {
      throw new ProgrammingErrorException(e);
    }
    return props;
  }

  public static boolean exists(String formType, Disease disease)
  {
    MobileDataUploadJobQuery query = new MobileDataUploadJobQuery(new QueryFactory());
    query.WHERE(query.getFormType().EQ(formType));
    query.AND(query.getDisease().EQ(disease));

    return ( query.getCount() > 0 );
  }

  public static MobileDataUploadJob getJob(String formType, Disease disease)
  {
    MobileDataUploadJobQuery query = new MobileDataUploadJobQuery(new QueryFactory());
    query.WHERE(query.getFormType().EQ(formType));
    query.AND(query.getDisease().EQ(disease));

    OIterator<? extends MobileDataUploadJob> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }
}
