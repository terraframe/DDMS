package dss.vector.solutions.entomology;

public abstract class MosquitoCollectionPointDTOBase extends dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239517567794L;
  
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollectionPoint";
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
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionPointDTO searchByGeoEntityAndDateAndCollectionMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, java.util.Date collectionDate, dss.vector.solutions.mo.CollectionMethodDTO collectionMethod)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.generated.GeoEntity", "java.util.Date", "dss.vector.solutions.mo.CollectionMethod"};
    Object[] _parameters = new Object[]{geoEntity, collectionDate, collectionMethod};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionPointDTO.CLASS, "searchByGeoEntityAndDateAndCollectionMethod", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO[] searchByGeoEntityAndDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, java.util.Date startDate, java.util.Date endDate)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.generated.GeoEntity", "java.util.Date", "java.util.Date"};
    Object[] _parameters = new Object[]{geoEntity, startDate, endDate};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionPointDTO.CLASS, "searchByGeoEntityAndDate", _declaredTypes);
    return (dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionPointDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.MosquitoCollectionPointDTO) dto;
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
  
  public static dss.vector.solutions.entomology.MosquitoCollectionPointQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.MosquitoCollectionPointQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.MosquitoCollectionPoint", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionPointDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionPointDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionPointDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionPointDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
