package dss.vector.solutions.irs;

import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;

public abstract class AbstractSprayView extends AbstractSprayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860693788L;

  public AbstractSprayView()
  {
    super();
  }

  protected void populateConcrete(AbstractSpray spray)
  {
    SprayData data = this.getSprayData();

    this.populateSprayData(data);

    spray.setSprayData(data);
  }

  private void populateSprayData(SprayData data)
  {
    data.setSprayDate(this.getSprayDate());
    data.setBrand(this.getBrand());
    data.setGeoEntity(this.getGeoEntity());

    data.clearSprayMethod();
    data.clearSurfaceType();

    for (SprayMethod method : this.getSprayMethod())
    {
      data.addSprayMethod(method);
    }

    for (SurfaceType surface : this.getSurfaceType())
    {
      data.addSurfaceType(surface);
    }
  }

  protected void lockSprayData()
  {
    if (this.getSprayData() == null)
    {
      SprayData data = new SprayData();

      this.populateSprayData(data);

      data.apply();

      this.setSprayData(data);
    }
    else
    {
      this.getSprayData().lock();
    }
  }

  protected void validateSprayMethod(List<SprayMethod> existing)
  {
    List<SprayMethod> newMethod = this.getSprayMethod();

    // Determine if the spray method has changed. If a spray method has changed
    // and this spray already has a spray status then throw an exception,
    // because a the status may have values that are invalid with the new spray
    // method.
    if (!newMethod.containsAll(existing) && this.hasStatus())
    {
      String msg = "The spray method cannot be altered if status rows already exist";
      ModifiedSprayMethodException e = new ModifiedSprayMethodException(msg);
      e.apply();

      throw e;
    }
  }

  public abstract boolean hasStatus();

  protected boolean hasConcrete()
  {
    return this.getSprayId() != null && !this.getSprayId().equals("");
  }

  protected void populateMapping(AbstractSpray spray)
  {
    new AttributeNotificationMap(this.getSprayData(), SprayData.BRAND, this, AbstractSprayView.BRAND);
    new AttributeNotificationMap(this.getSprayData(), SprayData.GEOENTITY, this, AbstractSprayView.GEOENTITY);
    new AttributeNotificationMap(this.getSprayData(), SprayData.SPRAYDATE, this, AbstractSprayView.SPRAYDATE);
    new AttributeNotificationMap(this.getSprayData(), SprayData.SPRAYMETHOD, this, AbstractSprayView.SPRAYMETHOD);
    new AttributeNotificationMap(this.getSprayData(), SprayData.SURFACETYPE, this, AbstractSprayView.SURFACETYPE);
  }

}
