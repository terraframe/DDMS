package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -1925398131)
public abstract class NotificationProblemDTOBase extends com.terraframe.mojo.business.ProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.NotificationProblem";
  private static final long serialVersionUID = -1925398131;
  
  public NotificationProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public NotificationProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRIBUTEDISPLAYLABEL = "attributeDisplayLabel";
  public static java.lang.String ATTRIBUTENAME = "attributeName";
  public static java.lang.String COMPONENTID = "componentId";
  public static java.lang.String DEFININGTYPE = "definingType";
  public static java.lang.String DEFININGTYPEDISPLAYLABEL = "definingTypeDisplayLabel";
  public static java.lang.String ID = "id";
  public String getAttributeDisplayLabel()
  {
    return getValue(ATTRIBUTEDISPLAYLABEL);
  }
  
  public void setAttributeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEDISPLAYLABEL, "");
    }
    else
    {
      setValue(ATTRIBUTEDISPLAYLABEL, value);
    }
  }
  
  public boolean isAttributeDisplayLabelWritable()
  {
    return isWritable(ATTRIBUTEDISPLAYLABEL);
  }
  
  public boolean isAttributeDisplayLabelReadable()
  {
    return isReadable(ATTRIBUTEDISPLAYLABEL);
  }
  
  public boolean isAttributeDisplayLabelModified()
  {
    return isModified(ATTRIBUTEDISPLAYLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAttributeDisplayLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRIBUTEDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getAttributeName()
  {
    return getValue(ATTRIBUTENAME);
  }
  
  public void setAttributeName(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTENAME, "");
    }
    else
    {
      setValue(ATTRIBUTENAME, value);
    }
  }
  
  public boolean isAttributeNameWritable()
  {
    return isWritable(ATTRIBUTENAME);
  }
  
  public boolean isAttributeNameReadable()
  {
    return isReadable(ATTRIBUTENAME);
  }
  
  public boolean isAttributeNameModified()
  {
    return isModified(ATTRIBUTENAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAttributeNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRIBUTENAME).getAttributeMdDTO();
  }
  
  public String getComponentId()
  {
    return getValue(COMPONENTID);
  }
  
  public void setComponentId(String value)
  {
    if(value == null)
    {
      setValue(COMPONENTID, "");
    }
    else
    {
      setValue(COMPONENTID, value);
    }
  }
  
  public boolean isComponentIdWritable()
  {
    return isWritable(COMPONENTID);
  }
  
  public boolean isComponentIdReadable()
  {
    return isReadable(COMPONENTID);
  }
  
  public boolean isComponentIdModified()
  {
    return isModified(COMPONENTID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getComponentIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COMPONENTID).getAttributeMdDTO();
  }
  
  public String getDefiningType()
  {
    return getValue(DEFININGTYPE);
  }
  
  public void setDefiningType(String value)
  {
    if(value == null)
    {
      setValue(DEFININGTYPE, "");
    }
    else
    {
      setValue(DEFININGTYPE, value);
    }
  }
  
  public boolean isDefiningTypeWritable()
  {
    return isWritable(DEFININGTYPE);
  }
  
  public boolean isDefiningTypeReadable()
  {
    return isReadable(DEFININGTYPE);
  }
  
  public boolean isDefiningTypeModified()
  {
    return isModified(DEFININGTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDefiningTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DEFININGTYPE).getAttributeMdDTO();
  }
  
  public String getDefiningTypeDisplayLabel()
  {
    return getValue(DEFININGTYPEDISPLAYLABEL);
  }
  
  public void setDefiningTypeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DEFININGTYPEDISPLAYLABEL, "");
    }
    else
    {
      setValue(DEFININGTYPEDISPLAYLABEL, value);
    }
  }
  
  public boolean isDefiningTypeDisplayLabelWritable()
  {
    return isWritable(DEFININGTYPEDISPLAYLABEL);
  }
  
  public boolean isDefiningTypeDisplayLabelReadable()
  {
    return isReadable(DEFININGTYPEDISPLAYLABEL);
  }
  
  public boolean isDefiningTypeDisplayLabelModified()
  {
    return isModified(DEFININGTYPEDISPLAYLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDefiningTypeDisplayLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DEFININGTYPEDISPLAYLABEL).getAttributeMdDTO();
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
      return localizedMessage;
    }
  }
  private java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.NotificationProblem", locale);
      
      message = message.replace("{attributeDisplayLabel}", this.getAttributeDisplayLabel().toString());
      message = message.replace("{attributeName}", this.getAttributeName().toString());
      message = message.replace("{componentId}", this.getComponentId().toString());
      message = message.replace("{definingType}", this.getDefiningType().toString());
      message = message.replace("{definingTypeDisplayLabel}", this.getDefiningTypeDisplayLabel().toString());
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
