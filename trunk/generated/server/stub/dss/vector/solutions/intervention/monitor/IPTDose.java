package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.ChildOption;

public class IPTDose extends IPTDoseBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 1244737040224L;

  public IPTDose(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public IPTDose clone(AggregatedIPTView parent)
  {
    IPTDose clone = new IPTDose(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }

}
