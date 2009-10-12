package dss.vector.solutions.entomology.assay;

public class TargetSiteTestResult extends TargetSiteTestResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255138266118L;
  
  public TargetSiteTestResult(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public TargetSiteTestResult(dss.vector.solutions.entomology.Mosquito parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
