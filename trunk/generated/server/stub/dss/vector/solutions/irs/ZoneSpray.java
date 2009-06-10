package dss.vector.solutions.irs;

public class ZoneSpray extends ZoneSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676906L;

  public ZoneSpray()
  {
    super();
  }

  public ZoneSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ZoneSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public ZoneSprayView getView()
  {
    ZoneSprayView view = new ZoneSprayView();

    this.populateView(view);

    return view;
  }

  public void populateView(ZoneSprayView view)
  {
    super.populateView(view);
    
    view.setSupervisorName(this.getSupervisorName());
    view.setSupervisorSurname(this.getSupervisorSurname());
    view.setTarget(this.getTarget());
    view.setSprayWeek(this.getSprayWeek());    
  }

  @Override
  public SprayStatusView getSprayStatusView()
  {
    return null;
  }

  public static ZoneSprayView getView(String id)
  {
    return ZoneSpray.get(id).getView();
  }

}
