package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -344788924)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ZoneSprayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ZoneSprayExcelViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.ZoneSprayExcelView";
  public static java.lang.String BEDNETS = "bedNets";
  public static java.lang.String CATTLESHEDS = "cattleSheds";
  public static java.lang.String CATTLESHEDSLOCKED = "cattleShedsLocked";
  public static java.lang.String CATTLESHEDSOTHER = "cattleShedsOther";
  public static java.lang.String CATTLESHEDSREFUSED = "cattleShedsRefused";
  public static java.lang.String CATTLESHEDSSPRAYED = "cattleShedsSprayed";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String HOUSEHOLDS = "households";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDETERM = "insecticideTerm";
  public static java.lang.String LEADERID = "leaderId";
  public static java.lang.String LOCKED = "locked";
  public static java.lang.String NOZZLESUSED = "nozzlesUsed";
  public static java.lang.String NUMBEROFPEOPLE = "numberOfPeople";
  public static java.lang.String OTHER = "other";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PREVSPRAYEDHOUSEHOLDS = "prevSprayedHouseholds";
  public static java.lang.String PREVSPRAYEDSTRUCTURES = "prevSprayedStructures";
  public static java.lang.String PUMPSUSED = "pumpsUsed";
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
  public static java.lang.String SUPERVISORNAME = "supervisorName";
  public static java.lang.String SUPERVISORSURNAME = "supervisorSurname";
  public static java.lang.String SURFACETYPE = "surfaceType";
  public static java.lang.String TEAMRECEIVED = "teamReceived";
  public static java.lang.String TEAMREFILLS = "teamRefills";
  public static java.lang.String TEAMRETURNED = "teamReturned";
  public static java.lang.String TEAMTARGET = "teamTarget";
  public static java.lang.String TEAMUSED = "teamUsed";
  public static java.lang.String VERANDAS = "verandas";
  public static java.lang.String VERANDASLOCKED = "verandasLocked";
  public static java.lang.String VERANDASOTHER = "verandasOther";
  public static java.lang.String VERANDASREFUSED = "verandasRefused";
  public static java.lang.String VERANDASSPRAYED = "verandasSprayed";
  public static java.lang.String WRONGSURFACE = "wrongSurface";
  private static final long serialVersionUID = -344788924;
  
  public ZoneSprayExcelViewBase()
  {
    super();
  }
  
  public Integer getBedNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(BEDNETS));
  }
  
  public void validateBedNets()
  {
    this.validateAttribute(BEDNETS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getBedNetsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
  
  public Integer getCattleSheds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDS));
  }
  
  public void validateCattleSheds()
  {
    this.validateAttribute(CATTLESHEDS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCattleShedsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(CATTLESHEDS);
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
  
  public Integer getCattleShedsLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSLOCKED));
  }
  
  public void validateCattleShedsLocked()
  {
    this.validateAttribute(CATTLESHEDSLOCKED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCattleShedsLockedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(CATTLESHEDSLOCKED);
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
  
  public Integer getCattleShedsOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSOTHER));
  }
  
  public void validateCattleShedsOther()
  {
    this.validateAttribute(CATTLESHEDSOTHER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCattleShedsOtherMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(CATTLESHEDSOTHER);
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
  
  public Integer getCattleShedsRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSREFUSED));
  }
  
  public void validateCattleShedsRefused()
  {
    this.validateAttribute(CATTLESHEDSREFUSED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCattleShedsRefusedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(CATTLESHEDSREFUSED);
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
  
  public Integer getCattleShedsSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSSPRAYED));
  }
  
  public void validateCattleShedsSprayed()
  {
    this.validateAttribute(CATTLESHEDSSPRAYED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCattleShedsSprayedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(CATTLESHEDSSPRAYED);
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
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOUSEHOLDS));
  }
  
  public void validateHouseholds()
  {
    this.validateAttribute(HOUSEHOLDS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getHouseholdsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getInsecticideTerm()
  {
    return getValue(INSECTICIDETERM);
  }
  
  public void validateInsecticideTerm()
  {
    this.validateAttribute(INSECTICIDETERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getInsecticideTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDETERM);
  }
  
  public void setInsecticideTerm(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDETERM, "");
    }
    else
    {
      setValue(INSECTICIDETERM, value);
    }
  }
  
  public String getLeaderId()
  {
    return getValue(LEADERID);
  }
  
  public void validateLeaderId()
  {
    this.validateAttribute(LEADERID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLeaderIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOCKED));
  }
  
  public void validateLocked()
  {
    this.validateAttribute(LOCKED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLockedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
  
  public Integer getNozzlesUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NOZZLESUSED));
  }
  
  public void validateNozzlesUsed()
  {
    this.validateAttribute(NOZZLESUSED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNozzlesUsedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(NOZZLESUSED);
  }
  
  public void setNozzlesUsed(Integer value)
  {
    if(value == null)
    {
      setValue(NOZZLESUSED, "");
    }
    else
    {
      setValue(NOZZLESUSED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberOfPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFPEOPLE));
  }
  
  public void validateNumberOfPeople()
  {
    this.validateAttribute(NUMBEROFPEOPLE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberOfPeopleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(NUMBEROFPEOPLE);
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
  
  public Integer getOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHER));
  }
  
  public void validateOther()
  {
    this.validateAttribute(OTHER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getOtherMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
  }
  
  public void validatePeople()
  {
    this.validateAttribute(PEOPLE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPeopleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDHOUSEHOLDS));
  }
  
  public void validatePrevSprayedHouseholds()
  {
    this.validateAttribute(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPrevSprayedHouseholdsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDSTRUCTURES));
  }
  
  public void validatePrevSprayedStructures()
  {
    this.validateAttribute(PREVSPRAYEDSTRUCTURES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPrevSprayedStructuresMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
  
  public Integer getPumpsUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUMPSUSED));
  }
  
  public void validatePumpsUsed()
  {
    this.validateAttribute(PUMPSUSED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPumpsUsedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(PUMPSUSED);
  }
  
  public void setPumpsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(PUMPSUSED, "");
    }
    else
    {
      setValue(PUMPSUSED, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFUSED));
  }
  
  public void validateRefused()
  {
    this.validateAttribute(REFUSED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getRefusedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
  
  public Integer getRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
  }
  
  public void validateRooms()
  {
    this.validateAttribute(ROOMS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getRoomsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMSWITHBEDNETS));
  }
  
  public void validateRoomsWithBedNets()
  {
    this.validateAttribute(ROOMSWITHBEDNETS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getRoomsWithBedNetsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void validateSprayDate()
  {
    this.validateAttribute(SPRAYDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayTeamMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDHOUSEHOLDS));
  }
  
  public void validateSprayedHouseholds()
  {
    this.validateAttribute(SPRAYEDHOUSEHOLDS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayedHouseholdsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDROOMS));
  }
  
  public void validateSprayedRooms()
  {
    this.validateAttribute(SPRAYEDROOMS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayedRoomsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDSTRUCTURES));
  }
  
  public void validateSprayedStructures()
  {
    this.validateAttribute(SPRAYEDSTRUCTURES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayedStructuresMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURES));
  }
  
  public void validateStructures()
  {
    this.validateAttribute(STRUCTURES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getStructuresMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
  
  public String getSupervisorName()
  {
    return getValue(SUPERVISORNAME);
  }
  
  public void validateSupervisorName()
  {
    this.validateAttribute(SUPERVISORNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSupervisorNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SUPERVISORNAME);
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
  
  public String getSupervisorSurname()
  {
    return getValue(SUPERVISORSURNAME);
  }
  
  public void validateSupervisorSurname()
  {
    this.validateAttribute(SUPERVISORSURNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSupervisorSurnameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(SUPERVISORSURNAME);
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
  
  public String getSurfaceType()
  {
    return getValue(SURFACETYPE);
  }
  
  public void validateSurfaceType()
  {
    this.validateAttribute(SURFACETYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSurfaceTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
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
  
  public Integer getTeamReceived()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMRECEIVED));
  }
  
  public void validateTeamReceived()
  {
    this.validateAttribute(TEAMRECEIVED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTeamReceivedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(TEAMRECEIVED);
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
  
  public Integer getTeamRefills()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMREFILLS));
  }
  
  public void validateTeamRefills()
  {
    this.validateAttribute(TEAMREFILLS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTeamRefillsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(TEAMREFILLS);
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
  
  public Integer getTeamReturned()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMRETURNED));
  }
  
  public void validateTeamReturned()
  {
    this.validateAttribute(TEAMRETURNED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTeamReturnedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(TEAMRETURNED);
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
  
  public Integer getTeamTarget()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMTARGET));
  }
  
  public void validateTeamTarget()
  {
    this.validateAttribute(TEAMTARGET);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTeamTargetMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(TEAMTARGET);
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
  
  public Integer getTeamUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMUSED));
  }
  
  public void validateTeamUsed()
  {
    this.validateAttribute(TEAMUSED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTeamUsedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(TEAMUSED);
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
  
  public Integer getVerandas()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDAS));
  }
  
  public void validateVerandas()
  {
    this.validateAttribute(VERANDAS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getVerandasMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(VERANDAS);
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
  
  public Integer getVerandasLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASLOCKED));
  }
  
  public void validateVerandasLocked()
  {
    this.validateAttribute(VERANDASLOCKED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getVerandasLockedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(VERANDASLOCKED);
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
  
  public Integer getVerandasOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASOTHER));
  }
  
  public void validateVerandasOther()
  {
    this.validateAttribute(VERANDASOTHER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getVerandasOtherMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(VERANDASOTHER);
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
  
  public Integer getVerandasRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASREFUSED));
  }
  
  public void validateVerandasRefused()
  {
    this.validateAttribute(VERANDASREFUSED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getVerandasRefusedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(VERANDASREFUSED);
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
  
  public Integer getVerandasSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASSPRAYED));
  }
  
  public void validateVerandasSprayed()
  {
    this.validateAttribute(VERANDASSPRAYED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getVerandasSprayedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(VERANDASSPRAYED);
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
  
  public Integer getWrongSurface()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WRONGSURFACE));
  }
  
  public void validateWrongSurface()
  {
    this.validateAttribute(WRONGSURFACE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWrongSurfaceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.ZoneSprayExcelView.CLASS);
    return mdClassIF.definesAttribute(WRONGSURFACE);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ZoneSprayExcelView get(String id)
  {
    return (ZoneSprayExcelView) com.runwaysdk.business.View.get(id);
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
