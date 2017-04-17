package dss.vector.solutions;

import com.runwaysdk.query.Condition;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.QueryBuilder;

public class Physician extends PhysicianBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -800749037;
  
  public Physician()
  {
    super();
  }
  
  @Override
  public PersonView getView()
  {
    return this.getPerson().getView();
  }
  
  public static ValueQuery searchForPhysician(String search)
  {
    QueryFactory factory = new QueryFactory();
    ValueQuery valueQuery = factory.valueQuery();

    PersonQuery personQuery = new PersonQuery(valueQuery);
    PhysicianQuery leaderQuery = new PhysicianQuery(valueQuery);

    SelectablePrimitive[] selectables = new SelectablePrimitive[] { leaderQuery.getId(PersonView.ID), personQuery.getFirstName(PersonView.FIRSTNAME), personQuery.getLastName(PersonView.LASTNAME) };

    Condition[] conditions = new Condition[] {personQuery.getPhysicianDelegate().EQ(leaderQuery) };

    if (search != null && !search.equals(""))
    {
      String[] array = search.split(" ");
      QueryBuilder.textLookup(valueQuery, factory, array, selectables, selectables, conditions);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, personQuery.getFirstName(PersonView.FIRSTNAME), selectables, conditions);
    }

    valueQuery.restrictRows(15, 1);

    return valueQuery;
  }
  
}
