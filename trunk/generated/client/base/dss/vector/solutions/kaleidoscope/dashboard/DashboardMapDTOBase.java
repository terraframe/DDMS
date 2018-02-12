/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.kaleidoscope.dashboard;

@com.runwaysdk.business.ClassSignature(hash = 1412749262)
public abstract class DashboardMapDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.DashboardMap";
  private static final long serialVersionUID = 1412749262;
  
  protected DashboardMapDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DashboardMapDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVEBASEMAP = "activeBaseMap";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DASHBOARD = "dashboard";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NAME = "name";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public String getActiveBaseMap()
  {
    return getValue(ACTIVEBASEMAP);
  }
  
  public void setActiveBaseMap(String value)
  {
    if(value == null)
    {
      setValue(ACTIVEBASEMAP, "");
    }
    else
    {
      setValue(ACTIVEBASEMAP, value);
    }
  }
  
  public boolean isActiveBaseMapWritable()
  {
    return isWritable(ACTIVEBASEMAP);
  }
  
  public boolean isActiveBaseMapReadable()
  {
    return isReadable(ACTIVEBASEMAP);
  }
  
  public boolean isActiveBaseMapModified()
  {
    return isModified(ACTIVEBASEMAP);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getActiveBaseMapMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACTIVEBASEMAP).getAttributeMdDTO();
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
  
  public dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO getDashboard()
  {
    if(getValue(DASHBOARD) == null || getValue(DASHBOARD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO.get(getRequest(), getValue(DASHBOARD));
    }
  }
  
  public String getDashboardId()
  {
    return getValue(DASHBOARD);
  }
  
  public void setDashboard(dss.vector.solutions.kaleidoscope.dashboard.DashboardDTO value)
  {
    if(value == null)
    {
      setValue(DASHBOARD, "");
    }
    else
    {
      setValue(DASHBOARD, value.getId());
    }
  }
  
  public boolean isDashboardWritable()
  {
    return isWritable(DASHBOARD);
  }
  
  public boolean isDashboardReadable()
  {
    return isReadable(DASHBOARD);
  }
  
  public boolean isDashboardModified()
  {
    return isModified(DASHBOARD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDashboardMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DASHBOARD).getAttributeMdDTO();
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
  
  public String getName()
  {
    return getValue(NAME);
  }
  
  public void setName(String value)
  {
    if(value == null)
    {
      setValue(NAME, "");
    }
    else
    {
      setValue(NAME, value);
    }
  }
  
  public boolean isNameWritable()
  {
    return isWritable(NAME);
  }
  
  public boolean isNameReadable()
  {
    return isReadable(NAME);
  }
  
  public boolean isNameModified()
  {
    return isModified(NAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NAME).getAttributeMdDTO();
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
  
  public final java.io.InputStream exportLayerData(java.lang.String state, java.lang.String layerId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{state, layerId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "exportLayerData", _declaredTypes);
    return (java.io.InputStream) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.io.InputStream exportLayerData(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String state, java.lang.String layerId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, state, layerId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "exportLayerData", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.io.InputStream generateMapImageExport(java.lang.String outFileFormat, java.lang.String mapBounds, java.lang.String mapSize, java.lang.String activeBaseMap)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{outFileFormat, mapBounds, mapSize, activeBaseMap};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "generateMapImageExport", _declaredTypes);
    return (java.io.InputStream) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.io.InputStream generateMapImageExport(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String outFileFormat, java.lang.String mapBounds, java.lang.String mapSize, java.lang.String activeBaseMap)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, outFileFormat, mapBounds, mapSize, activeBaseMap};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "generateMapImageExport", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getDrillDownUniversals(java.lang.String layerId, java.lang.String geoId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{layerId, geoId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "getDrillDownUniversals", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getDrillDownUniversals(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String layerId, java.lang.String geoId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, layerId, geoId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "getDrillDownUniversals", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getMapJSON(java.lang.String config)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{config};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "getMapJSON", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getMapJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String config)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, config};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "getMapJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.kaleidoscope.dashboard.MdAttributeViewDTO[] getSecondaryAttributes(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String mapId, java.lang.String mdAttributeId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{mapId, mdAttributeId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "getSecondaryAttributes", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.MdAttributeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.geo.GeoHierarchyDTO[] getUniversalAggregations(java.lang.String mdAttributeId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{mdAttributeId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "getUniversalAggregations", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoHierarchyDTO[] getUniversalAggregations(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String mdAttributeId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, mdAttributeId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "getUniversalAggregations", _declaredTypes);
    return (dss.vector.solutions.geo.GeoHierarchyDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String orderLayers(java.lang.String[] layerIds)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{layerIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "orderLayers", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String orderLayers(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String[] layerIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{id, layerIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "orderLayers", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String refresh(java.lang.String state)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{state};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "refresh", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String refresh(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String state)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, state};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "refresh", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO> getAllHasLayer()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO> getAllHasLayer(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO> getAllHasLayerRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO> getAllHasLayerRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO addHasLayer(dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO child)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO addHasLayer(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardLayerDTO child)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public void removeHasLayer(dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHasLayer(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHasLayer()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public static void removeAllHasLayer(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerDTO.CLASS);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO) dto;
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
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardMapQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardMapQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.dashboard.DashboardMapDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
