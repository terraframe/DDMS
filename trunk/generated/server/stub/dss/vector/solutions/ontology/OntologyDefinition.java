package dss.vector.solutions.ontology;

public class OntologyDefinition extends OntologyDefinitionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253040066677L;
  
  public OntologyDefinition()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getNamespace()+"."+this.getOntologyName();
  }
  
}
