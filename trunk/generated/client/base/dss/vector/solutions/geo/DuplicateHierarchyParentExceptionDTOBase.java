package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = 2059194226)
public abstract class DuplicateHierarchyParentExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateHierarchyParentException";
  private static final long serialVersionUID = 2059194226;
  
  public DuplicateHierarchyParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateHierarchyParentExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CHILDDISPLAYLABEL = "childDisplayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTDISPLAYLABEL = "parentDisplayLabel";
  public String getChildDisplayLabel()
  {
    return getValue(CHILDDISPLAYLABEL);
  }
  
  public void setChildDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(CHILDDISPLAYLABEL, "");
    }
    else
    {
      setValue(CHILDDISPLAYLABEL, value);
    }
  }
  
  public boolean isChildDisplayLabelWritable()
  {
    return isWritable(CHILDDISPLAYLABEL);
  }
  
  public boolean isChildDisplayLabelReadable()
  {
    return isReadable(CHILDDISPLAYLABEL);
  }
  
  public boolean isChildDisplayLabelModified()
  {
    return isModified(CHILDDISPLAYLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getChildDisplayLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CHILDDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getParentDisplayLabel()
  {
    return getValue(PARENTDISPLAYLABEL);
  }
  
  public void setParentDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(PARENTDISPLAYLABEL, "");
    }
    else
    {
      setValue(PARENTDISPLAYLABEL, value);
    }
  }
  
  public boolean isParentDisplayLabelWritable()
  {
    return isWritable(PARENTDISPLAYLABEL);
  }
  
  public boolean isParentDisplayLabelReadable()
  {
    return isReadable(PARENTDISPLAYLABEL);
  }
  
  public boolean isParentDisplayLabelModified()
  {
    return isModified(PARENTDISPLAYLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getParentDisplayLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTDISPLAYLABEL).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.DuplicateHierarchyParentException", locale);
      
      message = message.replace("{childDisplayLabel}", this.getChildDisplayLabel().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{parentDisplayLabel}", this.getParentDisplayLabel().toString());
      
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
