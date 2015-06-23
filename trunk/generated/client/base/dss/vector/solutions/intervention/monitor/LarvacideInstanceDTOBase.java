package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 924178317)
public abstract class LarvacideInstanceDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.LarvacideInstance";
  private static final long serialVersionUID = 924178317;
  
  protected LarvacideInstanceDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LarvacideInstanceDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONTROLMETHOD = "controlMethod";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SUBSTANCE = "substance";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TREATED = "treated";
  public static java.lang.String TYPE = "type";
  public static java.lang.String UNIT = "unit";
  public static java.lang.String UNITSUSED = "unitsUsed";
  public dss.vector.solutions.ontology.TermDTO getControlMethod()
  {
    if(getValue(CONTROLMETHOD) == null || getValue(CONTROLMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CONTROLMETHOD));
    }
  }
  
  public String getControlMethodId()
  {
    return getValue(CONTROLMETHOD);
  }
  
  public void setControlMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CONTROLMETHOD, "");
    }
    else
    {
      setValue(CONTROLMETHOD, value.getId());
    }
  }
  
  public boolean isControlMethodWritable()
  {
    return isWritable(CONTROLMETHOD);
  }
  
  public boolean isControlMethodReadable()
  {
    return isReadable(CONTROLMETHOD);
  }
  
  public boolean isControlMethodModified()
  {
    return isModified(CONTROLMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getControlMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CONTROLMETHOD).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getSubstance()
  {
    if(getValue(SUBSTANCE) == null || getValue(SUBSTANCE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SUBSTANCE));
    }
  }
  
  public String getSubstanceId()
  {
    return getValue(SUBSTANCE);
  }
  
  public void setSubstance(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SUBSTANCE, "");
    }
    else
    {
      setValue(SUBSTANCE, value.getId());
    }
  }
  
  public boolean isSubstanceWritable()
  {
    return isWritable(SUBSTANCE);
  }
  
  public boolean isSubstanceReadable()
  {
    return isReadable(SUBSTANCE);
  }
  
  public boolean isSubstanceModified()
  {
    return isModified(SUBSTANCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSubstanceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SUBSTANCE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTarget()
  {
    if(getValue(TARGET) == null || getValue(TARGET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TARGET));
    }
  }
  
  public String getTargetId()
  {
    return getValue(TARGET);
  }
  
  public void setTarget(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value.getId());
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTargetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public Integer getTreated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TREATED));
  }
  
  public void setTreated(Integer value)
  {
    if(value == null)
    {
      setValue(TREATED, "");
    }
    else
    {
      setValue(TREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTreatedWritable()
  {
    return isWritable(TREATED);
  }
  
  public boolean isTreatedReadable()
  {
    return isReadable(TREATED);
  }
  
  public boolean isTreatedModified()
  {
    return isModified(TREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TREATED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getUnit()
  {
    if(getValue(UNIT) == null || getValue(UNIT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(UNIT));
    }
  }
  
  public String getUnitId()
  {
    return getValue(UNIT);
  }
  
  public void setUnit(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(UNIT, "");
    }
    else
    {
      setValue(UNIT, value.getId());
    }
  }
  
  public boolean isUnitWritable()
  {
    return isWritable(UNIT);
  }
  
  public boolean isUnitReadable()
  {
    return isReadable(UNIT);
  }
  
  public boolean isUnitModified()
  {
    return isModified(UNIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getUnitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(UNIT).getAttributeMdDTO();
  }
  
  public Integer getUnitsUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSUSED));
  }
  
  public void setUnitsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSUSED, "");
    }
    else
    {
      setValue(UNITSUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnitsUsedWritable()
  {
    return isWritable(UNITSUSED);
  }
  
  public boolean isUnitsUsedReadable()
  {
    return isReadable(UNITSUSED);
  }
  
  public boolean isUnitsUsedModified()
  {
    return isModified(UNITSUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUnitsUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNITSUSED).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideDTO> getAllControlLarvacide()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideDTO> getAllControlLarvacide(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO> getAllControlLarvacideRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO> getAllControlLarvacideRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO addControlLarvacide(dss.vector.solutions.intervention.monitor.LarvacideDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO addControlLarvacide(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.LarvacideDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public void removeControlLarvacide(dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeControlLarvacide(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllControlLarvacide()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public static void removeAllControlLarvacide(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.LarvacideInstanceQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
