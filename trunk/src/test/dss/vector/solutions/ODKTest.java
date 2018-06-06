package dss.vector.solutions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdForm;

import dss.vector.solutions.export.AggregatedCaseExcelView;
import dss.vector.solutions.export.AggregatedIPTExcelView;
import dss.vector.solutions.export.AggregatedITNExcelView;
import dss.vector.solutions.export.BiochemicalAssayExcelView;
import dss.vector.solutions.export.ControlInterventionExcelView;
import dss.vector.solutions.export.DiagnosticAssayExcelView;
import dss.vector.solutions.export.EfficacyAssayExcelView;
import dss.vector.solutions.export.GeoTargetExcelView;
import dss.vector.solutions.export.ITNCommunityExcelView;
import dss.vector.solutions.export.ITNDistributionExcelView;
import dss.vector.solutions.export.ImmatureCollectionExcelView;
import dss.vector.solutions.export.IndividualCaseExcelView;
import dss.vector.solutions.export.IndividualIPTExcelView;
import dss.vector.solutions.export.InfectionAssayExcelView;
import dss.vector.solutions.export.KnockDownAssayExcelView;
import dss.vector.solutions.export.LarvacideExcelView;
import dss.vector.solutions.export.LarvaeDiscriminatingDoseAssayExcelView;
import dss.vector.solutions.export.MolecularAssayExcelView;
import dss.vector.solutions.export.MosquitoCollectionExcelView;
import dss.vector.solutions.export.OperatorSprayExcelView;
import dss.vector.solutions.export.PersonExcelView;
import dss.vector.solutions.export.PooledInfectionAssayExcelView;
import dss.vector.solutions.export.PopulationDataExcelView;
import dss.vector.solutions.export.PupalCollectionExcelView;
import dss.vector.solutions.export.ResourceTargetExcelView;
import dss.vector.solutions.export.SurveyExcelView;
import dss.vector.solutions.export.TeamSprayExcelView;
import dss.vector.solutions.export.ThresholdDataExcelView;
import dss.vector.solutions.export.TimeResponseAssayExcelView;
import dss.vector.solutions.export.ZoneSprayExcelView;
import dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.irs.SprayTeamExcelView;
import dss.vector.solutions.mobile.MobileUtil;
import dss.vector.solutions.odk.MobileDataUploadJob;
import dss.vector.solutions.odk.ODKForm;
import dss.vector.solutions.odk.ODKFormExporter;

public class ODKTest
{
  public static void main(String[] args)
  {
    mainInRequest();
  }

  @Request
  private static void mainInRequest()
  {
//    export(MdForm.get("itwb6b9w77gdaqt7k71popftnaihokqqzo86ttx2qfhw4ayiqelfr3igdjhd90eu").getFormMdClass().definesType());
//    MobileUtil.export(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE + "." + "SlimWaterSource");
    
//    MobileUtil.export(PooledInfectionAssayExcelView.CLASS);
    
    exportAll(0, allTypes.length);
    
//    export(LarvaeDiscriminatingDoseAssayExcelView.CLASS);
    
//    int myIndex = ArrayUtils.indexOf(allTypes, KnockDownAssayExcelView.CLASS);
//    exportAll(myIndex,myIndex + 1);
    
//    exportAll(ArrayUtils.indexOf(allTypes, KnockDownAssayExcelView.CLASS), ArrayUtils.indexOf(allTypes, LarvaeDiscriminatingDoseAssayExcelView.CLASS)+1);
  }
  
  private static void export(String mobileType)
  {
    ODKForm master = ODKForm.factory(mobileType);

    ODKFormExporter odkExp = new ODKFormExporter(master);
    
    odkExp.useMyGeo(5, new File("/home/rick/Documents/tomcat/ddms/webapps/DDMS/ODK/dss_vector_solutions_export_AggregatedCaseExcelView-itemsets.csv"));

    String html = odkExp.doIt();
    
    System.out.println(html);
  }
  
  // KnockDownAssayExcelView.CLASS
  // LarvaeDiscriminatingDoseAssayExcelView.CLASS
  public static final String[] allTypes = new String[]{
      AggregatedCaseExcelView.CLASS, ControlInterventionExcelView.CLASS, MosquitoCollectionExcelView.CLASS, AggregatedIPTExcelView.CLASS, AggregatedITNExcelView.CLASS,
      EfficacyAssayExcelView.CLASS, IndividualCaseExcelView.CLASS, IndividualIPTExcelView.CLASS, ITNCommunityExcelView.CLASS, ITNDistributionExcelView.CLASS,
      LarvacideExcelView.CLASS, OperatorSprayExcelView.CLASS, TeamSprayExcelView.CLASS, ZoneSprayExcelView.CLASS, PersonExcelView.CLASS, PopulationDataExcelView.CLASS,
      PupalCollectionExcelView.CLASS, SurveyExcelView.CLASS, ThresholdDataExcelView.CLASS, ImmatureCollectionExcelView.CLASS, SprayTeamExcelView.CLASS, DiagnosticAssayExcelView.CLASS,
      AdultDiscriminatingDoseAssayExcelView.CLASS, BiochemicalAssayExcelView.CLASS, InfectionAssayExcelView.CLASS, KnockDownAssayExcelView.CLASS, LarvaeDiscriminatingDoseAssayExcelView.CLASS,
      MolecularAssayExcelView.CLASS, PooledInfectionAssayExcelView.CLASS, TimeResponseAssayExcelView.CLASS, GeoTargetExcelView.CLASS, ResourceTargetExcelView.CLASS, FormSurvey.CLASS
  };

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

       odkExp.useMyGeo(5, new File("/home/rick/Documents/tomcat/ddms/webapps/DDMS/ODK/dss_vector_solutions_export_AggregatedCaseExcelView-itemsets.csv"));

       String html = odkExp.doIt();
       
       Disease disease = Disease.getCurrent();

       if (!MobileDataUploadJob.exists(type, disease))
       {
         for (int j = 0; j < 10; j++)
         {
           MobileDataUploadJob job = new MobileDataUploadJob();
           job.setJobId(master.getFormTitle() + ": " + j);
           job.getDescription().setValue(master.getFormTitle() + ": " + j);
           job.setDisease(disease);
           job.setFormType(type);
           job.apply();
         }
       }

//       String html = MobileUtil.export(type);
       
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
