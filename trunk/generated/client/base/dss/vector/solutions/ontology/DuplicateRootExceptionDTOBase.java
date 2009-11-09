package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = 1247122483)
public abstract class DuplicateRootExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.DuplicateRootException";
  private static final long serialVersionUID = 1247122483;
  
  public DuplicateRootExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateRootExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateRootExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateRootExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateRootExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateRootExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateRootExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateRootExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BROWSERFIELD = "browserField";
  public static java.lang.String BROWSERROOT = "browserRoot";
  public static java.lang.String ID = "id";
  public String getBrowserField()
  {
    return getValue(BROWSERFIELD);
  }
  
  public void setBrowserField(String value)
  {
    if(value == null)
    {
      setValue(BROWSERFIELD, "");
    }
    else
    {
      setValue(BROWSERFIELD, value);
    }
  }
  
  public boolean isBrowserFieldWritable()
  {
    return isWritable(BROWSERFIELD);
  }
  
  public boolean isBrowserFieldReadable()
  {
    return isReadable(BROWSERFIELD);
  }
  
  public boolean isBrowserFieldModified()
  {
    return isModified(BROWSERFIELD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBrowserFieldMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERFIELD).getAttributeMdDTO();
  }
  
  public String getBrowserRoot()
  {
    return getValue(BROWSERROOT);
  }
  
  public void setBrowserRoot(String value)
  {
    if(value == null)
    {
      setValue(BROWSERROOT, "");
    }
    else
    {
      setValue(BROWSERROOT, value);
    }
  }
  
  public boolean isBrowserRootWritable()
  {
    return isWritable(BROWSERROOT);
  }
  
  public boolean isBrowserRootReadable()
  {
    return isReadable(BROWSERROOT);
  }
  
  public boolean isBrowserRootModified()
  {
    return isModified(BROWSERROOT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBrowserRootMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERROOT).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.ontology.DuplicateRootException", locale);
      
      message = message.replace("{browserField}", this.getBrowserField().toString());
      message = message.replace("{browserRoot}", this.getBrowserRoot().toString());
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
