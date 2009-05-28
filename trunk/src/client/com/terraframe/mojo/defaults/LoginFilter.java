package com.terraframe.mojo.defaults;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.web.WebClientSession;

public class LoginFilter implements Filter, Reloadable
{
  private FilterConfig filterConfig;

  public void init(FilterConfig filterConfig) throws ServletException
  {
    this.filterConfig = filterConfig;
  }

  public void destroy()
  {
  }

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
  {
    HttpServletRequest httpReq = (HttpServletRequest) req;
    req.setAttribute("startTime", (Long)(new Date().getTime()));
    HttpServletResponse httpRes = (HttpServletResponse) res;

    HttpSession session = httpReq.getSession();

    WebClientSession clientSession = (WebClientSession)session.getAttribute(ClientConstants.CLIENTSESSION);

    String uri = httpReq.getRequestURI();

    // let some requests pass through
    if(pathAllowed(uri))
    {
      chain.doFilter(req, res);
      return;
    }
    else if(clientSession != null)
    {
      // Create a request object for this request
      ClientRequestIF clientRequest = clientSession.getRequest();

      if (clientRequest.isLoggedIn())
      {
        req.setAttribute(ClientConstants.CLIENTREQUEST, clientRequest);
        chain.doFilter(req, res);
        return;
      }
    }

    // redirect to the login page because the user is not logged in
    filterConfig.getServletContext().getRequestDispatcher("/login.jsp").forward(httpReq, httpRes);
  }

  private boolean pathAllowed(String uri)
  {
    // Allow style files for GIS maps
    if(uri.endsWith(".sld"))
    {
      return true;
    }

    if(uri.endsWith("reload.jsp"))
    {
      return true;
    }

    // Login/Logout requests
    if(uri.endsWith(LoginController.LOGIN_ACTION) || uri.endsWith(LoginController.LOGOUT_ACTION))
    {
      return true;
    }

    return false;
  }
}
