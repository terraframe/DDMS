package dss.vector.solutions.ontology;

public class OntologyHasRelationship extends OntologyHasRelationshipBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1574987883;
  
  public OntologyHasRelationship(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public OntologyHasRelationship(dss.vector.solutions.ontology.Ontology parent, dss.vector.solutions.ontology.OntologyRelationship child)
  {
    this(parent.getId(), child.getId());
  }
  
}
