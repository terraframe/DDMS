package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 2030225925)
public abstract class SprayedSumProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayedSumProblem";
  private static final long serialVersionUID = 2030225925;
  
  public SprayedSumProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public SprayedSumProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String OBJECTLABEL = "objectLabel";
  public static java.lang.String OBJECTS = "objects";
  public static java.lang.String SPRAYEDOBJECTLABEL = "sprayedObjectLabel";
  public static java.lang.String SPRAYEDOBJECTS = "sprayedObjects";
  public String getObjectLabel()
  {
    return getValue(OBJECTLABEL);
  }
  
  public void setObjectLabel(String value)
  {
    if(value == null)
    {
      setValue(OBJECTLABEL, "");
    }
    else
    {
      setValue(OBJECTLABEL, value);
    }
  }
  
  public boolean isObjectLabelWritable()
  {
    return isWritable(OBJECTLABEL);
  }
  
  public boolean isObjectLabelReadable()
  {
    return isReadable(OBJECTLABEL);
  }
  
  public boolean isObjectLabelModified()
  {
    return isModified(OBJECTLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getObjectLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OBJECTLABEL).getAttributeMdDTO();
  }
  
  public Integer getObjects()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OBJECTS));
  }
  
  public void setObjects(Integer value)
  {
    if(value == null)
    {
      setValue(OBJECTS, "");
    }
    else
    {
      setValue(OBJECTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isObjectsWritable()
  {
    return isWritable(OBJECTS);
  }
  
  public boolean isObjectsReadable()
  {
    return isReadable(OBJECTS);
  }
  
  public boolean isObjectsModified()
  {
    return isModified(OBJECTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getObjectsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OBJECTS).getAttributeMdDTO();
  }
  
  public String getSprayedObjectLabel()
  {
    return getValue(SPRAYEDOBJECTLABEL);
  }
  
  public void setSprayedObjectLabel(String value)
  {
    if(value == null)
    {
      setValue(SPRAYEDOBJECTLABEL, "");
    }
    else
    {
      setValue(SPRAYEDOBJECTLABEL, value);
    }
  }
  
  public boolean isSprayedObjectLabelWritable()
  {
    return isWritable(SPRAYEDOBJECTLABEL);
  }
  
  public boolean isSprayedObjectLabelReadable()
  {
    return isReadable(SPRAYEDOBJECTLABEL);
  }
  
  public boolean isSprayedObjectLabelModified()
  {
    return isModified(SPRAYEDOBJECTLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSprayedObjectLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYEDOBJECTLABEL).getAttributeMdDTO();
  }
  
  public Integer getSprayedObjects()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDOBJECTS));
  }
  
  public void setSprayedObjects(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDOBJECTS, "");
    }
    else
    {
      setValue(SPRAYEDOBJECTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedObjectsWritable()
  {
    return isWritable(SPRAYEDOBJECTS);
  }
  
  public boolean isSprayedObjectsReadable()
  {
    return isReadable(SPRAYEDOBJECTS);
  }
  
  public boolean isSprayedObjectsModified()
  {
    return isModified(SPRAYEDOBJECTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedObjectsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDOBJECTS).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{objectLabel}", this.getObjectLabel().toString());
    template = template.replace("{objects}", this.getObjects().toString());
    template = template.replace("{sprayedObjectLabel}", this.getSprayedObjectLabel().toString());
    template = template.replace("{sprayedObjects}", this.getSprayedObjects().toString());
    
    return template;
  }
  
}
