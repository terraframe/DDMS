package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 725338948)
public abstract class PropertyDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.Property";
  private static final long serialVersionUID = 725338948;
  
  protected PropertyDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PropertyDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISEASE = "disease";
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
  
  public dss.vector.solutions.PropertyDescriptionDTO getDescription()
  {
    return (dss.vector.solutions.PropertyDescriptionDTO) this.getAttributeStructDTO(DESCRIPTION).getStructDTO();
  }
  
  public boolean isDescriptionWritable()
  {
    return isWritable(DESCRIPTION);
  }
  
  public boolean isDescriptionReadable()
  {
    return isReadable(DESCRIPTION);
  }
  
  public boolean isDescriptionModified()
  {
    return isModified(DESCRIPTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalMdDTO getDescriptionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalMdDTO) getAttributeDTO(DESCRIPTION).getAttributeMdDTO();
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
  
  public dss.vector.solutions.PropertyDisplayLabelDTO getDisplayLabel()
  {
    return (dss.vector.solutions.PropertyDisplayLabelDTO) this.getAttributeStructDTO(DISPLAYLABEL).getStructDTO();
  }
  
  public boolean isDisplayLabelWritable()
  {
    return isWritable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelReadable()
  {
    return isReadable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelModified()
  {
    return isModified(DISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalMdDTO getDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalMdDTO) getAttributeDTO(DISPLAYLABEL).getAttributeMdDTO();
  }
  
  public Boolean getEditable()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(EDITABLE));
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
  
  public boolean isEditableWritable()
  {
    return isWritable(EDITABLE);
  }
  
  public boolean isEditableReadable()
  {
    return isReadable(EDITABLE);
  }
  
  public boolean isEditableModified()
  {
    return isModified(EDITABLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEditableMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(EDITABLE).getAttributeMdDTO();
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
  
  public com.runwaysdk.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
    }
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
  
  public String getPropertyName()
  {
    return getValue(PROPERTYNAME);
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
  
  public boolean isPropertyNameWritable()
  {
    return isWritable(PROPERTYNAME);
  }
  
  public boolean isPropertyNameReadable()
  {
    return isReadable(PROPERTYNAME);
  }
  
  public boolean isPropertyNameModified()
  {
    return isModified(PROPERTYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPropertyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PROPERTYNAME).getAttributeMdDTO();
  }
  
  public String getPropertyPackage()
  {
    return getValue(PROPERTYPACKAGE);
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
  
  public boolean isPropertyPackageWritable()
  {
    return isWritable(PROPERTYPACKAGE);
  }
  
  public boolean isPropertyPackageReadable()
  {
    return isReadable(PROPERTYPACKAGE);
  }
  
  public boolean isPropertyPackageModified()
  {
    return isModified(PROPERTYPACKAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPropertyPackageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PROPERTYPACKAGE).getAttributeMdDTO();
  }
  
  public String getPropertyType()
  {
    return getValue(PROPERTYTYPE);
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
  
  public boolean isPropertyTypeWritable()
  {
    return isWritable(PROPERTYTYPE);
  }
  
  public boolean isPropertyTypeReadable()
  {
    return isReadable(PROPERTYTYPE);
  }
  
  public boolean isPropertyTypeModified()
  {
    return isModified(PROPERTYTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPropertyTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PROPERTYTYPE).getAttributeMdDTO();
  }
  
  public String getPropertyValidator()
  {
    return getValue(PROPERTYVALIDATOR);
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
  
  public boolean isPropertyValidatorWritable()
  {
    return isWritable(PROPERTYVALIDATOR);
  }
  
  public boolean isPropertyValidatorReadable()
  {
    return isReadable(PROPERTYVALIDATOR);
  }
  
  public boolean isPropertyValidatorModified()
  {
    return isModified(PROPERTYVALIDATOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPropertyValidatorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PROPERTYVALIDATOR).getAttributeMdDTO();
  }
  
  public String getPropertyValue()
  {
    return getValue(PROPERTYVALUE);
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
  
  public boolean isPropertyValueWritable()
  {
    return isWritable(PROPERTYVALUE);
  }
  
  public boolean isPropertyValueReadable()
  {
    return isReadable(PROPERTYVALUE);
  }
  
  public boolean isPropertyValueModified()
  {
    return isModified(PROPERTYVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPropertyValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PROPERTYVALUE).getAttributeMdDTO();
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
  
  public String getValidValues()
  {
    return getValue(VALIDVALUES);
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
  
  public boolean isValidValuesWritable()
  {
    return isWritable(VALIDVALUES);
  }
  
  public boolean isValidValuesReadable()
  {
    return isReadable(VALIDVALUES);
  }
  
  public boolean isValidValuesModified()
  {
    return isModified(VALIDVALUES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getValidValuesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VALIDVALUES).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.PropertyQueryDTO getAllByPackage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String pkg)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{pkg};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "getAllByPackage", _declaredTypes);
    return (dss.vector.solutions.PropertyQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.PropertyQueryDTO getAllEditable(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "getAllEditable", _declaredTypes);
    return (dss.vector.solutions.PropertyQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.PropertyDTO getByPackageAndName(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String pkg, java.lang.String name)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{pkg, name};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "getByPackageAndName", _declaredTypes);
    return (dss.vector.solutions.PropertyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date getDate(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String pkg, java.lang.String name)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{pkg, name};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "getDate", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.Integer getInt(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String pkg, java.lang.String name)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{pkg, name};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "getInt", _declaredTypes);
    return (java.lang.Integer) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getStr(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String pkg, java.lang.String name)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{pkg, name};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "getStr", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void setUnitsPerDay(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Integer unitsPerDay)
  {
    String[] _declaredTypes = new String[]{"java.lang.Integer"};
    Object[] _parameters = new Object[]{unitsPerDay};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "setUnitsPerDay", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.PropertyDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.PropertyDTO) dto;
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
  
  public static dss.vector.solutions.PropertyQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.PropertyQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.PropertyDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.PropertyDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.PropertyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.PropertyDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PropertyDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.PropertyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
