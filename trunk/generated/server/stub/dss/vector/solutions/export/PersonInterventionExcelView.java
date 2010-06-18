package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView;
import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.intervention.monitor.PersonInterventionMethodView;
import dss.vector.solutions.intervention.monitor.PersonInterventionView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class PersonInterventionExcelView extends PersonInterventionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 311164596;

  private List<Term>        interventionMethod;

  private List<Integer>     interventionMethodAmount;
  
  public PersonInterventionExcelView()
  {
    super();
    interventionMethod = new LinkedList<Term>();
    interventionMethodAmount = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ControlInterventionView controlPoint = this.getControlPoint();
    PersonInterventionView piv = new PersonInterventionView();
    piv.setVehicleDays(this.getVehicleDays());

    PersonInterventionMethodView[][] methodArray = new PersonInterventionMethodView[1][interventionMethod.size()];
    for (int i = 0; i < interventionMethod.size(); i++)
    {
      methodArray[0][i] = new PersonInterventionMethodView();
      methodArray[0][i].setTerm(interventionMethod.get(i));
      methodArray[0][i].setAmount(interventionMethodAmount.get(i));
    }
    
    controlPoint.applyWithPersonInterventionViews(new PersonInterventionView[] {piv}, methodArray);
  }

  public static List<String> customAttributeOrder()
  {
    List<String> list = ControlInterventionExcelView.customAttributeOrder();
    list.add(VEHICLEDAYS);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    ControlInterventionExcelView.setupExportListener(exporter, params);
    exporter.addListener(new PersonInterventionListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    ControlInterventionExcelView.setupImportListener(context, params);
    context.addListener(new PersonInterventionListener());
  }

  public void addInterventionMethod(Term term, Integer amount)
  {
    interventionMethod.add(term);
    interventionMethodAmount.add(amount);
  }
}
