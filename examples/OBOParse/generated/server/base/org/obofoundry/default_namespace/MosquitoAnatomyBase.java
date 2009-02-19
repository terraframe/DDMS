package org.obofoundry.default_namespace;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoAnatomy.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MosquitoAnatomyBase extends org.obofoundry.Term implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.default_namespace.MosquitoAnatomy";
  private static final long serialVersionUID = 1229530633590L;
  
  public MosquitoAnatomyBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public org.obofoundry.default_namespace.OBO_REL_has_part addHas_part(org.obofoundry.default_namespace.MosquitoAnatomy mosquitoAnatomy)
  {
    return (org.obofoundry.default_namespace.OBO_REL_has_part) addChild(mosquitoAnatomy, org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  public void removeHas_part(org.obofoundry.default_namespace.MosquitoAnatomy mosquitoAnatomy)
  {
    removeAllChildren(mosquitoAnatomy, org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.MosquitoAnatomy> getAllHas_part()
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.MosquitoAnatomy>) getChildren(org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.OBO_REL_has_part> getAllHas_partRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.OBO_REL_has_part>) getChildRelationships(org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.OBO_REL_has_part> getHas_partRel(org.obofoundry.default_namespace.MosquitoAnatomy mosquitoAnatomy)
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.OBO_REL_has_part>) getRelationshipsWithChild(mosquitoAnatomy, org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  public org.obofoundry.default_namespace.OBO_REL_has_part addPart_of(org.obofoundry.default_namespace.MosquitoAnatomy mosquitoAnatomy)
  {
    return (org.obofoundry.default_namespace.OBO_REL_has_part) addParent(mosquitoAnatomy, org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  public void removePart_of(org.obofoundry.default_namespace.MosquitoAnatomy mosquitoAnatomy)
  {
    removeAllParents(mosquitoAnatomy, org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.MosquitoAnatomy> getAllPart_of()
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.MosquitoAnatomy>) getParents(org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.OBO_REL_has_part> getAllPart_ofRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.OBO_REL_has_part>) getParentRelationships(org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.OBO_REL_has_part> getPart_ofRel(org.obofoundry.default_namespace.MosquitoAnatomy mosquitoAnatomy)
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.default_namespace.OBO_REL_has_part>) getRelationshipsWithParent(mosquitoAnatomy, org.obofoundry.default_namespace.OBO_REL_has_part.CLASS);
  }
  
  public static MosquitoAnatomy get(String id)
  {
    return (MosquitoAnatomy) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static org.obofoundry.default_namespace.MosquitoAnatomy lock(java.lang.String id)
  {
    MosquitoAnatomy _instance = MosquitoAnatomy.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static org.obofoundry.default_namespace.MosquitoAnatomy unlock(java.lang.String id)
  {
    MosquitoAnatomy _instance = MosquitoAnatomy.get(id);
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
