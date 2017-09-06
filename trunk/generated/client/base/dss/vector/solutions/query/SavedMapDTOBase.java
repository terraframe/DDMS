package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -977455462)
public abstract class SavedMapDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.SavedMap";
  private static final long serialVersionUID = -977455462;
  
  protected SavedMapDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SavedMapDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MAPCENTER = "mapCenter";
  public static java.lang.String MAPHEIGHT = "mapHeight";
  public static java.lang.String MAPNAME = "mapName";
  public static java.lang.String MAPWIDTH = "mapWidth";
  public static java.lang.String NORTHARROWACTIVE = "northArrowActive";
  public static java.lang.String NORTHARROWXPOSITION = "northArrowXPosition";
  public static java.lang.String NORTHARROWYPOSITION = "northArrowYPosition";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SCALEBARACTIVE = "scaleBarActive";
  public static java.lang.String SCALEBARXPOSITION = "scaleBarXPosition";
  public static java.lang.String SCALEBARYPOSITION = "scaleBarYPosition";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String ZOOMLEVEL = "zoomLevel";
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
  
  public String getMapCenter()
  {
    return getValue(MAPCENTER);
  }
  
  public void setMapCenter(String value)
  {
    if(value == null)
    {
      setValue(MAPCENTER, "");
    }
    else
    {
      setValue(MAPCENTER, value);
    }
  }
  
  public boolean isMapCenterWritable()
  {
    return isWritable(MAPCENTER);
  }
  
  public boolean isMapCenterReadable()
  {
    return isReadable(MAPCENTER);
  }
  
  public boolean isMapCenterModified()
  {
    return isModified(MAPCENTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMapCenterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MAPCENTER).getAttributeMdDTO();
  }
  
  public Integer getMapHeight()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAPHEIGHT));
  }
  
  public void setMapHeight(Integer value)
  {
    if(value == null)
    {
      setValue(MAPHEIGHT, "");
    }
    else
    {
      setValue(MAPHEIGHT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMapHeightWritable()
  {
    return isWritable(MAPHEIGHT);
  }
  
  public boolean isMapHeightReadable()
  {
    return isReadable(MAPHEIGHT);
  }
  
  public boolean isMapHeightModified()
  {
    return isModified(MAPHEIGHT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getMapHeightMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MAPHEIGHT).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMapNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MAPNAME).getAttributeMdDTO();
  }
  
  public Integer getMapWidth()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAPWIDTH));
  }
  
  public void setMapWidth(Integer value)
  {
    if(value == null)
    {
      setValue(MAPWIDTH, "");
    }
    else
    {
      setValue(MAPWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMapWidthWritable()
  {
    return isWritable(MAPWIDTH);
  }
  
  public boolean isMapWidthReadable()
  {
    return isReadable(MAPWIDTH);
  }
  
  public boolean isMapWidthModified()
  {
    return isModified(MAPWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getMapWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MAPWIDTH).getAttributeMdDTO();
  }
  
  public Boolean getNorthArrowActive()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NORTHARROWACTIVE));
  }
  
  public void setNorthArrowActive(Boolean value)
  {
    if(value == null)
    {
      setValue(NORTHARROWACTIVE, "");
    }
    else
    {
      setValue(NORTHARROWACTIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNorthArrowActiveWritable()
  {
    return isWritable(NORTHARROWACTIVE);
  }
  
  public boolean isNorthArrowActiveReadable()
  {
    return isReadable(NORTHARROWACTIVE);
  }
  
  public boolean isNorthArrowActiveModified()
  {
    return isModified(NORTHARROWACTIVE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getNorthArrowActiveMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NORTHARROWACTIVE).getAttributeMdDTO();
  }
  
  public Integer getNorthArrowXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NORTHARROWXPOSITION));
  }
  
  public void setNorthArrowXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(NORTHARROWXPOSITION, "");
    }
    else
    {
      setValue(NORTHARROWXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNorthArrowXPositionWritable()
  {
    return isWritable(NORTHARROWXPOSITION);
  }
  
  public boolean isNorthArrowXPositionReadable()
  {
    return isReadable(NORTHARROWXPOSITION);
  }
  
  public boolean isNorthArrowXPositionModified()
  {
    return isModified(NORTHARROWXPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNorthArrowXPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NORTHARROWXPOSITION).getAttributeMdDTO();
  }
  
  public Integer getNorthArrowYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NORTHARROWYPOSITION));
  }
  
  public void setNorthArrowYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(NORTHARROWYPOSITION, "");
    }
    else
    {
      setValue(NORTHARROWYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNorthArrowYPositionWritable()
  {
    return isWritable(NORTHARROWYPOSITION);
  }
  
  public boolean isNorthArrowYPositionReadable()
  {
    return isReadable(NORTHARROWYPOSITION);
  }
  
  public boolean isNorthArrowYPositionModified()
  {
    return isModified(NORTHARROWYPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNorthArrowYPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NORTHARROWYPOSITION).getAttributeMdDTO();
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
  
  public Boolean getScaleBarActive()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SCALEBARACTIVE));
  }
  
  public void setScaleBarActive(Boolean value)
  {
    if(value == null)
    {
      setValue(SCALEBARACTIVE, "");
    }
    else
    {
      setValue(SCALEBARACTIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isScaleBarActiveWritable()
  {
    return isWritable(SCALEBARACTIVE);
  }
  
  public boolean isScaleBarActiveReadable()
  {
    return isReadable(SCALEBARACTIVE);
  }
  
  public boolean isScaleBarActiveModified()
  {
    return isModified(SCALEBARACTIVE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getScaleBarActiveMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SCALEBARACTIVE).getAttributeMdDTO();
  }
  
  public Integer getScaleBarXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SCALEBARXPOSITION));
  }
  
  public void setScaleBarXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(SCALEBARXPOSITION, "");
    }
    else
    {
      setValue(SCALEBARXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isScaleBarXPositionWritable()
  {
    return isWritable(SCALEBARXPOSITION);
  }
  
  public boolean isScaleBarXPositionReadable()
  {
    return isReadable(SCALEBARXPOSITION);
  }
  
  public boolean isScaleBarXPositionModified()
  {
    return isModified(SCALEBARXPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getScaleBarXPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SCALEBARXPOSITION).getAttributeMdDTO();
  }
  
  public Integer getScaleBarYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SCALEBARYPOSITION));
  }
  
  public void setScaleBarYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(SCALEBARYPOSITION, "");
    }
    else
    {
      setValue(SCALEBARYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isScaleBarYPositionWritable()
  {
    return isWritable(SCALEBARYPOSITION);
  }
  
  public boolean isScaleBarYPositionReadable()
  {
    return isReadable(SCALEBARYPOSITION);
  }
  
  public boolean isScaleBarYPositionModified()
  {
    return isModified(SCALEBARYPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getScaleBarYPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SCALEBARYPOSITION).getAttributeMdDTO();
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
  
  public Integer getZoomLevel()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ZOOMLEVEL));
  }
  
  public void setZoomLevel(Integer value)
  {
    if(value == null)
    {
      setValue(ZOOMLEVEL, "");
    }
    else
    {
      setValue(ZOOMLEVEL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isZoomLevelWritable()
  {
    return isWritable(ZOOMLEVEL);
  }
  
  public boolean isZoomLevelReadable()
  {
    return isReadable(ZOOMLEVEL);
  }
  
  public boolean isZoomLevelModified()
  {
    return isModified(ZOOMLEVEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getZoomLevelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ZOOMLEVEL).getAttributeMdDTO();
  }
  
  public final java.lang.String addMapImage(java.lang.String savedMapId, java.lang.String imageName, java.lang.String imagePath)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{savedMapId, imageName, imagePath};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "addMapImage", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String addMapImage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String savedMapId, java.lang.String imageName, java.lang.String imagePath)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, savedMapId, imageName, imagePath};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "addMapImage", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String addTextElement(java.lang.String savedMapId, java.lang.String textValue, java.lang.String fontColor, java.lang.String fontFamily, java.lang.Integer fontSize, java.lang.String fontStyle, java.lang.String customTextElementId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Integer", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{savedMapId, textValue, fontColor, fontFamily, fontSize, fontStyle, customTextElementId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "addTextElement", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String addTextElement(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String savedMapId, java.lang.String textValue, java.lang.String fontColor, java.lang.String fontFamily, java.lang.Integer fontSize, java.lang.String fontStyle, java.lang.String customTextElementId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Integer", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, savedMapId, textValue, fontColor, fontFamily, fontSize, fontStyle, customTextElementId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "addTextElement", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void cleanOldViews(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "cleanOldViews", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.LayerViewQueryDTO createFromExisting(java.lang.String existingMapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{existingMapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "createFromExisting", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.LayerViewQueryDTO createFromExisting(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String existingMapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, existingMapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "createFromExisting", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String deleteLayerFromMap(java.lang.String layerId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{layerId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "deleteLayerFromMap", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String deleteLayerFromMap(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String layerId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, layerId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "deleteLayerFromMap", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.io.InputStream exportShapefile()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "exportShapefile", _declaredTypes);
    return (java.io.InputStream) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.io.InputStream exportShapefile(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "exportShapefile", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.io.InputStream generateMapImageExport(java.lang.String outFileFormat, java.lang.String mapBounds, java.lang.String mapSize)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{outFileFormat, mapBounds, mapSize};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "generateMapImageExport", _declaredTypes);
    return (java.io.InputStream) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.io.InputStream generateMapImageExport(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String outFileFormat, java.lang.String mapBounds, java.lang.String mapSize)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, outFileFormat, mapBounds, mapSize};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "generateMapImageExport", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.LayerViewQueryDTO getAllLayers()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getAllLayers", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.LayerViewQueryDTO getAllLayers(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getAllLayers", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.MapImageQueryDTO getAllMapImages()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getAllMapImages", _declaredTypes);
    return (dss.vector.solutions.query.MapImageQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.MapImageQueryDTO getAllMapImages(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getAllMapImages", _declaredTypes);
    return (dss.vector.solutions.query.MapImageQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedMapQueryDTO getAllSavedMaps(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getAllSavedMaps", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.CycleJobViewDTO getCycleJobView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getCycleJobView", _declaredTypes);
    return (dss.vector.solutions.query.CycleJobViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.CycleJobViewDTO getCycleJobView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getCycleJobView", _declaredTypes);
    return (dss.vector.solutions.query.CycleJobViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getImageByCustomImageId(java.lang.String customImageId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{customImageId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getImageByCustomImageId", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getImageByCustomImageId(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String customImageId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, customImageId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getImageByCustomImageId", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getTextByCustomTextElementId(java.lang.String customTextElementId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{customTextElementId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getTextByCustomTextElementId", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getTextByCustomTextElementId(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String customTextElementId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, customTextElementId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "getTextByCustomTextElementId", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedMapDTO loadDefaultMap(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.query.SavedMapDTO savedMap)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.SavedMap"};
    Object[] _parameters = new Object[]{savedMap};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "loadDefaultMap", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedMapDTO loadMap(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{mapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "loadMap", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void moveLayerOnMap(java.lang.String layerId, java.lang.Integer layerPosition)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer"};
    Object[] _parameters = new Object[]{layerId, layerPosition};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "moveLayerOnMap", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void moveLayerOnMap(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String layerId, java.lang.Integer layerPosition)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, layerId, layerPosition};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "moveLayerOnMap", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String refreshMap(java.lang.String currentMapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{currentMapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "refreshMap", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String refreshMap(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String currentMapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, currentMapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "refreshMap", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String removeMapImage(java.lang.String customImageId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{customImageId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "removeMapImage", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String removeMapImage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String customImageId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, customImageId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "removeMapImage", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String removeTextElement(java.lang.String customTextElementId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{customTextElementId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "removeTextElement", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String removeTextElement(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String customTextElementId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, customTextElementId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "removeTextElement", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateImageLocations(java.lang.String imageLocations)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{imageLocations};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateImageLocations", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateImageLocations(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String imageLocations)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, imageLocations};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateImageLocations", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateLegendLocations(java.lang.String legendLocations)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{legendLocations};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateLegendLocations", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateLegendLocations(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String legendLocations)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, legendLocations};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateLegendLocations", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateMapState(java.lang.Integer zoomLevel, java.lang.String mapCenter, java.lang.Integer mapPanelWidth, java.lang.Integer mapPanelHeight)
  {
    String[] _declaredTypes = new String[]{"java.lang.Integer", "java.lang.String", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{zoomLevel, mapCenter, mapPanelWidth, mapPanelHeight};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateMapState", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateMapState(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Integer zoomLevel, java.lang.String mapCenter, java.lang.Integer mapPanelWidth, java.lang.Integer mapPanelHeight)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer", "java.lang.String", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, zoomLevel, mapCenter, mapPanelWidth, mapPanelHeight};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateMapState", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateNorthArrow(java.lang.Integer northArrowXPosition, java.lang.Integer northArrowYPosition, java.lang.Boolean northArrowActive)
  {
    String[] _declaredTypes = new String[]{"java.lang.Integer", "java.lang.Integer", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{northArrowXPosition, northArrowYPosition, northArrowActive};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateNorthArrow", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateNorthArrow(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Integer northArrowXPosition, java.lang.Integer northArrowYPosition, java.lang.Boolean northArrowActive)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer", "java.lang.Integer", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, northArrowXPosition, northArrowYPosition, northArrowActive};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateNorthArrow", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateScaleBar(java.lang.Integer scaleBarXPosition, java.lang.Integer scaleBarYPosition, java.lang.Boolean scaleBarActive)
  {
    String[] _declaredTypes = new String[]{"java.lang.Integer", "java.lang.Integer", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{scaleBarXPosition, scaleBarYPosition, scaleBarActive};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateScaleBar", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateScaleBar(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Integer scaleBarXPosition, java.lang.Integer scaleBarYPosition, java.lang.Boolean scaleBarActive)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer", "java.lang.Integer", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, scaleBarXPosition, scaleBarYPosition, scaleBarActive};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateScaleBar", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateTextElements(java.lang.String textElements)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{textElements};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateTextElements", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateTextElements(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String textElements)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, textElements};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "updateTextElements", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.MapImageDTO> getAllHasImage()
  {
    return (java.util.List<? extends dss.vector.solutions.query.MapImageDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.HasImageDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.MapImageDTO> getAllHasImage(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.MapImageDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.HasImageDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasImageDTO> getAllHasImageRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasImageDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.HasImageDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasImageDTO> getAllHasImageRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasImageDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.HasImageDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasImageDTO addHasImage(dss.vector.solutions.query.MapImageDTO child)
  {
    return (dss.vector.solutions.query.HasImageDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.HasImageDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasImageDTO addHasImage(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.MapImageDTO child)
  {
    return (dss.vector.solutions.query.HasImageDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.HasImageDTO.CLASS);
  }
  
  public void removeHasImage(dss.vector.solutions.query.HasImageDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHasImage(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasImageDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHasImage()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.HasImageDTO.CLASS);
  }
  
  public static void removeAllHasImage(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.HasImageDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.TextElementDTO> getAllHasTextElement()
  {
    return (java.util.List<? extends dss.vector.solutions.query.TextElementDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.TextElementDTO> getAllHasTextElement(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.TextElementDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasTextElementDTO> getAllHasTextElementRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasTextElementDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasTextElementDTO> getAllHasTextElementRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasTextElementDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasTextElementDTO addHasTextElement(dss.vector.solutions.query.TextElementDTO child)
  {
    return (dss.vector.solutions.query.HasTextElementDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasTextElementDTO addHasTextElement(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.TextElementDTO child)
  {
    return (dss.vector.solutions.query.HasTextElementDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public void removeHasTextElement(dss.vector.solutions.query.HasTextElementDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHasTextElement(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasTextElementDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHasTextElement()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public static void removeAllHasTextElement(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.LayerDTO> getAllLayer()
  {
    return (java.util.List<? extends dss.vector.solutions.query.LayerDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.LayerDTO> getAllLayer(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.LayerDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasLayersDTO> getAllLayerRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasLayersDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasLayersDTO> getAllLayerRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasLayersDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasLayersDTO addLayer(dss.vector.solutions.query.LayerDTO child)
  {
    return (dss.vector.solutions.query.HasLayersDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasLayersDTO addLayer(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.LayerDTO child)
  {
    return (dss.vector.solutions.query.HasLayersDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public void removeLayer(dss.vector.solutions.query.HasLayersDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeLayer(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasLayersDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllLayer()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static void removeAllLayer(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.SavedMapDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.query.SavedMapQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.SavedMapQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.SavedMapDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.SavedMapDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.SavedMapDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedMapDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.SavedMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
