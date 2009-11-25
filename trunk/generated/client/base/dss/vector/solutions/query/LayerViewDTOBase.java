package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 601756128)
public abstract class LayerViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.LayerView";
  private static final long serialVersionUID = 601756128;
  
  protected LayerViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LAYERID = "layerId";
  public static java.lang.String LAYERNAME = "layerName";
  public static java.lang.String LAYERPOSITION = "layerPosition";
  public static java.lang.String UNIVERSALTYPE = "universalType";
  public String getLayerId()
  {
    return getValue(LAYERID);
  }
  
  public void setLayerId(String value)
  {
    if(value == null)
    {
      setValue(LAYERID, "");
    }
    else
    {
      setValue(LAYERID, value);
    }
  }
  
  public boolean isLayerIdWritable()
  {
    return isWritable(LAYERID);
  }
  
  public boolean isLayerIdReadable()
  {
    return isReadable(LAYERID);
  }
  
  public boolean isLayerIdModified()
  {
    return isModified(LAYERID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLayerIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LAYERID).getAttributeMdDTO();
  }
  
  public String getLayerName()
  {
    return getValue(LAYERNAME);
  }
  
  public void setLayerName(String value)
  {
    if(value == null)
    {
      setValue(LAYERNAME, "");
    }
    else
    {
      setValue(LAYERNAME, value);
    }
  }
  
  public boolean isLayerNameWritable()
  {
    return isWritable(LAYERNAME);
  }
  
  public boolean isLayerNameReadable()
  {
    return isReadable(LAYERNAME);
  }
  
  public boolean isLayerNameModified()
  {
    return isModified(LAYERNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLayerNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LAYERNAME).getAttributeMdDTO();
  }
  
  public Integer getLayerPosition()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LAYERPOSITION));
  }
  
  public void setLayerPosition(Integer value)
  {
    if(value == null)
    {
      setValue(LAYERPOSITION, "");
    }
    else
    {
      setValue(LAYERPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLayerPositionWritable()
  {
    return isWritable(LAYERPOSITION);
  }
  
  public boolean isLayerPositionReadable()
  {
    return isReadable(LAYERPOSITION);
  }
  
  public boolean isLayerPositionModified()
  {
    return isModified(LAYERPOSITION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getLayerPositionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LAYERPOSITION).getAttributeMdDTO();
  }
  
  public String getUniversalType()
  {
    return getValue(UNIVERSALTYPE);
  }
  
  public void setUniversalType(String value)
  {
    if(value == null)
    {
      setValue(UNIVERSALTYPE, "");
    }
    else
    {
      setValue(UNIVERSALTYPE, value);
    }
  }
  
  public boolean isUniversalTypeWritable()
  {
    return isWritable(UNIVERSALTYPE);
  }
  
  public boolean isUniversalTypeReadable()
  {
    return isReadable(UNIVERSALTYPE);
  }
  
  public boolean isUniversalTypeModified()
  {
    return isModified(UNIVERSALTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getUniversalTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNIVERSALTYPE).getAttributeMdDTO();
  }
  
  public static LayerViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (LayerViewDTO) dto;
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
