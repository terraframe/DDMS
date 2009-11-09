package dss.vector.solutions.geo.generated;

@com.terraframe.mojo.business.ClassSignature(hash = -1757531240)
public abstract class CollectionSiteDTOBase extends dss.vector.solutions.geo.generated.GeoEntityDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.generated.CollectionSite";
  private static final long serialVersionUID = -1757531240;
  
  protected CollectionSiteDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected CollectionSiteDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String MULTIPOLYGON = "multiPolygon";
  public com.vividsolutions.jts.geom.MultiPolygon getMultiPolygon()
  {
    return (com.vividsolutions.jts.geom.MultiPolygon)getObjectValue(MULTIPOLYGON);
  }
  
  public void setMultiPolygon(com.vividsolutions.jts.geom.MultiPolygon value)
  {
    if(value == null)
    {
      setValue(MULTIPOLYGON, "");
    }
    else
    {
      setValue(MULTIPOLYGON, value);
    }
  }
  
  public boolean isMultiPolygonWritable()
  {
    return isWritable(MULTIPOLYGON);
  }
  
  public boolean isMultiPolygonReadable()
  {
    return isReadable(MULTIPOLYGON);
  }
  
  public boolean isMultiPolygonModified()
  {
    return isModified(MULTIPOLYGON);
  }
  
  public final com.terraframe.mojo.gis.transport.metadata.AttributeMultiPolygonMdDTO getMultiPolygonMd()
  {
    return (com.terraframe.mojo.gis.transport.metadata.AttributeMultiPolygonMdDTO) getAttributeDTO(MULTIPOLYGON).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.geo.generated.CollectionSiteDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.generated.CollectionSiteDTO) dto;
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
  
  public static dss.vector.solutions.geo.generated.CollectionSiteQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.generated.CollectionSiteQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.geo.generated.CollectionSite", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.generated.CollectionSiteDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.CollectionSiteDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.CollectionSiteDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.generated.CollectionSiteDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.CollectionSiteDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.CollectionSiteDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
