package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class PatientGrid extends PatientGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737073746L;
  
  public PatientGrid()
  {
    super();
  }
  
  public static PatientGrid[] getAll()
  {
    List<PatientGrid> list = new LinkedList<PatientGrid>();
    PatientGridQuery query = new PatientGridQuery(new QueryFactory());
    query.WHERE(query.getActive().EQ(true));

    for(PatientGrid d : query.getIterator())
    {
      list.add(d);
    }

    return list.toArray(new PatientGrid[list.size()]);
  }
}
