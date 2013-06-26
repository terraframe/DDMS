package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 250276681)
public abstract class SubCollectionDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.SubCollection";
  private static final long serialVersionUID = 250276681;
  
  protected SubCollectionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SubCollectionDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISECTED = "disected";
  public static java.lang.String EGGS = "eggs";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FEMALESFED = "femalesFed";
  public static java.lang.String FEMALESGRAVID = "femalesGravid";
  public static java.lang.String FEMALESHALFGRAVID = "femalesHalfGravid";
  public static java.lang.String FEMALESTOTAL = "femalesTotal";
  public static java.lang.String FEMALESUNFED = "femalesUnfed";
  public static java.lang.String FEMALESUNKNOWN = "femalesUnknown";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LARVAE = "larvae";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MALE = "male";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PAROUS = "parous";
  public static java.lang.String PUPAE = "pupae";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SUBCOLLECTIONID = "subCollectionId";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String TOTAL = "total";
  public static java.lang.String TYPE = "type";
  public static java.lang.String UNKNOWNS = "unknowns";
  public dss.vector.solutions.entomology.MosquitoCollectionDTO getCollection()
  {
    if(getValue(COLLECTION) == null || getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.MosquitoCollectionDTO.get(getRequest(), getValue(COLLECTION));
    }
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTION);
  }
  
  public void setCollection(dss.vector.solutions.entomology.MosquitoCollectionDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTION, "");
    }
    else
    {
      setValue(COLLECTION, value.getId());
    }
  }
  
  public boolean isCollectionWritable()
  {
    return isWritable(COLLECTION);
  }
  
  public boolean isCollectionReadable()
  {
    return isReadable(COLLECTION);
  }
  
  public boolean isCollectionModified()
  {
    return isModified(COLLECTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTION).getAttributeMdDTO();
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
  
  public Integer getDisected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISECTED));
  }
  
  public void setDisected(Integer value)
  {
    if(value == null)
    {
      setValue(DISECTED, "");
    }
    else
    {
      setValue(DISECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisectedWritable()
  {
    return isWritable(DISECTED);
  }
  
  public boolean isDisectedReadable()
  {
    return isReadable(DISECTED);
  }
  
  public boolean isDisectedModified()
  {
    return isModified(DISECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getDisectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISECTED).getAttributeMdDTO();
  }
  
  public Integer getEggs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EGGS));
  }
  
  public void setEggs(Integer value)
  {
    if(value == null)
    {
      setValue(EGGS, "");
    }
    else
    {
      setValue(EGGS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEggsWritable()
  {
    return isWritable(EGGS);
  }
  
  public boolean isEggsReadable()
  {
    return isReadable(EGGS);
  }
  
  public boolean isEggsModified()
  {
    return isModified(EGGS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getEggsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EGGS).getAttributeMdDTO();
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
  
  public Integer getFemalesFed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESFED));
  }
  
  public void setFemalesFed(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESFED, "");
    }
    else
    {
      setValue(FEMALESFED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesFedWritable()
  {
    return isWritable(FEMALESFED);
  }
  
  public boolean isFemalesFedReadable()
  {
    return isReadable(FEMALESFED);
  }
  
  public boolean isFemalesFedModified()
  {
    return isModified(FEMALESFED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesFedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESFED).getAttributeMdDTO();
  }
  
  public Integer getFemalesGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESGRAVID));
  }
  
  public void setFemalesGravid(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESGRAVID, "");
    }
    else
    {
      setValue(FEMALESGRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesGravidWritable()
  {
    return isWritable(FEMALESGRAVID);
  }
  
  public boolean isFemalesGravidReadable()
  {
    return isReadable(FEMALESGRAVID);
  }
  
  public boolean isFemalesGravidModified()
  {
    return isModified(FEMALESGRAVID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesGravidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESGRAVID).getAttributeMdDTO();
  }
  
  public Integer getFemalesHalfGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESHALFGRAVID));
  }
  
  public void setFemalesHalfGravid(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESHALFGRAVID, "");
    }
    else
    {
      setValue(FEMALESHALFGRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesHalfGravidWritable()
  {
    return isWritable(FEMALESHALFGRAVID);
  }
  
  public boolean isFemalesHalfGravidReadable()
  {
    return isReadable(FEMALESHALFGRAVID);
  }
  
  public boolean isFemalesHalfGravidModified()
  {
    return isModified(FEMALESHALFGRAVID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesHalfGravidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESHALFGRAVID).getAttributeMdDTO();
  }
  
  public Integer getFemalesTotal()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESTOTAL));
  }
  
  public void setFemalesTotal(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESTOTAL, "");
    }
    else
    {
      setValue(FEMALESTOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesTotalWritable()
  {
    return isWritable(FEMALESTOTAL);
  }
  
  public boolean isFemalesTotalReadable()
  {
    return isReadable(FEMALESTOTAL);
  }
  
  public boolean isFemalesTotalModified()
  {
    return isModified(FEMALESTOTAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesTotalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESTOTAL).getAttributeMdDTO();
  }
  
  public Integer getFemalesUnfed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNFED));
  }
  
  public void setFemalesUnfed(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESUNFED, "");
    }
    else
    {
      setValue(FEMALESUNFED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesUnfedWritable()
  {
    return isWritable(FEMALESUNFED);
  }
  
  public boolean isFemalesUnfedReadable()
  {
    return isReadable(FEMALESUNFED);
  }
  
  public boolean isFemalesUnfedModified()
  {
    return isModified(FEMALESUNFED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesUnfedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESUNFED).getAttributeMdDTO();
  }
  
  public Integer getFemalesUnknown()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNKNOWN));
  }
  
  public void setFemalesUnknown(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESUNKNOWN, "");
    }
    else
    {
      setValue(FEMALESUNKNOWN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesUnknownWritable()
  {
    return isWritable(FEMALESUNKNOWN);
  }
  
  public boolean isFemalesUnknownReadable()
  {
    return isReadable(FEMALESUNKNOWN);
  }
  
  public boolean isFemalesUnknownModified()
  {
    return isModified(FEMALESUNKNOWN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesUnknownMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESUNKNOWN).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getIdentMethod()
  {
    if(getValue(IDENTMETHOD) == null || getValue(IDENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(IDENTMETHOD));
    }
  }
  
  public String getIdentMethodId()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void setIdentMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value.getId());
    }
  }
  
  public boolean isIdentMethodWritable()
  {
    return isWritable(IDENTMETHOD);
  }
  
  public boolean isIdentMethodReadable()
  {
    return isReadable(IDENTMETHOD);
  }
  
  public boolean isIdentMethodModified()
  {
    return isModified(IDENTMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getIdentMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IDENTMETHOD).getAttributeMdDTO();
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
  
  public Integer getLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LARVAE));
  }
  
  public void setLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(LARVAE, "");
    }
    else
    {
      setValue(LARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLarvaeWritable()
  {
    return isWritable(LARVAE);
  }
  
  public boolean isLarvaeReadable()
  {
    return isReadable(LARVAE);
  }
  
  public boolean isLarvaeModified()
  {
    return isModified(LARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LARVAE).getAttributeMdDTO();
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
  
  public Integer getMale()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MALE));
  }
  
  public void setMale(Integer value)
  {
    if(value == null)
    {
      setValue(MALE, "");
    }
    else
    {
      setValue(MALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMaleWritable()
  {
    return isWritable(MALE);
  }
  
  public boolean isMaleReadable()
  {
    return isReadable(MALE);
  }
  
  public boolean isMaleModified()
  {
    return isModified(MALE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getMaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MALE).getAttributeMdDTO();
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
  
  public Integer getParous()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PAROUS));
  }
  
  public void setParous(Integer value)
  {
    if(value == null)
    {
      setValue(PAROUS, "");
    }
    else
    {
      setValue(PAROUS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isParousWritable()
  {
    return isWritable(PAROUS);
  }
  
  public boolean isParousReadable()
  {
    return isReadable(PAROUS);
  }
  
  public boolean isParousModified()
  {
    return isModified(PAROUS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getParousMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PAROUS).getAttributeMdDTO();
  }
  
  public Integer getPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUPAE));
  }
  
  public void setPupae(Integer value)
  {
    if(value == null)
    {
      setValue(PUPAE, "");
    }
    else
    {
      setValue(PUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPupaeWritable()
  {
    return isWritable(PUPAE);
  }
  
  public boolean isPupaeReadable()
  {
    return isReadable(PUPAE);
  }
  
  public boolean isPupaeModified()
  {
    return isModified(PUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PUPAE).getAttributeMdDTO();
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
  
  public String getSubCollectionId()
  {
    return getValue(SUBCOLLECTIONID);
  }
  
  public void setSubCollectionId(String value)
  {
    if(value == null)
    {
      setValue(SUBCOLLECTIONID, "");
    }
    else
    {
      setValue(SUBCOLLECTIONID, value);
    }
  }
  
  public boolean isSubCollectionIdWritable()
  {
    return isWritable(SUBCOLLECTIONID);
  }
  
  public boolean isSubCollectionIdReadable()
  {
    return isReadable(SUBCOLLECTIONID);
  }
  
  public boolean isSubCollectionIdModified()
  {
    return isModified(SUBCOLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSubCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUBCOLLECTIONID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTaxon()
  {
    if(getValue(TAXON) == null || getValue(TAXON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TAXON));
    }
  }
  
  public String getTaxonId()
  {
    return getValue(TAXON);
  }
  
  public void setTaxon(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TAXON, "");
    }
    else
    {
      setValue(TAXON, value.getId());
    }
  }
  
  public boolean isTaxonWritable()
  {
    return isWritable(TAXON);
  }
  
  public boolean isTaxonReadable()
  {
    return isReadable(TAXON);
  }
  
  public boolean isTaxonModified()
  {
    return isModified(TAXON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTaxonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TAXON).getAttributeMdDTO();
  }
  
  public Integer getTotal()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTAL));
  }
  
  public void setTotal(Integer value)
  {
    if(value == null)
    {
      setValue(TOTAL, "");
    }
    else
    {
      setValue(TOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTotalWritable()
  {
    return isWritable(TOTAL);
  }
  
  public boolean isTotalReadable()
  {
    return isReadable(TOTAL);
  }
  
  public boolean isTotalModified()
  {
    return isModified(TOTAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTotalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTAL).getAttributeMdDTO();
  }
  
  public Integer getUnknowns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNKNOWNS));
  }
  
  public void setUnknowns(Integer value)
  {
    if(value == null)
    {
      setValue(UNKNOWNS, "");
    }
    else
    {
      setValue(UNKNOWNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnknownsWritable()
  {
    return isWritable(UNKNOWNS);
  }
  
  public boolean isUnknownsReadable()
  {
    return isReadable(UNKNOWNS);
  }
  
  public boolean isUnknownsModified()
  {
    return isModified(UNKNOWNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUnknownsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNKNOWNS).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.entomology.SubCollectionViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.SubCollectionViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.SubCollectionViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.entomology.SubCollectionDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.SubCollectionDTO) dto;
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
  
  public static dss.vector.solutions.entomology.SubCollectionQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.SubCollectionQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.SubCollectionDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.SubCollectionDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
