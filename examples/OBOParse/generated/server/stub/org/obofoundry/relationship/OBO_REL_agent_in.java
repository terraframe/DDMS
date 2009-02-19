package org.obofoundry.relationship;

public abstract class OBO_REL_agent_in extends OBO_REL_agent_inBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530475087L;
  
  public OBO_REL_agent_in(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_agent_in(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
