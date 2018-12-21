package dss.vector.solutions.util;

import java.nio.charset.Charset;

import javax.servlet.ServletOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.Session;
import com.runwaysdk.session.SessionFacade;

public class SessionInfoController extends SessionInfoControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public SessionInfoController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  public void getSessionTimeLeft() throws java.io.IOException, javax.servlet.ServletException
  {
    String json = requestGetSessionTimeLeft();
    
    ServletOutputStream sos = this.getResponse().getOutputStream();
    sos.write(json.getBytes(Charset.forName("UTF-8")));
    sos.close();
  }
  
  /**
   * We are intentionally executing server code here inside a controller. The reason being that we want to completely bypass permissions
   * and avoid things like "InvalidSessionException" (because this is invoked on login.jsp).
   */
  @Request
  public static OrientationType getSessionLocaleOrientation()
  {
    return LocalizationFacade.getSessionLocaleOrientationNoAuthenticate();
  }
  
  /**
   * We are intentionally executing server code here inside a controller. The reason being that we can't execute an MdMethod because
   * it would refresh the user's session, and we're not trying to do that we're just trying to query information about it.
   */
  @Request
  private String requestGetSessionTimeLeft()
  {
    try
    {
      JSONObject json = new JSONObject();
      
      json.put("sessionTimeLength", Session.getSessionTime());
      
      if (SessionFacade.containsSession(this.getClientRequest().getSessionId()))
      {
        Session session = SessionFacade.getSessionForRequest(this.getClientRequest().getSessionId());
        json.put("sessionTimeLeft", (session.getTimeLeft() / 1000));
      }
      else
      {
        json.put("sessionTimeLeft", -1);
      }
      
      return json.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  public void failGetSessionTimeLeft() throws java.io.IOException, javax.servlet.ServletException
  {
    
  }
  
}
