package dss.vector.solutions.surveillance;

public abstract class TreatmentGridDTOBase extends dss.vector.solutions.surveillance.AbstractGridDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239658600222L;
  
  public final static String CLASS = "dss.vector.solutions.surveillance.TreatmentGrid";
  protected TreatmentGridDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TreatmentGridDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static final dss.vector.solutions.surveillance.TreatmentGridDTO[] getAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.TreatmentGridDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.surveillance.TreatmentGridDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllCaseTreatment()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllCaseTreatment(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO> getAllCaseTreatmentRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO> getAllCaseTreatmentRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentDTO addCaseTreatment(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentDTO addCaseTreatment(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public void removeCaseTreatment(dss.vector.solutions.surveillance.CaseTreatmentDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeCaseTreatment(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllCaseTreatment()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  public static void removeAllCaseTreatment(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllCaseTreatmentStock()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllCaseTreatmentStock(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO> getAllCaseTreatmentStockRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO> getAllCaseTreatmentStockRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentStockDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentStockDTO addCaseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentStockDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentStockDTO addCaseTreatmentStock(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentStockDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public void removeCaseTreatmentStock(dss.vector.solutions.surveillance.CaseTreatmentStockDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeCaseTreatmentStock(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentStockDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllCaseTreatmentStock()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public static void removeAllCaseTreatmentStock(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseTreatmentStockDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.TreatmentGridDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.surveillance.TreatmentGridDTO) dto;
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
  
  public static dss.vector.solutions.surveillance.TreatmentGridQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.surveillance.TreatmentGridQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.surveillance.TreatmentGrid", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.surveillance.TreatmentGridDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.TreatmentGridDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.surveillance.TreatmentGridDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.surveillance.TreatmentGridDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.TreatmentGridDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.surveillance.TreatmentGridDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
