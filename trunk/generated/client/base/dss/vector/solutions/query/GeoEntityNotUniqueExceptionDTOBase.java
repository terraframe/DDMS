package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1386751600)
public abstract class GeoEntityNotUniqueExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.GeoEntityNotUniqueException";
  private static final long serialVersionUID = 1386751600;
  
  public GeoEntityNotUniqueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected GeoEntityNotUniqueExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public GeoEntityNotUniqueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public GeoEntityNotUniqueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public GeoEntityNotUniqueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public GeoEntityNotUniqueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public GeoEntityNotUniqueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public GeoEntityNotUniqueExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENTITYTYPE = "entityType";
  public static java.lang.String ID = "id";
  public String getEntityType()
  {
    return getValue(ENTITYTYPE);
  }
  
  public void setEntityType(String value)
  {
    if(value == null)
    {
      setValue(ENTITYTYPE, "");
    }
    else
    {
      setValue(ENTITYTYPE, value);
    }
  }
  
  public boolean isEntityTypeWritable()
  {
    return isWritable(ENTITYTYPE);
  }
  
  public boolean isEntityTypeReadable()
  {
    return isReadable(ENTITYTYPE);
  }
  
  public boolean isEntityTypeModified()
  {
    return isModified(ENTITYTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getEntityTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYTYPE).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.query.GeoEntityNotUniqueException", locale);
      
      message = message.replace("{entityType}", this.getEntityType().toString());
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
