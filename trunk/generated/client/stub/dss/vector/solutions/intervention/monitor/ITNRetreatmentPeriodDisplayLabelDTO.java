package dss.vector.solutions.intervention.monitor;

public class ITNRetreatmentPeriodDisplayLabelDTO extends ITNRetreatmentPeriodDisplayLabelDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252970175034L;
  
  public ITNRetreatmentPeriodDisplayLabelDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given LocalStructDTO into a new DTO.
  * 
  * @param localStructDTO The LocalStructDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ITNRetreatmentPeriodDisplayLabelDTO(com.terraframe.mojo.business.LocalStructDTO localStructDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(localStructDTO, clientRequest);
  }
  
}
