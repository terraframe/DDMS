package dss.vector.solutions.surveillance;

import java.util.Date;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;

import dss.vector.solutions.FuturePeriodProblem;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;

public class AggregatedCaseView extends AggregatedCaseViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239135495810L;

  public AggregatedCaseView()
  {
    super();
  }

  public void populateConcrete(AggregatedCase concrete)
  {
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setStartDate(this.getStartDate());
    concrete.setEndDate(this.getEndDate());
    concrete.setAgeGroup(this.getAgeGroup());
    concrete.setCases(this.getCases());
    concrete.setPositiveCases(this.getPositiveCases());
    concrete.setNegativeCases(this.getNegativeCases());
    concrete.setDeaths(this.getDeaths());
  }

  public void populateView(AggregatedCase concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setGeoEntity(concrete.getGeoEntity());
    this.setStartDate(concrete.getStartDate());
    this.setEndDate(concrete.getEndDate());
    this.setAgeGroup(concrete.getAgeGroup());
    this.setCases(concrete.getCases());
    this.setPositiveCases(concrete.getPositiveCases());
    this.setNegativeCases(concrete.getNegativeCases());
    this.setDeaths(concrete.getDeaths());
  }

  public final void apply()
  {
    AggregatedCase concrete = new AggregatedCase();

    if (this.hasConcrete())
    {
      concrete = AggregatedCase.lock(this.getConcreteId());
    }

    this.populateConcrete(concrete);
    
    this.buildAttributeMap(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Transaction
  public void deleteConcrete()
  {
    if (hasConcrete())
    {
      AggregatedCase.get(this.getConcreteId()).delete();
    }
  }

  boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  private void buildAttributeMap(AggregatedCase concrete)
  {
    new AttributeNotificationMap(concrete, AggregatedCase.ID, this, AggregatedCaseView.CONCRETEID);
    new AttributeNotificationMap(concrete, AggregatedCase.AGEGROUP, this, AggregatedCaseView.AGEGROUP);
    new AttributeNotificationMap(concrete, AggregatedCase.GEOENTITY, this, AggregatedCaseView.GEOENTITY);
    new AttributeNotificationMap(concrete, AggregatedCase.STARTDATE, this, AggregatedCaseView.STARTDATE);
    new AttributeNotificationMap(concrete, AggregatedCase.ENDDATE, this, AggregatedCaseView.ENDDATE);
    new AttributeNotificationMap(concrete, AggregatedCase.CASES, this, AggregatedCaseView.CASES);
    new AttributeNotificationMap(concrete, AggregatedCase.NEGATIVECASES, this, AggregatedCaseView.NEGATIVECASES);
    new AttributeNotificationMap(concrete, AggregatedCase.POSITIVECASES, this, AggregatedCaseView.POSITIVECASES);
  }

  @Transaction
  public static void validateEpiDate(String periodType, Integer period, Integer year)
  {
    PeriodType type = PeriodType.valueOf(periodType);

    // IMPORTANT: WEEK is 0 based while MONTH and QUARTER are 1 based. Thus we
    // need to offset the 'period' for WEEK
    Integer _period = ( type.equals(PeriodType.WEEK) ? period - 1 : period );

    EpiDate.validate(type, _period, year);

    EpiDate date = EpiDate.getInstanceByPeriod(type, _period, year);

    Date current = new Date();

    if (current.before(date.getStartDate()) || current.before(date.getEndDate()))
    {
      String msg = "The period contains date past the current date";

      FuturePeriodProblem p = new FuturePeriodProblem(msg);
      p.setAttributeName(AggregatedCaseSearchView.PERIODYEAR);
      p.setAttributeDisplayLabel(AggregatedCaseSearchView.getPeriodYearMd().getDisplayLabel(Session.getCurrentLocale()));
      p.setDefiningType(AggregatedCaseView.CLASS);
      p.apply();
      p.throwIt();
    }
  }

  @Transaction
  public static void validateSearchCriteria(String geoId, String periodType, Integer period, Integer year)
  {
    // Validate that the geo id references a real geo entity by retreving the
    // Geo Entity
    GeoEntity.searchByGeoId(geoId);

    // Validate the epi date
    validateEpiDate(periodType, period, year);
  }
  
  AggregatedCase getConcrete()
  {
    if(this.hasConcrete())
    {
      return AggregatedCase.get(this.getConcreteId());
    }
    
    return null;
  }

  
  @Override
  @Transaction
  public void applyAll(CaseTreatmentView[] treatments, CaseTreatmentMethodView[] treatmentMethods, CaseTreatmentStockView[] stock, CaseDiagnosticView[] diagnosticMethods, CaseReferralView[] referrals, CaseStockReferralView[] stockReferrals, CaseDiagnosisTypeView[] diagnosticTypes, CaseDiagnosisTypeAmountView[][] diagnosticTypeAmounts, CaseDiseaseManifestationView[] diseaseManifestations, CaseDiseaseManifestationAmountView[][] diseaseManifestationAmounts, CasePatientTypeView[] patientTypes, CasePatientTypeAmountView[][] patientTypeAmounts)
  {
    this.apply();
    
    AggregatedCase concrete = this.getConcrete();
    
    for (CaseDiagnosticView view : diagnosticMethods)
    {
      view.setAggregatedCase(concrete);
      view.apply();
    }
    
    for (CaseReferralView view : referrals)
    {
      view.setAggregatedCase(concrete);
      view.apply();
    }

    for (CaseTreatmentView view : treatments)
    {
      view.setAggregatedCase(concrete);
      view.apply();
    }
    
    for (CaseTreatmentMethodView view : treatmentMethods)
    {
      view.setAggregatedCase(concrete);
      view.apply();
    }
    
    for (CaseTreatmentStockView view : stock)
    {
      view.setAggregatedCase(concrete);
      view.apply();
    }

    for (CaseStockReferralView view : stockReferrals)
    {
      view.setAggregatedCase(concrete);
      view.apply();
    }

    for (int i = 0; i < diagnosticTypes.length; i++)
    {
      CaseDiagnosisTypeView view = diagnosticTypes[i];
      view.setAggregatedCase(concrete);
      
      view.applyAll(diagnosticTypeAmounts[i]);
    }
    
    for (int i = 0; i < diseaseManifestations.length; i++)
    {
      CaseDiseaseManifestationView view = diseaseManifestations[i];
      view.setAggregatedCase(concrete);
      
      view.applyAll(diseaseManifestationAmounts[i]);
    }
    
    for (int i = 0; i < patientTypes.length; i++)
    {
      CasePatientTypeView view = patientTypes[i];
      view.setAggregatedCase(concrete);
      
      view.applyAll(patientTypeAmounts[i]);
    }
  }

  public CaseDiagnosticView[] getDiagnosticMethods()
  {
    return CaseDiagnosticView.getDiagnosticMethods(this);
  }


  public CaseReferralView[] getReferrals()
  {
    return CaseReferralView.getReferrals(this);
  }

  public CaseTreatmentMethodView[] getTreatmentMethods()
  {
    return CaseTreatmentMethodView.getTreatmentMethods(this);
  }

  public CaseTreatmentView[] getTreatments()
  {
    return CaseTreatmentView.getTreatments(this);
  }

  public CaseTreatmentStockView[] getTreatmentStocks()
  {
    return CaseTreatmentStockView.getTreatmentStocks(this);
  }
  
  @Override
  public CaseStockReferralView[] getStockReferrals()
  {
    return CaseStockReferralView.getReferrals(this);
  }
  
  @Override
  public CaseDiagnosisTypeView[] getDiagnosticTypes()
  {
    return CaseDiagnosisTypeView.getDiagnosisTypes(this);
  }
  
  @Override
  public CaseDiseaseManifestationView[] getDiseaseManifestations()
  {
    return CaseDiseaseManifestationView.getDiseaseManifestations(this);
  }

  @Override
  public CasePatientTypeView[] getPatientTypes()
  {
    return CasePatientTypeView.getPatientTypes(this);
  }
}
