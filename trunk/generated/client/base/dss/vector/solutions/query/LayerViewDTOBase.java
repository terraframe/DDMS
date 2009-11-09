package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -196746908)
public abstract class LayerViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.LayerView";
  private static final long serialVersionUID = -196746908;
  
  protected LayerViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String ISTHEMATIC = "isThematic";
  public static java.lang.String LAYERID = "layerId";
  public static java.lang.String THEMATICTYPE = "thematicType";
  public static java.lang.String UNIVERSALTYPE = "universalType";
  public Boolean getIsThematic()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISTHEMATIC));
  }
  
  public void setIsThematic(Boolean value)
  {
    if(value == null)
    {
      setValue(ISTHEMATIC, "");
    }
    else
    {
      setValue(ISTHEMATIC, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsThematicWritable()
  {
    return isWritable(ISTHEMATIC);
  }
  
  public boolean isIsThematicReadable()
  {
    return isReadable(ISTHEMATIC);
  }
  
  public boolean isIsThematicModified()
  {
    return isModified(ISTHEMATIC);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsThematicMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISTHEMATIC).getAttributeMdDTO();
  }
  
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
  
  public String getThematicType()
  {
    return getValue(THEMATICTYPE);
  }
  
  public void setThematicType(String value)
  {
    if(value == null)
    {
      setValue(THEMATICTYPE, "");
    }
    else
    {
      setValue(THEMATICTYPE, value);
    }
  }
  
  public boolean isThematicTypeWritable()
  {
    return isWritable(THEMATICTYPE);
  }
  
  public boolean isThematicTypeReadable()
  {
    return isReadable(THEMATICTYPE);
  }
  
  public boolean isThematicTypeModified()
  {
    return isModified(THEMATICTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getThematicTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(THEMATICTYPE).getAttributeMdDTO();
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
