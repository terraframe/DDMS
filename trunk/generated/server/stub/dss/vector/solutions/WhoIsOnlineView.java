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
package dss.vector.solutions;

import java.util.ArrayList;

import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.session.SessionIterator;

public class WhoIsOnlineView extends WhoIsOnlineViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -472887093;
  
  public WhoIsOnlineView()
  {
    super();
  }
  
  public static dss.vector.solutions.WhoIsOnlineView[] getViews()
  {
    ArrayList<WhoIsOnlineView> views = new ArrayList<WhoIsOnlineView>();
    
    String pubId = UserDAO.getPublicUser().getId();
    
    SessionIterator it = SessionFacade.getIterator();
    try
    {
      while (it.hasNext())
      {
        SessionIF session = it.next();
        
        UserDAO user = (UserDAO) session.getUser().getBusinessDAO();
        
        if (!pubId.equals(user.getId()))
        {
          WhoIsOnlineView view = new WhoIsOnlineView();
          view.setUsername(user.getUsername());
          view.setLocale(session.getLocale().getDisplayName());
          views.add(view);
        }
      }
    }
    finally
    {
      it.close();
    }
    
    return views.toArray(new WhoIsOnlineView[views.size()]);
  }
  
}
