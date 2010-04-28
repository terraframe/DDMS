package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1447282641)
public abstract class DiseaseMasterDTOBase extends com.runwaysdk.system.EnumerationMasterDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.DiseaseMaster";
  private static final long serialVersionUID = 1447282641;
  
  protected DiseaseMasterDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DiseaseMasterDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String MENUROOT = "menuRoot";
  public dss.vector.solutions.ontology.TermDTO getMenuRoot()
  {
    if(getValue(MENUROOT) == null || getValue(MENUROOT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(MENUROOT));
    }
  }
  
  public void setMenuRoot(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(MENUROOT, "");
    }
    else
    {
      setValue(MENUROOT, value.getId());
    }
  }
  
  public boolean isMenuRootWritable()
  {
    return isWritable(MENUROOT);
  }
  
  public boolean isMenuRootReadable()
  {
    return isReadable(MENUROOT);
  }
  
  public boolean isMenuRootModified()
  {
    return isModified(MENUROOT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getMenuRootMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MENUROOT).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.general.DiseaseMasterDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.general.DiseaseMasterDTO) dto;
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
  
  public static dss.vector.solutions.general.DiseaseMasterQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.DiseaseMasterQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.general.DiseaseMasterDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.DiseaseMasterDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.DiseaseMasterDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.DiseaseMasterDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.DiseaseMasterDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.DiseaseMasterDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.DiseaseMasterDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
