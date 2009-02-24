package mdss.mo;

import mdss.mo.CollectionMethodBase;
import mdss.mo.CollectionMethodQuery;

import com.terraframe.mojo.query.QueryFactory;

public class CollectionMethod extends CollectionMethodBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725413266L;
  
  public CollectionMethod()
  {
    super();
  }
 
  public static java.lang.String[] getAllDisplayLabels()
  {
    return AbstractTerm.getAllDisplayLabels(new CollectionMethodQuery(new QueryFactory()));
  }

}
