package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = -292181958)
public abstract class GeoEntityDefinitionDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoEntityDefinition";
  private static final long serialVersionUID = -292181958;
  
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
  public static java.lang.String PARENTGEOHIERARCHYID = "parentGeoHierarchyId";
  public static java.lang.String PARENTTYPEGEOHIERARCHYID = "parentTypeGeoHierarchyId";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String POPULATIONALLOWED = "populationAllowed";
  public static java.lang.String SPATIALTYPE = "spatialType";
  public static java.lang.String SPRAYTARGETALLOWED = "sprayTargetAllowed";
  public static java.lang.String TERM = "term";
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
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DESCRIPTION).getAttributeMdDTO();
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
  
  public String getParentGeoHierarchyId()
  {
    return getValue(PARENTGEOHIERARCHYID);
  }
  
  public void setParentGeoHierarchyId(String value)
  {
    if(value == null)
    {
      setValue(PARENTGEOHIERARCHYID, "");
    }
    else
    {
      setValue(PARENTGEOHIERARCHYID, value);
    }
  }
  
  public boolean isParentGeoHierarchyIdWritable()
  {
    return isWritable(PARENTGEOHIERARCHYID);
  }
  
  public boolean isParentGeoHierarchyIdReadable()
  {
    return isReadable(PARENTGEOHIERARCHYID);
  }
  
  public boolean isParentGeoHierarchyIdModified()
  {
    return isModified(PARENTGEOHIERARCHYID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getParentGeoHierarchyIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTGEOHIERARCHYID).getAttributeMdDTO();
  }
  
  public String getParentTypeGeoHierarchyId()
  {
    return getValue(PARENTTYPEGEOHIERARCHYID);
  }
  
  public void setParentTypeGeoHierarchyId(String value)
  {
    if(value == null)
    {
      setValue(PARENTTYPEGEOHIERARCHYID, "");
    }
    else
    {
      setValue(PARENTTYPEGEOHIERARCHYID, value);
    }
  }
  
  public boolean isParentTypeGeoHierarchyIdWritable()
  {
    return isWritable(PARENTTYPEGEOHIERARCHYID);
  }
  
  public boolean isParentTypeGeoHierarchyIdReadable()
  {
    return isReadable(PARENTTYPEGEOHIERARCHYID);
  }
  
  public boolean isParentTypeGeoHierarchyIdModified()
  {
    return isModified(PARENTTYPEGEOHIERARCHYID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getParentTypeGeoHierarchyIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARENTTYPEGEOHIERARCHYID).getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(POLITICAL).getAttributeMdDTO();
  }
  
  public Boolean getPopulationAllowed()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POPULATIONALLOWED));
  }
  
  public void setPopulationAllowed(Boolean value)
  {
    if(value == null)
    {
      setValue(POPULATIONALLOWED, "");
    }
    else
    {
      setValue(POPULATIONALLOWED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPopulationAllowedWritable()
  {
    return isWritable(POPULATIONALLOWED);
  }
  
  public boolean isPopulationAllowedReadable()
  {
    return isReadable(POPULATIONALLOWED);
  }
  
  public boolean isPopulationAllowedModified()
  {
    return isModified(POPULATIONALLOWED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPopulationAllowedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(POPULATIONALLOWED).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.geo.SpatialTypesDTO> getSpatialType()
  {
    return (java.util.List<dss.vector.solutions.geo.SpatialTypesDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.geo.SpatialTypes", getEnumNames(SPATIALTYPE));
  }
  
  public java.util.List<String> getSpatialTypeEnumNames()
  {
    return getEnumNames(SPATIALTYPE);
  }
  
  public void addSpatialType(dss.vector.solutions.geo.SpatialTypesDTO enumDTO)
  {
    addEnumItem(SPATIALTYPE, enumDTO.toString());
  }
  
  public void removeSpatialType(dss.vector.solutions.geo.SpatialTypesDTO enumDTO)
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
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(SPATIALTYPE).getAttributeMdDTO();
  }
  
  public Boolean getSprayTargetAllowed()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SPRAYTARGETALLOWED));
  }
  
  public void setSprayTargetAllowed(Boolean value)
  {
    if(value == null)
    {
      setValue(SPRAYTARGETALLOWED, "");
    }
    else
    {
      setValue(SPRAYTARGETALLOWED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSprayTargetAllowedWritable()
  {
    return isWritable(SPRAYTARGETALLOWED);
  }
  
  public boolean isSprayTargetAllowedReadable()
  {
    return isReadable(SPRAYTARGETALLOWED);
  }
  
  public boolean isSprayTargetAllowedModified()
  {
    return isModified(SPRAYTARGETALLOWED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getSprayTargetAllowedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SPRAYTARGETALLOWED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTerm()
  {
    if(getValue(TERM) == null || getValue(TERM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TERM));
    }
  }
  
  public void setTerm(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value.getId());
    }
  }
  
  public boolean isTermWritable()
  {
    return isWritable(TERM);
  }
  
  public boolean isTermReadable()
  {
    return isReadable(TERM);
  }
  
  public boolean isTermModified()
  {
    return isModified(TERM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTermMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TERM).getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TYPENAME).getAttributeMdDTO();
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
