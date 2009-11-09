package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -670795112)
public abstract class UniversalLayerDTOBase extends dss.vector.solutions.query.LayerDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.UniversalLayer";
  private static final long serialVersionUID = -670795112;
  
  protected UniversalLayerDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected UniversalLayerDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static final dss.vector.solutions.query.LayerDTO createLayer(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String savedSearchId, java.lang.String layerClass)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{savedSearchId, layerClass};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.UniversalLayerDTO.CLASS, "createLayer", _declaredTypes);
    return (dss.vector.solutions.query.LayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO> getAllFromSavedSearch()
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO> getAllFromSavedSearch(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO>) clientRequestIF.getParents(id, dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.DefinesLayersDTO> getAllFromSavedSearchRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.DefinesLayersDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.DefinesLayersDTO> getAllFromSavedSearchRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.DefinesLayersDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public dss.vector.solutions.query.DefinesLayersDTO addFromSavedSearch(dss.vector.solutions.query.SavedSearchDTO parent)
  {
    return (dss.vector.solutions.query.DefinesLayersDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.DefinesLayersDTO addFromSavedSearch(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.SavedSearchDTO parent)
  {
    return (dss.vector.solutions.query.DefinesLayersDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public void removeFromSavedSearch(dss.vector.solutions.query.DefinesLayersDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeFromSavedSearch(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.DefinesLayersDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllFromSavedSearch()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public static void removeAllFromSavedSearch(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.query.DefinesLayersDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.UniversalLayerDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.UniversalLayerDTO) dto;
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
  
  public static dss.vector.solutions.query.UniversalLayerQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.UniversalLayerQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.UniversalLayer", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.UniversalLayerDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.UniversalLayerDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.UniversalLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.UniversalLayerDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.UniversalLayerDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.UniversalLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
