package dss.vector.solutions.intervention.monitor;

public class LarvacideAssociation extends LarvacideAssociationBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257372024562L;
  
  public LarvacideAssociation(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public LarvacideAssociation(dss.vector.solutions.intervention.monitor.Larvacide parent, dss.vector.solutions.intervention.monitor.LarvacideInstance child)
  {
    this(parent.getId(), child.getId());
  }
  
}
