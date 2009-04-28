package dss.vector.solutions.irs;

public class HouseholdSprayStatus extends HouseholdSprayStatusBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860647013L;

  public HouseholdSprayStatus()
  {
    super();
  }

  public SprayStatusView getView()
  {
    HouseholdSprayStatusView view = new HouseholdSprayStatusView();

    view.populate(this);

    return view;
  }
}
