package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1174226669)
public abstract class RefreshViewJobDTOBase extends com.runwaysdk.system.scheduler.ExecutableJobDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.RefreshViewJob";
  private static final long serialVersionUID = 1174226669;
  
  protected RefreshViewJobDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected RefreshViewJobDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String MATERIALIZEDTABLE = "materializedTable";
  public com.runwaysdk.system.metadata.MdTableDTO getMaterializedTable()
  {
    if(getValue(MATERIALIZEDTABLE) == null || getValue(MATERIALIZEDTABLE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdTableDTO.get(getRequest(), getValue(MATERIALIZEDTABLE));
    }
  }
  
  public String getMaterializedTableId()
  {
    return getValue(MATERIALIZEDTABLE);
  }
  
  public void setMaterializedTable(com.runwaysdk.system.metadata.MdTableDTO value)
  {
    if(value == null)
    {
      setValue(MATERIALIZEDTABLE, "");
    }
    else
    {
      setValue(MATERIALIZEDTABLE, value.getId());
    }
  }
  
  public boolean isMaterializedTableWritable()
  {
    return isWritable(MATERIALIZEDTABLE);
  }
  
  public boolean isMaterializedTableReadable()
  {
    return isReadable(MATERIALIZEDTABLE);
  }
  
  public boolean isMaterializedTableModified()
  {
    return isModified(MATERIALIZEDTABLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getMaterializedTableMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MATERIALIZEDTABLE).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.RefreshViewJobDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.RefreshViewJobDTO) dto;
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
  
  public static dss.vector.solutions.query.RefreshViewJobQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.RefreshViewJobQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.RefreshViewJobDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.RefreshViewJobDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.RefreshViewJobDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.RefreshViewJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.RefreshViewJobDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.RefreshViewJobDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.RefreshViewJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
