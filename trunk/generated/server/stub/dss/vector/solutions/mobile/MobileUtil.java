package dss.vector.solutions.mobile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.runwaysdk.session.Request;

import dss.vector.solutions.export.AggregatedCaseExcelView;
import dss.vector.solutions.export.AggregatedIPTExcelView;
import dss.vector.solutions.export.AggregatedITNExcelView;
import dss.vector.solutions.export.ControlInterventionExcelView;
import dss.vector.solutions.export.EfficacyAssayExcelView;
import dss.vector.solutions.export.ITNCommunityExcelView;
import dss.vector.solutions.export.ITNDistributionExcelView;
import dss.vector.solutions.export.ImmatureCollectionExcelView;
import dss.vector.solutions.export.IndividualCaseExcelView;
import dss.vector.solutions.export.IndividualIPTExcelView;
import dss.vector.solutions.export.LarvacideExcelView;
import dss.vector.solutions.export.MosquitoCollectionExcelView;
import dss.vector.solutions.export.OperatorSprayExcelView;
import dss.vector.solutions.export.PersonExcelView;
import dss.vector.solutions.export.PopulationDataExcelView;
import dss.vector.solutions.export.PupalCollectionExcelView;
import dss.vector.solutions.export.SurveyExcelView;
import dss.vector.solutions.export.TeamSprayExcelView;
import dss.vector.solutions.export.ThresholdDataExcelView;
import dss.vector.solutions.export.ZoneSprayExcelView;
import dss.vector.solutions.odk.ODKForm;
import dss.vector.solutions.odk.ODKFormExporter;

public class MobileUtil extends MobileUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2080435980;
  
  public MobileUtil()
  {
    super();
  }
  
  public static void main(String[] args) {
    mainInRequest();
  }
  
  @Request
  private static void mainInRequest()
  {
    MobileUtil.export(EfficacyAssayExcelView.CLASS);
//    MobileUtil.exportAll();
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
    
    return odkExp.doIt();
  }
  
  public static final String[] allTypes = new String[]{
      AggregatedCaseExcelView.CLASS, ControlInterventionExcelView.CLASS, MosquitoCollectionExcelView.CLASS, AggregatedIPTExcelView.CLASS, AggregatedITNExcelView.CLASS,
      EfficacyAssayExcelView.CLASS, IndividualCaseExcelView.CLASS, IndividualIPTExcelView.CLASS, ITNCommunityExcelView.CLASS, ITNDistributionExcelView.CLASS,
      LarvacideExcelView.CLASS, OperatorSprayExcelView.CLASS, TeamSprayExcelView.CLASS, ZoneSprayExcelView.CLASS, PersonExcelView.CLASS, PopulationDataExcelView.CLASS,
      PupalCollectionExcelView.CLASS, SurveyExcelView.CLASS, ThresholdDataExcelView.CLASS, ImmatureCollectionExcelView.CLASS
  };
  public static void exportAll()
  {
    for (String type : allTypes)
    {
      System.out.println("Starting export of form [" + type + "].");
      
      ODKForm master = ODKForm.factory(type);
      
      ODKFormExporter odkExp = new ODKFormExporter(master);
      
      String html = odkExp.doIt();
      
      try {
        FileUtils.writeStringToFile(new File("/home/rick/Documents/eclipse/workspace/MDSS/dev/" + type + ".html"), html);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
