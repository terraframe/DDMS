package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;

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
    
    sql += IRSQB.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    
    return sql;
  }

}
