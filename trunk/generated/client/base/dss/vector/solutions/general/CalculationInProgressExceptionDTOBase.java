package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1355612951)
public abstract class CalculationInProgressExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.CalculationInProgressException";
  private static final long serialVersionUID = -1355612951;
  
  public CalculationInProgressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected CalculationInProgressExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public CalculationInProgressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public CalculationInProgressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public CalculationInProgressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public CalculationInProgressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public CalculationInProgressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public CalculationInProgressExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String PERCENTCOMPLETE = "percentComplete";
  public Integer getPercentComplete()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERCENTCOMPLETE));
  }
  
  public void setPercentComplete(Integer value)
  {
    if(value == null)
    {
      setValue(PERCENTCOMPLETE, "");
    }
    else
    {
      setValue(PERCENTCOMPLETE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPercentCompleteWritable()
  {
    return isWritable(PERCENTCOMPLETE);
  }
  
  public boolean isPercentCompleteReadable()
  {
    return isReadable(PERCENTCOMPLETE);
  }
  
  public boolean isPercentCompleteModified()
  {
    return isModified(PERCENTCOMPLETE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPercentCompleteMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERCENTCOMPLETE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    if (this.getLocale() != null)
    {
      return this.localize(this.getLocale());
    }
    else
    {
      return this.getExceptionDTO().getLocalizedMessage();
    }
  }
  private java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.CalculationInProgressException", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{percentComplete}", this.getPercentComplete().toString());
      
      return message;
    }
    catch (java.io.IOException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (com.runwaysdk.util.LocalizeException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
  }
  
}
