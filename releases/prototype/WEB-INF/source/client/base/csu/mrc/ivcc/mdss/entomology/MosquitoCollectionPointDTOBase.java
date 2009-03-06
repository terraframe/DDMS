package csu.mrc.ivcc.mdss.entomology;

public abstract class MosquitoCollectionPointDTOBase extends csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollectionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236360385771L;
  
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPoint";
  protected MosquitoCollectionPointDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MosquitoCollectionPointDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COMPOSITECOLLECTION = "compositeCollection";
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
  
  public static final csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO searchByGeoEntityAndDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, csu.mrc.ivcc.mdss.geo.GeoEntityDTO geoEntity, java.util.Date collectionDate)
  {
    String[] _declaredTypes = new String[]{"csu.mrc.ivcc.mdss.geo.GeoEntity", "java.util.Date"};
    Object[] _parameters = new Object[]{geoEntity, collectionDate};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO.CLASS, "searchByGeoEntityAndDate", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO) dto;
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
  
  public static csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointQueryDTO) clientRequest.getAllInstances("csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPoint", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO.CLASS, "lock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO.CLASS, "unlock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
