package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1981617529)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TeamSprayExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class TeamSprayExcelViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.TeamSprayExcelView";
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
  public static java.lang.String STRUCTURES = "structures";
  public static java.lang.String SURFACETYPE = "surfaceType";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMSPRAYWEEK = "teamSprayWeek";
  public static java.lang.String USED = "used";
  private static final long serialVersionUID = -1981617529;
  
  public TeamSprayExcelViewBase()
  {
    super();
  }
  
  public Integer getBedNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(BEDNETS));
  }
  
  public void validateBedNets()
  {
    this.validateAttribute(BEDNETS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getBedNetsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(BEDNETS);
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
  
  public String getBrandName()
  {
    return getValue(BRANDNAME);
  }
  
  public void validateBrandName()
  {
    this.validateAttribute(BRANDNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getBrandNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(BRANDNAME);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  public Integer getHouseholds()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOUSEHOLDS));
  }
  
  public void validateHouseholds()
  {
    this.validateAttribute(HOUSEHOLDS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHouseholdsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(HOUSEHOLDS);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getLeaderId()
  {
    return getValue(LEADERID);
  }
  
  public void validateLeaderId()
  {
    this.validateAttribute(LEADERID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLeaderIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(LEADERID);
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
  
  public Integer getLocked()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOCKED));
  }
  
  public void validateLocked()
  {
    this.validateAttribute(LOCKED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLockedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(LOCKED);
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
  
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void validateOperatorId()
  {
    this.validateAttribute(OPERATORID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOperatorIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(OPERATORID);
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
  
  public Integer getOperatorReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORRECEIVED));
  }
  
  public void validateOperatorReceived()
  {
    this.validateAttribute(OPERATORRECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOperatorReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(OPERATORRECEIVED);
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
  
  public Integer getOperatorRefills()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORREFILLS));
  }
  
  public void validateOperatorRefills()
  {
    this.validateAttribute(OPERATORREFILLS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOperatorRefillsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(OPERATORREFILLS);
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
  
  public Integer getOperatorReturned()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORRETURNED));
  }
  
  public void validateOperatorReturned()
  {
    this.validateAttribute(OPERATORRETURNED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOperatorReturnedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(OPERATORRETURNED);
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
  
  public Integer getOperatorSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORSPRAYWEEK));
  }
  
  public void validateOperatorSprayWeek()
  {
    this.validateAttribute(OPERATORSPRAYWEEK);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOperatorSprayWeekMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(OPERATORSPRAYWEEK);
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
  
  public Integer getOperatorUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORUSED));
  }
  
  public void validateOperatorUsed()
  {
    this.validateAttribute(OPERATORUSED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOperatorUsedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(OPERATORUSED);
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
  
  public Integer getOther()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHER));
  }
  
  public void validateOther()
  {
    this.validateAttribute(OTHER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOtherMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(OTHER);
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
  
  public Integer getPeople()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
  }
  
  public void validatePeople()
  {
    this.validateAttribute(PEOPLE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPeopleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(PEOPLE);
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
  
  public Integer getPrevSprayedHouseholds()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDHOUSEHOLDS));
  }
  
  public void validatePrevSprayedHouseholds()
  {
    this.validateAttribute(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPrevSprayedHouseholdsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(PREVSPRAYEDHOUSEHOLDS);
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
  
  public Integer getPrevSprayedStructures()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDSTRUCTURES));
  }
  
  public void validatePrevSprayedStructures()
  {
    this.validateAttribute(PREVSPRAYEDSTRUCTURES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPrevSprayedStructuresMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(PREVSPRAYEDSTRUCTURES);
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
  
  public Integer getReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVED));
  }
  
  public void validateReceived()
  {
    this.validateAttribute(RECEIVED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReceivedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(RECEIVED);
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
  
  public Integer getRefills()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFILLS));
  }
  
  public void validateRefills()
  {
    this.validateAttribute(REFILLS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRefillsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(REFILLS);
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
  
  public Integer getRefused()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFUSED));
  }
  
  public void validateRefused()
  {
    this.validateAttribute(REFUSED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRefusedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(REFUSED);
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
  
  public Integer getReturned()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RETURNED));
  }
  
  public void validateReturned()
  {
    this.validateAttribute(RETURNED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReturnedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(RETURNED);
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
  
  public Integer getRooms()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
  }
  
  public void validateRooms()
  {
    this.validateAttribute(ROOMS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRoomsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(ROOMS);
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
  
  public Integer getRoomsWithBedNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMSWITHBEDNETS));
  }
  
  public void validateRoomsWithBedNets()
  {
    this.validateAttribute(ROOMSWITHBEDNETS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRoomsWithBedNetsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(ROOMSWITHBEDNETS);
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
  
  public java.util.Date getSprayDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void validateSprayDate()
  {
    this.validateAttribute(SPRAYDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPRAYDATE);
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
  
  public String getSprayMethod()
  {
    return getValue(SPRAYMETHOD);
  }
  
  public void validateSprayMethod()
  {
    this.validateAttribute(SPRAYMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPRAYMETHOD);
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
  
  public String getSprayTeam()
  {
    return getValue(SPRAYTEAM);
  }
  
  public void validateSprayTeam()
  {
    this.validateAttribute(SPRAYTEAM);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayTeamMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPRAYTEAM);
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
  
  public Integer getSprayedHouseholds()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDHOUSEHOLDS));
  }
  
  public void validateSprayedHouseholds()
  {
    this.validateAttribute(SPRAYEDHOUSEHOLDS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayedHouseholdsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPRAYEDHOUSEHOLDS);
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
  
  public Integer getSprayedRooms()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDROOMS));
  }
  
  public void validateSprayedRooms()
  {
    this.validateAttribute(SPRAYEDROOMS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayedRoomsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPRAYEDROOMS);
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
  
  public Integer getSprayedStructures()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDSTRUCTURES));
  }
  
  public void validateSprayedStructures()
  {
    this.validateAttribute(SPRAYEDSTRUCTURES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayedStructuresMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPRAYEDSTRUCTURES);
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
  
  public Integer getStructures()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURES));
  }
  
  public void validateStructures()
  {
    this.validateAttribute(STRUCTURES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStructuresMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(STRUCTURES);
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
  
  public String getSurfaceType()
  {
    return getValue(SURFACETYPE);
  }
  
  public void validateSurfaceType()
  {
    this.validateAttribute(SURFACETYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSurfaceTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SURFACETYPE);
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
  
  public Integer getTarget()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
  }
  
  public void validateTarget()
  {
    this.validateAttribute(TARGET);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTargetMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(TARGET);
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
  
  public Integer getTeamSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMSPRAYWEEK));
  }
  
  public void validateTeamSprayWeek()
  {
    this.validateAttribute(TEAMSPRAYWEEK);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTeamSprayWeekMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(TEAMSPRAYWEEK);
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
  
  public Integer getUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USED));
  }
  
  public void validateUsed()
  {
    this.validateAttribute(USED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUsedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TeamSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(USED);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TeamSprayExcelView get(String id)
  {
    return (TeamSprayExcelView) com.terraframe.mojo.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
