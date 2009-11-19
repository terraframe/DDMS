package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = 601427145)
public abstract class TermDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.Term";
  private static final long serialVersionUID = 601427145;
  
  protected TermDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TermDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COMMENT = "comment";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DEF = "def";
  public static java.lang.String DISPLAY = "display";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NAME = "name";
  public static java.lang.String NAMESPACE = "namespace";
  public static java.lang.String OBSOLETE = "obsolete";
  public static java.lang.String ONTOLOGY = "ontology";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TERMID = "termId";
  public static java.lang.String TYPE = "type";
  public String getComment()
  {
    return getValue(COMMENT);
  }
  
  public void setComment(String value)
  {
    if(value == null)
    {
      setValue(COMMENT, "");
    }
    else
    {
      setValue(COMMENT, value);
    }
  }
  
  public boolean isCommentWritable()
  {
    return isWritable(COMMENT);
  }
  
  public boolean isCommentReadable()
  {
    return isReadable(COMMENT);
  }
  
  public boolean isCommentModified()
  {
    return isModified(COMMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getCommentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(COMMENT).getAttributeMdDTO();
  }
  
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
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(DEF).getAttributeMdDTO();
  }
  
  public String getDisplay()
  {
    return getValue(DISPLAY);
  }
  
  public void setDisplay(String value)
  {
    if(value == null)
    {
      setValue(DISPLAY, "");
    }
    else
    {
      setValue(DISPLAY, value);
    }
  }
  
  public boolean isDisplayWritable()
  {
    return isWritable(DISPLAY);
  }
  
  public boolean isDisplayReadable()
  {
    return isReadable(DISPLAY);
  }
  
  public boolean isDisplayModified()
  {
    return isModified(DISPLAY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDisplayMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISPLAY).getAttributeMdDTO();
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
  
  public String getName()
  {
    return getValue(NAME);
  }
  
  public void setName(String value)
  {
    if(value == null)
    {
      setValue(NAME, "");
    }
    else
    {
      setValue(NAME, value);
    }
  }
  
  public boolean isNameWritable()
  {
    return isWritable(NAME);
  }
  
  public boolean isNameReadable()
  {
    return isReadable(NAME);
  }
  
  public boolean isNameModified()
  {
    return isModified(NAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NAME).getAttributeMdDTO();
  }
  
  public String getNamespace()
  {
    return getValue(NAMESPACE);
  }
  
  public void setNamespace(String value)
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
  
  public boolean isNamespaceWritable()
  {
    return isWritable(NAMESPACE);
  }
  
  public boolean isNamespaceReadable()
  {
    return isReadable(NAMESPACE);
  }
  
  public boolean isNamespaceModified()
  {
    return isModified(NAMESPACE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getNamespaceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NAMESPACE).getAttributeMdDTO();
  }
  
  public Boolean getObsolete()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(OBSOLETE));
  }
  
  public void setObsolete(Boolean value)
  {
    if(value == null)
    {
      setValue(OBSOLETE, "");
    }
    else
    {
      setValue(OBSOLETE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isObsoleteWritable()
  {
    return isWritable(OBSOLETE);
  }
  
  public boolean isObsoleteReadable()
  {
    return isReadable(OBSOLETE);
  }
  
  public boolean isObsoleteModified()
  {
    return isModified(OBSOLETE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getObsoleteMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(OBSOLETE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.OntologyDTO getOntology()
  {
    if(getValue(ONTOLOGY) == null || getValue(ONTOLOGY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.OntologyDTO.get(getRequest(), getValue(ONTOLOGY));
    }
  }
  
  public void setOntology(dss.vector.solutions.ontology.OntologyDTO value)
  {
    if(value == null)
    {
      setValue(ONTOLOGY, "");
    }
    else
    {
      setValue(ONTOLOGY, value.getId());
    }
  }
  
  public boolean isOntologyWritable()
  {
    return isWritable(ONTOLOGY);
  }
  
  public boolean isOntologyReadable()
  {
    return isReadable(ONTOLOGY);
  }
  
  public boolean isOntologyModified()
  {
    return isModified(ONTOLOGY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getOntologyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ONTOLOGY).getAttributeMdDTO();
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
  
  public String getTermId()
  {
    return getValue(TERMID);
  }
  
  public void setTermId(String value)
  {
    if(value == null)
    {
      setValue(TERMID, "");
    }
    else
    {
      setValue(TERMID, value);
    }
  }
  
  public boolean isTermIdWritable()
  {
    return isWritable(TERMID);
  }
  
  public boolean isTermIdReadable()
  {
    return isReadable(TERMID);
  }
  
  public boolean isTermIdModified()
  {
    return isModified(TERMID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTermIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMID).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.ontology.TermViewDTO applyWithParent(java.lang.String parentTermId, java.lang.Boolean cloneOperation)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{parentTermId, cloneOperation};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "applyWithParent", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewDTO applyWithParent(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentTermId, java.lang.Boolean cloneOperation)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, parentTermId, cloneOperation};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "applyWithParent", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void confirmChangeParent(java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "confirmChangeParent", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void confirmChangeParent(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "confirmChangeParent", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void confirmDeleteTerm(java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "confirmDeleteTerm", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void confirmDeleteTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "confirmDeleteTerm", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermDTO[] getAllTermsForField(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String className, java.lang.String attributeName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{className, attributeName};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getAllTermsForField", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getByIds(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String[] termIds)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{termIds};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getByIds", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getDefaultRoots(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getDefaultRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.ontology.TermViewQueryDTO getOntologyChildren(java.lang.Boolean filterObsolete)
  {
    String[] _declaredTypes = new String[]{"java.lang.Boolean"};
    Object[] _parameters = new Object[]{filterObsolete};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getOntologyChildren", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getOntologyChildren(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Boolean filterObsolete)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, filterObsolete};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getOntologyChildren", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getRoots(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String relationshipType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{relationshipType};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO searchTerms(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String searchValue, java.lang.String[] parentTermIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{searchValue, parentTermIds};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "searchTerms", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllChildTerm()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllChildTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO> getAllChildTermRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO> getAllChildTermRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public dss.vector.solutions.ontology.TermRelationshipDTO addChildTerm(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.ontology.TermRelationshipDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.TermRelationshipDTO addChildTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.ontology.TermRelationshipDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public void removeChildTerm(dss.vector.solutions.ontology.TermRelationshipDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.ontology.TermRelationshipDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildTerm()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public static void removeAllChildTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllANCVisitAggregatedIPTs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllANCVisitAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO> getAllANCVisitAggregatedIPTsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO> getAllANCVisitAggregatedIPTsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTANCVisitDTO addANCVisitAggregatedIPTs(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTANCVisitDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTANCVisitDTO addANCVisitAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTANCVisitDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public void removeANCVisitAggregatedIPTs(dss.vector.solutions.intervention.monitor.IPTANCVisitDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeANCVisitAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllANCVisitAggregatedIPTs()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public static void removeAllANCVisitAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithNets()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNNetDTO> getAllAggregatedITNsWithNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNNetDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNNetDTO> getAllAggregatedITNsWithNetsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNNetDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNNetDTO addAggregatedITNsWithNets(dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNNetDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNNetDTO addAggregatedITNsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNNetDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  public void removeAggregatedITNsWithNets(dss.vector.solutions.intervention.monitor.ITNNetDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedITNsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNNetDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedITNsWithNets()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  public static void removeAllAggregatedITNsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithService()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithService(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNServiceDTO> getAllAggregatedITNsWithServiceRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNServiceDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNServiceDTO> getAllAggregatedITNsWithServiceRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNServiceDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNServiceDTO addAggregatedITNsWithService(dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNServiceDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNServiceDTO addAggregatedITNsWithService(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNServiceDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  public void removeAggregatedITNsWithService(dss.vector.solutions.intervention.monitor.ITNServiceDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedITNsWithService(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNServiceDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedITNsWithService()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  public static void removeAllAggregatedITNsWithService(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithTargetGroups()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO> getAllAggregatedITNsWithTargetGroupsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO> getAllAggregatedITNsWithTargetGroupsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO addAggregatedITNsWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO addAggregatedITNsWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  public void removeAggregatedITNsWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedITNsWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedITNsWithTargetGroups()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  public static void removeAllAggregatedITNsWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllDiagnosticAggregatedCases()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllDiagnosticAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO> getAllDiagnosticAggregatedCasesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO> getAllDiagnosticAggregatedCasesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseDiagnosticDTO addDiagnosticAggregatedCases(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseDiagnosticDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseDiagnosticDTO addDiagnosticAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseDiagnosticDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public void removeDiagnosticAggregatedCases(dss.vector.solutions.surveillance.CaseDiagnosticDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeDiagnosticAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseDiagnosticDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllDiagnosticAggregatedCases()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public static void removeAllDiagnosticAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllDoseAggregatedIPTs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllDoseAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO> getAllDoseAggregatedIPTsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO> getAllDoseAggregatedIPTsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTDoseDTO addDoseAggregatedIPTs(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTDoseDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTDoseDTO addDoseAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTDoseDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public void removeDoseAggregatedIPTs(dss.vector.solutions.intervention.monitor.IPTDoseDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeDoseAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTDoseDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllDoseAggregatedIPTs()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public static void removeAllDoseAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO> getAllITNCommunityDistributionsWithNets()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO> getAllITNCommunityDistributionsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO> getAllITNCommunityDistributionsWithNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO> getAllITNCommunityDistributionsWithNetsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO addITNCommunityDistributionsWithNets(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO addITNCommunityDistributionsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public void removeITNCommunityDistributionsWithNets(dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNCommunityDistributionsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNCommunityDistributionsWithNets()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public static void removeAllITNCommunityDistributionsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO> getAllITNCommunityDistributionsWithTargetGroups()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO> getAllITNCommunityDistributionsWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO> getAllITNCommunityDistributionsWithTargetGroupsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO> getAllITNCommunityDistributionsWithTargetGroupsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO addITNCommunityDistributionsWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO addITNCommunityDistributionsWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public void removeITNCommunityDistributionsWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNCommunityDistributionsWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNCommunityDistributionsWithTargetGroups()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public static void removeAllITNCommunityDistributionsWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionDTO> getAllITNFacilityDistributionsWithNets()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionDTO> getAllITNFacilityDistributionsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO> getAllITNFacilityDistributionsWithNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO> getAllITNFacilityDistributionsWithNetsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO addITNFacilityDistributionsWithNets(dss.vector.solutions.intervention.monitor.ITNDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO addITNFacilityDistributionsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public void removeITNFacilityDistributionsWithNets(dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNFacilityDistributionsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNFacilityDistributionsWithNets()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public static void removeAllITNFacilityDistributionsWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO> getAllITNHouseholdSurveysWithNets()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO> getAllITNHouseholdSurveysWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO> getAllITNHouseholdSurveysWithNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO> getAllITNHouseholdSurveysWithNetsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO addITNHouseholdSurveysWithNets(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO addITNHouseholdSurveysWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  public void removeITNHouseholdSurveysWithNets(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNHouseholdSurveysWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNHouseholdSurveysWithNets()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  public static void removeAllITNHouseholdSurveysWithNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO> getAllITNHouseholdSurveysWithNonUseReasons()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO> getAllITNHouseholdSurveysWithNonUseReasons(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO> getAllITNHouseholdSurveysWithNonUseReasonsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO> getAllITNHouseholdSurveysWithNonUseReasonsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO addITNHouseholdSurveysWithNonUseReasons(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO addITNHouseholdSurveysWithNonUseReasons(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  public void removeITNHouseholdSurveysWithNonUseReasons(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNHouseholdSurveysWithNonUseReasons(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNHouseholdSurveysWithNonUseReasons()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  public static void removeAllITNHouseholdSurveysWithNonUseReasons(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO> getAllITNHouseholdSurveysWithTargetGroups()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO> getAllITNHouseholdSurveysWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO> getAllITNHouseholdSurveysWithTargetGroupsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO> getAllITNHouseholdSurveysWithTargetGroupsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO addITNHouseholdSurveysWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO addITNHouseholdSurveysWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public void removeITNHouseholdSurveysWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNHouseholdSurveysWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNHouseholdSurveysWithTargetGroups()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public static void removeAllITNHouseholdSurveysWithTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualInstanceDTO> getAllIndividualInstance()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualInstanceDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualInstanceDTO> getAllIndividualInstance(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualInstanceDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO> getAllIndividualInstanceRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO> getAllIndividualInstanceRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.IndividualCaseSymptomDTO addIndividualInstance(dss.vector.solutions.intervention.monitor.IndividualInstanceDTO parent)
  {
    return (dss.vector.solutions.surveillance.IndividualCaseSymptomDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.IndividualCaseSymptomDTO addIndividualInstance(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.IndividualInstanceDTO parent)
  {
    return (dss.vector.solutions.surveillance.IndividualCaseSymptomDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public void removeIndividualInstance(dss.vector.solutions.surveillance.IndividualCaseSymptomDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeIndividualInstance(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllIndividualInstance()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public static void removeAllIndividualInstance(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO> getAllMosquitosWithBiochemicalResults()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO> getAllMosquitosWithBiochemicalResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO>) clientRequestIF.getParents(id, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO> getAllMosquitosWithBiochemicalResultsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO> getAllMosquitosWithBiochemicalResultsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO addMosquitosWithBiochemicalResults(dss.vector.solutions.entomology.MosquitoDTO parent)
  {
    return (dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO addMosquitosWithBiochemicalResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.entomology.MosquitoDTO parent)
  {
    return (dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  public void removeMosquitosWithBiochemicalResults(dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeMosquitosWithBiochemicalResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllMosquitosWithBiochemicalResults()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  public static void removeAllMosquitosWithBiochemicalResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO> getAllMosquitosWithInfectivityResults()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO> getAllMosquitosWithInfectivityResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO>) clientRequestIF.getParents(id, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.assay.InfectivityTestResultDTO> getAllMosquitosWithInfectivityResultsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.InfectivityTestResultDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.assay.InfectivityTestResultDTO> getAllMosquitosWithInfectivityResultsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.InfectivityTestResultDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.assay.InfectivityTestResultDTO addMosquitosWithInfectivityResults(dss.vector.solutions.entomology.MosquitoDTO parent)
  {
    return (dss.vector.solutions.entomology.assay.InfectivityTestResultDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.assay.InfectivityTestResultDTO addMosquitosWithInfectivityResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.entomology.MosquitoDTO parent)
  {
    return (dss.vector.solutions.entomology.assay.InfectivityTestResultDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  public void removeMosquitosWithInfectivityResults(dss.vector.solutions.entomology.assay.InfectivityTestResultDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeMosquitosWithInfectivityResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllMosquitosWithInfectivityResults()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  public static void removeAllMosquitosWithInfectivityResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO> getAllMosquitosWithTargetSiteResults()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO> getAllMosquitosWithTargetSiteResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.MosquitoDTO>) clientRequestIF.getParents(id, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO> getAllMosquitosWithTargetSiteResultsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO> getAllMosquitosWithTargetSiteResultsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO addMosquitosWithTargetSiteResults(dss.vector.solutions.entomology.MosquitoDTO parent)
  {
    return (dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO addMosquitosWithTargetSiteResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.entomology.MosquitoDTO parent)
  {
    return (dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  public void removeMosquitosWithTargetSiteResults(dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeMosquitosWithTargetSiteResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllMosquitosWithTargetSiteResults()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  public static void removeAllMosquitosWithTargetSiteResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllParentTerm()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllParentTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getParents(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO> getAllParentTermRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO> getAllParentTermRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public dss.vector.solutions.ontology.TermRelationshipDTO addParentTerm(dss.vector.solutions.ontology.TermDTO parent)
  {
    return (dss.vector.solutions.ontology.TermRelationshipDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.TermRelationshipDTO addParentTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO parent)
  {
    return (dss.vector.solutions.ontology.TermRelationshipDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public void removeParentTerm(dss.vector.solutions.ontology.TermRelationshipDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.ontology.TermRelationshipDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentTerm()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public static void removeAllParentTerm(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllPatientAggregatedIPTs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllPatientAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO> getAllPatientAggregatedIPTsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO> getAllPatientAggregatedIPTsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTPatientsDTO addPatientAggregatedIPTs(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTPatientsDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTPatientsDTO addPatientAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTPatientsDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public void removePatientAggregatedIPTs(dss.vector.solutions.intervention.monitor.IPTPatientsDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePatientAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTPatientsDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPatientAggregatedIPTs()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public static void removeAllPatientAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllPersonsWithTreatmentLocations()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllPersonsWithTreatmentLocations(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO> getAllPersonsWithTreatmentLocationsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO> getAllPersonsWithTreatmentLocationsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO addPersonsWithTreatmentLocations(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO addPersonsWithTreatmentLocations(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.SurveyedPersonDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public void removePersonsWithTreatmentLocations(dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePersonsWithTreatmentLocations(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPersonsWithTreatmentLocations()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public static void removeAllPersonsWithTreatmentLocations(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllPersonsWithTreatments()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllPersonsWithTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO> getAllPersonsWithTreatmentsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO> getAllPersonsWithTreatmentsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO addPersonsWithTreatments(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO addPersonsWithTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.SurveyedPersonDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public void removePersonsWithTreatments(dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePersonsWithTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPersonsWithTreatments()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public static void removeAllPersonsWithTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllReferralAggregatedCase()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllReferralAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseReferralDTO> getAllReferralAggregatedCaseRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseReferralDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseReferralDTO> getAllReferralAggregatedCaseRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseReferralDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseReferralDTO addReferralAggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseReferralDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseReferralDTO addReferralAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseReferralDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public void removeReferralAggregatedCase(dss.vector.solutions.surveillance.CaseReferralDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeReferralAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseReferralDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllReferralAggregatedCase()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public static void removeAllReferralAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllStockAggregatedCases()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllStockAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO> getAllStockAggregatedCasesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO> getAllStockAggregatedCasesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentStockDTO addStockAggregatedCases(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentStockDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentStockDTO addStockAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentStockDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public void removeStockAggregatedCases(dss.vector.solutions.surveillance.CaseTreatmentStockDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeStockAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentStockDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllStockAggregatedCases()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public static void removeAllStockAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllTreatmentAggregatedCases()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllTreatmentAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO> getAllTreatmentAggregatedCasesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO> getAllTreatmentAggregatedCasesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentDTO addTreatmentAggregatedCases(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentDTO addTreatmentAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public void removeTreatmentAggregatedCases(dss.vector.solutions.surveillance.CaseTreatmentDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeTreatmentAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllTreatmentAggregatedCases()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public static void removeAllTreatmentAggregatedCases(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllTreatmentAggregatedIPTs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllTreatmentAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO> getAllTreatmentAggregatedIPTsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO> getAllTreatmentAggregatedIPTsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTTreatmentDTO addTreatmentAggregatedIPTs(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTTreatmentDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTTreatmentDTO addTreatmentAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTTreatmentDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public void removeTreatmentAggregatedIPTs(dss.vector.solutions.intervention.monitor.IPTTreatmentDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeTreatmentAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllTreatmentAggregatedIPTs()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public static void removeAllTreatmentAggregatedIPTs(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllTreatmentMethodAggregatedCase()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllTreatmentMethodAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO> getAllTreatmentMethodAggregatedCaseRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO> getAllTreatmentMethodAggregatedCaseRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentMethodDTO addTreatmentMethodAggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentMethodDTO addTreatmentMethodAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public void removeTreatmentMethodAggregatedCase(dss.vector.solutions.surveillance.CaseTreatmentMethodDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeTreatmentMethodAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllTreatmentMethodAggregatedCase()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public static void removeAllTreatmentMethodAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.TermDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.ontology.TermDTO) dto;
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
  
  public static dss.vector.solutions.ontology.TermQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.ontology.TermQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.ontology.Term", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.ontology.TermDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.ontology.TermDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
