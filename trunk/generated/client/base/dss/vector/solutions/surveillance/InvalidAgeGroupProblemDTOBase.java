package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = 469458641)
public abstract class InvalidAgeGroupProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.InvalidAgeGroupProblem";
  private static final long serialVersionUID = 469458641;
  
  public InvalidAgeGroupProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidAgeGroupProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDAGE = "endAge";
  public static java.lang.String STARTAGE = "startAge";
  public Integer getEndAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ENDAGE));
  }
  
  public void setEndAge(Integer value)
  {
    if(value == null)
    {
      setValue(ENDAGE, "");
    }
    else
    {
      setValue(ENDAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEndAgeWritable()
  {
    return isWritable(ENDAGE);
  }
  
  public boolean isEndAgeReadable()
  {
    return isReadable(ENDAGE);
  }
  
  public boolean isEndAgeModified()
  {
    return isModified(ENDAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getEndAgeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ENDAGE).getAttributeMdDTO();
  }
  
  public Integer getStartAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STARTAGE));
  }
  
  public void setStartAge(Integer value)
  {
    if(value == null)
    {
      setValue(STARTAGE, "");
    }
    else
    {
      setValue(STARTAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStartAgeWritable()
  {
    return isWritable(STARTAGE);
  }
  
  public boolean isStartAgeReadable()
  {
    return isReadable(STARTAGE);
  }
  
  public boolean isStartAgeModified()
  {
    return isModified(STARTAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStartAgeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STARTAGE).getAttributeMdDTO();
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
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.surveillance.InvalidAgeGroupProblem", locale);
      
      message = message.replace("{endAge}", this.getEndAge().toString());
      message = message.replace("{startAge}", this.getStartAge().toString());
      
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
