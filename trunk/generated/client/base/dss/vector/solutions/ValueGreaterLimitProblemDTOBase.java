package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 266707817)
public abstract class ValueGreaterLimitProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ValueGreaterLimitProblem";
  private static final long serialVersionUID = 266707817;
  
  public ValueGreaterLimitProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public ValueGreaterLimitProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LIMITATTRIBUTELABEL = "limitAttributeLabel";
  public static java.lang.String VALUEATTRIBUTELABEL = "valueAttributeLabel";
  public String getLimitAttributeLabel()
  {
    return getValue(LIMITATTRIBUTELABEL);
  }
  
  public void setLimitAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(LIMITATTRIBUTELABEL, "");
    }
    else
    {
      setValue(LIMITATTRIBUTELABEL, value);
    }
  }
  
  public boolean isLimitAttributeLabelWritable()
  {
    return isWritable(LIMITATTRIBUTELABEL);
  }
  
  public boolean isLimitAttributeLabelReadable()
  {
    return isReadable(LIMITATTRIBUTELABEL);
  }
  
  public boolean isLimitAttributeLabelModified()
  {
    return isModified(LIMITATTRIBUTELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLimitAttributeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LIMITATTRIBUTELABEL).getAttributeMdDTO();
  }
  
  public String getValueAttributeLabel()
  {
    return getValue(VALUEATTRIBUTELABEL);
  }
  
  public void setValueAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(VALUEATTRIBUTELABEL, "");
    }
    else
    {
      setValue(VALUEATTRIBUTELABEL, value);
    }
  }
  
  public boolean isValueAttributeLabelWritable()
  {
    return isWritable(VALUEATTRIBUTELABEL);
  }
  
  public boolean isValueAttributeLabelReadable()
  {
    return isReadable(VALUEATTRIBUTELABEL);
  }
  
  public boolean isValueAttributeLabelModified()
  {
    return isModified(VALUEATTRIBUTELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getValueAttributeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VALUEATTRIBUTELABEL).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{limitAttributeLabel}", this.getLimitAttributeLabel().toString());
    template = template.replace("{valueAttributeLabel}", this.getValueAttributeLabel().toString());
    
    return template;
  }
  
}
