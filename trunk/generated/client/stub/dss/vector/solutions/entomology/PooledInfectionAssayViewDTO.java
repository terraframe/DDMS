package dss.vector.solutions.entomology;

import com.terraframe.mojo.generation.loader.Reloadable;

public class PooledInfectionAssayViewDTO extends PooledInfectionAssayViewDTOBase implements InfectionAssayIF, Reloadable
{
  private static final long serialVersionUID = 1053586196;

  public PooledInfectionAssayViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

}
