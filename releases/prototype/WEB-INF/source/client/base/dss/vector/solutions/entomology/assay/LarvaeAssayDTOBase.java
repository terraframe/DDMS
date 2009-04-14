package dss.vector.solutions.entomology.assay;

public abstract class LarvaeAssayDTOBase extends dss.vector.solutions.entomology.assay.CollectionAssayDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239658588083L;
  
  public final static String CLASS = "dss.vector.solutions.entomology.assay.LarvaeAssay";
  protected LarvaeAssayDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LarvaeAssayDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDPOINT = "endPoint";
  public static java.lang.String STARTPOINT = "startPoint";
  public dss.vector.solutions.mo.LarvaeAgeDTO getEndPoint()
  {
    if(getValue(ENDPOINT) == null || getValue(ENDPOINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.LarvaeAgeDTO.get(getRequest(), getValue(ENDPOINT));
    }
  }
  
  public void setEndPoint(dss.vector.solutions.mo.LarvaeAgeDTO value)
  {
    setValue(ENDPOINT, value.getId());
  }
  
  public boolean isEndPointWritable()
  {
    return isWritable(ENDPOINT);
  }
  
  public boolean isEndPointReadable()
  {
    return isReadable(ENDPOINT);
  }
  
  public boolean isEndPointModified()
  {
    return isModified(ENDPOINT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getEndPointMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENDPOINT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.LarvaeAgeDTO getStartPoint()
  {
    if(getValue(STARTPOINT) == null || getValue(STARTPOINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.LarvaeAgeDTO.get(getRequest(), getValue(STARTPOINT));
    }
  }
  
  public void setStartPoint(dss.vector.solutions.mo.LarvaeAgeDTO value)
  {
    setValue(STARTPOINT, value.getId());
  }
  
  public boolean isStartPointWritable()
  {
    return isWritable(STARTPOINT);
  }
  
  public boolean isStartPointReadable()
  {
    return isReadable(STARTPOINT);
  }
  
  public boolean isStartPointModified()
  {
    return isModified(STARTPOINT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getStartPointMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STARTPOINT).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.entomology.assay.LarvaeTestIntervalViewDTO[] getTestIntervals()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.LarvaeAssayDTO.CLASS, "getTestIntervals", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeTestIntervalViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.LarvaeTestIntervalViewDTO[] getTestIntervals(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.LarvaeAssayDTO.CLASS, "getTestIntervals", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeTestIntervalViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.entomology.assay.LarvaeAssayDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.LarvaeAssayDTO) dto;
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
  
  public static dss.vector.solutions.entomology.assay.LarvaeAssayQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.LarvaeAssayQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.assay.LarvaeAssay", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.LarvaeAssayDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.LarvaeAssayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.LarvaeAssayDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.LarvaeAssayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
