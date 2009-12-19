package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 2088782388)
public abstract class YearValueExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.YearValueException";
  private static final long serialVersionUID = 2088782388;
  
  public YearValueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected YearValueExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public YearValueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public YearValueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public YearValueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public YearValueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public YearValueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public YearValueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
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
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAXYEAR));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMaxYearMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MAXYEAR).getAttributeMdDTO();
  }
  
  public Integer getMinYear()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MINYEAR));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMinYearMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MINYEAR).getAttributeMdDTO();
  }
  
  public Integer getYearValue()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARVALUE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearValueMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARVALUE).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.YearValueException", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{maxYear}", this.getMaxYear().toString());
      message = message.replace("{minYear}", this.getMinYear().toString());
      message = message.replace("{yearValue}", this.getYearValue().toString());
      
      return message;
    }
    catch (java.io.IOException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (com.terraframe.mojo.util.LocalizeException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
  }
  
}
