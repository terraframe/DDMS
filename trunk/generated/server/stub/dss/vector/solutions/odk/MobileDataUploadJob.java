package dss.vector.solutions.odk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.io.ExcelExportSheet;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.scheduler.ExecutionContext;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.export.MosquitoCollectionExcelView;

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
    doIt();
  }

  private void doIt()
  {
    ODKForm master = ODKForm.factory(this.getFormType());
    
    ExcelExporter exporter = new ExcelExporter();
    MosquitoCollectionExcelView.setupExportListener(exporter);
    ExcelExportSheet sheet = exporter.addTemplate(this.getFormType());

    ODK2Excel importer = new ODK2Excel(master);
    Collection<String> uuids = importer.getUUIDs(null);

    importer.export(uuids, sheet);

    // TODO Write file to proper location
    try
    {
      exporter.write(new FileOutputStream("test.xlsx"));
    }
    catch (FileNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
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
    job.setFormType(MosquitoCollection.CLASS);
    job.doIt();
  }
}
