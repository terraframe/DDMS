package dss.vector.solutions.intervention.monitor;

public class ITNRecipient extends ITNRecipientBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240792903115L;
  
  public ITNRecipient()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
}
