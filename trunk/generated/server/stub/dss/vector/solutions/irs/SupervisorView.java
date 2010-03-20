package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class SupervisorView extends SupervisorViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -286287312;

  public SupervisorView()
  {
    super();
  }
  
  public void populateView(Supervisor concrete)
  {
    this.populateView(concrete.getPerson());

    this.setSupervisorId(concrete.getId());
  }
  
  public static SupervisorView[] getSupervisors()
  {
    List<SupervisorView> list = new LinkedList<SupervisorView>();
    SupervisorQuery query = new SupervisorQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getPerson().getFirstName());

    OIterator<? extends Supervisor> it = query.getIterator();

    try
    {
      while(it.hasNext())
      {
        Supervisor supervisor = it.next();
        
        list.add(supervisor.getView());
      }
      
      return list.toArray(new SupervisorView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

}
