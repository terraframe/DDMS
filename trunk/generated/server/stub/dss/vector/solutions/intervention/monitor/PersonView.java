package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.AgeConverter;
import dss.vector.solutions.intervention.BloodslideResponse;
import dss.vector.solutions.intervention.FeverResponse;
import dss.vector.solutions.intervention.HumanSex;
import dss.vector.solutions.intervention.RDTResponse;
import dss.vector.solutions.intervention.RDTResult;

public class PersonView extends PersonViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239989843132L;

  public PersonView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    Person person = new Person();

    if (this.hasConcrete())
    {
      person = Person.lock(this.getConcreteId());
    }
    
    this.populateMapping(person);
    
    this.populateConcrete(person);

    person.apply();

    person.populateView(this);
  }

  private void populateMapping(Person person)
  {
    new AttributeNotificationMap(person, Person.ANAEMIATREATMENT, this, PersonView.ANAEMIATREATMENT);
    new AttributeNotificationMap(person, Person.BLOODSLIDE, this, PersonView.BLOODSLIDE);
    new AttributeNotificationMap(person, Person.DOB, this, PersonView.DOB);
    new AttributeNotificationMap(person, Person.FEVER, this, PersonView.FEVER);
    new AttributeNotificationMap(person, Person.FEVERTREATMENT, this, PersonView.FEVERTREATMENT);
    new AttributeNotificationMap(person, Person.HAEMOGLOBIN, this, PersonView.HAEMOGLOBIN);
    new AttributeNotificationMap(person, Person.HAEMOGLOBINMEASURED, this, PersonView.HAEMOGLOBINMEASURED);
    new AttributeNotificationMap(person, Person.HOUSEHOLD, this, PersonView.HOUSEHOLD);
    new AttributeNotificationMap(person, Person.IRON, this, PersonView.IRON);
    new AttributeNotificationMap(person, Person.MALARIA, this, PersonView.MALARIA);
    new AttributeNotificationMap(person, Person.MALARIATREATMENT, this, PersonView.MALARIATREATMENT);
    new AttributeNotificationMap(person, Person.PAYMENT, this, PersonView.PAYMENT);
    new AttributeNotificationMap(person, Person.PERFORMEDRDT, this, PersonView.PERFORMEDRDT);
    new AttributeNotificationMap(person, Person.PERSONID, this, PersonView.PERSONID);
    new AttributeNotificationMap(person, Person.PREGNANT, this, PersonView.PREGNANT);
    new AttributeNotificationMap(person, Person.RDTRESULT, this, PersonView.RDTRESULT);
    new AttributeNotificationMap(person, Person.RDTTREATMENT, this, PersonView.RDTTREATMENT);
    new AttributeNotificationMap(person, Person.SEX, this, PersonView.SEX);
    new AttributeNotificationMap(person, Person.SLEPTUNDERNET, this, PersonView.SLEPTUNDERNET);
  }

  @Override
  public void delete()
  {
    if (this.hasConcrete())
    {
      Person.get(this.getConcreteId()).delete();
    }
  }

  private void populateConcrete(Person person)
  {
    if (this.getDob() != null)
    {
      person.setDob(this.getDob());
    }
    else if (this.getAge() != null)
    {      
      // Must calculate the date of birth from the age
      person.setDob(new AgeConverter(this.getAge()).getDateOfBirth());
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

    for (BloodslideResponse r : this.getBloodslide())
      person.addBloodslide(r);
    for (FeverResponse r : this.getFever())
      person.addFever(r);
    for (FeverResponse r : this.getMalaria())
      person.addMalaria(r);
    for (FeverResponse r : this.getPayment())
      person.addPayment(r);
    for (RDTResponse r : this.getPerformedRDT())
      person.addPerformedRDT(r);
    for (RDTResult r : this.getRDTResult())
      person.addRDTResult(r);
    for (HumanSex r : this.getSex())
      person.addSex(r);
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
}
