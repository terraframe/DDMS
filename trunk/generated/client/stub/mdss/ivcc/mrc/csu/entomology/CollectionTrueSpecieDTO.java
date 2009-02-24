package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecieDTOBase;

public class CollectionTrueSpecieDTO extends CollectionTrueSpecieDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie";
  private static final long serialVersionUID = 1234724385658L;
  
  public CollectionTrueSpecieDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String parentId, String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected CollectionTrueSpecieDTO(com.terraframe.mojo.business.RelationshipDTO relationshipDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
}
