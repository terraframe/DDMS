package dss.vector.solutions.surveillance;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class DiagnosticGrid extends DiagnosticGridBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693138348L;

  public DiagnosticGrid()
  {
    super();
  }

  public static DiagnosticGrid[] getAll()
  {
    DiagnosticGridQuery query = new DiagnosticGridQuery(new QueryFactory());
    List<DiagnosticGrid> list = AbstractGrid.getAll(query, DiagnosticGrid.class);

    return list.toArray(new DiagnosticGrid[list.size()]);
  }

  public static DiagnosticGrid[] getAllActive()
  {
    DiagnosticGridQuery query = new DiagnosticGridQuery(new QueryFactory());
    List<DiagnosticGrid> list = AbstractGrid.getAllActive(query, DiagnosticGrid.class);

    return list.toArray(new DiagnosticGrid[list.size()]);
  }
}
