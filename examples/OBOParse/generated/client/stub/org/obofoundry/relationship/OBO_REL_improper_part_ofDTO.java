package org.obofoundry.relationship;

public abstract class OBO_REL_improper_part_ofDTO extends OBO_REL_improper_part_ofDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "org.obofoundry.relationship.OBO_REL_improper_part_of";
  private static final long serialVersionUID = 1229530475157L;
  
  public OBO_REL_improper_part_ofDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String parentId, String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected OBO_REL_improper_part_ofDTO(com.terraframe.mojo.transport.RelationshipDTO relationshipDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
}
