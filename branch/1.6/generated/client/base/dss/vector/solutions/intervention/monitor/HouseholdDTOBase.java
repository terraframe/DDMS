package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1166223339)
public abstract class HouseholdDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.Household";
  private static final long serialVersionUID = 1166223339;
  
  protected HouseholdDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected HouseholdDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String HASBEENSPRAYED = "hasBeenSprayed";
  public static java.lang.String HASWINDOWS = "hasWindows";
  public static java.lang.String HOUSEHOLDNAME = "householdName";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTSPRAYED = "lastSprayed";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NETS = "nets";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String ROOF = "roof";
  public static java.lang.String ROOFINFO = "roofInfo";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SURVEYPOINT = "surveyPoint";
  public static java.lang.String TYPE = "type";
  public static java.lang.String URBAN = "urban";
  public static java.lang.String WALL = "wall";
  public static java.lang.String WALLINFO = "wallInfo";
  public static java.lang.String WINDOWTYPE = "windowType";
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.ResponseDTO> getHasBeenSprayed()
  {
    return (java.util.List<dss.vector.solutions.ResponseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.ResponseDTO.CLASS, getEnumNames(HASBEENSPRAYED));
  }
  
  public java.util.List<String> getHasBeenSprayedEnumNames()
  {
    return getEnumNames(HASBEENSPRAYED);
  }
  
  public void addHasBeenSprayed(dss.vector.solutions.ResponseDTO enumDTO)
  {
    addEnumItem(HASBEENSPRAYED, enumDTO.toString());
  }
  
  public void removeHasBeenSprayed(dss.vector.solutions.ResponseDTO enumDTO)
  {
    removeEnumItem(HASBEENSPRAYED, enumDTO.toString());
  }
  
  public void clearHasBeenSprayed()
  {
    clearEnum(HASBEENSPRAYED);
  }
  
  public boolean isHasBeenSprayedWritable()
  {
    return isWritable(HASBEENSPRAYED);
  }
  
  public boolean isHasBeenSprayedReadable()
  {
    return isReadable(HASBEENSPRAYED);
  }
  
  public boolean isHasBeenSprayedModified()
  {
    return isModified(HASBEENSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getHasBeenSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(HASBEENSPRAYED).getAttributeMdDTO();
  }
  
  public Boolean getHasWindows()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HASWINDOWS));
  }
  
  public void setHasWindows(Boolean value)
  {
    if(value == null)
    {
      setValue(HASWINDOWS, "");
    }
    else
    {
      setValue(HASWINDOWS, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isHasWindowsWritable()
  {
    return isWritable(HASWINDOWS);
  }
  
  public boolean isHasWindowsReadable()
  {
    return isReadable(HASWINDOWS);
  }
  
  public boolean isHasWindowsModified()
  {
    return isModified(HASWINDOWS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getHasWindowsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(HASWINDOWS).getAttributeMdDTO();
  }
  
  public String getHouseholdName()
  {
    return getValue(HOUSEHOLDNAME);
  }
  
  public void setHouseholdName(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDNAME, "");
    }
    else
    {
      setValue(HOUSEHOLDNAME, value);
    }
  }
  
  public boolean isHouseholdNameWritable()
  {
    return isWritable(HOUSEHOLDNAME);
  }
  
  public boolean isHouseholdNameReadable()
  {
    return isReadable(HOUSEHOLDNAME);
  }
  
  public boolean isHouseholdNameModified()
  {
    return isModified(HOUSEHOLDNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHouseholdNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDNAME).getAttributeMdDTO();
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
  
  public Integer getLastSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LASTSPRAYED));
  }
  
  public void setLastSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(LASTSPRAYED, "");
    }
    else
    {
      setValue(LASTSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLastSprayedWritable()
  {
    return isWritable(LASTSPRAYED);
  }
  
  public boolean isLastSprayedReadable()
  {
    return isReadable(LASTSPRAYED);
  }
  
  public boolean isLastSprayedModified()
  {
    return isModified(LASTSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLastSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LASTSPRAYED).getAttributeMdDTO();
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
  
  public com.runwaysdk.system.SingleActorDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LOCKEDBY));
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
  
  public Integer getNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETS));
  }
  
  public void setNets(Integer value)
  {
    if(value == null)
    {
      setValue(NETS, "");
    }
    else
    {
      setValue(NETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNetsWritable()
  {
    return isWritable(NETS);
  }
  
  public boolean isNetsReadable()
  {
    return isReadable(NETS);
  }
  
  public boolean isNetsModified()
  {
    return isModified(NETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NETS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getRoof()
  {
    if(getValue(ROOF) == null || getValue(ROOF).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ROOF));
    }
  }
  
  public String getRoofId()
  {
    return getValue(ROOF);
  }
  
  public void setRoof(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ROOF, "");
    }
    else
    {
      setValue(ROOF, value.getId());
    }
  }
  
  public boolean isRoofWritable()
  {
    return isWritable(ROOF);
  }
  
  public boolean isRoofReadable()
  {
    return isReadable(ROOF);
  }
  
  public boolean isRoofModified()
  {
    return isModified(ROOF);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getRoofMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ROOF).getAttributeMdDTO();
  }
  
  public String getRoofInfo()
  {
    return getValue(ROOFINFO);
  }
  
  public void setRoofInfo(String value)
  {
    if(value == null)
    {
      setValue(ROOFINFO, "");
    }
    else
    {
      setValue(ROOFINFO, value);
    }
  }
  
  public boolean isRoofInfoWritable()
  {
    return isWritable(ROOFINFO);
  }
  
  public boolean isRoofInfoReadable()
  {
    return isReadable(ROOFINFO);
  }
  
  public boolean isRoofInfoModified()
  {
    return isModified(ROOFINFO);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRoofInfoMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ROOFINFO).getAttributeMdDTO();
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
  
  public dss.vector.solutions.intervention.monitor.SurveyPointDTO getSurveyPoint()
  {
    if(getValue(SURVEYPOINT) == null || getValue(SURVEYPOINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.SurveyPointDTO.get(getRequest(), getValue(SURVEYPOINT));
    }
  }
  
  public String getSurveyPointId()
  {
    return getValue(SURVEYPOINT);
  }
  
  public void setSurveyPoint(dss.vector.solutions.intervention.monitor.SurveyPointDTO value)
  {
    if(value == null)
    {
      setValue(SURVEYPOINT, "");
    }
    else
    {
      setValue(SURVEYPOINT, value.getId());
    }
  }
  
  public boolean isSurveyPointWritable()
  {
    return isWritable(SURVEYPOINT);
  }
  
  public boolean isSurveyPointReadable()
  {
    return isReadable(SURVEYPOINT);
  }
  
  public boolean isSurveyPointModified()
  {
    return isModified(SURVEYPOINT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSurveyPointMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SURVEYPOINT).getAttributeMdDTO();
  }
  
  public Boolean getUrban()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(URBAN));
  }
  
  public void setUrban(Boolean value)
  {
    if(value == null)
    {
      setValue(URBAN, "");
    }
    else
    {
      setValue(URBAN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isUrbanWritable()
  {
    return isWritable(URBAN);
  }
  
  public boolean isUrbanReadable()
  {
    return isReadable(URBAN);
  }
  
  public boolean isUrbanModified()
  {
    return isModified(URBAN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getUrbanMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(URBAN).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWall()
  {
    if(getValue(WALL) == null || getValue(WALL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WALL));
    }
  }
  
  public String getWallId()
  {
    return getValue(WALL);
  }
  
  public void setWall(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WALL, "");
    }
    else
    {
      setValue(WALL, value.getId());
    }
  }
  
  public boolean isWallWritable()
  {
    return isWritable(WALL);
  }
  
  public boolean isWallReadable()
  {
    return isReadable(WALL);
  }
  
  public boolean isWallModified()
  {
    return isModified(WALL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWallMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WALL).getAttributeMdDTO();
  }
  
  public String getWallInfo()
  {
    return getValue(WALLINFO);
  }
  
  public void setWallInfo(String value)
  {
    if(value == null)
    {
      setValue(WALLINFO, "");
    }
    else
    {
      setValue(WALLINFO, value);
    }
  }
  
  public boolean isWallInfoWritable()
  {
    return isWritable(WALLINFO);
  }
  
  public boolean isWallInfoReadable()
  {
    return isReadable(WALLINFO);
  }
  
  public boolean isWallInfoModified()
  {
    return isModified(WALLINFO);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWallInfoMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WALLINFO).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWindowType()
  {
    if(getValue(WINDOWTYPE) == null || getValue(WINDOWTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WINDOWTYPE));
    }
  }
  
  public String getWindowTypeId()
  {
    return getValue(WINDOWTYPE);
  }
  
  public void setWindowType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WINDOWTYPE, "");
    }
    else
    {
      setValue(WINDOWTYPE, value.getId());
    }
  }
  
  public boolean isWindowTypeWritable()
  {
    return isWritable(WINDOWTYPE);
  }
  
  public boolean isWindowTypeReadable()
  {
    return isReadable(WINDOWTYPE);
  }
  
  public boolean isWindowTypeModified()
  {
    return isModified(WINDOWTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWindowTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WINDOWTYPE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.intervention.monitor.HouseholdViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.HouseholdViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.HouseholdViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.HouseholdViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.HouseholdViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.HouseholdViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.HouseholdViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.HouseholdViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.HouseholdViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.HouseholdViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNInstanceDTO> getAllITNs()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNInstanceDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNInstanceDTO> getAllITNs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNInstanceDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO> getAllITNsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO> getAllITNsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO addITNs(dss.vector.solutions.intervention.monitor.ITNInstanceDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO addITNs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.ITNInstanceDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public void removeITNs(dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeITNs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllITNs()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public static void removeAllITNs(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllSurveyedPeople()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO> getAllSurveyedPeople(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO> getAllSurveyedPeopleRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO> getAllSurveyedPeopleRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO addSurveyedPeople(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO addSurveyedPeople(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.SurveyedPersonDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public void removeSurveyedPeople(dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeSurveyedPeople(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllSurveyedPeople()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public static void removeAllSurveyedPeople(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyPointDTO> getAllSurveyPoints()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyPointDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyPointDTO> getAllSurveyPoints(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyPointDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO> getAllSurveyPointsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO> getAllSurveyPointsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO addSurveyPoints(dss.vector.solutions.intervention.monitor.SurveyPointDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO addSurveyPoints(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.SurveyPointDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO.CLASS);
  }
  
  public void removeSurveyPoints(dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeSurveyPoints(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllSurveyPoints()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO.CLASS);
  }
  
  public static void removeAllSurveyPoints(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.SurveyHouseholdDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.HouseholdDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.HouseholdQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.intervention.monitor.HouseholdDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.HouseholdDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.HouseholdDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.HouseholdDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
