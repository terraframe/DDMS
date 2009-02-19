package org.obofoundry.relationship;

public abstract class OBO_REL_has_part extends OBO_REL_has_partBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530475133L;
  
  public OBO_REL_has_part(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_has_part(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
