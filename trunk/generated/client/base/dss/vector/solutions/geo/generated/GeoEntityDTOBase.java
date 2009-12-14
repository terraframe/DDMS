package dss.vector.solutions.geo.generated;

@com.terraframe.mojo.business.ClassSignature(hash = -134375475)
public abstract class GeoEntityDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.GeoEntity";
  private static final long serialVersionUID = -134375475;
  
  protected GeoEntityDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GeoEntityDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVATED = "activated";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String GEODATA = "geoData";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String GEOMULTIPOLYGON = "geoMultiPolygon";
  public static java.lang.String GEOPOINT = "geoPoint";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TERM = "term";
  public static java.lang.String TYPE = "type";
  public Boolean getActivated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVATED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getActivatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ACTIVATED).getAttributeMdDTO();
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
  
  public String getGeoData()
  {
    return getValue(GEODATA);
  }
  
  public void setGeoData(String value)
  {
    if(value == null)
    {
      setValue(GEODATA, "");
    }
    else
    {
      setValue(GEODATA, value);
    }
  }
  
  public boolean isGeoDataWritable()
  {
    return isWritable(GEODATA);
  }
  
  public boolean isGeoDataReadable()
  {
    return isReadable(GEODATA);
  }
  
  public boolean isGeoDataModified()
  {
    return isModified(GEODATA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getGeoDataMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(GEODATA).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGeoIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOID).getAttributeMdDTO();
  }
  
  public com.vividsolutions.jts.geom.MultiPolygon getGeoMultiPolygon()
  {
    return (com.vividsolutions.jts.geom.MultiPolygon)getObjectValue(GEOMULTIPOLYGON);
  }
  
  public void setGeoMultiPolygon(com.vividsolutions.jts.geom.MultiPolygon value)
  {
    if(value == null)
    {
      setValue(GEOMULTIPOLYGON, "");
    }
    else
    {
      setValue(GEOMULTIPOLYGON, value);
    }
  }
  
  public boolean isGeoMultiPolygonWritable()
  {
    return isWritable(GEOMULTIPOLYGON);
  }
  
  public boolean isGeoMultiPolygonReadable()
  {
    return isReadable(GEOMULTIPOLYGON);
  }
  
  public boolean isGeoMultiPolygonModified()
  {
    return isModified(GEOMULTIPOLYGON);
  }
  
  public final com.terraframe.mojo.gis.transport.metadata.AttributeMultiPolygonMdDTO getGeoMultiPolygonMd()
  {
    return (com.terraframe.mojo.gis.transport.metadata.AttributeMultiPolygonMdDTO) getAttributeDTO(GEOMULTIPOLYGON).getAttributeMdDTO();
  }
  
  public com.vividsolutions.jts.geom.Point getGeoPoint()
  {
    return (com.vividsolutions.jts.geom.Point)getObjectValue(GEOPOINT);
  }
  
  public void setGeoPoint(com.vividsolutions.jts.geom.Point value)
  {
    if(value == null)
    {
      setValue(GEOPOINT, "");
    }
    else
    {
      setValue(GEOPOINT, value);
    }
  }
  
  public boolean isGeoPointWritable()
  {
    return isWritable(GEOPOINT);
  }
  
  public boolean isGeoPointReadable()
  {
    return isReadable(GEOPOINT);
  }
  
  public boolean isGeoPointModified()
  {
    return isModified(GEOPOINT);
  }
  
  public final com.terraframe.mojo.gis.transport.metadata.AttributePointMdDTO getGeoPointMd()
  {
    return (com.terraframe.mojo.gis.transport.metadata.AttributePointMdDTO) getAttributeDTO(GEOPOINT).getAttributeMdDTO();
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
  
  public final java.lang.String[] applyWithParent(java.lang.String parentGeoEntityId, java.lang.Boolean cloneOperation, java.lang.String oldParentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.String"};
    Object[] _parameters = new Object[]{parentGeoEntityId, cloneOperation, oldParentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "applyWithParent", _declaredTypes);
    return (java.lang.String[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String[] applyWithParent(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentGeoEntityId, java.lang.Boolean cloneOperation, java.lang.String oldParentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentGeoEntityId, cloneOperation, oldParentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "applyWithParent", _declaredTypes);
    return (java.lang.String[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.geo.GeoEntityViewDTO changeUniversalType(java.lang.String newType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{newType};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "changeUniversalType", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityViewDTO changeUniversalType(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String newType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, newType};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "changeUniversalType", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.geo.GeoEntityViewDTO[] collectAllLocatedIn(java.lang.Boolean includeParents, java.lang.String filter)
  {
    String[] _declaredTypes = new String[]{"java.lang.Boolean", "java.lang.String"};
    Object[] _parameters = new Object[]{includeParents, filter};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "collectAllLocatedIn", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityViewDTO[] collectAllLocatedIn(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.Boolean includeParents, java.lang.String filter)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.String"};
    Object[] _parameters = new Object[]{id, includeParents, filter};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "collectAllLocatedIn", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void confirmChangeParent(java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "confirmChangeParent", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void confirmChangeParent(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "confirmChangeParent", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void confirmDeleteEntity(java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "confirmDeleteEntity", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void confirmDeleteEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "confirmDeleteEntity", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteEntity()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "deleteEntity", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "deleteEntity", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteRelationship(java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "deleteRelationship", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteRelationship(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String parentId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, parentId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "deleteRelationship", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String[] getAllChildIds(java.lang.String typeFilter)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{typeFilter};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getAllChildIds", _declaredTypes);
    return (java.lang.String[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String[] getAllChildIds(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String typeFilter)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, typeFilter};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getAllChildIds", _declaredTypes);
    return (java.lang.String[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityViewQueryDTO getAsViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String[] entities)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{entities};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getAsViews", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String[] getCompatibleTypes()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getCompatibleTypes", _declaredTypes);
    return (java.lang.String[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String[] getCompatibleTypes(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getCompatibleTypes", _declaredTypes);
    return (java.lang.String[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.geo.generated.GeoEntityDTO[] getImmediateSprayChildren()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getImmediateSprayChildren", _declaredTypes);
    return (dss.vector.solutions.geo.generated.GeoEntityDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.geo.generated.GeoEntityDTO[] getImmediateSprayChildren(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getImmediateSprayChildren", _declaredTypes);
    return (dss.vector.solutions.geo.generated.GeoEntityDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.geo.GeoEntityViewQueryDTO getOrderedChildren(java.lang.String typeFilter)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{typeFilter};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getOrderedChildren", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityViewQueryDTO getOrderedChildren(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String typeFilter)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, typeFilter};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getOrderedChildren", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getTypeDisplayLabel()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getTypeDisplayLabel", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getTypeDisplayLabel(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getTypeDisplayLabel", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityViewDTO getViewByGeoId(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "getViewByGeoId", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoEntityViewDTO[] searchAndCollectByGeoId(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.lang.String filter)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{geoId, filter};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "searchAndCollectByGeoId", _declaredTypes);
    return (dss.vector.solutions.geo.GeoEntityViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO searchByEntityName(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String entityType, java.lang.String entityName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{entityType, entityName};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "searchByEntityName", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO searchByEntityNameOrGeoId(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String entityType, java.lang.String entityName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{entityType, entityName};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "searchByEntityNameOrGeoId", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.generated.GeoEntityDTO searchByGeoId(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "searchByGeoId", _declaredTypes);
    return (dss.vector.solutions.geo.generated.GeoEntityDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String[] updateFromTree()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "updateFromTree", _declaredTypes);
    return (java.lang.String[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String[] updateFromTree(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "updateFromTree", _declaredTypes);
    return (java.lang.String[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.generated.GeoEntityDTO> getAllContainsGeoEntity()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.generated.GeoEntityDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.generated.GeoEntityDTO> getAllContainsGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.generated.GeoEntityDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.LocatedInDTO> getAllContainsGeoEntityRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.LocatedInDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.LocatedInDTO> getAllContainsGeoEntityRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.LocatedInDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public dss.vector.solutions.geo.LocatedInDTO addContainsGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO child)
  {
    return (dss.vector.solutions.geo.LocatedInDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.LocatedInDTO addContainsGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.geo.generated.GeoEntityDTO child)
  {
    return (dss.vector.solutions.geo.LocatedInDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public void removeContainsGeoEntity(dss.vector.solutions.geo.LocatedInDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeContainsGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.geo.LocatedInDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllContainsGeoEntity()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public static void removeAllContainsGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.GeoSynonymDTO> getAllSynonyms()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoSynonymDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.geo.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.GeoSynonymDTO> getAllSynonyms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoSynonymDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.geo.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.HasSynonymDTO> getAllSynonymsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.HasSynonymDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.geo.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.HasSynonymDTO> getAllSynonymsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.HasSynonymDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.geo.HasSynonymDTO.CLASS);
  }
  
  public dss.vector.solutions.geo.HasSynonymDTO addSynonyms(dss.vector.solutions.geo.GeoSynonymDTO child)
  {
    return (dss.vector.solutions.geo.HasSynonymDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.geo.HasSynonymDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.HasSynonymDTO addSynonyms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.geo.GeoSynonymDTO child)
  {
    return (dss.vector.solutions.geo.HasSynonymDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.geo.HasSynonymDTO.CLASS);
  }
  
  public void removeSynonyms(dss.vector.solutions.geo.HasSynonymDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeSynonyms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.geo.HasSynonymDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllSynonyms()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.geo.HasSynonymDTO.CLASS);
  }
  
  public static void removeAllSynonyms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.geo.HasSynonymDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.generated.GeoEntityDTO> getAllLocatedInGeoEntity()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.generated.GeoEntityDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.generated.GeoEntityDTO> getAllLocatedInGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.generated.GeoEntityDTO>) clientRequestIF.getParents(id, dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.LocatedInDTO> getAllLocatedInGeoEntityRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.LocatedInDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.LocatedInDTO> getAllLocatedInGeoEntityRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.LocatedInDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public dss.vector.solutions.geo.LocatedInDTO addLocatedInGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO parent)
  {
    return (dss.vector.solutions.geo.LocatedInDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.LocatedInDTO addLocatedInGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.geo.generated.GeoEntityDTO parent)
  {
    return (dss.vector.solutions.geo.LocatedInDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public void removeLocatedInGeoEntity(dss.vector.solutions.geo.LocatedInDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeLocatedInGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.geo.LocatedInDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllLocatedInGeoEntity()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public static void removeAllLocatedInGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.geo.LocatedInDTO.CLASS);
  }
  
  public static dss.vector.solutions.geo.generated.GeoEntityDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.generated.GeoEntityDTO) dto;
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
  
  public static dss.vector.solutions.geo.generated.GeoEntityQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.generated.GeoEntityQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.geo.generated.GeoEntity", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.generated.GeoEntityDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.GeoEntityDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.generated.GeoEntityDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.GeoEntityDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.GeoEntityDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
