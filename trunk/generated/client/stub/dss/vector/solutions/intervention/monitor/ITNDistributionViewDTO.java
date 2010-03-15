package dss.vector.solutions.intervention.monitor;

public class ITNDistributionViewDTO extends ITNDistributionViewDTOBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255545119455L;

  public ITNDistributionViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public boolean getIsTargetGroupsReadable()
  {
    return this.isTargetGroupsReadable();
  }
}
