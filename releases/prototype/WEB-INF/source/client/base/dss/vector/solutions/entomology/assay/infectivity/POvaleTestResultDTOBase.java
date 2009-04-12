package dss.vector.solutions.entomology.assay.infectivity;

public abstract class POvaleTestResultDTOBase extends dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239517539910L;
  
  public final static String CLASS = "dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult";
  protected POvaleTestResultDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected POvaleTestResultDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String TESTRESULT = "testResult";
  public Boolean getTestResult()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(TESTRESULT));
  }
  
  public void setTestResult(Boolean value)
  {
    if(value == null)
    {
      setValue(TESTRESULT, "");
    }
    else
    {
      setValue(TESTRESULT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isTestResultWritable()
  {
    return isWritable(TESTRESULT);
  }
  
  public boolean isTestResultReadable()
  {
    return isReadable(TESTRESULT);
  }
  
  public boolean isTestResultModified()
  {
    return isModified(TESTRESULT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getTestResultMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(TESTRESULT).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultDTO) dto;
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
  
  public static dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
