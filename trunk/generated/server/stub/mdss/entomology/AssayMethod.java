package mdss.entomology;

import mdss.mo.AbstractTerm;

import com.terraframe.mojo.query.QueryFactory;

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
  
  public static java.lang.String[] getAllTermNames()
  {
    return AbstractTerm.getAllTermNames(new AssayMethodQuery(new QueryFactory()));
  }

}
