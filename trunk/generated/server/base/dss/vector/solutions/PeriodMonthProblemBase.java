package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 1899584452)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PeriodMonthProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PeriodMonthProblemBase extends com.runwaysdk.business.Problem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PeriodMonthProblem";
  public static java.lang.String ID = "id";
  public static java.lang.String MAXPERIOD = "maxPeriod";
  public static java.lang.String PERIOD = "period";
  private static final long serialVersionUID = 1899584452;
  
  public PeriodMonthProblemBase()
  {
    super();
  }
  
  public PeriodMonthProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.PeriodMonthProblem.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Integer getMaxPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAXPERIOD));
  }
  
  public void validateMaxPeriod()
  {
    this.validateAttribute(MAXPERIOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMaxPeriodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.PeriodMonthProblem.CLASS);
    return mdClassIF.definesAttribute(MAXPERIOD);
  }
  
  public void setMaxPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(MAXPERIOD, "");
    }
    else
    {
      setValue(MAXPERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void validatePeriod()
  {
    this.validateAttribute(PERIOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPeriodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.PeriodMonthProblem.CLASS);
    return mdClassIF.definesAttribute(PERIOD);
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{maxPeriod}", this.getMaxPeriod());
    message = replace(message, "{period}", this.getPeriod());
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
