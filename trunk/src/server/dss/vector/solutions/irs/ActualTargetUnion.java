package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;



public abstract class ActualTargetUnion extends AbstractTargetUnion implements Reloadable
{
  
  public final String setSprayDate(Alias alias)
  {
    return set(this.q.sprayDate, alias);
  }

  public final String setSpraySeason(Alias alias)
  {
    return set("sprayseason", q.idCol, alias);
  };
}
