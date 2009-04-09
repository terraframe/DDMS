package dss.vector.solutions.surveillance;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class AggregatedCase extends AggregatedCaseBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693161773L;

  public AggregatedCase()
  {
    super();
  }

  @Override
  public void apply()
  {
    this.setStartAge(this.getAgeGroup().getStartAge());
    this.setEndAge(this.getAgeGroup().getEndAge());

    super.apply();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction
    for (CaseDiagnostic diagnostic : this.getAllDiagnosticMethodRel())
    {
      diagnostic.lock();
    }

    for (CaseReferral referral : this.getAllReferralRel())
    {
      referral.lock();
    }

    for (CaseTreatment treatment : this.getAllTreatmentRel())
    {
      treatment.lock();
    }

    for (CaseTreatmentMethod method : this.getAllTreatmentMethodRel())
    {
      method.lock();
    }

    for (CaseTreatmentStock stock : this.getAllTreatmentStockRel())
    {
      stock.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    // Unlock the grid relationship also, this must be in the same transaction
    for (CaseDiagnostic diagnostic : this.getAllDiagnosticMethodRel())
    {
      diagnostic.unlock();
    }

    for (CaseReferral referral : this.getAllReferralRel())
    {
      referral.unlock();
    }

    for (CaseTreatment treatment : this.getAllTreatmentRel())
    {
      treatment.unlock();
    }

    for (CaseTreatmentMethod method : this.getAllTreatmentMethodRel())
    {
      method.unlock();
    }

    for (CaseTreatmentStock stock : this.getAllTreatmentStockRel())
    {
      stock.unlock();
    }

    super.unlock();
  }

  @Override
  @Transaction
  public void applyAll(CaseTreatment[] treatments, CaseTreatmentMethod[] treatmentMethods,
      CaseTreatmentStock[] stock, CaseDiagnostic[] diagnosticMethods, CaseReferral[] referrals)
  {
    boolean newCase = this.isNew();

    this.apply();

    if (newCase)
    {
      for (int i = 0; i < diagnosticMethods.length; i++)
      {
        diagnosticMethods[i] = diagnosticMethods[i].clone(this);
      }

      for (int i = 0; i < referrals.length; i++)
      {
        referrals[i] = referrals[i].clone(this);
      }

      for (int i = 0; i < treatments.length; i++)
      {
        treatments[i] = treatments[i].clone(this);
      }

      for (int i = 0; i < treatmentMethods.length; i++)
      {
        treatmentMethods[i] = treatmentMethods[i].clone(this);
      }

      for (int i = 0; i < stock.length; i++)
      {
        stock[i] = stock[i].clone(this);
      }
    }

    for (CaseDiagnostic method : diagnosticMethods)
    {
      method.apply();
    }

    for (CaseReferral referral : referrals)
    {
      referral.apply();
    }

    for (CaseTreatment treatment : treatments)
    {
      treatment.apply();
    }

    for (CaseTreatmentMethod method : treatmentMethods)
    {
      method.apply();
    }

    for (CaseTreatmentStock s : stock)
    {
      s.apply();
    }

  }

  public static AggregatedCase searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate,
      Date endDate, AggregatedAgeGroup ageGroup)
  {
    AggregatedCaseQuery query = new AggregatedCaseQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));
    query.AND(query.getStartAge().EQ(ageGroup.getStartAge()));
    query.AND(query.getEndAge().EQ(ageGroup.getEndAge()));

    OIterator<? extends AggregatedCase> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public AggregatedCaseView getView()
  {
    return this.updateView(this.getAgeGroup().getView());
  }

  public AggregatedCaseView updateView(AggregatedCaseView view)
  {
    view.setGeoEntity(this.getGeoEntity());
    view.setAgeGroup(this.getAgeGroup());
    view.setCaseId(this.getId());
    view.setCases(this.getCases());
    view.setCasesMale(this.getCasesMale());
    view.setCasesFemale(this.getCasesFemale());
    view.setCasesPregnant(this.getCasesPregnant());
    view.setDeaths(this.getDeaths());
    view.setDeathsMale(this.getDeathsMale());
    view.setDeathsFemale(this.getDeathsFemale());
    view.setDeathsPregnant(this.getDeathsPregnant());
    view.setInPatients(this.getInPatients());
    view.setOutPatients(this.getOutPatients());
    view.setReferralsReceived(this.getReferralsReceived());
    view.setReferralsSent(this.getReferralsSent());
    view.setPregnantReferralsReceived(this.getPregnantReferralsReceived());
    view.setPregnantDiagnosis(this.getPregnantDiagnosis());
    view.setPregnantDiagnosisDeath(this.getPregnantDiagnosisDeath());
    view.setClinicallyDiagnosed(this.getClinicallyDiagnosed());
    view.setDefinitivelyDiagnosed(this.getDefinitivelyDiagnosed());
    view.setClinicallyDiagnosedDeath(this.getClinicallyDiagnosedDeath());
    view.setDefinitivelyDiagnosedDeath(this.getDefinitivelyDiagnosedDeath());
    view.setInPatientsTotal(this.getInPatientsTotal());
    view.setInPatientsAnemia(this.getInPatientsAnemia());
    view.setInPatientsPregnantAnemia(this.getInPatientsPregnantAnemia());
    view.setInPatientsPregnantDianosis(this.getInPatientsPregnantDianosis());
    view.setInPatientsFemale(this.getInPatientsFemale());
    view.setInPatientsMale(this.getInPatientsMale());
    view.setInPatientsDefinitive(this.getInPatientsDefinitive());
    view.setInPatientsClinically(this.getInPatientsClinically());
    view.setInPatientsDischarged(this.getInPatientsDischarged());
    view.setInPatientsNotTreated(this.getInPatientsNotTreated());
    view.setOutPatientsTotal(this.getOutPatientsTotal());
    view.setOutPatientsFemale(this.getOutPatientsFemale());
    view.setOutPatientsMale(this.getOutPatientsMale());
    view.setPatientsNotTreated(this.getPatientsNotTreated());
    view.setOutPatientsNotTreated(this.getOutPatientsNotTreated());
    view.setStillBirths(this.getStillBirths());
    view.setDaysOutOfStock(this.getDaysOutOfStock());

    return view;
  }

  public static AggregatedCaseView searchByGeoEntityAndEpiDate(GeoEntity geoEntity,
      PeriodType periodType, Integer period, String year, AggregatedAgeGroup ageGroup)
  {
    EpiDate date = new EpiDate(periodType, period, year);
    AggregatedCase c = AggregatedCase.searchByGeoEntityAndDate(geoEntity, date.getStartDate(), date
        .getEndDate(), ageGroup);

    if (c != null)
    {
      AggregatedCaseView view = c.getView();
      view.setGeoEntity(geoEntity);
      view.setPeriod(period);
      view.setPeriodYear(year);
      view.setAgeGroup(ageGroup);
      view.setCaseId(c.getId());

      return view;
    }

    AggregatedCaseView view = ageGroup.getView();
    view.setGeoEntity(geoEntity);
    view.setPeriod(period);
    view.setPeriodYear(year);
    view.setAgeGroup(ageGroup);

    return view;
  }

  public static AggregatedCaseView getView(String id)
  {
    return AggregatedCase.get(id).getView();
  }

  public static AggregatedCaseView lockView(String id)
  {
    return AggregatedCase.lock(id).getView();
  }

  public static AggregatedCaseView unlockView(String id)
  {
    return AggregatedCase.unlock(id).getView();
  }

  private <T> List<T> convertToList(OIterator<T> it)
  {
    List<T> list = new LinkedList<T>();

    for (T value : it)
    {
      list.add(value);
    }

    return list;
  }

  @Override
  public CaseDiagnostic[] getDiagnosticMethods()
  {
    List<? extends CaseDiagnostic> list = convertToList(this.getAllDiagnosticMethodRel());

    return list.toArray(new CaseDiagnostic[list.size()]);
  }

  @Override
  public CaseReferral[] getReferrals()
  {
    List<? extends CaseReferral> list = convertToList(this.getAllReferralRel());

    return list.toArray(new CaseReferral[list.size()]);
  }

  @Override
  public CaseTreatmentMethod[] getTreatmentMethods()
  {
    List<? extends CaseTreatmentMethod> list = convertToList(this.getAllTreatmentMethodRel());

    return list.toArray(new CaseTreatmentMethod[list.size()]);
  }

  @Override
  public CaseTreatment[] getTreatments()
  {
    List<? extends CaseTreatment> list = convertToList(this.getAllTreatmentRel());

    return list.toArray(new CaseTreatment[list.size()]);
  }

  @Override
  public CaseTreatmentStock[] getTreatmentStocks()
  {
    List<? extends CaseTreatmentStock> list = convertToList(this.getAllTreatmentStockRel());

    return list.toArray(new CaseTreatmentStock[list.size()]);
  }

}
