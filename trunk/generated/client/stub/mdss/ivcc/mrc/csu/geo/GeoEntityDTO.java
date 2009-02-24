package mdss.ivcc.mrc.csu.geo;

import mdss.ivcc.mrc.csu.geo.GeoEntityDTOBase;

public class GeoEntityDTO extends GeoEntityDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.geo.GeoEntity";
  private static final long serialVersionUID = 1234288139561L;
  
  public GeoEntityDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GeoEntityDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
