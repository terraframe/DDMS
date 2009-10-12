package dss.vector.solutions.entomology.assay;

public class InfectivityTestResult extends InfectivityTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255138267919L;
  
  public InfectivityTestResult(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public InfectivityTestResult(dss.vector.solutions.entomology.Mosquito parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
