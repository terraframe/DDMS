package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.intervention.monitor.IPTPatients;
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

public class AggregatedCaseReferralsExcelView extends AggregatedCaseReferralsExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1979607493;

  private List<Term>        referrals;

  private List<Integer>     referralAmounts;
  
  private List<Term>        reasons;
  
  private List<Integer>     reasonAmounts;

  private List<Term>        diagnostics;

  private List<Integer>     diagnosticAmounts;

  private List<Integer>     diagnosticPositives;
  
  public AggregatedCaseReferralsExcelView()
  {
    super();
    referrals = new LinkedList<Term>();
    referralAmounts = new LinkedList<Integer>();
    reasons = new LinkedList<Term>();
    reasonAmounts = new LinkedList<Integer>();
    diagnostics = new LinkedList<Term>();
    diagnosticAmounts = new LinkedList<Integer>();
    diagnosticPositives = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    AggregatedCaseView acv = this.getAggregatedCaseView();
    
    // Attempt to set optional attributes
    Integer cases = this.getCases();
    if (cases!=null)
    {
      acv.setCases(cases);
    }
    
    Integer positiveCases = this.getPositiveCases();
    if (positiveCases!=null)
    {
      acv.setPositiveCases(positiveCases);
    }
    
    Integer negativeCases = this.getNegativeCases();
    if (negativeCases!=null)
    {
      acv.setNegativeCases(negativeCases);
    }
    
    Integer deaths = this.getDeaths();
    if (deaths!=null)
    {
      acv.setDeaths(deaths);
    }
    
    CaseStockReferralView[] existingReferrals = acv.getStockReferrals();
    CaseStockReferralView[] referralArray = new CaseStockReferralView[referrals.size()];
    for (int i=0; i< referrals.size(); i++)
    {
      // Default to a new record
      referralArray[i] = new CaseStockReferralView();
      Term referral = referrals.get(i);
      referralArray[i].setTerm(referral);
      
      // If a record already exists, use it instead
      for (CaseStockReferralView existing : existingReferrals)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(CaseStockReferralView.TERM).equals(referral.getId()))
        {
          referralArray[i] = existing;
        }
      }
      
      // Set the amount
      referralArray[i].setAmount(referralAmounts.get(i));
    }
    
    CaseReferralView[] existingReasons = acv.getReferrals();
    CaseReferralView[] reasonArray = new CaseReferralView[reasons.size()];
    for (int i=0; i< reasons.size(); i++)
    {
      // Default to a new record
      reasonArray[i] = new CaseReferralView();
      Term reason = reasons.get(i);
      reasonArray[i].setTerm(reason);
      
      // If a record already exists, use it instead
      for (CaseReferralView existing : existingReasons)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(CaseReferralView.TERM).equals(reason.getId()))
        {
          reasonArray[i] = existing;
        }
      }
      
      // Set the amount
      reasonArray[i].setAmount(reasonAmounts.get(i));
    }
    
    CaseDiagnosticView[] existingDiagnostics = acv.getDiagnosticMethods();
    CaseDiagnosticView[] diagnosticArray = new CaseDiagnosticView[diagnostics.size()];
    for (int i=0; i< diagnostics.size(); i++)
    {
      // Default to a new record
      diagnosticArray[i] = new CaseDiagnosticView();
      Term diagnostic = diagnostics.get(i);
      diagnosticArray[i].setTerm(diagnostic);
      
      // If a record already exists, use it instead
      for (CaseDiagnosticView existing : existingDiagnostics)
      {
        // Use IDs to avoid cost of instantiating the whole object
        if (existing.getValue(CaseDiagnosticView.TERM).equals(diagnostic.getId()))
        {
          diagnosticArray[i] = existing;
        }
      }
      
      // Set the amounts
      diagnosticArray[i].setAmount(diagnosticAmounts.get(i));
      diagnosticArray[i].setAmountPositive(diagnosticPositives.get(i));
    }
    
    acv.applyAll(new CaseTreatmentView[0], new CaseTreatmentMethodView[0], new CaseTreatmentStockView[0], diagnosticArray, reasonArray, referralArray, new CaseDiagnosisTypeView[0], new CaseDiagnosisTypeAmountView[0][0], new CaseDiseaseManifestationView[0], new CaseDiseaseManifestationAmountView[0][0], new CasePatientTypeView[0], new CasePatientTypeAmountView[0][0]);
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = AggregatedCaseExcelView.customAttributeOrder();
    list.add(CASES);
    list.add(POSITIVECASES);
    list.add(NEGATIVECASES);
    list.add(DEATHS);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    AggregatedCaseExcelView.setupExportListener(exporter, params);
    exporter.addListener(new AggregatedCaseReferralListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    AggregatedCaseExcelView.setupImportListener(context, params);
    context.addListener(new AggregatedCaseReferralListener());
  }
  
  public void addReferral(Term grid, Integer count)
  {
    referrals.add(grid);
    referralAmounts.add(count);
  }
  
  public void addReason(Term grid, Integer total)
  {
    reasons.add(grid);
    reasonAmounts.add(total);
  }
  
  public void addDiagnostic(Term grid, Integer total, Integer positive)
  {
    diagnostics.add(grid);
    diagnosticAmounts.add(total);
    diagnosticPositives.add(positive);
  }
}
