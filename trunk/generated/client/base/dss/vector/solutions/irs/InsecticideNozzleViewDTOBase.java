package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -89646458)
public abstract class InsecticideNozzleViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideNozzleView";
  private static final long serialVersionUID = -89646458;
  
  protected InsecticideNozzleViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BRAND = "brand";
  public static java.lang.String BRANDLABEL = "brandLabel";
  public static java.lang.String ENABLED = "enabled";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDENOZZLEID = "insecticideNozzleId";
  public static java.lang.String NOZZLE = "nozzle";
  public static java.lang.String NOZZLELABEL = "nozzleLabel";
  public dss.vector.solutions.irs.InsecticideBrandDTO getBrand()
  {
    if(getValue(BRAND) == null || getValue(BRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(BRAND));
    }
  }
  
  public void setBrand(dss.vector.solutions.irs.InsecticideBrandDTO value)
  {
    if(value == null)
    {
      setValue(BRAND, "");
    }
    else
    {
      setValue(BRAND, value.getId());
    }
  }
  
  public boolean isBrandWritable()
  {
    return isWritable(BRAND);
  }
  
  public boolean isBrandReadable()
  {
    return isReadable(BRAND);
  }
  
  public boolean isBrandModified()
  {
    return isModified(BRAND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getBrandMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BRAND).getAttributeMdDTO();
  }
  
  public String getBrandLabel()
  {
    return getValue(BRANDLABEL);
  }
  
  public void setBrandLabel(String value)
  {
    if(value == null)
    {
      setValue(BRANDLABEL, "");
    }
    else
    {
      setValue(BRANDLABEL, value);
    }
  }
  
  public boolean isBrandLabelWritable()
  {
    return isWritable(BRANDLABEL);
  }
  
  public boolean isBrandLabelReadable()
  {
    return isReadable(BRANDLABEL);
  }
  
  public boolean isBrandLabelModified()
  {
    return isModified(BRANDLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBrandLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BRANDLABEL).getAttributeMdDTO();
  }
  
  public Boolean getEnabled()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getEnabledMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLED).getAttributeMdDTO();
  }
  
  public String getInsecticideNozzleId()
  {
    return getValue(INSECTICIDENOZZLEID);
  }
  
  public void setInsecticideNozzleId(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDENOZZLEID, "");
    }
    else
    {
      setValue(INSECTICIDENOZZLEID, value);
    }
  }
  
  public boolean isInsecticideNozzleIdWritable()
  {
    return isWritable(INSECTICIDENOZZLEID);
  }
  
  public boolean isInsecticideNozzleIdReadable()
  {
    return isReadable(INSECTICIDENOZZLEID);
  }
  
  public boolean isInsecticideNozzleIdModified()
  {
    return isModified(INSECTICIDENOZZLEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getInsecticideNozzleIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICIDENOZZLEID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.NozzleDTO getNozzle()
  {
    if(getValue(NOZZLE) == null || getValue(NOZZLE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.NozzleDTO.get(getRequest(), getValue(NOZZLE));
    }
  }
  
  public void setNozzle(dss.vector.solutions.irs.NozzleDTO value)
  {
    if(value == null)
    {
      setValue(NOZZLE, "");
    }
    else
    {
      setValue(NOZZLE, value.getId());
    }
  }
  
  public boolean isNozzleWritable()
  {
    return isWritable(NOZZLE);
  }
  
  public boolean isNozzleReadable()
  {
    return isReadable(NOZZLE);
  }
  
  public boolean isNozzleModified()
  {
    return isModified(NOZZLE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getNozzleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NOZZLE).getAttributeMdDTO();
  }
  
  public String getNozzleLabel()
  {
    return getValue(NOZZLELABEL);
  }
  
  public void setNozzleLabel(String value)
  {
    if(value == null)
    {
      setValue(NOZZLELABEL, "");
    }
    else
    {
      setValue(NOZZLELABEL, value);
    }
  }
  
  public boolean isNozzleLabelWritable()
  {
    return isWritable(NOZZLELABEL);
  }
  
  public boolean isNozzleLabelReadable()
  {
    return isReadable(NOZZLELABEL);
  }
  
  public boolean isNozzleLabelModified()
  {
    return isModified(NOZZLELABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getNozzleLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NOZZLELABEL).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.InsecticideNozzleViewDTO[] applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.InsecticideNozzleViewDTO[] insecticideNozzles)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.InsecticideNozzleView;"};
    Object[] _parameters = new Object[]{insecticideNozzles};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideNozzleViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideNozzleViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideNozzleViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideNozzleViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideNozzleViewDTO[] getAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideNozzleViewDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideNozzleViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideNozzleViewDTO[] getAllActive(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideNozzleViewDTO.CLASS, "getAllActive", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideNozzleViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static InsecticideNozzleViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (InsecticideNozzleViewDTO) dto;
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
