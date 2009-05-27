package dss.vector.solutions.general;

import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.surveillance.PeriodTypeMasterDTO;

public class EpiDateDTO extends EpiDateDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1243373494507L;

  public EpiDateDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public String getDisplayLabel(ClientRequestIF clientRequest)
  {
    PeriodTypeMasterDTO item = this.getPeriodType().get(0).item(clientRequest);

    return item.getDisplayLabel() + " " + this.getPeriod() + " - " + this.getEpiYear();
  }

}
