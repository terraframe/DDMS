package dss.vector.solutions.intervention;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Drug extends DrugBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641310565L;

  public Drug()
  {
    super();
  }

  public static Drug[] getAll()
  {
    DrugQuery query = new DrugQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getDrugName());

    return Drug.convertQueryToArray(query);
  }
  
  public static Drug[] getAllActive()
  {
    DrugQuery query = new DrugQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getDrugName());

    return Drug.convertQueryToArray(query);
  }

  private static Drug[] convertQueryToArray(DrugQuery query)
  {
    List<Drug> drugs = new LinkedList<Drug>();
    OIterator<? extends Drug> it = query.getIterator();

    try
    {
      while(it.hasNext())
      {
        drugs.add(it.next());
      }

      return drugs.toArray(new Drug[drugs.size()]);
    }
    finally
    {
      it.close();
    }
  }

}
