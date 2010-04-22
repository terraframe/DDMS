package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 1157116272)
public abstract class CollectionContainerDTOBase extends com.runwaysdk.business.RelationshipDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.CollectionContainer";
  private static final long serialVersionUID = 1157116272;
  
  public CollectionContainerDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String parentId, java.lang.String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected CollectionContainerDTOBase(com.runwaysdk.business.RelationshipDTO relationshipDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
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
  public static java.lang.String NUMBERCONTAINERS = "numberContainers";
  public static java.lang.String NUMBERDESTROYED = "numberDestroyed";
  public static java.lang.String NUMBERIMMATURES = "numberImmatures";
  public static java.lang.String NUMBERLARVAE = "numberLarvae";
  public static java.lang.String NUMBERLARVAECOLLECTED = "numberLarvaeCollected";
  public static java.lang.String NUMBERPUPAE = "numberPupae";
  public static java.lang.String NUMBERPUPAECOLLECTED = "numberPupaeCollected";
  public static java.lang.String NUMBERWITHLARVICIDE = "numberWithLarvicide";
  public static java.lang.String NUMBERWITHWATER = "numberWithWater";
  public static java.lang.String OWNER = "owner";
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
  
  public Integer getNumberContainers()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERCONTAINERS));
  }
  
  public void setNumberContainers(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERCONTAINERS, "");
    }
    else
    {
      setValue(NUMBERCONTAINERS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberContainersWritable()
  {
    return isWritable(NUMBERCONTAINERS);
  }
  
  public boolean isNumberContainersReadable()
  {
    return isReadable(NUMBERCONTAINERS);
  }
  
  public boolean isNumberContainersModified()
  {
    return isModified(NUMBERCONTAINERS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberContainersMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERCONTAINERS).getAttributeMdDTO();
  }
  
  public Integer getNumberDestroyed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERDESTROYED));
  }
  
  public void setNumberDestroyed(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERDESTROYED, "");
    }
    else
    {
      setValue(NUMBERDESTROYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberDestroyedWritable()
  {
    return isWritable(NUMBERDESTROYED);
  }
  
  public boolean isNumberDestroyedReadable()
  {
    return isReadable(NUMBERDESTROYED);
  }
  
  public boolean isNumberDestroyedModified()
  {
    return isModified(NUMBERDESTROYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberDestroyedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERDESTROYED).getAttributeMdDTO();
  }
  
  public Integer getNumberImmatures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERIMMATURES));
  }
  
  public void setNumberImmatures(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERIMMATURES, "");
    }
    else
    {
      setValue(NUMBERIMMATURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberImmaturesWritable()
  {
    return isWritable(NUMBERIMMATURES);
  }
  
  public boolean isNumberImmaturesReadable()
  {
    return isReadable(NUMBERIMMATURES);
  }
  
  public boolean isNumberImmaturesModified()
  {
    return isModified(NUMBERIMMATURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberImmaturesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERIMMATURES).getAttributeMdDTO();
  }
  
  public Integer getNumberLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERLARVAE));
  }
  
  public void setNumberLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERLARVAE, "");
    }
    else
    {
      setValue(NUMBERLARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberLarvaeWritable()
  {
    return isWritable(NUMBERLARVAE);
  }
  
  public boolean isNumberLarvaeReadable()
  {
    return isReadable(NUMBERLARVAE);
  }
  
  public boolean isNumberLarvaeModified()
  {
    return isModified(NUMBERLARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERLARVAE).getAttributeMdDTO();
  }
  
  public Integer getNumberLarvaeCollected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERLARVAECOLLECTED));
  }
  
  public void setNumberLarvaeCollected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERLARVAECOLLECTED, "");
    }
    else
    {
      setValue(NUMBERLARVAECOLLECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberLarvaeCollectedWritable()
  {
    return isWritable(NUMBERLARVAECOLLECTED);
  }
  
  public boolean isNumberLarvaeCollectedReadable()
  {
    return isReadable(NUMBERLARVAECOLLECTED);
  }
  
  public boolean isNumberLarvaeCollectedModified()
  {
    return isModified(NUMBERLARVAECOLLECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberLarvaeCollectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERLARVAECOLLECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPUPAE));
  }
  
  public void setNumberPupae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPUPAE, "");
    }
    else
    {
      setValue(NUMBERPUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPupaeWritable()
  {
    return isWritable(NUMBERPUPAE);
  }
  
  public boolean isNumberPupaeReadable()
  {
    return isReadable(NUMBERPUPAE);
  }
  
  public boolean isNumberPupaeModified()
  {
    return isModified(NUMBERPUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPUPAE).getAttributeMdDTO();
  }
  
  public Integer getNumberPupaeCollected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPUPAECOLLECTED));
  }
  
  public void setNumberPupaeCollected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPUPAECOLLECTED, "");
    }
    else
    {
      setValue(NUMBERPUPAECOLLECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPupaeCollectedWritable()
  {
    return isWritable(NUMBERPUPAECOLLECTED);
  }
  
  public boolean isNumberPupaeCollectedReadable()
  {
    return isReadable(NUMBERPUPAECOLLECTED);
  }
  
  public boolean isNumberPupaeCollectedModified()
  {
    return isModified(NUMBERPUPAECOLLECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPupaeCollectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPUPAECOLLECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberWithLarvicide()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHLARVICIDE));
  }
  
  public void setNumberWithLarvicide(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHLARVICIDE, "");
    }
    else
    {
      setValue(NUMBERWITHLARVICIDE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithLarvicideWritable()
  {
    return isWritable(NUMBERWITHLARVICIDE);
  }
  
  public boolean isNumberWithLarvicideReadable()
  {
    return isReadable(NUMBERWITHLARVICIDE);
  }
  
  public boolean isNumberWithLarvicideModified()
  {
    return isModified(NUMBERWITHLARVICIDE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithLarvicideMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHLARVICIDE).getAttributeMdDTO();
  }
  
  public Integer getNumberWithWater()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHWATER));
  }
  
  public void setNumberWithWater(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHWATER, "");
    }
    else
    {
      setValue(NUMBERWITHWATER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithWaterWritable()
  {
    return isWritable(NUMBERWITHWATER);
  }
  
  public boolean isNumberWithWaterReadable()
  {
    return isReadable(NUMBERWITHWATER);
  }
  
  public boolean isNumberWithWaterModified()
  {
    return isModified(NUMBERWITHWATER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithWaterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHWATER).getAttributeMdDTO();
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
  
  public dss.vector.solutions.entomology.PremiseTaxonDTO getParent()
  {
    return dss.vector.solutions.entomology.PremiseTaxonDTO.get(getRequest(), super.getParentId());
  }
  
    public dss.vector.solutions.ontology.TermDTO getChild()
  {
    return dss.vector.solutions.ontology.TermDTO.get(getRequest(), super.getChildId());
  }
  
  public static dss.vector.solutions.entomology.CollectionContainerDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.RelationshipDTO dto = (com.runwaysdk.business.RelationshipDTO) clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.CollectionContainerDTO) dto;
  }
  
  public static dss.vector.solutions.entomology.CollectionContainerQueryDTO parentQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, String parentId)
  {
    com.runwaysdk.business.RelationshipQueryDTO queryDTO = (com.runwaysdk.business.RelationshipQueryDTO) clientRequest.getQuery(dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
    queryDTO.addCondition("parent_id", "EQ", parentId);
    return (dss.vector.solutions.entomology.CollectionContainerQueryDTO) clientRequest.queryRelationships(queryDTO);
  }
  public static dss.vector.solutions.entomology.CollectionContainerQueryDTO childQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, String childId)
  {
    com.runwaysdk.business.RelationshipQueryDTO queryDTO = (com.runwaysdk.business.RelationshipQueryDTO) clientRequest.getQuery(dss.vector.solutions.entomology.CollectionContainerDTO.CLASS);
    queryDTO.addCondition("child_id", "EQ", childId);
    return (dss.vector.solutions.entomology.CollectionContainerQueryDTO) clientRequest.queryRelationships(queryDTO);
  }
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createRelationship(this);
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
  
  public static dss.vector.solutions.entomology.CollectionContainerQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.CollectionContainerQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.entomology.CollectionContainerDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.CollectionContainerDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.CollectionContainerDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.CollectionContainerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.CollectionContainerDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.CollectionContainerDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.CollectionContainerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
