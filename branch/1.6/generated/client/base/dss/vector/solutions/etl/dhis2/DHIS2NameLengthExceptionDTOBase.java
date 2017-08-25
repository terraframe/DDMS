package dss.vector.solutions.etl.dhis2;

@com.runwaysdk.business.ClassSignature(hash = 1371407277)
public abstract class DHIS2NameLengthExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.DHIS2NameLengthException";
  private static final long serialVersionUID = 1371407277;
  
  public DHIS2NameLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DHIS2NameLengthExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DHIS2NameLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DHIS2NameLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DHIS2NameLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DHIS2NameLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DHIS2NameLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DHIS2NameLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CHARLEN = "charLen";
  public static java.lang.String ID = "id";
  public static java.lang.String NAME = "name";
  public String getCharLen()
  {
    return getValue(CHARLEN);
  }
  
  public void setCharLen(String value)
  {
    if(value == null)
    {
      setValue(CHARLEN, "");
    }
    else
    {
      setValue(CHARLEN, value);
    }
  }
  
  public boolean isCharLenWritable()
  {
    return isWritable(CHARLEN);
  }
  
  public boolean isCharLenReadable()
  {
    return isReadable(CHARLEN);
  }
  
  public boolean isCharLenModified()
  {
    return isModified(CHARLEN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCharLenMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CHARLEN).getAttributeMdDTO();
  }
  
  public String getName()
  {
    return getValue(NAME);
  }
  
  public void setName(String value)
  {
    if(value == null)
    {
      setValue(NAME, "");
    }
    else
    {
      setValue(NAME, value);
    }
  }
  
  public boolean isNameWritable()
  {
    return isWritable(NAME);
  }
  
  public boolean isNameReadable()
  {
    return isReadable(NAME);
  }
  
  public boolean isNameModified()
  {
    return isModified(NAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{charLen}", this.getCharLen().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{name}", this.getName().toString());
    
    return template;
  }
  
}
