package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = 146106593)
public abstract class DuplicateParentExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.DuplicateParentException";
  private static final long serialVersionUID = 146106593;
  
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
  
  public static java.lang.String CHILDTERM = "childTerm";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTTERM = "parentTerm";
  public String getChildTerm()
  {
    return getValue(CHILDTERM);
  }
  
  public void setChildTerm(String value)
  {
    if(value == null)
    {
      setValue(CHILDTERM, "");
    }
    else
    {
      setValue(CHILDTERM, value);
    }
  }
  
  public boolean isChildTermWritable()
  {
    return isWritable(CHILDTERM);
  }
  
  public boolean isChildTermReadable()
  {
    return isReadable(CHILDTERM);
  }
  
  public boolean isChildTermModified()
  {
    return isModified(CHILDTERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getChildTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CHILDTERM).getAttributeMdDTO();
  }
  
  public String getParentTerm()
  {
    return getValue(PARENTTERM);
  }
  
  public void setParentTerm(String value)
  {
    if(value == null)
    {
      setValue(PARENTTERM, "");
    }
    else
    {
      setValue(PARENTTERM, value);
    }
  }
  
  public boolean isParentTermWritable()
  {
    return isWritable(PARENTTERM);
  }
  
  public boolean isParentTermReadable()
  {
    return isReadable(PARENTTERM);
  }
  
  public boolean isParentTermModified()
  {
    return isModified(PARENTTERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTTERM).getAttributeMdDTO();
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
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.ontology.DuplicateParentException", locale);
      
      message = message.replace("{childTerm}", this.getChildTerm().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{parentTerm}", this.getParentTerm().toString());
      
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
