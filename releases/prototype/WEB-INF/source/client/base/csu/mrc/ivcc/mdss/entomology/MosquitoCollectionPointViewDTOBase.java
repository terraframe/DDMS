package csu.mrc.ivcc.mdss.entomology;

public abstract class MosquitoCollectionPointViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointView";
  protected MosquitoCollectionPointViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String COMPOSITECOLLECTION = "compositeCollection";
  public static java.lang.String DATECOLLECTED = "dateCollected";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
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
  
  public boolean isCollectionIdWritable()
  {
    return isWritable(COLLECTIONID);
  }
  
  public boolean isCollectionIdReadable()
  {
    return isReadable(COLLECTIONID);
  }
  
  public boolean isCollectionIdModified()
  {
    return isModified(COLLECTIONID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("collectionId").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollectionDTO getCompositeCollection()
  {
    return csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollectionDTO.get(getRequest(), getValue(COMPOSITECOLLECTION));
  }
  
  public void setCompositeCollection(csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollectionDTO value)
  {
    setValue(COMPOSITECOLLECTION, value.getId());
  }
  
  public boolean isCompositeCollectionWritable()
  {
    return isWritable(COMPOSITECOLLECTION);
  }
  
  public boolean isCompositeCollectionReadable()
  {
    return isReadable(COMPOSITECOLLECTION);
  }
  
  public boolean isCompositeCollectionModified()
  {
    return isModified(COMPOSITECOLLECTION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCompositeCollectionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("compositeCollection").getAttributeMdDTO();
  }
  
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
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO("dateCollected").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.geo.GeoEntityDTO getGeoEntity()
  {
    return csu.mrc.ivcc.mdss.geo.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
  }
  
  public void setGeoEntity(csu.mrc.ivcc.mdss.geo.GeoEntityDTO value)
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
