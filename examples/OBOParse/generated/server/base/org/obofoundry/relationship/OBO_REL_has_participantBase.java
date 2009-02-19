package org.obofoundry.relationship;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OBO_REL_has_participant.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class OBO_REL_has_participantBase extends org.obofoundry.OBORelationship implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.relationship.OBO_REL_has_participant";
  private static final long serialVersionUID = 1229530475149L;
  
  public OBO_REL_has_participantBase(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public org.obofoundry.Term getParent()
  {
    return (org.obofoundry.Term) super.getParent();
  }
  
  public org.obofoundry.Term getChild()
  {
    return (org.obofoundry.Term) super.getChild();
  }
  
  public static OBO_REL_has_participant get(String id)
  {
    return (OBO_REL_has_participant) OBO_REL_has_participantBase.get(id);
  }
  
  public static org.obofoundry.relationship.OBO_REL_has_participant lock(java.lang.String id)
  {
    OBO_REL_has_participant _instance = OBO_REL_has_participant.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static org.obofoundry.relationship.OBO_REL_has_participant unlock(java.lang.String id)
  {
    OBO_REL_has_participant _instance = OBO_REL_has_participant.get(id);
    _instance.unlock();
    
    return _instance;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
