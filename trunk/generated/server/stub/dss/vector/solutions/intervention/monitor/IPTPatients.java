package dss.vector.solutions.intervention.monitor;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.surveillance.ChildOption;

public class IPTPatients extends IPTPatientsBase implements ChildOption, Reloadable 
{
  private static final long serialVersionUID = 1244737040350L;

  public IPTPatients(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public IPTPatients(dss.vector.solutions.intervention.monitor.AggregatedIPT parent,
      dss.vector.solutions.intervention.monitor.PatientGrid child)
  {
    this(parent.getId(), child.getId());
  }

  public IPTPatients clone(AggregatedIPTView parent)
  {
    IPTPatients clone = new IPTPatients(parent.getConcreteId(), this.getChildId());
    clone.setAmount(this.getAmount());

    return clone;
  }
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }

}
