package dss.vector.solutions.kaleidoscope.report;

@com.runwaysdk.business.ClassSignature(hash = 434449889)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MissingReportDocumentException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MissingReportDocumentExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.report.MissingReportDocumentException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 434449889;
  
  public MissingReportDocumentExceptionBase()
  {
    super();
  }
  
  public MissingReportDocumentExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public MissingReportDocumentExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public MissingReportDocumentExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.kaleidoscope.report.MissingReportDocumentException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
