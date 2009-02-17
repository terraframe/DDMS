package mdss.entomology;

import mdss.mo.AbstractTerm;

import com.terraframe.mojo.query.QueryFactory;

public class CollectionMethod extends CollectionMethodBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725413266L;
  
  public CollectionMethod()
  {
    super();
  }
 
  public static java.lang.String[] getAllTermNames()
  {
    return AbstractTerm.getAllTermNames(new CollectionMethodQuery(new QueryFactory()));
  }

}
