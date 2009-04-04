package dss.vector.solutions.geo.generated;

public abstract class CityDTOBase extends dss.vector.solutions.geo.generated.PopulatedAreaDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238826342672L;
  
  public final static String CLASS = "dss.vector.solutions.geo.generated.City";
  protected CityDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected CityDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String POINT = "point";
  public com.vividsolutions.jts.geom.Point getPoint()
  {
    return (com.vividsolutions.jts.geom.Point)getObjectValue(POINT);
  }
  
  public void setPoint(com.vividsolutions.jts.geom.Point value)
  {
    if(value == null)
    {
      setValue(POINT, "");
    }
    else
    {
      setValue(POINT, value);
    }
  }
  
  public boolean isPointWritable()
  {
    return isWritable(POINT);
  }
  
  public boolean isPointReadable()
  {
    return isReadable(POINT);
  }
  
  public boolean isPointModified()
  {
    return isModified(POINT);
  }
  
  public final com.terraframe.mojo.gis.transport.metadata.AttributePointMdDTO getPointMd()
  {
    return (com.terraframe.mojo.gis.transport.metadata.AttributePointMdDTO) getAttributeDTO("point").getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.geo.generated.CityDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.generated.CityDTO) dto;
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
  
  public static dss.vector.solutions.geo.generated.CityQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.generated.CityQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.geo.generated.City", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.generated.CityDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.CityDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.CityDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.generated.CityDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.CityDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.CityDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
