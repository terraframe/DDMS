package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -88804225)
public abstract class OverlapBoundsExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.OverlapBoundsException";
  private static final long serialVersionUID = -88804225;
  
  public OverlapBoundsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected OverlapBoundsExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public OverlapBoundsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public OverlapBoundsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public OverlapBoundsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public OverlapBoundsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public OverlapBoundsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public OverlapBoundsExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String RANGEONE = "rangeOne";
  public static java.lang.String RANGETWO = "rangeTwo";
  public String getRangeOne()
  {
    return getValue(RANGEONE);
  }
  
  public void setRangeOne(String value)
  {
    if(value == null)
    {
      setValue(RANGEONE, "");
    }
    else
    {
      setValue(RANGEONE, value);
    }
  }
  
  public boolean isRangeOneWritable()
  {
    return isWritable(RANGEONE);
  }
  
  public boolean isRangeOneReadable()
  {
    return isReadable(RANGEONE);
  }
  
  public boolean isRangeOneModified()
  {
    return isModified(RANGEONE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRangeOneMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RANGEONE).getAttributeMdDTO();
  }
  
  public String getRangeTwo()
  {
    return getValue(RANGETWO);
  }
  
  public void setRangeTwo(String value)
  {
    if(value == null)
    {
      setValue(RANGETWO, "");
    }
    else
    {
      setValue(RANGETWO, value);
    }
  }
  
  public boolean isRangeTwoWritable()
  {
    return isWritable(RANGETWO);
  }
  
  public boolean isRangeTwoReadable()
  {
    return isReadable(RANGETWO);
  }
  
  public boolean isRangeTwoModified()
  {
    return isModified(RANGETWO);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRangeTwoMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RANGETWO).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{rangeOne}", this.getRangeOne().toString());
    template = template.replace("{rangeTwo}", this.getRangeTwo().toString());
    
    return template;
  }
  
}
