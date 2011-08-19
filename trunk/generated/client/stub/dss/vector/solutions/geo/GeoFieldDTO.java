package dss.vector.solutions.geo;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.GeoFieldIF;

public class GeoFieldDTO extends GeoFieldDTOBase implements Reloadable, GeoFieldIF
{
  private static final long serialVersionUID = 727056416;

  public GeoFieldDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  /**
   * Copy Constructor: Duplicates the values and attributes of the given
   * BusinessDTO into a new DTO.
   * 
   * @param businessDTO
   *          The BusinessDTO to duplicate
   * @param clientRequest
   *          The clientRequest this DTO should use to communicate with the
   *          server.
   */
  protected GeoFieldDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

}
