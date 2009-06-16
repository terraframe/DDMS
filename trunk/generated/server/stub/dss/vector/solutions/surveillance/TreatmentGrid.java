package dss.vector.solutions.surveillance;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class TreatmentGrid extends TreatmentGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693147591L;
  
  public TreatmentGrid()
  {
    super();
  }

  public static TreatmentGrid[] getAll()
  {
    TreatmentGridQuery query = new TreatmentGridQuery(new QueryFactory());
    List<TreatmentGrid> list = AbstractGrid.getAll(query, TreatmentGrid.class);

    return list.toArray(new TreatmentGrid[list.size()]);
  }

  public static TreatmentGrid[] getAllActive()
  {
    TreatmentGridQuery query = new TreatmentGridQuery(new QueryFactory());
    List<TreatmentGrid> list = AbstractGrid.getAllActive(query, TreatmentGrid.class);

    return list.toArray(new TreatmentGrid[list.size()]);
  }
}
