package dss.vector.solutions.mo;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.LabeledDTO;

public abstract class AbstractTermDTO extends AbstractTermDTOBase implements Reloadable, LabeledDTO
{
  private static final long serialVersionUID = 1234811656650L;

  public AbstractTermDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  protected AbstractTermDTO(com.terraframe.mojo.business.BusinessDTO businessDTO,
      com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public String getLabel()
  {
    return this.getDisplayLabel().getValue();
  }
}
