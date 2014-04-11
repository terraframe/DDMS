package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1430877996)
public abstract class HouseholdSprayStatusDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.HouseholdSprayStatus";
  private static final long serialVersionUID = -1430877996;
  
  protected HouseholdSprayStatusDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected HouseholdSprayStatusDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BEDNETS = "bedNets";
  public static java.lang.String CATTLESHEDS = "cattleSheds";
  public static java.lang.String CATTLESHEDSLOCKED = "cattleShedsLocked";
  public static java.lang.String CATTLESHEDSOTHER = "cattleShedsOther";
  public static java.lang.String CATTLESHEDSREFUSED = "cattleShedsRefused";
  public static java.lang.String CATTLESHEDSSPRAYED = "cattleShedsSprayed";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String HOUSEHOLDID = "householdId";
  public static java.lang.String HOUSEHOLDS = "households";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKED = "locked";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NUMBEROFPEOPLE = "numberOfPeople";
  public static java.lang.String OTHER = "other";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PREVSPRAYEDHOUSEHOLDS = "prevSprayedHouseholds";
  public static java.lang.String PREVSPRAYEDSTRUCTURES = "prevSprayedStructures";
  public static java.lang.String REFUSED = "refused";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String ROOMSWITHBEDNETS = "roomsWithBedNets";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SPRAY = "spray";
  public static java.lang.String SPRAYEDHOUSEHOLDS = "sprayedHouseholds";
  public static java.lang.String SPRAYEDROOMS = "sprayedRooms";
  public static java.lang.String SPRAYEDSTRUCTURES = "sprayedStructures";
  public static java.lang.String STRUCTUREID = "structureId";
  public static java.lang.String STRUCTURES = "structures";
  public static java.lang.String TYPE = "type";
  public static java.lang.String VERANDAS = "verandas";
  public static java.lang.String VERANDASLOCKED = "verandasLocked";
  public static java.lang.String VERANDASOTHER = "verandasOther";
  public static java.lang.String VERANDASREFUSED = "verandasRefused";
  public static java.lang.String VERANDASSPRAYED = "verandasSprayed";
  public static java.lang.String WRONGSURFACE = "wrongSurface";
  public Integer getBedNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(BEDNETS));
  }
  
  public void setBedNets(Integer value)
  {
    if(value == null)
    {
      setValue(BEDNETS, "");
    }
    else
    {
      setValue(BEDNETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isBedNetsWritable()
  {
    return isWritable(BEDNETS);
  }
  
  public boolean isBedNetsReadable()
  {
    return isReadable(BEDNETS);
  }
  
  public boolean isBedNetsModified()
  {
    return isModified(BEDNETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getBedNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(BEDNETS).getAttributeMdDTO();
  }
  
  public Integer getCattleSheds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDS));
  }
  
  public void setCattleSheds(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDS, "");
    }
    else
    {
      setValue(CATTLESHEDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsWritable()
  {
    return isWritable(CATTLESHEDS);
  }
  
  public boolean isCattleShedsReadable()
  {
    return isReadable(CATTLESHEDS);
  }
  
  public boolean isCattleShedsModified()
  {
    return isModified(CATTLESHEDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDS).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSLOCKED));
  }
  
  public void setCattleShedsLocked(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSLOCKED, "");
    }
    else
    {
      setValue(CATTLESHEDSLOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsLockedWritable()
  {
    return isWritable(CATTLESHEDSLOCKED);
  }
  
  public boolean isCattleShedsLockedReadable()
  {
    return isReadable(CATTLESHEDSLOCKED);
  }
  
  public boolean isCattleShedsLockedModified()
  {
    return isModified(CATTLESHEDSLOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSLOCKED).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSOTHER));
  }
  
  public void setCattleShedsOther(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSOTHER, "");
    }
    else
    {
      setValue(CATTLESHEDSOTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsOtherWritable()
  {
    return isWritable(CATTLESHEDSOTHER);
  }
  
  public boolean isCattleShedsOtherReadable()
  {
    return isReadable(CATTLESHEDSOTHER);
  }
  
  public boolean isCattleShedsOtherModified()
  {
    return isModified(CATTLESHEDSOTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSOTHER).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSREFUSED));
  }
  
  public void setCattleShedsRefused(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSREFUSED, "");
    }
    else
    {
      setValue(CATTLESHEDSREFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsRefusedWritable()
  {
    return isWritable(CATTLESHEDSREFUSED);
  }
  
  public boolean isCattleShedsRefusedReadable()
  {
    return isReadable(CATTLESHEDSREFUSED);
  }
  
  public boolean isCattleShedsRefusedModified()
  {
    return isModified(CATTLESHEDSREFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSREFUSED).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSSPRAYED));
  }
  
  public void setCattleShedsSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSSPRAYED, "");
    }
    else
    {
      setValue(CATTLESHEDSSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsSprayedWritable()
  {
    return isWritable(CATTLESHEDSSPRAYED);
  }
  
  public boolean isCattleShedsSprayedReadable()
  {
    return isReadable(CATTLESHEDSSPRAYED);
  }
  
  public boolean isCattleShedsSprayedModified()
  {
    return isModified(CATTLESHEDSSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSSPRAYED).getAttributeMdDTO();
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
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLDID);
  }
  
  public void setHouseholdId(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDID, "");
    }
    else
    {
      setValue(HOUSEHOLDID, value);
    }
  }
  
  public boolean isHouseholdIdWritable()
  {
    return isWritable(HOUSEHOLDID);
  }
  
  public boolean isHouseholdIdReadable()
  {
    return isReadable(HOUSEHOLDID);
  }
  
  public boolean isHouseholdIdModified()
  {
    return isModified(HOUSEHOLDID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHouseholdIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDID).getAttributeMdDTO();
  }
  
  public Integer getHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOUSEHOLDS));
  }
  
  public void setHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDS, "");
    }
    else
    {
      setValue(HOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isHouseholdsWritable()
  {
    return isWritable(HOUSEHOLDS);
  }
  
  public boolean isHouseholdsReadable()
  {
    return isReadable(HOUSEHOLDS);
  }
  
  public boolean isHouseholdsModified()
  {
    return isModified(HOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HOUSEHOLDS).getAttributeMdDTO();
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
  
  public Integer getLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOCKED));
  }
  
  public void setLocked(Integer value)
  {
    if(value == null)
    {
      setValue(LOCKED, "");
    }
    else
    {
      setValue(LOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLockedWritable()
  {
    return isWritable(LOCKED);
  }
  
  public boolean isLockedReadable()
  {
    return isReadable(LOCKED);
  }
  
  public boolean isLockedModified()
  {
    return isModified(LOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOCKED).getAttributeMdDTO();
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
  
  public Integer getNumberOfPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFPEOPLE));
  }
  
  public void setNumberOfPeople(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFPEOPLE, "");
    }
    else
    {
      setValue(NUMBEROFPEOPLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfPeopleWritable()
  {
    return isWritable(NUMBEROFPEOPLE);
  }
  
  public boolean isNumberOfPeopleReadable()
  {
    return isReadable(NUMBEROFPEOPLE);
  }
  
  public boolean isNumberOfPeopleModified()
  {
    return isModified(NUMBEROFPEOPLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfPeopleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFPEOPLE).getAttributeMdDTO();
  }
  
  public Integer getOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHER));
  }
  
  public void setOther(Integer value)
  {
    if(value == null)
    {
      setValue(OTHER, "");
    }
    else
    {
      setValue(OTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOtherWritable()
  {
    return isWritable(OTHER);
  }
  
  public boolean isOtherReadable()
  {
    return isReadable(OTHER);
  }
  
  public boolean isOtherModified()
  {
    return isModified(OTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OTHER).getAttributeMdDTO();
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
  
  public Integer getPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
  }
  
  public void setPeople(Integer value)
  {
    if(value == null)
    {
      setValue(PEOPLE, "");
    }
    else
    {
      setValue(PEOPLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeopleWritable()
  {
    return isWritable(PEOPLE);
  }
  
  public boolean isPeopleReadable()
  {
    return isReadable(PEOPLE);
  }
  
  public boolean isPeopleModified()
  {
    return isModified(PEOPLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeopleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PEOPLE).getAttributeMdDTO();
  }
  
  public Integer getPrevSprayedHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDHOUSEHOLDS));
  }
  
  public void setPrevSprayedHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(PREVSPRAYEDHOUSEHOLDS, "");
    }
    else
    {
      setValue(PREVSPRAYEDHOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPrevSprayedHouseholdsWritable()
  {
    return isWritable(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public boolean isPrevSprayedHouseholdsReadable()
  {
    return isReadable(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public boolean isPrevSprayedHouseholdsModified()
  {
    return isModified(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPrevSprayedHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREVSPRAYEDHOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getPrevSprayedStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDSTRUCTURES));
  }
  
  public void setPrevSprayedStructures(Integer value)
  {
    if(value == null)
    {
      setValue(PREVSPRAYEDSTRUCTURES, "");
    }
    else
    {
      setValue(PREVSPRAYEDSTRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPrevSprayedStructuresWritable()
  {
    return isWritable(PREVSPRAYEDSTRUCTURES);
  }
  
  public boolean isPrevSprayedStructuresReadable()
  {
    return isReadable(PREVSPRAYEDSTRUCTURES);
  }
  
  public boolean isPrevSprayedStructuresModified()
  {
    return isModified(PREVSPRAYEDSTRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPrevSprayedStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREVSPRAYEDSTRUCTURES).getAttributeMdDTO();
  }
  
  public Integer getRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFUSED));
  }
  
  public void setRefused(Integer value)
  {
    if(value == null)
    {
      setValue(REFUSED, "");
    }
    else
    {
      setValue(REFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRefusedWritable()
  {
    return isWritable(REFUSED);
  }
  
  public boolean isRefusedReadable()
  {
    return isReadable(REFUSED);
  }
  
  public boolean isRefusedModified()
  {
    return isModified(REFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFUSED).getAttributeMdDTO();
  }
  
  public Integer getRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
  }
  
  public void setRooms(Integer value)
  {
    if(value == null)
    {
      setValue(ROOMS, "");
    }
    else
    {
      setValue(ROOMS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRoomsWritable()
  {
    return isWritable(ROOMS);
  }
  
  public boolean isRoomsReadable()
  {
    return isReadable(ROOMS);
  }
  
  public boolean isRoomsModified()
  {
    return isModified(ROOMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRoomsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMS).getAttributeMdDTO();
  }
  
  public Integer getRoomsWithBedNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMSWITHBEDNETS));
  }
  
  public void setRoomsWithBedNets(Integer value)
  {
    if(value == null)
    {
      setValue(ROOMSWITHBEDNETS, "");
    }
    else
    {
      setValue(ROOMSWITHBEDNETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRoomsWithBedNetsWritable()
  {
    return isWritable(ROOMSWITHBEDNETS);
  }
  
  public boolean isRoomsWithBedNetsReadable()
  {
    return isReadable(ROOMSWITHBEDNETS);
  }
  
  public boolean isRoomsWithBedNetsModified()
  {
    return isModified(ROOMSWITHBEDNETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRoomsWithBedNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMSWITHBEDNETS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.irs.OperatorSprayDTO getSpray()
  {
    if(getValue(SPRAY) == null || getValue(SPRAY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.OperatorSprayDTO.get(getRequest(), getValue(SPRAY));
    }
  }
  
  public String getSprayId()
  {
    return getValue(SPRAY);
  }
  
  public void setSpray(dss.vector.solutions.irs.OperatorSprayDTO value)
  {
    if(value == null)
    {
      setValue(SPRAY, "");
    }
    else
    {
      setValue(SPRAY, value.getId());
    }
  }
  
  public boolean isSprayWritable()
  {
    return isWritable(SPRAY);
  }
  
  public boolean isSprayReadable()
  {
    return isReadable(SPRAY);
  }
  
  public boolean isSprayModified()
  {
    return isModified(SPRAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSprayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAY).getAttributeMdDTO();
  }
  
  public Integer getSprayedHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDHOUSEHOLDS));
  }
  
  public void setSprayedHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDHOUSEHOLDS, "");
    }
    else
    {
      setValue(SPRAYEDHOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedHouseholdsWritable()
  {
    return isWritable(SPRAYEDHOUSEHOLDS);
  }
  
  public boolean isSprayedHouseholdsReadable()
  {
    return isReadable(SPRAYEDHOUSEHOLDS);
  }
  
  public boolean isSprayedHouseholdsModified()
  {
    return isModified(SPRAYEDHOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDHOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getSprayedRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDROOMS));
  }
  
  public void setSprayedRooms(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDROOMS, "");
    }
    else
    {
      setValue(SPRAYEDROOMS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedRoomsWritable()
  {
    return isWritable(SPRAYEDROOMS);
  }
  
  public boolean isSprayedRoomsReadable()
  {
    return isReadable(SPRAYEDROOMS);
  }
  
  public boolean isSprayedRoomsModified()
  {
    return isModified(SPRAYEDROOMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedRoomsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDROOMS).getAttributeMdDTO();
  }
  
  public Integer getSprayedStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDSTRUCTURES));
  }
  
  public void setSprayedStructures(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDSTRUCTURES, "");
    }
    else
    {
      setValue(SPRAYEDSTRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedStructuresWritable()
  {
    return isWritable(SPRAYEDSTRUCTURES);
  }
  
  public boolean isSprayedStructuresReadable()
  {
    return isReadable(SPRAYEDSTRUCTURES);
  }
  
  public boolean isSprayedStructuresModified()
  {
    return isModified(SPRAYEDSTRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDSTRUCTURES).getAttributeMdDTO();
  }
  
  public String getStructureId()
  {
    return getValue(STRUCTUREID);
  }
  
  public void setStructureId(String value)
  {
    if(value == null)
    {
      setValue(STRUCTUREID, "");
    }
    else
    {
      setValue(STRUCTUREID, value);
    }
  }
  
  public boolean isStructureIdWritable()
  {
    return isWritable(STRUCTUREID);
  }
  
  public boolean isStructureIdReadable()
  {
    return isReadable(STRUCTUREID);
  }
  
  public boolean isStructureIdModified()
  {
    return isModified(STRUCTUREID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getStructureIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(STRUCTUREID).getAttributeMdDTO();
  }
  
  public Integer getStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURES));
  }
  
  public void setStructures(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURES, "");
    }
    else
    {
      setValue(STRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresWritable()
  {
    return isWritable(STRUCTURES);
  }
  
  public boolean isStructuresReadable()
  {
    return isReadable(STRUCTURES);
  }
  
  public boolean isStructuresModified()
  {
    return isModified(STRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURES).getAttributeMdDTO();
  }
  
  public Integer getVerandas()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDAS));
  }
  
  public void setVerandas(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDAS, "");
    }
    else
    {
      setValue(VERANDAS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasWritable()
  {
    return isWritable(VERANDAS);
  }
  
  public boolean isVerandasReadable()
  {
    return isReadable(VERANDAS);
  }
  
  public boolean isVerandasModified()
  {
    return isModified(VERANDAS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDAS).getAttributeMdDTO();
  }
  
  public Integer getVerandasLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASLOCKED));
  }
  
  public void setVerandasLocked(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASLOCKED, "");
    }
    else
    {
      setValue(VERANDASLOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasLockedWritable()
  {
    return isWritable(VERANDASLOCKED);
  }
  
  public boolean isVerandasLockedReadable()
  {
    return isReadable(VERANDASLOCKED);
  }
  
  public boolean isVerandasLockedModified()
  {
    return isModified(VERANDASLOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASLOCKED).getAttributeMdDTO();
  }
  
  public Integer getVerandasOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASOTHER));
  }
  
  public void setVerandasOther(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASOTHER, "");
    }
    else
    {
      setValue(VERANDASOTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasOtherWritable()
  {
    return isWritable(VERANDASOTHER);
  }
  
  public boolean isVerandasOtherReadable()
  {
    return isReadable(VERANDASOTHER);
  }
  
  public boolean isVerandasOtherModified()
  {
    return isModified(VERANDASOTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASOTHER).getAttributeMdDTO();
  }
  
  public Integer getVerandasRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASREFUSED));
  }
  
  public void setVerandasRefused(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASREFUSED, "");
    }
    else
    {
      setValue(VERANDASREFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasRefusedWritable()
  {
    return isWritable(VERANDASREFUSED);
  }
  
  public boolean isVerandasRefusedReadable()
  {
    return isReadable(VERANDASREFUSED);
  }
  
  public boolean isVerandasRefusedModified()
  {
    return isModified(VERANDASREFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASREFUSED).getAttributeMdDTO();
  }
  
  public Integer getVerandasSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASSPRAYED));
  }
  
  public void setVerandasSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASSPRAYED, "");
    }
    else
    {
      setValue(VERANDASSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasSprayedWritable()
  {
    return isWritable(VERANDASSPRAYED);
  }
  
  public boolean isVerandasSprayedReadable()
  {
    return isReadable(VERANDASSPRAYED);
  }
  
  public boolean isVerandasSprayedModified()
  {
    return isModified(VERANDASSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASSPRAYED).getAttributeMdDTO();
  }
  
  public Integer getWrongSurface()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WRONGSURFACE));
  }
  
  public void setWrongSurface(Integer value)
  {
    if(value == null)
    {
      setValue(WRONGSURFACE, "");
    }
    else
    {
      setValue(WRONGSURFACE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWrongSurfaceWritable()
  {
    return isWritable(WRONGSURFACE);
  }
  
  public boolean isWrongSurfaceReadable()
  {
    return isReadable(WRONGSURFACE);
  }
  
  public boolean isWrongSurfaceModified()
  {
    return isModified(WRONGSURFACE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWrongSurfaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WRONGSURFACE).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.irs.HouseholdSprayStatusDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.HouseholdSprayStatusDTO) dto;
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
  
  public static dss.vector.solutions.irs.HouseholdSprayStatusQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.HouseholdSprayStatusQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.HouseholdSprayStatusDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.HouseholdSprayStatusDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.HouseholdSprayStatusDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
