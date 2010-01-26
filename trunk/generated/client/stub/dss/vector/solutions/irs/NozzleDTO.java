package dss.vector.solutions.irs;

import com.terraframe.mojo.generation.loader.Reloadable;

public class NozzleDTO extends NozzleDTOBase implements Reloadable
{
  private static final long serialVersionUID = 1240597892276L;

  public NozzleDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  protected NozzleDTO(com.terraframe.mojo.business.BusinessDTO businessDTO,
      com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public String getLabel()
  {
    return this.getDisplayLabel();
  }

  public String getOptionId()
  {
    return this.getId();
  }

}
