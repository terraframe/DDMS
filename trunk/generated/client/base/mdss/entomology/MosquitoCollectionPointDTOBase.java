package mdss.entomology;

public abstract class MosquitoCollectionPointDTOBase extends mdss.entomology.AbstractMosquitoCollectionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "mdss.entomology.MosquitoCollectionPoint";
  private static final long serialVersionUID = 1234203360475L;
  
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
  
  public static final mdss.entomology.MosquitoCollectionPointDTO searchByGeoEntityAndDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, mdss.test.GeoEntityDTO geoEntity, java.util.Date collectionDate)
  {
    String[] _declaredTypes = new String[]{"mdss.test.GeoEntity", "java.util.Date"};
    Object[] _parameters = new Object[]{geoEntity, collectionDate};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.entomology.MosquitoCollectionPointDTO.CLASS, "searchByGeoEntityAndDate", _declaredTypes);
    return (mdss.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static mdss.entomology.MosquitoCollectionPointDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (mdss.entomology.MosquitoCollectionPointDTO) dto;
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
  
  public static mdss.entomology.MosquitoCollectionPointQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (mdss.entomology.MosquitoCollectionPointQueryDTO) clientRequest.getAllInstances("mdss.entomology.MosquitoCollectionPoint", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static mdss.entomology.MosquitoCollectionPointDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.entomology.MosquitoCollectionPointDTO.CLASS, "lock", _declaredTypes);
    return (mdss.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static mdss.entomology.MosquitoCollectionPointDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.entomology.MosquitoCollectionPointDTO.CLASS, "unlock", _declaredTypes);
    return (mdss.entomology.MosquitoCollectionPointDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
