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

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

import dss.vector.solutions.kaleidoscope.data.etl.ImportRunnable;
import dss.vector.solutions.kaleidoscope.data.etl.excel.JobHistoryProgressMonitor;

public class DataUploaderImportJob extends DataUploaderImportJobBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -97661808;

  public DataUploaderImportJob()
  {
    super();
  }

  private static Map<String, SharedState> sharedStates = new HashMap<String, SharedState>();

  protected SharedState                   sharedState;                                      // This
                                                                                            // state
                                                                                            // is
                                                                                            // shared
                                                                                            // across
                                                                                            // threads

  private ExecutionContext                context;

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
    protected String    configuration;

    protected File      file;

    // protected Semaphore semaphore;

    protected Throwable sharedEx;

    protected String    responseJSON;

    protected String    fileName;
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
    // String responseJSON = this.sharedState.responseJSON;
    //
    // sharedStates.remove(this.getId());

    // Unfortunately we have to return the response JSON because the classloader
    // reloads and invalidates our ImportResponseIF object.
    // return responseJSON;

    return "";
  }

  @Override
  public void execute(ExecutionContext context)
  {
    loadSharedState();
    this.context = context;

    try
    {
      executeAuthenticated();
    }
    catch (Throwable ex)
    {
      this.sharedState.sharedEx = ex;
      throw ex;
    }
    finally
    {
      // this.sharedState.semaphore.release();
      sharedStates.remove(this.getId());
    }
  }

  @Authenticate
  public void executeAuthenticated()
  {
    doInTransaction(context);
  }

  // @Transaction
  public void doInTransaction(ExecutionContext context)
  {
    JobHistoryProgressMonitor monitor = new JobHistoryProgressMonitor((ExcelImportHistory) context.getJobHistory());

    /*
     * This can cause a reload, everything after this line needs to be invoked
     * through reflection
     */
    ImportRunnable run = new ImportRunnable(this.sharedState.fileName, this.sharedState.configuration, this.sharedState.file, monitor);
    String status = run.run();

    if (status.equals("WARNING"))
    {
      context.setStatus(AllJobStatus.WARNING);
    }
  }

}
