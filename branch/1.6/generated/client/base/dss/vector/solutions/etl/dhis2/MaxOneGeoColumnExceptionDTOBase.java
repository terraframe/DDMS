package dss.vector.solutions.etl.dhis2;

@com.runwaysdk.business.ClassSignature(hash = 1686398003)
public abstract class MaxOneGeoColumnExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.MaxOneGeoColumnException";
  private static final long serialVersionUID = 1686398003;
  
  public MaxOneGeoColumnExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected MaxOneGeoColumnExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public MaxOneGeoColumnExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public MaxOneGeoColumnExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public MaxOneGeoColumnExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public MaxOneGeoColumnExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public MaxOneGeoColumnExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public MaxOneGeoColumnExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DATASET = "dataset";
  public static java.lang.String ID = "id";
  public String getDataset()
  {
    return getValue(DATASET);
  }
  
  public void setDataset(String value)
  {
    if(value == null)
    {
      setValue(DATASET, "");
    }
    else
    {
      setValue(DATASET, value);
    }
  }
  
  public boolean isDatasetWritable()
  {
    return isWritable(DATASET);
  }
  
  public boolean isDatasetReadable()
  {
    return isReadable(DATASET);
  }
  
  public boolean isDatasetModified()
  {
    return isModified(DATASET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDatasetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DATASET).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{dataset}", this.getDataset().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
