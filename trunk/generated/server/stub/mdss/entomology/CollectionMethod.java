package mdss.entomology;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
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
    List<String> list = new LinkedList<String>();

    CollectionMethodQuery query = new CollectionMethodQuery(new QueryFactory());
    OIterator<? extends CollectionMethod> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next().getTermName());
    }
    
    return list.toArray(new String[list.size()]);
  }

}
