package dss.vector.solutions.surveillance;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class TreatmentMethodGrid extends TreatmentMethodGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693161406L;
  
  public TreatmentMethodGrid()
  {
    super();
  }

  public static TreatmentMethodGrid[] getAll()
  {
    TreatmentMethodGridQuery query = new TreatmentMethodGridQuery(new QueryFactory());
    List<TreatmentMethodGrid> list = AbstractGrid.getAll(query, TreatmentMethodGrid.class);

    return list.toArray(new TreatmentMethodGrid[list.size()]);
  }

  public static TreatmentMethodGrid[] getAllActive()
  {
    TreatmentMethodGridQuery query = new TreatmentMethodGridQuery(new QueryFactory());
    List<TreatmentMethodGrid> list = AbstractGrid.getAllActive(query, TreatmentMethodGrid.class);

    return list.toArray(new TreatmentMethodGrid[list.size()]);
  }
}
