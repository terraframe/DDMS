package dss.vector.solutions.surveillance;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.general.Disease;

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
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getDisplayLabel() != null)
    {
      return this.getDisplayLabel();
    }

    return super.toString();
  }

  protected String buildKey()
  {
    return this.getDisease().getKey() + ": " + this.getStartAge() + " - " + this.getEndAge();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  public static AggregatedAgeGroup[] getAll()
  {
    List<AggregatedAgeGroup> list = new LinkedList<AggregatedAgeGroup>();
    AggregatedAgeGroupQuery query = new AggregatedAgeGroupQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
    query.AND(query.getActive().EQ(true));
    query.ORDER_BY_ASC(query.getEndAge());

    for (AggregatedAgeGroup d : query.getIterator())
    {
      list.add(d);
    }

    return list.toArray(new AggregatedAgeGroup[list.size()]);
  }

  public static AggregatedAgeGroupQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    AggregatedAgeGroupQuery query = new AggregatedAgeGroupQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));

    SelectablePrimitive selectablePrimitive = (SelectablePrimitive) query.get(AggregatedAgeGroup.STARTAGE);

    if (sortAttribute != null)
    {
      selectablePrimitive = (SelectablePrimitive) query.get(sortAttribute);

    }

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectablePrimitive, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC(selectablePrimitive, sortAttribute);
    }

    return query;
  }
}
