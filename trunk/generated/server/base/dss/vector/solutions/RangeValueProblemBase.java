package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -150536616)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RangeValueProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class RangeValueProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.RangeValueProblem";
  public static java.lang.String INVALIDVALUE = "invalidValue";
  public static java.lang.String LOWERLIMIT = "lowerLimit";
  public static java.lang.String UPPERLIMIT = "upperLimit";
  private static final long serialVersionUID = -150536616;
  
  public RangeValueProblemBase()
  {
    super();
  }
  
  public RangeValueProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getInvalidValue()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INVALIDVALUE));
  }
  
  public void validateInvalidValue()
  {
    this.validateAttribute(INVALIDVALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getInvalidValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.RangeValueProblem.CLASS);
    return mdClassIF.definesAttribute(INVALIDVALUE);
  }
  
  public void setInvalidValue(Integer value)
  {
    if(value == null)
    {
      setValue(INVALIDVALUE, "");
    }
    else
    {
      setValue(INVALIDVALUE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getLowerLimit()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOWERLIMIT));
  }
  
  public void validateLowerLimit()
  {
    this.validateAttribute(LOWERLIMIT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLowerLimitMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.RangeValueProblem.CLASS);
    return mdClassIF.definesAttribute(LOWERLIMIT);
  }
  
  public void setLowerLimit(Integer value)
  {
    if(value == null)
    {
      setValue(LOWERLIMIT, "");
    }
    else
    {
      setValue(LOWERLIMIT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getUpperLimit()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UPPERLIMIT));
  }
  
  public void validateUpperLimit()
  {
    this.validateAttribute(UPPERLIMIT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUpperLimitMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.RangeValueProblem.CLASS);
    return mdClassIF.definesAttribute(UPPERLIMIT);
  }
  
  public void setUpperLimit(Integer value)
  {
    if(value == null)
    {
      setValue(UPPERLIMIT, "");
    }
    else
    {
      setValue(UPPERLIMIT, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{invalidValue}", this.getInvalidValue());
    message = replace(message, "{lowerLimit}", this.getLowerLimit());
    message = replace(message, "{upperLimit}", this.getUpperLimit());
    return message;
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
