package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.surveillance.AbstractGrid;

public class PatientGrid extends PatientGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737073746L;
  
  public PatientGrid()
  {
    super();
  }
  

  public static PatientGrid[] getAll()
  {
    PatientGridQuery query = new PatientGridQuery(new QueryFactory());
    List<PatientGrid> list = AbstractGrid.getAll(query, PatientGrid.class);

    return list.toArray(new PatientGrid[list.size()]);
  }

  public static PatientGrid[] getAllActive()
  {
    PatientGridQuery query = new PatientGridQuery(new QueryFactory());
    List<PatientGrid> list = AbstractGrid.getAllActive(query, PatientGrid.class);

    return list.toArray(new PatientGrid[list.size()]);
  }
}
