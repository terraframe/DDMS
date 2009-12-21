package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 1170282073)
public abstract class ZoneSprayExcelViewDTOBase extends dss.vector.solutions.export.AbstractSprayExcelViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.ZoneSprayExcelView";
  private static final long serialVersionUID = 1170282073;
  
  protected ZoneSprayExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BEDNETS = "bedNets";
  public static java.lang.String HOUSEHOLDS = "households";
  public static java.lang.String LEADERID = "leaderId";
  public static java.lang.String LOCKED = "locked";
  public static java.lang.String OTHER = "other";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PREVSPRAYEDHOUSEHOLDS = "prevSprayedHouseholds";
  public static java.lang.String PREVSPRAYEDSTRUCTURES = "prevSprayedStructures";
  public static java.lang.String REFUSED = "refused";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String ROOMSWITHBEDNETS = "roomsWithBedNets";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public static java.lang.String SPRAYWEEK = "sprayWeek";
  public static java.lang.String SPRAYEDHOUSEHOLDS = "sprayedHouseholds";
  public static java.lang.String SPRAYEDROOMS = "sprayedRooms";
  public static java.lang.String SPRAYEDSTRUCTURES = "sprayedStructures";
  public static java.lang.String STRUCTURES = "structures";
  public static java.lang.String SUPERVISORNAME = "supervisorName";
  public static java.lang.String SUPERVISORSURNAME = "supervisorSurname";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMRECEIVED = "teamReceived";
  public static java.lang.String TEAMREFILLS = "teamRefills";
  public static java.lang.String TEAMRETURNED = "teamReturned";
  public static java.lang.String TEAMSPRAYWEEK = "teamSprayWeek";
  public static java.lang.String TEAMTARGET = "teamTarget";
  public static java.lang.String TEAMUSED = "teamUsed";
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
  
  public Integer getSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYWEEK));
  }
  
  public void setSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYWEEK, "");
    }
    else
    {
      setValue(SPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayWeekWritable()
  {
    return isWritable(SPRAYWEEK);
  }
  
  public boolean isSprayWeekReadable()
  {
    return isReadable(SPRAYWEEK);
  }
  
  public boolean isSprayWeekModified()
  {
    return isModified(SPRAYWEEK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSprayWeekMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYWEEK).getAttributeMdDTO();
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
  
  public String getSupervisorName()
  {
    return getValue(SUPERVISORNAME);
  }
  
  public void setSupervisorName(String value)
  {
    if(value == null)
    {
      setValue(SUPERVISORNAME, "");
    }
    else
    {
      setValue(SUPERVISORNAME, value);
    }
  }
  
  public boolean isSupervisorNameWritable()
  {
    return isWritable(SUPERVISORNAME);
  }
  
  public boolean isSupervisorNameReadable()
  {
    return isReadable(SUPERVISORNAME);
  }
  
  public boolean isSupervisorNameModified()
  {
    return isModified(SUPERVISORNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSupervisorNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUPERVISORNAME).getAttributeMdDTO();
  }
  
  public String getSupervisorSurname()
  {
    return getValue(SUPERVISORSURNAME);
  }
  
  public void setSupervisorSurname(String value)
  {
    if(value == null)
    {
      setValue(SUPERVISORSURNAME, "");
    }
    else
    {
      setValue(SUPERVISORSURNAME, value);
    }
  }
  
  public boolean isSupervisorSurnameWritable()
  {
    return isWritable(SUPERVISORSURNAME);
  }
  
  public boolean isSupervisorSurnameReadable()
  {
    return isReadable(SUPERVISORSURNAME);
  }
  
  public boolean isSupervisorSurnameModified()
  {
    return isModified(SUPERVISORSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSupervisorSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUPERVISORSURNAME).getAttributeMdDTO();
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
  
  public Integer getTeamReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMRECEIVED));
  }
  
  public void setTeamReceived(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMRECEIVED, "");
    }
    else
    {
      setValue(TEAMRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTeamReceivedWritable()
  {
    return isWritable(TEAMRECEIVED);
  }
  
  public boolean isTeamReceivedReadable()
  {
    return isReadable(TEAMRECEIVED);
  }
  
  public boolean isTeamReceivedModified()
  {
    return isModified(TEAMRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTeamReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getTeamRefills()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMREFILLS));
  }
  
  public void setTeamRefills(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMREFILLS, "");
    }
    else
    {
      setValue(TEAMREFILLS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTeamRefillsWritable()
  {
    return isWritable(TEAMREFILLS);
  }
  
  public boolean isTeamRefillsReadable()
  {
    return isReadable(TEAMREFILLS);
  }
  
  public boolean isTeamRefillsModified()
  {
    return isModified(TEAMREFILLS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTeamRefillsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMREFILLS).getAttributeMdDTO();
  }
  
  public Integer getTeamReturned()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMRETURNED));
  }
  
  public void setTeamReturned(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMRETURNED, "");
    }
    else
    {
      setValue(TEAMRETURNED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTeamReturnedWritable()
  {
    return isWritable(TEAMRETURNED);
  }
  
  public boolean isTeamReturnedReadable()
  {
    return isReadable(TEAMRETURNED);
  }
  
  public boolean isTeamReturnedModified()
  {
    return isModified(TEAMRETURNED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTeamReturnedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMRETURNED).getAttributeMdDTO();
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
  
  public Integer getTeamTarget()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMTARGET));
  }
  
  public void setTeamTarget(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMTARGET, "");
    }
    else
    {
      setValue(TEAMTARGET, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTeamTargetWritable()
  {
    return isWritable(TEAMTARGET);
  }
  
  public boolean isTeamTargetReadable()
  {
    return isReadable(TEAMTARGET);
  }
  
  public boolean isTeamTargetModified()
  {
    return isModified(TEAMTARGET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTeamTargetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMTARGET).getAttributeMdDTO();
  }
  
  public Integer getTeamUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMUSED));
  }
  
  public void setTeamUsed(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMUSED, "");
    }
    else
    {
      setValue(TEAMUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTeamUsedWritable()
  {
    return isWritable(TEAMUSED);
  }
  
  public boolean isTeamUsedReadable()
  {
    return isReadable(TEAMUSED);
  }
  
  public boolean isTeamUsedModified()
  {
    return isModified(TEAMUSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTeamUsedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMUSED).getAttributeMdDTO();
  }
  
  public static ZoneSprayExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ZoneSprayExcelViewDTO) dto;
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
