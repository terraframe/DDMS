package dss.vector.solutions.geo;

public abstract class GeoHierarchyDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238826351723L;
  
  public final static String CLASS = "dss.vector.solutions.geo.GeoHierarchy";
  protected GeoHierarchyDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GeoHierarchyDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String GEOENTITYCLASS = "geoEntityClass";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public java.util.Date getCreateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO("createDate").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("createdBy").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public void setEntityDomain(com.terraframe.mojo.system.metadata.MdDomainDTO value)
  {
    setValue(ENTITYDOMAIN, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("entityDomain").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.metadata.MdBusinessDTO getGeoEntityClass()
  {
    if(getValue(GEOENTITYCLASS) == null || getValue(GEOENTITYCLASS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdBusinessDTO.get(getRequest(), getValue(GEOENTITYCLASS));
    }
  }
  
  public void setGeoEntityClass(com.terraframe.mojo.system.metadata.MdBusinessDTO value)
  {
    setValue(GEOENTITYCLASS, value.getId());
  }
  
  public boolean isGeoEntityClassWritable()
  {
    return isWritable(GEOENTITYCLASS);
  }
  
  public boolean isGeoEntityClassReadable()
  {
    return isReadable(GEOENTITYCLASS);
  }
  
  public boolean isGeoEntityClassModified()
  {
    return isModified(GEOENTITYCLASS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityClassMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("geoEntityClass").getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("keyName").getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO("lastUpdateDate").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("lastUpdatedBy").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("lockedBy").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public void setOwner(com.terraframe.mojo.system.ActorDTO value)
  {
    setValue(OWNER, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("owner").getAttributeMdDTO();
  }
  
  public Boolean getPolitical()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POLITICAL));
  }
  
  public void setPolitical(Boolean value)
  {
    if(value == null)
    {
      setValue(POLITICAL, "");
    }
    else
    {
      setValue(POLITICAL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPoliticalWritable()
  {
    return isWritable(POLITICAL);
  }
  
  public boolean isPoliticalReadable()
  {
    return isReadable(POLITICAL);
  }
  
  public boolean isPoliticalModified()
  {
    return isModified(POLITICAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPoliticalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("political").getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("seq").getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("siteMaster").getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.geo.GeoHierarchyViewDTO getEarthGeoHierarchy(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "getEarthGeoHierarchy", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoHierarchyViewDTO[] getPoliticalGeoHierarchies(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoEntityId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoEntityId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "getPoliticalGeoHierarchies", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void updateFromView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.GeoHierarchyViewDTO view)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.GeoHierarchyView"};
    Object[] _parameters = new Object[]{view};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "updateFromView", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String defineAllowedTree(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoEntityId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoEntityId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "defineAllowedTree", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String defineGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.GeoEntityDefinitionDTO definition)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.GeoEntityDefinition"};
    Object[] _parameters = new Object[]{definition};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "defineGeoEntity", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoHierarchyViewQueryDTO getGeoEntityHierarchyViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean ascending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, ascending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "getGeoEntityHierarchyViews", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void deleteGeoHierarchy(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoHierarchyId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoHierarchyId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "deleteGeoHierarchy", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.geo.GeoHierarchyViewDTO getViewForGeoHierarchy()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "getViewForGeoHierarchy", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoHierarchyViewDTO getViewForGeoHierarchy(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "getViewForGeoHierarchy", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.geo.GeoHierarchyViewQueryDTO getOrderedChildren()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "getOrderedChildren", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoHierarchyViewQueryDTO getOrderedChildren(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "getOrderedChildren", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void applyExistingWithParent(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String childGeoHierarchyId, java.lang.String parentGeoHierarchyId, java.lang.Boolean cloneOperation)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{childGeoHierarchyId, parentGeoHierarchyId, cloneOperation};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "applyExistingWithParent", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO> getAllAcceptsGeoEntity()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO> getAllAcceptsGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.AllowedInDTO> getAllAcceptsGeoEntityRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.AllowedInDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.AllowedInDTO> getAllAcceptsGeoEntityRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.AllowedInDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public dss.vector.solutions.geo.AllowedInDTO addAcceptsGeoEntity(dss.vector.solutions.geo.GeoHierarchyDTO child)
  {
    return (dss.vector.solutions.geo.AllowedInDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.AllowedInDTO addAcceptsGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.geo.GeoHierarchyDTO child)
  {
    return (dss.vector.solutions.geo.AllowedInDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public void removeAcceptsGeoEntity(dss.vector.solutions.geo.AllowedInDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeAcceptsGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.geo.AllowedInDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllAcceptsGeoEntity()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public static void removeAllAcceptsGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO> getAllAllowedInGeoEntity()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO> getAllAllowedInGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO>) clientRequestIF.getParents(id, dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.AllowedInDTO> getAllAllowedInGeoEntityRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.AllowedInDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.AllowedInDTO> getAllAllowedInGeoEntityRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.AllowedInDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public dss.vector.solutions.geo.AllowedInDTO addAllowedInGeoEntity(dss.vector.solutions.geo.GeoHierarchyDTO parent)
  {
    return (dss.vector.solutions.geo.AllowedInDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.AllowedInDTO addAllowedInGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.geo.GeoHierarchyDTO parent)
  {
    return (dss.vector.solutions.geo.AllowedInDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public void removeAllowedInGeoEntity(dss.vector.solutions.geo.AllowedInDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAllowedInGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.geo.AllowedInDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAllowedInGeoEntity()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public static void removeAllAllowedInGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.geo.AllowedInDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.GeoHierarchyDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.GeoHierarchyDTO) dto;
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
  
  public static dss.vector.solutions.geo.GeoHierarchyQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.GeoHierarchyQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.geo.GeoHierarchy", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.GeoHierarchyDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.GeoHierarchyDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.GeoHierarchyDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
