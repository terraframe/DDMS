package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 1039258148)
public abstract class GeoEntityExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.GeoEntityExcelView";
  private static final long serialVersionUID = 1039258148;
  
  protected GeoEntityExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVATED = "activated";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String GEOTYPE = "geoType";
  public static java.lang.String GEOMETRYWKT = "geometryWKT";
  public static java.lang.String ID = "id";
  public static java.lang.String SUBTYPE = "subType";
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
  
  public String getGeoType()
  {
    return getValue(GEOTYPE);
  }
  
  public void setGeoType(String value)
  {
    if(value == null)
    {
      setValue(GEOTYPE, "");
    }
    else
    {
      setValue(GEOTYPE, value);
    }
  }
  
  public boolean isGeoTypeWritable()
  {
    return isWritable(GEOTYPE);
  }
  
  public boolean isGeoTypeReadable()
  {
    return isReadable(GEOTYPE);
  }
  
  public boolean isGeoTypeModified()
  {
    return isModified(GEOTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGeoTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOTYPE).getAttributeMdDTO();
  }
  
  public String getGeometryWKT()
  {
    return getValue(GEOMETRYWKT);
  }
  
  public void setGeometryWKT(String value)
  {
    if(value == null)
    {
      setValue(GEOMETRYWKT, "");
    }
    else
    {
      setValue(GEOMETRYWKT, value);
    }
  }
  
  public boolean isGeometryWKTWritable()
  {
    return isWritable(GEOMETRYWKT);
  }
  
  public boolean isGeometryWKTReadable()
  {
    return isReadable(GEOMETRYWKT);
  }
  
  public boolean isGeometryWKTModified()
  {
    return isModified(GEOMETRYWKT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getGeometryWKTMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(GEOMETRYWKT).getAttributeMdDTO();
  }
  
  public String getSubType()
  {
    return getValue(SUBTYPE);
  }
  
  public void setSubType(String value)
  {
    if(value == null)
    {
      setValue(SUBTYPE, "");
    }
    else
    {
      setValue(SUBTYPE, value);
    }
  }
  
  public boolean isSubTypeWritable()
  {
    return isWritable(SUBTYPE);
  }
  
  public boolean isSubTypeReadable()
  {
    return isReadable(SUBTYPE);
  }
  
  public boolean isSubTypeModified()
  {
    return isModified(SUBTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSubTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUBTYPE).getAttributeMdDTO();
  }
  
  public static GeoEntityExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (GeoEntityExcelViewDTO) dto;
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
