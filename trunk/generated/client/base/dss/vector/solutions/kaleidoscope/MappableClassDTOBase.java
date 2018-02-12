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
package dss.vector.solutions.kaleidoscope;

@com.runwaysdk.business.ClassSignature(hash = -1974570780)
public abstract class MappableClassDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.MappableClass";
  private static final long serialVersionUID = -1974570780;
  
  protected MappableClassDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MappableClassDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DATASOURCE = "dataSource";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String REMOVABLE = "removable";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WRAPPEDMDCLASS = "wrappedMdClass";
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
  
  public String getDataSource()
  {
    return getValue(DATASOURCE);
  }
  
  public void setDataSource(String value)
  {
    if(value == null)
    {
      setValue(DATASOURCE, "");
    }
    else
    {
      setValue(DATASOURCE, value);
    }
  }
  
  public boolean isDataSourceWritable()
  {
    return isWritable(DATASOURCE);
  }
  
  public boolean isDataSourceReadable()
  {
    return isReadable(DATASOURCE);
  }
  
  public boolean isDataSourceModified()
  {
    return isModified(DATASOURCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getDataSourceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(DATASOURCE).getAttributeMdDTO();
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
  
  public Boolean getRemovable()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(REMOVABLE));
  }
  
  public void setRemovable(Boolean value)
  {
    if(value == null)
    {
      setValue(REMOVABLE, "");
    }
    else
    {
      setValue(REMOVABLE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRemovableWritable()
  {
    return isWritable(REMOVABLE);
  }
  
  public boolean isRemovableReadable()
  {
    return isReadable(REMOVABLE);
  }
  
  public boolean isRemovableModified()
  {
    return isModified(REMOVABLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getRemovableMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(REMOVABLE).getAttributeMdDTO();
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
  
  public com.runwaysdk.system.metadata.MdClassDTO getWrappedMdClass()
  {
    if(getValue(WRAPPEDMDCLASS) == null || getValue(WRAPPEDMDCLASS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdClassDTO.get(getRequest(), getValue(WRAPPEDMDCLASS));
    }
  }
  
  public String getWrappedMdClassId()
  {
    return getValue(WRAPPEDMDCLASS);
  }
  
  public void setWrappedMdClass(com.runwaysdk.system.metadata.MdClassDTO value)
  {
    if(value == null)
    {
      setValue(WRAPPEDMDCLASS, "");
    }
    else
    {
      setValue(WRAPPEDMDCLASS, value.getId());
    }
  }
  
  public boolean isWrappedMdClassWritable()
  {
    return isWritable(WRAPPEDMDCLASS);
  }
  
  public boolean isWrappedMdClassReadable()
  {
    return isReadable(WRAPPEDMDCLASS);
  }
  
  public boolean isWrappedMdClassModified()
  {
    return isModified(WRAPPEDMDCLASS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWrappedMdClassMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WRAPPEDMDCLASS).getAttributeMdDTO();
  }
  
  public static final java.lang.String addIndicator(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String datasetId, java.lang.String indicator)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{datasetId, indicator};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "addIndicator", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void applyDatasetUpdate(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String dataset)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{dataset};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "applyDatasetUpdate", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void edit(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "edit", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getAllAsJSON(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "getAllAsJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getAsJSON()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "getAsJSON", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getAsJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "getAsJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getAttributesAsJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String dashboardId, java.lang.String mdClassId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{dashboardId, mdClassId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "getAttributesAsJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String getClassesAsJSON(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String dashboardId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{dashboardId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "getClassesAsJSON", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String lockIndicator(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "lockIndicator", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void remove(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "remove", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void removeIndicator(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "removeIndicator", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO> getAllGeoNode()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO> getAllGeoNode(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO> getAllGeoNodeRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO> getAllGeoNodeRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO.CLASS);
  }
  
  public dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO addGeoNode(dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO child)
  {
    return (dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO.CLASS);
  }
  
  public static dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO addGeoNode(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.kaleidoscope.geo.GeoNodeDTO child)
  {
    return (dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO.CLASS);
  }
  
  public void removeGeoNode(dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeGeoNode(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllGeoNode()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO.CLASS);
  }
  
  public static void removeAllGeoNode(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.kaleidoscope.MappableClassGeoNodeDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO> getAllUniversal()
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.kaleidoscope.ClassUniversalDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO> getAllUniversal(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.geo.GeoHierarchyDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.kaleidoscope.ClassUniversalDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.kaleidoscope.ClassUniversalDTO> getAllUniversalRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.ClassUniversalDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.kaleidoscope.ClassUniversalDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.kaleidoscope.ClassUniversalDTO> getAllUniversalRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.kaleidoscope.ClassUniversalDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.kaleidoscope.ClassUniversalDTO.CLASS);
  }
  
  public dss.vector.solutions.kaleidoscope.ClassUniversalDTO addUniversal(dss.vector.solutions.geo.GeoHierarchyDTO child)
  {
    return (dss.vector.solutions.kaleidoscope.ClassUniversalDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.kaleidoscope.ClassUniversalDTO.CLASS);
  }
  
  public static dss.vector.solutions.kaleidoscope.ClassUniversalDTO addUniversal(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.geo.GeoHierarchyDTO child)
  {
    return (dss.vector.solutions.kaleidoscope.ClassUniversalDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.kaleidoscope.ClassUniversalDTO.CLASS);
  }
  
  public void removeUniversal(dss.vector.solutions.kaleidoscope.ClassUniversalDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeUniversal(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.kaleidoscope.ClassUniversalDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllUniversal()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.kaleidoscope.ClassUniversalDTO.CLASS);
  }
  
  public static void removeAllUniversal(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.kaleidoscope.ClassUniversalDTO.CLASS);
  }
  
  public static dss.vector.solutions.kaleidoscope.MappableClassDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.kaleidoscope.MappableClassDTO) dto;
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
  
  public static dss.vector.solutions.kaleidoscope.MappableClassQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.MappableClassQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.MappableClassDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.MappableClassDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.MappableClassDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.MappableClassDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.MappableClassDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
