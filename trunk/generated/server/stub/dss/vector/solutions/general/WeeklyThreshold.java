package dss.vector.solutions.general;

public class WeeklyThreshold extends WeeklyThresholdBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256576864223L;
  
  public WeeklyThreshold(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public WeeklyThreshold(dss.vector.solutions.general.ThresholdData parent, dss.vector.solutions.general.EpiWeek child)
  {
    this(parent.getId(), child.getId());
  }
  
}
