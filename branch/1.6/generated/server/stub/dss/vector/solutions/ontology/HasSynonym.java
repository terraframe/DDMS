package dss.vector.solutions.ontology;

public class HasSynonym extends HasSynonymBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1119245038;
  
  public HasSynonym(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HasSynonym(dss.vector.solutions.ontology.Term parent, dss.vector.solutions.ontology.TermSynonym child)
  {
    this(parent.getId(), child.getId());
  }
  
}
