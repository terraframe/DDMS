package dss.vector.solutions.surveillance;

import java.util.LinkedList;
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
    List<TreatmentMethodGrid> list = new LinkedList<TreatmentMethodGrid>();
    TreatmentMethodGridQuery query = new TreatmentMethodGridQuery(new QueryFactory());
    query.WHERE(query.getActive().EQ(true));
    
    for(TreatmentMethodGrid d : query.getIterator())
    {
      list.add(d);
    }
    
    return list.toArray(new TreatmentMethodGrid[list.size()]);
  }

}
