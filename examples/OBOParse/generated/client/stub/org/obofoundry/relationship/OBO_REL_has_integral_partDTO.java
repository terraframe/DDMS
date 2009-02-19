package org.obofoundry.relationship;

public abstract class OBO_REL_has_integral_partDTO extends OBO_REL_has_integral_partDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "org.obofoundry.relationship.OBO_REL_has_integral_part";
  private static final long serialVersionUID = 1229530475099L;
  
  public OBO_REL_has_integral_partDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String parentId, String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected OBO_REL_has_integral_partDTO(com.terraframe.mojo.transport.RelationshipDTO relationshipDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
}
