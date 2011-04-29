package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -763938021)
public abstract class GeoEntityExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.GeoEntityExcelView";
  private static final long serialVersionUID = -763938021;
  
  protected GeoEntityExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String PARENTNAME = "parentName";
  public static java.lang.String PARENTTYPE = "parentType";
  public static java.lang.String SUBTYPE = "subType";
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYNAME).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOTYPE).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getGeometryWKTMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(GEOMETRYWKT).getAttributeMdDTO();
  }
  
  public String getParentName()
  {
    return getValue(PARENTNAME);
  }
  
  public void setParentName(String value)
  {
    if(value == null)
    {
      setValue(PARENTNAME, "");
    }
    else
    {
      setValue(PARENTNAME, value);
    }
  }
  
  public boolean isParentNameWritable()
  {
    return isWritable(PARENTNAME);
  }
  
  public boolean isParentNameReadable()
  {
    return isReadable(PARENTNAME);
  }
  
  public boolean isParentNameModified()
  {
    return isModified(PARENTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTNAME).getAttributeMdDTO();
  }
  
  public String getParentType()
  {
    return getValue(PARENTTYPE);
  }
  
  public void setParentType(String value)
  {
    if(value == null)
    {
      setValue(PARENTTYPE, "");
    }
    else
    {
      setValue(PARENTTYPE, value);
    }
  }
  
  public boolean isParentTypeWritable()
  {
    return isWritable(PARENTTYPE);
  }
  
  public boolean isParentTypeReadable()
  {
    return isReadable(PARENTTYPE);
  }
  
  public boolean isParentTypeModified()
  {
    return isModified(PARENTTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getParentTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTTYPE).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSubTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUBTYPE).getAttributeMdDTO();
  }
  
  public static GeoEntityExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
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
