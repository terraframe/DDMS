package dss.vector.solutions.intervention.monitor;

public class FreeITNProviderDisplayLabelDTO extends FreeITNProviderDisplayLabelDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252970216067L;
  
  public FreeITNProviderDisplayLabelDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given LocalStructDTO into a new DTO.
  * 
  * @param localStructDTO The LocalStructDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected FreeITNProviderDisplayLabelDTO(com.terraframe.mojo.business.LocalStructDTO localStructDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(localStructDTO, clientRequest);
  }
  
}
