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
    List<Drug> drugs = new LinkedList<Drug>();
    DrugQuery query = new DrugQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getDrugName());

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
