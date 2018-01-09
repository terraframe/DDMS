package dss.vector.solutions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.json.JSONArray;

import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.generator.ExcelImportLegacyHistoryRecordingProgressMonitor;
import dss.vector.solutions.ontology.TermRootCache;

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
    
    byte[] errorBytes = null;
    
    try
    {
      // Start caching Broswer Roots for this Thread.
      TermRootCache.start();
      EpiCache.start();
  
      try
      {
        ContextBuilderIF builder = this.constructContextBuilder();
  
        ExcelImporter importer = new ExcelImporter(this.sharedState.inputStreamIn, builder);
        
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
    }
    catch (Throwable ex)
    {
      this.sharedState.sharedEx = ex;
      throw ex;
    }
    finally
    {
      this.sharedState.semaphore.release();
      
      ExcelImportHistory history = (ExcelImportHistory) context.getJobHistory();
      history.appLock();
      
      if (this.sharedState.manager.hasUnknownTerms())
      {
        history.setSerializedUnknownTerms(this.sharedState.manager.serializeUnknownTerms());
      }
      if (this.sharedState.manager.hasUnknownGeos())
      {
        history.setSerializedUnknownGeos(this.sharedState.manager.serializeUnknownGeos());
      }
      if (errorBytes != null && errorBytes.length > 0)
      {
        history.setHasError(true);
      }
      else
      {
        history.setHasError(false);
      }
      
      history.apply();
    }
  }
}
