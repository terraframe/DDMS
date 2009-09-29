package dss.vector.solutions;

import java.util.Date;

import com.terraframe.mojo.business.Entity;
import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.IPTRecipient;
import dss.vector.solutions.intervention.monitor.ITNRecipient;
import dss.vector.solutions.irs.SprayLeader;
import dss.vector.solutions.irs.SprayOperator;

public class PersonView extends PersonViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240852495794L;

  public PersonView()
  {
    super();
  }

  @Override
  public void validateOperatorId()
  {
    if (this.getOperatorId() != null && !this.getOperatorId().equals("") && this.getLeaderId() != null && !this.getLeaderId().equals(""))
    {
      if (this.getOperatorId().equals(this.getLeaderId()))
      {
        String msg = "Operator Id and Leader Id cannot be the same";
        throw new DelegateIdException(msg);
      }
    }
  }

  @Override
  @Transaction
  public void apply()
  {
    validateOperatorId();

    Person person = applyPerson();

    // Update the delegates
    MDSSUser user = person.getUserDelegate();

    if (this.getIsMDSSUser())
    {
      if (user == null)
      {
        user = new MDSSUser();
      }
      user.setPerson(person);
      user.setUsername(this.getUsername());
      user.setPassword(this.getPassword());
      user.apply();
    }
    else
    {
      if (user != null)
      {
        user.delete();
        user = null;
      }
    }

    Patient patient = person.getPatientDelegate();
    if (this.getIsPatient())
    {
      if (patient == null)
      {
        patient = new Patient();
      }
      patient.setPerson(person);
      patient.apply();
    }
    else
    {
      if (patient != null)
      {
        patient.delete();
        patient = null;
      }
    }

    ITNRecipient itnRecipient = person.getItnRecipientDelegate();
    if (this.getIsITNRecipient())
    {
      if (itnRecipient == null)
      {
        itnRecipient = new ITNRecipient();
      }

      itnRecipient.setPerson(person);
      itnRecipient.apply();
    }
    else
    {
      if (itnRecipient != null)
      {
        itnRecipient.delete();
        itnRecipient = null;
      }
    }

    IPTRecipient iptRecipient = person.getIptRecipientDelegate();
    if (this.getIsIPTRecipient())
    {
      if (iptRecipient == null)
      {
        iptRecipient = new IPTRecipient();
      }
      iptRecipient.setPerson(person);
      iptRecipient.apply();
    }
    else
    {
      if (iptRecipient != null)
      {
        iptRecipient.delete();
        iptRecipient = null;
      }
    }

    SprayOperator sprayOperator = person.getSprayOperatorDelegate();
    if (this.getIsSprayOperator())
    {
      if (sprayOperator == null)
      {
        sprayOperator = new SprayOperator();
      }
      sprayOperator.setPerson(person);
      sprayOperator.setOperatorId(this.getOperatorId());
      sprayOperator.apply();
    }
    else
    {
      if (sprayOperator != null)
      {
        sprayOperator.delete();
        sprayOperator = null;
      }
    }

    SprayLeader sprayLeader = person.getSprayLeaderDelegate();
    if (this.getIsSprayLeader())
    {
      if (sprayLeader == null)
      {
        sprayLeader = new SprayLeader();
      }

      sprayLeader.setPerson(person);
      sprayLeader.setLeaderId(this.getLeaderId());
      sprayLeader.apply();
    }
    else
    {
      if (sprayLeader != null)
      {
        sprayLeader.delete();
        sprayLeader = null;
      }
    }

    // Update the person delegates
    person = Person.lockPerson(person.getId());
    person.setUserDelegate(user);
    person.setItnRecipientDelegate(itnRecipient);
    person.setIptRecipientDelegate(iptRecipient);
    person.setSprayOperatorDelegate(sprayOperator);
    person.setSprayLeaderDelegate(sprayLeader);
    person.setPatientDelegate(patient);

    // Do not validate the person again because a second problem will be
    // generated for every validation problem that occurs. The person has
    // already been validated when it was applied for the first time in this
    // method.
    person.apply(false);

    this.setPersonId(person.getId());
  }
  
  @Override
  public void applyNonDelegates()
  {
    this.applyPerson();
  }
  
  private Person applyPerson()
  {
    // Update the person data
    Person person = Person.get(this.getPersonId());

    if (person == null)
    {
      person = new Person();
    }
    else
    {
      person.lock();
    }

    this.populateAttributeMapping(person);

    person.setFirstName(this.getFirstName());
    person.setLastName(this.getLastName());
    person.setDateOfBirth(this.getDateOfBirth());
    person.addSex(this.getSex().get(0));

    String geoId = this.getResidentialGeoId();

    if (geoId != null && !geoId.equals(""))
    {
      person.setResidentialGeoEntity(GeoEntity.searchByGeoId(geoId));
    }

    // Applying the person with validate it's attributes
    person.apply();
    return person;
  }
  
  private void populateAttributeMapping(Person person)
  {
    new AttributeNotificationMap(person, Person.DATEOFBIRTH, this, PersonView.DATEOFBIRTH);
    new AttributeNotificationMap(person, Person.FIRSTNAME, this, PersonView.FIRSTNAME);
    new AttributeNotificationMap(person, Person.LASTNAME, this, PersonView.LASTNAME);
    new AttributeNotificationMap(person, Person.SEX, this, PersonView.SEX);
    new AttributeNotificationMap(person, Person.RESIDENTIALGEOENTITY, this, PersonView.RESIDENTIALGEOID);
  }

  @Override
  public PersonQuery searchForDuplicates()
  {
    return getDuplicatesPage(Person.LASTNAME, true, 20, 0);
  }
  
  @Override
  public PersonQuery getDuplicatesPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    PersonQuery query = new PersonQuery(new QueryFactory());

    String firstName = this.getFirstName();
    if (firstName.length() > 0)
      query.WHERE(query.getFirstName().EQ(firstName));

    String lastName = this.getLastName();
    if (lastName.length() > 0)
      query.WHERE(query.getLastName().EQ(lastName));

    Date dob = this.getDateOfBirth();
    if (dob != null)
      query.WHERE(query.getDateOfBirth().EQ(dob));

    // We have a default value set, so there is always a value
    Sex sex = this.getSex().get(0);
    if (!sex.equals(Sex.UNKNOWN))
      query.WHERE(query.getSex().containsExactly(sex));
    
    Entity.getAllInstances(query, sortAttribute, isAscending, pageSize, pageNumber);
    
    return query;
  }
}
