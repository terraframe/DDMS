package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 306778403)
public abstract class MosquitoCollectionExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.MosquitoCollectionExcelView";
  private static final long serialVersionUID = 306778403;
  
  protected MosquitoCollectionExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
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
  public static java.lang.String DATELASTSPRAYED = "dateLastSprayed";
  public static java.lang.String DISECTED = "disected";
  public static java.lang.String EGGS = "eggs";
  public static java.lang.String FEMALESFED = "femalesFed";
  public static java.lang.String FEMALESGRAVID = "femalesGravid";
  public static java.lang.String FEMALESHALFGRAVID = "femalesHalfGravid";
  public static java.lang.String FEMALESUNFED = "femalesUnfed";
  public static java.lang.String FEMALESUNKNOWN = "femalesUnknown";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String INSECTICIDEBRAND = "insecticideBrand";
  public static java.lang.String LARVAE = "larvae";
  public static java.lang.String LIFESTAGE = "lifeStage";
  public static java.lang.String MALE = "male";
  public static java.lang.String NUMBEROFANIMALOCCUPANTS = "numberOfAnimalOccupants";
  public static java.lang.String NUMBEROFHUMANOCCUPANTS = "numberOfHumanOccupants";
  public static java.lang.String NUMBEROFLLINS = "numberOfLLINs";
  public static java.lang.String PAROUS = "parous";
  public static java.lang.String PUPAE = "pupae";
  public static java.lang.String SUBCOLLECTIONID = "subCollectionId";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String UNKNOWNS = "unknowns";
  public static java.lang.String WALLTYPE = "wallType";
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
  
  public String getCollectionMethod()
  {
    return getValue(COLLECTIONMETHOD);
  }
  
  public void setCollectionMethod(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONMETHOD, "");
    }
    else
    {
      setValue(COLLECTIONMETHOD, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONMETHOD).getAttributeMdDTO();
  }
  
  public String getCollectionRound()
  {
    return getValue(COLLECTIONROUND);
  }
  
  public void setCollectionRound(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONROUND, "");
    }
    else
    {
      setValue(COLLECTIONROUND, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionRoundMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONROUND).getAttributeMdDTO();
  }
  
  public String getCollectionType()
  {
    return getValue(COLLECTIONTYPE);
  }
  
  public void setCollectionType(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONTYPE, "");
    }
    else
    {
      setValue(COLLECTIONTYPE, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONTYPE).getAttributeMdDTO();
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
  
  public Integer getDisected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISECTED));
  }
  
  public void setDisected(Integer value)
  {
    if(value == null)
    {
      setValue(DISECTED, "");
    }
    else
    {
      setValue(DISECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDisectedWritable()
  {
    return isWritable(DISECTED);
  }
  
  public boolean isDisectedReadable()
  {
    return isReadable(DISECTED);
  }
  
  public boolean isDisectedModified()
  {
    return isModified(DISECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getDisectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DISECTED).getAttributeMdDTO();
  }
  
  public Integer getEggs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EGGS));
  }
  
  public void setEggs(Integer value)
  {
    if(value == null)
    {
      setValue(EGGS, "");
    }
    else
    {
      setValue(EGGS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEggsWritable()
  {
    return isWritable(EGGS);
  }
  
  public boolean isEggsReadable()
  {
    return isReadable(EGGS);
  }
  
  public boolean isEggsModified()
  {
    return isModified(EGGS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getEggsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EGGS).getAttributeMdDTO();
  }
  
  public Integer getFemalesFed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESFED));
  }
  
  public void setFemalesFed(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESFED, "");
    }
    else
    {
      setValue(FEMALESFED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesFedWritable()
  {
    return isWritable(FEMALESFED);
  }
  
  public boolean isFemalesFedReadable()
  {
    return isReadable(FEMALESFED);
  }
  
  public boolean isFemalesFedModified()
  {
    return isModified(FEMALESFED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesFedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESFED).getAttributeMdDTO();
  }
  
  public Integer getFemalesGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESGRAVID));
  }
  
  public void setFemalesGravid(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESGRAVID, "");
    }
    else
    {
      setValue(FEMALESGRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesGravidWritable()
  {
    return isWritable(FEMALESGRAVID);
  }
  
  public boolean isFemalesGravidReadable()
  {
    return isReadable(FEMALESGRAVID);
  }
  
  public boolean isFemalesGravidModified()
  {
    return isModified(FEMALESGRAVID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesGravidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESGRAVID).getAttributeMdDTO();
  }
  
  public Integer getFemalesHalfGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESHALFGRAVID));
  }
  
  public void setFemalesHalfGravid(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESHALFGRAVID, "");
    }
    else
    {
      setValue(FEMALESHALFGRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesHalfGravidWritable()
  {
    return isWritable(FEMALESHALFGRAVID);
  }
  
  public boolean isFemalesHalfGravidReadable()
  {
    return isReadable(FEMALESHALFGRAVID);
  }
  
  public boolean isFemalesHalfGravidModified()
  {
    return isModified(FEMALESHALFGRAVID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesHalfGravidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESHALFGRAVID).getAttributeMdDTO();
  }
  
  public Integer getFemalesUnfed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNFED));
  }
  
  public void setFemalesUnfed(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESUNFED, "");
    }
    else
    {
      setValue(FEMALESUNFED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesUnfedWritable()
  {
    return isWritable(FEMALESUNFED);
  }
  
  public boolean isFemalesUnfedReadable()
  {
    return isReadable(FEMALESUNFED);
  }
  
  public boolean isFemalesUnfedModified()
  {
    return isModified(FEMALESUNFED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesUnfedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESUNFED).getAttributeMdDTO();
  }
  
  public Integer getFemalesUnknown()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNKNOWN));
  }
  
  public void setFemalesUnknown(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESUNKNOWN, "");
    }
    else
    {
      setValue(FEMALESUNKNOWN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesUnknownWritable()
  {
    return isWritable(FEMALESUNKNOWN);
  }
  
  public boolean isFemalesUnknownReadable()
  {
    return isReadable(FEMALESUNKNOWN);
  }
  
  public boolean isFemalesUnknownModified()
  {
    return isModified(FEMALESUNKNOWN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesUnknownMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESUNKNOWN).getAttributeMdDTO();
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
  
  public String getIdentMethod()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void setIdentMethod(String value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value);
    }
  }
  
  public boolean isIdentMethodWritable()
  {
    return isWritable(IDENTMETHOD);
  }
  
  public boolean isIdentMethodReadable()
  {
    return isReadable(IDENTMETHOD);
  }
  
  public boolean isIdentMethodModified()
  {
    return isModified(IDENTMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIdentMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTMETHOD).getAttributeMdDTO();
  }
  
  public String getInsecticideBrand()
  {
    return getValue(INSECTICIDEBRAND);
  }
  
  public void setInsecticideBrand(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEBRAND, "");
    }
    else
    {
      setValue(INSECTICIDEBRAND, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInsecticideBrandMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICIDEBRAND).getAttributeMdDTO();
  }
  
  public Integer getLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LARVAE));
  }
  
  public void setLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(LARVAE, "");
    }
    else
    {
      setValue(LARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLarvaeWritable()
  {
    return isWritable(LARVAE);
  }
  
  public boolean isLarvaeReadable()
  {
    return isReadable(LARVAE);
  }
  
  public boolean isLarvaeModified()
  {
    return isModified(LARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LARVAE).getAttributeMdDTO();
  }
  
  public String getLifeStage()
  {
    return getValue(LIFESTAGE);
  }
  
  public void setLifeStage(String value)
  {
    if(value == null)
    {
      setValue(LIFESTAGE, "");
    }
    else
    {
      setValue(LIFESTAGE, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLifeStageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LIFESTAGE).getAttributeMdDTO();
  }
  
  public Integer getMale()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MALE));
  }
  
  public void setMale(Integer value)
  {
    if(value == null)
    {
      setValue(MALE, "");
    }
    else
    {
      setValue(MALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMaleWritable()
  {
    return isWritable(MALE);
  }
  
  public boolean isMaleReadable()
  {
    return isReadable(MALE);
  }
  
  public boolean isMaleModified()
  {
    return isModified(MALE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getMaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MALE).getAttributeMdDTO();
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
  
  public Integer getParous()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PAROUS));
  }
  
  public void setParous(Integer value)
  {
    if(value == null)
    {
      setValue(PAROUS, "");
    }
    else
    {
      setValue(PAROUS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isParousWritable()
  {
    return isWritable(PAROUS);
  }
  
  public boolean isParousReadable()
  {
    return isReadable(PAROUS);
  }
  
  public boolean isParousModified()
  {
    return isModified(PAROUS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getParousMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PAROUS).getAttributeMdDTO();
  }
  
  public Integer getPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUPAE));
  }
  
  public void setPupae(Integer value)
  {
    if(value == null)
    {
      setValue(PUPAE, "");
    }
    else
    {
      setValue(PUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPupaeWritable()
  {
    return isWritable(PUPAE);
  }
  
  public boolean isPupaeReadable()
  {
    return isReadable(PUPAE);
  }
  
  public boolean isPupaeModified()
  {
    return isModified(PUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PUPAE).getAttributeMdDTO();
  }
  
  public String getSubCollectionId()
  {
    return getValue(SUBCOLLECTIONID);
  }
  
  public void setSubCollectionId(String value)
  {
    if(value == null)
    {
      setValue(SUBCOLLECTIONID, "");
    }
    else
    {
      setValue(SUBCOLLECTIONID, value);
    }
  }
  
  public boolean isSubCollectionIdWritable()
  {
    return isWritable(SUBCOLLECTIONID);
  }
  
  public boolean isSubCollectionIdReadable()
  {
    return isReadable(SUBCOLLECTIONID);
  }
  
  public boolean isSubCollectionIdModified()
  {
    return isModified(SUBCOLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSubCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUBCOLLECTIONID).getAttributeMdDTO();
  }
  
  public String getTaxon()
  {
    return getValue(TAXON);
  }
  
  public void setTaxon(String value)
  {
    if(value == null)
    {
      setValue(TAXON, "");
    }
    else
    {
      setValue(TAXON, value);
    }
  }
  
  public boolean isTaxonWritable()
  {
    return isWritable(TAXON);
  }
  
  public boolean isTaxonReadable()
  {
    return isReadable(TAXON);
  }
  
  public boolean isTaxonModified()
  {
    return isModified(TAXON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTaxonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TAXON).getAttributeMdDTO();
  }
  
  public Integer getUnknowns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNKNOWNS));
  }
  
  public void setUnknowns(Integer value)
  {
    if(value == null)
    {
      setValue(UNKNOWNS, "");
    }
    else
    {
      setValue(UNKNOWNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnknownsWritable()
  {
    return isWritable(UNKNOWNS);
  }
  
  public boolean isUnknownsReadable()
  {
    return isReadable(UNKNOWNS);
  }
  
  public boolean isUnknownsModified()
  {
    return isModified(UNKNOWNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUnknownsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNKNOWNS).getAttributeMdDTO();
  }
  
  public String getWallType()
  {
    return getValue(WALLTYPE);
  }
  
  public void setWallType(String value)
  {
    if(value == null)
    {
      setValue(WALLTYPE, "");
    }
    else
    {
      setValue(WALLTYPE, value);
    }
  }
  
  public boolean isWallTypeWritable()
  {
    return isWritable(WALLTYPE);
  }
  
  public boolean isWallTypeReadable()
  {
    return isReadable(WALLTYPE);
  }
  
  public boolean isWallTypeModified()
  {
    return isModified(WALLTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWallTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WALLTYPE).getAttributeMdDTO();
  }
  
  public static MosquitoCollectionExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MosquitoCollectionExcelViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
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
  
}
