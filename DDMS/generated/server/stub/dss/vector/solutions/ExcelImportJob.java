/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.generator.ExcelImportLegacyHistoryRecordingProgressMonitor;
import dss.vector.solutions.generator.FormBedNetContextBuilder;
import dss.vector.solutions.generator.FormContextBuilder;
import dss.vector.solutions.generator.FormHouseholdContextBuilder;
import dss.vector.solutions.generator.FormImportFilter;
import dss.vector.solutions.generator.FormPersonContextBuilder;
import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.kaleidoscope.data.etl.CategoryProblem;
import dss.vector.solutions.kaleidoscope.data.etl.LocationProblem;
import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.ontology.BrowserRootView;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.ontology.UnknownTerm;
import dss.vector.solutions.util.GeoEntitySearcher;

/**
 * This class is used only for the 'legacy' importer, although it is used for
 * both generated forms as well as hardcoded forms. Semaphores are used to join
 * the import thread with the quartz thread and the 'sharedState' is used to
 * share state between the 2 threads.
 * 
 * @author rrowlands
 *
 */
public class ExcelImportJob extends ExcelImportJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private Logger logger = LoggerFactory.getLogger(ExcelImportJob.class);
  
  private static final long               serialVersionUID = -1649401623;

  private static Map<String, SharedState> sharedStates     = new HashMap<String, SharedState>();

  protected SharedState                   sharedState;                                          // This
                                                                                                // state
                                                                                                // is
                                                                                                // shared
                                                                                                // across
                                                                                                // threads

  public ExcelImportJob(ExcelImportManager manager, InputStream inputStream, String[] params, String fileName)
  {
    super();

    this.sharedState = new SharedState();
    this.sharedState.manager = manager;
    this.sharedState.inputStreamIn = inputStream;
    this.sharedState.params = params;
    this.sharedState.fileName = fileName;
  }

  /**
   * Don't invoke this. The job won't have any of the necessary parameters and
   * won't be able to execute.
   */
  public ExcelImportJob()
  {

  }

  private void saveSharedState()
  {
    sharedStates.put(this.getId(), this.sharedState);
  }

  private void loadSharedState()
  {
    if (this.sharedState != null) { return; }
    
    if (sharedStates.containsKey(this.getId()))
    {
      this.sharedState = sharedStates.get(this.getId());
    }
    else
    {
      this.sharedState = new SharedState();
    }
  }

  protected class SharedState implements com.runwaysdk.generation.loader.Reloadable
  {
    protected ExcelImportManager manager;

    protected InputStream        inputStreamIn;

    protected InputStream        inputStreamOut;

    protected String[]           params;

    protected Throwable          sharedEx;

    protected Semaphore          threadWorkLock;
    
    protected Semaphore          threadInitLock;

    protected String             fileName;

    protected AllJobStatus       status;

    protected ExcelImportHistory history = null;
  }

  @Override
  protected JobHistory createNewHistory()
  {
    loadSharedState();
    
    ExcelImportHistory history = new ExcelImportHistory();
    history.setStartTime(new Date());
    history.addStatus(AllJobStatus.RUNNING);
    history.setFileName(this.sharedState.fileName);
    history.apply();

    return history;
  }

  public void importAsync()
  {
    this.saveSharedState();

    this.start();
  }

  public ExcelImportHistory importAndWait()
  {
    this.sharedState.threadWorkLock = new Semaphore(0);
    this.sharedState.threadInitLock = new Semaphore(0);
    

    this.saveSharedState();

    this.start();

    try
    {
      SessionFacade.renewSession(Session.getCurrentSession().getId());
      
      // If our sister thread hasn't initialized within a short period of time, error out quickly.
      if (!this.sharedState.threadInitLock.tryAcquire(5, TimeUnit.MINUTES))
      {
        logger.error("Timeout waiting for initialization of Quartz worker thread on scheduled job [" + this.toString() + "].");
        return null;
      }
    }
    catch (InterruptedException e1)
    {
      return null;
    }
    
    try
    {
      // Our sister thread has initialized. Let's wait a long time for her to do the work (but not forever).
      int minutes = 0;
      boolean didAcquire = false;
      
      while (!didAcquire && minutes < 1440)
      {
        SessionFacade.renewSession(Session.getCurrentSession().getId());
        
        if (this.sharedState.threadWorkLock.tryAcquire(5, TimeUnit.MINUTES))
        {
          didAcquire = true;
        }
        
        minutes = minutes + 5;
      }
      
      if (!didAcquire)
      {
        logger.error("Timeout waiting for job completion of Quartz worker thread on scheduled job [" + this.toString() + "].");
        return null;
      }
    }
    catch (InterruptedException e1)
    {
      return null;
    }

    return this.sharedState.history;
  }

  public InputStream doImport()
  {
    // this.sharedState.semaphore = new Semaphore(0);

    this.saveSharedState();

    this.start();

    // try
    // {
    // this.sharedState.semaphore.acquire();
    // }
    // catch (InterruptedException e1)
    // {
    // }
    //
    // if (this.sharedState.sharedEx != null)
    // {
    // if (this.sharedState.sharedEx instanceof RuntimeException)
    // {
    // throw (RuntimeException) this.sharedState.sharedEx;
    // }
    // else
    // {
    // throw new RuntimeException(this.sharedState.sharedEx);
    // }
    // }
    //
    // InputStream streamOut = this.sharedState.inputStreamOut;
    //
    // sharedStates.remove(this.getId());

    return null;
  }

  protected ContextBuilderIF constructContextBuilder()
  {
    MdWebFormDAOIF formSurvey = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormSurvey.FORM_TYPE);
    MdWebFormDAOIF formHousehold = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE);
    MdWebFormDAOIF formBedNet = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
    MdWebFormDAOIF formPerson = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO(FormPerson.FORM_TYPE);

    ContextBuilderFacade builder = new ContextBuilderFacade(new DefaultContextBuilder(this.sharedState.params, this.sharedState.manager), this.sharedState.manager);

    builder.add(FormSurvey.CLASS, new FormContextBuilder(formSurvey, new FormImportFilter(), this.sharedState.manager));
    builder.add(FormHousehold.CLASS, new FormHouseholdContextBuilder(this.sharedState.manager));
    builder.add(FormBedNet.CLASS, new FormBedNetContextBuilder(this.sharedState.manager));
    builder.add(FormPerson.CLASS, new FormPersonContextBuilder(this.sharedState.manager));

    MdFormUtil.addGridContexts(formSurvey, builder);
    MdFormUtil.addGridContexts(formHousehold, builder);
    MdFormUtil.addGridContexts(formBedNet, builder);
    MdFormUtil.addGridContexts(formPerson, builder);

    return builder;
  }

  protected void configureImporter(ExcelImporter importer, ExecutionContext context)
  {
    importer.setProgressMonitor(new ExcelImportLegacyHistoryRecordingProgressMonitor((ExcelImportHistory) context.getJobHistory()));

    List<ImportContext> contexts = importer.getContexts();

    for (ImportContext iContext : contexts)
    {
      List<ImportListener> listeners = FormSurvey.getImportListeners(iContext.getMdClassType());

      for (ImportListener listener : listeners)
      {
        iContext.addListener(listener);
      }
    }
  }

  @Override
  public void execute(ExecutionContext context)
  {
    try
    {
      loadSharedState();
      
      if (this.sharedState.threadInitLock != null) // The locks will be null unless invoked from 'importAndWait'
      {
        this.sharedState.threadInitLock.release();
      }

      executeInner(context);

      this.sharedState.history = (ExcelImportHistory) context.getJobHistory();
    }
    catch (Throwable ex)
    {
      this.sharedState.sharedEx = ex;
      ex.printStackTrace();
      throw ex;
    }
    finally
    {
      if (this.sharedState.threadWorkLock != null) // The locks will be null unless invoked from 'importAndWait'
      {
        this.sharedState.threadWorkLock.release();
      }

      sharedStates.remove(this.getId());
    }
  }

  public void executeInner(ExecutionContext context)
  {
    byte[] errorBytes = null;
    ExcelImporter importer = null;

    VaultFile vf = VaultFile.createAndApply(this.sharedState.fileName, this.sharedState.inputStreamIn);

    // Start caching Broswer Roots for this Thread.
    TermRootCache.start();
    EpiCache.start();
    
    try
    {
      ContextBuilderIF builder = this.constructContextBuilder();

      importer = new ExcelImporter(vf.getFileStream(), builder);

      this.configureImporter(importer, context);
      
      try
      {
        errorBytes = importer.read();

        if (errorBytes != null && errorBytes.length > 0)
        {
          context.setStatus(AllJobStatus.WARNING);
        }

        this.sharedState.manager.onFinishImport();

        this.sharedState.inputStreamOut = new ByteArrayInputStream(errorBytes);
      }
      catch (RuntimeException e)
      {
        /*
         * Ticket #2663: Errors from reading external sheet should have a better
         * error message. Unfortunately, the HSSF API doesn't throw a specific
         * exception for external sheet errors, but throws a RuntimeException.
         * As such the only way to tell if the exception is an external sheet
         * error is by reading the message.
         */
        Throwable cause = e.getCause();

        if (cause != null && cause.getMessage().startsWith("No external workbook with name"))
        {
          throw new ExcelReadException();
        }

        throw e;
      }
      
      if(importer.getContexts().size() == 0)
      {
        throw new ExcelReadException("No sheets were imported");
      }
    }
    finally
    {
      TermRootCache.stop();
      EpiCache.stop();
    }

    ExcelImportHistory history = (ExcelImportHistory) context.getJobHistory();
    history.appLock();

    history.setNumberUnknownGeos(this.sharedState.manager.getNumberUnknownGeos());
    history.setNumberUnknownTerms(this.sharedState.manager.getNumberUnknownTerms());

    if (errorBytes != null && errorBytes.length > 0)
    {
      VaultFile vf2 = VaultFile.createAndApply(this.sharedState.fileName, new ByteArrayInputStream(errorBytes));
      history.setErrorFile(vf2);
    }

    if (importer != null)
    {
      history.setReconstructionJSON(generateReconstructionJSON(vf, importer));
    }

    history.apply();
  }

  private String generateReconstructionJSON(VaultFile vf, ExcelImporter importer)
  {
//    Earth earth = Earth.getEarthInstance();
    GeoEntity defaultGeo = DefaultGeoEntity.getDefaultGeoEntity().getGeoEntity();

    try
    {
      JSONObject reconstructionJSON = new JSONObject();

      // RESPONSE JSON //
      JSONObject responseJSON = new JSONObject();

      responseJSON.put("success", this.sharedState.manager.getNumberUnknownGeos() == 0 && this.sharedState.manager.getNumberUnknownTerms() == 0);

      // BEGIN PROBLEMS //
      JSONObject problems = new JSONObject();

      JSONArray catProbs = new JSONArray();
      List<UnknownTerm> uterms = this.sharedState.manager.unknownTerms;
      for (UnknownTerm uterm : uterms)
      {
        String categoryId = "";
        BrowserRootView[] roots = BrowserField.getBrowserField(uterm.getMdAttributeId()).getRoots();

        if (roots.length > 0)
        {
          // TODO : Multiple roots? Or maybe there are no roots.
          categoryId = roots[0].getTermId();
        }

        CategoryProblem catp = new CategoryProblem(uterm.getTermName(), categoryId, uterm.getMdAttributeId(), uterm.getAttributeLabel());
        catProbs.put(catp.toJSON());
      }
      problems.put("categories", catProbs);

      JSONArray locProbs = new JSONArray();
      List<UnknownGeoEntity> ugeos = this.sharedState.manager.unknownEntityList;
      for (UnknownGeoEntity ugeo : ugeos)
      {
        String mdType = this.sharedState.manager.getGeoTypeInfo(ugeo);
        
        // Build context from UnknownGeoEntity's 'knownHierarchy' field
        LinkedList<JSONObject> context = new LinkedList<JSONObject>();
        String sKH = ugeo.getKnownHierarchy();
        String[] saKH = StringUtils.splitByWholeSeparator(sKH, ", ");
        for (int index = 0; index < saKH.length; ++index)
        {
          String sGeo = saKH[index];
          sGeo = sGeo.trim();
          
          Pattern pattern = Pattern.compile("^(.*)\\(([^)]+)\\)$");
          Matcher matcher = pattern.matcher(sGeo);
          if (matcher.find())
          {
            String label = matcher.group(1).trim();
            String universal = matcher.group(2).trim();
            
            if (!ugeo.getEntityName().equals(label))
            {
              JSONObject object = new JSONObject();
              object.put("label", label);
              object.put("universal", universal);
              context.add(object);
            }
          }
        }
        
        Collections.reverse(context);
        
        GeoEntity parent = defaultGeo;
        
        if (context.size() > 0)
        {
          List<GeoEntity> parents = calculateParentFromContext(context);
          
          if (parents.size() > 0)
          {
            parent = parents.get(0);
          }
          else
          {
            logger.error("Error when getting parent from the context. This import will fail if re-imported. The calculated parents size was 0 for [" + ugeo.getEntityName() + "].");
          }
        }

        LocationProblem locp = new LocationProblem(ugeo.getEntityName(), context, parent, GeoHierarchy.getGeoHierarchyFromType(mdType));
        locProbs.put(locp.toJSON());
      }
      problems.put("locations", locProbs);

      responseJSON.put("problems", problems);
      // END PROBLEMS //

      responseJSON.put("datasets", new JSONArray());

      responseJSON.put("sheets", new JSONArray());

      reconstructionJSON.put("importResponse", responseJSON);
      // END RESPONSE JSON //

      // CONFIG JSON //
      JSONObject config = new JSONObject();
      config.put("filename", vf.getFileName() + "." + vf.getFileExtension());
      config.put("vaultId", vf.getId());
      config.put("locationExclusions", new JSONArray());
      
      JSONArray jParams = new JSONArray();
      for (int i = 0; i < this.sharedState.params.length; ++i)
      {
        jParams.put(this.sharedState.params[i]);
      }
      config.put("params", jParams);

      config.put("sheets", new JSONArray());

      reconstructionJSON.put("configuration", config); // referred to in angular
                                                       // as 'workbook' or
                                                       // 'information'
      // END CONFIG JSON //

      return reconstructionJSON.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private List<GeoEntity> calculateParentFromContext(LinkedList<JSONObject> context)
  {
    Savepoint sp = Database.setSavepoint();
    
    try
    {
      List<JSONObject> contextOfParent = new LinkedList<JSONObject>(context);
      Collections.reverse(contextOfParent);
      
      String parentEntityType = null;
      HashMap<String, String> parentMap = new HashMap<String, String>();
      ArrayList<String> parentTypes = new ArrayList<String>();
      
      for (int i = 0; i < contextOfParent.size(); ++i)
      {
        JSONObject joParentOfParent = contextOfParent.get(i);
        
        MdBusinessQuery mbq = new MdBusinessQuery(new QueryFactory());
        mbq.WHERE(mbq.getDisplayLabel().localize().EQ(joParentOfParent.getString("universal")));
        OIterator<? extends MdBusiness> it = mbq.getIterator();
        try
        {
          if (it.hasNext())
          {
            String definesType = it.next().definesType();
            
            parentMap.put(definesType, joParentOfParent.getString("label"));
            parentTypes.add(definesType);
          }
        }
        finally
        {
          it.close();
        }
      }
      
      parentEntityType = GeoHierarchy.getMostChildishUniversialType(parentTypes.toArray(new String[parentTypes.size()]));
      String parentLabel = parentMap.get(parentEntityType);
      parentMap.remove(parentEntityType);
      
      List<GeoEntity> parents = GeoEntitySearcher.search(false, parentMap, parentEntityType, parentLabel);
      
      return parents;
    }
    catch (Throwable t)
    {
      Database.rollbackSavepoint(sp);
      
      logger.error("Error when getting parent from the context. This import will fail if re-imported.", t);
      
      return new LinkedList<GeoEntity>();
    }
    finally
    {
      Database.releaseSavepoint(sp);
    }
  }
}
