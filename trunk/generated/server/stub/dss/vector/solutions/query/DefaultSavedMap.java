package dss.vector.solutions.query;

public class DefaultSavedMap extends DefaultSavedMapBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 188069998;
  
  static final String DEFAULT = "__DEFAULT__";
  
  public DefaultSavedMap()
  {
    super();
  }
  
  public void apply()
  {
    this.setMapName(DEFAULT);
    
    super.apply();
  }
  
}
