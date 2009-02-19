package org.obofoundry;

public abstract class OBORelationship extends OBORelationshipBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1229530369173L;
  
  public OBORelationship(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OBORelationship(org.obofoundry.Component parent, org.obofoundry.Component child)
  {
    this(parent.getId(), child.getId());
  }
  
}
