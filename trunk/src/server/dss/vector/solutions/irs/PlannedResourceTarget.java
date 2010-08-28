package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public abstract class PlannedResourceTarget extends PlannedTargetUnion implements Reloadable
{
  @Override
  public String setDisease(Alias alias)
  {
    return set(IRSQuery.RESOURCE_TARGET_VIEW, "disease", alias);
  }
}
