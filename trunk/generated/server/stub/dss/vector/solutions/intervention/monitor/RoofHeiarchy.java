package dss.vector.solutions.intervention.monitor;

public class RoofHeiarchy extends RoofHeiarchyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241556930644L;
  
  public RoofHeiarchy(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public RoofHeiarchy(dss.vector.solutions.intervention.monitor.Roof parent, dss.vector.solutions.intervention.monitor.Roof child)
  {
    this(parent.getId(), child.getId());
  }
  
}
