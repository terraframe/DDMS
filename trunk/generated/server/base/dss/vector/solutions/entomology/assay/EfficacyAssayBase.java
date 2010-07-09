package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -684533853)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EfficacyAssay.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class EfficacyAssayBase extends dss.vector.solutions.entomology.assay.AbstractAssay implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.EfficacyAssay";
  public static java.lang.String AGERANGE = "ageRange";
  private com.runwaysdk.business.Struct ageRange = null;
  
  public static java.lang.String COLONYNAME = "colonyName";
  public static java.lang.String EXPOSURETIME = "exposureTime";
  public static java.lang.String FED = "fed";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GRAVID = "gravid";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String INSECTICIDEBRAND = "insecticideBrand";
  public static java.lang.String MORTALITY = "mortality";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYLIVE = "quantityLive";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SURFACEPOSTION = "surfacePostion";
  public static java.lang.String SURFACETYPE = "surfaceType";
  public static java.lang.String TESTMETHOD = "testMethod";
  public static java.lang.String TIMEONSURFACE = "timeOnSurface";
  private static final long serialVersionUID = -684533853;
  
  public EfficacyAssayBase()
  {
    super();
    ageRange = super.getStruct("ageRange");
  }
  
  public dss.vector.solutions.entomology.assay.AdultAgeRange getAgeRange()
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRange) ageRange;
  }
  
  public void validateAgeRange()
  {
    this.validateAttribute(AGERANGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAgeRangeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getColonyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EXPOSURETIME));
  }
  
  public void validateExposureTime()
  {
    this.validateAttribute(EXPOSURETIME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getExposureTimeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FED));
  }
  
  public void validateFed()
  {
    this.validateAttribute(FED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
  
  public dss.vector.solutions.geo.generated.Surface getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.Surface.get(getValue(GEOENTITY));
    }
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.Surface value)
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(GRAVID));
  }
  
  public void validateGravid()
  {
    this.validateAttribute(GRAVID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGravidMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOLDINGTIME));
  }
  
  public void validateHoldingTime()
  {
    this.validateAttribute(HOLDINGTIME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getHoldingTimeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
  
  public dss.vector.solutions.irs.InsecticideBrand getInsecticideBrand()
  {
    if (getValue(INSECTICIDEBRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrand.get(getValue(INSECTICIDEBRAND));
    }
  }
  
  public void validateInsecticideBrand()
  {
    this.validateAttribute(INSECTICIDEBRAND);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getInsecticideBrandMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDEBRAND);
  }
  
  public void setInsecticideBrand(dss.vector.solutions.irs.InsecticideBrand value)
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
  
  public Float getMortality()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(MORTALITY));
  }
  
  public void validateMortality()
  {
    this.validateAttribute(MORTALITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMortalityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYDEAD));
  }
  
  public void validateQuantityDead()
  {
    this.validateAttribute(QUANTITYDEAD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQuantityDeadMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYLIVE));
  }
  
  public void validateQuantityLive()
  {
    this.validateAttribute(QUANTITYLIVE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQuantityLiveMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
  }
  
  public void validateQuantityTested()
  {
    this.validateAttribute(QUANTITYTESTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQuantityTestedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
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
  
  public dss.vector.solutions.ontology.Term getSex()
  {
    if (getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SEX));
    }
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getSurfacePostion()
  {
    if (getValue(SURFACEPOSTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SURFACEPOSTION));
    }
  }
  
  public void validateSurfacePostion()
  {
    this.validateAttribute(SURFACEPOSTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSurfacePostionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(SURFACEPOSTION);
  }
  
  public void setSurfacePostion(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SURFACEPOSTION, "");
    }
    else
    {
      setValue(SURFACEPOSTION, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getSurfaceType()
  {
    if (getValue(SURFACETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SURFACETYPE));
    }
  }
  
  public void validateSurfaceType()
  {
    this.validateAttribute(SURFACETYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSurfaceTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(SURFACETYPE);
  }
  
  public void setSurfaceType(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SURFACETYPE, "");
    }
    else
    {
      setValue(SURFACETYPE, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getTestMethod()
  {
    if (getValue(TESTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TESTMETHOD));
    }
  }
  
  public void validateTestMethod()
  {
    this.validateAttribute(TESTMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTestMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(TESTMETHOD);
  }
  
  public void setTestMethod(dss.vector.solutions.ontology.Term value)
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
  
  public Integer getTimeOnSurface()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TIMEONSURFACE));
  }
  
  public void validateTimeOnSurface()
  {
    this.validateAttribute(TIMEONSURFACE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTimeOnSurfaceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS);
    return mdClassIF.definesAttribute(TIMEONSURFACE);
  }
  
  public void setTimeOnSurface(Integer value)
  {
    if(value == null)
    {
      setValue(TIMEONSURFACE, "");
    }
    else
    {
      setValue(TIMEONSURFACE, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static EfficacyAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    EfficacyAssayQuery query = new EfficacyAssayQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static EfficacyAssay get(String id)
  {
    return (EfficacyAssay) com.runwaysdk.business.Business.get(id);
  }
  
  public static EfficacyAssay getByKey(String key)
  {
    return (EfficacyAssay) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public java.lang.Float getOverallMortalityRate()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.EfficacyAssay.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.Float getOverallMortalityRate(java.lang.String id)
  {
    EfficacyAssay _instance = EfficacyAssay.get(id);
    return _instance.getOverallMortalityRate();
  }
  
  public static dss.vector.solutions.entomology.assay.EfficacyAssayView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.EfficacyAssay.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.entomology.assay.EfficacyAssayView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.EfficacyAssay.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.assay.EfficacyAssayView lockView(java.lang.String id)
  {
    EfficacyAssay _instance = EfficacyAssay.get(id);
    return _instance.lockView();
  }
  
  public static dss.vector.solutions.entomology.assay.EfficacyAssay[] searchByGeoEntityAndDate(dss.vector.solutions.geo.generated.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.EfficacyAssay.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.entomology.assay.EfficacyAssayView unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.EfficacyAssay.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.entomology.assay.EfficacyAssayView unlockView(java.lang.String id)
  {
    EfficacyAssay _instance = EfficacyAssay.get(id);
    return _instance.unlockView();
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
