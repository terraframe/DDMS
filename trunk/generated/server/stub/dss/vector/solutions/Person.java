package dss.vector.solutions;

import java.util.Date;
import java.util.StringTokenizer;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.QueryUtil;

public class Person extends PersonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240792902476L;

  private static final long MAXIMUM_AGE      = 110;

  public Person()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      String toString = this.getFirstName() + " " + this.getLastName();
      
      if (toString.length() > 1)
      {
        return toString;
      }
      
      return super.toString();
    }
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
    if (validate)
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

        if (age > MAXIMUM_AGE)
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

    if (this.getTeamMemberDelegate() != null)
    {
      this.getTeamMemberDelegate().delete();
    }

    if (this.getStockStaffDelegate() != null)
    {
      this.getStockStaffDelegate().delete();
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

    if (this.getTeamMemberDelegate() != null)
    {
      this.getTeamMemberDelegate().lock();
    }

    if (this.getStockStaffDelegate() != null)
    {
      this.getStockStaffDelegate().lock();
    }

    if (this.getSupervisorDelegate() != null)
    {
      this.getSupervisorDelegate().lock();
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

    if (this.getTeamMemberDelegate() != null)
    {
      this.getTeamMemberDelegate().unlock();
    }

    if (this.getStockStaffDelegate() != null)
    {
      this.getStockStaffDelegate().unlock();
    }

    if (this.getSupervisorDelegate() != null)
    {
      this.getSupervisorDelegate().unlock();
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
    // Set the person's base attributes
    PersonView view = new PersonView();

    view.populateView(this);

    return view;
  }

  public PersonWithDelegatesViewQuery searchForDuplicates()
  {
    PersonWithDelegatesViewQuery query = new PersonWithDelegatesViewQuery(new QueryFactory());

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

    if (sex != null)
    {
      query.WHERE(query.getSex().EQ(sex));
    }

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

  public static ValueQuery searchForPerson(String value)
  {
    QueryFactory f = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(f);
    PersonQuery personQuery = new PersonQuery(valueQuery);

    String residentialLabel = Person.RESIDENTIALGEOENTITY + QueryUtil.DISPLAY_LABEL_SUFFIX;

    Selectable[] selectables = new Selectable[] {
        personQuery.getId(PersonView.ID),
        personQuery.getFirstName(PersonView.FIRSTNAME),
        personQuery.getLastName(PersonView.LASTNAME),
        personQuery.getDateOfBirth(PersonView.DATEOFBIRTH),
        personQuery.getSex().getName(PersonView.SEX),
        valueQuery.aSQLCharacter(PersonView.RESIDENTIALGEOID, residentialLabel)
    };

    valueQuery.SELECT(selectables);

    QueryUtil.joinGeoDisplayLabels(valueQuery, Person.CLASS, personQuery);

    String statement = "%" + value + "%";

    // Search conditions
    Condition or = OR.get(personQuery.getFirstName().LIKEi(statement), personQuery.getLastName().LIKEi(statement));

    StringTokenizer toke = new StringTokenizer(value, ", ");

    while (toke.hasMoreTokens())
    {
      String string = "%" + toke.nextToken() + "%";

      or = OR.get(or, personQuery.getFirstName().LIKEi(string), personQuery.getLastName().LIKEi(string));
    }

    // The person must be a IPT Recipient
    valueQuery.WHERE(or);
    valueQuery.ORDER_BY_ASC((SelectablePrimitive) valueQuery.getSelectableRef(Person.FIRSTNAME));

    valueQuery.restrictRows(15, 1);

    return valueQuery;
  }

}
