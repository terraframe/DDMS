package org.obofoundry.default_namespace;

public abstract class OBO_REL_has_partDTOBase extends org.obofoundry.relationship.OBO_REL_has_partDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.default_namespace.OBO_REL_has_part";
  private static final long serialVersionUID = 1229530633582L;
  
  public OBO_REL_has_partDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String parentId, java.lang.String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected OBO_REL_has_partDTOBase(com.terraframe.mojo.transport.RelationshipDTO relationshipDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public org.obofoundry.default_namespace.MosquitoAnatomyDTO getParent()
  {
    return org.obofoundry.default_namespace.MosquitoAnatomyDTO.get(getRequest(), super.getParentId());
  }
  
    public org.obofoundry.default_namespace.MosquitoAnatomyDTO getChild()
  {
    return org.obofoundry.default_namespace.MosquitoAnatomyDTO.get(getRequest(), super.getChildId());
  }
  
  public static OBO_REL_has_partDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.transport.RelationshipDTO dto = (com.terraframe.mojo.transport.RelationshipDTO) clientRequest.get(id);
    
    return (OBO_REL_has_partDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createRelationship(this);
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
  
  public static org.obofoundry.default_namespace.OBO_REL_has_partDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.default_namespace.OBO_REL_has_part", "lock", _declaredTypes);
    return (org.obofoundry.default_namespace.OBO_REL_has_partDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static org.obofoundry.default_namespace.OBO_REL_has_partDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.default_namespace.OBO_REL_has_part", "unlock", _declaredTypes);
    return (org.obofoundry.default_namespace.OBO_REL_has_partDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
