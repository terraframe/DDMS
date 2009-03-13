package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EfficacyAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class EfficacyAssayBase extends csu.mrc.ivcc.mdss.entomology.assay.AbstractAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay";
  public static java.lang.String AGERANGE = "ageRange";
  private com.terraframe.mojo.business.Struct ageRange = null;
  
  public static java.lang.String COLONYNAME = "colonyName";
  public static java.lang.String EXPOSURETIME = "exposureTime";
  public static java.lang.String FED = "fed";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GRAVID = "gravid";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String MORTALITY = "mortality";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYLIVE = "quantityLive";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SURFACEPOSTION = "surfacePostion";
  public static java.lang.String TESTMETHOD = "testMethod";
  private static final long serialVersionUID = 1236982482937L;
  
  public EfficacyAssayBase()
  {
    super();
    ageRange = super.getStruct("ageRange");
  }
  
  public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRange getAgeRange()
  {
    return (csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRange) ageRange;
  }
  
  public void validateAgeRange()
  {
    this.validateAttribute(AGERANGE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgeRangeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(AGERANGE);
  }
  
  public String getColonyName()
  {
    return getValue(COLONYNAME);
  }
  
  public void validateColonyName()
  {
    this.validateAttribute(COLONYNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getColonyNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(COLONYNAME);
  }
  
  public void setColonyName(String value)
  {
    if(value == null)
    {
      setValue(COLONYNAME, "");
    }
    else
    {
      setValue(COLONYNAME, value);
    }
  }
  
  public Integer getExposureTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EXPOSURETIME));
  }
  
  public void validateExposureTime()
  {
    this.validateAttribute(EXPOSURETIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getExposureTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(EXPOSURETIME);
  }
  
  public void setExposureTime(Integer value)
  {
    if(value == null)
    {
      setValue(EXPOSURETIME, "");
    }
    else
    {
      setValue(EXPOSURETIME, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getFed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FED));
  }
  
  public void validateFed()
  {
    this.validateAttribute(FED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(FED);
  }
  
  public void setFed(Integer value)
  {
    if(value == null)
    {
      setValue(FED, "");
    }
    else
    {
      setValue(FED, java.lang.Integer.toString(value));
    }
  }
  
  public csu.mrc.ivcc.mdss.geo.generated.GeoEntity getGeoEntity()
  {
    try
    {
      return csu.mrc.ivcc.mdss.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(csu.mrc.ivcc.mdss.geo.generated.GeoEntity value)
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
  
  public Integer getGravid()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(GRAVID));
  }
  
  public void validateGravid()
  {
    this.validateAttribute(GRAVID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGravidMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(GRAVID);
  }
  
  public void setGravid(Integer value)
  {
    if(value == null)
    {
      setValue(GRAVID, "");
    }
    else
    {
      setValue(GRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getHoldingTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOLDINGTIME));
  }
  
  public void validateHoldingTime()
  {
    this.validateAttribute(HOLDINGTIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHoldingTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(HOLDINGTIME);
  }
  
  public void setHoldingTime(Integer value)
  {
    if(value == null)
    {
      setValue(HOLDINGTIME, "");
    }
    else
    {
      setValue(HOLDINGTIME, java.lang.Integer.toString(value));
    }
  }
  
  public Float getMortality()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(MORTALITY));
  }
  
  public void validateMortality()
  {
    this.validateAttribute(MORTALITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMortalityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(MORTALITY);
  }
  
  public void setMortality(Float value)
  {
    if(value == null)
    {
      setValue(MORTALITY, "");
    }
    else
    {
      setValue(MORTALITY, java.lang.Float.toString(value));
    }
  }
  
  public Integer getQuantityDead()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYDEAD));
  }
  
  public void validateQuantityDead()
  {
    this.validateAttribute(QUANTITYDEAD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityDeadMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(QUANTITYDEAD);
  }
  
  public void setQuantityDead(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYDEAD, "");
    }
    else
    {
      setValue(QUANTITYDEAD, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityLive()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYLIVE));
  }
  
  public void validateQuantityLive()
  {
    this.validateAttribute(QUANTITYLIVE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityLiveMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(QUANTITYLIVE);
  }
  
  public void setQuantityLive(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYLIVE, "");
    }
    else
    {
      setValue(QUANTITYLIVE, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityTested()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
  }
  
  public void validateQuantityTested()
  {
    this.validateAttribute(QUANTITYTESTED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityTestedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(QUANTITYTESTED);
  }
  
  public void setQuantityTested(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYTESTED, "");
    }
    else
    {
      setValue(QUANTITYTESTED, java.lang.Integer.toString(value));
    }
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<csu.mrc.ivcc.mdss.entomology.AssaySex> getSex()
  {
    return (java.util.List<csu.mrc.ivcc.mdss.entomology.AssaySex>) getEnumValues(SEX);
  }
  
  public void addSex(csu.mrc.ivcc.mdss.entomology.AssaySex value)
  {
    addEnumItem(SEX, value.getId());
  }
  
  public void removeSex(csu.mrc.ivcc.mdss.entomology.AssaySex value)
  {
    removeEnumItem(SEX, value.getId());
  }
  
  public void clearSex()
  {
    clearEnum(SEX);
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<csu.mrc.ivcc.mdss.SurfacePosition> getSurfacePostion()
  {
    return (java.util.List<csu.mrc.ivcc.mdss.SurfacePosition>) getEnumValues(SURFACEPOSTION);
  }
  
  public void addSurfacePostion(csu.mrc.ivcc.mdss.SurfacePosition value)
  {
    addEnumItem(SURFACEPOSTION, value.getId());
  }
  
  public void removeSurfacePostion(csu.mrc.ivcc.mdss.SurfacePosition value)
  {
    removeEnumItem(SURFACEPOSTION, value.getId());
  }
  
  public void clearSurfacePostion()
  {
    clearEnum(SURFACEPOSTION);
  }
  
  public void validateSurfacePostion()
  {
    this.validateAttribute(SURFACEPOSTION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSurfacePostionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(SURFACEPOSTION);
  }
  
  public csu.mrc.ivcc.mdss.mo.ResistanceMethodology getTestMethod()
  {
    try
    {
      return csu.mrc.ivcc.mdss.mo.ResistanceMethodology.get(getValue(TESTMETHOD));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateTestMethod()
  {
    this.validateAttribute(TESTMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(TESTMETHOD);
  }
  
  public void setTestMethod(csu.mrc.ivcc.mdss.mo.ResistanceMethodology value)
  {
    if(value == null)
    {
      setValue(TESTMETHOD, "");
    }
    else
    {
      setValue(TESTMETHOD, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static EfficacyAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    EfficacyAssayQuery query = new EfficacyAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static EfficacyAssay get(String id)
  {
    return (EfficacyAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public java.lang.Float getOverallMortalityRate()
  {
    return null;
  }
  
  public static final java.lang.Float getOverallMortalityRate(java.lang.String id)
  {
    EfficacyAssay _instance = EfficacyAssay.get(id);
    return _instance.getOverallMortalityRate();
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay[] searchByGeoEntityAndDate(csu.mrc.ivcc.mdss.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    return null;
  }
  
  public static EfficacyAssay lock(java.lang.String id)
  {
    EfficacyAssay _instance = EfficacyAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static EfficacyAssay unlock(java.lang.String id)
  {
    EfficacyAssay _instance = EfficacyAssay.get(id);
    _instance.unlock();
    
    return _instance;
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
