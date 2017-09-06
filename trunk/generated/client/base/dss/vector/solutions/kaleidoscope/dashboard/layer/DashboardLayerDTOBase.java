package dss.vector.solutions.kaleidoscope.dashboard.layer;

@com.runwaysdk.business.ClassSignature(hash = 979038657)
public abstract class DashboardLayerDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayer";
  private static final long serialVersionUID = 979038657;
  
  protected DashboardLayerDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DashboardLayerDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BBOXINCLUDED = "BBoxIncluded";
  public static java.lang.String ACTIVEBYDEFAULT = "activeByDefault";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DASHBOARDLEGEND = "dashboardLegend";
  public static java.lang.String DASHBOARDMAP = "dashboardMap";
  public static java.lang.String DISPLAYINLEGEND = "displayInLegend";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTPUBLISHDATE = "lastPublishDate";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LAYERENABLED = "layerEnabled";
  public static java.lang.String LAYERTYPE = "layerType";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NAMELABEL = "nameLabel";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String VIEWNAME = "viewName";
  public static java.lang.String VIRTUAL = "virtual";
  public Boolean getBBoxIncluded()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(BBOXINCLUDED));
  }
  
  public void setBBoxIncluded(Boolean value)
  {
    if(value == null)
    {
      setValue(BBOXINCLUDED, "");
    }
    else
    {
      setValue(BBOXINCLUDED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isBBoxIncludedWritable()
  {
    return isWritable(BBOXINCLUDED);
  }
  
  public boolean isBBoxIncludedReadable()
  {
    return isReadable(BBOXINCLUDED);
  }
  
  public boolean isBBoxIncludedModified()
  {
    return isModified(BBOXINCLUDED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getBBoxIncludedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(BBOXINCLUDED).getAttributeMdDTO();
  }
  
  public Boolean getActiveByDefault()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVEBYDEFAULT));
  }
  
  public void setActiveByDefault(Boolean value)
  {
    if(value == null)
    {
      setValue(ACTIVEBYDEFAULT, "");
    }
    else
    {
      setValue(ACTIVEBYDEFAULT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isActiveByDefaultWritable()
  {
    return isWritable(ACTIVEBYDEFAULT);
  }
  
  public boolean isActiveByDefaultReadable()
  {
    return isReadable(ACTIVEBYDEFAULT);
  }
  
  public boolean isActiveByDefaultModified()
  {
    return isModified(ACTIVEBYDEFAULT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getActiveByDefaultMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ACTIVEBYDEFAULT).getAttributeMdDTO();
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
  
  public dss.vector.solutions.kaleidoscope.dashboard.DashboardLegendDTO getDashboardLegend()
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardLegendDTO) this.getAttributeStructDTO(DASHBOARDLEGEND).getStructDTO();
  }
  
  public boolean isDashboardLegendWritable()
  {
    return isWritable(DASHBOARDLEGEND);
  }
  
  public boolean isDashboardLegendReadable()
  {
    return isReadable(DASHBOARDLEGEND);
  }
  
  public boolean isDashboardLegendModified()
  {
    return isModified(DASHBOARDLEGEND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeStructMdDTO getDashboardLegendMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeStructMdDTO) getAttributeDTO(DASHBOARDLEGEND).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO getDashboardMap()
  {
    if(getValue(DASHBOARDMAP) == null || getValue(DASHBOARDMAP).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.get(getRequest(), getValue(DASHBOARDMAP));
    }
  }
  
  public String getDashboardMapId()
  {
    return getValue(DASHBOARDMAP);
  }
  
  public void setDashboardMap(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO value)
  {
    if(value == null)
    {
      setValue(DASHBOARDMAP, "");
    }
    else
    {
      setValue(DASHBOARDMAP, value.getId());
    }
  }
  
  public boolean isDashboardMapWritable()
  {
    return isWritable(DASHBOARDMAP);
  }
  
  public boolean isDashboardMapReadable()
  {
    return isReadable(DASHBOARDMAP);
  }
  
  public boolean isDashboardMapModified()
  {
    return isModified(DASHBOARDMAP);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDashboardMapMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DASHBOARDMAP).getAttributeMdDTO();
  }
  
  public Boolean getDisplayInLegend()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(DISPLAYINLEGEND));
  }
  
  public void setDisplayInLegend(Boolean value)
  {
    if(value == null)
    {
      setValue(DISPLAYINLEGEND, "");
    }
    else
    {
      setValue(DISPLAYINLEGEND, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isDisplayInLegendWritable()
  {
    return isWritable(DISPLAYINLEGEND);
  }
  
  public boolean isDisplayInLegendReadable()
  {
    return isReadable(DISPLAYINLEGEND);
  }
  
  public boolean isDisplayInLegendModified()
  {
    return isModified(DISPLAYINLEGEND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getDisplayInLegendMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(DISPLAYINLEGEND).getAttributeMdDTO();
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
  
  public java.util.Date getLastPublishDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTPUBLISHDATE));
  }
  
  public void setLastPublishDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(LASTPUBLISHDATE, "");
    }
    else
    {
      setValue(LASTPUBLISHDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATETIME_FORMAT).format(value));
    }
  }
  
  public boolean isLastPublishDateWritable()
  {
    return isWritable(LASTPUBLISHDATE);
  }
  
  public boolean isLastPublishDateReadable()
  {
    return isReadable(LASTPUBLISHDATE);
  }
  
  public boolean isLastPublishDateModified()
  {
    return isModified(LASTPUBLISHDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getLastPublishDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTPUBLISHDATE).getAttributeMdDTO();
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
  
  public Boolean getLayerEnabled()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(LAYERENABLED));
  }
  
  public void setLayerEnabled(Boolean value)
  {
    if(value == null)
    {
      setValue(LAYERENABLED, "");
    }
    else
    {
      setValue(LAYERENABLED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isLayerEnabledWritable()
  {
    return isWritable(LAYERENABLED);
  }
  
  public boolean isLayerEnabledReadable()
  {
    return isReadable(LAYERENABLED);
  }
  
  public boolean isLayerEnabledModified()
  {
    return isModified(LAYERENABLED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getLayerEnabledMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(LAYERENABLED).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO> getLayerType()
  {
    return (java.util.List<dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO.CLASS, getEnumNames(LAYERTYPE));
  }
  
  public java.util.List<String> getLayerTypeEnumNames()
  {
    return getEnumNames(LAYERTYPE);
  }
  
  public void addLayerType(dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO enumDTO)
  {
    addEnumItem(LAYERTYPE, enumDTO.toString());
  }
  
  public void removeLayerType(dss.vector.solutions.kaleidoscope.dashboard.layer.AllLayerTypeDTO enumDTO)
  {
    removeEnumItem(LAYERTYPE, enumDTO.toString());
  }
  
  public void clearLayerType()
  {
    clearEnum(LAYERTYPE);
  }
  
  public boolean isLayerTypeWritable()
  {
    return isWritable(LAYERTYPE);
  }
  
  public boolean isLayerTypeReadable()
  {
    return isReadable(LAYERTYPE);
  }
  
  public boolean isLayerTypeModified()
  {
    return isModified(LAYERTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getLayerTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(LAYERTYPE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabelDTO getNameLabel()
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerNameLabelDTO) this.getAttributeStructDTO(NAMELABEL).getStructDTO();
  }
  
  public boolean isNameLabelWritable()
  {
    return isWritable(NAMELABEL);
  }
  
  public boolean isNameLabelReadable()
  {
    return isReadable(NAMELABEL);
  }
  
  public boolean isNameLabelModified()
  {
    return isModified(NAMELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalCharacterMdDTO getNameLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalCharacterMdDTO) getAttributeDTO(NAMELABEL).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getViewNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VIEWNAME).getAttributeMdDTO();
  }
  
  public Boolean getVirtual()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(VIRTUAL));
  }
  
  public void setVirtual(Boolean value)
  {
    if(value == null)
    {
      setValue(VIRTUAL, "");
    }
    else
    {
      setValue(VIRTUAL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isVirtualWritable()
  {
    return isWritable(VIRTUAL);
  }
  
  public boolean isVirtualReadable()
  {
    return isReadable(VIRTUAL);
  }
  
  public boolean isVirtualModified()
  {
    return isModified(VIRTUAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getVirtualMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(VIRTUAL).getAttributeMdDTO();
  }
  
  public final java.lang.String applyWithStyle(dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO style, java.lang.String mapId, java.lang.String state)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{style, mapId, state};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "applyWithStyle", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String applyWithStyle(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO style, java.lang.String mapId, java.lang.String state)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, style, mapId, state};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "applyWithStyle", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getJSON()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "getJSON", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "getJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoHierarchyQueryDTO getSortedUniversals(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "getSortedUniversals", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateLegend(java.lang.Integer legendXPosition, java.lang.Integer legendYPosition, java.lang.Boolean groupedInLegend)
  {
    String[] _declaredTypes = new String[]{"java.lang.Integer", "java.lang.Integer", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{legendXPosition, legendYPosition, groupedInLegend};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "updateLegend", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateLegend(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Integer legendXPosition, java.lang.Integer legendYPosition, java.lang.Boolean groupedInLegend)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer", "java.lang.Integer", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{id, legendXPosition, legendYPosition, groupedInLegend};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "updateLegend", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO> getAllHasStyle()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO> getAllHasStyle(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO> getAllHasStyleRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO> getAllHasStyleRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO.CLASS);
  }
  
  public dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO addHasStyle(dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO child)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO.CLASS);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO addHasStyle(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleDTO child)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO.CLASS);
  }
  
  public void removeHasStyle(dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHasStyle(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHasStyle()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO.CLASS);
  }
  
  public static void removeAllHasStyle(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.kaleidoscope.dashboard.HasStyleDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO> getAllContainingMap()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO> getAllContainingMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO>) clientRequestIF.getParents(id, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO> getAllContainingMapRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO> getAllContainingMapRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO addContainingMap(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO parent)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO addContainingMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO parent)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public void removeContainingMap(dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeContainingMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllContainingMap()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public static void removeAllContainingMap(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO) dto;
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
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
