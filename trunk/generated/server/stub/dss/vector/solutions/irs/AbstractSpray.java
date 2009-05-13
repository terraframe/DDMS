package dss.vector.solutions.irs;

public abstract class AbstractSpray extends AbstractSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597926952L;

  public AbstractSpray()
  {
    super();
  }

  public abstract SprayStatusView getSprayStatusView();
  
  public void populateView(AbstractSprayView view)
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

    view.setDataId(data.getId());
    view.setSprayId(this.getId());
  }
}
