package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -92072912)
public abstract class PeriodQuarterProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PeriodQuarterProblem";
  private static final long serialVersionUID = -92072912;
  
  public PeriodQuarterProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public PeriodQuarterProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAXPERIOD));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getMaxPeriodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MAXPERIOD).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{maxPeriod}", this.getMaxPeriod().toString());
    template = template.replace("{period}", this.getPeriod().toString());
    
    return template;
  }
  
}
