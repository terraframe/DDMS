package mdss.entomology;

public class AssayMethod extends AssayMethodBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731975869L;
  
  public AssayMethod()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    return this.getTermName();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getTermName();
  }
}
