package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.RequiredAttributeProblem;
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

public class CasePatientTypeExcelView extends CasePatientTypeExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -918183639;
  
  private List<Term>        patientType;
  
  private List<Integer>     patientTypeAmount;
  
  public CasePatientTypeExcelView()
  {
    super();
    patientType = new LinkedList<Term>();
    patientTypeAmount = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    AggregatedCaseView acv = this.getAggregatedCaseView();
    
    CasePatientTypeView[] patientArray;
    CasePatientTypeAmountView[][] patientAmountArray;
    // Default to a new CasePatientTypeView
    CasePatientTypeView cptv = new CasePatientTypeView();
    Term patientTerm = Term.validateByDisplayLabel(this.getPatientType(), AggregatedCaseView.getCasePatientTypeMd());
    if (patientTerm!=null)
    {
      cptv.setTerm(patientTerm);
      
      // If a CaseDiagnosisTypeView already exists, use it instead
      for (CasePatientTypeView existing : acv.getPatientTypes())
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(CasePatientTypeView.TERM).equals(patientTerm.getId()))
        {
          cptv = existing;
        }
      }
      
      CasePatientTypeAmountView[] existingAmounts = cptv.getAmounts();
      patientArray = new CasePatientTypeView[]{cptv};
      patientAmountArray = new CasePatientTypeAmountView[1][patientType.size()];
      
      for (int i=0; i<patientType.size(); i++)
      {
        // Default to a new CasePatientTypeAmountView
        patientAmountArray[0][i] = new CasePatientTypeAmountView();
        Term type = patientType.get(i);
        patientAmountArray[0][i].setTerm(type);
        
        // If a CaseDiagnosisTypeAmountView already exists, use it instead
        for (CasePatientTypeAmountView existing : existingAmounts)
        {
          // Use IDs to avoid cost of instantiating the whole object
          if (existing.getValue(CasePatientTypeAmountView.TERM).equals(type.getId()))
          {
            patientAmountArray[0][i] = existing;
          }
        }
        
        // Set the amount
        patientAmountArray[0][i].setAmount(patientTypeAmount.get(i));
      }
      
      acv.applyAll(new CaseTreatmentView[0], new CaseTreatmentMethodView[0], new CaseTreatmentStockView[0], new CaseDiagnosticView[0], new CaseReferralView[0], new CaseStockReferralView[0], new CaseDiagnosisTypeView[0], new CaseDiagnosisTypeAmountView[0][0], new CaseDiseaseManifestationView[0], new CaseDiseaseManifestationAmountView[0][0], patientArray, patientAmountArray);
    }
    else
    {
      RequiredAttributeProblem rap = new RequiredAttributeProblem();
      rap.setAttributeName(PATIENTTYPE);
      rap.setAttributeDisplayLabel(CasePatientTypeExcelView.getPatientTypeMd().getDisplayLabel(Session.getCurrentLocale()));
      rap.throwIt();
    }
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = AggregatedCaseExcelView.customAttributeOrder();
    list.add(PATIENTTYPE);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    AggregatedCaseExcelView.setupExportListener(exporter, params);
    exporter.addListener(new CasePatientTypeListener());
  }
  
  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    AggregatedCaseExcelView.setupImportListener(context, params, importer);
    context.addListener(new CasePatientTypeListener());
  }
  
  public void addPatientType(Term term, Integer amount)
  {
    patientType.add(term);
    patientTypeAmount.add(amount);
  }
  
}
