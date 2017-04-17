package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1210159160)
public abstract class InvalidPercentageProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.InvalidPercentageProblem";
  private static final long serialVersionUID = 1210159160;
  
  public InvalidPercentageProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidPercentageProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String INVALIDPERCENT = "invalidPercent";
  public Integer getInvalidPercent()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INVALIDPERCENT));
  }
  
  public void setInvalidPercent(Integer value)
  {
    if(value == null)
    {
      setValue(INVALIDPERCENT, "");
    }
    else
    {
      setValue(INVALIDPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInvalidPercentWritable()
  {
    return isWritable(INVALIDPERCENT);
  }
  
  public boolean isInvalidPercentReadable()
  {
    return isReadable(INVALIDPERCENT);
  }
  
  public boolean isInvalidPercentModified()
  {
    return isModified(INVALIDPERCENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getInvalidPercentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INVALIDPERCENT).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{invalidPercent}", this.getInvalidPercent().toString());
    
    return template;
  }
  
}
