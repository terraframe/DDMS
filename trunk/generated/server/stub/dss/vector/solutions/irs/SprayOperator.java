package dss.vector.solutions.irs;

import java.util.concurrent.locks.ReentrantLock;

import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.UniqueOperatorIdException;

public class SprayOperator extends SprayOperatorBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID = 1240853333951L;

  /**
   * Lock used for the validate-then-apply on operator/leader id uniquness
   */
  public static ReentrantLock reentrantLock           = new ReentrantLock();

  public SprayOperator()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getOperatorId();
  }

  @Override
  public void apply()
  {
    reentrantLock.lock();

    try
    {
      validateOperatorId();

      super.apply();
    }
    finally
    {
      reentrantLock.unlock();
    }
  }

  @Override
  public void validateOperatorId()
  {
    if (this.getOperatorId() != null)
    {
      if(SprayOperator.checkForExistingId(this.getOperatorId(), this.getId()))
      {
        String msg = "A Spray Operator or Spray Leader already exists with this operator id";
        throw new UniqueOperatorIdException(msg);
      }
    }
  }
  
  public SprayOperatorView populateView()
  {
    Person person = this.getPerson();

    SprayOperatorView view = new SprayOperatorView();
    view.setActorId(this.getId());
    view.setOperatorId(this.getOperatorId());
    view.setFirstName(person.getFirstName());
    view.setLastName(person.getLastName());
    
    OIterator<? extends SprayTeam> sprayTeam = this.getAllSprayTeam();
    if (sprayTeam.hasNext())
    {
      view.setIsAssigned(true);
      view.setTeamId(sprayTeam.next().getTeamId());
    }
    else
    {
      view.setIsAssigned(false);
    }
    
    return view;
  }

  public static boolean checkForExistingId(String id, String uuid)
  {
    reentrantLock.lock();

    try
    {
      SprayOperatorQuery operatorQuery = new SprayOperatorQuery(new QueryFactory());
      operatorQuery.WHERE(AND.get(operatorQuery.getOperatorId().EQ(id), operatorQuery.getId().NE(uuid)));
      int operatorCount = operatorQuery.getIterator().getAll().size();
      
      SprayLeaderQuery leaderQuery = new SprayLeaderQuery(new QueryFactory());
      leaderQuery.WHERE(AND.get(leaderQuery.getLeaderId().EQ(id), leaderQuery.getId().NE(uuid)));
      int leaderCount = leaderQuery.getIterator().getAll().size();

      return (operatorCount + leaderCount) > 0;
    }
    finally
    {
      reentrantLock.unlock();
    }
  }
  
  public static ValueQuery searchForOperators(String search)
  {
    QueryFactory f = new QueryFactory();
    

    PersonQuery personQuery = new PersonQuery(f);
    SprayOperatorQuery operatorQuery = new SprayOperatorQuery(f);
    ValueQuery valueQuery = new ValueQuery(f);

    Selectable[] selectables = new Selectable[] {
        operatorQuery.getId(SprayOperator.ID),
        operatorQuery.getOperatorId(SprayOperator.OPERATORID),
        personQuery.getFirstName(Person.FIRSTNAME),
        personQuery.getLastName(Person.LASTNAME),
    };
    
    valueQuery.SELECT(selectables);

    String statement = "%" + search + "%";

    // Search conditions
    Condition or = OR.get(
        operatorQuery.getOperatorId().LIKEi(statement),
        personQuery.getFirstName().LIKEi(statement),
        personQuery.getLastName().LIKEi(statement));

    // The person must be a spray operator AND not in team
    Condition and = AND.get(personQuery.getSprayOperatorDelegate().EQ(operatorQuery), or);

    valueQuery.WHERE(and);

    valueQuery.restrictRows(20, 1);
    
    return valueQuery;
  }
  
  public static SprayOperator getByOperatorId(String operatorId)
  {
    SprayOperatorQuery query = new SprayOperatorQuery(new QueryFactory());
    query.WHERE(query.getOperatorId().EQ(operatorId));

    OIterator<? extends SprayOperator> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      return null;
    }
    finally
    {
      iterator.close();
    }
  }
  
}
