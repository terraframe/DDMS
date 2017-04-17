package dss.vector.solutions.intervention.monitor;

public class ITNDistributionViewDTO extends ITNDistributionViewDTOBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255545119455L;

  public ITNDistributionViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public boolean getIsTargetGroupsReadable()
  {
    return this.isTargetGroupsReadable();
  }
}
