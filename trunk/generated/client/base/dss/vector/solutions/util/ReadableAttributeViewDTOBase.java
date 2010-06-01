package dss.vector.solutions.util;

@com.runwaysdk.business.ClassSignature(hash = 2003183571)
public abstract class ReadableAttributeViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.util.ReadableAttributeView";
  private static final long serialVersionUID = 2003183571;
  
  protected ReadableAttributeViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRIBUTEDESCRIPTION = "attributeDescription";
  public static java.lang.String ATTRIBUTENAME = "attributeName";
  public static java.lang.String ATTRIBUTEREQUIRED = "attributeRequired";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String FIELDID = "fieldId";
  public static java.lang.String ID = "id";
  public static java.lang.String NOTBLANK = "notBlank";
  public static java.lang.String READPERMISSION = "readPermission";
  public String getAttributeDescription()
  {
    return getValue(ATTRIBUTEDESCRIPTION);
  }
  
  public void setAttributeDescription(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEDESCRIPTION, "");
    }
    else
    {
      setValue(ATTRIBUTEDESCRIPTION, value);
    }
  }
  
  public boolean isAttributeDescriptionWritable()
  {
    return isWritable(ATTRIBUTEDESCRIPTION);
  }
  
  public boolean isAttributeDescriptionReadable()
  {
    return isReadable(ATTRIBUTEDESCRIPTION);
  }
  
  public boolean isAttributeDescriptionModified()
  {
    return isModified(ATTRIBUTEDESCRIPTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAttributeDescriptionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRIBUTEDESCRIPTION).getAttributeMdDTO();
  }
  
  public String getAttributeName()
  {
    return getValue(ATTRIBUTENAME);
  }
  
  public void setAttributeName(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTENAME, "");
    }
    else
    {
      setValue(ATTRIBUTENAME, value);
    }
  }
  
  public boolean isAttributeNameWritable()
  {
    return isWritable(ATTRIBUTENAME);
  }
  
  public boolean isAttributeNameReadable()
  {
    return isReadable(ATTRIBUTENAME);
  }
  
  public boolean isAttributeNameModified()
  {
    return isModified(ATTRIBUTENAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAttributeNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRIBUTENAME).getAttributeMdDTO();
  }
  
  public Boolean getAttributeRequired()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ATTRIBUTEREQUIRED));
  }
  
  public void setAttributeRequired(Boolean value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEREQUIRED, "");
    }
    else
    {
      setValue(ATTRIBUTEREQUIRED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isAttributeRequiredWritable()
  {
    return isWritable(ATTRIBUTEREQUIRED);
  }
  
  public boolean isAttributeRequiredReadable()
  {
    return isReadable(ATTRIBUTEREQUIRED);
  }
  
  public boolean isAttributeRequiredModified()
  {
    return isModified(ATTRIBUTEREQUIRED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getAttributeRequiredMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ATTRIBUTEREQUIRED).getAttributeMdDTO();
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
    }
  }
  
  public boolean isDisplayLabelWritable()
  {
    return isWritable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelReadable()
  {
    return isReadable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelModified()
  {
    return isModified(DISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getFieldId()
  {
    return getValue(FIELDID);
  }
  
  public void setFieldId(String value)
  {
    if(value == null)
    {
      setValue(FIELDID, "");
    }
    else
    {
      setValue(FIELDID, value);
    }
  }
  
  public boolean isFieldIdWritable()
  {
    return isWritable(FIELDID);
  }
  
  public boolean isFieldIdReadable()
  {
    return isReadable(FIELDID);
  }
  
  public boolean isFieldIdModified()
  {
    return isModified(FIELDID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFieldIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIELDID).getAttributeMdDTO();
  }
  
  public Boolean getNotBlank()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NOTBLANK));
  }
  
  public void setNotBlank(Boolean value)
  {
    if(value == null)
    {
      setValue(NOTBLANK, "");
    }
    else
    {
      setValue(NOTBLANK, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNotBlankWritable()
  {
    return isWritable(NOTBLANK);
  }
  
  public boolean isNotBlankReadable()
  {
    return isReadable(NOTBLANK);
  }
  
  public boolean isNotBlankModified()
  {
    return isModified(NOTBLANK);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getNotBlankMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NOTBLANK).getAttributeMdDTO();
  }
  
  public Boolean getReadPermission()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(READPERMISSION));
  }
  
  public void setReadPermission(Boolean value)
  {
    if(value == null)
    {
      setValue(READPERMISSION, "");
    }
    else
    {
      setValue(READPERMISSION, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isReadPermissionWritable()
  {
    return isWritable(READPERMISSION);
  }
  
  public boolean isReadPermissionReadable()
  {
    return isReadable(READPERMISSION);
  }
  
  public boolean isReadPermissionModified()
  {
    return isModified(READPERMISSION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getReadPermissionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(READPERMISSION).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.util.ReadableAttributeViewDTO[] getActorAttributes(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String universal, java.lang.String actorName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{universal, actorName};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.util.ReadableAttributeViewDTO.CLASS, "getActorAttributes", _declaredTypes);
    return (dss.vector.solutions.util.ReadableAttributeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.util.ReadableAttributeViewDTO[] getReadableAttributes(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String qualifiedClass)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{qualifiedClass};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.util.ReadableAttributeViewDTO.CLASS, "getReadableAttributes", _declaredTypes);
    return (dss.vector.solutions.util.ReadableAttributeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void setActorAttributes(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String universal, java.lang.String actorName, dss.vector.solutions.util.ReadableAttributeViewDTO[] attributeViews)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "[Ldss.vector.solutions.util.ReadableAttributeView;"};
    Object[] _parameters = new Object[]{universal, actorName, attributeViews};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.util.ReadableAttributeViewDTO.CLASS, "setActorAttributes", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ReadableAttributeViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ReadableAttributeViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
