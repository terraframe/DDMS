package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;

public class ActualJoin extends TargetJoin implements Reloadable
{

  public ActualJoin(IRSQB irsQB)
  {
    super(irsQB, true, false);
  }

  @Override
  public String FROM()
  {
    return IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
  }

}
