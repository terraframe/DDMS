package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1883921175)
public abstract class DuplicateZoneSprayImportExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.DuplicateZoneSprayImportException";
  private static final long serialVersionUID = 1883921175;
  
  public DuplicateZoneSprayImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateZoneSprayImportExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateZoneSprayImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateZoneSprayImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateZoneSprayImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateZoneSprayImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateZoneSprayImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateZoneSprayImportExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDETERM = "insecticideTerm";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public String getInsecticideTerm()
  {
    return getValue(INSECTICIDETERM);
  }
  
  public void setInsecticideTerm(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDETERM, "");
    }
    else
    {
      setValue(INSECTICIDETERM, value);
    }
  }
  
  public boolean isInsecticideTermWritable()
  {
    return isWritable(INSECTICIDETERM);
  }
  
  public boolean isInsecticideTermReadable()
  {
    return isReadable(INSECTICIDETERM);
  }
  
  public boolean isInsecticideTermModified()
  {
    return isModified(INSECTICIDETERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInsecticideTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICIDETERM).getAttributeMdDTO();
  }
  
  public java.util.Date getSprayDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void setSprayDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATE, "");
    }
    else
    {
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isSprayDateWritable()
  {
    return isWritable(SPRAYDATE);
  }
  
  public boolean isSprayDateReadable()
  {
    return isReadable(SPRAYDATE);
  }
  
  public boolean isSprayDateModified()
  {
    return isModified(SPRAYDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getSprayDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATE).getAttributeMdDTO();
  }
  
  public String getSprayMethod()
  {
    return getValue(SPRAYMETHOD);
  }
  
  public void setSprayMethod(String value)
  {
    if(value == null)
    {
      setValue(SPRAYMETHOD, "");
    }
    else
    {
      setValue(SPRAYMETHOD, value);
    }
  }
  
  public boolean isSprayMethodWritable()
  {
    return isWritable(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodReadable()
  {
    return isReadable(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodModified()
  {
    return isModified(SPRAYMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSprayMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYMETHOD).getAttributeMdDTO();
  }
  
  public String getSprayTeam()
  {
    return getValue(SPRAYTEAM);
  }
  
  public void setSprayTeam(String value)
  {
    if(value == null)
    {
      setValue(SPRAYTEAM, "");
    }
    else
    {
      setValue(SPRAYTEAM, value);
    }
  }
  
  public boolean isSprayTeamWritable()
  {
    return isWritable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamReadable()
  {
    return isReadable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamModified()
  {
    return isModified(SPRAYTEAM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSprayTeamMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYTEAM).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{geoEntity}", this.getGeoEntity().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{insecticideTerm}", this.getInsecticideTerm().toString());
    template = template.replace("{sprayDate}", this.getSprayDate().toString());
    template = template.replace("{sprayMethod}", this.getSprayMethod().toString());
    template = template.replace("{sprayTeam}", this.getSprayTeam().toString());
    
    return template;
  }
  
}
