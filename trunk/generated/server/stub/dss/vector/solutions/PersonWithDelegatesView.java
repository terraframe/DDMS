package dss.vector.solutions;

import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.ontology.Term;

public class PersonWithDelegatesView extends PersonWithDelegatesViewBase implements com.runwaysdk.generation.loader.Reloadable
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
    
    Selectable selectable = query.getComponentQuery().getSelectableRef(sortAttribute);
    
    if(sortAttribute.equalsIgnoreCase(SEX))
    {
      selectable = ((AttributeReference) selectable.getAttribute()).get(Term.DISPLAY);
    }
    
    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }
    
    return query;
  }

}
