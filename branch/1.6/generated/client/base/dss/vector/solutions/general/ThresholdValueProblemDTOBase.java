package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1950685556)
public abstract class ThresholdValueProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ThresholdValueProblem";
  private static final long serialVersionUID = -1950685556;
  
  public ThresholdValueProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public ThresholdValueProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String THRESHOLD = "threshold";
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public boolean isEntityLabelWritable()
  {
    return isWritable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelReadable()
  {
    return isReadable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelModified()
  {
    return isModified(ENTITYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public Double getThreshold()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(THRESHOLD));
  }
  
  public void setThreshold(Double value)
  {
    if(value == null)
    {
      setValue(THRESHOLD, "");
    }
    else
    {
      setValue(THRESHOLD, java.lang.Double.toString(value));
    }
  }
  
  public boolean isThresholdWritable()
  {
    return isWritable(THRESHOLD);
  }
  
  public boolean isThresholdReadable()
  {
    return isReadable(THRESHOLD);
  }
  
  public boolean isThresholdModified()
  {
    return isModified(THRESHOLD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getThresholdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(THRESHOLD).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{entityLabel}", this.getEntityLabel().toString());
    template = template.replace("{threshold}", this.getThreshold().toString());
    
    return template;
  }
  
}
