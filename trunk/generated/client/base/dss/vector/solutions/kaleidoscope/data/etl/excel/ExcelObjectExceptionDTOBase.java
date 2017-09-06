package dss.vector.solutions.kaleidoscope.data.etl.excel;

@com.runwaysdk.business.ClassSignature(hash = -1824854148)
public abstract class ExcelObjectExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelObjectException";
  private static final long serialVersionUID = -1824854148;
  
  public ExcelObjectExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected ExcelObjectExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ExcelObjectExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ExcelObjectExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ExcelObjectExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ExcelObjectExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ExcelObjectExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ExcelObjectExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String MSG = "msg";
  public static java.lang.String ROW = "row";
  public String getMsg()
  {
    return getValue(MSG);
  }
  
  public void setMsg(String value)
  {
    if(value == null)
    {
      setValue(MSG, "");
    }
    else
    {
      setValue(MSG, value);
    }
  }
  
  public boolean isMsgWritable()
  {
    return isWritable(MSG);
  }
  
  public boolean isMsgReadable()
  {
    return isReadable(MSG);
  }
  
  public boolean isMsgModified()
  {
    return isModified(MSG);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getMsgMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(MSG).getAttributeMdDTO();
  }
  
  public Long getRow()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(ROW));
  }
  
  public void setRow(Long value)
  {
    if(value == null)
    {
      setValue(ROW, "");
    }
    else
    {
      setValue(ROW, java.lang.Long.toString(value));
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
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{msg}", this.getMsg().toString());
    template = template.replace("{row}", this.getRow().toString());
    
    return template;
  }
  
}
