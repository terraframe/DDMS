package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -261644812)
public abstract class ITNInstanceDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNInstance";
  private static final long serialVersionUID = -261644812;
  
  protected ITNInstanceDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ITNInstanceDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DAMAGED = "damaged";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String HANGING = "hanging";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MONTHRECIEVED = "monthRecieved";
  public static java.lang.String MONTHRETREATED = "monthRetreated";
  public static java.lang.String NETBRAND = "netBrand";
  public static java.lang.String NETID = "netId";
  public static java.lang.String NOTUSEDFORSLEEPING = "notUsedForSleeping";
  public static java.lang.String OBTAINED = "obtained";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PRICE = "price";
  public static java.lang.String PURPOSE = "purpose";
  public static java.lang.String PURPOSECOMMENTS = "purposeComments";
  public static java.lang.String RETEATED = "reteated";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WASHFREQUENCY = "washFrequency";
  public static java.lang.String WASHPERIOD = "washPeriod";
  public static java.lang.String WASHED = "washed";
  public static java.lang.String YEARRECIEVED = "yearRecieved";
  public static java.lang.String YEARRETREATED = "yearRetreated";
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
  
  public dss.vector.solutions.ontology.TermDTO getDamaged()
  {
    if(getValue(DAMAGED) == null || getValue(DAMAGED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DAMAGED));
    }
  }
  
  public void setDamaged(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DAMAGED, "");
    }
    else
    {
      setValue(DAMAGED, value.getId());
    }
  }
  
  public boolean isDamagedWritable()
  {
    return isWritable(DAMAGED);
  }
  
  public boolean isDamagedReadable()
  {
    return isReadable(DAMAGED);
  }
  
  public boolean isDamagedModified()
  {
    return isModified(DAMAGED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDamagedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DAMAGED).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getHanging()
  {
    if(getValue(HANGING) == null || getValue(HANGING).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(HANGING));
    }
  }
  
  public void setHanging(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(HANGING, "");
    }
    else
    {
      setValue(HANGING, value.getId());
    }
  }
  
  public boolean isHangingWritable()
  {
    return isWritable(HANGING);
  }
  
  public boolean isHangingReadable()
  {
    return isReadable(HANGING);
  }
  
  public boolean isHangingModified()
  {
    return isModified(HANGING);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getHangingMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HANGING).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.MonthOfYearDTO> getMonthRecieved()
  {
    return (java.util.List<dss.vector.solutions.MonthOfYearDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.MonthOfYear", getEnumNames(MONTHRECIEVED));
  }
  
  public java.util.List<String> getMonthRecievedEnumNames()
  {
    return getEnumNames(MONTHRECIEVED);
  }
  
  public void addMonthRecieved(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    addEnumItem(MONTHRECIEVED, enumDTO.toString());
  }
  
  public void removeMonthRecieved(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    removeEnumItem(MONTHRECIEVED, enumDTO.toString());
  }
  
  public void clearMonthRecieved()
  {
    clearEnum(MONTHRECIEVED);
  }
  
  public boolean isMonthRecievedWritable()
  {
    return isWritable(MONTHRECIEVED);
  }
  
  public boolean isMonthRecievedReadable()
  {
    return isReadable(MONTHRECIEVED);
  }
  
  public boolean isMonthRecievedModified()
  {
    return isModified(MONTHRECIEVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getMonthRecievedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(MONTHRECIEVED).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.MonthOfYearDTO> getMonthRetreated()
  {
    return (java.util.List<dss.vector.solutions.MonthOfYearDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.MonthOfYear", getEnumNames(MONTHRETREATED));
  }
  
  public java.util.List<String> getMonthRetreatedEnumNames()
  {
    return getEnumNames(MONTHRETREATED);
  }
  
  public void addMonthRetreated(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    addEnumItem(MONTHRETREATED, enumDTO.toString());
  }
  
  public void removeMonthRetreated(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    removeEnumItem(MONTHRETREATED, enumDTO.toString());
  }
  
  public void clearMonthRetreated()
  {
    clearEnum(MONTHRETREATED);
  }
  
  public boolean isMonthRetreatedWritable()
  {
    return isWritable(MONTHRETREATED);
  }
  
  public boolean isMonthRetreatedReadable()
  {
    return isReadable(MONTHRETREATED);
  }
  
  public boolean isMonthRetreatedModified()
  {
    return isModified(MONTHRETREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getMonthRetreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(MONTHRETREATED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getNetBrand()
  {
    if(getValue(NETBRAND) == null || getValue(NETBRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(NETBRAND));
    }
  }
  
  public void setNetBrand(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(NETBRAND, "");
    }
    else
    {
      setValue(NETBRAND, value.getId());
    }
  }
  
  public boolean isNetBrandWritable()
  {
    return isWritable(NETBRAND);
  }
  
  public boolean isNetBrandReadable()
  {
    return isReadable(NETBRAND);
  }
  
  public boolean isNetBrandModified()
  {
    return isModified(NETBRAND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getNetBrandMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NETBRAND).getAttributeMdDTO();
  }
  
  public String getNetId()
  {
    return getValue(NETID);
  }
  
  public void setNetId(String value)
  {
    if(value == null)
    {
      setValue(NETID, "");
    }
    else
    {
      setValue(NETID, value);
    }
  }
  
  public boolean isNetIdWritable()
  {
    return isWritable(NETID);
  }
  
  public boolean isNetIdReadable()
  {
    return isReadable(NETID);
  }
  
  public boolean isNetIdModified()
  {
    return isModified(NETID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getNetIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NETID).getAttributeMdDTO();
  }
  
  public Boolean getNotUsedForSleeping()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NOTUSEDFORSLEEPING));
  }
  
  public void setNotUsedForSleeping(Boolean value)
  {
    if(value == null)
    {
      setValue(NOTUSEDFORSLEEPING, "");
    }
    else
    {
      setValue(NOTUSEDFORSLEEPING, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNotUsedForSleepingWritable()
  {
    return isWritable(NOTUSEDFORSLEEPING);
  }
  
  public boolean isNotUsedForSleepingReadable()
  {
    return isReadable(NOTUSEDFORSLEEPING);
  }
  
  public boolean isNotUsedForSleepingModified()
  {
    return isModified(NOTUSEDFORSLEEPING);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getNotUsedForSleepingMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NOTUSEDFORSLEEPING).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getObtained()
  {
    if(getValue(OBTAINED) == null || getValue(OBTAINED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(OBTAINED));
    }
  }
  
  public void setObtained(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(OBTAINED, "");
    }
    else
    {
      setValue(OBTAINED, value.getId());
    }
  }
  
  public boolean isObtainedWritable()
  {
    return isWritable(OBTAINED);
  }
  
  public boolean isObtainedReadable()
  {
    return isReadable(OBTAINED);
  }
  
  public boolean isObtainedModified()
  {
    return isModified(OBTAINED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getObtainedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OBTAINED).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getPrice()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PRICE));
  }
  
  public void setPrice(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(PRICE, "");
    }
    else
    {
      setValue(PRICE, value.toString());
    }
  }
  
  public boolean isPriceWritable()
  {
    return isWritable(PRICE);
  }
  
  public boolean isPriceReadable()
  {
    return isReadable(PRICE);
  }
  
  public boolean isPriceModified()
  {
    return isModified(PRICE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getPriceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(PRICE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPurpose()
  {
    if(getValue(PURPOSE) == null || getValue(PURPOSE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PURPOSE));
    }
  }
  
  public void setPurpose(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PURPOSE, "");
    }
    else
    {
      setValue(PURPOSE, value.getId());
    }
  }
  
  public boolean isPurposeWritable()
  {
    return isWritable(PURPOSE);
  }
  
  public boolean isPurposeReadable()
  {
    return isReadable(PURPOSE);
  }
  
  public boolean isPurposeModified()
  {
    return isModified(PURPOSE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPurposeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PURPOSE).getAttributeMdDTO();
  }
  
  public String getPurposeComments()
  {
    return getValue(PURPOSECOMMENTS);
  }
  
  public void setPurposeComments(String value)
  {
    if(value == null)
    {
      setValue(PURPOSECOMMENTS, "");
    }
    else
    {
      setValue(PURPOSECOMMENTS, value);
    }
  }
  
  public boolean isPurposeCommentsWritable()
  {
    return isWritable(PURPOSECOMMENTS);
  }
  
  public boolean isPurposeCommentsReadable()
  {
    return isReadable(PURPOSECOMMENTS);
  }
  
  public boolean isPurposeCommentsModified()
  {
    return isModified(PURPOSECOMMENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getPurposeCommentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PURPOSECOMMENTS).getAttributeMdDTO();
  }
  
  public Boolean getReteated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETEATED));
  }
  
  public void setReteated(Boolean value)
  {
    if(value == null)
    {
      setValue(RETEATED, "");
    }
    else
    {
      setValue(RETEATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isReteatedWritable()
  {
    return isWritable(RETEATED);
  }
  
  public boolean isReteatedReadable()
  {
    return isReadable(RETEATED);
  }
  
  public boolean isReteatedModified()
  {
    return isModified(RETEATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getReteatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETEATED).getAttributeMdDTO();
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
  
  public Long getSleptUnderNet()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SLEPTUNDERNET));
  }
  
  public void setSleptUnderNet(Long value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNET, "");
    }
    else
    {
      setValue(SLEPTUNDERNET, java.lang.Long.toString(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSleptUnderNetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SLEPTUNDERNET).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getWashPeriod()
  {
    if(getValue(WASHPERIOD) == null || getValue(WASHPERIOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(WASHPERIOD));
    }
  }
  
  public void setWashPeriod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(WASHPERIOD, "");
    }
    else
    {
      setValue(WASHPERIOD, value.getId());
    }
  }
  
  public boolean isWashPeriodWritable()
  {
    return isWritable(WASHPERIOD);
  }
  
  public boolean isWashPeriodReadable()
  {
    return isReadable(WASHPERIOD);
  }
  
  public boolean isWashPeriodModified()
  {
    return isModified(WASHPERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWashPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WASHPERIOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.ResponseDTO> getWashed()
  {
    return (java.util.List<dss.vector.solutions.ResponseDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.Response", getEnumNames(WASHED));
  }
  
  public java.util.List<String> getWashedEnumNames()
  {
    return getEnumNames(WASHED);
  }
  
  public void addWashed(dss.vector.solutions.ResponseDTO enumDTO)
  {
    addEnumItem(WASHED, enumDTO.toString());
  }
  
  public void removeWashed(dss.vector.solutions.ResponseDTO enumDTO)
  {
    removeEnumItem(WASHED, enumDTO.toString());
  }
  
  public void clearWashed()
  {
    clearEnum(WASHED);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getWashedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(WASHED).getAttributeMdDTO();
  }
  
  public Integer getYearRecieved()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRECIEVED));
  }
  
  public void setYearRecieved(Integer value)
  {
    if(value == null)
    {
      setValue(YEARRECIEVED, "");
    }
    else
    {
      setValue(YEARRECIEVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearRecievedWritable()
  {
    return isWritable(YEARRECIEVED);
  }
  
  public boolean isYearRecievedReadable()
  {
    return isReadable(YEARRECIEVED);
  }
  
  public boolean isYearRecievedModified()
  {
    return isModified(YEARRECIEVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearRecievedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRECIEVED).getAttributeMdDTO();
  }
  
  public Integer getYearRetreated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRETREATED));
  }
  
  public void setYearRetreated(Integer value)
  {
    if(value == null)
    {
      setValue(YEARRETREATED, "");
    }
    else
    {
      setValue(YEARRETREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearRetreatedWritable()
  {
    return isWritable(YEARRETREATED);
  }
  
  public boolean isYearRetreatedReadable()
  {
    return isReadable(YEARRETREATED);
  }
  
  public boolean isYearRetreatedModified()
  {
    return isModified(YEARRETREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearRetreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRETREATED).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO> getAllHouseholds()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO> getAllHouseholds(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO> getAllHouseholdsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO> getAllHouseholdsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO addHouseholds(dss.vector.solutions.intervention.monitor.HouseholdDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO addHouseholds(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.HouseholdDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public void removeHouseholds(dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeHouseholds(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllHouseholds()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public static void removeAllHouseholds(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNInstanceDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.ITNInstanceDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.ITNInstanceQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.ITNInstanceQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.intervention.monitor.ITNInstance", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNInstanceDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNInstanceDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
