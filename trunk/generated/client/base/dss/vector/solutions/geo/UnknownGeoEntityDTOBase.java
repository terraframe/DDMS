package dss.vector.solutions.geo;

@com.terraframe.mojo.business.ClassSignature(hash = -2132114337)
public abstract class UnknownGeoEntityDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.UnknownGeoEntity";
  private static final long serialVersionUID = -2132114337;
  
  protected UnknownGeoEntityDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String ENTITYTYPE = "entityType";
  public static java.lang.String ID = "id";
  public static java.lang.String KNOWNHIERARCHY = "knownHierarchy";
  public static java.lang.String SIBLINGS = "siblings";
  public static java.lang.String SYNONYMS = "synonyms";
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
  
  public String getKnownHierarchy()
  {
    return getValue(KNOWNHIERARCHY);
  }
  
  public void setKnownHierarchy(String value)
  {
    if(value == null)
    {
      setValue(KNOWNHIERARCHY, "");
    }
    else
    {
      setValue(KNOWNHIERARCHY, value);
    }
  }
  
  public boolean isKnownHierarchyWritable()
  {
    return isWritable(KNOWNHIERARCHY);
  }
  
  public boolean isKnownHierarchyReadable()
  {
    return isReadable(KNOWNHIERARCHY);
  }
  
  public boolean isKnownHierarchyModified()
  {
    return isModified(KNOWNHIERARCHY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getKnownHierarchyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(KNOWNHIERARCHY).getAttributeMdDTO();
  }
  
  public String getSiblings()
  {
    return getValue(SIBLINGS);
  }
  
  public void setSiblings(String value)
  {
    if(value == null)
    {
      setValue(SIBLINGS, "");
    }
    else
    {
      setValue(SIBLINGS, value);
    }
  }
  
  public boolean isSiblingsWritable()
  {
    return isWritable(SIBLINGS);
  }
  
  public boolean isSiblingsReadable()
  {
    return isReadable(SIBLINGS);
  }
  
  public boolean isSiblingsModified()
  {
    return isModified(SIBLINGS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getSiblingsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SIBLINGS).getAttributeMdDTO();
  }
  
  public String getSynonyms()
  {
    return getValue(SYNONYMS);
  }
  
  public void setSynonyms(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMS, "");
    }
    else
    {
      setValue(SYNONYMS, value);
    }
  }
  
  public boolean isSynonymsWritable()
  {
    return isWritable(SYNONYMS);
  }
  
  public boolean isSynonymsReadable()
  {
    return isReadable(SYNONYMS);
  }
  
  public boolean isSynonymsModified()
  {
    return isModified(SYNONYMS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getSynonymsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SYNONYMS).getAttributeMdDTO();
  }
  
  public static UnknownGeoEntityDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (UnknownGeoEntityDTO) dto;
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
