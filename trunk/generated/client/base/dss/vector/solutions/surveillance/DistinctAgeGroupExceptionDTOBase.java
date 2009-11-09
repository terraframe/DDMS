package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = -1016143725)
public abstract class DistinctAgeGroupExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.DistinctAgeGroupException";
  private static final long serialVersionUID = -1016143725;
  
  public DistinctAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DistinctAgeGroupExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DistinctAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDAGE = "endAge";
  public static java.lang.String ID = "id";
  public static java.lang.String STARTAGE = "startAge";
  public Integer getEndAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ENDAGE));
  }
  
  public void setEndAge(Integer value)
  {
    if(value == null)
    {
      setValue(ENDAGE, "");
    }
    else
    {
      setValue(ENDAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEndAgeWritable()
  {
    return isWritable(ENDAGE);
  }
  
  public boolean isEndAgeReadable()
  {
    return isReadable(ENDAGE);
  }
  
  public boolean isEndAgeModified()
  {
    return isModified(ENDAGE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getEndAgeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ENDAGE).getAttributeMdDTO();
  }
  
  public Integer getStartAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STARTAGE));
  }
  
  public void setStartAge(Integer value)
  {
    if(value == null)
    {
      setValue(STARTAGE, "");
    }
    else
    {
      setValue(STARTAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStartAgeWritable()
  {
    return isWritable(STARTAGE);
  }
  
  public boolean isStartAgeReadable()
  {
    return isReadable(STARTAGE);
  }
  
  public boolean isStartAgeModified()
  {
    return isModified(STARTAGE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getStartAgeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STARTAGE).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.surveillance.DistinctAgeGroupException", locale);
      
      message = message.replace("{endAge}", this.getEndAge().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{startAge}", this.getStartAge().toString());
      
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
