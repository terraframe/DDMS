package csu.mrc.ivcc.mdss.geo;

public class LocatedIn extends LocatedInBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133821075L;
  
  public LocatedIn(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public LocatedIn(csu.mrc.ivcc.mdss.geo.generated.GeoEntity parent, csu.mrc.ivcc.mdss.geo.generated.GeoEntity child)
  {
    this(parent.getId(), child.getId());
  }
  
}
