package dss.vector.solutions.mobile;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.odk.MobileDataUploadJob;
import dss.vector.solutions.odk.ODKForm;
import dss.vector.solutions.odk.ODKFormExporter;

public class MobileUtil extends MobileUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2080435980;

  public MobileUtil()
  {
    super();
  }

  public static String convertToOdkId(String seed)
  {
    seed = seed.replaceAll("\\.", "_");

    if (seed.endsWith("ExcelView"))
    {
      seed.substring(0, seed.length() - "ExcelView".length());
    }

    return seed;
  }

  public static String export(java.lang.String mobileType)
  {
    ODKForm master = ODKForm.factory(mobileType);

    ODKFormExporter odkExp = new ODKFormExporter(master);

    String html = odkExp.doIt();

    Disease disease = Disease.getCurrent();

    if (!MobileDataUploadJob.exists(mobileType, disease))
    {
      for (int i = 0; i < 10; i++)
      {
        MobileDataUploadJob job = new MobileDataUploadJob();
        job.setJobId(master.getFormTitle() + " (" + disease.getDisplayLabel() + "): " + i);
        job.getDescription().setValue(master.getFormTitle() + " (" + disease.getDisplayLabel() + "): " + i);
        job.setDisease(disease);
        job.setFormType(mobileType);
        job.apply();
      }
    }

    return html;
  }

}
