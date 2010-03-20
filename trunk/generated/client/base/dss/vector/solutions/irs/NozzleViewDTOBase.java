package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -86323865)
public abstract class NozzleViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.NozzleView";
  private static final long serialVersionUID = -86323865;
  
  protected NozzleViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ENABLED = "enabled";
  public static java.lang.String ID = "id";
  public static java.lang.String NOZZLEID = "nozzleId";
  public static java.lang.String RATIO = "ratio";
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
  
  public Boolean getEnabled()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLED));
  }
  
  public void setEnabled(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLED, "");
    }
    else
    {
      setValue(ENABLED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnabledWritable()
  {
    return isWritable(ENABLED);
  }
  
  public boolean isEnabledReadable()
  {
    return isReadable(ENABLED);
  }
  
  public boolean isEnabledModified()
  {
    return isModified(ENABLED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnabledMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLED).getAttributeMdDTO();
  }
  
  public String getNozzleId()
  {
    return getValue(NOZZLEID);
  }
  
  public void setNozzleId(String value)
  {
    if(value == null)
    {
      setValue(NOZZLEID, "");
    }
    else
    {
      setValue(NOZZLEID, value);
    }
  }
  
  public boolean isNozzleIdWritable()
  {
    return isWritable(NOZZLEID);
  }
  
  public boolean isNozzleIdReadable()
  {
    return isReadable(NOZZLEID);
  }
  
  public boolean isNozzleIdModified()
  {
    return isModified(NOZZLEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNozzleIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NOZZLEID).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getRatio()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(RATIO));
  }
  
  public void setRatio(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(RATIO, "");
    }
    else
    {
      setValue(RATIO, value.toString());
    }
  }
  
  public boolean isRatioWritable()
  {
    return isWritable(RATIO);
  }
  
  public boolean isRatioReadable()
  {
    return isReadable(RATIO);
  }
  
  public boolean isRatioModified()
  {
    return isModified(RATIO);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getRatioMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(RATIO).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.NozzleViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.NozzleViewDTO[] nozzles)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.NozzleView;"};
    Object[] _parameters = new Object[]{nozzles};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.NozzleViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.irs.NozzleViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.NozzleViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.NozzleViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.NozzleViewDTO[] getAll(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.NozzleViewDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.irs.NozzleViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.NozzleViewDTO[] getAllActive(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.NozzleViewDTO.CLASS, "getAllActive", _declaredTypes);
    return (dss.vector.solutions.irs.NozzleViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static NozzleViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (NozzleViewDTO) dto;
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
