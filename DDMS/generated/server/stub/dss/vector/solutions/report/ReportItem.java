/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axis.encoding.Base64;
import org.eclipse.birt.core.archive.FileArchiveWriter;
import org.eclipse.birt.core.archive.IDocArchiveReader;
import org.eclipse.birt.core.archive.compound.ArchiveReader;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.DocxRenderOption;
import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IExcelRenderOption;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.IncludeScriptHandle;
import org.eclipse.birt.report.model.api.LibraryHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.ScriptLibHandle;
import org.eclipse.birt.report.model.api.SlotHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.SingleActorDAOIF;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.constants.VaultFileInfo;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.metadata.MdMethodDAO;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.CreatePermissionException;
import com.runwaysdk.session.ReadPermissionException;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.vault.VaultFileDAO;
import com.runwaysdk.vault.VaultFileDAOIF;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.permission.ReadAction;
import dss.vector.solutions.permission.WriteAction;
import dss.vector.solutions.permissions.RoleProperty;
import dss.vector.solutions.report.serialization.CascadingScalarParameterSerializer;
import dss.vector.solutions.util.BirtEngine;

public class ReportItem extends ReportItemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID      = -935561311;
  
  private static Logger logger = LoggerFactory.getLogger(ReportItem.class);

  private static final String TEMP_REPORT_PREFIX    = "birt-temp-doc-archive";

  public static final String  RPTDESIGN_EXTENSION   = "rptdesign";
  
  public static final String  RESOURCES_BUNDLE_EXT   = "zip";

  public static final String  RPTDOCUMENT_EXTENSION = "rptdoc";

  public static final String  ROOT_RUN_URL          = "dss.vector.solutions.report.ReportController.run.mojo?";

  public static final String  BASE_RUN_URL          = ROOT_RUN_URL + "report=";

  public static final String  BASE_URL              = "dss.vector.solutions.report.ReportController.generate.mojo?report=";

  public ReportItem()
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
    /*
     * Delete the corresponding system url
     */
    SystemURL url = SystemURL.getByURL(this.getURL());

    if (url != null)
    {
      OIterator<? extends RoleProperty> iterator = url.getAllRoles();

      try
      {
        while (iterator.hasNext())
        {
          RoleProperty property = iterator.next();
          Roles role = property.getRole();

          property.delete();
          role.delete();
        }
      }
      finally
      {
        iterator.close();
      }

      List<? extends MenuItem> items = MenuItem.findMenuItems(url);

      for (MenuItem item : items)
      {
        item.delete();
      }

      url.delete();
    }

    /*
     * Delete the design file and if there is a document file delete that too
     */
    if (this.getDesign() != null && this.getDesign().length() > 0)
    {
      VaultFile file = VaultFile.lock(this.getDesign());
      file.delete();
    }

    if (this.getDocument() != null && this.getDocument().length() > 0)
    {
      VaultFile file = VaultFile.lock(this.getDocument());
      file.delete();
    }

    /*
     * Delete the job report
     */
    ReportJob job = ReportJob.get(this);

    if (job != null)
    {
      job.lock();
      job.delete();
    }

    super.delete();
  }

  /**
   * MdMethod
   * Allows the user to upload BIRT report resource files. If the file is a zip it will be unzipped. Otherwise it will be available as a resource for BIRT reports.
   * 
   * @param resourcesIS
   * @param nameOfResource
   */
  public static void uploadResources(java.io.InputStream resourcesIS, java.lang.String nameOfResource)
  {
    ReportResourceManager.getInstance().uploadResource(resourcesIS, nameOfResource);
  }
  
  @Override
  public void apply()
  {
    this.setOutputFormatIndex(this.getOutputFormat().get(0).getEnumName());

    super.apply();
  }

  @Override
  @Transaction
  public void applyWithFile(InputStream designIS)
  {
    boolean isNew = this.isNew() && !this.isAppliedToDB();

    /*
     * Validate the report name
     */
    if (isNew)
    {
      if (designIS != null)
      {
        /*
         * Save the rptdesign to the vault.
         */
        VaultFile designVF = new VaultFile();
        VaultFileDAO designVFDAO = (VaultFileDAO) BusinessFacade.getEntityDAO(designVF);

        this.checkVaultPermissions(designVF, Operation.CREATE);

        String reportName = this.getReportName();
        int index = reportName.lastIndexOf('.');

        String filename = reportName.substring(0, index);
        String extension = reportName.substring(index + 1);

        if (extension != null && extension.equals(RPTDESIGN_EXTENSION))
        {
          designVF.setValue(VaultFileInfo.FILE_NAME, filename);
          designVF.setValue(VaultFileInfo.EXTENSION, extension);

          designVFDAO.setSize(0);
          designVF.apply();
          designVFDAO.putFile(designIS);

          this.setDesign(designVF.getId());
        }
        else
        {
          throw new ReportItemException("Report design must have the rptdesign extension");
        }
      }
      else
      {
        throw new ReportItemException("Report item must have a report document");
      }
    }
    else
    {
      /*
       * Update the existing rptdesign vault file
       */
      if (designIS != null)
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
          file.putFile(designIS);
        }
        else
        {
          throw new ReportItemException("Report design must have the rptdesign extension");
        }

        new CacheDocumentManager().run();
      }
    }

    this.apply();

    if (isNew)
    {
      // Create the system url and permissions to view the report
      SystemURL url = new SystemURL();
      url.setUrl(this.getURL());
      url.getDisplayLabel().setValue(this.getReportLabel().getValue());
      url.setUrlName(this.getReportLabel().getValue());
      url.apply();

      this.addPermissions(url, Disease.getAllDiseases());
    }

    /*
    * Handle the job report
    */
    ReportJob job = ReportJob.get(this);

    if (this.getCacheDocument())
    {
      if (job == null)
      {
        job = new ReportJob();
        job.setReportItem(this);
      }
      else
      {
        job.lock();
      }

      job.setJobId(this.getReportLabel().getValue() + " [" + this.getOutputFormat().get(0).getDisplayLabel() + "]");
      job.getDescription().setValue(this.getReportLabel().getValue() + " [" + this.getOutputFormat().get(0).getDisplayLabel() + "]");
      job.setWorkTotal(1);
      job.apply();
    }
    else
    {
      if (job != null)
      {
        job.lock();
        job.delete();
      }
    }
  }

  public void addPermissions(SystemURL url, Disease... diseases)
  {
    for (Disease disease : diseases)
    {
      // Create the Write role for the report url
      WriteAction writeAction = new WriteAction(url, disease);
      writeAction.assign(MdMethodDAO.getMdMethod(ReportItem.CLASS + ".render"));

      // Create the Read role for the report url
      ReadAction readAction = new ReadAction(url, disease);
      readAction.assign(this.getMdClass());
      readAction.assign(MdViewDAO.getMdViewDAO(ReportParameter.CLASS));
      readAction.assign(MdMethodDAO.getMdMethod(ReportItem.CLASS + ".render"));
    }
  }

  public void addPermissions(Disease... diseases)
  {
    SystemURL url = SystemURL.getByURL(this.getURL());

    this.addPermissions(url, diseases);
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

  public String getRunURL()
  {
    return BASE_RUN_URL + this.getId();
  }

  /**
   * MdMethod
   * 
   * Used by reports to dynamically update a cascading parameter.
   */
  @Override
  public java.lang.String getSelectionListForCascadingGroup(java.lang.String paramName, java.lang.String groupName, java.lang.String[] cascadingValues)
  {
    InputStream stream = this.getDesignAsStream();
    IGetParameterDefinitionTask task = null;
    
    try
    {
      IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());
  
      // Open a report design
      IReportRunnable design = engine.openReportDesign(stream);
  
      task = engine.createGetParameterDefinitionTask(design);
      
      ArrayList<Object> cv = new ArrayList<Object>();
      for (String v : cascadingValues)
      {
        cv.add(v);
      }
    
      CascadingScalarParameterSerializer serializer = new CascadingScalarParameterSerializer(task, design, null, cv, groupName);

      return serializer.optionsToJSON(null, null).toString();
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
        if (task != null)
        {
          task.close();
        }
        stream.close();
      }
      catch (IOException e)
      {
        // TODO change exception type
        throw new RuntimeException("Unable to get a report document", e);
      }
    }
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
  @Transaction
  @Authenticate
  public Long render(OutputStream outputStream, ReportParameter[] parameters, String baseURL, String reportURL)
  {
    /*
     * Ensure the user has permissions to view the report
     */
    this.validatePermissions();

    try
    {
      RenderContext context = this.createRenderContext(baseURL, parameters);

      return this.runAndRender(outputStream, context);
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
  
  /**
   * MdMethod
   * 
   * Invoked in the report manager's "view" page to get a quick list of any issues the report is expected to have. 
   * 
   * @return A serialized JSON string of report problems.
   */
  @SuppressWarnings("unchecked")
  @Override
  public String getReportProblems()
  {
    this.validatePermissions();
    
    try
    {
      // Open the BIRT design file (NOT the document file)
      IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());
      IReportRunnable runnable = engine.openReportDesign(this.getDesignAsStream());
      DesignElementHandle handle = runnable.getDesignHandle();
      ReportDesignHandle design = handle.getDesignHandle(); 

      // Read the resources
      List<String> resources = new ArrayList<String>();
      
      // Properties files
      List<String> icr = design.getIncludeResources();
      if (icr != null)
      {
        resources.addAll(icr);
      }
      
      // Javascript files
      List<IncludeScriptHandle> jsResources = design.getAllIncludeScripts();
      if (jsResources != null)
      {
        for (IncludeScriptHandle resource : jsResources)
        {
          resources.add(resource.getFileName());
        }
      }
      
      // Libraries
      List<LibraryHandle> libRess = design.getAllLibraries();
      if (libRess != null)
      {
        for (LibraryHandle libRes : libRess)
        {
          resources.add(libRes.getFileName());
        }
      }
      
      // Jars
      List<ScriptLibHandle> jarRess = design.getAllScriptLibs();
      if (jarRess != null)
      {
        for (ScriptLibHandle jarRes : jarRess)
        {
          resources.add(jarRes.getName());
        }
      }
      
      // There is unfortunately no getter for images. design.getAllImages only returns EMBEDDED images, which we don't care about.
      // We have to loop over all the elements and check if its an image.
      resources.addAll(this.getAllImages(design.getBody()));
      
      // Ensure that all these resources exist
      List<String> missingResources = new ArrayList<String>();
      BIRTVaultResourceResolver vaultResolver = new BIRTVaultResourceResolver();
      for (String resource : resources)
      {
        URL url = null;
        try
        {
          url = vaultResolver.resolve(resource);
        }
        catch (ReportResourcesException e)
        {
          // Don't care about your exceptions
        }
        
        if (url == null)
        {
          missingResources.add(resource);
        }
        else
        {
          try
          {
            InputStream is = url.openStream();
            is.close();
          }
          catch (IOException e)
          {
            missingResources.add(resource);
          }
        }
      }
      
      // Serialize them to JSON
      JSONObject json = new JSONObject();
      JSONArray jaMissingRes = new JSONArray();
      for (String res : missingResources)
      {
        jaMissingRes.put(res);
      }
      json.put("missingResources", jaMissingRes);
      return json.toString();
    }
    catch (BirtException | JSONException e)
    {
      logger.error("Something went wrong while fetching report problems.", e);
      throw new RuntimeException(e);
    }
  }
  
  //There is unfortunately no getter for images. design.getAllImages only returns EMBEDDED images, which we don't care about.
  // We have to loop over all the elements and check if its an image.
  @SuppressWarnings("unchecked")
  private List<String> getAllImages(SlotHandle slot)
  {
    List<String> resources = new ArrayList<String>();
    
    List<DesignElementHandle> children = slot.getContents();
    if (children != null)
    {
      for (DesignElementHandle child : children)
      {
        if (child instanceof ImageHandle)
        {
          ImageHandle img = (ImageHandle) child;
          
          if (img.getSource().equals(DesignChoiceConstants.IMAGE_REF_TYPE_FILE))
          {
            resources.add(img.getFile());
          }
        }
        
        Iterator<SlotHandle> iterator = child.slotsIterator();
        while (iterator.hasNext())
        {
          resources.addAll(getAllImages(iterator.next()));
        }
      }
    }
    
    return resources;
  }

  private Long renderFromDocument(OutputStream outputStream, IDocArchiveReader reader, RenderContext context) throws BirtException, EngineException
  {
    IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());

    HashMap<String, Object> contextMap = new HashMap<String, Object>();
    contextMap.put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, this.getClass().getClassLoader());

    IReportDocument document = engine.openReportDocument(this.getReportName(), reader, new HashMap<Object, Object>());
    
    try
    {
      IRenderTask task = engine.createRenderTask(document);
      try
      {
        task.setLocale(Session.getCurrentLocale());
        task.setAppContext(contextMap);
        task.setRenderOption(this.getRenderOptions(outputStream, document, context));

        if (task.getRenderOption() instanceof HTMLRenderOption)
        {
          long pageNumber = context.getPageNumber();

          if (pageNumber > 0)
          {
            task.setPageNumber(pageNumber);
          }
        }

        // set and validate the parameters
        task.setParameterValues(context.getParameters());
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

  private RenderContext createRenderContext(String baseUrl, ReportParameter[] parameters) throws BirtException, EngineException, IOException
  {
    Map<String, String> map = new HashMap<String, String>();

    for (ReportParameter parameter : parameters)
    {
      map.put(parameter.getParameterName(), parameter.getParameterValue());
    }

    Map<String, Object> params = new ReportParameterUtil().convertParameters(this.getDesignAsStream(), map);

    return new RenderContext(this, baseUrl, params);
  }

  @Transaction
  public void generateAndSaveDocument(ReportParameter[] parameters)
  {
    try
    {
      RenderContext context = this.createRenderContext(DeployProperties.getApplicationURL(), parameters);

      // Run the report and get the path of the temp rptdocument file
      File file = this.run(context);

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

  private File run(RenderContext context) throws BirtException, EngineException, IOException
  {
    File file = this.getCachedDocument(context);

    if (!file.exists() || file.getName().startsWith(TEMP_REPORT_PREFIX) || ( !context.hasPageNumber() && !this.getCacheDocument() ))
    {
      IReportEngine engine = BirtEngine.getBirtEngine(LocalProperties.getLogDirectory());

      HashMap<String, Object> contextMap = new HashMap<String, Object>();
      contextMap.put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, this.getClass().getClassLoader());

      IReportRunnable design = engine.openReportDesign(this.getDesignAsStream());
      IRunTask task = engine.createRunTask(design);

      try
      {
        task.setLocale(Session.getCurrentLocale());
        task.setAppContext(contextMap);
        task.setParameterValues(context.getParameters());
        task.validateParameters();

        task.run(new FileArchiveWriter(file.getAbsolutePath()));
      }
      finally
      {
        task.close();
      }
    }

    return file;
  }

  public File getCachedDocument(RenderContext context)
  {
    SessionIF session = Session.getCurrentSession();

    if (session != null)
    {
      int hashCode = context.getReportHash(this.getReportName());

      String filepath = CacheDocumentManager.CACHE_DIR + File.separator + this.getCacheFolderName();
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

  private Long runAndRender(OutputStream outputStream, RenderContext context) throws BirtException, EngineException, IOException
  {
    File document = this.getReportDocument(context);

    try
    {
      IDocArchiveReader reader = new ArchiveReader(document.getAbsolutePath());

      return this.renderFromDocument(outputStream, reader, context);
    }
    finally
    {
      if (document != null && document.getName().startsWith(TEMP_REPORT_PREFIX))
      {
        FileIO.deleteFile(document);
      }
    }
  }

  public File getReportDocument(RenderContext context) throws BirtException, EngineException, IOException
  {
    File document = null;

    if (this.getCacheDocument() && this.getDocument() != null && this.getDocument().length() > 0)
    {
      document = this.getDocumentAsFile();
    }

    if (document == null || !document.exists() || ( !context.hasPageNumber() && !this.getCacheDocument() ))
    {
      document = this.run(context);
    }

    return document;
  }

  private IRenderOption getRenderOptions(OutputStream outputStream, IReportDocument document, RenderContext context)
  {
    if (context.getFormat().equals("xlsx"))
    {
      EXCELRenderOption options = new EXCELRenderOption();
      options.setOutputStream(outputStream);
      options.setOutputFormat("xlsx");
      options.setOption(IExcelRenderOption.OFFICE_VERSION, "office2007");

      return options;
    }
    else if (context.getFormat().equals("docx"))
    {
      DocxRenderOption option = new DocxRenderOption();
      option.setOutputStream(outputStream);
      option.setOption(DocxRenderOption.OPTION_EMBED_HTML, Boolean.FALSE);
      option.setOption(IRenderOption.EMITTER_ID, "org.eclipse.birt.report.engine.emitter.docx");
      return option;
    }
    else if (context.getFormat().equalsIgnoreCase(OutputFormat.PDF.name()))
    {
      // set output options
      PDFRenderOption options = new PDFRenderOption();
      options.setOutputFormat(RenderOption.OUTPUT_FORMAT_PDF);
      options.setOutputStream(outputStream);
      options.setBaseURL(context.getBaseUrl());
      options.setActionHandler(new PDFUrlActionHandler(document, context));

      return options;
    }
    else if (context.getFormat().equalsIgnoreCase(OutputFormat.HTML.name()))
    {
      String folderName = this.getCacheFolderName();

      // set output options
      HTMLRenderOption options = new HTMLRenderOption();
      options.setOutputFormat(RenderOption.OUTPUT_FORMAT_HTML);
      options.setOutputStream(outputStream);
      options.setBaseURL(context.getBaseUrl());
      options.setImageHandler(new HTMLServerImageHandler());
      options.setBaseImageURL(context.getBaseUrl() + "/" + CacheDocumentManager.BIRT_SUFFIX + "/" + folderName);
      options.setImageDirectory(CacheDocumentManager.IMGS_DIR + File.separator + folderName);
      options.setActionHandler(new HTMLUrlActionHandler(document, context));
      options.setHtmlTitle(this.getReportLabel().getValue());
      options.setEmbeddable(true);
      options.setHtmlPagination(true);
      
      return options;
    }

    UnsupportedOutputFormatException e = new UnsupportedOutputFormatException("Unknown output format type");
    e.setOutputFormat(context.getFormat());
    e.apply();

    throw e;
  }

  private String getCacheFolderName()
  {
    SessionIF session = Session.getCurrentSession();
    String sessionId = session.getId();

    return Base64.encode(sessionId.getBytes());
  }

  public void validatePermissions()
  {
    SystemURL url = SystemURL.getByURL(this.getURL());

    Map<String, String> roles = Session.getCurrentSession().getUserRoles();
    SingleActorDAOIF user = Session.getCurrentSession().getUser();
    Disease disease = Disease.getCurrent();
    ReadAction action = new ReadAction(url, disease);
    Roles readRole = action.getRole();

    if (!user.isAdministrator() && !roles.containsKey(readRole.getRoleName()))
    {
      String message = "User doesn't have permissions to see the report [" + this.getReportLabel().getValue() + "]";

      throw new ReadPermissionException(message, this, user);
    }
  }

  public static ReportItem find(String reportName, OutputFormat outputFormat)
  {
    ReportItemQuery query = new ReportItemQuery(new QueryFactory());
    query.WHERE(query.getReportName().EQ(reportName));
    query.AND(query.getOutputFormat().containsAny(outputFormat));

    OIterator<? extends ReportItem> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        ReportItem item = it.next();

        if (it.hasNext())
        {
          String message = "Multiple report items have been found with the report [" + reportName + "] and output format [" + outputFormat.name() + "]";

          MultipleReportException e = new MultipleReportException(message);
          e.setReportName(reportName);
          e.setFormat(outputFormat.name());
          e.apply();

          throw e;
        }

        return item;
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

  @Override
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
        // TODO change exception type
        throw new RuntimeException("Unable to get a report document", e);
      }
    }
  }

  @Override
  public Boolean hasParameterDefinitions()
  {
    InputStream stream = this.getDesignAsStream();

    try
    {
      ReportParameterUtil util = new ReportParameterUtil();

      return util.hasParameterDefinitions(stream);
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
        // TODO change exception type
        throw new RuntimeException("Unable to get a report document", e);
      }
    }
  }
}
