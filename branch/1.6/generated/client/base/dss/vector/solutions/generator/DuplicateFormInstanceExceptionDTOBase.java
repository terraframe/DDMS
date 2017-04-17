package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = 2083815673)
public abstract class DuplicateFormInstanceExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.DuplicateFormInstanceException";
  private static final long serialVersionUID = 2083815673;
  
  public DuplicateFormInstanceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateFormInstanceExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateFormInstanceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateFormInstanceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateFormInstanceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateFormInstanceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateFormInstanceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateFormInstanceExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FORMID = "formId";
  public static java.lang.String ID = "id";
  public String getFormId()
  {
    return getValue(FORMID);
  }
  
  public void setFormId(String value)
  {
    if(value == null)
    {
      setValue(FORMID, "");
    }
    else
    {
      setValue(FORMID, value);
    }
  }
  
  public boolean isFormIdWritable()
  {
    return isWritable(FORMID);
  }
  
  public boolean isFormIdReadable()
  {
    return isReadable(FORMID);
  }
  
  public boolean isFormIdModified()
  {
    return isModified(FORMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFormIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FORMID).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{formId}", this.getFormId().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
