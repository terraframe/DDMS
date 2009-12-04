package dss.vector.solutions.ontology;

public class TermViewDTO extends TermViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable, TermComponentIF {
  private static final long serialVersionUID = 1253134809470L;
  
  public TermViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public String getDisplayLabel()
  {
    return this.getTermName() + " (" + this.getTermOntologyId() + ")";
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
