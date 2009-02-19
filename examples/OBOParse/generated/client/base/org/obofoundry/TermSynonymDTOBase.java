package org.obofoundry;

public abstract class TermSynonymDTOBase extends com.terraframe.mojo.transport.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.TermSynonym";
  private static final long serialVersionUID = 1229530369284L;
  
  protected TermSynonymDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TermSynonymDTOBase(com.terraframe.mojo.transport.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String ID = "id";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SYNONYMNAME = "synonymName";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String TYPE = "type";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
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
  
  public String getSynonymName()
  {
    return getValue(SYNONYMNAME);
  }
  
  public void setSynonymName(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMNAME, "");
    }
    else
    {
      setValue(SYNONYMNAME, value);
    }
  }
  
  public boolean isSynonymNameWritable()
  {
    return isWritable(SYNONYMNAME);
  }
  
  public boolean isSynonymNameReadable()
  {
    return isReadable(SYNONYMNAME);
  }
  
  public boolean isSynonymNameModified()
  {
    return isModified(SYNONYMNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSynonymNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("synonymName").getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.TermDTO> getAllTerms()
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) getRequest().getParents(this.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.TermDTO> getAllTerms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.TermDTO>) clientRequestIF.getParents(id, org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.HasSynonymDTO> getAllTermsRelationships()
  {
    return (java.util.List<? extends org.obofoundry.HasSynonymDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.HasSynonymDTO> getAllTermsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.HasSynonymDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public org.obofoundry.HasSynonymDTO addTerms(org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.HasSynonymDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public static org.obofoundry.HasSynonymDTO addTerms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.TermDTO parent)
  {
    return (org.obofoundry.HasSynonymDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public void removeTerms(org.obofoundry.HasSynonymDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeTerms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.HasSynonymDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllTerms()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public static void removeAllTerms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.HasSynonymDTO.CLASS);
  }
  
  public static TermSynonymDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.transport.EntityDTO dto = (com.terraframe.mojo.transport.EntityDTO)clientRequest.get(id);
    
    return (TermSynonymDTO) dto;
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
  
  public static org.obofoundry.TermSynonymDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.TermSynonym", "lock", _declaredTypes);
    return (org.obofoundry.TermSynonymDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static org.obofoundry.TermSynonymDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.TermSynonym", "unlock", _declaredTypes);
    return (org.obofoundry.TermSynonymDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
