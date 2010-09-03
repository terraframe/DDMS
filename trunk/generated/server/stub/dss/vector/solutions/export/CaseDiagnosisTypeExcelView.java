package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;

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

public class CaseDiagnosisTypeExcelView extends CaseDiagnosisTypeExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -563599147;

  private List<Term>        diagnosisType;

  private List<Integer>     diagnosisTypeAmount;
  
  public CaseDiagnosisTypeExcelView()
  {
    super();
    diagnosisType = new LinkedList<Term>();
    diagnosisTypeAmount = new LinkedList<Integer>();
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
      acv.applyAll(new CaseTreatmentView[0], new CaseTreatmentMethodView[0], new CaseTreatmentStockView[0], new CaseDiagnosticView[0], new CaseReferralView[0], new CaseStockReferralView[0], diagnosisArray, diagnosisAmountArray, new CaseDiseaseManifestationView[0], new CaseDiseaseManifestationAmountView[0][0], new CasePatientTypeView[0], new CasePatientTypeAmountView[0][0]);
    }
    else
    {
      RequiredAttributeProblem rap = new RequiredAttributeProblem();
      rap.setAttributeName(DIAGNOSISTYPE);
      rap.setAttributeDisplayLabel(CaseDiagnosisTypeExcelView.getDiagnosisTypeMd().getDisplayLabel(Session.getCurrentLocale()));
      rap.throwIt();
    }
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = AggregatedCaseExcelView.customAttributeOrder();
    list.add(DIAGNOSISTYPE);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    AggregatedCaseExcelView.setupExportListener(exporter, params);
    exporter.addListener(new CaseDiagnosisTypeListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    AggregatedCaseExcelView.setupImportListener(context, params);
    context.addListener(new CaseDiagnosisTypeListener());
  }
  
  public void addDiagnosisType(Term term, Integer amount)
  {
    diagnosisType.add(term);
    diagnosisTypeAmount.add(amount);
  }
  
}
