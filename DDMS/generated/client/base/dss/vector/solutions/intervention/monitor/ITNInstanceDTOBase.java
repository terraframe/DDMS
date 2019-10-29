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
package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -1070216734)
public abstract class ITNInstanceDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNInstance";
  private static final long serialVersionUID = -1070216734;
  
  protected ITNInstanceDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ITNInstanceDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String MONTHRECEIVED = "monthReceived";
  public static java.lang.String MONTHRETREATED = "monthRetreated";
  public static java.lang.String NETBRAND = "netBrand";
  public static java.lang.String NETID = "netId";
  public static java.lang.String NOTUSEDFORSLEEPING = "notUsedForSleeping";
  public static java.lang.String OBTAINED = "obtained";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PRICE = "price";
  public static java.lang.String PURPOSE = "purpose";
  public static java.lang.String PURPOSECOMMENTS = "purposeComments";
  public static java.lang.String RECEIVEDDATE = "receivedDate";
  public static java.lang.String RETREATED = "retreated";
  public static java.lang.String RETREATEDDATE = "retreatedDate";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WASHFREQUENCY = "washFrequency";
  public static java.lang.String WASHPERIOD = "washPeriod";
  public static java.lang.String WASHED = "washed";
  public static java.lang.String YEARRECEIVED = "yearReceived";
  public static java.lang.String YEARRETREATED = "yearRetreated";
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
  
  public String getDamagedId()
  {
    return getValue(DAMAGED);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDamagedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DAMAGED).getAttributeMdDTO();
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
  
  public String getHangingId()
  {
    return getValue(HANGING);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getHangingMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HANGING).getAttributeMdDTO();
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
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLD);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getHouseholdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HOUSEHOLD).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.MonthOfYearDTO> getMonthReceived()
  {
    return (java.util.List<dss.vector.solutions.MonthOfYearDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.MonthOfYearDTO.CLASS, getEnumNames(MONTHRECEIVED));
  }
  
  public java.util.List<String> getMonthReceivedEnumNames()
  {
    return getEnumNames(MONTHRECEIVED);
  }
  
  public void addMonthReceived(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    addEnumItem(MONTHRECEIVED, enumDTO.toString());
  }
  
  public void removeMonthReceived(dss.vector.solutions.MonthOfYearDTO enumDTO)
  {
    removeEnumItem(MONTHRECEIVED, enumDTO.toString());
  }
  
  public void clearMonthReceived()
  {
    clearEnum(MONTHRECEIVED);
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
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getMonthReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(MONTHRECEIVED).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.MonthOfYearDTO> getMonthRetreated()
  {
    return (java.util.List<dss.vector.solutions.MonthOfYearDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.MonthOfYearDTO.CLASS, getEnumNames(MONTHRETREATED));
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
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getMonthRetreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(MONTHRETREATED).getAttributeMdDTO();
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
  
  public String getNetBrandId()
  {
    return getValue(NETBRAND);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getNetBrandMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NETBRAND).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNetIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NETID).getAttributeMdDTO();
  }
  
  public Boolean getNotUsedForSleeping()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NOTUSEDFORSLEEPING));
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
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getNotUsedForSleepingMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NOTUSEDFORSLEEPING).getAttributeMdDTO();
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
  
  public String getObtainedId()
  {
    return getValue(OBTAINED);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getObtainedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OBTAINED).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getPrice()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PRICE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPriceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(PRICE).getAttributeMdDTO();
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
  
  public String getPurposeId()
  {
    return getValue(PURPOSE);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPurposeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PURPOSE).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getPurposeCommentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PURPOSECOMMENTS).getAttributeMdDTO();
  }
  
  public java.util.Date getReceivedDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(RECEIVEDDATE));
  }
  
  public void setReceivedDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(RECEIVEDDATE, "");
    }
    else
    {
      setValue(RECEIVEDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isReceivedDateWritable()
  {
    return isWritable(RECEIVEDDATE);
  }
  
  public boolean isReceivedDateReadable()
  {
    return isReadable(RECEIVEDDATE);
  }
  
  public boolean isReceivedDateModified()
  {
    return isModified(RECEIVEDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getReceivedDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(RECEIVEDDATE).getAttributeMdDTO();
  }
  
  public Boolean getRetreated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETREATED));
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
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getRetreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETREATED).getAttributeMdDTO();
  }
  
  public java.util.Date getRetreatedDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(RETREATEDDATE));
  }
  
  public void setRetreatedDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(RETREATEDDATE, "");
    }
    else
    {
      setValue(RETREATEDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isRetreatedDateWritable()
  {
    return isWritable(RETREATEDDATE);
  }
  
  public boolean isRetreatedDateReadable()
  {
    return isReadable(RETREATEDDATE);
  }
  
  public boolean isRetreatedDateModified()
  {
    return isModified(RETREATEDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getRetreatedDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(RETREATEDDATE).getAttributeMdDTO();
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
  
  public Long getSleptUnderNet()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SLEPTUNDERNET));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSleptUnderNetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SLEPTUNDERNET).getAttributeMdDTO();
  }
  
  public Integer getWashFrequency()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WASHFREQUENCY));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWashFrequencyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WASHFREQUENCY).getAttributeMdDTO();
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
  
  public String getWashPeriodId()
  {
    return getValue(WASHPERIOD);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWashPeriodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WASHPERIOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.ResponseDTO> getWashed()
  {
    return (java.util.List<dss.vector.solutions.ResponseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.ResponseDTO.CLASS, getEnumNames(WASHED));
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
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getWashedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(WASHED).getAttributeMdDTO();
  }
  
  public Integer getYearReceived()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRECEIVED));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getYearRetreated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRETREATED));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearRetreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRETREATED).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO> getAllHouseholds()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO> getAllHouseholds(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO> getAllHouseholdsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO> getAllHouseholdsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO addHouseholds(dss.vector.solutions.intervention.monitor.HouseholdDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO addHouseholds(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.HouseholdDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public void removeHouseholds(dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeHouseholds(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllHouseholds()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public static void removeAllHouseholds(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.HouseholdITNInstanceDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNInstanceDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.intervention.monitor.ITNInstanceQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.ITNInstanceQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNInstanceDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNInstanceDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNInstanceDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNInstanceDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
