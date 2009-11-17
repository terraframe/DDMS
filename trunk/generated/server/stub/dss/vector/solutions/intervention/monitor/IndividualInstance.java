package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;
import dss.vector.solutions.surveillance.IndividualCaseSymptom;


public class IndividualInstance extends IndividualInstanceBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254360074462L;
  
  public IndividualInstance()
  {
    super();
  }
  
  @Transaction
  public void applyAll(IndividualCaseSymptom[] symptom)
  {
    boolean newCase = this.isNew();

    this.apply();

    if (newCase)
    {
      for (IndividualCaseSymptom s : symptom)
      {
        s.overwriteParentId(this.getId());
      }
    }

    for (IndividualCaseSymptom s : symptom)
    {
      s.apply();
    }
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction
    for (IndividualCaseSymptom symptom : this.getAllSymptomsRel())
    {
      symptom.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    // Unlock the grid relationship also, this must be in the same transaction
    for (IndividualCaseSymptom symptom : this.getAllSymptomsRel())
    {
      symptom.unlock();
    }

    super.unlock();
  }
  
  /**
   * Gets the symptom relationships associated with this case
   * 
   * @return
   */
  @Override
  public IndividualCaseSymptom[] getSymptoms()
  {
    Set<IndividualCaseSymptom> set = new TreeSet<IndividualCaseSymptom>(new GridComparator());

    for (Term d : Term.getRootChildren(getSymptomMd()))
    {
      set.add(new IndividualCaseSymptom(this.getId(), d.getId()));
    }
    
    for (IndividualCaseSymptom d : this.getAllSymptomsRel())
    {
      // We will only want grid options methods which are active. All active
      // methods are already in the set.  Thus, if the set already contains an
      // entry for the Grid Option replace the default relationship with the
      // actual relationship
      if(set.contains(d))
      {
        set.remove(d);
        set.add(d);
      }
    }

    return set.toArray(new IndividualCaseSymptom[set.size()]);
  }
  
  @Override
  protected String buildKey()
  {
    Date admissionDate = this.getAdmissionDate();
    Date facilityVisit = this.getFacilityVisit();
    Boolean clinicalDiagnosis = this.getClinicalDiagnosis();
    Term labTest = this.getLabTest();
    Term treatment = this.getTreatment();
    if(admissionDate != null && facilityVisit != null && clinicalDiagnosis != null && labTest != null && treatment != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      
      return format.format(admissionDate) + "." + format.format(facilityVisit) + "." + clinicalDiagnosis + "." + labTest.getName() + "." + treatment.getName();
    }
    return this.getId();
  }
}
