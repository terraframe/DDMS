package dss.vector.solutions.geo;

public abstract class DuplicateParentExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateParentException";
  public DuplicateParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateParentExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CHILDENTITYNAME = "childEntityName";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTENTITYNAME = "parentEntityName";
  public String getChildEntityName()
  {
    return getValue(CHILDENTITYNAME);
  }
  
  public void setChildEntityName(String value)
  {
    if(value == null)
    {
      setValue(CHILDENTITYNAME, "");
    }
    else
    {
      setValue(CHILDENTITYNAME, value);
    }
  }
  
  public boolean isChildEntityNameWritable()
  {
    return isWritable(CHILDENTITYNAME);
  }
  
  public boolean isChildEntityNameReadable()
  {
    return isReadable(CHILDENTITYNAME);
  }
  
  public boolean isChildEntityNameModified()
  {
    return isModified(CHILDENTITYNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getChildEntityNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CHILDENTITYNAME).getAttributeMdDTO();
  }
  
  public String getParentEntityName()
  {
    return getValue(PARENTENTITYNAME);
  }
  
  public void setParentEntityName(String value)
  {
    if(value == null)
    {
      setValue(PARENTENTITYNAME, "");
    }
    else
    {
      setValue(PARENTENTITYNAME, value);
    }
  }
  
  public boolean isParentEntityNameWritable()
  {
    return isWritable(PARENTENTITYNAME);
  }
  
  public boolean isParentEntityNameReadable()
  {
    return isReadable(PARENTENTITYNAME);
  }
  
  public boolean isParentEntityNameModified()
  {
    return isModified(PARENTENTITYNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getParentEntityNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTENTITYNAME).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.DuplicateParentException", locale);
      
      message = message.replace("{childEntityName}", this.getChildEntityName().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{parentEntityName}", this.getParentEntityName().toString());
      
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
