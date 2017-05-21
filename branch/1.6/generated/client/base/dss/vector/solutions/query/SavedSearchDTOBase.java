package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1124646508)
public abstract class SavedSearchDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.SavedSearch";
  private static final long serialVersionUID = 1124646508;
  
  protected SavedSearchDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SavedSearchDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String ISMATERIALIZED = "isMaterialized";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MAPPABLE = "mappable";
  public static java.lang.String MATERIALIZEDTABLE = "materializedTable";
  public static java.lang.String MATERIALIZEDVIEWNAME = "materializedViewName";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String QUERYNAME = "queryName";
  public static java.lang.String QUERYTYPE = "queryType";
  public static java.lang.String QUERYXML = "queryXml";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TEMPLATEFILE = "templateFile";
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getConfigMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(CONFIG).getAttributeMdDTO();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeFileMdDTO getCsvFileMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeFileMdDTO) getAttributeDTO(CSVFILE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.DiseaseDTO getDisease()
  {
    if(getValue(DISEASE) == null || getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.DiseaseDTO.get(getRequest(), getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.DiseaseDTO value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
    }
  }
  
  public boolean isDiseaseWritable()
  {
    return isWritable(DISEASE);
  }
  
  public boolean isDiseaseReadable()
  {
    return isReadable(DISEASE);
  }
  
  public boolean isDiseaseModified()
  {
    return isModified(DISEASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomainDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  public Boolean getIsMaterialized()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISMATERIALIZED));
  }
  
  public void setIsMaterialized(Boolean value)
  {
    if(value == null)
    {
      setValue(ISMATERIALIZED, "");
    }
    else
    {
      setValue(ISMATERIALIZED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsMaterializedWritable()
  {
    return isWritable(ISMATERIALIZED);
  }
  
  public boolean isIsMaterializedReadable()
  {
    return isReadable(ISMATERIALIZED);
  }
  
  public boolean isIsMaterializedModified()
  {
    return isModified(ISMATERIALIZED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsMaterializedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISMATERIALIZED).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public Boolean getMappable()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(MAPPABLE));
  }
  
  public void setMappable(Boolean value)
  {
    if(value == null)
    {
      setValue(MAPPABLE, "");
    }
    else
    {
      setValue(MAPPABLE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isMappableWritable()
  {
    return isWritable(MAPPABLE);
  }
  
  public boolean isMappableReadable()
  {
    return isReadable(MAPPABLE);
  }
  
  public boolean isMappableModified()
  {
    return isModified(MAPPABLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getMappableMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(MAPPABLE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdTableDTO getMaterializedTable()
  {
    if(getValue(MATERIALIZEDTABLE) == null || getValue(MATERIALIZEDTABLE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdTableDTO.get(getRequest(), getValue(MATERIALIZEDTABLE));
    }
  }
  
  public String getMaterializedTableId()
  {
    return getValue(MATERIALIZEDTABLE);
  }
  
  public void setMaterializedTable(com.runwaysdk.system.metadata.MdTableDTO value)
  {
    if(value == null)
    {
      setValue(MATERIALIZEDTABLE, "");
    }
    else
    {
      setValue(MATERIALIZEDTABLE, value.getId());
    }
  }
  
  public boolean isMaterializedTableWritable()
  {
    return isWritable(MATERIALIZEDTABLE);
  }
  
  public boolean isMaterializedTableReadable()
  {
    return isReadable(MATERIALIZEDTABLE);
  }
  
  public boolean isMaterializedTableModified()
  {
    return isModified(MATERIALIZEDTABLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getMaterializedTableMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MATERIALIZEDTABLE).getAttributeMdDTO();
  }
  
  public String getMaterializedViewName()
  {
    return getValue(MATERIALIZEDVIEWNAME);
  }
  
  public void setMaterializedViewName(String value)
  {
    if(value == null)
    {
      setValue(MATERIALIZEDVIEWNAME, "");
    }
    else
    {
      setValue(MATERIALIZEDVIEWNAME, value);
    }
  }
  
  public boolean isMaterializedViewNameWritable()
  {
    return isWritable(MATERIALIZEDVIEWNAME);
  }
  
  public boolean isMaterializedViewNameReadable()
  {
    return isReadable(MATERIALIZEDVIEWNAME);
  }
  
  public boolean isMaterializedViewNameModified()
  {
    return isModified(MATERIALIZEDVIEWNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMaterializedViewNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MATERIALIZEDVIEWNAME).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.ActorDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getQueryNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUERYNAME).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getQueryTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUERYTYPE).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getQueryXmlMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(QUERYXML).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeFileMdDTO getTemplateFileMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeFileMdDTO) getAttributeDTO(TEMPLATEFILE).getAttributeMdDTO();
  }
  
  public final java.io.InputStream exportQuery()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "exportQuery", _declaredTypes);
    return (java.io.InputStream) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.io.InputStream exportQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "exportQuery", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getAllKaleidoscopes()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAllKaleidoscopes", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getAllKaleidoscopes(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAllKaleidoscopes", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.SavedSearchViewDTO getAsView(java.lang.Boolean includeXML, java.lang.Boolean includeConfig)
  {
    String[] _declaredTypes = new String[]{"java.lang.Boolean", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{includeXML, includeConfig};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAsView", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO getAsView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Boolean includeXML, java.lang.Boolean includeConfig)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, includeXML, includeConfig};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAsView", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.AttributeGeoHierarchyDTO[] getAttributeGeoHierarchies()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAttributeGeoHierarchies", _declaredTypes);
    return (dss.vector.solutions.query.AttributeGeoHierarchyDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.AttributeGeoHierarchyDTO[] getAttributeGeoHierarchies(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getAttributeGeoHierarchies", _declaredTypes);
    return (dss.vector.solutions.query.AttributeGeoHierarchyDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getDatabaseViewName()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getDatabaseViewName", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getDatabaseViewName(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getDatabaseViewName", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO[] getMappableSearches(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getMappableSearches", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewQueryDTO getSearchesForType(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String searchType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{searchType};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getSearchesForType", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.io.InputStream getTemplateStream()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getTemplateStream", _declaredTypes);
    return (java.io.InputStream) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.io.InputStream getTemplateStream(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getTemplateStream", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.query.ThematicVariableDTO[] getThematicVariables()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getThematicVariables", _declaredTypes);
    return (dss.vector.solutions.query.ThematicVariableDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.query.ThematicVariableDTO[] getThematicVariables(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "getThematicVariables", _declaredTypes);
    return (dss.vector.solutions.query.ThematicVariableDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void importQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.io.InputStream queryFile)
  {
    String[] _declaredTypes = new String[]{"java.io.InputStream"};
    Object[] _parameters = new Object[]{queryFile};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "importQuery", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO loadDefaultSearch(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.query.SavedSearchViewDTO savedSearchView)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.SavedSearchView"};
    Object[] _parameters = new Object[]{savedSearchView};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "loadDefaultSearch", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO loadSearch(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String searchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{searchId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "loadSearch", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO saveSearch(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.query.SavedSearchViewDTO savedSearchView)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.SavedSearchView"};
    Object[] _parameters = new Object[]{savedSearchView};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "saveSearch", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.SavedSearchViewDTO updateSearch(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.query.SavedSearchViewDTO savedSearchView)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.query.SavedSearchView"};
    Object[] _parameters = new Object[]{savedSearchView};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "updateSearch", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.MDSSUserDTO> getAllPersistedBy()
  {
    return (java.util.List<? extends dss.vector.solutions.MDSSUserDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.MDSSUserDTO> getAllPersistedBy(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.MDSSUserDTO>) clientRequestIF.getParents(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO> getAllPersistedByRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO> getAllPersistedByRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public dss.vector.solutions.query.PersistsSearchDTO addPersistedBy(dss.vector.solutions.MDSSUserDTO parent)
  {
    return (dss.vector.solutions.query.PersistsSearchDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.PersistsSearchDTO addPersistedBy(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.MDSSUserDTO parent)
  {
    return (dss.vector.solutions.query.PersistsSearchDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public void removePersistedBy(dss.vector.solutions.query.PersistsSearchDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePersistedBy(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.PersistsSearchDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPersistedBy()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static void removeAllPersistedBy(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.SavedSearchDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.query.SavedSearchQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.SavedSearchQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.SavedSearchDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.SavedSearchDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.SavedSearchDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.SavedSearchDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.SavedSearchDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
