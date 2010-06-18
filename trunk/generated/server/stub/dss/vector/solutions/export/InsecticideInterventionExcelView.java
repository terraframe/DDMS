package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.intervention.monitor.InsecticideInterventionView;
import dss.vector.solutions.intervention.monitor.PersonInterventionMethodView;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.ontology.Term;

public class InsecticideInterventionExcelView extends InsecticideInterventionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -784221955;
  
  private List<Term> interventionMethods;
  private List<InsecticideBrand> insecticides;
  private List<Integer> quantities;
  private List<Term> units;
  
  public InsecticideInterventionExcelView()
  {
    super();
    interventionMethods = new LinkedList<Term>();
    insecticides = new LinkedList<InsecticideBrand>();
    quantities = new LinkedList<Integer>();
    units = new LinkedList<Term>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ControlInterventionView controlPoint = this.getControlPoint();

    InsecticideInterventionView[] array = new InsecticideInterventionView[interventionMethods.size()];
    for (int i = 0; i < interventionMethods.size(); i++)
    {
      array[i] = new InsecticideInterventionView();
      array[i].setInterventionMethod(interventionMethods.get(i));
      array[i].setInsecticide(insecticides.get(i));
      array[i].setQuantity(quantities.get(i));
      array[i].setUnit(units.get(i));
    }
    
    controlPoint.applyWithInsecticideInterventionViews(array);
  }

  public static List<String> customAttributeOrder()
  {
    List<String> list = ControlInterventionExcelView.customAttributeOrder();
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    ControlInterventionExcelView.setupExportListener(exporter, params);
    exporter.addListener(new InsecticideInterventionListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    ControlInterventionExcelView.setupImportListener(context, params);
    context.addListener(new InsecticideInterventionListener());
  }

  public void addInterventionMethod(Term method)
  {
    interventionMethods.add(method);
  }

  public void addInsecticide(String brand)
  {
    insecticides.add(InsecticideBrand.validateByName(brand));
  }

  public void addQuantity(Integer quantity)
  {
    quantities.add(quantity);
  }

  public void addUnit(String unitLabel)
  {
    units.add(Term.validateByDisplayLabel(unitLabel, InsecticideInterventionView.getUnitMd()));
  }
}
