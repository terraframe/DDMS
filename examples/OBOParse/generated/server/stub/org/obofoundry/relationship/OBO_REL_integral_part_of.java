package org.obofoundry.relationship;

public abstract class OBO_REL_integral_part_of extends OBO_REL_integral_part_ofBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530474980L;
  
  public OBO_REL_integral_part_of(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_integral_part_of(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
