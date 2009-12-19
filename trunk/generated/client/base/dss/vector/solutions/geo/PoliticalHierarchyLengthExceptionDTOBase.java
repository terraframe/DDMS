package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = 230471356)
public abstract class PoliticalHierarchyLengthExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.PoliticalHierarchyLengthException";
  private static final long serialVersionUID = 230471356;
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected PoliticalHierarchyLengthExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public PoliticalHierarchyLengthExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String HIERARCHYLENGTH = "hierarchyLength";
  public static java.lang.String ID = "id";
  public static java.lang.String SLOTS = "slots";
  public Integer getHierarchyLength()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HIERARCHYLENGTH));
  }
  
  public void setHierarchyLength(Integer value)
  {
    if(value == null)
    {
      setValue(HIERARCHYLENGTH, "");
    }
    else
    {
      setValue(HIERARCHYLENGTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isHierarchyLengthWritable()
  {
    return isWritable(HIERARCHYLENGTH);
  }
  
  public boolean isHierarchyLengthReadable()
  {
    return isReadable(HIERARCHYLENGTH);
  }
  
  public boolean isHierarchyLengthModified()
  {
    return isModified(HIERARCHYLENGTH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getHierarchyLengthMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HIERARCHYLENGTH).getAttributeMdDTO();
  }
  
  public Integer getSlots()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SLOTS));
  }
  
  public void setSlots(Integer value)
  {
    if(value == null)
    {
      setValue(SLOTS, "");
    }
    else
    {
      setValue(SLOTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSlotsWritable()
  {
    return isWritable(SLOTS);
  }
  
  public boolean isSlotsReadable()
  {
    return isReadable(SLOTS);
  }
  
  public boolean isSlotsModified()
  {
    return isModified(SLOTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSlotsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SLOTS).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.PoliticalHierarchyLengthException", locale);
      
      message = message.replace("{hierarchyLength}", this.getHierarchyLength().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{slots}", this.getSlots().toString());
      
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
