package dss.vector.solutions.irs;

public class InTeam extends InTeamBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240342487003L;
  
  public InTeam(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public InTeam(dss.vector.solutions.irs.SprayTeam parent, dss.vector.solutions.MDSSUser child)
  {
    this(parent.getId(), child.getId());
  }

  @Override
  protected String buildKey()
  {
    return this.getParent().buildKey() + "-" + this.getChild().buildKey();
  }
}
