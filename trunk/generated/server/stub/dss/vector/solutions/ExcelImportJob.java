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
package dss.vector.solutions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.generator.ExcelImportLegacyHistoryRecordingProgressMonitor;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.UnknownGeoEntity;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.kaleidoscope.data.etl.CategoryProblem;
import dss.vector.solutions.kaleidoscope.data.etl.LocationProblem;
import dss.vector.solutions.ontology.BrowserField;
import dss.vector.solutions.ontology.BrowserRootView;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.ontology.UnknownTerm;

/**
 * This class is used only for the 'legacy' importer, although it is used for both generated forms as well as hardcoded forms.
 * Semaphores are used to join the import thread with the quartz thread and the 'sharedState' is used to share state
 * between the 2 threads.
 * 
 * @author rrowlands
 *
 */
public class ExcelImportJob extends ExcelImportJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1649401623;
  
  private static Map<String,SharedState> sharedStates = new HashMap<String,SharedState>();
  
  protected SharedState sharedState; // This state is shared across threads
  
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
   * Don't invoke this. The job won't have any of the necessary parameters and won't be able to execute.
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
    
    protected InputStream inputStreamIn;
    
    protected InputStream inputStreamOut;
    
    protected String[] params;
    
    protected Throwable sharedEx;
    
    protected Semaphore semaphore;
    
    protected String fileName;
  }
  
  @Override
  protected JobHistory createNewHistory()
  {
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
  
  public InputStream doImport()
  {
    this.sharedState.semaphore = new Semaphore(0);
    
    this.saveSharedState();
    
    this.start();
    
    try
    {
      this.sharedState.semaphore.acquire();
    }
    catch (InterruptedException e1)
    {
    }
    
    if (this.sharedState.sharedEx != null)
    {
      if (this.sharedState.sharedEx instanceof RuntimeException)
      {
        throw (RuntimeException) this.sharedState.sharedEx;
      }
      else
      {
        throw new RuntimeException(this.sharedState.sharedEx);
      }
    }
    
    InputStream streamOut = this.sharedState.inputStreamOut;
    
    sharedStates.remove(this.getId());
    
    return streamOut;
  }
  
  protected ContextBuilderIF constructContextBuilder()
  {
    return new ContextBuilderFacade(new DefaultContextBuilder(this.sharedState.params, this.sharedState.manager), this.sharedState.manager);
  }
  
  protected void configureImporter(ExcelImporter importer, ExecutionContext context)
  {
    importer.setProgressMonitor(new ExcelImportLegacyHistoryRecordingProgressMonitor((ExcelImportHistory) context.getJobHistory()));
  }
  
  @Override
  public void execute(ExecutionContext context)
  {
    loadSharedState();
    
    try
    {
      executeInner(context);
    }
    catch (Throwable ex)
    {
      this.sharedState.sharedEx = ex;
      ex.printStackTrace();
      throw ex;
    }
    finally
    {
      this.sharedState.semaphore.release();
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
    Earth earth = Earth.getEarthInstance();
    
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
        List<JSONObject> context = new ArrayList<JSONObject>();
        
        String mdType = this.sharedState.manager.getGeoTypeInfo(ugeo);
        GeoHierarchy gh = GeoHierarchy.getGeoHierarchyFromType(mdType);
        
        // TODO : Context and parent geo (earth)
        LocationProblem locp = new LocationProblem(ugeo.getEntityName(), context, earth, GeoHierarchy.getGeoHierarchyFromType(mdType));
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
      
      config.put("sheets", new JSONArray());
      
      reconstructionJSON.put("configuration", config); // referred to in angular as 'workbook' or 'information'
      // END CONFIG JSON //
      
      return reconstructionJSON.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    } 
  }
}
