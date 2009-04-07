package dss.vector.solutions.surveillance;

import java.util.LinkedList;
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
    List<TreatmentGrid> list = new LinkedList<TreatmentGrid>();
    TreatmentGridQuery query = new TreatmentGridQuery(new QueryFactory());
    query.WHERE(query.getActive().EQ(true));
    
    for(TreatmentGrid d : query.getIterator())
    {
      list.add(d);
    }
    
    return list.toArray(new TreatmentGrid[list.size()]);
  }

}
