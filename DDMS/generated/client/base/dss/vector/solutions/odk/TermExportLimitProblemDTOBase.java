package dss.vector.solutions.odk;

@com.runwaysdk.business.ClassSignature(hash = -1641921276)
public abstract class TermExportLimitProblemDTOBase extends com.runwaysdk.business.ProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.odk.TermExportLimitProblem";
  private static final long serialVersionUID = -1641921276;
  
  public TermExportLimitProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public TermExportLimitProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRIBUTELABEL = "attributeLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String LIMIT = "limit";
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
  
  public Integer getLimit()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LIMIT));
  }
  
  public void setLimit(Integer value)
  {
    if(value == null)
    {
      setValue(LIMIT, "");
    }
    else
    {
      setValue(LIMIT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLimitWritable()
  {
    return isWritable(LIMIT);
  }
  
  public boolean isLimitReadable()
  {
    return isReadable(LIMIT);
  }
  
  public boolean isLimitModified()
  {
    return isModified(LIMIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLimitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LIMIT).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{attributeLabel}", this.getAttributeLabel().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{limit}", this.getLimit().toString());
    
    return template;
  }
  
}
