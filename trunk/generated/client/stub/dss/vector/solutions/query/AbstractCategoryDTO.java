package dss.vector.solutions.query;

public abstract class AbstractCategoryDTO extends AbstractCategoryDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable, AbstractCategoryIF {
  private static final long serialVersionUID = 1241158097328L;
  
  public AbstractCategoryDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AbstractCategoryDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
