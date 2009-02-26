package mdss.ivcc.mrc.csu.geoserver;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mdss.ivcc.mrc.csu.global.CredentialsSingleton;

/**
 * Filter for the GeoServer webapp to check for a valid session in the MDSS
 * webapp.
 */
public class GeoServerRequestFilter implements Filter
{

  public void destroy()
  {
    // do nothing
  }

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
      ServletException
  {
    HttpServletRequest httpReq = (HttpServletRequest) req;
    HttpServletResponse httpRes = (HttpServletResponse) res;

    Cookie[] cookies = httpReq.getCookies();
    boolean loggedIn = false;
    if (cookies != null)
    {
      for (Cookie cookie : cookies)
      {
        if (cookie.getName().equals(CredentialsSingleton.GLOBAL_SESSION_ID))
        {
          String sessionId = cookie.getValue();
          loggedIn = CredentialsSingleton.getInstance().sessionIdExists(sessionId);
        }
      }
    }

    if (loggedIn)
    {
      chain.doFilter(req, res);
    }
    else
    {
      // TODO send some other error/message?
      httpRes.sendError(403);
    }
  }

  public void init(FilterConfig chain) throws ServletException
  {
    // do nothing
  }

}
