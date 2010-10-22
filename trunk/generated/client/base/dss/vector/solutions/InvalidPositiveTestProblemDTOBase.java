package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -501501883)
public abstract class InvalidPositiveTestProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.InvalidPositiveTestProblem";
  private static final long serialVersionUID = -501501883;
  
  public InvalidPositiveTestProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidPositiveTestProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String AMOUNTPOSITIVE = "amountPositive";
  public Integer getAmount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNT));
  }
  
  public void setAmount(Integer value)
  {
    if(value == null)
    {
      setValue(AMOUNT, "");
    }
    else
    {
      setValue(AMOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAmountWritable()
  {
    return isWritable(AMOUNT);
  }
  
  public boolean isAmountReadable()
  {
    return isReadable(AMOUNT);
  }
  
  public boolean isAmountModified()
  {
    return isModified(AMOUNT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAmountMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AMOUNT).getAttributeMdDTO();
  }
  
  public Integer getAmountPositive()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNTPOSITIVE));
  }
  
  public void setAmountPositive(Integer value)
  {
    if(value == null)
    {
      setValue(AMOUNTPOSITIVE, "");
    }
    else
    {
      setValue(AMOUNTPOSITIVE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAmountPositiveWritable()
  {
    return isWritable(AMOUNTPOSITIVE);
  }
  
  public boolean isAmountPositiveReadable()
  {
    return isReadable(AMOUNTPOSITIVE);
  }
  
  public boolean isAmountPositiveModified()
  {
    return isModified(AMOUNTPOSITIVE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAmountPositiveMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AMOUNTPOSITIVE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{amount}", this.getAmount().toString());
    template = template.replace("{amountPositive}", this.getAmountPositive().toString());
    
    return template;
  }
  
}
