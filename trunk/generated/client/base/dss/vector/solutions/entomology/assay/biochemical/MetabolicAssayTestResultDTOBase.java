package dss.vector.solutions.entomology.assay.biochemical;

@com.terraframe.mojo.business.ClassSignature(hash = -589656981)
public abstract class MetabolicAssayTestResultDTOBase extends dss.vector.solutions.entomology.assay.AssayTestResultDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult";
  private static final long serialVersionUID = -589656981;
  
  protected MetabolicAssayTestResultDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MetabolicAssayTestResultDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultDTO) dto;
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
  
  public static dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
