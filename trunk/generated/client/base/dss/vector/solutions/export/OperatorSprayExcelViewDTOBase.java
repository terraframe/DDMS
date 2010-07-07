package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1175826752)
public abstract class OperatorSprayExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.OperatorSprayExcelView";
  private static final long serialVersionUID = 1175826752;
  
  protected OperatorSprayExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BEDNETS = "bedNets";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String HOUSEHOLDID = "householdId";
  public static java.lang.String HOUSEHOLDS = "households";
  public static java.lang.String ID = "id";
  public static java.lang.String LEADERID = "leaderId";
  public static java.lang.String LOCKED = "locked";
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String OPERATORSPRAYWEEK = "operatorSprayWeek";
  public static java.lang.String OTHER = "other";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PREVSPRAYEDHOUSEHOLDS = "prevSprayedHouseholds";
  public static java.lang.String PREVSPRAYEDSTRUCTURES = "prevSprayedStructures";
  public static java.lang.String RECEIVED = "received";
  public static java.lang.String REFILLS = "refills";
  public static java.lang.String REFUSED = "refused";
  public static java.lang.String RETURNED = "returned";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String ROOMSWITHBEDNETS = "roomsWithBedNets";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public static java.lang.String SPRAYEDHOUSEHOLDS = "sprayedHouseholds";
  public static java.lang.String SPRAYEDROOMS = "sprayedRooms";
  public static java.lang.String SPRAYEDSTRUCTURES = "sprayedStructures";
  public static java.lang.String STRUCTUREID = "structureId";
  public static java.lang.String STRUCTURES = "structures";
  public static java.lang.String SURFACETYPE = "surfaceType";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMSPRAYWEEK = "teamSprayWeek";
  public static java.lang.String USED = "used";
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLeaderIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LEADERID).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getOperatorIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OPERATORID).getAttributeMdDTO();
  }
  
  public Integer getOperatorSprayWeek()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORSPRAYWEEK));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOperatorSprayWeekMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORSPRAYWEEK).getAttributeMdDTO();
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
  
  public Integer getReceived()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVED));
  }
  
  public void setReceived(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVED, "");
    }
    else
    {
      setValue(RECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedWritable()
  {
    return isWritable(RECEIVED);
  }
  
  public boolean isReceivedReadable()
  {
    return isReadable(RECEIVED);
  }
  
  public boolean isReceivedModified()
  {
    return isModified(RECEIVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVED).getAttributeMdDTO();
  }
  
  public Integer getRefills()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFILLS));
  }
  
  public void setRefills(Integer value)
  {
    if(value == null)
    {
      setValue(REFILLS, "");
    }
    else
    {
      setValue(REFILLS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRefillsWritable()
  {
    return isWritable(REFILLS);
  }
  
  public boolean isRefillsReadable()
  {
    return isReadable(REFILLS);
  }
  
  public boolean isRefillsModified()
  {
    return isModified(REFILLS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRefillsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFILLS).getAttributeMdDTO();
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
  
  public Integer getReturned()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RETURNED));
  }
  
  public void setReturned(Integer value)
  {
    if(value == null)
    {
      setValue(RETURNED, "");
    }
    else
    {
      setValue(RETURNED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReturnedWritable()
  {
    return isWritable(RETURNED);
  }
  
  public boolean isReturnedReadable()
  {
    return isReadable(RETURNED);
  }
  
  public boolean isReturnedModified()
  {
    return isModified(RETURNED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReturnedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RETURNED).getAttributeMdDTO();
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
  
  public java.util.Date getSprayDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void setSprayDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATE, "");
    }
    else
    {
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getSprayDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATE).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSprayMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYMETHOD).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSprayTeamMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYTEAM).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSurfaceTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SURFACETYPE).getAttributeMdDTO();
  }
  
  public Integer getTarget()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTargetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public Integer getTeamSprayWeek()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMSPRAYWEEK));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTeamSprayWeekMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMSPRAYWEEK).getAttributeMdDTO();
  }
  
  public Integer getUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USED));
  }
  
  public void setUsed(Integer value)
  {
    if(value == null)
    {
      setValue(USED, "");
    }
    else
    {
      setValue(USED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUsedWritable()
  {
    return isWritable(USED);
  }
  
  public boolean isUsedReadable()
  {
    return isReadable(USED);
  }
  
  public boolean isUsedModified()
  {
    return isModified(USED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(USED).getAttributeMdDTO();
  }
  
  public static OperatorSprayExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (OperatorSprayExcelViewDTO) dto;
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
