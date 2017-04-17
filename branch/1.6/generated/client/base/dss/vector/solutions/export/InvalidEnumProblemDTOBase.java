package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 712297690)
public abstract class InvalidEnumProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.InvalidEnumProblem";
  private static final long serialVersionUID = 712297690;
  
  public InvalidEnumProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidEnumProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENUMLABEL = "enumLabel";
  public static java.lang.String ENUMNAME = "enumName";
  public static java.lang.String ID = "id";
  public String getEnumLabel()
  {
    return getValue(ENUMLABEL);
  }
  
  public void setEnumLabel(String value)
  {
    if(value == null)
    {
      setValue(ENUMLABEL, "");
    }
    else
    {
      setValue(ENUMLABEL, value);
    }
  }
  
  public boolean isEnumLabelWritable()
  {
    return isWritable(ENUMLABEL);
  }
  
  public boolean isEnumLabelReadable()
  {
    return isReadable(ENUMLABEL);
  }
  
  public boolean isEnumLabelModified()
  {
    return isModified(ENUMLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEnumLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENUMLABEL).getAttributeMdDTO();
  }
  
  public String getEnumName()
  {
    return getValue(ENUMNAME);
  }
  
  public void setEnumName(String value)
  {
    if(value == null)
    {
      setValue(ENUMNAME, "");
    }
    else
    {
      setValue(ENUMNAME, value);
    }
  }
  
  public boolean isEnumNameWritable()
  {
    return isWritable(ENUMNAME);
  }
  
  public boolean isEnumNameReadable()
  {
    return isReadable(ENUMNAME);
  }
  
  public boolean isEnumNameModified()
  {
    return isModified(ENUMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEnumNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENUMNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{enumLabel}", this.getEnumLabel().toString());
    template = template.replace("{enumName}", this.getEnumName().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
