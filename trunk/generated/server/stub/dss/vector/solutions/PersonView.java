/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.IPTRecipient;
import dss.vector.solutions.intervention.monitor.ITNRecipient;
import dss.vector.solutions.irs.Supervisor;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.stock.StockStaff;

public class PersonView extends PersonViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240852495794L;

  public PersonView()
  {
    super();
  }

  public void populateView(Person concrete)
  {
    this.setPersonId(concrete.getId());
    this.setIdentifier(concrete.getIdentifier());
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

    if (concrete.getBirthEntity() != null)
    {
      this.setBirthEntity(concrete.getBirthEntity().getGeoId());
    }

    this.setBirthLocation(concrete.getBirthLocation());

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

      UserSettings settings = UserSettings.getForUser(user);
      if (settings != null)
      {
        this.setDisease(settings.getDisease());
      }
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
    
    Supervisor sup = concrete.getSupervisorDelegate();
    if (sup == null)
    {
      this.setIsSupervisor(false);
    }
    else
    {
      this.setIsSupervisor(true);
      this.setCode(sup.getCode());
    }

    this.setIsStockStaff(concrete.getStockStaffDelegate() != null);
    this.setIsSupervisor(concrete.getSupervisorDelegate() != null);
    this.setIsPhysician(concrete.getPhysicianDelegate() != null);
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

      UserSettings settings = UserSettings.createIfNotExists(user);
      settings.lock();
      settings.setDisease(this.getDisease());
      settings.apply();
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
      supervisor.setCode(this.getCode());
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

    Physician physician = person.getPhysicianDelegate();

    if (this.getIsPhysician() != null && this.getIsPhysician())
    {
      if (physician == null)
      {
        physician = new Physician();
      }

      physician.setPerson(person);
      physician.apply();
    }
    else
    {
      if (physician != null)
      {
        physician.delete();
        physician = null;
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
    person.setPhysicianDelegate(physician);

    // Do not validate the person again because a second problem will be
    // generated for every validation problem that occurs. The person has
    // already been validated when it was applied for the first time in this
    // method.
    person.apply(false);

    if (!this.getPersonId().equals(person.getId()))
    {
      this.setPersonId(person.getId());
    }
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

  private Physician applyPhysician(Person person)
  {
    Physician physician = person.getPhysicianDelegate();
    if (this.getIsPhysician())
    {
      if (physician == null)
      {
        physician = new Physician();
      }

      physician.setPerson(person);
      physician.apply();
    }
    else
    {
      if (physician != null)
      {
        physician.delete();
        physician = null;
      }
    }
    return physician;
  }

  @Override
  @Transaction
  public void applyAsITNRecipient()
  {
    this.applyPerson();

    this.applyITNRecipient(Person.get(this.getPersonId()));
  }

  @Override
  @Transaction
  public PersonWithDelegatesView applyAsPhysician()
  {
    this.setIsPhysician(true);

    Person person = this.applyPerson();

    Physician physician = this.applyPhysician(person);

    person.setPhysicianDelegate(physician);
    person.apply();

    return PersonWithDelegatesView.getView(person.getId());
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

    if (this.hasConcrete())
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
    person.setIdentifier(this.getIdentifier());
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

    person.setWorkInformation(this.getWorkInformation());

    String birthGeoId = this.getBirthEntity();

    if (birthGeoId != null && !birthGeoId.equals(""))
    {
      person.setBirthEntity(GeoEntity.searchByGeoId(birthGeoId));
    }
    else
    {
      person.setBirthEntity(null);
    }

    person.setBirthLocation(this.getBirthLocation());
  }

  private void populateAttributeMapping(Person person)
  {
    new AttributeNotificationMap(person, Person.IDENTIFIER, this, PersonView.IDENTIFIER);
    new AttributeNotificationMap(person, Person.DATEOFBIRTH, this, PersonView.DATEOFBIRTH);
    new AttributeNotificationMap(person, Person.FIRSTNAME, this, PersonView.FIRSTNAME);
    new AttributeNotificationMap(person, Person.LASTNAME, this, PersonView.LASTNAME);
    new AttributeNotificationMap(person, Person.SEX, this, PersonView.SEX);
    new AttributeNotificationMap(person, Person.RESIDENTIALGEOENTITY, this, PersonView.RESIDENTIALGEOID);
    new AttributeNotificationMap(person, Person.RESIDENTIALINFORMATION, this, PersonView.RESIDENTIALINFORMATION);
    new AttributeNotificationMap(person, Person.WORKGEOENTITY, this, PersonView.WORKGEOID);
    new AttributeNotificationMap(person, Person.WORKINFORMATION, this, PersonView.WORKINFORMATION);
    new AttributeNotificationMap(person, Person.BIRTHENTITY, this, PersonView.BIRTHENTITY);
    new AttributeNotificationMap(person, Person.BIRTHLOCATION, this, PersonView.BIRTHLOCATION);
  }

  @Override
  public PersonWithDelegatesViewQuery searchForDuplicates()
  {
    return getDuplicatesPage(Person.LASTNAME, true, 20, 0);
  }

  @Override
  public PersonWithDelegatesViewQuery getDuplicatesPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    PersonWithDelegatesViewQuery query = PersonWithDelegatesView.getPage(new PersonWithDelegatesViewQuery(new QueryFactory(), this), sortAttribute, isAscending, pageSize, pageNumber);

    return query;
  }

  public static PersonWithDelegatesView getViewFromIdentifier(String identifier)
  {
    PersonWithDelegatesViewQuery query = new PersonWithDelegatesViewQuery(new QueryFactory());
    query.WHERE(query.getIdentifier().EQ(identifier));

    OIterator<? extends PersonWithDelegatesView> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
    }
    finally
    {
      iterator.close();
    }

    return null;
  }

}
