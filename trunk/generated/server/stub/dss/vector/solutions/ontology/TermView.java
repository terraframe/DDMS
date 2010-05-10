package dss.vector.solutions.ontology;

public class TermView extends TermViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253134809451L;
  
  public TermView()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    return this.getTermName();
  }
  
}
