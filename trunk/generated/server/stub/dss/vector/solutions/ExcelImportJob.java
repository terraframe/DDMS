package dss.vector.solutions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

import dss.vector.solutions.export.ExcelReadException;
import dss.vector.solutions.general.EpiCache;
import dss.vector.solutions.generator.ContextBuilderFacade;
import dss.vector.solutions.generator.DefaultContextBuilder;
import dss.vector.solutions.ontology.TermRootCache;

public class ExcelImportJob extends ExcelImportJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1649401623;
  
  private static Map<String,SharedState> sharedStates = new HashMap<String,SharedState>();
  
  private SharedState sharedState;
  
  public ExcelImportJob(ExcelImportManager manager, InputStream inputStream, String[] params)
  {
    super();
    
    this.sharedState = new SharedState();
    this.sharedState.manager = manager;
    this.sharedState.inputStreamIn = inputStream;
    this.sharedState.params = params;
  }
  
  /**
   * Don't invoke this.
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
  
  private class SharedState implements com.runwaysdk.generation.loader.Reloadable
  {
    private ExcelImportManager manager;
    
    private InputStream inputStreamIn;
    
    private InputStream inputStreamOut;
    
    private String[] params;
    
    private RuntimeException sharedEx;
    
    private Semaphore semaphore;
  }
  
  @Override
  protected JobHistory createNewHistory()
  {
    ExcelImportHistory history = new ExcelImportHistory();
    history.setStartTime(new Date());
    history.addStatus(AllJobStatus.RUNNING);
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
      throw this.sharedState.sharedEx;
    }
    
    return this.sharedState.inputStreamOut;
  }
  
  @Override
  @Request
  public void execute(ExecutionContext context)
  {
    loadSharedState();
    
    try
    {
      // Start caching Broswer Roots for this Thread.
      TermRootCache.start();
      EpiCache.start();
  
      try
      {
        ContextBuilderFacade builder = new ContextBuilderFacade(new DefaultContextBuilder(this.sharedState.params, this.sharedState.manager), this.sharedState.manager);
  
        ExcelImporter importer = new ExcelImporter(this.sharedState.inputStreamIn, builder);
  
        try
        {
          byte[] read = importer.read();
  
          this.sharedState.manager.onFinishImport();
  
          this.sharedState.inputStreamOut = new ByteArrayInputStream(read);
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
    catch (RuntimeException ex)
    {
      this.sharedState.sharedEx = ex;
      throw ex;
    }
    finally
    {
      this.sharedState.semaphore.release();
    }
  }
}
