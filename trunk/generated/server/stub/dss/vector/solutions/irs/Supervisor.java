package dss.vector.solutions.irs;

import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Supervisor extends SupervisorBase implements com.terraframe.mojo.generation.loader.Reloadable
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
