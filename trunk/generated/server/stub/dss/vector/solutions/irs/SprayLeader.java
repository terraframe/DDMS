package dss.vector.solutions.irs;

import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectablePrimitive;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.UniqueLeaderIdException;
import dss.vector.solutions.query.QueryBuilder;

public class SprayLeader extends SprayLeaderBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240792904267L;

  public SprayLeader()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getLeaderId();
  }

  @Override
  public void apply()
  {
    SprayOperator.reentrantLock.lock();

    try
    {
      validateLeaderId();

      super.apply();
    }
    finally
    {
      SprayOperator.reentrantLock.unlock();
    }
  }

  @Override
  public void validateLeaderId()
  {
    if (this.getLeaderId() != null)
    {
      if (SprayOperator.checkForExistingId(this.getLeaderId(), this.getId()))
      {
        String msg = "A Spray Operator or Spray Leader already exists with the leader id [" + this.getLeaderId() + "]";
        throw new UniqueLeaderIdException(msg);
      }
    }
  }

  public static ValueQuery searchForLeader(String search)
  {
    String[] array = search.split(" ");

    QueryFactory f = new QueryFactory();
    PersonQuery personQuery = new PersonQuery(f);
    SprayLeaderQuery leaderQuery = new SprayLeaderQuery(f);

    SelectablePrimitive[] selectables = new SelectablePrimitive[] {
      leaderQuery.getId(PersonView.ID),
      leaderQuery.getLeaderId(PersonView.LEADERID),
      personQuery.getFirstName(PersonView.FIRSTNAME),
      personQuery.getLastName(PersonView.LASTNAME)
    };

    Condition[] conditions = new Condition[] { personQuery.getSprayLeaderDelegate().EQ(leaderQuery) };

    ValueQuery valueQuery = QueryBuilder.textLookup(f, array, selectables, conditions);

    return valueQuery;
  }

  @Override
  public String toString()
  {
    Person person = this.getPerson();

    if (person != null)
    {
      return person.getFirstName() + " " + person.getLastName() + " - " + this.getLeaderId();
    }

    return this.getId();
  }
}
