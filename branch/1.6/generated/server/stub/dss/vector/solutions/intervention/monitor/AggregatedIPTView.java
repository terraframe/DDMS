package dss.vector.solutions.intervention.monitor;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.GridComparator;
import dss.vector.solutions.surveillance.PeriodType;

public class AggregatedIPTView extends AggregatedIPTViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737040579L;

  public AggregatedIPTView()
  {
    super();
  }

  public void populateView(AggregatedIPT concrete)
  {
    this.clearPeriodType();
    this.setConcreteId(concrete.getId());
    this.setGeoId(concrete.getGeoEntity().getGeoId());
    this.setNumberNatalCare(concrete.getNumberNatalCare());
    this.setNumberPregnant(concrete.getNumberPregnant());
    this.setNumberPregnantIron(concrete.getNumberPregnantIron());
    this.setNumberPregnantITN(concrete.getNumberPregnantITN());
    this.setTotalITN(concrete.getTotalITN());
    this.setStartDate(concrete.getStartDate());
    this.setEndDate(concrete.getEndDate());
  }

  private void populateConcrete(AggregatedIPT concrete)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(this.getGeoId());

    Date _startDate = this.getStartDate();
    Date _endDate = this.getEndDate();

    if (_startDate == null || _endDate == null)
    {
      PeriodType pt = this.getPeriodType().get(0);
      
      // IMPORTANT: WEEK is 0 based while MONTH and QUARTER are 1 based. Thus we
      // need to offset the 'period' for WEEK
      Integer _period = ( pt.equals(PeriodType.WEEK) ? this.getPeriod() - 1 : this.getPeriod() );

      EpiDate date = EpiDate.getInstanceByPeriod(pt, _period, this.getPeriodYear());
      
      _startDate = date.getStartDate();
      _endDate = date.getEndDate();
    }

    concrete.setGeoEntity(geoEntity);
    concrete.setNumberNatalCare(this.getNumberNatalCare());
    concrete.setNumberPregnant(this.getNumberPregnant());
    concrete.setNumberPregnantIron(this.getNumberPregnantIron());
    concrete.setNumberPregnantITN(this.getNumberPregnantITN());
    concrete.setTotalITN(this.getTotalITN());
    concrete.setStartDate(_startDate);
    concrete.setEndDate(_endDate);
  }

  @Override
  public void apply()
  {
    AggregatedIPT concrete = new AggregatedIPT();

    if (this.hasConcrete())
    {
      concrete = AggregatedIPT.get(this.getConcreteId());
    }

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      AggregatedIPT.get(this.getConcreteId()).delete();
    }
  }

  public boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public void applyAll(IPTPatients[] patients, IPTANCVisit[] visits, IPTDose[] doses, IPTTreatment[] treatments)
  {
    this.apply();

    for (IPTPatients patient : patients)
    {
      patient.overwriteParentId(this.getConcreteId());
      patient.apply();
    }

    for (IPTANCVisit visit : visits)
    {
      visit.overwriteParentId(this.getConcreteId());
      visit.apply();
    }

    for (IPTDose dose : doses)
    {
      dose.overwriteParentId(this.getConcreteId());
      dose.apply();
    }

    for (IPTTreatment treatment : treatments)
    {
      treatment.overwriteParentId(this.getConcreteId());
      treatment.apply();
    }
  }

  @Override
  public IPTANCVisit[] getIPTANCVisits()
  {
    Set<IPTANCVisit> set = new TreeSet<IPTANCVisit>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedIPTView.getDisplayVisitsMd()))
    {
      set.add(new IPTANCVisit(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedIPT concrete = AggregatedIPT.get(this.getConcreteId());

      for (IPTANCVisit d : concrete.getAllANCVisitsRel())
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

    return set.toArray(new IPTANCVisit[set.size()]);
  }

  @Override
  public IPTDose[] getIPTDoses()
  {
    Set<IPTDose> set = new TreeSet<IPTDose>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedIPTView.getDisplayDoseMd()))
    {
      set.add(new IPTDose(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedIPT concrete = AggregatedIPT.get(this.getConcreteId());

      for (IPTDose d : concrete.getAllDosesRel())
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

    return set.toArray(new IPTDose[set.size()]);
  }

  @Override
  public IPTPatients[] getIPTPatients()
  {
    Set<IPTPatients> set = new TreeSet<IPTPatients>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedIPTView.getDisplayPatientsMd()))
    {
      set.add(new IPTPatients(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedIPT concrete = AggregatedIPT.get(this.getConcreteId());

      for (IPTPatients d : concrete.getAllPatientsRel())
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

    return set.toArray(new IPTPatients[set.size()]);
  }

  @Override
  public IPTTreatment[] getIPTTreatments()
  {
    Set<IPTTreatment> set = new TreeSet<IPTTreatment>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedIPTView.getDisplayTreatmentsMd()))
    {
      set.add(new IPTTreatment(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedIPT concrete = AggregatedIPT.get(this.getConcreteId());

      for (IPTTreatment d : concrete.getAllTreatmentsRel())
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

    return set.toArray(new IPTTreatment[set.size()]);
  }

  @Override
  public AggregatedIPTView searchByView()
  {
    GeoEntity entity = GeoEntity.searchByGeoId(this.getGeoId());

    if (this.getSearchType())
    {
      return AggregatedIPT.searchByDate(entity, this.getStartDate(), this.getEndDate());
    }
    else
    {
      PeriodType _periodType = this.getPeriodType().get(0);

      return AggregatedIPT.searchByGeoEntityAndEpiDate(entity, _periodType, this.getPeriod(), this.getPeriodYear());
    }
  }
}
