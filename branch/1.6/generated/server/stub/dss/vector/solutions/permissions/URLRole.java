package dss.vector.solutions.permissions;

public class URLRole extends URLRoleBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -2037498074;
  
  public URLRole(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public URLRole(dss.vector.solutions.general.SystemURL parent, dss.vector.solutions.permissions.RoleProperty child)
  {
    this(parent.getId(), child.getId());
  }
  
}
