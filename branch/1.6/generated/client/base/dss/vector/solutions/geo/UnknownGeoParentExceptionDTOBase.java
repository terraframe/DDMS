package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 1320142402)
public abstract class UnknownGeoParentExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.UnknownGeoParentException";
  private static final long serialVersionUID = 1320142402;
  
  public UnknownGeoParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UnknownGeoParentExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UnknownGeoParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UnknownGeoParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UnknownGeoParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UnknownGeoParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UnknownGeoParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UnknownGeoParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String GEOTYPE = "geoType";
  public static java.lang.String ID = "id";
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
    }
  }
  
  public boolean isGeoIdWritable()
  {
    return isWritable(GEOID);
  }
  
  public boolean isGeoIdReadable()
  {
    return isReadable(GEOID);
  }
  
  public boolean isGeoIdModified()
  {
    return isModified(GEOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getGeoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(GEOID).getAttributeMdDTO();
  }
  
  public String getGeoType()
  {
    return getValue(GEOTYPE);
  }
  
  public void setGeoType(String value)
  {
    if(value == null)
    {
      setValue(GEOTYPE, "");
    }
    else
    {
      setValue(GEOTYPE, value);
    }
  }
  
  public boolean isGeoTypeWritable()
  {
    return isWritable(GEOTYPE);
  }
  
  public boolean isGeoTypeReadable()
  {
    return isReadable(GEOTYPE);
  }
  
  public boolean isGeoTypeModified()
  {
    return isModified(GEOTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getGeoTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(GEOTYPE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{geoId}", this.getGeoId().toString());
    template = template.replace("{geoType}", this.getGeoType().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
