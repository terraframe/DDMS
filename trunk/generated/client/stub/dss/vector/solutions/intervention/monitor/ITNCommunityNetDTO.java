package dss.vector.solutions.intervention.monitor;

public class ITNCommunityNetDTO extends ITNCommunityNetDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252612171032L;
  
  public ITNCommunityNetDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String parentId, String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ITNCommunityNetDTO(com.terraframe.mojo.business.RelationshipDTO relationshipDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
}
