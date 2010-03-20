package dss.vector.solutions.geo;

public class GeoHierarchyProperty extends GeoHierarchyPropertyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1509771798;
  
  public GeoHierarchyProperty()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getPropertyName() != null)
    {
      return this.getPropertyName();
    }
    
    return super.buildKey();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }
}
