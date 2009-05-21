package dss.vector.solutions.surveillance;

import com.terraframe.mojo.query.QueryFactory;

public abstract class AbstractGrid extends AbstractGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693165534L;

  public AbstractGrid()
  {
    super();
  }

  public static AbstractGridQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AbstractGridQuery q = new AbstractGridQuery(new QueryFactory());
    q.ORDER_BY_ASC(q.getCreateDate());
    return q;
  }

}
