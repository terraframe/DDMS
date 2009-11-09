package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1324806278)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OperatorInterventionPlanningView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class OperatorInterventionPlanningViewBase extends dss.vector.solutions.irs.InterventionPlanningView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorInterventionPlanningView";
  public static java.lang.String NUMBEROFDAYS = "numberofDays";
  public static java.lang.String REQUIREDOPERATORS = "requiredOperators";
  public static java.lang.String UNITSPERDAY = "unitsPerDay";
  private static final long serialVersionUID = -1324806278;
  
  public OperatorInterventionPlanningViewBase()
  {
    super();
  }
  
  public Integer getNumberofDays()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFDAYS));
  }
  
  public void validateNumberofDays()
  {
    this.validateAttribute(NUMBEROFDAYS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberofDaysMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorInterventionPlanningView.CLASS);
    return mdClassIF.definesAttribute(NUMBEROFDAYS);
  }
  
  public void setNumberofDays(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFDAYS, "");
    }
    else
    {
      setValue(NUMBEROFDAYS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getRequiredOperators()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REQUIREDOPERATORS));
  }
  
  public void validateRequiredOperators()
  {
    this.validateAttribute(REQUIREDOPERATORS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getRequiredOperatorsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorInterventionPlanningView.CLASS);
    return mdClassIF.definesAttribute(REQUIREDOPERATORS);
  }
  
  public void setRequiredOperators(Integer value)
  {
    if(value == null)
    {
      setValue(REQUIREDOPERATORS, "");
    }
    else
    {
      setValue(REQUIREDOPERATORS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getUnitsPerDay()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSPERDAY));
  }
  
  public void validateUnitsPerDay()
  {
    this.validateAttribute(UNITSPERDAY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getUnitsPerDayMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorInterventionPlanningView.CLASS);
    return mdClassIF.definesAttribute(UNITSPERDAY);
  }
  
  public void setUnitsPerDay(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSPERDAY, "");
    }
    else
    {
      setValue(UNITSPERDAY, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static OperatorInterventionPlanningView get(String id)
  {
    return (OperatorInterventionPlanningView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.irs.OperatorInterventionPlanningView[] calculate(dss.vector.solutions.irs.OperatorInterventionPlanningView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorInterventionPlanningView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream exportToExcel(dss.vector.solutions.irs.OperatorInterventionPlanningView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorInterventionPlanningView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.OperatorInterventionPlanningView[] getViews(java.lang.String geoId, dss.vector.solutions.general.MalariaSeason season)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorInterventionPlanningView.java";
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
