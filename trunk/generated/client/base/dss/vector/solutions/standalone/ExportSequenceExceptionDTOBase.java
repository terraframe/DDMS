package dss.vector.solutions.standalone;

@com.terraframe.mojo.business.ClassSignature(hash = -728642074)
public abstract class ExportSequenceExceptionDTOBase extends com.terraframe.mojo.business.SmartExceptionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.standalone.ExportSequenceException";
  private static final long serialVersionUID = -728642074;
  
  public ExportSequenceExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected ExportSequenceExceptionDTOBase(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ExportSequenceExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ExportSequenceExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ExportSequenceExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ExportSequenceExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ExportSequenceExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ExportSequenceExceptionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDSEQUENCE = "endSequence";
  public static java.lang.String ID = "id";
  public static java.lang.String STARTSEQUENCE = "startSequence";
  public Long getEndSequence()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(ENDSEQUENCE));
  }
  
  public void setEndSequence(Long value)
  {
    if(value == null)
    {
      setValue(ENDSEQUENCE, "");
    }
    else
    {
      setValue(ENDSEQUENCE, java.lang.Long.toString(value));
    }
  }
  
  public boolean isEndSequenceWritable()
  {
    return isWritable(ENDSEQUENCE);
  }
  
  public boolean isEndSequenceReadable()
  {
    return isReadable(ENDSEQUENCE);
  }
  
  public boolean isEndSequenceModified()
  {
    return isModified(ENDSEQUENCE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getEndSequenceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ENDSEQUENCE).getAttributeMdDTO();
  }
  
  public Long getStartSequence()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(STARTSEQUENCE));
  }
  
  public void setStartSequence(Long value)
  {
    if(value == null)
    {
      setValue(STARTSEQUENCE, "");
    }
    else
    {
      setValue(STARTSEQUENCE, java.lang.Long.toString(value));
    }
  }
  
  public boolean isStartSequenceWritable()
  {
    return isWritable(STARTSEQUENCE);
  }
  
  public boolean isStartSequenceReadable()
  {
    return isReadable(STARTSEQUENCE);
  }
  
  public boolean isStartSequenceModified()
  {
    return isModified(STARTSEQUENCE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getStartSequenceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STARTSEQUENCE).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.standalone.ExportSequenceException", locale);
      
      message = message.replace("{endSequence}", this.getEndSequence().toString());
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{startSequence}", this.getStartSequence().toString());
      
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
