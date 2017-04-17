package dss.vector.solutions.irs;

import dss.vector.solutions.geo.generated.GeoEntity;

public abstract class InterventionPlanningView extends InterventionPlanningViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254346304596L;

  public InterventionPlanningView()
  {
    super();
  }

  public void setEntityLabel(GeoEntity entity)
  {
    this.setEntityLabel(entity.getLabel());
  }

  protected boolean validateCalculation()
  {
    if (this.getTargets() == null || this.getTargets() == 0)
    {
//      String msg = "Targets have not been populated";
//
//      RequiredTargetsProblem p = new RequiredTargetsProblem(msg);
//      p.setNotification(this, TARGETS);
//      p.setEntityLabel(this.getEntityLabel());
//      p.apply();
//      p.throwIt();
//      
      return false;
    }
    
    return true;
  }
}
