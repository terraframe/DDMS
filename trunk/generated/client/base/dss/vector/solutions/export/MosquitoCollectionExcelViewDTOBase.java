package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 236582535)
public abstract class MosquitoCollectionExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.MosquitoCollectionExcelView";
  private static final long serialVersionUID = 236582535;
  
  protected MosquitoCollectionExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String EGGS = "eggs";
  public static java.lang.String FEMALE = "female";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String LARVAE = "larvae";
  public static java.lang.String LIFESTAGE = "lifeStage";
  public static java.lang.String MALE = "male";
  public static java.lang.String PUPAE = "pupae";
  public static java.lang.String SUBCOLLECTIONID = "subCollectionId";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String UNKNOWNS = "unknowns";
  public Boolean getAbundance()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ABUNDANCE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getAbundanceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ABUNDANCE).getAttributeMdDTO();
  }
  
  public java.util.Date getCollectionDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COLLECTIONDATE));
  }
  
  public void setCollectionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COLLECTIONDATE, "");
    }
    else
    {
      setValue(COLLECTIONDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getCollectionDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(COLLECTIONDATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getCollectionMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONMETHOD).getAttributeMdDTO();
  }
  
  public Integer getEggs()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EGGS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getEggsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EGGS).getAttributeMdDTO();
  }
  
  public Integer getFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALE));
  }
  
  public void setFemale(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALE, "");
    }
    else
    {
      setValue(FEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemaleWritable()
  {
    return isWritable(FEMALE);
  }
  
  public boolean isFemaleReadable()
  {
    return isReadable(FEMALE);
  }
  
  public boolean isFemaleModified()
  {
    return isModified(FEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getIdentMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTMETHOD).getAttributeMdDTO();
  }
  
  public Integer getLarvae()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LARVAE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getLarvaeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LARVAE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLifeStageMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LIFESTAGE).getAttributeMdDTO();
  }
  
  public Integer getMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MALE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MALE).getAttributeMdDTO();
  }
  
  public Integer getPupae()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUPAE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPupaeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PUPAE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSubCollectionIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUBCOLLECTIONID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTaxonMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TAXON).getAttributeMdDTO();
  }
  
  public Integer getUnknowns()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNKNOWNS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getUnknownsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNKNOWNS).getAttributeMdDTO();
  }
  
  public static MosquitoCollectionExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
