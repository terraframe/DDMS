package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;

public class ITNNet extends ITNNetBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1245774474310L;
  
  public ITNNet(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNNet(ITNData parent, Term child)
  {
    this(parent.getId(), child.getId());
  }

  public ITNNet clone(ITNDataView parent)
  {
    ITNNet clone = new ITNNet(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
}
