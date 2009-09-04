package dss.vector.solutions.query;

public abstract class StyleRule extends StyleRuleBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158059617L;
  
  public StyleRule()
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
