package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 718526515)
public abstract class UnknownAgeGroupExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.UnknownAgeGroupException";
  private static final long serialVersionUID = 718526515;
  
  public UnknownAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UnknownAgeGroupExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UnknownAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UnknownAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UnknownAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UnknownAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UnknownAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UnknownAgeGroupExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.UnknownAgeGroupException", locale);
      
      message = message.replace("{id}", this.getId().toString());
      
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
