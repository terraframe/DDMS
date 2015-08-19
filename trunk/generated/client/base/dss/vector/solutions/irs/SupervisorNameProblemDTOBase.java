package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1878063894)
public abstract class SupervisorNameProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SupervisorNameProblem";
  private static final long serialVersionUID = -1878063894;
  
  public SupervisorNameProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public SupervisorNameProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String NAME = "name";
  public static java.lang.String SURNAME = "surname";
  public String getName()
  {
    return getValue(NAME);
  }
  
  public void setName(String value)
  {
    if(value == null)
    {
      setValue(NAME, "");
    }
    else
    {
      setValue(NAME, value);
    }
  }
  
  public boolean isNameWritable()
  {
    return isWritable(NAME);
  }
  
  public boolean isNameReadable()
  {
    return isReadable(NAME);
  }
  
  public boolean isNameModified()
  {
    return isModified(NAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NAME).getAttributeMdDTO();
  }
  
  public String getSurname()
  {
    return getValue(SURNAME);
  }
  
  public void setSurname(String value)
  {
    if(value == null)
    {
      setValue(SURNAME, "");
    }
    else
    {
      setValue(SURNAME, value);
    }
  }
  
  public boolean isSurnameWritable()
  {
    return isWritable(SURNAME);
  }
  
  public boolean isSurnameReadable()
  {
    return isReadable(SURNAME);
  }
  
  public boolean isSurnameModified()
  {
    return isModified(SURNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSurnameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SURNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{name}", this.getName().toString());
    template = template.replace("{surname}", this.getSurname().toString());
    
    return template;
  }
  
}
