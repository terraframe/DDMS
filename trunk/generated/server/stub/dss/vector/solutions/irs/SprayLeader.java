package dss.vector.solutions.irs;

import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.PersonView;
import dss.vector.solutions.UniqueLeaderIdException;

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
      if(SprayOperator.checkForExistingId(this.getLeaderId(), this.getId()))
      {
        String msg = "A Spray Operator or Spray Leader already exists with the leader id [" + this.getLeaderId() + "]";
        throw new UniqueLeaderIdException(msg);
      }
    }
  }
  
  public static ValueQuery searchForLeader(String search)
  {
    QueryFactory f = new QueryFactory();
    
    PersonQuery personQuery = new PersonQuery(f);
    SprayLeaderQuery leaderQuery = new SprayLeaderQuery(f);
    ValueQuery valueQuery = new ValueQuery(f);

    Selectable[] selectables = new Selectable[] {
        leaderQuery.getId(PersonView.ID),
        leaderQuery.getLeaderId(PersonView.LEADERID),
        personQuery.getFirstName(PersonView.FIRSTNAME),
        personQuery.getLastName(PersonView.LASTNAME),
    };
    
    valueQuery.SELECT(selectables);

    String statement = "%" + search + "%";

    // Search conditions
    Condition or = OR.get(
        leaderQuery.getLeaderId().LIKEi(statement),
        personQuery.getFirstName().LIKEi(statement),
        personQuery.getLastName().LIKEi(statement));

    // The person must be a spray operator AND not in team
    Condition and = AND.get(personQuery.getSprayLeaderDelegate().EQ(leaderQuery), or);

    valueQuery.WHERE(and);

    valueQuery.restrictRows(20, 1);
    
    return valueQuery;
  }
  
  @Override
  public String toString()
  {
    Person person = this.getPerson();
    
    if(person != null)
    {
      return person.getFirstName() + " " + person.getLastName() + " - " + this.getLeaderId();
    }
    
    return this.getId();
  }
}
