package dss.vector.solutions.surveillance;

import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.FuturePeriodProblem;
import dss.vector.solutions.PeriodMonthProblem;
import dss.vector.solutions.PeriodQuarterProblem;
import dss.vector.solutions.PeriodWeekProblem;



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

  public AggregatedCase getAggregatedCase()
  {
    AggregatedCase c = new AggregatedCase();

    if(hasCaseId())
    {
      c = AggregatedCase.get(this.getCaseId());
    }
    else
    {
      PeriodType pt = this.getPeriodType().get(0);
      EpiDate date = new EpiDate(pt, this.getPeriod(), this.getPeriodYear());

      c.setStartDate(date.getStartDate());
      c.setEndDate(date.getEndDate());
      c.setStartAge(this.getAgeGroup().getStartAge());
      c.setEndAge(this.getAgeGroup().getEndAge());
      c.setAgeGroup(this.getAgeGroup());
      c.setGeoEntity(this.getGeoEntity());
    }

    c.setCases(this.getCases());
    c.setCasesFemale(this.getCasesFemale());
    c.setCasesMale(this.getCasesMale());
    c.setCasesPregnant(this.getCasesPregnant());
    c.setDeaths(this.getDeaths());
    c.setDeathsMale(this.getDeathsMale());
    c.setDeathsFemale(this.getDeathsFemale());
    c.setDeathsPregnant(this.getDeathsPregnant());
    c.setInPatients(this.getInPatients());
    c.setOutPatients(this.getOutPatients());
    c.setReferralsReceived(this.getReferralsReceived());
    c.setReferralsSent(this.getReferralsSent());
    c.setPregnantReferralsReceived(this.getPregnantReferralsReceived());
    c.setPregnantDiagnosis(this.getPregnantDiagnosis());
    c.setPregnantDiagnosisDeath(this.getPregnantDiagnosisDeath());
    c.setClinicallyDiagnosed(this.getClinicallyDiagnosed());
    c.setDefinitivelyDiagnosed(this.getDefinitivelyDiagnosed());
    c.setClinicallyDiagnosedDeath(this.getClinicallyDiagnosedDeath());
    c.setDefinitivelyDiagnosedDeath(this.getDefinitivelyDiagnosedDeath());
    c.setInPatientsTotal(this.getInPatientsTotal());
    c.setInPatientsAnemia(this.getInPatientsAnemia());
    c.setInPatientsPregnantAnemia(this.getInPatientsPregnantAnemia());
    c.setInPatientsPregnantDianosis(this.getInPatientsPregnantDianosis());
    c.setInPatientsFemale(this.getInPatientsFemale());
    c.setInPatientsMale(this.getInPatientsMale());
    c.setInPatientsDefinitive(this.getInPatientsDefinitive());
    c.setInPatientsClinically(this.getInPatientsClinically());
    c.setInPatientsDischarged(this.getInPatientsDischarged());
    c.setInPatientsNotTreated(this.getInPatientsNotTreated());
    c.setOutPatientsTotal(this.getOutPatientsTotal());
    c.setOutPatientsFemale(this.getOutPatientsFemale());
    c.setOutPatientsMale(this.getOutPatientsMale());
    c.setPatientsNotTreated(this.getPatientsNotTreated());
    c.setOutPatientsNotTreated(this.getOutPatientsNotTreated());
    c.setStillBirths(this.getStillBirths());
    c.setDaysOutOfStock(this.getDaysOutOfStock());

    return c;
  }

  @Override
  public void lockCase()
  {
    if(hasCaseId())
    {
      AggregatedCase lock = AggregatedCase.lock(this.getCaseId());
      lock.updateView(this);
    }
  }

  @Override
  public void unlockCase()
  {
    if(hasCaseId())
    {
      AggregatedCase lock = AggregatedCase.unlock(this.getCaseId());
      lock.updateView(this);
    }

  }

  public final void apply()
  {
    AggregatedCase aggregatedCase = this.getAggregatedCase();
    aggregatedCase.apply();

    aggregatedCase.updateView(this);
  }

  @Override
  public final void delete()
  {
    if(hasCaseId())
    {
      AggregatedCase.get(this.getCaseId()).delete();
    }
  }

  private boolean hasCaseId()
  {
    return this.getCaseId() != null && !this.getCaseId().equals("");
  }

  public void applyAll(CaseTreatment[] treatments, CaseTreatmentMethod[] treatmentMethods, CaseTreatmentStock[] stock, CaseDiagnostic[] diagnosticMethods, CaseReferral[] referrals)
  {
    AggregatedCase aggregatedCase = this.getAggregatedCase();
    aggregatedCase.applyAll(treatments, treatmentMethods, stock, diagnosticMethods, referrals);

    aggregatedCase.updateView(this);
  }

  public static AggregatedCaseView get(String id)
  {
    return (AggregatedCaseView) AggregatedCase.get(id).getView();
  }
  
  @Transaction
  public static void validateEpiDate(String periodType, Integer period, Integer year)
  {
    PeriodType type = PeriodType.valueOf(periodType);
    
    if (period > type.getMaximumPeriod() && type.equals(PeriodType.QUARTER))
    {
      PeriodQuarterProblem p = new PeriodQuarterProblem();
      p.setPeriod(period);
      p.setMaxPeriod(type.getMaximumPeriod());
      p.throwIt();
    }
    else if (period > type.getMaximumPeriod() && type.equals(PeriodType.MONTH))
    {
      PeriodMonthProblem p = new PeriodMonthProblem();
      p.setPeriod(period);
      p.setMaxPeriod(type.getMaximumPeriod());
      p.throwIt();
    }
    else if (period > type.getMaximumPeriod() && type.equals(PeriodType.WEEK))
    {
      PeriodWeekProblem p = new PeriodWeekProblem();
      p.setPeriod(period);
      p.setMaxPeriod(type.getMaximumPeriod());
      p.throwIt();
    }    
    
    EpiDate date = new EpiDate(type, period, year);
    
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
}
