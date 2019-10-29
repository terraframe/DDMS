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
package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1481507016)
public abstract class TeamSprayStatusDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TeamSprayStatus";
  private static final long serialVersionUID = -1481507016;
  
  protected TeamSprayStatusDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TeamSprayStatusDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BEDNETS = "bedNets";
  public static java.lang.String CATTLESHEDS = "cattleSheds";
  public static java.lang.String CATTLESHEDSLOCKED = "cattleShedsLocked";
  public static java.lang.String CATTLESHEDSOTHER = "cattleShedsOther";
  public static java.lang.String CATTLESHEDSREFUSED = "cattleShedsRefused";
  public static java.lang.String CATTLESHEDSSPRAYED = "cattleShedsSprayed";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String HOUSEHOLDS = "households";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKED = "locked";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NOZZLESUSED = "nozzlesUsed";
  public static java.lang.String NUMBERCHILDRENUNDERFIVEPROTECTED = "numberChildrenUnderFiveProtected";
  public static java.lang.String NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS = "numberChildrenUnderFiveSleepingUnderItns";
  public static java.lang.String NUMBERFEMALESPROTECTED = "numberFemalesProtected";
  public static java.lang.String NUMBERITNSINUSE = "numberItnsInUse";
  public static java.lang.String NUMBERMALESPROTECTED = "numberMalesProtected";
  public static java.lang.String NUMBEROFPEOPLE = "numberOfPeople";
  public static java.lang.String NUMBERPEOPLESLEEPINGUNDERITNS = "numberPeopleSleepingUnderItns";
  public static java.lang.String NUMBERPREGNANTWOMENPROTECTED = "numberPregnantWomenProtected";
  public static java.lang.String NUMBERPREGNANTWOMENSLEEPINGUNDERITNS = "numberPregnantWomenSleepingUnderItns";
  public static java.lang.String NUMBERROOMSNOTSPRAYEDSICK = "numberRoomsNotSprayedSick";
  public static java.lang.String OTHER = "other";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PREVSPRAYEDHOUSEHOLDS = "prevSprayedHouseholds";
  public static java.lang.String PREVSPRAYEDSTRUCTURES = "prevSprayedStructures";
  public static java.lang.String PUMPSUSED = "pumpsUsed";
  public static java.lang.String RECEIVED = "received";
  public static java.lang.String REFILLS = "refills";
  public static java.lang.String REFUSED = "refused";
  public static java.lang.String RETURNED = "returned";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String ROOMSWITHBEDNETS = "roomsWithBedNets";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SPRAY = "spray";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public static java.lang.String SPRAYEDHOUSEHOLDS = "sprayedHouseholds";
  public static java.lang.String SPRAYEDROOMS = "sprayedRooms";
  public static java.lang.String SPRAYEDSTRUCTURES = "sprayedStructures";
  public static java.lang.String STRUCTURES = "structures";
  public static java.lang.String STRUCTURESNOTSPRAYEDFUNERAL = "structuresNotSprayedFuneral";
  public static java.lang.String STRUCTURESNOTSPRAYEDLOCKED = "structuresNotSprayedLocked";
  public static java.lang.String STRUCTURESNOTSPRAYEDNOONEHOME = "structuresNotSprayedNoOneHome";
  public static java.lang.String STRUCTURESNOTSPRAYEDOTHER = "structuresNotSprayedOther";
  public static java.lang.String STRUCTURESNOTSPRAYEDREFUSED = "structuresNotSprayedRefused";
  public static java.lang.String STRUCTURESNOTSPRAYEDSICK = "structuresNotSprayedSick";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public static java.lang.String TYPE = "type";
  public static java.lang.String USED = "used";
  public static java.lang.String VERANDAS = "verandas";
  public static java.lang.String VERANDASLOCKED = "verandasLocked";
  public static java.lang.String VERANDASOTHER = "verandasOther";
  public static java.lang.String VERANDASREFUSED = "verandasRefused";
  public static java.lang.String VERANDASSPRAYED = "verandasSprayed";
  public static java.lang.String WRONGSURFACE = "wrongSurface";
  public Integer getBedNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(BEDNETS));
  }
  
  public void setBedNets(Integer value)
  {
    if(value == null)
    {
      setValue(BEDNETS, "");
    }
    else
    {
      setValue(BEDNETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isBedNetsWritable()
  {
    return isWritable(BEDNETS);
  }
  
  public boolean isBedNetsReadable()
  {
    return isReadable(BEDNETS);
  }
  
  public boolean isBedNetsModified()
  {
    return isModified(BEDNETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getBedNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(BEDNETS).getAttributeMdDTO();
  }
  
  public Integer getCattleSheds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDS));
  }
  
  public void setCattleSheds(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDS, "");
    }
    else
    {
      setValue(CATTLESHEDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsWritable()
  {
    return isWritable(CATTLESHEDS);
  }
  
  public boolean isCattleShedsReadable()
  {
    return isReadable(CATTLESHEDS);
  }
  
  public boolean isCattleShedsModified()
  {
    return isModified(CATTLESHEDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDS).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSLOCKED));
  }
  
  public void setCattleShedsLocked(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSLOCKED, "");
    }
    else
    {
      setValue(CATTLESHEDSLOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsLockedWritable()
  {
    return isWritable(CATTLESHEDSLOCKED);
  }
  
  public boolean isCattleShedsLockedReadable()
  {
    return isReadable(CATTLESHEDSLOCKED);
  }
  
  public boolean isCattleShedsLockedModified()
  {
    return isModified(CATTLESHEDSLOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSLOCKED).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSOTHER));
  }
  
  public void setCattleShedsOther(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSOTHER, "");
    }
    else
    {
      setValue(CATTLESHEDSOTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsOtherWritable()
  {
    return isWritable(CATTLESHEDSOTHER);
  }
  
  public boolean isCattleShedsOtherReadable()
  {
    return isReadable(CATTLESHEDSOTHER);
  }
  
  public boolean isCattleShedsOtherModified()
  {
    return isModified(CATTLESHEDSOTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSOTHER).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSREFUSED));
  }
  
  public void setCattleShedsRefused(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSREFUSED, "");
    }
    else
    {
      setValue(CATTLESHEDSREFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsRefusedWritable()
  {
    return isWritable(CATTLESHEDSREFUSED);
  }
  
  public boolean isCattleShedsRefusedReadable()
  {
    return isReadable(CATTLESHEDSREFUSED);
  }
  
  public boolean isCattleShedsRefusedModified()
  {
    return isModified(CATTLESHEDSREFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSREFUSED).getAttributeMdDTO();
  }
  
  public Integer getCattleShedsSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CATTLESHEDSSPRAYED));
  }
  
  public void setCattleShedsSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(CATTLESHEDSSPRAYED, "");
    }
    else
    {
      setValue(CATTLESHEDSSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isCattleShedsSprayedWritable()
  {
    return isWritable(CATTLESHEDSSPRAYED);
  }
  
  public boolean isCattleShedsSprayedReadable()
  {
    return isReadable(CATTLESHEDSSPRAYED);
  }
  
  public boolean isCattleShedsSprayedModified()
  {
    return isModified(CATTLESHEDSSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getCattleShedsSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CATTLESHEDSSPRAYED).getAttributeMdDTO();
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
  
  public Integer getHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOUSEHOLDS));
  }
  
  public void setHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDS, "");
    }
    else
    {
      setValue(HOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isHouseholdsWritable()
  {
    return isWritable(HOUSEHOLDS);
  }
  
  public boolean isHouseholdsReadable()
  {
    return isReadable(HOUSEHOLDS);
  }
  
  public boolean isHouseholdsModified()
  {
    return isModified(HOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HOUSEHOLDS).getAttributeMdDTO();
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
  
  public Integer getLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOCKED));
  }
  
  public void setLocked(Integer value)
  {
    if(value == null)
    {
      setValue(LOCKED, "");
    }
    else
    {
      setValue(LOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLockedWritable()
  {
    return isWritable(LOCKED);
  }
  
  public boolean isLockedReadable()
  {
    return isReadable(LOCKED);
  }
  
  public boolean isLockedModified()
  {
    return isModified(LOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOCKED).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
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
  
  public Integer getNozzlesUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NOZZLESUSED));
  }
  
  public void setNozzlesUsed(Integer value)
  {
    if(value == null)
    {
      setValue(NOZZLESUSED, "");
    }
    else
    {
      setValue(NOZZLESUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNozzlesUsedWritable()
  {
    return isWritable(NOZZLESUSED);
  }
  
  public boolean isNozzlesUsedReadable()
  {
    return isReadable(NOZZLESUSED);
  }
  
  public boolean isNozzlesUsedModified()
  {
    return isModified(NOZZLESUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNozzlesUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NOZZLESUSED).getAttributeMdDTO();
  }
  
  public Integer getNumberChildrenUnderFiveProtected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERCHILDRENUNDERFIVEPROTECTED));
  }
  
  public void setNumberChildrenUnderFiveProtected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERCHILDRENUNDERFIVEPROTECTED, "");
    }
    else
    {
      setValue(NUMBERCHILDRENUNDERFIVEPROTECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberChildrenUnderFiveProtectedWritable()
  {
    return isWritable(NUMBERCHILDRENUNDERFIVEPROTECTED);
  }
  
  public boolean isNumberChildrenUnderFiveProtectedReadable()
  {
    return isReadable(NUMBERCHILDRENUNDERFIVEPROTECTED);
  }
  
  public boolean isNumberChildrenUnderFiveProtectedModified()
  {
    return isModified(NUMBERCHILDRENUNDERFIVEPROTECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberChildrenUnderFiveProtectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERCHILDRENUNDERFIVEPROTECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberChildrenUnderFiveSleepingUnderItns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS));
  }
  
  public void setNumberChildrenUnderFiveSleepingUnderItns(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, "");
    }
    else
    {
      setValue(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberChildrenUnderFiveSleepingUnderItnsWritable()
  {
    return isWritable(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS);
  }
  
  public boolean isNumberChildrenUnderFiveSleepingUnderItnsReadable()
  {
    return isReadable(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS);
  }
  
  public boolean isNumberChildrenUnderFiveSleepingUnderItnsModified()
  {
    return isModified(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberChildrenUnderFiveSleepingUnderItnsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS).getAttributeMdDTO();
  }
  
  public Integer getNumberFemalesProtected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERFEMALESPROTECTED));
  }
  
  public void setNumberFemalesProtected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERFEMALESPROTECTED, "");
    }
    else
    {
      setValue(NUMBERFEMALESPROTECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberFemalesProtectedWritable()
  {
    return isWritable(NUMBERFEMALESPROTECTED);
  }
  
  public boolean isNumberFemalesProtectedReadable()
  {
    return isReadable(NUMBERFEMALESPROTECTED);
  }
  
  public boolean isNumberFemalesProtectedModified()
  {
    return isModified(NUMBERFEMALESPROTECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberFemalesProtectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERFEMALESPROTECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberItnsInUse()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERITNSINUSE));
  }
  
  public void setNumberItnsInUse(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERITNSINUSE, "");
    }
    else
    {
      setValue(NUMBERITNSINUSE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberItnsInUseWritable()
  {
    return isWritable(NUMBERITNSINUSE);
  }
  
  public boolean isNumberItnsInUseReadable()
  {
    return isReadable(NUMBERITNSINUSE);
  }
  
  public boolean isNumberItnsInUseModified()
  {
    return isModified(NUMBERITNSINUSE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberItnsInUseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERITNSINUSE).getAttributeMdDTO();
  }
  
  public Integer getNumberMalesProtected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERMALESPROTECTED));
  }
  
  public void setNumberMalesProtected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERMALESPROTECTED, "");
    }
    else
    {
      setValue(NUMBERMALESPROTECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberMalesProtectedWritable()
  {
    return isWritable(NUMBERMALESPROTECTED);
  }
  
  public boolean isNumberMalesProtectedReadable()
  {
    return isReadable(NUMBERMALESPROTECTED);
  }
  
  public boolean isNumberMalesProtectedModified()
  {
    return isModified(NUMBERMALESPROTECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberMalesProtectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERMALESPROTECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberOfPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFPEOPLE));
  }
  
  public void setNumberOfPeople(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFPEOPLE, "");
    }
    else
    {
      setValue(NUMBEROFPEOPLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfPeopleWritable()
  {
    return isWritable(NUMBEROFPEOPLE);
  }
  
  public boolean isNumberOfPeopleReadable()
  {
    return isReadable(NUMBEROFPEOPLE);
  }
  
  public boolean isNumberOfPeopleModified()
  {
    return isModified(NUMBEROFPEOPLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfPeopleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFPEOPLE).getAttributeMdDTO();
  }
  
  public Integer getNumberPeopleSleepingUnderItns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPEOPLESLEEPINGUNDERITNS));
  }
  
  public void setNumberPeopleSleepingUnderItns(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPEOPLESLEEPINGUNDERITNS, "");
    }
    else
    {
      setValue(NUMBERPEOPLESLEEPINGUNDERITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPeopleSleepingUnderItnsWritable()
  {
    return isWritable(NUMBERPEOPLESLEEPINGUNDERITNS);
  }
  
  public boolean isNumberPeopleSleepingUnderItnsReadable()
  {
    return isReadable(NUMBERPEOPLESLEEPINGUNDERITNS);
  }
  
  public boolean isNumberPeopleSleepingUnderItnsModified()
  {
    return isModified(NUMBERPEOPLESLEEPINGUNDERITNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPeopleSleepingUnderItnsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPEOPLESLEEPINGUNDERITNS).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantWomenProtected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTWOMENPROTECTED));
  }
  
  public void setNumberPregnantWomenProtected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTWOMENPROTECTED, "");
    }
    else
    {
      setValue(NUMBERPREGNANTWOMENPROTECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantWomenProtectedWritable()
  {
    return isWritable(NUMBERPREGNANTWOMENPROTECTED);
  }
  
  public boolean isNumberPregnantWomenProtectedReadable()
  {
    return isReadable(NUMBERPREGNANTWOMENPROTECTED);
  }
  
  public boolean isNumberPregnantWomenProtectedModified()
  {
    return isModified(NUMBERPREGNANTWOMENPROTECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPregnantWomenProtectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTWOMENPROTECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantWomenSleepingUnderItns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS));
  }
  
  public void setNumberPregnantWomenSleepingUnderItns(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, "");
    }
    else
    {
      setValue(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantWomenSleepingUnderItnsWritable()
  {
    return isWritable(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS);
  }
  
  public boolean isNumberPregnantWomenSleepingUnderItnsReadable()
  {
    return isReadable(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS);
  }
  
  public boolean isNumberPregnantWomenSleepingUnderItnsModified()
  {
    return isModified(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPregnantWomenSleepingUnderItnsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTWOMENSLEEPINGUNDERITNS).getAttributeMdDTO();
  }
  
  public Integer getNumberRoomsNotSprayedSick()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERROOMSNOTSPRAYEDSICK));
  }
  
  public void setNumberRoomsNotSprayedSick(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERROOMSNOTSPRAYEDSICK, "");
    }
    else
    {
      setValue(NUMBERROOMSNOTSPRAYEDSICK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberRoomsNotSprayedSickWritable()
  {
    return isWritable(NUMBERROOMSNOTSPRAYEDSICK);
  }
  
  public boolean isNumberRoomsNotSprayedSickReadable()
  {
    return isReadable(NUMBERROOMSNOTSPRAYEDSICK);
  }
  
  public boolean isNumberRoomsNotSprayedSickModified()
  {
    return isModified(NUMBERROOMSNOTSPRAYEDSICK);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberRoomsNotSprayedSickMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERROOMSNOTSPRAYEDSICK).getAttributeMdDTO();
  }
  
  public Integer getOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OTHER));
  }
  
  public void setOther(Integer value)
  {
    if(value == null)
    {
      setValue(OTHER, "");
    }
    else
    {
      setValue(OTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOtherWritable()
  {
    return isWritable(OTHER);
  }
  
  public boolean isOtherReadable()
  {
    return isReadable(OTHER);
  }
  
  public boolean isOtherModified()
  {
    return isModified(OTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OTHER).getAttributeMdDTO();
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
  
  public Integer getPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
  }
  
  public void setPeople(Integer value)
  {
    if(value == null)
    {
      setValue(PEOPLE, "");
    }
    else
    {
      setValue(PEOPLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeopleWritable()
  {
    return isWritable(PEOPLE);
  }
  
  public boolean isPeopleReadable()
  {
    return isReadable(PEOPLE);
  }
  
  public boolean isPeopleModified()
  {
    return isModified(PEOPLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeopleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PEOPLE).getAttributeMdDTO();
  }
  
  public Integer getPrevSprayedHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDHOUSEHOLDS));
  }
  
  public void setPrevSprayedHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(PREVSPRAYEDHOUSEHOLDS, "");
    }
    else
    {
      setValue(PREVSPRAYEDHOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPrevSprayedHouseholdsWritable()
  {
    return isWritable(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public boolean isPrevSprayedHouseholdsReadable()
  {
    return isReadable(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public boolean isPrevSprayedHouseholdsModified()
  {
    return isModified(PREVSPRAYEDHOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPrevSprayedHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREVSPRAYEDHOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getPrevSprayedStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREVSPRAYEDSTRUCTURES));
  }
  
  public void setPrevSprayedStructures(Integer value)
  {
    if(value == null)
    {
      setValue(PREVSPRAYEDSTRUCTURES, "");
    }
    else
    {
      setValue(PREVSPRAYEDSTRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPrevSprayedStructuresWritable()
  {
    return isWritable(PREVSPRAYEDSTRUCTURES);
  }
  
  public boolean isPrevSprayedStructuresReadable()
  {
    return isReadable(PREVSPRAYEDSTRUCTURES);
  }
  
  public boolean isPrevSprayedStructuresModified()
  {
    return isModified(PREVSPRAYEDSTRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPrevSprayedStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREVSPRAYEDSTRUCTURES).getAttributeMdDTO();
  }
  
  public Integer getPumpsUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUMPSUSED));
  }
  
  public void setPumpsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(PUMPSUSED, "");
    }
    else
    {
      setValue(PUMPSUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPumpsUsedWritable()
  {
    return isWritable(PUMPSUSED);
  }
  
  public boolean isPumpsUsedReadable()
  {
    return isReadable(PUMPSUSED);
  }
  
  public boolean isPumpsUsedModified()
  {
    return isModified(PUMPSUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPumpsUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PUMPSUSED).getAttributeMdDTO();
  }
  
  public Integer getReceived()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVED));
  }
  
  public void setReceived(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVED, "");
    }
    else
    {
      setValue(RECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedWritable()
  {
    return isWritable(RECEIVED);
  }
  
  public boolean isReceivedReadable()
  {
    return isReadable(RECEIVED);
  }
  
  public boolean isReceivedModified()
  {
    return isModified(RECEIVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVED).getAttributeMdDTO();
  }
  
  public Integer getRefills()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFILLS));
  }
  
  public void setRefills(Integer value)
  {
    if(value == null)
    {
      setValue(REFILLS, "");
    }
    else
    {
      setValue(REFILLS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRefillsWritable()
  {
    return isWritable(REFILLS);
  }
  
  public boolean isRefillsReadable()
  {
    return isReadable(REFILLS);
  }
  
  public boolean isRefillsModified()
  {
    return isModified(REFILLS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRefillsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFILLS).getAttributeMdDTO();
  }
  
  public Integer getRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFUSED));
  }
  
  public void setRefused(Integer value)
  {
    if(value == null)
    {
      setValue(REFUSED, "");
    }
    else
    {
      setValue(REFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRefusedWritable()
  {
    return isWritable(REFUSED);
  }
  
  public boolean isRefusedReadable()
  {
    return isReadable(REFUSED);
  }
  
  public boolean isRefusedModified()
  {
    return isModified(REFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFUSED).getAttributeMdDTO();
  }
  
  public Integer getReturned()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RETURNED));
  }
  
  public void setReturned(Integer value)
  {
    if(value == null)
    {
      setValue(RETURNED, "");
    }
    else
    {
      setValue(RETURNED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReturnedWritable()
  {
    return isWritable(RETURNED);
  }
  
  public boolean isReturnedReadable()
  {
    return isReadable(RETURNED);
  }
  
  public boolean isReturnedModified()
  {
    return isModified(RETURNED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReturnedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RETURNED).getAttributeMdDTO();
  }
  
  public Integer getRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
  }
  
  public void setRooms(Integer value)
  {
    if(value == null)
    {
      setValue(ROOMS, "");
    }
    else
    {
      setValue(ROOMS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRoomsWritable()
  {
    return isWritable(ROOMS);
  }
  
  public boolean isRoomsReadable()
  {
    return isReadable(ROOMS);
  }
  
  public boolean isRoomsModified()
  {
    return isModified(ROOMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRoomsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMS).getAttributeMdDTO();
  }
  
  public Integer getRoomsWithBedNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMSWITHBEDNETS));
  }
  
  public void setRoomsWithBedNets(Integer value)
  {
    if(value == null)
    {
      setValue(ROOMSWITHBEDNETS, "");
    }
    else
    {
      setValue(ROOMSWITHBEDNETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRoomsWithBedNetsWritable()
  {
    return isWritable(ROOMSWITHBEDNETS);
  }
  
  public boolean isRoomsWithBedNetsReadable()
  {
    return isReadable(ROOMSWITHBEDNETS);
  }
  
  public boolean isRoomsWithBedNetsModified()
  {
    return isModified(ROOMSWITHBEDNETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRoomsWithBedNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMSWITHBEDNETS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.irs.ZoneSprayDTO getSpray()
  {
    if(getValue(SPRAY) == null || getValue(SPRAY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.ZoneSprayDTO.get(getRequest(), getValue(SPRAY));
    }
  }
  
  public String getSprayId()
  {
    return getValue(SPRAY);
  }
  
  public void setSpray(dss.vector.solutions.irs.ZoneSprayDTO value)
  {
    if(value == null)
    {
      setValue(SPRAY, "");
    }
    else
    {
      setValue(SPRAY, value.getId());
    }
  }
  
  public boolean isSprayWritable()
  {
    return isWritable(SPRAY);
  }
  
  public boolean isSprayReadable()
  {
    return isReadable(SPRAY);
  }
  
  public boolean isSprayModified()
  {
    return isModified(SPRAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSprayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SprayTeamDTO getSprayTeam()
  {
    if(getValue(SPRAYTEAM) == null || getValue(SPRAYTEAM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayTeamDTO.get(getRequest(), getValue(SPRAYTEAM));
    }
  }
  
  public String getSprayTeamId()
  {
    return getValue(SPRAYTEAM);
  }
  
  public void setSprayTeam(dss.vector.solutions.irs.SprayTeamDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYTEAM, "");
    }
    else
    {
      setValue(SPRAYTEAM, value.getId());
    }
  }
  
  public boolean isSprayTeamWritable()
  {
    return isWritable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamReadable()
  {
    return isReadable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamModified()
  {
    return isModified(SPRAYTEAM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSprayTeamMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYTEAM).getAttributeMdDTO();
  }
  
  public Integer getSprayedHouseholds()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDHOUSEHOLDS));
  }
  
  public void setSprayedHouseholds(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDHOUSEHOLDS, "");
    }
    else
    {
      setValue(SPRAYEDHOUSEHOLDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedHouseholdsWritable()
  {
    return isWritable(SPRAYEDHOUSEHOLDS);
  }
  
  public boolean isSprayedHouseholdsReadable()
  {
    return isReadable(SPRAYEDHOUSEHOLDS);
  }
  
  public boolean isSprayedHouseholdsModified()
  {
    return isModified(SPRAYEDHOUSEHOLDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedHouseholdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDHOUSEHOLDS).getAttributeMdDTO();
  }
  
  public Integer getSprayedRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDROOMS));
  }
  
  public void setSprayedRooms(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDROOMS, "");
    }
    else
    {
      setValue(SPRAYEDROOMS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedRoomsWritable()
  {
    return isWritable(SPRAYEDROOMS);
  }
  
  public boolean isSprayedRoomsReadable()
  {
    return isReadable(SPRAYEDROOMS);
  }
  
  public boolean isSprayedRoomsModified()
  {
    return isModified(SPRAYEDROOMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedRoomsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDROOMS).getAttributeMdDTO();
  }
  
  public Integer getSprayedStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDSTRUCTURES));
  }
  
  public void setSprayedStructures(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDSTRUCTURES, "");
    }
    else
    {
      setValue(SPRAYEDSTRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedStructuresWritable()
  {
    return isWritable(SPRAYEDSTRUCTURES);
  }
  
  public boolean isSprayedStructuresReadable()
  {
    return isReadable(SPRAYEDSTRUCTURES);
  }
  
  public boolean isSprayedStructuresModified()
  {
    return isModified(SPRAYEDSTRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSprayedStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDSTRUCTURES).getAttributeMdDTO();
  }
  
  public Integer getStructures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURES));
  }
  
  public void setStructures(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURES, "");
    }
    else
    {
      setValue(STRUCTURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresWritable()
  {
    return isWritable(STRUCTURES);
  }
  
  public boolean isStructuresReadable()
  {
    return isReadable(STRUCTURES);
  }
  
  public boolean isStructuresModified()
  {
    return isModified(STRUCTURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURES).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedFuneral()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDFUNERAL));
  }
  
  public void setStructuresNotSprayedFuneral(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDFUNERAL, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDFUNERAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedFuneralWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDFUNERAL);
  }
  
  public boolean isStructuresNotSprayedFuneralReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDFUNERAL);
  }
  
  public boolean isStructuresNotSprayedFuneralModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDFUNERAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedFuneralMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDFUNERAL).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDLOCKED));
  }
  
  public void setStructuresNotSprayedLocked(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDLOCKED, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDLOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedLockedWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDLOCKED);
  }
  
  public boolean isStructuresNotSprayedLockedReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDLOCKED);
  }
  
  public boolean isStructuresNotSprayedLockedModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDLOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDLOCKED).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedNoOneHome()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDNOONEHOME));
  }
  
  public void setStructuresNotSprayedNoOneHome(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDNOONEHOME, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDNOONEHOME, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedNoOneHomeWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDNOONEHOME);
  }
  
  public boolean isStructuresNotSprayedNoOneHomeReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDNOONEHOME);
  }
  
  public boolean isStructuresNotSprayedNoOneHomeModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDNOONEHOME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedNoOneHomeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDNOONEHOME).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDOTHER));
  }
  
  public void setStructuresNotSprayedOther(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDOTHER, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDOTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedOtherWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDOTHER);
  }
  
  public boolean isStructuresNotSprayedOtherReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDOTHER);
  }
  
  public boolean isStructuresNotSprayedOtherModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDOTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDOTHER).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDREFUSED));
  }
  
  public void setStructuresNotSprayedRefused(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDREFUSED, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDREFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedRefusedWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDREFUSED);
  }
  
  public boolean isStructuresNotSprayedRefusedReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDREFUSED);
  }
  
  public boolean isStructuresNotSprayedRefusedModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDREFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDREFUSED).getAttributeMdDTO();
  }
  
  public Integer getStructuresNotSprayedSick()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STRUCTURESNOTSPRAYEDSICK));
  }
  
  public void setStructuresNotSprayedSick(Integer value)
  {
    if(value == null)
    {
      setValue(STRUCTURESNOTSPRAYEDSICK, "");
    }
    else
    {
      setValue(STRUCTURESNOTSPRAYEDSICK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStructuresNotSprayedSickWritable()
  {
    return isWritable(STRUCTURESNOTSPRAYEDSICK);
  }
  
  public boolean isStructuresNotSprayedSickReadable()
  {
    return isReadable(STRUCTURESNOTSPRAYEDSICK);
  }
  
  public boolean isStructuresNotSprayedSickModified()
  {
    return isModified(STRUCTURESNOTSPRAYEDSICK);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getStructuresNotSprayedSickMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STRUCTURESNOTSPRAYEDSICK).getAttributeMdDTO();
  }
  
  public Integer getTarget()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
  }
  
  public void setTarget(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTargetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.TeamMemberDTO getTeamLeader()
  {
    if(getValue(TEAMLEADER) == null || getValue(TEAMLEADER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.TeamMemberDTO.get(getRequest(), getValue(TEAMLEADER));
    }
  }
  
  public String getTeamLeaderId()
  {
    return getValue(TEAMLEADER);
  }
  
  public void setTeamLeader(dss.vector.solutions.irs.TeamMemberDTO value)
  {
    if(value == null)
    {
      setValue(TEAMLEADER, "");
    }
    else
    {
      setValue(TEAMLEADER, value.getId());
    }
  }
  
  public boolean isTeamLeaderWritable()
  {
    return isWritable(TEAMLEADER);
  }
  
  public boolean isTeamLeaderReadable()
  {
    return isReadable(TEAMLEADER);
  }
  
  public boolean isTeamLeaderModified()
  {
    return isModified(TEAMLEADER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTeamLeaderMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TEAMLEADER).getAttributeMdDTO();
  }
  
  public Integer getUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USED));
  }
  
  public void setUsed(Integer value)
  {
    if(value == null)
    {
      setValue(USED, "");
    }
    else
    {
      setValue(USED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUsedWritable()
  {
    return isWritable(USED);
  }
  
  public boolean isUsedReadable()
  {
    return isReadable(USED);
  }
  
  public boolean isUsedModified()
  {
    return isModified(USED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(USED).getAttributeMdDTO();
  }
  
  public Integer getVerandas()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDAS));
  }
  
  public void setVerandas(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDAS, "");
    }
    else
    {
      setValue(VERANDAS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasWritable()
  {
    return isWritable(VERANDAS);
  }
  
  public boolean isVerandasReadable()
  {
    return isReadable(VERANDAS);
  }
  
  public boolean isVerandasModified()
  {
    return isModified(VERANDAS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDAS).getAttributeMdDTO();
  }
  
  public Integer getVerandasLocked()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASLOCKED));
  }
  
  public void setVerandasLocked(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASLOCKED, "");
    }
    else
    {
      setValue(VERANDASLOCKED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasLockedWritable()
  {
    return isWritable(VERANDASLOCKED);
  }
  
  public boolean isVerandasLockedReadable()
  {
    return isReadable(VERANDASLOCKED);
  }
  
  public boolean isVerandasLockedModified()
  {
    return isModified(VERANDASLOCKED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasLockedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASLOCKED).getAttributeMdDTO();
  }
  
  public Integer getVerandasOther()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASOTHER));
  }
  
  public void setVerandasOther(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASOTHER, "");
    }
    else
    {
      setValue(VERANDASOTHER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasOtherWritable()
  {
    return isWritable(VERANDASOTHER);
  }
  
  public boolean isVerandasOtherReadable()
  {
    return isReadable(VERANDASOTHER);
  }
  
  public boolean isVerandasOtherModified()
  {
    return isModified(VERANDASOTHER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasOtherMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASOTHER).getAttributeMdDTO();
  }
  
  public Integer getVerandasRefused()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASREFUSED));
  }
  
  public void setVerandasRefused(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASREFUSED, "");
    }
    else
    {
      setValue(VERANDASREFUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasRefusedWritable()
  {
    return isWritable(VERANDASREFUSED);
  }
  
  public boolean isVerandasRefusedReadable()
  {
    return isReadable(VERANDASREFUSED);
  }
  
  public boolean isVerandasRefusedModified()
  {
    return isModified(VERANDASREFUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasRefusedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASREFUSED).getAttributeMdDTO();
  }
  
  public Integer getVerandasSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VERANDASSPRAYED));
  }
  
  public void setVerandasSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(VERANDASSPRAYED, "");
    }
    else
    {
      setValue(VERANDASSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVerandasSprayedWritable()
  {
    return isWritable(VERANDASSPRAYED);
  }
  
  public boolean isVerandasSprayedReadable()
  {
    return isReadable(VERANDASSPRAYED);
  }
  
  public boolean isVerandasSprayedModified()
  {
    return isModified(VERANDASSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVerandasSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VERANDASSPRAYED).getAttributeMdDTO();
  }
  
  public Integer getWrongSurface()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WRONGSURFACE));
  }
  
  public void setWrongSurface(Integer value)
  {
    if(value == null)
    {
      setValue(WRONGSURFACE, "");
    }
    else
    {
      setValue(WRONGSURFACE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWrongSurfaceWritable()
  {
    return isWritable(WRONGSURFACE);
  }
  
  public boolean isWrongSurfaceReadable()
  {
    return isReadable(WRONGSURFACE);
  }
  
  public boolean isWrongSurfaceModified()
  {
    return isModified(WRONGSURFACE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWrongSurfaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WRONGSURFACE).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.irs.TeamSprayStatusViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayStatusViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamSprayStatusViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayStatusViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamSprayStatusViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayStatusViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.irs.TeamSprayStatusDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.TeamSprayStatusDTO) dto;
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
  
  public static dss.vector.solutions.irs.TeamSprayStatusQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.TeamSprayStatusQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.TeamSprayStatusDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.TeamSprayStatusDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
