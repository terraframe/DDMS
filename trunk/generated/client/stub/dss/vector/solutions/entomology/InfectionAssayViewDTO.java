package dss.vector.solutions.entomology;

import com.terraframe.mojo.generation.loader.Reloadable;

public class InfectionAssayViewDTO extends InfectionAssayViewDTOBase implements InfectionAssayIF, Reloadable
{
  private static final long serialVersionUID = 981196528;

  public InfectionAssayViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

}
