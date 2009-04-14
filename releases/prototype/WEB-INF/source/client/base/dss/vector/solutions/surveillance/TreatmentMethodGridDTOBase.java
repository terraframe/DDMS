package dss.vector.solutions.surveillance;

public abstract class TreatmentMethodGridDTOBase extends dss.vector.solutions.surveillance.AbstractGridDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239670267430L;
  
  public final static String CLASS = "dss.vector.solutions.surveillance.TreatmentMethodGrid";
  protected TreatmentMethodGridDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TreatmentMethodGridDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static final dss.vector.solutions.surveillance.TreatmentMethodGridDTO[] getAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.TreatmentMethodGridDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.surveillance.TreatmentMethodGridDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllAggregatedCase()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO> getAllAggregatedCaseRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO> getAllAggregatedCaseRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseTreatmentMethodDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseTreatmentMethodDTO addAggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseTreatmentMethodDTO addAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseTreatmentMethodDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public void removeAggregatedCase(dss.vector.solutions.surveillance.CaseTreatmentMethodDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedCase()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public static void removeAllAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseTreatmentMethodDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.TreatmentMethodGridDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.surveillance.TreatmentMethodGridDTO) dto;
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
  
  public static dss.vector.solutions.surveillance.TreatmentMethodGridQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.surveillance.TreatmentMethodGridQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.surveillance.TreatmentMethodGrid", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.surveillance.TreatmentMethodGridDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.TreatmentMethodGridDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.surveillance.TreatmentMethodGridDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.surveillance.TreatmentMethodGridDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.TreatmentMethodGridDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.surveillance.TreatmentMethodGridDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
