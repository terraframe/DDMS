package dss.vector.solutions;

import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;

public class PersonWithDelegatesView extends PersonWithDelegatesViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -2036129620;

  public PersonWithDelegatesView()
  {
    super();
  }

  public static PersonWithDelegatesViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    PersonWithDelegatesViewQuery query = new PersonWithDelegatesViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = FIRSTNAME;
    }
    
    Selectable selectable = query.getComponentQuery().getSelectable(sortAttribute);
    
    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) selectable);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) selectable);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }
    
    System.out.println(query.getSQL());

    return query;
  }

}
