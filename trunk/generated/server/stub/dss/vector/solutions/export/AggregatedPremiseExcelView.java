package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class AggregatedPremiseExcelView extends AggregatedPremiseExcelViewBase implements Reloadable
{
  private static final long serialVersionUID = 1378016463;

  private List<Term>        interventionMethod;

  private List<Integer>     interventionMethodAmount;
  
  private List<Term>        nonTreatmentReason;
  
  private List<Integer>     nonTreatmentReasonAmount;
  
  public AggregatedPremiseExcelView()
  {
    super();
    interventionMethod = new LinkedList<Term>();
    interventionMethodAmount = new LinkedList<Integer>();
    nonTreatmentReason = new LinkedList<Term>();
    nonTreatmentReasonAmount = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ControlInterventionView controlPoint = this.getControlPoint();
    AggregatedPremiseVisitView apv = new AggregatedPremiseVisitView();
    apv.setGeoEntity(this.getPremiseGeoEntity());
    apv.setPremisesAvailable(this.getPremisesAvailable());
    apv.setPremisesIncluded(this.getPremisesIncluded());
    apv.setPremises(this.getPremises());
    apv.setVisited(this.getVisited());
    apv.setTreated(this.getTreated());

    AggregatedPremiseMethodView[][] methodArray = new AggregatedPremiseMethodView[1][interventionMethod.size()];
    for (int i = 0; i < interventionMethod.size(); i++)
    {
      methodArray[0][i] = new AggregatedPremiseMethodView();
      methodArray[0][i].setTerm(interventionMethod.get(i));
      methodArray[0][i].setAmount(interventionMethodAmount.get(i));
    }
    
    AggregatedPremiseReasonView[][] reasonArray = new AggregatedPremiseReasonView[1][nonTreatmentReason.size()];
    for (int i = 0; i < nonTreatmentReason.size(); i++)
    {
      reasonArray[0][i] = new AggregatedPremiseReasonView();
      reasonArray[0][i].setTerm(nonTreatmentReason.get(i));
      reasonArray[0][i].setAmount(nonTreatmentReasonAmount.get(i));
    }
    
    controlPoint.applyWithAggregatedPremiseViews(new AggregatedPremiseVisitView[] {apv}, reasonArray, methodArray);
  }

  public static List<String> customAttributeOrder()
  {
    List<String> list = ControlInterventionExcelView.customAttributeOrder();
    list.add(PREMISESAVAILABLE);
    list.add(PREMISESINCLUDED);
    list.add(PREMISES);
    list.add(VISITED);
    list.add(TREATED);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    ControlInterventionExcelView.setupExportListener(exporter, params);
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(new AggregatedPremiseListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    ControlInterventionExcelView.setupImportListener(context, params);
    context.addListener(createExcelGeoListener());
    context.addListener(new AggregatedPremiseListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllUrban())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, PREMISEGEOENTITY, builder);
  }

  public void addInterventionMethod(Term term, Integer amount)
  {
    interventionMethod.add(term);
    interventionMethodAmount.add(amount);
  }

  public void addNonTreatmentReason(Term term, Integer amount)
  {
    nonTreatmentReason.add(term);
    nonTreatmentReasonAmount.add(amount);
  }
  
}
