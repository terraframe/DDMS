package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 807300622)
public abstract class InsecticideBrandUseProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrandUseProblem";
  private static final long serialVersionUID = 807300622;
  
  public InsecticideBrandUseProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InsecticideBrandUseProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRIBUTELABEL = "attributeLabel";
  public static java.lang.String ATTRIBUTEVALUE = "attributeValue";
  public static java.lang.String INSECTICIDEUSELABEL = "insecticideUseLabel";
  public static java.lang.String INSECTICIDEUSEVALUE = "insecticideUseValue";
  public String getAttributeLabel()
  {
    return getValue(ATTRIBUTELABEL);
  }
  
  public void setAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTELABEL, "");
    }
    else
    {
      setValue(ATTRIBUTELABEL, value);
    }
  }
  
  public boolean isAttributeLabelWritable()
  {
    return isWritable(ATTRIBUTELABEL);
  }
  
  public boolean isAttributeLabelReadable()
  {
    return isReadable(ATTRIBUTELABEL);
  }
  
  public boolean isAttributeLabelModified()
  {
    return isModified(ATTRIBUTELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getAttributeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ATTRIBUTELABEL).getAttributeMdDTO();
  }
  
  public String getAttributeValue()
  {
    return getValue(ATTRIBUTEVALUE);
  }
  
  public void setAttributeValue(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEVALUE, "");
    }
    else
    {
      setValue(ATTRIBUTEVALUE, value);
    }
  }
  
  public boolean isAttributeValueWritable()
  {
    return isWritable(ATTRIBUTEVALUE);
  }
  
  public boolean isAttributeValueReadable()
  {
    return isReadable(ATTRIBUTEVALUE);
  }
  
  public boolean isAttributeValueModified()
  {
    return isModified(ATTRIBUTEVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getAttributeValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ATTRIBUTEVALUE).getAttributeMdDTO();
  }
  
  public String getInsecticideUseLabel()
  {
    return getValue(INSECTICIDEUSELABEL);
  }
  
  public void setInsecticideUseLabel(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEUSELABEL, "");
    }
    else
    {
      setValue(INSECTICIDEUSELABEL, value);
    }
  }
  
  public boolean isInsecticideUseLabelWritable()
  {
    return isWritable(INSECTICIDEUSELABEL);
  }
  
  public boolean isInsecticideUseLabelReadable()
  {
    return isReadable(INSECTICIDEUSELABEL);
  }
  
  public boolean isInsecticideUseLabelModified()
  {
    return isModified(INSECTICIDEUSELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getInsecticideUseLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(INSECTICIDEUSELABEL).getAttributeMdDTO();
  }
  
  public String getInsecticideUseValue()
  {
    return getValue(INSECTICIDEUSEVALUE);
  }
  
  public void setInsecticideUseValue(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEUSEVALUE, "");
    }
    else
    {
      setValue(INSECTICIDEUSEVALUE, value);
    }
  }
  
  public boolean isInsecticideUseValueWritable()
  {
    return isWritable(INSECTICIDEUSEVALUE);
  }
  
  public boolean isInsecticideUseValueReadable()
  {
    return isReadable(INSECTICIDEUSEVALUE);
  }
  
  public boolean isInsecticideUseValueModified()
  {
    return isModified(INSECTICIDEUSEVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getInsecticideUseValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(INSECTICIDEUSEVALUE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{attributeLabel}", this.getAttributeLabel().toString());
    template = template.replace("{attributeValue}", this.getAttributeValue().toString());
    template = template.replace("{insecticideUseLabel}", this.getInsecticideUseLabel().toString());
    template = template.replace("{insecticideUseValue}", this.getInsecticideUseValue().toString());
    
    return template;
  }
  
}
