package dss.vector.solutions.intervention.monitor;

public class LarvacideInstance extends LarvacideInstanceBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257372023824L;
  
  public LarvacideInstance()
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
  public LarvacideInstanceView getView()
  {
    LarvacideInstanceView view = new LarvacideInstanceView();
    view.populateView(this);
    
    return view;
  }
  
  @Override
  public LarvacideInstanceView lockView()
  {
    this.lock();

    return this.getView();
  }
  
  @Override
  public LarvacideInstanceView unlockView()
  {
    this.unlock();

    return this.getView();
  }
}
