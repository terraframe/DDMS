package dss.vector.solutions.entomology.assay.infectivity;

public abstract class InfectivityAssayTestResultDTOBase extends dss.vector.solutions.entomology.assay.AssayTestResultDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239670270627L;
  
  public final static String CLASS = "dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult";
  protected InfectivityAssayTestResultDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected InfectivityAssayTestResultDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String TESTMETHOD = "testMethod";
  public dss.vector.solutions.mo.InfectivityMethodologyDTO getTestMethod()
  {
    if(getValue(TESTMETHOD) == null || getValue(TESTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(TESTMETHOD));
    }
  }
  
  public void setTestMethod(dss.vector.solutions.mo.InfectivityMethodologyDTO value)
  {
    setValue(TESTMETHOD, value.getId());
  }
  
  public boolean isTestMethodWritable()
  {
    return isWritable(TESTMETHOD);
  }
  
  public boolean isTestMethodReadable()
  {
    return isReadable(TESTMETHOD);
  }
  
  public boolean isTestMethodModified()
  {
    return isModified(TESTMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTestMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TESTMETHOD).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO getInfectivityAssays(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO.CLASS, "getInfectivityAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO) dto;
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
  
  public static dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
