package dss.vector.solutions.surveillance;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.FuturePeriodProblem;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;

public abstract class AggregatedCaseView extends AggregatedCaseViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239135495810L;

  public AggregatedCaseView()
  {
    super();
  }

  public abstract void setCases(Integer cases);

  public abstract Integer getCases();

  public abstract void setCasesMale(Integer casesFemale);

  public abstract Integer getCasesMale();

  public abstract void setCasesFemale(Integer casesMale);

  public abstract Integer getCasesFemale();

  public abstract void setCasesPregnant(Integer casesPregnant);

  public abstract Integer getCasesPregnant();

  public abstract void setDeaths(Integer deaths);

  public abstract Integer getDeaths();

  public abstract void setDeathsMale(Integer deathsMale);

  public abstract Integer getDeathsMale();

  public abstract void setDeathsFemale(Integer deathsFemale);

  public abstract Integer getDeathsFemale();

  public abstract void setDeathsPregnant(Integer deathsPregnant);

  public abstract Integer getDeathsPregnant();

  public abstract void setInPatients(Integer inPatients);

  public abstract Integer getInPatients();

  public abstract void setOutPatients(Integer outPatients);

  public abstract Integer getOutPatients();

  public abstract void setReferralsReceived(Integer referralsReceived);

  public abstract Integer getReferralsReceived();

  public abstract void setReferralsSent(Integer referralsSent);

  public abstract Integer getReferralsSent();

  public abstract void setPregnantReferralsReceived(Integer pregnantReferralsReceived);

  public abstract Integer getPregnantReferralsReceived();

  public abstract void setPregnantDiagnosis(Integer pregnantDiagnosis);

  public abstract Integer getPregnantDiagnosis();

  public abstract void setPregnantDiagnosisDeath(Integer pregnantDiagnosisDeath);

  public abstract Integer getPregnantDiagnosisDeath();

  public abstract void setClinicallyDiagnosed(Integer clinicallyDiagnosed);

  public abstract Integer getClinicallyDiagnosed();

  public abstract void setDefinitivelyDiagnosed(Integer definitivelyDiagnosed);

  public abstract Integer getDefinitivelyDiagnosed();

  public abstract void setClinicallyDiagnosedDeath(Integer clinicallyDiagnosedDeath);

  public abstract Integer getClinicallyDiagnosedDeath();

  public abstract void setDefinitivelyDiagnosedDeath(Integer definitivelyDiagnosedDeath);

  public abstract Integer getDefinitivelyDiagnosedDeath();

  public abstract void setInPatientsTotal(Integer inPatientsTotal);

  public abstract Integer getInPatientsTotal();

  public abstract void setInPatientsAnemia(Integer inPatientsAnemia);

  public abstract Integer getInPatientsAnemia();

  public abstract void setInPatientsPregnantAnemia(Integer inPatientsPregnantAnemia);

  public abstract Integer getInPatientsPregnantAnemia();

  public abstract void setInPatientsPregnantDianosis(Integer inPatientsPregnantDianosis);

  public abstract Integer getInPatientsPregnantDianosis();

  public abstract void setInPatientsFemale(Integer inPatientsFemale);

  public abstract Integer getInPatientsFemale();

  public abstract void setInPatientsMale(Integer inPatientsMale);

  public abstract Integer getInPatientsMale();

  public abstract void setInPatientsDefinitive(Integer inPatientsDefinitive);

  public abstract Integer getInPatientsDefinitive();

  public abstract void setInPatientsClinically(Integer inPatientsClinically);

  public abstract Integer getInPatientsClinically();

  public abstract void setInPatientsDischarged(Integer inPatientsDischarged);

  public abstract Integer getInPatientsDischarged();

  public abstract void setInPatientsNotTreated(Integer inPatientsNotTreated);

  public abstract Integer getInPatientsNotTreated();

  public abstract void setOutPatientsTotal(Integer outPatientsTotal);

  public abstract Integer getOutPatientsTotal();

  public abstract void setOutPatientsFemale(Integer outPatientsFemale);

  public abstract Integer getOutPatientsFemale();

  public abstract void setOutPatientsMale(Integer outPatientsMale);

  public abstract Integer getOutPatientsMale();

  public abstract void setPatientsNotTreated(Integer patientsNotTreated);

  public abstract Integer getPatientsNotTreated();

  public abstract void setOutPatientsNotTreated(Integer outPatientsNotTreated);

  public abstract Integer getOutPatientsNotTreated();

  public abstract void setStillBirths(Integer stillBirths);

  public abstract Integer getStillBirths();

  public abstract void setDaysOutOfStock(Integer daysOutOfStock);

  public abstract Integer getDaysOutOfStock();

  public AggregatedCase populateConcrete(AggregatedCase concrete)
  {
    concrete.setCases(this.getCases());
    concrete.setCasesFemale(this.getCasesFemale());
    concrete.setCasesMale(this.getCasesMale());
    concrete.setCasesPregnant(this.getCasesPregnant());
    concrete.setDeaths(this.getDeaths());
    concrete.setDeathsMale(this.getDeathsMale());
    concrete.setDeathsFemale(this.getDeathsFemale());
    concrete.setDeathsPregnant(this.getDeathsPregnant());
    concrete.setInPatients(this.getInPatients());
    concrete.setOutPatients(this.getOutPatients());
    concrete.setReferralsReceived(this.getReferralsReceived());
    concrete.setReferralsSent(this.getReferralsSent());
    concrete.setPregnantReferralsReceived(this.getPregnantReferralsReceived());
    concrete.setPregnantDiagnosis(this.getPregnantDiagnosis());
    concrete.setPregnantDiagnosisDeath(this.getPregnantDiagnosisDeath());
    concrete.setClinicallyDiagnosed(this.getClinicallyDiagnosed());
    concrete.setDefinitivelyDiagnosed(this.getDefinitivelyDiagnosed());
    concrete.setClinicallyDiagnosedDeath(this.getClinicallyDiagnosedDeath());
    concrete.setDefinitivelyDiagnosedDeath(this.getDefinitivelyDiagnosedDeath());
    concrete.setInPatientsTotal(this.getInPatientsTotal());
    concrete.setInPatientsAnemia(this.getInPatientsAnemia());
    concrete.setInPatientsPregnantAnemia(this.getInPatientsPregnantAnemia());
    concrete.setInPatientsPregnantDianosis(this.getInPatientsPregnantDianosis());
    concrete.setInPatientsFemale(this.getInPatientsFemale());
    concrete.setInPatientsMale(this.getInPatientsMale());
    concrete.setInPatientsDefinitive(this.getInPatientsDefinitive());
    concrete.setInPatientsClinically(this.getInPatientsClinically());
    concrete.setInPatientsDischarged(this.getInPatientsDischarged());
    concrete.setInPatientsNotTreated(this.getInPatientsNotTreated());
    concrete.setOutPatientsTotal(this.getOutPatientsTotal());
    concrete.setOutPatientsFemale(this.getOutPatientsFemale());
    concrete.setOutPatientsMale(this.getOutPatientsMale());
    concrete.setPatientsNotTreated(this.getPatientsNotTreated());
    concrete.setOutPatientsNotTreated(this.getOutPatientsNotTreated());
    concrete.setStillBirths(this.getStillBirths());
    concrete.setDaysOutOfStock(this.getDaysOutOfStock());

    return concrete;
  }

  public void populateView(AggregatedCase concrete)
  {
    EpiDate epiDate = EpiDate.getInstanceByDate(concrete.getStartDate(), concrete.getEndDate());

    this.setGeoEntity(concrete.getGeoEntity());
    this.setPeriod(epiDate.getPeriod());
    this.addPeriodType(epiDate.getEpiPeriodType());
    this.setPeriodYear(epiDate.getYear());
    this.setAgeGroup(concrete.getAgeGroup());
    this.setCaseId(concrete.getId());
    this.setCases(concrete.getCases());
    this.setCasesMale(concrete.getCasesMale());
    this.setCasesFemale(concrete.getCasesFemale());
    this.setCasesPregnant(concrete.getCasesPregnant());
    this.setDeaths(concrete.getDeaths());
    this.setDeathsMale(concrete.getDeathsMale());
    this.setDeathsFemale(concrete.getDeathsFemale());
    this.setDeathsPregnant(concrete.getDeathsPregnant());
    this.setInPatients(concrete.getInPatients());
    this.setOutPatients(concrete.getOutPatients());
    this.setReferralsReceived(concrete.getReferralsReceived());
    this.setReferralsSent(concrete.getReferralsSent());
    this.setPregnantReferralsReceived(concrete.getPregnantReferralsReceived());
    this.setPregnantDiagnosis(concrete.getPregnantDiagnosis());
    this.setPregnantDiagnosisDeath(concrete.getPregnantDiagnosisDeath());
    this.setClinicallyDiagnosed(concrete.getClinicallyDiagnosed());
    this.setDefinitivelyDiagnosed(concrete.getDefinitivelyDiagnosed());
    this.setClinicallyDiagnosedDeath(concrete.getClinicallyDiagnosedDeath());
    this.setDefinitivelyDiagnosedDeath(concrete.getDefinitivelyDiagnosedDeath());
    this.setInPatientsTotal(concrete.getInPatientsTotal());
    this.setInPatientsAnemia(concrete.getInPatientsAnemia());
    this.setInPatientsPregnantAnemia(concrete.getInPatientsPregnantAnemia());
    this.setInPatientsPregnantDianosis(concrete.getInPatientsPregnantDianosis());
    this.setInPatientsFemale(concrete.getInPatientsFemale());
    this.setInPatientsMale(concrete.getInPatientsMale());
    this.setInPatientsDefinitive(concrete.getInPatientsDefinitive());
    this.setInPatientsClinically(concrete.getInPatientsClinically());
    this.setInPatientsDischarged(concrete.getInPatientsDischarged());
    this.setInPatientsNotTreated(concrete.getInPatientsNotTreated());
    this.setOutPatientsTotal(concrete.getOutPatientsTotal());
    this.setOutPatientsFemale(concrete.getOutPatientsFemale());
    this.setOutPatientsMale(concrete.getOutPatientsMale());
    this.setPatientsNotTreated(concrete.getPatientsNotTreated());
    this.setOutPatientsNotTreated(concrete.getOutPatientsNotTreated());
    this.setStillBirths(concrete.getStillBirths());
    this.setDaysOutOfStock(concrete.getDaysOutOfStock());
  }

  @Override
  public void lockCase()
  {
    if (hasConcreteId())
    {
      this.populateView(AggregatedCase.lock(this.getCaseId()));
    }
  }

  @Override
  public void unlockCase()
  {
    if (hasConcreteId())
    {
      this.populateView(AggregatedCase.unlock(this.getCaseId()));
    }

  }

  public final void apply()
  {
    AggregatedCase concrete = new AggregatedCase();

    if (hasConcreteId())
    {
      concrete = AggregatedCase.get(this.getCaseId());
    }
    else
    {
      PeriodType pt = this.getPeriodType().get(0);
      EpiDate date = EpiDate.getInstanceByPeriod(pt, this.getPeriod(), this.getPeriodYear());

      concrete.setStartDate(date.getStartDate());
      concrete.setEndDate(date.getEndDate());
      concrete.setStartAge(this.getAgeGroup().getStartAge());
      concrete.setEndAge(this.getAgeGroup().getEndAge());
      concrete.setAgeGroup(this.getAgeGroup());
      concrete.setGeoEntity(this.getGeoEntity());
    }

    this.populateView(concrete);
  }

  @Transaction
  public void deleteConcrete()
  {
    if (hasConcreteId())
    {
      AggregatedCase.get(this.getCaseId()).delete();
    }
  }

  private boolean hasConcreteId()
  {
    return this.getCaseId() != null && !this.getCaseId().equals("");
  }

  @Transaction
  public void applyAll(CaseTreatment[] treatments, CaseTreatmentMethod[] treatmentMethods, CaseTreatmentStock[] stock, CaseDiagnostic[] diagnosticMethods, CaseReferral[] referrals)
  {
    AggregatedCase concrete = new AggregatedCase();

    if (hasConcreteId())
    {
      concrete = AggregatedCase.get(this.getCaseId());
    }
    else
    {
      PeriodType pt = this.getPeriodType().get(0);
      EpiDate date = EpiDate.getInstanceByPeriod(pt, this.getPeriod(), this.getPeriodYear());

      concrete.setStartDate(date.getStartDate());
      concrete.setEndDate(date.getEndDate());
      concrete.setStartAge(this.getAgeGroup().getStartAge());
      concrete.setEndAge(this.getAgeGroup().getEndAge());
      concrete.setAgeGroup(this.getAgeGroup());
      concrete.setGeoEntity(this.getGeoEntity());
    }
    
    this.buildAttributeMap(concrete);

    concrete.applyAll(treatments, treatmentMethods, stock, diagnosticMethods, referrals);

    this.populateView(concrete);
  }
  
  private void buildAttributeMap(AggregatedCase concrete)
  {
    new AttributeNotificationMap(concrete, AggregatedCase.ID, this, AggregatedCaseView.CASEID);
    new AttributeNotificationMap(concrete, AggregatedCase.AGEGROUP, this, AggregatedCaseView.AGEGROUP);
    new AttributeNotificationMap(concrete, AggregatedCase.GEOENTITY, this, AggregatedCaseView.GEOENTITY);
    new AttributeNotificationMap(concrete, AggregatedCase.STARTDATE, this, AggregatedCaseView.PERIOD);
    new AttributeNotificationMap(concrete, AggregatedCase.ENDDATE, this, AggregatedCaseView.PERIOD);
    new AttributeNotificationMap(concrete, AggregatedCase.ENDAGE, this, AggregatedCaseView.AGEGROUP);
    new AttributeNotificationMap(concrete, AggregatedCase.STARTAGE, this, AggregatedCaseView.AGEGROUP);
  }

  public CaseDiagnostic[] getDiagnosticMethods()
  {
    Set<CaseDiagnostic> set = new TreeSet<CaseDiagnostic>(new GridComparator());

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      set.add(new CaseDiagnostic(this.getId(), d.getId()));
    }

    if (this.hasConcreteId())
    {
      AggregatedCase c = AggregatedCase.get(this.getCaseId());

      for (CaseDiagnostic d : c.getAllDiagnosticMethodRel())
      {
        // We will only want Diagnostic methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationshipo with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new CaseDiagnostic[set.size()]);
  }

  public CaseReferral[] getReferrals()
  {
    Set<CaseReferral> set = new TreeSet<CaseReferral>(new GridComparator());

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseReferralsMd()))
    {
      set.add(new CaseReferral(this.getId(), d.getId()));
    }

    if (this.hasConcreteId())
    {
      AggregatedCase c = AggregatedCase.get(this.getCaseId());

      for (CaseReferral d : c.getAllReferralRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new CaseReferral[set.size()]);
  }

  public CaseTreatmentMethod[] getTreatmentMethods()
  {
    Set<CaseTreatmentMethod> set = new TreeSet<CaseTreatmentMethod>(new GridComparator());

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseTreatmentMethodMd()))
    {
      set.add(new CaseTreatmentMethod(this.getId(), d.getId()));
    }

    if (this.hasConcreteId())
    {
      AggregatedCase c = AggregatedCase.get(this.getCaseId());

      for (CaseTreatmentMethod d : c.getAllTreatmentMethodRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new CaseTreatmentMethod[set.size()]);
  }

  public CaseTreatment[] getTreatments()
  {
    Set<CaseTreatment> set = new TreeSet<CaseTreatment>(new GridComparator());

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseTreatmentsMd()))
    {
      set.add(new CaseTreatment(this.getId(), d.getId()));
    }

    if (this.hasConcreteId())
    {
      AggregatedCase c = AggregatedCase.get(this.getCaseId());

      for (CaseTreatment d : c.getAllTreatmentRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new CaseTreatment[set.size()]);
  }

  public CaseTreatmentStock[] getTreatmentStocks()
  {
    Set<CaseTreatmentStock> set = new TreeSet<CaseTreatmentStock>(new GridComparator());

    for (Term d : Term.getRootChildren(ChildCaseView.getCaseStocksMd()))
    {
      set.add(new CaseTreatmentStock(this.getId(), d.getId()));
    }

    if (this.hasConcreteId())
    {
      AggregatedCase c = AggregatedCase.get(this.getCaseId());

      for (CaseTreatmentStock d : c.getAllTreatmentStockRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new CaseTreatmentStock[set.size()]);
  }

  @Transaction
  public static void validateEpiDate(String periodType, Integer period, Integer year)
  {
    PeriodType type = PeriodType.valueOf(periodType);

    EpiDate.validate(type, period, year);

    EpiDate date = EpiDate.getInstanceByPeriod(type, period, year);

    Date current = new Date();

    if (current.before(date.getStartDate()) || current.before(date.getEndDate()))
    {
      String msg = "The period contains date past the current date";

      FuturePeriodProblem p = new FuturePeriodProblem(msg);
      p.setAttributeName(PERIODYEAR);
      p.setAttributeDisplayLabel(getPeriodYearMd().getDisplayLabel(Session.getCurrentLocale()));
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

}
