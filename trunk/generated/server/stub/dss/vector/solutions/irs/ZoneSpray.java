package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import dss.vector.solutions.general.Disease;

public class ZoneSpray extends ZoneSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676906L;

  public ZoneSpray()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }

    return this.getClassDisplayLabel();
  }

  @Override
  protected String buildKey()
  {
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      return this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName;
    }

    return this.getId();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    List<SprayMethod> _sprayMethod = this.getSprayMethod();

    if (_sprayMethod.size() > 0)
    {
      this.setSprayMethodForIndex(_sprayMethod.get(0).getEnumName());
    }

    this.setGeoEntityForIndex(this.getGeoEntity());
    this.setBrandForIndex(this.getBrand());
    this.setSprayDateForIndex(this.getSprayDate());

    super.apply();
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

    view.populateView(this);

    return view;
  }


}
