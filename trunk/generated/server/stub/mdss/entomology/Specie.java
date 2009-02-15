package mdss.entomology;

public class Specie extends SpecieBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725411963L;
  
  public Specie()
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
    return super.getTermName();
  }  
}
