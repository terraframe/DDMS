package dss.vector.solutions.entomology.assay;


public class AdultAgeRangeDTO extends AdultAgeRangeDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1234731978161L;
  
  public AdultAgeRangeDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given StructDTO into a new DTO.
  * 
  * @param structDTO The StructDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AdultAgeRangeDTO(com.runwaysdk.business.StructDTO structDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(structDTO, clientRequest);
  }
  
}
