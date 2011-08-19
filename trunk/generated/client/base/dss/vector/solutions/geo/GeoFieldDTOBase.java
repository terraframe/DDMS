package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 1771637431)
public abstract class GeoFieldDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoField";
  private static final long serialVersionUID = 1771637431;
  
  protected GeoFieldDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GeoFieldDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FILTER = "filter";
  public static java.lang.String GEOATTRIBUTE = "geoAttribute";
  public static java.lang.String ID = "id";
  public static java.lang.String ISPOLITICALHIERARCHY = "isPoliticalHierarchy";
  public static java.lang.String ISPOPULATIONHIERARCHY = "isPopulationHierarchy";
  public static java.lang.String ISSPRAYHIERARCHY = "isSprayHierarchy";
  public static java.lang.String ISUNDERSYSTEMROOT = "isUnderSystemRoot";
  public static java.lang.String ISURBANHIERARCHY = "isUrbanHierarchy";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
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
  
  public dss.vector.solutions.geo.GeoHierarchyDTO getFilter()
  {
    if(getValue(FILTER) == null || getValue(FILTER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchyDTO.get(getRequest(), getValue(FILTER));
    }
  }
  
  public String getFilterId()
  {
    return getValue(FILTER);
  }
  
  public void setFilter(dss.vector.solutions.geo.GeoHierarchyDTO value)
  {
    if(value == null)
    {
      setValue(FILTER, "");
    }
    else
    {
      setValue(FILTER, value.getId());
    }
  }
  
  public boolean isFilterWritable()
  {
    return isWritable(FILTER);
  }
  
  public boolean isFilterReadable()
  {
    return isReadable(FILTER);
  }
  
  public boolean isFilterModified()
  {
    return isModified(FILTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getFilterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FILTER).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdAttributeReferenceDTO getGeoAttribute()
  {
    if(getValue(GEOATTRIBUTE) == null || getValue(GEOATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttributeReferenceDTO.get(getRequest(), getValue(GEOATTRIBUTE));
    }
  }
  
  public String getGeoAttributeId()
  {
    return getValue(GEOATTRIBUTE);
  }
  
  public void setGeoAttribute(com.runwaysdk.system.metadata.MdAttributeReferenceDTO value)
  {
    if(value == null)
    {
      setValue(GEOATTRIBUTE, "");
    }
    else
    {
      setValue(GEOATTRIBUTE, value.getId());
    }
  }
  
  public boolean isGeoAttributeWritable()
  {
    return isWritable(GEOATTRIBUTE);
  }
  
  public boolean isGeoAttributeReadable()
  {
    return isReadable(GEOATTRIBUTE);
  }
  
  public boolean isGeoAttributeModified()
  {
    return isModified(GEOATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOATTRIBUTE).getAttributeMdDTO();
  }
  
  public Boolean getIsPoliticalHierarchy()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISPOLITICALHIERARCHY));
  }
  
  public void setIsPoliticalHierarchy(Boolean value)
  {
    if(value == null)
    {
      setValue(ISPOLITICALHIERARCHY, "");
    }
    else
    {
      setValue(ISPOLITICALHIERARCHY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsPoliticalHierarchyWritable()
  {
    return isWritable(ISPOLITICALHIERARCHY);
  }
  
  public boolean isIsPoliticalHierarchyReadable()
  {
    return isReadable(ISPOLITICALHIERARCHY);
  }
  
  public boolean isIsPoliticalHierarchyModified()
  {
    return isModified(ISPOLITICALHIERARCHY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsPoliticalHierarchyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISPOLITICALHIERARCHY).getAttributeMdDTO();
  }
  
  public Boolean getIsPopulationHierarchy()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISPOPULATIONHIERARCHY));
  }
  
  public void setIsPopulationHierarchy(Boolean value)
  {
    if(value == null)
    {
      setValue(ISPOPULATIONHIERARCHY, "");
    }
    else
    {
      setValue(ISPOPULATIONHIERARCHY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsPopulationHierarchyWritable()
  {
    return isWritable(ISPOPULATIONHIERARCHY);
  }
  
  public boolean isIsPopulationHierarchyReadable()
  {
    return isReadable(ISPOPULATIONHIERARCHY);
  }
  
  public boolean isIsPopulationHierarchyModified()
  {
    return isModified(ISPOPULATIONHIERARCHY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsPopulationHierarchyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISPOPULATIONHIERARCHY).getAttributeMdDTO();
  }
  
  public Boolean getIsSprayHierarchy()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYHIERARCHY));
  }
  
  public void setIsSprayHierarchy(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSPRAYHIERARCHY, "");
    }
    else
    {
      setValue(ISSPRAYHIERARCHY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsSprayHierarchyWritable()
  {
    return isWritable(ISSPRAYHIERARCHY);
  }
  
  public boolean isIsSprayHierarchyReadable()
  {
    return isReadable(ISSPRAYHIERARCHY);
  }
  
  public boolean isIsSprayHierarchyModified()
  {
    return isModified(ISSPRAYHIERARCHY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsSprayHierarchyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSPRAYHIERARCHY).getAttributeMdDTO();
  }
  
  public Boolean getIsUnderSystemRoot()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISUNDERSYSTEMROOT));
  }
  
  public void setIsUnderSystemRoot(Boolean value)
  {
    if(value == null)
    {
      setValue(ISUNDERSYSTEMROOT, "");
    }
    else
    {
      setValue(ISUNDERSYSTEMROOT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsUnderSystemRootWritable()
  {
    return isWritable(ISUNDERSYSTEMROOT);
  }
  
  public boolean isIsUnderSystemRootReadable()
  {
    return isReadable(ISUNDERSYSTEMROOT);
  }
  
  public boolean isIsUnderSystemRootModified()
  {
    return isModified(ISUNDERSYSTEMROOT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsUnderSystemRootMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISUNDERSYSTEMROOT).getAttributeMdDTO();
  }
  
  public Boolean getIsUrbanHierarchy()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISURBANHIERARCHY));
  }
  
  public void setIsUrbanHierarchy(Boolean value)
  {
    if(value == null)
    {
      setValue(ISURBANHIERARCHY, "");
    }
    else
    {
      setValue(ISURBANHIERARCHY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsUrbanHierarchyWritable()
  {
    return isWritable(ISURBANHIERARCHY);
  }
  
  public boolean isIsUrbanHierarchyReadable()
  {
    return isReadable(ISURBANHIERARCHY);
  }
  
  public boolean isIsUrbanHierarchyModified()
  {
    return isModified(ISURBANHIERARCHY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsUrbanHierarchyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISURBANHIERARCHY).getAttributeMdDTO();
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
  
  public final java.lang.String[] getExtraUniversals()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoFieldDTO.CLASS, "getExtraUniversals", _declaredTypes);
    return (java.lang.String[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String[] getExtraUniversals(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoFieldDTO.CLASS, "getExtraUniversals", _declaredTypes);
    return (java.lang.String[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getFilterType()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoFieldDTO.CLASS, "getFilterType", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getFilterType(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoFieldDTO.CLASS, "getFilterType", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoFieldDTO getGeoField(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String klass, java.lang.String name)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{klass, name};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoFieldDTO.CLASS, "getGeoField", _declaredTypes);
    return (dss.vector.solutions.geo.GeoFieldDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO> getAllGeoHierarchies()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.geo.ExtraFieldUniversalDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO> getAllGeoHierarchies(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.geo.ExtraFieldUniversalDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.ExtraFieldUniversalDTO> getAllGeoHierarchiesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.ExtraFieldUniversalDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.geo.ExtraFieldUniversalDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.ExtraFieldUniversalDTO> getAllGeoHierarchiesRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.ExtraFieldUniversalDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.geo.ExtraFieldUniversalDTO.CLASS);
  }
  
  public dss.vector.solutions.geo.ExtraFieldUniversalDTO addGeoHierarchies(dss.vector.solutions.geo.GeoHierarchyDTO child)
  {
    return (dss.vector.solutions.geo.ExtraFieldUniversalDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.geo.ExtraFieldUniversalDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.ExtraFieldUniversalDTO addGeoHierarchies(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.geo.GeoHierarchyDTO child)
  {
    return (dss.vector.solutions.geo.ExtraFieldUniversalDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.geo.ExtraFieldUniversalDTO.CLASS);
  }
  
  public void removeGeoHierarchies(dss.vector.solutions.geo.ExtraFieldUniversalDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeGeoHierarchies(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.geo.ExtraFieldUniversalDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllGeoHierarchies()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.geo.ExtraFieldUniversalDTO.CLASS);
  }
  
  public static void removeAllGeoHierarchies(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.geo.ExtraFieldUniversalDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.GeoFieldDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.GeoFieldDTO) dto;
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
  
  public static dss.vector.solutions.geo.GeoFieldQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.GeoFieldQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.geo.GeoFieldDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.GeoFieldDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoFieldDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.GeoFieldDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.GeoFieldDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoFieldDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.GeoFieldDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
