package csu.mrc.ivcc.mdss.entomology.assay;

public abstract class EfficacyAssayDTOBase extends csu.mrc.ivcc.mdss.entomology.assay.AdultAssayDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236612278191L;
  
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay";
  protected EfficacyAssayDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected EfficacyAssayDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLONYNAME = "colonyName";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String INSECTICIDE = "insecticide";
  public static java.lang.String INSECTICIDELENGTH = "insecticideLength";
  public static java.lang.String MORTALITY = "mortality";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYLIVE = "quantityLive";
  public static java.lang.String SURFACEPOSTION = "surfacePostion";
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
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("colonyName").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO getGeoEntity()
  {
    return csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
  }
  
  public void setGeoEntity(csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO value)
  {
    setValue(GEOENTITY, value.getId());
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
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("geoEntity").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("holdingTime").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InsecticideDTO getInsecticide()
  {
    return csu.mrc.ivcc.mdss.mo.InsecticideDTO.get(getRequest(), getValue(INSECTICIDE));
  }
  
  public void setInsecticide(csu.mrc.ivcc.mdss.mo.InsecticideDTO value)
  {
    setValue(INSECTICIDE, value.getId());
  }
  
  public boolean isInsecticideWritable()
  {
    return isWritable(INSECTICIDE);
  }
  
  public boolean isInsecticideReadable()
  {
    return isReadable(INSECTICIDE);
  }
  
  public boolean isInsecticideModified()
  {
    return isModified(INSECTICIDE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getInsecticideMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("insecticide").getAttributeMdDTO();
  }
  
  public Integer getInsecticideLength()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INSECTICIDELENGTH));
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
  
  public boolean isInsecticideLengthWritable()
  {
    return isWritable(INSECTICIDELENGTH);
  }
  
  public boolean isInsecticideLengthReadable()
  {
    return isReadable(INSECTICIDELENGTH);
  }
  
  public boolean isInsecticideLengthModified()
  {
    return isModified(INSECTICIDELENGTH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInsecticideLengthMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("insecticideLength").getAttributeMdDTO();
  }
  
  public Float getMortality()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(MORTALITY));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getMortalityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO("mortality").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("quantityDead").getAttributeMdDTO();
  }
  
  public Integer getQuantityLive()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYLIVE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuantityLiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("quantityLive").getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<csu.mrc.ivcc.mdss.SurfacePositionDTO> getSurfacePostion()
  {
    return (java.util.List<csu.mrc.ivcc.mdss.SurfacePositionDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "csu.mrc.ivcc.mdss.SurfacePosition", getEnumNames(SURFACEPOSTION));
  }
  
  public java.util.List<String> getSurfacePostionEnumNames()
  {
    return getEnumNames(SURFACEPOSTION);
  }
  
  public void addSurfacePostion(csu.mrc.ivcc.mdss.SurfacePositionDTO enumDTO)
  {
    addEnumItem(SURFACEPOSTION, enumDTO.toString());
  }
  
  public void removeSurfacePostion(csu.mrc.ivcc.mdss.SurfacePositionDTO enumDTO)
  {
    removeEnumItem(SURFACEPOSTION, enumDTO.toString());
  }
  
  public void clearSurfacePostion()
  {
    clearEnum(SURFACEPOSTION);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getSurfacePostionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO("surfacePostion").getAttributeMdDTO();
  }
  
  public final java.lang.Float getOverallMortalityRate()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO.CLASS, "getOverallMortalityRate", _declaredTypes);
    return (java.lang.Float) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.Float getOverallMortalityRate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO.CLASS, "getOverallMortalityRate", _declaredTypes);
    return (java.lang.Float) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO[] searchByGeoEntityAndDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, csu.mrc.ivcc.mdss.geo.generated.GeoEntityDTO geoEntity, java.util.Date collectionDate)
  {
    String[] _declaredTypes = new String[]{"csu.mrc.ivcc.mdss.geo.generated.GeoEntity", "java.util.Date"};
    Object[] _parameters = new Object[]{geoEntity, collectionDate};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO.CLASS, "searchByGeoEntityAndDate", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO) dto;
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
  
  public static csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayQueryDTO) clientRequest.getAllInstances("csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssay", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO.CLASS, "lock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO.CLASS, "unlock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.EfficacyAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
