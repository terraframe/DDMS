package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class ResourceTargetView extends AbstractTargetView implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.ResourceTargetView";

  public ResourceTargetView(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  @Override
  protected View getView()
  {
    return View.RESOURCE_TARGET_VIEW;
  }

  @Override
  public String getSQL()
  {
    return generateEpiWeekSeriesView(true);
  }

}
