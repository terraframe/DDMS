package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.ChildOption;

public class ITNTargetGroup extends ITNTargetGroupBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1245774474363L;
  
  public ITNTargetGroup(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNTargetGroup(dss.vector.solutions.intervention.monitor.ITNData parent, dss.vector.solutions.intervention.monitor.TargetGroupGrid child)
  {
    this(parent.getId(), child.getId());
  }
  
  public ITNTargetGroup clone(ITNDataView parent)
  {
    ITNTargetGroup clone = new ITNTargetGroup(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
}
