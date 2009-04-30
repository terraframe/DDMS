package dss.vector.solutions;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

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
    }
    else
    {
      if (user!=null)
        user.delete();
      person.setUserDelegate(null);
    }
    
    Patient patient = person.getPatientDelegate();
    if (this.getIsPatient())
    {
      if (patient==null)
        patient = new Patient();
      patient.setPerson(person);
      patient.apply();
      person.setPatientDelegate(patient);
    }
    else
    {
      if (patient!=null)
        patient.delete();
      person.setPatientDelegate(null);
    }
    
    ITNRecipient itnRecipient = person.getItnRecipientDelegate();
    if (this.getIsITNRecipient())
    {
      if (itnRecipient==null)
        itnRecipient = new ITNRecipient();
      itnRecipient.setPerson(person);
      itnRecipient.apply();
      person.setItnRecipientDelegate(itnRecipient);
    }
    else
    {
      if (itnRecipient!=null)
        itnRecipient.delete();
      person.setItnRecipientDelegate(null);
    }
    
    IPTRecipient iptRecipient = person.getIptRecipientDelegate();
    if (this.getIsIPTRecipient())
    {
      if (iptRecipient==null)
        iptRecipient = new IPTRecipient();
      iptRecipient.setPerson(person);
      iptRecipient.apply();
      person.setIptRecipientDelegate(iptRecipient);
    }
    else
    {
      if (iptRecipient!=null)
        iptRecipient.delete();
      person.setIptRecipientDelegate(null);
    }
    
    SprayOperator sprayOperator = person.getSprayOperatorDelegate();
    if (this.getIsSprayOperator())
    {
      if (sprayOperator==null)
        sprayOperator = new SprayOperator();
      sprayOperator.setPerson(person);
      sprayOperator.apply();
      person.setSprayOperatorDelegate(sprayOperator);
    }
    else
    {
      if (sprayOperator!=null)
        sprayOperator.delete();
      person.setSprayOperatorDelegate(null);
    }
    
    SprayLeader sprayLeader = person.getSprayLeaderDelegate();
    if (this.getIsSprayLeader())
    {
      if (sprayLeader==null)
        sprayLeader = new SprayLeader();
      sprayLeader.setPerson(person);
      sprayLeader.apply();
      person.setSprayLeaderDelegate(sprayLeader);
    }
    else
    {
      if (sprayLeader!=null)
        sprayLeader.delete();
      person.setSprayLeaderDelegate(null);
    }
    person.apply();
    super.apply();
  }
}
