package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1415894125)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideInterventionPlanningView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InsecticideInterventionPlanningViewBase extends dss.vector.solutions.irs.InterventionPlanningView implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideInterventionPlanningView";
  public static java.lang.String REQUIREDINSECTICIDE = "requiredInsecticide";
  private static final long serialVersionUID = -1415894125;
  
  public InsecticideInterventionPlanningViewBase()
  {
    super();
  }
  
  public Double getRequiredInsecticide()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(REQUIREDINSECTICIDE));
  }
  
  public void validateRequiredInsecticide()
  {
    this.validateAttribute(REQUIREDINSECTICIDE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getRequiredInsecticideMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InsecticideInterventionPlanningView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(REQUIREDINSECTICIDE);
  }
  
  public void setRequiredInsecticide(Double value)
  {
    if(value == null)
    {
      setValue(REQUIREDINSECTICIDE, "");
    }
    else
    {
      setValue(REQUIREDINSECTICIDE, java.lang.Double.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static InsecticideInterventionPlanningView get(String id)
  {
    return (InsecticideInterventionPlanningView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.irs.InsecticideInterventionPlanningView[] calculate(dss.vector.solutions.irs.InsecticideInterventionPlanningView[] views, java.lang.String brandId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideInterventionPlanningView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream exportToExcel(dss.vector.solutions.irs.InsecticideInterventionPlanningView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideInterventionPlanningView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.irs.InsecticideInterventionPlanningView[] getViews(java.lang.String geoId, dss.vector.solutions.general.MalariaSeason season)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.InsecticideInterventionPlanningView.java";
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
