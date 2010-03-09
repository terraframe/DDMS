package dss.vector.solutions;

import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.IPTRecipient;
import dss.vector.solutions.intervention.monitor.ITNRecipient;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.stock.StockStaff;

public class PersonView extends PersonViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240852495794L;

  public PersonView()
  {
    super();
  }
  
  public void populateView(Person concrete)
  {
    this.setPersonId(concrete.getId());
    this.setFirstName(concrete.getFirstName());
    this.setLastName(concrete.getLastName());
    this.setSex(concrete.getSex());

    this.setDateOfBirth(concrete.getDateOfBirth());

    if (concrete.getDateOfBirth() != null)
    {
      this.setAge(Math.max(0, new AgeConverter(concrete.getDateOfBirth()).getAge()));
    }

    if (concrete.getResidentialGeoEntity() != null)
    {
      this.setResidentialGeoId(concrete.getResidentialGeoEntity().getGeoId());
    }

    this.setResidentialInformation(concrete.getResidentialInformation());

    if (concrete.getWorkGeoEntity() != null)
    {
      this.setWorkGeoId(concrete.getWorkGeoEntity().getGeoId());
    }

    this.setWorkInformation(concrete.getWorkInformation());

    // Set the person's delegate attributes

    MDSSUser user = concrete.getUserDelegate();
    if (user == null)
    {
      this.setIsMDSSUser(false);
    }
    else
    {
      this.setIsMDSSUser(true);
      this.setUsername(user.getUsername());
    }

    Patient patient = concrete.getPatientDelegate();
    if (patient == null)
    {
      this.setIsPatient(false);
    }
    else
    {
      this.setIsPatient(true);
    }

    ITNRecipient itnRecipient = concrete.getItnRecipientDelegate();
    if (itnRecipient == null)
    {
      this.setIsITNRecipient(false);
    }
    else
    {
      this.setIsITNRecipient(true);
    }

    IPTRecipient iptRecipient = concrete.getIptRecipientDelegate();
    if (iptRecipient == null)
    {
      this.setIsIPTRecipient(false);
    }
    else
    {
      this.setIsIPTRecipient(true);
    }

    TeamMember member = concrete.getTeamMemberDelegate();
    if (member == null)
    {
      this.setIsSprayOperator(false);
      this.setIsSprayLeader(false);
    }
    else
    {
      this.setIsSprayOperator(member.getIsSprayOperator());
      this.setIsSprayLeader(member.getIsSprayLeader());
      this.setMemberId(member.getMemberId());
    }

    this.setIsStockStaff(concrete.getStockStaffDelegate() != null);
    this.setIsSupervisor(concrete.getSupervisorDelegate() != null);
  }

  @Override
  @Transaction
  public void apply()
  {
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

    ITNRecipient itnRecipient = applyITNRecipient(person);

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

    TeamMember member = person.getTeamMemberDelegate();
    
    if (this.getIsSprayOperator() || this.getIsSprayLeader())
    {
      if (member == null)
      {
        member = new TeamMember();
      }
      
      member.setPerson(person);
      member.setIsSprayLeader(this.getIsSprayLeader());
      member.setIsSprayOperator(this.getIsSprayOperator());
      member.setMemberId(this.getMemberId());
      member.apply();
    }
    else
    {
      if (member != null)
      {
        member.delete();
        member = null;
      }
    }

    StockStaff staff = person.getStockStaffDelegate();
    
    if (this.getIsStockStaff() != null && this.getIsStockStaff())
    {
      if (staff == null)
      {
        staff = new StockStaff();
      }
      staff.setPerson(person);
      staff.apply();
    }
    else
    {
      if (staff != null)
      {
        staff.delete();
        staff = null;
      }
    }
    
    Supervisor supervisor = person.getSupervisorDelegate();
    
    if (this.getIsSupervisor() != null && this.getIsSupervisor())
    {
      if (supervisor == null)
      {
        supervisor = new Supervisor();
      }
      supervisor.setPerson(person);
      supervisor.apply();
    }
    else
    {
      if (supervisor != null)
      {
        supervisor.delete();
        supervisor = null;
      }
    }

    // Update the person delegates
    person = Person.lockPerson(person.getId());
    person.setUserDelegate(user);
    person.setItnRecipientDelegate(itnRecipient);
    person.setIptRecipientDelegate(iptRecipient);
    person.setTeamMemberDelegate(member);
    person.setPatientDelegate(patient);
    person.setStockStaffDelegate(staff);
    person.setSupervisorDelegate(supervisor);

    // Do not validate the person again because a second problem will be
    // generated for every validation problem that occurs. The person has
    // already been validated when it was applied for the first time in this
    // method.
    person.apply(false);

    this.setPersonId(person.getId());
  }

  private ITNRecipient applyITNRecipient(Person person)
  {
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
    return itnRecipient;
  }
  
  @Override
  public void applyAsITNRecipient()
  {
    this.applyPerson();
    
    this.applyITNRecipient(Person.get(this.getPersonId()));
  }

  @Override
  public void applyNonDelegates()
  {
    Person concrete = this.applyPerson();
    
    this.populateView(concrete);
  }
  

  private Person applyPerson()
  {
    // Update the person data
    Person person = new Person();
    
    if(this.hasConcrete())
    {
      person = Person.lock(this.getPersonId());
    }

    this.populateAttributeMapping(person);

    this.populateConcrete(person);

    // Applying the person with validate it's attributes
    person.apply();
    
    return person;
  }

  private boolean hasConcrete()
  {
    return this.getPersonId() != null && !this.getPersonId().equals("");
  }

  private void populateConcrete(Person person)
  {
    person.setFirstName(this.getFirstName());
    person.setLastName(this.getLastName());
    person.setSex(this.getSex());

    // Set the persons age
    if (this.getDateOfBirth() != null)
    {
      person.setDateOfBirth(this.getDateOfBirth());
    }
    else if (this.getAge() != null)
    {
      // Must calculate the date of birth from the age
      person.setDateOfBirth(new AgeConverter(this.getAge()).getDateOfBirth());
    }

    String residentialId = this.getResidentialGeoId();

    if (residentialId != null && !residentialId.equals(""))
    {
      person.setResidentialGeoEntity(GeoEntity.searchByGeoId(residentialId));
    }
    else
    {
      person.setResidentialGeoEntity(null);
    }

    person.setResidentialInformation(this.getResidentialInformation());

    String workId = this.getWorkGeoId();

    if (workId != null && !workId.equals(""))
    {
      person.setWorkGeoEntity(GeoEntity.searchByGeoId(workId));
    }
    else
    {
      person.setWorkGeoEntity(null);
    }

    person.setResidentialInformation(this.getResidentialInformation());
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
  public PersonWithDelegatesViewQuery searchForDuplicates()
  {
    return getDuplicatesPage(Person.LASTNAME, true, 20, 0);
  }

  @Override
  public PersonWithDelegatesViewQuery getDuplicatesPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    PersonWithDelegatesViewQuery query = PersonWithDelegatesView.getPage(sortAttribute, isAscending, pageSize, pageNumber);

    String firstName = this.getFirstName();
    String lastName = this.getLastName();
    Date dob = this.getDateOfBirth();
    Term sex = this.getSex();
    
    if (firstName.length() > 0)
    {
      query.WHERE(query.getFirstName().EQ(firstName));
    }

    if (lastName.length() > 0)
    {
      query.WHERE(query.getLastName().EQ(lastName));
    }

    if (dob != null)
    {
      query.WHERE(query.getDateOfBirth().EQ(dob));
    }
    
    if(sex != null)
    {
      query.WHERE(query.getSex().EQ(sex));
    }

    return query;
  }
}
