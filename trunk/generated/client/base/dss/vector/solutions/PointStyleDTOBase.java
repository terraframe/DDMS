package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -74013319)
public abstract class PointStyleDTOBase extends dss.vector.solutions.query.GeometryStyleDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PointStyle";
  private static final long serialVersionUID = -74013319;
  
  protected PointStyleDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PointStyleDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String WELLKNOWNNAME = "wellKnownName";
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.query.WellKnownNamesDTO> getWellKnownName()
  {
    return (java.util.List<dss.vector.solutions.query.WellKnownNamesDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.query.WellKnownNames", getEnumNames(WELLKNOWNNAME));
  }
  
  public java.util.List<String> getWellKnownNameEnumNames()
  {
    return getEnumNames(WELLKNOWNNAME);
  }
  
  public void addWellKnownName(dss.vector.solutions.query.WellKnownNamesDTO enumDTO)
  {
    addEnumItem(WELLKNOWNNAME, enumDTO.toString());
  }
  
  public void removeWellKnownName(dss.vector.solutions.query.WellKnownNamesDTO enumDTO)
  {
    removeEnumItem(WELLKNOWNNAME, enumDTO.toString());
  }
  
  public void clearWellKnownName()
  {
    clearEnum(WELLKNOWNNAME);
  }
  
  public boolean isWellKnownNameWritable()
  {
    return isWritable(WELLKNOWNNAME);
  }
  
  public boolean isWellKnownNameReadable()
  {
    return isReadable(WELLKNOWNNAME);
  }
  
  public boolean isWellKnownNameModified()
  {
    return isModified(WELLKNOWNNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getWellKnownNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(WELLKNOWNNAME).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.PointStyleDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.PointStyleDTO) dto;
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
  
  public static dss.vector.solutions.PointStyleQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.PointStyleQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.PointStyle", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.PointStyleDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PointStyleDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.PointStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.PointStyleDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PointStyleDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.PointStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
