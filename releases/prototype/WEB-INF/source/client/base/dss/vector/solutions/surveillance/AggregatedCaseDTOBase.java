package dss.vector.solutions.surveillance;

public abstract class AggregatedCaseDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239658631843L;
  
  public final static String CLASS = "dss.vector.solutions.surveillance.AggregatedCase";
  protected AggregatedCaseDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AggregatedCaseDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGEGROUP = "ageGroup";
  public static java.lang.String CASES = "cases";
  public static java.lang.String CASESFEMALE = "casesFemale";
  public static java.lang.String CASESMALE = "casesMale";
  public static java.lang.String CASESPREGNANT = "casesPregnant";
  public static java.lang.String CLINICALLYDIAGNOSED = "clinicallyDiagnosed";
  public static java.lang.String CLINICALLYDIAGNOSEDDEATH = "clinicallyDiagnosedDeath";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DAYSOUTOFSTOCK = "daysOutOfStock";
  public static java.lang.String DEATHS = "deaths";
  public static java.lang.String DEATHSFEMALE = "deathsFemale";
  public static java.lang.String DEATHSMALE = "deathsMale";
  public static java.lang.String DEATHSPREGNANT = "deathsPregnant";
  public static java.lang.String DEFINITIVELYDIAGNOSED = "definitivelyDiagnosed";
  public static java.lang.String DEFINITIVELYDIAGNOSEDDEATH = "definitivelyDiagnosedDeath";
  public static java.lang.String ENDAGE = "endAge";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String INPATIENTS = "inPatients";
  public static java.lang.String INPATIENTSANEMIA = "inPatientsAnemia";
  public static java.lang.String INPATIENTSCLINICALLY = "inPatientsClinically";
  public static java.lang.String INPATIENTSDEFINITIVE = "inPatientsDefinitive";
  public static java.lang.String INPATIENTSDISCHARGED = "inPatientsDischarged";
  public static java.lang.String INPATIENTSFEMALE = "inPatientsFemale";
  public static java.lang.String INPATIENTSMALE = "inPatientsMale";
  public static java.lang.String INPATIENTSNOTTREATED = "inPatientsNotTreated";
  public static java.lang.String INPATIENTSPREGNANTANEMIA = "inPatientsPregnantAnemia";
  public static java.lang.String INPATIENTSPREGNANTDIANOSIS = "inPatientsPregnantDianosis";
  public static java.lang.String INPATIENTSTOTAL = "inPatientsTotal";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OUTPATIENTS = "outPatients";
  public static java.lang.String OUTPATIENTSFEMALE = "outPatientsFemale";
  public static java.lang.String OUTPATIENTSMALE = "outPatientsMale";
  public static java.lang.String OUTPATIENTSNOTTREATED = "outPatientsNotTreated";
  public static java.lang.String OUTPATIENTSTOTAL = "outPatientsTotal";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENTSNOTTREATED = "patientsNotTreated";
  public static java.lang.String PREGNANTDIAGNOSIS = "pregnantDiagnosis";
  public static java.lang.String PREGNANTDIAGNOSISDEATH = "pregnantDiagnosisDeath";
  public static java.lang.String PREGNANTREFERRALSRECEIVED = "pregnantReferralsReceived";
  public static java.lang.String REFERRALSRECEIVED = "referralsReceived";
  public static java.lang.String REFERRALSSENT = "referralsSent";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String STARTAGE = "startAge";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String STILLBIRTHS = "stillBirths";
  public static java.lang.String TYPE = "type";
  public dss.vector.solutions.surveillance.AggregatedAgeGroupDTO getAgeGroup()
  {
    if(getValue(AGEGROUP) == null || getValue(AGEGROUP).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.AggregatedAgeGroupDTO.get(getRequest(), getValue(AGEGROUP));
    }
  }
  
  public void setAgeGroup(dss.vector.solutions.surveillance.AggregatedAgeGroupDTO value)
  {
    setValue(AGEGROUP, value.getId());
  }
  
  public boolean isAgeGroupWritable()
  {
    return isWritable(AGEGROUP);
  }
  
  public boolean isAgeGroupReadable()
  {
    return isReadable(AGEGROUP);
  }
  
  public boolean isAgeGroupModified()
  {
    return isModified(AGEGROUP);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAgeGroupMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(AGEGROUP).getAttributeMdDTO();
  }
  
  public Integer getCases()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASES));
  }
  
  public void setCases(Integer value)
  {
    if(value == null)
    {
      setValue(CASES, "");
    }
    else
    {
      setValue(CASES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCasesWritable()
  {
    return isWritable(CASES);
  }
  
  public boolean isCasesReadable()
  {
    return isReadable(CASES);
  }
  
  public boolean isCasesModified()
  {
    return isModified(CASES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCasesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASES).getAttributeMdDTO();
  }
  
  public Integer getCasesFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESFEMALE));
  }
  
  public void setCasesFemale(Integer value)
  {
    if(value == null)
    {
      setValue(CASESFEMALE, "");
    }
    else
    {
      setValue(CASESFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCasesFemaleWritable()
  {
    return isWritable(CASESFEMALE);
  }
  
  public boolean isCasesFemaleReadable()
  {
    return isReadable(CASESFEMALE);
  }
  
  public boolean isCasesFemaleModified()
  {
    return isModified(CASESFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCasesFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASESFEMALE).getAttributeMdDTO();
  }
  
  public Integer getCasesMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESMALE));
  }
  
  public void setCasesMale(Integer value)
  {
    if(value == null)
    {
      setValue(CASESMALE, "");
    }
    else
    {
      setValue(CASESMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCasesMaleWritable()
  {
    return isWritable(CASESMALE);
  }
  
  public boolean isCasesMaleReadable()
  {
    return isReadable(CASESMALE);
  }
  
  public boolean isCasesMaleModified()
  {
    return isModified(CASESMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCasesMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASESMALE).getAttributeMdDTO();
  }
  
  public Integer getCasesPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CASESPREGNANT));
  }
  
  public void setCasesPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(CASESPREGNANT, "");
    }
    else
    {
      setValue(CASESPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCasesPregnantWritable()
  {
    return isWritable(CASESPREGNANT);
  }
  
  public boolean isCasesPregnantReadable()
  {
    return isReadable(CASESPREGNANT);
  }
  
  public boolean isCasesPregnantModified()
  {
    return isModified(CASESPREGNANT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getCasesPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CASESPREGNANT).getAttributeMdDTO();
  }
  
  public Integer getClinicallyDiagnosed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CLINICALLYDIAGNOSED));
  }
  
  public void setClinicallyDiagnosed(Integer value)
  {
    if(value == null)
    {
      setValue(CLINICALLYDIAGNOSED, "");
    }
    else
    {
      setValue(CLINICALLYDIAGNOSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isClinicallyDiagnosedWritable()
  {
    return isWritable(CLINICALLYDIAGNOSED);
  }
  
  public boolean isClinicallyDiagnosedReadable()
  {
    return isReadable(CLINICALLYDIAGNOSED);
  }
  
  public boolean isClinicallyDiagnosedModified()
  {
    return isModified(CLINICALLYDIAGNOSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getClinicallyDiagnosedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CLINICALLYDIAGNOSED).getAttributeMdDTO();
  }
  
  public Integer getClinicallyDiagnosedDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CLINICALLYDIAGNOSEDDEATH));
  }
  
  public void setClinicallyDiagnosedDeath(Integer value)
  {
    if(value == null)
    {
      setValue(CLINICALLYDIAGNOSEDDEATH, "");
    }
    else
    {
      setValue(CLINICALLYDIAGNOSEDDEATH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isClinicallyDiagnosedDeathWritable()
  {
    return isWritable(CLINICALLYDIAGNOSEDDEATH);
  }
  
  public boolean isClinicallyDiagnosedDeathReadable()
  {
    return isReadable(CLINICALLYDIAGNOSEDDEATH);
  }
  
  public boolean isClinicallyDiagnosedDeathModified()
  {
    return isModified(CLINICALLYDIAGNOSEDDEATH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getClinicallyDiagnosedDeathMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CLINICALLYDIAGNOSEDDEATH).getAttributeMdDTO();
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
  
  public Integer getDaysOutOfStock()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DAYSOUTOFSTOCK));
  }
  
  public void setDaysOutOfStock(Integer value)
  {
    if(value == null)
    {
      setValue(DAYSOUTOFSTOCK, "");
    }
    else
    {
      setValue(DAYSOUTOFSTOCK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDaysOutOfStockWritable()
  {
    return isWritable(DAYSOUTOFSTOCK);
  }
  
  public boolean isDaysOutOfStockReadable()
  {
    return isReadable(DAYSOUTOFSTOCK);
  }
  
  public boolean isDaysOutOfStockModified()
  {
    return isModified(DAYSOUTOFSTOCK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDaysOutOfStockMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DAYSOUTOFSTOCK).getAttributeMdDTO();
  }
  
  public Integer getDeaths()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHS));
  }
  
  public void setDeaths(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHS, "");
    }
    else
    {
      setValue(DEATHS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDeathsWritable()
  {
    return isWritable(DEATHS);
  }
  
  public boolean isDeathsReadable()
  {
    return isReadable(DEATHS);
  }
  
  public boolean isDeathsModified()
  {
    return isModified(DEATHS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDeathsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHS).getAttributeMdDTO();
  }
  
  public Integer getDeathsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSFEMALE));
  }
  
  public void setDeathsFemale(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHSFEMALE, "");
    }
    else
    {
      setValue(DEATHSFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDeathsFemaleWritable()
  {
    return isWritable(DEATHSFEMALE);
  }
  
  public boolean isDeathsFemaleReadable()
  {
    return isReadable(DEATHSFEMALE);
  }
  
  public boolean isDeathsFemaleModified()
  {
    return isModified(DEATHSFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDeathsFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHSFEMALE).getAttributeMdDTO();
  }
  
  public Integer getDeathsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSMALE));
  }
  
  public void setDeathsMale(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHSMALE, "");
    }
    else
    {
      setValue(DEATHSMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDeathsMaleWritable()
  {
    return isWritable(DEATHSMALE);
  }
  
  public boolean isDeathsMaleReadable()
  {
    return isReadable(DEATHSMALE);
  }
  
  public boolean isDeathsMaleModified()
  {
    return isModified(DEATHSMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDeathsMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHSMALE).getAttributeMdDTO();
  }
  
  public Integer getDeathsPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEATHSPREGNANT));
  }
  
  public void setDeathsPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(DEATHSPREGNANT, "");
    }
    else
    {
      setValue(DEATHSPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDeathsPregnantWritable()
  {
    return isWritable(DEATHSPREGNANT);
  }
  
  public boolean isDeathsPregnantReadable()
  {
    return isReadable(DEATHSPREGNANT);
  }
  
  public boolean isDeathsPregnantModified()
  {
    return isModified(DEATHSPREGNANT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDeathsPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEATHSPREGNANT).getAttributeMdDTO();
  }
  
  public Integer getDefinitivelyDiagnosed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEFINITIVELYDIAGNOSED));
  }
  
  public void setDefinitivelyDiagnosed(Integer value)
  {
    if(value == null)
    {
      setValue(DEFINITIVELYDIAGNOSED, "");
    }
    else
    {
      setValue(DEFINITIVELYDIAGNOSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDefinitivelyDiagnosedWritable()
  {
    return isWritable(DEFINITIVELYDIAGNOSED);
  }
  
  public boolean isDefinitivelyDiagnosedReadable()
  {
    return isReadable(DEFINITIVELYDIAGNOSED);
  }
  
  public boolean isDefinitivelyDiagnosedModified()
  {
    return isModified(DEFINITIVELYDIAGNOSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDefinitivelyDiagnosedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEFINITIVELYDIAGNOSED).getAttributeMdDTO();
  }
  
  public Integer getDefinitivelyDiagnosedDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DEFINITIVELYDIAGNOSEDDEATH));
  }
  
  public void setDefinitivelyDiagnosedDeath(Integer value)
  {
    if(value == null)
    {
      setValue(DEFINITIVELYDIAGNOSEDDEATH, "");
    }
    else
    {
      setValue(DEFINITIVELYDIAGNOSEDDEATH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDefinitivelyDiagnosedDeathWritable()
  {
    return isWritable(DEFINITIVELYDIAGNOSEDDEATH);
  }
  
  public boolean isDefinitivelyDiagnosedDeathReadable()
  {
    return isReadable(DEFINITIVELYDIAGNOSEDDEATH);
  }
  
  public boolean isDefinitivelyDiagnosedDeathModified()
  {
    return isModified(DEFINITIVELYDIAGNOSEDDEATH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getDefinitivelyDiagnosedDeathMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DEFINITIVELYDIAGNOSEDDEATH).getAttributeMdDTO();
  }
  
  public Integer getEndAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ENDAGE));
  }
  
  public void setEndAge(Integer value)
  {
    if(value == null)
    {
      setValue(ENDAGE, "");
    }
    else
    {
      setValue(ENDAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEndAgeWritable()
  {
    return isWritable(ENDAGE);
  }
  
  public boolean isEndAgeReadable()
  {
    return isReadable(ENDAGE);
  }
  
  public boolean isEndAgeModified()
  {
    return isModified(ENDAGE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getEndAgeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ENDAGE).getAttributeMdDTO();
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
    setValue(ENTITYDOMAIN, value.getId());
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
    setValue(GEOENTITY, value.getId());
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
  
  public Integer getInPatients()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTS));
  }
  
  public void setInPatients(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTS, "");
    }
    else
    {
      setValue(INPATIENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsWritable()
  {
    return isWritable(INPATIENTS);
  }
  
  public boolean isInPatientsReadable()
  {
    return isReadable(INPATIENTS);
  }
  
  public boolean isInPatientsModified()
  {
    return isModified(INPATIENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTS).getAttributeMdDTO();
  }
  
  public Integer getInPatientsAnemia()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSANEMIA));
  }
  
  public void setInPatientsAnemia(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSANEMIA, "");
    }
    else
    {
      setValue(INPATIENTSANEMIA, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsAnemiaWritable()
  {
    return isWritable(INPATIENTSANEMIA);
  }
  
  public boolean isInPatientsAnemiaReadable()
  {
    return isReadable(INPATIENTSANEMIA);
  }
  
  public boolean isInPatientsAnemiaModified()
  {
    return isModified(INPATIENTSANEMIA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsAnemiaMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSANEMIA).getAttributeMdDTO();
  }
  
  public Integer getInPatientsClinically()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSCLINICALLY));
  }
  
  public void setInPatientsClinically(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSCLINICALLY, "");
    }
    else
    {
      setValue(INPATIENTSCLINICALLY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsClinicallyWritable()
  {
    return isWritable(INPATIENTSCLINICALLY);
  }
  
  public boolean isInPatientsClinicallyReadable()
  {
    return isReadable(INPATIENTSCLINICALLY);
  }
  
  public boolean isInPatientsClinicallyModified()
  {
    return isModified(INPATIENTSCLINICALLY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsClinicallyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSCLINICALLY).getAttributeMdDTO();
  }
  
  public Integer getInPatientsDefinitive()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSDEFINITIVE));
  }
  
  public void setInPatientsDefinitive(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSDEFINITIVE, "");
    }
    else
    {
      setValue(INPATIENTSDEFINITIVE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsDefinitiveWritable()
  {
    return isWritable(INPATIENTSDEFINITIVE);
  }
  
  public boolean isInPatientsDefinitiveReadable()
  {
    return isReadable(INPATIENTSDEFINITIVE);
  }
  
  public boolean isInPatientsDefinitiveModified()
  {
    return isModified(INPATIENTSDEFINITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsDefinitiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSDEFINITIVE).getAttributeMdDTO();
  }
  
  public Integer getInPatientsDischarged()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSDISCHARGED));
  }
  
  public void setInPatientsDischarged(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSDISCHARGED, "");
    }
    else
    {
      setValue(INPATIENTSDISCHARGED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsDischargedWritable()
  {
    return isWritable(INPATIENTSDISCHARGED);
  }
  
  public boolean isInPatientsDischargedReadable()
  {
    return isReadable(INPATIENTSDISCHARGED);
  }
  
  public boolean isInPatientsDischargedModified()
  {
    return isModified(INPATIENTSDISCHARGED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsDischargedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSDISCHARGED).getAttributeMdDTO();
  }
  
  public Integer getInPatientsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSFEMALE));
  }
  
  public void setInPatientsFemale(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSFEMALE, "");
    }
    else
    {
      setValue(INPATIENTSFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsFemaleWritable()
  {
    return isWritable(INPATIENTSFEMALE);
  }
  
  public boolean isInPatientsFemaleReadable()
  {
    return isReadable(INPATIENTSFEMALE);
  }
  
  public boolean isInPatientsFemaleModified()
  {
    return isModified(INPATIENTSFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSFEMALE).getAttributeMdDTO();
  }
  
  public Integer getInPatientsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSMALE));
  }
  
  public void setInPatientsMale(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSMALE, "");
    }
    else
    {
      setValue(INPATIENTSMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsMaleWritable()
  {
    return isWritable(INPATIENTSMALE);
  }
  
  public boolean isInPatientsMaleReadable()
  {
    return isReadable(INPATIENTSMALE);
  }
  
  public boolean isInPatientsMaleModified()
  {
    return isModified(INPATIENTSMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSMALE).getAttributeMdDTO();
  }
  
  public Integer getInPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSNOTTREATED));
  }
  
  public void setInPatientsNotTreated(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSNOTTREATED, "");
    }
    else
    {
      setValue(INPATIENTSNOTTREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsNotTreatedWritable()
  {
    return isWritable(INPATIENTSNOTTREATED);
  }
  
  public boolean isInPatientsNotTreatedReadable()
  {
    return isReadable(INPATIENTSNOTTREATED);
  }
  
  public boolean isInPatientsNotTreatedModified()
  {
    return isModified(INPATIENTSNOTTREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsNotTreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSNOTTREATED).getAttributeMdDTO();
  }
  
  public Integer getInPatientsPregnantAnemia()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSPREGNANTANEMIA));
  }
  
  public void setInPatientsPregnantAnemia(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSPREGNANTANEMIA, "");
    }
    else
    {
      setValue(INPATIENTSPREGNANTANEMIA, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsPregnantAnemiaWritable()
  {
    return isWritable(INPATIENTSPREGNANTANEMIA);
  }
  
  public boolean isInPatientsPregnantAnemiaReadable()
  {
    return isReadable(INPATIENTSPREGNANTANEMIA);
  }
  
  public boolean isInPatientsPregnantAnemiaModified()
  {
    return isModified(INPATIENTSPREGNANTANEMIA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsPregnantAnemiaMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSPREGNANTANEMIA).getAttributeMdDTO();
  }
  
  public Integer getInPatientsPregnantDianosis()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSPREGNANTDIANOSIS));
  }
  
  public void setInPatientsPregnantDianosis(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSPREGNANTDIANOSIS, "");
    }
    else
    {
      setValue(INPATIENTSPREGNANTDIANOSIS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsPregnantDianosisWritable()
  {
    return isWritable(INPATIENTSPREGNANTDIANOSIS);
  }
  
  public boolean isInPatientsPregnantDianosisReadable()
  {
    return isReadable(INPATIENTSPREGNANTDIANOSIS);
  }
  
  public boolean isInPatientsPregnantDianosisModified()
  {
    return isModified(INPATIENTSPREGNANTDIANOSIS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsPregnantDianosisMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSPREGNANTDIANOSIS).getAttributeMdDTO();
  }
  
  public Integer getInPatientsTotal()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INPATIENTSTOTAL));
  }
  
  public void setInPatientsTotal(Integer value)
  {
    if(value == null)
    {
      setValue(INPATIENTSTOTAL, "");
    }
    else
    {
      setValue(INPATIENTSTOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInPatientsTotalWritable()
  {
    return isWritable(INPATIENTSTOTAL);
  }
  
  public boolean isInPatientsTotalReadable()
  {
    return isReadable(INPATIENTSTOTAL);
  }
  
  public boolean isInPatientsTotalModified()
  {
    return isModified(INPATIENTSTOTAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInPatientsTotalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INPATIENTSTOTAL).getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
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
  
  public Integer getOutPatients()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTS));
  }
  
  public void setOutPatients(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTS, "");
    }
    else
    {
      setValue(OUTPATIENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOutPatientsWritable()
  {
    return isWritable(OUTPATIENTS);
  }
  
  public boolean isOutPatientsReadable()
  {
    return isReadable(OUTPATIENTS);
  }
  
  public boolean isOutPatientsModified()
  {
    return isModified(OUTPATIENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTS).getAttributeMdDTO();
  }
  
  public Integer getOutPatientsFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSFEMALE));
  }
  
  public void setOutPatientsFemale(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTSFEMALE, "");
    }
    else
    {
      setValue(OUTPATIENTSFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOutPatientsFemaleWritable()
  {
    return isWritable(OUTPATIENTSFEMALE);
  }
  
  public boolean isOutPatientsFemaleReadable()
  {
    return isReadable(OUTPATIENTSFEMALE);
  }
  
  public boolean isOutPatientsFemaleModified()
  {
    return isModified(OUTPATIENTSFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTSFEMALE).getAttributeMdDTO();
  }
  
  public Integer getOutPatientsMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSMALE));
  }
  
  public void setOutPatientsMale(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTSMALE, "");
    }
    else
    {
      setValue(OUTPATIENTSMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOutPatientsMaleWritable()
  {
    return isWritable(OUTPATIENTSMALE);
  }
  
  public boolean isOutPatientsMaleReadable()
  {
    return isReadable(OUTPATIENTSMALE);
  }
  
  public boolean isOutPatientsMaleModified()
  {
    return isModified(OUTPATIENTSMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTSMALE).getAttributeMdDTO();
  }
  
  public Integer getOutPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSNOTTREATED));
  }
  
  public void setOutPatientsNotTreated(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTSNOTTREATED, "");
    }
    else
    {
      setValue(OUTPATIENTSNOTTREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOutPatientsNotTreatedWritable()
  {
    return isWritable(OUTPATIENTSNOTTREATED);
  }
  
  public boolean isOutPatientsNotTreatedReadable()
  {
    return isReadable(OUTPATIENTSNOTTREATED);
  }
  
  public boolean isOutPatientsNotTreatedModified()
  {
    return isModified(OUTPATIENTSNOTTREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsNotTreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTSNOTTREATED).getAttributeMdDTO();
  }
  
  public Integer getOutPatientsTotal()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OUTPATIENTSTOTAL));
  }
  
  public void setOutPatientsTotal(Integer value)
  {
    if(value == null)
    {
      setValue(OUTPATIENTSTOTAL, "");
    }
    else
    {
      setValue(OUTPATIENTSTOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOutPatientsTotalWritable()
  {
    return isWritable(OUTPATIENTSTOTAL);
  }
  
  public boolean isOutPatientsTotalReadable()
  {
    return isReadable(OUTPATIENTSTOTAL);
  }
  
  public boolean isOutPatientsTotalModified()
  {
    return isModified(OUTPATIENTSTOTAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOutPatientsTotalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OUTPATIENTSTOTAL).getAttributeMdDTO();
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
    setValue(OWNER, value.getId());
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
  
  public Integer getPatientsNotTreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PATIENTSNOTTREATED));
  }
  
  public void setPatientsNotTreated(Integer value)
  {
    if(value == null)
    {
      setValue(PATIENTSNOTTREATED, "");
    }
    else
    {
      setValue(PATIENTSNOTTREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPatientsNotTreatedWritable()
  {
    return isWritable(PATIENTSNOTTREATED);
  }
  
  public boolean isPatientsNotTreatedReadable()
  {
    return isReadable(PATIENTSNOTTREATED);
  }
  
  public boolean isPatientsNotTreatedModified()
  {
    return isModified(PATIENTSNOTTREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPatientsNotTreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PATIENTSNOTTREATED).getAttributeMdDTO();
  }
  
  public Integer getPregnantDiagnosis()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTDIAGNOSIS));
  }
  
  public void setPregnantDiagnosis(Integer value)
  {
    if(value == null)
    {
      setValue(PREGNANTDIAGNOSIS, "");
    }
    else
    {
      setValue(PREGNANTDIAGNOSIS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPregnantDiagnosisWritable()
  {
    return isWritable(PREGNANTDIAGNOSIS);
  }
  
  public boolean isPregnantDiagnosisReadable()
  {
    return isReadable(PREGNANTDIAGNOSIS);
  }
  
  public boolean isPregnantDiagnosisModified()
  {
    return isModified(PREGNANTDIAGNOSIS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPregnantDiagnosisMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREGNANTDIAGNOSIS).getAttributeMdDTO();
  }
  
  public Integer getPregnantDiagnosisDeath()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTDIAGNOSISDEATH));
  }
  
  public void setPregnantDiagnosisDeath(Integer value)
  {
    if(value == null)
    {
      setValue(PREGNANTDIAGNOSISDEATH, "");
    }
    else
    {
      setValue(PREGNANTDIAGNOSISDEATH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPregnantDiagnosisDeathWritable()
  {
    return isWritable(PREGNANTDIAGNOSISDEATH);
  }
  
  public boolean isPregnantDiagnosisDeathReadable()
  {
    return isReadable(PREGNANTDIAGNOSISDEATH);
  }
  
  public boolean isPregnantDiagnosisDeathModified()
  {
    return isModified(PREGNANTDIAGNOSISDEATH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPregnantDiagnosisDeathMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREGNANTDIAGNOSISDEATH).getAttributeMdDTO();
  }
  
  public Integer getPregnantReferralsReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREGNANTREFERRALSRECEIVED));
  }
  
  public void setPregnantReferralsReceived(Integer value)
  {
    if(value == null)
    {
      setValue(PREGNANTREFERRALSRECEIVED, "");
    }
    else
    {
      setValue(PREGNANTREFERRALSRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPregnantReferralsReceivedWritable()
  {
    return isWritable(PREGNANTREFERRALSRECEIVED);
  }
  
  public boolean isPregnantReferralsReceivedReadable()
  {
    return isReadable(PREGNANTREFERRALSRECEIVED);
  }
  
  public boolean isPregnantReferralsReceivedModified()
  {
    return isModified(PREGNANTREFERRALSRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPregnantReferralsReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREGNANTREFERRALSRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getReferralsReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFERRALSRECEIVED));
  }
  
  public void setReferralsReceived(Integer value)
  {
    if(value == null)
    {
      setValue(REFERRALSRECEIVED, "");
    }
    else
    {
      setValue(REFERRALSRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReferralsReceivedWritable()
  {
    return isWritable(REFERRALSRECEIVED);
  }
  
  public boolean isReferralsReceivedReadable()
  {
    return isReadable(REFERRALSRECEIVED);
  }
  
  public boolean isReferralsReceivedModified()
  {
    return isModified(REFERRALSRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReferralsReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFERRALSRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getReferralsSent()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFERRALSSENT));
  }
  
  public void setReferralsSent(Integer value)
  {
    if(value == null)
    {
      setValue(REFERRALSSENT, "");
    }
    else
    {
      setValue(REFERRALSSENT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReferralsSentWritable()
  {
    return isWritable(REFERRALSSENT);
  }
  
  public boolean isReferralsSentReadable()
  {
    return isReadable(REFERRALSSENT);
  }
  
  public boolean isReferralsSentModified()
  {
    return isModified(REFERRALSSENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReferralsSentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFERRALSSENT).getAttributeMdDTO();
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
  
  public Integer getStartAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STARTAGE));
  }
  
  public void setStartAge(Integer value)
  {
    if(value == null)
    {
      setValue(STARTAGE, "");
    }
    else
    {
      setValue(STARTAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStartAgeWritable()
  {
    return isWritable(STARTAGE);
  }
  
  public boolean isStartAgeReadable()
  {
    return isReadable(STARTAGE);
  }
  
  public boolean isStartAgeModified()
  {
    return isModified(STARTAGE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getStartAgeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STARTAGE).getAttributeMdDTO();
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
  
  public Integer getStillBirths()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STILLBIRTHS));
  }
  
  public void setStillBirths(Integer value)
  {
    if(value == null)
    {
      setValue(STILLBIRTHS, "");
    }
    else
    {
      setValue(STILLBIRTHS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStillBirthsWritable()
  {
    return isWritable(STILLBIRTHS);
  }
  
  public boolean isStillBirthsReadable()
  {
    return isReadable(STILLBIRTHS);
  }
  
  public boolean isStillBirthsModified()
  {
    return isModified(STILLBIRTHS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getStillBirthsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STILLBIRTHS).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.surveillance.AggregatedCaseDTO searchByGeoEntityAndDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, java.util.Date startDate, java.util.Date endDate, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.generated.GeoEntity", "java.util.Date", "java.util.Date", "dss.vector.solutions.surveillance.AggregatedAgeGroup"};
    Object[] _parameters = new Object[]{geoEntity, startDate, endDate, ageGroup};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "searchByGeoEntityAndDate", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.AggregatedCaseViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseTreatmentDTO[] getTreatments()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getTreatments", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentDTO[] getTreatments(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getTreatments", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.AggregatedCaseViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.AggregatedCaseViewDTO searchByGeoEntityAndEpiDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, dss.vector.solutions.surveillance.PeriodTypeDTO periodType, java.lang.Integer period, java.lang.String year, dss.vector.solutions.surveillance.AggregatedAgeGroupDTO ageGroup)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.generated.GeoEntity", "dss.vector.solutions.surveillance.PeriodType", "java.lang.Integer", "java.lang.String", "dss.vector.solutions.surveillance.AggregatedAgeGroup"};
    Object[] _parameters = new Object[]{geoEntity, periodType, period, year, ageGroup};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "searchByGeoEntityAndEpiDate", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void applyAll(dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.surveillance.CaseTreatment;", "[Ldss.vector.solutions.surveillance.CaseTreatmentMethod;", "[Ldss.vector.solutions.surveillance.CaseTreatmentStock;", "[Ldss.vector.solutions.surveillance.CaseDiagnostic;", "[Ldss.vector.solutions.surveillance.CaseReferral;"};
    Object[] _parameters = new Object[]{treatments, treatmentMethods, stock, diagnosticMethods, referrals};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.surveillance.CaseTreatmentDTO[] treatments, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] treatmentMethods, dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] stock, dss.vector.solutions.surveillance.CaseDiagnosticDTO[] diagnosticMethods, dss.vector.solutions.surveillance.CaseReferralDTO[] referrals)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.surveillance.CaseTreatment;", "[Ldss.vector.solutions.surveillance.CaseTreatmentMethod;", "[Ldss.vector.solutions.surveillance.CaseTreatmentStock;", "[Ldss.vector.solutions.surveillance.CaseDiagnostic;", "[Ldss.vector.solutions.surveillance.CaseReferral;"};
    Object[] _parameters = new Object[]{id, treatments, treatmentMethods, stock, diagnosticMethods, referrals};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseDiagnosticDTO[] getDiagnosticMethods()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getDiagnosticMethods", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosticDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnosticDTO[] getDiagnosticMethods(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getDiagnosticMethods", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseDiagnosticDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] getTreatmentStocks()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getTreatmentStocks", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentStockDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentStockDTO[] getTreatmentStocks(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getTreatmentStocks", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentStockDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] getTreatmentMethods()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getTreatmentMethods", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[] getTreatmentMethods(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getTreatmentMethods", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.AggregatedCaseViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.CaseReferralDTO[] getReferrals()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getReferrals", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseReferralDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.CaseReferralDTO[] getReferrals(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "getReferrals", _declaredTypes);
    return (dss.vector.solutions.surveillance.CaseReferralDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.TreatmentGridDTO> getAllTreatment()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.TreatmentGridDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.TreatmentGridDTO> getAllTreatment(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.TreatmentGridDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO> getAllTreatmentRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO> getAllTreatmentRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentDTO addTreatment(dss.vector.solutions.surveillance.TreatmentGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentDTO addTreatment(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.TreatmentGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public void removeTreatment(dss.vector.solutions.surveillance.CaseTreatmentDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTreatment(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTreatment()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public static void removeAllTreatment(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.TreatmentMethodGridDTO> getAllTreatmentMethod()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.TreatmentMethodGridDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.TreatmentMethodGridDTO> getAllTreatmentMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.TreatmentMethodGridDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO> getAllTreatmentMethodRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO> getAllTreatmentMethodRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentMethodDTO addTreatmentMethod(dss.vector.solutions.surveillance.TreatmentMethodGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentMethodDTO addTreatmentMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.TreatmentMethodGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public void removeTreatmentMethod(dss.vector.solutions.surveillance.CaseTreatmentMethodDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTreatmentMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTreatmentMethod()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public static void removeAllTreatmentMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.TreatmentGridDTO> getAllTreatmentStock()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.TreatmentGridDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.TreatmentGridDTO> getAllTreatmentStock(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.TreatmentGridDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO> getAllTreatmentStockRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO> getAllTreatmentStockRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentStockDTO addTreatmentStock(dss.vector.solutions.surveillance.TreatmentGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentStockDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentStockDTO addTreatmentStock(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.TreatmentGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentStockDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public void removeTreatmentStock(dss.vector.solutions.surveillance.CaseTreatmentStockDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTreatmentStock(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentStockDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTreatmentStock()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public static void removeAllTreatmentStock(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.DiagnosticGridDTO> getAllDiagnosticMethod()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.DiagnosticGridDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.DiagnosticGridDTO> getAllDiagnosticMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.DiagnosticGridDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO> getAllDiagnosticMethodRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO> getAllDiagnosticMethodRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseDiagnosticDTO addDiagnosticMethod(dss.vector.solutions.surveillance.DiagnosticGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseDiagnosticDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseDiagnosticDTO addDiagnosticMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.DiagnosticGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseDiagnosticDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public void removeDiagnosticMethod(dss.vector.solutions.surveillance.CaseDiagnosticDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeDiagnosticMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseDiagnosticDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllDiagnosticMethod()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public static void removeAllDiagnosticMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.ReferralGridDTO> getAllReferral()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.ReferralGridDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.ReferralGridDTO> getAllReferral(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.ReferralGridDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseReferralDTO> getAllReferralRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseReferralDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseReferralDTO> getAllReferralRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseReferralDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseReferralDTO addReferral(dss.vector.solutions.surveillance.ReferralGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseReferralDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseReferralDTO addReferral(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.ReferralGridDTO child)
  {
    return (dss.vector.solutions.surveillance.CaseReferralDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public void removeReferral(dss.vector.solutions.surveillance.CaseReferralDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeReferral(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseReferralDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllReferral()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public static void removeAllReferral(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.surveillance.CaseReferralDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.AggregatedCaseDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.surveillance.AggregatedCaseDTO) dto;
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
  
  public static dss.vector.solutions.surveillance.AggregatedCaseQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.surveillance.AggregatedCaseQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.surveillance.AggregatedCase", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.surveillance.AggregatedCaseDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.surveillance.AggregatedCaseDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
