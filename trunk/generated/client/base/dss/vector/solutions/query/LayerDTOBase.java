package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1425035663)
public abstract class LayerDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.Layer";
  private static final long serialVersionUID = 1425035663;
  
  protected LayerDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LayerDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DEFAULTSTYLES = "defaultStyles";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOHIERARCHY = "geoHierarchy";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LAYERNAME = "layerName";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SAVEDSEARCH = "savedSearch";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SLDFILE = "sldFile";
  public static java.lang.String THEMATICVARIABLE = "thematicVariable";
  public static java.lang.String TYPE = "type";
  public static java.lang.String VIEWCREATED = "viewCreated";
  public static java.lang.String VIEWNAME = "viewName";
  public java.util.Date getCreateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public boolean isCreateDateWritable()
  {
    return isWritable(CREATEDATE);
  }
  
  public boolean isCreateDateReadable()
  {
    return isReadable(CREATEDATE);
  }
  
  public boolean isCreateDateModified()
  {
    return isModified(CREATEDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
  }
  
  public boolean isCreatedByWritable()
  {
    return isWritable(CREATEDBY);
  }
  
  public boolean isCreatedByReadable()
  {
    return isReadable(CREATEDBY);
  }
  
  public boolean isCreatedByModified()
  {
    return isModified(CREATEDBY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.query.StylesDTO getDefaultStyles()
  {
    if(getValue(DEFAULTSTYLES) == null || getValue(DEFAULTSTYLES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.StylesDTO.get(getRequest(), getValue(DEFAULTSTYLES));
    }
  }
  
  public void setDefaultStyles(dss.vector.solutions.query.StylesDTO value)
  {
    if(value == null)
    {
      setValue(DEFAULTSTYLES, "");
    }
    else
    {
      setValue(DEFAULTSTYLES, value.getId());
    }
  }
  
  public boolean isDefaultStylesWritable()
  {
    return isWritable(DEFAULTSTYLES);
  }
  
  public boolean isDefaultStylesReadable()
  {
    return isReadable(DEFAULTSTYLES);
  }
  
  public boolean isDefaultStylesModified()
  {
    return isModified(DEFAULTSTYLES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDefaultStylesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DEFAULTSTYLES).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public void setEntityDomain(com.terraframe.mojo.system.metadata.MdDomainDTO value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
    }
  }
  
  public boolean isEntityDomainWritable()
  {
    return isWritable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainReadable()
  {
    return isReadable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainModified()
  {
    return isModified(ENTITYDOMAIN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.GeoHierarchyDTO getGeoHierarchy()
  {
    if(getValue(GEOHIERARCHY) == null || getValue(GEOHIERARCHY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchyDTO.get(getRequest(), getValue(GEOHIERARCHY));
    }
  }
  
  public void setGeoHierarchy(dss.vector.solutions.geo.GeoHierarchyDTO value)
  {
    if(value == null)
    {
      setValue(GEOHIERARCHY, "");
    }
    else
    {
      setValue(GEOHIERARCHY, value.getId());
    }
  }
  
  public boolean isGeoHierarchyWritable()
  {
    return isWritable(GEOHIERARCHY);
  }
  
  public boolean isGeoHierarchyReadable()
  {
    return isReadable(GEOHIERARCHY);
  }
  
  public boolean isGeoHierarchyModified()
  {
    return isModified(GEOHIERARCHY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoHierarchyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOHIERARCHY).getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public boolean isKeyNameWritable()
  {
    return isWritable(KEYNAME);
  }
  
  public boolean isKeyNameReadable()
  {
    return isReadable(KEYNAME);
  }
  
  public boolean isKeyNameModified()
  {
    return isModified(KEYNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public boolean isLastUpdateDateWritable()
  {
    return isWritable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateReadable()
  {
    return isReadable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateModified()
  {
    return isModified(LASTUPDATEDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
  }
  
  public boolean isLastUpdatedByWritable()
  {
    return isWritable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByReadable()
  {
    return isReadable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByModified()
  {
    return isModified(LASTUPDATEDBY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public String getLayerName()
  {
    return getValue(LAYERNAME);
  }
  
  public void setLayerName(String value)
  {
    if(value == null)
    {
      setValue(LAYERNAME, "");
    }
    else
    {
      setValue(LAYERNAME, value);
    }
  }
  
  public boolean isLayerNameWritable()
  {
    return isWritable(LAYERNAME);
  }
  
  public boolean isLayerNameReadable()
  {
    return isReadable(LAYERNAME);
  }
  
  public boolean isLayerNameModified()
  {
    return isModified(LAYERNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLayerNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LAYERNAME).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
    }
  }
  
  public boolean isLockedByWritable()
  {
    return isWritable(LOCKEDBY);
  }
  
  public boolean isLockedByReadable()
  {
    return isReadable(LOCKEDBY);
  }
  
  public boolean isLockedByModified()
  {
    return isModified(LOCKEDBY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public void setOwner(com.terraframe.mojo.system.ActorDTO value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public boolean isOwnerWritable()
  {
    return isWritable(OWNER);
  }
  
  public boolean isOwnerReadable()
  {
    return isReadable(OWNER);
  }
  
  public boolean isOwnerModified()
  {
    return isModified(OWNER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.query.SavedSearchDTO getSavedSearch()
  {
    if(getValue(SAVEDSEARCH) == null || getValue(SAVEDSEARCH).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.SavedSearchDTO.get(getRequest(), getValue(SAVEDSEARCH));
    }
  }
  
  public void setSavedSearch(dss.vector.solutions.query.SavedSearchDTO value)
  {
    if(value == null)
    {
      setValue(SAVEDSEARCH, "");
    }
    else
    {
      setValue(SAVEDSEARCH, value.getId());
    }
  }
  
  public boolean isSavedSearchWritable()
  {
    return isWritable(SAVEDSEARCH);
  }
  
  public boolean isSavedSearchReadable()
  {
    return isReadable(SAVEDSEARCH);
  }
  
  public boolean isSavedSearchModified()
  {
    return isModified(SAVEDSEARCH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSavedSearchMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SAVEDSEARCH).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public boolean isSeqWritable()
  {
    return isWritable(SEQ);
  }
  
  public boolean isSeqReadable()
  {
    return isReadable(SEQ);
  }
  
  public boolean isSeqModified()
  {
    return isModified(SEQ);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public boolean isSiteMasterWritable()
  {
    return isWritable(SITEMASTER);
  }
  
  public boolean isSiteMasterReadable()
  {
    return isReadable(SITEMASTER);
  }
  
  public boolean isSiteMasterModified()
  {
    return isModified(SITEMASTER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public String getSldFile()
  {
    return getValue(SLDFILE);
  }
  
  public void setSldFile(String value)
  {
    if(value == null)
    {
      setValue(SLDFILE, "");
    }
    else
    {
      setValue(SLDFILE, value);
    }
  }
  
  public boolean isSldFileWritable()
  {
    return isWritable(SLDFILE);
  }
  
  public boolean isSldFileReadable()
  {
    return isReadable(SLDFILE);
  }
  
  public boolean isSldFileModified()
  {
    return isModified(SLDFILE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSldFileMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SLDFILE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.query.ThematicVariableDTO getThematicVariable()
  {
    if(getValue(THEMATICVARIABLE) == null || getValue(THEMATICVARIABLE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.ThematicVariableDTO.get(getRequest(), getValue(THEMATICVARIABLE));
    }
  }
  
  public void setThematicVariable(dss.vector.solutions.query.ThematicVariableDTO value)
  {
    if(value == null)
    {
      setValue(THEMATICVARIABLE, "");
    }
    else
    {
      setValue(THEMATICVARIABLE, value.getId());
    }
  }
  
  public boolean isThematicVariableWritable()
  {
    return isWritable(THEMATICVARIABLE);
  }
  
  public boolean isThematicVariableReadable()
  {
    return isReadable(THEMATICVARIABLE);
  }
  
  public boolean isThematicVariableModified()
  {
    return isModified(THEMATICVARIABLE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getThematicVariableMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(THEMATICVARIABLE).getAttributeMdDTO();
  }
  
  public Boolean getViewCreated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(VIEWCREATED));
  }
  
  public void setViewCreated(Boolean value)
  {
    if(value == null)
    {
      setValue(VIEWCREATED, "");
    }
    else
    {
      setValue(VIEWCREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isViewCreatedWritable()
  {
    return isWritable(VIEWCREATED);
  }
  
  public boolean isViewCreatedReadable()
  {
    return isReadable(VIEWCREATED);
  }
  
  public boolean isViewCreatedModified()
  {
    return isModified(VIEWCREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getViewCreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(VIEWCREATED).getAttributeMdDTO();
  }
  
  public String getViewName()
  {
    return getValue(VIEWNAME);
  }
  
  public void setViewName(String value)
  {
    if(value == null)
    {
      setValue(VIEWNAME, "");
    }
    else
    {
      setValue(VIEWNAME, value);
    }
  }
  
  public boolean isViewNameWritable()
  {
    return isWritable(VIEWNAME);
  }
  
  public boolean isViewNameReadable()
  {
    return isReadable(VIEWNAME);
  }
  
  public boolean isViewNameModified()
  {
    return isModified(VIEWNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getViewNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VIEWNAME).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO> getAllHasCategory()
  {
    return (java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO> getAllHasCategory(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasCategoriesDTO> getAllHasCategoryRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasCategoriesDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasCategoriesDTO> getAllHasCategoryRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasCategoriesDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasCategoriesDTO addHasCategory(dss.vector.solutions.query.AbstractCategoryDTO child)
  {
    return (dss.vector.solutions.query.HasCategoriesDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasCategoriesDTO addHasCategory(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.AbstractCategoryDTO child)
  {
    return (dss.vector.solutions.query.HasCategoriesDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  public void removeHasCategory(dss.vector.solutions.query.HasCategoriesDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHasCategory(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasCategoriesDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHasCategory()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  public static void removeAllHasCategory(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.HasCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.SavedMapDTO> getAllMap()
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedMapDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.SavedMapDTO> getAllMap(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedMapDTO>) clientRequestIF.getParents(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.HasLayersDTO> getAllMapRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasLayersDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.HasLayersDTO> getAllMapRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.HasLayersDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public dss.vector.solutions.query.HasLayersDTO addMap(dss.vector.solutions.query.SavedMapDTO parent)
  {
    return (dss.vector.solutions.query.HasLayersDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.HasLayersDTO addMap(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.SavedMapDTO parent)
  {
    return (dss.vector.solutions.query.HasLayersDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public void removeMap(dss.vector.solutions.query.HasLayersDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeMap(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.HasLayersDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllMap()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static void removeAllMap(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.query.HasLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.LayerDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.LayerDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.query.LayerQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.LayerQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.Layer", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.LayerDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.LayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.LayerDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.LayerDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.LayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
