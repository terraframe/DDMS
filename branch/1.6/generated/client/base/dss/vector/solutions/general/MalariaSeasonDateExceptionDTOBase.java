package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -10269576)
public abstract class MalariaSeasonDateExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.MalariaSeasonDateException";
  private static final long serialVersionUID = -10269576;
  
  public MalariaSeasonDateExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected MalariaSeasonDateExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public MalariaSeasonDateExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public MalariaSeasonDateExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public MalariaSeasonDateExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public MalariaSeasonDateExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public MalariaSeasonDateExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public MalariaSeasonDateExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String SEASONNAME = "seasonName";
  public static java.lang.String WEEKDATE = "weekDate";
  public String getSeasonName()
  {
    return getValue(SEASONNAME);
  }
  
  public void setSeasonName(String value)
  {
    if(value == null)
    {
      setValue(SEASONNAME, "");
    }
    else
    {
      setValue(SEASONNAME, value);
    }
  }
  
  public boolean isSeasonNameWritable()
  {
    return isWritable(SEASONNAME);
  }
  
  public boolean isSeasonNameReadable()
  {
    return isReadable(SEASONNAME);
  }
  
  public boolean isSeasonNameModified()
  {
    return isModified(SEASONNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSeasonNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEASONNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getWeekDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(WEEKDATE));
  }
  
  public void setWeekDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(WEEKDATE, "");
    }
    else
    {
      setValue(WEEKDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isWeekDateWritable()
  {
    return isWritable(WEEKDATE);
  }
  
  public boolean isWeekDateReadable()
  {
    return isReadable(WEEKDATE);
  }
  
  public boolean isWeekDateModified()
  {
    return isModified(WEEKDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getWeekDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(WEEKDATE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{seasonName}", this.getSeasonName().toString());
    template = template.replace("{weekDate}", this.getWeekDate().toString());
    
    return template;
  }
  
}
