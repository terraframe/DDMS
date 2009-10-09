package dss.vector.solutions.ontology;

public abstract class TermRelationship extends TermRelationshipBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253040116570L;
  
  public TermRelationship(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public TermRelationship(dss.vector.solutions.ontology.Term parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
}
