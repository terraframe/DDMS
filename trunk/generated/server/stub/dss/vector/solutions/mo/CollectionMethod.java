package dss.vector.solutions.mo;


import java.util.List;

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
    CollectionMethodQuery query = new CollectionMethodQuery(new QueryFactory());
    List<CollectionMethod> list = AbstractTerm.getAll(query, CollectionMethod.class);

    return list.toArray(new CollectionMethod[list.size()]);
  }
  
  public static CollectionMethod[] getAllActive()
  {
    CollectionMethodQuery query = new CollectionMethodQuery(new QueryFactory());
    List<CollectionMethod> list = AbstractTerm.getAllActive(query, CollectionMethod.class);

    return list.toArray(new CollectionMethod[list.size()]);
  }  
}
