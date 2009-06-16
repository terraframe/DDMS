package dss.vector.solutions.mo;


import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class ActiveIngredient extends ActiveIngredientBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731976982L;
  
  public ActiveIngredient()
  {
    super();
  }
  
  public static ActiveIngredient[] getAll()
  {
    ActiveIngredientQuery query = new ActiveIngredientQuery(new QueryFactory());
    List<ActiveIngredient> list = AbstractTerm.getAll(query, ActiveIngredient.class);

    return list.toArray(new ActiveIngredient[list.size()]);
  }
  
  public static ActiveIngredient[] getAllActive()
  {
    ActiveIngredientQuery query = new ActiveIngredientQuery(new QueryFactory());
    List<ActiveIngredient> list = AbstractTerm.getAllActive(query, ActiveIngredient.class);

    return list.toArray(new ActiveIngredient[list.size()]);
  }  
}
