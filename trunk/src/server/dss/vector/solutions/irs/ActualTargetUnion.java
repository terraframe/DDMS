package dss.vector.solutions.irs;

import dss.vector.solutions.irs.IRSUnionIF.ALIAS;


public abstract class ActualTargetUnion extends AbstractTargetUnion
{
  public String setSprayDate(ALIAS alias)
  {
    return set(this.q.sprayDate, alias);
  }

  public String setAggregationLevel(ALIAS alias)
  {
    return set("'3'::TEXT", alias);
  }

}
