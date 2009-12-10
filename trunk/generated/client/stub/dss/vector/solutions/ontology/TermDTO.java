package dss.vector.solutions.ontology;

import com.terraframe.mojo.generation.loader.Reloadable;

public class TermDTO extends TermDTOBase implements Reloadable, TermComponentIF
{
  private static final long serialVersionUID = 1253040032600L;

  public TermDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  protected TermDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  public String getDisplayLabel()
  {
    // return this.getName() + " (" + this.getTermId() + ")";
    return this.getDisplay() + " (" + this.getTermId() + ")";
  }

  public static String getFieldKeyName(String className, String attributeName)
  {
    return className + "." + attributeName;
  }

  public String getTermComponentDisplay()
  {
    return getDisplayLabel();
  }

  public String getTermComponentId()
  {
    return getId();
  }
}
