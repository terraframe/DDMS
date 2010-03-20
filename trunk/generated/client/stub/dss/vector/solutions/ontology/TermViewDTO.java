package dss.vector.solutions.ontology;

public class TermViewDTO extends TermViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable, TermComponentIF {
  private static final long serialVersionUID = 1253134809470L;
  
  public TermViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public String getDisplayLabel()
  {
    return this.getTermName();
  }

  public String getTermComponentDisplay()
  {
    return this.getDisplayLabel();
  }

  public String getTermComponentId()
  {
    return this.getTermId();
  }
  
}
