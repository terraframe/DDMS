package dss.vector.solutions.query;

public class TextStyle extends TextStyleBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158043136L;
  
  public TextStyle()
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
