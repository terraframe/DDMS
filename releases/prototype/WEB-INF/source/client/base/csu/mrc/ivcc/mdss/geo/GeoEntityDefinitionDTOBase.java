package csu.mrc.ivcc.mdss.geo;

public abstract class GeoEntityDefinitionDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.geo.GeoEntityDefinition";
  protected GeoEntityDefinitionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTTYPEID = "parentTypeId";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String SPATIALTYPE = "spatialType";
  public static java.lang.String TYPENAME = "typeName";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDescriptionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("description").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("displayLabel").getAttributeMdDTO();
  }
  
  public String getParentTypeId()
  {
    return getValue(PARENTTYPEID);
  }
  
  public void setParentTypeId(String value)
  {
    if(value == null)
    {
      setValue(PARENTTYPEID, "");
    }
    else
    {
      setValue(PARENTTYPEID, value);
    }
  }
  
  public boolean isParentTypeIdWritable()
  {
    return isWritable(PARENTTYPEID);
  }
  
  public boolean isParentTypeIdReadable()
  {
    return isReadable(PARENTTYPEID);
  }
  
  public boolean isParentTypeIdModified()
  {
    return isModified(PARENTTYPEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getParentTypeIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("parentTypeId").getAttributeMdDTO();
  }
  
  public Boolean getPolitical()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POLITICAL));
  }
  
  public void setPolitical(Boolean value)
  {
    if(value == null)
    {
      setValue(POLITICAL, "");
    }
    else
    {
      setValue(POLITICAL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPoliticalWritable()
  {
    return isWritable(POLITICAL);
  }
  
  public boolean isPoliticalReadable()
  {
    return isReadable(POLITICAL);
  }
  
  public boolean isPoliticalModified()
  {
    return isModified(POLITICAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPoliticalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("political").getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<csu.mrc.ivcc.mdss.geo.SpatialTypesDTO> getSpatialType()
  {
    return (java.util.List<csu.mrc.ivcc.mdss.geo.SpatialTypesDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "csu.mrc.ivcc.mdss.geo.SpatialTypes", getEnumNames(SPATIALTYPE));
  }
  
  public java.util.List<String> getSpatialTypeEnumNames()
  {
    return getEnumNames(SPATIALTYPE);
  }
  
  public void addSpatialType(csu.mrc.ivcc.mdss.geo.SpatialTypesDTO enumDTO)
  {
    addEnumItem(SPATIALTYPE, enumDTO.toString());
  }
  
  public void removeSpatialType(csu.mrc.ivcc.mdss.geo.SpatialTypesDTO enumDTO)
  {
    removeEnumItem(SPATIALTYPE, enumDTO.toString());
  }
  
  public void clearSpatialType()
  {
    clearEnum(SPATIALTYPE);
  }
  
  public boolean isSpatialTypeWritable()
  {
    return isWritable(SPATIALTYPE);
  }
  
  public boolean isSpatialTypeReadable()
  {
    return isReadable(SPATIALTYPE);
  }
  
  public boolean isSpatialTypeModified()
  {
    return isModified(SPATIALTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getSpatialTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO("spatialType").getAttributeMdDTO();
  }
  
  public String getTypeName()
  {
    return getValue(TYPENAME);
  }
  
  public void setTypeName(String value)
  {
    if(value == null)
    {
      setValue(TYPENAME, "");
    }
    else
    {
      setValue(TYPENAME, value);
    }
  }
  
  public boolean isTypeNameWritable()
  {
    return isWritable(TYPENAME);
  }
  
  public boolean isTypeNameReadable()
  {
    return isReadable(TYPENAME);
  }
  
  public boolean isTypeNameModified()
  {
    return isModified(TYPENAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTypeNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("typeName").getAttributeMdDTO();
  }
  
  public static GeoEntityDefinitionDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (GeoEntityDefinitionDTO) dto;
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
