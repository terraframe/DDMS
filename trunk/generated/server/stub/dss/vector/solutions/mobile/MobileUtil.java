package dss.vector.solutions.mobile;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdForm;

import dss.vector.solutions.export.BiochemicalAssayExcelView;
import dss.vector.solutions.export.IndividualIPTExcelView;
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

  public static void main(String[] args)
  {
    mainInRequest();
  }

  @Request
  private static void mainInRequest()
  {
    // MobileUtil.exportMdForm("i0t2kre3vugi2k1a8qbdqguk7rxf5b7nzo86ttx2qfhw4ayiqelfr3igdjhd90eu");
    MobileUtil.export(BiochemicalAssayExcelView.CLASS);
    // MobileUtil.export(IndividualCaseExcelView.CLASS);

    // MobileUtil.export(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE + "." +
    // "SlimWaterSource");

    // int myIndex = ArrayUtils.indexOf(allTypes, FormSurvey.CLASS);
    // MobileUtil.exportAll(myIndex,myIndex + 1);
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

  public static void exportMdForm(String mdFormId)
  {
    // MdForm.get(mdFormId).definesType()

    export(MdForm.get(mdFormId).getFormMdClass().definesType());
  }

  public static String export(java.lang.String mobileType)
  {
    ODKForm master = ODKForm.factory(mobileType);

    ODKFormExporter odkExp = new ODKFormExporter(master);

    String id = odkExp.doIt();

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

    return id;
  }

  // public static final String[] allTypes = new String[]{
  // AggregatedCaseExcelView.CLASS, ControlInterventionExcelView.CLASS,
  // MosquitoCollectionExcelView.CLASS, AggregatedIPTExcelView.CLASS,
  // AggregatedITNExcelView.CLASS,
  // EfficacyAssayExcelView.CLASS, IndividualCaseExcelView.CLASS,
  // IndividualIPTExcelView.CLASS, ITNCommunityExcelView.CLASS,
  // ITNDistributionExcelView.CLASS,
  // LarvacideExcelView.CLASS, OperatorSprayExcelView.CLASS,
  // TeamSprayExcelView.CLASS, ZoneSprayExcelView.CLASS, PersonExcelView.CLASS,
  // PopulationDataExcelView.CLASS,
  // PupalCollectionExcelView.CLASS, SurveyExcelView.CLASS,
  // ThresholdDataExcelView.CLASS, ImmatureCollectionExcelView.CLASS,
  // SprayTeamExcelView.CLASS, DiagnosticAssayExcelView.CLASS,
  // AdultDiscriminatingDoseAssayExcelView.CLASS,
  // BiochemicalAssayExcelView.CLASS, InfectionAssayExcelView.CLASS,
  // KnockDownAssayExcelView.CLASS,
  // LarvaeDiscriminatingDoseAssayExcelView.CLASS,
  // MolecularAssayExcelView.CLASS, PooledInfectionAssayExcelView.CLASS,
  // TimeResponseAssayExcelView.CLASS, GeoTargetExcelView.CLASS,
  // ResourceTargetExcelView.CLASS, FormSurvey.CLASS
  // };
  public static final String[] allTypes = new String[] { IndividualIPTExcelView.CLASS };

  public static void exportAll(int startIndex, int endIndex)
  {
    File devGen = new File("dev/gen");
    if (devGen.exists())
    {
      try
      {
        FileUtils.deleteDirectory(devGen);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    devGen.mkdirs();

    for (int i = startIndex; i < endIndex; ++i)
    {
      String type = allTypes[i];

      System.out.println("Starting export of form [" + type + "].");

      ODKForm master = ODKForm.factory(type);

      ODKFormExporter odkExp = new ODKFormExporter(master);

      // odkExp.useMyGeo(5, new
      // File("/home/rick/Documents/tomcat/ddms/webapps/DDMS/ODK/dss_vector_solutions_export_AggregatedCaseExcelView-itemsets.csv"));

      String html = odkExp.doIt();

      if (!html.contains("Successful form upload"))
      {
        System.out.println(type + " was not successful.");
      }

      try
      {
        FileUtils.writeStringToFile(new File("dev/gen/" + type + ".html"), html);
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }

}
