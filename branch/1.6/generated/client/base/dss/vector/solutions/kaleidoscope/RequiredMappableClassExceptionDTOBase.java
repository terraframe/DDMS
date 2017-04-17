package dss.vector.solutions.kaleidoscope;

@com.runwaysdk.business.ClassSignature(hash = 678594933)
public abstract class RequiredMappableClassExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.RequiredMappableClassException";
  private static final long serialVersionUID = 678594933;
  
  public RequiredMappableClassExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected RequiredMappableClassExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public RequiredMappableClassExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public RequiredMappableClassExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public RequiredMappableClassExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public RequiredMappableClassExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public RequiredMappableClassExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public RequiredMappableClassExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DATASETLABEL = "dataSetLabel";
  public static java.lang.String ID = "id";
  public String getDataSetLabel()
  {
    return getValue(DATASETLABEL);
  }
  
  public void setDataSetLabel(String value)
  {
    if(value == null)
    {
      setValue(DATASETLABEL, "");
    }
    else
    {
      setValue(DATASETLABEL, value);
    }
  }
  
  public boolean isDataSetLabelWritable()
  {
    return isWritable(DATASETLABEL);
  }
  
  public boolean isDataSetLabelReadable()
  {
    return isReadable(DATASETLABEL);
  }
  
  public boolean isDataSetLabelModified()
  {
    return isModified(DATASETLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getDataSetLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(DATASETLABEL).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{dataSetLabel}", this.getDataSetLabel().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
