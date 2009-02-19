package org.obofoundry.relationship;

public abstract class OBO_REL_agent_inDTOBase extends org.obofoundry.OBORelationshipDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.relationship.OBO_REL_agent_in";
  private static final long serialVersionUID = 1229530475088L;
  
  public OBO_REL_agent_inDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String parentId, java.lang.String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected OBO_REL_agent_inDTOBase(com.terraframe.mojo.transport.RelationshipDTO relationshipDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public org.obofoundry.TermDTO getParent()
  {
    return org.obofoundry.TermDTO.get(getRequest(), super.getParentId());
  }
  
    public org.obofoundry.TermDTO getChild()
  {
    return org.obofoundry.TermDTO.get(getRequest(), super.getChildId());
  }
  
  public static OBO_REL_agent_inDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.transport.RelationshipDTO dto = (com.terraframe.mojo.transport.RelationshipDTO) clientRequest.get(id);
    
    return (OBO_REL_agent_inDTO) dto;
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
  
  public static org.obofoundry.relationship.OBO_REL_agent_inDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.relationship.OBO_REL_agent_in", "lock", _declaredTypes);
    return (org.obofoundry.relationship.OBO_REL_agent_inDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static org.obofoundry.relationship.OBO_REL_agent_inDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.relationship.OBO_REL_agent_in", "unlock", _declaredTypes);
    return (org.obofoundry.relationship.OBO_REL_agent_inDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
