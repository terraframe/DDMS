package org.obofoundry.relationship;

public abstract class OBO_REL_contains extends OBO_REL_containsBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530475168L;
  
  public OBO_REL_contains(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_contains(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
