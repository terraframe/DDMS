package dss.vector.solutions;

import com.terraframe.mojo.query.AttributeReference;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;

import dss.vector.solutions.ontology.Term;

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
    
    Selectable selectable = query.getComponentQuery().getSelectableRef(sortAttribute);
    
    if(sortAttribute.equalsIgnoreCase(SEX))
    {
      selectable = ((AttributeReference) selectable.getAttribute()).aAttribute(Term.DISPLAY);
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
