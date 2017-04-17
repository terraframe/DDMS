package dss.vector.solutions.permissions;

import java.util.List;

public class RoleProperty extends RolePropertyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -465952321;

  public RoleProperty()
  {
    super();
  }

  public void removeExistingURLs()
  {
    // Delete any existing URL role relationships of the RoleProperty
    List<? extends URLRole> urlRoles = this.getAllSystemURLsRel().getAll();

    for (URLRole urlRole : urlRoles)
    {
      urlRole.delete();
    }
  }

}
