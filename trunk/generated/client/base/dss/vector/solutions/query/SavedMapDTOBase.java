package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 756001465)
public abstract class SavedMapDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.SavedMap";
  private static final long serialVersionUID = 756001465;
  
  protected SavedMapDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SavedMapDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MAPNAME = "mapName";
  public static java.lang.String OWNER = "owner";
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
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public String getMapName()
  {
    return getValue(MAPNAME);
  }
  
  public void setMapName(String value)
  {
    if(value == null)
    {
      setValue(MAPNAME, "");
    }
    else
    {
      setValue(MAPNAME, value);
    }
  }
  
  public boolean isMapNameWritable()
  {
    return isWritable(MAPNAME);
  }
  
  public boolean isMapNameReadable()
  {
    return isReadable(MAPNAME);
  }
  
  public boolean isMapNameModified()
  {
    return isModified(MAPNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMapNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MAPNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.query.LayerViewDTO addLayerToMap(java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "addLayerToMap", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.LayerViewDTO addLayerToMap(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "addLayerToMap", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void cleanOldViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "cleanOldViews", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteLayerFromMap(java.lang.String layerId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{layerId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "deleteLayerFromMap", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteLayerFromMap(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String layerId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, layerId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "deleteLayerFromMap", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.LayerViewQueryDTO getAllLayers()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getAllLayers", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.LayerViewQueryDTO getAllLayers(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getAllLayers", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedMapQueryDTO getAllSavedMaps(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getAllSavedMaps", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedMapDTO loadDefaultMap(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.query.SavedMapDTO savedMap)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.SavedMap"};
    Object[] _parameters = new Object[]{savedMap};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "loadDefaultMap", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedMapDTO loadMap(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String mapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{mapId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "loadMap", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void moveLayerOnMap(java.lang.String layerId, java.lang.Integer layerPosition)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer"};
    Object[] _parameters = new Object[]{layerId, layerPosition};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "moveLayerOnMap", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void moveLayerOnMap(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String layerId, java.lang.Integer layerPosition)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, layerId, layerPosition};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "moveLayerOnMap", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.LayerDTO> getAllLayer()
  {
    return (java.util.List<? extends dss.vector.solutions.query.LayerDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.LayerDTO> getAllLayer(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.LayerDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasLayersDTO> getAllLayerRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasLayersDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasLayersDTO> getAllLayerRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasLayersDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasLayersDTO addLayer(dss.vector.solutions.query.LayerDTO child)
  {
    return (dss.vector.solutions.query.HasLayersDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasLayersDTO addLayer(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.LayerDTO child)
  {
    return (dss.vector.solutions.query.HasLayersDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public void removeLayer(dss.vector.solutions.query.HasLayersDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeLayer(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasLayersDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllLayer()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static void removeAllLayer(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.SavedMapDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.SavedMapDTO) dto;
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
  
  public static dss.vector.solutions.query.SavedMapQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.SavedMapQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.SavedMap", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.SavedMapDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.SavedMapDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
