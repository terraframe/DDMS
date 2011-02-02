package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1680084132)
public abstract class InsufficentSeasonNumberExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.InsufficentSeasonNumberException";
  private static final long serialVersionUID = -1680084132;
  
  public InsufficentSeasonNumberExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected InsufficentSeasonNumberExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InsufficentSeasonNumberExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InsufficentSeasonNumberExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InsufficentSeasonNumberExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InsufficentSeasonNumberExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InsufficentSeasonNumberExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InsufficentSeasonNumberExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBEROFSEASONS = "numberOfSeasons";
  public Integer getNumberOfSeasons()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFSEASONS));
  }
  
  public void setNumberOfSeasons(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFSEASONS, "");
    }
    else
    {
      setValue(NUMBEROFSEASONS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfSeasonsWritable()
  {
    return isWritable(NUMBEROFSEASONS);
  }
  
  public boolean isNumberOfSeasonsReadable()
  {
    return isReadable(NUMBEROFSEASONS);
  }
  
  public boolean isNumberOfSeasonsModified()
  {
    return isModified(NUMBEROFSEASONS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfSeasonsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFSEASONS).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{numberOfSeasons}", this.getNumberOfSeasons().toString());
    
    return template;
  }
  
}
