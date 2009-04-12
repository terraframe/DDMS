package dss.vector.solutions.geo;

public abstract class SpatialTypeDefinedExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.SpatialTypeDefinedException";
  public SpatialTypeDefinedExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected SpatialTypeDefinedExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public SpatialTypeDefinedExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public SpatialTypeDefinedExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public SpatialTypeDefinedExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public SpatialTypeDefinedExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public SpatialTypeDefinedExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public SpatialTypeDefinedExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String ISAPARENTLABEL = "isAParentLabel";
  public static java.lang.String SPATIALLABEL = "spatialLabel";
  public String getIsAParentLabel()
  {
    return getValue(ISAPARENTLABEL);
  }
  
  public void setIsAParentLabel(String value)
  {
    if(value == null)
    {
      setValue(ISAPARENTLABEL, "");
    }
    else
    {
      setValue(ISAPARENTLABEL, value);
    }
  }
  
  public boolean isIsAParentLabelWritable()
  {
    return isWritable(ISAPARENTLABEL);
  }
  
  public boolean isIsAParentLabelReadable()
  {
    return isReadable(ISAPARENTLABEL);
  }
  
  public boolean isIsAParentLabelModified()
  {
    return isModified(ISAPARENTLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getIsAParentLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ISAPARENTLABEL).getAttributeMdDTO();
  }
  
  public String getSpatialLabel()
  {
    return getValue(SPATIALLABEL);
  }
  
  public void setSpatialLabel(String value)
  {
    if(value == null)
    {
      setValue(SPATIALLABEL, "");
    }
    else
    {
      setValue(SPATIALLABEL, value);
    }
  }
  
  public boolean isSpatialLabelWritable()
  {
    return isWritable(SPATIALLABEL);
  }
  
  public boolean isSpatialLabelReadable()
  {
    return isReadable(SPATIALLABEL);
  }
  
  public boolean isSpatialLabelModified()
  {
    return isModified(SPATIALLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSpatialLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPATIALLABEL).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.geo.SpatialTypeDefinedException", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{isAParentLabel}", this.getIsAParentLabel().toString());
      message = message.replace("{spatialLabel}", this.getSpatialLabel().toString());
      
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
