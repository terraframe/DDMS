package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;

public class ITNCommunityTargetGroup extends ITNCommunityTargetGroupBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1252612169723L;
  
  public ITNCommunityTargetGroup(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNCommunityTargetGroup(ITNCommunityDistribution parent, Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public ITNCommunityTargetGroup clone(ITNCommunityDistributionView parent)
  {
    ITNCommunityTargetGroup clone = new ITNCommunityTargetGroup(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
}
