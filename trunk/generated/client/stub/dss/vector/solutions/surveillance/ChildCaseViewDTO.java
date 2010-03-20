package dss.vector.solutions.surveillance;

public class ChildCaseViewDTO extends ChildCaseViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239151378545L;

  public ChildCaseViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
