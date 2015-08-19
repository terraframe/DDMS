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
  
  public static Supervisor getByCodeAndName(String code, String name, String lastname)
  {
    Supervisor sup;
    
    if (code != null && !code.equals(""))
    {
      sup = Supervisor.getByCode(code);
      
      if (
          sup == null
          || (name != null && !name.equals("") && !name.equals(sup.getPerson().getFirstName()))
          || (lastname != null && !lastname.equals("") && !lastname.equals(sup.getPerson().getLastName()))
          )
      {
        SupervisorCodeProblem prob = new SupervisorCodeProblem();
        prob.setCode(code);
        prob.throwIt();
      }
    }
    else
    {
      sup = Supervisor.getByName(name, lastname);
      
      if (sup == null)
      {
        SupervisorNameProblem prob = new SupervisorNameProblem();
        prob.setName(name);
        prob.setSurname(lastname);
        prob.throwIt();
      }
    }
    
    return sup;
  }
  
  public static Supervisor getByCode(String code)
  {
    SupervisorQuery query = new SupervisorQuery(new QueryFactory());
    Condition condition = query.getCode().EQ(code);
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
