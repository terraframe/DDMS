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
