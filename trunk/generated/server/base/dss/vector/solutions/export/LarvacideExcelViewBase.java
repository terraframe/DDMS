package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -593513612)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvacideExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class LarvacideExcelViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.LarvacideExcelView";
  public static java.lang.String COMPLETIONDATE = "completionDate";
  public static java.lang.String CONTROLMETHOD = "controlMethod";
  public static java.lang.String GEODESCRIPTION = "geoDescription";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NATUREOFCONTROL = "natureOfControl";
  public static java.lang.String PERSONCOUNT = "personCount";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String SUBSTANCE = "substance";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMLEADERID = "teamLeaderId";
  public static java.lang.String TREATED = "treated";
  public static java.lang.String UNIT = "unit";
  public static java.lang.String UNITSUSED = "unitsUsed";
  private static final long serialVersionUID = -593513612;
  
  public LarvacideExcelViewBase()
  {
    super();
  }
  
  public java.util.Date getCompletionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COMPLETIONDATE));
  }
  
  public void validateCompletionDate()
  {
    this.validateAttribute(COMPLETIONDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCompletionDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(COMPLETIONDATE);
  }
  
  public void setCompletionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COMPLETIONDATE, "");
    }
    else
    {
      setValue(COMPLETIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getControlMethod()
  {
    return getValue(CONTROLMETHOD);
  }
  
  public void validateControlMethod()
  {
    this.validateAttribute(CONTROLMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getControlMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(CONTROLMETHOD);
  }
  
  public void setControlMethod(String value)
  {
    if(value == null)
    {
      setValue(CONTROLMETHOD, "");
    }
    else
    {
      setValue(CONTROLMETHOD, value);
    }
  }
  
  public String getGeoDescription()
  {
    return getValue(GEODESCRIPTION);
  }
  
  public void validateGeoDescription()
  {
    this.validateAttribute(GEODESCRIPTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoDescriptionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(GEODESCRIPTION);
  }
  
  public void setGeoDescription(String value)
  {
    if(value == null)
    {
      setValue(GEODESCRIPTION, "");
    }
    else
    {
      setValue(GEODESCRIPTION, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Boolean getNatureOfControl()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NATUREOFCONTROL));
  }
  
  public void validateNatureOfControl()
  {
    this.validateAttribute(NATUREOFCONTROL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNatureOfControlMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(NATUREOFCONTROL);
  }
  
  public void setNatureOfControl(Boolean value)
  {
    if(value == null)
    {
      setValue(NATUREOFCONTROL, "");
    }
    else
    {
      setValue(NATUREOFCONTROL, java.lang.Boolean.toString(value));
    }
  }
  
  public Integer getPersonCount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERSONCOUNT));
  }
  
  public void validatePersonCount()
  {
    this.validateAttribute(PERSONCOUNT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPersonCountMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(PERSONCOUNT);
  }
  
  public void setPersonCount(Integer value)
  {
    if(value == null)
    {
      setValue(PERSONCOUNT, "");
    }
    else
    {
      setValue(PERSONCOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void validateStartDate()
  {
    this.validateAttribute(STARTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getStartDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(STARTDATE);
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getSubstance()
  {
    return getValue(SUBSTANCE);
  }
  
  public void validateSubstance()
  {
    this.validateAttribute(SUBSTANCE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSubstanceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(SUBSTANCE);
  }
  
  public void setSubstance(String value)
  {
    if(value == null)
    {
      setValue(SUBSTANCE, "");
    }
    else
    {
      setValue(SUBSTANCE, value);
    }
  }
  
  public String getTarget()
  {
    return getValue(TARGET);
  }
  
  public void validateTarget()
  {
    this.validateAttribute(TARGET);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTargetMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(TARGET);
  }
  
  public void setTarget(String value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value);
    }
  }
  
  public String getTeamLeaderId()
  {
    return getValue(TEAMLEADERID);
  }
  
  public void validateTeamLeaderId()
  {
    this.validateAttribute(TEAMLEADERID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTeamLeaderIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(TEAMLEADERID);
  }
  
  public void setTeamLeaderId(String value)
  {
    if(value == null)
    {
      setValue(TEAMLEADERID, "");
    }
    else
    {
      setValue(TEAMLEADERID, value);
    }
  }
  
  public Integer getTreated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TREATED));
  }
  
  public void validateTreated()
  {
    this.validateAttribute(TREATED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTreatedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(TREATED);
  }
  
  public void setTreated(Integer value)
  {
    if(value == null)
    {
      setValue(TREATED, "");
    }
    else
    {
      setValue(TREATED, java.lang.Integer.toString(value));
    }
  }
  
  public String getUnit()
  {
    return getValue(UNIT);
  }
  
  public void validateUnit()
  {
    this.validateAttribute(UNIT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUnitMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(UNIT);
  }
  
  public void setUnit(String value)
  {
    if(value == null)
    {
      setValue(UNIT, "");
    }
    else
    {
      setValue(UNIT, value);
    }
  }
  
  public Integer getUnitsUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSUSED));
  }
  
  public void validateUnitsUsed()
  {
    this.validateAttribute(UNITSUSED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUnitsUsedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.LarvacideExcelView.CLASS);
    return mdClassIF.definesAttribute(UNITSUSED);
  }
  
  public void setUnitsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSUSED, "");
    }
    else
    {
      setValue(UNITSUSED, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LarvacideExcelView get(String id)
  {
    return (LarvacideExcelView) com.runwaysdk.business.View.get(id);
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
