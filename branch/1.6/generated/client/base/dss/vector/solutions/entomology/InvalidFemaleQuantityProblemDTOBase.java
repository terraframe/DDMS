package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 1472766359)
public abstract class InvalidFemaleQuantityProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InvalidFemaleQuantityProblem";
  private static final long serialVersionUID = 1472766359;
  
  public InvalidFemaleQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidFemaleQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String QUANTITYFEMALE = "quantityFemale";
  public Integer getQuantity()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityWritable()
  {
    return isWritable(QUANTITY);
  }
  
  public boolean isQuantityReadable()
  {
    return isReadable(QUANTITY);
  }
  
  public boolean isQuantityModified()
  {
    return isModified(QUANTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITY).getAttributeMdDTO();
  }
  
  public Integer getQuantityFemale()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYFEMALE));
  }
  
  public void setQuantityFemale(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYFEMALE, "");
    }
    else
    {
      setValue(QUANTITYFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityFemaleWritable()
  {
    return isWritable(QUANTITYFEMALE);
  }
  
  public boolean isQuantityFemaleReadable()
  {
    return isReadable(QUANTITYFEMALE);
  }
  
  public boolean isQuantityFemaleModified()
  {
    return isModified(QUANTITYFEMALE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityFemaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYFEMALE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{quantity}", this.getQuantity().toString());
    template = template.replace("{quantityFemale}", this.getQuantityFemale().toString());
    
    return template;
  }
  
}
