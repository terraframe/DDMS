package dss.vector.solutions.entomology;

import com.runwaysdk.session.Session;

public class ImmatureThreshold extends ImmatureThresholdBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -955212168;

  public ImmatureThreshold()
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

    return this.getClassDisplayLabel() + ": (" + this.getDisplayLabel().getValue(Session.getCurrentLocale()) + ")";
  }

  @Override
  protected String buildKey()
  {
    return this.getThresholdIndex();
  }

  @Override
  public ImmatureThresholdView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public ImmatureThresholdView unlockView()
  {
    this.unlock();

    return this.getView();
  }
  
  @Override
  public ImmatureThresholdView getView()
  {
    ImmatureThresholdView view = new ImmatureThresholdView();

    view.populateView(this);

    return view;
  }

}
