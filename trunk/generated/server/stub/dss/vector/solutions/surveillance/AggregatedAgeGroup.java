package dss.vector.solutions.surveillance;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.QueryFactory;

public class AggregatedAgeGroup extends AggregatedAgeGroupBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693152481L;

  public AggregatedAgeGroup()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if(this.getDisplayLabel() != null)
    {
      return this.getDisplayLabel();
    }
    
    return super.toString();
  }

  protected String buildKey()
  {
    return this.getMdView().definesType();
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
