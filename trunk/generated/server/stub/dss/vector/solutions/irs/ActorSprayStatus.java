package dss.vector.solutions.irs;

public class ActorSprayStatus extends ActorSprayStatusBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242233863751L;
  
  public ActorSprayStatus()
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
    else 
    {
      return this.buildKey();
    }
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getSpray() != null)
    {
      return this.getSpray().getKey();
    }
    
    return this.getId();
  }
}
