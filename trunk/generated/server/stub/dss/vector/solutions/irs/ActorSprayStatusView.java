package dss.vector.solutions.irs;

public abstract class ActorSprayStatusView extends ActorSprayStatusViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240941676542L;

  public ActorSprayStatusView()
  {
    super();
  }

  public void populate(SprayStatus status)
  {
    super.populate(status);

    this.setSprayData(status.getSpray().getSprayData());
  }

}
