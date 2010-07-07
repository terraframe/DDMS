package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.LabeledDTO;

public class InsecticideBrandViewDTO extends InsecticideBrandViewDTOBase implements Reloadable, LabeledDTO
{
  private static final long serialVersionUID = 1240597920724L;

  public InsecticideBrandViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public String getLabel()
  {
    return this.getProductName().getTermDisplayLabel().getValue();
  }

  public String getOptionId()
  {
    return this.getInsecticdeId();
  }
}
