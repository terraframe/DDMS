package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 602177236)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OperatorInterventionPlanningView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class OperatorInterventionPlanningViewBase extends dss.vector.solutions.irs.InterventionPlanningView implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorInterventionPlanningView";
  public static java.lang.String NUMBEROFDAYS = "numberofDays";
  public static java.lang.String REQUIREDOPERATORS = "requiredOperators";
  public static java.lang.String UNITSPERDAY = "unitsPerDay";
  private static final long serialVersionUID = 602177236;
  
  public OperatorInterventionPlanningViewBase()
  {
    super();
  }
  
  public Integer getNumberofDays()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFDAYS));
  }
  
  public void validateNumberofDays()
  {
    this.validateAttribute(NUMBEROFDAYS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getNumberofDaysMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorInterventionPlanningView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REQUIREDOPERATORS));
  }
  
  public void validateRequiredOperators()
  {
    this.validateAttribute(REQUIREDOPERATORS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getRequiredOperatorsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorInterventionPlanningView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSPERDAY));
  }
  
  public void validateUnitsPerDay()
  {
    this.validateAttribute(UNITSPERDAY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUnitsPerDayMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorInterventionPlanningView.CLASS);
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
    return (OperatorInterventionPlanningView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.irs.OperatorInterventionPlanningView[] calculate(dss.vector.solutions.irs.OperatorInterventionPlanningView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorInterventionPlanningView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream exportToExcel(dss.vector.solutions.irs.OperatorInterventionPlanningView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorInterventionPlanningView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.OperatorInterventionPlanningView[] getViews(java.lang.String geoId, dss.vector.solutions.general.MalariaSeason season)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorInterventionPlanningView.java";
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
