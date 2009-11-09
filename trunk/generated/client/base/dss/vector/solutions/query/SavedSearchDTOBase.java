package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -111561813)
public abstract class SavedSearchDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.SavedSearch";
  private static final long serialVersionUID = -111561813;
  
  protected SavedSearchDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SavedSearchDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONFIG = "config";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String CSVFILE = "csvFile";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String QUERYNAME = "queryName";
  public static java.lang.String QUERYTYPE = "queryType";
  public static java.lang.String QUERYXML = "queryXml";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TEMPLATEFILE = "templateFile";
  public static java.lang.String THEMATICLAYER = "thematicLayer";
  public static java.lang.String TYPE = "type";
  public String getConfig()
  {
    return getValue(CONFIG);
  }
  
  public void setConfig(String value)
  {
    if(value == null)
    {
      setValue(CONFIG, "");
    }
    else
    {
      setValue(CONFIG, value);
    }
  }
  
  public boolean isConfigWritable()
  {
    return isWritable(CONFIG);
  }
  
  public boolean isConfigReadable()
  {
    return isReadable(CONFIG);
  }
  
  public boolean isConfigModified()
  {
    return isModified(CONFIG);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getConfigMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(CONFIG).getAttributeMdDTO();
  }
  
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
  
  public void setCsvFile(String value)
  {
    if(value == null)
    {
      setValue(CSVFILE, "");
    }
    else
    {
      setValue(CSVFILE, value);
    }
  }
  
  public boolean isCsvFileWritable()
  {
    return isWritable(CSVFILE);
  }
  
  public boolean isCsvFileModified()
  {
    return isModified(CSVFILE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeFileMdDTO getCsvFileMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeFileMdDTO) getAttributeDTO(CSVFILE).getAttributeMdDTO();
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
  
  public String getQueryName()
  {
    return getValue(QUERYNAME);
  }
  
  public void setQueryName(String value)
  {
    if(value == null)
    {
      setValue(QUERYNAME, "");
    }
    else
    {
      setValue(QUERYNAME, value);
    }
  }
  
  public boolean isQueryNameWritable()
  {
    return isWritable(QUERYNAME);
  }
  
  public boolean isQueryNameReadable()
  {
    return isReadable(QUERYNAME);
  }
  
  public boolean isQueryNameModified()
  {
    return isModified(QUERYNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getQueryNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUERYNAME).getAttributeMdDTO();
  }
  
  public String getQueryType()
  {
    return getValue(QUERYTYPE);
  }
  
  public void setQueryType(String value)
  {
    if(value == null)
    {
      setValue(QUERYTYPE, "");
    }
    else
    {
      setValue(QUERYTYPE, value);
    }
  }
  
  public boolean isQueryTypeWritable()
  {
    return isWritable(QUERYTYPE);
  }
  
  public boolean isQueryTypeReadable()
  {
    return isReadable(QUERYTYPE);
  }
  
  public boolean isQueryTypeModified()
  {
    return isModified(QUERYTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getQueryTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUERYTYPE).getAttributeMdDTO();
  }
  
  public String getQueryXml()
  {
    return getValue(QUERYXML);
  }
  
  public void setQueryXml(String value)
  {
    if(value == null)
    {
      setValue(QUERYXML, "");
    }
    else
    {
      setValue(QUERYXML, value);
    }
  }
  
  public boolean isQueryXmlWritable()
  {
    return isWritable(QUERYXML);
  }
  
  public boolean isQueryXmlReadable()
  {
    return isReadable(QUERYXML);
  }
  
  public boolean isQueryXmlModified()
  {
    return isModified(QUERYXML);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getQueryXmlMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(QUERYXML).getAttributeMdDTO();
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
  
  public String getTemplateFile()
  {
    return getValue(TEMPLATEFILE);
  }
  
  public void setTemplateFile(String value)
  {
    if(value == null)
    {
      setValue(TEMPLATEFILE, "");
    }
    else
    {
      setValue(TEMPLATEFILE, value);
    }
  }
  
  public boolean isTemplateFileWritable()
  {
    return isWritable(TEMPLATEFILE);
  }
  
  public boolean isTemplateFileReadable()
  {
    return isReadable(TEMPLATEFILE);
  }
  
  public boolean isTemplateFileModified()
  {
    return isModified(TEMPLATEFILE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeFileMdDTO getTemplateFileMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeFileMdDTO) getAttributeDTO(TEMPLATEFILE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.query.ThematicLayerDTO getThematicLayer()
  {
    if(getValue(THEMATICLAYER) == null || getValue(THEMATICLAYER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.ThematicLayerDTO.get(getRequest(), getValue(THEMATICLAYER));
    }
  }
  
  public void setThematicLayer(dss.vector.solutions.query.ThematicLayerDTO value)
  {
    if(value == null)
    {
      setValue(THEMATICLAYER, "");
    }
    else
    {
      setValue(THEMATICLAYER, value.getId());
    }
  }
  
  public boolean isThematicLayerWritable()
  {
    return isWritable(THEMATICLAYER);
  }
  
  public boolean isThematicLayerReadable()
  {
    return isReadable(THEMATICLAYER);
  }
  
  public boolean isThematicLayerModified()
  {
    return isModified(THEMATICLAYER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getThematicLayerMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(THEMATICLAYER).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.query.LayerViewDTO[] getAllLayers()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAllLayers", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.LayerViewDTO[] getAllLayers(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAllLayers", _declaredTypes);
    return (dss.vector.solutions.query.LayerViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.SavedSearchViewDTO getAsView(java.lang.Boolean includeXML, java.lang.Boolean includeConfig)
  {
    String[] _declaredTypes = new String[]{"java.lang.Boolean", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{includeXML, includeConfig};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAsView", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO getAsView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Boolean includeXML, java.lang.Boolean includeConfig)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, includeXML, includeConfig};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAsView", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewQueryDTO getSearchesForType(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String searchType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{searchType};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getSearchesForType", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.io.InputStream getTemplateStream()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getTemplateStream", _declaredTypes);
    return (java.io.InputStream) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.io.InputStream getTemplateStream(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getTemplateStream", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO loadDefaultSearch(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.query.SavedSearchViewDTO savedSearchView)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.SavedSearchView"};
    Object[] _parameters = new Object[]{savedSearchView};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "loadDefaultSearch", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO loadSearch(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String searchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{searchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "loadSearch", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO saveSearch(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.query.SavedSearchViewDTO savedSearchView)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.SavedSearchView"};
    Object[] _parameters = new Object[]{savedSearchView};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "saveSearch", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO updateSearch(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.query.SavedSearchViewDTO savedSearchView)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.SavedSearchView"};
    Object[] _parameters = new Object[]{savedSearchView};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "updateSearch", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.UniversalLayerDTO> getAllDefinesLayers()
  {
    return (java.util.List<? extends dss.vector.solutions.query.UniversalLayerDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.UniversalLayerDTO> getAllDefinesLayers(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.UniversalLayerDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.DefinesLayersDTO> getAllDefinesLayersRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.DefinesLayersDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.DefinesLayersDTO> getAllDefinesLayersRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.DefinesLayersDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public dss.vector.solutions.query.DefinesLayersDTO addDefinesLayers(dss.vector.solutions.query.UniversalLayerDTO child)
  {
    return (dss.vector.solutions.query.DefinesLayersDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.DefinesLayersDTO addDefinesLayers(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.UniversalLayerDTO child)
  {
    return (dss.vector.solutions.query.DefinesLayersDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public void removeDefinesLayers(dss.vector.solutions.query.DefinesLayersDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeDefinesLayers(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.DefinesLayersDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllDefinesLayers()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public static void removeAllDefinesLayers(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.MDSSUserDTO> getAllPersistedBy()
  {
    return (java.util.List<? extends dss.vector.solutions.MDSSUserDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.MDSSUserDTO> getAllPersistedBy(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.MDSSUserDTO>) clientRequestIF.getParents(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO> getAllPersistedByRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO> getAllPersistedByRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public dss.vector.solutions.query.PersistsSearchDTO addPersistedBy(dss.vector.solutions.MDSSUserDTO parent)
  {
    return (dss.vector.solutions.query.PersistsSearchDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.PersistsSearchDTO addPersistedBy(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.MDSSUserDTO parent)
  {
    return (dss.vector.solutions.query.PersistsSearchDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public void removePersistedBy(dss.vector.solutions.query.PersistsSearchDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePersistedBy(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.PersistsSearchDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPersistedBy()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static void removeAllPersistedBy(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.SavedSearchDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.SavedSearchDTO) dto;
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
  
  public static dss.vector.solutions.query.SavedSearchQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.SavedSearchQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.SavedSearch", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.SavedSearchDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.SavedSearchDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
