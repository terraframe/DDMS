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
package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -1442184340)
public abstract class CollectionPremiseDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.CollectionPremise";
  private static final long serialVersionUID = -1442184340;
  
  protected CollectionPremiseDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected CollectionPremiseDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NUMBEREXAMINED = "numberExamined";
  public static java.lang.String NUMBERINHABITANTS = "numberInhabitants";
  public static java.lang.String NUMBERWITHIMMATURES = "numberWithImmatures";
  public static java.lang.String NUMBERWITHLARVAE = "numberWithLarvae";
  public static java.lang.String NUMBERWITHPUPAE = "numberWithPupae";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PREMISESIZE = "premiseSize";
  public static java.lang.String PREMISETYPE = "premiseType";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public dss.vector.solutions.entomology.ImmatureCollectionDTO getCollection()
  {
    if(getValue(COLLECTION) == null || getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.ImmatureCollectionDTO.get(getRequest(), getValue(COLLECTION));
    }
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTION);
  }
  
  public void setCollection(dss.vector.solutions.entomology.ImmatureCollectionDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTION, "");
    }
    else
    {
      setValue(COLLECTION, value.getId());
    }
  }
  
  public boolean isCollectionWritable()
  {
    return isWritable(COLLECTION);
  }
  
  public boolean isCollectionReadable()
  {
    return isReadable(COLLECTION);
  }
  
  public boolean isCollectionModified()
  {
    return isModified(COLLECTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTION).getAttributeMdDTO();
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
  
  public Integer getNumberExamined()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEREXAMINED));
  }
  
  public void setNumberExamined(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEREXAMINED, "");
    }
    else
    {
      setValue(NUMBEREXAMINED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberExaminedWritable()
  {
    return isWritable(NUMBEREXAMINED);
  }
  
  public boolean isNumberExaminedReadable()
  {
    return isReadable(NUMBEREXAMINED);
  }
  
  public boolean isNumberExaminedModified()
  {
    return isModified(NUMBEREXAMINED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberExaminedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEREXAMINED).getAttributeMdDTO();
  }
  
  public Integer getNumberInhabitants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERINHABITANTS));
  }
  
  public void setNumberInhabitants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERINHABITANTS, "");
    }
    else
    {
      setValue(NUMBERINHABITANTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberInhabitantsWritable()
  {
    return isWritable(NUMBERINHABITANTS);
  }
  
  public boolean isNumberInhabitantsReadable()
  {
    return isReadable(NUMBERINHABITANTS);
  }
  
  public boolean isNumberInhabitantsModified()
  {
    return isModified(NUMBERINHABITANTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberInhabitantsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERINHABITANTS).getAttributeMdDTO();
  }
  
  public Integer getNumberWithImmatures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHIMMATURES));
  }
  
  public void setNumberWithImmatures(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHIMMATURES, "");
    }
    else
    {
      setValue(NUMBERWITHIMMATURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithImmaturesWritable()
  {
    return isWritable(NUMBERWITHIMMATURES);
  }
  
  public boolean isNumberWithImmaturesReadable()
  {
    return isReadable(NUMBERWITHIMMATURES);
  }
  
  public boolean isNumberWithImmaturesModified()
  {
    return isModified(NUMBERWITHIMMATURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithImmaturesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHIMMATURES).getAttributeMdDTO();
  }
  
  public Integer getNumberWithLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHLARVAE));
  }
  
  public void setNumberWithLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHLARVAE, "");
    }
    else
    {
      setValue(NUMBERWITHLARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithLarvaeWritable()
  {
    return isWritable(NUMBERWITHLARVAE);
  }
  
  public boolean isNumberWithLarvaeReadable()
  {
    return isReadable(NUMBERWITHLARVAE);
  }
  
  public boolean isNumberWithLarvaeModified()
  {
    return isModified(NUMBERWITHLARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHLARVAE).getAttributeMdDTO();
  }
  
  public Integer getNumberWithPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHPUPAE));
  }
  
  public void setNumberWithPupae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHPUPAE, "");
    }
    else
    {
      setValue(NUMBERWITHPUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithPupaeWritable()
  {
    return isWritable(NUMBERWITHPUPAE);
  }
  
  public boolean isNumberWithPupaeReadable()
  {
    return isReadable(NUMBERWITHPUPAE);
  }
  
  public boolean isNumberWithPupaeModified()
  {
    return isModified(NUMBERWITHPUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHPUPAE).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getPremiseSize()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PREMISESIZE));
  }
  
  public void setPremiseSize(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(PREMISESIZE, "");
    }
    else
    {
      setValue(PREMISESIZE, value.toString());
    }
  }
  
  public boolean isPremiseSizeWritable()
  {
    return isWritable(PREMISESIZE);
  }
  
  public boolean isPremiseSizeReadable()
  {
    return isReadable(PREMISESIZE);
  }
  
  public boolean isPremiseSizeModified()
  {
    return isModified(PREMISESIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPremiseSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(PREMISESIZE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPremiseType()
  {
    if(getValue(PREMISETYPE) == null || getValue(PREMISETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PREMISETYPE));
    }
  }
  
  public String getPremiseTypeId()
  {
    return getValue(PREMISETYPE);
  }
  
  public void setPremiseType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PREMISETYPE, "");
    }
    else
    {
      setValue(PREMISETYPE, value.getId());
    }
  }
  
  public boolean isPremiseTypeWritable()
  {
    return isWritable(PREMISETYPE);
  }
  
  public boolean isPremiseTypeReadable()
  {
    return isReadable(PREMISETYPE);
  }
  
  public boolean isPremiseTypeModified()
  {
    return isModified(PREMISETYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPremiseTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PREMISETYPE).getAttributeMdDTO();
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
  
  public static dss.vector.solutions.entomology.CollectionPremiseDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.CollectionPremiseDTO) dto;
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
  
  public static dss.vector.solutions.entomology.CollectionPremiseQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.CollectionPremiseQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.entomology.CollectionPremiseDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.CollectionPremiseDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.CollectionPremiseDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.CollectionPremiseDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.CollectionPremiseDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.CollectionPremiseDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.CollectionPremiseDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
