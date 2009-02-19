package org.obofoundry.relationship;

public abstract class OBO_REL_has_participant extends OBO_REL_has_participantBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530475149L;
  
  public OBO_REL_has_participant(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_has_participant(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
