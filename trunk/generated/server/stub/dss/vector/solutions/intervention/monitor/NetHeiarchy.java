package dss.vector.solutions.intervention.monitor;

public class NetHeiarchy extends NetHeiarchyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641284632L;

  public NetHeiarchy(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public NetHeiarchy(dss.vector.solutions.intervention.monitor.Net parent, dss.vector.solutions.intervention.monitor.Net child)
  {
    this(parent.getId(), child.getId());
  }

  @Override
  protected String buildKey()
  {
    return this.getParent().getKeyName() + "-" + this.getChild().getKeyName();
  }
}
