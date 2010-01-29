package dss.vector.solutions.synchronization;

@com.terraframe.mojo.business.ClassSignature(hash = 149228513)
public abstract class SynchronizedTypeViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.synchronization.SynchronizedTypeView";
  private static final long serialVersionUID = 149228513;
  
  protected SynchronizedTypeViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String EXPORTED = "exported";
  public static java.lang.String ID = "id";
  public static java.lang.String MDTYPEID = "mdTypeId";
  public static java.lang.String QUALIFIEDTYPE = "qualifiedType";
  public String getDescription()
  {
    return getValue(DESCRIPTION);
  }
  
  public void setDescription(String value)
  {
    if(value == null)
    {
      setValue(DESCRIPTION, "");
    }
    else
    {
      setValue(DESCRIPTION, value);
    }
  }
  
  public boolean isDescriptionWritable()
  {
    return isWritable(DESCRIPTION);
  }
  
  public boolean isDescriptionReadable()
  {
    return isReadable(DESCRIPTION);
  }
  
  public boolean isDescriptionModified()
  {
    return isModified(DESCRIPTION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getDescriptionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(DESCRIPTION).getAttributeMdDTO();
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
  
  public Boolean getExported()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(EXPORTED));
  }
  
  public void setExported(Boolean value)
  {
    if(value == null)
    {
      setValue(EXPORTED, "");
    }
    else
    {
      setValue(EXPORTED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isExportedWritable()
  {
    return isWritable(EXPORTED);
  }
  
  public boolean isExportedReadable()
  {
    return isReadable(EXPORTED);
  }
  
  public boolean isExportedModified()
  {
    return isModified(EXPORTED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getExportedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(EXPORTED).getAttributeMdDTO();
  }
  
  public String getMdTypeId()
  {
    return getValue(MDTYPEID);
  }
  
  public void setMdTypeId(String value)
  {
    if(value == null)
    {
      setValue(MDTYPEID, "");
    }
    else
    {
      setValue(MDTYPEID, value);
    }
  }
  
  public boolean isMdTypeIdWritable()
  {
    return isWritable(MDTYPEID);
  }
  
  public boolean isMdTypeIdReadable()
  {
    return isReadable(MDTYPEID);
  }
  
  public boolean isMdTypeIdModified()
  {
    return isModified(MDTYPEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMdTypeIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDTYPEID).getAttributeMdDTO();
  }
  
  public String getQualifiedType()
  {
    return getValue(QUALIFIEDTYPE);
  }
  
  public void setQualifiedType(String value)
  {
    if(value == null)
    {
      setValue(QUALIFIEDTYPE, "");
    }
    else
    {
      setValue(QUALIFIEDTYPE, value);
    }
  }
  
  public boolean isQualifiedTypeWritable()
  {
    return isWritable(QUALIFIEDTYPE);
  }
  
  public boolean isQualifiedTypeReadable()
  {
    return isReadable(QUALIFIEDTYPE);
  }
  
  public boolean isQualifiedTypeModified()
  {
    return isModified(QUALIFIEDTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getQualifiedTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUALIFIEDTYPE).getAttributeMdDTO();
  }
  
  public static final void confirmAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.synchronization.SynchronizedTypeView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "confirmAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[] getAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[] getDependencies(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String[] rootTypes)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{rootTypes};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "getDependencies", _declaredTypes);
    return (dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.synchronization.SynchronizedTypeViewQueryDTO getQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "getQuery", _declaredTypes);
    return (dss.vector.solutions.synchronization.SynchronizedTypeViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.synchronization.SynchronizedTypeViewQueryDTO search(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String query)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{query};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "search", _declaredTypes);
    return (dss.vector.solutions.synchronization.SynchronizedTypeViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SynchronizedTypeViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (SynchronizedTypeViewDTO) dto;
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
