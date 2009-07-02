package dss.vector.solutions.irs;

import dss.vector.solutions.UniqueLeaderIdException;

public class SprayLeader extends SprayLeaderBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240792904267L;
  
  public SprayLeader()
  {
    super();
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
}
