package csu.mrc.ivcc.mdss.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import csu.mrc.ivcc.mdss.mo.CollectionMethodBase;
import csu.mrc.ivcc.mdss.mo.CollectionMethodQuery;

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
