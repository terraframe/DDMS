package dss.vector.solutions;

import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.Sex;
import dss.vector.solutions.intervention.monitor.IPTRecipient;
import dss.vector.solutions.intervention.monitor.ITNRecipient;
import dss.vector.solutions.irs.SprayLeader;
import dss.vector.solutions.irs.SprayOperator;

public class Person extends PersonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240792902476L;
  
  public Person()
  {
    super();
  }
  
  @Override
  @Transaction
  public void delete()
  {
    MDSSUser user = this.getUserDelegate();
    if (user!=null)
    {
      this.setUserDelegate(null);
      user.delete();
    }
    
    Patient patient = this.getPatientDelegate();
    if (patient!=null)
    {
      this.setPatientDelegate(null);
      patient.delete();
    }
    
    ITNRecipient itnRecipient = this.getItnRecipientDelegate();
    if (itnRecipient!=null)
    {
      this.setItnRecipientDelegate(null);
      itnRecipient.delete();
    }
    
    IPTRecipient iptRecipient = this.getIptRecipientDelegate();
    if (iptRecipient!=null)
    {
      this.setIptRecipientDelegate(null);
      iptRecipient.delete();
    }
    
    SprayOperator sprayOperator = this.getSprayOperatorDelegate();
    if (sprayOperator!=null)
    {
      this.setSprayOperatorDelegate(null);
      sprayOperator.delete();
    }
    
    SprayLeader sprayLeader = this.getSprayLeaderDelegate();
    if (sprayLeader!=null)
    {
      this.setSprayLeaderDelegate(null);
      sprayLeader.delete();
    }
    
    super.delete();
  }
  
  @Override
  public PersonView lockView()
  {
    PersonView view = this.getView();
    this.lock();
    if (view.getIsMDSSUser())
      this.getUserDelegate().lock();
    if (view.getIsPatient())
      this.getPatientDelegate().lock();
    if (view.getIsITNRecipient())
      this.getItnRecipientDelegate().lock();
    if (view.getIsIPTRecipient())
      this.getIptRecipientDelegate().lock();
    if (view.getIsSprayOperator())
      this.getSprayOperatorDelegate().lock();
    if (view.getIsSprayLeader())
      this.getSprayLeaderDelegate().lock();
    return view;
  }
  
  @Override
  public void unlockView()
  {
    this.unlock();
    
    MDSSUser user = this.getUserDelegate();
    if (user != null)
      user.unlock();
    
    Patient patient = this.getPatientDelegate();
    if (patient != null)
      patient.unlock();
    
    ITNRecipient itnRecipient = this.getItnRecipientDelegate();
    if (itnRecipient != null)
      itnRecipient.unlock();
    
    IPTRecipient iptRecipient = this.getIptRecipientDelegate();
    if (iptRecipient != null)
      iptRecipient.unlock();
    
    SprayOperator sprayOperator = this.getSprayOperatorDelegate();
    if (sprayOperator != null)
      sprayOperator.unlock();
    
    SprayLeader sprayLeader = this.getSprayLeaderDelegate();
    if (sprayLeader != null)
      sprayLeader.unlock();
  }
  
  @Override
  public PersonView getView()
  {
    PersonView view = new PersonView();
    view.setPersonId(this.getId());
    view.setFirstName(this.getFirstName());
    view.setLastName(this.getLastName());
    view.setDateOfBirth(this.getDateOfBirth());
    view.addSex(this.getSex().get(0));
    
    MDSSUser user = this.getUserDelegate();
    if (user==null)
    {
      view.setIsMDSSUser(false);
    }
    else
    {
      view.setIsMDSSUser(true);
      view.setUsername(user.getUsername());
    }
    
    Patient patient = this.getPatientDelegate();
    if (patient==null)
    {
      view.setIsPatient(false);
    }
    else
    {
      view.setIsPatient(true);
    }
    
    ITNRecipient itnRecipient = this.getItnRecipientDelegate();
    if (itnRecipient==null)
    {
      view.setIsITNRecipient(false);
    }
    else
    {
      view.setIsITNRecipient(true);
    }
    
    IPTRecipient iptRecipient = this.getIptRecipientDelegate();
    if (iptRecipient==null)
    {
      view.setIsIPTRecipient(false);
    }
    else
    {
      view.setIsIPTRecipient(true);
    }
    
    SprayOperator sprayOperator = this.getSprayOperatorDelegate();
    if (sprayOperator==null)
    {
      view.setIsSprayOperator(false);
    }
    else
    {
      view.setIsSprayOperator(true);
    }
    
    SprayLeader sprayLeader= this.getSprayLeaderDelegate();
    if (sprayLeader==null)
    {
      view.setIsSprayLeader(false);
    }
    else
    {
      view.setIsSprayLeader(true);
    }
    
    return view;
  }
  
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
