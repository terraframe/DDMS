package dss.vector.solutions.intervention.monitor;

public class WallHeiarchy extends WallHeiarchyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241556933535L;
  
  public WallHeiarchy(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public WallHeiarchy(dss.vector.solutions.intervention.monitor.Wall parent, dss.vector.solutions.intervention.monitor.Wall child)
  {
    this(parent.getId(), child.getId());
  }
  
}
