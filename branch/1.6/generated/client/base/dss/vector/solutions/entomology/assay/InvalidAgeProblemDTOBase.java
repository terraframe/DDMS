package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -1048444498)
public abstract class InvalidAgeProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidAgeProblem";
  private static final long serialVersionUID = -1048444498;
  
  public InvalidAgeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidAgeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGE = "age";
  public Integer getAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
  }
  
  public void setAge(Integer value)
  {
    if(value == null)
    {
      setValue(AGE, "");
    }
    else
    {
      setValue(AGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAgeWritable()
  {
    return isWritable(AGE);
  }
  
  public boolean isAgeReadable()
  {
    return isReadable(AGE);
  }
  
  public boolean isAgeModified()
  {
    return isModified(AGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAgeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AGE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{age}", this.getAge().toString());
    
    return template;
  }
  
}
