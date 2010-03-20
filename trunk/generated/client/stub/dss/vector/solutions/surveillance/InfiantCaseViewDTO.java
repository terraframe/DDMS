package dss.vector.solutions.surveillance;

public class InfiantCaseViewDTO extends InfiantCaseViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239135503362L;

  public InfiantCaseViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  @Override
  public void delete()
  {
    if(this.hasCaseId())
    {
      AggregatedCaseDTO.lock(this.getRequest(), this.getCaseId()).delete();
    }
  }
}
