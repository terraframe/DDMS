package dss.vector.solutions;

import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.Sex;
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
  @Transaction
  public void apply()
  {
    Person person = Person.get(this.getPersonId());
    if (person==null)
      person = new Person();
    else
      person.lock();
    
    person.setFirstName(this.getFirstName());
    person.setLastName(this.getLastName());
    person.setDateOfBirth(this.getDateOfBirth());
    person.addSex(this.getSex().get(0));
    person.apply();
    person.lock();
    this.setPersonId(person.getId());
    
    MDSSUser user = person.getUserDelegate();
    if (this.getIsMDSSUser())
    {
      if (user==null)
        user = new MDSSUser();
      user.setPerson(person);
      user.setUsername(this.getUsername());
      user.setPassword(this.getPassword());
      user.apply();
      person.setUserDelegate(user);
      person.apply();
    }
    else
    {
      if (user!=null)
        user.delete();
      person.lock();
    }
    
    Patient patient = person.getPatientDelegate();
    if (this.getIsPatient())
    {
      if (patient==null)
        patient = new Patient();
      patient.setPerson(person);
      patient.apply();
      person.setPatientDelegate(patient);
      person.apply();
    }
    else
    {
      if (patient!=null)
        patient.delete();
      person.lock();
    }
    
    ITNRecipient itnRecipient = person.getItnRecipientDelegate();
    if (this.getIsITNRecipient())
    {
      if (itnRecipient==null)
        itnRecipient = new ITNRecipient();
      itnRecipient.setPerson(person);
      itnRecipient.apply();
      person.setItnRecipientDelegate(itnRecipient);
      person.apply();
    }
    else
    {
      if (itnRecipient!=null)
        itnRecipient.delete();
      person.lock();
    }
    
    IPTRecipient iptRecipient = person.getIptRecipientDelegate();
    if (this.getIsIPTRecipient())
    {
      if (iptRecipient==null)
        iptRecipient = new IPTRecipient();
      iptRecipient.setPerson(person);
      iptRecipient.apply();
      person.setIptRecipientDelegate(iptRecipient);
      person.apply();
    }
    else
    {
      if (iptRecipient!=null)
        iptRecipient.delete();
      person.lock();
    }
    
    SprayOperator sprayOperator = person.getSprayOperatorDelegate();
    if (this.getIsSprayOperator())
    {
      if (sprayOperator==null)
        sprayOperator = new SprayOperator();
      sprayOperator.setPerson(person);
      sprayOperator.setOperatorId(this.getOperatorId());
      sprayOperator.apply();
      person.setSprayOperatorDelegate(sprayOperator);
      person.apply();
    }
    else
    {
      if (sprayOperator!=null)
        sprayOperator.delete();
      person.lock();
    }
    
    SprayLeader sprayLeader = person.getSprayLeaderDelegate();
    if (this.getIsSprayLeader())
    {
      if (sprayLeader==null)
        sprayLeader = new SprayLeader();
      sprayLeader.setPerson(person);
      sprayLeader.apply();
      person.setSprayLeaderDelegate(sprayLeader);
      person.apply();
    }
    else
    {
      if (sprayLeader!=null)
        sprayLeader.delete();
      person.lock();
    }
    super.apply();
  }
  
  @Override
  public PersonQuery searchForDuplicates()
  {
    PersonQuery query = new PersonQuery(new QueryFactory());
    
    String firstName = this.getFirstName();
    if (firstName.length()>0)
      query.WHERE(query.getFirstName().EQ(firstName));
    
    String lastName = this.getLastName();
    if (lastName.length()>0)
      query.WHERE(query.getLastName().EQ(lastName));
    
    Date dob = this.getDateOfBirth();
    if (dob!=null)
      query.WHERE(query.getDateOfBirth().EQ(dob));
    
    // We have a default value set, so there is always a value
    Sex sex = this.getSex().get(0);
    if (!sex.equals(Sex.UNKNOWN))
      query.WHERE(query.getSex().containsExactly(sex));
    
//    SprayOperator sprayDelegate = this.getSprayOperatorDelegate();
//    if (sprayDelegate==null)
//      query.WHERE(query.getSprayOperatorDelegate().EQ(""));
//    else
//      query.WHERE(query.getSprayOperatorDelegate().NE(""));
    
    return query;
  }
}
