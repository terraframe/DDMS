package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -169810536)
public abstract class ConfirmParentChangeExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.ConfirmParentChangeException";
  private static final long serialVersionUID = -169810536;
  
  public ConfirmParentChangeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected ConfirmParentChangeExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ConfirmParentChangeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ConfirmParentChangeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ConfirmParentChangeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ConfirmParentChangeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ConfirmParentChangeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ConfirmParentChangeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTTERM = "parentTerm";
  public String getParentTerm()
  {
    return getValue(PARENTTERM);
  }
  
  public void setParentTerm(String value)
  {
    if(value == null)
    {
      setValue(PARENTTERM, "");
    }
    else
    {
      setValue(PARENTTERM, value);
    }
  }
  
  public boolean isParentTermWritable()
  {
    return isWritable(PARENTTERM);
  }
  
  public boolean isParentTermReadable()
  {
    return isReadable(PARENTTERM);
  }
  
  public boolean isParentTermModified()
  {
    return isModified(PARENTTERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTTERM).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{parentTerm}", this.getParentTerm().toString());
    
    return template;
  }
  
}
