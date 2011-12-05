package dss.vector.solutions.querybuilder;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

public class DistinctOrderedLookupBuilder extends OrderedLookupBuilder implements Reloadable
{

  public DistinctOrderedLookupBuilder(ValueQuery query, SelectablePrimitive orderBy, SelectablePrimitive[] selectables, Condition[] conditions, Join[] joins)
  {
    super(query, orderBy, selectables, conditions, joins);
  }

  @Override
  protected void select()
  {
    this.getQuery().SELECT_DISTINCT(this.getSelectables());
  }
}
