package dss.vector.solutions.export.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -1585682339)
public abstract class AdultDiscriminatingDoseAssayExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelView";
  private static final long serialVersionUID = -1585682339;
  
  protected AdultDiscriminatingDoseAssayExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGERANGE = "ageRange";
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
  public dss.vector.solutions.entomology.assay.AdultAgeRangeDTO getAgeRange()
  {
    return (dss.vector.solutions.entomology.assay.AdultAgeRangeDTO) this.getAttributeStructDTO(AGERANGE).getStructDTO();
  }
  
  public boolean isAgeRangeWritable()
  {
    return isWritable(AGERANGE);
  }
  
  public boolean isAgeRangeReadable()
  {
    return isReadable(AGERANGE);
  }
  
  public boolean isAgeRangeModified()
  {
    return isModified(AGERANGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeStructMdDTO getAgeRangeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeStructMdDTO) getAttributeDTO(AGERANGE).getAttributeMdDTO();
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
  
  public Float getControlTestMortality()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(CONTROLTESTMORTALITY));
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
  
  public boolean isControlTestMortalityWritable()
  {
    return isWritable(CONTROLTESTMORTALITY);
  }
  
  public boolean isControlTestMortalityReadable()
  {
    return isReadable(CONTROLTESTMORTALITY);
  }
  
  public boolean isControlTestMortalityModified()
  {
    return isModified(CONTROLTESTMORTALITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getControlTestMortalityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CONTROLTESTMORTALITY).getAttributeMdDTO();
  }
  
  public java.util.Date getDateCollected()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATECOLLECTED));
  }
  
  public void setDateCollected(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATECOLLECTED, "");
    }
    else
    {
      setValue(DATECOLLECTED, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDateCollectedWritable()
  {
    return isWritable(DATECOLLECTED);
  }
  
  public boolean isDateCollectedReadable()
  {
    return isReadable(DATECOLLECTED);
  }
  
  public boolean isDateCollectedModified()
  {
    return isModified(DATECOLLECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDateCollectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DATECOLLECTED).getAttributeMdDTO();
  }
  
  public Integer getExposureTime()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EXPOSURETIME));
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
  
  public boolean isExposureTimeWritable()
  {
    return isWritable(EXPOSURETIME);
  }
  
  public boolean isExposureTimeReadable()
  {
    return isReadable(EXPOSURETIME);
  }
  
  public boolean isExposureTimeModified()
  {
    return isModified(EXPOSURETIME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getExposureTimeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EXPOSURETIME).getAttributeMdDTO();
  }
  
  public Integer getFed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FED));
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
  
  public boolean isFedWritable()
  {
    return isWritable(FED);
  }
  
  public boolean isFedReadable()
  {
    return isReadable(FED);
  }
  
  public boolean isFedModified()
  {
    return isModified(FED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FED).getAttributeMdDTO();
  }
  
  public String getGeneration()
  {
    return getValue(GENERATION);
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
  
  public boolean isGenerationWritable()
  {
    return isWritable(GENERATION);
  }
  
  public boolean isGenerationReadable()
  {
    return isReadable(GENERATION);
  }
  
  public boolean isGenerationModified()
  {
    return isModified(GENERATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGenerationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GENERATION).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Integer getGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(GRAVID));
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
  
  public boolean isGravidWritable()
  {
    return isWritable(GRAVID);
  }
  
  public boolean isGravidReadable()
  {
    return isReadable(GRAVID);
  }
  
  public boolean isGravidModified()
  {
    return isModified(GRAVID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getGravidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(GRAVID).getAttributeMdDTO();
  }
  
  public Integer getHoldingTime()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOLDINGTIME));
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
  
  public boolean isHoldingTimeWritable()
  {
    return isWritable(HOLDINGTIME);
  }
  
  public boolean isHoldingTimeReadable()
  {
    return isReadable(HOLDINGTIME);
  }
  
  public boolean isHoldingTimeModified()
  {
    return isModified(HOLDINGTIME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getHoldingTimeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HOLDINGTIME).getAttributeMdDTO();
  }
  
  public String getIdentificationMethod()
  {
    return getValue(IDENTIFICATIONMETHOD);
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
  
  public boolean isIdentificationMethodWritable()
  {
    return isWritable(IDENTIFICATIONMETHOD);
  }
  
  public boolean isIdentificationMethodReadable()
  {
    return isReadable(IDENTIFICATIONMETHOD);
  }
  
  public boolean isIdentificationMethodModified()
  {
    return isModified(IDENTIFICATIONMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIdentificationMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTIFICATIONMETHOD).getAttributeMdDTO();
  }
  
  public String getInsecticideActiveIngredient()
  {
    return getValue(INSECTICIDEACTIVEINGREDIENT);
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
  
  public boolean isInsecticideActiveIngredientWritable()
  {
    return isWritable(INSECTICIDEACTIVEINGREDIENT);
  }
  
  public boolean isInsecticideActiveIngredientReadable()
  {
    return isReadable(INSECTICIDEACTIVEINGREDIENT);
  }
  
  public boolean isInsecticideActiveIngredientModified()
  {
    return isModified(INSECTICIDEACTIVEINGREDIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInsecticideActiveIngredientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICIDEACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
  public Double getInsecticideAmount()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(INSECTICIDEAMOUNT));
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
  
  public boolean isInsecticideAmountWritable()
  {
    return isWritable(INSECTICIDEAMOUNT);
  }
  
  public boolean isInsecticideAmountReadable()
  {
    return isReadable(INSECTICIDEAMOUNT);
  }
  
  public boolean isInsecticideAmountModified()
  {
    return isModified(INSECTICIDEAMOUNT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getInsecticideAmountMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(INSECTICIDEAMOUNT).getAttributeMdDTO();
  }
  
  public String getInsecticideUnits()
  {
    return getValue(INSECTICIDEUNITS);
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
  
  public boolean isInsecticideUnitsWritable()
  {
    return isWritable(INSECTICIDEUNITS);
  }
  
  public boolean isInsecticideUnitsReadable()
  {
    return isReadable(INSECTICIDEUNITS);
  }
  
  public boolean isInsecticideUnitsModified()
  {
    return isModified(INSECTICIDEUNITS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInsecticideUnitsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICIDEUNITS).getAttributeMdDTO();
  }
  
  public Boolean getIsofemale()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
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
  
  public boolean isIsofemaleWritable()
  {
    return isWritable(ISOFEMALE);
  }
  
  public boolean isIsofemaleReadable()
  {
    return isReadable(ISOFEMALE);
  }
  
  public boolean isIsofemaleModified()
  {
    return isModified(ISOFEMALE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsofemaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISOFEMALE).getAttributeMdDTO();
  }
  
  public Integer getQuantityDead()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYDEAD));
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
  
  public boolean isQuantityDeadWritable()
  {
    return isWritable(QUANTITYDEAD);
  }
  
  public boolean isQuantityDeadReadable()
  {
    return isReadable(QUANTITYDEAD);
  }
  
  public boolean isQuantityDeadModified()
  {
    return isModified(QUANTITYDEAD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityDeadMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYDEAD).getAttributeMdDTO();
  }
  
  public Integer getQuantityTested()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
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
  
  public boolean isQuantityTestedWritable()
  {
    return isWritable(QUANTITYTESTED);
  }
  
  public boolean isQuantityTestedReadable()
  {
    return isReadable(QUANTITYTESTED);
  }
  
  public boolean isQuantityTestedModified()
  {
    return isModified(QUANTITYTESTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityTestedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYTESTED).getAttributeMdDTO();
  }
  
  public String getSex()
  {
    return getValue(SEX);
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
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public String getSpecie()
  {
    return getValue(SPECIE);
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
  
  public boolean isSpecieWritable()
  {
    return isWritable(SPECIE);
  }
  
  public boolean isSpecieReadable()
  {
    return isReadable(SPECIE);
  }
  
  public boolean isSpecieModified()
  {
    return isModified(SPECIE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSpecieMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPECIE).getAttributeMdDTO();
  }
  
  public java.util.Date getTestDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
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
  
  public boolean isTestDateWritable()
  {
    return isWritable(TESTDATE);
  }
  
  public boolean isTestDateReadable()
  {
    return isReadable(TESTDATE);
  }
  
  public boolean isTestDateModified()
  {
    return isModified(TESTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getTestDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(TESTDATE).getAttributeMdDTO();
  }
  
  public String getTestMethod()
  {
    return getValue(TESTMETHOD);
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
  
  public boolean isTestMethodWritable()
  {
    return isWritable(TESTMETHOD);
  }
  
  public boolean isTestMethodReadable()
  {
    return isReadable(TESTMETHOD);
  }
  
  public boolean isTestMethodModified()
  {
    return isModified(TESTMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTestMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TESTMETHOD).getAttributeMdDTO();
  }
  
  public static AdultDiscriminatingDoseAssayExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AdultDiscriminatingDoseAssayExcelViewDTO) dto;
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
