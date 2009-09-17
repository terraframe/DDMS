package dss.vector.solutions.ontology;

public class IsA extends IsABase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253039996681L;
  
  public IsA(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public IsA(dss.vector.solutions.ontology.Term parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
}
