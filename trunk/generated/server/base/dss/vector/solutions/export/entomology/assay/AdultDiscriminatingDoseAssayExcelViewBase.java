package dss.vector.solutions.export.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = 705576601)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssayExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AdultDiscriminatingDoseAssayExcelViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView";
  public static java.lang.String AGERANGE = "ageRange";
  private com.terraframe.mojo.business.Struct ageRange = null;
  
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
  public static java.lang.String CONTROLTESTMORTALITY = "controlTestMortality";
  public static java.lang.String DATECOLLECTED = "dateCollected";
  public static java.lang.String EXPOSURETIME = "exposureTime";
  public static java.lang.String FED = "fed";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GRAVID = "gravid";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String INSECTICIDEACTIVEINGREDIENT = "insecticideActiveIngredient";
  public static java.lang.String INSECTICIDEAMOUNT = "insecticideAmount";
  public static java.lang.String INSECTICIDEUNITS = "insecticideUnits";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIE = "specie";
  public static java.lang.String TESTDATE = "testDate";
  public static java.lang.String TESTMETHOD = "testMethod";
  private static final long serialVersionUID = 705576601;
  
  public AdultDiscriminatingDoseAssayExcelViewBase()
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgeRangeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(AGERANGE);
  }
  
  public String getCollectionMethod()
  {
    return getValue(COLLECTIONMETHOD);
  }
  
  public void validateCollectionMethod()
  {
    this.validateAttribute(COLLECTIONMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public Float getControlTestMortality()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(CONTROLTESTMORTALITY));
  }
  
  public void validateControlTestMortality()
  {
    this.validateAttribute(CONTROLTESTMORTALITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getControlTestMortalityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(CONTROLTESTMORTALITY);
  }
  
  public void setControlTestMortality(Float value)
  {
    if(value == null)
    {
      setValue(CONTROLTESTMORTALITY, "");
    }
    else
    {
      setValue(CONTROLTESTMORTALITY, java.lang.Float.toString(value));
    }
  }
  
  public java.util.Date getDateCollected()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATECOLLECTED));
  }
  
  public void validateDateCollected()
  {
    this.validateAttribute(DATECOLLECTED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDateCollectedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(DATECOLLECTED);
  }
  
  public void setDateCollected(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATECOLLECTED, "");
    }
    else
    {
      setValue(DATECOLLECTED, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public String getGeneration()
  {
    return getValue(GENERATION);
  }
  
  public void validateGeneration()
  {
    this.validateAttribute(GENERATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGenerationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(GENERATION);
  }
  
  public void setGeneration(String value)
  {
    if(value == null)
    {
      setValue(GENERATION, "");
    }
    else
    {
      setValue(GENERATION, value);
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
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getIdentificationMethod()
  {
    return getValue(IDENTIFICATIONMETHOD);
  }
  
  public void validateIdentificationMethod()
  {
    this.validateAttribute(IDENTIFICATIONMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdentificationMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(IDENTIFICATIONMETHOD);
  }
  
  public void setIdentificationMethod(String value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMETHOD, "");
    }
    else
    {
      setValue(IDENTIFICATIONMETHOD, value);
    }
  }
  
  public String getInsecticideActiveIngredient()
  {
    return getValue(INSECTICIDEACTIVEINGREDIENT);
  }
  
  public void validateInsecticideActiveIngredient()
  {
    this.validateAttribute(INSECTICIDEACTIVEINGREDIENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInsecticideActiveIngredientMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDEACTIVEINGREDIENT);
  }
  
  public void setInsecticideActiveIngredient(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEACTIVEINGREDIENT, "");
    }
    else
    {
      setValue(INSECTICIDEACTIVEINGREDIENT, value);
    }
  }
  
  public Double getInsecticideAmount()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(INSECTICIDEAMOUNT));
  }
  
  public void validateInsecticideAmount()
  {
    this.validateAttribute(INSECTICIDEAMOUNT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInsecticideAmountMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDEAMOUNT);
  }
  
  public void setInsecticideAmount(Double value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEAMOUNT, "");
    }
    else
    {
      setValue(INSECTICIDEAMOUNT, java.lang.Double.toString(value));
    }
  }
  
  public String getInsecticideUnits()
  {
    return getValue(INSECTICIDEUNITS);
  }
  
  public void validateInsecticideUnits()
  {
    this.validateAttribute(INSECTICIDEUNITS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getInsecticideUnitsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDEUNITS);
  }
  
  public void setInsecticideUnits(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEUNITS, "");
    }
    else
    {
      setValue(INSECTICIDEUNITS, value);
    }
  }
  
  public Boolean getIsofemale()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
  }
  
  public void validateIsofemale()
  {
    this.validateAttribute(ISOFEMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsofemaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(ISOFEMALE);
  }
  
  public void setIsofemale(Boolean value)
  {
    if(value == null)
    {
      setValue(ISOFEMALE, "");
    }
    else
    {
      setValue(ISOFEMALE, java.lang.Boolean.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  public void setSex(String value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value);
    }
  }
  
  public String getSpecie()
  {
    return getValue(SPECIE);
  }
  
  public void validateSpecie()
  {
    this.validateAttribute(SPECIE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSpecieMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPECIE);
  }
  
  public void setSpecie(String value)
  {
    if(value == null)
    {
      setValue(SPECIE, "");
    }
    else
    {
      setValue(SPECIE, value);
    }
  }
  
  public java.util.Date getTestDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
  }
  
  public void validateTestDate()
  {
    this.validateAttribute(TESTDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(TESTDATE);
  }
  
  public void setTestDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TESTDATE, "");
    }
    else
    {
      setValue(TESTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getTestMethod()
  {
    return getValue(TESTMETHOD);
  }
  
  public void validateTestMethod()
  {
    this.validateAttribute(TESTMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(TESTMETHOD);
  }
  
  public void setTestMethod(String value)
  {
    if(value == null)
    {
      setValue(TESTMETHOD, "");
    }
    else
    {
      setValue(TESTMETHOD, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AdultDiscriminatingDoseAssayExcelView get(String id)
  {
    return (AdultDiscriminatingDoseAssayExcelView) com.terraframe.mojo.business.View.get(id);
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
