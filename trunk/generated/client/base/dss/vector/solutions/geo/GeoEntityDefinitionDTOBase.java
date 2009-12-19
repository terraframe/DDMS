package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = -1785837340)
public abstract class GeoEntityDefinitionDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoEntityDefinition";
  private static final long serialVersionUID = -1785837340;
  
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
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String POPULATIONALLOWED = "populationAllowed";
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
