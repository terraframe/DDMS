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

public class AggregatedCaseTypesExcelView extends AggregatedCaseTypesExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -928027342;

  private List<Term>        diagnosisType;

  private List<Integer>     diagnosisTypeAmount;
  
  private List<Term>        diseaseCategory;
  
  private List<Integer>     diseaseCategoryAmount;
  
  private List<Term>        patientType;
  
  private List<Integer>     patientTypeAmount;
  
  public AggregatedCaseTypesExcelView()
  {
    super();
    diagnosisType = new LinkedList<Term>();
    diagnosisTypeAmount = new LinkedList<Integer>();
    diseaseCategory = new LinkedList<Term>();
    diseaseCategoryAmount = new LinkedList<Integer>();
    patientType = new LinkedList<Term>();
    patientTypeAmount = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    AggregatedCaseView acv = this.getAggregatedCaseView();
    
    CaseDiagnosisTypeView[] diagnosisArray;
    CaseDiagnosisTypeAmountView[][] diagnosisAmountArray;
    CaseDiagnosisTypeView cdtv = new CaseDiagnosisTypeView();
    Term diagnosisTerm = Term.validateByDisplayLabel(this.getDiagnosisType(), AggregatedCaseView.getCaseDiagnosisTypeMd());
    if (diagnosisTerm!=null)
    {
      cdtv.setTerm(diagnosisTerm);
      diagnosisArray = new CaseDiagnosisTypeView[]{cdtv};
      diagnosisAmountArray = new CaseDiagnosisTypeAmountView[1][diagnosisType.size()];
      
      for (int i=0; i<diagnosisType.size(); i++)
      {
        diagnosisAmountArray[0][i] = new CaseDiagnosisTypeAmountView();
        diagnosisAmountArray[0][i].setTerm(diagnosisType.get(i));
        diagnosisAmountArray[0][i].setAmount(diagnosisTypeAmount.get(i));
      }
    }
    else
    {
      diagnosisArray = new CaseDiagnosisTypeView[0];
      diagnosisAmountArray = new CaseDiagnosisTypeAmountView[0][0];
    }
    
    CaseDiseaseManifestationView[] diseaseArray;
    CaseDiseaseManifestationAmountView[][] diseaseAmountArray;
    CaseDiseaseManifestationView cdmv = new CaseDiseaseManifestationView();
    Term diseaseTerm = Term.validateByDisplayLabel(this.getDiseaseManifestation(), AggregatedCaseView.getCaseDiseaseManifestationMd());
    if (diseaseTerm!=null)
    {
      cdmv.setTerm(diseaseTerm);
      diseaseArray = new CaseDiseaseManifestationView[]{cdmv};
      diseaseAmountArray = new CaseDiseaseManifestationAmountView[1][diseaseCategory.size()];
      
      for (int i=0; i<diseaseCategory.size(); i++)
      {
        diseaseAmountArray[0][i] = new CaseDiseaseManifestationAmountView();
        diseaseAmountArray[0][i].setTerm(diseaseCategory.get(i));
        diseaseAmountArray[0][i].setAmount(diseaseCategoryAmount.get(i));
      }
    }
    else
    {
      diseaseArray = new CaseDiseaseManifestationView[0];
      diseaseAmountArray = new CaseDiseaseManifestationAmountView[0][0];
    }
    
    CasePatientTypeView[] patientArray;
    CasePatientTypeAmountView[][] patientAmountArray;
    CasePatientTypeView cptv = new CasePatientTypeView();
    Term patientTerm = Term.validateByDisplayLabel(this.getPatientType(), AggregatedCaseView.getCasePatientTypeMd());
    if (patientTerm!=null)
    {
      cptv.setTerm(patientTerm);
      patientArray = new CasePatientTypeView[]{cptv};
      patientAmountArray = new CasePatientTypeAmountView[1][patientType.size()];
      
      for (int i=0; i<patientType.size(); i++)
      {
        patientAmountArray[0][i] = new CasePatientTypeAmountView();
        patientAmountArray[0][i].setTerm(patientType.get(i));
        patientAmountArray[0][i].setAmount(patientTypeAmount.get(i));
      }
    }
    else
    {
      patientArray = new CasePatientTypeView[0];
      patientAmountArray = new CasePatientTypeAmountView[0][0];
    }
    
    acv.applyAll(new CaseTreatmentView[0], new CaseTreatmentMethodView[0], new CaseTreatmentStockView[0], new CaseDiagnosticView[0], new CaseReferralView[0], new CaseStockReferralView[0], diagnosisArray, diagnosisAmountArray, diseaseArray, diseaseAmountArray, patientArray, patientAmountArray);
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = AggregatedCaseExcelView.customAttributeOrder();
    list.add(DIAGNOSISTYPE);
    list.add(DISEASEMANIFESTATION);
    list.add(PATIENTTYPE);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    AggregatedCaseExcelView.setupExportListener(exporter, params);
    exporter.addListener(new AggregatedCaseTypesListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    AggregatedCaseExcelView.setupImportListener(context, params);
    context.addListener(new AggregatedCaseTypesListener());
  }
  
  public void addDiagnosisType(Term term, Integer amount)
  {
    diagnosisType.add(term);
    diagnosisTypeAmount.add(amount);
  }
  
  public void addDiseaseCategory(Term term, Integer amount)
  {
    diseaseCategory.add(term);
    diseaseCategoryAmount.add(amount);
  }
  
  public void addPatientType(Term term, Integer amount)
  {
    patientType.add(term);
    patientTypeAmount.add(amount);
  }
}
