package dss.vector.solutions.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.generation.loader.Reloadable;

public class RedirectUtility implements Reloadable
{
  private HttpServletRequest req;
  private HttpServletResponse resp;
  private Map<String, String> map;
  
  public RedirectUtility(HttpServletRequest req, HttpServletResponse resp)
  {
    this.req = req;
    this.resp = resp;
    this.map = new HashMap<String, String>();
  }
  
  public void put(String key, Object value)
  {
    if(value != null)
    {
      map.put(key, value.toString());
    }
  }

  public boolean checkURL(String controller, String action) throws IOException
  {
    String mojoAction = "." + action + ".mojo";
    
    if (!req.getRequestURI().contains(mojoAction))
    {      
      String path = req.getRequestURL().toString();
      path = path.replaceFirst("(\\w+)Controller", controller);
      path = path.replaceFirst("\\.[a-zA-Z]+\\.mojo", mojoAction);

      this.sendRedirect(path);

      return true;
    }
    
    ErrorUtility.prepareMessages(req);
    
    return false;
  }
  
  public void sendRedirect(String url) throws IOException
  {
    URLUtility utility = new URLUtility(url, map);      
    ErrorUtility.addErrorMessages(req, utility);
    
    if(!resp.isCommitted())
    {
      resp.sendRedirect(utility.getURL());    
    }    
  }
}
