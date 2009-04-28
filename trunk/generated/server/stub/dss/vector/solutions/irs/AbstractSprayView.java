package dss.vector.solutions.irs;

public abstract class AbstractSprayView extends AbstractSprayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860693788L;

  public AbstractSprayView()
  {
    super();
  }

  protected void populateSprayData(SprayData data)
  {
    data.clearSurfaceType();

    for(SurfaceType type : this.getSurfaceType())
    {
      data.addSurfaceType(type);
    }
  }

  protected void populateConcrete(AbstractSpray spray, SprayData data)
  {
    spray.setSprayData(data);
  }

  protected boolean hasConcrete()
  {
    return this.getSprayId() != null && !this.getSprayId().equals("");
  }

}
