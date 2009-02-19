package org.obofoundry;

public class IsA extends IsABase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530369007L;
  
  public IsA(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public IsA(org.obofoundry.Component parent, org.obofoundry.Component child)
  {
    this(parent.getId(), child.getId());
  }
  
}
