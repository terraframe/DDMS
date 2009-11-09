package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 257675022)
public abstract class IndividualIPTDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualIPT";
  private static final long serialVersionUID = 257675022;
  
  protected IndividualIPTDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected IndividualIPTDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ADMINISTRATORNAME = "administratorName";
  public static java.lang.String ADMINISTRATORSURNAME = "administratorSurname";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DOSENUMBER = "doseNumber";
  public static java.lang.String DOSETYPE = "doseType";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String ID = "id";
  public static java.lang.String IPTCASE = "iptCase";
  public static java.lang.String ISANCVISIT = "isANCVisit";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NUMBEROFRECIEVEDITNS = "numberOfRecievedITNs";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENTTYPE = "patientType";
  public static java.lang.String RECIEVEDITN = "recievedITN";
  public static java.lang.String RECIEVEDSUPPLEMENT = "recievedSupplement";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SERVICEDATE = "serviceDate";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String VISITNUMBER = "visitNumber";
  public String getAdministratorName()
  {
    return getValue(ADMINISTRATORNAME);
  }
  
  public void setAdministratorName(String value)
  {
    if(value == null)
    {
      setValue(ADMINISTRATORNAME, "");
    }
    else
    {
      setValue(ADMINISTRATORNAME, value);
    }
  }
  
  public boolean isAdministratorNameWritable()
  {
    return isWritable(ADMINISTRATORNAME);
  }
  
  public boolean isAdministratorNameReadable()
  {
    return isReadable(ADMINISTRATORNAME);
  }
  
  public boolean isAdministratorNameModified()
  {
    return isModified(ADMINISTRATORNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAdministratorNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ADMINISTRATORNAME).getAttributeMdDTO();
  }
  
  public String getAdministratorSurname()
  {
    return getValue(ADMINISTRATORSURNAME);
  }
  
  public void setAdministratorSurname(String value)
  {
    if(value == null)
    {
      setValue(ADMINISTRATORSURNAME, "");
    }
    else
    {
      setValue(ADMINISTRATORSURNAME, value);
    }
  }
  
  public boolean isAdministratorSurnameWritable()
  {
    return isWritable(ADMINISTRATORSURNAME);
  }
  
  public boolean isAdministratorSurnameReadable()
  {
    return isReadable(ADMINISTRATORSURNAME);
  }
  
  public boolean isAdministratorSurnameModified()
  {
    return isModified(ADMINISTRATORSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAdministratorSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ADMINISTRATORSURNAME).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getDoseNumber()
  {
    if(getValue(DOSENUMBER) == null || getValue(DOSENUMBER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DOSENUMBER));
    }
  }
  
  public void setDoseNumber(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DOSENUMBER, "");
    }
    else
    {
      setValue(DOSENUMBER, value.getId());
    }
  }
  
  public boolean isDoseNumberWritable()
  {
    return isWritable(DOSENUMBER);
  }
  
  public boolean isDoseNumberReadable()
  {
    return isReadable(DOSENUMBER);
  }
  
  public boolean isDoseNumberModified()
  {
    return isModified(DOSENUMBER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDoseNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DOSENUMBER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDoseType()
  {
    if(getValue(DOSETYPE) == null || getValue(DOSETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DOSETYPE));
    }
  }
  
  public void setDoseType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DOSETYPE, "");
    }
    else
    {
      setValue(DOSETYPE, value.getId());
    }
  }
  
  public boolean isDoseTypeWritable()
  {
    return isWritable(DOSETYPE);
  }
  
  public boolean isDoseTypeReadable()
  {
    return isReadable(DOSETYPE);
  }
  
  public boolean isDoseTypeModified()
  {
    return isModified(DOSETYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDoseTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DOSETYPE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.geo.generated.HealthFacilityDTO getFacility()
  {
    if(getValue(FACILITY) == null || getValue(FACILITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.HealthFacilityDTO.get(getRequest(), getValue(FACILITY));
    }
  }
  
  public void setFacility(dss.vector.solutions.geo.generated.HealthFacilityDTO value)
  {
    if(value == null)
    {
      setValue(FACILITY, "");
    }
    else
    {
      setValue(FACILITY, value.getId());
    }
  }
  
  public boolean isFacilityWritable()
  {
    return isWritable(FACILITY);
  }
  
  public boolean isFacilityReadable()
  {
    return isReadable(FACILITY);
  }
  
  public boolean isFacilityModified()
  {
    return isModified(FACILITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFacilityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO getIptCase()
  {
    if(getValue(IPTCASE) == null || getValue(IPTCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO.get(getRequest(), getValue(IPTCASE));
    }
  }
  
  public void setIptCase(dss.vector.solutions.intervention.monitor.IndividualIPTCaseDTO value)
  {
    if(value == null)
    {
      setValue(IPTCASE, "");
    }
    else
    {
      setValue(IPTCASE, value.getId());
    }
  }
  
  public boolean isIptCaseWritable()
  {
    return isWritable(IPTCASE);
  }
  
  public boolean isIptCaseReadable()
  {
    return isReadable(IPTCASE);
  }
  
  public boolean isIptCaseModified()
  {
    return isModified(IPTCASE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIptCaseMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IPTCASE).getAttributeMdDTO();
  }
  
  public Boolean getIsANCVisit()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISANCVISIT));
  }
  
  public void setIsANCVisit(Boolean value)
  {
    if(value == null)
    {
      setValue(ISANCVISIT, "");
    }
    else
    {
      setValue(ISANCVISIT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsANCVisitWritable()
  {
    return isWritable(ISANCVISIT);
  }
  
  public boolean isIsANCVisitReadable()
  {
    return isReadable(ISANCVISIT);
  }
  
  public boolean isIsANCVisitModified()
  {
    return isModified(ISANCVISIT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsANCVisitMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISANCVISIT).getAttributeMdDTO();
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
  
  public Integer getNumberOfRecievedITNs()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFRECIEVEDITNS));
  }
  
  public void setNumberOfRecievedITNs(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFRECIEVEDITNS, "");
    }
    else
    {
      setValue(NUMBEROFRECIEVEDITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfRecievedITNsWritable()
  {
    return isWritable(NUMBEROFRECIEVEDITNS);
  }
  
  public boolean isNumberOfRecievedITNsReadable()
  {
    return isReadable(NUMBEROFRECIEVEDITNS);
  }
  
  public boolean isNumberOfRecievedITNsModified()
  {
    return isModified(NUMBEROFRECIEVEDITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberOfRecievedITNsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFRECIEVEDITNS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getPatientType()
  {
    if(getValue(PATIENTTYPE) == null || getValue(PATIENTTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PATIENTTYPE));
    }
  }
  
  public void setPatientType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PATIENTTYPE, "");
    }
    else
    {
      setValue(PATIENTTYPE, value.getId());
    }
  }
  
  public boolean isPatientTypeWritable()
  {
    return isWritable(PATIENTTYPE);
  }
  
  public boolean isPatientTypeReadable()
  {
    return isReadable(PATIENTTYPE);
  }
  
  public boolean isPatientTypeModified()
  {
    return isModified(PATIENTTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPatientTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENTTYPE).getAttributeMdDTO();
  }
  
  public Boolean getRecievedITN()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECIEVEDITN));
  }
  
  public void setRecievedITN(Boolean value)
  {
    if(value == null)
    {
      setValue(RECIEVEDITN, "");
    }
    else
    {
      setValue(RECIEVEDITN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRecievedITNWritable()
  {
    return isWritable(RECIEVEDITN);
  }
  
  public boolean isRecievedITNReadable()
  {
    return isReadable(RECIEVEDITN);
  }
  
  public boolean isRecievedITNModified()
  {
    return isModified(RECIEVEDITN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getRecievedITNMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RECIEVEDITN).getAttributeMdDTO();
  }
  
  public Boolean getRecievedSupplement()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECIEVEDSUPPLEMENT));
  }
  
  public void setRecievedSupplement(Boolean value)
  {
    if(value == null)
    {
      setValue(RECIEVEDSUPPLEMENT, "");
    }
    else
    {
      setValue(RECIEVEDSUPPLEMENT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRecievedSupplementWritable()
  {
    return isWritable(RECIEVEDSUPPLEMENT);
  }
  
  public boolean isRecievedSupplementReadable()
  {
    return isReadable(RECIEVEDSUPPLEMENT);
  }
  
  public boolean isRecievedSupplementModified()
  {
    return isModified(RECIEVEDSUPPLEMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getRecievedSupplementMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RECIEVEDSUPPLEMENT).getAttributeMdDTO();
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
  
  public java.util.Date getServiceDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SERVICEDATE));
  }
  
  public void setServiceDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SERVICEDATE, "");
    }
    else
    {
      setValue(SERVICEDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isServiceDateWritable()
  {
    return isWritable(SERVICEDATE);
  }
  
  public boolean isServiceDateReadable()
  {
    return isReadable(SERVICEDATE);
  }
  
  public boolean isServiceDateModified()
  {
    return isModified(SERVICEDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getServiceDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SERVICEDATE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getVisitNumber()
  {
    if(getValue(VISITNUMBER) == null || getValue(VISITNUMBER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(VISITNUMBER));
    }
  }
  
  public void setVisitNumber(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(VISITNUMBER, "");
    }
    else
    {
      setValue(VISITNUMBER, value.getId());
    }
  }
  
  public boolean isVisitNumberWritable()
  {
    return isWritable(VISITNUMBER);
  }
  
  public boolean isVisitNumberReadable()
  {
    return isReadable(VISITNUMBER);
  }
  
  public boolean isVisitNumberModified()
  {
    return isModified(VISITNUMBER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getVisitNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(VISITNUMBER).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualIPTDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.IndividualIPTDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.IndividualIPTQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.IndividualIPTQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.intervention.monitor.IndividualIPT", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualIPTDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualIPTDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualIPTDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualIPTDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
