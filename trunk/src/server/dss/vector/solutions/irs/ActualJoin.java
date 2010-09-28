package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public class ActualJoin extends TargetJoin implements Reloadable
{

  public ActualJoin()
  {
    super(true, false);
  }

  @Override
  public String from()
  {
    String sql = "";
    
    sql += IRSQuery.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    
    return sql;
  }

}
