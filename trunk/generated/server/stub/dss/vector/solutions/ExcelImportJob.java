package dss.vector.solutions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
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
  
  private ExcelImportManager manager;
  
  private InputStream inputStreamIn;
  
  private InputStream inputStreamOut;
  
  private String[] params;
  
  private Thread importThread; // TODO : This code needs a review by Smethie.
  
  public ExcelImportJob(ExcelImportManager manager, InputStream inputStream, String[] params)
  {
    super();
    
    this.manager = manager;
    this.inputStreamIn = inputStream;
    this.params = params;
    this.importThread = null;
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
    this.start();
    
    int loopCounter = 0;
    
    while (this.importThread == null)
    {
      if (loopCounter > 3000)
      {
        throw new ProgrammingErrorException("Import never started.");
      }
      
      try
      {
        Thread.sleep(100);
      }
      catch (InterruptedException e)
      {
      }
      
      loopCounter++;
    }
    
    try
    {
      this.importThread.join();
    }
    catch (InterruptedException e)
    {
      throw new ProgrammingErrorException("We were interrupted while waiting for the import.");
    }
    
    return this.inputStreamOut;
  }
  
  @Override
  @Request
  public void execute(ExecutionContext context)
  {
    this.importThread = Thread.currentThread();
    
    // Start caching Broswer Roots for this Thread.
    TermRootCache.start();
    EpiCache.start();

    try
    {
      ContextBuilderFacade builder = new ContextBuilderFacade(new DefaultContextBuilder(params, this.manager), this.manager);

      ExcelImporter importer = new ExcelImporter(inputStreamIn, builder);

      try
      {
        byte[] read = importer.read();

        this.manager.onFinishImport();

        this.inputStreamOut = new ByteArrayInputStream(read);
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
}
