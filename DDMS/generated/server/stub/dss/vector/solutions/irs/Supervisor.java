/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
  
  public static Supervisor getByCodeAndName(String code, String name, String lastname, boolean ignoreNull)
  {
    Supervisor sup;
    
    if (code != null && !code.equals(""))
    {
      sup = Supervisor.getByCode(code);
      
      if (
          (sup == null
          || (name != null && !name.equals("") && !name.equals(sup.getPerson().getFirstName()))
          || (lastname != null && !lastname.equals("") && !lastname.equals(sup.getPerson().getLastName())))
          && !ignoreNull
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
      
      if (sup == null && !ignoreNull)
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
