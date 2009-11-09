package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -2001154443)
public abstract class ITNHouseholdSurveyDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey";
  private static final long serialVersionUID = -2001154443;
  
  protected ITNHouseholdSurveyDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ITNHouseholdSurveyDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGENTFIRSTNAME = "agentFirstName";
  public static java.lang.String AGENTSURNAME = "agentSurname";
  public static java.lang.String BOUGHTPROVIDER = "boughtProvider";
  public static java.lang.String CHILDRESIDENTS = "childResidents";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DAMAGEDITNS = "damagedItns";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FREEPROVIDER = "freeProvider";
  public static java.lang.String HANGINGITNS = "hangingItns";
  public static java.lang.String ID = "id";
  public static java.lang.String ITNS = "itns";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MONTHRECEIVED = "monthReceived";
  public static java.lang.String NETSOBTAINED = "netsObtained";
  public static java.lang.String OTHERITNS = "otherItns";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PREGNANTRESIDENTS = "pregnantResidents";
  public static java.lang.String QUESTIONNAIRENUMBER = "questionnaireNumber";
  public static java.lang.String RESIDENTS = "residents";
  public static java.lang.String RETREATED = "retreated";
  public static java.lang.String RETREATMENTPERIOD = "retreatmentPeriod";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String SURVEYLOCATION = "surveyLocation";
  public static java.lang.String TYPE = "type";
  public static java.lang.String USEDEVERYNIGHT = "usedEveryNight";
  public static java.lang.String USEDITNS = "usedItns";
  public static java.lang.String WASHFREQUENCY = "washFrequency";
  public static java.lang.String WASHINTERVAL = "washInterval";
  public static java.lang.String WASHED = "washed";
  public static java.lang.String YEARRECEIVED = "yearReceived";
  public String getAgentFirstName()
  {
    return getValue(AGENTFIRSTNAME);
  }
  
  public void setAgentFirstName(String value)
  {
    if(value == null)
    {
      setValue(AGENTFIRSTNAME, "");
    }
    else
    {
      setValue(AGENTFIRSTNAME, value);
    }
  }
  
  public boolean isAgentFirstNameWritable()
  {
    return isWritable(AGENTFIRSTNAME);
  }
  
  public boolean isAgentFirstNameReadable()
  {
    return isReadable(AGENTFIRSTNAME);
  }
  
  public boolean isAgentFirstNameModified()
  {
    return isModified(AGENTFIRSTNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAgentFirstNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AGENTFIRSTNAME).getAttributeMdDTO();
  }
  
  public String getAgentSurname()
  {
    return getValue(AGENTSURNAME);
  }
  
  public void setAgentSurname(String value)
  {
    if(value == null)
    {
      setValue(AGENTSURNAME, "");
    }
    else
    {
      setValue(AGENTSURNAME, value);
    }
  }
  
  public boolean isAgentSurnameWritable()
  {
    return isWritable(AGENTSURNAME);
  }
  
  public boolean isAgentSurnameReadable()
  {
    return isReadable(AGENTSURNAME);
  }
  
  public boolean isAgentSurnameModified()
  {
    return isModified(AGENTSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAgentSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AGENTSURNAME).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getBoughtProvider()
  {
    if(getValue(BOUGHTPROVIDER) == null || getValue(BOUGHTPROVIDER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(BOUGHTPROVIDER));
    }
  }
  
  public void setBoughtProvider(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(BOUGHTPROVIDER, "");
    }
    else
    {
      setValue(BOUGHTPROVIDER, value.getId());
    }
  }
  
  public boolean isBoughtProviderWritable()
  {
    return isWritable(BOUGHTPROVIDER);
  }
  
  public boolean isBoughtProviderReadable()
  {
    return isReadable(BOUGHTPROVIDER);
  }
  
  public boolean isBoughtProviderModified()
  {
    return isModified(BOUGHTPROVIDER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getBoughtProviderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BOUGHTPROVIDER).getAttributeMdDTO();
  }
  
  public Integer getChildResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CHILDRESIDENTS));
  }
  
  public void setChildResidents(Integer value)
  {
    if(value == null)
    {
      setValue(CHILDRESIDENTS, "");
    }
    else
    {
      setValue(CHILDRESIDENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isChildResidentsWritable()
  {
    return isWritable(CHILDRESIDENTS);
  }
  
  public boolean isChildResidentsReadable()
  {
    return isReadable(CHILDRESIDENTS);
  }
  
  public boolean isChildResidentsModified()
  {
    return isModified(CHILDRESIDENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getChildResidentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CHILDRESIDENTS).getAttributeMdDTO();
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
  
  public Integer getDamagedItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DAMAGEDITNS));
  }
  
  public void setDamagedItns(Integer value)
  {
    if(value == null)
    {
      setValue(DAMAGEDITNS, "");
    }
    else
    {
      setValue(DAMAGEDITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDamagedItnsWritable()
  {
    return isWritable(DAMAGEDITNS);
  }
  
  public boolean isDamagedItnsReadable()
  {
    return isReadable(DAMAGEDITNS);
  }
  
  public boolean isDamagedItnsModified()
  {
    return isModified(DAMAGEDITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDamagedItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DAMAGEDITNS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getFreeProvider()
  {
    if(getValue(FREEPROVIDER) == null || getValue(FREEPROVIDER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(FREEPROVIDER));
    }
  }
  
  public void setFreeProvider(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(FREEPROVIDER, "");
    }
    else
    {
      setValue(FREEPROVIDER, value.getId());
    }
  }
  
  public boolean isFreeProviderWritable()
  {
    return isWritable(FREEPROVIDER);
  }
  
  public boolean isFreeProviderReadable()
  {
    return isReadable(FREEPROVIDER);
  }
  
  public boolean isFreeProviderModified()
  {
    return isModified(FREEPROVIDER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFreeProviderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FREEPROVIDER).getAttributeMdDTO();
  }
  
  public Integer getHangingItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HANGINGITNS));
  }
  
  public void setHangingItns(Integer value)
  {
    if(value == null)
    {
      setValue(HANGINGITNS, "");
    }
    else
    {
      setValue(HANGINGITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isHangingItnsWritable()
  {
    return isWritable(HANGINGITNS);
  }
  
  public boolean isHangingItnsReadable()
  {
    return isReadable(HANGINGITNS);
  }
  
  public boolean isHangingItnsModified()
  {
    return isModified(HANGINGITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getHangingItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HANGINGITNS).getAttributeMdDTO();
  }
  
  public Integer getItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ITNS));
  }
  
  public void setItns(Integer value)
  {
    if(value == null)
    {
      setValue(ITNS, "");
    }
    else
    {
      setValue(ITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isItnsWritable()
  {
    return isWritable(ITNS);
  }
  
  public boolean isItnsReadable()
  {
    return isReadable(ITNS);
  }
  
  public boolean isItnsModified()
  {
    return isModified(ITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ITNS).getAttributeMdDTO();
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
  
  public Integer getMonthReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MONTHRECEIVED));
  }
  
  public void setMonthReceived(Integer value)
  {
    if(value == null)
    {
      setValue(MONTHRECEIVED, "");
    }
    else
    {
      setValue(MONTHRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMonthReceivedWritable()
  {
    return isWritable(MONTHRECEIVED);
  }
  
  public boolean isMonthReceivedReadable()
  {
    return isReadable(MONTHRECEIVED);
  }
  
  public boolean isMonthReceivedModified()
  {
    return isModified(MONTHRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMonthReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MONTHRECEIVED).getAttributeMdDTO();
  }
  
  public Boolean getNetsObtained()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NETSOBTAINED));
  }
  
  public void setNetsObtained(Boolean value)
  {
    if(value == null)
    {
      setValue(NETSOBTAINED, "");
    }
    else
    {
      setValue(NETSOBTAINED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNetsObtainedWritable()
  {
    return isWritable(NETSOBTAINED);
  }
  
  public boolean isNetsObtainedReadable()
  {
    return isReadable(NETSOBTAINED);
  }
  
  public boolean isNetsObtainedModified()
  {
    return isModified(NETSOBTAINED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getNetsObtainedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NETSOBTAINED).getAttributeMdDTO();
  }
  
  public Integer getOtherItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHERITNS));
  }
  
  public void setOtherItns(Integer value)
  {
    if(value == null)
    {
      setValue(OTHERITNS, "");
    }
    else
    {
      setValue(OTHERITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOtherItnsWritable()
  {
    return isWritable(OTHERITNS);
  }
  
  public boolean isOtherItnsReadable()
  {
    return isReadable(OTHERITNS);
  }
  
  public boolean isOtherItnsModified()
  {
    return isModified(OTHERITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOtherItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OTHERITNS).getAttributeMdDTO();
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
  
  public Integer getPregnantResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTRESIDENTS));
  }
  
  public void setPregnantResidents(Integer value)
  {
    if(value == null)
    {
      setValue(PREGNANTRESIDENTS, "");
    }
    else
    {
      setValue(PREGNANTRESIDENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPregnantResidentsWritable()
  {
    return isWritable(PREGNANTRESIDENTS);
  }
  
  public boolean isPregnantResidentsReadable()
  {
    return isReadable(PREGNANTRESIDENTS);
  }
  
  public boolean isPregnantResidentsModified()
  {
    return isModified(PREGNANTRESIDENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPregnantResidentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREGNANTRESIDENTS).getAttributeMdDTO();
  }
  
  public String getQuestionnaireNumber()
  {
    return getValue(QUESTIONNAIRENUMBER);
  }
  
  public void setQuestionnaireNumber(String value)
  {
    if(value == null)
    {
      setValue(QUESTIONNAIRENUMBER, "");
    }
    else
    {
      setValue(QUESTIONNAIRENUMBER, value);
    }
  }
  
  public boolean isQuestionnaireNumberWritable()
  {
    return isWritable(QUESTIONNAIRENUMBER);
  }
  
  public boolean isQuestionnaireNumberReadable()
  {
    return isReadable(QUESTIONNAIRENUMBER);
  }
  
  public boolean isQuestionnaireNumberModified()
  {
    return isModified(QUESTIONNAIRENUMBER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getQuestionnaireNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUESTIONNAIRENUMBER).getAttributeMdDTO();
  }
  
  public Integer getResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RESIDENTS));
  }
  
  public void setResidents(Integer value)
  {
    if(value == null)
    {
      setValue(RESIDENTS, "");
    }
    else
    {
      setValue(RESIDENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isResidentsWritable()
  {
    return isWritable(RESIDENTS);
  }
  
  public boolean isResidentsReadable()
  {
    return isReadable(RESIDENTS);
  }
  
  public boolean isResidentsModified()
  {
    return isModified(RESIDENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getResidentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RESIDENTS).getAttributeMdDTO();
  }
  
  public Boolean getRetreated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETREATED));
  }
  
  public void setRetreated(Boolean value)
  {
    if(value == null)
    {
      setValue(RETREATED, "");
    }
    else
    {
      setValue(RETREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRetreatedWritable()
  {
    return isWritable(RETREATED);
  }
  
  public boolean isRetreatedReadable()
  {
    return isReadable(RETREATED);
  }
  
  public boolean isRetreatedModified()
  {
    return isModified(RETREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getRetreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETREATED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getRetreatmentPeriod()
  {
    if(getValue(RETREATMENTPERIOD) == null || getValue(RETREATMENTPERIOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(RETREATMENTPERIOD));
    }
  }
  
  public void setRetreatmentPeriod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(RETREATMENTPERIOD, "");
    }
    else
    {
      setValue(RETREATMENTPERIOD, value.getId());
    }
  }
  
  public boolean isRetreatmentPeriodWritable()
  {
    return isWritable(RETREATMENTPERIOD);
  }
  
  public boolean isRetreatmentPeriodReadable()
  {
    return isReadable(RETREATMENTPERIOD);
  }
  
  public boolean isRetreatmentPeriodModified()
  {
    return isModified(RETREATMENTPERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getRetreatmentPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RETREATMENTPERIOD).getAttributeMdDTO();
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
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getSurveyLocation()
  {
    if(getValue(SURVEYLOCATION) == null || getValue(SURVEYLOCATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(SURVEYLOCATION));
    }
  }
  
  public void setSurveyLocation(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(SURVEYLOCATION, "");
    }
    else
    {
      setValue(SURVEYLOCATION, value.getId());
    }
  }
  
  public boolean isSurveyLocationWritable()
  {
    return isWritable(SURVEYLOCATION);
  }
  
  public boolean isSurveyLocationReadable()
  {
    return isReadable(SURVEYLOCATION);
  }
  
  public boolean isSurveyLocationModified()
  {
    return isModified(SURVEYLOCATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSurveyLocationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SURVEYLOCATION).getAttributeMdDTO();
  }
  
  public Boolean getUsedEveryNight()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(USEDEVERYNIGHT));
  }
  
  public void setUsedEveryNight(Boolean value)
  {
    if(value == null)
    {
      setValue(USEDEVERYNIGHT, "");
    }
    else
    {
      setValue(USEDEVERYNIGHT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isUsedEveryNightWritable()
  {
    return isWritable(USEDEVERYNIGHT);
  }
  
  public boolean isUsedEveryNightReadable()
  {
    return isReadable(USEDEVERYNIGHT);
  }
  
  public boolean isUsedEveryNightModified()
  {
    return isModified(USEDEVERYNIGHT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getUsedEveryNightMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(USEDEVERYNIGHT).getAttributeMdDTO();
  }
  
  public Integer getUsedItns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USEDITNS));
  }
  
  public void setUsedItns(Integer value)
  {
    if(value == null)
    {
      setValue(USEDITNS, "");
    }
    else
    {
      setValue(USEDITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUsedItnsWritable()
  {
    return isWritable(USEDITNS);
  }
  
  public boolean isUsedItnsReadable()
  {
    return isReadable(USEDITNS);
  }
  
  public boolean isUsedItnsModified()
  {
    return isModified(USEDITNS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getUsedItnsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(USEDITNS).getAttributeMdDTO();
  }
  
  public Integer getWashFrequency()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WASHFREQUENCY));
  }
  
  public void setWashFrequency(Integer value)
  {
    if(value == null)
    {
      setValue(WASHFREQUENCY, "");
    }
    else
    {
      setValue(WASHFREQUENCY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWashFrequencyWritable()
  {
    return isWritable(WASHFREQUENCY);
  }
  
  public boolean isWashFrequencyReadable()
  {
    return isReadable(WASHFREQUENCY);
  }
  
  public boolean isWashFrequencyModified()
  {
    return isModified(WASHFREQUENCY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getWashFrequencyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WASHFREQUENCY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWashInterval()
  {
    if(getValue(WASHINTERVAL) == null || getValue(WASHINTERVAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WASHINTERVAL));
    }
  }
  
  public void setWashInterval(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WASHINTERVAL, "");
    }
    else
    {
      setValue(WASHINTERVAL, value.getId());
    }
  }
  
  public boolean isWashIntervalWritable()
  {
    return isWritable(WASHINTERVAL);
  }
  
  public boolean isWashIntervalReadable()
  {
    return isReadable(WASHINTERVAL);
  }
  
  public boolean isWashIntervalModified()
  {
    return isModified(WASHINTERVAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWashIntervalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WASHINTERVAL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getWashed()
  {
    if(getValue(WASHED) == null || getValue(WASHED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WASHED));
    }
  }
  
  public void setWashed(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WASHED, "");
    }
    else
    {
      setValue(WASHED, value.getId());
    }
  }
  
  public boolean isWashedWritable()
  {
    return isWritable(WASHED);
  }
  
  public boolean isWashedReadable()
  {
    return isReadable(WASHED);
  }
  
  public boolean isWashedModified()
  {
    return isModified(WASHED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWashedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WASHED).getAttributeMdDTO();
  }
  
  public Integer getYearReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRECEIVED));
  }
  
  public void setYearReceived(Integer value)
  {
    if(value == null)
    {
      setValue(YEARRECEIVED, "");
    }
    else
    {
      setValue(YEARRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearReceivedWritable()
  {
    return isWritable(YEARRECEIVED);
  }
  
  public boolean isYearReceivedReadable()
  {
    return isReadable(YEARRECEIVED);
  }
  
  public boolean isYearReceivedModified()
  {
    return isModified(YEARRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRECEIVED).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllNets()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO> getAllNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO> getAllNetsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO addNets(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO addNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  public void removeNets(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllNets()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  public static void removeAllNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllNonUseReasons()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllNonUseReasons(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO> getAllNonUseReasonsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO> getAllNonUseReasonsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO addNonUseReasons(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO addNonUseReasons(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  public void removeNonUseReasons(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeNonUseReasons(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllNonUseReasons()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  public static void removeAllNonUseReasons(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyNonUseReasonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTargetGroups()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO> getAllTargetGroupsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO> getAllTargetGroupsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO addTargetGroups(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO addTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public void removeTargetGroups(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTargetGroups()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public static void removeAllTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
