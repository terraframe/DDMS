package dss.vector.solutions.entomology.assay.molecular;

public abstract class GABASTestResultDTOBase extends dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResultDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239670229916L;
  
  public final static String CLASS = "dss.vector.solutions.entomology.assay.molecular.GABASTestResult";
  protected GABASTestResultDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GABASTestResultDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String TESTRESULT = "testResult";
  public dss.vector.solutions.mo.MolecularAssayResultDTO getTestResult()
  {
    if(getValue(TESTRESULT) == null || getValue(TESTRESULT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(TESTRESULT));
    }
  }
  
  public void setTestResult(dss.vector.solutions.mo.MolecularAssayResultDTO value)
  {
    setValue(TESTRESULT, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTestResultMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TESTRESULT).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.entomology.assay.molecular.GABASTestResultDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.molecular.GABASTestResultDTO) dto;
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
  
  public static dss.vector.solutions.entomology.assay.molecular.GABASTestResultQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.molecular.GABASTestResultQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.assay.molecular.GABASTestResult", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.molecular.GABASTestResultDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.molecular.GABASTestResultDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.molecular.GABASTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.molecular.GABASTestResultDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.molecular.GABASTestResultDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.molecular.GABASTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
