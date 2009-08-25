package dss.vector.solutions.surveillance;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public abstract class AbstractGrid extends AbstractGridBase implements Reloadable, OptionIF
{
  private static final long serialVersionUID = 1238693165534L;

  public AbstractGrid()
  {
    super();
  }

  protected String buildKey()
  {
    return this.getType()+"."+this.getOptionName();
  }

  public static AbstractGridQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AbstractGridQuery q = new AbstractGridQuery(new QueryFactory());
    q.ORDER_BY_ASC(q.getCreateDate());
    return q;
  }

  protected static <T> List<T> getAll(AbstractGridQuery query, Class<T> c)
  {
    query.ORDER_BY_ASC(query.getOptionName());

    return convertQueryToList(query, c);
  }

  public static <T> List<T> getAllActive(AbstractGridQuery query, Class<T> c)
  {
    query.WHERE(query.getActive().EQ(true));
    query.ORDER_BY_ASC(query.getOptionName());

    return convertQueryToList(query, c);
  }

  @SuppressWarnings("unchecked")
  private static <T> List<T> convertQueryToList(AbstractGridQuery query, Class<T> c)
  {
    List<T> list = new LinkedList<T>();
    OIterator<? extends AbstractGrid> it = query.getIterator();

    while(it.hasNext())
    {
      list.add((T) it.next());
    }

    it.close();

    return list;
  }
}
