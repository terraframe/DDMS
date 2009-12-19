package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = 344116941)
public abstract class BrowserRootDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.BrowserRoot";
  private static final long serialVersionUID = 344116941;
  
  protected BrowserRootDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected BrowserRootDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SELECTABLE = "selectable";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TERM = "term";
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
  
  public Boolean getSelectable()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SELECTABLE));
  }
  
  public void setSelectable(Boolean value)
  {
    if(value == null)
    {
      setValue(SELECTABLE, "");
    }
    else
    {
      setValue(SELECTABLE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSelectableWritable()
  {
    return isWritable(SELECTABLE);
  }
  
  public boolean isSelectableReadable()
  {
    return isReadable(SELECTABLE);
  }
  
  public boolean isSelectableModified()
  {
    return isModified(SELECTABLE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getSelectableMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SELECTABLE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getTerm()
  {
    if(getValue(TERM) == null || getValue(TERM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TERM));
    }
  }
  
  public void setTerm(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value.getId());
    }
  }
  
  public boolean isTermWritable()
  {
    return isWritable(TERM);
  }
  
  public boolean isTermReadable()
  {
    return isReadable(TERM);
  }
  
  public boolean isTermModified()
  {
    return isModified(TERM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTermMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TERM).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.ontology.BrowserRootViewQueryDTO getAsViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.BrowserRootDTO.CLASS, "getAsViews", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.BrowserRootViewDTO[] getAttributeRoots(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String className, java.lang.String attributeName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{className, attributeName};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.BrowserRootDTO.CLASS, "getAttributeRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.BrowserRootViewDTO[] getDefaultGeoRoots(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String universalType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{universalType};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.BrowserRootDTO.CLASS, "getDefaultGeoRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.BrowserRootViewDTO[] getDefaultRoot(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.BrowserRootDTO.CLASS, "getDefaultRoot", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.ontology.BrowserRootViewDTO update(dss.vector.solutions.ontology.BrowserRootDTO browserRoot)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.ontology.BrowserRoot"};
    Object[] _parameters = new Object[]{browserRoot};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.BrowserRootDTO.CLASS, "update", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.BrowserRootViewDTO update(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.ontology.BrowserRootDTO browserRoot)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.ontology.BrowserRoot"};
    Object[] _parameters = new Object[]{id, browserRoot};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.BrowserRootDTO.CLASS, "update", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.BrowserFieldDTO> getAllField()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.BrowserFieldDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.ontology.FieldRootDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.BrowserFieldDTO> getAllField(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.BrowserFieldDTO>) clientRequestIF.getParents(id, dss.vector.solutions.ontology.FieldRootDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.FieldRootDTO> getAllFieldRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.FieldRootDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.ontology.FieldRootDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.FieldRootDTO> getAllFieldRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.FieldRootDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.ontology.FieldRootDTO.CLASS);
  }
  
  public dss.vector.solutions.ontology.FieldRootDTO addField(dss.vector.solutions.ontology.BrowserFieldDTO parent)
  {
    return (dss.vector.solutions.ontology.FieldRootDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.ontology.FieldRootDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.FieldRootDTO addField(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.BrowserFieldDTO parent)
  {
    return (dss.vector.solutions.ontology.FieldRootDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.ontology.FieldRootDTO.CLASS);
  }
  
  public void removeField(dss.vector.solutions.ontology.FieldRootDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeField(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.ontology.FieldRootDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllField()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.ontology.FieldRootDTO.CLASS);
  }
  
  public static void removeAllField(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.ontology.FieldRootDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.BrowserRootDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.ontology.BrowserRootDTO) dto;
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
  
  public static dss.vector.solutions.ontology.BrowserRootQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.ontology.BrowserRootQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.ontology.BrowserRoot", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.ontology.BrowserRootDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.BrowserRootDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.ontology.BrowserRootDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.BrowserRootDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
