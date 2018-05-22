package dss.vector.solutions.odk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.RunwayExceptionIF;
import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExportSheet;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelSheetMetadata;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.scheduler.AllJobStatus;
import com.runwaysdk.system.scheduler.ExecutionContext;
import com.runwaysdk.system.scheduler.JobHistory;

import dss.vector.solutions.ExcelImportJob;
import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.export.MosquitoCollectionExcelView;
import dss.vector.solutions.general.Disease;

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
    AllJobStatus status = AllJobStatus.SUCCESS;

    File parent = new File("process");
    parent.mkdirs();

    ExcelExporter exporter = new ExcelExporter();

    // Setup the listeners excel export listeners
    this.setupListener(exporter, form.getViewMd());

    ExcelSheetMetadata metadata = new ExcelSheetMetadata();
    metadata.setValues(this.getDisease().getDisplayLabel());

    ExcelExportSheet sheet = exporter.addTemplate(form.getViewMd().definesType(), metadata);

    // ODK2Excel importer = new ODK2Excel(form, this.getQueryCursor());
    ODK2Excel importer = new ODK2Excel(form, null);
    Collection<String> uuids = importer.getUUIDs();

    if (uuids.size() > 0)
    {
      importer.export(uuids, sheet);

      try
      {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String filename = form.getViewMd().getDisplayLabel(Session.getCurrentLocale()) + "-" + format.format(importer.getExportDateTime()) + ".xlsx";
        exporter.write(new FileOutputStream(new File(parent, filename)));
      }
      catch (FileNotFoundException e)
      {
        logger.error("Unable to write file");
      }

      String userId = this.getUser();
      String dimensionId = this.getDisease().getDimensionId();

      /*
       * 
       */
      File[] files = parent.listFiles();
      
      for (File file : files)
      {
        try
        {
          ExcelImportManager manager = ExcelImportManager.getNewInstance();
          manager.setUserId(userId);
          manager.setDimensionId(dimensionId);

          String historyId = manager.importAndWait(new FileInputStream(file), new String[] {}, file.getName());

          JobHistory result = JobHistory.get(historyId);
          
          if(result.getStatus().get(0).equals(AllJobStatus.WARNING))
          {
            status = AllJobStatus.WARNING;
          }
        }
        catch (IOException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }

      this.setQueryCursor(importer.getCursor());
      this.setLastExportDate(importer.getExportDateTime());
    }
    else
    {
      logger.debug("No ODK data to export for type [" + form.getViewMd().definesType() + "]");
    }
    
    return status;
  }

  private String getUser()
  {
    // TODO Determine how the user is going to be setup
    return UserDAO.findUser("ddms").getId();
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

  public static void main(String[] args)
  {
    mainInRequest(args);
  }

  @Request
  private static void mainInRequest(String[] args)
  {
    MobileDataUploadJob job = new MobileDataUploadJob();
    job.setJobId("Mosquito Collection View ODK");
    job.setDisease(Disease.getCurrent());
    job.setFormType(MosquitoCollectionExcelView.CLASS);
    job.apply();
  }
}
