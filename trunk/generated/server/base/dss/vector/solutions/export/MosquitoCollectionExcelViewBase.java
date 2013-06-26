package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 2055196020)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MosquitoCollectionExcelViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.MosquitoCollectionExcelView";
  public static java.lang.String ABUNDANCE = "abundance";
  public static java.lang.String COLLECTIONDATE = "collectionDate";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
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
  public static java.lang.String LARVAE = "larvae";
  public static java.lang.String LIFESTAGE = "lifeStage";
  public static java.lang.String MALE = "male";
  public static java.lang.String PAROUS = "parous";
  public static java.lang.String PUPAE = "pupae";
  public static java.lang.String SUBCOLLECTIONID = "subCollectionId";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String UNKNOWNS = "unknowns";
  private static final long serialVersionUID = 2055196020;
  
  public MosquitoCollectionExcelViewBase()
  {
    super();
  }
  
  public Boolean getAbundance()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ABUNDANCE));
  }
  
  public void validateAbundance()
  {
    this.validateAttribute(ABUNDANCE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAbundanceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(ABUNDANCE);
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
  
  public java.util.Date getCollectionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COLLECTIONDATE));
  }
  
  public void validateCollectionDate()
  {
    this.validateAttribute(COLLECTIONDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCollectionDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONDATE);
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
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void validateCollectionId()
  {
    this.validateAttribute(COLLECTIONID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCollectionIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONID);
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
  
  public String getCollectionMethod()
  {
    return getValue(COLLECTIONMETHOD);
  }
  
  public void validateCollectionMethod()
  {
    this.validateAttribute(COLLECTIONMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCollectionMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONMETHOD);
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
  
  public Integer getDisected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DISECTED));
  }
  
  public void validateDisected()
  {
    this.validateAttribute(DISECTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDisectedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(DISECTED);
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
  
  public Integer getEggs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EGGS));
  }
  
  public void validateEggs()
  {
    this.validateAttribute(EGGS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEggsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(EGGS);
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
  
  public Integer getFemalesFed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESFED));
  }
  
  public void validateFemalesFed()
  {
    this.validateAttribute(FEMALESFED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFemalesFedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(FEMALESFED);
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
  
  public Integer getFemalesGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESGRAVID));
  }
  
  public void validateFemalesGravid()
  {
    this.validateAttribute(FEMALESGRAVID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFemalesGravidMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(FEMALESGRAVID);
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
  
  public Integer getFemalesHalfGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESHALFGRAVID));
  }
  
  public void validateFemalesHalfGravid()
  {
    this.validateAttribute(FEMALESHALFGRAVID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFemalesHalfGravidMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(FEMALESHALFGRAVID);
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
  
  public Integer getFemalesUnfed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNFED));
  }
  
  public void validateFemalesUnfed()
  {
    this.validateAttribute(FEMALESUNFED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFemalesUnfedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(FEMALESUNFED);
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
  
  public Integer getFemalesUnknown()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNKNOWN));
  }
  
  public void validateFemalesUnknown()
  {
    this.validateAttribute(FEMALESUNKNOWN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFemalesUnknownMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(FEMALESUNKNOWN);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getIdentMethod()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void validateIdentMethod()
  {
    this.validateAttribute(IDENTMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdentMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(IDENTMETHOD);
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
  
  public Integer getLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LARVAE));
  }
  
  public void validateLarvae()
  {
    this.validateAttribute(LARVAE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLarvaeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(LARVAE);
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
  
  public String getLifeStage()
  {
    return getValue(LIFESTAGE);
  }
  
  public void validateLifeStage()
  {
    this.validateAttribute(LIFESTAGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLifeStageMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(LIFESTAGE);
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
  
  public Integer getMale()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MALE));
  }
  
  public void validateMale()
  {
    this.validateAttribute(MALE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMaleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(MALE);
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
  
  public Integer getParous()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PAROUS));
  }
  
  public void validateParous()
  {
    this.validateAttribute(PAROUS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getParousMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(PAROUS);
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
  
  public Integer getPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUPAE));
  }
  
  public void validatePupae()
  {
    this.validateAttribute(PUPAE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPupaeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(PUPAE);
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
  
  public String getSubCollectionId()
  {
    return getValue(SUBCOLLECTIONID);
  }
  
  public void validateSubCollectionId()
  {
    this.validateAttribute(SUBCOLLECTIONID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSubCollectionIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(SUBCOLLECTIONID);
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
  
  public String getTaxon()
  {
    return getValue(TAXON);
  }
  
  public void validateTaxon()
  {
    this.validateAttribute(TAXON);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTaxonMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(TAXON);
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
  
  public Integer getUnknowns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNKNOWNS));
  }
  
  public void validateUnknowns()
  {
    this.validateAttribute(UNKNOWNS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUnknownsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS);
    return mdClassIF.definesAttribute(UNKNOWNS);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MosquitoCollectionExcelView get(String id)
  {
    return (MosquitoCollectionExcelView) com.runwaysdk.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
