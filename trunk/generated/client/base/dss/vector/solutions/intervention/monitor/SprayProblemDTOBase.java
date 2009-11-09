package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 662766990)
public abstract class SprayProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.SprayProblem";
  private static final long serialVersionUID = 662766990;
  
  public SprayProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public SprayProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String MONTHS = "months";
  public Integer getMonths()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MONTHS));
  }
  
  public void setMonths(Integer value)
  {
    if(value == null)
    {
      setValue(MONTHS, "");
    }
    else
    {
      setValue(MONTHS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMonthsWritable()
  {
    return isWritable(MONTHS);
  }
  
  public boolean isMonthsReadable()
  {
    return isReadable(MONTHS);
  }
  
  public boolean isMonthsModified()
  {
    return isModified(MONTHS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMonthsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MONTHS).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.intervention.monitor.SprayProblem", locale);
      
      message = message.replace("{months}", this.getMonths().toString());
      
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
