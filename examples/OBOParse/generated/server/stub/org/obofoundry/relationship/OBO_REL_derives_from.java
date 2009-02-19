package org.obofoundry.relationship;

public abstract class OBO_REL_derives_from extends OBO_REL_derives_fromBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530475105L;
  
  public OBO_REL_derives_from(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_derives_from(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
