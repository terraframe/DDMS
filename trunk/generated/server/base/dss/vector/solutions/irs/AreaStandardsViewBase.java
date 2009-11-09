package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1363805580)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AreaStandardsView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AreaStandardsViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.AreaStandardsView";
  public static java.lang.String AREASTANDARDSID = "areaStandardsId";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String ROOM = "room";
  public static java.lang.String STRUCTUREAREA = "structureArea";
  public static java.lang.String TARGETUNIT = "targetUnit";
  public static java.lang.String UNITNOZZLEAREACOVERAGE = "unitNozzleAreaCoverage";
  private static final long serialVersionUID = 1363805580;
  
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAreaStandardsIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
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
  
  public Float getHousehold()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(HOUSEHOLD));
  }
  
  public void validateHousehold()
  {
    this.validateAttribute(HOUSEHOLD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHouseholdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Float getRoom()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(ROOM));
  }
  
  public void validateRoom()
  {
    this.validateAttribute(ROOM);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRoomMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
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
  
  public Float getStructureArea()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(STRUCTUREAREA));
  }
  
  public void validateStructureArea()
  {
    this.validateAttribute(STRUCTUREAREA);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStructureAreaMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTargetUnitMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
    return mdClassIF.definesAttribute(TARGETUNIT);
  }
  
  public Float getUnitNozzleAreaCoverage()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(UNITNOZZLEAREACOVERAGE));
  }
  
  public void validateUnitNozzleAreaCoverage()
  {
    this.validateAttribute(UNITNOZZLEAREACOVERAGE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUnitNozzleAreaCoverageMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.AreaStandardsView.CLASS);
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
    return (AreaStandardsView) com.terraframe.mojo.business.View.get(id);
  }
  
  public void applyClone()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.AreaStandardsView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyClone(java.lang.String id)
  {
    AreaStandardsView _instance = AreaStandardsView.get(id);
    _instance.applyClone();
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.AreaStandardsView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    AreaStandardsView _instance = AreaStandardsView.get(id);
    _instance.deleteConcrete();
  }
  
  public static dss.vector.solutions.irs.AreaStandardsView getMostRecent()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.AreaStandardsView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
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
