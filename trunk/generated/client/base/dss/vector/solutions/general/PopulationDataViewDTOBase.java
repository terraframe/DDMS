package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 960940843)
public abstract class PopulationDataViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.PopulationDataView";
  private static final long serialVersionUID = 960940843;
  
  protected PopulationDataViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String ESTIMATED = "estimated";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GROWTHRATE = "growthRate";
  public static java.lang.String ID = "id";
  public static java.lang.String POPULATION = "population";
  public static java.lang.String POPULATIONTYPE = "populationType";
  public static java.lang.String YEAROFDATA = "yearOfData";
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public boolean isEntityLabelWritable()
  {
    return isWritable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelReadable()
  {
    return isReadable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelModified()
  {
    return isModified(ENTITYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public Boolean getEstimated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ESTIMATED));
  }
  
  public void setEstimated(Boolean value)
  {
    if(value == null)
    {
      setValue(ESTIMATED, "");
    }
    else
    {
      setValue(ESTIMATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEstimatedWritable()
  {
    return isWritable(ESTIMATED);
  }
  
  public boolean isEstimatedReadable()
  {
    return isReadable(ESTIMATED);
  }
  
  public boolean isEstimatedModified()
  {
    return isModified(ESTIMATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEstimatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ESTIMATED).getAttributeMdDTO();
  }
  
  public String getGeoEntity()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Double getGrowthRate()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(GROWTHRATE));
  }
  
  public void setGrowthRate(Double value)
  {
    if(value == null)
    {
      setValue(GROWTHRATE, "");
    }
    else
    {
      setValue(GROWTHRATE, java.lang.Double.toString(value));
    }
  }
  
  public boolean isGrowthRateWritable()
  {
    return isWritable(GROWTHRATE);
  }
  
  public boolean isGrowthRateReadable()
  {
    return isReadable(GROWTHRATE);
  }
  
  public boolean isGrowthRateModified()
  {
    return isModified(GROWTHRATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getGrowthRateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(GROWTHRATE).getAttributeMdDTO();
  }
  
  public Long getPopulation()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(POPULATION));
  }
  
  public void setPopulation(Long value)
  {
    if(value == null)
    {
      setValue(POPULATION, "");
    }
    else
    {
      setValue(POPULATION, java.lang.Long.toString(value));
    }
  }
  
  public boolean isPopulationWritable()
  {
    return isWritable(POPULATION);
  }
  
  public boolean isPopulationReadable()
  {
    return isReadable(POPULATION);
  }
  
  public boolean isPopulationModified()
  {
    return isModified(POPULATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPopulationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POPULATION).getAttributeMdDTO();
  }
  
  public Boolean getPopulationType()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POPULATIONTYPE));
  }
  
  public void setPopulationType(Boolean value)
  {
    if(value == null)
    {
      setValue(POPULATIONTYPE, "");
    }
    else
    {
      setValue(POPULATIONTYPE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPopulationTypeWritable()
  {
    return isWritable(POPULATIONTYPE);
  }
  
  public boolean isPopulationTypeReadable()
  {
    return isReadable(POPULATIONTYPE);
  }
  
  public boolean isPopulationTypeModified()
  {
    return isModified(POPULATIONTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getPopulationTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(POPULATIONTYPE).getAttributeMdDTO();
  }
  
  public Integer getYearOfData()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEAROFDATA));
  }
  
  public void setYearOfData(Integer value)
  {
    if(value == null)
    {
      setValue(YEAROFDATA, "");
    }
    else
    {
      setValue(YEAROFDATA, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearOfDataWritable()
  {
    return isWritable(YEAROFDATA);
  }
  
  public boolean isYearOfDataReadable()
  {
    return isReadable(YEAROFDATA);
  }
  
  public boolean isYearOfDataModified()
  {
    return isModified(YEAROFDATA);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearOfDataMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEAROFDATA).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.general.PopulationDataViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.general.PopulationDataViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.general.PopulationDataView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.PopulationDataViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.general.PopulationDataViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.PopulationDataViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.PopulationDataViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.Long getCalculatedPopulation()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.PopulationDataViewDTO.CLASS, "getCalculatedPopulation", _declaredTypes);
    return (java.lang.Long) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.Long getCalculatedPopulation(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.PopulationDataViewDTO.CLASS, "getCalculatedPopulation", _declaredTypes);
    return (java.lang.Long) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.PopulationDataViewDTO[] getFacilityViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.lang.Integer yearOfData)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer"};
    Object[] _parameters = new Object[]{geoId, yearOfData};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.PopulationDataViewDTO.CLASS, "getFacilityViews", _declaredTypes);
    return (dss.vector.solutions.general.PopulationDataViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.PopulationDataViewDTO[] getViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.lang.Integer yearOfData)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Integer"};
    Object[] _parameters = new Object[]{geoId, yearOfData};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.PopulationDataViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.general.PopulationDataViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static PopulationDataViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PopulationDataViewDTO) dto;
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
