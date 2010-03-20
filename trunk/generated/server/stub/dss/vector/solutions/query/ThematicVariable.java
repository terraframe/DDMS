package dss.vector.solutions.query;

public class ThematicVariable extends ThematicVariableBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242186804833L;
  
  public ThematicVariable()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }
}
