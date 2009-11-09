package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = 570679451)
public abstract class OntologyRelationshipDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.OntologyRelationship";
  private static final long serialVersionUID = 570679451;
  
  protected OntologyRelationshipDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected OntologyRelationshipDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ALTID = "altId";
  public static java.lang.String COMMENT = "comment";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DEF = "def";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String INVERSEOF = "inverseOf";
  public static java.lang.String INVERSEOFONINSTANCELEVEL = "inverseOfOnInstanceLevel";
  public static java.lang.String ISANTISYMMETRIC = "isAntiSymmetric";
  public static java.lang.String ISBUILTIN = "isBuiltIn";
  public static java.lang.String ISOBSOLETE = "isObsolete";
  public static java.lang.String ISREFLEXIVE = "isReflexive";
  public static java.lang.String ISTRANSITIVE = "isTransitive";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NAME = "name";
  public static java.lang.String NAMESPACE = "namespace";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String RELATIONSHIPID = "relationshipId";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public String getAltId()
  {
    return getValue(ALTID);
  }
  
  public void setAltId(String value)
  {
    if(value == null)
    {
      setValue(ALTID, "");
    }
    else
    {
      setValue(ALTID, value);
    }
  }
  
  public boolean isAltIdWritable()
  {
    return isWritable(ALTID);
  }
  
  public boolean isAltIdReadable()
  {
    return isReadable(ALTID);
  }
  
  public boolean isAltIdModified()
  {
    return isModified(ALTID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAltIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ALTID).getAttributeMdDTO();
  }
  
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
  
  public dss.vector.solutions.ontology.OntologyRelationshipDTO getInverseOf()
  {
    if(getValue(INVERSEOF) == null || getValue(INVERSEOF).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.OntologyRelationshipDTO.get(getRequest(), getValue(INVERSEOF));
    }
  }
  
  public void setInverseOf(dss.vector.solutions.ontology.OntologyRelationshipDTO value)
  {
    if(value == null)
    {
      setValue(INVERSEOF, "");
    }
    else
    {
      setValue(INVERSEOF, value.getId());
    }
  }
  
  public boolean isInverseOfWritable()
  {
    return isWritable(INVERSEOF);
  }
  
  public boolean isInverseOfReadable()
  {
    return isReadable(INVERSEOF);
  }
  
  public boolean isInverseOfModified()
  {
    return isModified(INVERSEOF);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getInverseOfMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INVERSEOF).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.OntologyRelationshipDTO getInverseOfOnInstanceLevel()
  {
    if(getValue(INVERSEOFONINSTANCELEVEL) == null || getValue(INVERSEOFONINSTANCELEVEL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.OntologyRelationshipDTO.get(getRequest(), getValue(INVERSEOFONINSTANCELEVEL));
    }
  }
  
  public void setInverseOfOnInstanceLevel(dss.vector.solutions.ontology.OntologyRelationshipDTO value)
  {
    if(value == null)
    {
      setValue(INVERSEOFONINSTANCELEVEL, "");
    }
    else
    {
      setValue(INVERSEOFONINSTANCELEVEL, value.getId());
    }
  }
  
  public boolean isInverseOfOnInstanceLevelWritable()
  {
    return isWritable(INVERSEOFONINSTANCELEVEL);
  }
  
  public boolean isInverseOfOnInstanceLevelReadable()
  {
    return isReadable(INVERSEOFONINSTANCELEVEL);
  }
  
  public boolean isInverseOfOnInstanceLevelModified()
  {
    return isModified(INVERSEOFONINSTANCELEVEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getInverseOfOnInstanceLevelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INVERSEOFONINSTANCELEVEL).getAttributeMdDTO();
  }
  
  public Boolean getIsAntiSymmetric()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISANTISYMMETRIC));
  }
  
  public void setIsAntiSymmetric(Boolean value)
  {
    if(value == null)
    {
      setValue(ISANTISYMMETRIC, "");
    }
    else
    {
      setValue(ISANTISYMMETRIC, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsAntiSymmetricWritable()
  {
    return isWritable(ISANTISYMMETRIC);
  }
  
  public boolean isIsAntiSymmetricReadable()
  {
    return isReadable(ISANTISYMMETRIC);
  }
  
  public boolean isIsAntiSymmetricModified()
  {
    return isModified(ISANTISYMMETRIC);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsAntiSymmetricMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISANTISYMMETRIC).getAttributeMdDTO();
  }
  
  public Boolean getIsBuiltIn()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISBUILTIN));
  }
  
  public void setIsBuiltIn(Boolean value)
  {
    if(value == null)
    {
      setValue(ISBUILTIN, "");
    }
    else
    {
      setValue(ISBUILTIN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsBuiltInWritable()
  {
    return isWritable(ISBUILTIN);
  }
  
  public boolean isIsBuiltInReadable()
  {
    return isReadable(ISBUILTIN);
  }
  
  public boolean isIsBuiltInModified()
  {
    return isModified(ISBUILTIN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsBuiltInMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISBUILTIN).getAttributeMdDTO();
  }
  
  public Boolean getIsObsolete()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOBSOLETE));
  }
  
  public void setIsObsolete(Boolean value)
  {
    if(value == null)
    {
      setValue(ISOBSOLETE, "");
    }
    else
    {
      setValue(ISOBSOLETE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsObsoleteWritable()
  {
    return isWritable(ISOBSOLETE);
  }
  
  public boolean isIsObsoleteReadable()
  {
    return isReadable(ISOBSOLETE);
  }
  
  public boolean isIsObsoleteModified()
  {
    return isModified(ISOBSOLETE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsObsoleteMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISOBSOLETE).getAttributeMdDTO();
  }
  
  public Boolean getIsReflexive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISREFLEXIVE));
  }
  
  public void setIsReflexive(Boolean value)
  {
    if(value == null)
    {
      setValue(ISREFLEXIVE, "");
    }
    else
    {
      setValue(ISREFLEXIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsReflexiveWritable()
  {
    return isWritable(ISREFLEXIVE);
  }
  
  public boolean isIsReflexiveReadable()
  {
    return isReadable(ISREFLEXIVE);
  }
  
  public boolean isIsReflexiveModified()
  {
    return isModified(ISREFLEXIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsReflexiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISREFLEXIVE).getAttributeMdDTO();
  }
  
  public Boolean getIsTransitive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISTRANSITIVE));
  }
  
  public void setIsTransitive(Boolean value)
  {
    if(value == null)
    {
      setValue(ISTRANSITIVE, "");
    }
    else
    {
      setValue(ISTRANSITIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsTransitiveWritable()
  {
    return isWritable(ISTRANSITIVE);
  }
  
  public boolean isIsTransitiveReadable()
  {
    return isReadable(ISTRANSITIVE);
  }
  
  public boolean isIsTransitiveModified()
  {
    return isModified(ISTRANSITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsTransitiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISTRANSITIVE).getAttributeMdDTO();
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
  
  public String getRelationshipId()
  {
    return getValue(RELATIONSHIPID);
  }
  
  public void setRelationshipId(String value)
  {
    if(value == null)
    {
      setValue(RELATIONSHIPID, "");
    }
    else
    {
      setValue(RELATIONSHIPID, value);
    }
  }
  
  public boolean isRelationshipIdWritable()
  {
    return isWritable(RELATIONSHIPID);
  }
  
  public boolean isRelationshipIdReadable()
  {
    return isReadable(RELATIONSHIPID);
  }
  
  public boolean isRelationshipIdModified()
  {
    return isModified(RELATIONSHIPID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getRelationshipIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RELATIONSHIPID).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.OntologyDTO> getAllOntology()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.OntologyDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.ontology.OntologyHasRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.OntologyDTO> getAllOntology(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.OntologyDTO>) clientRequestIF.getParents(id, dss.vector.solutions.ontology.OntologyHasRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.OntologyHasRelationshipDTO> getAllOntologyRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.OntologyHasRelationshipDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.ontology.OntologyHasRelationshipDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.OntologyHasRelationshipDTO> getAllOntologyRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.OntologyHasRelationshipDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.ontology.OntologyHasRelationshipDTO.CLASS);
  }
  
  public dss.vector.solutions.ontology.OntologyHasRelationshipDTO addOntology(dss.vector.solutions.ontology.OntologyDTO parent)
  {
    return (dss.vector.solutions.ontology.OntologyHasRelationshipDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.ontology.OntologyHasRelationshipDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.OntologyHasRelationshipDTO addOntology(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.OntologyDTO parent)
  {
    return (dss.vector.solutions.ontology.OntologyHasRelationshipDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.ontology.OntologyHasRelationshipDTO.CLASS);
  }
  
  public void removeOntology(dss.vector.solutions.ontology.OntologyHasRelationshipDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeOntology(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.ontology.OntologyHasRelationshipDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllOntology()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.ontology.OntologyHasRelationshipDTO.CLASS);
  }
  
  public static void removeAllOntology(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.ontology.OntologyHasRelationshipDTO.CLASS);
  }
  
  public static dss.vector.solutions.ontology.OntologyRelationshipDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.ontology.OntologyRelationshipDTO) dto;
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
  
  public static dss.vector.solutions.ontology.OntologyRelationshipQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.ontology.OntologyRelationshipQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.ontology.OntologyRelationship", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.ontology.OntologyRelationshipDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.OntologyRelationshipDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.ontology.OntologyRelationshipDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.ontology.OntologyRelationshipDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.ontology.OntologyRelationshipDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.ontology.OntologyRelationshipDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
