package dss.vector.solutions.intervention.monitor;

public class SurveyHousehold extends SurveyHouseholdBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239652682107L;
  
  public SurveyHousehold(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public SurveyHousehold(dss.vector.solutions.intervention.monitor.SurveyPoint parent, dss.vector.solutions.intervention.monitor.Household child)
  {
    this(parent.getId(), child.getId());
  }
  
}
