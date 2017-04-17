package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 26542355)
public abstract class SupervisorCodeProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SupervisorCodeProblem";
  private static final long serialVersionUID = 26542355;
  
  public SupervisorCodeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public SupervisorCodeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CODE = "code";
  public static java.lang.String ID = "id";
  public String getCode()
  {
    return getValue(CODE);
  }
  
  public void setCode(String value)
  {
    if(value == null)
    {
      setValue(CODE, "");
    }
    else
    {
      setValue(CODE, value);
    }
  }
  
  public boolean isCodeWritable()
  {
    return isWritable(CODE);
  }
  
  public boolean isCodeReadable()
  {
    return isReadable(CODE);
  }
  
  public boolean isCodeModified()
  {
    return isModified(CODE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCodeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CODE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{code}", this.getCode().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
