package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.ChildOption;

public class ITNHouseholdSurveyNet extends ITNHouseholdSurveyNetBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1252970185315L;
  
  public ITNHouseholdSurveyNet(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNHouseholdSurveyNet(ITNHouseholdSurvey parent, Net child)
  {
    this(parent.getId(), child.getId());
  }
  
  public ITNHouseholdSurveyNet clone(ITNHouseholdSurveyView parent)
  {
    ITNHouseholdSurveyNet clone = new ITNHouseholdSurveyNet(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }  
}
