package dss.vector.solutions.etl.dhis2.response;

@com.runwaysdk.business.ClassSignature(hash = -182932615)
public abstract class DHIS2AttributeLengthExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.response.DHIS2AttributeLengthException";
  private static final long serialVersionUID = -182932615;
  
  public DHIS2AttributeLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DHIS2AttributeLengthExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DHIS2AttributeLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DHIS2AttributeLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DHIS2AttributeLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DHIS2AttributeLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DHIS2AttributeLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DHIS2AttributeLengthExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRLEN = "attrLen";
  public static java.lang.String ID = "id";
  public String getAttrLen()
  {
    return getValue(ATTRLEN);
  }
  
  public void setAttrLen(String value)
  {
    if(value == null)
    {
      setValue(ATTRLEN, "");
    }
    else
    {
      setValue(ATTRLEN, value);
    }
  }
  
  public boolean isAttrLenWritable()
  {
    return isWritable(ATTRLEN);
  }
  
  public boolean isAttrLenReadable()
  {
    return isReadable(ATTRLEN);
  }
  
  public boolean isAttrLenModified()
  {
    return isModified(ATTRLEN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAttrLenMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRLEN).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{attrLen}", this.getAttrLen().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
