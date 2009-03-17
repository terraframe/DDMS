package dss.vector.solutions.mo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AbstractTerm.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AbstractTermBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.mo.AbstractTerm";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DEFINITION = "definition";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ENABLED = "enabled";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String INHERITSTERM = "inheritsTerm";
  public static java.lang.String INHERITSTERMNAME = "inheritsTermName";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OBONAMESPACE = "oboNamespace";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TERMID = "termId";
  public static java.lang.String TERMNAME = "termName";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = 1237311441259L;
  
  public AbstractTermBase()
  {
    super();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreateDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getCreatedBy()
  {
    try
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(CREATEDBY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public String getDefinition()
  {
    return getValue(DEFINITION);
  }
  
  public void validateDefinition()
  {
    this.validateAttribute(DEFINITION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDefinitionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(DEFINITION);
  }
  
  public void setDefinition(String value)
  {
    if(value == null)
    {
      setValue(DEFINITION, "");
    }
    else
    {
      setValue(DEFINITION, value);
    }
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
    }
  }
  
  public Boolean getEnabled()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLED));
  }
  
  public void validateEnabled()
  {
    this.validateAttribute(ENABLED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEnabledMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(ENABLED);
  }
  
  public void setEnabled(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLED, "");
    }
    else
    {
      setValue(ENABLED, java.lang.Boolean.toString(value));
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getInheritsTerm()
  {
    return getValue(INHERITSTERM);
  }
  
  public void validateInheritsTerm()
  {
    this.validateAttribute(INHERITSTERM);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInheritsTermMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(INHERITSTERM);
  }
  
  public void setInheritsTerm(String value)
  {
    if(value == null)
    {
      setValue(INHERITSTERM, "");
    }
    else
    {
      setValue(INHERITSTERM, value);
    }
  }
  
  public String getInheritsTermName()
  {
    return getValue(INHERITSTERMNAME);
  }
  
  public void validateInheritsTermName()
  {
    this.validateAttribute(INHERITSTERMNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInheritsTermNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(INHERITSTERMNAME);
  }
  
  public void setInheritsTermName(String value)
  {
    if(value == null)
    {
      setValue(INHERITSTERMNAME, "");
    }
    else
    {
      setValue(INHERITSTERMNAME, value);
    }
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(KEYNAME);
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdateDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getLastUpdatedBy()
  {
    try
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.terraframe.mojo.system.Users getLockedBy()
  {
    try
    {
      return com.terraframe.mojo.system.Users.get(getValue(LOCKEDBY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLockedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public String getOboNamespace()
  {
    return getValue(OBONAMESPACE);
  }
  
  public void validateOboNamespace()
  {
    this.validateAttribute(OBONAMESPACE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOboNamespaceMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(OBONAMESPACE);
  }
  
  public void setOboNamespace(String value)
  {
    if(value == null)
    {
      setValue(OBONAMESPACE, "");
    }
    else
    {
      setValue(OBONAMESPACE, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
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
  
  public Long getSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSeqMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(SEQ);
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getTermId()
  {
    return getValue(TERMID);
  }
  
  public void validateTermId()
  {
    this.validateAttribute(TERMID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTermIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(TERMID);
  }
  
  public void setTermId(String value)
  {
    if(value == null)
    {
      setValue(TERMID, "");
    }
    else
    {
      setValue(TERMID, value);
    }
  }
  
  public String getTermName()
  {
    return getValue(TERMNAME);
  }
  
  public void validateTermName()
  {
    this.validateAttribute(TERMNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTermNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(TERMNAME);
  }
  
  public void setTermName(String value)
  {
    if(value == null)
    {
      setValue(TERMNAME, "");
    }
    else
    {
      setValue(TERMNAME, value);
    }
  }
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.mo.AbstractTerm.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AbstractTermQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AbstractTermQuery query = new AbstractTermQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AbstractTerm get(String id)
  {
    return (AbstractTerm) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static dss.vector.solutions.mo.AbstractTerm searchByTermId(java.lang.String moId)
  {
    return null;
  }
  
  public static dss.vector.solutions.mo.AbstractTerm searchByTermName(java.lang.String termName)
  {
    return null;
  }
  
  public static AbstractTerm lock(java.lang.String id)
  {
    AbstractTerm _instance = AbstractTerm.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AbstractTerm unlock(java.lang.String id)
  {
    AbstractTerm _instance = AbstractTerm.get(id);
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
