package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1654888404)
public abstract class TextElementDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.TextElement";
  private static final long serialVersionUID = 1654888404;
  
  protected TextElementDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TextElementDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String CUSTOMTEXTELEMENTID = "customTextElementId";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FONTCOLOR = "fontColor";
  public static java.lang.String FONTFAMILY = "fontFamily";
  public static java.lang.String FONTSIZE = "fontSize";
  public static java.lang.String FONTSTYLE = "fontStyle";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TEXTVALUE = "textValue";
  public static java.lang.String TEXTXPOSITION = "textXPosition";
  public static java.lang.String TEXTYPOSITION = "textYPosition";
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
  
  public String getCustomTextElementId()
  {
    return getValue(CUSTOMTEXTELEMENTID);
  }
  
  public void setCustomTextElementId(String value)
  {
    if(value == null)
    {
      setValue(CUSTOMTEXTELEMENTID, "");
    }
    else
    {
      setValue(CUSTOMTEXTELEMENTID, value);
    }
  }
  
  public boolean isCustomTextElementIdWritable()
  {
    return isWritable(CUSTOMTEXTELEMENTID);
  }
  
  public boolean isCustomTextElementIdReadable()
  {
    return isReadable(CUSTOMTEXTELEMENTID);
  }
  
  public boolean isCustomTextElementIdModified()
  {
    return isModified(CUSTOMTEXTELEMENTID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCustomTextElementIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CUSTOMTEXTELEMENTID).getAttributeMdDTO();
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
  
  public String getFontColor()
  {
    return getValue(FONTCOLOR);
  }
  
  public void setFontColor(String value)
  {
    if(value == null)
    {
      setValue(FONTCOLOR, "");
    }
    else
    {
      setValue(FONTCOLOR, value);
    }
  }
  
  public boolean isFontColorWritable()
  {
    return isWritable(FONTCOLOR);
  }
  
  public boolean isFontColorReadable()
  {
    return isReadable(FONTCOLOR);
  }
  
  public boolean isFontColorModified()
  {
    return isModified(FONTCOLOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFontColorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTCOLOR).getAttributeMdDTO();
  }
  
  public String getFontFamily()
  {
    return getValue(FONTFAMILY);
  }
  
  public void setFontFamily(String value)
  {
    if(value == null)
    {
      setValue(FONTFAMILY, "");
    }
    else
    {
      setValue(FONTFAMILY, value);
    }
  }
  
  public boolean isFontFamilyWritable()
  {
    return isWritable(FONTFAMILY);
  }
  
  public boolean isFontFamilyReadable()
  {
    return isReadable(FONTFAMILY);
  }
  
  public boolean isFontFamilyModified()
  {
    return isModified(FONTFAMILY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFontFamilyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTFAMILY).getAttributeMdDTO();
  }
  
  public Integer getFontSize()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FONTSIZE));
  }
  
  public void setFontSize(Integer value)
  {
    if(value == null)
    {
      setValue(FONTSIZE, "");
    }
    else
    {
      setValue(FONTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFontSizeWritable()
  {
    return isWritable(FONTSIZE);
  }
  
  public boolean isFontSizeReadable()
  {
    return isReadable(FONTSIZE);
  }
  
  public boolean isFontSizeModified()
  {
    return isModified(FONTSIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFontSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FONTSIZE).getAttributeMdDTO();
  }
  
  public String getFontStyle()
  {
    return getValue(FONTSTYLE);
  }
  
  public void setFontStyle(String value)
  {
    if(value == null)
    {
      setValue(FONTSTYLE, "");
    }
    else
    {
      setValue(FONTSTYLE, value);
    }
  }
  
  public boolean isFontStyleWritable()
  {
    return isWritable(FONTSTYLE);
  }
  
  public boolean isFontStyleReadable()
  {
    return isReadable(FONTSTYLE);
  }
  
  public boolean isFontStyleModified()
  {
    return isModified(FONTSTYLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFontStyleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTSTYLE).getAttributeMdDTO();
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
  
  public String getTextValue()
  {
    return getValue(TEXTVALUE);
  }
  
  public void setTextValue(String value)
  {
    if(value == null)
    {
      setValue(TEXTVALUE, "");
    }
    else
    {
      setValue(TEXTVALUE, value);
    }
  }
  
  public boolean isTextValueWritable()
  {
    return isWritable(TEXTVALUE);
  }
  
  public boolean isTextValueReadable()
  {
    return isReadable(TEXTVALUE);
  }
  
  public boolean isTextValueModified()
  {
    return isModified(TEXTVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTextValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEXTVALUE).getAttributeMdDTO();
  }
  
  public Integer getTextXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEXTXPOSITION));
  }
  
  public void setTextXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(TEXTXPOSITION, "");
    }
    else
    {
      setValue(TEXTXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTextXPositionWritable()
  {
    return isWritable(TEXTXPOSITION);
  }
  
  public boolean isTextXPositionReadable()
  {
    return isReadable(TEXTXPOSITION);
  }
  
  public boolean isTextXPositionModified()
  {
    return isModified(TEXTXPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTextXPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEXTXPOSITION).getAttributeMdDTO();
  }
  
  public Integer getTextYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEXTYPOSITION));
  }
  
  public void setTextYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(TEXTYPOSITION, "");
    }
    else
    {
      setValue(TEXTYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTextYPositionWritable()
  {
    return isWritable(TEXTYPOSITION);
  }
  
  public boolean isTextYPositionReadable()
  {
    return isReadable(TEXTYPOSITION);
  }
  
  public boolean isTextYPositionModified()
  {
    return isModified(TEXTYPOSITION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTextYPositionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEXTYPOSITION).getAttributeMdDTO();
  }
  
  public final java.lang.String removeTextElement(java.lang.String textElementId, java.lang.String mapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{textElementId, mapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.TextElementDTO.CLASS, "removeTextElement", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String removeTextElement(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String textElementId, java.lang.String mapId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, textElementId, mapId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.TextElementDTO.CLASS, "removeTextElement", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.SavedMapDTO> getAllContainingMap()
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedMapDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.SavedMapDTO> getAllContainingMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedMapDTO>) clientRequestIF.getParents(id, dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasTextElementDTO> getAllContainingMapRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasTextElementDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasTextElementDTO> getAllContainingMapRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasTextElementDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasTextElementDTO addContainingMap(dss.vector.solutions.query.SavedMapDTO parent)
  {
    return (dss.vector.solutions.query.HasTextElementDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasTextElementDTO addContainingMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.SavedMapDTO parent)
  {
    return (dss.vector.solutions.query.HasTextElementDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public void removeContainingMap(dss.vector.solutions.query.HasTextElementDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeContainingMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasTextElementDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllContainingMap()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public static void removeAllContainingMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.query.HasTextElementDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.TextElementDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.TextElementDTO) dto;
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
  
  public static dss.vector.solutions.query.TextElementQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.TextElementQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.TextElementDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.TextElementDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.TextElementDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.TextElementDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.TextElementDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.TextElementDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.TextElementDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
