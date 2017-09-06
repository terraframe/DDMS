package dss.vector.solutions.kaleidoscope.report;

@com.runwaysdk.business.ClassSignature(hash = 34819242)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UnsupportedDrillThroughFormatException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class UnsupportedDrillThroughFormatExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.report.UnsupportedDrillThroughFormatException";
  public static java.lang.String ID = "id";
  public static java.lang.String OUTPUTFORMAT = "outputFormat";
  private static final long serialVersionUID = 34819242;
  
  public UnsupportedDrillThroughFormatExceptionBase()
  {
    super();
  }
  
  public UnsupportedDrillThroughFormatExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UnsupportedDrillThroughFormatExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UnsupportedDrillThroughFormatExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.report.UnsupportedDrillThroughFormatException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getOutputFormat()
  {
    return getValue(OUTPUTFORMAT);
  }
  
  public void validateOutputFormat()
  {
    this.validateAttribute(OUTPUTFORMAT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getOutputFormatMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.report.UnsupportedDrillThroughFormatException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(OUTPUTFORMAT);
  }
  
  public void setOutputFormat(String value)
  {
    if(value == null)
    {
      setValue(OUTPUTFORMAT, "");
    }
    else
    {
      setValue(OUTPUTFORMAT, value);
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
    message = replace(message, "{outputFormat}", this.getOutputFormat());
    return message;
  }
  
}
