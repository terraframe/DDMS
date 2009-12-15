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

    if (this.getSprayOperatorDelegate() != null)
    {
      this.getSprayOperatorDelegate().delete();
    }

    if (this.getSprayLeaderDelegate() != null)
    {
      this.getSprayLeaderDelegate().delete();
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

    if (this.getSprayOperatorDelegate() != null)
    {
      this.getSprayOperatorDelegate().lock();
    }

    if (this.getSprayLeaderDelegate() != null)
    {
      this.getSprayLeaderDelegate().lock();
    }

    if (this.getStockStaffDelegate() != null)
    {
      this.getStockStaffDelegate().lock();
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

    if (this.getStockStaffDelegate() != null)
    {
      this.getStockStaffDelegate().unlock();
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
    // FIXME MO Upgrade
    // Sex sex = this.getSex().get(0);
    // if (!sex.equals(Sex.UNKNOWN))
    // query.WHERE(query.getSex().containsExactly(sex));
    query.WHERE(query.getSex().EQ(this.getSex()));

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

  @Override
  public String toString()
  {
    String value = this.getFirstName() + " " + this.getLastName();
    if (value.length() > 1)
    {
      return value;
    }
    return this.getKey();
  }

  public static ValueQuery searchForPerson(String value)
  {
    QueryFactory f = new QueryFactory();

    PersonQuery personQuery = new PersonQuery(f);
    ValueQuery valueQuery = new ValueQuery(f);

    String residentialLabel = Person.RESIDENTIALGEOENTITY + QueryUtil.DISPLAY_LABEL_SUFFIX;
    
    Selectable[] selectables = new Selectable[] {
        personQuery.getId(PersonView.ID),
        personQuery.getFirstName(PersonView.FIRSTNAME),
        personQuery.getLastName(PersonView.LASTNAME),
        personQuery.getDateOfBirth(PersonView.DATEOFBIRTH),
        personQuery.getSex().getName(PersonView.SEX),
        valueQuery.aSQLCharacter(PersonView.RESIDENTIALGEOID, residentialLabel),
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
    valueQuery.ORDER_BY_ASC((SelectablePrimitive) valueQuery.getSelectable(Person.FIRSTNAME));

    valueQuery.restrictRows(20, 1);

    System.out.println(valueQuery.getSQL());
    
    return valueQuery;
  }

}
