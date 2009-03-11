package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EfficacyAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class EfficacyAssayBase extends csu.mrc.ivcc.mdss.entomology.assay.AdultAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay";
  public static java.lang.String COLONYNAME = "colonyName";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String INSECTICIDE = "insecticide";
  public static java.lang.String INSECTICIDELENGTH = "insecticideLength";
  public static java.lang.String MORTALITY = "mortality";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYLIVE = "quantityLive";
  public static java.lang.String SURFACEPOSTION = "surfacePostion";
  private static final long serialVersionUID = 1236803175671L;
  
  public EfficacyAssayBase()
  {
    super();
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
  
  public csu.mrc.ivcc.mdss.mo.Insecticide getInsecticide()
  {
    try
    {
      return csu.mrc.ivcc.mdss.mo.Insecticide.get(getValue(INSECTICIDE));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateInsecticide()
  {
    this.validateAttribute(INSECTICIDE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInsecticideMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDE);
  }
  
  public void setInsecticide(csu.mrc.ivcc.mdss.mo.Insecticide value)
  {
    if(value == null)
    {
      setValue(INSECTICIDE, "");
    }
    else
    {
      setValue(INSECTICIDE, value.getId());
    }
  }
  
  public Integer getInsecticideLength()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INSECTICIDELENGTH));
  }
  
  public void validateInsecticideLength()
  {
    this.validateAttribute(INSECTICIDELENGTH);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInsecticideLengthMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDELENGTH);
  }
  
  public void setInsecticideLength(Integer value)
  {
    if(value == null)
    {
      setValue(INSECTICIDELENGTH, "");
    }
    else
    {
      setValue(INSECTICIDELENGTH, java.lang.Integer.toString(value));
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
