package dss.vector.solutions;

import java.util.Date;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.QueryBuilder;
import dss.vector.solutions.util.QueryUtil;

public class Person extends PersonBase implements com.runwaysdk.generation.loader.Reloadable
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
    if (this.getIdentifier() != null && this.getIdentifier().length() > 0)
    {
      return this.getIdentifier();
    }

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
      validateIdentifier();
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

  @Override
  public void validateIdentifier()
  {
    if (this.getIdentifier() != null && this.getIdentifier().length() > 0)
    {
      PersonQuery query = new PersonQuery(new QueryFactory());
      query.WHERE(AND.get(query.getIdentifier().EQ(this.getIdentifier()), query.getId().NE(this.getId())));
      
      long count = query.getCount();

      if (count > 0)
      {
        throw new UniquePersonIdException();
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

    if (this.getSupervisorDelegate() != null)
    {
      this.getSupervisorDelegate().delete();
    }

    if (this.getPhysicianDelegate() != null)
    {
      this.getPhysicianDelegate().delete();
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

    if (this.getPhysicianDelegate() != null)
    {
      this.getPhysicianDelegate().lock();
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

    if (this.getSupervisorDelegate() != null)
    {
      this.getPhysicianDelegate().unlock();
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
    QueryFactory factory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(factory);
    PersonQuery personQuery = new PersonQuery(valueQuery);

    String residentialLabel = Person.RESIDENTIALGEOENTITY + QueryUtil.DISPLAY_LABEL_SUFFIX;

    SelectablePrimitive[] selectables = new SelectablePrimitive[] { personQuery.getId(PersonView.ID), personQuery.getIdentifier(PersonView.IDENTIFIER), personQuery.getFirstName(PersonView.FIRSTNAME), personQuery.getLastName(PersonView.LASTNAME), personQuery.getDateOfBirth(PersonView.DATEOFBIRTH), personQuery.getSex().getName(PersonView.SEX) };

    SelectableSQLCharacter residentialSelectable = valueQuery.aSQLCharacter(residentialLabel, residentialLabel);

    if (value != null && !value.equals(""))
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { personQuery.getIdentifier(PersonView.IDENTIFIER), personQuery.getFirstName(PersonView.FIRSTNAME), personQuery.getLastName(PersonView.LASTNAME) };

      QueryBuilder.textLookup(valueQuery, factory, tokens, searchables, selectables, new Condition[] {});

      // IMPORTANT: This only works because there is an inner query
      // FIXME: Get the actual reference to the correct query alias and column
      // alias
      QueryUtil.subselectGeoDisplayLabels(residentialSelectable, Person.CLASS, Person.RESIDENTIALGEOENTITY, personQuery.getId(PersonView.ID).getColumnAlias());
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, personQuery.getFirstName(PersonView.FIRSTNAME), selectables, new Condition[] {});

      // IMPORTANT: This only works because there is no inner query
      // FIXME: Get the actual reference to the correct query alias and column
      // alias
      QueryUtil.subselectGeoDisplayLabels(residentialSelectable, Person.CLASS, Person.RESIDENTIALGEOENTITY, personQuery.getId(PersonView.ID).getDefiningTableAlias() + "." + Person.ID);
    }

    valueQuery.SELECT(residentialSelectable);

    valueQuery.restrictRows(20, 1);

    System.out.println(valueQuery.getSQL());

    return valueQuery;
  }

}
