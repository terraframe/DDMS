package dss.vector.solutions.geo;

public abstract class GeoHierarchyViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoHierarchyView";
  protected GeoHierarchyViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String GEOHIERARCHYID = "geoHierarchyId";
  public static java.lang.String ID = "id";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String REFERENCEID = "referenceId";
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
  
  public String getGeoHierarchyId()
  {
    return getValue(GEOHIERARCHYID);
  }
  
  public void setGeoHierarchyId(String value)
  {
    if(value == null)
    {
      setValue(GEOHIERARCHYID, "");
    }
    else
    {
      setValue(GEOHIERARCHYID, value);
    }
  }
  
  public boolean isGeoHierarchyIdWritable()
  {
    return isWritable(GEOHIERARCHYID);
  }
  
  public boolean isGeoHierarchyIdReadable()
  {
    return isReadable(GEOHIERARCHYID);
  }
  
  public boolean isGeoHierarchyIdModified()
  {
    return isModified(GEOHIERARCHYID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGeoHierarchyIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("geoHierarchyId").getAttributeMdDTO();
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
  
  public String getReferenceId()
  {
    return getValue(REFERENCEID);
  }
  
  public void setReferenceId(String value)
  {
    if(value == null)
    {
      setValue(REFERENCEID, "");
    }
    else
    {
      setValue(REFERENCEID, value);
    }
  }
  
  public boolean isReferenceIdWritable()
  {
    return isWritable(REFERENCEID);
  }
  
  public boolean isReferenceIdReadable()
  {
    return isReadable(REFERENCEID);
  }
  
  public boolean isReferenceIdModified()
  {
    return isModified(REFERENCEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getReferenceIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("referenceId").getAttributeMdDTO();
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
  
  public static GeoHierarchyViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (GeoHierarchyViewDTO) dto;
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
