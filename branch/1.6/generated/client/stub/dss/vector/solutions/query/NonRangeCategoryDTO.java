package dss.vector.solutions.query;

public class NonRangeCategoryDTO extends NonRangeCategoryDTOBase
 implements com.runwaysdk.generation.loader.Reloadable, NonRangeCategoryIF {
  private static final long serialVersionUID = 1241158039455L;
  
  public NonRangeCategoryDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected NonRangeCategoryDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public String toString()
  {
    return this.getExactValueStr();
  }
  
}
