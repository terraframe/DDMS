package mdss.entomology;

public class IdentificationMethod extends IdentificationMethodBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725407730L;
  
  public IdentificationMethod()
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
