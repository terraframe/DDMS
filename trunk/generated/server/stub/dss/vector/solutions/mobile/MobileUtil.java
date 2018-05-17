package dss.vector.solutions.mobile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.session.Request;

import dss.vector.solutions.PersonView;
import dss.vector.solutions.entomology.ImmatureCollectionView;
import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.entomology.PupalCollectionView;
import dss.vector.solutions.entomology.SubCollectionView;
import dss.vector.solutions.entomology.assay.EfficacyAssayView;
import dss.vector.solutions.export.AggregatedCaseExcelView;
import dss.vector.solutions.export.AggregatedCaseReferralsExcelView;
import dss.vector.solutions.export.AggregatedCaseTreatmentsExcelView;
import dss.vector.solutions.export.AggregatedIPTExcelView;
import dss.vector.solutions.export.AggregatedITNExcelView;
import dss.vector.solutions.export.AggregatedPremiseExcelView;
import dss.vector.solutions.export.CaseDiagnosisTypeExcelView;
import dss.vector.solutions.export.CaseDiseaseManifestationExcelView;
import dss.vector.solutions.export.CasePatientTypeExcelView;
import dss.vector.solutions.export.ControlInterventionExcelView;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.export.EfficacyAssayExcelView;
import dss.vector.solutions.export.ITNCommunityExcelView;
import dss.vector.solutions.export.ITNDistributionExcelView;
import dss.vector.solutions.export.ImmatureCollectionExcelView;
import dss.vector.solutions.export.IndividualCaseExcelView;
import dss.vector.solutions.export.IndividualIPTExcelView;
import dss.vector.solutions.export.IndividualPremiseExcelView;
import dss.vector.solutions.export.InsecticideInterventionExcelView;
import dss.vector.solutions.export.LarvacideExcelView;
import dss.vector.solutions.export.MosquitoCollectionExcelView;
import dss.vector.solutions.export.OperatorSprayExcelView;
import dss.vector.solutions.export.PersonExcelView;
import dss.vector.solutions.export.PersonInterventionExcelView;
import dss.vector.solutions.export.PopulationDataExcelView;
import dss.vector.solutions.export.PupalCollectionExcelView;
import dss.vector.solutions.export.SurveyExcelView;
import dss.vector.solutions.export.TeamSprayExcelView;
import dss.vector.solutions.export.ThresholdDataExcelView;
import dss.vector.solutions.export.ZoneSprayExcelView;
import dss.vector.solutions.general.PopulationDataView;
import dss.vector.solutions.general.ThresholdDataView;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.intervention.monitor.AggregatedIPTView;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView;
import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNDistributionView;
import dss.vector.solutions.intervention.monitor.IndividualCaseView;
import dss.vector.solutions.intervention.monitor.IndividualIPTView;
import dss.vector.solutions.intervention.monitor.PersonInterventionView;
import dss.vector.solutions.irs.OperatorSprayView;
import dss.vector.solutions.irs.TeamSprayView;
import dss.vector.solutions.irs.ZoneSprayView;
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
    MobileUtil.export(MosquitoCollectionExcelView.CLASS);
  }
  
  public static String export(java.lang.String mobileType)
  {
    GeoFilterCriteria gfc = getGeoCriteriaFromListeners(mobileType);
    ODKFormExporter odkExp = new ODKFormExporter();
    
    if (mobileType.equals(AggregatedCaseExcelView.CLASS))
    {
      odkExp.addForm(new ODKForm(AggregatedCaseReferralsExcelView.CLASS, gfc));
      odkExp.addForm(new ODKForm(AggregatedCaseTreatmentsExcelView.CLASS, gfc));
      odkExp.addForm(new ODKForm(CaseDiagnosisTypeExcelView.CLASS, gfc));
      odkExp.addForm(new ODKForm(CaseDiseaseManifestationExcelView.CLASS, gfc));
      odkExp.addForm(new ODKForm(CasePatientTypeExcelView.CLASS, gfc));
    }
    else if (mobileType.equals(ControlInterventionExcelView.CLASS))
    {
      odkExp.addForm(new ODKForm(AggregatedPremiseExcelView.CLASS, gfc));
      odkExp.addForm(new ODKForm(IndividualPremiseExcelView.CLASS, gfc));
      odkExp.addForm(new ODKForm(InsecticideInterventionExcelView.CLASS, gfc));
      odkExp.addForm(new ODKForm(PersonInterventionExcelView.CLASS, gfc));
    }
    // TODO : Merg form, form gen types
//    else if (mobileType.equals(MosquitoCollectionExcelView.CLASS))
//    {
//      MdClassDAOIF subc = MdClassDAO.getMdClassDAO(SubCollectionView.CLASS);
//      MdClassDAOIF mosq = MdClassDAO.getMdClassDAO(MosquitoCollectionView.CLASS);
//      ODKForm form = new ODKForm(mosq, gfc, new ODKForm(subc));
//      odkExp.addForm(form);
//    }
//    else if (mobileType.equals(AggregatedIPTExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(AggregatedIPTView.CLASS, gfc));
//    }
//    else if (mobileType.equals(AggregatedITNExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ITNDataView.CLASS, gfc));
//    }
//    else if (mobileType.equals(EfficacyAssayExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(EfficacyAssayView.CLASS, gfc));
//    }
//    else if (mobileType.equals(IndividualCaseExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(IndividualCaseView.CLASS, gfc));
//    }
//    else if (mobileType.equals(IndividualIPTExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(IndividualIPTView.CLASS, gfc));
//    }
//    else if (mobileType.equals(ITNCommunityExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ITNCommunityDistributionView.CLASS, gfc));
//    }
//    else if (mobileType.equals(ITNDistributionExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ITNDistributionView.CLASS, gfc));
//    }
//    else if (mobileType.equals(LarvacideExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(LarvacideView.CLASS, gfc));
//    }
//    else if (mobileType.equals(OperatorSprayExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(OperatorSprayView.CLASS, gfc));
//    }
//    else if (mobileType.equals(TeamSprayExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(TeamSprayView.CLASS, gfc));
//    }
//    else if (mobileType.equals(ZoneSprayExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ZoneSprayView.CLASS, gfc));
//    }
//    else if (mobileType.equals(PersonExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(PersonView.CLASS, gfc));
//    }
//    else if (mobileType.equals(PersonInterventionExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(PersonInterventionView.CLASS, gfc));
//    }
//    else if (mobileType.equals(PopulationDataExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(PopulationDataView.CLASS, gfc));
//    }
//    else if (mobileType.equals(PupalCollectionExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(PupalCollectionView.CLASS, gfc));
//    }
//    else if (mobileType.equals(SurveyExcelView.CLASS))
//    {
//      // TODO
////      odkExp.addForm(new ODKForm(Survey.CLASS, gfc));
//    }
//    else if (mobileType.equals(ThresholdDataExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ThresholdDataView.CLASS, gfc));
//    }
//    else if (mobileType.equals(ImmatureCollectionExcelView.CLASS))
//    {
//      odkExp.addForm(new ODKForm(ImmatureCollectionView.CLASS, gfc));
//    }
    else
    {
      odkExp.addForm(new ODKForm(mobileType, gfc));
    }
    
    return odkExp.doIt();
  }
  
  private static GeoFilterCriteria getGeoCriteriaFromListeners(String clazz)
  {
    ExcelExporter listenerCollector;
    
    try
    {
      listenerCollector = new ExcelExporter();
      
      // Load the type which is being exported
      Class<?> c = LoaderDecorator.load(clazz);
      
      // Get the listener method
      Method method = c.getMethod("setupExportListener", ExcelExporter.class, String[].class);
      
      // Invoke the method and get the ExcelExportListener
      method.invoke(null, listenerCollector, new String[]{});
    }
    catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
    {
      throw new ProgrammingErrorException(e);
    }
    
    List<ExcelExportListener> listeners = listenerCollector.getListeners();
    List<GeoHierarchy> ghl = new ArrayList<GeoHierarchy>();
    
    for (ExcelExportListener listener : listeners)
    {
      if (listener instanceof DynamicGeoColumnListener)
      {
         List<GeoHierarchy> list = ((DynamicGeoColumnListener)listener).getHierarchyList();
         ghl.addAll(list);
      }
    }
    
    return new GeoFilterCriteria(ghl);
  }
}
