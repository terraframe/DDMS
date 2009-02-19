package org.obofoundry;

public abstract class TermDTO extends TermDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "org.obofoundry.Term";
  private static final long serialVersionUID = 1229530369222L;
  
  public TermDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TermDTO(com.terraframe.mojo.transport.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
