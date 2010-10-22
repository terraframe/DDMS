package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 662050192)
public abstract class DuplicateHierarchyParentExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateHierarchyParentException";
  private static final long serialVersionUID = 662050192;
  
  public DuplicateHierarchyParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected DuplicateHierarchyParentExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DuplicateHierarchyParentExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CHILDDISPLAYLABEL = "childDisplayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTDISPLAYLABEL = "parentDisplayLabel";
  public String getChildDisplayLabel()
  {
    return getValue(CHILDDISPLAYLABEL);
  }
  
  public void setChildDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(CHILDDISPLAYLABEL, "");
    }
    else
    {
      setValue(CHILDDISPLAYLABEL, value);
    }
  }
  
  public boolean isChildDisplayLabelWritable()
  {
    return isWritable(CHILDDISPLAYLABEL);
  }
  
  public boolean isChildDisplayLabelReadable()
  {
    return isReadable(CHILDDISPLAYLABEL);
  }
  
  public boolean isChildDisplayLabelModified()
  {
    return isModified(CHILDDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getChildDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CHILDDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getParentDisplayLabel()
  {
    return getValue(PARENTDISPLAYLABEL);
  }
  
  public void setParentDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(PARENTDISPLAYLABEL, "");
    }
    else
    {
      setValue(PARENTDISPLAYLABEL, value);
    }
  }
  
  public boolean isParentDisplayLabelWritable()
  {
    return isWritable(PARENTDISPLAYLABEL);
  }
  
  public boolean isParentDisplayLabelReadable()
  {
    return isReadable(PARENTDISPLAYLABEL);
  }
  
  public boolean isParentDisplayLabelModified()
  {
    return isModified(PARENTDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTDISPLAYLABEL).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{childDisplayLabel}", this.getChildDisplayLabel().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{parentDisplayLabel}", this.getParentDisplayLabel().toString());
    
    return template;
  }
  
}
