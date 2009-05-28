package dss.vector.solutions;

import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectablePrimitive;

public class MDSSUserView extends MDSSUserViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242427875661L;
  
  public MDSSUserView()
  {
    super();
  }
  
  public static MDSSUserViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    MDSSUserViewQuery query = new MDSSUserViewQuery(new QueryFactory());

    if (sortAttribute==null)
    {
      sortAttribute = USERNAME;
    }
    
    SelectablePrimitive selectable = (SelectablePrimitive)query.getComponentQuery().getSelectable(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable);
    }
    else
    {
      query.ORDER_BY_DESC(selectable);
    }
    
    if (pageSize != 0 && pageNumber != 0)
    {
       query.restrictRows(pageSize, pageNumber);
    }
    
    return query;
  }
}
