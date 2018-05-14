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
    HashMap<String, String> mapping = new HashMap<String, String>();
    mapping.put(MosquitoCollectionView.ABUNDANCE, MosquitoCollectionExcelView.ABUNDANCE);
    mapping.put(MosquitoCollectionView.COLLECTIONID, MosquitoCollectionExcelView.COLLECTIONID);
    mapping.put(MosquitoCollectionView.COLLECTIONDATE, MosquitoCollectionExcelView.COLLECTIONDATE);
    mapping.put(MosquitoCollectionView.COLLECTIONMETHOD, MosquitoCollectionExcelView.COLLECTIONMETHOD);
    mapping.put(MosquitoCollectionView.COLLECTIONROUND, MosquitoCollectionExcelView.COLLECTIONROUND);
    mapping.put(MosquitoCollectionView.COLLECTIONTYPE, MosquitoCollectionExcelView.COLLECTIONTYPE);
    mapping.put(MosquitoCollectionView.DATELASTSPRAYED, MosquitoCollectionExcelView.DATELASTSPRAYED);
    mapping.put(MosquitoCollectionView.GEOENTITY, MosquitoCollectionExcelView.GEOENTITY);
    mapping.put(MosquitoCollectionView.INSECTICIDEBRAND, MosquitoCollectionExcelView.INSECTICIDEBRAND);
    mapping.put(MosquitoCollectionView.LIFESTAGE, MosquitoCollectionExcelView.LIFESTAGE);
    mapping.put(MosquitoCollectionView.NUMBEROFANIMALOCCUPANTS, MosquitoCollectionExcelView.NUMBEROFANIMALOCCUPANTS);
    mapping.put(MosquitoCollectionView.NUMBEROFHUMANOCCUPANTS, MosquitoCollectionExcelView.NUMBEROFHUMANOCCUPANTS);
    mapping.put(MosquitoCollectionView.NUMBEROFLLINS, MosquitoCollectionExcelView.NUMBEROFLLINS);
    mapping.put(MosquitoCollectionView.WALLTYPE, MosquitoCollectionExcelView.WALLTYPE);

    mapping.put(SubCollectionView.DISECTED, MosquitoCollectionExcelView.DISECTED);
    mapping.put(SubCollectionView.EGGS, MosquitoCollectionExcelView.EGGS);
    mapping.put(SubCollectionView.FEMALESFED, MosquitoCollectionExcelView.FEMALESFED);
    mapping.put(SubCollectionView.FEMALESGRAVID, MosquitoCollectionExcelView.FEMALESGRAVID);
    mapping.put(SubCollectionView.FEMALESHALFGRAVID, MosquitoCollectionExcelView.FEMALESHALFGRAVID);
    mapping.put(SubCollectionView.FEMALESUNFED, MosquitoCollectionExcelView.FEMALESUNFED);
    mapping.put(SubCollectionView.FEMALESUNKNOWN, MosquitoCollectionExcelView.FEMALESUNKNOWN);
    mapping.put(SubCollectionView.IDENTMETHOD, MosquitoCollectionExcelView.IDENTMETHOD);
    mapping.put(SubCollectionView.LARVAE, MosquitoCollectionExcelView.LARVAE);
    mapping.put(SubCollectionView.MALE, MosquitoCollectionExcelView.MALE);
    mapping.put(SubCollectionView.PAROUS, MosquitoCollectionExcelView.PAROUS);
    mapping.put(SubCollectionView.PUPAE, MosquitoCollectionExcelView.PUPAE);
    mapping.put(SubCollectionView.SUBCOLLECTIONID, MosquitoCollectionExcelView.SUBCOLLECTIONID);
    mapping.put(SubCollectionView.TAXON, MosquitoCollectionExcelView.TAXON);
    mapping.put(SubCollectionView.UNKNOWNS, MosquitoCollectionExcelView.UNKNOWNS);

    MdClassDAOIF subc = MdClassDAO.getMdClassDAO(SubCollectionView.CLASS);
    MdClassDAOIF mosq = MdClassDAO.getMdClassDAO(MosquitoCollectionView.CLASS);

    ODKForm form = new ODKForm(mosq, mapping, new ODKForm(subc, mapping));
    form.setTarget(MdClassDAO.getMdClassDAO(MosquitoCollectionExcelView.CLASS));

    ExcelExporter exporter = new ExcelExporter();
    MosquitoCollectionExcelView.setupExportListener(exporter);
    ExcelExportSheet sheet = exporter.addTemplate(MosquitoCollectionExcelView.CLASS);

    ODK2Excel importer = new ODK2Excel(form);
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
