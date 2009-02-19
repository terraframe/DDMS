package org.obofoundry.relationship;

public abstract class OBO_REL_transformed_into extends OBO_REL_transformed_intoBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530474988L;
  
  public OBO_REL_transformed_into(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_transformed_into(org.obofoundry.Term parent, org.obofoundry.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
