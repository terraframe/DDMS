package dss.vector.solutions.intervention.monitor;

import java.util.Calendar;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.intervention.BloodslideResponse;
import dss.vector.solutions.intervention.FeverResponse;
import dss.vector.solutions.intervention.HumanSex;
import dss.vector.solutions.intervention.RDTResponse;
import dss.vector.solutions.intervention.RDTResult;

public class Person extends PersonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641280013L;

  public Person()
  {
    super();
  }
    
  @Override
  protected String buildKey()
  {
    if(this.getPersonId() != null)
    {
      return this.getPersonId();
    }
    
    return this.getId();
  }

  @Override
  public void validatePregnant()
  {
    if(this.getPregnant() != null && this.getPregnant() == true)
    {
      if(this.getSex().size() > 0 && !this.getSex().get(0).equals(HumanSex.FEMALE))
      {
        String msg = "It is impossible for a human male to be pregnant";

        PregnantProblem p = new PregnantProblem(msg);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void apply()
  {
    validatePregnant();

    boolean first = this.isNew() && !this.isAppliedToDB();

    super.apply();

    if(first)
    {
      HouseholdPerson householdPerson = new HouseholdPerson(this.getHousehold(), this);
      householdPerson.apply();
    }
  }

  @Override
  @Transaction
  public PersonView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public PersonView unlockView()
  {
    this.unlock();

    return this.getView();
  }
  
  public PersonView getView()
  {
    PersonView view = new PersonView();
    
    this.populateView(view);
    
    return view;
  }

  public void populateView(PersonView view)
  {
    view.setConcreteId(this.getId());

    if(this.getDob() != null)
    {
      Calendar today = Calendar.getInstance();

      Calendar dob = Calendar.getInstance();
      dob.setTime(this.getDob());

      int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
      if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR))
      {
        age = age - 1;
      }
      
      view.setAge(age);
    }

    view.setDob(this.getDob());
    view.setAnaemiaTreatment(this.getAnaemiaTreatment());
    view.setFeverTreatment(this.getFeverTreatment());
    view.setHaemoglobin(this.getHaemoglobin());
    view.setHaemoglobinMeasured(this.getHaemoglobinMeasured());
    view.setHousehold(this.getHousehold());
    view.setIron(this.getIron());
    view.setMalariaTreatment(this.getMalariaTreatment());
    view.setPersonId(this.getPersonId());
    view.setPregnant(this.getPregnant());
    view.setRdtTreatment(this.getRdtTreatment());
    view.setSleptUnderNet(this.getSleptUnderNet());
    
    view.clearBloodslide();
    view.clearFever();
    view.clearMalaria();
    view.clearPayment();
    view.clearPerformedRDT();
    view.clearRDTResult();
    view.clearSex();

    for(BloodslideResponse r : this.getBloodslide()) view.addBloodslide(r);
    for(FeverResponse r : this.getFever()) view.addFever(r);
    for(FeverResponse r : this.getMalaria()) view.addMalaria(r);
    for(FeverResponse r : this.getPayment()) view.addPayment(r);
    for(RDTResponse r : this.getPerformedRDT()) view.addPerformedRDT(r);
    for(RDTResult r : this.getRDTResult()) view.addRDTResult(r);
    for(HumanSex r : this.getSex()) view.addSex(r);
  }

  public static PersonView getView(String id)
  {
    return Person.get(id).getView();
  }
}
