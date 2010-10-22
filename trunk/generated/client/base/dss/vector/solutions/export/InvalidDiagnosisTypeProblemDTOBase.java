package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -682813550)
public abstract class InvalidDiagnosisTypeProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.InvalidDiagnosisTypeProblem";
  private static final long serialVersionUID = -682813550;
  
  public InvalidDiagnosisTypeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidDiagnosisTypeProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DIAGNOSISTYPE = "diagnosisType";
  public static java.lang.String ID = "id";
  public String getDiagnosisType()
  {
    return getValue(DIAGNOSISTYPE);
  }
  
  public void setDiagnosisType(String value)
  {
    if(value == null)
    {
      setValue(DIAGNOSISTYPE, "");
    }
    else
    {
      setValue(DIAGNOSISTYPE, value);
    }
  }
  
  public boolean isDiagnosisTypeWritable()
  {
    return isWritable(DIAGNOSISTYPE);
  }
  
  public boolean isDiagnosisTypeReadable()
  {
    return isReadable(DIAGNOSISTYPE);
  }
  
  public boolean isDiagnosisTypeModified()
  {
    return isModified(DIAGNOSISTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDiagnosisTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DIAGNOSISTYPE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{diagnosisType}", this.getDiagnosisType().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
