package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -522294706)
public abstract class MosquitoCollectionPointViewDTOBase extends dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollectionPointView";
  private static final long serialVersionUID = -522294706;
  
  protected MosquitoCollectionPointViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DATECOLLECTED = "dateCollected";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String TOTAL = "total";
  public java.util.Date getDateCollected()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATECOLLECTED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getDateCollectedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DATECOLLECTED).getAttributeMdDTO();
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
  
  public Integer getTotal()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTAL));
  }
  
  public void setTotal(Integer value)
  {
    if(value == null)
    {
      setValue(TOTAL, "");
    }
    else
    {
      setValue(TOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTotalWritable()
  {
    return isWritable(TOTAL);
  }
  
  public boolean isTotalReadable()
  {
    return isReadable(TOTAL);
  }
  
  public boolean isTotalModified()
  {
    return isModified(TOTAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTotalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTAL).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO[] searchByGeoEntityAndDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.util.Date startDate, java.util.Date endDate)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.util.Date", "java.util.Date"};
    Object[] _parameters = new Object[]{geoId, startDate, endDate};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO.CLASS, "searchByGeoEntityAndDate", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionPointViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MosquitoCollectionPointViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (MosquitoCollectionPointViewDTO) dto;
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
