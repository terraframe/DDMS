package dss.vector.solutions;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;

import com.runwaysdk.business.rbac.UserDAO;
import com.runwaysdk.session.SessionFacade;
import com.runwaysdk.session.SessionIF;
import com.runwaysdk.session.SessionIterator;
import com.runwaysdk.transport.conversion.json.JSONReturnObject;


public class WhoIsOnlineController extends WhoIsOnlineControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/WhoIsOnline/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1236023121846L;
  
  public WhoIsOnlineController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  @Override
  public void view() throws java.io.IOException, javax.servlet.ServletException
  {
    render("whoIsOnline.jsp");
  }
  
  @Override
  public void failView() throws java.io.IOException, javax.servlet.ServletException
  {
    render("whoIsOnline.jsp");
  }

/**
 *  We're returning JSON that is going to be read by the JSONDataSource. From the JSONDataSource documentation:
 * 
 *   The data portion of this JSON returned from the server is simply a 2 dimensional array, as an example:
 *     [
 *       [ "sun", "red" ]
 *       [ "jeans, "blue ]
 *       [ "paper", "white" ]
 *       [ "dirt", "brown" ]
 *     ]
 *     
 *     In this example, column 1 is the name of the object and column 2 is its color.
 */
  @Override
  public void getUpdateJSON() throws java.io.IOException, javax.servlet.ServletException
  {
    JSONReturnObject jsonRet = new JSONReturnObject();
    
    JSONArray resultSet = new JSONArray();
    
    SessionIterator it = SessionFacade.getIterator();
    try
    {
      while (it.hasNext())
      {
        SessionIF session = it.next();
        
        UserDAO user = session.getUser().getBusinessDAO();
        
        JSONArray arr = new JSONArray();
        arr.put(user.getUsername());
        arr.put(user.getInactive());
        arr.put(session.getLocale().getDisplayName());
        resultSet.put(arr);
      }
    }
    finally
    {
      it.close();
    }
    
    jsonRet.setReturnValue(resultSet);
    
    resp.getWriter().print(jsonRet.toString());
  }
  
  @Override
  public void failGetUpdateJSON() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.getWriter().print(new JSONReturnObject(new JSONArray()).toString());
  }
}
