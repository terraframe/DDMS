package dss.vector.solutions.surveillance;

public class TeenCaseViewDTO extends TeenCaseViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239151363747L;

  public TeenCaseViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
