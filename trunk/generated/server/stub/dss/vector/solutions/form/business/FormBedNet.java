package dss.vector.solutions.form.business;

public class FormBedNet extends FormBedNetBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1917753186;
  
  public FormBedNet()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getNetId() != null && this.getNetId().length() > 0)
    {
      return this.getNetId();
    }
    
    return super.buildKey();
  }
  
}
