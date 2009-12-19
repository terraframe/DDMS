package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 368863827)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LethalTimeProperty.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LethalTimePropertyBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.LethalTimeProperty";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDE = "insecticide";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String LOWERPERCENT = "lowerPercent";
  public static java.lang.String LOWERTIME = "lowerTime";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String UPPERPERCENT = "upperPercent";
  public static java.lang.String UPPERTIME = "upperTime";
  private static final long serialVersionUID = 368863827;
  
  public LethalTimePropertyBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public com.terraframe.mojo.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityDomainMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.general.Insecticide getInsecticide()
  {
    if (getValue(INSECTICIDE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.Insecticide.get(getValue(INSECTICIDE));
    }
  }
  
  public void validateInsecticide()
  {
    this.validateAttribute(INSECTICIDE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInsecticideMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDE);
  }
  
  public void setInsecticide(dss.vector.solutions.general.Insecticide value)
  {
    if(value == null)
    {
      setValue(INSECTICIDE, "");
    }
    else
    {
      setValue(INSECTICIDE, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.terraframe.mojo.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.Users.get(getValue(LOCKEDBY));
    }
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLockedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Integer getLowerPercent()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOWERPERCENT));
  }
  
  public void validateLowerPercent()
  {
    this.validateAttribute(LOWERPERCENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLowerPercentMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(LOWERPERCENT);
  }
  
  public void setLowerPercent(Integer value)
  {
    if(value == null)
    {
      setValue(LOWERPERCENT, "");
    }
    else
    {
      setValue(LOWERPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getLowerTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOWERTIME));
  }
  
  public void validateLowerTime()
  {
    this.validateAttribute(LOWERTIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLowerTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(LOWERTIME);
  }
  
  public void setLowerTime(Integer value)
  {
    if(value == null)
    {
      setValue(LOWERTIME, "");
    }
    else
    {
      setValue(LOWERTIME, java.lang.Integer.toString(value));
    }
  }
  
  public com.terraframe.mojo.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.Actor.get(getValue(OWNER));
    }
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOwnerMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  public Integer getUpperPercent()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UPPERPERCENT));
  }
  
  public void validateUpperPercent()
  {
    this.validateAttribute(UPPERPERCENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUpperPercentMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(UPPERPERCENT);
  }
  
  public void setUpperPercent(Integer value)
  {
    if(value == null)
    {
      setValue(UPPERPERCENT, "");
    }
    else
    {
      setValue(UPPERPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getUpperTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UPPERTIME));
  }
  
  public void validateUpperTime()
  {
    this.validateAttribute(UPPERTIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUpperTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.LethalTimeProperty.CLASS);
    return mdClassIF.definesAttribute(UPPERTIME);
  }
  
  public void setUpperTime(Integer value)
  {
    if(value == null)
    {
      setValue(UPPERTIME, "");
    }
    else
    {
      setValue(UPPERTIME, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LethalTimePropertyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LethalTimePropertyQuery query = new LethalTimePropertyQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static LethalTimeProperty get(String id)
  {
    return (LethalTimeProperty) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static LethalTimeProperty getByKey(String key)
  {
    return (LethalTimeProperty) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.general.LethalTimeProperty searchByInsecticide(dss.vector.solutions.general.Insecticide insecticide)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.LethalTimeProperty.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static LethalTimeProperty lock(java.lang.String id)
  {
    LethalTimeProperty _instance = LethalTimeProperty.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static LethalTimeProperty unlock(java.lang.String id)
  {
    LethalTimeProperty _instance = LethalTimeProperty.get(id);
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
