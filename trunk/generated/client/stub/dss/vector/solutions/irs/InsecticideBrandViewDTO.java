package dss.vector.solutions.irs;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.LabeledDTO;

public class InsecticideBrandViewDTO extends InsecticideBrandViewDTOBase implements Reloadable, LabeledDTO
{
  private static final long serialVersionUID = 1240597920724L;

  public InsecticideBrandViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public String getDisplayLabel()
  {
    return this.getBrandName();
  }
}
