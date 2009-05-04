package dss.vector.solutions.irs;

public class LeadTeam extends LeadTeamBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241338125751L;
  
  public LeadTeam(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public LeadTeam(dss.vector.solutions.irs.SprayTeam parent, dss.vector.solutions.irs.SprayLeader child)
  {
    this(parent.getId(), child.getId());
  }
  
}
