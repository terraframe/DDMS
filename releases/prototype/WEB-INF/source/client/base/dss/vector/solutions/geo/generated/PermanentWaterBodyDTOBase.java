package dss.vector.solutions.geo.generated;

public abstract class PermanentWaterBodyDTOBase extends dss.vector.solutions.geo.generated.BreedingSiteDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239075035318L;
  
  public final static String CLASS = "dss.vector.solutions.geo.generated.PermanentWaterBody";
  protected PermanentWaterBodyDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PermanentWaterBodyDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
    return (com.terraframe.mojo.gis.transport.metadata.AttributePolygonMdDTO) getAttributeDTO("polygon").getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.geo.generated.PermanentWaterBodyDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.generated.PermanentWaterBodyDTO) dto;
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
  
  public static dss.vector.solutions.geo.generated.PermanentWaterBodyQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.generated.PermanentWaterBodyQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.geo.generated.PermanentWaterBody", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.generated.PermanentWaterBodyDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.PermanentWaterBodyDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.PermanentWaterBodyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.generated.PermanentWaterBodyDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.PermanentWaterBodyDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.PermanentWaterBodyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
