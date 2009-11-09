package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1726389014)
public abstract class AbstractSprayViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.AbstractSprayView";
  private static final long serialVersionUID = 1726389014;
  
  protected AbstractSprayViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BRAND = "brand";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String SPRAYDATA = "sprayData";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYID = "sprayId";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  public static java.lang.String SURFACETYPE = "surfaceType";
  public dss.vector.solutions.irs.InsecticideBrandDTO getBrand()
  {
    if(getValue(BRAND) == null || getValue(BRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(BRAND));
    }
  }
  
  public void setBrand(dss.vector.solutions.irs.InsecticideBrandDTO value)
  {
    if(value == null)
    {
      setValue(BRAND, "");
    }
    else
    {
      setValue(BRAND, value.getId());
    }
  }
  
  public boolean isBrandWritable()
  {
    return isWritable(BRAND);
  }
  
  public boolean isBrandReadable()
  {
    return isReadable(BRAND);
  }
  
  public boolean isBrandModified()
  {
    return isModified(BRAND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getBrandMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BRAND).getAttributeMdDTO();
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
  
  public dss.vector.solutions.irs.SprayDataDTO getSprayData()
  {
    if(getValue(SPRAYDATA) == null || getValue(SPRAYDATA).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayDataDTO.get(getRequest(), getValue(SPRAYDATA));
    }
  }
  
  public void setSprayData(dss.vector.solutions.irs.SprayDataDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYDATA, "");
    }
    else
    {
      setValue(SPRAYDATA, value.getId());
    }
  }
  
  public boolean isSprayDataWritable()
  {
    return isWritable(SPRAYDATA);
  }
  
  public boolean isSprayDataReadable()
  {
    return isReadable(SPRAYDATA);
  }
  
  public boolean isSprayDataModified()
  {
    return isModified(SPRAYDATA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayDataMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYDATA).getAttributeMdDTO();
  }
  
  public java.util.Date getSprayDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void setSprayDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATE, "");
    }
    else
    {
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isSprayDateWritable()
  {
    return isWritable(SPRAYDATE);
  }
  
  public boolean isSprayDateReadable()
  {
    return isReadable(SPRAYDATE);
  }
  
  public boolean isSprayDateModified()
  {
    return isModified(SPRAYDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getSprayDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATE).getAttributeMdDTO();
  }
  
  public String getSprayId()
  {
    return getValue(SPRAYID);
  }
  
  public void setSprayId(String value)
  {
    if(value == null)
    {
      setValue(SPRAYID, "");
    }
    else
    {
      setValue(SPRAYID, value);
    }
  }
  
  public boolean isSprayIdWritable()
  {
    return isWritable(SPRAYID);
  }
  
  public boolean isSprayIdReadable()
  {
    return isReadable(SPRAYID);
  }
  
  public boolean isSprayIdModified()
  {
    return isModified(SPRAYID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSprayIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYID).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.SprayMethodDTO> getSprayMethod()
  {
    return (java.util.List<dss.vector.solutions.irs.SprayMethodDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.irs.SprayMethod", getEnumNames(SPRAYMETHOD));
  }
  
  public java.util.List<String> getSprayMethodEnumNames()
  {
    return getEnumNames(SPRAYMETHOD);
  }
  
  public void addSprayMethod(dss.vector.solutions.irs.SprayMethodDTO enumDTO)
  {
    addEnumItem(SPRAYMETHOD, enumDTO.toString());
  }
  
  public void removeSprayMethod(dss.vector.solutions.irs.SprayMethodDTO enumDTO)
  {
    removeEnumItem(SPRAYMETHOD, enumDTO.toString());
  }
  
  public void clearSprayMethod()
  {
    clearEnum(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodWritable()
  {
    return isWritable(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodReadable()
  {
    return isReadable(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodModified()
  {
    return isModified(SPRAYMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getSprayMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(SPRAYMETHOD).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSurfaceTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SURFACETYPE).getAttributeMdDTO();
  }
  
  public static AbstractSprayViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (AbstractSprayViewDTO) dto;
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
