package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 200039495)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ZoneSprayView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class ZoneSprayViewBase extends dss.vector.solutions.irs.AbstractSprayView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ZoneSprayView";
  public static java.lang.String SPRAYWEEK = "sprayWeek";
  public static java.lang.String SUPERVISORNAME = "supervisorName";
  public static java.lang.String SUPERVISORSURNAME = "supervisorSurname";
  public static java.lang.String TARGET = "target";
  private static final long serialVersionUID = 200039495;
  
  public ZoneSprayViewBase()
  {
    super();
  }
  
  public Integer getSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYWEEK));
  }
  
  public void validateSprayWeek()
  {
    this.validateAttribute(SPRAYWEEK);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayWeekMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ZoneSprayView.CLASS);
    return mdClassIF.definesAttribute(SPRAYWEEK);
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
  
  public String getSupervisorName()
  {
    return getValue(SUPERVISORNAME);
  }
  
  public void validateSupervisorName()
  {
    this.validateAttribute(SUPERVISORNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSupervisorNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ZoneSprayView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSupervisorSurnameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ZoneSprayView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ZoneSprayView.CLASS);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ZoneSprayView get(String id)
  {
    return (ZoneSprayView) com.terraframe.mojo.business.View.get(id);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.ZoneSprayView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    ZoneSprayView _instance = ZoneSprayView.get(id);
    _instance.deleteConcrete();
  }
  
  public dss.vector.solutions.irs.TeamSprayStatusView[] getStatus()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.ZoneSprayView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayStatusView[] getStatus(java.lang.String id)
  {
    ZoneSprayView _instance = ZoneSprayView.get(id);
    return _instance.getStatus();
  }
  
  public static dss.vector.solutions.irs.ZoneSprayView searchBySprayData(java.lang.String geoId, java.util.Date sprayDate, dss.vector.solutions.irs.SprayMethod sprayMethod, dss.vector.solutions.irs.InsecticideBrand brand)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.ZoneSprayView.java";
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
