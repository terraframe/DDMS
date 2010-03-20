package dss.vector.solutions.irs;

import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class Supervisor extends SupervisorBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1509403859;

  public Supervisor()
  {
    super();
  }

  public static Supervisor getByName(String supervisorName, String supervisorSurname)
  {
    SupervisorQuery query = new SupervisorQuery(new QueryFactory());

    Condition condition = query.getPerson().getFirstName().EQ(supervisorName);
    condition = AND.get(condition, query.getPerson().getLastName().EQ(supervisorSurname));

    query.WHERE(condition);
    
    OIterator<? extends Supervisor> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
      
      return null;
    }
    finally
    {
      it.close();
    }

  }

  public SupervisorView getView()
  {
    SupervisorView view = new SupervisorView();
    
    view.populateView(this);
    
    return view;
  }

}
