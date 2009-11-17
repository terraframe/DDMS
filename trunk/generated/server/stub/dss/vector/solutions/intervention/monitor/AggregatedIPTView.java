package dss.vector.solutions.intervention.monitor;

import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;

public class AggregatedIPTView extends AggregatedIPTViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737040579L;
  
  public AggregatedIPTView()
  {
    super();
  }

  public void populateView(AggregatedIPT concrete)
  {
    EpiDate date = EpiDate.getInstanceByDate(concrete.getStartDate(), concrete.getEndDate());
    
    this.clearPeriodType();    
    this.setConcreteId(concrete.getId());
    this.setGeoId(concrete.getGeoEntity().getGeoId());
    this.setNumberNatalCare(concrete.getNumberNatalCare());
    this.setNumberPregnant(concrete.getNumberPregnant());
    this.setNumberPregnantIron(concrete.getNumberPregnantIron());
    this.setNumberPregnantITN(concrete.getNumberPregnantITN());
    this.setTotalITN(concrete.getTotalITN());
    this.setPeriod(date.getPeriod());
    this.setPeriodYear(date.getYear());
    this.addPeriodType(date.getEpiPeriodType());
  }

  private void populateConcrete(AggregatedIPT concrete)
  {
    EpiDate date = EpiDate.getInstanceByPeriod(this.getPeriodType().get(0), this.getPeriod(), this.getPeriodYear());
    GeoEntity geoEntity = GeoEntity.searchByGeoId(this.getGeoId());
    
    concrete.setGeoEntity(geoEntity);
    concrete.setNumberNatalCare(this.getNumberNatalCare());
    concrete.setNumberPregnant(this.getNumberPregnant());
    concrete.setNumberPregnantIron(this.getNumberPregnantIron());
    concrete.setNumberPregnantITN(this.getNumberPregnantITN());
    concrete.setTotalITN(this.getTotalITN());
    concrete.setStartDate(date.getStartDate());
    concrete.setEndDate(date.getEndDate());
  }
  
  @Override
  public void apply()
  {
    AggregatedIPT concrete = new AggregatedIPT();
    
    if(this.hasConcrete())
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
    if(this.hasConcrete())
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
    boolean newCase = !this.hasConcrete();

    this.apply();

    if (newCase)
    {
      this.updateParentIds(patients, visits, doses, treatments);
    }

    for (IPTPatients patient : patients)
    {
      patient.apply();
    }

    for (IPTANCVisit visit : visits)
    {
      visit.apply();
    }

    for (IPTDose dose : doses)
    {
      dose.apply();
    }

    for (IPTTreatment treatment : treatments)
    {
      treatment.apply();
    }
  }

  @Transaction
  private void updateParentIds(IPTPatients[] patients, IPTANCVisit[] visits, IPTDose[] doses, IPTTreatment[] treatments)
  {
    for (int i = 0; i < patients.length; i++)
    {
      patients[i].overwriteParentId(this.getConcreteId());
    }

    for (int i = 0; i < visits.length; i++)
    {
      visits[i].overwriteParentId(this.getConcreteId());
    }

    for (int i = 0; i < doses.length; i++)
    {
      doses[i].overwriteParentId(this.getConcreteId());
    }

    for (int i = 0; i < treatments.length; i++)
    {
      treatments[i].overwriteParentId(this.getConcreteId());
    }
  }
  
  @Override
  public IPTANCVisit[] getIPTANCVisits()
  {
    Set<IPTANCVisit> set = new TreeSet<IPTANCVisit>(new GridComparator());

    for (Term d : Term.getRootChildren(AggregatedIPTView.getDisplayVisitsMd()))
    {
      set.add(new IPTANCVisit(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedIPT concrete = AggregatedIPT.get(this.getConcreteId());

      for (IPTANCVisit d : concrete.getAllANCVisitsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set.  Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if(set.contains(d))
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

    for (Term d : Term.getRootChildren(AggregatedIPTView.getDisplayDoseMd()))
    {
      set.add(new IPTDose(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedIPT concrete = AggregatedIPT.get(this.getConcreteId());

      for (IPTDose d : concrete.getAllDosesRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set.  Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if(set.contains(d))
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

    for (Term d : Term.getRootChildren(AggregatedIPTView.getDisplayPatientsMd()))
    {
      set.add(new IPTPatients(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedIPT concrete = AggregatedIPT.get(this.getConcreteId());

      for (IPTPatients d : concrete.getAllPatientsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set.  Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if(set.contains(d))
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

    for (Term d : Term.getRootChildren(AggregatedIPTView.getDisplayTreatmentsMd()))
    {
      set.add(new IPTTreatment(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      AggregatedIPT concrete = AggregatedIPT.get(this.getConcreteId());

      for (IPTTreatment d : concrete.getAllTreatmentsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set.  Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if(set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new IPTTreatment[set.size()]);
  }
}
