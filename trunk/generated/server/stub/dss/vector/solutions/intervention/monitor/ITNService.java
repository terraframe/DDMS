package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;

public class ITNService extends ITNServiceBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1245774424262L;
  
  public ITNService(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ITNService(ITNData parent, Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public ITNService clone(ITNDataView parent)
  {
    ITNService clone = new ITNService(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }  
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
}
