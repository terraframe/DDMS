package dss.vector.solutions.entomology;

import com.runwaysdk.generation.loader.Reloadable;

public class PooledInfectionAssayViewDTO extends PooledInfectionAssayViewDTOBase implements InfectionAssayIF, Reloadable
{
  private static final long serialVersionUID = 1053586196;

  public PooledInfectionAssayViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

}
