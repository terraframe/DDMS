package dss.vector.solutions.export.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 673749695)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AdultDiscriminatingDoseAssayExcelViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView";
  public static java.lang.String AGERANGE = "ageRange";
  private com.runwaysdk.business.Struct ageRange = null;
  
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String CONTROLTESTMORTALITY = "controlTestMortality";
  public static java.lang.String EXPOSURETIME = "exposureTime";
  public static java.lang.String FED = "fed";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String GRAVID = "gravid";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String INSECTICIDEACTIVEINGREDIENT = "insecticideActiveIngredient";
  public static java.lang.String INSECTICIDEAMOUNT = "insecticideAmount";
  public static java.lang.String INSECTICIDEUNITS = "insecticideUnits";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String KD50 = "kd50";
  public static java.lang.String KD95 = "kd95";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIE = "specie";
  public static java.lang.String TESTDATE = "testDate";
  public static java.lang.String TESTMETHOD = "testMethod";
  private static final long serialVersionUID = 673749695;
  
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAgeRangeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(AGERANGE);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public Float getControlTestMortality()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(CONTROLTESTMORTALITY));
  }
  
  public void validateControlTestMortality()
  {
    this.validateAttribute(CONTROLTESTMORTALITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getControlTestMortalityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGenerationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdentificationMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getInsecticideActiveIngredientMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(INSECTICIDEAMOUNT));
  }
  
  public void validateInsecticideAmount()
  {
    this.validateAttribute(INSECTICIDEAMOUNT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getInsecticideAmountMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getInsecticideUnitsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
  }
  
  public void validateIsofemale()
  {
    this.validateAttribute(ISOFEMALE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIsofemaleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public Double getKd50()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD50));
  }
  
  public void validateKd50()
  {
    this.validateAttribute(KD50);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getKd50Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(KD50);
  }
  
  public void setKd50(Double value)
  {
    if(value == null)
    {
      setValue(KD50, "");
    }
    else
    {
      setValue(KD50, java.lang.Double.toString(value));
    }
  }
  
  public Double getKd95()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD95));
  }
  
  public void validateKd95()
  {
    this.validateAttribute(KD95);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getKd95Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(KD95);
  }
  
  public void setKd95(Double value)
  {
    if(value == null)
    {
      setValue(KD95, "");
    }
    else
    {
      setValue(KD95, java.lang.Double.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
  }
  
  public void validateQuantityTested()
  {
    this.validateAttribute(QUANTITYTESTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getQuantityTestedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSpecieMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
  }
  
  public void validateTestDate()
  {
    this.validateAttribute(TESTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTestDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
      setValue(TESTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTestMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView.CLASS);
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
    return (AdultDiscriminatingDoseAssayExcelView) com.runwaysdk.business.View.get(id);
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
