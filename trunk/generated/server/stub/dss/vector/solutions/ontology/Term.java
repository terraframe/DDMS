package dss.vector.solutions.ontology;

public abstract class Term extends TermBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253040225616L;
  
  public Term()
  {
    super();
  }
  
  @Override
  protected String buildKey() {
  	// TODO Auto-generated method stub
  	return this.getTermId();
  }
}
