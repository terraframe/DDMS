package dss.vector.solutions.entomology;

public abstract class MosquitoCollectionDTOBase extends dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239658611530L;
  
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollection";
  protected MosquitoCollectionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MosquitoCollectionDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
  public dss.vector.solutions.mo.CollectionMethodDTO getCollectionMethod()
  {
    if(getValue(COLLECTIONMETHOD) == null || getValue(COLLECTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.CollectionMethodDTO.get(getRequest(), getValue(COLLECTIONMETHOD));
    }
  }
  
  public void setCollectionMethod(dss.vector.solutions.mo.CollectionMethodDTO value)
  {
    setValue(COLLECTIONMETHOD, value.getId());
  }
  
  public boolean isCollectionMethodWritable()
  {
    return isWritable(COLLECTIONMETHOD);
  }
  
  public boolean isCollectionMethodReadable()
  {
    return isReadable(COLLECTIONMETHOD);
  }
  
  public boolean isCollectionMethodModified()
  {
    return isModified(COLLECTIONMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCollectionMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTIONMETHOD).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionDTO searchByGeoEntityAndDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, java.util.Date collectionDate)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.generated.GeoEntity", "java.util.Date"};
    Object[] _parameters = new Object[]{geoEntity, collectionDate};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "searchByGeoEntityAndDate", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionDTO searchByGeoEntityAndDateAndCollectionMethod(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.generated.GeoEntityDTO geoEntity, java.util.Date collectionDate, dss.vector.solutions.mo.CollectionMethodDTO collectionMethod)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.generated.GeoEntity", "java.util.Date", "dss.vector.solutions.mo.CollectionMethod"};
    Object[] _parameters = new Object[]{geoEntity, collectionDate, collectionMethod};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "searchByGeoEntityAndDateAndCollectionMethod", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.MosquitoCollectionDTO) dto;
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
  
  public static dss.vector.solutions.entomology.MosquitoCollectionQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.MosquitoCollectionQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.MosquitoCollection", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.MosquitoCollectionDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
