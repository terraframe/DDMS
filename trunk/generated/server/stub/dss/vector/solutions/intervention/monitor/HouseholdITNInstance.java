package dss.vector.solutions.intervention.monitor;

public class HouseholdITNInstance extends HouseholdITNInstanceBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1730503174;
  
  public HouseholdITNInstance(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HouseholdITNInstance(dss.vector.solutions.intervention.monitor.Household parent, dss.vector.solutions.intervention.monitor.ITNInstance child)
  {
    this(parent.getId(), child.getId());
  }
  
}
