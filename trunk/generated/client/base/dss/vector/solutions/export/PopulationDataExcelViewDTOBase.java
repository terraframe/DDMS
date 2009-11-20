package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -200857611)
public abstract class PopulationDataExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PopulationDataExcelView";
  private static final long serialVersionUID = -200857611;
  
  protected PopulationDataExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GROWTHRATE = "growthRate";
  public static java.lang.String ID = "id";
  public static java.lang.String POPULATION = "population";
  public static java.lang.String YEAROFDATA = "yearOfData";
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
  
  public Double getGrowthRate()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(GROWTHRATE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getGrowthRateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(GROWTHRATE).getAttributeMdDTO();
  }
  
  public Long getPopulation()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(POPULATION));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPopulationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POPULATION).getAttributeMdDTO();
  }
  
  public Integer getYearOfData()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEAROFDATA));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getYearOfDataMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEAROFDATA).getAttributeMdDTO();
  }
  
  public static PopulationDataExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (PopulationDataExcelViewDTO) dto;
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
