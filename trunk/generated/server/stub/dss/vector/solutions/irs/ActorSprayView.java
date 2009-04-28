package dss.vector.solutions.irs;

public abstract class ActorSprayView extends ActorSprayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676330L;

  public ActorSprayView()
  {
    super();
  }

  protected void populateConcrete(ActorSpray spray, SprayData data)
  {
    super.populateConcrete(spray, data);
    spray.setTarget(this.getTarget());
    spray.setTeamSprayWeek(this.getTeamSprayWeek());
  }

}
