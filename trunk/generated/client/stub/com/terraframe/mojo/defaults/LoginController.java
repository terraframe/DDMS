package com.terraframe.mojo.defaults;


import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.CommonProperties;
import com.terraframe.mojo.constants.MdActionInfo;
import com.terraframe.mojo.web.WebClientSession;

import csu.mrc.ivcc.mdss.util.GlobalSessionListener;

public class LoginController extends LoginControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234283350799L;

  public static final String LOGIN_ACTION = LoginController.class.getName()+ ".login" + MdActionInfo.ACTION_SUFFIX;
  
  public static final String LOGOUT_ACTION = LoginController.class.getName()+ ".logout" + MdActionInfo.ACTION_SUFFIX;
  
  public LoginController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void login(String username, String password) throws java.io.IOException,
      javax.servlet.ServletException
  {
    WebClientSession clientSession = WebClientSession.createUserSession(username, password, req.getLocale());
    ClientRequestIF clientRequest = clientSession.getRequest();

    req.getSession().setMaxInactiveInterval(CommonProperties.getSessionTime());
    req.getSession().setAttribute(ClientConstants.CLIENTSESSION, clientSession);
    req.setAttribute(ClientConstants.CLIENTREQUEST, clientRequest);
    

    // create a global cookie for geoserver to read
    GlobalSessionListener globalSessionListener = new GlobalSessionListener(clientSession.getSessionId());
    globalSessionListener.setCookie(this.getResponse());
    req.getSession().setAttribute(GlobalSessionListener.GLOBAL_SESSION_LISTENER, globalSessionListener);

    req.getRequestDispatcher("index.jsp").forward(req, resp);
  }

  @Override
  public void logout() throws java.io.IOException, javax.servlet.ServletException
  {
    // process which logs the user out.
    ClientSession clientSession = super.getClientSession();
    if (clientSession != null)
    {
      clientSession.logout();
    }
    
    req.getSession().removeAttribute(GlobalSessionListener.GLOBAL_SESSION_LISTENER);
    
    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }
}
