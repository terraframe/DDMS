package dss.vector.solutions.surveillance;

public abstract class DiagnosticGridDTOBase extends dss.vector.solutions.surveillance.AbstractGridDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239658580999L;
  
  public final static String CLASS = "dss.vector.solutions.surveillance.DiagnosticGrid";
  protected DiagnosticGridDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DiagnosticGridDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static final dss.vector.solutions.surveillance.DiagnosticGridDTO[] getAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.DiagnosticGridDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.surveillance.DiagnosticGridDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllAggregatedCase()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO> getAllAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.AggregatedCaseDTO>) clientRequestIF.getParents(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO> getAllAggregatedCaseRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO> getAllAggregatedCaseRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.CaseDiagnosticDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.CaseDiagnosticDTO addAggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseDiagnosticDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.CaseDiagnosticDTO addAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.surveillance.AggregatedCaseDTO parent)
  {
    return (dss.vector.solutions.surveillance.CaseDiagnosticDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public void removeAggregatedCase(dss.vector.solutions.surveillance.CaseDiagnosticDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.CaseDiagnosticDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllAggregatedCase()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public static void removeAllAggregatedCase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.surveillance.CaseDiagnosticDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.DiagnosticGridDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.surveillance.DiagnosticGridDTO) dto;
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
  
  public static dss.vector.solutions.surveillance.DiagnosticGridQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.surveillance.DiagnosticGridQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.surveillance.DiagnosticGrid", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.surveillance.DiagnosticGridDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.DiagnosticGridDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.surveillance.DiagnosticGridDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.surveillance.DiagnosticGridDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.DiagnosticGridDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.surveillance.DiagnosticGridDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
