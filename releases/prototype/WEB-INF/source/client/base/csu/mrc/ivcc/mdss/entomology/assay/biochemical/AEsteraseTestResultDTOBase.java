package csu.mrc.ivcc.mdss.entomology.assay.biochemical;

public abstract class AEsteraseTestResultDTOBase extends csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236360385027L;
  
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResult";
  protected AEsteraseTestResultDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AEsteraseTestResultDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String TESTRESULT = "testResult";
  public Integer getTestResult()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TESTRESULT));
  }
  
  public void setTestResult(Integer value)
  {
    if(value == null)
    {
      setValue(TESTRESULT, "");
    }
    else
    {
      setValue(TESTRESULT, java.lang.Integer.toString(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTestResultMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("testResult").getAttributeMdDTO();
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultDTO) dto;
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
  
  public static csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultQueryDTO) clientRequest.getAllInstances("csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResult", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultDTO.CLASS, "lock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultDTO.CLASS, "unlock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.biochemical.AEsteraseTestResultDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
