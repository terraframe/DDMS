package dss.vector.solutions.intervention.monitor;


public class HouseholdNet extends HouseholdNetBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641285868L;

  public HouseholdNet(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public HouseholdNet(dss.vector.solutions.intervention.monitor.Household parent, dss.vector.solutions.intervention.monitor.Net child)
  {
    this(parent.getId(), child.getId());
  }

  public HouseholdNet clone(Household parent)
  {
    HouseholdNet clone = new HouseholdNet(parent, this.getChild());
    clone.setAmount(this.getAmount());

    return clone;
  }
}
