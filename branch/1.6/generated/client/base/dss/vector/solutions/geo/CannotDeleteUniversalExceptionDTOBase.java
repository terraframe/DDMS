package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 1590776254)
public abstract class CannotDeleteUniversalExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.CannotDeleteUniversalException";
  private static final long serialVersionUID = 1590776254;
  
  public CannotDeleteUniversalExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected CannotDeleteUniversalExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public CannotDeleteUniversalExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public CannotDeleteUniversalExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public CannotDeleteUniversalExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public CannotDeleteUniversalExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public CannotDeleteUniversalExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public CannotDeleteUniversalExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String UNIVERSALLABEL = "universalLabel";
  public String getUniversalLabel()
  {
    return getValue(UNIVERSALLABEL);
  }
  
  public void setUniversalLabel(String value)
  {
    if(value == null)
    {
      setValue(UNIVERSALLABEL, "");
    }
    else
    {
      setValue(UNIVERSALLABEL, value);
    }
  }
  
  public boolean isUniversalLabelWritable()
  {
    return isWritable(UNIVERSALLABEL);
  }
  
  public boolean isUniversalLabelReadable()
  {
    return isReadable(UNIVERSALLABEL);
  }
  
  public boolean isUniversalLabelModified()
  {
    return isModified(UNIVERSALLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUniversalLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNIVERSALLABEL).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{universalLabel}", this.getUniversalLabel().toString());
    
    return template;
  }
  
}
