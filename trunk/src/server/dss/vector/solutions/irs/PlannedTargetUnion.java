package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public abstract class PlannedTargetUnion extends AbstractTargetUnion implements Reloadable
{
  @Override
  public String setTargetWeek(Alias alias)
  {
    return set(alias.getAlias(), alias);    
  }

}
