package org.obofoundry;

public class HasSynonym extends HasSynonymBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530369034L;
  
  public HasSynonym(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HasSynonym(org.obofoundry.Term parent, org.obofoundry.TermSynonym child)
  {
    this(parent.getId(), child.getId());
  }
  
}
