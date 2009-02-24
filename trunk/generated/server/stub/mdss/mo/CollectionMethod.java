package mdss.mo;

import java.util.LinkedList;
import java.util.List;

import mdss.mo.CollectionMethodBase;
import mdss.mo.CollectionMethodQuery;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class CollectionMethod extends CollectionMethodBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725413266L;
  
  public CollectionMethod()
  {
    super();
  }
 
  public static CollectionMethod[] getAll()
  {
    List<CollectionMethod> list = new LinkedList<CollectionMethod>();   
    CollectionMethodQuery query = new CollectionMethodQuery(new QueryFactory());
    OIterator<? extends CollectionMethod> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new CollectionMethod[list.size()]);
  }

}
