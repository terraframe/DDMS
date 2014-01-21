package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -956861752)
public abstract class TermImportExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.TermImportException";
  private static final long serialVersionUID = -956861752;
  
  public TermImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected TermImportExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public TermImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public TermImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public TermImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public TermImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public TermImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public TermImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String EXCEPTIONMESSAGE = "exceptionMessage";
  public static java.lang.String ID = "id";
  public static java.lang.String ROW = "row";
  public String getExceptionMessage()
  {
    return getValue(EXCEPTIONMESSAGE);
  }
  
  public void setExceptionMessage(String value)
  {
    if(value == null)
    {
      setValue(EXCEPTIONMESSAGE, "");
    }
    else
    {
      setValue(EXCEPTIONMESSAGE, value);
    }
  }
  
  public boolean isExceptionMessageWritable()
  {
    return isWritable(EXCEPTIONMESSAGE);
  }
  
  public boolean isExceptionMessageReadable()
  {
    return isReadable(EXCEPTIONMESSAGE);
  }
  
  public boolean isExceptionMessageModified()
  {
    return isModified(EXCEPTIONMESSAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getExceptionMessageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EXCEPTIONMESSAGE).getAttributeMdDTO();
  }
  
  public Integer getRow()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROW));
  }
  
  public void setRow(Integer value)
  {
    if(value == null)
    {
      setValue(ROW, "");
    }
    else
    {
      setValue(ROW, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRowWritable()
  {
    return isWritable(ROW);
  }
  
  public boolean isRowReadable()
  {
    return isReadable(ROW);
  }
  
  public boolean isRowModified()
  {
    return isModified(ROW);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRowMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROW).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{exceptionMessage}", this.getExceptionMessage().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{row}", this.getRow().toString());
    
    return template;
  }
  
}
