package dss.vector.solutions.surveillance;

public class AdultCaseViewDTO extends AdultCaseViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239135504208L;

  public AdultCaseViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
