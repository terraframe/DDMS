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
    SprayData data = this.getSprayData();
    view.setBrand(data.getBrand());
    view.setGeoEntity(data.getGeoEntity());
    view.setSprayDate(data.getSprayDate());

    view.clearSprayMethod();
    view.clearSurfaceType();

    for(SprayMethod method : data.getSprayMethod())
    {
      view.addSprayMethod(method);
    }

    for(SurfaceType type : data.getSurfaceType())
    {
      view.addSurfaceType(type);
    }

    view.setSprayId(this.getId());
    view.setDataId(data.getId());
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
