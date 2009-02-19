package org.obofoundry;

public abstract class IsADTOBase extends org.obofoundry.OBORelationshipDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.IsA";
  private static final long serialVersionUID = 1229530369009L;
  
  public IsADTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String parentId, java.lang.String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected IsADTOBase(com.terraframe.mojo.transport.RelationshipDTO relationshipDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public org.obofoundry.ComponentDTO getParent()
  {
    return org.obofoundry.ComponentDTO.get(getRequest(), super.getParentId());
  }
  
    public org.obofoundry.ComponentDTO getChild()
  {
    return org.obofoundry.ComponentDTO.get(getRequest(), super.getChildId());
  }
  
  public static IsADTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.transport.RelationshipDTO dto = (com.terraframe.mojo.transport.RelationshipDTO) clientRequest.get(id);
    
    return (IsADTO) dto;
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
  
  public static org.obofoundry.IsADTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.IsA", "lock", _declaredTypes);
    return (org.obofoundry.IsADTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static org.obofoundry.IsADTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.IsA", "unlock", _declaredTypes);
    return (org.obofoundry.IsADTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
