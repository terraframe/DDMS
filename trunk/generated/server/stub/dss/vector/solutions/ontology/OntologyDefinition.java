package dss.vector.solutions.ontology;

public class OntologyDefinition extends OntologyDefinitionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253040235394L;
  
  public OntologyDefinition()
  {
    super();
  }

@Override
protected String buildKey() {
	// TODO Auto-generated method stub
	return this.getNamespace();
}
  
  
  
}
