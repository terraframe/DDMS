package dss.vector.solutions.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.session.Session;

public class SessionInfoUtil extends SessionInfoUtilBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1972291184;
  
  public SessionInfoUtil()
  {
    super();
  }
  
  public static java.lang.String getSessionTimeLeft()
  {
    try
    {
      JSONObject json = new JSONObject();
      
      json.put("sessionTimeLength", Session.getSessionTime());
      
      json.put("sessionTimeLeft", (Session.getCurrentSession().getTimeLeft() / 1000));
      
      return json.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
  
  public static java.lang.String refreshSession()
  {
    return "";
  }
  
}
