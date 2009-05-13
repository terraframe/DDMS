package dss.vector.solutions.surveillance;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.query.QueryFactory;

public class AggregatedAgeGroup extends AggregatedAgeGroupBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693152481L;

  public AggregatedAgeGroup()
  {
    super();
  }

  public static AggregatedAgeGroup[] getAll()
  {
    List<AggregatedAgeGroup> list = new LinkedList<AggregatedAgeGroup>();
    AggregatedAgeGroupQuery query = new AggregatedAgeGroupQuery(new QueryFactory());
    query.WHERE(query.getActive().EQ(true));
    query.ORDER_BY_ASC(query.getEndAge());

    for(AggregatedAgeGroup d : query.getIterator())
    {
      list.add(d);
    }

    return list.toArray(new AggregatedAgeGroup[list.size()]);
  }

  public AggregatedCaseView getView()
  {
    Class<?> clazz = LoaderDecorator.load(this.getMdView().definesType());

    try
    {
      AggregatedCaseView newInstance = (AggregatedCaseView) clazz.newInstance();
      newInstance.setAgeGroup(this);

      return newInstance;
    }
    catch (InstantiationException e)
    {
      throw new RuntimeException(e);
    }
    catch (IllegalAccessException e)
    {
      throw new RuntimeException(e);
    }
  }
}
