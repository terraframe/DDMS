package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;

public class ITNDistributionTargetGroup extends ITNDistributionTargetGroupBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1253142896539L;
  
  public ITNDistributionTargetGroup(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNDistributionTargetGroup(ITNDistribution parent, Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public ITNDistributionTargetGroup clone(ITNDistributionView parent)
  {
    ITNDistributionTargetGroup clone = new ITNDistributionTargetGroup(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
  
}
