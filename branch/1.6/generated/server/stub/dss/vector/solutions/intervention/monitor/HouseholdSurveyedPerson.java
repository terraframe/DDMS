package dss.vector.solutions.intervention.monitor;

public class HouseholdSurveyedPerson extends HouseholdSurveyedPersonBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 828050340;
  
  public HouseholdSurveyedPerson(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HouseholdSurveyedPerson(dss.vector.solutions.intervention.monitor.Household parent, dss.vector.solutions.intervention.monitor.SurveyedPerson child)
  {
    this(parent.getId(), child.getId());
  }
  
}
