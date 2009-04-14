package dss.vector.solutions.geo.generated;

public abstract class AbstractSiteDTOBase extends dss.vector.solutions.geo.generated.GeoEntityDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239658590091L;
  
  public final static String CLASS = "dss.vector.solutions.geo.generated.AbstractSite";
  protected AbstractSiteDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AbstractSiteDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String MULTIPOINT = "multiPoint";
  public com.vividsolutions.jts.geom.MultiPoint getMultiPoint()
  {
    return (com.vividsolutions.jts.geom.MultiPoint)getObjectValue(MULTIPOINT);
  }
  
  public void setMultiPoint(com.vividsolutions.jts.geom.MultiPoint value)
  {
    if(value == null)
    {
      setValue(MULTIPOINT, "");
    }
    else
    {
      setValue(MULTIPOINT, value);
    }
  }
  
  public boolean isMultiPointWritable()
  {
    return isWritable(MULTIPOINT);
  }
  
  public boolean isMultiPointReadable()
  {
    return isReadable(MULTIPOINT);
  }
  
  public boolean isMultiPointModified()
  {
    return isModified(MULTIPOINT);
  }
  
  public final com.terraframe.mojo.gis.transport.metadata.AttributeMultiPointMdDTO getMultiPointMd()
  {
    return (com.terraframe.mojo.gis.transport.metadata.AttributeMultiPointMdDTO) getAttributeDTO(MULTIPOINT).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.geo.generated.AbstractSiteDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.geo.generated.AbstractSiteDTO) dto;
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
  
  public static dss.vector.solutions.geo.generated.AbstractSiteQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.geo.generated.AbstractSiteQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.geo.generated.AbstractSite", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.geo.generated.AbstractSiteDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.AbstractSiteDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.AbstractSiteDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.geo.generated.AbstractSiteDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.geo.generated.AbstractSiteDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.geo.generated.AbstractSiteDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
