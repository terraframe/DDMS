package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;

public class ITNCommunityNet extends ITNCommunityNetBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1252612171019L;
  
  public ITNCommunityNet(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNCommunityNet(ITNCommunityDistribution parent, Term child)
  {
    this(parent.getId(), child.getId());
  }

  public ITNCommunityNet clone(ITNCommunityDistributionView parent)
  {
    ITNCommunityNet clone = new ITNCommunityNet(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }

}
