package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = 56160277)
public abstract class TermDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.Term";
  private static final long serialVersionUID = 56160277;
  
  protected TermDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TermDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String TERMDISPLAYLABEL = "termDisplayLabel";
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getCommentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(COMMENT).getAttributeMdDTO();
  }
  
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getDefMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(DEF).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NAME).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNamespaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NAMESPACE).getAttributeMdDTO();
  }
  
  public Boolean getObsolete()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(OBSOLETE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getObsoleteMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(OBSOLETE).getAttributeMdDTO();
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
  
  public String getOntologyId()
  {
    return getValue(ONTOLOGY);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOntologyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ONTOLOGY).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermTermDisplayLabelDTO getTermDisplayLabel()
  {
    return (dss.vector.solutions.ontology.TermTermDisplayLabelDTO) this.getAttributeStructDTO(TERMDISPLAYLABEL).getStructDTO();
  }
  
  public boolean isTermDisplayLabelWritable()
  {
    return isWritable(TERMDISPLAYLABEL);
  }
  
  public boolean isTermDisplayLabelReadable()
  {
    return isReadable(TERMDISPLAYLABEL);
  }
  
  public boolean isTermDisplayLabelModified()
  {
    return isModified(TERMDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalCharacterMdDTO getTermDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalCharacterMdDTO) getAttributeDTO(TERMDISPLAYLABEL).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMID).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.ontology.TermViewDTO applyWithParent(java.lang.String parentTermId, java.lang.Boolean cloneOperation, java.lang.String oldParentId, java.lang.Boolean inactive)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{parentTermId, cloneOperation, oldParentId, inactive};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "applyWithParent", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewDTO applyWithParent(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentTermId, java.lang.Boolean cloneOperation, java.lang.String oldParentId, java.lang.Boolean inactive)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, parentTermId, cloneOperation, oldParentId, inactive};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "applyWithParent", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String[] checkForNestedTerms(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String[] termIds)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{termIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "checkForNestedTerms", _declaredTypes);
    return (java.lang.String[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void confirmChangeParent(java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{parentId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "confirmChangeParent", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void confirmChangeParent(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "confirmChangeParent", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void confirmDeleteTerm(java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{parentId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "confirmDeleteTerm", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void confirmDeleteTerm(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "confirmDeleteTerm", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteRelationship(java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{parentId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "deleteRelationship", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteRelationship(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "deleteRelationship", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteTerm()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "deleteTerm", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteTerm(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "deleteTerm", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermDTO[] getAllTermsForField(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String className, java.lang.String attributeName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{className, attributeName};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getAllTermsForField", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getByIds(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String[] termIds)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{termIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getByIds", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getDefaultRoots(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getDefaultRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.ontology.InactivePropertyDTO getInactiveByDisease()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getInactiveByDisease", _declaredTypes);
    return (dss.vector.solutions.ontology.InactivePropertyDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.InactivePropertyDTO getInactiveByDisease(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getInactiveByDisease", _declaredTypes);
    return (dss.vector.solutions.ontology.InactivePropertyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.ontology.TermViewQueryDTO getOntologyChildren(java.lang.Boolean filterObsolete)
  {
    String[] _declaredTypes = new String[]{"java.lang.Boolean"};
    Object[] _parameters = new Object[]{filterObsolete};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getOntologyChildren", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getOntologyChildren(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Boolean filterObsolete)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, filterObsolete};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getOntologyChildren", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermDTO[] getRootChildren(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String className, java.lang.String attributeName, java.lang.Boolean returnOnlySelectable)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{className, attributeName, returnOnlySelectable};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getRootChildren", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO getRoots(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String relationshipType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{relationshipType};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermDTO[] getSortedRootChildren(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String className, java.lang.String attributeName, java.lang.Boolean returnOnlySelectable)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{className, attributeName, returnOnlySelectable};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getSortedRootChildren", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewDTO getTermById(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String termId, java.lang.String[] parameters)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{termId, parameters};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "getTermById", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO searchByRoots(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value, java.lang.String[][] roots)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{value, roots};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "searchByRoots", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO searchTerms(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String searchValue, java.lang.String[] parentTermIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{searchValue, parentTermIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "searchTerms", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermViewQueryDTO searchTermsWithRoots(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value, java.lang.String[] parameters)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{value, parameters};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "searchTermsWithRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.TermViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO termQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value, java.lang.String[] parentTermIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{value, parentTermIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "termQuery", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO termQueryByIds(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String[] termIds)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{termIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "termQueryByIds", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO termQueryWithRoots(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value, java.lang.String[] parameters)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{value, parameters};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "termQueryWithRoots", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateFromTree(java.lang.Boolean inactive)
  {
    String[] _declaredTypes = new String[]{"java.lang.Boolean"};
    Object[] _parameters = new Object[]{inactive};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "updateFromTree", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateFromTree(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Boolean inactive)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, inactive};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "updateFromTree", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllChildTerm()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllChildTerm(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO> getAllChildTermRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO> getAllChildTermRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public dss.vector.solutions.ontology.TermRelationshipDTO addChildTerm(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.ontology.TermRelationshipDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.TermRelationshipDTO addChildTerm(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.ontology.TermRelationshipDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public void removeChildTerm(dss.vector.solutions.ontology.TermRelationshipDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeChildTerm(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.ontology.TermRelationshipDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllChildTerm()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public static void removeAllChildTerm(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.InactivePropertyDTO> getAllInactiveProperties()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.InactivePropertyDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.ontology.InactiveByDiseaseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.InactivePropertyDTO> getAllInactiveProperties(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.InactivePropertyDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.ontology.InactiveByDiseaseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.InactiveByDiseaseDTO> getAllInactivePropertiesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.InactiveByDiseaseDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.ontology.InactiveByDiseaseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.InactiveByDiseaseDTO> getAllInactivePropertiesRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.InactiveByDiseaseDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.ontology.InactiveByDiseaseDTO.CLASS);
  }
  
  public dss.vector.solutions.ontology.InactiveByDiseaseDTO addInactiveProperties(dss.vector.solutions.ontology.InactivePropertyDTO child)
  {
    return (dss.vector.solutions.ontology.InactiveByDiseaseDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.ontology.InactiveByDiseaseDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.InactiveByDiseaseDTO addInactiveProperties(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.InactivePropertyDTO child)
  {
    return (dss.vector.solutions.ontology.InactiveByDiseaseDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.ontology.InactiveByDiseaseDTO.CLASS);
  }
  
  public void removeInactiveProperties(dss.vector.solutions.ontology.InactiveByDiseaseDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeInactiveProperties(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.ontology.InactiveByDiseaseDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllInactiveProperties()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.ontology.InactiveByDiseaseDTO.CLASS);
  }
  
  public static void removeAllInactiveProperties(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.ontology.InactiveByDiseaseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllANCVisitAggregatedIPTs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllANCVisitAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO> getAllANCVisitAggregatedIPTsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO> getAllANCVisitAggregatedIPTsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTANCVisitDTO addANCVisitAggregatedIPTs(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTANCVisitDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTANCVisitDTO addANCVisitAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTANCVisitDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public void removeANCVisitAggregatedIPTs(dss.vector.solutions.intervention.monitor.IPTANCVisitDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeANCVisitAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllANCVisitAggregatedIPTs()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public static void removeAllANCVisitAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithNets()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNNetDTO> getAllAggregatedITNsWithNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNNetDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNNetDTO> getAllAggregatedITNsWithNetsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNNetDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNNetDTO addAggregatedITNsWithNets(dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNNetDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNNetDTO addAggregatedITNsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNNetDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  public void removeAggregatedITNsWithNets(dss.vector.solutions.intervention.monitor.ITNNetDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedITNsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNNetDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedITNsWithNets()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  public static void removeAllAggregatedITNsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithService()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithService(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNServiceDTO> getAllAggregatedITNsWithServiceRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNServiceDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNServiceDTO> getAllAggregatedITNsWithServiceRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNServiceDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNServiceDTO addAggregatedITNsWithService(dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNServiceDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNServiceDTO addAggregatedITNsWithService(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNServiceDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  public void removeAggregatedITNsWithService(dss.vector.solutions.intervention.monitor.ITNServiceDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedITNsWithService(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNServiceDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedITNsWithService()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  public static void removeAllAggregatedITNsWithService(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNServiceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithTargetGroups()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO> getAllAggregatedITNsWithTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDataDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO> getAllAggregatedITNsWithTargetGroupsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO> getAllAggregatedITNsWithTargetGroupsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO addAggregatedITNsWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO addAggregatedITNsWithTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNDataDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  public void removeAggregatedITNsWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedITNsWithTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedITNsWithTargetGroups()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  public static void removeAllAggregatedITNsWithTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO> getAllAggregatedPremiseMethod()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO> getAllAggregatedPremiseMethod(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO> getAllAggregatedPremiseMethodRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO> getAllAggregatedPremiseMethodRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO addAggregatedPremiseMethod(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO addAggregatedPremiseMethod(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO.CLASS);
  }
  
  public void removeAggregatedPremiseMethod(dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedPremiseMethod(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedPremiseMethod()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO.CLASS);
  }
  
  public static void removeAllAggregatedPremiseMethod(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO> getAllAggregatedPremiseReasons()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO> getAllAggregatedPremiseReasons(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO> getAllAggregatedPremiseReasonsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO> getAllAggregatedPremiseReasonsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO addAggregatedPremiseReasons(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO addAggregatedPremiseReasons(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO.CLASS);
  }
  
  public void removeAggregatedPremiseReasons(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedPremiseReasons(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedPremiseReasons()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO.CLASS);
  }
  
  public static void removeAllAggregatedPremiseReasons(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO> getAllCaseDiagnosisTypes()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO> getAllCaseDiagnosisTypes(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO> getAllCaseDiagnosisTypesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO> getAllCaseDiagnosisTypesRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO addCaseDiagnosisTypes(dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO addCaseDiagnosisTypes(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO.CLASS);
  }
  
  public void removeCaseDiagnosisTypes(dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeCaseDiagnosisTypes(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllCaseDiagnosisTypes()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO.CLASS);
  }
  
  public static void removeAllCaseDiagnosisTypes(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseDiseaseManifestationDTO> getAllCaseDiseaseManifestations()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiseaseManifestationDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseDiseaseManifestationDTO> getAllCaseDiseaseManifestations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiseaseManifestationDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO> getAllCaseDiseaseManifestationsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO> getAllCaseDiseaseManifestationsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO addCaseDiseaseManifestations(dss.vector.solutions.surveillance.CaseDiseaseManifestationDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO addCaseDiseaseManifestations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.CaseDiseaseManifestationDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO.CLASS);
  }
  
  public void removeCaseDiseaseManifestations(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeCaseDiseaseManifestations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllCaseDiseaseManifestations()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO.CLASS);
  }
  
  public static void removeAllCaseDiseaseManifestations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CasePatientTypeDTO> getAllCasePatientTypes()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CasePatientTypeDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CasePatientTypeAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CasePatientTypeDTO> getAllCasePatientTypes(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CasePatientTypeDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CasePatientTypeAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CasePatientTypeAmountDTO> getAllCasePatientTypesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CasePatientTypeAmountDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CasePatientTypeAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CasePatientTypeAmountDTO> getAllCasePatientTypesRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CasePatientTypeAmountDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CasePatientTypeAmountDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CasePatientTypeAmountDTO addCasePatientTypes(dss.vector.solutions.surveillance.CasePatientTypeDTO parent)
  {
    return (dss.vector.solutions.surveillance.CasePatientTypeAmountDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CasePatientTypeAmountDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CasePatientTypeAmountDTO addCasePatientTypes(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.CasePatientTypeDTO parent)
  {
    return (dss.vector.solutions.surveillance.CasePatientTypeAmountDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CasePatientTypeAmountDTO.CLASS);
  }
  
  public void removeCasePatientTypes(dss.vector.solutions.surveillance.CasePatientTypeAmountDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeCasePatientTypes(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CasePatientTypeAmountDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllCasePatientTypes()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CasePatientTypeAmountDTO.CLASS);
  }
  
  public static void removeAllCasePatientTypes(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CasePatientTypeAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllDoseAggregatedIPTs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllDoseAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO> getAllDoseAggregatedIPTsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO> getAllDoseAggregatedIPTsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTDoseDTO addDoseAggregatedIPTs(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTDoseDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTDoseDTO addDoseAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTDoseDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public void removeDoseAggregatedIPTs(dss.vector.solutions.intervention.monitor.IPTDoseDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeDoseAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTDoseDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllDoseAggregatedIPTs()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public static void removeAllDoseAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO> getAllITNCommunityDistributionsWithNets()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO> getAllITNCommunityDistributionsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO> getAllITNCommunityDistributionsWithNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO> getAllITNCommunityDistributionsWithNetsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO addITNCommunityDistributionsWithNets(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO addITNCommunityDistributionsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public void removeITNCommunityDistributionsWithNets(dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNCommunityDistributionsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNCommunityDistributionsWithNets()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public static void removeAllITNCommunityDistributionsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO> getAllITNCommunityDistributionsWithTargetGroups()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO> getAllITNCommunityDistributionsWithTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO> getAllITNCommunityDistributionsWithTargetGroupsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO> getAllITNCommunityDistributionsWithTargetGroupsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO addITNCommunityDistributionsWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO addITNCommunityDistributionsWithTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public void removeITNCommunityDistributionsWithTargetGroups(dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNCommunityDistributionsWithTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNCommunityDistributionsWithTargetGroups()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public static void removeAllITNCommunityDistributionsWithTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionDTO> getAllITNFacilityDistributionsWithNets()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionDTO> getAllITNFacilityDistributionsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO> getAllITNFacilityDistributionsWithNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO> getAllITNFacilityDistributionsWithNetsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO addITNFacilityDistributionsWithNets(dss.vector.solutions.intervention.monitor.ITNDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO addITNFacilityDistributionsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNDistributionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public void removeITNFacilityDistributionsWithNets(dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeITNFacilityDistributionsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllITNFacilityDistributionsWithNets()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public static void removeAllITNFacilityDistributionsWithNets(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualInstanceDTO> getAllIndividualInstance()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualInstanceDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualInstanceDTO> getAllIndividualInstance(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualInstanceDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO> getAllIndividualInstanceRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO> getAllIndividualInstanceRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.IndividualCaseSymptomDTO addIndividualInstance(dss.vector.solutions.intervention.monitor.IndividualInstanceDTO parent)
  {
    return (dss.vector.solutions.surveillance.IndividualCaseSymptomDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.IndividualCaseSymptomDTO addIndividualInstance(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.IndividualInstanceDTO parent)
  {
    return (dss.vector.solutions.surveillance.IndividualCaseSymptomDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public void removeIndividualInstance(dss.vector.solutions.surveillance.IndividualCaseSymptomDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeIndividualInstance(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllIndividualInstance()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public static void removeAllIndividualInstance(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualPremiseVisitDTO> getAllIndividualPremiseVisits()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualPremiseVisitDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualPremiseVisitDTO> getAllIndividualPremiseVisits(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualPremiseVisitDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO> getAllIndividualPremiseVisitsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO> getAllIndividualPremiseVisitsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO addIndividualPremiseVisits(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO addIndividualPremiseVisits(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO.CLASS);
  }
  
  public void removeIndividualPremiseVisits(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeIndividualPremiseVisits(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllIndividualPremiseVisits()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO.CLASS);
  }
  
  public static void removeAllIndividualPremiseVisits(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllParentTerm()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllParentTerm(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getParents(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO> getAllParentTermRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO> getAllParentTermRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermRelationshipDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public dss.vector.solutions.ontology.TermRelationshipDTO addParentTerm(dss.vector.solutions.ontology.TermDTO parent)
  {
    return (dss.vector.solutions.ontology.TermRelationshipDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.TermRelationshipDTO addParentTerm(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO parent)
  {
    return (dss.vector.solutions.ontology.TermRelationshipDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public void removeParentTerm(dss.vector.solutions.ontology.TermRelationshipDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeParentTerm(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.ontology.TermRelationshipDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllParentTerm()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  public static void removeAllParentTerm(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.ontology.TermRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllPatientAggregatedIPTs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllPatientAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO> getAllPatientAggregatedIPTsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO> getAllPatientAggregatedIPTsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTPatientsDTO addPatientAggregatedIPTs(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTPatientsDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTPatientsDTO addPatientAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTPatientsDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public void removePatientAggregatedIPTs(dss.vector.solutions.intervention.monitor.IPTPatientsDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePatientAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTPatientsDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPatientAggregatedIPTs()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public static void removeAllPatientAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonInterventionDTO> getAllPersonInterventionMethod()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonInterventionDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonInterventionDTO> getAllPersonInterventionMethod(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonInterventionDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO> getAllPersonInterventionMethodRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO> getAllPersonInterventionMethodRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO addPersonInterventionMethod(dss.vector.solutions.intervention.monitor.PersonInterventionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO addPersonInterventionMethod(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.PersonInterventionDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO.CLASS);
  }
  
  public void removePersonInterventionMethod(dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePersonInterventionMethod(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPersonInterventionMethod()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO.CLASS);
  }
  
  public static void removeAllPersonInterventionMethod(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.PersonInterventionMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllPersonsWithTreatmentLocations()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllPersonsWithTreatmentLocations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO> getAllPersonsWithTreatmentLocationsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO> getAllPersonsWithTreatmentLocationsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO addPersonsWithTreatmentLocations(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO addPersonsWithTreatmentLocations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.SurveyedPersonDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public void removePersonsWithTreatmentLocations(dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePersonsWithTreatmentLocations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPersonsWithTreatmentLocations()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public static void removeAllPersonsWithTreatmentLocations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllPersonsWithTreatments()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllPersonsWithTreatments(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO> getAllPersonsWithTreatmentsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO> getAllPersonsWithTreatmentsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO addPersonsWithTreatments(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO addPersonsWithTreatments(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.SurveyedPersonDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public void removePersonsWithTreatments(dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePersonsWithTreatments(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPersonsWithTreatments()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public static void removeAllPersonsWithTreatments(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.PremiseTaxonDTO> getAllPremiseTaxons()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.PremiseTaxonDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.PremiseTaxonDTO> getAllPremiseTaxons(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.PremiseTaxonDTO>) clientRequestIF.getParents(id, dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.CollectionContainerDTO> getAllPremiseTaxonsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.CollectionContainerDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.CollectionContainerDTO> getAllPremiseTaxonsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.CollectionContainerDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.CollectionContainerDTO addPremiseTaxons(dss.vector.solutions.entomology.PremiseTaxonDTO parent)
  {
    return (dss.vector.solutions.entomology.CollectionContainerDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.CollectionContainerDTO addPremiseTaxons(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.entomology.PremiseTaxonDTO parent)
  {
    return (dss.vector.solutions.entomology.CollectionContainerDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
  }
  
  public void removePremiseTaxons(dss.vector.solutions.entomology.CollectionContainerDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePremiseTaxons(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.CollectionContainerDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPremiseTaxons()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
  }
  
  public static void removeAllPremiseTaxons(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.PupalContainerDTO> getAllPupalContainerAmounts()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.PupalContainerDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.PupalContainerDTO> getAllPupalContainerAmounts(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.PupalContainerDTO>) clientRequestIF.getParents(id, dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.PupalContainerAmountDTO> getAllPupalContainerAmountsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.PupalContainerAmountDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.PupalContainerAmountDTO> getAllPupalContainerAmountsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.PupalContainerAmountDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.PupalContainerAmountDTO addPupalContainerAmounts(dss.vector.solutions.entomology.PupalContainerDTO parent)
  {
    return (dss.vector.solutions.entomology.PupalContainerAmountDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.PupalContainerAmountDTO addPupalContainerAmounts(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.entomology.PupalContainerDTO parent)
  {
    return (dss.vector.solutions.entomology.PupalContainerAmountDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public void removePupalContainerAmounts(dss.vector.solutions.entomology.PupalContainerAmountDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePupalContainerAmounts(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.PupalContainerAmountDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPupalContainerAmounts()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  public static void removeAllPupalContainerAmounts(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.entomology.PupalContainerAmountDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllTreatmentAggregatedIPTs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO> getAllTreatmentAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.AggregatedIPTDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO> getAllTreatmentAggregatedIPTsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO> getAllTreatmentAggregatedIPTsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTTreatmentDTO addTreatmentAggregatedIPTs(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTTreatmentDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTTreatmentDTO addTreatmentAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.AggregatedIPTDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.IPTTreatmentDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public void removeTreatmentAggregatedIPTs(dss.vector.solutions.intervention.monitor.IPTTreatmentDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeTreatmentAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllTreatmentAggregatedIPTs()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public static void removeAllTreatmentAggregatedIPTs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.TermDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.ontology.TermQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.ontology.TermQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.ontology.TermDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.ontology.TermDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.ontology.TermDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.TermDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
