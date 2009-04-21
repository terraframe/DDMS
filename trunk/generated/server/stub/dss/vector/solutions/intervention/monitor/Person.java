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

    return this.populateView();
  }

  @Override
  @Transaction
  public PersonView unlockView()
  {
    this.unlock();

    return this.populateView();
  }

  private PersonView populateView()
  {
    PersonView view = new PersonView();

    view.setConcreteId(this.getId());

    if(this.getDob() != null)
    {
      Calendar c1 = Calendar.getInstance();
      Calendar c2 = Calendar.getInstance();
      c2.setTime(this.getDob());

      view.setAge(c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR));
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

    for(BloodslideResponse r : this.getBloodslide()) view.addBloodslide(r);
    for(FeverResponse r : this.getFever()) view.addFever(r);
    for(FeverResponse r : this.getMalaria()) view.addMalaria(r);
    for(FeverResponse r : this.getPayment()) view.addPayment(r);
    for(RDTResponse r : this.getPerformedRDT()) view.addPerformedRDT(r);
    for(RDTResult r : this.getRDTResult()) view.addRDTResult(r);
    for(HumanSex r : this.getSex()) view.addSex(r);

    return view;
  }

  public static PersonView getView(String id)
  {
    return Person.get(id).populateView();
  }
}
