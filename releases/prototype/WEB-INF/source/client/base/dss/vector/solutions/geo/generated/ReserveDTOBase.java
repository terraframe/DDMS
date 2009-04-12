package dss.vector.solutions.geo.generated;

public abstract class ReserveDTOBase extends dss.vector.solutions.geo.generated.GeoEntityDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239572483422L;
  
  public final static String CLASS = "dss.vector.solutions.geo.generated.Reserve";
  protected ReserveDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ReserveDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String POLYGON = "polygon";
  public com.vividsolutions.jts.geom.Polygon getPolygon()
  {
    return (com.vividsolutions.jts.geom.Polygon)getObjectValue(POLYGON);
  }
  
  public void setPolygon(com.vividsolutions.jts.geom.Polygon value)
  {
    if(value == null)
    {
      setValue(POLYGON, "");
    }
    else
    {
      setValue(POLYGON, value);
    }
  }
  
  public boolean isPolygonWritable()
  {
    return isWritable(POLYGON);
  }
  
  public boolean isPolygonReadable()
  {
    return isReadable(POLYGON);
  }
  
  public boolean isPolygonModified()
  {
    return isModified(POLYGON);
  }
  
  public final com.terraframe.mojo.gis.transport.metadata.AttributePolygonMdDTO getPolygonMd()
  {
    return (com.terraframe.mojo.gis.transport.metadata.AttributePolygonMdDTO) getAttributeDTO(POLYGON).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.geo.generated.ReserveDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.generated.ReserveDTO) dto;
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
  
  public static dss.vector.solutions.geo.generated.ReserveQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.generated.ReserveQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.geo.generated.Reserve", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.generated.ReserveDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.ReserveDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.ReserveDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.generated.ReserveDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.ReserveDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.ReserveDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
