package dss.vector.solutions.synchronization;

import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.QueryFactory;

public class ImportLogView extends ImportLogViewBase implements Reloadable
{
  private static final long serialVersionUID = -505154499;
  
  public ImportLogView()
  {
    super();
  }
  
  public static ImportLogViewQuery getQuery(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    ImportLogViewQuery query = new ImportLogViewQuery(new QueryFactory(), sortAttribute, isAscending, pageSize, pageNumber);
    return query;
  }
  
}
