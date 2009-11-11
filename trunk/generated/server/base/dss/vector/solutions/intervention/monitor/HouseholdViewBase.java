package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -496664981)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to HouseholdView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class HouseholdViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.HouseholdView";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISPLAYNETS = "displayNets";
  public static java.lang.String HASWINDOWS = "hasWindows";
  public static java.lang.String HOUSEHOLDNAME = "householdName";
  public static java.lang.String ID = "id";
  public static java.lang.String LASTSPRAYED = "lastSprayed";
  public static java.lang.String NETS = "nets";
  public static java.lang.String NETSUSED = "netsUsed";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String ROOF = "roof";
  public static java.lang.String ROOFINFO = "roofInfo";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String SLEPTUNDERNETS = "sleptUnderNets";
  public static java.lang.String SURVEYPOINT = "surveyPoint";
  public static java.lang.String URBAN = "urban";
  public static java.lang.String WALL = "wall";
  public static java.lang.String WALLINFO = "wallInfo";
  public static java.lang.String WINDOWTYPE = "windowType";
  private static final long serialVersionUID = -496664981;
  
  public HouseholdViewBase()
  {
    super();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public dss.vector.solutions.ontology.Term getDisplayNets()
  {
    if (getValue(DISPLAYNETS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DISPLAYNETS));
    }
  }
  
  public void validateDisplayNets()
  {
    this.validateAttribute(DISPLAYNETS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDisplayNetsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
    return mdClassIF.definesAttribute(DISPLAYNETS);
  }
  
  public void setDisplayNets(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DISPLAYNETS, "");
    }
    else
    {
      setValue(DISPLAYNETS, value.getId());
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
    return mdClassIF.definesAttribute(ID);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
  
  public Integer getNetsUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETSUSED));
  }
  
  public void validateNetsUsed()
  {
    this.validateAttribute(NETSUSED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNetsUsedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
    return mdClassIF.definesAttribute(NETSUSED);
  }
  
  public void setNetsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(NETSUSED, "");
    }
    else
    {
      setValue(NETSUSED, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
  
  public Integer getSleptUnderNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SLEPTUNDERNETS));
  }
  
  public void validateSleptUnderNets()
  {
    this.validateAttribute(SLEPTUNDERNETS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSleptUnderNetsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
    return mdClassIF.definesAttribute(SLEPTUNDERNETS);
  }
  
  public void setSleptUnderNets(Integer value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNETS, "");
    }
    else
    {
      setValue(SLEPTUNDERNETS, java.lang.Integer.toString(value));
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.HouseholdView.CLASS);
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
  
  public static HouseholdView get(String id)
  {
    return (HouseholdView) com.terraframe.mojo.business.View.get(id);
  }
  
  public void applyAll(dss.vector.solutions.intervention.monitor.HouseholdNet[] nets)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.HouseholdView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyAll(java.lang.String id, dss.vector.solutions.intervention.monitor.HouseholdNet[] nets)
  {
    HouseholdView _instance = HouseholdView.get(id);
    _instance.applyAll(nets);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.HouseholdView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    HouseholdView _instance = HouseholdView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdNet[] getHouseholdNets()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.HouseholdView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.HouseholdNet[] getHouseholdNets(java.lang.String id)
  {
    HouseholdView _instance = HouseholdView.get(id);
    return _instance.getHouseholdNets();
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
