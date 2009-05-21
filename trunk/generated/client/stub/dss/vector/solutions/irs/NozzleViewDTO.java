package dss.vector.solutions.irs;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.LabeledDTO;

public class NozzleViewDTO extends NozzleViewDTOBase implements Reloadable, LabeledDTO
{
  private static final long serialVersionUID = 1240597925963L;

  public NozzleViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public String getLabel()
  {
    return this.getDisplayLabel();
  }
}
