package dss.vector.solutions.ontology;

public class Ontology extends OntologyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -478026297;

  public Ontology()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return buildKey(this.getDefaultNamespace(), this.getTitle());
  }

  public static String buildKey(String defaultNamespace, String title)
  {
    return defaultNamespace+"."+title;
  }

}
