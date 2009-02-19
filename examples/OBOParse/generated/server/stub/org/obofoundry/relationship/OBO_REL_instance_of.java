package org.obofoundry.relationship;

public abstract class OBO_REL_instance_of extends OBO_REL_instance_ofBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530474972L;
  
  public OBO_REL_instance_of(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_instance_of(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
