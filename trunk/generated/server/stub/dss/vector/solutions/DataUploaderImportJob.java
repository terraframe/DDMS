package dss.vector.solutions;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

import dss.vector.solutions.kaleidoscope.data.etl.CategoryProblem;
import dss.vector.solutions.kaleidoscope.data.etl.ImportProblemIF;
import dss.vector.solutions.kaleidoscope.data.etl.ImportResponseIF;
import dss.vector.solutions.kaleidoscope.data.etl.ImportRunnable;
import dss.vector.solutions.kaleidoscope.data.etl.LocationProblem;
import dss.vector.solutions.kaleidoscope.data.etl.ProblemResponse;
import dss.vector.solutions.kaleidoscope.data.etl.excel.JobHistoryProgressMonitor;

public class DataUploaderImportJob extends DataUploaderImportJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -97661808;
  
  public DataUploaderImportJob()
  {
    super();
  }
  
  private static Map<String,SharedState> sharedStates = new HashMap<String,SharedState>();
  
  protected SharedState sharedState; // This state is shared across threads
  
  public DataUploaderImportJob(String configuration, File file, String fileName)
  {
    super();
    
    this.sharedState = new SharedState();
    this.sharedState.configuration = configuration;
    this.sharedState.file = file;
    this.sharedState.fileName = fileName;
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
    protected String configuration;
    
    protected File file;
    
    protected Semaphore semaphore;
    
    protected Throwable sharedEx;
    
    protected String responseJSON;
    
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
  
  public String doImport()
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
    
    String responseJSON = this.sharedState.responseJSON;
    
    sharedStates.remove(this.getId());
    
    // Unfortunately we have to return the response JSON because the classloader reloads and invalidates our ImportResponseIF object.
    return responseJSON;
  }
  
  @Override
  public void execute(ExecutionContext context)
  {
    loadSharedState();
    
    try
    {
      doInTransaction(context);
    }
    catch (Throwable ex)
    {
      this.sharedState.sharedEx = ex;
      throw ex;
    }
    finally
    {
      this.sharedState.semaphore.release();
    }
  }
  
  @Transaction
  public void doInTransaction(ExecutionContext context)
  {
    try
    {
      JobHistoryProgressMonitor monitor = new JobHistoryProgressMonitor((ExcelImportHistory) context.getJobHistory());
      
      ImportResponseIF response = new ImportRunnable(this.sharedState.configuration, this.sharedState.file, monitor).run();
      
      JSONObject responseJSON = response.toJSON();
      
      this.sharedState.responseJSON = responseJSON.toString();
      
      ExcelImportHistory history = (ExcelImportHistory) context.getJobHistory();
      history.appLock();
      
      JSONObject reconstructionJSON = new JSONObject();
      reconstructionJSON.put("importResponse", responseJSON);
      reconstructionJSON.put("configuration", new JSONObject(this.sharedState.configuration)); // referred to in angular as 'workbook' or 'information'
      history.setReconstructionJSON(reconstructionJSON.toString());
      
      if (response.hasProblems())
      {
        int catProbs = 0;
        int locProbs = 0;
        
        ProblemResponse pr = (ProblemResponse) response;
        
        Collection<ImportProblemIF> problems = pr.getProblems();
        
        for (ImportProblemIF problem : problems)
        {
          if (problem instanceof CategoryProblem)
          {
            catProbs++;
          }
          else if (problem instanceof LocationProblem)
          {
            locProbs++;
          }
        }
        
        history.setNumberUnknownTerms(catProbs);
        history.setNumberUnknownGeos(locProbs);
      }
      
      history.apply();
    }
    catch(JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
}
