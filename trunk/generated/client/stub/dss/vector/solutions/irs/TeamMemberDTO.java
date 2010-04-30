package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.PersonDTO;

public class TeamMemberDTO extends TeamMemberDTOBase implements Reloadable
{
  private static final long serialVersionUID = 564195389;

  public TeamMemberDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  protected TeamMemberDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  public String getLabel()
  {
    PersonDTO _person = this.getPerson();
    
    return this.getMemberId() + " - " + _person.getFirstName() + ", " + _person.getLastName();
  }

  public String getOptionId()
  {
    return this.getId();
  }
}
