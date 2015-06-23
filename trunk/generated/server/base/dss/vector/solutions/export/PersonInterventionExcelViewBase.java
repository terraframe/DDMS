package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -719237158)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PersonInterventionExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PersonInterventionExcelViewBase extends dss.vector.solutions.export.ControlInterventionExcelView implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PersonInterventionExcelView";
  public static java.lang.String VEHICLEDAYS = "vehicleDays";
  private static final long serialVersionUID = -719237158;
  
  public PersonInterventionExcelViewBase()
  {
    super();
  }
  
  public Integer getVehicleDays()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VEHICLEDAYS));
  }
  
  public void validateVehicleDays()
  {
    this.validateAttribute(VEHICLEDAYS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getVehicleDaysMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.PersonInterventionExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(VEHICLEDAYS);
  }
  
  public void setVehicleDays(Integer value)
  {
    if(value == null)
    {
      setValue(VEHICLEDAYS, "");
    }
    else
    {
      setValue(VEHICLEDAYS, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PersonInterventionExcelView get(String id)
  {
    return (PersonInterventionExcelView) com.runwaysdk.business.View.get(id);
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
