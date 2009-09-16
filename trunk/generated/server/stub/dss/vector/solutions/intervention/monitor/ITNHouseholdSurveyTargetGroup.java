package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.ChildOption;

public class ITNHouseholdSurveyTargetGroup extends ITNHouseholdSurveyTargetGroupBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1252970215678L;
  
  public ITNHouseholdSurveyTargetGroup(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNHouseholdSurveyTargetGroup(ITNHouseholdSurvey parent, TargetGroupGrid child)
  {
    this(parent.getId(), child.getId());
  }
  
  public ITNHouseholdSurveyTargetGroup clone(ITNHouseholdSurveyView parent)
  {
    ITNHouseholdSurveyTargetGroup clone = new ITNHouseholdSurveyTargetGroup(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }    
}
