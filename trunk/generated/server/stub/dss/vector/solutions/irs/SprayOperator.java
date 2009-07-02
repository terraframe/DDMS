package dss.vector.solutions.irs;

import java.util.concurrent.locks.ReentrantLock;

import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.QueryFactory;

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
}
