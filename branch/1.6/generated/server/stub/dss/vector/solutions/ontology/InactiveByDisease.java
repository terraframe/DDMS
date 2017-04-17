package dss.vector.solutions.ontology;

public class InactiveByDisease extends InactiveByDiseaseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -502903093;
  
  public InactiveByDisease(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public InactiveByDisease(dss.vector.solutions.ontology.Term parent, dss.vector.solutions.ontology.InactiveProperty child)
  {
    this(parent.getId(), child.getId());
  }
  
}
