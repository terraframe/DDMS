package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView;
import dss.vector.solutions.surveillance.CaseDiagnosisTypeView;
import dss.vector.solutions.surveillance.CaseDiagnosticView;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationView;
import dss.vector.solutions.surveillance.CasePatientTypeAmountView;
import dss.vector.solutions.surveillance.CasePatientTypeView;
import dss.vector.solutions.surveillance.CaseReferralView;
import dss.vector.solutions.surveillance.CaseStockReferralView;
import dss.vector.solutions.surveillance.CaseTreatmentMethodView;
import dss.vector.solutions.surveillance.CaseTreatmentStockView;
import dss.vector.solutions.surveillance.CaseTreatmentView;

public class AggregatedCaseTreatmentsExcelView extends AggregatedCaseTreatmentsExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1333048936;

  private List<Term>        treatments;

  private List<Integer>     treatmentAmounts;

  private List<Term>        methods;

  private List<Integer>     methodAmounts;
  
  private List<Term>        stock;
  
  private List<Boolean>     stockValues;
  
  public AggregatedCaseTreatmentsExcelView()
  {
    super();
    treatments = new LinkedList<Term>();
    treatmentAmounts = new LinkedList<Integer>();
    methods = new LinkedList<Term>();
    methodAmounts = new LinkedList<Integer>();
    stock = new LinkedList<Term>();
    stockValues = new LinkedList<Boolean>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    AggregatedCaseView acv = this.getAggregatedCaseView();
    
    CaseTreatmentView[] treatmentArray = new CaseTreatmentView[treatments.size()];
    for (int i=0; i< treatments.size(); i++)
    {
      treatmentArray[i] = new CaseTreatmentView();
      treatmentArray[i].setTerm(treatments.get(i));
      treatmentArray[i].setAmount(treatmentAmounts.get(i));
    }
    
    CaseTreatmentMethodView[] methodArray = new CaseTreatmentMethodView[methods.size()];
    for (int i=0; i< methods.size(); i++)
    {
      methodArray[i] = new CaseTreatmentMethodView();
      methodArray[i].setTerm(methods.get(i));
      methodArray[i].setAmount(methodAmounts.get(i));
    }
    
    CaseTreatmentStockView[] stockArray = new CaseTreatmentStockView[stock.size()];
    for (int i=0; i< stock.size(); i++)
    {
      stockArray[i] = new CaseTreatmentStockView();
      stockArray[i].setTerm(stock.get(i));
      stockArray[i].setOutOfStock(stockValues.get(i));
    }
    
    acv.applyAll(treatmentArray, methodArray, stockArray, new CaseDiagnosticView[0], new CaseReferralView[0], new CaseStockReferralView[0], new CaseDiagnosisTypeView[0], new CaseDiagnosisTypeAmountView[0][0], new CaseDiseaseManifestationView[0], new CaseDiseaseManifestationAmountView[0][0], new CasePatientTypeView[0], new CasePatientTypeAmountView[0][0]);
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = AggregatedCaseExcelView.customAttributeOrder();
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    AggregatedCaseExcelView.setupExportListener(exporter, params);
    exporter.addListener(new AggregatedCaseTreatmentListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    AggregatedCaseExcelView.setupImportListener(context, params);
    context.addListener(new AggregatedCaseTreatmentListener());
  }

  public void addStock(Term grid, Boolean inStock)
  {
    stock.add(grid);
    stockValues.add(inStock);
  }

  public void addTreatment(Term grid, Integer count)
  {
    treatments.add(grid);
    treatmentAmounts.add(count);
  }

  public void addMethod(Term grid, Integer count)
  {
    methods.add(grid);
    methodAmounts.add(count);
  }
  
}
