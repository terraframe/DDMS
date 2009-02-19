package org.obofoundry;

public abstract class ComponentDTOBase extends com.terraframe.mojo.transport.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.Component";
  private static final long serialVersionUID = 1229530368863L;
  
  protected ComponentDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ComponentDTOBase(com.terraframe.mojo.transport.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OBOID = "oboId";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String DEF = "def";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String COMPONENTNAME = "componentName";
  public static java.lang.String NAMESPACE = "nameSpace";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String COMPONENTCOMMENT = "componentComment";
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
  
  public com.terraframe.mojo.system.UsersDTO getLockedBy()
  {
    return com.terraframe.mojo.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
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
  
  public String getOboId()
  {
    return getValue(OBOID);
  }
  
  public void setOboId(String value)
  {
    if(value == null)
    {
      setValue(OBOID, "");
    }
    else
    {
      setValue(OBOID, value);
    }
  }
  
  public boolean isOboIdWritable()
  {
    return isWritable(OBOID);
  }
  
  public boolean isOboIdReadable()
  {
    return isReadable(OBOID);
  }
  
  public boolean isOboIdModified()
  {
    return isModified(OBOID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getOboIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("oboId").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getCreatedBy()
  {
    return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
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
    return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
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
  
  public com.terraframe.mojo.system.ActorDTO getOwner()
  {
    return com.terraframe.mojo.system.ActorDTO.get(getRequest(), getValue(OWNER));
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
  
  public String getDef()
  {
    return getValue(DEF);
  }
  
  public void setDef(String value)
  {
    if(value == null)
    {
      setValue(DEF, "");
    }
    else
    {
      setValue(DEF, value);
    }
  }
  
  public boolean isDefWritable()
  {
    return isWritable(DEF);
  }
  
  public boolean isDefReadable()
  {
    return isReadable(DEF);
  }
  
  public boolean isDefModified()
  {
    return isModified(DEF);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getDefMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO("def").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.metadata.MdDomainDTO getEntityDomain()
  {
    return com.terraframe.mojo.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
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
  
  public String getComponentName()
  {
    return getValue(COMPONENTNAME);
  }
  
  public void setComponentName(String value)
  {
    if(value == null)
    {
      setValue(COMPONENTNAME, "");
    }
    else
    {
      setValue(COMPONENTNAME, value);
    }
  }
  
  public boolean isComponentNameWritable()
  {
    return isWritable(COMPONENTNAME);
  }
  
  public boolean isComponentNameReadable()
  {
    return isReadable(COMPONENTNAME);
  }
  
  public boolean isComponentNameModified()
  {
    return isModified(COMPONENTNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getComponentNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("componentName").getAttributeMdDTO();
  }
  
  public String getNameSpace()
  {
    return getValue(NAMESPACE);
  }
  
  public void setNameSpace(String value)
  {
    if(value == null)
    {
      setValue(NAMESPACE, "");
    }
    else
    {
      setValue(NAMESPACE, value);
    }
  }
  
  public boolean isNameSpaceWritable()
  {
    return isWritable(NAMESPACE);
  }
  
  public boolean isNameSpaceReadable()
  {
    return isReadable(NAMESPACE);
  }
  
  public boolean isNameSpaceModified()
  {
    return isModified(NAMESPACE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getNameSpaceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("nameSpace").getAttributeMdDTO();
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
  
  public String getComponentComment()
  {
    return getValue(COMPONENTCOMMENT);
  }
  
  public void setComponentComment(String value)
  {
    if(value == null)
    {
      setValue(COMPONENTCOMMENT, "");
    }
    else
    {
      setValue(COMPONENTCOMMENT, value);
    }
  }
  
  public boolean isComponentCommentWritable()
  {
    return isWritable(COMPONENTCOMMENT);
  }
  
  public boolean isComponentCommentReadable()
  {
    return isReadable(COMPONENTCOMMENT);
  }
  
  public boolean isComponentCommentModified()
  {
    return isModified(COMPONENTCOMMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getComponentCommentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO("componentComment").getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.ComponentDTO> getAllChildRelationship()
  {
    return (java.util.List<? extends org.obofoundry.ComponentDTO>) getRequest().getChildren(this.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.ComponentDTO> getAllChildRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.ComponentDTO>) clientRequestIF.getChildren(id, org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.OBORelationshipDTO> getAllChildRelationshipRelationships()
  {
    return (java.util.List<? extends org.obofoundry.OBORelationshipDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.OBORelationshipDTO> getAllChildRelationshipRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.OBORelationshipDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  public org.obofoundry.OBORelationshipDTO addChildRelationship(org.obofoundry.ComponentDTO child)
  {
    return (org.obofoundry.OBORelationshipDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  public static org.obofoundry.OBORelationshipDTO addChildRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.ComponentDTO child)
  {
    return (org.obofoundry.OBORelationshipDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  public void removeChildRelationship(org.obofoundry.OBORelationshipDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.OBORelationshipDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildRelationship()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  public static void removeAllChildRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.ComponentDTO> getAllIsASubClasses()
  {
    return (java.util.List<? extends org.obofoundry.ComponentDTO>) getRequest().getChildren(this.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.ComponentDTO> getAllIsASubClasses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.ComponentDTO>) clientRequestIF.getChildren(id, org.obofoundry.IsADTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.IsADTO> getAllIsASubClassesRelationships()
  {
    return (java.util.List<? extends org.obofoundry.IsADTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.IsADTO> getAllIsASubClassesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.IsADTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.IsADTO.CLASS);
  }
  
  public org.obofoundry.IsADTO addIsASubClasses(org.obofoundry.ComponentDTO child)
  {
    return (org.obofoundry.IsADTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  public static org.obofoundry.IsADTO addIsASubClasses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.ComponentDTO child)
  {
    return (org.obofoundry.IsADTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  public void removeIsASubClasses(org.obofoundry.IsADTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeIsASubClasses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.IsADTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllIsASubClasses()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  public static void removeAllIsASubClasses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.IsADTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.ComponentDTO> getAllParentRelationship()
  {
    return (java.util.List<? extends org.obofoundry.ComponentDTO>) getRequest().getParents(this.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.ComponentDTO> getAllParentRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.ComponentDTO>) clientRequestIF.getParents(id, org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.OBORelationshipDTO> getAllParentRelationshipRelationships()
  {
    return (java.util.List<? extends org.obofoundry.OBORelationshipDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.OBORelationshipDTO> getAllParentRelationshipRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.OBORelationshipDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  public org.obofoundry.OBORelationshipDTO addParentRelationship(org.obofoundry.ComponentDTO parent)
  {
    return (org.obofoundry.OBORelationshipDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  public static org.obofoundry.OBORelationshipDTO addParentRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.ComponentDTO parent)
  {
    return (org.obofoundry.OBORelationshipDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  public void removeParentRelationship(org.obofoundry.OBORelationshipDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.OBORelationshipDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentRelationship()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  public static void removeAllParentRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.OBORelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.ComponentDTO> getAllIsASuperClasses()
  {
    return (java.util.List<? extends org.obofoundry.ComponentDTO>) getRequest().getParents(this.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.ComponentDTO> getAllIsASuperClasses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.ComponentDTO>) clientRequestIF.getParents(id, org.obofoundry.IsADTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.IsADTO> getAllIsASuperClassesRelationships()
  {
    return (java.util.List<? extends org.obofoundry.IsADTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.IsADTO> getAllIsASuperClassesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.IsADTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.IsADTO.CLASS);
  }
  
  public org.obofoundry.IsADTO addIsASuperClasses(org.obofoundry.ComponentDTO parent)
  {
    return (org.obofoundry.IsADTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  public static org.obofoundry.IsADTO addIsASuperClasses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.ComponentDTO parent)
  {
    return (org.obofoundry.IsADTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.IsADTO.CLASS);
  }
  
  public void removeIsASuperClasses(org.obofoundry.IsADTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeIsASuperClasses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.IsADTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllIsASuperClasses()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.IsADTO.CLASS);
  }
  
  public static void removeAllIsASuperClasses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.IsADTO.CLASS);
  }
  
  public static ComponentDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.transport.EntityDTO dto = (com.terraframe.mojo.transport.EntityDTO)clientRequest.get(id);
    
    return (ComponentDTO) dto;
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
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static org.obofoundry.ComponentDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.Component", "lock", _declaredTypes);
    return (org.obofoundry.ComponentDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static org.obofoundry.ComponentDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.Component", "unlock", _declaredTypes);
    return (org.obofoundry.ComponentDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
