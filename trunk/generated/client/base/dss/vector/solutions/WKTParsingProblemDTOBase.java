package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 352669096)
public abstract class WKTParsingProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.WKTParsingProblem";
  private static final long serialVersionUID = 352669096;
  
  public WKTParsingProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public WKTParsingProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String REASON = "reason";
  public String getReason()
  {
    return getValue(REASON);
  }
  
  public void setReason(String value)
  {
    if(value == null)
    {
      setValue(REASON, "");
    }
    else
    {
      setValue(REASON, value);
    }
  }
  
  public boolean isReasonWritable()
  {
    return isWritable(REASON);
  }
  
  public boolean isReasonReadable()
  {
    return isReadable(REASON);
  }
  
  public boolean isReasonModified()
  {
    return isModified(REASON);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getReasonMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(REASON).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.WKTParsingProblem", locale);
      
      message = message.replace("{reason}", this.getReason().toString());
      
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
