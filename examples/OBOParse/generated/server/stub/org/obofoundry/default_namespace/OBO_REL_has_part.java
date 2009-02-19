package org.obofoundry.default_namespace;

public class OBO_REL_has_part extends OBO_REL_has_partBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530633581L;
  
  public OBO_REL_has_part(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBO_REL_has_part(org.obofoundry.default_namespace.MosquitoAnatomy parent, org.obofoundry.default_namespace.MosquitoAnatomy child)
  {
    this(parent.getId(), child.getId());
  }
  
}
