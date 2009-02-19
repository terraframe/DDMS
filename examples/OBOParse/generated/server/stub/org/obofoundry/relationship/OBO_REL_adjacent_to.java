package org.obofoundry.relationship;

public abstract class OBO_REL_adjacent_to extends OBO_REL_adjacent_toBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530474955L;
  
  public OBO_REL_adjacent_to(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_adjacent_to(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
