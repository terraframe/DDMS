package dss.vector.solutions.kaleidoscope.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.eclipse.birt.core.archive.FileArchiveWriter;
import org.eclipse.birt.core.archive.IDocArchiveReader;
import org.eclipse.birt.core.archive.IDocArchiveWriter;
import org.eclipse.birt.core.archive.compound.ArchiveReader;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.DocxRenderOption;
import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IExcelRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.SingleActorDAOIF;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.constants.VaultFileInfo;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.CreatePermissionException;
import com.runwaysdk.session.RequestState;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.util.Base64;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.vault.VaultFileDAO;
import com.runwaysdk.vault.VaultFileDAOIF;

import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.kaleidoscope.dashboard.Dashboard;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardCondition;
import dss.vector.solutions.report.CacheDocumentManager;
import dss.vector.solutions.report.ReportParameterUtil;
import dss.vector.solutions.util.BirtEngine;
import dss.vector.solutions.util.LocalizationFacade;

public class KaleidoscopeReport extends KaleidoscopeReportBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID      = 1840753825;

  private static final String TEMP_REPORT_PREFIX    = "birt-temp-doc-archive";

  private static final String PAGE_NUMBER           = "pageNumber";

  private static final String FORMAT                = "format";

  public static final String  RPTDESIGN_EXTENSION   = "rptdesign";

  public static final String  RPTDOCUMENT_EXTENSION = "rptdoc";

  public static final String  LAYER_ID              = "layerId";

  public static final String  CONTEXT               = "context";

  public static final String  CATEGORY              = "category";

  public static final String  CRITERIA              = "criteria";

  public static final String  CRITERIA_INFO         = "criteriaInfo";

  public static final String  STATE                 = "state";

  public static final String  AGGREGATION           = "aggregation";

  public static final String  DEFAULT_GEO_ID        = "defaultGeoId";

  public static final String  GEO_NODE_ID           = "geoNodeId";

  public static final String  BASE_URL              = "dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.generate.mojo?report=";

  public KaleidoscopeReport()
  {
    super();
  }

  @Override
  public String toString()
  {
    return this.getReportLabel().getValue();
  }

  @Override
  @Transaction
  public void delete()
  {
    super.delete();

    /*
     * Delete the design file and if there is a document file delete that too
     */
    if (this.getDesign() != null && this.getDesign().length() > 0)
    {
      VaultFile file = VaultFile.get(this.getDesign());
      file.delete();
    }

    if (this.getDocument() != null && this.getDocument().length() > 0)
    {
      VaultFile file = VaultFile.get(this.getDocument());
      file.delete();
    }
  }

  @Override
  public void applyWithFile(InputStream fileStream)
  {

    try
    {
      File rptdesign = File.createTempFile(TEMP_REPORT_PREFIX, "rptdesign");

      try
      {
        FileIO.write(new FileOutputStream(rptdesign), fileStream);

        /*
         * First validate that the report works. This cannot be done in a transaction because report generation drops the request database connection.
         * Thus we must validate outside of a transaction and then apply within a transaction
         */
        try
        {
          File rptdocument = File.createTempFile(TEMP_REPORT_PREFIX, "rptdocument");

          try
          {
            FileArchiveWriter writer = new FileArchiveWriter(rptdocument.getAbsolutePath());

            FileInputStream istream = new FileInputStream(rptdesign);

            try
            {
              this.run(istream, writer, new HashMap<String, String>());
            }
            finally
            {
              istream.close();
            }
          }
          finally
          {
            FileUtils.deleteQuietly(rptdocument);
          }
        }
        catch (Exception e)
        {
          throw new InvalidReportDefinitionException(e);
        }

        FileInputStream istream = new FileInputStream(rptdesign);

        try
        {
          this.applyWithFileInTransaction(istream);
        }
        finally
        {
          istream.close();
        }
      }
      finally
      {
        FileUtils.deleteQuietly(rptdesign);
      }
    }
    catch (IOException e)
    {
      throw new InvalidReportDefinitionException(e);
    }
  }

  @Transaction
  private void applyWithFileInTransaction(InputStream fileStream)
  {
    boolean isNew = this.isNew() && !this.isAppliedToDB();

    /*
     * Validate the report name
     */
    if (isNew)
    {
      /*
       * Create a new vault file
       */
      if (fileStream != null)
      {
        VaultFile entity = new VaultFile();
        VaultFileDAO file = (VaultFileDAO) BusinessFacade.getEntityDAO(entity);

        this.checkVaultPermissions(entity, Operation.CREATE);

        String reportName = this.getReportName();

        int index = reportName.lastIndexOf('.');

        if (index != -1)
        {
          String filename = reportName.substring(0, index);
          String extension = reportName.substring(index + 1);

          if (extension != null && extension.equals(RPTDESIGN_EXTENSION))
          {
            entity.setValue(VaultFileInfo.FILE_NAME, filename);
            entity.setValue(VaultFileInfo.EXTENSION, extension);

            file.setSize(0);
            entity.apply();
            file.putFile(fileStream);

            this.setDesign(entity.getId());
          }
          else
          {
            throw new KaleidoscopeReportException("Report design must have the rptdesign extension");
          }
        }
        else
        {
          throw new KaleidoscopeReportException("Report design must have the rptdesign extension");
        }
      }
      else
      {
        throw new KaleidoscopeReportException("Report item must have a report document");
      }
    }
    else
    {
      /*
       * Update the existing vault file
       */
      if (fileStream != null)
      {
        VaultFile entity = VaultFile.lock(this.getDesign());
        VaultFileDAO file = (VaultFileDAO) BusinessFacade.getEntityDAO(entity);

        this.checkVaultPermissions(entity, Operation.WRITE);

        String reportName = this.getReportName();

        int index = reportName.lastIndexOf('.');

        String filename = reportName.substring(0, index);
        String extension = reportName.substring(index + 1);

        if (extension != null && extension.equals(RPTDESIGN_EXTENSION))
        {
          entity.setValue(VaultFileInfo.FILE_NAME, filename);
          entity.setValue(VaultFileInfo.EXTENSION, extension);

          file.setSize(0);
          entity.apply();
          file.putFile(fileStream);
        }
        else
        {
          throw new KaleidoscopeReportException("Report design must have the rptdesign extension");
        }

        /*
         * This invalidates all cached report documents so we need to delete
         */
        new CacheDocumentManager().run();
      }
    }

    this.apply();
  }

  @Override
  public void apply()
  {
    Dashboard dashboard = this.getDashboard();

    if (dashboard != null)
    {
      this.getReportLabel().setValue(dashboard.getDisplayLabel().getValue());
    }

    super.apply();
  }

  private void checkVaultPermissions(VaultFile entity, Operation operation)
  {
    SessionIF session = Session.getCurrentSession();

    if (session != null)
    {
      String sessionId = session.getId();
      boolean access = SessionFacade.checkAccess(sessionId, operation, entity);

      if (!access)
      {
        SingleActorDAOIF user = SessionFacade.getUser(sessionId);
        String errorMsg = "User [" + user.getSingleActorName() + "] does not have permission to upload a new design file ";
        throw new CreatePermissionException(errorMsg, entity, user);
      }
    }
  }

  @Override
  public String getURL()
  {
    return BASE_URL + this.getId();
  }

  @Override
  public InputStream getDesignAsStream()
  {
    String design = this.getDesign();

    if (design == null || design.equals(""))
    {
      String msg = "A report template has not been defined for this report";

      MissingReportDesignException e = new MissingReportDesignException(msg);
      e.apply();

      throw e;
    }

    VaultFileDAOIF file = VaultFileDAO.get(design);

    return file.getFileStream();
  }
  

  

  @Override
  public InputStream getDocumentAsStream()
  {
    VaultFileDAOIF file = getDocumentAsVaultFile();

    return file.getFileStream();
  }

  private File getDocumentAsFile()
  {
    VaultFileDAOIF file = this.getDocumentAsVaultFile();

    return file.getFile();
  }

  private VaultFileDAOIF getDocumentAsVaultFile()
  {
    String document = this.getDocument();

    if (document == null || document.equals(""))
    {
      String msg = "A report document has not been defined for this report";

      MissingReportDocumentException e = new MissingReportDocumentException(msg);
      e.apply();

      throw e;
    }

    VaultFileDAOIF file = VaultFileDAO.get(document);
    return file;
  }

  @Override
  public Long render(OutputStream outputStream, ReportParameter[] parameters, String baseURL, String reportURL)
  {
    /*
     * Ensure the user has permissions to view the report
     */
    this.validatePermissions();

    try
    {
      Map<String, String> parameterMap = this.createParameterMap(parameters);

      return this.runAndRender(outputStream, parameterMap, baseURL, reportURL);
    }
    catch (EngineException e)
    {
      if (e.getCause() != null)
      {
        ReportRenderException exception = new ReportRenderException(e.getCause());
        exception.setErrorMessage(e.getCause().getLocalizedMessage());
        exception.apply();

        throw exception;
      }
      else
      {
        ReportRenderException exception = new ReportRenderException(e);
        exception.setErrorMessage(e.getLocalizedMessage());
        exception.apply();

        throw exception;
      }
    }
    catch (BirtException e)
    {
      ReportRenderException exception = new ReportRenderException(e);
      exception.setErrorMessage(e.getLocalizedMessage());
      exception.apply();

      throw exception;
    }
    catch (IOException e)
    {
      // TODO change exception type
      throw new RuntimeException("Unable to generate the report document", e);
    }
  }

  private Long renderFromDocument(OutputStream outputStream, Map<String, String> parameterMap, String baseURL, String reportURL, IDocArchiveReader reader) throws BirtException, EngineException
  {
    IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());

    HashMap<String, Object> contextMap = new HashMap<String, Object>();
    contextMap.put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, this.getClass().getClassLoader());

    IReportDocument document = engine.openReportDocument(this.getReportName(), reader, new HashMap<Object, Object>());

    String format = this.getFormat(parameterMap);

    try
    {
      IRenderTask task = engine.createRenderTask(document);
      try
      {
        task.setAppContext(contextMap);
        task.setRenderOption(this.getRenderOptions(outputStream, document, baseURL, reportURL, format));
        task.setLocale(LocalizationFacade.getLocale());

        IReportRunnable design = engine.openReportDesign(document.getDesignStream());

        Map<String, Object> convertedParameters = new ReportParameterUtil().convertParameters(design, parameterMap);

        // set and validate the parameters
        task.setParameterValues(convertedParameters);
        task.validateParameters();

        // run report
        task.render();
      }
      finally
      {
        task.close();
      }
    }
    finally
    {
      document.close();
    }

    return document.getPageCount();
  }

  private Map<String, String> createParameterMap(ReportParameter[] parameters)
  {
    Map<String, String> map = new HashMap<String, String>();

    for (ReportParameter parameter : parameters)
    {
      map.put(parameter.getParameterName(), parameter.getParameterValue());
    }

    if (map.containsKey(STATE))
    {
      String state = map.get(STATE);
      DashboardCondition[] conditions = DashboardCondition.getConditionsFromState(state);

      // Get the user friendly description of the criteria
      if (conditions.length > 0)
      {
        map.put(CRITERIA, DashboardCondition.serialize(conditions));

        String information = ReportProviderUtil.getConditionInformation(conditions);

        map.put(CRITERIA_INFO, information);
      }

      map.remove(STATE);
    }

    map.put(CONTEXT, new JSONObject(map).toString());

    return map;
  }

  private String getFormat(Map<String, String> parameters)
  {
    if (parameters.containsKey(FORMAT))
    {
      return parameters.get(FORMAT);
    }

    return IRenderOption.OUTPUT_FORMAT_HTML;
  }

  public void generateAndSaveDocument(ReportParameter[] parameters)
  {
    try
    {
      // Run the report and get the path of the temp rptdocument file
      File file = this.run(this.createParameterMap(parameters));

      this.save(file);
    }
    catch (BirtException e)
    {
      ReportRenderException exception = new ReportRenderException(e);
      exception.setErrorMessage(e.getLocalizedMessage());
      exception.apply();

      throw exception;
    }
    catch (IOException e)
    {
      // TODO change exception type
      throw new RuntimeException("Unable to get a report document", e);
    }
  }

  @Transaction
  private void save(File file) throws IOException
  {
    try
    {
      // If a vault file doesn't exist for the rptdocument then create one
      if (this.getDocument() == null || this.getDocument().length() == 0)
      {
        this.lock();

        VaultFile entity = new VaultFile();
        VaultFileDAO fileDao = (VaultFileDAO) BusinessFacade.getEntityDAO(entity);

        this.checkVaultPermissions(entity, Operation.CREATE);

        String reportName = this.getReportName();

        int index = reportName.lastIndexOf('.');

        String filename = reportName.substring(0, index);
        String extension = RPTDOCUMENT_EXTENSION;

        entity.setValue(VaultFileInfo.FILE_NAME, filename);
        entity.setValue(VaultFileInfo.EXTENSION, extension);

        fileDao.setSize(0);
        entity.apply();
        fileDao.putFile(new FileInputStream(file));

        this.setDocument(entity.getId());
        this.apply();
      }
      else
      {
        VaultFile vaultFile = VaultFile.lock(this.getDocument());

        try
        {
          VaultFileDAO document = (VaultFileDAO) BusinessFacade.getEntityDAO(vaultFile);

          document.putFile(new FileInputStream(file));
        }
        finally
        {
          vaultFile.unlock();
        }
      }
    }
    catch (FileNotFoundException e)
    {
      throw new FileReadException(file, e);
    }
    finally
    {
      if (file.getName().startsWith(TEMP_REPORT_PREFIX))
      {
        FileIO.deleteFile(file);
      }
    }
  }

  private File run(Map<String, String> parameterMap) throws BirtException, EngineException, IOException
  {
    File file = this.getCachedDocument(parameterMap);

    if (!file.exists() || file.getName().startsWith(TEMP_REPORT_PREFIX) || ( !parameterMap.containsKey(PAGE_NUMBER) && !this.getCacheDocument() ))
    {
      InputStream stream = this.getDesignAsStream();
      FileArchiveWriter writer = new FileArchiveWriter(file.getAbsolutePath());

      this.run(stream, writer, parameterMap);
    }

    return file;
  }

  private void run(InputStream stream, IDocArchiveWriter writer, Map<String, String> parameterMap) throws BirtException, EngineException, IOException
  {
    IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());

    SessionIF session = Session.getCurrentSession();

    HashMap<String, Object> contextMap = new HashMap<String, Object>();
    contextMap.put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, this.getClass().getClassLoader());

    if (session != null)
    {
      contextMap.put("sessionId", session.getId());
    }

    IReportRunnable design = engine.openReportDesign(stream);

    IRunTask task = engine.createRunTask(design);

    try
    {
      Map<String, Object> convertedParameters = new ReportParameterUtil().convertParameters(design, parameterMap);

      task.setAppContext(contextMap);
      task.setParameterValues(convertedParameters);
      task.validateParameters();
      task.setLocale(LocalizationFacade.getLocale());

      String prop = System.getProperty("birt-server");

      if (prop != null && prop.equals("true"))
      {
        task.run(writer);
      }
      else
      {
        RequestState requestState = RequestState.getCurrentRequestState();

        try
        {
          // Release the request connection
          requestState.returnDatabaseConnectionToPool();

          task.run(writer);
        }
        finally
        {
          // Reconnect the request connection
          requestState.getNewDatabaseConnectionFromPool();
        }
      }
    }
    finally
    {
      task.close();
    }
  }

  public File getCachedDocument(Map<String, String> parameters)
  {
    SessionIF session = Session.getCurrentSession();

    if (session != null)
    {
      StringBuffer key = new StringBuffer();
      key.append(this.getReportName());

      Iterator<Entry<String, String>> iterator = parameters.entrySet().iterator();

      while (iterator.hasNext())
      {
        Entry<String, String> entry = iterator.next();

        if (this.isValidParameter(entry))
        {
          key.append(entry.getKey() + "-" + entry.getValue());
        }
      }

      int hashCode = key.toString().hashCode();

      String filepath = BirtConstants.CACHE_DIR + File.separator + this.getCacheFolderName();
      String filename = hashCode + ".rptdocument";

      return new File(filepath + File.separator + filename);
    }

    try
    {
      return File.createTempFile(TEMP_REPORT_PREFIX, "tempReportDocument");
    }
    catch (IOException e)
    {
      // Change exception
      throw new RuntimeException(e);
    }
  }

  private boolean isValidParameter(Entry<String, String> parameter)
  {
    if (parameter.getKey().equals(PAGE_NUMBER))
    {
      return false;
    }

    return true;
  }

  private Long runAndRender(OutputStream outputStream, Map<String, String> parameterMap, String baseURL, String reportURL) throws BirtException, EngineException, IOException
  {
    File document = null;

    if (this.getCacheDocument() && this.getDocument() != null && this.getDocument().length() > 0)
    {
      document = this.getDocumentAsFile();
    }

    if (document == null || !document.exists() || ( !parameterMap.containsKey(PAGE_NUMBER) && !this.getCacheDocument() ))
    {
      document = this.run(parameterMap);
    }

    try
    {
      IDocArchiveReader reader = new ArchiveReader(document.getAbsolutePath());

      return this.renderFromDocument(outputStream, parameterMap, baseURL, reportURL, reader);
    }
    finally
    {
      if (document != null && document.getName().startsWith(TEMP_REPORT_PREFIX))
      {
        FileIO.deleteFile(document);
      }
    }
  }

  private IRenderOption getRenderOptions(OutputStream outputStream, IReportDocument document, String baseURL, String reportURL, String format)
  {
    if (format.equals("xlsx"))
    {
      EXCELRenderOption options = new EXCELRenderOption();
      options.setOutputStream(outputStream);
      options.setOutputFormat("xlsx");
      options.setOption(IExcelRenderOption.OFFICE_VERSION, "office2007");

      return options;
    }
    else if (format.equals("docx"))
    {
      DocxRenderOption option = new DocxRenderOption();
      option.setOutputStream(outputStream);
      option.setOption(DocxRenderOption.OPTION_EMBED_HTML, Boolean.FALSE);
      option.setOption(IRenderOption.EMITTER_ID, "org.eclipse.birt.report.engine.emitter.docx");
      return option;
    }
    else if (format.equalsIgnoreCase("pdf"))
    {
      // set output options
      PDFRenderOption options = new PDFRenderOption();
      options.setOutputFormat(RenderOption.OUTPUT_FORMAT_PDF);
      options.setOutputStream(outputStream);
      options.setBaseURL(baseURL);
      options.setActionHandler(new PDFUrlActionHandler(document, baseURL, reportURL));

      return options;
    }
    else if (format.equalsIgnoreCase("html"))
    {
      String folderName = this.getCacheFolderName();

      // set output options
      HTMLRenderOption options = new HTMLRenderOption();
      options.setOutputFormat(RenderOption.OUTPUT_FORMAT_HTML);
      options.setOutputStream(outputStream);
      options.setBaseURL(baseURL);
      options.setImageHandler(new HTMLServerImageHandler());
      options.setBaseImageURL(baseURL + "/" + BirtConstants.BIRT_SUFFIX + "/" + folderName);
      options.setImageDirectory(BirtConstants.IMGS_DIR + File.separator + folderName);
      options.setActionHandler(new HTMLUrlActionHandler(document, baseURL, reportURL));
      options.setHtmlTitle(this.getReportLabel().getValue());
      options.setEmbeddable(true);
      options.setHtmlPagination(true);

      return options;
    }

    UnsupportedOutputFormatException e = new UnsupportedOutputFormatException("Unknown output format type");
    e.setOutputFormat(format);
    e.apply();

    throw e;
  }

  private String getCacheFolderName()
  {
    SessionIF session = Session.getCurrentSession();
    String sessionId = session.getId();

    return new String(Base64.encodeToChar(sessionId.getBytes(), false));
  }

  public void validatePermissions()
  {

  }

  public static KaleidoscopeReport find(String reportName)
  {
    KaleidoscopeReportQuery query = new KaleidoscopeReportQuery(new QueryFactory());
    query.WHERE(query.getReportName().EQ(reportName));

    OIterator<? extends KaleidoscopeReport> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      it.close();
    }
  }

  // @Override
  public String getParameterDefinitions()
  {
    InputStream stream = this.getDesignAsStream();

    try
    {
      ReportParameterUtil util = new ReportParameterUtil();
      JSONArray definition = util.getParameterDefinitions(stream);

      return definition.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (BirtException e)
    {
      throw new ProgrammingErrorException(e);
    }
    finally
    {
      try
      {
        stream.close();
      }
      catch (IOException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }
  }

  public static KaleidoscopeReport getReportItemForDashboard(String dashboardId)
  {
    KaleidoscopeReportQuery query = new KaleidoscopeReportQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(dashboardId));

    OIterator<? extends KaleidoscopeReport> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  public static String getValuesForReporting(String queryId, String context, Integer pageSize, Integer pageNumber)
  {
    ValueQuery query = ReportProviderBridge.getValuesForReporting(queryId, context);

    if (pageSize != null && pageSize > 0 && pageNumber != null)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    RemoteQueryBuilder builder = new RemoteQueryBuilder(query, true);
    RemoteQuery q = builder.build();

    return q.serialize();
  }

  @Transaction
  public static Integer getPageCount(String queryId, String context, Integer pageSize)
  {
    if (pageSize != null && pageSize > 0)
    {
      ValueQuery query = ReportProviderBridge.getValuesForReporting(queryId, context);
      long count = query.getCount();

      return ( (int) count / pageSize ) + 1;
    }

    return 1;
  }

  @Transaction
  public static String getMetadataForReporting(String queryId, String context)
  {
    ValueQuery query = ReportProviderBridge.getValuesForReporting(queryId, context);
    query.restrictRows(1, 1);

    RemoteQueryBuilder builder = new RemoteQueryBuilder(query, false);
    RemoteQuery q = builder.build();

    return q.serialize();
  }

  public static String getQueriesForReporting()
  {
    return ReportProviderBridge.getQueriesForReporting();
  }

  public static String getSupportedAggregation(String queryId)
  {
    return ReportProviderBridge.getSupportedAggregation(queryId);
  }

  public static String getSupportedGeoNodes(String queryId)
  {
    return ReportProviderBridge.getGeoNodeIds(queryId);
  }

  public static String getGeoEntitySuggestions(String text, Integer limit)
  {
    GeoEntity root = Earth.getEarthInstance();

    QueryFactory factory = new QueryFactory();

    LocatedInQuery lQuery = new LocatedInQuery(factory);
    lQuery.WHERE(lQuery.getParent().EQ(root));

    GeoEntityQuery query = new GeoEntityQuery(factory);
    query.WHERE(query.EQ(lQuery.getChild()));

    OIterator<? extends GeoEntity> iterator = query.getIterator();

    try
    {
      List<PairView> list = new LinkedList<PairView>();

      while (iterator.hasNext())
      {
        GeoEntity entity = iterator.next();

        list.add(PairView.createWithLabel(entity.getId(), entity.getEntityLabel().getValue()));
      }

      return new PairViewSerializer().serialize(list);
    }
    finally
    {
      iterator.close();
    }

  }


  public static KaleidoscopeReport getByDashboard(String dashboardId)
  {
    KaleidoscopeReportQuery query = new KaleidoscopeReportQuery(new QueryFactory());
    query.WHERE(query.getDashboard().EQ(dashboardId));

    OIterator<? extends KaleidoscopeReport> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        KaleidoscopeReport item = iterator.next();

        return item;
      }

      return null;
    }
    finally
    {
      iterator.close();
    }

  }

  public static KaleidoscopeReport lockOrCreateReport(String dashboardId)
  {
    KaleidoscopeReport report = KaleidoscopeReport.getByDashboard(dashboardId);

    if (report == null)
    {
      report = new KaleidoscopeReport();
    }
    else
    {
      report.lock();
    }

    return report;
  }

  public static void unlockByDashboard(String dashboardId)
  {
    KaleidoscopeReport report = KaleidoscopeReport.getByDashboard(dashboardId);

    if (report != null)
    {
      report.unlock();
    }
  }

  public void clone(Dashboard dashboard)
  {
    InputStream stream = this.getDesignAsStream();

    try
    {
      KaleidoscopeReport clone = new KaleidoscopeReport();
      clone.setDashboard(dashboard);
      clone.setReportName(this.getReportName());
      clone.applyWithFile(stream);
    }
    finally
    {
      try
      {
        stream.close();
      }
      catch (IOException e)
      {
        throw new ProgrammingErrorException("Unable to get a report document", e);
      }
    }
  }

}
