package dss.vector.solutions.geo;

public abstract class GeoEntityViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoEntityView";
  protected GeoEntityViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVATED = "activated";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String ENTITYTYPE = "entityType";
  public static java.lang.String GEOENTITYID = "geoEntityId";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  public Boolean getActivated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVATED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getActivatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ACTIVATED).getAttributeMdDTO();
  }
  
  public String getEntityName()
  {
    return getValue(ENTITYNAME);
  }
  
  public void setEntityName(String value)
  {
    if(value == null)
    {
      setValue(ENTITYNAME, "");
    }
    else
    {
      setValue(ENTITYNAME, value);
    }
  }
  
  public boolean isEntityNameWritable()
  {
    return isWritable(ENTITYNAME);
  }
  
  public boolean isEntityNameReadable()
  {
    return isReadable(ENTITYNAME);
  }
  
  public boolean isEntityNameModified()
  {
    return isModified(ENTITYNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getEntityNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getEntityTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYTYPE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGeoEntityIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITYID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGeoIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOID).getAttributeMdDTO();
  }
  
  public static GeoEntityViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
