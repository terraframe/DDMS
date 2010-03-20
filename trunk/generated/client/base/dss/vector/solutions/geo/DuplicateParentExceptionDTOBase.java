package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = -570875053)
public abstract class DuplicateParentExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateParentException";
  private static final long serialVersionUID = -570875053;
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateParentExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getChildEntityNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CHILDENTITYNAME).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentEntityNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTENTITYNAME).getAttributeMdDTO();
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
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.DuplicateParentException", locale);
      
      message = message.replace("{childEntityName}", this.getChildEntityName().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{parentEntityName}", this.getParentEntityName().toString());
      
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
