package dss.vector.solutions.intervention.monitor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.AgeConverter;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermComparator;

public class PersonView extends PersonViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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

  @Override
  @Transaction
  public void applyAll(Term[] results)
  {
    this.apply();

    Person person = Person.get(this.getConcreteId());

    List<Term> resultList = Arrays.asList(results);

    this.clearResults(resultList, person);

    this.setResults(person, resultList);
  }

  private void setResults(Person person, List<Term> resultList)
  {
    Set<Term> set = new TreeSet<Term>(new TermComparator());
    set.addAll(resultList);
    
    List<? extends Term> existing = person.getAllRDTResults().getAll();

    // Get all of the new results which this Person does not already have
    set.removeAll(existing);
    
    for (Term result : set)
    {
      PersonRDTResult relationship = person.addRDTResults(result);

      relationship.apply();
    }
  }

  private void clearResults(List<Term> resultList, Person person)
  {
    // First delete all of the exiting relationships where the Term is not in
    // the result list
    List<? extends PersonRDTResult> relationships = person.getAllRDTResultsRel().getAll();

    for (PersonRDTResult relationship : relationships)
    {
      if (!resultList.contains(relationship.getChild()))
      {
        relationship.delete();
      }
    }
  }
  
  @Override
  public Term[] getRDTResults()
  {
    if(this.hasConcrete())
    {
      Person person = Person.get(this.getConcreteId());
      
      List<? extends Term> results = person.getAllRDTResults().getAll();
      
      return results.toArray(new Term[results.size()]);
    }
    
    return new Term[0];
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
    new AttributeNotificationMap(person, Person.RDTTREATMENT, this, PersonView.RDTTREATMENT);
    new AttributeNotificationMap(person, Person.SEX, this, PersonView.SEX);
    new AttributeNotificationMap(person, Person.SLEPTUNDERNET, this, PersonView.SLEPTUNDERNET);
  }

  @Override
  public void deleteConcrete()
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
    person.setBloodslide(this.getBloodslide());
    person.setFever(this.getFever());
    person.setMalaria(this.getMalaria());
    person.setPayment(this.getPayment());
    person.setPerformedRDT(this.getPerformedRDT());
    person.setSex(this.getSex());
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
}
