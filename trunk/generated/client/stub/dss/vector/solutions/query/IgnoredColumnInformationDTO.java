package dss.vector.solutions.query;

public class IgnoredColumnInformationDTO extends IgnoredColumnInformationDTOBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 127169472;

  public IgnoredColumnInformationDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }

  @Override
  public String getId()
  {
    return "";
  }

  public void setId(String id)
  {
    // Do nothing
  }
}
