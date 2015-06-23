package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -404981295)
public abstract class UndefinedKnockDownPropertyExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.UndefinedKnockDownPropertyException";
  private static final long serialVersionUID = -404981295;
  
  public UndefinedKnockDownPropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UndefinedKnockDownPropertyExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UndefinedKnockDownPropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UndefinedKnockDownPropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UndefinedKnockDownPropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UndefinedKnockDownPropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UndefinedKnockDownPropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UndefinedKnockDownPropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDE = "insecticide";
  public dss.vector.solutions.general.InsecticideDTO getInsecticide()
  {
    if(getValue(INSECTICIDE) == null || getValue(INSECTICIDE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.InsecticideDTO.get(getRequest(), getValue(INSECTICIDE));
    }
  }
  
  public String getInsecticideId()
  {
    return getValue(INSECTICIDE);
  }
  
  public void setInsecticide(dss.vector.solutions.general.InsecticideDTO value)
  {
    if(value == null)
    {
      setValue(INSECTICIDE, "");
    }
    else
    {
      setValue(INSECTICIDE, value.getId());
    }
  }
  
  public boolean isInsecticideWritable()
  {
    return isWritable(INSECTICIDE);
  }
  
  public boolean isInsecticideReadable()
  {
    return isReadable(INSECTICIDE);
  }
  
  public boolean isInsecticideModified()
  {
    return isModified(INSECTICIDE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInsecticideMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INSECTICIDE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{insecticide}", this.getInsecticide().toString());
    
    return template;
  }
  
}
