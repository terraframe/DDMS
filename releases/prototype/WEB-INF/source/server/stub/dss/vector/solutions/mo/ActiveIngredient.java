package dss.vector.solutions.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.mo.ActiveIngredientBase;
import dss.vector.solutions.mo.ActiveIngredientQuery;

public class ActiveIngredient extends ActiveIngredientBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731976982L;
  
  public ActiveIngredient()
  {
    super();
  }
  
  public static ActiveIngredient[] getAll()
  {
    List<ActiveIngredient> list = new LinkedList<ActiveIngredient>();   
    ActiveIngredientQuery query = new ActiveIngredientQuery(new QueryFactory());
    OIterator<? extends ActiveIngredient> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new ActiveIngredient[list.size()]);
  }  
}
