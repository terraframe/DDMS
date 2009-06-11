package dss.vector.solutions.intervention.monitor;

public class AggregatedIPTViewDTO extends AggregatedIPTViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1244737040638L;
  
  public AggregatedIPTViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public boolean hasConcreteId()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
  
  public boolean getIsDisplayPatientsReadable()
  {
    return this.isDisplayPatientsReadable();
  }

  public boolean getIsDisplayVisitsReadable()
  {
    return this.isDisplayVisitsReadable();
  }
  
  public boolean getIsDisplayDoseReadable()
  {
    return this.isDisplayDoseReadable();
  }
  
  public boolean getIsDisplayTreatmentsReadable()
  {
    return this.isDisplayTreatmentsReadable();
  }
}
