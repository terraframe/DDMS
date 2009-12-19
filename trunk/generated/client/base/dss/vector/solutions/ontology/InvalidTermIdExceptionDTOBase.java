package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = 1893659243)
public abstract class InvalidTermIdExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.InvalidTermIdException";
  private static final long serialVersionUID = 1893659243;
  
  public InvalidTermIdExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected InvalidTermIdExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InvalidTermIdExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InvalidTermIdExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InvalidTermIdExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InvalidTermIdExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InvalidTermIdExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InvalidTermIdExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String TERMID = "termId";
  public String getTermId()
  {
    return getValue(TERMID);
  }
  
  public void setTermId(String value)
  {
    if(value == null)
    {
      setValue(TERMID, "");
    }
    else
    {
      setValue(TERMID, value);
    }
  }
  
  public boolean isTermIdWritable()
  {
    return isWritable(TERMID);
  }
  
  public boolean isTermIdReadable()
  {
    return isReadable(TERMID);
  }
  
  public boolean isTermIdModified()
  {
    return isModified(TERMID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTermIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMID).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.ontology.InvalidTermIdException", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{termId}", this.getTermId().toString());
      
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
