package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 833433750)
public abstract class GeoEntityViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoEntityView";
  private static final long serialVersionUID = 833433750;
  
  protected GeoEntityViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVATED = "activated";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String ENTITYTYPE = "entityType";
  public static java.lang.String GEOENTITYID = "geoEntityId";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  public static java.lang.String MOSUBTYPE = "moSubType";
  public static java.lang.String TYPEDISPLAYLABEL = "typeDisplayLabel";
  public Boolean getActivated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVATED));
  }
  
  public void setActivated(Boolean value)
  {
    if(value == null)
    {
      setValue(ACTIVATED, "");
    }
    else
    {
      setValue(ACTIVATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isActivatedWritable()
  {
    return isWritable(ACTIVATED);
  }
  
  public boolean isActivatedReadable()
  {
    return isReadable(ACTIVATED);
  }
  
  public boolean isActivatedModified()
  {
    return isModified(ACTIVATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getActivatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ACTIVATED).getAttributeMdDTO();
  }
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public boolean isEntityLabelWritable()
  {
    return isWritable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelReadable()
  {
    return isReadable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelModified()
  {
    return isModified(ENTITYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public String getEntityType()
  {
    return getValue(ENTITYTYPE);
  }
  
  public void setEntityType(String value)
  {
    if(value == null)
    {
      setValue(ENTITYTYPE, "");
    }
    else
    {
      setValue(ENTITYTYPE, value);
    }
  }
  
  public boolean isEntityTypeWritable()
  {
    return isWritable(ENTITYTYPE);
  }
  
  public boolean isEntityTypeReadable()
  {
    return isReadable(ENTITYTYPE);
  }
  
  public boolean isEntityTypeModified()
  {
    return isModified(ENTITYTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYTYPE).getAttributeMdDTO();
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITYID);
  }
  
  public void setGeoEntityId(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITYID, "");
    }
    else
    {
      setValue(GEOENTITYID, value);
    }
  }
  
  public boolean isGeoEntityIdWritable()
  {
    return isWritable(GEOENTITYID);
  }
  
  public boolean isGeoEntityIdReadable()
  {
    return isReadable(GEOENTITYID);
  }
  
  public boolean isGeoEntityIdModified()
  {
    return isModified(GEOENTITYID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoEntityIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITYID).getAttributeMdDTO();
  }
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
    }
  }
  
  public boolean isGeoIdWritable()
  {
    return isWritable(GEOID);
  }
  
  public boolean isGeoIdReadable()
  {
    return isReadable(GEOID);
  }
  
  public boolean isGeoIdModified()
  {
    return isModified(GEOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOID).getAttributeMdDTO();
  }
  
  public String getMoSubType()
  {
    return getValue(MOSUBTYPE);
  }
  
  public void setMoSubType(String value)
  {
    if(value == null)
    {
      setValue(MOSUBTYPE, "");
    }
    else
    {
      setValue(MOSUBTYPE, value);
    }
  }
  
  public boolean isMoSubTypeWritable()
  {
    return isWritable(MOSUBTYPE);
  }
  
  public boolean isMoSubTypeReadable()
  {
    return isReadable(MOSUBTYPE);
  }
  
  public boolean isMoSubTypeModified()
  {
    return isModified(MOSUBTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMoSubTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MOSUBTYPE).getAttributeMdDTO();
  }
  
  public String getTypeDisplayLabel()
  {
    return getValue(TYPEDISPLAYLABEL);
  }
  
  public void setTypeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(TYPEDISPLAYLABEL, "");
    }
    else
    {
      setValue(TYPEDISPLAYLABEL, value);
    }
  }
  
  public boolean isTypeDisplayLabelWritable()
  {
    return isWritable(TYPEDISPLAYLABEL);
  }
  
  public boolean isTypeDisplayLabelReadable()
  {
    return isReadable(TYPEDISPLAYLABEL);
  }
  
  public boolean isTypeDisplayLabelModified()
  {
    return isModified(TYPEDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTypeDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TYPEDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public static GeoEntityViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (GeoEntityViewDTO) dto;
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
