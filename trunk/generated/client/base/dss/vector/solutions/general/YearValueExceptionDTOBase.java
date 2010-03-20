package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1510993236)
public abstract class YearValueExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.YearValueException";
  private static final long serialVersionUID = -1510993236;
  
  public YearValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected YearValueExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public YearValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public YearValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public YearValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public YearValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public YearValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public YearValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String MAXYEAR = "maxYear";
  public static java.lang.String MINYEAR = "minYear";
  public static java.lang.String YEARVALUE = "yearValue";
  public Integer getMaxYear()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAXYEAR));
  }
  
  public void setMaxYear(Integer value)
  {
    if(value == null)
    {
      setValue(MAXYEAR, "");
    }
    else
    {
      setValue(MAXYEAR, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMaxYearWritable()
  {
    return isWritable(MAXYEAR);
  }
  
  public boolean isMaxYearReadable()
  {
    return isReadable(MAXYEAR);
  }
  
  public boolean isMaxYearModified()
  {
    return isModified(MAXYEAR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getMaxYearMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MAXYEAR).getAttributeMdDTO();
  }
  
  public Integer getMinYear()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MINYEAR));
  }
  
  public void setMinYear(Integer value)
  {
    if(value == null)
    {
      setValue(MINYEAR, "");
    }
    else
    {
      setValue(MINYEAR, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMinYearWritable()
  {
    return isWritable(MINYEAR);
  }
  
  public boolean isMinYearReadable()
  {
    return isReadable(MINYEAR);
  }
  
  public boolean isMinYearModified()
  {
    return isModified(MINYEAR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getMinYearMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MINYEAR).getAttributeMdDTO();
  }
  
  public Integer getYearValue()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARVALUE));
  }
  
  public void setYearValue(Integer value)
  {
    if(value == null)
    {
      setValue(YEARVALUE, "");
    }
    else
    {
      setValue(YEARVALUE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearValueWritable()
  {
    return isWritable(YEARVALUE);
  }
  
  public boolean isYearValueReadable()
  {
    return isReadable(YEARVALUE);
  }
  
  public boolean isYearValueModified()
  {
    return isModified(YEARVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARVALUE).getAttributeMdDTO();
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
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.YearValueException", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{maxYear}", this.getMaxYear().toString());
      message = message.replace("{minYear}", this.getMinYear().toString());
      message = message.replace("{yearValue}", this.getYearValue().toString());
      
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
