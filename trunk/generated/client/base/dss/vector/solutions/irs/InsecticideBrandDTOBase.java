package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 631874780)
public abstract class InsecticideBrandDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrand";
  private static final long serialVersionUID = 631874780;
  
  protected InsecticideBrandDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected InsecticideBrandDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
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
  public dss.vector.solutions.ontology.TermDTO getActiveIngredient()
  {
    if(getValue(ACTIVEINGREDIENT) == null || getValue(ACTIVEINGREDIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ACTIVEINGREDIENT));
    }
  }
  
  public String getActiveIngredientId()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(dss.vector.solutions.ontology.TermDTO value)
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
  
  public boolean isActiveIngredientWritable()
  {
    return isWritable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientReadable()
  {
    return isReadable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientModified()
  {
    return isModified(ACTIVEINGREDIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getActiveIngredientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO> getConcentrationQualifier()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO.CLASS, getEnumNames(CONCENTRATIONQUALIFIER));
  }
  
  public java.util.List<String> getConcentrationQualifierEnumNames()
  {
    return getEnumNames(CONCENTRATIONQUALIFIER);
  }
  
  public void addConcentrationQualifier(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO enumDTO)
  {
    addEnumItem(CONCENTRATIONQUALIFIER, enumDTO.toString());
  }
  
  public void removeConcentrationQualifier(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO enumDTO)
  {
    removeEnumItem(CONCENTRATIONQUALIFIER, enumDTO.toString());
  }
  
  public void clearConcentrationQualifier()
  {
    clearEnum(CONCENTRATIONQUALIFIER);
  }
  
  public boolean isConcentrationQualifierWritable()
  {
    return isWritable(CONCENTRATIONQUALIFIER);
  }
  
  public boolean isConcentrationQualifierReadable()
  {
    return isReadable(CONCENTRATIONQUALIFIER);
  }
  
  public boolean isConcentrationQualifierModified()
  {
    return isModified(CONCENTRATIONQUALIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getConcentrationQualifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(CONCENTRATIONQUALIFIER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getConcentrationQuantifier()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CONCENTRATIONQUANTIFIER));
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
  
  public boolean isConcentrationQuantifierWritable()
  {
    return isWritable(CONCENTRATIONQUANTIFIER);
  }
  
  public boolean isConcentrationQuantifierReadable()
  {
    return isReadable(CONCENTRATIONQUANTIFIER);
  }
  
  public boolean isConcentrationQuantifierModified()
  {
    return isModified(CONCENTRATIONQUANTIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getConcentrationQuantifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CONCENTRATIONQUANTIFIER).getAttributeMdDTO();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public boolean isCreateDateWritable()
  {
    return isWritable(CREATEDATE);
  }
  
  public boolean isCreateDateReadable()
  {
    return isReadable(CREATEDATE);
  }
  
  public boolean isCreateDateModified()
  {
    return isModified(CREATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public boolean isCreatedByWritable()
  {
    return isWritable(CREATEDBY);
  }
  
  public boolean isCreatedByReadable()
  {
    return isReadable(CREATEDBY);
  }
  
  public boolean isCreatedByModified()
  {
    return isModified(CREATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.DiseaseDTO getDisease()
  {
    if(getValue(DISEASE) == null || getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.DiseaseDTO.get(getRequest(), getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.DiseaseDTO value)
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
  
  public boolean isDiseaseWritable()
  {
    return isWritable(DISEASE);
  }
  
  public boolean isDiseaseReadable()
  {
    return isReadable(DISEASE);
  }
  
  public boolean isDiseaseModified()
  {
    return isModified(DISEASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
  }
  
  public Boolean getEnabled()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLED));
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
  
  public boolean isEnabledWritable()
  {
    return isWritable(ENABLED);
  }
  
  public boolean isEnabledReadable()
  {
    return isReadable(ENABLED);
  }
  
  public boolean isEnabledModified()
  {
    return isModified(ENABLED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnabledMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLED).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomainDTO value)
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
  
  public boolean isEntityDomainWritable()
  {
    return isWritable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainReadable()
  {
    return isReadable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainModified()
  {
    return isModified(ENTITYDOMAIN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandUseDTO> getInsecticideUse()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandUseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.InsecticideBrandUseDTO.CLASS, getEnumNames(INSECTICIDEUSE));
  }
  
  public java.util.List<String> getInsecticideUseEnumNames()
  {
    return getEnumNames(INSECTICIDEUSE);
  }
  
  public void addInsecticideUse(dss.vector.solutions.irs.InsecticideBrandUseDTO enumDTO)
  {
    addEnumItem(INSECTICIDEUSE, enumDTO.toString());
  }
  
  public void removeInsecticideUse(dss.vector.solutions.irs.InsecticideBrandUseDTO enumDTO)
  {
    removeEnumItem(INSECTICIDEUSE, enumDTO.toString());
  }
  
  public void clearInsecticideUse()
  {
    clearEnum(INSECTICIDEUSE);
  }
  
  public boolean isInsecticideUseWritable()
  {
    return isWritable(INSECTICIDEUSE);
  }
  
  public boolean isInsecticideUseReadable()
  {
    return isReadable(INSECTICIDEUSE);
  }
  
  public boolean isInsecticideUseModified()
  {
    return isModified(INSECTICIDEUSE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getInsecticideUseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(INSECTICIDEUSE).getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
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
  
  public boolean isKeyNameWritable()
  {
    return isWritable(KEYNAME);
  }
  
  public boolean isKeyNameReadable()
  {
    return isReadable(KEYNAME);
  }
  
  public boolean isKeyNameModified()
  {
    return isModified(KEYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public boolean isLastUpdateDateWritable()
  {
    return isWritable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateReadable()
  {
    return isReadable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateModified()
  {
    return isModified(LASTUPDATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByWritable()
  {
    return isWritable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByReadable()
  {
    return isReadable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByModified()
  {
    return isModified(LASTUPDATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public boolean isLockedByWritable()
  {
    return isWritable(LOCKEDBY);
  }
  
  public boolean isLockedByReadable()
  {
    return isReadable(LOCKEDBY);
  }
  
  public boolean isLockedByModified()
  {
    return isModified(LOCKEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.ActorDTO value)
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
  
  public boolean isOwnerWritable()
  {
    return isWritable(OWNER);
  }
  
  public boolean isOwnerReadable()
  {
    return isReadable(OWNER);
  }
  
  public boolean isOwnerModified()
  {
    return isModified(OWNER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getProductName()
  {
    if(getValue(PRODUCTNAME) == null || getValue(PRODUCTNAME).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PRODUCTNAME));
    }
  }
  
  public String getProductNameId()
  {
    return getValue(PRODUCTNAME);
  }
  
  public void setProductName(dss.vector.solutions.ontology.TermDTO value)
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
  
  public boolean isProductNameWritable()
  {
    return isWritable(PRODUCTNAME);
  }
  
  public boolean isProductNameReadable()
  {
    return isReadable(PRODUCTNAME);
  }
  
  public boolean isProductNameModified()
  {
    return isModified(PRODUCTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getProductNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PRODUCTNAME).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public boolean isSeqWritable()
  {
    return isWritable(SEQ);
  }
  
  public boolean isSeqReadable()
  {
    return isReadable(SEQ);
  }
  
  public boolean isSeqModified()
  {
    return isModified(SEQ);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public boolean isSiteMasterWritable()
  {
    return isWritable(SITEMASTER);
  }
  
  public boolean isSiteMasterReadable()
  {
    return isReadable(SITEMASTER);
  }
  
  public boolean isSiteMasterModified()
  {
    return isModified(SITEMASTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO> getUnitQualifier()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO.CLASS, getEnumNames(UNITQUALIFIER));
  }
  
  public java.util.List<String> getUnitQualifierEnumNames()
  {
    return getEnumNames(UNITQUALIFIER);
  }
  
  public void addUnitQualifier(dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO enumDTO)
  {
    addEnumItem(UNITQUALIFIER, enumDTO.toString());
  }
  
  public void removeUnitQualifier(dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO enumDTO)
  {
    removeEnumItem(UNITQUALIFIER, enumDTO.toString());
  }
  
  public void clearUnitQualifier()
  {
    clearEnum(UNITQUALIFIER);
  }
  
  public boolean isUnitQualifierWritable()
  {
    return isWritable(UNITQUALIFIER);
  }
  
  public boolean isUnitQualifierReadable()
  {
    return isReadable(UNITQUALIFIER);
  }
  
  public boolean isUnitQualifierModified()
  {
    return isModified(UNITQUALIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getUnitQualifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(UNITQUALIFIER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getUnitQuantifier()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(UNITQUANTIFIER));
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
  
  public boolean isUnitQuantifierWritable()
  {
    return isWritable(UNITQUANTIFIER);
  }
  
  public boolean isUnitQuantifierReadable()
  {
    return isReadable(UNITQUANTIFIER);
  }
  
  public boolean isUnitQuantifierModified()
  {
    return isModified(UNITQUANTIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getUnitQuantifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(UNITQUANTIFIER).getAttributeMdDTO();
  }
  
  public Integer getUnitsPerApplication()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSPERAPPLICATION));
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
  
  public boolean isUnitsPerApplicationWritable()
  {
    return isWritable(UNITSPERAPPLICATION);
  }
  
  public boolean isUnitsPerApplicationReadable()
  {
    return isReadable(UNITSPERAPPLICATION);
  }
  
  public boolean isUnitsPerApplicationModified()
  {
    return isModified(UNITSPERAPPLICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUnitsPerApplicationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNITSPERAPPLICATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getUseDetail()
  {
    if(getValue(USEDETAIL) == null || getValue(USEDETAIL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(USEDETAIL));
    }
  }
  
  public String getUseDetailId()
  {
    return getValue(USEDETAIL);
  }
  
  public void setUseDetail(dss.vector.solutions.ontology.TermDTO value)
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
  
  public boolean isUseDetailWritable()
  {
    return isWritable(USEDETAIL);
  }
  
  public boolean isUseDetailReadable()
  {
    return isReadable(USEDETAIL);
  }
  
  public boolean isUseDetailModified()
  {
    return isModified(USEDETAIL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getUseDetailMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(USEDETAIL).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandDTO[] getAll(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.NozzleDTO> getAllNozzles()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.NozzleDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.irs.InsecticideNozzleDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.NozzleDTO> getAllNozzles(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.NozzleDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.irs.InsecticideNozzleDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.InsecticideNozzleDTO> getAllNozzlesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InsecticideNozzleDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.irs.InsecticideNozzleDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.InsecticideNozzleDTO> getAllNozzlesRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InsecticideNozzleDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.irs.InsecticideNozzleDTO.CLASS);
  }
  
  public dss.vector.solutions.irs.InsecticideNozzleDTO addNozzles(dss.vector.solutions.irs.NozzleDTO child)
  {
    return (dss.vector.solutions.irs.InsecticideNozzleDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.irs.InsecticideNozzleDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.InsecticideNozzleDTO addNozzles(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.irs.NozzleDTO child)
  {
    return (dss.vector.solutions.irs.InsecticideNozzleDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.irs.InsecticideNozzleDTO.CLASS);
  }
  
  public void removeNozzles(dss.vector.solutions.irs.InsecticideNozzleDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeNozzles(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.irs.InsecticideNozzleDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllNozzles()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.irs.InsecticideNozzleDTO.CLASS);
  }
  
  public static void removeAllNozzles(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.irs.InsecticideNozzleDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.InsecticideBrandDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.InsecticideBrandQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.irs.InsecticideBrandDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.InsecticideBrandDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
