package com.runwaysdk.defaults;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.ClientSession;
import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.constants.ClientConstants;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.MdActionInfo;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.web.ServletUtility;
import com.runwaysdk.web.WebClientSession;

import dss.vector.solutions.MDSSUserDTO;
import dss.vector.solutions.ServerContext;
import dss.vector.solutions.general.DiseaseDTO;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.GlobalSessionListener;

public class LoginController extends LoginControllerBase implements Reloadable
{
  private static final long  serialVersionUID = 1234283350799L;

  public static final String LOGIN_ACTION     = LoginController.class.getName() + ".login" + MdActionInfo.ACTION_SUFFIX;

  public static final String LOGOUT_ACTION    = LoginController.class.getName() + ".logout" + MdActionInfo.ACTION_SUFFIX;

  public LoginController(HttpServletRequest req, HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void login(String username, String password) throws IOException, ServletException
  {
    try
    {
      // Ensure the server context has been initialized
      ServerContext.instance();

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

      DiseaseDTO.setCurrentDimension(clientRequest);

      BusinessDTO user = clientRequest.getSessionUser();
      MDSSUserDTO mdss = (MDSSUserDTO) user;

      String diseaseName = mdss.getDiseaseName();
      String diseaseDisplay = DiseaseDTO.getCurrent(clientRequest).getDimension().getDisplayLabel().getValue();

      req.getSession().setAttribute(MDSSUserDTO.DISEASENAME, diseaseName);
      req.getSession().setAttribute(MDSSUserDTO.DISEASELABEL, diseaseDisplay);
      Map<String, String> menus = new HashMap<String, String>();
      menus.put(diseaseName, DiseaseDTO.getMenuJson(this.getClientRequest()));
      req.getSession().setAttribute("menus", menus);

      req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failLogin(username, password);
      }
    }
  }

  @Override
  public void failLogin(String username, String password) throws IOException, ServletException
  {
    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }

  @Override
  public void logout() throws IOException, ServletException
  {
    // process which logs the user out.
    ClientSession clientSession = super.getClientSession();
    if (clientSession != null)
    {
      clientSession.logout();
    }
    req.getSession().removeAttribute(GlobalSessionListener.GLOBAL_SESSION_LISTENER);
    req.getSession().removeAttribute(ClientConstants.CLIENTSESSION);
    req.getSession().removeAttribute(MDSSUserDTO.DISEASENAME);
    req.getSession().removeAttribute(MDSSUserDTO.DISEASELABEL);
    req.getSession().removeAttribute("menu");
    req.getSession().invalidate();

    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }
}
