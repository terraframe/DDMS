package dss.vector.solutions.export;

import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.intervention.monitor.InsecticideInterventionView;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.ontology.Term;

public class InsecticideInterventionExcelView extends InsecticideInterventionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -784221955;
  
  public InsecticideInterventionExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ControlInterventionView controlPoint = this.getControlPoint();
    
    InsecticideInterventionView[] existingArray = controlPoint.getInsecticideInterventionViews();
    
    // Default to a new relationship
    Term method = Term.validateByDisplayLabel(this.getInterventionMethod(), ControlInterventionView.getInsecticideInterventionMd());
    InsecticideInterventionView iiv = new InsecticideInterventionView();
    iiv.setInterventionMethod(method);
    
    // If a relationship already exists, use it instead
    for (InsecticideInterventionView existing : existingArray)
    {
      // Use IDs to avoid cost of instantiating the whole object
      if (existing.getValue(InsecticideInterventionView.INTERVENTIONMETHOD).equals(method.getId()))
      {
        iiv = existing;
        break;
      }
    }
    
    // Set the information
    iiv.setInsecticide(InsecticideBrand.validateByName(this.getInsecticide()));
    iiv.setQuantity(this.getQuantity());
    iiv.setUnit(Term.validateByDisplayLabel(this.getUnit(), InsecticideInterventionView.getUnitMd()));
    
    controlPoint.applyWithInsecticideInterventionViews(new InsecticideInterventionView[] {iiv});
  }

  public static List<String> customAttributeOrder()
  {
    List<String> list = ControlInterventionExcelView.customAttributeOrder();
    list.add(INTERVENTIONMETHOD);
    list.add(INSECTICIDE);
    list.add(QUANTITY);
    list.add(UNIT);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    ControlInterventionExcelView.setupExportListener(exporter, params);
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    ControlInterventionExcelView.setupImportListener(context, params);
  }
}
