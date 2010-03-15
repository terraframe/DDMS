package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -1288845333)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LocalProperty.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class LocalPropertyBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.LocalProperty";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String EDITABLE = "editable";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PROPERTYNAME = "propertyName";
  public static java.lang.String PROPERTYPACKAGE = "propertyPackage";
  public static java.lang.String PROPERTYTYPE = "propertyType";
  public static java.lang.String PROPERTYVALIDATOR = "propertyValidator";
  public static java.lang.String PROPERTYVALUE = "propertyValue";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String VALIDVALUES = "validValues";
  private static final long serialVersionUID = -1288845333;
  
  public LocalPropertyBase()
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public String getDescription()
  {
    return getValue(DESCRIPTION);
  }
  
  public void validateDescription()
  {
    this.validateAttribute(DESCRIPTION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDescriptionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(DESCRIPTION);
  }
  
  public void setDescription(String value)
  {
    if(value == null)
    {
      setValue(DESCRIPTION, "");
    }
    else
    {
      setValue(DESCRIPTION, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
  
  public Boolean getEditable()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(EDITABLE));
  }
  
  public void validateEditable()
  {
    this.validateAttribute(EDITABLE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEditableMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(EDITABLE);
  }
  
  public void setEditable(Boolean value)
  {
    if(value == null)
    {
      setValue(EDITABLE, "");
    }
    else
    {
      setValue(EDITABLE, java.lang.Boolean.toString(value));
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(ID);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
  
  public String getPropertyName()
  {
    return getValue(PROPERTYNAME);
  }
  
  public void validatePropertyName()
  {
    this.validateAttribute(PROPERTYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPropertyNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(PROPERTYNAME);
  }
  
  public void setPropertyName(String value)
  {
    if(value == null)
    {
      setValue(PROPERTYNAME, "");
    }
    else
    {
      setValue(PROPERTYNAME, value);
    }
  }
  
  public String getPropertyPackage()
  {
    return getValue(PROPERTYPACKAGE);
  }
  
  public void validatePropertyPackage()
  {
    this.validateAttribute(PROPERTYPACKAGE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPropertyPackageMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(PROPERTYPACKAGE);
  }
  
  public void setPropertyPackage(String value)
  {
    if(value == null)
    {
      setValue(PROPERTYPACKAGE, "");
    }
    else
    {
      setValue(PROPERTYPACKAGE, value);
    }
  }
  
  public String getPropertyType()
  {
    return getValue(PROPERTYTYPE);
  }
  
  public void validatePropertyType()
  {
    this.validateAttribute(PROPERTYTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPropertyTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(PROPERTYTYPE);
  }
  
  public void setPropertyType(String value)
  {
    if(value == null)
    {
      setValue(PROPERTYTYPE, "");
    }
    else
    {
      setValue(PROPERTYTYPE, value);
    }
  }
  
  public String getPropertyValidator()
  {
    return getValue(PROPERTYVALIDATOR);
  }
  
  public void validatePropertyValidator()
  {
    this.validateAttribute(PROPERTYVALIDATOR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPropertyValidatorMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(PROPERTYVALIDATOR);
  }
  
  public void setPropertyValidator(String value)
  {
    if(value == null)
    {
      setValue(PROPERTYVALIDATOR, "");
    }
    else
    {
      setValue(PROPERTYVALIDATOR, value);
    }
  }
  
  public String getPropertyValue()
  {
    return getValue(PROPERTYVALUE);
  }
  
  public void validatePropertyValue()
  {
    this.validateAttribute(PROPERTYVALUE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPropertyValueMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(PROPERTYVALUE);
  }
  
  public void setPropertyValue(String value)
  {
    if(value == null)
    {
      setValue(PROPERTYVALUE, "");
    }
    else
    {
      setValue(PROPERTYVALUE, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  public String getValidValues()
  {
    return getValue(VALIDVALUES);
  }
  
  public void validateValidValues()
  {
    this.validateAttribute(VALIDVALUES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getValidValuesMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.LocalProperty.CLASS);
    return mdClassIF.definesAttribute(VALIDVALUES);
  }
  
  public void setValidValues(String value)
  {
    if(value == null)
    {
      setValue(VALIDVALUES, "");
    }
    else
    {
      setValue(VALIDVALUES, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LocalPropertyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LocalPropertyQuery query = new LocalPropertyQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static LocalProperty get(String id)
  {
    return (LocalProperty) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static LocalProperty getByKey(String key)
  {
    return (LocalProperty) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.LocalPropertyQuery getAllByPackage(java.lang.String pkg)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.LocalProperty.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.Property getByPackageAndName(java.lang.String pkg, java.lang.String name)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.LocalProperty.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.Integer getInt(java.lang.String pkg, java.lang.String name)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.LocalProperty.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String getNextId()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.LocalProperty.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static LocalProperty lock(java.lang.String id)
  {
    LocalProperty _instance = LocalProperty.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static LocalProperty unlock(java.lang.String id)
  {
    LocalProperty _instance = LocalProperty.get(id);
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
