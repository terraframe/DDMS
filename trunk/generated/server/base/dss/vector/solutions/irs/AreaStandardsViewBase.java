package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1727684120)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AreaStandardsView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AreaStandardsViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.AreaStandardsView";
  public static java.lang.String AREASTANDARDSID = "areaStandardsId";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String ROOM = "room";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String STRUCTUREAREA = "structureArea";
  public static java.lang.String TARGETUNIT = "targetUnit";
  public static java.lang.String UNITNOZZLEAREACOVERAGE = "unitNozzleAreaCoverage";
  private static final long serialVersionUID = -1727684120;
  
  public AreaStandardsViewBase()
  {
    super();
  }
  
  public String getAreaStandardsId()
  {
    return getValue(AREASTANDARDSID);
  }
  
  public void validateAreaStandardsId()
  {
    this.validateAttribute(AREASTANDARDSID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAreaStandardsIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(AREASTANDARDSID);
  }
  
  public void setAreaStandardsId(String value)
  {
    if(value == null)
    {
      setValue(AREASTANDARDSID, "");
    }
    else
    {
      setValue(AREASTANDARDSID, value);
    }
  }
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void validateEndDate()
  {
    this.validateAttribute(ENDDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEndDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(ENDDATE);
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public Float getHousehold()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(HOUSEHOLD));
  }
  
  public void validateHousehold()
  {
    this.validateAttribute(HOUSEHOLD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getHouseholdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(HOUSEHOLD);
  }
  
  public void setHousehold(Float value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLD, "");
    }
    else
    {
      setValue(HOUSEHOLD, java.lang.Float.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Float getRoom()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(ROOM));
  }
  
  public void validateRoom()
  {
    this.validateAttribute(ROOM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getRoomMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(ROOM);
  }
  
  public void setRoom(Float value)
  {
    if(value == null)
    {
      setValue(ROOM, "");
    }
    else
    {
      setValue(ROOM, java.lang.Float.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
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
  
  public Float getStructureArea()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(STRUCTUREAREA));
  }
  
  public void validateStructureArea()
  {
    this.validateAttribute(STRUCTUREAREA);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getStructureAreaMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(STRUCTUREAREA);
  }
  
  public void setStructureArea(Float value)
  {
    if(value == null)
    {
      setValue(STRUCTUREAREA, "");
    }
    else
    {
      setValue(STRUCTUREAREA, java.lang.Float.toString(value));
    }
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.TargetUnit> getTargetUnit()
  {
    return (java.util.List<dss.vector.solutions.irs.TargetUnit>) getEnumValues(TARGETUNIT);
  }
  
  public void addTargetUnit(dss.vector.solutions.irs.TargetUnit value)
  {
    if(value != null)
    {
      addEnumItem(TARGETUNIT, value.getId());
    }
  }
  
  public void removeTargetUnit(dss.vector.solutions.irs.TargetUnit value)
  {
    if(value != null)
    {
      removeEnumItem(TARGETUNIT, value.getId());
    }
  }
  
  public void clearTargetUnit()
  {
    clearEnum(TARGETUNIT);
  }
  
  public void validateTargetUnit()
  {
    this.validateAttribute(TARGETUNIT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTargetUnitMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(TARGETUNIT);
  }
  
  public Float getUnitNozzleAreaCoverage()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(UNITNOZZLEAREACOVERAGE));
  }
  
  public void validateUnitNozzleAreaCoverage()
  {
    this.validateAttribute(UNITNOZZLEAREACOVERAGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUnitNozzleAreaCoverageMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(UNITNOZZLEAREACOVERAGE);
  }
  
  public void setUnitNozzleAreaCoverage(Float value)
  {
    if(value == null)
    {
      setValue(UNITNOZZLEAREACOVERAGE, "");
    }
    else
    {
      setValue(UNITNOZZLEAREACOVERAGE, java.lang.Float.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AreaStandardsView get(String id)
  {
    return (AreaStandardsView) com.runwaysdk.business.View.get(id);
  }
  
  public void applyClone()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.AreaStandardsView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyClone(java.lang.String id)
  {
    AreaStandardsView _instance = AreaStandardsView.get(id);
    _instance.applyClone();
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.AreaStandardsView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    AreaStandardsView _instance = AreaStandardsView.get(id);
    _instance.deleteConcrete();
  }
  
  public static dss.vector.solutions.irs.AreaStandardsView getMostRecent()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.AreaStandardsView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.AreaStandardsViewQuery getPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.AreaStandardsView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
