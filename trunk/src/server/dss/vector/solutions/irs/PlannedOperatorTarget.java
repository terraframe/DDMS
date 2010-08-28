package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public class PlannedOperatorTarget extends PlannedResourceTarget implements Reloadable
{
  @Override
  public String setOperatorPlannedTarget(Alias alias)
  {
    return set(IRSQuery.WEEKLY_TARGET, alias);
  }
  
  @Override
  public String from()
  {
    String sql = IRSQuery.RESOURCE_TARGET_VIEW + " " + IRSQuery.RESOURCE_TARGET_VIEW + " INNER JOIN "+teamMemberTable+" ON " +
    " "+IRSQuery.RESOURCE_TARGET_VIEW+"."+targeter+" = "+teamMemberTable+"."+idCol+" \n";

    return sql;
  }
}
