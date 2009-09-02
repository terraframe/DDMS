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
  
  private static final long MAXIMUM_AGE = 110;

  public Person()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    // Person class has no attributes that can form a unique identifier
    return this.getId();
  }

  @Override
  public void apply()
  {
    this.apply(true);
  }
  
  @Transaction
  public void apply(boolean validate)
  {
    if(validate)
    {
      validateDateOfBirth();
    }
    
    super.apply();
  }

  @Override
  public void validateDateOfBirth()
  {
    if (this.getDateOfBirth() != null)
    {
      super.validateDateOfBirth();

      Date current = new Date();

      if (current.before(this.getDateOfBirth()))
      {
        String msg = "It is impossible to have a birth date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getDateOfBirth());
        p.setCurrentDate(current);
        p.setNotification(this, DATEOFBIRTH);
        p.apply();
        p.throwIt();
      }
      else
      {
        Integer age = new AgeConverter(this.getDateOfBirth()).getAge();
        
        if(age > MAXIMUM_AGE)
        {
          String msg = "A person's age can not be older than 110";
          
          PersonAgeProblem p = new PersonAgeProblem(msg);
          p.setAge(age);
          p.setNotification(this, DATEOFBIRTH);
          p.apply();
          p.throwIt();          
        }
      }
    }
  }

  @Transaction
  public void deleteDelegates()
  {
    if (this.getPatientDelegate() != null)
    {
      this.getPatientDelegate().delete();
    }

    if (this.getUserDelegate() != null)
    {
      this.getUserDelegate().delete();
    }

    if (this.getItnRecipientDelegate() != null)
    {
      this.getItnRecipientDelegate().delete();
    }

    if (this.getIptRecipientDelegate() != null)
    {
      this.getIptRecipientDelegate().delete();
    }

    if (this.getSprayOperatorDelegate() != null)
    {
      this.getSprayOperatorDelegate().delete();
    }

    if (this.getSprayLeaderDelegate() != null)
    {
      this.getSprayLeaderDelegate().delete();
    }
  }

  @Transaction
  public void delete()
  {
    this.deleteDelegates();

    super.delete();
  }

  @Transaction
  public void lock()
  {
    super.lock();

    if (this.getUserDelegate() != null)
    {
      this.getUserDelegate().lock();
    }

    if (this.getPatientDelegate() != null)
    {
      this.getPatientDelegate().lock();
    }

    if (this.getItnRecipientDelegate() != null)
    {
      this.getItnRecipientDelegate().lock();
    }

    if (this.getIptRecipientDelegate() != null)
    {
      this.getIptRecipientDelegate().lock();
    }

    if (this.getSprayOperatorDelegate() != null)
    {
      this.getSprayOperatorDelegate().lock();
    }

    if (this.getSprayLeaderDelegate() != null)
    {
      this.getSprayLeaderDelegate().lock();
    }
  }

  @Transaction
  public void unlock()
  {
    super.unlock();

    if (this.getUserDelegate() != null)
    {
      this.getUserDelegate().unlock();
    }

    if (this.getPatientDelegate() != null)
    {
      this.getPatientDelegate().unlock();
    }

    if (this.getItnRecipientDelegate() != null)
    {
      this.getItnRecipientDelegate().unlock();
    }

    if (this.getIptRecipientDelegate() != null)
    {
      this.getIptRecipientDelegate().unlock();
    }

    if (this.getSprayOperatorDelegate() != null)
    {
      this.getSprayOperatorDelegate().unlock();
    }

    if (this.getSprayLeaderDelegate() != null)
    {
      this.getSprayLeaderDelegate().unlock();
    }
  }

  @Override
  public PersonView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void unlockView()
  {
    this.unlock();
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

    if(this.getResidentialGeoEntity() != null)
    {
      view.setResidentialGeoId(this.getResidentialGeoEntity().getGeoId());
    }

    MDSSUser user = this.getUserDelegate();
    if (user == null)
    {
      view.setIsMDSSUser(false);
    }
    else
    {
      view.setIsMDSSUser(true);
      view.setUsername(user.getUsername());
    }

    Patient patient = this.getPatientDelegate();
    if (patient == null)
    {
      view.setIsPatient(false);
    }
    else
    {
      view.setIsPatient(true);
    }

    ITNRecipient itnRecipient = this.getItnRecipientDelegate();
    if (itnRecipient == null)
    {
      view.setIsITNRecipient(false);
    }
    else
    {
      view.setIsITNRecipient(true);
    }

    IPTRecipient iptRecipient = this.getIptRecipientDelegate();
    if (iptRecipient == null)
    {
      view.setIsIPTRecipient(false);
    }
    else
    {
      view.setIsIPTRecipient(true);
    }

    SprayOperator sprayOperator = this.getSprayOperatorDelegate();
    if (sprayOperator == null)
    {
      view.setIsSprayOperator(false);
    }
    else
    {
      view.setIsSprayOperator(true);
      view.setOperatorId(sprayOperator.getOperatorId());
    }

    SprayLeader sprayLeader = this.getSprayLeaderDelegate();
    if (sprayLeader == null)
    {
      view.setIsSprayLeader(false);
    }
    else
    {
      view.setIsSprayLeader(true);
      view.setLeaderId(sprayLeader.getLeaderId());
    }

    return view;
  }

  public PersonQuery searchForDuplicates()
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

    // SprayOperator sprayDelegate = this.getSprayOperatorDelegate();
    // if (sprayDelegate==null)
    // query.WHERE(query.getSprayOperatorDelegate().EQ(""));
    // else
    // query.WHERE(query.getSprayOperatorDelegate().NE(""));

    return query;
  }

  public static Person lockPerson(String id)
  {
    Person person = Person.get(id);
    person.lockPerson();

    return person;
  }

  public void lockPerson()
  {
    super.lock();
  }

  public void unlockPerson()
  {
    super.unlock();
  }
}
