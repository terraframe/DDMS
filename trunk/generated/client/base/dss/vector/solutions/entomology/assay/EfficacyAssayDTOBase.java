package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -537451596)
public abstract class EfficacyAssayDTOBase extends dss.vector.solutions.entomology.assay.AbstractAssayDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.EfficacyAssay";
  private static final long serialVersionUID = -537451596;
  
  protected EfficacyAssayDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected EfficacyAssayDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGERANGE = "ageRange";
  public static java.lang.String COLONYNAME = "colonyName";
  public static java.lang.String CONTROLTESTMORTALITY = "controlTestMortality";
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
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getColonyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLONYNAME).getAttributeMdDTO();
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
  
  public dss.vector.solutions.geo.generated.SurfaceDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.SurfaceDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.SurfaceDTO value)
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
  
  public dss.vector.solutions.irs.InsecticideBrandDTO getInsecticideBrand()
  {
    if(getValue(INSECTICIDEBRAND) == null || getValue(INSECTICIDEBRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(INSECTICIDEBRAND));
    }
  }
  
  public String getInsecticideBrandId()
  {
    return getValue(INSECTICIDEBRAND);
  }
  
  public void setInsecticideBrand(dss.vector.solutions.irs.InsecticideBrandDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInsecticideBrandMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INSECTICIDEBRAND).getAttributeMdDTO();
  }
  
  public Float getMortality()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(MORTALITY));
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
  
  public boolean isMortalityWritable()
  {
    return isWritable(MORTALITY);
  }
  
  public boolean isMortalityReadable()
  {
    return isReadable(MORTALITY);
  }
  
  public boolean isMortalityModified()
  {
    return isModified(MORTALITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getMortalityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(MORTALITY).getAttributeMdDTO();
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
  
  public Integer getQuantityLive()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYLIVE));
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
  
  public boolean isQuantityLiveWritable()
  {
    return isWritable(QUANTITYLIVE);
  }
  
  public boolean isQuantityLiveReadable()
  {
    return isReadable(QUANTITYLIVE);
  }
  
  public boolean isQuantityLiveModified()
  {
    return isModified(QUANTITYLIVE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityLiveMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYLIVE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getSex()
  {
    if(getValue(SEX) == null || getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SEX));
    }
  }
  
  public String getSexId()
  {
    return getValue(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.TermDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSurfacePostion()
  {
    if(getValue(SURFACEPOSTION) == null || getValue(SURFACEPOSTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SURFACEPOSTION));
    }
  }
  
  public String getSurfacePostionId()
  {
    return getValue(SURFACEPOSTION);
  }
  
  public void setSurfacePostion(dss.vector.solutions.ontology.TermDTO value)
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
  
  public boolean isSurfacePostionWritable()
  {
    return isWritable(SURFACEPOSTION);
  }
  
  public boolean isSurfacePostionReadable()
  {
    return isReadable(SURFACEPOSTION);
  }
  
  public boolean isSurfacePostionModified()
  {
    return isModified(SURFACEPOSTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSurfacePostionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SURFACEPOSTION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSurfaceType()
  {
    if(getValue(SURFACETYPE) == null || getValue(SURFACETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SURFACETYPE));
    }
  }
  
  public String getSurfaceTypeId()
  {
    return getValue(SURFACETYPE);
  }
  
  public void setSurfaceType(dss.vector.solutions.ontology.TermDTO value)
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
  
  public boolean isSurfaceTypeWritable()
  {
    return isWritable(SURFACETYPE);
  }
  
  public boolean isSurfaceTypeReadable()
  {
    return isReadable(SURFACETYPE);
  }
  
  public boolean isSurfaceTypeModified()
  {
    return isModified(SURFACETYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSurfaceTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SURFACETYPE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTestMethod()
  {
    if(getValue(TESTMETHOD) == null || getValue(TESTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TESTMETHOD));
    }
  }
  
  public String getTestMethodId()
  {
    return getValue(TESTMETHOD);
  }
  
  public void setTestMethod(dss.vector.solutions.ontology.TermDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTestMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TESTMETHOD).getAttributeMdDTO();
  }
  
  public Integer getTimeOnSurface()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TIMEONSURFACE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTimeOnSurfaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TIMEONSURFACE).getAttributeMdDTO();
  }
  
  public String getUniqueAssayId()
  {
    return getValue(UNIQUEASSAYID);
  }
  
  public void setUniqueAssayId(String value)
  {
    if(value == null)
    {
      setValue(UNIQUEASSAYID, "");
    }
    else
    {
      setValue(UNIQUEASSAYID, value);
    }
  }
  
  public boolean isUniqueAssayIdWritable()
  {
    return isWritable(UNIQUEASSAYID);
  }
  
  public boolean isUniqueAssayIdReadable()
  {
    return isReadable(UNIQUEASSAYID);
  }
  
  public boolean isUniqueAssayIdModified()
  {
    return isModified(UNIQUEASSAYID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUniqueAssayIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNIQUEASSAYID).getAttributeMdDTO();
  }
  
  public static final java.lang.Boolean checkControlTestMortality(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.util.Date testDate, java.lang.Float controlTestMortality)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.util.Date", "java.lang.Float"};
    Object[] _parameters = new Object[]{geoId, testDate, controlTestMortality};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "checkControlTestMortality", _declaredTypes);
    return (java.lang.Boolean) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.Float getOverallMortalityRate()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "getOverallMortalityRate", _declaredTypes);
    return (java.lang.Float) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.Float getOverallMortalityRate(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "getOverallMortalityRate", _declaredTypes);
    return (java.lang.Float) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.EfficacyAssayDTO[] searchByGeoEntityAndDate(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, java.util.Date collectionDate)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.generated.GeoEntity", "java.util.Date"};
    Object[] _parameters = new Object[]{geoEntity, collectionDate};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "searchByGeoEntityAndDate", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.EfficacyAssayDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.entomology.assay.EfficacyAssayDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.EfficacyAssayDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.entomology.assay.EfficacyAssayQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.EfficacyAssayQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.EfficacyAssayDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.EfficacyAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.EfficacyAssayDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.EfficacyAssayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.EfficacyAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
