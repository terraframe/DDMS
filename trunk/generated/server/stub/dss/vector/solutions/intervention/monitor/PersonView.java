package dss.vector.solutions.intervention.monitor;

import java.util.Calendar;

import dss.vector.solutions.intervention.BloodslideResponse;
import dss.vector.solutions.intervention.FeverResponse;
import dss.vector.solutions.intervention.HumanSex;
import dss.vector.solutions.intervention.RDTResponse;
import dss.vector.solutions.intervention.RDTResult;

public class PersonView extends PersonViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239989843132L;

  public PersonView()
  {
    super();
  }

  @Override
  public void apply()
  {
    this.populatePerson().apply();
  }

  @Override
  public void delete()
  {
    if(this.getConcreteId() != null)
    {
      Person.get(this.getConcreteId()).delete();
    }
  }

  private Person populatePerson()
  {
    Person person = Person.get(this.getConcreteId());

    if(person == null)
    {
      person = new Person();
    }

    if(this.getDob() != null)
    {
      person.setDob(this.getDob());
    }
    else if(this.getAge() != null)
    {
      //Must calculate the date of birth from the age
      Calendar c1 = Calendar.getInstance();
      c1.add(Calendar.YEAR, -this.getAge());

      person.setDob(c1.getTime());
    }

    person.setAnaemiaTreatment(this.getAnaemiaTreatment());
    person.setFeverTreatment(this.getFeverTreatment());
    person.setHaemoglobin(this.getHaemoglobin());
    person.setHaemoglobinMeasured(this.getHaemoglobinMeasured());
    person.setHousehold(this.getHousehold());
    person.setIron(this.getIron());
    person.setMalariaTreatment(this.getMalariaTreatment());
    person.setPersonId(this.getPersonId());
    person.setPregnant(this.getPregnant());
    person.setRdtTreatment(this.getRdtTreatment());
    person.setSleptUnderNet(this.getSleptUnderNet());

    for(BloodslideResponse r : this.getBloodslide()) person.addBloodslide(r);
    for(FeverResponse r : this.getFever()) person.addFever(r);
    for(FeverResponse r : this.getMalaria()) person.addMalaria(r);
    for(FeverResponse r : this.getPayment()) person.addPayment(r);
    for(RDTResponse r : this.getPerformedRDT()) person.addPerformedRDT(r);
    for(RDTResult r : this.getRDTResult()) person.addRDTResult(r);
    for(HumanSex r : this.getSex()) person.addSex(r);

    return person;
  }
}
