package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -909193676)
public abstract class AmbigiousTermExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.AmbigiousTermException";
  private static final long serialVersionUID = -909193676;
  
  public AmbigiousTermExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected AmbigiousTermExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public AmbigiousTermExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public AmbigiousTermExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public AmbigiousTermExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public AmbigiousTermExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public AmbigiousTermExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public AmbigiousTermExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String TERMNAME = "termName";
  public String getTermName()
  {
    return getValue(TERMNAME);
  }
  
  public void setTermName(String value)
  {
    if(value == null)
    {
      setValue(TERMNAME, "");
    }
    else
    {
      setValue(TERMNAME, value);
    }
  }
  
  public boolean isTermNameWritable()
  {
    return isWritable(TERMNAME);
  }
  
  public boolean isTermNameReadable()
  {
    return isReadable(TERMNAME);
  }
  
  public boolean isTermNameModified()
  {
    return isModified(TERMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{termName}", this.getTermName().toString());
    
    return template;
  }
  
}
