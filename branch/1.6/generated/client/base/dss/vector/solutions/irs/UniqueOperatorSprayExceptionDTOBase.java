package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -2116475611)
public abstract class UniqueOperatorSprayExceptionDTOBase extends dss.vector.solutions.irs.UniqueSprayExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.UniqueOperatorSprayException";
  private static final long serialVersionUID = -2116475611;
  
  public UniqueOperatorSprayExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UniqueOperatorSprayExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UniqueOperatorSprayExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UniqueOperatorSprayExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UniqueOperatorSprayExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UniqueOperatorSprayExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UniqueOperatorSprayExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UniqueOperatorSprayExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String SPRAYOPERATOR = "sprayOperator";
  public String getSprayOperator()
  {
    return getValue(SPRAYOPERATOR);
  }
  
  public void setSprayOperator(String value)
  {
    if(value == null)
    {
      setValue(SPRAYOPERATOR, "");
    }
    else
    {
      setValue(SPRAYOPERATOR, value);
    }
  }
  
  public boolean isSprayOperatorWritable()
  {
    return isWritable(SPRAYOPERATOR);
  }
  
  public boolean isSprayOperatorReadable()
  {
    return isReadable(SPRAYOPERATOR);
  }
  
  public boolean isSprayOperatorModified()
  {
    return isModified(SPRAYOPERATOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSprayOperatorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYOPERATOR).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{sprayOperator}", this.getSprayOperator().toString());
    
    return template;
  }
  
}
