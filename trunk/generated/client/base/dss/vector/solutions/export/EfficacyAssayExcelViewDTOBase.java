package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 537195313)
public abstract class EfficacyAssayExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.EfficacyAssayExcelView";
  private static final long serialVersionUID = 537195313;
  
  protected EfficacyAssayExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGERANGE = "ageRange";
  public static java.lang.String COLONYNAME = "colonyName";
  public static java.lang.String EXPOSURETIME = "exposureTime";
  public static java.lang.String FED = "fed";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GRAVID = "gravid";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDEACTIVEINGREDIENT = "insecticideActiveIngredient";
  public static java.lang.String INSECTICIDEAMOUNT = "insecticideAmount";
  public static java.lang.String INSECTICIDEUNITS = "insecticideUnits";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIE = "specie";
  public static java.lang.String SURFACEPOSITION = "surfacePosition";
  public static java.lang.String TESTDATE = "testDate";
  public static java.lang.String TESTMETHOD = "testMethod";
  public static java.lang.String TIMEONSURFACE = "timeOnSurface";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeStructMdDTO getAgeRangeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeStructMdDTO) getAttributeDTO(AGERANGE).getAttributeMdDTO();
  }
  
  public String getColonyName()
  {
    return getValue(COLONYNAME);
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
  
  public boolean isColonyNameWritable()
  {
    return isWritable(COLONYNAME);
  }
  
  public boolean isColonyNameReadable()
  {
    return isReadable(COLONYNAME);
  }
  
  public boolean isColonyNameModified()
  {
    return isModified(COLONYNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getColonyNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLONYNAME).getAttributeMdDTO();
  }
  
  public Integer getExposureTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EXPOSURETIME));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getExposureTimeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EXPOSURETIME).getAttributeMdDTO();
  }
  
  public Integer getFed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getFedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FED).getAttributeMdDTO();
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
  
  public Integer getGravid()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(GRAVID));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getGravidMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(GRAVID).getAttributeMdDTO();
  }
  
  public Integer getHoldingTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOLDINGTIME));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getHoldingTimeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HOLDINGTIME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getInsecticideActiveIngredientMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICIDEACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
  public Double getInsecticideAmount()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(INSECTICIDEAMOUNT));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getInsecticideAmountMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(INSECTICIDEAMOUNT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getInsecticideUnitsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICIDEUNITS).getAttributeMdDTO();
  }
  
  public Integer getQuantityDead()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYDEAD));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuantityDeadMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYDEAD).getAttributeMdDTO();
  }
  
  public Integer getQuantityTested()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuantityTestedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYTESTED).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSpecieMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPECIE).getAttributeMdDTO();
  }
  
  public String getSurfacePosition()
  {
    return getValue(SURFACEPOSITION);
  }
  
  public void setSurfacePosition(String value)
  {
    if(value == null)
    {
      setValue(SURFACEPOSITION, "");
    }
    else
    {
      setValue(SURFACEPOSITION, value);
    }
  }
  
  public boolean isSurfacePositionWritable()
  {
    return isWritable(SURFACEPOSITION);
  }
  
  public boolean isSurfacePositionReadable()
  {
    return isReadable(SURFACEPOSITION);
  }
  
  public boolean isSurfacePositionModified()
  {
    return isModified(SURFACEPOSITION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSurfacePositionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SURFACEPOSITION).getAttributeMdDTO();
  }
  
  public java.util.Date getTestDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getTestDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(TESTDATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTestMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TESTMETHOD).getAttributeMdDTO();
  }
  
  public Integer getTimeOnSurface()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TIMEONSURFACE));
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
  
  public boolean isTimeOnSurfaceWritable()
  {
    return isWritable(TIMEONSURFACE);
  }
  
  public boolean isTimeOnSurfaceReadable()
  {
    return isReadable(TIMEONSURFACE);
  }
  
  public boolean isTimeOnSurfaceModified()
  {
    return isModified(TIMEONSURFACE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTimeOnSurfaceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TIMEONSURFACE).getAttributeMdDTO();
  }
  
  public static EfficacyAssayExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (EfficacyAssayExcelViewDTO) dto;
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
