package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -896122172)
public abstract class PeriodWeekProblemDTOBase extends com.terraframe.mojo.business.ProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PeriodWeekProblem";
  private static final long serialVersionUID = -896122172;
  
  public PeriodWeekProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public PeriodWeekProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String MAXPERIOD = "maxPeriod";
  public static java.lang.String PERIOD = "period";
  public Integer getMaxPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAXPERIOD));
  }
  
  public void setMaxPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(MAXPERIOD, "");
    }
    else
    {
      setValue(MAXPERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMaxPeriodWritable()
  {
    return isWritable(MAXPERIOD);
  }
  
  public boolean isMaxPeriodReadable()
  {
    return isReadable(MAXPERIOD);
  }
  
  public boolean isMaxPeriodModified()
  {
    return isModified(MAXPERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMaxPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MAXPERIOD).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodWritable()
  {
    return isWritable(PERIOD);
  }
  
  public boolean isPeriodReadable()
  {
    return isReadable(PERIOD);
  }
  
  public boolean isPeriodModified()
  {
    return isModified(PERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.PeriodWeekProblem", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{maxPeriod}", this.getMaxPeriod().toString());
      message = message.replace("{period}", this.getPeriod().toString());
      
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
