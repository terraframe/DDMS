package dss.vector.solutions;

import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.MDSSUserViewQuery.NonAdminUserViewBuilder;

public class MDSSUserView extends MDSSUserViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242427875661L;
  
  public MDSSUserView()
  {
    super();
  }
  
  public static MDSSUserViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    QueryFactory factory = new QueryFactory();
    MDSSUserViewQuery query = new MDSSUserViewQuery(factory, new NonAdminUserViewBuilder(factory));

    if (sortAttribute==null)
    {
      sortAttribute = USERNAME;
    }
    
    SelectablePrimitive selectable = (SelectablePrimitive)query.getComponentQuery().getSelectableRef(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC(selectable, sortAttribute);
    }
    
    if (pageSize != 0 && pageNumber != 0)
    {
       query.restrictRows(pageSize, pageNumber);
    }
    
    return query;
  }
}
