package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1561697020)
public abstract class UniqueSprayExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.UniqueSprayException";
  private static final long serialVersionUID = -1561697020;
  
  public UniqueSprayExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UniqueSprayExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UniqueSprayExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UniqueSprayExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UniqueSprayExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UniqueSprayExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UniqueSprayExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UniqueSprayExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BRAND = "brand";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  public String getBrand()
  {
    return getValue(BRAND);
  }
  
  public void setBrand(String value)
  {
    if(value == null)
    {
      setValue(BRAND, "");
    }
    else
    {
      setValue(BRAND, value);
    }
  }
  
  public boolean isBrandWritable()
  {
    return isWritable(BRAND);
  }
  
  public boolean isBrandReadable()
  {
    return isReadable(BRAND);
  }
  
  public boolean isBrandModified()
  {
    return isModified(BRAND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBrandMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BRAND).getAttributeMdDTO();
  }
  
  public String getGeoEntity()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public java.util.Date getSprayDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void setSprayDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATE, "");
    }
    else
    {
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getSprayDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSprayMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYMETHOD).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.irs.UniqueSprayException", locale);
      
      message = message.replace("{brand}", this.getBrand().toString());
      message = message.replace("{geoEntity}", this.getGeoEntity().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{sprayDate}", this.getSprayDate().toString());
      message = message.replace("{sprayMethod}", this.getSprayMethod().toString());
      
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
