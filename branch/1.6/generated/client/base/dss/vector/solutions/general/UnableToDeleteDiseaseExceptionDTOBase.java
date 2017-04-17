package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -90429905)
public abstract class UnableToDeleteDiseaseExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.UnableToDeleteDiseaseException";
  private static final long serialVersionUID = -90429905;
  
  public UnableToDeleteDiseaseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UnableToDeleteDiseaseExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UnableToDeleteDiseaseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UnableToDeleteDiseaseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UnableToDeleteDiseaseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UnableToDeleteDiseaseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UnableToDeleteDiseaseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UnableToDeleteDiseaseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DISEASENAME = "diseaseName";
  public static java.lang.String ID = "id";
  public String getDiseaseName()
  {
    return getValue(DISEASENAME);
  }
  
  public void setDiseaseName(String value)
  {
    if(value == null)
    {
      setValue(DISEASENAME, "");
    }
    else
    {
      setValue(DISEASENAME, value);
    }
  }
  
  public boolean isDiseaseNameWritable()
  {
    return isWritable(DISEASENAME);
  }
  
  public boolean isDiseaseNameReadable()
  {
    return isReadable(DISEASENAME);
  }
  
  public boolean isDiseaseNameModified()
  {
    return isModified(DISEASENAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDiseaseNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISEASENAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{diseaseName}", this.getDiseaseName().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
