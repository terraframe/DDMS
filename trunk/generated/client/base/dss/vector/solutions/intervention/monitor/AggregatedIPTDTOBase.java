package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 369462438)
public abstract class AggregatedIPTDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.AggregatedIPT";
  private static final long serialVersionUID = 369462438;
  
  protected AggregatedIPTDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AggregatedIPTDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NUMBERNATALCARE = "numberNatalCare";
  public static java.lang.String NUMBERPREGNANT = "numberPregnant";
  public static java.lang.String NUMBERPREGNANTITN = "numberPregnantITN";
  public static java.lang.String NUMBERPREGNANTIRON = "numberPregnantIron";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TOTALITN = "totalITN";
  public static java.lang.String TYPE = "type";
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
  
  public java.util.Date getEndDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEndDateWritable()
  {
    return isWritable(ENDDATE);
  }
  
  public boolean isEndDateReadable()
  {
    return isReadable(ENDDATE);
  }
  
  public boolean isEndDateModified()
  {
    return isModified(ENDDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
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
  
  public Integer getNumberNatalCare()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERNATALCARE));
  }
  
  public void setNumberNatalCare(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERNATALCARE, "");
    }
    else
    {
      setValue(NUMBERNATALCARE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberNatalCareWritable()
  {
    return isWritable(NUMBERNATALCARE);
  }
  
  public boolean isNumberNatalCareReadable()
  {
    return isReadable(NUMBERNATALCARE);
  }
  
  public boolean isNumberNatalCareModified()
  {
    return isModified(NUMBERNATALCARE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberNatalCareMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERNATALCARE).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANT));
  }
  
  public void setNumberPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANT, "");
    }
    else
    {
      setValue(NUMBERPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantWritable()
  {
    return isWritable(NUMBERPREGNANT);
  }
  
  public boolean isNumberPregnantReadable()
  {
    return isReadable(NUMBERPREGNANT);
  }
  
  public boolean isNumberPregnantModified()
  {
    return isModified(NUMBERPREGNANT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANT).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantITN()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTITN));
  }
  
  public void setNumberPregnantITN(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTITN, "");
    }
    else
    {
      setValue(NUMBERPREGNANTITN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantITNWritable()
  {
    return isWritable(NUMBERPREGNANTITN);
  }
  
  public boolean isNumberPregnantITNReadable()
  {
    return isReadable(NUMBERPREGNANTITN);
  }
  
  public boolean isNumberPregnantITNModified()
  {
    return isModified(NUMBERPREGNANTITN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberPregnantITNMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTITN).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantIron()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTIRON));
  }
  
  public void setNumberPregnantIron(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTIRON, "");
    }
    else
    {
      setValue(NUMBERPREGNANTIRON, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantIronWritable()
  {
    return isWritable(NUMBERPREGNANTIRON);
  }
  
  public boolean isNumberPregnantIronReadable()
  {
    return isReadable(NUMBERPREGNANTIRON);
  }
  
  public boolean isNumberPregnantIronModified()
  {
    return isModified(NUMBERPREGNANTIRON);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberPregnantIronMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTIRON).getAttributeMdDTO();
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
  
  public java.util.Date getStartDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isStartDateWritable()
  {
    return isWritable(STARTDATE);
  }
  
  public boolean isStartDateReadable()
  {
    return isReadable(STARTDATE);
  }
  
  public boolean isStartDateModified()
  {
    return isModified(STARTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public Integer getTotalITN()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTALITN));
  }
  
  public void setTotalITN(Integer value)
  {
    if(value == null)
    {
      setValue(TOTALITN, "");
    }
    else
    {
      setValue(TOTALITN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTotalITNWritable()
  {
    return isWritable(TOTALITN);
  }
  
  public boolean isTotalITNReadable()
  {
    return isReadable(TOTALITN);
  }
  
  public boolean isTotalITNModified()
  {
    return isModified(TOTALITN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTotalITNMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTALITN).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO searchByGeoEntityAndEpiDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, dss.vector.solutions.surveillance.PeriodTypeDTO periodType, java.lang.Integer period, java.lang.Integer year)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.generated.GeoEntity", "dss.vector.solutions.surveillance.PeriodType", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{geoEntity, periodType, period, year};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO.CLASS, "searchByGeoEntityAndEpiDate", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllANCVisits()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllANCVisits(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO> getAllANCVisitsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO> getAllANCVisitsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTANCVisitDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTANCVisitDTO addANCVisits(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.IPTANCVisitDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTANCVisitDTO addANCVisits(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.IPTANCVisitDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public void removeANCVisits(dss.vector.solutions.intervention.monitor.IPTANCVisitDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeANCVisits(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllANCVisits()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  public static void removeAllANCVisits(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.IPTANCVisitDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllDoses()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllDoses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO> getAllDosesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO> getAllDosesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTDoseDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTDoseDTO addDoses(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.IPTDoseDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTDoseDTO addDoses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.IPTDoseDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public void removeDoses(dss.vector.solutions.intervention.monitor.IPTDoseDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeDoses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTDoseDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllDoses()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  public static void removeAllDoses(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.IPTDoseDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllPatients()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllPatients(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO> getAllPatientsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO> getAllPatientsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTPatientsDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTPatientsDTO addPatients(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.IPTPatientsDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTPatientsDTO addPatients(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.IPTPatientsDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public void removePatients(dss.vector.solutions.intervention.monitor.IPTPatientsDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removePatients(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTPatientsDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllPatients()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  public static void removeAllPatients(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.IPTPatientsDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTreatments()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO> getAllTreatmentsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO> getAllTreatmentsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.IPTTreatmentDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTTreatmentDTO addTreatments(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.IPTTreatmentDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IPTTreatmentDTO addTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.IPTTreatmentDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public void removeTreatments(dss.vector.solutions.intervention.monitor.IPTTreatmentDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTreatments()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public static void removeAllTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.IPTTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.AggregatedIPTDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.AggregatedIPTQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.intervention.monitor.AggregatedIPT", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.AggregatedIPTDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.AggregatedIPTDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedIPTDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedIPTDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
