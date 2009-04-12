package dss.vector.solutions.geo.generated;

public abstract class RiverDTOBase extends dss.vector.solutions.geo.generated.GeoEntityDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239572443862L;
  
  public final static String CLASS = "dss.vector.solutions.geo.generated.River";
  protected RiverDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected RiverDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String LINESTRING = "lineString";
  public com.vividsolutions.jts.geom.LineString getLineString()
  {
    return (com.vividsolutions.jts.geom.LineString)getObjectValue(LINESTRING);
  }
  
  public void setLineString(com.vividsolutions.jts.geom.LineString value)
  {
    if(value == null)
    {
      setValue(LINESTRING, "");
    }
    else
    {
      setValue(LINESTRING, value);
    }
  }
  
  public boolean isLineStringWritable()
  {
    return isWritable(LINESTRING);
  }
  
  public boolean isLineStringReadable()
  {
    return isReadable(LINESTRING);
  }
  
  public boolean isLineStringModified()
  {
    return isModified(LINESTRING);
  }
  
  public final com.terraframe.mojo.gis.transport.metadata.AttributeLineStringMdDTO getLineStringMd()
  {
    return (com.terraframe.mojo.gis.transport.metadata.AttributeLineStringMdDTO) getAttributeDTO(LINESTRING).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.geo.generated.RiverDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.generated.RiverDTO) dto;
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
  
  public static dss.vector.solutions.geo.generated.RiverQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.generated.RiverQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.geo.generated.River", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.generated.RiverDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.RiverDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.RiverDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.generated.RiverDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.RiverDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.RiverDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
