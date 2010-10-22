package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 240615269)
public abstract class RelativeValueProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.RelativeValueProblem";
  private static final long serialVersionUID = 240615269;
  
  public RelativeValueProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public RelativeValueProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String RELATION = "relation";
  public static java.lang.String RELATIVEATTRIBUTELABEL = "relativeAttributeLabel";
  public String getRelation()
  {
    return getValue(RELATION);
  }
  
  public void setRelation(String value)
  {
    if(value == null)
    {
      setValue(RELATION, "");
    }
    else
    {
      setValue(RELATION, value);
    }
  }
  
  public boolean isRelationWritable()
  {
    return isWritable(RELATION);
  }
  
  public boolean isRelationReadable()
  {
    return isReadable(RELATION);
  }
  
  public boolean isRelationModified()
  {
    return isModified(RELATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getRelationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(RELATION).getAttributeMdDTO();
  }
  
  public String getRelativeAttributeLabel()
  {
    return getValue(RELATIVEATTRIBUTELABEL);
  }
  
  public void setRelativeAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(RELATIVEATTRIBUTELABEL, "");
    }
    else
    {
      setValue(RELATIVEATTRIBUTELABEL, value);
    }
  }
  
  public boolean isRelativeAttributeLabelWritable()
  {
    return isWritable(RELATIVEATTRIBUTELABEL);
  }
  
  public boolean isRelativeAttributeLabelReadable()
  {
    return isReadable(RELATIVEATTRIBUTELABEL);
  }
  
  public boolean isRelativeAttributeLabelModified()
  {
    return isModified(RELATIVEATTRIBUTELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getRelativeAttributeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(RELATIVEATTRIBUTELABEL).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{relation}", this.getRelation().toString());
    template = template.replace("{relativeAttributeLabel}", this.getRelativeAttributeLabel().toString());
    
    return template;
  }
  
}
