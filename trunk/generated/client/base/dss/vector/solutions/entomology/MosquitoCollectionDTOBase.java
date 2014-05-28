package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -25563932)
public abstract class MosquitoCollectionDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollection";
  private static final long serialVersionUID = -25563932;
  
  protected MosquitoCollectionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MosquitoCollectionDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ABUNDANCE = "abundance";
  public static java.lang.String COLLECTIONDATE = "collectionDate";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
  public static java.lang.String COLLECTIONROUND = "collectionRound";
  public static java.lang.String COLLECTIONTYPE = "collectionType";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DATELASTSPRAYED = "dateLastSprayed";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDEBRAND = "insecticideBrand";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LIFESTAGE = "lifeStage";
  public static java.lang.String LIFESTAGENAME = "lifeStageName";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NUMBEROFANIMALOCCUPANTS = "numberOfAnimalOccupants";
  public static java.lang.String NUMBEROFHUMANOCCUPANTS = "numberOfHumanOccupants";
  public static java.lang.String NUMBEROFLLINS = "numberOfLLINs";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String RESISTANCEASSAYCOMMENTS = "resistanceAssayComments";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public Boolean getAbundance()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ABUNDANCE));
  }
  
  public void setAbundance(Boolean value)
  {
    if(value == null)
    {
      setValue(ABUNDANCE, "");
    }
    else
    {
      setValue(ABUNDANCE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isAbundanceWritable()
  {
    return isWritable(ABUNDANCE);
  }
  
  public boolean isAbundanceReadable()
  {
    return isReadable(ABUNDANCE);
  }
  
  public boolean isAbundanceModified()
  {
    return isModified(ABUNDANCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getAbundanceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ABUNDANCE).getAttributeMdDTO();
  }
  
  public java.util.Date getCollectionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COLLECTIONDATE));
  }
  
  public void setCollectionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COLLECTIONDATE, "");
    }
    else
    {
      setValue(COLLECTIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCollectionDateWritable()
  {
    return isWritable(COLLECTIONDATE);
  }
  
  public boolean isCollectionDateReadable()
  {
    return isReadable(COLLECTIONDATE);
  }
  
  public boolean isCollectionDateModified()
  {
    return isModified(COLLECTIONDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCollectionDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(COLLECTIONDATE).getAttributeMdDTO();
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void setCollectionId(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONID, "");
    }
    else
    {
      setValue(COLLECTIONID, value);
    }
  }
  
  public boolean isCollectionIdWritable()
  {
    return isWritable(COLLECTIONID);
  }
  
  public boolean isCollectionIdReadable()
  {
    return isReadable(COLLECTIONID);
  }
  
  public boolean isCollectionIdModified()
  {
    return isModified(COLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCollectionMethod()
  {
    if(getValue(COLLECTIONMETHOD) == null || getValue(COLLECTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(COLLECTIONMETHOD));
    }
  }
  
  public String getCollectionMethodId()
  {
    return getValue(COLLECTIONMETHOD);
  }
  
  public void setCollectionMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTIONMETHOD, "");
    }
    else
    {
      setValue(COLLECTIONMETHOD, value.getId());
    }
  }
  
  public boolean isCollectionMethodWritable()
  {
    return isWritable(COLLECTIONMETHOD);
  }
  
  public boolean isCollectionMethodReadable()
  {
    return isReadable(COLLECTIONMETHOD);
  }
  
  public boolean isCollectionMethodModified()
  {
    return isModified(COLLECTIONMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTIONMETHOD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCollectionRound()
  {
    if(getValue(COLLECTIONROUND) == null || getValue(COLLECTIONROUND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(COLLECTIONROUND));
    }
  }
  
  public String getCollectionRoundId()
  {
    return getValue(COLLECTIONROUND);
  }
  
  public void setCollectionRound(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTIONROUND, "");
    }
    else
    {
      setValue(COLLECTIONROUND, value.getId());
    }
  }
  
  public boolean isCollectionRoundWritable()
  {
    return isWritable(COLLECTIONROUND);
  }
  
  public boolean isCollectionRoundReadable()
  {
    return isReadable(COLLECTIONROUND);
  }
  
  public boolean isCollectionRoundModified()
  {
    return isModified(COLLECTIONROUND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionRoundMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTIONROUND).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCollectionType()
  {
    if(getValue(COLLECTIONTYPE) == null || getValue(COLLECTIONTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(COLLECTIONTYPE));
    }
  }
  
  public String getCollectionTypeId()
  {
    return getValue(COLLECTIONTYPE);
  }
  
  public void setCollectionType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTIONTYPE, "");
    }
    else
    {
      setValue(COLLECTIONTYPE, value.getId());
    }
  }
  
  public boolean isCollectionTypeWritable()
  {
    return isWritable(COLLECTIONTYPE);
  }
  
  public boolean isCollectionTypeReadable()
  {
    return isReadable(COLLECTIONTYPE);
  }
  
  public boolean isCollectionTypeModified()
  {
    return isModified(COLLECTIONTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTIONTYPE).getAttributeMdDTO();
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
  
  public java.util.Date getDateLastSprayed()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATELASTSPRAYED));
  }
  
  public void setDateLastSprayed(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATELASTSPRAYED, "");
    }
    else
    {
      setValue(DATELASTSPRAYED, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDateLastSprayedWritable()
  {
    return isWritable(DATELASTSPRAYED);
  }
  
  public boolean isDateLastSprayedReadable()
  {
    return isReadable(DATELASTSPRAYED);
  }
  
  public boolean isDateLastSprayedModified()
  {
    return isModified(DATELASTSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDateLastSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DATELASTSPRAYED).getAttributeMdDTO();
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
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.InsecticideBrandDTO getInsecticideBrand()
  {
    if(getValue(INSECTICIDEBRAND) == null || getValue(INSECTICIDEBRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(INSECTICIDEBRAND));
    }
  }
  
  public String getInsecticideBrandId()
  {
    return getValue(INSECTICIDEBRAND);
  }
  
  public void setInsecticideBrand(dss.vector.solutions.irs.InsecticideBrandDTO value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEBRAND, "");
    }
    else
    {
      setValue(INSECTICIDEBRAND, value.getId());
    }
  }
  
  public boolean isInsecticideBrandWritable()
  {
    return isWritable(INSECTICIDEBRAND);
  }
  
  public boolean isInsecticideBrandReadable()
  {
    return isReadable(INSECTICIDEBRAND);
  }
  
  public boolean isInsecticideBrandModified()
  {
    return isModified(INSECTICIDEBRAND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInsecticideBrandMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INSECTICIDEBRAND).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.entomology.LifeStageDTO> getLifeStage()
  {
    return (java.util.List<dss.vector.solutions.entomology.LifeStageDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.entomology.LifeStageDTO.CLASS, getEnumNames(LIFESTAGE));
  }
  
  public java.util.List<String> getLifeStageEnumNames()
  {
    return getEnumNames(LIFESTAGE);
  }
  
  public void addLifeStage(dss.vector.solutions.entomology.LifeStageDTO enumDTO)
  {
    addEnumItem(LIFESTAGE, enumDTO.toString());
  }
  
  public void removeLifeStage(dss.vector.solutions.entomology.LifeStageDTO enumDTO)
  {
    removeEnumItem(LIFESTAGE, enumDTO.toString());
  }
  
  public void clearLifeStage()
  {
    clearEnum(LIFESTAGE);
  }
  
  public boolean isLifeStageWritable()
  {
    return isWritable(LIFESTAGE);
  }
  
  public boolean isLifeStageReadable()
  {
    return isReadable(LIFESTAGE);
  }
  
  public boolean isLifeStageModified()
  {
    return isModified(LIFESTAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getLifeStageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(LIFESTAGE).getAttributeMdDTO();
  }
  
  public String getLifeStageName()
  {
    return getValue(LIFESTAGENAME);
  }
  
  public void setLifeStageName(String value)
  {
    if(value == null)
    {
      setValue(LIFESTAGENAME, "");
    }
    else
    {
      setValue(LIFESTAGENAME, value);
    }
  }
  
  public boolean isLifeStageNameWritable()
  {
    return isWritable(LIFESTAGENAME);
  }
  
  public boolean isLifeStageNameReadable()
  {
    return isReadable(LIFESTAGENAME);
  }
  
  public boolean isLifeStageNameModified()
  {
    return isModified(LIFESTAGENAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLifeStageNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LIFESTAGENAME).getAttributeMdDTO();
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
  
  public Integer getNumberOfAnimalOccupants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFANIMALOCCUPANTS));
  }
  
  public void setNumberOfAnimalOccupants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFANIMALOCCUPANTS, "");
    }
    else
    {
      setValue(NUMBEROFANIMALOCCUPANTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfAnimalOccupantsWritable()
  {
    return isWritable(NUMBEROFANIMALOCCUPANTS);
  }
  
  public boolean isNumberOfAnimalOccupantsReadable()
  {
    return isReadable(NUMBEROFANIMALOCCUPANTS);
  }
  
  public boolean isNumberOfAnimalOccupantsModified()
  {
    return isModified(NUMBEROFANIMALOCCUPANTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfAnimalOccupantsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFANIMALOCCUPANTS).getAttributeMdDTO();
  }
  
  public Integer getNumberOfHumanOccupants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFHUMANOCCUPANTS));
  }
  
  public void setNumberOfHumanOccupants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFHUMANOCCUPANTS, "");
    }
    else
    {
      setValue(NUMBEROFHUMANOCCUPANTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfHumanOccupantsWritable()
  {
    return isWritable(NUMBEROFHUMANOCCUPANTS);
  }
  
  public boolean isNumberOfHumanOccupantsReadable()
  {
    return isReadable(NUMBEROFHUMANOCCUPANTS);
  }
  
  public boolean isNumberOfHumanOccupantsModified()
  {
    return isModified(NUMBEROFHUMANOCCUPANTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfHumanOccupantsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFHUMANOCCUPANTS).getAttributeMdDTO();
  }
  
  public Integer getNumberOfLLINs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFLLINS));
  }
  
  public void setNumberOfLLINs(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFLLINS, "");
    }
    else
    {
      setValue(NUMBEROFLLINS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfLLINsWritable()
  {
    return isWritable(NUMBEROFLLINS);
  }
  
  public boolean isNumberOfLLINsReadable()
  {
    return isReadable(NUMBEROFLLINS);
  }
  
  public boolean isNumberOfLLINsModified()
  {
    return isModified(NUMBEROFLLINS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfLLINsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFLLINS).getAttributeMdDTO();
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
  
  public String getResistanceAssayComments()
  {
    return getValue(RESISTANCEASSAYCOMMENTS);
  }
  
  public void setResistanceAssayComments(String value)
  {
    if(value == null)
    {
      setValue(RESISTANCEASSAYCOMMENTS, "");
    }
    else
    {
      setValue(RESISTANCEASSAYCOMMENTS, value);
    }
  }
  
  public boolean isResistanceAssayCommentsWritable()
  {
    return isWritable(RESISTANCEASSAYCOMMENTS);
  }
  
  public boolean isResistanceAssayCommentsReadable()
  {
    return isReadable(RESISTANCEASSAYCOMMENTS);
  }
  
  public boolean isResistanceAssayCommentsModified()
  {
    return isModified(RESISTANCEASSAYCOMMENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getResistanceAssayCommentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(RESISTANCEASSAYCOMMENTS).getAttributeMdDTO();
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
  
  public final dss.vector.solutions.entomology.MosquitoCollectionViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.MosquitoCollectionViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.MosquitoCollectionViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.MosquitoCollectionDTO) dto;
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
  
  public static dss.vector.solutions.entomology.MosquitoCollectionQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.MosquitoCollectionQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
