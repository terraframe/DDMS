package dss.vector.solutions.geo;

public class ExtraFieldUniversal extends ExtraFieldUniversalBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -2104292423;

  public ExtraFieldUniversal(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public ExtraFieldUniversal(dss.vector.solutions.geo.GeoField parent, dss.vector.solutions.geo.GeoHierarchy child)
  {
    this(parent.getId(), child.getId());
  }

  @Override
  protected String buildKey()
  {
    if (this.getParentId() != null && this.getChildId() != null)
    {
      GeoField parent = GeoField.get(this.getParentId());
      GeoHierarchy child = GeoHierarchy.get(this.getChildId());

      return parent.getKey() + "-" + child.getKey();
    }

    return super.buildKey();
  }

}
