package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -943750591)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideBrand.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InsecticideBrandBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrand";
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String CONCENTRATIONQUALIFIER = "concentrationQualifier";
  public static java.lang.String CONCENTRATIONQUANTIFIER = "concentrationQuantifier";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENABLED = "enabled";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDEUSE = "insecticideUse";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PRODUCTNAME = "productName";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String UNITQUALIFIER = "unitQualifier";
  public static java.lang.String UNITQUANTIFIER = "unitQuantifier";
  public static java.lang.String UNITSPERAPPLICATION = "unitsPerApplication";
  public static java.lang.String USEDETAIL = "useDetail";
  private static final long serialVersionUID = -943750591;
  
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
  
  public String getActiveIngredientId()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void validateActiveIngredient()
  {
    this.validateAttribute(ACTIVEINGREDIENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getActiveIngredientMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(ACTIVEINGREDIENT);
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier> getConcentrationQualifier()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier>) getEnumValues(CONCENTRATIONQUALIFIER);
  }
  
  public void addConcentrationQualifier(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier value)
  {
    if(value != null)
    {
      addEnumItem(CONCENTRATIONQUALIFIER, value.getId());
    }
  }
  
  public void removeConcentrationQualifier(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier value)
  {
    if(value != null)
    {
      removeEnumItem(CONCENTRATIONQUALIFIER, value.getId());
    }
  }
  
  public void clearConcentrationQualifier()
  {
    clearEnum(CONCENTRATIONQUALIFIER);
  }
  
  public void validateConcentrationQualifier()
  {
    this.validateAttribute(CONCENTRATIONQUALIFIER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF getConcentrationQualifierMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF)mdClassIF.definesAttribute(CONCENTRATIONQUALIFIER);
  }
  
  public java.math.BigDecimal getConcentrationQuantifier()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CONCENTRATIONQUANTIFIER));
  }
  
  public void validateConcentrationQuantifier()
  {
    this.validateAttribute(CONCENTRATIONQUANTIFIER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF getConcentrationQuantifierMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF)mdClassIF.definesAttribute(CONCENTRATIONQUANTIFIER);
  }
  
  public void setConcentrationQuantifier(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CONCENTRATIONQUANTIFIER, "");
    }
    else
    {
      setValue(CONCENTRATIONQUANTIFIER, value.toString());
    }
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCreatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public dss.vector.solutions.general.Disease getDisease()
  {
    if (getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.Disease.get(getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void validateDisease()
  {
    this.validateAttribute(DISEASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDiseaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.Disease value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
    }
  }
  
  public Boolean getEnabled()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLED));
  }
  
  public void validateEnabled()
  {
    this.validateAttribute(ENABLED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getEnabledMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ENABLED);
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
  
  public com.runwaysdk.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getEntityDomainMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomain value)
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandUse> getInsecticideUse()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandUse>) getEnumValues(INSECTICIDEUSE);
  }
  
  public void addInsecticideUse(dss.vector.solutions.irs.InsecticideBrandUse value)
  {
    if(value != null)
    {
      addEnumItem(INSECTICIDEUSE, value.getId());
    }
  }
  
  public void removeInsecticideUse(dss.vector.solutions.irs.InsecticideBrandUse value)
  {
    if(value != null)
    {
      removeEnumItem(INSECTICIDEUSE, value.getId());
    }
  }
  
  public void clearInsecticideUse()
  {
    clearEnum(INSECTICIDEUSE);
  }
  
  public void validateInsecticideUse()
  {
    this.validateAttribute(INSECTICIDEUSE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF getInsecticideUseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF)mdClassIF.definesAttribute(INSECTICIDEUSE);
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(KEYNAME);
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
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastUpdatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.runwaysdk.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Users.get(getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLockedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public com.runwaysdk.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Actor.get(getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getOwnerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.Actor value)
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
  
  public dss.vector.solutions.ontology.Term getProductName()
  {
    if (getValue(PRODUCTNAME).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(PRODUCTNAME));
    }
  }
  
  public String getProductNameId()
  {
    return getValue(PRODUCTNAME);
  }
  
  public void validateProductName()
  {
    this.validateAttribute(PRODUCTNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getProductNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(PRODUCTNAME);
  }
  
  public void setProductName(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(PRODUCTNAME, "");
    }
    else
    {
      setValue(PRODUCTNAME, value.getId());
    }
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLongDAOIF getSeqMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(SEQ);
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandUnitQualifier> getUnitQualifier()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandUnitQualifier>) getEnumValues(UNITQUALIFIER);
  }
  
  public void addUnitQualifier(dss.vector.solutions.irs.InsecticideBrandUnitQualifier value)
  {
    if(value != null)
    {
      addEnumItem(UNITQUALIFIER, value.getId());
    }
  }
  
  public void removeUnitQualifier(dss.vector.solutions.irs.InsecticideBrandUnitQualifier value)
  {
    if(value != null)
    {
      removeEnumItem(UNITQUALIFIER, value.getId());
    }
  }
  
  public void clearUnitQualifier()
  {
    clearEnum(UNITQUALIFIER);
  }
  
  public void validateUnitQualifier()
  {
    this.validateAttribute(UNITQUALIFIER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF getUnitQualifierMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF)mdClassIF.definesAttribute(UNITQUALIFIER);
  }
  
  public java.math.BigDecimal getUnitQuantifier()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(UNITQUANTIFIER));
  }
  
  public void validateUnitQuantifier()
  {
    this.validateAttribute(UNITQUANTIFIER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF getUnitQuantifierMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF)mdClassIF.definesAttribute(UNITQUANTIFIER);
  }
  
  public void setUnitQuantifier(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(UNITQUANTIFIER, "");
    }
    else
    {
      setValue(UNITQUANTIFIER, value.toString());
    }
  }
  
  public Integer getUnitsPerApplication()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSPERAPPLICATION));
  }
  
  public void validateUnitsPerApplication()
  {
    this.validateAttribute(UNITSPERAPPLICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getUnitsPerApplicationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(UNITSPERAPPLICATION);
  }
  
  public void setUnitsPerApplication(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSPERAPPLICATION, "");
    }
    else
    {
      setValue(UNITSPERAPPLICATION, java.lang.Integer.toString(value));
    }
  }
  
  public dss.vector.solutions.ontology.Term getUseDetail()
  {
    if (getValue(USEDETAIL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(USEDETAIL));
    }
  }
  
  public String getUseDetailId()
  {
    return getValue(USEDETAIL);
  }
  
  public void validateUseDetail()
  {
    this.validateAttribute(USEDETAIL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getUseDetailMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideBrand.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(USEDETAIL);
  }
  
  public void setUseDetail(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(USEDETAIL, "");
    }
    else
    {
      setValue(USEDETAIL, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static InsecticideBrandQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    InsecticideBrandQuery query = new InsecticideBrandQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
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
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.irs.Nozzle> getAllNozzles()
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.irs.Nozzle>) getChildren(dss.vector.solutions.irs.InsecticideNozzle.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.irs.InsecticideNozzle> getAllNozzlesRel()
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.irs.InsecticideNozzle>) getChildRelationships(dss.vector.solutions.irs.InsecticideNozzle.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.irs.InsecticideNozzle> getNozzlesRel(dss.vector.solutions.irs.Nozzle nozzle)
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.irs.InsecticideNozzle>) getRelationshipsWithChild(nozzle, dss.vector.solutions.irs.InsecticideNozzle.CLASS);
  }
  
  public static InsecticideBrand get(String id)
  {
    return (InsecticideBrand) com.runwaysdk.business.Business.get(id);
  }
  
  public static InsecticideBrand getByKey(String key)
  {
    return (InsecticideBrand) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrand[] getAll()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideBrand.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideBrand.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandView lockView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideBrand.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandView unlockView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideBrand.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
