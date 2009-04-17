package dss.vector.solutions.intervention.monitor;

public class HouseholdPerson extends HouseholdPersonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239729491570L;
  
  public HouseholdPerson(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HouseholdPerson(dss.vector.solutions.intervention.monitor.Household parent, dss.vector.solutions.intervention.monitor.Person child)
  {
    this(parent.getId(), child.getId());
  }
  
}
