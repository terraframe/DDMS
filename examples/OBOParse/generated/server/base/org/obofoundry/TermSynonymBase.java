package org.obofoundry;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TermSynonym.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class TermSynonymBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.TermSynonym";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String ID = "id";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SYNONYMNAME = "synonymName";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String TYPE = "type";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  private static final long serialVersionUID = 1229530369267L;
  
  public TermSynonymBase()
  {
    super();
  }
  
  public com.terraframe.mojo.system.metadata.MdDomain getEntityDomain()
  {
    try
    {
      return com.terraframe.mojo.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityDomainMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(org.obofoundry.TermSynonym.CLASS);
    return mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.terraframe.mojo.system.metadata.MdDomain value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
    }
  }
  
  public com.terraframe.mojo.system.Actor getOwner()
  {
    try
    {
      return com.terraframe.mojo.system.Actor.get(getValue(OWNER));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOwnerMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(org.obofoundry.TermSynonym.CLASS);
    return mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.terraframe.mojo.system.Actor value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public String getSynonymName()
  {
    return getValue(SYNONYMNAME);
  }
  
  public void validateSynonymName()
  {
    this.validateAttribute(SYNONYMNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSynonymNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(org.obofoundry.TermSynonym.CLASS);
    return mdClassIF.definesAttribute(SYNONYMNAME);
  }
  
  public void setSynonymName(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMNAME, "");
    }
    else
    {
      setValue(SYNONYMNAME, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public org.obofoundry.HasSynonym addTerms(org.obofoundry.Term term)
  {
    return (org.obofoundry.HasSynonym) addParent(term, org.obofoundry.HasSynonym.CLASS);
  }
  
  public void removeTerms(org.obofoundry.Term term)
  {
    removeAllParents(term, org.obofoundry.HasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.Term> getAllTerms()
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.Term>) getParents(org.obofoundry.HasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.HasSynonym> getAllTermsRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.HasSynonym>) getParentRelationships(org.obofoundry.HasSynonym.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends org.obofoundry.HasSynonym> getTermsRel(org.obofoundry.Term term)
  {
    return (com.terraframe.mojo.query.OIterator<? extends org.obofoundry.HasSynonym>) getRelationshipsWithParent(term, org.obofoundry.HasSynonym.CLASS);
  }
  
  public static TermSynonym get(String id)
  {
    return (TermSynonym) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static org.obofoundry.TermSynonym lock(java.lang.String id)
  {
    TermSynonym _instance = TermSynonym.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static org.obofoundry.TermSynonym unlock(java.lang.String id)
  {
    TermSynonym _instance = TermSynonym.get(id);
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
