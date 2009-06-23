package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.surveillance.AbstractGrid;

public class ServiceGrid extends ServiceGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245774490367L;
  
  public ServiceGrid()
  {
    super();
  }
  
  public static ServiceGrid[] getAll()
  {
    ServiceGridQuery query = new ServiceGridQuery(new QueryFactory());
    List<ServiceGrid> list = AbstractGrid.getAll(query, ServiceGrid.class);

    return list.toArray(new ServiceGrid[list.size()]);
  }

  public static ServiceGrid[] getAllActive()
  {
    ServiceGridQuery query = new ServiceGridQuery(new QueryFactory());
    List<ServiceGrid> list = AbstractGrid.getAllActive(query, ServiceGrid.class);

    return list.toArray(new ServiceGrid[list.size()]);
  }
}
