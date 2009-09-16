package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.surveillance.AbstractGrid;

public class NonUseReasonGrid extends NonUseReasonGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252970223465L;
  
  public NonUseReasonGrid()
  {
    super();
  }
 
  public static NonUseReasonGrid[] getAll()
  {
    NonUseReasonGridQuery query = new NonUseReasonGridQuery(new QueryFactory());
    List<NonUseReasonGrid> list = AbstractGrid.getAll(query, NonUseReasonGrid.class);

    return list.toArray(new NonUseReasonGrid[list.size()]);
  }

  public static NonUseReasonGrid[] getAllActive()
  {
    NonUseReasonGridQuery query = new NonUseReasonGridQuery(new QueryFactory());
    List<NonUseReasonGrid> list = AbstractGrid.getAllActive(query, NonUseReasonGrid.class);

    return list.toArray(new NonUseReasonGrid[list.size()]);
  }
}
