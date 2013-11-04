package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class GeoTargetView extends AbstractTargetView implements Reloadable
{

  public GeoTargetView(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  @Override
  protected View getView()
  {
    return View.GEO_TARGET_VIEW;
  }

  @Override
  public String getSQL()
  {
    return generateEpiWeekSeriesView(false);
  }

}
