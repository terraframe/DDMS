package dss.vector.solutions.ontology;

public class OntologyRelationship extends OntologyRelationshipBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -2030377686;

  public OntologyRelationship()
  {
    super();
  }

  public String buildKey()
  {
    return this.getRelationshipId();
  }

}
