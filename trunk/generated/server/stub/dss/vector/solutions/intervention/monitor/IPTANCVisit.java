package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.ChildOption;

public class IPTANCVisit extends IPTANCVisitBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 1244737056790L;

  public IPTANCVisit(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public IPTANCVisit(dss.vector.solutions.intervention.monitor.AggregatedIPT parent,
      dss.vector.solutions.intervention.monitor.VisitGrid child)
  {
    this(parent.getId(), child.getId());
  }

  public IPTANCVisit clone(AggregatedIPTView parent)
  {
    IPTANCVisit clone = new IPTANCVisit(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }

}
