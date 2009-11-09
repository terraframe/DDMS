package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 1719456973)
public abstract class PersonDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.Person";
  private static final long serialVersionUID = 1719456973;
  
  protected PersonDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PersonDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ANAEMIATREATMENT = "anaemiaTreatment";
  public static java.lang.String BLOODSLIDE = "bloodslide";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DOB = "dob";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FEVER = "fever";
  public static java.lang.String FEVERTREATMENT = "feverTreatment";
  public static java.lang.String HAEMOGLOBIN = "haemoglobin";
  public static java.lang.String HAEMOGLOBINMEASURED = "haemoglobinMeasured";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String IRON = "iron";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MALARIA = "malaria";
  public static java.lang.String MALARIATREATMENT = "malariaTreatment";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PAYMENT = "payment";
  public static java.lang.String PERFORMEDRDT = "performedRDT";
  public static java.lang.String PERSONID = "personId";
  public static java.lang.String PREGNANT = "pregnant";
  public static java.lang.String RDTTREATMENT = "rdtTreatment";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public static java.lang.String TYPE = "type";
  public dss.vector.solutions.ontology.TermDTO getAnaemiaTreatment()
  {
    if(getValue(ANAEMIATREATMENT) == null || getValue(ANAEMIATREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ANAEMIATREATMENT));
    }
  }
  
  public void setAnaemiaTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ANAEMIATREATMENT, "");
    }
    else
    {
      setValue(ANAEMIATREATMENT, value.getId());
    }
  }
  
  public boolean isAnaemiaTreatmentWritable()
  {
    return isWritable(ANAEMIATREATMENT);
  }
  
  public boolean isAnaemiaTreatmentReadable()
  {
    return isReadable(ANAEMIATREATMENT);
  }
  
  public boolean isAnaemiaTreatmentModified()
  {
    return isModified(ANAEMIATREATMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAnaemiaTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ANAEMIATREATMENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getBloodslide()
  {
    if(getValue(BLOODSLIDE) == null || getValue(BLOODSLIDE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(BLOODSLIDE));
    }
  }
  
  public void setBloodslide(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(BLOODSLIDE, "");
    }
    else
    {
      setValue(BLOODSLIDE, value.getId());
    }
  }
  
  public boolean isBloodslideWritable()
  {
    return isWritable(BLOODSLIDE);
  }
  
  public boolean isBloodslideReadable()
  {
    return isReadable(BLOODSLIDE);
  }
  
  public boolean isBloodslideModified()
  {
    return isModified(BLOODSLIDE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getBloodslideMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BLOODSLIDE).getAttributeMdDTO();
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
  
  public java.util.Date getDob()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DOB));
  }
  
  public void setDob(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DOB, "");
    }
    else
    {
      setValue(DOB, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDobWritable()
  {
    return isWritable(DOB);
  }
  
  public boolean isDobReadable()
  {
    return isReadable(DOB);
  }
  
  public boolean isDobModified()
  {
    return isModified(DOB);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getDobMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DOB).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getFever()
  {
    if(getValue(FEVER) == null || getValue(FEVER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(FEVER));
    }
  }
  
  public void setFever(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(FEVER, "");
    }
    else
    {
      setValue(FEVER, value.getId());
    }
  }
  
  public boolean isFeverWritable()
  {
    return isWritable(FEVER);
  }
  
  public boolean isFeverReadable()
  {
    return isReadable(FEVER);
  }
  
  public boolean isFeverModified()
  {
    return isModified(FEVER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFeverMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FEVER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getFeverTreatment()
  {
    if(getValue(FEVERTREATMENT) == null || getValue(FEVERTREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(FEVERTREATMENT));
    }
  }
  
  public void setFeverTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(FEVERTREATMENT, "");
    }
    else
    {
      setValue(FEVERTREATMENT, value.getId());
    }
  }
  
  public boolean isFeverTreatmentWritable()
  {
    return isWritable(FEVERTREATMENT);
  }
  
  public boolean isFeverTreatmentReadable()
  {
    return isReadable(FEVERTREATMENT);
  }
  
  public boolean isFeverTreatmentModified()
  {
    return isModified(FEVERTREATMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFeverTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FEVERTREATMENT).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getHaemoglobin()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(HAEMOGLOBIN));
  }
  
  public void setHaemoglobin(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(HAEMOGLOBIN, "");
    }
    else
    {
      setValue(HAEMOGLOBIN, value.toString());
    }
  }
  
  public boolean isHaemoglobinWritable()
  {
    return isWritable(HAEMOGLOBIN);
  }
  
  public boolean isHaemoglobinReadable()
  {
    return isReadable(HAEMOGLOBIN);
  }
  
  public boolean isHaemoglobinModified()
  {
    return isModified(HAEMOGLOBIN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getHaemoglobinMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(HAEMOGLOBIN).getAttributeMdDTO();
  }
  
  public Boolean getHaemoglobinMeasured()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HAEMOGLOBINMEASURED));
  }
  
  public void setHaemoglobinMeasured(Boolean value)
  {
    if(value == null)
    {
      setValue(HAEMOGLOBINMEASURED, "");
    }
    else
    {
      setValue(HAEMOGLOBINMEASURED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isHaemoglobinMeasuredWritable()
  {
    return isWritable(HAEMOGLOBINMEASURED);
  }
  
  public boolean isHaemoglobinMeasuredReadable()
  {
    return isReadable(HAEMOGLOBINMEASURED);
  }
  
  public boolean isHaemoglobinMeasuredModified()
  {
    return isModified(HAEMOGLOBINMEASURED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getHaemoglobinMeasuredMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(HAEMOGLOBINMEASURED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdDTO getHousehold()
  {
    if(getValue(HOUSEHOLD) == null || getValue(HOUSEHOLD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.HouseholdDTO.get(getRequest(), getValue(HOUSEHOLD));
    }
  }
  
  public void setHousehold(dss.vector.solutions.intervention.monitor.HouseholdDTO value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLD, "");
    }
    else
    {
      setValue(HOUSEHOLD, value.getId());
    }
  }
  
  public boolean isHouseholdWritable()
  {
    return isWritable(HOUSEHOLD);
  }
  
  public boolean isHouseholdReadable()
  {
    return isReadable(HOUSEHOLD);
  }
  
  public boolean isHouseholdModified()
  {
    return isModified(HOUSEHOLD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getHouseholdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HOUSEHOLD).getAttributeMdDTO();
  }
  
  public Boolean getIron()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(IRON));
  }
  
  public void setIron(Boolean value)
  {
    if(value == null)
    {
      setValue(IRON, "");
    }
    else
    {
      setValue(IRON, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIronWritable()
  {
    return isWritable(IRON);
  }
  
  public boolean isIronReadable()
  {
    return isReadable(IRON);
  }
  
  public boolean isIronModified()
  {
    return isModified(IRON);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIronMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(IRON).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getMalaria()
  {
    if(getValue(MALARIA) == null || getValue(MALARIA).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(MALARIA));
    }
  }
  
  public void setMalaria(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(MALARIA, "");
    }
    else
    {
      setValue(MALARIA, value.getId());
    }
  }
  
  public boolean isMalariaWritable()
  {
    return isWritable(MALARIA);
  }
  
  public boolean isMalariaReadable()
  {
    return isReadable(MALARIA);
  }
  
  public boolean isMalariaModified()
  {
    return isModified(MALARIA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getMalariaMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MALARIA).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getMalariaTreatment()
  {
    if(getValue(MALARIATREATMENT) == null || getValue(MALARIATREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(MALARIATREATMENT));
    }
  }
  
  public void setMalariaTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(MALARIATREATMENT, "");
    }
    else
    {
      setValue(MALARIATREATMENT, value.getId());
    }
  }
  
  public boolean isMalariaTreatmentWritable()
  {
    return isWritable(MALARIATREATMENT);
  }
  
  public boolean isMalariaTreatmentReadable()
  {
    return isReadable(MALARIATREATMENT);
  }
  
  public boolean isMalariaTreatmentModified()
  {
    return isModified(MALARIATREATMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getMalariaTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MALARIATREATMENT).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getPayment()
  {
    if(getValue(PAYMENT) == null || getValue(PAYMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PAYMENT));
    }
  }
  
  public void setPayment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PAYMENT, "");
    }
    else
    {
      setValue(PAYMENT, value.getId());
    }
  }
  
  public boolean isPaymentWritable()
  {
    return isWritable(PAYMENT);
  }
  
  public boolean isPaymentReadable()
  {
    return isReadable(PAYMENT);
  }
  
  public boolean isPaymentModified()
  {
    return isModified(PAYMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPaymentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PAYMENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPerformedRDT()
  {
    if(getValue(PERFORMEDRDT) == null || getValue(PERFORMEDRDT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PERFORMEDRDT));
    }
  }
  
  public void setPerformedRDT(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PERFORMEDRDT, "");
    }
    else
    {
      setValue(PERFORMEDRDT, value.getId());
    }
  }
  
  public boolean isPerformedRDTWritable()
  {
    return isWritable(PERFORMEDRDT);
  }
  
  public boolean isPerformedRDTReadable()
  {
    return isReadable(PERFORMEDRDT);
  }
  
  public boolean isPerformedRDTModified()
  {
    return isModified(PERFORMEDRDT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPerformedRDTMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PERFORMEDRDT).getAttributeMdDTO();
  }
  
  public String getPersonId()
  {
    return getValue(PERSONID);
  }
  
  public void setPersonId(String value)
  {
    if(value == null)
    {
      setValue(PERSONID, "");
    }
    else
    {
      setValue(PERSONID, value);
    }
  }
  
  public boolean isPersonIdWritable()
  {
    return isWritable(PERSONID);
  }
  
  public boolean isPersonIdReadable()
  {
    return isReadable(PERSONID);
  }
  
  public boolean isPersonIdModified()
  {
    return isModified(PERSONID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPersonIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERSONID).getAttributeMdDTO();
  }
  
  public Boolean getPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PREGNANT));
  }
  
  public void setPregnant(Boolean value)
  {
    if(value == null)
    {
      setValue(PREGNANT, "");
    }
    else
    {
      setValue(PREGNANT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPregnantWritable()
  {
    return isWritable(PREGNANT);
  }
  
  public boolean isPregnantReadable()
  {
    return isReadable(PREGNANT);
  }
  
  public boolean isPregnantModified()
  {
    return isModified(PREGNANT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PREGNANT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getRdtTreatment()
  {
    if(getValue(RDTTREATMENT) == null || getValue(RDTTREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(RDTTREATMENT));
    }
  }
  
  public void setRdtTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(RDTTREATMENT, "");
    }
    else
    {
      setValue(RDTTREATMENT, value.getId());
    }
  }
  
  public boolean isRdtTreatmentWritable()
  {
    return isWritable(RDTTREATMENT);
  }
  
  public boolean isRdtTreatmentReadable()
  {
    return isReadable(RDTTREATMENT);
  }
  
  public boolean isRdtTreatmentModified()
  {
    return isModified(RDTTREATMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getRdtTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RDTTREATMENT).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getSex()
  {
    if(getValue(SEX) == null || getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SEX));
    }
  }
  
  public void setSex(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
    }
  }
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public Boolean getSleptUnderNet()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SLEPTUNDERNET));
  }
  
  public void setSleptUnderNet(Boolean value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNET, "");
    }
    else
    {
      setValue(SLEPTUNDERNET, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSleptUnderNetWritable()
  {
    return isWritable(SLEPTUNDERNET);
  }
  
  public boolean isSleptUnderNetReadable()
  {
    return isReadable(SLEPTUNDERNET);
  }
  
  public boolean isSleptUnderNetModified()
  {
    return isModified(SLEPTUNDERNET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getSleptUnderNetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SLEPTUNDERNET).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.intervention.monitor.PersonViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.PersonViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.PersonViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.PersonViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.PersonViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllRDTResults()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.PersonRDTResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllRDTResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.PersonRDTResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonRDTResultDTO> getAllRDTResultsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonRDTResultDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.PersonRDTResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonRDTResultDTO> getAllRDTResultsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.PersonRDTResultDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.PersonRDTResultDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.PersonRDTResultDTO addRDTResults(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.PersonRDTResultDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.PersonRDTResultDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.PersonRDTResultDTO addRDTResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.PersonRDTResultDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.PersonRDTResultDTO.CLASS);
  }
  
  public void removeRDTResults(dss.vector.solutions.intervention.monitor.PersonRDTResultDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeRDTResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.PersonRDTResultDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllRDTResults()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.PersonRDTResultDTO.CLASS);
  }
  
  public static void removeAllRDTResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.PersonRDTResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO> getAllHouseholds()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO> getAllHouseholds(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.HouseholdPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdPersonDTO> getAllHouseholdsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdPersonDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdPersonDTO> getAllHouseholdsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdPersonDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.HouseholdPersonDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdPersonDTO addHouseholds(dss.vector.solutions.intervention.monitor.HouseholdDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdPersonDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.HouseholdPersonDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdPersonDTO addHouseholds(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.HouseholdDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdPersonDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.HouseholdPersonDTO.CLASS);
  }
  
  public void removeHouseholds(dss.vector.solutions.intervention.monitor.HouseholdPersonDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeHouseholds(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.HouseholdPersonDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllHouseholds()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdPersonDTO.CLASS);
  }
  
  public static void removeAllHouseholds(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.HouseholdPersonDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.PersonDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.PersonDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.PersonQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.PersonQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.intervention.monitor.Person", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.PersonDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.PersonDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
