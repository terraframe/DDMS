package org.obofoundry.default_namespace;

public abstract class MosquitoAnatomyDTOBase extends org.obofoundry.TermDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.default_namespace.MosquitoAnatomy";
  private static final long serialVersionUID = 1229530633607L;
  
  protected MosquitoAnatomyDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MosquitoAnatomyDTOBase(com.terraframe.mojo.transport.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.default_namespace.MosquitoAnatomyDTO> getAllHas_part()
  {
    return (java.util.List<? extends org.obofoundry.default_namespace.MosquitoAnatomyDTO>) getRequest().getChildren(this.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.default_namespace.MosquitoAnatomyDTO> getAllHas_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.default_namespace.MosquitoAnatomyDTO>) clientRequestIF.getChildren(id, org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.default_namespace.OBO_REL_has_partDTO> getAllHas_partRelationships()
  {
    return (java.util.List<? extends org.obofoundry.default_namespace.OBO_REL_has_partDTO>) getRequest().getChildRelationships(this.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.default_namespace.OBO_REL_has_partDTO> getAllHas_partRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.default_namespace.OBO_REL_has_partDTO>) clientRequestIF.getChildRelationships(id, org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public org.obofoundry.default_namespace.OBO_REL_has_partDTO addHas_part(org.obofoundry.default_namespace.MosquitoAnatomyDTO child)
  {
    return (org.obofoundry.default_namespace.OBO_REL_has_partDTO) getRequest().addChild(this.getId(), child.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public static org.obofoundry.default_namespace.OBO_REL_has_partDTO addHas_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.default_namespace.MosquitoAnatomyDTO child)
  {
    return (org.obofoundry.default_namespace.OBO_REL_has_partDTO) clientRequestIF.addChild(id, child.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public void removeHas_part(org.obofoundry.default_namespace.OBO_REL_has_partDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeHas_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.default_namespace.OBO_REL_has_partDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllHas_part()
  {
    getRequest().deleteChildren(this.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public static void removeAllHas_part(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.default_namespace.MosquitoAnatomyDTO> getAllPart_of()
  {
    return (java.util.List<? extends org.obofoundry.default_namespace.MosquitoAnatomyDTO>) getRequest().getParents(this.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.default_namespace.MosquitoAnatomyDTO> getAllPart_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.default_namespace.MosquitoAnatomyDTO>) clientRequestIF.getParents(id, org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends org.obofoundry.default_namespace.OBO_REL_has_partDTO> getAllPart_ofRelationships()
  {
    return (java.util.List<? extends org.obofoundry.default_namespace.OBO_REL_has_partDTO>) getRequest().getParentRelationships(this.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends org.obofoundry.default_namespace.OBO_REL_has_partDTO> getAllPart_ofRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends org.obofoundry.default_namespace.OBO_REL_has_partDTO>) clientRequestIF.getParentRelationships(id, org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public org.obofoundry.default_namespace.OBO_REL_has_partDTO addPart_of(org.obofoundry.default_namespace.MosquitoAnatomyDTO parent)
  {
    return (org.obofoundry.default_namespace.OBO_REL_has_partDTO) getRequest().addParent(parent.getId(), this.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public static org.obofoundry.default_namespace.OBO_REL_has_partDTO addPart_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, org.obofoundry.default_namespace.MosquitoAnatomyDTO parent)
  {
    return (org.obofoundry.default_namespace.OBO_REL_has_partDTO) clientRequestIF.addParent(parent.getId(), id, org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public void removePart_of(org.obofoundry.default_namespace.OBO_REL_has_partDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removePart_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, org.obofoundry.default_namespace.OBO_REL_has_partDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllPart_of()
  {
    getRequest().deleteParents(this.getId(), org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public static void removeAllPart_of(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, org.obofoundry.default_namespace.OBO_REL_has_partDTO.CLASS);
  }
  
  public static MosquitoAnatomyDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.transport.EntityDTO dto = (com.terraframe.mojo.transport.EntityDTO)clientRequest.get(id);
    
    return (MosquitoAnatomyDTO) dto;
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
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static org.obofoundry.default_namespace.MosquitoAnatomyDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.default_namespace.MosquitoAnatomy", "lock", _declaredTypes);
    return (org.obofoundry.default_namespace.MosquitoAnatomyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static org.obofoundry.default_namespace.MosquitoAnatomyDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.default_namespace.MosquitoAnatomy", "unlock", _declaredTypes);
    return (org.obofoundry.default_namespace.MosquitoAnatomyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
