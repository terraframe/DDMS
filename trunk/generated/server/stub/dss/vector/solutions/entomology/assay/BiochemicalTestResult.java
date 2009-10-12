package dss.vector.solutions.entomology.assay;

public class BiochemicalTestResult extends BiochemicalTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255138301920L;
  
  public BiochemicalTestResult(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public BiochemicalTestResult(dss.vector.solutions.entomology.Mosquito parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
