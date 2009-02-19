package org.obofoundry.relationship;

public abstract class OBO_REL_preceded_by extends OBO_REL_preceded_byBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530475125L;
  
  public OBO_REL_preceded_by(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_preceded_by(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
