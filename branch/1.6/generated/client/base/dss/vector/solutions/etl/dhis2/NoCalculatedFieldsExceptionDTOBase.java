package dss.vector.solutions.etl.dhis2;

@com.runwaysdk.business.ClassSignature(hash = 542975467)
public abstract class NoCalculatedFieldsExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.NoCalculatedFieldsException";
  private static final long serialVersionUID = 542975467;
  
  public NoCalculatedFieldsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected NoCalculatedFieldsExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public NoCalculatedFieldsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public NoCalculatedFieldsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public NoCalculatedFieldsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public NoCalculatedFieldsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public NoCalculatedFieldsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public NoCalculatedFieldsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CALCFIELD = "calcField";
  public static java.lang.String ID = "id";
  public String getCalcField()
  {
    return getValue(CALCFIELD);
  }
  
  public void setCalcField(String value)
  {
    if(value == null)
    {
      setValue(CALCFIELD, "");
    }
    else
    {
      setValue(CALCFIELD, value);
    }
  }
  
  public boolean isCalcFieldWritable()
  {
    return isWritable(CALCFIELD);
  }
  
  public boolean isCalcFieldReadable()
  {
    return isReadable(CALCFIELD);
  }
  
  public boolean isCalcFieldModified()
  {
    return isModified(CALCFIELD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCalcFieldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CALCFIELD).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{calcField}", this.getCalcField().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
