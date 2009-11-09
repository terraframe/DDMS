package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1078534281)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideBrand.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InsecticideBrandBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrand";
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String BRANDNAME = "brandName";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENABLED = "enabled";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SACHETSPERREFILL = "sachetsPerRefill";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WEIGHT = "weight";
  private static final long serialVersionUID = 1078534281;
  
  public InsecticideBrandBase()
  {
    super();
  }
  
  public dss.vector.solutions.ontology.Term getActiveIngredient()
  {
    if (getValue(ACTIVEINGREDIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(ACTIVEINGREDIENT));
    }
  }
  
  public void validateActiveIngredient()
  {
    this.validateAttribute(ACTIVEINGREDIENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getActiveIngredientMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return mdClassIF.definesAttribute(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(ACTIVEINGREDIENT, "");
    }
    else
    {
      setValue(ACTIVEINGREDIENT, value.getId());
    }
  }
  
  public Integer getAmount()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNT));
  }
  
  public void validateAmount()
  {
    this.validateAttribute(AMOUNT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAmountMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return mdClassIF.definesAttribute(AMOUNT);
  }
  
  public void setAmount(Integer value)
  {
    if(value == null)
    {
      setValue(AMOUNT, "");
    }
    else
    {
      setValue(AMOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public String getBrandName()
  {
    return getValue(BRANDNAME);
  }
  
  public void validateBrandName()
  {
    this.validateAttribute(BRANDNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getBrandNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return mdClassIF.definesAttribute(BRANDNAME);
  }
  
  public void setBrandName(String value)
  {
    if(value == null)
    {
      setValue(BRANDNAME, "");
    }
    else
    {
      setValue(BRANDNAME, value);
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
  
  public Integer getSachetsPerRefill()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SACHETSPERREFILL));
  }
  
  public void validateSachetsPerRefill()
  {
    this.validateAttribute(SACHETSPERREFILL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSachetsPerRefillMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return mdClassIF.definesAttribute(SACHETSPERREFILL);
  }
  
  public void setSachetsPerRefill(Integer value)
  {
    if(value == null)
    {
      setValue(SACHETSPERREFILL, "");
    }
    else
    {
      setValue(SACHETSPERREFILL, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  public java.math.BigDecimal getWeight()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(WEIGHT));
  }
  
  public void validateWeight()
  {
    this.validateAttribute(WEIGHT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWeightMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return mdClassIF.definesAttribute(WEIGHT);
  }
  
  public void setWeight(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(WEIGHT, "");
    }
    else
    {
      setValue(WEIGHT, value.toString());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static InsecticideBrandQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    InsecticideBrandQuery query = new InsecticideBrandQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.irs.InsecticideNozzle addNozzles(dss.vector.solutions.irs.Nozzle nozzle)
  {
    return (dss.vector.solutions.irs.InsecticideNozzle) addChild(nozzle, dss.vector.solutions.irs.InsecticideNozzle.CLASS);
  }
  
  public void removeNozzles(dss.vector.solutions.irs.Nozzle nozzle)
  {
    removeAllChildren(nozzle, dss.vector.solutions.irs.InsecticideNozzle.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.Nozzle> getAllNozzles()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.Nozzle>) getChildren(dss.vector.solutions.irs.InsecticideNozzle.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InsecticideNozzle> getAllNozzlesRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InsecticideNozzle>) getChildRelationships(dss.vector.solutions.irs.InsecticideNozzle.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InsecticideNozzle> getNozzlesRel(dss.vector.solutions.irs.Nozzle nozzle)
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.irs.InsecticideNozzle>) getRelationshipsWithChild(nozzle, dss.vector.solutions.irs.InsecticideNozzle.CLASS);
  }
  
  public static InsecticideBrand get(String id)
  {
    return (InsecticideBrand) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static InsecticideBrand getByKey(String key)
  {
    return (InsecticideBrand) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrand[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideBrand.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideBrand.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandView lockView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideBrand.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandView unlockView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideBrand.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static InsecticideBrand lock(java.lang.String id)
  {
    InsecticideBrand _instance = InsecticideBrand.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static InsecticideBrand unlock(java.lang.String id)
  {
    InsecticideBrand _instance = InsecticideBrand.get(id);
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
