package dss.vector.solutions.report;

@com.runwaysdk.business.ClassSignature(hash = 880487589)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MultipleReportException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MultipleReportExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.MultipleReportException";
  public static java.lang.String FORMAT = "format";
  public static java.lang.String ID = "id";
  public static java.lang.String REPORTNAME = "reportName";
  private static final long serialVersionUID = 880487589;
  
  public MultipleReportExceptionBase()
  {
    super();
  }
  
  public MultipleReportExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public MultipleReportExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public MultipleReportExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getFormat()
  {
    return getValue(FORMAT);
  }
  
  public void validateFormat()
  {
    this.validateAttribute(FORMAT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFormatMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.MultipleReportException.CLASS);
    return mdClassIF.definesAttribute(FORMAT);
  }
  
  public void setFormat(String value)
  {
    if(value == null)
    {
      setValue(FORMAT, "");
    }
    else
    {
      setValue(FORMAT, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.MultipleReportException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getReportName()
  {
    return getValue(REPORTNAME);
  }
  
  public void validateReportName()
  {
    this.validateAttribute(REPORTNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getReportNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.MultipleReportException.CLASS);
    return mdClassIF.definesAttribute(REPORTNAME);
  }
  
  public void setReportName(String value)
  {
    if(value == null)
    {
      setValue(REPORTNAME, "");
    }
    else
    {
      setValue(REPORTNAME, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{format}", this.getFormat());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{reportName}", this.getReportName());
    return message;
  }
  
}
