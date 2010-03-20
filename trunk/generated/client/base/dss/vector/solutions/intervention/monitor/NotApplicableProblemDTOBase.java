package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1903388378)
public abstract class NotApplicableProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.NotApplicableProblem";
  private static final long serialVersionUID = 1903388378;
  
  public NotApplicableProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public NotApplicableProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String INPUTATTRIBUTE = "inputAttribute";
  public static java.lang.String INPUTVALUE = "inputValue";
  public String getInputAttribute()
  {
    return getValue(INPUTATTRIBUTE);
  }
  
  public void setInputAttribute(String value)
  {
    if(value == null)
    {
      setValue(INPUTATTRIBUTE, "");
    }
    else
    {
      setValue(INPUTATTRIBUTE, value);
    }
  }
  
  public boolean isInputAttributeWritable()
  {
    return isWritable(INPUTATTRIBUTE);
  }
  
  public boolean isInputAttributeReadable()
  {
    return isReadable(INPUTATTRIBUTE);
  }
  
  public boolean isInputAttributeModified()
  {
    return isModified(INPUTATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInputAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INPUTATTRIBUTE).getAttributeMdDTO();
  }
  
  public String getInputValue()
  {
    return getValue(INPUTVALUE);
  }
  
  public void setInputValue(String value)
  {
    if(value == null)
    {
      setValue(INPUTVALUE, "");
    }
    else
    {
      setValue(INPUTVALUE, value);
    }
  }
  
  public boolean isInputValueWritable()
  {
    return isWritable(INPUTVALUE);
  }
  
  public boolean isInputValueReadable()
  {
    return isReadable(INPUTVALUE);
  }
  
  public boolean isInputValueModified()
  {
    return isModified(INPUTVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInputValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INPUTVALUE).getAttributeMdDTO();
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
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.intervention.monitor.NotApplicableProblem", locale);
      
      message = message.replace("{inputAttribute}", this.getInputAttribute().toString());
      message = message.replace("{inputValue}", this.getInputValue().toString());
      
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
