package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -1606577895)
public abstract class InvalidFedQuantityProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidFedQuantityProblem";
  private static final long serialVersionUID = -1606577895;
  
  public InvalidFedQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidFedQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FED = "fed";
  public Integer getFed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FED));
  }
  
  public void setFed(Integer value)
  {
    if(value == null)
    {
      setValue(FED, "");
    }
    else
    {
      setValue(FED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFedWritable()
  {
    return isWritable(FED);
  }
  
  public boolean isFedReadable()
  {
    return isReadable(FED);
  }
  
  public boolean isFedModified()
  {
    return isModified(FED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FED).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{fed}", this.getFed().toString());
    
    return template;
  }
  
}
