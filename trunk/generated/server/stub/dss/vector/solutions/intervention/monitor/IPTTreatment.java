package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.ChildOption;

public class IPTTreatment extends IPTTreatmentBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 1244737056472L;

  public IPTTreatment(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public IPTTreatment clone(AggregatedIPTView parent)
  {
    IPTTreatment clone = new IPTTreatment(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }

  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
}
