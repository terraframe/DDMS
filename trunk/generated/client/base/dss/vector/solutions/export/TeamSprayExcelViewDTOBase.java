package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -496418349)
public abstract class TeamSprayExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.TeamSprayExcelView";
  private static final long serialVersionUID = -496418349;
  
  protected TeamSprayExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BEDNETS = "bedNets";
  public static java.lang.String BRANDNAME = "brandName";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String HOUSEHOLDS = "households";
  public static java.lang.String ID = "id";
  public static java.lang.String LEADERID = "leaderId";
  public static java.lang.String LOCKED = "locked";
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String OPERATORRECEIVED = "operatorReceived";
  public static java.lang.String OPERATORREFILLS = "operatorRefills";
  public static java.lang.String OPERATORRETURNED = "operatorReturned";
  public static java.lang.String OPERATORSPRAYWEEK = "operatorSprayWeek";
  public static java.lang.String OPERATORUSED = "operatorUsed";
  public static java.lang.String OTHER = "other";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PREVSPRAYEDHOUSEHOLDS = "prevSprayedHouseholds";
  public static java.lang.String PREVSPRAYEDSTRUCTURES = "prevSprayedStructures";
  public static java.lang.String REFUSED = "refused";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String ROOMSWITHBEDNETS = "roomsWithBedNets";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public static java.lang.String SPRAYEDHOUSEHOLDS = "sprayedHouseholds";
  public static java.lang.String SPRAYEDROOMS = "sprayedRooms";
  public static java.lang.String SPRAYEDSTRUCTURES = "sprayedStructures";
  public static java.lang.String STRUCTURES = "structures";
  public static java.lang.String SURFACETYPE = "surfaceType";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMSPRAYWEEK = "teamSprayWeek";
  public Integer getBedNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(BEDNETS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getBedNetsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(BEDNETS).getAttributeMdDTO();
  }
  
  public String getBrandName()
  {
    return getValue(BRANDNAME);
  }
  
  public void setBrandName(String value)
  {
    if(value == null)
    {
      setValue(BRANDNAME, "");
    }
    else
    {
      setValue(BRANDNAME, value);
    }
  }
  
  public boolean isBrandNameWritable()
  {
    return isWritable(BRANDNAME);
  }
  
  public boolean isBrandNameReadable()
  {
    return isReadable(BRANDNAME);
  }
  
  public boolean isBrandNameModified()
  {
    return isModified(BRANDNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBrandNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BRANDNAME).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Integer getHouseholds()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOUSEHOLDS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getHouseholdsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HOUSEHOLDS).getAttributeMdDTO();
  }
  
  public String getLeaderId()
  {
    return getValue(LEADERID);
  }
  
  public void setLeaderId(String value)
  {
    if(value == null)
    {
      setValue(LEADERID, "");
    }
    else
    {
      setValue(LEADERID, value);
    }
  }
  
  public boolean isLeaderIdWritable()
  {
    return isWritable(LEADERID);
  }
  
  public boolean isLeaderIdReadable()
  {
    return isReadable(LEADERID);
  }
  
  public boolean isLeaderIdModified()
  {
    return isModified(LEADERID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLeaderIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEADERID).getAttributeMdDTO();
  }
  
  public Integer getLocked()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOCKED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getLockedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOCKED).getAttributeMdDTO();
  }
  
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void setOperatorId(String value)
  {
    if(value == null)
    {
      setValue(OPERATORID, "");
    }
    else
    {
      setValue(OPERATORID, value);
    }
  }
  
  public boolean isOperatorIdWritable()
  {
    return isWritable(OPERATORID);
  }
  
  public boolean isOperatorIdReadable()
  {
    return isReadable(OPERATORID);
  }
  
  public boolean isOperatorIdModified()
  {
    return isModified(OPERATORID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getOperatorIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OPERATORID).getAttributeMdDTO();
  }
  
  public Integer getOperatorReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORRECEIVED));
  }
  
  public void setOperatorReceived(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORRECEIVED, "");
    }
    else
    {
      setValue(OPERATORRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOperatorReceivedWritable()
  {
    return isWritable(OPERATORRECEIVED);
  }
  
  public boolean isOperatorReceivedReadable()
  {
    return isReadable(OPERATORRECEIVED);
  }
  
  public boolean isOperatorReceivedModified()
  {
    return isModified(OPERATORRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOperatorReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getOperatorRefills()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORREFILLS));
  }
  
  public void setOperatorRefills(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORREFILLS, "");
    }
    else
    {
      setValue(OPERATORREFILLS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOperatorRefillsWritable()
  {
    return isWritable(OPERATORREFILLS);
  }
  
  public boolean isOperatorRefillsReadable()
  {
    return isReadable(OPERATORREFILLS);
  }
  
  public boolean isOperatorRefillsModified()
  {
    return isModified(OPERATORREFILLS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOperatorRefillsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORREFILLS).getAttributeMdDTO();
  }
  
  public Integer getOperatorReturned()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORRETURNED));
  }
  
  public void setOperatorReturned(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORRETURNED, "");
    }
    else
    {
      setValue(OPERATORRETURNED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOperatorReturnedWritable()
  {
    return isWritable(OPERATORRETURNED);
  }
  
  public boolean isOperatorReturnedReadable()
  {
    return isReadable(OPERATORRETURNED);
  }
  
  public boolean isOperatorReturnedModified()
  {
    return isModified(OPERATORRETURNED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOperatorReturnedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORRETURNED).getAttributeMdDTO();
  }
  
  public Integer getOperatorSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORSPRAYWEEK));
  }
  
  public void setOperatorSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORSPRAYWEEK, "");
    }
    else
    {
      setValue(OPERATORSPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOperatorSprayWeekWritable()
  {
    return isWritable(OPERATORSPRAYWEEK);
  }
  
  public boolean isOperatorSprayWeekReadable()
  {
    return isReadable(OPERATORSPRAYWEEK);
  }
  
  public boolean isOperatorSprayWeekModified()
  {
    return isModified(OPERATORSPRAYWEEK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOperatorSprayWeekMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORSPRAYWEEK).getAttributeMdDTO();
  }
  
  public Integer getOperatorUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORUSED));
  }
  
  public void setOperatorUsed(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORUSED, "");
    }
    else
    {
      setValue(OPERATORUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOperatorUsedWritable()
  {
    return isWritable(OPERATORUSED);
  }
  
  public boolean isOperatorUsedReadable()
  {
    return isReadable(OPERATORUSED);
  }
  
  public boolean isOperatorUsedModified()
  {
    return isModified(OPERATORUSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOperatorUsedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORUSED).getAttributeMdDTO();
  }
  
  public Integer getOther()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHER));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOtherMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OTHER).getAttributeMdDTO();
  }
  
  public Integer getPeople()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeopleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PEOPLE).getAttributeMdDTO();
  }
  
  public Integer getPrevSprayedHouseholds()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDHOUSEHOLDS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPrevSprayedHouseholdsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREVSPRAYEDHOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getPrevSprayedStructures()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDSTRUCTURES));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPrevSprayedStructuresMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREVSPRAYEDSTRUCTURES).getAttributeMdDTO();
  }
  
  public Integer getRefused()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFUSED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRefusedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFUSED).getAttributeMdDTO();
  }
  
  public Integer getRooms()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRoomsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMS).getAttributeMdDTO();
  }
  
  public Integer getRoomsWithBedNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMSWITHBEDNETS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRoomsWithBedNetsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMSWITHBEDNETS).getAttributeMdDTO();
  }
  
  public java.util.Date getSprayDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void setSprayDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATE, "");
    }
    else
    {
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isSprayDateWritable()
  {
    return isWritable(SPRAYDATE);
  }
  
  public boolean isSprayDateReadable()
  {
    return isReadable(SPRAYDATE);
  }
  
  public boolean isSprayDateModified()
  {
    return isModified(SPRAYDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getSprayDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATE).getAttributeMdDTO();
  }
  
  public String getSprayMethod()
  {
    return getValue(SPRAYMETHOD);
  }
  
  public void setSprayMethod(String value)
  {
    if(value == null)
    {
      setValue(SPRAYMETHOD, "");
    }
    else
    {
      setValue(SPRAYMETHOD, value);
    }
  }
  
  public boolean isSprayMethodWritable()
  {
    return isWritable(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodReadable()
  {
    return isReadable(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodModified()
  {
    return isModified(SPRAYMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSprayMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYMETHOD).getAttributeMdDTO();
  }
  
  public String getSprayTeam()
  {
    return getValue(SPRAYTEAM);
  }
  
  public void setSprayTeam(String value)
  {
    if(value == null)
    {
      setValue(SPRAYTEAM, "");
    }
    else
    {
      setValue(SPRAYTEAM, value);
    }
  }
  
  public boolean isSprayTeamWritable()
  {
    return isWritable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamReadable()
  {
    return isReadable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamModified()
  {
    return isModified(SPRAYTEAM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSprayTeamMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYTEAM).getAttributeMdDTO();
  }
  
  public Integer getSprayedHouseholds()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDHOUSEHOLDS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSprayedHouseholdsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDHOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getSprayedRooms()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDROOMS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSprayedRoomsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDROOMS).getAttributeMdDTO();
  }
  
  public Integer getSprayedStructures()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDSTRUCTURES));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSprayedStructuresMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDSTRUCTURES).getAttributeMdDTO();
  }
  
  public Integer getStructures()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURES));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getStructuresMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURES).getAttributeMdDTO();
  }
  
  public String getSurfaceType()
  {
    return getValue(SURFACETYPE);
  }
  
  public void setSurfaceType(String value)
  {
    if(value == null)
    {
      setValue(SURFACETYPE, "");
    }
    else
    {
      setValue(SURFACETYPE, value);
    }
  }
  
  public boolean isSurfaceTypeWritable()
  {
    return isWritable(SURFACETYPE);
  }
  
  public boolean isSurfaceTypeReadable()
  {
    return isReadable(SURFACETYPE);
  }
  
  public boolean isSurfaceTypeModified()
  {
    return isModified(SURFACETYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSurfaceTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SURFACETYPE).getAttributeMdDTO();
  }
  
  public Integer getTarget()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
  }
  
  public void setTarget(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTargetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public Integer getTeamSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMSPRAYWEEK));
  }
  
  public void setTeamSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMSPRAYWEEK, "");
    }
    else
    {
      setValue(TEAMSPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTeamSprayWeekWritable()
  {
    return isWritable(TEAMSPRAYWEEK);
  }
  
  public boolean isTeamSprayWeekReadable()
  {
    return isReadable(TEAMSPRAYWEEK);
  }
  
  public boolean isTeamSprayWeekModified()
  {
    return isModified(TEAMSPRAYWEEK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTeamSprayWeekMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMSPRAYWEEK).getAttributeMdDTO();
  }
  
  public static TeamSprayExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (TeamSprayExcelViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
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
  
}
