package dss.vector.solutions.surveillance;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class DiagnosticGrid extends DiagnosticGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693138348L;
  
  public DiagnosticGrid()
  {
    super();
  }
  
  public static DiagnosticGrid[] getAll()
  {
    List<DiagnosticGrid> list = new LinkedList<DiagnosticGrid>();
    DiagnosticGridQuery query = new DiagnosticGridQuery(new QueryFactory());
    query.WHERE(query.getActive().EQ(true));
    
    for(DiagnosticGrid d : query.getIterator())
    {
      list.add(d);
    }
    
    return list.toArray(new DiagnosticGrid[list.size()]);
  }
  
}
