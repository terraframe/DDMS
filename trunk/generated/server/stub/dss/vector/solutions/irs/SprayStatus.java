package dss.vector.solutions.irs;

public class SprayStatus extends SprayStatusBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860690116L;

  public SprayStatus()
  {
    super();
  }

  public SprayStatusView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public SprayStatusView lockView()
  {
    this.lock();

    return this.getView();
  }

  public SprayStatusView getView()
  {
    SprayStatusView view = this.getSpray().getSprayStatusView();

    view.populate(this);

    return view;
  }

  public static SprayStatusView getView(String id)
  {
    SprayStatus status = SprayStatus.get(id);
    return status.getView();
  }
}
