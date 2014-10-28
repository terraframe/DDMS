package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -1069576325)
public abstract class GeneratedMapDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.GeneratedMap";
  private static final long serialVersionUID = -1069576325;
  
  protected GeneratedMapDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GeneratedMapDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String CYCLELAYERNAME = "cycleLayerName";
  public static java.lang.String CYCLEUNIVERSAL = "cycleUniversal";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FILTERGEOENTITYNAME = "filterGeoEntityName";
  public static java.lang.String FILTERGEOID = "filterGeoId";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MAPIMAGE = "mapImage";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SAVEDMAP = "savedMap";
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
  
  public String getCycleLayerName()
  {
    return getValue(CYCLELAYERNAME);
  }
  
  public void setCycleLayerName(String value)
  {
    if(value == null)
    {
      setValue(CYCLELAYERNAME, "");
    }
    else
    {
      setValue(CYCLELAYERNAME, value);
    }
  }
  
  public boolean isCycleLayerNameWritable()
  {
    return isWritable(CYCLELAYERNAME);
  }
  
  public boolean isCycleLayerNameReadable()
  {
    return isReadable(CYCLELAYERNAME);
  }
  
  public boolean isCycleLayerNameModified()
  {
    return isModified(CYCLELAYERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCycleLayerNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CYCLELAYERNAME).getAttributeMdDTO();
  }
  
  public String getCycleUniversal()
  {
    return getValue(CYCLEUNIVERSAL);
  }
  
  public void setCycleUniversal(String value)
  {
    if(value == null)
    {
      setValue(CYCLEUNIVERSAL, "");
    }
    else
    {
      setValue(CYCLEUNIVERSAL, value);
    }
  }
  
  public boolean isCycleUniversalWritable()
  {
    return isWritable(CYCLEUNIVERSAL);
  }
  
  public boolean isCycleUniversalReadable()
  {
    return isReadable(CYCLEUNIVERSAL);
  }
  
  public boolean isCycleUniversalModified()
  {
    return isModified(CYCLEUNIVERSAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCycleUniversalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CYCLEUNIVERSAL).getAttributeMdDTO();
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
  
  public String getFilterGeoEntityName()
  {
    return getValue(FILTERGEOENTITYNAME);
  }
  
  public void setFilterGeoEntityName(String value)
  {
    if(value == null)
    {
      setValue(FILTERGEOENTITYNAME, "");
    }
    else
    {
      setValue(FILTERGEOENTITYNAME, value);
    }
  }
  
  public boolean isFilterGeoEntityNameWritable()
  {
    return isWritable(FILTERGEOENTITYNAME);
  }
  
  public boolean isFilterGeoEntityNameReadable()
  {
    return isReadable(FILTERGEOENTITYNAME);
  }
  
  public boolean isFilterGeoEntityNameModified()
  {
    return isModified(FILTERGEOENTITYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFilterGeoEntityNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FILTERGEOENTITYNAME).getAttributeMdDTO();
  }
  
  public String getFilterGeoId()
  {
    return getValue(FILTERGEOID);
  }
  
  public void setFilterGeoId(String value)
  {
    if(value == null)
    {
      setValue(FILTERGEOID, "");
    }
    else
    {
      setValue(FILTERGEOID, value);
    }
  }
  
  public boolean isFilterGeoIdWritable()
  {
    return isWritable(FILTERGEOID);
  }
  
  public boolean isFilterGeoIdReadable()
  {
    return isReadable(FILTERGEOID);
  }
  
  public boolean isFilterGeoIdModified()
  {
    return isModified(FILTERGEOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFilterGeoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FILTERGEOID).getAttributeMdDTO();
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
  
  public byte[] getMapImage()
  {
    return super.getBlob(MAPIMAGE);
  }
  
  public void setMapImage(byte[] bytes)
  {
    super.setBlob(MAPIMAGE, bytes);
  }
  
  public boolean isMapImageWritable()
  {
    return isWritable(MAPIMAGE);
  }
  
  public boolean isMapImageReadable()
  {
    return isReadable(MAPIMAGE);
  }
  
  public boolean isMapImageModified()
  {
    return isModified(MAPIMAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBlobMdDTO getMapImageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBlobMdDTO) getAttributeDTO(MAPIMAGE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.query.SavedMapDTO getSavedMap()
  {
    if(getValue(SAVEDMAP) == null || getValue(SAVEDMAP).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.SavedMapDTO.get(getRequest(), getValue(SAVEDMAP));
    }
  }
  
  public String getSavedMapId()
  {
    return getValue(SAVEDMAP);
  }
  
  public void setSavedMap(dss.vector.solutions.query.SavedMapDTO value)
  {
    if(value == null)
    {
      setValue(SAVEDMAP, "");
    }
    else
    {
      setValue(SAVEDMAP, value.getId());
    }
  }
  
  public boolean isSavedMapWritable()
  {
    return isWritable(SAVEDMAP);
  }
  
  public boolean isSavedMapReadable()
  {
    return isReadable(SAVEDMAP);
  }
  
  public boolean isSavedMapModified()
  {
    return isModified(SAVEDMAP);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSavedMapMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SAVEDMAP).getAttributeMdDTO();
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
  
  public static dss.vector.solutions.query.GeneratedMapDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.GeneratedMapDTO) dto;
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
  
  public static dss.vector.solutions.query.GeneratedMapQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.GeneratedMapQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.GeneratedMapDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.GeneratedMapDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.GeneratedMapDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.GeneratedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.GeneratedMapDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.GeneratedMapDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.GeneratedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
