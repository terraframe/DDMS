package dss.vector.solutions.intervention.monitor;

public class PersonRDTResult extends PersonRDTResultBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255384894949L;
  
  public PersonRDTResult(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public PersonRDTResult(dss.vector.solutions.intervention.monitor.Person parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
