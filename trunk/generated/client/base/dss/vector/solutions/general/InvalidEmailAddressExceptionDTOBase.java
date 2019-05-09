package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1926590090)
public abstract class InvalidEmailAddressExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.InvalidEmailAddressException";
  private static final long serialVersionUID = 1926590090;
  
  public InvalidEmailAddressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected InvalidEmailAddressExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InvalidEmailAddressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InvalidEmailAddressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InvalidEmailAddressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InvalidEmailAddressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InvalidEmailAddressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InvalidEmailAddressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ADDRESS = "address";
  public static java.lang.String ID = "id";
  public String getAddress()
  {
    return getValue(ADDRESS);
  }
  
  public void setAddress(String value)
  {
    if(value == null)
    {
      setValue(ADDRESS, "");
    }
    else
    {
      setValue(ADDRESS, value);
    }
  }
  
  public boolean isAddressWritable()
  {
    return isWritable(ADDRESS);
  }
  
  public boolean isAddressReadable()
  {
    return isReadable(ADDRESS);
  }
  
  public boolean isAddressModified()
  {
    return isModified(ADDRESS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getAddressMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ADDRESS).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{address}", this.getAddress().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
