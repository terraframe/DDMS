package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 1454959510)
public abstract class ResistancePropertyExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.ResistancePropertyException";
  private static final long serialVersionUID = 1454959510;
  
  public ResistancePropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected ResistancePropertyExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ResistancePropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ResistancePropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ResistancePropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ResistancePropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ResistancePropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ResistancePropertyExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LOWERLABEL = "lowerLabel";
  public static java.lang.String LOWERVALUE = "lowerValue";
  public static java.lang.String UPPERLABEL = "upperLabel";
  public static java.lang.String UPPERVALUE = "upperValue";
  public String getLowerLabel()
  {
    return getValue(LOWERLABEL);
  }
  
  public void setLowerLabel(String value)
  {
    if(value == null)
    {
      setValue(LOWERLABEL, "");
    }
    else
    {
      setValue(LOWERLABEL, value);
    }
  }
  
  public boolean isLowerLabelWritable()
  {
    return isWritable(LOWERLABEL);
  }
  
  public boolean isLowerLabelReadable()
  {
    return isReadable(LOWERLABEL);
  }
  
  public boolean isLowerLabelModified()
  {
    return isModified(LOWERLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLowerLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LOWERLABEL).getAttributeMdDTO();
  }
  
  public Integer getLowerValue()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOWERVALUE));
  }
  
  public void setLowerValue(Integer value)
  {
    if(value == null)
    {
      setValue(LOWERVALUE, "");
    }
    else
    {
      setValue(LOWERVALUE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLowerValueWritable()
  {
    return isWritable(LOWERVALUE);
  }
  
  public boolean isLowerValueReadable()
  {
    return isReadable(LOWERVALUE);
  }
  
  public boolean isLowerValueModified()
  {
    return isModified(LOWERVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLowerValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOWERVALUE).getAttributeMdDTO();
  }
  
  public String getUpperLabel()
  {
    return getValue(UPPERLABEL);
  }
  
  public void setUpperLabel(String value)
  {
    if(value == null)
    {
      setValue(UPPERLABEL, "");
    }
    else
    {
      setValue(UPPERLABEL, value);
    }
  }
  
  public boolean isUpperLabelWritable()
  {
    return isWritable(UPPERLABEL);
  }
  
  public boolean isUpperLabelReadable()
  {
    return isReadable(UPPERLABEL);
  }
  
  public boolean isUpperLabelModified()
  {
    return isModified(UPPERLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUpperLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UPPERLABEL).getAttributeMdDTO();
  }
  
  public Integer getUpperValue()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UPPERVALUE));
  }
  
  public void setUpperValue(Integer value)
  {
    if(value == null)
    {
      setValue(UPPERVALUE, "");
    }
    else
    {
      setValue(UPPERVALUE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUpperValueWritable()
  {
    return isWritable(UPPERVALUE);
  }
  
  public boolean isUpperValueReadable()
  {
    return isReadable(UPPERVALUE);
  }
  
  public boolean isUpperValueModified()
  {
    return isModified(UPPERVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUpperValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UPPERVALUE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{lowerLabel}", this.getLowerLabel().toString());
    template = template.replace("{lowerValue}", this.getLowerValue().toString());
    template = template.replace("{upperLabel}", this.getUpperLabel().toString());
    template = template.replace("{upperValue}", this.getUpperValue().toString());
    
    return template;
  }
  
}
