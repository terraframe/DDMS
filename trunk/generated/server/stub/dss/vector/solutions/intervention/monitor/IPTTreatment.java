package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.ChildOption;

public class IPTTreatment extends IPTTreatmentBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 1244737056472L;

  public IPTTreatment(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public IPTTreatment(dss.vector.solutions.intervention.monitor.AggregatedIPT parent,
      dss.vector.solutions.surveillance.TreatmentGrid child)
  {
    this(parent.getId(), child.getId());
  }

  public IPTTreatment clone(AggregatedIPTView parent)
  {
    IPTTreatment clone = new IPTTreatment(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }

}
