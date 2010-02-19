package com.terraframe.mojo.defaults;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.ProblemExceptionDTO;
import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.CommonProperties;
import com.terraframe.mojo.constants.MdActionInfo;
import com.terraframe.mojo.web.ServletUtility;
import com.terraframe.mojo.web.WebClientSession;

import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.GlobalSessionListener;

public class LoginController extends LoginControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1234283350799L;

  public static final String LOGIN_ACTION     = LoginController.class.getName() + ".login" + MdActionInfo.ACTION_SUFFIX;

  public static final String LOGOUT_ACTION    = LoginController.class.getName() + ".logout" + MdActionInfo.ACTION_SUFFIX;

  public LoginController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void login(String username, String password) throws java.io.IOException, javax.servlet.ServletException
  {
    try
    {
      Locale[] locales = ServletUtility.getLocales(req);

      WebClientSession clientSession = WebClientSession.createUserSession(username, password, locales);
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
    catch (ProblemExceptionDTO e)
    {
      ErrorUtility.prepareProblems(e, req);

      this.failLogin(username, password);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req);

      this.failLogin(username, password);
    }
  }
  
  @Override
  public void failLogin(String username, String password) throws IOException, ServletException
  {
    req.getRequestDispatcher("login.jsp").forward(req, resp);
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
    req.getSession().removeAttribute(ClientConstants.CLIENTSESSION);
    req.getSession().invalidate();

    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }
}
