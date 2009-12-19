package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -1718041216)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Household.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class HouseholdBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.Household";
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
  private static final long serialVersionUID = -1718041216;
  
  public HouseholdBase()
  {
    super();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreateDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public com.terraframe.mojo.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityDomainMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.terraframe.mojo.system.metadata.MdDomain value)
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.Response> getHasBeenSprayed()
  {
    return (java.util.List<dss.vector.solutions.Response>) getEnumValues(HASBEENSPRAYED);
  }
  
  public void addHasBeenSprayed(dss.vector.solutions.Response value)
  {
    if(value != null)
    {
      addEnumItem(HASBEENSPRAYED, value.getId());
    }
  }
  
  public void removeHasBeenSprayed(dss.vector.solutions.Response value)
  {
    if(value != null)
    {
      removeEnumItem(HASBEENSPRAYED, value.getId());
    }
  }
  
  public void clearHasBeenSprayed()
  {
    clearEnum(HASBEENSPRAYED);
  }
  
  public void validateHasBeenSprayed()
  {
    this.validateAttribute(HASBEENSPRAYED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHasBeenSprayedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(HASBEENSPRAYED);
  }
  
  public Boolean getHasWindows()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HASWINDOWS));
  }
  
  public void validateHasWindows()
  {
    this.validateAttribute(HASWINDOWS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHasWindowsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(HASWINDOWS);
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
  
  public String getHouseholdName()
  {
    return getValue(HOUSEHOLDNAME);
  }
  
  public void validateHouseholdName()
  {
    this.validateAttribute(HOUSEHOLDNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHouseholdNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(HOUSEHOLDNAME);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(KEYNAME);
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
  
  public Integer getLastSprayed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LASTSPRAYED));
  }
  
  public void validateLastSprayed()
  {
    this.validateAttribute(LASTSPRAYED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastSprayedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(LASTSPRAYED);
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
  
  public java.util.Date getLastUpdateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdateDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.terraframe.mojo.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.Users.get(getValue(LOCKEDBY));
    }
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLockedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Integer getNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETS));
  }
  
  public void validateNets()
  {
    this.validateAttribute(NETS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNetsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(NETS);
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
  
  public com.terraframe.mojo.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.Actor.get(getValue(OWNER));
    }
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOwnerMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.terraframe.mojo.system.Actor value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
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
  
  public dss.vector.solutions.ontology.Term getRoof()
  {
    if (getValue(ROOF).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(ROOF));
    }
  }
  
  public void validateRoof()
  {
    this.validateAttribute(ROOF);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRoofMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(ROOF);
  }
  
  public void setRoof(dss.vector.solutions.ontology.Term value)
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
  
  public String getRoofInfo()
  {
    return getValue(ROOFINFO);
  }
  
  public void validateRoofInfo()
  {
    this.validateAttribute(ROOFINFO);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRoofInfoMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(ROOFINFO);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
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
  
  public Long getSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSeqMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(SEQ);
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyPoint getSurveyPoint()
  {
    if (getValue(SURVEYPOINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.SurveyPoint.get(getValue(SURVEYPOINT));
    }
  }
  
  public void validateSurveyPoint()
  {
    this.validateAttribute(SURVEYPOINT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSurveyPointMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(SURVEYPOINT);
  }
  
  public void setSurveyPoint(dss.vector.solutions.intervention.monitor.SurveyPoint value)
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
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  public Boolean getUrban()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(URBAN));
  }
  
  public void validateUrban()
  {
    this.validateAttribute(URBAN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUrbanMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(URBAN);
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
  
  public dss.vector.solutions.ontology.Term getWall()
  {
    if (getValue(WALL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(WALL));
    }
  }
  
  public void validateWall()
  {
    this.validateAttribute(WALL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWallMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(WALL);
  }
  
  public void setWall(dss.vector.solutions.ontology.Term value)
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
  
  public String getWallInfo()
  {
    return getValue(WALLINFO);
  }
  
  public void validateWallInfo()
  {
    this.validateAttribute(WALLINFO);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWallInfoMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(WALLINFO);
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
  
  public dss.vector.solutions.ontology.Term getWindowType()
  {
    if (getValue(WINDOWTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(WINDOWTYPE));
    }
  }
  
  public void validateWindowType()
  {
    this.validateAttribute(WINDOWTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWindowTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.Household.CLASS);
    return mdClassIF.definesAttribute(WINDOWTYPE);
  }
  
  public void setWindowType(dss.vector.solutions.ontology.Term value)
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static HouseholdQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    HouseholdQuery query = new HouseholdQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdITNInstance addITNs(dss.vector.solutions.intervention.monitor.ITNInstance iTNInstance)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdITNInstance) addChild(iTNInstance, dss.vector.solutions.intervention.monitor.HouseholdITNInstance.CLASS);
  }
  
  public void removeITNs(dss.vector.solutions.intervention.monitor.ITNInstance iTNInstance)
  {
    removeAllChildren(iTNInstance, dss.vector.solutions.intervention.monitor.HouseholdITNInstance.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.ITNInstance> getAllITNs()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.ITNInstance>) getChildren(dss.vector.solutions.intervention.monitor.HouseholdITNInstance.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstance> getAllITNsRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstance>) getChildRelationships(dss.vector.solutions.intervention.monitor.HouseholdITNInstance.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.intervention.monitor.HouseholdITNInstance getITNsRel(dss.vector.solutions.intervention.monitor.ITNInstance iTNInstance)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstance> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstance>) getRelationshipsWithChild(iTNInstance, dss.vector.solutions.intervention.monitor.HouseholdITNInstance.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson addSurveyedPeople(dss.vector.solutions.intervention.monitor.SurveyedPerson surveyedPerson)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson) addChild(surveyedPerson, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson.CLASS);
  }
  
  public void removeSurveyedPeople(dss.vector.solutions.intervention.monitor.SurveyedPerson surveyedPerson)
  {
    removeAllChildren(surveyedPerson, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.SurveyedPerson> getAllSurveyedPeople()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.SurveyedPerson>) getChildren(dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson> getAllSurveyedPeopleRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson>) getChildRelationships(dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson getSurveyedPeopleRel(dss.vector.solutions.intervention.monitor.SurveyedPerson surveyedPerson)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson>) getRelationshipsWithChild(surveyedPerson, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPerson.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyHousehold addSurveyPoints(dss.vector.solutions.intervention.monitor.SurveyPoint surveyPoint)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyHousehold) addParent(surveyPoint, dss.vector.solutions.intervention.monitor.SurveyHousehold.CLASS);
  }
  
  public void removeSurveyPoints(dss.vector.solutions.intervention.monitor.SurveyPoint surveyPoint)
  {
    removeAllParents(surveyPoint, dss.vector.solutions.intervention.monitor.SurveyHousehold.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.SurveyPoint> getAllSurveyPoints()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.SurveyPoint>) getParents(dss.vector.solutions.intervention.monitor.SurveyHousehold.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.SurveyHousehold> getAllSurveyPointsRel()
  {
    return (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.SurveyHousehold>) getParentRelationships(dss.vector.solutions.intervention.monitor.SurveyHousehold.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.intervention.monitor.SurveyHousehold getSurveyPointsRel(dss.vector.solutions.intervention.monitor.SurveyPoint surveyPoint)
  {
    com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.SurveyHousehold> iterator = (com.terraframe.mojo.query.OIterator<? extends dss.vector.solutions.intervention.monitor.SurveyHousehold>) getRelationshipsWithParent(surveyPoint, dss.vector.solutions.intervention.monitor.SurveyHousehold.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public static Household get(String id)
  {
    return (Household) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static Household getByKey(String key)
  {
    return (Household) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.Household.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.Household.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.HouseholdView lockView(java.lang.String id)
  {
    Household _instance = Household.get(id);
    return _instance.lockView();
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdView unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.Household.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.HouseholdView unlockView(java.lang.String id)
  {
    Household _instance = Household.get(id);
    return _instance.unlockView();
  }
  
  public static Household lock(java.lang.String id)
  {
    Household _instance = Household.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Household unlock(java.lang.String id)
  {
    Household _instance = Household.get(id);
    _instance.unlock();
    
    return _instance;
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
