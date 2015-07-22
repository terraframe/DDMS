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
    
    SessionIterator it = SessionFacade.getIterator();
    try
    {
      while (it.hasNext())
      {
        SessionIF session = it.next();
        
        UserDAO user = session.getUser().getBusinessDAO();
        
        WhoIsOnlineView view = new WhoIsOnlineView();
        view.setUsername(user.getUsername());
        view.setLocale(session.getLocale().getDisplayName());
        views.add(view);
      }
    }
    finally
    {
      it.close();
    }
    
    return views.toArray(new WhoIsOnlineView[views.size()]);
  }
  
}
