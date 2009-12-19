package dss.vector.solutions.util;

@com.terraframe.mojo.business.ClassSignature(hash = -1180848254)
public abstract class ReadableAttributeViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.util.ReadableAttributeView";
  private static final long serialVersionUID = -1180848254;
  
  protected ReadableAttributeViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String ID = "id";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAttributeDescriptionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRIBUTEDESCRIPTION).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAttributeNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ATTRIBUTENAME).getAttributeMdDTO();
  }
  
  public Boolean getAttributeRequired()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ATTRIBUTEREQUIRED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getAttributeRequiredMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ATTRIBUTEREQUIRED).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDisplayLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISPLAYLABEL).getAttributeMdDTO();
  }
  
  public Boolean getReadPermission()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(READPERMISSION));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getReadPermissionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(READPERMISSION).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.util.ReadableAttributeViewDTO[] getActorAttributes(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String universal, java.lang.String actorName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{universal, actorName};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.util.ReadableAttributeViewDTO.CLASS, "getActorAttributes", _declaredTypes);
    return (dss.vector.solutions.util.ReadableAttributeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void setActorAttributes(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String universal, java.lang.String actorName, dss.vector.solutions.util.ReadableAttributeViewDTO[] attributeViews)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "[Ldss.vector.solutions.util.ReadableAttributeView;"};
    Object[] _parameters = new Object[]{universal, actorName, attributeViews};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.util.ReadableAttributeViewDTO.CLASS, "setActorAttributes", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ReadableAttributeViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
